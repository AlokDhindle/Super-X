package com.kryox.dao.Delivery;

import com.kryox.config.Firebaseconfig;
import com.kryox.model.Delivery.DeliveryPartner;
import com.kryox.model.Delivery.PartnerConstants;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import javafx.application.Platform;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DeliveryPartnerDAO {

    private static final String COLLECTION_NAME = "delivery_partners";
    private static ListenerRegistration partnerListener;

    // =========================================================================
    // 1. REGISTRATION METHOD WITH CLOUDINARY & VERIFICATION FIELDS
    // =========================================================================
    public CompletableFuture<String> registerPartnerWithAuth(DeliveryPartner partner, String password) {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Firestore db = Firebaseconfig.gFirestore();

                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(partner.getEmail().trim())
                        .setPassword(password)
                        .setDisplayName(partner.getFullName());

                UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
                String uid = userRecord.getUid();

                partner.setId(uid);
                partner.setCreatedAt(System.currentTimeMillis());

                Map<String, Object> partnerMap = new HashMap<>();
                partnerMap.put("id", uid);
                partnerMap.put("fullName", partner.getFullName());
                partnerMap.put("email", partner.getEmail());
                partnerMap.put("mobile", partner.getMobile());
                partnerMap.put("dob", partner.getDob());
                partnerMap.put("gender", partner.getGender());
                partnerMap.put("address", partner.getAddress());
                partnerMap.put("vehicleType", partner.getVehicleType());
                partnerMap.put("vehicleNumber", partner.getVehicleNumber());
                partnerMap.put("drivingLicense", partner.getDrivingLicense());

                // Cloudinary Image URLs (Normalized across root and path aliases)
                String photoUrl = partner.getProfilePhotoPath() != null ? partner.getProfilePhotoPath() : "";
                String idCardUrl = partner.getIdCardPath() != null ? partner.getIdCardPath() : "";
                String licenseDocUrl = partner.getLicenseDocPath() != null ? partner.getLicenseDocPath() : "";
                String rcBookUrl = partner.getRcBookPath() != null ? partner.getRcBookPath() : "";

                partnerMap.put("profilePhotoUrl", photoUrl);
                partnerMap.put("profilePhotoPath", photoUrl);
                partnerMap.put("idCardUrl", idCardUrl);
                partnerMap.put("idCardPath", idCardUrl);
                partnerMap.put("licenseDocUrl", licenseDocUrl);
                partnerMap.put("licenseDocPath", licenseDocUrl);
                partnerMap.put("rcBookUrl", rcBookUrl);
                partnerMap.put("rcBookPath", rcBookUrl);

                // Document Verification Statuses (Initial: Pending Approval)
                partnerMap.put("licenseStatus", "Pending Approval");
                partnerMap.put("governmentIdStatus", "Pending Approval");
                partnerMap.put("rcBookStatus", "Pending Approval");
                partnerMap.put("insuranceStatus", "Pending Approval");
                partnerMap.put("isAdminApproved", false);
                partnerMap.put("status", "PENDING_APPROVAL");

                // Partner Metrics Defaults
                partnerMap.put("ratingScore", 5.0);
                partnerMap.put("ratingQuote", "\"Fast and always polite! Great service.\"");
                partnerMap.put("totalDeliveries", 0);
                partnerMap.put("completionRate", 100.0);
                partnerMap.put("partnerTier", "Standard Partner");
                partnerMap.put("city", "Pune");

                // Bank & Emergency Details
                partnerMap.put("accountHolder", partner.getAccountHolder());
                partnerMap.put("bankName", partner.getBankName());
                partnerMap.put("accountNumber", partner.getAccountNumber());
                partnerMap.put("ifscCode", partner.getIfscCode());
                partnerMap.put("emergencyContactName", partner.getEmergencyContactName());
                partnerMap.put("emergencyContactPhone", partner.getEmergencyContactPhone());
                partnerMap.put("createdAt", partner.getCreatedAt());

                // Nested verification structure for the Admin Portal
                Map<String, Object> adminReview = new HashMap<>();
                adminReview.put("idCardUrl", idCardUrl);
                adminReview.put("licenseDocUrl", licenseDocUrl);
                adminReview.put("rcBookUrl", rcBookUrl);
                adminReview.put("verificationStatus", "PENDING_APPROVAL");
                adminReview.put("submittedAt", System.currentTimeMillis());
                partnerMap.put("adminVerification", adminReview);

                db.collection(COLLECTION_NAME).document(uid).set(partnerMap).get();
                future.complete(uid);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();

        return future;
    }

    // =========================================================================
    // 2. AUTHENTICATION & SESSION POPULATION
    // =========================================================================
    public CompletableFuture<DeliveryPartner> authenticatePartner(String email, String password) {
        CompletableFuture<DeliveryPartner> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + Firebaseconfig.WEB_API_KEY;
                String jsonPayload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    String body = response.body();
                    String uidKey = "\"localId\": \"";
                    int startIndex = body.indexOf(uidKey) + uidKey.length();
                    int endIndex = body.indexOf("\"", startIndex);
                    String uid = body.substring(startIndex, endIndex);

                    Firestore db = Firebaseconfig.gFirestore();
                    DocumentSnapshot snapshot = db.collection(COLLECTION_NAME).document(uid).get().get();

                    if (snapshot.exists()) {
                        DeliveryPartner partner = snapshot.toObject(DeliveryPartner.class);
                        if (partner == null) {
                            partner = new DeliveryPartner();
                        }

                        partner.setId(uid);
                        PartnerConstants.UID = uid;

                        Map<String, Object> data = snapshot.getData();
                        if (data != null) {
                            // Extract nested adminVerification URLs if root fields are empty
                            extractDocumentUrlsFromData(data, partner);

                            // Sync whole dataset into session constants on the UI thread
                            Platform.runLater(() -> PartnerConstants.setLoggedInPartner(data));
                        }

                        future.complete(partner);
                    } else {
                        future.completeExceptionally(new Exception("User profile not found in database."));
                    }
                } else {
                    String responseBody = response.body();
                    String userError = "Incorrect email or password.";
                    if (responseBody.contains("EMAIL_NOT_FOUND")) {
                        userError = "No account found with this email address.";
                    } else if (responseBody.contains("INVALID_PASSWORD") || responseBody.contains("INVALID_LOGIN_CREDENTIALS")) {
                        userError = "Incorrect password. Please try again.";
                    } else if (responseBody.contains("USER_DISABLED")) {
                        userError = "This account has been suspended by an administrator.";
                    }
                    future.completeExceptionally(new Exception(userError));
                }
            } catch (Exception e) {
                future.completeExceptionally(new Exception("Network or authentication error: " + e.getMessage()));
            }
        }).start();

        return future;
    }

    // =========================================================================
    // 3. REAL-TIME FIRESTORE LISTENER (ADMIN APPROVAL SYNC)
    // =========================================================================
    public static void listenToPartnerUpdates(String uid, Consumer<DocumentSnapshot> onUpdate) {
        if (uid == null || uid.isEmpty()) return;

        if (partnerListener != null) {
            partnerListener.remove();
        }

        Firestore db = Firebaseconfig.gFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(uid);

        partnerListener = docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null || snapshot == null || !snapshot.exists()) {
                return;
            }

            Map<String, Object> data = snapshot.getData();
            if (data != null) {
                Platform.runLater(() -> {
                    PartnerConstants.setLoggedInPartner(data);
                    if (onUpdate != null) {
                        onUpdate.accept(snapshot);
                    }
                });
            }
        });
    }

    // =========================================================================
    // 4. HELPER: EXTRACT DOCUMENT URLS ROBUSTLY (ROOT + NESTED)
    // =========================================================================
    @SuppressWarnings("unchecked")
    private static void extractDocumentUrlsFromData(Map<String, Object> data, DeliveryPartner partner) {
        String profilePhoto = getVal(data, "profilePhotoUrl", "profilePhotoPath");
        String idCard = getVal(data, "idCardUrl", "idCardPath");
        String licenseDoc = getVal(data, "licenseDocUrl", "licenseDocPath");
        String rcBook = getVal(data, "rcBookUrl", "rcBookPath");

        // Fallback to nested adminVerification map if empty at root
        if ((idCard.isEmpty() || licenseDoc.isEmpty() || rcBook.isEmpty()) && data.get("adminVerification") instanceof Map) {
            Map<String, Object> adminMap = (Map<String, Object>) data.get("adminVerification");
            if (idCard.isEmpty()) idCard = getVal(adminMap, "idCardUrl", "idCardPath");
            if (licenseDoc.isEmpty()) licenseDoc = getVal(adminMap, "licenseDocUrl", "licenseDocPath");
            if (rcBook.isEmpty()) rcBook = getVal(adminMap, "rcBookUrl", "rcBookPath");
        }

        if (!profilePhoto.isEmpty()) partner.setProfilePhotoPath(profilePhoto);
        if (!idCard.isEmpty()) partner.setIdCardPath(idCard);
        if (!licenseDoc.isEmpty()) partner.setLicenseDocPath(licenseDoc);
        if (!rcBook.isEmpty()) partner.setRcBookPath(rcBook);
    }

    private static String getVal(Map<String, Object> map, String key1, String key2) {
        Object v1 = map.get(key1);
        if (v1 != null && !v1.toString().trim().isEmpty()) return v1.toString().trim();
        Object v2 = map.get(key2);
        if (v2 != null && !v2.toString().trim().isEmpty()) return v2.toString().trim();
        return "";
    }

    // =========================================================================
    // 5. CLEANUP LISTENER ON LOGOUT
    // =========================================================================
    public static void stopListening() {
        if (partnerListener != null) {
            partnerListener.remove();
            partnerListener = null;
        }
    }
}
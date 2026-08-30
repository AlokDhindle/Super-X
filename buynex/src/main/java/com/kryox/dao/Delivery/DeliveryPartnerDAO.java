package com.kryox.dao.Delivery;

import com.kryox.config.FirebaseConfig;
import com.kryox.model.Delivery.DeliveryPartner;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class DeliveryPartnerDAO {

    private static final String COLLECTION_NAME = "delivery_partners";

    // 1. Register Method (With Admin Verification Fields & Cloudinary Profile Photo)
    public CompletableFuture<String> registerPartnerWithAuth(DeliveryPartner partner, String password) {
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Firestore db = FirebaseConfig.getFireStore();

                UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                        .setEmail(partner.getEmail().trim())
                        .setPassword(password)
                        .setDisplayName(partner.getFullName());

                UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
                String uid = userRecord.getUid();

                partner.setId(uid);
                partner.setCreatedAt(System.currentTimeMillis());

                // Prepare custom map to support both object fields and nested Admin Verification structures
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
                
                // Cloudinary Photo URL for dynamic avatar rendering
                String photoUrl = partner.getProfilePhotoPath() != null ? partner.getProfilePhotoPath() : "";
                partnerMap.put("profilePhotoUrl", photoUrl);
                partnerMap.put("profilePhotoPath", photoUrl);

                // Bank & Emergency Contact
                partnerMap.put("accountHolder", partner.getAccountHolder());
                partnerMap.put("bankName", partner.getBankName());
                partnerMap.put("accountNumber", partner.getAccountNumber());
                partnerMap.put("ifscCode", partner.getIfscCode());
                partnerMap.put("emergencyContactName", partner.getEmergencyContactName());
                partnerMap.put("emergencyContactPhone", partner.getEmergencyContactPhone());
                partnerMap.put("createdAt", partner.getCreatedAt());

                // Nested verification payload for the Admin Portal
                Map<String, Object> adminReview = new HashMap<>();
                adminReview.put("idCardUrl", partner.getIdCardPath() != null ? partner.getIdCardPath() : "");
                adminReview.put("licenseDocUrl", partner.getLicenseDocPath() != null ? partner.getLicenseDocPath() : "");
                adminReview.put("rcBookUrl", partner.getRcBookPath() != null ? partner.getRcBookPath() : "");
                adminReview.put("verificationStatus", "PENDING_APPROVAL");
                adminReview.put("submittedAt", System.currentTimeMillis());
                
                partnerMap.put("adminVerification", adminReview);
                partnerMap.put("status", "PENDING_APPROVAL");

                db.collection(COLLECTION_NAME).document(uid).set(partnerMap).get();
                future.complete(uid);
            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();

        return future;
    }

    // 2. Email + Password Verification & Session Data Retrieval
    public CompletableFuture<DeliveryPartner> authenticatePartner(String email, String password) {
        CompletableFuture<DeliveryPartner> future = new CompletableFuture<>();

        new Thread(() -> {
            try {
                String url = "" + FirebaseConfig.WEB_API_KEY;
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

                    Firestore db = FirebaseConfig.getFireStore();
                    DocumentSnapshot snapshot = db.collection(COLLECTION_NAME).document(uid).get().get();

                    if (snapshot.exists()) {
                        DeliveryPartner partner = snapshot.toObject(DeliveryPartner.class);
                        if (partner == null) {
                            partner = new DeliveryPartner();
                        }

                        // Ensure UID and Cloudinary URLs are set
                        partner.setId(uid);

                        if (snapshot.contains("profilePhotoUrl") && snapshot.getString("profilePhotoUrl") != null) {
                            partner.setProfilePhotoPath(snapshot.getString("profilePhotoUrl"));
                        } else if (snapshot.contains("profilePhotoPath") && snapshot.getString("profilePhotoPath") != null) {
                            partner.setProfilePhotoPath(snapshot.getString("profilePhotoPath"));
                        }

                        future.complete(partner);
                    } else {
                        future.completeExceptionally(new Exception("User profile not found in database."));
                    }
                } else {
                    // Extract exact Firebase error message (e.g., EMAIL_NOT_FOUND, INVALID_PASSWORD)
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
}
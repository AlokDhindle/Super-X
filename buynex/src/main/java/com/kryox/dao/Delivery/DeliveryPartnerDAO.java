package com.kryox.dao.Delivery;

import com.kryox.config.DelivrayFirebaseConfig;
import com.kryox.model.Delivery.DeliveryPartner;
import com.kryox.model.Delivery.PartnerConstants;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.application.Platform;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DeliveryPartnerDAO {

    private static final String COLLECTION_NAME =
            "delivery_partners";

    private static ListenerRegistration partnerListener;

    public CompletableFuture<String> registerPartnerWithAuth(
            DeliveryPartner partner,
            String password
    ) {

        CompletableFuture<String> future =
                new CompletableFuture<>();

        new Thread(() -> {

            try {

                Firestore db =
                        DelivrayFirebaseConfig.getFireStore();

                String signUpUrl =
                        "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                                + DelivrayFirebaseConfig.WEB_API_KEY;

                JSONObject signUpJson =
                        new JSONObject()
                                .put(
                                        "email",
                                        partner.getEmail().trim()
                                )
                                .put(
                                        "password",
                                        password
                                )
                                .put(
                                        "returnSecureToken",
                                        true
                                );

                HttpClient authClient =
                        HttpClient.newHttpClient();

                HttpRequest authRequest =
                        HttpRequest.newBuilder()
                                .uri(
                                        URI.create(signUpUrl)
                                )
                                .header(
                                        "Content-Type",
                                        "application/json"
                                )
                                .POST(
                                        HttpRequest.BodyPublishers.ofString(
                                                signUpJson.toString()
                                        )
                                )
                                .build();

                HttpResponse<String> authResponse =
                        authClient.send(
                                authRequest,
                                HttpResponse.BodyHandlers.ofString()
                        );

                if (authResponse.statusCode() != 200) {

                    String responseBody =
                            authResponse.body();

                    String userError =
                            "Unable to create delivery partner account.";

                    if (responseBody.contains("EMAIL_EXISTS")) {

                        userError =
                                "An account already exists with this email.";

                    } else if (responseBody.contains("WEAK_PASSWORD")) {

                        userError =
                                "Password is too weak. Please use at least 8 characters.";

                    } else if (responseBody.contains("INVALID_EMAIL")) {

                        userError =
                                "Please enter a valid email address.";
                    }

                    throw new Exception(userError);
                }

                JSONObject authResponseJson =
                        new JSONObject(
                                authResponse.body()
                        );

                String uid =
                        authResponseJson.getString(
                                "localId"
                        );

                partner.setId(uid);
                partner.setCreatedAt(
                        System.currentTimeMillis()
                );
Map<String, Object> partnerMap =
                        new HashMap<>();

                partnerMap.put(
                        "id",
                        uid
                );

                partnerMap.put(
                        "fullName",
                        partner.getFullName()
                );

                partnerMap.put(
                        "email",
                        partner.getEmail()
                );

                partnerMap.put(
                        "mobile",
                        partner.getMobile()
                );

                partnerMap.put(
                        "dob",
                        partner.getDob()
                );

                partnerMap.put(
                        "gender",
                        partner.getGender()
                );

                partnerMap.put(
                        "address",
                        partner.getAddress()
                );

                partnerMap.put(
                        "vehicleType",
                        partner.getVehicleType()
                );

                partnerMap.put(
                        "vehicleNumber",
                        partner.getVehicleNumber()
                );

                partnerMap.put(
                        "drivingLicense",
                        partner.getDrivingLicense()
                );

                String photoUrl =
                        partner.getProfilePhotoPath() != null
                                ? partner.getProfilePhotoPath()
                                : "";

                String idCardUrl =
                        partner.getIdCardPath() != null
                                ? partner.getIdCardPath()
                                : "";

                String licenseDocUrl =
                        partner.getLicenseDocPath() != null
                                ? partner.getLicenseDocPath()
                                : "";

                String rcBookUrl =
                        partner.getRcBookPath() != null
                                ? partner.getRcBookPath()
                                : "";

                partnerMap.put(
                        "profilePhotoUrl",
                        photoUrl
                );

                partnerMap.put(
                        "profilePhotoPath",
                        photoUrl
                );

                partnerMap.put(
                        "idCardUrl",
                        idCardUrl
                );

                partnerMap.put(
                        "idCardPath",
                        idCardUrl
                );

                partnerMap.put(
                        "licenseDocUrl",
                        licenseDocUrl
                );

                partnerMap.put(
                        "licenseDocPath",
                        licenseDocUrl
                );

                partnerMap.put(
                        "rcBookUrl",
                        rcBookUrl
                );

                partnerMap.put(
                        "rcBookPath",
                        rcBookUrl
                );
partnerMap.put(
                        "ratingScore",
                        5.0
                );

                partnerMap.put(
                        "ratingQuote",
                        "\"Fast and always polite! Great service.\""
                );

                partnerMap.put(
                        "totalDeliveries",
                        0
                );

                partnerMap.put(
                        "completionRate",
                        100.0
                );

                partnerMap.put(
                        "partnerTier",
                        "Standard Partner"
                );

                partnerMap.put(
                        "city",
                        "Pune"
                );

                partnerMap.put(
                        "accountHolder",
                        partner.getAccountHolder()
                );

                partnerMap.put(
                        "bankName",
                        partner.getBankName()
                );

                partnerMap.put(
                        "accountNumber",
                        partner.getAccountNumber()
                );

                partnerMap.put(
                        "ifscCode",
                        partner.getIfscCode()
                );

                partnerMap.put(
                        "emergencyContactName",
                        partner.getEmergencyContactName()
                );

                partnerMap.put(
                        "emergencyContactPhone",
                        partner.getEmergencyContactPhone()
                );

                partnerMap.put(
                        "createdAt",
                        partner.getCreatedAt()
                );
db.collection(
                        COLLECTION_NAME
                )
                        .document(uid)
                        .set(partnerMap)
                        .get();

                System.out.println(
                        "Delivery Partner saved successfully"
                );

                future.complete(uid);

            } catch (Exception e) {

                future.completeExceptionally(e);
            }

        }).start();

        return future;
    }

    public CompletableFuture<DeliveryPartner> authenticatePartner(
            String email,
            String password
    ) {
        CompletableFuture<DeliveryPartner> future =
                new CompletableFuture<>();

        if (email == null || email.trim().isEmpty() ||
                password == null || password.isEmpty()) {
            future.completeExceptionally(
                    new IllegalArgumentException(
                            "Email and password are required."
                    )
            );
            return future;
        }

        final String loginEmail = email.trim();
        final String loginPassword = password;

        new Thread(() -> {
            try {
                String url =
                        "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                                + DelivrayFirebaseConfig.WEB_API_KEY;

                JSONObject requestJson =
                        new JSONObject()
                                .put("email", loginEmail)
                                .put("password", loginPassword)
                                .put("returnSecureToken", true);

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request =
                        HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Content-Type", "application/json")
                                .POST(HttpRequest.BodyPublishers.ofString(
                                        requestJson.toString()
                                ))
                                .build();

                HttpResponse<String> response =
                        client.send(
                                request,
                                HttpResponse.BodyHandlers.ofString()
                        );

                if (response.statusCode() != 200) {
                    String body = response.body() == null ? "" : response.body();
                    String message = "Incorrect email or password.";

                    if (body.contains("EMAIL_NOT_FOUND") ||
                            body.contains("INVALID_LOGIN_CREDENTIALS")) {
                        message = "Invalid email or password.";
                    } else if (body.contains("INVALID_PASSWORD")) {
                        message = "Incorrect password. Please try again.";
                    } else if (body.contains("USER_DISABLED")) {
                        message = "This account has been disabled.";
                    } else if (body.contains("OPERATION_NOT_ALLOWED")) {
                        message = "Email/password login is disabled in Firebase.";
                    } else if (body.contains("API_KEY_INVALID")) {
                        message = "Firebase Web API key is invalid.";
                    }

                    future.completeExceptionally(new Exception(message));
                    return;
                }

                JSONObject responseJson = new JSONObject(response.body());
                String uid = responseJson.getString("localId");

                Firestore db = DelivrayFirebaseConfig.getFireStore();

                DocumentSnapshot snapshot =
                        db.collection(COLLECTION_NAME)
                                .document(uid)
                                .get()
                                .get();

                if (!snapshot.exists()) {
                    future.completeExceptionally(
                            new Exception(
                                    "Login successful, but delivery partner profile was not found."
                            )
                    );
                    return;
                }

                DeliveryPartner partner =
                        snapshot.toObject(DeliveryPartner.class);

                if (partner == null) {
                    partner = new DeliveryPartner();
                }

                partner.setId(uid);

                Map<String, Object> data = snapshot.getData();

                if (data != null) {
                    extractDocumentUrlsFromData(data, partner);

                    Platform.runLater(() ->
                            PartnerConstants.setLoggedInPartner(data)
                    );
                }

                PartnerConstants.UID = uid;
                future.complete(partner);

            } catch (Exception e) {
                future.completeExceptionally(e);
            }
        }).start();

        return future;
    }

    public List<QueryDocumentSnapshot> getAllPartners() {

        List<QueryDocumentSnapshot> allPartners =
                new ArrayList<>();

        try {

            Firestore db =
                    DelivrayFirebaseConfig.getFireStore();

            QuerySnapshot snapshot =
                    db.collection(
                            COLLECTION_NAME
                    )
                            .get()
                            .get();

            allPartners.addAll(
                    snapshot.getDocuments()
            );

            System.out.println(
                    "Total Delivery Partners = "
                            + allPartners.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Delivery Partner fetch error: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return allPartners;
    }

    public static void listenToPartnerUpdates(
            String uid,
            Consumer<DocumentSnapshot> onUpdate
    ) {

        if (uid == null ||
                uid.isEmpty()) {

            return;
        }

        if (partnerListener != null) {

            partnerListener.remove();
        }

        Firestore db =
                DelivrayFirebaseConfig.getFireStore();

        DocumentReference docRef =
                db.collection(
                        COLLECTION_NAME
                )
                        .document(uid);

        partnerListener =
                docRef.addSnapshotListener(
                        (snapshot, e) -> {

                            if (e != null ||
                                    snapshot == null ||
                                    !snapshot.exists()) {

                                return;
                            }

                            Map<String, Object> data =
                                    snapshot.getData();

                            if (data != null) {

                                Platform.runLater(() -> {

                                    PartnerConstants.setLoggedInPartner(
                                            data
                                    );

                                    if (onUpdate != null) {

                                        onUpdate.accept(
                                                snapshot
                                        );
                                    }
                                });
                            }
                        }
                );
    }

    @SuppressWarnings("unchecked")
    private static void extractDocumentUrlsFromData(
            Map<String, Object> data,
            DeliveryPartner partner
    ) {
        String profilePhoto =
                getVal(data, "profilePhotoUrl", "profilePhotoPath");

        String idCard =
                getVal(data, "idCardUrl", "idCardPath");

        String licenseDoc =
                getVal(data, "licenseDocUrl", "licenseDocPath");

        String rcBook =
                getVal(data, "rcBookUrl", "rcBookPath");

        if (!profilePhoto.isEmpty()) {
            partner.setProfilePhotoPath(profilePhoto);
        }

        if (!idCard.isEmpty()) {
            partner.setIdCardPath(idCard);
        }

        if (!licenseDoc.isEmpty()) {
            partner.setLicenseDocPath(licenseDoc);
        }

        if (!rcBook.isEmpty()) {
            partner.setRcBookPath(rcBook);
        }
    }

    private static String getVal(
            Map<String, Object> map,
            String key1,
            String key2
    ) {

        Object value1 =
                map.get(key1);

        if (value1 != null &&
                !value1.toString().trim().isEmpty()) {

            return value1.toString().trim();
        }

        Object value2 =
                map.get(key2);

        if (value2 != null &&
                !value2.toString().trim().isEmpty()) {

            return value2.toString().trim();
        }

        return "";
    }

    public static void stopListening() {

        if (partnerListener != null) {

            partnerListener.remove();
            partnerListener = null;
        }
    }
}

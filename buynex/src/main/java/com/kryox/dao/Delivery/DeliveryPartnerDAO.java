package com.kryox.dao.Delivery;

import com.kryox.config.DelivrayFirebaseConfig;
import com.kryox.model.Delivery.DeliveryPartner;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;


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

public class DeliveryPartnerDAO {

    private static final String COLLECTION_NAME =
            "delivery_partners";

    public CompletableFuture<String> registerPartnerWithAuth(
            DeliveryPartner partner,
            String password
    ) {

        CompletableFuture<String> future =
                new CompletableFuture<>();

        new Thread(() -> {

            try {

                Firestore db =
                        DelivrayFirebaseConfig
                                .getFireStore();

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

                partner.setApproved(false);
                partner.setStatus(
                        "PENDING_APPROVAL"
                );

                Map<String, Object> partnerMap =
                        new HashMap<>();

                partnerMap.put("id", uid);
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

                partnerMap.put(
                        "profilePhotoUrl",
                        photoUrl
                );
                partnerMap.put(
                        "profilePhotoPath",
                        photoUrl
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

                Map<String, Object> adminReview =
                        new HashMap<>();

                adminReview.put(
                        "idCardUrl",
                        partner.getIdCardPath() != null
                                ? partner.getIdCardPath()
                                : ""
                );

                adminReview.put(
                        "licenseDocUrl",
                        partner.getLicenseDocPath() != null
                                ? partner.getLicenseDocPath()
                                : ""
                );

                adminReview.put(
                        "rcBookUrl",
                        partner.getRcBookPath() != null
                                ? partner.getRcBookPath()
                                : ""
                );

                adminReview.put(
                        "verificationStatus",
                        "PENDING_APPROVAL"
                );

                adminReview.put(
                        "submittedAt",
                        System.currentTimeMillis()
                );

                partnerMap.put(
                        "adminVerification",
                        adminReview
                );

                partnerMap.put(
                        "approved",
                        false
                );

                partnerMap.put(
                        "status",
                        "PENDING_APPROVAL"
                );

                db.collection(
                        COLLECTION_NAME
                )
                        .document(uid)
                        .set(partnerMap)
                        .get();

                System.out.println(
                        "Delivery Partner saved with approved = false"
                );

                future.complete(uid);

            } catch (Exception e) {

                future.completeExceptionally(e);
            }

        }).start();

        return future;
    }

    public CompletableFuture<DeliveryPartner>
            authenticatePartner(
                    String email,
                    String password
            ) {

        CompletableFuture<DeliveryPartner> future =
                new CompletableFuture<>();

        new Thread(() -> {

            try {

                String url =
                        "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                                + DelivrayFirebaseConfig
                                        .WEB_API_KEY;

                JSONObject requestJson =
                        new JSONObject()
                                .put(
                                        "email",
                                        email
                                )
                                .put(
                                        "password",
                                        password
                                )
                                .put(
                                        "returnSecureToken",
                                        true
                                );

                HttpClient client =
                        HttpClient.newHttpClient();

                HttpRequest request =
                        HttpRequest.newBuilder()
                                .uri(
                                        URI.create(url)
                                )
                                .header(
                                        "Content-Type",
                                        "application/json"
                                )
                                .POST(
                                        HttpRequest
                                                .BodyPublishers
                                                .ofString(
                                                        requestJson
                                                                .toString()
                                                )
                                )
                                .build();

                HttpResponse<String> response =
                        client.send(
                                request,
                                HttpResponse
                                        .BodyHandlers
                                        .ofString()
                        );

                if (response.statusCode() == 200) {

                    JSONObject responseJson =
                            new JSONObject(
                                    response.body()
                            );

                    String uid =
                            responseJson.getString(
                                    "localId"
                            );

                    Firestore db =
                            DelivrayFirebaseConfig
                                    .getFireStore();

                    DocumentSnapshot snapshot =
                            db.collection(
                                    COLLECTION_NAME
                            )
                                    .document(uid)
                                    .get()
                                    .get();

                    if (!snapshot.exists()) {

                        future.completeExceptionally(
                                new Exception(
                                        "Delivery partner profile not found."
                                )
                        );

                        return;
                    }

                    Boolean approved =
                            snapshot.getBoolean(
                                    "approved"
                            );

                    String status =
                            snapshot.getString(
                                    "status"
                            );

                    boolean isApproved =
                            Boolean.TRUE.equals(
                                    approved
                            )
                                    || "APPROVED"
                                            .equalsIgnoreCase(
                                                    status
                                            );

                    if (!isApproved) {

                        if ("REJECTED"
                                .equalsIgnoreCase(
                                        status
                                )) {

                            future.completeExceptionally(
                                    new Exception(
                                            "Your delivery partner verification was rejected."
                                    )
                            );

                        } else {

                            future.completeExceptionally(
                                    new Exception(
                                            "Your account is pending for admin approval."
                                    )
                            );
                        }

                        return;
                    }

                    DeliveryPartner partner =
                            snapshot.toObject(
                                    DeliveryPartner.class
                            );

                    if (partner == null) {

                        partner =
                                new DeliveryPartner();
                    }

                    partner.setId(uid);

                    partner.setApproved(true);

                    partner.setStatus(
                            status == null
                                    ? "APPROVED"
                                    : status
                    );

                    if (snapshot.contains(
                            "profilePhotoUrl"
                    )
                            && snapshot.getString(
                                    "profilePhotoUrl"
                            ) != null) {

                        partner.setProfilePhotoPath(
                                snapshot.getString(
                                        "profilePhotoUrl"
                                )
                        );

                    } else if (
                            snapshot.contains(
                                    "profilePhotoPath"
                            )
                                    && snapshot.getString(
                                            "profilePhotoPath"
                                    ) != null
                    ) {

                        partner.setProfilePhotoPath(
                                snapshot.getString(
                                        "profilePhotoPath"
                                )
                        );
                    }

                    future.complete(partner);

                } else {

                    String responseBody =
                            response.body();

                    String userError =
                            "Incorrect email or password.";

                    if (responseBody.contains(
                            "EMAIL_NOT_FOUND"
                    )) {

                        userError =
                                "No account found with this email address.";

                    } else if (
                            responseBody.contains(
                                    "INVALID_PASSWORD"
                            )
                                    || responseBody.contains(
                                            "INVALID_LOGIN_CREDENTIALS"
                                    )
                    ) {

                        userError =
                                "Incorrect password. Please try again.";

                    } else if (
                            responseBody.contains(
                                    "USER_DISABLED"
                            )
                    ) {

                        userError =
                                "This account has been suspended by an administrator.";
                    }

                    future.completeExceptionally(
                            new Exception(
                                    userError
                            )
                    );
                }

            } catch (Exception e) {

                future.completeExceptionally(
                        new Exception(
                                "Network or authentication error: "
                                        + e.getMessage()
                        )
                );
            }

        }).start();

        return future;
    }

    public List<QueryDocumentSnapshot>
            getAllPartners() {

        List<QueryDocumentSnapshot> allPartners =
                new ArrayList<>();

        try {

            Firestore db =
                    DelivrayFirebaseConfig
                            .getFireStore();

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

    public List<QueryDocumentSnapshot>
            getPendingPartners() {

        List<QueryDocumentSnapshot> pending =
                new ArrayList<>();

        try {

            Firestore db =
                    DelivrayFirebaseConfig
                            .getFireStore();

            QuerySnapshot snapshot =
                    db.collection(
                            COLLECTION_NAME
                    )
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Boolean approved =
                        document.getBoolean(
                                "approved"
                        );

                String status =
                        document.getString(
                                "status"
                        );

                boolean isApproved =
                        Boolean.TRUE.equals(
                                approved
                        )
                                || "APPROVED"
                                        .equalsIgnoreCase(
                                                status
                                        );

                boolean isRejected =
                        "REJECTED"
                                .equalsIgnoreCase(
                                        status
                                );

                if (!isApproved &&
                        !isRejected) {

                    pending.add(document);
                }
            }

            System.out.println(
                    "Pending Delivery Partners = "
                            + pending.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Pending Delivery Partner fetch error: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return pending;
    }

    public boolean approvePartner(
            String uid
    ) {

        try {

            Firestore db =
                    DelivrayFirebaseConfig
                            .getFireStore();

            Map<String, Object> updates =
                    new HashMap<>();

            updates.put(
                    "approved",
                    true
            );

            updates.put(
                    "status",
                    "APPROVED"
            );

            updates.put(
                    "adminVerification.verificationStatus",
                    "APPROVED"
            );

            updates.put(
                    "adminVerification.reviewedAt",
                    System.currentTimeMillis()
            );

            db.collection(
                    COLLECTION_NAME
            )
                    .document(uid)
                    .update(updates)
                    .get();

            System.out.println(
                    "Delivery Partner approved: "
                            + uid
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean rejectPartner(
            String uid,
            String reason
    ) {

        try {

            Firestore db =
                    DelivrayFirebaseConfig
                            .getFireStore();

            Map<String, Object> updates =
                    new HashMap<>();

            updates.put(
                    "approved",
                    false
            );

            updates.put(
                    "status",
                    "REJECTED"
            );

            updates.put(
                    "adminVerification.verificationStatus",
                    "REJECTED"
            );

            updates.put(
                    "adminVerification.reviewNote",
                    reason
            );

            updates.put(
                    "adminVerification.reviewedAt",
                    System.currentTimeMillis()
            );

            db.collection(
                    COLLECTION_NAME
            )
                    .document(uid)
                    .update(updates)
                    .get();

            System.out.println(
                    "Delivery Partner rejected: "
                            + uid
            );

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}

package com.kryox.controller.Admin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.kryox.config.Apikey;
import com.kryox.model.Admin.AdminSession;

public class ControllerFirebase {

    private static final String API_KEY =
            Apikey.API_KEY;

    private static final String PROJECT_ID =
            "customer-2ab2e";

    private final HttpClient client =
            HttpClient.newHttpClient();


    // =========================================================
    // OLD SIGNUP
    // Other existing code break hou naye mhanun thevla aahe
    // =========================================================
    public String signUp(
            String email,
            String password
    ) {

        JSONObject result =
                signUpAdmin(
                        email,
                        password
                );

        if (result != null) {

            return result.getString(
                    "idToken"
            );
        }

        return null;
    }


    // =========================================================
    // ADMIN SIGNUP
    // UID + idToken donhi miltil
    // =========================================================
    public JSONObject signUpAdmin(
            String email,
            String password
    ) {

        try {

            JSONObject payload =
                    new JSONObject();

            payload.put(
                    "email",
                    email
            );

            payload.put(
                    "password",
                    password
            );

            payload.put(
                    "returnSecureToken",
                    true
            );

            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                            + API_KEY;

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "Signup Status: "
                            + response.statusCode()
            );

            if (response.statusCode() == 200) {

                JSONObject json =
                        new JSONObject(
                                response.body()
                        );

                System.out.println(
                        "Admin Authentication account created"
                );

                String idToken =
                        json.getString(
                                "idToken"
                        );

                sendEmailVerification(
                        idToken
                );

                return json;
            }

            JSONObject error =
                    new JSONObject(
                            response.body()
                    );

            if (error.has("error")) {

                String message =
                        error
                                .getJSONObject("error")
                                .optString("message");

                System.out.println(
                        "Firebase Signup Error: "
                                + message
                );
            }

            return null;

        } catch (Exception e) {

            System.out.println(
                    "Signup Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return null;
        }
    }


    // =========================================================
    // ADMIN LOGIN
    // 1. Firebase Authentication
    // 2. UID
    // 3. Firestore admins/{UID}
    // 4. role == Admin
    // =========================================================
    public boolean login(
            String email,
            String password
    ) {

        clearAdminSession();

        try {

            JSONObject payload =
                    new JSONObject();

            payload.put(
                    "email",
                    email
            );

            payload.put(
                    "password",
                    password
            );

            payload.put(
                    "returnSecureToken",
                    true
            );

            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                            + API_KEY;

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "Login Status: "
                            + response.statusCode()
            );

            // Firebase Authentication failed
            if (response.statusCode() != 200) {

                try {

                    JSONObject errorJson =
                            new JSONObject(
                                    response.body()
                            );

                    String firebaseMessage =
                            errorJson
                                    .getJSONObject("error")
                                    .optString(
                                            "message",
                                            "UNKNOWN_ERROR"
                                    );

                    System.out.println(
                            "Firebase Login Error: "
                                    + firebaseMessage
                    );

                } catch (Exception ex) {

                    System.out.println(
                            "Firebase Login Error Body: "
                                    + response.body()
                    );
                }

                return false;
            }

            JSONObject json =
                    new JSONObject(
                            response.body()
                    );

            String uid =
                    json.getString(
                            "localId"
                    );

            String idToken =
                    json.getString(
                            "idToken"
                    );

            String firebaseEmail =
                    json.getString(
                            "email"
                    );

            System.out.println(
                    "Authentication successful"
            );


            // =================================================
            // REAL EMAIL VERIFICATION CHECK
            // User must click verification link sent to email
            // =================================================

            boolean emailVerified =
                    isEmailVerified(
                            idToken
                    );


            if (!emailVerified) {

                System.out.println(
                        "Access Denied: Email is not verified"
                );

                return false;
            }


            System.out.println(
                    "Email verified successfully"
            );


            System.out.println(
                    "Checking Admin permission..."
            );


            // =================================================
            // CHECK FIRESTORE admins/{UID}
            // =================================================

            String adminUrl =
                    "https://firestore.googleapis.com/v1/projects/"
                            + PROJECT_ID
                            + "/databases/(default)/documents/admins/"
                            + uid;

            HttpRequest adminRequest =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            adminUrl
                                    )
                            )
                            .header(
                                    "Authorization",
                                    "Bearer " + idToken
                            )
                            .GET()
                            .build();

            HttpResponse<String> adminResponse =
                    client.send(
                            adminRequest,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "Admin Check Status: "
                            + adminResponse.statusCode()
            );


            // admins/{UID} document nasel
            if (adminResponse.statusCode() != 200) {

                System.out.println(
                        "Access Denied: Email is not registered as Admin"
                );

                return false;
            }


            JSONObject adminJson =
                    new JSONObject(
                            adminResponse.body()
                    );

            if (!adminJson.has("fields")) {

                System.out.println(
                        "Access Denied: Admin fields not found"
                );

                return false;
            }

            JSONObject fields =
                    adminJson.getJSONObject(
                            "fields"
                    );


            // =================================================
            // ROLE CHECK
            // =================================================

            if (!fields.has("role")) {

                System.out.println(
                        "Access Denied: Admin role not found"
                );

                return false;
            }

            String role =
                    fields
                            .getJSONObject("role")
                            .getString("stringValue");


            if (!role.equalsIgnoreCase("Admin")) {

                System.out.println(
                        "Access Denied: User role is "
                                + role
                );

                return false;
            }


            // =================================================
            // EMAIL CHECK
            // =================================================

            if (!fields.has("email")) {

                System.out.println(
                        "Access Denied: Admin email not found"
                );

                return false;
            }

            String adminEmail =
                    fields
                            .getJSONObject("email")
                            .getString("stringValue");


            if (!adminEmail.equalsIgnoreCase(
                    firebaseEmail
            )) {

                System.out.println(
                        "Access Denied: Email does not match"
                );

                return false;
            }


            // =================================================
            // LOGGED-IN ADMIN DATA -> SESSION
            // Same admin who logged in will appear in profile
            // =================================================

            AdminSession.fullName =
                    getStringField(
                            fields,
                            "fullName",
                            "Admin"
                    );

            AdminSession.email =
                    adminEmail;

            AdminSession.mobile =
                    getStringField(
                            fields,
                            "mobile",
                            "-"
                    );

            AdminSession.employeeId =
                    getStringField(
                            fields,
                            "employeeId",
                            "-"
                    );

            AdminSession.role =
                    role;


            System.out.println(
                    "Admin verified successfully"
            );

            System.out.println(
                    "Logged Admin: "
                            + AdminSession.fullName
                            + " | "
                            + AdminSession.email
                            + " | "
                            + AdminSession.role
            );

            return true;


        } catch (Exception e) {

            System.out.println(
                    "Login Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }




    // =========================================================
    // GOOGLE SIGN-IN -> FIREBASE AUTH -> ADMIN CHECK
    // =========================================================
    public boolean loginWithGoogleIdToken(
            String googleIdToken
    ) {

        clearAdminSession();

        try {

            if (googleIdToken == null
                    || googleIdToken.isBlank()) {

                return false;
            }


            JSONObject payload =
                    new JSONObject();


            payload.put(
                    "postBody",
                    "id_token="
                            + googleIdToken
                            + "&providerId=google.com"
            );


            payload.put(
                    "requestUri",
                    "http://localhost"
            );


            payload.put(
                    "returnSecureToken",
                    true
            );


            payload.put(
                    "returnIdpCredential",
                    true
            );


            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithIdp?key="
                            + API_KEY;


            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();


            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "Firebase Google Login Status: "
                            + response.statusCode()
            );


            if (response.statusCode() != 200) {

                System.out.println(
                        "Firebase Google Login Error: "
                                + response.body()
                );

                return false;
            }


            JSONObject json =
                    new JSONObject(
                            response.body()
                    );


            String uid =
                    json.getString(
                            "localId"
                    );


            String firebaseIdToken =
                    json.getString(
                            "idToken"
                    );


            String firebaseEmail =
                    json.getString(
                            "email"
                    );


            System.out.println(
                    "Google Authentication successful: "
                            + firebaseEmail
            );


            return verifyGoogleAdminAccess(
                    uid,
                    firebaseIdToken,
                    firebaseEmail
            );


        } catch (Exception e) {

            System.out.println(
                    "Google Firebase Login Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // GOOGLE USER MUST ALSO EXIST IN Firestore admins/{UID}
    // =========================================================
    private boolean verifyGoogleAdminAccess(
            String uid,
            String firebaseIdToken,
            String firebaseEmail
    ) {

        try {

            String adminUrl =
                    "https://firestore.googleapis.com/v1/projects/"
                            + PROJECT_ID
                            + "/databases/(default)/documents/admins/"
                            + uid;


            HttpRequest adminRequest =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            adminUrl
                                    )
                            )
                            .header(
                                    "Authorization",
                                    "Bearer "
                                            + firebaseIdToken
                            )
                            .GET()
                            .build();


            HttpResponse<String> adminResponse =
                    client.send(
                            adminRequest,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "Google Admin Check Status: "
                            + adminResponse.statusCode()
            );


            if (adminResponse.statusCode() != 200) {

                System.out.println(
                        "Access Denied: Google account is not registered as Admin"
                );

                return false;
            }


            JSONObject adminJson =
                    new JSONObject(
                            adminResponse.body()
                    );


            if (!adminJson.has("fields")) {

                return false;
            }


            JSONObject fields =
                    adminJson.getJSONObject(
                            "fields"
                    );


            String role =
                    getStringField(
                            fields,
                            "role",
                            ""
                    );


            String adminEmail =
                    getStringField(
                            fields,
                            "email",
                            ""
                    );


            if (!role.equalsIgnoreCase(
                    "Admin"
            )) {

                System.out.println(
                        "Access Denied: Role is "
                                + role
                );

                return false;
            }


            if (!adminEmail.equalsIgnoreCase(
                    firebaseEmail
            )) {

                System.out.println(
                        "Access Denied: Google email does not match Admin email"
                );

                return false;
            }


            AdminSession.fullName =
                    getStringField(
                            fields,
                            "fullName",
                            "Admin"
                    );


            AdminSession.email =
                    adminEmail;


            AdminSession.mobile =
                    getStringField(
                            fields,
                            "mobile",
                            "-"
                    );


            AdminSession.employeeId =
                    getStringField(
                            fields,
                            "employeeId",
                            "-"
                    );


            AdminSession.role =
                    role;


            System.out.println(
                    "Google Admin verified successfully"
            );


            System.out.println(
                    "Logged Admin: "
                            + AdminSession.fullName
                            + " | "
                            + AdminSession.email
                            + " | "
                            + AdminSession.role
            );


            return true;


        } catch (Exception e) {

            System.out.println(
                    "Google Admin Verification Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }



    // =========================================================
    // SEND EMAIL VERIFICATION LINK
    // =========================================================
    public boolean sendEmailVerification(
            String idToken
    ) {

        try {

            JSONObject payload =
                    new JSONObject();

            payload.put(
                    "requestType",
                    "VERIFY_EMAIL"
            );

            payload.put(
                    "idToken",
                    idToken
            );


            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key="
                            + API_KEY;


            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();


            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "Verification Email Status: "
                            + response.statusCode()
            );

            System.out.println(
                    "Verification Email Response: "
                            + response.body()
            );


            if (response.statusCode() == 200) {

                System.out.println(
                        "Verification email sent successfully"
                );

                return true;
            }


            System.out.println(
                    "Verification email send failed: "
                            + response.body()
            );

            return false;


        } catch (Exception e) {

            System.out.println(
                    "Verification Email Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // CHECK WHETHER EMAIL WAS ACTUALLY VERIFIED
    // =========================================================
    public boolean isEmailVerified(
            String idToken
    ) {

        try {

            JSONObject payload =
                    new JSONObject();

            payload.put(
                    "idToken",
                    idToken
            );


            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:lookup?key="
                            + API_KEY;


            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();


            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            if (response.statusCode() != 200) {

                System.out.println(
                        "Email verification lookup failed: "
                                + response.body()
                );

                return false;
            }


            JSONObject json =
                    new JSONObject(
                            response.body()
                    );


            if (!json.has("users")
                    || json.getJSONArray("users").isEmpty()) {

                return false;
            }


            JSONObject user =
                    json
                            .getJSONArray("users")
                            .getJSONObject(0);


            return user.optBoolean(
                    "emailVerified",
                    false
            );


        } catch (Exception e) {

            System.out.println(
                    "Email Verification Check Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    // =========================================================
    // NEW ADMIN SAVE
    // Document ID = Firebase UID
    // =========================================================
    public boolean saveAdminData(
            String uid,
            String employeeId,
            String fullName,
            String username,
            String email,
            String mobile,
            String role,
            String accessCode,
            String idToken
    ) {

        try {

            if (uid == null
                    || uid.isBlank()) {

                System.out.println(
                        "Firebase UID is empty"
                );

                return false;
            }


            JSONObject fields =
                    new JSONObject();


            fields.put(
                    "uid",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    uid
                            )
            );


            fields.put(
                    "fullName",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    fullName
                            )
            );


            fields.put(
                    "username",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    username
                            )
            );


            fields.put(
                    "email",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    email
                            )
            );


            fields.put(
                    "mobile",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    mobile
                            )
            );


            fields.put(
                    "employeeId",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    employeeId
                            )
            );


            fields.put(
                    "role",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    role
                            )
            );


            fields.put(
                    "accessCode",
                    new JSONObject()
                            .put(
                                    "stringValue",
                                    accessCode
                            )
            );


            JSONObject payload =
                    new JSONObject();

            payload.put(
                    "fields",
                    fields
            );


            // IMPORTANT:
            // Employee ID nahi
            // Firebase UID document ID aahe

            String url =
                    "https://firestore.googleapis.com/v1/projects/"
                            + PROJECT_ID
                            + "/databases/(default)/documents/admins/"
                            + uid;


            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            url
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .header(
                                    "Authorization",
                                    "Bearer " + idToken
                            )
                            .method(
                                    "PATCH",
                                    HttpRequest.BodyPublishers.ofString(
                                            payload.toString()
                                    )
                            )
                            .build();


            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "Firestore Status: "
                            + response.statusCode()
            );


            if (response.statusCode() == 200) {

                System.out.println(
                        "Admin data stored successfully"
                );

                return true;
            }


            System.out.println(
                    "Admin data storage failed"
            );

            System.out.println(
                    response.body()
            );

            return false;


        } catch (Exception e) {

            System.out.println(
                    "Firestore Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    // =========================================================
    // READ FIRESTORE STRING FIELD SAFELY
    // =========================================================
    private String getStringField(
            JSONObject fields,
            String key,
            String defaultValue
    ) {

        try {

            if (!fields.has(key)) {
                return defaultValue;
            }

            JSONObject field =
                    fields.optJSONObject(key);

            if (field == null) {
                return defaultValue;
            }

            String value =
                    field.optString(
                            "stringValue",
                            defaultValue
                    );

            if (value == null
                    || value.isBlank()) {

                return defaultValue;
            }

            return value;

        } catch (Exception e) {

            return defaultValue;
        }
    }


    // =========================================================
    // CLEAR OLD ADMIN SESSION
    // =========================================================
    public static void clearAdminSession() {

        AdminSession.fullName = null;
        AdminSession.email = null;
        AdminSession.mobile = null;
        AdminSession.employeeId = null;
        AdminSession.role = null;
    }

}
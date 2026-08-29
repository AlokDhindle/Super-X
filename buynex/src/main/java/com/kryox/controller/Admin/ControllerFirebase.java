package com.kryox.controller.Admin;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.kryox.config.AdminApikey;

public class ControllerFirebase {

    private static final String API_KEY =
            AdminApikey.API_KEY;

    private static final String PROJECT_ID =
            "customer-2ab2e";

    private final HttpClient client =
            HttpClient.newHttpClient();


    public String signUp(
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

            System.out.println(
                    "Signup Response: "
                            + response.body()
            );

            if (response.statusCode() == 200) {

                JSONObject json =
                        new JSONObject(
                                response.body()
                        );

                return json.getString(
                        "idToken"
                );
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


    public boolean login(
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

            System.out.println(
                    "Login Response: "
                            + response.body()
            );

            return response.statusCode() == 200;

        } catch (Exception e) {

            System.out.println(
                    "Login Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    public boolean saveAdminData(
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

            if (employeeId == null
                    || employeeId.isBlank()) {

                System.out.println(
                        "Employee ID is empty"
                );

                return false;
            }

            JSONObject fields =
                    new JSONObject();

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

            String safeEmployeeId =
                    URLEncoder.encode(
                            employeeId,
                            StandardCharsets.UTF_8
                    );

            String url =
                    "https://firestore.googleapis.com/v1/projects/"
                            + PROJECT_ID
                            + "/databases/(default)/documents/admins/"
                            + safeEmployeeId;

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

            System.out.println(
                    "Firestore Response: "
                            + response.body()
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
}
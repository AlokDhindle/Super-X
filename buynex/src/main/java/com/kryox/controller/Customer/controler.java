package com.kryox.controller.Customer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.kryox.config.APIKEYconfig;



public class controler {

    private String API_KEY =
            APIKEYconfig.API_KEY;

    // Logged-in Firebase user ID
    public String userId;

    // ================= SIGN UP =================

    public boolean singUp(String email, String password) {

        JSONObject payload = new JSONObject()
                .put("email", email)
                .put("password", password)
                .put("returnSecureToken", true);

        try {

            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI.create(
                    "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                            + API_KEY
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
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
                    "Signup Status: " + response.statusCode()
            );

            System.out.println(
                    "Signup Response: " + response.body()
            );

            if (response.statusCode() == 200) {

                JSONObject responseJson =
                        new JSONObject(response.body());

                // Firebase UID
                userId =
                        responseJson.getString("localId");

                System.out.println(
                        "Created User ID: " + userId
                );

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ================= LOGIN =================

    public boolean logIn(String email, String password) {

        JSONObject payload = new JSONObject()
                .put("email", email)
                .put("password", password)
                .put("returnSecureToken", true);

        try {

            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI.create(
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                            + API_KEY
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
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
                    "Login Status: " + response.statusCode()
            );

            System.out.println(
                    "Login Response: " + response.body()
            );

            if (response.statusCode() == 200) {

                // Convert Firebase response into JSON
                JSONObject responseJson =
                        new JSONObject(response.body());

                // Get Firebase user ID
                userId =
                        responseJson.getString("localId");

                System.out.println(
                        "Logged User ID: " + userId
                );

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ================= GET USER ID =================

    public String getUserId() {

        return userId;
    }
}
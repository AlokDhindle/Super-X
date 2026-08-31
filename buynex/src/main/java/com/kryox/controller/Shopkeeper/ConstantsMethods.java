package com.kryox.controller.Shopkeeper;

import org.json.JSONObject;

import javafx.scene.control.Alert;

public class ConstantsMethods {


        

            // =============================================================
        // EMAIL VALIDATION
        // =============================================================

        public static boolean isValidEmail(
                        String email) {

                return email.matches(
                                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        }

        // =============================================================
        // FIREBASE ERROR HANDLER
        // =============================================================

        public static String getFirebaseError(
                        JSONObject responseJson) {

                try {

                        JSONObject errorObject = responseJson.getJSONObject(
                                        "error");

                        String message = errorObject.optString(
                                        "message",
                                        "Unknown Firebase error.");

                        switch (message) {

                                case "EMAIL_EXISTS":
                                        return "This email address is already registered.";

                                case "INVALID_EMAIL":
                                        return "The email address is invalid.";

                                case "WEAK_PASSWORD":
                                        return "The password is too weak. Use at least 6 characters.";

                                case "OPERATION_NOT_ALLOWED":
                                        return "Email/password authentication is not enabled in Firebase.";

                                case "TOO_MANY_ATTEMPTS_TRY_LATER":
                                        return "Too many attempts. Please try again later.";

                                case "INVALID_ID_TOKEN":
                                        return "Your Firebase session has expired. Please start registration again.";

                                case "USER_NOT_FOUND":
                                        return "Firebase could not find this user.";

                                case "INVALID_PASSWORD":
                                        return "The password is incorrect.";

                                default:
                                        return message;
                        }

                } catch (Exception ex) {

                        return "An unknown Firebase error occurred.";
                }
        }


        // =============================================================
        // ALERT
        // =============================================================

        public static void showAlert(
                        Alert.AlertType type,
                        String title,
                        String message) {
                Alert alert = new Alert(type);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(message);
                alert.showAndWait();
        }
    
}

package com.kryox.controller.Shopkeeper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.kryox.config.APIKEYconfig;
import com.kryox.dao.Shopkeeper.ShopkeeperDAO;
import com.kryox.model.Shopkeeper.ShopkeeperModel;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Shopkeeper.ShopkeeperDetails;
import com.kryox.view.Shopkeeper.ViewConstants;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class ShopkeeperLogController {

        private static final String FIREBASE_API_KEY = APIKEYconfig.API_KEY;
        public static JSONObject responseJson;
        static String firebaseIdToken;
        static String firebaseUid;
        static String registeredEmail;
        static boolean emailVerified = false;

        // =============================================================
        // HTTP CLIENT
        // =============================================================

        static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

        // =============================================================
        // LOGIN SHOPKEEPER
        // =============================================================


        public static ShopkeeperModel loginShopkeeper(String email, String password) {
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);

                try {

                        JSONObject requestJson = new JSONObject()
                                        .put(
                                                        "email",
                                                        email)
                                        .put(
                                                        "password",
                                                        password);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                                                                        + FIREBASE_API_KEY))
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers.ofString(
                                                                        requestJson.toString()))
                                        .build();

                        HttpResponse<String> response = HTTP_CLIENT.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        JSONObject responseJson = new JSONObject(
                                        response.body());

                        if (response.statusCode() == 200) {

                                ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
                                System.out.println("Login successful");
                                firebaseUid = responseJson.getString("localId");
                                ShopkeeperModel shopkeeperModel = shopkeeperDAO.getShopDetails(firebaseUid);
                                return shopkeeperModel;
                        }else{
                                System.out.println("Login failed ooooooooooo");
                                String error = ConstantsMethods.getFirebaseError(
                                                responseJson);
                                ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Login Failed", error);
                                return null;
                        }
                } catch (Exception ex) {
                        ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Connection Error", "Unable to connect to Firebase.\n\n");
                        System.out.println("Error logging in");
                        ex.printStackTrace();
                        return null;
                }
        }

        

        // =============================================================
        // REGISTER SHOPKEEPER
        // =============================================================

        public static void signupShopkeeper(
                        String email,
                        String password,
                        String confirmPassword,
                        Button verifyButton,
                        Button nextButton) {

                // ---------------------------------------------------------
                // VALIDATION
                // ---------------------------------------------------------

                if (email == null ||
                                email.trim().isEmpty()) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Email Required",
                                        "Please enter your email address.");

                        return;
                }

                email = email.trim();

                if (!ConstantsMethods.isValidEmail(email)) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Invalid Email",
                                        "Please enter a valid email address.");

                        return;
                }

                if (password == null ||
                                password.isEmpty()) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Password Required",
                                        "Please enter a password.");

                        return;
                }

                if (password.length() < 6) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Weak Password",
                                        "Password must contain at least 6 characters.");

                        return;
                }

                if (!password.equals(confirmPassword)) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Password Mismatch",
                                        "Password and Confirm Password do not match.");

                        return;
                }

                // ---------------------------------------------------------
                // RESET STATE
                // ---------------------------------------------------------

                firebaseIdToken = null;
                firebaseUid = null;
                registeredEmail = email;
                emailVerified = false;

                final String finalEmail = email;
                final String finalPassword = password;

                // ---------------------------------------------------------
                // FIREBASE ACCOUNT CREATION
                // ---------------------------------------------------------

                try {

                        JSONObject requestJson = new JSONObject()
                                        .put(
                                                        "email",
                                                        finalEmail)
                                        .put(
                                                        "password",
                                                        finalPassword)
                                        .put(
                                                        "returnSecureToken",
                                                        true);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                                                                        + FIREBASE_API_KEY))
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers.ofString(
                                                                        requestJson.toString()))
                                        .build();

                        HttpResponse<String> response = HTTP_CLIENT.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        JSONObject responseJson = new JSONObject(
                                        response.body());

                        if (response.statusCode() == 200) {

                                firebaseIdToken = responseJson.getString(
                                                "idToken");

                                firebaseUid = responseJson.getString(
                                                "localId");

                                registeredEmail = responseJson.getString(
                                                "email");

                                sendVerificationEmail(
                                                verifyButton,
                                                nextButton);

                        } else {

                                String error = ConstantsMethods.getFirebaseError(
                                                responseJson);

                                ConstantsMethods.showAlert(
                                                Alert.AlertType.ERROR,
                                                "Registration Failed",
                                                error);
                                // });
                        }

                } catch (Exception ex) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.ERROR,
                                        "Connection Error",
                                        "Unable to connect to Firebase.\n\n");
                        ex.printStackTrace();
                }

        }

        // =============================================================
        // SEND FIREBASE VERIFICATION EMAIL
        // =============================================================

        private static void sendVerificationEmail(
                        Button verifyButton,
                        Button nextButton) {

                if (firebaseIdToken == null) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.ERROR,
                                        "Verification Error",
                                        "Firebase ID token is missing.");
                        return;
                }

                try {

                        JSONObject requestJson = new JSONObject()
                                        .put(
                                                        "requestType",
                                                        "VERIFY_EMAIL")
                                        .put(
                                                        "idToken",
                                                        firebaseIdToken);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key="
                                                                        + FIREBASE_API_KEY))
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers.ofString(
                                                                        requestJson.toString()))
                                        .build();

                        HttpResponse<String> response = HTTP_CLIENT.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        responseJson = new JSONObject(
                                        response.body());

                        if (response.statusCode() == 200) {

                                verifyButton.setDisable(false);
                                verifyButton.setText("Check");

                                ConstantsMethods.showAlert(
                                                Alert.AlertType.INFORMATION,
                                                "Verification Email Sent",
                                                "A verification email has been sent to "
                                                                + registeredEmail
                                                                + ".\n\n"
                                                                + "Open your inbox and click the "
                                                                + "verification link.\n\n"
                                                                + "After verifying, click Check.");
                                verifyButton.setOnAction(e -> {
                                        ShopkeeperLogController.checkEmailVerification(verifyButton, nextButton);
                                });

                        } else {

                                String error = ConstantsMethods.getFirebaseError(
                                                responseJson);

                                verifyButton.setDisable(false);
                                verifyButton.setText("Verify");

                                ConstantsMethods.showAlert(
                                                Alert.AlertType.ERROR,
                                                "Verification Email Failed",
                                                error);

                        }

                } catch (Exception ex) {

                        verifyButton.setDisable(false);
                        verifyButton.setText("Verify");

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.ERROR,
                                        "Connection Error",
                                        "Unable to send verification email.\n\n"
                                                        + ex.getMessage());
                }
        }

        // =============================================================
        // CHECK EMAIL VERIFICATION
        // =============================================================

        public static void checkEmailVerification(
                        Button verifyButton,
                        Button nextButton) {

                if (firebaseIdToken == null) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Registration Required",
                                        "Please create your Firebase account first.");

                        return;
                }

                if (verifyButton != null) {

                        verifyButton.setDisable(true);
                        verifyButton.setText("Checking...");
                }

                try {

                        JSONObject requestJson = new JSONObject()
                                        .put(
                                                        "idToken",
                                                        firebaseIdToken);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create("https://identitytoolkit.googleapis.com/v1/accounts:lookup?key="
                                                                        + FIREBASE_API_KEY))
                                        .header(
                                                        "Content-Type",
                                                        "application/json")
                                        .POST(
                                                        HttpRequest.BodyPublishers.ofString(
                                                                        requestJson.toString()))
                                        .build();

                        HttpResponse<String> response = HTTP_CLIENT.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        JSONObject responseJson1 = new JSONObject(
                                        response.body());

                        if (response.statusCode() == 200) {

                                boolean verified = responseJson1
                                                .getJSONArray(
                                                                "users")
                                                .getJSONObject(0)
                                                .optBoolean(
                                                                "emailVerified",
                                                                false);

                                if (verified) {

                                        emailVerified = true;

                                        if (verifyButton != null) {

                                                verifyButton.setText(
                                                                "Verified ✓");

                                                verifyButton.setDisable(
                                                                true);

                                                verifyButton.setStyle(
                                                                "-fx-background-color: #E8F5E9;" +
                                                                                "-fx-text-fill: #2E7D32;"
                                                                                +
                                                                                "-fx-font-size: 13px;" +
                                                                                "-fx-font-weight: bold;"
                                                                                +
                                                                                "-fx-font-family: 'Arial';"
                                                                                +
                                                                                "-fx-border-color: #4CAF50;"
                                                                                +
                                                                                "-fx-border-width: 1.5px;"
                                                                                +
                                                                                "-fx-border-radius: 9px;"
                                                                                +
                                                                                "-fx-background-radius: 9px;");
                                        }

                                        if (nextButton != null) {

                                                nextButton.setDisable(false);
                                        }

                                        ConstantsMethods.showAlert(
                                                        Alert.AlertType.INFORMATION,
                                                        "Email Verified",
                                                        "Your email address has been "
                                                                        + "successfully verified.\n\n"
                                                                        + "You can now continue.");

                                } else {

                                        emailVerified = false;

                                        if (verifyButton != null) {

                                                verifyButton.setDisable(false);
                                                verifyButton.setText("Check");
                                        }

                                        ConstantsMethods.showAlert(
                                                        Alert.AlertType.WARNING,
                                                        "Not Verified",
                                                        "Your email is not verified yet.\n\n"
                                                                        + "Please open the verification "
                                                                        + "email and click the link.");
                                }

                        } else {

                                String error = ConstantsMethods.getFirebaseError(
                                                responseJson);

                                if (verifyButton != null) {

                                        verifyButton.setDisable(false);
                                        verifyButton.setText("Check");
                                }

                                ConstantsMethods.showAlert(
                                                Alert.AlertType.ERROR,
                                                "Verification Check Failed",
                                                error);

                        }

                } catch (Exception ex) {

                        if (verifyButton != null) {

                                verifyButton.setDisable(false);
                                verifyButton.setText("Check");
                        }

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.ERROR,
                                        "Connection Error",
                                        "Unable to check email verification.\n\n"
                                                        + ex.getMessage());
                }

        }
        // =============================================================
        // CONTINUE REGISTRATION
        // =============================================================

        public static void continueRegistration(
                        String email,
                        String password,
                        String confirmPassword) {

                if (!emailVerified) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Email Not Verified",
                                        "Please verify your email address before continuing.");

                        return;
                }

                if (email == null ||
                                !email.equalsIgnoreCase(registeredEmail)) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Email Changed",
                                        "The email address is different from the "
                                                        + "verified email.");

                        return;
                }

                if (password == null ||
                                password.isEmpty()) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Password Required",
                                        "Please enter your password.");

                        return;
                }

                if (!password.equals(confirmPassword)) {

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.WARNING,
                                        "Password Mismatch",
                                        "Password and Confirm Password do not match.");

                        return;
                }

                System.out.println(
                                "Email verified successfully.");

                System.out.println(
                                "Firebase UID: " + firebaseUid);

                Homepage.HomepageStage.setScene(
                                ShopkeeperDetails.detailsScene());
        }

        // =============================================================
        // RESET REGISTRATION
        // =============================================================

        public static void resetRegistration() {
                new Thread(() -> {
                        if (firebaseIdToken != null) {
                                try {
                                        JSONObject requestJson = new JSONObject()
                                                        .put("idToken", firebaseIdToken);

                                        HttpRequest request = HttpRequest.newBuilder()
                                                        .uri(URI.create(
                                                                        "https://identitytoolkit.googleapis.com/v1/accounts:delete?key="
                                                                                        + FIREBASE_API_KEY))
                                                        .header("Content-Type", "application/json")
                                                        .POST(HttpRequest.BodyPublishers.ofString(
                                                                        requestJson.toString()))
                                                        .build();

                                        HttpResponse<String> response = HttpClient.newHttpClient().send(
                                                        request,
                                                        HttpResponse.BodyHandlers.ofString());

                                        if (response.statusCode() == 200) {
                                                System.out.println("Firebase account deleted");
                                                ConstantsMethods.showAlert(
                                                                Alert.AlertType.INFORMATION,
                                                                "Firebase Account Deleted",
                                                                "Your BuyNex account Creation has been Terminated.");
                                                firebaseIdToken = null;
                                                firebaseUid = null;
                                                registeredEmail = null;
                                                emailVerified = false;
                                        }
                                } catch (Exception ex) {
                                        System.out.println("Error deleting Firebase account");
                                        ex.printStackTrace();
                                }
                        } else {
                                System.out.println("Firebase account not found");
                        }
                }).start();

        }

        // =============================================================
        // SEND CHANGE PASSWORD OTP
        // =============================================================

        public static void sendChangePasswordLink(String email, Button sendOtpButton) {

                new Thread(() -> {
                        try {
                                JSONObject requestJson = new JSONObject()
                                                .put(
                                                                "email",
                                                                email)
                                                .put(
                                                                "requestType",
                                                                "PASSWORD_RESET");

                                HttpRequest request = HttpRequest.newBuilder()
                                                .uri(
                                                                URI.create("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key="
                                                                                + FIREBASE_API_KEY))
                                                .header(
                                                                "Content-Type",
                                                                "application/json")
                                                .POST(
                                                                HttpRequest.BodyPublishers.ofString(
                                                                                requestJson.toString()))
                                                .build();
                                HttpResponse<String> response = HTTP_CLIENT.send(
                                                request,
                                                HttpResponse.BodyHandlers.ofString());

                                responseJson = new JSONObject(
                                                response.body());

                                if (response.statusCode() == 200) {
                                        Platform.runLater(() -> {
                                                ConstantsMethods.showAlert(
                                                                Alert.AlertType.INFORMATION,
                                                                "OTP Sent",
                                                                "Please check your email for the Link.");

                                                sendOtpButton.setDisable(true);
                                                sendOtpButton.setText("Link Sent");
                                        });
                                } else {
                                        String error = ConstantsMethods.getFirebaseError(
                                                        responseJson);
                                        Platform.runLater(() -> {
                                                ConstantsMethods.showAlert(
                                                                Alert.AlertType.ERROR,
                                                                "Link Send Failed",
                                                                error);
                                                sendOtpButton.setDisable(false);
                                                sendOtpButton.setText("Send Link");
                                        });
                                }
                        } catch (Exception ex) {
                                ConstantsMethods.showAlert(
                                                Alert.AlertType.ERROR,
                                                "Connection Error",
                                                "Unable to send OTP.\n\n");
                                ex.printStackTrace();
                        }
                }).start();
        }
        // =============================================================
        // GETTERS
        // =============================================================

        public static String getFirebaseIdToken() {

                return firebaseIdToken;
        }

        public static String getShopkeeperUid() {

                return firebaseUid;
        }

        public static String getRegisteredEmail() {

                return registeredEmail;
        }

        public static boolean isEmailVerified() {

                return emailVerified;
        }

        public static void registerShop(String shopNameValue, String ownerNameValue, String mobileValue,
                        String panValue, String gstValue,
                        String categoryValue, String addressValue, String stateValue, String cityValue,
                        String pinValue, String licenseValue, String uniqueIdValue, String shopkeeperUid, String descriptionValue,
                        String shopLogoURL, String shopPhotoURL, String licenseDocumentURL, String gstCertificateURL
                ) {

                System.out.println("Shop registration data validated.");
                String shopUid = shopkeeperUid;
                ShopkeeperModel newShop = new ShopkeeperModel(shopNameValue, ownerNameValue, mobileValue, panValue,
                                gstValue, categoryValue, addressValue, stateValue, cityValue, pinValue, licenseValue,
                                uniqueIdValue, shopUid, descriptionValue, shopLogoURL, shopPhotoURL, licenseDocumentURL, gstCertificateURL);
                ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
                shopkeeperDAO.addShop(newShop);

        }

        public static void getShopDetails(String shopUid) {
                ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
                ViewConstants.shopkeeperModel = shopkeeperDAO.getShopDetails(shopUid);
                System.out.println("Shop details: " + ViewConstants.shopkeeperModel.toString());
        }

}
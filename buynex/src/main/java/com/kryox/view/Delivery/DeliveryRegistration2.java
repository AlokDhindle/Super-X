
package com.kryox.view.Delivery;

import com.kryox.controller.Delivery.DeliveryRegistrationController;
import com.kryox.controller.Delivery.ImageUploadController;
import com.kryox.model.Delivery.DeliveryPartner;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DeliveryRegistration2 {

    // =========================================================
    // CONSTANTS
    // =========================================================

    private static final String GRADIENT_BTN =
            "linear-gradient(to right, #B84208, #F36A00)";

    private static final String BG_COLOR =
            "#eee5df";

    private static final String CONTENT_BG =
            "#f5f5fa";

    // =========================================================
    // SCENE
    // =========================================================

    private static Scene registrationScene;

    // =========================================================
    // FORM FIELD REFERENCES
    // =========================================================

    private static TextField fullNameField;
    private static TextField mobileField;
    private static TextField emailField;
    private static DatePicker dobPicker;
    private static ComboBox<String> genderBox;
    private static TextField addressField;

    private static ComboBox<String> vehicleTypeBox;
    private static TextField vehicleNumberField;
    private static TextField licenseField;

    // =========================================================
    // CLOUDINARY URLS
    // =========================================================

    private static final Map<String, String> uploadedCloudinaryUrls =
            new HashMap<>();

    // =========================================================
    // BANK DETAILS
    // =========================================================

    private static TextField accountHolderField;
    private static TextField bankNameField;
    private static TextField accountNumberField;
    private static TextField ifscCodeField;

    // =========================================================
    // EMERGENCY CONTACT
    // =========================================================

    private static TextField contactNameField;
    private static TextField contactPhoneField;

    // =========================================================
    // PASSWORD
    // =========================================================

    private static PasswordField passwordField;
    private static PasswordField confirmPasswordField;

    private static CheckBox termsCheckbox;

    // =========================================================
    // CONTROLLER
    // =========================================================

    private static final DeliveryRegistrationController controller =
            new DeliveryRegistrationController();

    // =========================================================
    // GET REGISTRATION SCENE
    // =========================================================

    public static Scene getRegistrationScene() {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BG_COLOR + ";"
        );

        // =====================================================
        // TOP BAR
        // =====================================================

        root.setTop(createTopBar());

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content = new VBox(22);

        content.setAlignment(Pos.TOP_CENTER);

        content.setPadding(
                new Insets(30, 40, 100, 40)
        );

        content.setFillWidth(true);

        content.setMinHeight(
                Region.USE_PREF_SIZE
        );

        content.getChildren().addAll(
                createHeading(),
                createPersonalDetails(),
                createVehicleInformation(),
                createDocuments(),
                createPayoutSecurity(),
                createSecuritySection(),
                createSubmissionSection()
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(false);

        scrollPane.setPannable(true);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: " + CONTENT_BG + ";" +
                "-fx-border-color: transparent;"
        );

        root.setCenter(scrollPane);

        // =====================================================
        // CREATE SCENE
        // =====================================================

        registrationScene =
                new Scene(
                        root,
                        1530,
                        850
                );

        return registrationScene;
    }

    // =========================================================
    // TOP BAR
    // =========================================================

    private static BorderPane createTopBar() {

        BorderPane topBar =
                new BorderPane();

        topBar.setPrefHeight(55);
        topBar.setMinHeight(55);
        topBar.setMaxHeight(55);

        topBar.setStyle(
                "-fx-background-color: #eee5df;" +
                "-fx-border-color: #e0d5ce;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        Text logo =
                new Text("Buynex");

        logo.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-fill: linear-gradient(to right, #B84208, #F36A00);" +
                "-fx-font-weight: bold;"
        );

        HBox logoBox =
                new HBox(logo);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(0, 0, 0, 30)
        );

        topBar.setLeft(logoBox);

        // -----------------------------------------------------
        // PARTNER LOGIN
        // -----------------------------------------------------

        Text needHelp =
                new Text("Already registered?");

        needHelp.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #333333;"
        );

        Button partnerLogin =
                new Button("Partner Login");

        partnerLogin.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #c95b14;" +
                "-fx-background-color: transparent;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        partnerLogin.setOnAction(e -> {

            if (Homepage.HomepageStage != null) {

                Homepage.HomepageStage.setScene(
                        Deliverylogin.getDelivaryScene()
                );

                Homepage.HomepageStage.show();
            }
        });

        HBox rightBox =
                new HBox(
                        8,
                        needHelp,
                        partnerLogin
                );

        rightBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        rightBox.setPadding(
                new Insets(0, 30, 0, 0)
        );

        topBar.setRight(rightBox);

        return topBar;
    }

    // =========================================================
    // HEADING
    // =========================================================

    private static VBox createHeading() {

        VBox box =
                new VBox(6);

        box.setMaxWidth(920);

        Text title =
                new Text(
                        "Become a BuyNeX Delivery Partner"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #111827;"
        );

        Text description =
                new Text(
                        "Register now to start delivering orders with our network. " +
                        "Upload your documents for admin approval and set up your instant payout account."
                );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );

        description.setWrappingWidth(920);

        box.getChildren().addAll(
                title,
                description
        );

        return box;
    }

    // =========================================================
    // 1. PERSONAL DETAILS
    // =========================================================

    private static VBox createPersonalDetails() {

        VBox card =
                createCard();

        Text title =
                createSectionTitle(
                        "1. Personal Details"
                );

        GridPane grid =
                createGrid();

        fullNameField =
                createTextField(
                        "e.g. John Doe"
                );

        mobileField =
                createTextField(
                        "+91 98765 43210"
                );

        emailField =
                createTextField(
                        "john@example.com"
                );

        dobPicker =
                new DatePicker();

        dobPicker.setPromptText(
                "DD/MM/YYYY"
        );

        dobPicker.setPrefHeight(38);

        dobPicker.setMaxWidth(
                Double.MAX_VALUE
        );

        genderBox =
                new ComboBox<>();

        genderBox.getItems().addAll(
                "Male",
                "Female",
                "Other"
        );

        genderBox.setPromptText(
                "Select Gender"
        );

        genderBox.setPrefHeight(38);

        genderBox.setMaxWidth(
                Double.MAX_VALUE
        );

        addressField =
                createTextField(
                        "Flat / House No, Street, Landmark, Pincode"
                );

        addField(
                grid,
                "Full Name (As per official ID)",
                fullNameField,
                0,
                0
        );

        addField(
                grid,
                "Mobile Number",
                mobileField,
                1,
                0
        );

        addField(
                grid,
                "Email Address",
                emailField,
                0,
                1
        );

        addField(
                grid,
                "Date of Birth",
                dobPicker,
                1,
                1
        );

        addField(
                grid,
                "Gender",
                genderBox,
                0,
                2
        );

        addFullField(
                grid,
                "Residential Address",
                addressField,
                3
        );

        card.getChildren().addAll(
                title,
                grid
        );

        return card;
    }

    // =========================================================
    // 2. VEHICLE INFORMATION
    // =========================================================

    private static VBox createVehicleInformation() {

        VBox card =
                createCard();

        Text title =
                createSectionTitle(
                        "2. Vehicle Information"
                );

        GridPane grid =
                createGrid();

        vehicleTypeBox =
                new ComboBox<>();

        vehicleTypeBox.getItems().addAll(
                "Bike / Motorcycle",
                "Scooter",
                "Electric Two-Wheeler",
                "Bicycle"
        );

        vehicleTypeBox.setValue(
                "Bike / Motorcycle"
        );

        vehicleTypeBox.setPrefHeight(38);

        vehicleTypeBox.setMaxWidth(
                Double.MAX_VALUE
        );

        vehicleNumberField =
                createTextField(
                        "e.g. MH 12 AB 1234"
                );

        licenseField =
                createTextField(
                        "e.g. DL-1234567890123"
                );

        addField(
                grid,
                "Vehicle Type",
                vehicleTypeBox,
                0,
                0
        );

        addField(
                grid,
                "Vehicle Registration Number",
                vehicleNumberField,
                1,
                0
        );

        addFullField(
                grid,
                "Driving License Number",
                licenseField,
                1
        );

        card.getChildren().addAll(
                title,
                grid
        );

        return card;
    }

    // =========================================================
    // 3. DOCUMENT UPLOADS
    // =========================================================

    private static VBox createDocuments() {

        VBox card =
                createCard();

        Text title =
                createSectionTitle(
                        "3. Document Uploads"
                );

        HBox documents =
                new HBox(14);

        documents.setAlignment(
                Pos.CENTER
        );

        Button profile =
                createDocumentUploadButton(
                        "Profile Photo\n(Live Avatar)",
                        "profilePhoto",
                        true
                );

        Button idCard =
                createDocumentUploadButton(
                        "Government ID Proof\n(Needs Admin Review)",
                        "idCard",
                        false
                );

        Button license =
                createDocumentUploadButton(
                        "Driving License\n(Front & Back)",
                        "licenseDoc",
                        false
                );

        Button rc =
                createDocumentUploadButton(
                        "Vehicle RC Book\n(Registration Copy)",
                        "rcBook",
                        false
                );

        documents.getChildren().addAll(
                profile,
                idCard,
                license,
                rc
        );

        card.getChildren().addAll(
                title,
                documents
        );

        return card;
    }

    // =========================================================
    // DOCUMENT UPLOAD BUTTON
    // =========================================================

    private static Button createDocumentUploadButton(
            String label,
            String key,
            boolean isProfileAvatar) {

        Button button =
                new Button(
                        "Upload\n" + label
                );

        button.setPrefWidth(200);
        button.setPrefHeight(80);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                button,
                Priority.ALWAYS
        );

        button.setStyle(
                "-fx-background-color: #fafafc;" +
                "-fx-border-color: #dfd8d4;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-border-style: dashed;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-cursor: hand;"
        );

        button.setOnAction(e -> {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Select " +
                    label.replace("\n", " ")
            );

            chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter(
                            "Image / PDF Files",
                            "*.png",
                            "*.jpg",
                            "*.jpeg",
                            "*.pdf"
                    )
            );

            /*
             * Stage removed.
             * null owner is used so FileChooser
             * does not require Stage.
             */

            File file =
                    chooser.showOpenDialog(null);

            if (file == null) {
                return;
            }

            button.setText(
                    "Uploading to Cloud..."
            );

            button.setDisable(true);

            new Thread(() -> {

                ImageUploadController uploader =
                        new ImageUploadController();

                String cdnUrl =
                        uploader.imageUpload(file);

                Platform.runLater(() -> {

                    if (cdnUrl != null &&
                            !cdnUrl.isEmpty()) {

                        uploadedCloudinaryUrls.put(
                                key,
                                cdnUrl
                        );

                        if (isProfileAvatar) {

                            PartnerConstants.PROFILE_PHOTO_URL =
                                    cdnUrl;

                            button.setText(
                                    "✓ Profile Photo\nUploaded"
                            );

                            button.setStyle(
                                    "-fx-background-color: #f1fcf4;" +
                                    "-fx-border-color: #22c55e;" +
                                    "-fx-border-width: 1.5;" +
                                    "-fx-border-radius: 8;" +
                                    "-fx-background-radius: 8;" +
                                    "-fx-text-fill: #15803d;" +
                                    "-fx-font-size: 10px;" +
                                    "-fx-font-weight: bold;"
                            );

                        } else {

                            button.setText(
                                    "✓ " +
                                    file.getName() +
                                    "\n(Sent to Admin)"
                            );

                            button.setStyle(
                                    "-fx-background-color: #fffbeb;" +
                                    "-fx-border-color: #f59e0b;" +
                                    "-fx-border-width: 1.5;" +
                                    "-fx-border-radius: 8;" +
                                    "-fx-background-radius: 8;" +
                                    "-fx-text-fill: #b45309;" +
                                    "-fx-font-size: 10px;" +
                                    "-fx-font-weight: bold;"
                            );
                        }

                    } else {

                        button.setText(
                                "Upload Failed!\nRetry"
                        );

                        button.setStyle(
                                "-fx-background-color: #fef2f2;" +
                                "-fx-border-color: #ef4444;" +
                                "-fx-border-width: 1.5;" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;" +
                                "-fx-text-fill: #b91c1c;" +
                                "-fx-font-size: 10px;"
                        );
                    }

                    button.setDisable(false);
                });

            }).start();
        });

        return button;
    }

    // =========================================================
    // 4. PAYOUT & BANK DETAILS
    // =========================================================

    private static VBox createPayoutSecurity() {

        VBox card =
                createCard();

        Text title =
                createSectionTitle(
                        "4. Payout & Bank Details"
                );

        GridPane grid =
                createGrid();

        accountHolderField =
                createTextField(
                        "Account Holder Name"
                );

        bankNameField =
                createTextField(
                        "e.g. HDFC Bank, State Bank of India"
                );

        accountNumberField =
                createTextField(
                        "Account Number"
                );

        ifscCodeField =
                createTextField(
                        "IFSC Code (e.g. HDFC0001234)"
                );

        addField(
                grid,
                "Account Holder Name",
                accountHolderField,
                0,
                0
        );

        addField(
                grid,
                "Bank Name",
                bankNameField,
                1,
                0
        );

        addField(
                grid,
                "Account Number",
                accountNumberField,
                0,
                1
        );

        addField(
                grid,
                "IFSC Code",
                ifscCodeField,
                1,
                1
        );

        Text emergencyTitle =
                new Text(
                        "Emergency Contact"
                );

        emergencyTitle.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        12
                )
        );

        emergencyTitle.setStyle(
                "-fx-fill: #555555;"
        );

        contactNameField =
                createTextField(
                        "Emergency Contact Person Name"
                );

        contactPhoneField =
                createTextField(
                        "Emergency Contact Phone Number"
                );

        HBox emergencyFields =
                new HBox(
                        12,
                        contactNameField,
                        contactPhoneField
                );

        HBox.setHgrow(
                contactNameField,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                contactPhoneField,
                Priority.ALWAYS
        );

        VBox emergency =
                new VBox(
                        8,
                        emergencyTitle,
                        emergencyFields
                );

        emergency.setPadding(
                new Insets(8, 0, 0, 0)
        );

        card.getChildren().addAll(
                title,
                grid,
                emergency
        );

        return card;
    }

    // =========================================================
    // 5. ACCOUNT SECURITY
    // =========================================================

    private static VBox createSecuritySection() {

        VBox card =
                createCard();

        Text title =
                createSectionTitle(
                        "5. Account Security"
                );

        GridPane grid =
                createGrid();

        passwordField =
                createPasswordField(
                        "Minimum 8 characters"
                );

        confirmPasswordField =
                createPasswordField(
                        "Re-enter your password"
                );

        addField(
                grid,
                "Set Password (minimum 8 characters)",
                passwordField,
                0,
                0
        );

        addField(
                grid,
                "Confirm Password",
                confirmPasswordField,
                1,
                0
        );

        card.getChildren().addAll(
                title,
                grid
        );

        return card;
    }

    // =========================================================
    // SUBMISSION SECTION
    // =========================================================

    private static VBox createSubmissionSection() {

        VBox section =
                new VBox(16);

        section.setMaxWidth(920);

        section.setAlignment(
                Pos.CENTER_LEFT
        );

        termsCheckbox =
                new CheckBox(
                        "I agree to the Terms & Conditions and Privacy Policy. " +
                        "I declare that all uploaded government identity documents " +
                        "and vehicle information are authentic."
                );

        termsCheckbox.setWrapText(true);

        termsCheckbox.setMaxWidth(920);

        termsCheckbox.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #555555;"
        );

        Button registerButton =
                new Button(
                        "Register Now   →"
                );

        registerButton.setPrefHeight(46);

        registerButton.setMaxWidth(
                Double.MAX_VALUE
        );

        registerButton.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: " +
                GRADIENT_BTN + ";" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;" +
                "-fx-cursor: hand;"
        );

        registerButton.setOnAction(e -> {

            DeliveryPartner partner =
                    new DeliveryPartner();

            partner.setFullName(
                    fullNameField.getText().trim()
            );

            partner.setMobile(
                    mobileField.getText().trim()
            );

            partner.setEmail(
                    emailField.getText().trim()
            );

            partner.setDob(
                    dobPicker.getValue() != null
                            ? dobPicker.getValue().toString()
                            : ""
            );

            partner.setGender(
                    genderBox.getValue()
            );

            partner.setAddress(
                    addressField.getText().trim()
            );

            partner.setVehicleType(
                    vehicleTypeBox.getValue()
            );

            partner.setVehicleNumber(
                    vehicleNumberField.getText().trim()
            );

            partner.setDrivingLicense(
                    licenseField.getText().trim()
            );

            // =================================================
            // CLOUDINARY DOCUMENT URLS
            // =================================================

            partner.setProfilePhotoPath(
                    uploadedCloudinaryUrls.get(
                            "profilePhoto"
                    )
            );

            partner.setIdCardPath(
                    uploadedCloudinaryUrls.get(
                            "idCard"
                    )
            );

            partner.setLicenseDocPath(
                    uploadedCloudinaryUrls.get(
                            "licenseDoc"
                    )
            );

            partner.setRcBookPath(
                    uploadedCloudinaryUrls.get(
                            "rcBook"
                    )
            );

            // =================================================
            // BANK DETAILS
            // =================================================

            partner.setAccountHolder(
                    accountHolderField.getText().trim()
            );

            partner.setBankName(
                    bankNameField.getText().trim()
            );

            partner.setAccountNumber(
                    accountNumberField.getText().trim()
            );

            partner.setIfscCode(
                    ifscCodeField.getText().trim()
            );

            // =================================================
            // EMERGENCY CONTACT
            // =================================================

            partner.setEmergencyContactName(
                    contactNameField.getText().trim()
            );

            partner.setEmergencyContactPhone(
                    contactPhoneField.getText().trim()
            );

            // =================================================
            // PASSWORD
            // =================================================

            String password =
                    passwordField.getText();

            String confirmPassword =
                    confirmPasswordField.getText();

            // =================================================
            // PARTNER CONSTANTS
            // =================================================

            PartnerConstants.FULL_NAME =
                    partner.getFullName().isEmpty()
                            ? "Partner"
                            : partner.getFullName();

            PartnerConstants.EMAIL =
                    partner.getEmail();

            PartnerConstants.PHONE =
                    partner.getMobile();

            PartnerConstants.VEHICLE_TYPE =
                    partner.getVehicleType() != null
                            ? partner.getVehicleType()
                            : "Bike / Motorcycle";

            PartnerConstants.VEHICLE_NUMBER =
                    partner.getVehicleNumber().isEmpty()
                            ? "MH 12 AA 0000"
                            : partner.getVehicleNumber();

            PartnerConstants.PARTNER_TIER =
                    "Standard Partner";

            PartnerConstants.BANK_NAME =
                    partner.getBankName().isEmpty()
                            ? "HDFC Bank"
                            : partner.getBankName();

            PartnerConstants.ACCOUNT_NUMBER =
                    partner.getAccountNumber().isEmpty()
                            ? "000000000000"
                            : partner.getAccountNumber();

            PartnerConstants.IFSC_CODE =
                    partner.getIfscCode().isEmpty()
                            ? "HDFC0000123"
                            : partner.getIfscCode();

            PartnerConstants.PROFILE_PHOTO_URL =
                    uploadedCloudinaryUrls.getOrDefault(
                            "profilePhoto",
                            ""
                    );

            if (PartnerConstants.ACCOUNT_NUMBER.length() >= 4) {

                PartnerConstants.MASKED_ACCOUNT =
                        "•••• •••• " +
                        PartnerConstants.ACCOUNT_NUMBER.substring(
                                PartnerConstants.ACCOUNT_NUMBER.length() - 4
                        );

            } else {

                PartnerConstants.MASKED_ACCOUNT =
                        "•••• •••• " +
                        PartnerConstants.ACCOUNT_NUMBER;
            }

            // =================================================
            // CONTROLLER
            // =================================================

            /*
             * Stage removed from this class.
             *
             * IMPORTANT:
             * DeliveryRegistrationController ke current
             * handleRegistration() method mein agar Stage
             * parameter hai, us controller ko bhi Stage-free
             * rewrite karna padega.
             */

            controller.handleRegistration(
                    partner,
                    password,
                    confirmPassword,
                    termsCheckbox.isSelected(),
                    null
            );
        });

        section.getChildren().addAll(
                termsCheckbox,
                registerButton
        );

        return section;
    }

    // =========================================================
    // UI HELPERS
    // =========================================================

    private static VBox createCard() {

        VBox card =
                new VBox(16);

        card.setMaxWidth(920);

        card.setMinHeight(
                Region.USE_PREF_SIZE
        );

        card.setPadding(
                new Insets(24)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-effect: dropshadow(" +
                "gaussian," +
                "rgba(0,0,0,0.06)," +
                "10,0,0,2);"
        );

        return card;
    }

    private static Text createSectionTitle(
            String text) {

        Text title =
                new Text(text);

        title.setFont(
                Font.font(
                        "System",
                        FontWeight.BOLD,
                        15
                )
        );

        title.setStyle(
                "-fx-fill: #222222;"
        );

        return title;
    }

    private static GridPane createGrid() {

        GridPane grid =
                new GridPane();

        grid.setHgap(16);

        grid.setVgap(14);

        ColumnConstraints col1 =
                new ColumnConstraints();

        ColumnConstraints col2 =
                new ColumnConstraints();

        col1.setPercentWidth(50);

        col2.setPercentWidth(50);

        grid.getColumnConstraints().addAll(
                col1,
                col2
        );

        return grid;
    }

    private static TextField createTextField(
            String prompt) {

        TextField field =
                new TextField();

        field.setPromptText(prompt);

        field.setPrefHeight(38);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );

        return field;
    }

    private static PasswordField createPasswordField(
            String prompt) {

        PasswordField field =
                new PasswordField();

        field.setPromptText(prompt);

        field.setPrefHeight(38);

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );

        return field;
    }

    private static void addField(
            GridPane grid,
            String labelText,
            Node node,
            int column,
            int row) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #555555;"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        node
                );

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
        );

        grid.add(
                box,
                column,
                row
        );
    }

    private static void addFullField(
            GridPane grid,
            String labelText,
            Node node,
            int row) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #555555;"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        node
                );

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
        );

        grid.add(
                box,
                0,
                row,
                2,
                1
        );
    }
}


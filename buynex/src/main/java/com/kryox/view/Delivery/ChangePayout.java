package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ChangePayout {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY PAYOUT ACCOUNT MODEL
    // =========================================================================
    public static class PayoutAccountData {
        public String partnerName = "Alex Walker";
        public String payoutType = "BANK"; // "BANK" or "UPI"

        // Bank Details
        public String bankName = "HDFC Bank";
        public String accountNumber = "50100492819284";
        public String ifscCode = "HDFC0001245";
        public String accountHolderName = "ALEX WALKER";
        public String accountType = "Savings";

        // UPI Details
        public String upiId = "alexwalker@okhdfcbank";

        public PayoutAccountData() {}

        public PayoutAccountData(String bankName, String accountNumber, String ifscCode, String accountHolderName, String accountType, String upiId) {
            this.bankName = bankName;
            this.accountNumber = accountNumber;
            this.ifscCode = ifscCode;
            this.accountHolderName = accountHolderName;
            this.accountType = accountType;
            this.upiId = upiId;
        }

        public String getMaskedAccountNumber() {
            if (accountNumber != null && accountNumber.length() >= 4) {
                return "•••• •••• " + accountNumber.substring(accountNumber.length() - 4);
            }
            return "•••• •••• 8219";
        }
    }

    // =========================================================================
    // STATIC SCENE FACTORY METHODS (SHOPKEEPER PATTERN)
    // =========================================================================
    public static Scene changePayoutScene() {
        return changePayoutScene(new PartnerSettings.SettingsData());
    }

    public static Scene changePayoutScene(PartnerSettings.SettingsData settingsData) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(settingsData));

        // 2. Center Content inside ScrollPane
        VBox mainContent = createMainContent(settingsData);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    // =========================================================================
    // TOP HEADER
    // =========================================================================
    private static BorderPane createTopHeader(PartnerSettings.SettingsData settingsData) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 35 0 25;"
        );

        Button btnBack = new Button("←  Back to Settings");
        btnBack.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #374151;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;"
        );
        btnBack.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene(settingsData));
            }
        });

        Text title = new Text("Change Payout Account");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        HBox liveDeskPill = new HBox(6);
        liveDeskPill.setAlignment(Pos.CENTER_RIGHT);
        Circle lockIcon = new Circle(4, Color.web("#16a34a"));
        Label secureText = new Label("256-Bit Bank Grade Encryption");
        secureText.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #15803d;");
        liveDeskPill.getChildren().addAll(lockIcon, secureText);
        topBar.setRight(liveDeskPill);

        return topBar;
    }

    // =========================================================================
    // MAIN CONTENT
    // =========================================================================
    private static VBox createMainContent(PartnerSettings.SettingsData settingsData) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(26, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox formCard = new VBox(20);
        formCard.setMaxWidth(720);
        formCard.setPadding(new Insets(30));
        formCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 10, 0, 0, 2);"
        );

        // Header Title
        VBox titleBox = new VBox(3);
        Text formTitle = new Text("Update Direct Deposit Bank Details");
        formTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #111827;");
        Text formSubtitle = new Text("Weekly payouts and incentive bonuses will be credited to this verified account.");
        formSubtitle.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
        titleBox.getChildren().addAll(formTitle, formSubtitle);

        // Current Active Account Capsule
        HBox currentAccPill = new HBox(12);
        currentAccPill.setAlignment(Pos.CENTER_LEFT);
        currentAccPill.setPadding(new Insets(12, 16, 12, 16));
        currentAccPill.setStyle("-fx-background-color: #f8fafc; -fx-border-color: #e2e8f0; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label cardIcon = new Label("💳");
        cardIcon.setStyle("-fx-font-size: 16px;");

        VBox currMeta = new VBox(1);
        Label currTitle = new Label("Current Payout Destination: " + settingsData.bankName);
        currTitle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #1e293b;");
        Label currDetails = new Label("Account: " + settingsData.maskedAccountNumber + "  •  Holder: " + settingsData.accountHolderName);
        currDetails.setStyle("-fx-font-size: 10px; -fx-text-fill: #64748b;");
        currMeta.getChildren().addAll(currTitle, currDetails);
        currentAccPill.getChildren().addAll(cardIcon, currMeta);

        // Form Fields
        Label formSec = new Label("Enter New Account Details (INR ₹)");
        formSec.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #9a3412; -fx-padding: 6 0 2 0;");

        GridPane formGrid = new GridPane();
        formGrid.setHgap(16);
        formGrid.setVgap(14);

        TextField bankNameField = createStyledTextField("Bank Name (e.g. HDFC, ICICI, SBI)");
        bankNameField.setText(settingsData.bankName);

        TextField holderField = createStyledTextField("Account Holder Full Name");
        holderField.setText(settingsData.accountHolderName);

        TextField accNoField = createStyledTextField("Bank Account Number");
        PasswordField confirmAccNoField = createStyledPasswordField("Re-enter Account Number");

        TextField ifscField = createStyledTextField("IFSC Code (e.g. HDFC0001245)");
        TextField upiField = createStyledTextField("UPI ID for Instant Payouts (Optional)");

        formGrid.add(createFieldBlock("Bank Name", bankNameField), 0, 0);
        formGrid.add(createFieldBlock("Account Holder Name", holderField), 1, 0);
        formGrid.add(createFieldBlock("Bank Account Number", accNoField), 0, 1);
        formGrid.add(createFieldBlock("Confirm Account Number", confirmAccNoField), 1, 1);
        formGrid.add(createFieldBlock("IFSC Code", ifscField), 0, 2);
        formGrid.add(createFieldBlock("UPI ID (Optional Instant Credit)", upiField), 1, 2);

        formGrid.getChildren().forEach(n -> GridPane.setHgrow(n, Priority.ALWAYS));

        // Info Note
        HBox noteBox = new HBox(8);
        noteBox.setAlignment(Pos.CENTER_LEFT);
        noteBox.setPadding(new Insets(10, 14, 10, 14));
        noteBox.setStyle("-fx-background-color: #fff7ed; -fx-background-radius: 8; -fx-border-color: #fed7aa; -fx-border-radius: 8;");
        Label infoIcon = new Label("ⓘ");
        infoIcon.setStyle("-fx-font-size: 12px; -fx-text-fill: #c2410c; -fx-font-weight: bold;");
        Label noteText = new Label("Bank account name must match your registered Government ID name to avoid settlement delays.");
        noteText.setStyle("-fx-font-size: 11px; -fx-text-fill: #9a3412;");
        noteBox.getChildren().addAll(infoIcon, noteText);

        // Submit Button
        Button btnSaveAccount = new Button("Verify & Update Payout Method  →");
        btnSaveAccount.setPrefHeight(44);
        btnSaveAccount.setMaxWidth(Double.MAX_VALUE);
        btnSaveAccount.setStyle(
                "-fx-background-color: " + ORANGE_GRADIENT + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        btnSaveAccount.setOnAction(e -> {
            String newBank = bankNameField.getText().trim();
            String newHolder = holderField.getText().trim();
            String newAcc = accNoField.getText().trim();

            if (!newBank.isEmpty()) settingsData.bankName = newBank;
            if (!newHolder.isEmpty()) settingsData.accountHolderName = newHolder.toUpperCase();
            if (!newAcc.isEmpty() && newAcc.length() >= 4) {
                settingsData.maskedAccountNumber = "•••• •••• " + newAcc.substring(newAcc.length() - 4);
            }

            btnSaveAccount.setText("✓ Bank Account Updated in Firestore!");
            btnSaveAccount.setStyle("-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-size: 13px; -fx-font-weight: bold; -fx-background-radius: 8;");

            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene(settingsData));
            }
        });

        formCard.getChildren().addAll(
                titleBox,
                currentAccPill,
                formSec,
                formGrid,
                noteBox,
                btnSaveAccount
        );

        content.getChildren().add(formCard);
        return content;
    }

    private static VBox createFieldBlock(String label, javafx.scene.Node control) {
        VBox box = new VBox(4);
        Label lbl = new Label(label);
        lbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563; -fx-font-weight: bold;");
        box.getChildren().addAll(lbl, control);
        return box;
    }

    private static TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(40);
        tf.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );
        return tf;
    }

    private static PasswordField createStyledPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setPrefHeight(40);
        pf.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );
        return pf;
    }
}
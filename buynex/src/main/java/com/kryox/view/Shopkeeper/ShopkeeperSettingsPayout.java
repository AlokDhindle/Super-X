package com.kryox.view.Shopkeeper;

import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperSettingsPayout {

    public static Scene settingsPayoutScene() {
        BorderPane borderPane = new BorderPane();

        // Header
        HBox headerMainBox = ViewConstants.header();
        headerMainBox.setStyle("-fx-background-color: #EBCCB7;");
        borderPane.setTop(headerMainBox);

        // Sidebar
        VBox sidebar = createSidebar();
        borderPane.setLeft(sidebar);

        // Footer
        VBox footerBox = ViewConstants.footer();
        borderPane.setBottom(footerBox);

        // Page Title & Description
        Text settingsTitle = new Text("Payout Settings");
        settingsTitle.setStyle(
                "-fx-font-size: 31px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial;" +
                "-fx-fill: #151515;"
        );

        Text settingsDescription = new Text("Manage your bank account and payout preferences.");
        settingsDescription.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #666666;"
        );

        VBox pageHeading = new VBox(6, settingsTitle, settingsDescription);

        // Settings Sub-Navigation Bar
        Button storeProfileButton = createNavigationButton("▤", "Store Profile", false);
        Button payoutButton = createNavigationButton("₹", "Payout Settings", true);

        HBox settingsNavigation = new HBox(10, storeProfileButton, payoutButton);
        settingsNavigation.setPadding(new Insets(10));
        settingsNavigation.setMaxWidth(490);
        settingsNavigation.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E0D7D2;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 17px;" +
                "-fx-background-radius: 17px;"
        );

        // Bank Account Information Section
        Text payoutInformationTitle = new Text("Bank Account Information");
        payoutInformationTitle.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #151515;"
        );

        Text payoutInformationDescription = new Text("Add the bank account where you want to receive your payouts.");
        payoutInformationDescription.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );

        VBox payoutHeading = new VBox(5, payoutInformationTitle, payoutInformationDescription);

        // Account Holder Name & Bank Name
        Text accountHolderLabel = createLabel("Account Holder Name");
        TextField accountHolderField = createTextField("Enter account holder name");
        VBox accountHolderBox = new VBox(7, accountHolderLabel, accountHolderField);

        Text bankNameLabel = createLabel("Bank Name");
        TextField bankNameField = createTextField("Enter bank name");
        VBox bankNameBox = new VBox(7, bankNameLabel, bankNameField);

        HBox bankInformationRow = new HBox(20, accountHolderBox, bankNameBox);
        HBox.setHgrow(accountHolderBox, Priority.ALWAYS);
        HBox.setHgrow(bankNameBox, Priority.ALWAYS);

        // Account Number & Confirm Account Number
        Text accountNumberLabel = createLabel("Account Number");
        PasswordField accountNumberField = new PasswordField();
        accountNumberField.setPromptText("Enter account number");
        styleInputField(accountNumberField);
        VBox accountNumberBox = new VBox(7, accountNumberLabel, accountNumberField);

        Text confirmAccountLabel = createLabel("Confirm Account Number");
        PasswordField confirmAccountField = new PasswordField();
        confirmAccountField.setPromptText("Re-enter account number");
        styleInputField(confirmAccountField);
        VBox confirmAccountBox = new VBox(7, confirmAccountLabel, confirmAccountField);

        HBox accountNumberRow = new HBox(20, accountNumberBox, confirmAccountBox);
        HBox.setHgrow(accountNumberBox, Priority.ALWAYS);
        HBox.setHgrow(confirmAccountBox, Priority.ALWAYS);

        // IFSC Code & Account Type
        Text ifscLabel = createLabel("IFSC Code");
        TextField ifscField = createTextField("Example: SBIN0001234");
        VBox ifscBox = new VBox(7, ifscLabel, ifscField);

        Text accountTypeLabel = createLabel("Account Type");
        ComboBox<String> accountTypeComboBox = new ComboBox<>();
        accountTypeComboBox.getItems().addAll("Savings Account", "Current Account");
        accountTypeComboBox.setPromptText("Select account type");
        accountTypeComboBox.setPrefHeight(50);
        accountTypeComboBox.setMaxWidth(Double.MAX_VALUE);
        accountTypeComboBox.setStyle(
                "-fx-background-color: #FAF7FB;" +
                "-fx-border-color: #D7C8C0;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 15px;"
        );
        VBox accountTypeBox = new VBox(7, accountTypeLabel, accountTypeComboBox);

        HBox bankDetailsRow = new HBox(20, ifscBox, accountTypeBox);
        HBox.setHgrow(ifscBox, Priority.ALWAYS);
        HBox.setHgrow(accountTypeBox, Priority.ALWAYS);

        // UPI Settings Section
        Text upiTitle = new Text("UPI Settings");
        upiTitle.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #151515;"
        );

        Text upiDescription = new Text("Optionally add a UPI ID for receiving payouts.");
        upiDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );

        VBox upiHeading = new VBox(4, upiTitle, upiDescription);

        Text upiLabel = createLabel("UPI ID");
        TextField upiField = createTextField("Example: shop@upi");
        VBox upiBox = new VBox(7, upiLabel, upiField);

        // Payout Preferences Section
        Text payoutPreferencesTitle = new Text("Payout Preferences");
        payoutPreferencesTitle.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #151515;"
        );

        Text payoutMethodLabel = createLabel("Preferred Payout Method");
        ComboBox<String> payoutMethodComboBox = new ComboBox<>();
        payoutMethodComboBox.getItems().addAll("Bank Transfer", "UPI");
        payoutMethodComboBox.setPromptText("Select payout method");
        payoutMethodComboBox.setPrefHeight(50);
        payoutMethodComboBox.setMaxWidth(Double.MAX_VALUE);
        payoutMethodComboBox.setStyle(
                "-fx-background-color: #FAF7FB;" +
                "-fx-border-color: #D7C8C0;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 15px;"
        );
        VBox payoutMethodBox = new VBox(7, payoutMethodLabel, payoutMethodComboBox);

        Text payoutFrequencyLabel = createLabel("Payout Frequency");
        ComboBox<String> payoutFrequencyComboBox = new ComboBox<>();
        payoutFrequencyComboBox.getItems().addAll("Daily", "Weekly", "Monthly");
        payoutFrequencyComboBox.setPromptText("Select payout frequency");
        payoutFrequencyComboBox.setPrefHeight(50);
        payoutFrequencyComboBox.setMaxWidth(Double.MAX_VALUE);
        payoutFrequencyComboBox.setStyle(
                "-fx-background-color: #FAF7FB;" +
                "-fx-border-color: #D7C8C0;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 15px;"
        );
        VBox payoutFrequencyBox = new VBox(7, payoutFrequencyLabel, payoutFrequencyComboBox);

        HBox payoutPreferenceRow = new HBox(20, payoutMethodBox, payoutFrequencyBox);
        HBox.setHgrow(payoutMethodBox, Priority.ALWAYS);
        HBox.setHgrow(payoutFrequencyBox, Priority.ALWAYS);

        // Security Message Box
        Text securityIcon = new Text("🔒");
        Text securityMessage = new Text("Your payout information is securely stored and used only for processing payments.");
        securityMessage.setWrappingWidth(520);
        securityMessage.setStyle("-fx-font-size: 12px; -fx-fill: #705C52;");

        HBox securityBox = new HBox(10, securityIcon, securityMessage);
        securityBox.setPadding(new Insets(14));
        securityBox.setStyle(
                "-fx-background-color: #FFF5EC;" +
                "-fx-border-color: #F0D5C2;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;"
        );

        // Buttons
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.setPrefHeight(42);
        cancelButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #963A12;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );

        Button saveButton = new Button("Save Payout Settings");
        saveButton.setPrefWidth(190);
        saveButton.setPrefHeight(42);
        saveButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        HBox buttonBox = new HBox(10, cancelButton, saveButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Payout Card Container
        VBox payoutCard = new VBox(
                22,
                payoutHeading,
                bankInformationRow,
                accountNumberRow,
                bankDetailsRow,
                upiHeading,
                upiBox,
                payoutPreferencesTitle,
                payoutPreferenceRow,
                securityBox,
                buttonBox
        );
        payoutCard.setPadding(new Insets(30));
        payoutCard.setPrefWidth(650);
        payoutCard.setMaxWidth(650);
        payoutCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #DDCFC8;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 20px;" +
                "-fx-background-radius: 20px;"
        );

        // Main Layout ScrollPane & Alignment
        VBox settingsMainContent = new VBox(20, settingsNavigation, payoutCard);
        settingsMainContent.setAlignment(Pos.TOP_CENTER);

        VBox centerContent = new VBox(25, pageHeading, settingsMainContent);
        centerContent.setAlignment(Pos.TOP_CENTER);
        centerContent.setPadding(new Insets(25));
        centerContent.setStyle("-fx-background-color: #F8F7FC;");

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #F8F7FC; -fx-border-color: transparent;");

        borderPane.setCenter(scrollPane);

        // Navigation Actions
        storeProfileButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperSettings.settingsScene()));
        cancelButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperSettings.settingsScene()));

        // Save Payout Settings Action
        saveButton.setOnAction(e -> {
            String accountHolder = accountHolderField.getText().trim();
            String bankName = bankNameField.getText().trim();
            String accountNumber = accountNumberField.getText().trim();
            String confirmAccountNumber = confirmAccountField.getText().trim();
            String ifsc = ifscField.getText().trim().toUpperCase();
            String accountType = accountTypeComboBox.getValue();
            String payoutMethod = payoutMethodComboBox.getValue();
            String payoutFrequency = payoutFrequencyComboBox.getValue();

            if (accountHolder.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Account Holder Required", "Please enter the account holder name.");
                return;
            }

            if (bankName.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Bank Name Required", "Please enter the bank name.");
                return;
            }

            if (!accountNumber.matches("[0-9]{9,18}")) {
                showAlert(Alert.AlertType.WARNING, "Invalid Account Number", "Please enter a valid bank account number (9 to 18 digits).");
                return;
            }

            if (!accountNumber.equals(confirmAccountNumber)) {
                showAlert(Alert.AlertType.WARNING, "Account Number Mismatch", "Both account numbers must match.");
                return;
            }

            if (!ifsc.matches("[A-Z]{4}0[A-Z0-9]{6}")) {
                showAlert(Alert.AlertType.WARNING, "Invalid IFSC Code", "Please enter a valid IFSC code (Example: SBIN0001234).");
                return;
            }

            if (accountType == null) {
                showAlert(Alert.AlertType.WARNING, "Account Type Required", "Please select an account type.");
                return;
            }

            if (payoutMethod == null) {
                showAlert(Alert.AlertType.WARNING, "Payout Method Required", "Please select a payout method.");
                return;
            }

            if (payoutFrequency == null) {
                showAlert(Alert.AlertType.WARNING, "Payout Frequency Required", "Please select a payout frequency.");
                return;
            }

            System.out.println("Payout Settings Saved Successfully");
            System.out.println("Account Holder: " + accountHolder);
            System.out.println("Bank Name: " + bankName);
            System.out.println("IFSC: " + ifsc);
            System.out.println("Payout Method: " + payoutMethod);
            System.out.println("Payout Frequency: " + payoutFrequency);

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Payout Settings Saved",
                    "Your payout settings have been saved successfully."
            );
        });

        Scene payoutScene = new Scene(borderPane, 1550, 850);
        payoutScene.setFill(Color.web("#F8F7FC"));
        return payoutScene;
    }

    private static Button createNavigationButton(String icon, String text, boolean selected) {
        Text iconText = new Text(icon);
        iconText.setStyle("-fx-font-size: 21px;" + (selected ? "-fx-fill: #963A12;" : "-fx-fill: #555555;"));

        Text buttonText = new Text(text);
        buttonText.setStyle("-fx-font-size: 14px;" + (selected ? "-fx-font-weight: bold; -fx-fill: #963A12;" : "-fx-fill: #555555;"));

        HBox content = new HBox(15, iconText, buttonText);
        content.setAlignment(Pos.CENTER_LEFT);

        Button button = new Button();
        button.setGraphic(content);
        button.setPrefWidth(220);
        button.setPrefHeight(52);
        button.setAlignment(Pos.CENTER_LEFT);

        if (selected) {
            button.setStyle(
                    "-fx-background-color: #F5F2F5;" +
                    "-fx-border-color: #D8C9C2;" +
                    "-fx-border-radius: 9px;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;"
            );
        } else {
            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;"
            );
        }

        return button;
    }

    private static Text createLabel(String value) {
        Text label = new Text(value);
        label.setStyle("-fx-font-size: 14px; -fx-fill: #222222;");
        return label;
    }

    private static TextField createTextField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        styleInputField(field);
        return field;
    }

    private static void styleInputField(TextField field) {
        field.setPrefHeight(50);
        field.setStyle(
                "-fx-background-color: #FAF7FB;" +
                "-fx-border-color: #D7C8C0;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 0 14px;"
        );
    }

    private static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setMinWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setMaxWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setStyle(
                "-fx-background-color: #EBCCB7;" +
                "-fx-border-color: #E3C7BA;" +
                "-fx-border-width: 0 1px 0 0;"
        );

        HBox profileBox = ViewConstants.letfProfileBox();
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(new Insets(30, 20, 30, 20));

        Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", false);
        Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", false);
        Button bookingsButton = ViewConstants.createDashboardButton("📅", "Bookings", false);
        Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);
        Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", false);
        Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);
        Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", true);
        Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

        VBox menu = new VBox(
                5,
                dashboardButton,
                ordersButton,
                bookingsButton,
                inventoryButton,
                offersButton,
                analyticsButton,
                settingsButton,
                supportButton
        );
        menu.setPadding(new Insets(0, 8, 0, 8));
        VBox.setVgrow(menu, Priority.ALWAYS);

        sidebar.getChildren().addAll(profileBox, menu);

        dashboardButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperDashboard.dashboardScene()));
        ordersButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperOrderReady.ordersScene()));
        bookingsButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperBookedProducts.bookedProductsScene()));
        inventoryButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperInventory.inventoryScene()));
        offersButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene()));
        analyticsButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperAnalytics.analyticsScene()));
        settingsButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperSettings.settingsScene()));
        supportButton.setOnAction(e -> Homepage.HomepageStage.setScene(ShopkeeperSupport.supportScene()));

        return sidebar;
    }
}

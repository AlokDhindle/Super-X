package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.control.Shopkeeper.ShopkeeperDetailsController;
import com.kryox.control.Shopkeeper.ConstantsMethods;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;

public class ShopkeeperSettings {

        public static Scene settingsScene() {

                String shopName = ViewConstants.shopkeeperModel.getShopNameValue();

                String ownerName = ViewConstants.shopkeeperModel.getOwnerNameValue();

                String phone = ViewConstants.shopkeeperModel.getMobileValue();

                String licenseNumber = ViewConstants.shopkeeperModel.getLicenseValue();

                String uniqueId = ViewConstants.shopkeeperModel.getUniqueIdValue();

                String gstNumber = ViewConstants.shopkeeperModel.getGstValue();

                String panNumber = ViewConstants.shopkeeperModel.getPanValue();

                String category = ViewConstants.shopkeeperModel.getCategoryValue();

                String description = ViewConstants.shopkeeperModel.getDescriptionValue();

                String address = ViewConstants.shopkeeperModel.getAddressValue();

                String state = ViewConstants.shopkeeperModel.getStateValue();

                String city = ViewConstants.shopkeeperModel.getCityValue();

                String pinCode = ViewConstants.shopkeeperModel.getPinValue();

                String shopLogoURL = ViewConstants.shopkeeperModel.getShopLogoURL();

                // ============================================================
                // MAIN BORDER PANE
                // ============================================================

                BorderPane borderPane = new BorderPane();

                // ============================================================
                // HEADER
                // ============================================================

                HBox headerMainBox = ViewConstants.header();

                borderPane.setTop(
                                headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = createSidebar();
                borderPane.setLeft(sidebar);

                // ============================================================
                // FOOTER
                // ============================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ============================================================
                // SETTINGS TITLE
                // ============================================================

                Text settingsTitle = new Text(
                                "Settings Dashboard");

                settingsTitle.setStyle(
                                "-fx-font-size: 31px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #f10b0b;");

                // ============================================================
                // SETTINGS LEFT NAVIGATION
                // ============================================================

                // ============================================================
                // STORE PROFILE BUTTON
                // ============================================================

                Text storeProfileIcon = new Text("▤");

                storeProfileIcon.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-fill: #963A12;");

                Text storeProfileText = new Text("Store Profile");

                storeProfileText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #963A12;");

                HBox storeProfileContent = new HBox(
                                15,
                                storeProfileIcon,
                                storeProfileText);

                storeProfileContent.setAlignment(
                                Pos.CENTER_LEFT);

                Button storeProfileButton = new Button();

                storeProfileButton.setGraphic(
                                storeProfileContent);

                storeProfileButton.setPrefWidth(
                                220);

                storeProfileButton.setPrefHeight(
                                52);

                storeProfileButton.setAlignment(
                                Pos.CENTER_LEFT);

                storeProfileButton.setStyle(
                                "-fx-background-color: #F5F2F5;" +
                                                "-fx-border-color: #D8C9C2;" +
                                                "-fx-border-radius: 9px;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");

                // ============================================================
                // PAYOUT BUTTON
                // ============================================================

                Text payoutIcon = new Text("₹");

                payoutIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #555555;");

                Text payoutText = new Text(
                                "Payout Settings");

                payoutText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");

                HBox payoutContent = new HBox(
                                15,
                                payoutIcon,
                                payoutText);

                payoutContent.setAlignment(
                                Pos.CENTER_LEFT);

                Button payoutButton = new Button();

                payoutButton.setGraphic(
                                payoutContent);

                payoutButton.setPrefWidth(
                                220);

                payoutButton.setPrefHeight(
                                52);

                payoutButton.setAlignment(
                                Pos.CENTER_LEFT);

                payoutButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");

                // ============================================================
                // SETTINGS NAVIGATION
                // ============================================================

                HBox settingsNavigation = new HBox(
                                10,
                                storeProfileButton,
                                payoutButton);

                settingsNavigation.setPadding(
                                new Insets(10));

                settingsNavigation.setMaxWidth(
                                490);

                settingsNavigation.setMaxHeight(
                                80);

                settingsNavigation.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0D7D2;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 17px;" +
                                                "-fx-background-radius: 17px;");

                // ============================================================
                // STORE PROFILE HEADING
                // ============================================================

                Text storeProfileTitle = new Text(
                                "Store Profile");

                storeProfileTitle.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #151515;");

                Text storeProfileDescription = new Text(
                                "Manage how your store appears to customers on\n" +
                                                "BuyNeX.");

                storeProfileDescription.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: #666666;");

                VBox storeProfileHeading = new VBox(
                                5,
                                storeProfileTitle,
                                storeProfileDescription);

                // ============================================================
                // STORE LOGO
                // ============================================================

                // ============================================================
                // STORE LOGO FROM DETAILS PAGE
                // ============================================================

                ImageView logoImageView = new ImageView();

                if (shopLogoURL != null &&
                                !shopLogoURL.isBlank()) {

                        Image logoImage = new Image(
                                        shopLogoURL,
                                        100,
                                        100,
                                        false,
                                        true);

                        logoImageView.setImage(
                                        logoImage);
                }

                logoImageView.setFitWidth(100);
                logoImageView.setFitHeight(100);
                logoImageView.setPreserveRatio(false);

                Circle logoClip = new Circle(
                                50,
                                50,
                                50);

                logoImageView.setClip(
                                logoClip);

                Circle logoBorder = new Circle(50);

                logoBorder.setFill(
                                Color.TRANSPARENT);

                logoBorder.setStroke(
                                Color.web("#E1DFE1"));

                logoBorder.setStrokeWidth(2);

                StackPane logoContainer = new StackPane(
                                logoImageView,
                                logoBorder);

                logoContainer.setPrefSize(
                                100,
                                100);

                logoContainer.setMinSize(
                                100,
                                100);

                logoContainer.setMaxSize(
                                100,
                                100);

                Text logoTitle = new Text(
                                "Store Logo");

                logoTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #222222;");

                Text logoDescription = new Text(
                                "Recommended size: 500×500px (JPG or PNG).");

                logoDescription.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #666666;");

                // Button changeLogoButton =
                // new Button(
                // "Change Logo");

                // changeLogoButton.setPrefWidth(
                // 100);

                // changeLogoButton.setPrefHeight(
                // 30);

                // changeLogoButton.setStyle(
                // "-fx-background-color: #FFFFFF;" +
                // "-fx-border-color: #333333;" +
                // "-fx-border-radius: 8px;" +
                // "-fx-background-radius: 8px;" +
                // "-fx-font-size: 13px;" +
                // "-fx-cursor: hand;");

                // HBox logoButtons =
                // new HBox(
                // 12,
                // changeLogoButton
                // );

                // logoButtons.setAlignment(
                // Pos.CENTER_LEFT);

                VBox logoInformation = new VBox(
                                5,
                                logoTitle,
                                logoDescription
                // logoButtons
                );

                logoInformation.setAlignment(
                                Pos.CENTER_LEFT);

                HBox logoSection = new HBox(
                                25,
                                logoContainer,
                                logoInformation);

                logoSection.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // STORE NAME
                // ============================================================

                Text storeNameLabel = new Text(
                                "Store Name");

                storeNameLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField storeNameField = new TextField(
                                shopName);

                storeNameField.setPrefHeight(
                                50);

                storeNameField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox storeNameBox = new VBox(
                                7,
                                storeNameLabel,
                                storeNameField);

                // ============================================================
                // CATEGORY
                // ============================================================

                Text categoryLabel = new Text(
                                "Category");

                categoryLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                ComboBox<String> categoryComboBox = new ComboBox<>();

                categoryComboBox.getItems().addAll(
                                "Grocery & Essentials",
                                "Fresh Produce",
                                "Bakery",
                                "Beverages",
                                "Household",
                                "Fashion",
                                "Grocery & Supermarket",
                                "Fashion & Clothing",
                                "Footwear & Accessories",
                                "Electronics & Mobile",
                                "Home & Kitchen",
                                "Beauty & Personal Care",
                                "Pharmacy & Healthcare",
                                "Books & Stationery",
                                "Toys, Kids & Baby",
                                "Sports & Fitness",
                                "Hardware & Electrical",
                                "Automotive",
                                "Pet Supplies",
                                "Gifts, Flowers & Lifestyle",
                                "Food & Fresh Produce",
                                "Other");

                categoryComboBox.setValue(
                                category);

                categoryComboBox.setPrefHeight(
                                50);

                categoryComboBox.setMaxWidth(
                                Double.MAX_VALUE);

                categoryComboBox.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;");

                VBox categoryBox = new VBox(
                                7,
                                categoryLabel,
                                categoryComboBox);

                HBox.setHgrow(
                                storeNameBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                categoryBox,
                                Priority.ALWAYS);

                HBox storeBasicDetails = new HBox(
                                20,
                                storeNameBox,
                                categoryBox);

                // ============================================================
                // OWNER NAME
                // ============================================================

                Text ownerNameLabel = new Text("Owner Name");

                ownerNameLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField ownerNameField = new TextField(ownerName);

                ownerNameField.setPrefHeight(50);

                ownerNameField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox ownerNameBox = new VBox(
                                7,
                                ownerNameLabel,
                                ownerNameField);

                // ============================================================
                // BUSINESS MOBILE
                // ============================================================

                Text businessMobileLabel = new Text("Business Mobile");

                businessMobileLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField businessMobileField = new TextField(phone);

                businessMobileField.setPrefHeight(50);

                businessMobileField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox businessMobileBox = new VBox(
                                7,
                                businessMobileLabel,
                                businessMobileField);

                HBox ownerMobileRow = new HBox(
                                20,
                                ownerNameBox,
                                businessMobileBox);

                HBox.setHgrow(
                                ownerNameBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                businessMobileBox,
                                Priority.ALWAYS);

                // ============================================================
                // LICENSE + UNIQUE ID
                // ============================================================

                Text licenseLabel = new Text("License Number");

                licenseLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField licenseField = new TextField(licenseNumber);

                licenseField.setPrefHeight(50);

                licenseField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox licenseBox = new VBox(
                                7,
                                licenseLabel,
                                licenseField);

                Text uniqueIdLabel = new Text("Shop Unique ID");

                uniqueIdLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField uniqueIdField = new TextField(uniqueId);

                uniqueIdField.setEditable(false);
                uniqueIdField.setPrefHeight(50);

                uniqueIdField.setStyle(
                                "-fx-background-color: #F1F1F1;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox uniqueIdBox = new VBox(
                                7,
                                uniqueIdLabel,
                                uniqueIdField);

                HBox licenseUniqueIdRow = new HBox(
                                20,
                                licenseBox,
                                uniqueIdBox);

                HBox.setHgrow(
                                licenseBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                uniqueIdBox,
                                Priority.ALWAYS);

                // ============================================================
                // PAN + GST
                // ============================================================

                Text panLabel = new Text("PAN");

                panLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField panField = new TextField(panNumber);

                panField.setPrefHeight(50);

                panField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox panBox = new VBox(
                                7,
                                panLabel,
                                panField);

                Text gstLabel = new Text("GST Number");

                gstLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField gstField = new TextField(gstNumber);

                gstField.setPrefHeight(50);

                gstField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox gstBox = new VBox(
                                7,
                                gstLabel,
                                gstField);

                HBox panGstRow = new HBox(
                                20,
                                panBox,
                                gstBox);

                HBox.setHgrow(
                                panBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                gstBox,
                                Priority.ALWAYS);

                // ============================================================
                // STATE
                // ============================================================

                Text stateLabel = new Text("State");

                stateLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField stateField = new TextField(state);

                stateField.setPrefHeight(50);

                stateField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox stateBox = new VBox(
                                7,
                                stateLabel,
                                stateField);

                // ============================================================
                // DESCRIPTION
                // ============================================================

                Text descriptionLabel = new Text(
                                "Store Description");

                descriptionLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextArea descriptionArea = new TextArea(
                                description);

                descriptionArea.setPrefHeight(
                                105);

                descriptionArea.setWrapText(
                                true);

                descriptionArea.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 12px;");

                VBox descriptionBox = new VBox(
                                7,
                                descriptionLabel,
                                descriptionArea);

                // ============================================================
                // BUSINESS ADDRESS
                // ============================================================

                Text addressLabel = new Text(
                                "Business Address");

                addressLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField addressField = new TextField(
                                address);

                addressField.setPrefHeight(
                                50);

                addressField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox addressBox = new VBox(
                                7,
                                addressLabel,
                                addressField);

                // ============================================================
                // CITY
                // ============================================================

                Text cityLabel = new Text(
                                "City");

                cityLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField cityField = new TextField(
                                city);

                cityField.setPrefHeight(
                                50);

                cityField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox cityBox = new VBox(
                                7,
                                cityLabel,
                                cityField);

                // ============================================================
                // PIN CODE
                // ============================================================

                Text pinLabel = new Text(
                                "PIN Code");

                pinLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField pinCodeField = new TextField(
                                pinCode);

                pinCodeField.setPrefHeight(
                                50);

                pinCodeField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox pinBox = new VBox(
                                7,
                                pinLabel,
                                pinCodeField);

                HBox cityZipBox = new HBox(
                                20,
                                cityBox,
                                pinBox);

                HBox.setHgrow(
                                cityBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                pinBox,
                                Priority.ALWAYS);

                // ============================================================
                // PHONE
                // ============================================================

                Text phoneLabel = new Text(
                                "Contact Phone");

                phoneLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #222222;");

                TextField phoneField = new TextField(
                                phone);

                phoneField.setPrefHeight(
                                50);

                phoneField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                                "-fx-border-color: #D7C8C0;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-padding: 0 14px;");

                VBox phoneBox = new VBox(
                                7,
                                phoneLabel,
                                phoneField);

                // ============================================================
                // SAVE BUTTONS
                // ============================================================

                Button discardButton = new Button(
                                "Discard");

                discardButton.setPrefWidth(
                                95);

                discardButton.setPrefHeight(
                                42);

                discardButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #963A12;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-cursor: hand;");

                Button saveChangesButton = new Button(
                                "Save Changes");

                saveChangesButton.setPrefWidth(
                                155);

                saveChangesButton.setPrefHeight(
                                42);

                saveChangesButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-cursor: hand;");

                HBox saveButtons = new HBox(
                                10,
                                discardButton,
                                saveChangesButton);

                saveButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                // ============================================================
                // STORE PROFILE CARD
                // ============================================================

                VBox storeProfileCard = new VBox(
                                22,
                                storeProfileHeading,
                                logoSection,
                                storeBasicDetails,
                                ownerMobileRow,
                                licenseUniqueIdRow,
                                panGstRow,
                                descriptionBox,
                                addressBox,
                                stateBox,
                                cityZipBox,
                                phoneBox,
                                saveButtons);

                storeProfileCard.setPadding(
                                new Insets(30));

                storeProfileCard.setPrefWidth(
                                650);

                storeProfileCard.setMaxWidth(
                                650);

                storeProfileCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #DDCFC8;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 20px;" +
                                                "-fx-background-radius: 20px;");

                DropShadow shadow = new DropShadow();

                shadow.setRadius(
                                10);

                shadow.setColor(
                                Color.rgb(
                                                80,
                                                60,
                                                50,
                                                0.08));

                storeProfileCard.setEffect(
                                shadow);

                // ============================================================
                // MAIN CONTENT
                // ============================================================


                settingsNavigation.setAlignment(
                                Pos.CENTER_LEFT);

                VBox settingsMainContent = new VBox(
                                20,
                                settingsNavigation,
                                storeProfileCard);

                settingsMainContent.setAlignment(
                                Pos.TOP_CENTER);

                VBox centerContent = new VBox(
                                25,
                                settingsTitle,
                                settingsMainContent);

                centerContent.setAlignment(
                                Pos.TOP_CENTER);

                centerContent.setPadding(
                                new Insets(25));

                centerContent.setStyle(
                                "-fx-background-color: #F8F7FC;");

                ScrollPane scrollPane = new ScrollPane(
                                centerContent);

                scrollPane.setFitToWidth(
                                true);

                scrollPane.setFitToHeight(
                                false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: #F8F7FC;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                scrollPane);

                // ============================================================
                // BUTTON ACTIONS
                // ============================================================

                storeProfileButton.setOnAction(e -> {

                        System.out.println(
                                        "Store Profile selected");
                });

                payoutButton.setOnAction(e -> {

                        System.out.println(
                                        "Payout Settings selected");
                });

                // changeLogoButton.setOnAction(e -> {

                // System.out.println(
                // "Change Logo clicked");
                // });

                // ============================================================
                // SAVE CHANGES
                // ============================================================

                saveChangesButton.setOnAction(e -> {

                        String shopNameValue = storeNameField.getText().trim().toUpperCase();

                        if (shopNameValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Store Name Required",
                                                "Please enter your store name.");
                                return;
                        }

                        String ownerNameValue = ownerNameField.getText().trim().toUpperCase();
                        if (ownerNameValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Owner Name Required",
                                                "Please enter the owner's full name.");
                                return;
                        }

                        String mobileValue = businessMobileField.getText().trim();
                        if (!mobileValue.matches(
                                        "[6-9][0-9]{9}")) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Invalid Mobile Number",
                                                "Enter a valid 10-digit Indian mobile number.");
                                return;
                        }

                        String panValue = panField.getText().trim().toUpperCase();
                        if (!panValue.matches(
                                        "[A-Z]{5}[0-9]{4}[A-Z]")) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Invalid PAN",
                                                "Enter a valid 10-character PAN, for example ABCDE1234F.");
                                return;
                        }

                        String gstValue = gstField.getText().trim().toUpperCase();
                        if (!gstValue.matches(
                                        "[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]")) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Invalid GSTIN",
                                                "Enter a valid 15-character GSTIN .");
                                return;
                        }

                        String categoryValue = categoryComboBox.getValue();
                        if (categoryValue == null ||
                                        categoryValue.isBlank()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Business Category Required",
                                                "Please select your business category.");
                                return;
                        }

                        String addressValue = addressField.getText().trim();
                        if (addressValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Store Address Required",
                                                "Please enter the complete store address.");
                                return;
                        }

                        String stateValue = stateField.getText().trim().toUpperCase();
                        if (stateValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "State Required",
                                                "Please enter the store state.");
                                return;
                        }

                        String cityValue = cityField.getText().trim().toUpperCase();
                        if (cityValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "City Required",
                                                "Please enter the store city.");
                                return;
                        }

                        String pinValue = pinCodeField.getText().trim();
                        if (!pinCodeField.getText()
                                        .trim()
                                        .matches("[1-9][0-9]{5}")) {
                                ShopkeeperDetails.showValidationAlert(
                                                "Invalid PIN Code",
                                                "Enter a valid 6-digit Indian PIN code.");
                                return;
                        }

                        String licenseValue = licenseField.getText().trim();
                        if (licenseValue.isEmpty()) {
                                ShopkeeperDetails.showValidationAlert(
                                                "License Number Required",
                                                "Please enter the license number.");
                                return;
                        }
                        new Thread(() -> {
                                ShopkeeperDetailsController.updateShopkeeperDetails(
                                                shopNameValue,
                                                ownerNameValue,
                                                mobileValue,
                                                panValue,
                                                gstValue,
                                                categoryValue,
                                                addressValue,
                                                stateValue,
                                                cityValue,
                                                pinValue,
                                                licenseValue);
                        }).start();

                        ConstantsMethods.showAlert(
                                        Alert.AlertType.INFORMATION,
                                        "Save Changes",
                                        "Changes saved successfully.");

                });

                // ============================================================
                // DISCARD CHANGES
                // ============================================================

                discardButton.setOnAction(e -> {

                        storeNameField.setText(
                                        shopName);

                        categoryComboBox.setValue(
                                        category);

                        descriptionArea.setText(
                                        description);

                        addressField.setText(
                                        address);

                        cityField.setText(
                                        city);

                        pinCodeField.setText(
                                        pinCode);

                        phoneField.setText(
                                        phone);

                        ownerNameField.setText(
                                        ownerName);

                        businessMobileField.setText(
                                        phone);

                        licenseField.setText(
                                        licenseNumber);

                        uniqueIdField.setText(
                                        uniqueId);

                        panField.setText(
                                        panNumber);

                        gstField.setText(
                                        gstNumber);

                        stateField.setText(
                                        state);

                        System.out.println(
                                        "Changes discarded");
                });

                // ============================================================
                // SCENE
                // ============================================================

                Scene settingsScene = new Scene(
                                borderPane,
                                ViewConstants.STAGE_WIDTH,
                                ViewConstants.STAGE_HEIGHT
                                                );

                settingsScene.setFill(
                                Color.web("#F8F7FC"));

                return settingsScene;
        }

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                false);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                false);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                true);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));

                offersButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));

                analyticsButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperAnalytics
                                                                .analyticsScene()));

 

                supportButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }

}
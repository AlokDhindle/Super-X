package com.kryox.view.Shopkeeper;

import java.io.File;
import java.util.UUID;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ImageUploadController;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ShopkeeperDetails {

    private static Scene shopDetailsScene;

    private static String shopLogoURL;
    private static String shopPhotoURL;
    private static String licenseDocumentURL;
    private static String gstCertificateURL;


    public static Scene detailsScene() {

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
<<<<<<< HEAD
                "-fx-background-color: #ffffff;");
=======
                "-fx-background-color: #EEE5DF;");
>>>>>>> Sayali

        // =========================================================
        // HEADER
        // =========================================================

        HBox headerBox = ViewConstants.loginHeader();

<<<<<<< HEAD
=======
        // Header background
        headerBox.setStyle(
                "-fx-background-color: #EBCCB7;");

>>>>>>> Sayali
        borderPane.setTop(headerBox);

        // =========================================================
        // PAGE INTRODUCTION
        // =========================================================

        Text shopInformationText =
                new Text("Shop Information");

        shopInformationText.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #252525;");

        Text shopInformationSubText =
                new Text(
                        "Tell us about your business to help customers find you.");

        shopInformationSubText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #795548;");

        VBox introTextBox =
                new VBox(
                        5,
                        shopInformationText,
                        shopInformationSubText);

        introTextBox.setAlignment(
                Pos.CENTER_LEFT);

        Text stepText =
                new Text("Step 2 of 2");

        stepText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #A83B16;");

        HBox stepTextBox =
                new HBox(stepText);

        stepTextBox.setAlignment(
                Pos.CENTER_RIGHT);

        HBox introHBox =
                new HBox(
                        introTextBox,
                        stepTextBox);

        HBox.setHgrow(
                introTextBox,
                javafx.scene.layout.Priority.ALWAYS);

        HBox.setHgrow(
                stepTextBox,
                javafx.scene.layout.Priority.ALWAYS);

        introHBox.setAlignment(
                Pos.CENTER);

        introHBox.setPrefWidth(800);

        // =========================================================
        // PROGRESS BAR
        // =========================================================

        HBox progressCompleted =
                new HBox();

        progressCompleted.setPrefWidth(800);

        progressCompleted.setPrefHeight(6);

        progressCompleted.setStyle(
                "-fx-background-color: linear-gradient(to right, #A52B08, #FF6A00);" +
                "-fx-background-radius: 5px;");

        VBox pageIntroBox =
                new VBox(
                        7,
                        introHBox,
                        progressCompleted);

        pageIntroBox.setAlignment(
                Pos.CENTER);

        pageIntroBox.setPrefWidth(800);

        // =========================================================
        // SHOP NAME
        // =========================================================

        Text shopNameText =
                new Text("Shop Name *");

        shopNameText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #654B44;");

        TextField shopName =
                new TextField();

        shopName.setPromptText(
                "Enter registered business name");

        shopName.setPrefWidth(350);

        shopName.setPrefHeight(33);

        shopName.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // OWNER NAME
        // =========================================================

        Text ownerNameText =
                new Text("Owner Name *");

        ownerNameText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #654B44;");

        TextField ownerName =
                new TextField();

        ownerName.setPromptText(
                "Enter owner's full name");

        ownerName.setPrefWidth(350);

        ownerName.setPrefHeight(33);

        ownerName.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // BUSINESS MOBILE
        // =========================================================

        Text businessMobileText =
                new Text("Business Mobile *");

        businessMobileText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextField businessMobile =
                new TextField();

        businessMobile.setPromptText(
                "10-digit mobile number");

        businessMobile.setPrefWidth(350);

        businessMobile.setPrefHeight(33);

        businessMobile.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // LICENSE + UNIQUE ID
        // =========================================================

        Text licenseText =
                new Text("License Number");

        licenseText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextField licenseNumber =
                new TextField();

        licenseNumber.setPromptText(
                "Trade license no.");

        licenseNumber.setPrefWidth(185);

        licenseNumber.setPrefHeight(33);

        licenseNumber.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        VBox licenseBox =
                new VBox(
                        5,
                        licenseText,
                        licenseNumber);

        Text uniqueIdText =
                new Text("Shop Unique ID");

        uniqueIdText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        String id = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .replace("_", "")
                .replace(":", "")
                .replace(" ", "")
                .substring(0, 4)
                .toUpperCase();

        TextField uniqueId =
                new TextField("BNX-SHOP-"+id+"-X");

        uniqueId.setEditable(false);

        uniqueId.setPrefWidth(185);

        uniqueId.setPrefHeight(33);

        uniqueId.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        VBox uniqueIdBox =
                new VBox(
                        5,
                        uniqueIdText,
                        uniqueId);

        HBox licenseRow =
                new HBox(
                        20,
                        licenseBox,
                        uniqueIdBox);

        licenseRow.setAlignment(
                Pos.CENTER_LEFT);

        // =========================================================
        // GST STATUS
        // =========================================================

        Text gstStatusText =
                new Text("GST Status");

        gstStatusText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        RadioButton haveGST =
                new RadioButton("I have GST");

        RadioButton noGST =
                new RadioButton("I don't have GST");

        ToggleGroup gstGroup =
                new ToggleGroup();

        haveGST.setToggleGroup(gstGroup);

        noGST.setToggleGroup(gstGroup);

        haveGST.setSelected(true);

        HBox gstRadioBox =
                new HBox(
                        20,
                        haveGST,
                        noGST);

        gstRadioBox.setAlignment(
                Pos.CENTER_LEFT);

        gstRadioBox.setPadding(
                new Insets(
                        8,
                        10,
                        8,
                        10));

        VBox gstBox =
                new VBox(gstRadioBox);

        gstBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        Text gstNote =
                new Text(
                        "Enter GSTIN if your business is GST-registered " +
                        "or GST registration applies to you.");

        gstNote.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-fill: #777777;");

        // =========================================================
        // GST NUMBER
        // =========================================================

        Text gstNumberText =
                new Text("GST Number");

        gstNumberText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextField gstNumber =
                new TextField();
        if (!haveGST.isSelected()) {
                gstNumber.setDisable(true);
        }
        gstNumber.setPromptText(
                "Enter 15-digit GSTIN");

        gstNumber.setPrefWidth(350);

        gstNumber.setPrefHeight(33);

        gstNumber.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // PAN
        // =========================================================

        Text panText =
                new Text("PAN *");

        panText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextField panNumber =
                new TextField();

        panNumber.setPromptText(
                "Enter PAN (e.g. ABCDE1234F)");

        panNumber.setPrefWidth(350);

        panNumber.setPrefHeight(33);

        panNumber.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // BUSINESS CATEGORY
        // =========================================================

        Text categoryText =
                new Text("Business Category");

        categoryText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        ComboBox<String> comboBox =
                new ComboBox<>();

        comboBox.getItems().addAll(
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

        comboBox.setPromptText(
                "Select Category");

        comboBox.setPrefWidth(350);

        comboBox.setPrefHeight(33);

        comboBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-text-fill: #555555;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-element-color: #B84A18;");

        comboBox.setOnAction(e -> {

            String categorySelected =
                    comboBox.getValue();
        });

        // =========================================================
        // SHOP ADDRESS
        // =========================================================

        Text addressText =
                new Text("Shop Address *");

        addressText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextArea shopAddress =
                new TextArea();

        shopAddress.setPromptText(
                "Shop no., building, street, area, landmark");

        shopAddress.setPrefWidth(350);

        shopAddress.setPrefHeight(58);

        shopAddress.setWrapText(true);

        shopAddress.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // STORE DESCRIPTION
        // =========================================================

        Text descriptionText =
                new Text("Store Description");

        descriptionText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        TextArea storeDescription =
                new TextArea();

        storeDescription.setPromptText(
                "Tell customers about your shop, products, and services");

        storeDescription.setPrefWidth(350);
        storeDescription.setPrefHeight(75);
        storeDescription.setWrapText(true);

        storeDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        VBox descriptionBox =
                new VBox(
                        5,
                        descriptionText,
                        storeDescription);

        // =========================================================
        // STATE CITY PIN
        // =========================================================

        TextField state =
                new TextField();

        state.setPromptText(
                "e.g. Maharashtra");

        state.setPrefWidth(120);

        state.setPrefHeight(33);

        state.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        TextField city =
                new TextField();

        city.setPromptText(
                "e.g. Mumbai");

        city.setPrefWidth(120);

        city.setPrefHeight(33);

        city.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        TextField pinCode =
                new TextField();

        pinCode.setPromptText(
                "400001");

        pinCode.setPrefWidth(120);

        pinCode.setPrefHeight(33);

        pinCode.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #999999;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        Text stateLabel =
                new Text("State");

        Text cityLabel =
                new Text("City");

        Text pinLabel =
                new Text("PIN Code");

        VBox stateBox =
                new VBox(
                        5,
                        stateLabel,
                        state);

        VBox cityBox =
                new VBox(
                        5,
                        cityLabel,
                        city);

        VBox pinBox =
                new VBox(
                        5,
                        pinLabel,
                        pinCode);

        HBox locationBox =
                new HBox(
                        10,
                        stateBox,
                        cityBox,
                        pinBox);

        // =========================================================
        // MAP LOCATION
        // =========================================================

        Text mapTitle =
                new Text(
                        "Google Map Location Picker");

        mapTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #654B44;");

        Text mapIcon =
                new Text("\uf041");

        mapIcon.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-fill: #B84A18;");

        Text mapText =
                new Text(
                        "Pinpoint your shop on the map");

        Button pickLocationButton =
                new Button("Pick Location");

        pickLocationButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #A83B16;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;");

        VBox mapContent =
                new VBox(
                        5,
                        mapIcon,
                        mapText,
                        pickLocationButton);

        mapContent.setAlignment(
                Pos.CENTER);

        VBox mapBox =
                new VBox(mapContent);

        mapBox.setAlignment(
                Pos.CENTER);

        mapBox.setPrefWidth(350);
        mapBox.setMinWidth(350);
        mapBox.setMaxWidth(350);

        mapBox.setPrefHeight(110);
        mapBox.setMinHeight(110);

        mapBox.setPadding(
                new Insets(10));

        mapBox.setStyle(
                "-fx-background-color: #F1F1F9;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // =========================================================
        // SHOP DOCUMENT / IMAGE BUTTONS
        // =========================================================

        Button shopLogoButton =
                new Button("🏪 SHOP LOGO");

        Button shopPhotoButton =
                new Button("📷 SHOP PHOTO");

        Button licenseDocumentButton =
                new Button("📝 SHOP LICENSE");

        Button gstCertificateButton =
                new Button(
                        "📜 GST CERTIFICATE\n(OPTIONAL)");
        if (!haveGST.isSelected()) {
                gstCertificateButton.setDisable(true);
        }

        Button[] documentButtons = {
                shopLogoButton,
                shopPhotoButton,
                licenseDocumentButton,
                gstCertificateButton
        };

        for (Button button : documentButtons) {

            button.setPrefWidth(110);
            button.setMinWidth(110);
            button.setMaxWidth(110);

            button.setPrefHeight(65);
            button.setMinHeight(65);

            button.setWrapText(true);

            button.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-text-fill: #555555;" +
                    "-fx-font-size: 9px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #E5CFC5;" +
                    "-fx-border-width: 1px;" +
                    "-fx-border-radius: 9px;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        }

        HBox documentBox =
                new HBox(
                        12,
                        shopLogoButton,
                        shopPhotoButton,
                        licenseDocumentButton,
                        gstCertificateButton);

        documentBox.setAlignment(
                Pos.CENTER_LEFT);

        documentBox.setPrefWidth(350);

        // ---------------------------------------------------------
        // Selected file names
        // ---------------------------------------------------------

        Text selectedFilesText =
                new Text("No files selected");

        selectedFilesText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #777777;");

        // ---------------------------------------------------------
        // File chooser helper
        // ---------------------------------------------------------


        shopLogoButton.setOnAction(e ->{
                FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle(
                            "Select Image");

                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.webp"));

                    File selectedFile =
                            fileChooser.showOpenDialog(
                                    Homepage.HomepageStage);

                    if (selectedFile != null) {

                        shopLogoButton.setText(
                                selectedFile.getName());

                        shopLogoButton.setStyle(
                                "-fx-background-color: #FFF0E9;" +
                                "-fx-text-fill: #A83B16;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-border-color: #C95016;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;");

                        selectedFilesText.setText(
                                "Selected: " +
                                selectedFile.getName());
                        shopLogoURL = ImageUploadController.imageUpload(selectedFile);
                    }
        });

        shopPhotoButton.setOnAction(e ->{
                FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle(
                            "Select Image");

                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.webp"));

                    File selectedFile =
                            fileChooser.showOpenDialog(
                                    Homepage.HomepageStage);

                    if (selectedFile != null) {

                        shopPhotoButton.setText(
                                selectedFile.getName());

                        shopPhotoButton.setStyle(
                                "-fx-background-color: #FFF0E9;" +
                                "-fx-text-fill: #A83B16;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-border-color: #C95016;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;");

                        selectedFilesText.setText(
                                "Selected: " +
                                selectedFile.getName());
                        shopPhotoURL = ImageUploadController.imageUpload(selectedFile);
                    }
    });

        licenseDocumentButton.setOnAction(e ->{
                FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle(
                            "Select Image");

                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.webp"));

                    File selectedFile =
                            fileChooser.showOpenDialog(
                                    Homepage.HomepageStage);

                    if (selectedFile != null) {

                        licenseDocumentButton.setText(
                                selectedFile.getName());

                        licenseDocumentButton.setStyle(
                                "-fx-background-color: #FFF0E9;" +
                                "-fx-text-fill: #A83B16;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-border-color: #C95016;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;");

                        selectedFilesText.setText(
                                "Selected: " +
                                selectedFile.getName());
                        licenseDocumentURL = ImageUploadController.imageUpload(selectedFile);
                    }
    });

        gstCertificateButton.setOnAction(e ->{
                FileChooser fileChooser =
                            new FileChooser();

                    fileChooser.setTitle(
                            "Select Image");

                    fileChooser.getExtensionFilters().add(
                            new FileChooser.ExtensionFilter(
                                    "Image Files",
                                    "*.png",
                                    "*.jpg",
                                    "*.jpeg",
                                    "*.webp"));

                    File selectedFile =
                            fileChooser.showOpenDialog(
                                    Homepage.HomepageStage);

                    if (selectedFile != null) {

                        gstCertificateButton.setText(
                                selectedFile.getName());

                        gstCertificateButton.setStyle(
                                "-fx-background-color: #FFF0E9;" +
                                "-fx-text-fill: #A83B16;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-border-color: #C95016;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;");

                        selectedFilesText.setText(
                                "Selected: " +
                                selectedFile.getName());
                        gstCertificateURL = ImageUploadController.imageUpload(selectedFile);
                    }
    });

        // =========================================================
        // BACK BUTTON
        // =========================================================

        Button backButton =
                new Button("←  Back");

        backButton.setPrefWidth(105);

        backButton.setPrefHeight(40);

        backButton.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #E5CFC5;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;");

        backButton.setOnAction(e -> {

            System.out.println(
                    "Back button clicked");

            ShopkeeperLogController.resetRegistration();

            Homepage.HomepageStage.setScene(
                    ShopkeeperRegistration.loginscene());
        });

        // =========================================================
        // REGISTER BUTTON
        // =========================================================

        Button registerButton =
                new Button("Register Shop");

        registerButton.setPrefWidth(155);

        registerButton.setPrefHeight(40);

        registerButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #B84208, #F36A00);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;");

        registerButton.setOnAction(e -> {

            String shopNameValue =
                    shopName.getText().trim().toUpperCase();

            String ownerNameValue =
                    ownerName.getText().trim().toUpperCase();

            String mobileValue =
                    businessMobile.getText().trim();

            String panValue =
                    panNumber.getText().trim().toUpperCase();

            String gstValue =
                    gstNumber.getText().trim().toUpperCase();

            String categoryValue =
                    comboBox.getValue();

            String addressValue =
                    shopAddress.getText().trim();

            String descriptionValue =
                    storeDescription.getText().trim();

            String stateValue =
                    state.getText().trim().toUpperCase();

            String cityValue =
                    city.getText().trim().toUpperCase();

            String pinValue =
                    pinCode.getText().trim();

            String licenseValue =
                    licenseNumber.getText().trim();

            String uniqueIdValue =
                    uniqueId.getText().trim();

            // =====================================================
            // SHOP NAME VALIDATION
            // =====================================================

            if (shopNameValue.isEmpty()) {

                showValidationAlert(
                        "Shop Name Required",
                        "Please enter your shop or registered business name.");

                shopName.requestFocus();

                return;
            }

            // =====================================================
            // OWNER NAME VALIDATION
            // =====================================================

            if (ownerNameValue.isEmpty()) {

                showValidationAlert(
                        "Owner Name Required",
                        "Please enter the owner's full name.");

                ownerName.requestFocus();

                return;
            }

            // =====================================================
            // MOBILE VALIDATION
            // =====================================================

            if (!mobileValue.matches(
                    "[6-9][0-9]{9}")) {

                showValidationAlert(
                        "Invalid Mobile Number",
                        "Enter a valid 10-digit Indian mobile number.");

                businessMobile.requestFocus();

                return;
            }

            // =====================================================
            // PAN VALIDATION
            // =====================================================

            if (!panValue.matches(
                    "[A-Z]{5}[0-9]{4}[A-Z]")) {

                showValidationAlert(
                        "Invalid PAN",
                        "Enter a valid 10-character PAN, for example ABCDE1234F.");

                panNumber.requestFocus();

                return;
            }

            // =====================================================
            // GST VALIDATION
            // =====================================================

            if (haveGST.isSelected() &&
                    !gstValue.matches(
                            "[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z][1-9A-Z]Z[0-9A-Z]")) {

                showValidationAlert(
                        "Invalid GSTIN",
                        "Enter a valid 15-character GSTIN or select \"I don't have GST\".");

                gstNumber.requestFocus();

                return;
            }

            if (!haveGST.isSelected()) {

                gstValue = "";
            }

            // =====================================================
            // CATEGORY VALIDATION
            // =====================================================

            if (categoryValue == null ||
                    categoryValue.isBlank()) {

                showValidationAlert(
                        "Business Category Required",
                        "Please select your business category.");

                comboBox.requestFocus();

                return;
            }

            // =====================================================
            // ADDRESS VALIDATION
            // =====================================================

            if (addressValue.isEmpty()) {

                showValidationAlert(
                        "Shop Address Required",
                        "Please enter the complete shop address.");

                shopAddress.requestFocus();

                return;
            }
            if (descriptionValue.isEmpty()) {
                showValidationAlert(
                        "Store Description Required",
                        "Please enter the store description.");
                storeDescription.requestFocus();
                return;
            }

            // =====================================================
            // STATE VALIDATION
            // =====================================================

            if (stateValue.isEmpty()) {

                showValidationAlert(
                        "State Required",
                        "Please enter the shop state.");

                state.requestFocus();

                return;
            }

            // =====================================================
            // CITY VALIDATION
            // =====================================================

            if (cityValue.isEmpty()) {

                showValidationAlert(
                        "City Required",
                        "Please enter the shop city.");

                city.requestFocus();

                return;
            }

            // =====================================================
            // PIN VALIDATION
            // =====================================================

            if (!pinCode.getText()
                    .trim()
                    .matches("[1-9][0-9]{5}")) {

                showValidationAlert(
                        "Invalid PIN Code",
                        "Enter a valid 6-digit Indian PIN code.");

                pinCode.requestFocus();

                return;
            }

            // =====================================================
            // SHOP LOGO
            // =====================================================
            if (shopLogoURL == null) {
                System.out.println("Shop logo is required.");
                showValidationAlert("Error",
                        "Shop logo is required. Please upload a valid image.");
                return;
            }
            // =====================================================
            // SHOP PHOTO
            // =====================================================
            if (shopPhotoURL == null) {
                System.out.println("Shop photo is required.");
                showValidationAlert("Error",
                        "Shop photo is required.Please upload a valid image.");
                return;
            }
            // =====================================================
            // LICENSE DOCUMENT
            // =====================================================
            if (licenseDocumentURL == null) {
                System.out.println("License document is required.");
                showValidationAlert("Error",
                        "License document is required.Please upload a valid image.");
                return;
            }
            // =====================================================
            // GST CERTIFICATE
            // =====================================================
            if (gstCertificateURL == null) {
                System.out.println("GST certificate is required.");
                showValidationAlert("Error",
                        "GST certificate is required.Please upload a valid image.");
                return;
            }   

            // =====================================================
            // REGISTER SHOP
            // =====================================================

            ShopkeeperLogController.registerShop(
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
                    licenseValue,
                    uniqueIdValue,
                    ShopkeeperLogController.getShopkeeperUid(),
                    descriptionValue,
                    shopLogoURL,
                    shopPhotoURL,
                    licenseDocumentURL,
                    gstCertificateURL
                );


            System.out.println(
                    "Shop registration data validated.");

            Homepage.HomepageStage.setScene(
<<<<<<< HEAD
                    ShopkeeperRegisterSucess.registeredScene());
=======
                    ShopkeeperRegisterSucess.regaisteredScene());
>>>>>>> Sayali
        });

        // =========================================================
        // BOTTOM BUTTONS
        // =========================================================

        HBox bottomButtons =
                new HBox(
                        backButton,
                        registerButton);

        bottomButtons.setAlignment(
                Pos.CENTER);

        bottomButtons.setSpacing(120);

        // =========================================================
        // LEFT FORM
        // =========================================================

        VBox formBox =
                new VBox(
                        9,

                        shopNameText,
                        shopName,

                        ownerNameText,
                        ownerName,

                        businessMobileText,
                        businessMobile,

                        licenseRow,

                        gstStatusText,
                        gstBox,
                        gstNote,

                        gstNumberText,
                        gstNumber,

                        panText,
                        panNumber,

                        categoryText,
                        comboBox,

                        addressText,
                        shopAddress,

                        descriptionBox,

                        locationBox,

                        mapTitle,
                        mapBox,

                        documentBox,
                        selectedFilesText,

                        bottomButtons);

        formBox.setAlignment(
                Pos.TOP_LEFT);

        formBox.setPadding(
                new Insets(
                        20,
                        30,
                        15,
                        30));

        formBox.setPrefWidth(470);

        formBox.setPrefHeight(620);

        formBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20px 0 0 20px;");

        // =========================================================
        // RIGHT SIDE IMAGE
        // =========================================================

        Image image =
                new Image(
                        "assets\\images\\ShopKeeperLogin.png");

        ImageView imageView =
                new ImageView(image);

        imageView.setFitWidth(230);

        imageView.setFitHeight(230);

        imageView.setPreserveRatio(false);

        Rectangle imageClip =
                new Rectangle(
                        230,
                        230);

        imageClip.setArcWidth(20);

        imageClip.setArcHeight(20);

        imageView.setClip(
                imageClip);

        Text rightText1 =
                new Text(
                        "Empowering Local Business");

        rightText1.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #D86B35;");

        Text rightText2 =
                new Text(
                        "Join 5000+ shops already thriving with\n" +
                        "BuyNeX's hyperlocal delivery network.");

        rightText2.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER);

        rightText2.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #795548;");

        VBox rightSide =
                new VBox(
                        12,
                        imageView,
                        rightText1,
                        rightText2);

        rightSide.setAlignment(
                Pos.CENTER);

        rightSide.setPrefWidth(330);

        rightSide.setPrefHeight(620);

        rightSide.setPadding(
                new Insets(20));

        rightSide.setStyle(
                "-fx-background-color: #FFF0E9;" +
                "-fx-background-radius: 0 20px 20px 0;");

        // =========================================================
        // FORM HBOX
        // =========================================================

        HBox formHBox =
                new HBox(
                        formBox,
                        rightSide);

        formHBox.setPrefWidth(800);

        formHBox.setPrefHeight(620);

        DropShadow shadow =
                new DropShadow();

        shadow.setRadius(18);

        shadow.setSpread(0.05);

        shadow.setColor(
                Color.rgb(
                        80,
                        50,
                        40,
                        0.15));

        formHBox.setEffect(shadow);

        // =========================================================
        // FEATURE 1
        // =========================================================

        Text featureIcon1 =
                new Text("ϟ");

        featureIcon1.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #C95016;");

        Text featureText1 =
                new Text("Instant Setup");

        featureText1.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #252525;");

        Text featureSubText1 =
                new Text(
                        "Go live in under 24 hours.");

        featureSubText1.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #777777;");

        VBox featureTextBox1 =
                new VBox(
                        3,
                        featureText1,
                        featureSubText1);

        HBox featureBox1 =
                new HBox(
                        12,
                        featureIcon1,
                        featureTextBox1);

        featureBox1.setAlignment(
                Pos.CENTER_LEFT);

        featureBox1.setPadding(
                new Insets(10));

        featureBox1.setPrefWidth(150);

        featureBox1.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #F0E4DE;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        // =========================================================
        // FEATURE 2
        // =========================================================

        Text featureIcon2 =
                new Text("▣");

        featureIcon2.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #C95016;");

        Text featureText2 =
                new Text("Daily Payouts");

        featureText2.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #252525;");

        Text featureSubText2 =
                new Text(
                        "Get money in your bank daily.");

        featureSubText2.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #777777;");

        VBox featureTextBox2 =
                new VBox(
                        3,
                        featureText2,
                        featureSubText2);

        HBox featureBox2 =
                new HBox(
                        12,
                        featureIcon2,
                        featureTextBox2);

        featureBox2.setAlignment(
                Pos.CENTER_LEFT);

        featureBox2.setPadding(
                new Insets(10));

        featureBox2.setPrefWidth(150);

        featureBox2.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #F0E4DE;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        // =========================================================
        // FEATURE 3
        // =========================================================

        Text featureIcon3 =
                new Text("⌁");

        featureIcon3.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-fill: #C95016;");

        Text featureText3 =
                new Text("AI Insights");

        featureText3.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #252525;");

        Text featureSubText3 =
                new Text(
                        "Know what's trending nearby.");

        featureSubText3.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #777777;");

        VBox featureTextBox3 =
                new VBox(
                        3,
                        featureText3,
                        featureSubText3);

        HBox featureBox3 =
                new HBox(
                        12,
                        featureIcon3,
                        featureTextBox3);

        featureBox3.setAlignment(
                Pos.CENTER_LEFT);

        featureBox3.setPadding(
                new Insets(10));

        featureBox3.setPrefWidth(150);

        featureBox3.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #F0E4DE;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        HBox featuresBox =
                new HBox(
                        25,
                        featureBox1,
                        featureBox2,
                        featureBox3);

        featuresBox.setAlignment(
                Pos.CENTER);

        // =========================================================
        // CENTER CONTENT
        // =========================================================

        VBox centerContent =
                new VBox(
                        16,
                        pageIntroBox,
                        formHBox,
                        featuresBox);

        centerContent.setAlignment(
                Pos.TOP_CENTER);

        centerContent.setPadding(
                new Insets(
                        30,
                        0,
                        30,
                        0));

        centerContent.setStyle(
<<<<<<< HEAD
                "-fx-background-color: #F8FBF8;");
=======
                "-fx-background-color: #EEE5DF;");
>>>>>>> Sayali

        centerContent.setMaxWidth(800);

        VBox page =
                new VBox(centerContent);

        page.setAlignment(
                Pos.TOP_CENTER);

        page.setPrefWidth(1280);

        page.setStyle(
<<<<<<< HEAD
                "-fx-background-color: #F8FBF8;");
=======
                "-fx-background-color: #EEE5DF;");
>>>>>>> Sayali

        // =========================================================
        // SCROLL PANE
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(page);

        scrollPane.setFitToWidth(true);

        scrollPane.setFitToHeight(false);

        borderPane.setCenter(
                scrollPane);

        // =========================================================
        // FOOTER
        // =========================================================

        VBox footerBox =
                ViewConstants.loginFooter();

        borderPane.setBottom(
                footerBox);

        // =========================================================
        // SCENE
        // =========================================================

        shopDetailsScene =
                new Scene(
                        borderPane,
                        1280,
                        720);

        shopDetailsScene.setFill(
<<<<<<< HEAD
                Color.web("#F8FBF8"));
=======
                Color.web("#EEE5DF"));
>>>>>>> Sayali

        return shopDetailsScene;
    }

    // =============================================================
    // VALIDATION ALERT
    // =============================================================

    public static void showValidationAlert(
            String title,
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.WARNING);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}
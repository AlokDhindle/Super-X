package com.kryox.view.Delivery;

import com.buynex.config.FirebaseConfig;
import com.kryox.controller.Delivery.ImageUploadController;
import com.kryox.model.Delivery.PartnerConstants;
import com.google.cloud.firestore.Firestore;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PartnerProfile {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    public static void show(Stage primaryStage) {
        show(primaryStage, new PartnerSettings.SettingsData());
    }

    public static void show(Stage primaryStage, PartnerSettings.SettingsData settingsData) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Header with Back to Settings Button
        root.setTop(createTopHeader(primaryStage, settingsData));

        // 2. Center Content inside ScrollPane
        VBox mainContent = createMainContent(primaryStage, settingsData);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }

        primaryStage.setTitle("BuyNeX - Partner Full Profile");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private static BorderPane createTopHeader(Stage primaryStage, PartnerSettings.SettingsData settingsData) {
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

        Button btnBack = new Button("←   Back to Settings");
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
        btnBack.setOnAction(e -> PartnerSettings.show(primaryStage, settingsData));

        Text title = new Text("Partner Account Profile");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        return topBar;
    }

    private static VBox createMainContent(Stage primaryStage, PartnerSettings.SettingsData settingsData) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(26, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox wrapper = new VBox(20);
        wrapper.setMaxWidth(820);

        // 1. Hero Identity Banner Card
        BorderPane heroCard = new BorderPane();
        heroCard.setPadding(new Insets(22, 26, 22, 26));
        heroCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);"
        );

        HBox identityLeft = new HBox(16);
        identityLeft.setAlignment(Pos.CENTER_LEFT);

        // --- Circular Avatar & Image Loader ---
        StackPane bigAvatar = createProfileAvatarNode(38);

        Button btnUploadPhoto = new Button("📷 Change Photo");
        btnUploadPhoto.setStyle(
                "-fx-background-color: #f3f4f6;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-text-fill: #374151;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 3 8 3 8;"
        );

        btnUploadPhoto.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                btnUploadPhoto.setText("Uploading...");
                btnUploadPhoto.setDisable(true);

                new Thread(() -> {
                    try {
                        ImageUploadController controller = new ImageUploadController();
                        String uploadedUrl = controller.imageUpload(selectedFile);

                        if (uploadedUrl != null && !uploadedUrl.isEmpty()) {
                            // Update Cloud Firestore
                            if (PartnerConstants.UID != null && !PartnerConstants.UID.isEmpty()) {
                                Firestore db = FirebaseConfig.getFireStore();
                                Map<String, Object> photoUpdate = new HashMap<>();
                                photoUpdate.put("profilePhotoUrl", uploadedUrl);
                                photoUpdate.put("profilePhotoPath", uploadedUrl);

                                db.collection("delivery_partners")
                                        .document(PartnerConstants.UID)
                                        .update(photoUpdate)
                                        .get();
                            }

                            // Update in-memory session constants
                            PartnerConstants.PROFILE_PHOTO_URL = uploadedUrl;

                            Platform.runLater(() -> {
                                btnUploadPhoto.setText("📷 Change Photo");
                                btnUploadPhoto.setDisable(false);
                                // Reload scene to immediately display the updated Cloudinary photo
                                PartnerProfile.show(primaryStage, settingsData);
                            });
                        } else {
                            Platform.runLater(() -> {
                                btnUploadPhoto.setText("Upload Failed");
                                btnUploadPhoto.setStyle("-fx-background-color: #fee2e2; -fx-text-fill: #b91c1c; -fx-font-weight: bold; -fx-font-size: 10px; -fx-background-radius: 6;");
                                btnUploadPhoto.setDisable(false);
                            });
                        }
                    } catch (Exception ex) {
                        Platform.runLater(() -> {
                            btnUploadPhoto.setText("Error! Retry");
                            btnUploadPhoto.setStyle("-fx-background-color: #fee2e2; -fx-text-fill: #b91c1c; -fx-font-weight: bold; -fx-font-size: 10px; -fx-background-radius: 6;");
                            btnUploadPhoto.setDisable(false);
                        });
                        ex.printStackTrace();
                    }
                }).start();
            }
        });

        VBox nameBox = new VBox(4);
        nameBox.setAlignment(Pos.CENTER_LEFT);
        Label nameLbl = new Label(PartnerConstants.FULL_NAME);
        nameLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        HBox badgesRow = new HBox(8);
        badgesRow.setAlignment(Pos.CENTER_LEFT);
        Label tierBadge = new Label("⭐ " + PartnerConstants.PARTNER_TIER);
        tierBadge.setStyle("-fx-background-color: #fef3c7; -fx-text-fill: #b45309; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 3 8 3 8;");

        String partnerUidDisplay = (PartnerConstants.UID == null || PartnerConstants.UID.isEmpty())
                ? "49201"
                : PartnerConstants.UID.substring(0, Math.min(6, PartnerConstants.UID.length())).toUpperCase();
        Label idBadge = new Label("ID: #NEX-P-" + partnerUidDisplay);
        idBadge.setStyle("-fx-background-color: #f3f4f6; -fx-text-fill: #4b5563; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 3 8 3 8;");
        badgesRow.getChildren().addAll(tierBadge, idBadge, btnUploadPhoto);

        nameBox.getChildren().addAll(nameLbl, badgesRow);
        identityLeft.getChildren().addAll(bigAvatar, nameBox);
        heroCard.setLeft(identityLeft);

        Label joinDateLbl = new Label("Active on BuyNeX Network\n" + (PartnerConstants.CITY == null || PartnerConstants.CITY.isEmpty() ? "Pune" : PartnerConstants.CITY));
        joinDateLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280; -fx-text-alignment: right;");
        heroCard.setRight(joinDateLbl);
        BorderPane.setAlignment(joinDateLbl, Pos.CENTER_RIGHT);

        // 2. Lifetime Delivery Performance KPIs
        HBox kpiRow = new HBox(14);
        kpiRow.getChildren().addAll(
                createKpiCard("Total Trips Completed", String.format("%,d", settingsData.totalSuccessfulDeliveries), "📦"),
                createKpiCard("Customer Rating", String.format("%.1f ★", settingsData.ratingScore), "⭐"),
                createKpiCard("Fulfillment Rate", String.format("%.1f%%", settingsData.completionRate), "🎯"),
                createKpiCard("Lifetime Earnings", "₹1,48,920", "💵")
        );
        kpiRow.getChildren().forEach(n -> HBox.setHgrow(n, Priority.ALWAYS));

        // 3. Detailed Profile & Contact Form Card
        VBox formCard = new VBox(16);
        formCard.setPadding(new Insets(24));
        formCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );

        Label formTitle = new Label("Personal & Contact Details");
        formTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        GridPane grid = new GridPane();
        grid.setHgap(16);
        grid.setVgap(14);

        TextField nameField = createStyledTextField("Full Name");
        nameField.setText(PartnerConstants.FULL_NAME);

        TextField emailField = createStyledTextField("Email Address");
        emailField.setText(PartnerConstants.EMAIL);
        emailField.setDisable(true);

        TextField phoneField = createStyledTextField("Phone Number");
        phoneField.setText(PartnerConstants.PHONE);

        TextField vehicleField = createStyledTextField("Vehicle Model");
        vehicleField.setText(PartnerConstants.VEHICLE_TYPE);

        TextField vehicleNumberField = createStyledTextField("Vehicle Reg No.");
        vehicleNumberField.setText(PartnerConstants.VEHICLE_NUMBER);

        TextField cityField = createStyledTextField("Operating City");
        cityField.setText(PartnerConstants.CITY == null || PartnerConstants.CITY.isEmpty() ? "Pune" : PartnerConstants.CITY);

        TextField addressField = createStyledTextField("Residential Address");
        addressField.setText((PartnerConstants.ADDRESS != null && !PartnerConstants.ADDRESS.trim().isEmpty())
                ? PartnerConstants.ADDRESS
                : "Flat / House No, Street, Pune, Maharashtra");

        grid.add(createFieldBox("Full Legal Name", nameField), 0, 0);
        grid.add(createFieldBox("Registered Email (Fixed)", emailField), 1, 0);
        grid.add(createFieldBox("Mobile Phone", phoneField), 0, 1);
        grid.add(createFieldBox("Operating City", cityField), 1, 1);
        grid.add(createFieldBox("Vehicle Model", vehicleField), 0, 2);
        grid.add(createFieldBox("Vehicle Number", vehicleNumberField), 1, 2);
        grid.add(createFieldBox("Current Residential Address", addressField), 0, 3, 2, 1);

        grid.getChildren().forEach(n -> GridPane.setHgrow(n, Priority.ALWAYS));

        Button btnSaveProfile = new Button("Save Profile Changes   →");
        btnSaveProfile.setPrefHeight(42);
        btnSaveProfile.setMaxWidth(Double.MAX_VALUE);
        btnSaveProfile.setStyle(
                "-fx-background-color: " + ORANGE_GRADIENT + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        btnSaveProfile.setOnAction(e -> {
            String newName = nameField.getText().trim();
            String newPhone = phoneField.getText().trim();
            String newVehicle = vehicleField.getText().trim();
            String newVehicleNum = vehicleNumberField.getText().trim();
            String newCity = cityField.getText().trim();
            String newAddress = addressField.getText().trim();

            Map<String, Object> updates = new HashMap<>();
            if (!newName.isEmpty()) updates.put("fullName", newName);
            if (!newPhone.isEmpty()) updates.put("mobile", newPhone);
            if (!newVehicle.isEmpty()) updates.put("vehicleType", newVehicle);
            if (!newVehicleNum.isEmpty()) updates.put("vehicleNumber", newVehicleNum);
            if (!newCity.isEmpty()) updates.put("city", newCity);
            if (!newAddress.isEmpty()) updates.put("address", newAddress);

            btnSaveProfile.setText("Saving to Firestore...");
            btnSaveProfile.setDisable(true);

            new Thread(() -> {
                try {
                    Firestore db = FirebaseConfig.getFireStore();

                    if (PartnerConstants.UID != null && !PartnerConstants.UID.isEmpty()) {
                        db.collection("delivery_partners")
                                .document(PartnerConstants.UID)
                                .update(updates)
                                .get();
                    }

                    if (!newName.isEmpty()) PartnerConstants.FULL_NAME = newName;
                    if (!newPhone.isEmpty()) PartnerConstants.PHONE = newPhone;
                    if (!newVehicle.isEmpty()) PartnerConstants.VEHICLE_TYPE = newVehicle;
                    if (!newVehicleNum.isEmpty()) PartnerConstants.VEHICLE_NUMBER = newVehicleNum;
                    if (!newCity.isEmpty()) PartnerConstants.CITY = newCity;
                    if (!newAddress.isEmpty()) PartnerConstants.ADDRESS = newAddress;

                    Platform.runLater(() -> {
                        settingsData.fullName = PartnerConstants.FULL_NAME;
                        settingsData.partnerName = PartnerConstants.FULL_NAME;
                        settingsData.phone = PartnerConstants.PHONE;
                        settingsData.vehicleType = PartnerConstants.VEHICLE_TYPE;
                        settingsData.vehicleIdNumber = PartnerConstants.VEHICLE_NUMBER;

                        nameLbl.setText(PartnerConstants.FULL_NAME);
                        btnSaveProfile.setText("✓ Profile Saved Permanently!");
                        btnSaveProfile.setStyle("-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 8;");
                        btnSaveProfile.setDisable(false);
                    });
                } catch (Exception ex) {
                    Platform.runLater(() -> {
                        btnSaveProfile.setText("Save Failed! Try Again");
                        btnSaveProfile.setStyle("-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 8;");
                        btnSaveProfile.setDisable(false);
                    });
                    ex.printStackTrace();
                }
            }).start();
        });

        formCard.getChildren().addAll(formTitle, grid, btnSaveProfile);

        wrapper.getChildren().addAll(heroCard, kpiRow, formCard);
        content.getChildren().add(wrapper);
        return content;
    }

    private static StackPane createProfileAvatarNode(double radius) {
        StackPane avatarPane = new StackPane();
        avatarPane.setPrefSize(radius * 2, radius * 2);
        avatarPane.setMaxSize(radius * 2, radius * 2);

        Circle bg = new Circle(radius, Color.web("#fed7aa"));

        if (PartnerConstants.PROFILE_PHOTO_URL != null && !PartnerConstants.PROFILE_PHOTO_URL.trim().isEmpty()) {
            try {
                ImageView imgView = new ImageView(new Image(PartnerConstants.PROFILE_PHOTO_URL, true));
                imgView.setFitWidth(radius * 2);
                imgView.setFitHeight(radius * 2);
                imgView.setPreserveRatio(false);

                Circle clip = new Circle(radius, radius, radius);
                imgView.setClip(clip);

                avatarPane.getChildren().addAll(bg, imgView);
                return avatarPane;
            } catch (Exception ignored) {}
        }

        String initials = "P";
        if (PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.trim().isEmpty()) {
            String[] parts = PartnerConstants.FULL_NAME.trim().split("\\s+");
            if (parts.length >= 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                initials = ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
            } else if (!parts[0].isEmpty()) {
                initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
            }
        }

        Label initialLabel = new Label(initials);
        initialLabel.setStyle("-fx-font-size: " + (radius * 0.7) + "px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        avatarPane.getChildren().addAll(bg, initialLabel);

        return avatarPane;
    }

    private static VBox createKpiCard(String title, String val, String icon) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(14, 16, 14, 16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );

        BorderPane top = new BorderPane();
        Label t = new Label(title);
        t.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280; -fx-font-weight: bold;");
        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 12px;");
        top.setLeft(t);
        top.setRight(i);

        Label v = new Label(val);
        v.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        card.getChildren().addAll(top, v);
        return card;
    }

    private static VBox createFieldBox(String label, TextField field) {
        VBox box = new VBox(4);
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #4b5563;");
        box.getChildren().addAll(l, field);
        return box;
    }

    private static TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setPrefHeight(38);
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
}
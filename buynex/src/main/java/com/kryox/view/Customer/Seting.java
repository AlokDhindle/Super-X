package com.kryox.view.Customer;

import java.io.File;
import java.time.LocalDate;

import com.kryox.controller.Customer.Clodnarycontroller;
import com.kryox.controller.Customer.Userstorecontroller;
import com.kryox.model.Customer.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;


public class Seting {
    public String userId;

    public Seting(String userId) {
        this.userId = userId;
    }

    private Scene setingSceene;
    private final Userstorecontroller userController = new Userstorecontroller();

    Scene getSetingscene(Runnable callbacktoDashboard) {
        Image defaultImage = new Image("https://cdn-icons-png.flaticon.com/512/149/149071.png");
        ImageView profileImage = new ImageView(defaultImage);
        profileImage.setFitWidth(45);
        profileImage.setFitHeight(45);
        profileImage.setPreserveRatio(true);

        Circle clip = new Circle(22.5, 22.5, 22.5);
        profileImage.setClip(clip);

        profileImage.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Picture");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showOpenDialog(Homepage.HomepageStage);
            if (file != null) {
                Image newImage = new Image(file.toURI().toString());
                profileImage.setImage(newImage);
                System.out.println("Selected File: " + file.getAbsolutePath());

                String url = Clodnarycontroller.imageUpload(file);
                System.out.println("Cloudinary URL: " + url);
                profileImage.setCursor(Cursor.HAND);
            }
        });
        profileImage.setCursor(Cursor.HAND);

        Label name = new Label("Loading...");
        name.setStyle(
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #a83f00;"
        );

        String currentPlan = CustomerManagePlan.CustomerPlanState.getCurrentPlan(userId);
        String planBadge = "Gold".equalsIgnoreCase(currentPlan) ? "👑 Gold Member" : ("Platinum".equalsIgnoreCase(currentPlan) ? "💎 Platinum VIP" : "Standard Member");
        Label member = new Label(planBadge);
        member.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: " + ("Free".equalsIgnoreCase(currentPlan) ? "#555555;" : "#a83f00;")
        );

        VBox userInfo = new VBox(2, name, member);
        userInfo.setAlignment(Pos.CENTER_LEFT);

        HBox profileBox = new HBox(12, profileImage, userInfo);
        profileBox.setPrefWidth(250);
        profileBox.setPrefHeight(80);
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(new Insets(10, 15, 10, 25));
        profileBox.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #eeeeee;" +
            "-fx-border-width: 0 0 1 0;"
        );

        Button profileBtn = new Button("♙    Profile");
        profileBtn.setPrefSize(200, 36);
        profileBtn.setStyle(
            "-fx-background-color: #fff0e6;" +
            "-fx-text-fill: #a83f00;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-border-color: #b84d00;" +
            "-fx-border-width: 0 3 0 0;" +
            "-fx-cursor: hand;"
        );

        Button privacyBtn = new Button("♙    Privacy");
        privacyBtn.setPrefSize(200, 36);
        privacyBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: normal;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button notificationBtn = new Button("♧    Notifications");
        notificationBtn.setPrefSize(200, 36);
        notificationBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        notificationBtn.setOnAction(e -> CustomerNavigation.navigateToNotifications(userId));

        Button addressBtn = new Button("⌖    Address");
        addressBtn.setPrefSize(200, 36);
        addressBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button paymentBtn = new Button("▣    Payment");
        paymentBtn.setPrefSize(200, 36);
        paymentBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button ordersBtn = new Button("♧    Orders");
        ordersBtn.setPrefSize(200, 36);
        ordersBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        ordersBtn.setOnAction(e -> CustomerNavigation.navigateToOrders(userId));

        Button deliveryBtn = new Button("▱    Delivery");
        deliveryBtn.setPrefSize(200, 36);
        deliveryBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button securityBtn = new Button("♢    Login & Security");
        securityBtn.setPrefSize(200, 36);
        securityBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button helpBtn = new Button("?    Help");
        helpBtn.setPrefSize(200, 36);
        helpBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        helpBtn.setOnAction(event -> {
            CustomerNavigation.navigateToHelp(userId);
        });

        Button termsBtn = new Button("▤    Terms");
        termsBtn.setPrefSize(200, 36);
        termsBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        Button aboutBtn = new Button("ℹ    About Us");
        aboutBtn.setPrefSize(200, 36);
        aboutBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #a83f00;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        aboutBtn.setOnAction(event -> {
            AboutUsPage about = new AboutUsPage(userId);
            Homepage.HomepageStage.setScene(about.getAboutUsScene(() -> backToseting()));
        });

        Button logoutBtn = new Button("🚪    Log Out");
        logoutBtn.setPrefSize(200, 36);
        logoutBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #b3261e;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );

        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle(
            "-fx-background-color: #fee2e2;" +
            "-fx-text-fill: #991b1b;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        ));

        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #b3261e;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        ));

        logoutBtn.setOnAction(event -> {
            CustomerNavigation.navigateToLogin();
        });

        Button back = new Button("←    Back to Dashboard");
        back.setPrefSize(200, 36);
        back.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #7b1212;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        back.setOnAction(even -> {
            if (callbacktoDashboard != null) {
                callbacktoDashboard.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
        });

        VBox menuBox = new VBox(5);
        menuBox.setPadding(new Insets(30, 15, 10, 15));
        menuBox.getChildren().addAll(
            profileBtn,
            privacyBtn,
            notificationBtn,
            addressBtn,
            paymentBtn,
            ordersBtn,
            deliveryBtn,
            securityBtn,
            helpBtn,
            termsBtn,
            aboutBtn,
            logoutBtn,
            back
        );
        menuBox.setStyle("-fx-background-color: #ebccb7;");

        String upBtnText = "Gold".equalsIgnoreCase(currentPlan) ? "👑 Upgrade to Platinum" : ("Platinum".equalsIgnoreCase(currentPlan) ? "💎 Manage VIP Membership" : "Upgrade to Gold / Pro");
        Button upgradeBtn = new Button(upBtnText);
        upgradeBtn.setPrefWidth(200);
        upgradeBtn.setMinWidth(200);
        upgradeBtn.setMaxWidth(200);
        upgradeBtn.setPrefHeight(30);
        upgradeBtn.setStyle(
            "-fx-background-color: #f5f1f3;" +
            "-fx-text-fill: #a83f00;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #e5caca;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;"
        );
        upgradeBtn.setOnAction(e -> CustomerNavigation.navigateToPlans(userId));

        HBox upgradeBox = new HBox(upgradeBtn);
        upgradeBox.setAlignment(Pos.CENTER);
        upgradeBox.setPadding(new Insets(10, 15, 15, 15));

        String selectedButtonStyle = "-fx-background-color: #fff0e6;" +
            "-fx-text-fill: #a83f00;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-border-radius: 6;" +
            "-fx-border-color: #b84d00;" +
            "-fx-border-width: 0 3 0 0;" +
            "-fx-cursor: hand;";

        String normalButtonStyle = "-fx-background-color: transparent;" +
            "-fx-text-fill: #333333;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: normal;" +
            "-fx-alignment: CENTER_LEFT;" +
            "-fx-padding: 0 0 0 14;" +
            "-fx-background-radius: 6;" +
            "-fx-cursor: hand;";

        Button[] menuButtons = {
            profileBtn,
            privacyBtn,
            notificationBtn,
            addressBtn,
            paymentBtn,
            ordersBtn,
            deliveryBtn,
            securityBtn,
            helpBtn,
            termsBtn
        };

        VBox leftVBox = new VBox(profileBox, menuBox, upgradeBox);
        leftVBox.setStyle("-fx-background-color: #ffffff;");

        DropShadow shadow = new DropShadow();
        shadow.setRadius(12);
        shadow.setSpread(0.10);
        shadow.setOffsetX(6);
        shadow.setOffsetY(0);
        shadow.setColor(Color.rgb(0, 0, 0, 0.25));
        leftVBox.setEffect(shadow);

        Label title = new Label("Profile & Account");
        title.setStyle(
            "-fx-font-size: 24px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #111111;"
        );

        Label subtitle = new Label("Manage your personal information and security preferences.");
        subtitle.setStyle(
            "-fx-font-size: 13px;" +
            "-fx-text-fill: #666666;"
        );

        HBox profileCard = new HBox();
        profileCard.setPrefWidth(600);
        profileCard.setMinWidth(600);
        profileCard.setMaxWidth(600);
        profileCard.setPrefHeight(130);
        profileCard.setMinHeight(130);
        profileCard.setMaxHeight(130);
        profileCard.setAlignment(Pos.CENTER_LEFT);
        profileCard.setPadding(new Insets(15, 25, 15, 20));
        profileCard.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-border-color: #eeeeee;" +
            "-fx-border-width: 1;"
        );

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(12);
        cardShadow.setSpread(0.03);
        cardShadow.setOffsetX(0);
        cardShadow.setOffsetY(4);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.10));
        profileCard.setEffect(cardShadow);

        Image profileImg = new Image("file:C:/Users/YourName/Pictures/profile.jpg");
        ImageView profileImagevView = new ImageView(profileImg);
        profileImagevView.setFitWidth(88);
        profileImagevView.setFitHeight(88);
        profileImagevView.setPreserveRatio(true);

        Circle imageClip = new Circle(44, 44, 44);
        profileImagevView.setClip(imageClip);

        Button editButton = new Button("✎");
        editButton.setPrefWidth(28);
        editButton.setPrefHeight(28);
        editButton.setMinWidth(28);
        editButton.setMinHeight(28);
        editButton.setMaxWidth(28);
        editButton.setMaxHeight(28);
        editButton.setAlignment(Pos.CENTER);
        editButton.setStyle(
            "-fx-background-color: #ff7100;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 50%;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 0;" +
            "-fx-cursor: hand;"
        );

        StackPane imageBox = new StackPane();
        imageBox.setPrefWidth(90);
        imageBox.setPrefHeight(90);
        imageBox.setMinWidth(90);
        imageBox.setMinHeight(90);
        imageBox.setMaxWidth(90);
        imageBox.setMaxHeight(90);
        imageBox.getChildren().addAll(profileImage, editButton);

        StackPane.setAlignment(editButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(editButton, new Insets(0, -2, -2, 0));

        Label nameLabel = new Label("Loading...");
        nameLabel.setStyle(
            "-fx-text-fill: #111111;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Label premiumLabel = new Label("♙ Premium");
        premiumLabel.setStyle(
            "-fx-background-color: #fff0e6;" +
            "-fx-text-fill: #c85c13;" +
            "-fx-background-radius: 12;" +
            "-fx-padding: 4px 8px;" +
            "-fx-font-size: 10px;" +
            "-fx-font-weight: bold;"
        );

        HBox nameRow = new HBox(7);
        nameRow.setAlignment(Pos.CENTER_LEFT);
        nameRow.getChildren().addAll(nameLabel, premiumLabel);

        Label emailLabel = new Label("Loading...");
        emailLabel.setStyle(
            "-fx-text-fill: #666666;" +
            "-fx-font-size: 12px;"
        );

        VBox detailsBox = new VBox(6);
        detailsBox.setAlignment(Pos.CENTER_LEFT);
        detailsBox.getChildren().addAll(nameRow, emailLabel);

        profileCard.getChildren().addAll(imageBox, detailsBox);

        VBox personalInfo = new VBox(18);
        personalInfo.setPrefWidth(600);
        personalInfo.setMinWidth(600);
        personalInfo.setMaxWidth(600);
        personalInfo.setPadding(new Insets(18));
        personalInfo.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #ff8a45;" +
            "-fx-border-width: 1;"
        );

        TextField nameField = new TextField();
        nameField.setPrefSize(194, 37);

        VBox nameBox = new VBox(6, new Label("Full Name"), nameField);

        TextField emailField = new TextField();
        emailField.setPrefSize(194, 37);

        VBox emailBox = new VBox(6, new Label("Email Address"), emailField);

        HBox nameAndEmail = new HBox(50, nameBox, emailBox);

        TextField phoneField = new TextField();
        phoneField.setPrefSize(194, 37);

        VBox phoneBox = new VBox(6, new Label("Phone Number"), phoneField);

        DatePicker dob = new DatePicker(LocalDate.of(1990, 10, 24));
        dob.setPrefSize(194, 37);

        VBox dobBox = new VBox(6, new Label("Date of Birth"), dob);

        HBox phoneanddob = new HBox(50, phoneBox, dobBox);

        Button saveButton = new Button("Save Changes");
        saveButton.setPrefWidth(150);
        saveButton.setPrefHeight(38);
        saveButton.setStyle(
            "-fx-background-color: #ff7100;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-cursor: hand;"
        );

        saveButton.setOnAction(event -> {
            String oldEmail = userId == null ? "" : userId.trim();
            String updatedName = nameField.getText().trim();
            String updatedEmail = emailField.getText().trim();
            String updatedMobile = phoneField.getText().trim();

            if (oldEmail.isEmpty()) {
                System.out.println("User email not found.");
                return;
            }

            if (updatedName.isEmpty() || updatedEmail.isEmpty() || updatedMobile.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }

            saveButton.setDisable(true);
            saveButton.setText("Saving...");

            String oldUserEmail = oldEmail;

            new Thread(() -> {
                boolean updated = userController.updateUser(
                    oldUserEmail,
                    updatedName,
                    updatedEmail,
                    updatedMobile
                );

                Platform.runLater(() -> {
                    saveButton.setDisable(false);
                    saveButton.setText("Save Changes");

                    if (updated) {
                        userId = updatedEmail;
                        nameLabel.setText(updatedName);
                        emailLabel.setText(updatedEmail);
                        name.setText(updatedName);
                        System.out.println("Profile updated successfully.");
                    } else {
                        System.out.println("Profile update failed.");
                    }
                });
            }).start();
        });

        personalInfo.getChildren().addAll(nameAndEmail, phoneanddob, saveButton);

        HBox accountSecurity = new HBox(50);
        accountSecurity.setPrefWidth(600);
        accountSecurity.setMinWidth(600);
        accountSecurity.setMaxWidth(600);
        accountSecurity.setPrefHeight(75);
        accountSecurity.setPadding(new Insets(12, 18, 12, 18));
        accountSecurity.setAlignment(Pos.CENTER_LEFT);
        accountSecurity.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;"
        );

        Label lockIcon = new Label("•••");
        lockIcon.setPrefSize(32, 32);
        lockIcon.setAlignment(Pos.CENTER);
        lockIcon.setStyle(
            "-fx-background-color: #e8e5eb;" +
            "-fx-background-radius: 50%;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );

        Label changePassword = new Label("Change Password");
        changePassword.setStyle(
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;"
        );

        Label description = new Label("Update your password to keep your account secure");
        description.setStyle(
            "-fx-font-size: 10px;" +
            "-fx-text-fill: #777777;"
        );

        VBox passwordText = new VBox(3, changePassword, description);

        Label arrow = new Label("›");
        arrow.setStyle("-fx-font-size: 22px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        accountSecurity.getChildren().addAll(lockIcon, passwordText, spacer, arrow);
        accountSecurity.setCursor(Cursor.HAND);
        accountSecurity.setOnMouseClicked(e -> securityBtn.fire());

        loadUserData(
            name,
            nameLabel,
            emailLabel,
            nameField,
            emailField,
            phoneField,
            member,
            saveButton
        );

        HBox logoutCard = new HBox(12);
        logoutCard.setPrefWidth(600);
        logoutCard.setMinWidth(600);
        logoutCard.setMaxWidth(600);
        logoutCard.setPrefHeight(64);
        logoutCard.setMinHeight(64);
        logoutCard.setAlignment(Pos.CENTER_LEFT);
        logoutCard.setPadding(new Insets(12, 20, 12, 20));
        logoutCard.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;" +
            "-fx-border-color: #fee2e2;" +
            "-fx-border-width: 1.2;"
        );

        DropShadow logoutCardShadow = new DropShadow();
        logoutCardShadow.setRadius(10);
        logoutCardShadow.setOffsetY(3);
        logoutCardShadow.setColor(Color.rgb(179, 38, 30, 0.08));
        logoutCard.setEffect(logoutCardShadow);

        Label logoutCardIcon = new Label("🚪");
        logoutCardIcon.setPrefSize(36, 36);
        logoutCardIcon.setMinSize(36, 36);
        logoutCardIcon.setAlignment(Pos.CENTER);
        logoutCardIcon.setStyle(
            "-fx-background-color: #fef2f2;" +
            "-fx-background-radius: 50%;" +
            "-fx-font-size: 16px;"
        );

        VBox logoutCardTextBox = new VBox(2);
        Label logoutCardTitle = new Label("Log Out of Account");
        logoutCardTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #b3261e;");
        Label logoutCardSub = new Label("Sign out of your account and jump directly to customer login.");
        logoutCardSub.setStyle("-fx-font-size: 10px; -fx-text-fill: #777777;");
        logoutCardTextBox.getChildren().addAll(logoutCardTitle, logoutCardSub);

        Region logoutCardSpacer = new Region();
        HBox.setHgrow(logoutCardSpacer, Priority.ALWAYS);

        Button logoutCardBtn = new Button("Log Out");
        logoutCardBtn.setPrefHeight(34);
        logoutCardBtn.setPrefWidth(100);
        logoutCardBtn.setStyle(
            "-fx-background-color: #b3261e;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 18;" +
            "-fx-cursor: hand;"
        );

        logoutCardBtn.setOnMouseEntered(e -> logoutCardBtn.setStyle(
            "-fx-background-color: #991b1b;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 18;" +
            "-fx-cursor: hand;"
        ));

        logoutCardBtn.setOnMouseExited(e -> logoutCardBtn.setStyle(
            "-fx-background-color: #b3261e;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 11px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 18;" +
            "-fx-cursor: hand;"
        ));

        logoutCardBtn.setOnAction(e -> {
            CustomerNavigation.navigateToLogin();
        });

        logoutCard.getChildren().addAll(logoutCardIcon, logoutCardTextBox, logoutCardSpacer, logoutCardBtn);

        VBox contentBox = new VBox(20, title, subtitle, profileCard, personalInfo, accountSecurity, logoutCard);
        contentBox.setPrefWidth(1250);
        contentBox.setPrefHeight(850);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setPadding(new Insets(25, 40, 25, 40));
        contentBox.setStyle("-fx-background-color: #eee5df;");

        Runnable resetStyles = () -> {
            for (Button b : menuButtons) {
                b.setStyle(normalButtonStyle);
            }
        };

        profileBtn.setOnAction(e -> {
            resetStyles.run();
            profileBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(title, subtitle, profileCard, personalInfo, accountSecurity, logoutCard);
        });

        privacyBtn.setOnAction(e -> {
            resetStyles.run();
            privacyBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Privacy Settings", "Manage your data privacy and visibility preferences."), createPrivacyView());
        });

        notificationBtn.setOnAction(e -> {
            resetStyles.run();
            notificationBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Notification Preferences", "Control how and when you receive alerts from BuyNeX."), createNotificationView());
        });

        addressBtn.setOnAction(e -> {
            resetStyles.run();
            addressBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Saved Addresses", "Manage your delivery addresses and shipping locations."), createAddressView());
        });

        paymentBtn.setOnAction(e -> {
            resetStyles.run();
            paymentBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Payment Methods", "Manage your credit cards, UPI IDs, and billing options."), createPaymentView());
        });

        ordersBtn.setOnAction(e -> {
            resetStyles.run();
            ordersBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Order Preferences", "Set default order behavior and checkout settings."), createOrdersView());
        });

        deliveryBtn.setOnAction(e -> {
            resetStyles.run();
            deliveryBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Delivery Preferences", "Customize your delivery instructions and preferred time slots."), createDeliveryView());
        });

        securityBtn.setOnAction(e -> {
            resetStyles.run();
            securityBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Login & Security", "Manage your password, 2-factor authentication, and account security."), createSecurityView());
        });

        helpBtn.setOnAction(e -> {
            Helppage helppage = new Helppage(userId);
            Homepage.HomepageStage.setScene(helppage.getHelpScene(() -> backToseting()));
        });

        termsBtn.setOnAction(e -> {
            resetStyles.run();
            termsBtn.setStyle(selectedButtonStyle);
            contentBox.getChildren().setAll(createTabHeader("Terms & Policies", "Review BuyNeX Terms of Service and Privacy Policy."), createTermsView());
        });

        VBox verifiedBox = new VBox(10);
        verifiedBox.setPrefWidth(700);
        verifiedBox.setPrefHeight(850);
        verifiedBox.setAlignment(Pos.CENTER);
        verifiedBox.setPadding(new Insets(18, 15, 15, 15));
        verifiedBox.setStyle(
            "-fx-background-color: #eee5df" +
            "-fx-background-radius: 14;" +
            "-fx-border-radius: 14;"
        );

        DropShadow shadow2 = new DropShadow();
        shadow2.setRadius(12);
        shadow2.setSpread(0.03);
        shadow2.setOffsetX(0);
        shadow2.setOffsetY(3);
        shadow2.setColor(Color.rgb(0, 0, 0, 0.10));
        verifiedBox.setEffect(shadow2);

        Label shieldIcon = new Label("♢");
        shieldIcon.setPrefSize(48, 48);
        shieldIcon.setMinSize(48, 48);
        shieldIcon.setMaxSize(48, 48);
        shieldIcon.setAlignment(Pos.CENTER);
        shieldIcon.setStyle(
            "-fx-background-color: #fff0e6;" +
            "-fx-background-radius: 50%;" +
            "-fx-text-fill: #c85c13;" +
            "-fx-font-size: 25px;" +
            "-fx-font-weight: bold;"
        );

        Label verifiedTitle = new Label("Account Verified");
        verifiedTitle.setStyle(
            "-fx-text-fill: #111111;" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Label verifiedDescription = new Label(
            "Your identity has been\n" +
            "confirmed. You have full\n" +
            "access to marketplace\n" +
            "features."
        );
        verifiedDescription.setAlignment(Pos.CENTER);
        verifiedDescription.setTextAlignment(TextAlignment.CENTER);
        verifiedDescription.setStyle(
            "-fx-text-fill: #555555;" +
            "-fx-font-size: 13px;" +
            "-fx-line-spacing: 2px;"
        );

        Button verificationBtn = new Button("View Verification Details");
        verificationBtn.setPrefWidth(180);
        verificationBtn.setPrefHeight(30);

        verifiedBox.getChildren().addAll(
            shieldIcon,
            verifiedTitle,
            verifiedDescription,
            verificationBtn
        );
        verifiedBox.setStyle("-fx-background-color: #ebccb7;");

        HBox mainBox = new HBox(leftVBox, contentBox, verifiedBox);
        mainBox.setPrefSize(1550, 850);

        Scene sc = new Scene(mainBox, 1550, 850);
        setingSceene = sc;

        return setingSceene;
    }

    private void loadUserData(
        Label headerName,
        Label profileName,
        Label profileEmail,
        TextField nameField,
        TextField emailField,
        TextField phoneField,
        Label member,
        Button saveButton
    ) {
        if (userId == null || userId.trim().isEmpty()) {
            headerName.setText("User");
            profileName.setText("User");
            profileEmail.setText("Email not found");
            saveButton.setDisable(true);
            return;
        }

        new Thread(() -> {
            User user = userController.getUser(userId.trim());

            Platform.runLater(() -> {
                if (user == null) {
                    headerName.setText("User not found");
                    profileName.setText("User not found");
                    profileEmail.setText(userId);
                    saveButton.setDisable(true);
                    return;
                }

                String userName = user.getName();
                String userEmail = user.getEmail();
                String userMobile = user.getMobile();
                String userRole = user.getRole();

                if (userName == null || userName.trim().isEmpty()) {
                    userName = "User";
                }

                if (userEmail == null || userEmail.trim().isEmpty()) {
                    userEmail = userId;
                }

                if (userMobile == null) {
                    userMobile = "";
                }

                if (userRole == null || userRole.trim().isEmpty()) {
                    userRole = "Member";
                }

                headerName.setText(userName);
                profileName.setText(userName);
                profileEmail.setText(userEmail);
                member.setText(userRole);

                nameField.setText(userName);
                emailField.setText(userEmail);
                phoneField.setText(userMobile);

                saveButton.setDisable(false);
            });
        }).start();
    }

    public void backToseting() {
        Homepage.HomepageStage.setScene(setingSceene);
    }

    private VBox createTabHeader(String titleText, String subtitleText) {
        Label t = new Label(titleText);
        t.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Label sub = new Label(subtitleText);
        sub.setStyle("-fx-font-size: 13px; -fx-text-fill: #666666;");

        VBox header = new VBox(4, t, sub);
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private VBox createCardContainer() {
        VBox card = new VBox(18);
        card.setPrefWidth(600);
        card.setMinWidth(600);
        card.setMaxWidth(600);
        card.setPadding(new Insets(24));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 14; -fx-border-radius: 14; -fx-border-color: #eeeeee; -fx-border-width: 1;");

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(12);
        cardShadow.setOffsetY(4);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.08));
        card.setEffect(cardShadow);

        return card;
    }

    private HBox createToggleRow(String titleText, String subtitleText) {
        Label t = new Label(titleText);
        t.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label sub = new Label(subtitleText);
        sub.setStyle("-fx-font-size: 11px; -fx-text-fill: #777777;");

        VBox textBox = new VBox(2, t, sub);

        Button toggle = new Button("ON");
        toggle.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 4 14; -fx-cursor: hand;");

        toggle.setOnAction(e -> {
            if ("ON".equals(toggle.getText())) {
                toggle.setText("OFF");
                toggle.setStyle("-fx-background-color: #cccccc; -fx-text-fill: #555555; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 4 14; -fx-cursor: hand;");
            } else {
                toggle.setText("ON");
                toggle.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 12; -fx-padding: 4 14; -fx-cursor: hand;");
            }
        });

        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);

        HBox row = new HBox(12, textBox, sp, toggle);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private VBox createAddressCard(String titleText, String descText) {
        VBox box = new VBox(4);
        box.setPadding(new Insets(14));
        box.setStyle("-fx-background-color: #fcf8f5; -fx-background-radius: 8; -fx-border-color: #f2e2d6; -fx-border-radius: 8;");

        Label t = new Label(titleText);
        t.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Label d = new Label(descText);
        d.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        box.getChildren().addAll(t, d);
        return box;
    }

    private VBox createPrivacyView() {
        VBox card = createCardContainer();
        Label label = new Label("Data Privacy & Sharing Controls");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        HBox toggle1 = createToggleRow("Public Profile Visibility", "Allow other shoppers to see your public profile & reviews.");
        HBox toggle2 = createToggleRow("Personalized Recommendations", "Receive tailored product deals based on your browsing history.");
        HBox toggle3 = createToggleRow("Activity Tracking", "Help improve BuyNeX by sharing anonymous usage statistics.");

        Label statusMsg = new Label("");
        statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #ff7100; -fx-font-weight: bold;");

        Button clearHistoryBtn = new Button("Clear Search History");
        clearHistoryBtn.setStyle("-fx-background-color: #fff0e6; -fx-text-fill: #a83f00; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand; -fx-border-color: #ff7100; -fx-border-radius: 8;");
        clearHistoryBtn.setOnAction(e -> statusMsg.setText("✓ Search history cleared successfully."));

        card.getChildren().addAll(label, toggle1, toggle2, toggle3, clearHistoryBtn, statusMsg);
        return card;
    }

    private VBox createNotificationView() {
        VBox card = createCardContainer();
        Label label = new Label("Push & Email Notification Preferences");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        HBox toggle1 = createToggleRow("Order Status Alerts", "Receive instant notifications when order status changes.");
        HBox toggle2 = createToggleRow("Promotions & Hot Deals", "Get notified about limited-time price drops and flash sales.");
        HBox toggle3 = createToggleRow("Email Invoices", "Automatically email digital receipts upon payment completion.");

        Label statusMsg = new Label("");
        statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #28a745; -fx-font-weight: bold;");

        Button saveNotifBtn = new Button("Save Notification Preferences");
        saveNotifBtn.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");
        saveNotifBtn.setOnAction(e -> statusMsg.setText("✓ Preferences saved successfully."));

        card.getChildren().addAll(label, toggle1, toggle2, toggle3, saveNotifBtn, statusMsg);
        return card;
    }

    private VBox createAddressView() {
        VBox card = createCardContainer();
        Label label = new Label("Saved Delivery Addresses");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        VBox addressList = new VBox(10);
        addressList.getChildren().addAll(
            createAddressCard("🏠 Home Address (Default)", "Downtown Manhattan, 5th Avenue, Flat 4B, New York, NY 10001"),
            createAddressCard("🏢 Office Address", "Tech Park Tower B, 12th Floor, Silicon Valley, CA 94025")
        );

        Label formTitle = new Label("Add New Delivery Address");
        formTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        TextField streetField = new TextField();
        streetField.setPromptText("Flat / Building / House No / Street Name");
        streetField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        TextField cityField = new TextField();
        cityField.setPromptText("City / Area & Pincode");
        cityField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        Button addAddrBtn = new Button("Save New Address");
        addAddrBtn.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");

        addAddrBtn.setOnAction(e -> {
            String st = streetField.getText().trim();
            String ct = cityField.getText().trim();
            if (!st.isEmpty() && !ct.isEmpty()) {
                addressList.getChildren().add(createAddressCard("📍 Saved Location", st + ", " + ct));
                streetField.clear();
                cityField.clear();
            }
        });

        card.getChildren().addAll(label, addressList, formTitle, streetField, cityField, addAddrBtn);
        return card;
    }

    private VBox createPaymentView() {
        VBox card = createCardContainer();
        Label label = new Label("Saved Payment Methods & Cards");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        VBox paymentList = new VBox(10);
        paymentList.getChildren().addAll(
            createAddressCard("💳 Visa Credit Card (Primary)", "**** **** **** 4242 (Expires 12/28)"),
            createAddressCard("📱 UPI Payment ID", "sai@upi")
        );

        Label formTitle = new Label("Add New Card / Payment Method");
        formTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        TextField cardNameField = new TextField();
        cardNameField.setPromptText("Cardholder Name");
        cardNameField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("16-digit Card Number");
        cardNumberField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        HBox cardDetailsRow = new HBox(10);
        TextField expField = new TextField();
        expField.setPromptText("MM/YY");
        expField.setPrefWidth(120);
        expField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");
        cvvField.setPrefWidth(120);
        cvvField.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        cardDetailsRow.getChildren().addAll(expField, cvvField);

        Button saveCardBtn = new Button("Save Payment Card");
        saveCardBtn.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");

        saveCardBtn.setOnAction(e -> {
            String num = cardNumberField.getText().trim();
            if (num.length() >= 4) {
                String last4 = num.substring(num.length() - 4);
                paymentList.getChildren().add(createAddressCard("💳 Credit Card", "**** **** **** " + last4 + " (Exp " + expField.getText().trim() + ")"));
                cardNumberField.clear();
                cardNameField.clear();
                expField.clear();
                cvvField.clear();
            }
        });

        card.getChildren().addAll(label, paymentList, formTitle, cardNameField, cardNumberField, cardDetailsRow, saveCardBtn);
        return card;
    }

    private VBox createOrdersView() {
        VBox card = createCardContainer();
        Label label = new Label("Order & Checkout Preferences");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        HBox toggle1 = createToggleRow("1-Click Fast Checkout", "Bypass extra confirmation steps during cart checkout.");
        HBox toggle2 = createToggleRow("Auto Reorder Groceries", "Automatically prompt weekly grocery restock when quantity runs low.");
        HBox toggle3 = createToggleRow("Save Order History Log", "Keep full itemized transaction records in customer dashboard.");

        card.getChildren().addAll(label, toggle1, toggle2, toggle3);
        return card;
    }

    private VBox createDeliveryView() {
        VBox card = createCardContainer();
        Label label = new Label("Delivery Instructions & Slot Preferences");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        HBox toggle1 = createToggleRow("No-Contact Doorstep Delivery", "Leave packages at front door without ringing bell.");
        HBox toggle2 = createToggleRow("Call Before Delivery", "Rider will call 10 minutes prior to arrival.");

        Label slotTitle = new Label("Preferred Delivery Time Window");
        slotTitle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button slot1 = new Button("🌅 Morning (8 AM - 12 PM)");
        Button slot2 = new Button("☀️ Afternoon (12 PM - 5 PM)");
        Button slot3 = new Button("🌙 Evening (5 PM - 9 PM)");

        String activeSlot = "-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 6 12; -fx-cursor: hand;";
        String inactiveSlot = "-fx-background-color: #f5f5f5; -fx-text-fill: #444444; -fx-font-weight: normal; -fx-background-radius: 8; -fx-padding: 6 12; -fx-cursor: hand;";

        slot1.setStyle(activeSlot);
        slot2.setStyle(inactiveSlot);
        slot3.setStyle(inactiveSlot);

        slot1.setOnAction(e -> { slot1.setStyle(activeSlot); slot2.setStyle(inactiveSlot); slot3.setStyle(inactiveSlot); });
        slot2.setOnAction(e -> { slot1.setStyle(inactiveSlot); slot2.setStyle(activeSlot); slot3.setStyle(inactiveSlot); });
        slot3.setOnAction(e -> { slot1.setStyle(inactiveSlot); slot2.setStyle(inactiveSlot); slot3.setStyle(activeSlot); });

        VBox slotsBox = new VBox(6, slot1, slot2, slot3);

        card.getChildren().addAll(label, toggle1, toggle2, slotTitle, slotsBox);
        return card;
    }

    private VBox createSecurityView() {
        VBox card = createCardContainer();
        Label label = new Label("Account Security & Credentials");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Label passTitle = new Label("Change Password");
        passTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        javafx.scene.control.PasswordField currentPass = new javafx.scene.control.PasswordField();
        currentPass.setPromptText("Current Password");
        currentPass.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        javafx.scene.control.PasswordField newPass = new javafx.scene.control.PasswordField();
        newPass.setPromptText("New Password");
        newPass.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        javafx.scene.control.PasswordField confirmPass = new javafx.scene.control.PasswordField();
        confirmPass.setPromptText("Confirm New Password");
        confirmPass.setStyle("-fx-font-size: 11px; -fx-padding: 8; -fx-background-radius: 6; -fx-border-color: #dddddd; -fx-border-radius: 6;");

        Label statusMsg = new Label("");
        statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #28a745; -fx-font-weight: bold;");

        Button updatePassBtn = new Button("Update Password");
        updatePassBtn.setStyle("-fx-background-color: #ff7100; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");

        updatePassBtn.setOnAction(e -> {
            String curr = currentPass.getText().trim();
            String nPass = newPass.getText().trim();
            String cPass = confirmPass.getText().trim();

            if (curr.isEmpty() || nPass.isEmpty()) {
                statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #d93025; -fx-font-weight: bold;");
                statusMsg.setText("❌ Please enter both current and new password.");
                return;
            }

            if (!nPass.equals(cPass)) {
                statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #d93025; -fx-font-weight: bold;");
                statusMsg.setText("❌ New password and confirmation password do not match.");
                return;
            }

            if (nPass.length() < 6) {
                statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #d93025; -fx-font-weight: bold;");
                statusMsg.setText("❌ New password must be at least 6 characters.");
                return;
            }

            updatePassBtn.setDisable(true);
            updatePassBtn.setText("Updating...");

            new Thread(() -> {
                boolean updated = userController.updatePassword(userId, nPass);
                Platform.runLater(() -> {
                    updatePassBtn.setDisable(false);
                    updatePassBtn.setText("Update Password");

                    if (updated) {
                        statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #28a745; -fx-font-weight: bold;");
                        statusMsg.setText("✓ Password updated successfully in database!");
                        currentPass.clear();
                        newPass.clear();
                        confirmPass.clear();
                    } else {
                        statusMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #d93025; -fx-font-weight: bold;");
                        statusMsg.setText("❌ Failed to update password. Please try again.");
                    }
                });
            }).start();
        });

        HBox toggle2FA = createToggleRow("Two-Factor Authentication (2FA)", "Require SMS OTP verification on every new login.");

        Button logoutFromSecBtn = new Button("🚪  Log Out of Account");
        logoutFromSecBtn.setStyle(
            "-fx-background-color: #fee2e2;" +
            "-fx-text-fill: #b3261e;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10 18;" +
            "-fx-cursor: hand;"
        );
        logoutFromSecBtn.setOnAction(e -> {
            CustomerNavigation.navigateToLogin();
        });

        card.getChildren().addAll(label, passTitle, currentPass, newPass, confirmPass, updatePassBtn, statusMsg, toggle2FA, logoutFromSecBtn);
        return card;
    }

    private VBox createTermsView() {
        VBox card = createCardContainer();
        Label label = new Label("Terms of Service & Policies");
        label.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111111;");

        Label termsText = new Label(
            "1. User Agreement: By accessing BuyNeX, you agree to comply with marketplace rules.\n\n" +
            "2. Privacy & Security: We protect your personal details using encryption and secure storage.\n\n" +
            "3. Orders & Refunds: Eligible returns will be refunded within 3-5 business days."
        );
        termsText.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555; -fx-line-spacing: 3px;");
        termsText.setWrapText(true);

        card.getChildren().addAll(label, termsText);
        return card;
    }
}
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
import javafx.stage.Stage;

public class Seting {
        public String userId;
        public Seting(String userId) {
        this.userId = userId;
    }
        private Scene setingSceene;

        private final Userstorecontroller userController =
                        new Userstorecontroller();

        Scene getSetingscene(Runnable callbacktoDashboard) {
                // ================= PROFILE IMAGE =================

                Image defaultImage = new Image(
                                "https://cdn-icons-png.flaticon.com/512/149/149071.png");

                ImageView profileImage = new ImageView(defaultImage);

                profileImage.setFitWidth(45);
                profileImage.setFitHeight(45);
                profileImage.setPreserveRatio(true);

                // Circle shape
                Circle clip = new Circle(22.5, 22.5, 22.5);
                profileImage.setClip(clip);

                // ================= CLICK TO CHANGE IMAGE =================

                profileImage.setOnMouseClicked(event -> {

                        FileChooser fileChooser = new FileChooser();

                        fileChooser.setTitle("Choose Profile Picture");

                        fileChooser.getExtensionFilters().add(
                                        new FileChooser.ExtensionFilter(
                                                        "Image Files",
                                                        "*.png",
                                                        "*.jpg",
                                                        "*.jpeg"));

                        File file = fileChooser.showOpenDialog(
                                        Homepage.HomepageStage);

                        if (file != null) {

                                // Immediately show selected image
                                Image newImage = new Image(
                                                file.toURI().toString());

                                profileImage.setImage(newImage);

                                System.out.println(
                                                "Selected File: " + file.getAbsolutePath());

                                // Upload image to Cloudinary
                                String url = Clodnarycontroller.imageUpload(file);

                                System.out.println(
                                                "Cloudinary URL: " + url);

                                // Cursor
                                profileImage.setCursor(Cursor.HAND);
                        }
                });

                profileImage.setCursor(Cursor.HAND);

                // ================= USER INFORMATION =================

                Label name = new Label("Loading...");

                name.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #a83f00;");

                Label member = new Label("Member");

                member.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-text-fill: #555555;");

                VBox userInfo = new VBox(
                                2,
                                name,
                                member);

                userInfo.setAlignment(Pos.CENTER_LEFT);

                // ================= PROFILE HEADER =================

                HBox profileBox = new HBox(
                                12,
                                profileImage,
                                userInfo);

                profileBox.setPrefWidth(250);
                profileBox.setPrefHeight(80);

                profileBox.setAlignment(Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(10, 15, 10, 25));

                profileBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #eeeeee;" +
                                                "-fx-border-width: 0 0 1 0;");

                // ================= PROFILE BUTTON =================

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
                                                "-fx-cursor: hand;");

                // ================= PRIVACY BUTTON =================

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
                                                "-fx-cursor: hand;");
                
                // ================= NOTIFICATION BUTTON =================

                Button notificationBtn = new Button("♧    Notifications");

                notificationBtn.setPrefSize(200, 36);
                notificationBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= ADDRESS BUTTON =================

                Button addressBtn = new Button("⌖    Address");

                addressBtn.setPrefSize(200, 36);
                addressBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= PAYMENT BUTTON =================

                Button paymentBtn = new Button("▣    Payment");

                paymentBtn.setPrefSize(200, 36);
                paymentBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= ORDERS BUTTON =================

                Button ordersBtn = new Button("♧    Orders");

                ordersBtn.setPrefSize(200, 36);
                ordersBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= DELIVERY BUTTON =================

                Button deliveryBtn = new Button("▱    Delivery");

                deliveryBtn.setPrefSize(200, 36);
                deliveryBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= LANGUAGE BUTTON =================

                Button languageBtn = new Button("◎    Language");

                languageBtn.setPrefSize(200, 36);
                languageBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= APPEARANCE BUTTON =================

                Button appearanceBtn = new Button("◉    Appearance");

                appearanceBtn.setPrefSize(200, 36);
                appearanceBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= ACCESSIBILITY BUTTON =================

                Button accessibilityBtn = new Button("♿   Accessibility");

                accessibilityBtn.setPrefSize(200, 36);
                accessibilityBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= SECURITY BUTTON =================

                Button securityBtn = new Button("♢    Login & Security");

                securityBtn.setPrefSize(200, 36);
                securityBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                // ================= HELP BUTTON =================

                Button helpBtn = new Button("?    Help");

                helpBtn.setPrefSize(200, 36);
                helpBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");
        

                // ================= TERMS BUTTON =================

                Button termsBtn = new Button("▤    Terms");

                termsBtn.setPrefSize(200, 36);
                termsBtn.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                Button back = new Button("back");
                back.setPrefSize(200, 36);
                back.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #7b1212;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");
                back.setOnAction(even -> {
                        
                        Dashbord ds=new Dashbord(userId);
                        Homepage.HomepageStage.setScene(ds.getDashbordScene());
                       
                });

                // ================= MENU BOX =================

                VBox menuBox = new VBox(5);

                menuBox.setPadding(
                                new Insets(30, 15, 10, 15));

                menuBox.getChildren().addAll(
                                profileBtn,
                                privacyBtn,
                                notificationBtn,
                                addressBtn,
                                paymentBtn,
                                ordersBtn,
                                deliveryBtn,
                                languageBtn,
                                appearanceBtn,
                                accessibilityBtn,
                                securityBtn,
                                helpBtn,
                                termsBtn, back);
                menuBox.setStyle("-fx-background-color: #ebccb7;");

                // ================= UPGRADE TO PRO =================

                Button upgradeBtn = new Button("Upgrade to Pro");

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
                                                "-fx-cursor: hand;");

                HBox upgradeBox = new HBox(upgradeBtn);

                upgradeBox.setAlignment(Pos.CENTER);

                upgradeBox.setPadding(
                                new Insets(10, 15, 15, 15));

                // ================= SELECTED BUTTON STYLE =================

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

                // ================= NORMAL BUTTON STYLE =================

                String normalButtonStyle = "-fx-background-color: transparent;" +
                                "-fx-text-fill: #333333;" +
                                "-fx-font-size: 12px;" +
                                "-fx-font-weight: normal;" +
                                "-fx-alignment: CENTER_LEFT;" +
                                "-fx-padding: 0 0 0 14;" +
                                "-fx-background-radius: 6;" +
                                "-fx-cursor: hand;";

                // ================= ALL MENU BUTTONS =================

                Button[] menuButtons = {
                                profileBtn,
                                privacyBtn,
                                notificationBtn,
                                addressBtn,
                                paymentBtn,
                                ordersBtn,
                                deliveryBtn,
                                languageBtn,
                                appearanceBtn,
                                accessibilityBtn,
                                securityBtn,
                                helpBtn,
                                termsBtn
                };

                // ================= CLICK EVENT =================

                for (Button button : menuButtons) {

                        button.setOnAction(event -> {

                                // Pehle sabhi buttons normal karo
                                for (Button b : menuButtons) {

                                        b.setStyle(normalButtonStyle);
                                }

                                // Jis button par click hua
                                // usko orange selected style do
                                button.setStyle(selectedButtonStyle);

                        });
                }

                helpBtn.setOnAction(event -> {
                        Helppage helppage = new Helppage(userId);
                        Runnable rn=new Runnable() {
                                public void run(){
                                        backToseting();
                                }
                        };

                        Homepage.HomepageStage.setScene(helppage.getHelpScene(rn));
                });
                privacyBtn.setOnAction(event -> {
                        Privacy pc = new Privacy(userId);
                        Runnable rn = new Runnable() {
                                public void run() {
                                        backToseting();
                                }
                        };
                        Homepage.HomepageStage.setScene(pc.getPrivecyscene(rn));
                });


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
                                                "-fx-text-fill: #111111;");

                title.setTranslateX(100);

                // Subtitle
                Label subtitle = new Label(
                                "Manage your personal information and security preferences.");

                subtitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-text-fill: #666666;");
                subtitle.setTranslateX(100);

                // ================= PROFILE CARD =================

                // ================= PROFILE CARD =================
                // ================= PROFILE CARD =================

                HBox profileCard = new HBox();

                profileCard.setPrefWidth(600);
                profileCard.setMinWidth(600);
                profileCard.setMaxWidth(600);

                profileCard.setPrefHeight(130);
                profileCard.setMinHeight(130);
                profileCard.setMaxHeight(130);

                profileCard.setAlignment(Pos.CENTER_LEFT);

                profileCard.setPadding(
                                new Insets(15, 25, 15, 20));

                profileCard.setStyle(
                                "-fx-background-color: #ffffff;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-border-radius: 14;" +
                                                "-fx-border-color: #eeeeee;" +
                                                "-fx-border-width: 1;");

                profileCard.setTranslateX(50);

                // ================= CARD SHADOW =================

                DropShadow cardShadow = new DropShadow();

                cardShadow.setRadius(12);
                cardShadow.setSpread(0.03);

                cardShadow.setOffsetX(0);
                cardShadow.setOffsetY(4);

                cardShadow.setColor(
                                Color.rgb(0, 0, 0, 0.10));

                profileCard.setEffect(cardShadow);

                // =====================================================
                // PROFILE IMAGE
                // =====================================================

                Image profileImg = new Image(
                                "file:C:/Users/YourName/Pictures/profile.jpg");

                ImageView profileImagevView = new ImageView(profileImg);

                profileImagevView.setFitWidth(88);
                profileImagevView.setFitHeight(88);

                profileImagevView.setPreserveRatio(true);

                // Circular Image
                Circle imageClip = new Circle(44, 44, 44);

                profileImagevView.setClip(imageClip);

                // =====================================================
                // EDIT BUTTON
                // =====================================================

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
                                                "-fx-cursor: hand;");

                // =====================================================
                // IMAGE + EDIT BUTTON
                // =====================================================

                StackPane imageBox = new StackPane();

                imageBox.setPrefWidth(90);
                imageBox.setPrefHeight(90);

                imageBox.setMinWidth(90);
                imageBox.setMinHeight(90);

                imageBox.setMaxWidth(90);
                imageBox.setMaxHeight(90);

                imageBox.getChildren().addAll(
                                profileImage,
                                editButton);

                // Position edit button
                StackPane.setAlignment(
                                editButton,
                                Pos.BOTTOM_RIGHT);

                StackPane.setMargin(
                                editButton,
                                new Insets(0, -2, -2, 0));

                // =====================================================
                // NAME
                // =====================================================

                Label nameLabel = new Label("Loading...");

                nameLabel.setStyle(
                                "-fx-text-fill: #111111;" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;");

                // =====================================================
                // PREMIUM BADGE
                // =====================================================

                Label premiumLabel = new Label("♙ Premium");

                premiumLabel.setStyle(
                                "-fx-background-color: #fff0e6;" +
                                                "-fx-text-fill: #c85c13;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-padding: 4px 8px;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;");

                // =====================================================
                // NAME + PREMIUM
                // =====================================================

                HBox nameRow = new HBox(7);

                nameRow.setAlignment(Pos.CENTER_LEFT);

                nameRow.getChildren().addAll(
                                nameLabel,
                                premiumLabel);

                // =====================================================
                // EMAIL
                // =====================================================

                Label emailLabel = new Label("Loading...");

                emailLabel.setStyle(
                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 12px;");

                // =====================================================
                // DETAILS BOX
                // =====================================================

                VBox detailsBox = new VBox(6);

                detailsBox.setAlignment(Pos.CENTER_LEFT);

                detailsBox.getChildren().addAll(
                                nameRow,
                                emailLabel);

                // =====================================================
                // ADD TO PROFILE CARD
                // =====================================================

                profileCard.getChildren().addAll(
                                imageBox,
                                detailsBox);

                // ================= PERSONAL INFORMATION =================

                VBox personalInfo = new VBox(18);

                personalInfo.setPrefWidth(600);
                personalInfo.setMinWidth(600);
                personalInfo.setMaxWidth(600);

                personalInfo.setPadding(
                                new Insets(18));

                personalInfo.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #ff8a45;" +
                                                "-fx-border-width: 1;");

                personalInfo.setTranslateX(50);

                // ================= FULL NAME =================

                TextField nameField = new TextField();
                nameField.setPrefSize(194, 37);

                VBox nameBox = new VBox(6,
                                new Label("Full Name"),
                                nameField);

                // ================= EMAIL =================

                TextField emailField = new TextField();

                emailField.setPrefSize(194, 37);

                VBox emailBox = new VBox(6,
                                new Label("Email Address"),
                                emailField);

                HBox nameAndEmail = new HBox(50, nameBox, emailBox);

                // ================= PHONE =================

                TextField phoneField = new TextField();

                phoneField.setPrefSize(194, 37);

                VBox phoneBox = new VBox(6,
                                new Label("Phone Number"),
                                phoneField);

                // ================= DATE =================

                DatePicker dob = new DatePicker(
                                LocalDate.of(1990, 10, 24));

                dob.setPrefSize(194, 37);

                VBox dobBox = new VBox(6,
                                new Label("Date of Birth"),
                                dob);

                HBox phoneanddob = new HBox(50, phoneBox, dobBox);

                // ================= SAVE BUTTON =================

                Button saveButton = new Button("Save Changes");
                saveButton.setPrefWidth(150);
                saveButton.setPrefHeight(38);
                saveButton.setStyle(
                                "-fx-background-color: #ff7100;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-cursor: hand;");

                saveButton.setOnAction(event -> {

                        String oldEmail = userId == null ? "" : userId.trim();
                        String updatedName = nameField.getText().trim();
                        String updatedEmail = emailField.getText().trim();
                        String updatedMobile = phoneField.getText().trim();

                        if (oldEmail.isEmpty()) {
                                System.out.println("User email not found.");
                                return;
                        }

                        if (updatedName.isEmpty() ||
                                        updatedEmail.isEmpty() ||
                                        updatedMobile.isEmpty()) {
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
                                                updatedMobile);

                                Platform.runLater(() -> {

                                        saveButton.setDisable(false);
                                        saveButton.setText("Save Changes");

                                        if (updated) {

                                                userId = updatedEmail;
                                                nameLabel.setText(updatedName);
                                                emailLabel.setText(updatedEmail);
                                                name.setText(updatedName);

                                                System.out.println(
                                                                "Profile updated successfully.");

                                        } else {

                                                System.out.println(
                                                                "Profile update failed.");
                                        }
                                });

                        }).start();
                });

                // ================= ADD PERSONAL DATA =================

                personalInfo.getChildren().addAll(
                                nameAndEmail,
                                phoneanddob,
                                saveButton);

                // ========================================================
                // ACCOUNT SECURITY
                // ========================================================

                HBox accountSecurity = new HBox(50);

                accountSecurity.setPrefWidth(600);
                accountSecurity.setMinWidth(600);
                accountSecurity.setMaxWidth(600);

                accountSecurity.setPrefHeight(75);
                accountSecurity.setPadding(
                                new Insets(12, 18, 12, 18));

                accountSecurity.setAlignment(
                                Pos.CENTER_LEFT);

                accountSecurity.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-border-radius: 14;");

                // ================= ICON =================

                Label lockIcon = new Label("•••");

                lockIcon.setPrefSize(32, 32);
                lockIcon.setAlignment(Pos.CENTER);

                lockIcon.setStyle(
                                "-fx-background-color: #e8e5eb;" +
                                                "-fx-background-radius: 50%;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;");

                // ================= TEXT =================

                Label changePassword = new Label("Change Password");

                changePassword.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;");

                Label description = new Label(
                                "Update your password to keep your account secure");

                description.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #777777;");

                VBox passwordText = new VBox(
                                3,
                                changePassword,
                                description);
                passwordText.setTranslateX(100);

                // ================= ARROW =================

                Label arrow = new Label("›");

                arrow.setStyle(
                                "-fx-font-size: 22px;");

                // ================= SPACER =================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // ================= ADD SECURITY DATA =================

                accountSecurity.getChildren().addAll(
                                lockIcon,
                                passwordText,
                                spacer,
                                arrow);
                accessibilityBtn.setTranslateX(200);

                // =========================================================
                // LOAD USER DATA FROM FIREBASE
                // =========================================================

                loadUserData(
                                name,
                                nameLabel,
                                emailLabel,
                                nameField,
                                emailField,
                                phoneField,
                                member,
                                saveButton);

                VBox contentBox = new VBox(20, title, subtitle, profileCard, personalInfo, accountSecurity);
                contentBox.setPrefWidth(1250);
                contentBox.setPrefHeight(800);
                contentBox.setStyle("-fx-background-color: #eee5df;");

                // ================= ACCOUNT VERIFIED CARD =================

                VBox verifiedBox = new VBox(10);

                verifiedBox.setPrefWidth(700);
                verifiedBox.setPrefHeight(800);

                verifiedBox.setAlignment(Pos.CENTER);

                verifiedBox.setPadding(new Insets(18, 15, 15, 15));

                verifiedBox.setStyle(
                                "-fx-background-color: #eee5df" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-border-radius: 14;");

                // Shadow
                DropShadow shadow2 = new DropShadow();
                shadow2.setRadius(12);
                shadow2.setSpread(0.03);
                shadow2.setOffsetX(0);
                shadow2.setOffsetY(3);
                shadow2.setColor(Color.rgb(0, 0, 0, 0.10));

                verifiedBox.setEffect(shadow2);

                // ================= ICON CIRCLE =================

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
                                                "-fx-font-weight: bold;");

                // ================= TITLE =================

                Label verifiedTitle = new Label("Account Verified");

                verifiedTitle.setStyle(
                                "-fx-text-fill: #111111;" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;");

                // ================= DESCRIPTION =================

                Label verifiedDescription = new Label(
                                "Your identity has been\n" +
                                                "confirmed. You have full\n" +
                                                "access to marketplace\n" +
                                                "features.");

                verifiedDescription.setAlignment(Pos.CENTER);

                verifiedDescription.setTextAlignment(TextAlignment.CENTER);

                verifiedDescription.setStyle(
                                "-fx-text-fill: #555555;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-line-spacing: 2px;");

                // ================= VIEW DETAILS =================

                Button verificationBtn = new Button("View Verification Details");

                verificationBtn.setPrefWidth(180);
                verificationBtn.setPrefHeight(30);

                // ================= ADD EVERYTHING =================

                verifiedBox.getChildren().addAll(
                                shieldIcon,
                                verifiedTitle,
                                verifiedDescription,
                                verificationBtn);
                verifiedBox.setStyle("-fx-background-color: #ebccb7;");

                // contentofpolicy

                HBox mainBox = new HBox(leftVBox, contentBox, verifiedBox);

                mainBox.setPrefSize(1500, 800);

                Scene sc = new Scene(mainBox, 1500, 800);
                setingSceene = sc;

                return setingSceene;
        }

        // =========================================================
        // LOAD USER DATA
        // =========================================================

        private void loadUserData(
                        Label headerName,
                        Label profileName,
                        Label profileEmail,
                        TextField nameField,
                        TextField emailField,
                        TextField phoneField,
                        Label member,
                        Button saveButton) {

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


        // =========================================================
        // BACK TO SETTINGS
        // =========================================================

        public void backToseting() {
                Homepage.HomepageStage.setScene(setingSceene);
        }

}
package com.kryox.view.Customer;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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

public class Privacy  {
        private Scene setingSceene;

        Scene getPrivecyscene(Runnable callbacktoDashboard){
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

                        File file = fileChooser.showOpenDialog(Homepage.HomepageStage);

                        if (file != null) {

                                Image newImage = new Image(
                                                file.toURI().toString());

                                profileImage.setImage(newImage);

                                // Cursor
                                profileImage.setCursor(Cursor.HAND);
                        }
                });

                profileImage.setCursor(Cursor.HAND);

                // ================= USER INFORMATION =================

                Label name = new Label("Saiprasad sable");

                name.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #a83f00;");

                Label member = new Label("Premium Member");

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

                Button back=new Button("back");
                back.setPrefSize(200, 36);
                back.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #7b1212;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-padding: 0 0 0 14;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");
                back.setOnAction(even->{
                        callbacktoDashboard.run();
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
                                termsBtn,back);
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

                HBox profileCard = new HBox();

                profileCard.setPrefWidth(600);
                profileCard.setMinWidth(600);
                profileCard.setMaxWidth(600);

                profileCard.setPrefHeight(130);
                profileCard.setMinHeight(130);
                profileCard.setMaxHeight(130);

                profileCard.setAlignment(Pos.CENTER_LEFT);

                profileCard.setPadding(
                        new Insets(15, 25, 15, 20)
                );

                profileCard.setStyle(
                        "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-radius: 14;" +
                        "-fx-border-color: #eeeeee;" +
                        "-fx-border-width: 1;"
                );

                profileCard.setTranslateX(50);


                // ================= CARD SHADOW =================

                DropShadow cardShadow = new DropShadow();

                cardShadow.setRadius(12);
                cardShadow.setSpread(0.03);

                cardShadow.setOffsetX(0);
                cardShadow.setOffsetY(4);

                cardShadow.setColor(
                        Color.rgb(0, 0, 0, 0.10)
                );

                profileCard.setEffect(cardShadow);


                // =====================================================
                //                  PROFILE IMAGE
                // =====================================================

                Image profileImg = new Image(
                        "file:C:/Users/YourName/Pictures/profile.jpg"
                );

                ImageView profileImagevView= new ImageView(profileImg);

                profileImagevView.setFitWidth(88);
                profileImagevView.setFitHeight(88);

                profileImagevView.setPreserveRatio(true);


                // Circular Image
                Circle imageClip = new Circle(44, 44, 44);

                profileImagevView.setClip(imageClip);


                // =====================================================
                //                  EDIT BUTTON
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
                        "-fx-cursor: hand;"
                );


                // =====================================================
                //              IMAGE + EDIT BUTTON
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
                        editButton
                );



                // Position edit button
                StackPane.setAlignment(
                        editButton,
                        Pos.BOTTOM_RIGHT
                );

                StackPane.setMargin(
                        editButton,
                        new Insets(0, -2, -2, 0)
                );


                // =====================================================
                //                    NAME
                // =====================================================

                Label nameLabel = new Label("Alex Rivera");

                nameLabel.setStyle(
                        "-fx-text-fill: #111111;" +
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;"
                );


                // =====================================================
                //                 PREMIUM BADGE
                // =====================================================

                Label premiumLabel = new Label("♙ Premium");

                premiumLabel.setStyle(
                        "-fx-background-color: #fff0e6;" +
                        "-fx-text-fill: #c85c13;" +
                        "-fx-background-radius: 12;" +
                        "-fx-padding: 4px 8px;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;"
                );


                // =====================================================
                //              NAME + PREMIUM
                // =====================================================

                HBox nameRow = new HBox(7);

                nameRow.setAlignment(Pos.CENTER_LEFT);

                nameRow.getChildren().addAll(
                        nameLabel,
                        premiumLabel
                );


                // =====================================================
                //                    EMAIL
                // =====================================================

                Label emailLabel = new Label(
                        "alex.rivera@example.com"
                );

                emailLabel.setStyle(
                        "-fx-text-fill: #666666;" +
                        "-fx-font-size: 12px;"
                );


                // =====================================================
                //                  DETAILS BOX
                // =====================================================

                VBox detailsBox = new VBox(6);

                detailsBox.setAlignment(Pos.CENTER_LEFT);

                detailsBox.getChildren().addAll(
                        nameRow,
                        emailLabel
                );


                // =====================================================
                //              ADD TO PROFILE CARD
                // =====================================================

                profileCard.getChildren().addAll(
                        imageBox,
                        detailsBox
                );

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

                TextField nameField = new TextField("Alex Rivera");
                nameField.setPrefSize(194, 37);

                VBox nameBox = new VBox(6,
                                new Label("Full Name"),
                                nameField);

                // ================= EMAIL =================

                TextField emailField = new TextField("alex.rivera@example.com");

                emailField.setPrefSize(194, 37);

                VBox emailBox = new VBox(6,
                                new Label("Email Address"),
                                emailField);

                HBox nameAndEmail = new HBox(50, nameBox, emailBox);

                // ================= PHONE =================

                TextField phoneField = new TextField("+1 (555) 123-4567");

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

                // ================= ADD 4 DATA =================

                personalInfo.getChildren().addAll(
                                nameAndEmail, phoneanddob);

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

                // ================= PRIVACY POLICY CONTENT =================
                VBox privacyContent = createPrivacyPolicyContent();

                VBox contentBox = new VBox(20, title, subtitle, profileCard, personalInfo, accountSecurity, privacyContent);
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

                verificationBtn.setStyle(
                                "-fx-background-color: #ebccb7;" +
                                                "-fx-text-fill: #c85c13;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;" +
                                                "-fx-padding: 5px;");

                // ================= ADD EVERYTHING =================

                verifiedBox.getChildren().addAll(
                                shieldIcon,
                                verifiedTitle,
                                verifiedDescription,
                                verificationBtn);
                verifiedBox.setStyle("-fx-background-color: #ebccb7;");

                HBox mainBox = new HBox(leftVBox, contentBox, verifiedBox);

                mainBox.setPrefSize(1500, 800);

                Scene sc = new Scene(mainBox, 1500, 800);
                setingSceene=sc;

                return setingSceene;
        }

        private VBox createPrivacyPolicyContent() {
                VBox privacyBox = new VBox(15);
                privacyBox.setPadding(new Insets(20, 30, 20, 30));
                privacyBox.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-border-color: #ff8a45;" +
                        "-fx-border-width: 1;" +
                        "-fx-background-radius: 10;"
                );
                privacyBox.setTranslateX(50);
                privacyBox.setPrefWidth(600);
                privacyBox.setMaxWidth(600);

                // ================= HEADER =================
                Label privacyHeader = new Label("Privacy Policy");
                privacyHeader.setStyle(
                        "-fx-font-size: 24px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1a1a1a;"
                );

                // ================= 1. INTRODUCTION =================
                Label section1Title = createPrivacySectionTitle("1. Introduction");
                Label section1Content = createPrivacyContentLabel(
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ac massa id velit. " +
                        "Fusce euismod lectus sed nibh ultricies, in hendrerit risus egestas. Nunc accumsan, " +
                        "arcu sed dignissim feugiat, risus nisl egestas, nulla enim tempor arcu, ut aliquet " +
                        "neque leo quis ante. Duis mollis, tellus id vulputate facilisis, ipsum felis lobortis " +
                        "sapien, a hendrerit libero justo et lacus. Sed nonummy nibh euismod tincidunt."
                );

                Separator sep1 = new Separator();
                sep1.setPadding(new Insets(10, 0, 10, 0));

                // ================= 2. INFORMATION WE COLLECT =================
                Label section2Title = createPrivacySectionTitle("2. Information We Collect");
                Label section2Desc = createPrivacyContentLabel(
                        "We collect personal information to provide services and products that meet your needs. This includes:"
                );

                // Personal Information List
                VBox personalInfoList = new VBox(5);
                personalInfoList.setPadding(new Insets(10, 0, 10, 20));
                String[] personalItems = {
                        "Name", "Address", "Phone number", "Email address",
                        "Date of birth", "Social Security number", "Driver's license number"
                };
                for (String item : personalItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        personalInfoList.getChildren().add(itemLabel);
                }

                // Usage Data List
                Label usageLabel = new Label("Usage Data:");
                usageLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                usageLabel.setPadding(new Insets(10, 0, 5, 0));

                VBox usageInfoList = new VBox(5);
                usageInfoList.setPadding(new Insets(0, 0, 10, 20));
                String[] usageItems = {
                        "Browsing history", "Search history", "Internet behavior"
                };
                for (String item : usageItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        usageInfoList.getChildren().add(itemLabel);
                }

                // Device Information List
                Label deviceLabel = new Label("Device Information:");
                deviceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                deviceLabel.setPadding(new Insets(10, 0, 5, 0));

                VBox deviceInfoList = new VBox(5);
                deviceInfoList.setPadding(new Insets(0, 0, 10, 20));
                String[] deviceItems = {
                        "Operating system", "Browser type", "IP address", "Device identifier"
                };
                for (String item : deviceItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        deviceInfoList.getChildren().add(itemLabel);
                }

                Label personalLabel = new Label("Personal Information:");
                personalLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;");

                VBox section2Content = new VBox(5);
                section2Content.getChildren().addAll(
                        section2Desc,
                        personalLabel,
                        personalInfoList,
                        usageLabel,
                        usageInfoList,
                        deviceLabel,
                        deviceInfoList
                );

                Separator sep2 = new Separator();
                sep2.setPadding(new Insets(10, 0, 10, 0));

                // ================= 3. HOW WE USE YOUR INFORMATION =================
                Label section3Title = createPrivacySectionTitle("3. How We Use Your Information");
                Label section3Desc = createPrivacyContentLabel(
                        "We use your information for the following purposes:"
                );

                VBox useInfoList = new VBox(5);
                useInfoList.setPadding(new Insets(10, 0, 10, 20));
                String[] useItems = {
                        "To provide and improve our services",
                        "To respond to your requests",
                        "To communicate with you",
                        "To administer our business operations",
                        "To comply with legal requirements"
                };
                for (String item : useItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        useInfoList.getChildren().add(itemLabel);
                }

                VBox section3Content = new VBox(5);
                section3Content.getChildren().addAll(section3Desc, useInfoList);

                Separator sep3 = new Separator();
                sep3.setPadding(new Insets(10, 0, 10, 0));

                // ================= 4. COOKIES AND TRACKING =================
                Label section4Title = createPrivacySectionTitle("4. Cookies and Tracking Technologies");
                Label section4Content = createPrivacyContentLabel(
                        "We use cookies and tracking technologies to enhance your experience on our website " +
                        "and to gather information about your usage. These technologies help us analyze how " +
                        "you interact with our website and make improvements."
                );

                Separator sep4 = new Separator();
                sep4.setPadding(new Insets(10, 0, 10, 0));

                // ================= 5. HOW WE SHARE INFORMATION =================
                Label section5Title = createPrivacySectionTitle("5. How We Share Information");
                Label section5Desc = createPrivacyContentLabel(
                        "We may share your information with third parties to fulfill orders, process payments, " +
                        "and improve our services. We do not sell or share your information with third parties " +
                        "for marketing purposes."
                );

                // Service Providers
                Label serviceProviderLabel = new Label("With Service Providers");
                serviceProviderLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                serviceProviderLabel.setPadding(new Insets(10, 0, 5, 0));

                VBox serviceProviderList = new VBox(5);
                serviceProviderList.setPadding(new Insets(0, 0, 10, 20));
                String[] serviceItems = {
                        "Amazon Web Services",
                        "Microsoft Azure",
                        "Google Cloud Platform"
                };
                for (String item : serviceItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        serviceProviderList.getChildren().add(itemLabel);
                }

                // Affiliates
                Label affiliateLabel = new Label("With Affiliates");
                affiliateLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #333333;");
                affiliateLabel.setPadding(new Insets(10, 0, 5, 0));

                VBox affiliateList = new VBox(5);
                affiliateList.setPadding(new Insets(0, 0, 10, 20));
                String[] affiliateItems = {
                        "Salesforce.com",
                        "Oracle Corporation",
                        "SAP SE"
                };
                for (String item : affiliateItems) {
                        Label itemLabel = new Label("• " + item);
                        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333333;");
                        affiliateList.getChildren().add(itemLabel);
                }

                VBox section5Content = new VBox(5);
                section5Content.getChildren().addAll(
                        section5Desc,
                        serviceProviderLabel,
                        serviceProviderList,
                        affiliateLabel,
                        affiliateList
                );

                Separator sep5 = new Separator();
                sep5.setPadding(new Insets(10, 0, 10, 0));

                // ================= 6. CONTACT US =================
                Label section6Title = createPrivacySectionTitle("6. Contact Us");
                Label section6Desc = createPrivacyContentLabel(
                        "If you have any questions or feedback, please contact us at:"
                );

                // Contact Buttons
                Button emailContactBtn = new Button("📧 info@example.com");
                emailContactBtn.setStyle(
                        "-fx-background-color: #ff7100;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 16 8 16;" +
                        "-fx-cursor: hand;"
                );

                Button phoneContactBtn = new Button("📞 +1 (234) 567-890");
                phoneContactBtn.setStyle(
                        "-fx-background-color: #ff7100;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 8 16 8 16;" +
                        "-fx-cursor: hand;"
                );

                HBox contactBox = new HBox(15);
                contactBox.setAlignment(Pos.CENTER_LEFT);
                contactBox.setPadding(new Insets(10, 0, 10, 0));
                contactBox.getChildren().addAll(emailContactBtn, phoneContactBtn);

                // ================= SOCIAL LINKS =================
                Label socialLabel = new Label("Contact Us");
                socialLabel.setStyle(
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
                );
                socialLabel.setPadding(new Insets(15, 0, 5, 0));

                HBox socialBox = new HBox(15);
                socialBox.setAlignment(Pos.CENTER_LEFT);

                Button facebookBtn = new Button("Facebook");
                facebookBtn.setStyle(
                        "-fx-background-color: #1877F2;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 6 14 6 14;" +
                        "-fx-cursor: hand;"
                );

                Button twitterBtn = new Button("Twitter");
                twitterBtn.setStyle(
                        "-fx-background-color: #1DA1F2;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 6 14 6 14;" +
                        "-fx-cursor: hand;"
                );

                Button linkedinBtn = new Button("LinkedIn");
                linkedinBtn.setStyle(
                        "-fx-background-color: #0A66C2;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 6 14 6 14;" +
                        "-fx-cursor: hand;"
                );

                socialBox.getChildren().addAll(facebookBtn, twitterBtn, linkedinBtn);

                VBox section6Content = new VBox(5);
                section6Content.getChildren().addAll(
                        section6Desc,
                        contactBox,
                        socialLabel,
                        socialBox
                );

                Separator sep6 = new Separator();
                sep6.setPadding(new Insets(10, 0, 10, 0));

                // ================= FOOTER =================
                Label footerLabel = new Label("Privacy Policy Last Updated: Dec 31, 2023");
                footerLabel.setStyle(
                        "-fx-font-size: 12px;" +
                        "-fx-text-fill: #888888;" +
                        "-fx-font-style: italic;"
                );

                // ================= ADD ALL TO PRIVACY BOX =================
                privacyBox.getChildren().addAll(
                        privacyHeader,
                        section1Title,
                        section1Content,
                        sep1,
                        section2Title,
                        section2Content,
                        sep2,
                        section3Title,
                        section3Content,
                        sep3,
                        section4Title,
                        section4Content,
                        sep4,
                        section5Title,
                        section5Content,
                        sep5,
                        section6Title,
                        section6Content,
                        sep6,
                        footerLabel
                );

                return privacyBox;
        }

        private Label createPrivacySectionTitle(String text) {
                Label label = new Label(text);
                label.setStyle(
                        "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1a1a1a;" +
                        "-fx-padding: 10 0 5 0;"
                );
                return label;
        }

        private Label createPrivacyContentLabel(String text) {
                Label label = new Label(text);
                label.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #333333;" +
                        "-fx-wrap-text: true;"
                );
                label.setWrapText(true);
                return label;
        }
}
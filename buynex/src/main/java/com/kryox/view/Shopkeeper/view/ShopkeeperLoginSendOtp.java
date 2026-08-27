package com.kryox.view;

import com.kryox.Main;
import com.kryox.control.ConstantsMethods;
import com.kryox.control.ShopkeeperLogController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ShopkeeperLoginSendOtp {

        private static Scene sendOtpScene;

        public static Scene sendOtpScene() {

                // =========================================================
                // MAIN BORDERPANE
                // =========================================================

                BorderPane borderPane = new BorderPane();

                borderPane.setStyle(
                                "-fx-background-color: #F8FBF8;");

                // =========================================================
                // HEADER
                // =========================================================

                HBox headerBox = ViewConstants.loginHeader();

                borderPane.setTop(headerBox);

                // =========================================================
                // CENTER BACKGROUND
                // =========================================================

                StackPane centerPane = new StackPane();

                centerPane.setAlignment(
                                Pos.CENTER);

                centerPane.setPadding(
                                new Insets(12, 60, 12, 60));

                centerPane.setStyle(
                                "-fx-background-color: #F8FBF8;");

                // =========================================================
                // MAIN CARD
                // COMPRESSED OVERALL
                // =========================================================

                HBox mainCard = new HBox();

                mainCard.setAlignment(
                                Pos.CENTER);

                mainCard.setPrefWidth(900);
                mainCard.setPrefHeight(440);

                mainCard.setMaxWidth(900);
                mainCard.setMaxHeight(440);

                mainCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18;");

                // =========================================================
                // CARD SHADOW
                // =========================================================

                DropShadow cardShadow = new DropShadow();

                cardShadow.setRadius(18);

                cardShadow.setSpread(0.01);

                cardShadow.setOffsetX(0);

                cardShadow.setOffsetY(5);

                cardShadow.setColor(
                                Color.rgb(50, 40, 35, 0.13));

                mainCard.setEffect(
                                cardShadow);

                // =========================================================
                // LEFT SECTION
                // =========================================================

                VBox leftBox = new VBox();

                leftBox.setAlignment(
                                Pos.TOP_CENTER);

                leftBox.setSpacing(0);

                leftBox.setPrefWidth(510);

                leftBox.setMinWidth(510);

                leftBox.setMaxWidth(510);

                leftBox.setPrefHeight(440);

                leftBox.setMinHeight(440);

                leftBox.setMaxHeight(440);

                leftBox.setPadding(
                                new Insets(23, 38, 20, 38));

                leftBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 18 0 0 18;");

                // =========================================================
                // INNER FORM
                // =========================================================

                VBox formBox = new VBox();

                formBox.setAlignment(
                                Pos.TOP_LEFT);

                formBox.setSpacing(0);

                formBox.setPrefWidth(380);

                formBox.setMinWidth(380);

                formBox.setMaxWidth(380);

                // =========================================================
                // LOGO
                // =========================================================

                Label logoIcon = new Label("✦");

                logoIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                logoIcon.setTextFill(
                                Color.WHITE);

                StackPane logoIconBox = new StackPane();

                logoIconBox.setPrefWidth(38);

                logoIconBox.setPrefHeight(38);

                logoIconBox.setMaxWidth(38);

                logoIconBox.setMaxHeight(38);

                logoIconBox.setStyle(
                                "-fx-background-color: #B84D00;" +
                                                "-fx-background-radius: 9;");

                logoIconBox.getChildren().add(
                                logoIcon);

                Label logoText = new Label("BuyNeX");

                logoText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                23));

                logoText.setTextFill(
                                Color.web("#B84D00"));

                HBox logoBox = new HBox(8);

                logoBox.setAlignment(
                                Pos.CENTER_LEFT);

                logoBox.getChildren().addAll(
                                logoIconBox,
                                logoText);

                // =========================================================
                // TITLE
                // =========================================================

                Label resetPasswordLabel = new Label("Reset Password");

                resetPasswordLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.NORMAL,
                                                28));

                resetPasswordLabel.setTextFill(
                                Color.web("#111111"));

                resetPasswordLabel.setPadding(
                                new Insets(31, 0, 8, 0));

                // =========================================================
                // DESCRIPTION
                // =========================================================

                Label description = new Label(
                                "Enter your registered email address and we'll send you an\n" +
                                                "Link to reset your password.");

                description.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                description.setTextFill(
                                Color.web("#59483F"));

                description.setLineSpacing(2);

                description.setPadding(
                                new Insets(0, 0, 17, 0));

                // =========================================================
                // EMAIL ADDRESS LABEL
                // =========================================================

                Label mobileLabel = new Label("EMAIL ADDRESS");

                mobileLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

                mobileLabel.setTextFill(
                                Color.web("#55433B"));

                mobileLabel.setPadding(
                                new Insets(0, 0, 4, 2));

                // =========================================================
                // PHONE ICON
                // =========================================================

                Label phoneIcon = new Label("📧");

                phoneIcon.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                phoneIcon.setTextFill(
                                Color.web("#806F67"));

                // =========================================================
                // MOBILE TEXT FIELD
                // =========================================================

                TextField emailField = new TextField();

                emailField.setPromptText(
                                "buynex@gmail.com");

                emailField.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                emailField.setPrefHeight(36);

                emailField.setMinHeight(36);

                emailField.setMaxHeight(36);

                emailField.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0;");

                HBox.setHgrow(
                                emailField,
                                Priority.ALWAYS);

                // =========================================================
                // MOBILE FIELD BOX
                // =========================================================

                HBox mobileBox = new HBox(8);

                mobileBox.setAlignment(
                                Pos.CENTER_LEFT);

                mobileBox.setPrefWidth(380);

                mobileBox.setMinWidth(380);

                mobileBox.setMaxWidth(380);

                mobileBox.setPrefHeight(40);

                mobileBox.setMinHeight(40);

                mobileBox.setMaxHeight(40);

                mobileBox.setPadding(
                                new Insets(2, 10, 2, 12));

                mobileBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #E4C9BD;" +
                                                "-fx-border-width: 1.2;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;");

                // =========================================================
                // FIELD SHADOW
                // =========================================================

                DropShadow fieldShadow = new DropShadow();

                fieldShadow.setRadius(5);

                fieldShadow.setSpread(0.01);

                fieldShadow.setOffsetY(1);

                fieldShadow.setColor(
                                Color.rgb(80, 50, 35, 0.07));

                mobileBox.setEffect(
                                fieldShadow);

                mobileBox.getChildren().addAll(
                                phoneIcon,
                                emailField);

                // =========================================================
                // SEND OTP BUTTON
                // =========================================================

                Button sendOtpButton = new Button(
                                "Send Link     →");

                sendOtpButton.setPrefWidth(380);

                sendOtpButton.setMinWidth(380);

                sendOtpButton.setMaxWidth(380);

                sendOtpButton.setPrefHeight(40);

                sendOtpButton.setMinHeight(40);

                sendOtpButton.setMaxHeight(40);

                sendOtpButton.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                sendOtpButton.setTextFill(
                                Color.WHITE);

                sendOtpButton.setStyle(
                                "-fx-background-color: #B84D00;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-cursor: hand;");

                // =========================================================
                // BUTTON SHADOW
                // =========================================================

                DropShadow buttonShadow = new DropShadow();

                buttonShadow.setRadius(7);

                buttonShadow.setSpread(0.01);

                buttonShadow.setOffsetY(2);

                buttonShadow.setColor(
                                Color.rgb(120, 55, 15, 0.20));

                sendOtpButton.setEffect(
                                buttonShadow);

                // =========================================================
                // BUTTON HOVER
                // =========================================================

                sendOtpButton.setOnMouseEntered(e -> {

                        sendOtpButton.setStyle(
                                        "-fx-background-color: #9F4100;" +
                                                        "-fx-background-radius: 8;" +
                                                        "-fx-border-radius: 8;" +
                                                        "-fx-cursor: hand;");

                });

                sendOtpButton.setOnMouseExited(e -> {

                        sendOtpButton.setStyle(
                                        "-fx-background-color: #B84D00;" +
                                                        "-fx-background-radius: 8;" +
                                                        "-fx-border-radius: 8;" +
                                                        "-fx-cursor: hand;");

                });

                // =========================================================
                // SEND OTP ACTION
                // =========================================================

                sendOtpButton.setOnAction(e -> {

                        System.out.println("Send Link button clicked");
                        String email = emailField.getText().trim();
                        if (email == null ||
                                        email.trim().isEmpty()) {
                                ConstantsMethods.showAlert(
                                                Alert.AlertType.WARNING,
                                                "Email Required",
                                                "Please enter your email address.");
                                emailField.requestFocus();
                                return;
                        }
                        if (!ConstantsMethods.isValidEmail(email)) {
                                ConstantsMethods.showAlert(
                                                Alert.AlertType.WARNING,
                                                "Invalid Email",
                                                "Please enter a valid email address.");
                                emailField.requestFocus();
                                return;
                        }
                        ShopkeeperLogController.sendChangePasswordLink(email, sendOtpButton);
                        sendOtpButton.setDisable(true);
                        sendOtpButton.setText("Sending...");

                });

                VBox.setMargin(
                                sendOtpButton,
                                new Insets(12, 0, 0, 0));

                // =========================================================
                // BACK TO LOGIN
                // =========================================================

                Button backToLogin = new Button(
                                "←  Back to Login");

                backToLogin.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                backToLogin.setTextFill(
                                Color.web("#59483F"));

                backToLogin.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-padding: 0;" +
                                                "-fx-cursor: hand;");

                backToLogin.setOnMouseEntered(e -> backToLogin.setTextFill(
                                Color.web("#A94600")));

                backToLogin.setOnMouseExited(e -> backToLogin.setTextFill(
                                Color.web("#59483F")));

                backToLogin.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperLogin.loginscene());

                });

                HBox backBox = new HBox();

                backBox.setAlignment(
                                Pos.CENTER);

                backBox.setPrefWidth(380);

                backBox.setMinWidth(380);

                backBox.setMaxWidth(380);

                backBox.setPadding(
                                new Insets(9, 0, 0, 0));

                backBox.getChildren().add(
                                backToLogin);

                // =========================================================
                // ADD CONTENT TO FORM
                // =========================================================

                formBox.getChildren().addAll(
                                logoBox,
                                resetPasswordLabel,
                                description,
                                mobileLabel,
                                mobileBox,
                                sendOtpButton,
                                backBox);

                // =========================================================
                // ADD FORM TO LEFT SECTION
                // =========================================================

                leftBox.getChildren().add(
                                formBox);

                // =========================================================
                // RIGHT IMAGE SECTION
                // =========================================================

                StackPane rightPane = new StackPane();

                rightPane.setPrefWidth(390);

                rightPane.setMinWidth(390);

                rightPane.setMaxWidth(390);

                rightPane.setPrefHeight(440);

                rightPane.setMinHeight(440);

                rightPane.setMaxHeight(440);

                // =========================================================
                // IMAGE
                // =========================================================

                Image image = new Image(
                                "/assets/images/Login.png");

                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(390);

                imageView.setFitHeight(440);

                imageView.setPreserveRatio(false);

                imageView.setSmooth(true);

                // =========================================================
                // IMAGE CLIP
                // =========================================================

                Rectangle imageClip = new Rectangle(
                                390,
                                440);

                imageClip.setArcWidth(28);

                imageClip.setArcHeight(28);

                imageView.setClip(
                                imageClip);

                // =========================================================
                // DARK OVERLAY
                // =========================================================

                Rectangle darkOverlay = new Rectangle(
                                390,
                                440);

                darkOverlay.setFill(
                                Color.rgb(0, 0, 0, 0.38));

                darkOverlay.setArcWidth(28);

                darkOverlay.setArcHeight(28);

                // =========================================================
                // RIGHT CONTENT
                // =========================================================

                VBox rightContent = new VBox();

                rightContent.setAlignment(
                                Pos.BOTTOM_LEFT);

                rightContent.setSpacing(0);

                rightContent.setPrefWidth(390);

                rightContent.setPrefHeight(440);

                rightContent.setPadding(
                                new Insets(0, 32, 38, 32));

                // =========================================================
                // MERCHANT PORTAL BADGE
                // =========================================================

                Label badgeDot = new Label("●");

                badgeDot.setFont(
                                Font.font(
                                                "Arial",
                                                9));

                badgeDot.setTextFill(
                                Color.web("#B84D00"));

                Label badgeText = new Label(
                                "MERCHANT PORTAL");

                badgeText.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                badgeText.setTextFill(
                                Color.WHITE);

                HBox badge = new HBox(6);

                badge.setAlignment(
                                Pos.CENTER_LEFT);

                badge.setPadding(
                                new Insets(7, 12, 7, 12));

                badge.setStyle(
                                "-fx-background-color: rgba(50,40,35,0.65);" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: rgba(255,255,255,0.20);" +
                                                "-fx-border-width: 1;" +
                                                "-fx-border-radius: 20;");

                badge.getChildren().addAll(
                                badgeDot,
                                badgeText);

                // =========================================================
                // BUSINESS TITLE
                // =========================================================

                Label businessTitle = new Label(
                                "Empower Your Business.");

                businessTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                27));

                businessTitle.setTextFill(
                                Color.WHITE);

                businessTitle.setWrapText(true);

                businessTitle.setPadding(
                                new Insets(18, 0, 11, 0));

                // =========================================================
                // BUSINESS DESCRIPTION
                // =========================================================

                Label businessDescription = new Label(
                                "Secure, seamless access to your hyperlocal shopping\n" +
                                                "ecosystem.");

                businessDescription.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                businessDescription.setTextFill(
                                Color.WHITE);

                businessDescription.setOpacity(0.90);

                businessDescription.setLineSpacing(3);

                businessDescription.setTextAlignment(
                                TextAlignment.LEFT);

                // =========================================================
                // ADD RIGHT CONTENT
                // =========================================================

                rightContent.getChildren().addAll(
                                badge,
                                businessTitle,
                                businessDescription);

                // =========================================================
                // RIGHT LAYERS
                // =========================================================

                rightPane.getChildren().addAll(
                                imageView,
                                darkOverlay,
                                rightContent);

                // =========================================================
                // ADD LEFT + RIGHT
                // =========================================================

                mainCard.getChildren().addAll(
                                leftBox,
                                rightPane);

                // =========================================================
                // ADD CARD TO CENTER
                // =========================================================

                centerPane.getChildren().add(
                                mainCard);

                borderPane.setCenter(
                                centerPane);

                // =========================================================
                // FOOTER
                // SAME AS EXISTING CODE
                // =========================================================

                VBox footerBox = ViewConstants.loginFooter();

                borderPane.setBottom(
                                footerBox);

                // =========================================================
                // SCENE
                // =========================================================

                sendOtpScene = new Scene(
                                borderPane,
                                1280,
                                650);

                sendOtpScene.setFill(
                                Color.web("#F8FBF8"));

                return sendOtpScene;
        }
}
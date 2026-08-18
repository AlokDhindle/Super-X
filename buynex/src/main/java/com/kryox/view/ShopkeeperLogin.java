package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ShopkeeperLogin {

    private static Scene loginScene;

    public static Scene loginscene() {

        // =========================================================
        // MAIN BORDERPANE
        // =========================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8FBF8;"
        );


        // =========================================================
        // HEADER
        // SAME AS EXISTING CODE
        // =========================================================

        HBox headerBox = Constants.loginHeader();

        borderPane.setTop(headerBox);


        // =========================================================
        // CENTER BACKGROUND
        // =========================================================

        StackPane centerPane = new StackPane();

        centerPane.setAlignment(
                Pos.CENTER
        );

        centerPane.setPadding(
                new Insets(12, 60, 12, 60)
        );

        centerPane.setStyle(
                "-fx-background-color: #F8FBF8;"
        );


        // =========================================================
        // MAIN LOGIN CARD
        // SAME SIZE AS SEND OTP
        // =========================================================

        HBox loginCard = new HBox();

        loginCard.setAlignment(
                Pos.CENTER
        );

        loginCard.setPrefWidth(900);

        loginCard.setPrefHeight(440);

        loginCard.setMaxWidth(900);

        loginCard.setMaxHeight(440);

        loginCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;"
        );


        // =========================================================
        // CARD SHADOW
        // =========================================================

        DropShadow cardShadow =
                new DropShadow();

        cardShadow.setRadius(18);

        cardShadow.setSpread(0.01);

        cardShadow.setOffsetX(0);

        cardShadow.setOffsetY(5);

        cardShadow.setColor(
                Color.rgb(50, 40, 35, 0.13)
        );

        loginCard.setEffect(
                cardShadow
        );


        // =========================================================
        // LEFT LOGIN SECTION
        // =========================================================

        VBox leftBox =
                new VBox();

        leftBox.setAlignment(
                Pos.CENTER_LEFT
        );

        leftBox.setSpacing(0);

        leftBox.setPrefWidth(510);

        leftBox.setMinWidth(510);

        leftBox.setMaxWidth(510);

        leftBox.setPrefHeight(440);

        leftBox.setMinHeight(440);

        leftBox.setMaxHeight(440);

        leftBox.setPadding(
                new Insets(20, 38, 18, 38)
        );

        leftBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18 0 0 18;"
        );


        // =========================================================
        // INNER LOGIN CONTENT
        // =========================================================

        VBox loginContent =
                new VBox();

        loginContent.setAlignment(
                Pos.TOP_LEFT
        );

        loginContent.setSpacing(0);

        loginContent.setPrefWidth(434);

        loginContent.setMinWidth(434);

        loginContent.setMaxWidth(434);


        // =========================================================
        // LOGO
        // =========================================================

        Label logoIcon =
                new Label("✦");

        logoIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        logoIcon.setTextFill(
                Color.web("#F28C28")
        );


        Label logoText =
                new Label("BuyNeX");

        logoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        logoText.setTextFill(
                Color.web("#111111")
        );


        HBox logoBox =
                new HBox(2);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.getChildren().addAll(
                logoIcon,
                logoText
        );


        // =========================================================
        // WELCOME BACK
        // =========================================================

        Label welcomeLabel =
                new Label("Welcome Back");

        welcomeLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        29
                )
        );

        welcomeLabel.setTextFill(
                Color.web("#111111")
        );

        welcomeLabel.setPadding(
                new Insets(12, 0, 2, 0)
        );


        // =========================================================
        // SUBTITLE
        // =========================================================

        Label subtitle =
                new Label(
                        "Log in to manage your store and orders."
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        subtitle.setTextFill(
                Color.web("#59483F")
        );

        subtitle.setPadding(
                new Insets(0, 0, 14, 0)
        );


        // =========================================================
        // MOBILE NUMBER LABEL
        // =========================================================

        Label mobileLabel =
                new Label("MOBILE NUMBER");

        mobileLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        mobileLabel.setTextFill(
                Color.web("#55433B")
        );


        // =========================================================
        // PHONE ICON
        // =========================================================

        Label phoneIcon =
                new Label("📞");

        phoneIcon.setFont(
                Font.font(
                        "Arial",
                        17
                )
        );

        phoneIcon.setTextFill(
                Color.web("#806F67")
        );


        // =========================================================
        // MOBILE FIELD
        // =========================================================

        TextField mobileField =
                new TextField();

        mobileField.setPromptText(
                "+91 00000-00000"
        );

        mobileField.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        mobileField.setPrefHeight(38);

        mobileField.setMinHeight(38);

        mobileField.setMaxHeight(38);

        mobileField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;"
        );

        HBox.setHgrow(
                mobileField,
                Priority.ALWAYS
        );


        // =========================================================
        // MOBILE BOX
        // =========================================================

        HBox mobileBox =
                new HBox(8);

        mobileBox.setAlignment(
                Pos.CENTER_LEFT
        );

        mobileBox.setPrefWidth(434);

        mobileBox.setMinWidth(434);

        mobileBox.setMaxWidth(434);

        mobileBox.setPrefHeight(40);

        mobileBox.setMinHeight(40);

        mobileBox.setMaxHeight(40);

        mobileBox.setPadding(
                new Insets(3, 10, 3, 11)
        );

        mobileBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E4C9BD;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        // =========================================================
        // FIELD SHADOW
        // =========================================================

        DropShadow fieldShadow =
                new DropShadow();

        fieldShadow.setRadius(5);

        fieldShadow.setSpread(0.01);

        fieldShadow.setOffsetY(1);

        fieldShadow.setColor(
                Color.rgb(80, 50, 35, 0.07)
        );

        mobileBox.setEffect(
                fieldShadow
        );


        mobileBox.getChildren().addAll(
                phoneIcon,
                mobileField
        );


        // =========================================================
        // PASSWORD TITLE
        // =========================================================

        Label passwordLabel =
                new Label("PASSWORD");

        passwordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        passwordLabel.setTextFill(
                Color.web("#55433B")
        );


        // =========================================================
        // FORGOT PASSWORD
        // =========================================================

        Button forgotPassword =
                new Button(
                        "Forgot Password?"
                );

        forgotPassword.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        forgotPassword.setTextFill(
                Color.web("#A94600")
        );

        forgotPassword.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );


        forgotPassword.setOnAction(e -> {

            System.out.println(
                    "Forgot Password button clicked"
            );
            

            Main.primaryStage.setScene(
                    ShopkeeperLoginSendOtp.sendOtpScene()
            );

        });


        Region passwordSpacer =
                new Region();

        HBox.setHgrow(
                passwordSpacer,
                Priority.ALWAYS
        );


        HBox passwordTitleBox =
                new HBox();

        passwordTitleBox.setAlignment(
                Pos.CENTER_LEFT
        );

        passwordTitleBox.setPadding(
                new Insets(10, 0, 4, 0)
        );

        passwordTitleBox.getChildren().addAll(
                passwordLabel,
                passwordSpacer,
                forgotPassword
        );


        // =========================================================
        // PASSWORD ICON
        // =========================================================

        Label lockIcon =
                new Label("🔐");

        lockIcon.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        lockIcon.setTextFill(
                Color.web("#806F67")
        );


        // =========================================================
        // PASSWORD FIELD
        // =========================================================

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Password"
        );

        passwordField.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        passwordField.setPrefHeight(38);

        passwordField.setMinHeight(38);

        passwordField.setMaxHeight(38);

        passwordField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;"
        );

        HBox.setHgrow(
                passwordField,
                Priority.ALWAYS
        );



        // =========================================================
        // PASSWORD BOX
        // =========================================================

        HBox passwordBox =
                new HBox(8);

        passwordBox.setAlignment(
                Pos.CENTER_LEFT
        );

        passwordBox.setPrefWidth(434);

        passwordBox.setMinWidth(434);

        passwordBox.setMaxWidth(434);

        passwordBox.setPrefHeight(40);

        passwordBox.setMinHeight(40);

        passwordBox.setMaxHeight(40);

        passwordBox.setPadding(
                new Insets(3, 10, 3, 11)
        );

        passwordBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E4C9BD;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        passwordBox.setEffect(
                fieldShadow
        );

        passwordBox.getChildren().addAll(
                lockIcon,
                passwordField
        );


        // =========================================================
        // LOGIN BUTTON
        // =========================================================

        Button loginButton =
                new Button(
                        "Login to Dashboard  →"
                );

        loginButton.setPrefWidth(434);

        loginButton.setMinWidth(434);

        loginButton.setMaxWidth(434);

        loginButton.setPrefHeight(40);

        loginButton.setMinHeight(40);

        loginButton.setMaxHeight(40);

        loginButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        loginButton.setTextFill(
                Color.WHITE
        );

        loginButton.setStyle(
                "-fx-background-color: #B84D00;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;" +
                "-fx-cursor: hand;"
        );


        // =========================================================
        // LOGIN BUTTON SHADOW
        // =========================================================

        DropShadow buttonShadow =
                new DropShadow();

        buttonShadow.setRadius(7);

        buttonShadow.setSpread(0.01);

        buttonShadow.setOffsetY(2);

        buttonShadow.setColor(
                Color.rgb(120, 55, 15, 0.20)
        );

        loginButton.setEffect(
                buttonShadow
        );


        loginButton.setOnMouseEntered(e -> {

            loginButton.setStyle(
                    "-fx-background-color: #9F4100;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-radius: 8;" +
                    "-fx-cursor: hand;"
            );

        });


        loginButton.setOnMouseExited(e -> {

            loginButton.setStyle(
                    "-fx-background-color: #B84D00;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-radius: 8;" +
                    "-fx-cursor: hand;"
            );

        });


        loginButton.setOnAction(e -> {

            System.out.println(
                    "Login button clicked"
            );

            Main.primaryStage.setScene(
                    ShopkeeperDashboard.dashboardScene()
            );

        });


        VBox.setMargin(
                loginButton,
                new Insets(12, 0, 0, 0)
        );


        // =========================================================
        // OR
        // =========================================================

        Label orLabel =
                new Label("OR");

        orLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );

        orLabel.setTextFill(
                Color.web("#806F67")
        );


        HBox orBox =
                new HBox();

        orBox.setAlignment(
                Pos.CENTER
        );

        orBox.setPrefWidth(434);

        orBox.setPadding(
                new Insets(7, 0, 7, 0)
        );

        orBox.getChildren().add(
                orLabel
        );


        // =========================================================
        // GOOGLE ICON
        // =========================================================

        Label googleIcon =
                new Label("G");

        googleIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        googleIcon.setTextFill(
                Color.web("#4285F4")
        );


        // =========================================================
        // GOOGLE TEXT
        // =========================================================

        Label googleText =
                new Label(
                        "Sign in with Google"
                );

        googleText.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        googleText.setTextFill(
                Color.web("#222222")
        );


        HBox googleContent =
                new HBox(8);

        googleContent.setAlignment(
                Pos.CENTER
        );

        googleContent.getChildren().addAll(
                googleIcon,
                googleText
        );


        // =========================================================
        // GOOGLE BUTTON
        // =========================================================

        Button googleButton =
                new Button();

        googleButton.setGraphic(
                googleContent
        );

        googleButton.setPrefWidth(434);

        googleButton.setMinWidth(434);

        googleButton.setMaxWidth(434);

        googleButton.setPrefHeight(39);

        googleButton.setMinHeight(39);

        googleButton.setMaxHeight(39);

        googleButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E4C9BD;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        googleButton.setEffect(
                fieldShadow
        );


        // =========================================================
        // REGISTER TEXT
        // =========================================================

        Label accountText =
                new Label(
                        "Don't have an account?"
                );

        accountText.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        accountText.setTextFill(
                Color.web("#59483F")
        );


        // =========================================================
        // REGISTER BUTTON
        // =========================================================

        Button registerButton =
                new Button(
                        "Register"
                );

        registerButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        registerButton.setTextFill(
                Color.web("#A94600")
        );

        registerButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0 0 0 4;" +
                "-fx-cursor: hand;"
        );


        registerButton.setOnMouseEntered(e ->
                registerButton.setTextFill(
                        Color.web("#7F3500")
                )
        );


        registerButton.setOnMouseExited(e ->
                registerButton.setTextFill(
                        Color.web("#A94600")
                )
        );


        registerButton.setOnAction(e -> {

            System.out.println(
                    "Register button clicked"
            );

            Main.primaryStage.setScene(
                    ShopkeeperRegistration.loginscene()
            );

        });


        HBox registerBox =
                new HBox();

        registerBox.setAlignment(
                Pos.CENTER
        );

        registerBox.setPrefWidth(434);

        registerBox.setPadding(
                new Insets(7, 0, 0, 0)
        );

        registerBox.getChildren().addAll(
                accountText,
                registerButton
        );


        // =========================================================
        // ADD LOGIN CONTENT
        // =========================================================

        loginContent.getChildren().addAll(
                logoBox,
                welcomeLabel,
                subtitle,
                mobileLabel,
                mobileBox,
                passwordTitleBox,
                passwordBox,
                loginButton,
                orBox,
                googleButton,
                registerBox
        );


        leftBox.getChildren().add(
                loginContent
        );


        // =========================================================
        // RIGHT SECTION
        // SAME SIZE AS SEND OTP
        // =========================================================

        VBox rightBox =
                new VBox();

        rightBox.setAlignment(
                Pos.CENTER
        );

        rightBox.setSpacing(0);

        rightBox.setPrefWidth(390);

        rightBox.setMinWidth(390);

        rightBox.setMaxWidth(390);

        rightBox.setPrefHeight(440);

        rightBox.setMinHeight(440);

        rightBox.setMaxHeight(440);

        rightBox.setPadding(
                new Insets(25, 30, 25, 30)
        );

        rightBox.setStyle(
                "-fx-background-color: #FFF0E8;" +
                "-fx-background-radius: 0 18 18 0;"
        );


        // =========================================================
        // IMAGE
        // =========================================================

        Image image =
                new Image(
                        "/assets/images/Login.png"
                );


        ImageView imageView =
                new ImageView(image);

        imageView.setFitWidth(300);

        imageView.setFitHeight(200);

        imageView.setPreserveRatio(false);

        imageView.setSmooth(true);


        // =========================================================
        // IMAGE ROUNDED CORNERS
        // =========================================================

        Rectangle imageClip =
                new Rectangle(
                        300,
                        200
                );

        imageClip.setArcWidth(22);

        imageClip.setArcHeight(22);

        imageView.setClip(
                imageClip
        );


        // =========================================================
        // IMAGE CARD
        // =========================================================

        StackPane imageCard =
                new StackPane();

        imageCard.setPrefWidth(300);

        imageCard.setPrefHeight(200);

        imageCard.setMaxWidth(300);

        imageCard.setMaxHeight(200);

        imageCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;"
        );


        // =========================================================
        // IMAGE SHADOW
        // =========================================================

        DropShadow imageShadow =
                new DropShadow();

        imageShadow.setRadius(12);

        imageShadow.setSpread(0.01);

        imageShadow.setOffsetY(5);

        imageShadow.setColor(
                Color.rgb(60, 40, 30, 0.18)
        );

        imageCard.setEffect(
                imageShadow
        );


        imageCard.getChildren().add(
                imageView
        );


        // =========================================================
        // RIGHT TITLE
        // =========================================================

        Label businessTitle =
                new Label(
                        "Back to Business"
                );

        businessTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        businessTitle.setTextFill(
                Color.web("#C65B24")
        );

        businessTitle.setTextAlignment(
                TextAlignment.CENTER
        );

        businessTitle.setAlignment(
                Pos.CENTER
        );

        businessTitle.setPadding(
                new Insets(12, 0, 3, 0)
        );


        // =========================================================
        // RIGHT DESCRIPTION
        // =========================================================

        Label businessDescription =
                new Label(
                        "Access your real-time analytics, manage inventory,\n" +
                        "and grow your local footprint effortlessly."
                );

        businessDescription.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        businessDescription.setTextFill(
                Color.web("#76584B")
        );

        businessDescription.setTextAlignment(
                TextAlignment.CENTER
        );

        businessDescription.setAlignment(
                Pos.CENTER
        );


        // =========================================================
        // ADD RIGHT CONTENT
        // =========================================================

        rightBox.getChildren().addAll(
                imageCard,
                businessTitle,
                businessDescription
        );


        // =========================================================
        // ADD LEFT + RIGHT
        // =========================================================

        loginCard.getChildren().addAll(
                leftBox,
                rightBox
        );


        // =========================================================
        // ADD CARD TO CENTER
        // =========================================================

        centerPane.getChildren().add(
                loginCard
        );

        borderPane.setCenter(
                centerPane
        );


        // =========================================================
        // FOOTER
        // SAME AS EXISTING CODE
        // =========================================================

        VBox footerBox =
                Constants.loginFooter();

        borderPane.setBottom(
                footerBox
        );


        // =========================================================
        // SCENE
        // =========================================================

        loginScene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        loginScene.setFill(
                Color.web("#F8FBF8")
        );

        return loginScene;
    }
}
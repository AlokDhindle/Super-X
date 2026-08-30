package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ShopkeeperRegistration {

    private static Scene shopkeeperScene;

    public static Scene loginscene() {

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8FBF8;");

        // =========================================================
        // HEADER
        // =========================================================

        HBox headerMainBox = ViewConstants.loginHeader();

        borderPane.setTop(headerMainBox);

        // =========================================================
        // PAGE INTRODUCTION
        // =========================================================

        Text shopPartnerText1 = new Text(
                "Become a BuyNeX Shop Partner");

        shopPartnerText1.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #252525;");

        Text shopPartnerText2 = new Text(
                "Register your local shop and start reaching nearby customers.");

        shopPartnerText2.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #795548;");

        VBox introVBox1 = new VBox(
                5,
                shopPartnerText1,
                shopPartnerText2);

        introVBox1.setAlignment(
                Pos.CENTER_LEFT);

        Text stepsText1 = new Text(
                "Step 1 of 2");

        stepsText1.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #A83B16;");

        HBox stepsText1Box = new HBox(
                stepsText1);

        stepsText1Box.setAlignment(
                Pos.CENTER_RIGHT);

        HBox introHBox1 = new HBox(
                introVBox1,
                stepsText1Box);

        HBox.setHgrow(
                introVBox1,
                Priority.ALWAYS);

        HBox.setHgrow(
                stepsText1Box,
                Priority.ALWAYS);

        introHBox1.setAlignment(
                Pos.CENTER);

        introHBox1.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        introHBox1.setMaxHeight(
                46);

        introHBox1.setMaxWidth(
                800);

        // =========================================================
        // PROGRESS LINE
        // =========================================================

        HBox progressMainBox = new HBox();

        progressMainBox.setMaxHeight(
                6);

        progressMainBox.setMaxWidth(
                800);

        progressMainBox.setStyle(
                "-fx-background-color: #E9E2E0;" +
                        "-fx-background-radius: 5px;");

        HBox progressCompleted = new HBox();

        progressCompleted.setPrefWidth(
                400);

        progressCompleted.setPrefHeight(
                6);

        progressCompleted.setStyle(
                "-fx-background-color: linear-gradient(to right, #A52B08, #FF6A00);" +
                        "-fx-background-radius: 5px;");

        progressMainBox.getChildren().add(
                progressCompleted);

        VBox pageIntroBox = new VBox(
                7,
                introHBox1,
                progressMainBox);

        pageIntroBox.setAlignment(
                Pos.CENTER);

        pageIntroBox.setPrefWidth(
                800);

        // =========================================================
        // RIGHT IMAGE
        // =========================================================

        Image image = new Image(
                "assets\\images\\ShopKeeperLogin.png");

        ImageView imageView = new ImageView(
                image);

        imageView.setFitWidth(
                250);

        imageView.setFitHeight(
                230);

        imageView.setPreserveRatio(
                false);

        Rectangle imageClip = new Rectangle();

        imageClip.setWidth(
                250);

        imageClip.setHeight(
                230);

        imageClip.setArcWidth(
                20);

        imageClip.setArcHeight(
                20);

        imageView.setClip(
                imageClip);

        // =========================================================
        // RIGHT IMAGE TEXT
        // =========================================================

        Text belowImageText1 = new Text(
                "Empowering Local Business");

        belowImageText1.setStyle(
                "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #D86B35;");

        Text belowImageText2 = new Text(
                "Join 5000+ shops already thriving with\n" +
                        "BuyNeX's hyperlocal delivery network.");

        belowImageText2.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #795548;");

        belowImageText2.setTextAlignment(
                TextAlignment.CENTER);

        VBox introVBox3 = new VBox(
                12,
                imageView,
                belowImageText1,
                belowImageText2);

        introVBox3.setAlignment(
                Pos.CENTER);

        introVBox3.setPrefWidth(
                400);

        introVBox3.setPrefHeight(
                400);

        introVBox3.setPadding(
                new Insets(
                        15,
                        20,
                        15,
                        20));

        introVBox3.setStyle(
                "-fx-background-color: #FFF0E9;" +
                        "-fx-background-radius: 0 20px 20px 0;");

        // =========================================================
        // EMAIL ADDRESS
        // =========================================================

        Text text2 = new Text(
                "Email Address *");

        text2.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #654B44;");

        TextField emailAddress = new TextField();

        emailAddress.setPromptText(
                "✉  example@buynex.com");

        emailAddress.setPrefWidth(
                260);

        emailAddress.setPrefHeight(
                33);

        emailAddress.setStyle(
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
        // VERIFY EMAIL BUTTON
        // =========================================================

        Button verifyButton = new Button(
                "Verify");

        verifyButton.setPrefWidth(
                80);

        verifyButton.setPrefHeight(
                33);

        verifyButton.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-text-fill: #A83B16;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-border-color: #A83B16;" +
                        "-fx-border-width: 1.5px;" +
                        "-fx-border-radius: 9px;" +
                        "-fx-background-radius: 9px;" +
                        "-fx-cursor: hand;");

        HBox emailBox = new HBox(
                8,
                emailAddress,
                verifyButton);

        emailBox.setAlignment(
                Pos.CENTER_LEFT);

        // =========================================================
        // PASSWORD
        // =========================================================

        Text text3 = new Text(
                "Password *");

        text3.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #654B44;");

        PasswordField password = new PasswordField();

        password.setPromptText(
                "🔐  Enter password");

        password.setPrefWidth(
                350);

        password.setPrefHeight(
                33);

        password.setStyle(
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
        // CONFIRM PASSWORD
        // =========================================================

        Text text4 = new Text(
                "Confirm Password *");

        text4.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #654B44;");

        PasswordField confirmPassword = new PasswordField();

        confirmPassword.setPromptText(
                "🔐  Enter password");

        confirmPassword.setPrefWidth(
                350);

        confirmPassword.setPrefHeight(
                33);

        confirmPassword.setStyle(
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
        // NEXT BUTTON
        // =========================================================

        Button nextButton = new Button(
                "Next  →");

        nextButton.setPrefWidth(
                120);

        nextButton.setPrefHeight(
                33);

        nextButton.setDisable(
                true);

        nextButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #B84208, #F36A00);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-background-radius: 9px;" +
                        "-fx-border-radius: 9px;" +
                        "-fx-cursor: hand;");

        HBox nextButtonBox = new HBox(
                nextButton);

        nextButtonBox.setAlignment(
                Pos.CENTER_RIGHT);

        // =========================================================
        // REGISTER BUTTON ACTION
        // =========================================================

        verifyButton.setOnAction(e -> {

            String emailValue =
                    emailAddress.getText().trim();

            String passwordValue =
                    password.getText();

            String confirmPasswordValue =
                    confirmPassword.getText();

            ShopkeeperLogController.signupShopkeeper(
                    emailValue,
                    passwordValue,
                    confirmPasswordValue,
                    verifyButton,
                    nextButton);
        });

        // =========================================================
        // NEXT BUTTON ACTION
        // =========================================================

        nextButton.setOnAction(e -> {

            String emailValue =
                    emailAddress.getText().trim();

            String passwordValue =
                    password.getText();

            String confirmPasswordValue =
                    confirmPassword.getText();

            ShopkeeperLogController.continueRegistration(
                    emailValue,
                    passwordValue,
                    confirmPasswordValue);
        });

        // =========================================================
        // BACK TO LOGIN BUTTON
        // =========================================================

        Button backToLoginButton = new Button(
                "←  Back to Login");

        backToLoginButton.setPrefHeight(
                30);

        backToLoginButton.setFont(
                Font.font(
                        "Arial",
                        12));

        backToLoginButton.setTextFill(
                Color.web("#795548"));

        backToLoginButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: transparent;" +
                        "-fx-padding: 0;" +
                        "-fx-cursor: hand;");

        backToLoginButton.setOnMouseEntered(e -> {

            backToLoginButton.setTextFill(
                    Color.web("#A83B16"));
        });

        backToLoginButton.setOnMouseExited(e -> {

            backToLoginButton.setTextFill(
                    Color.web("#795548"));
        });

        backToLoginButton.setOnAction(e -> {

            System.out.println(
                    "Back to login button clicked");

            ShopkeeperLogController.resetRegistration();

            Homepage.HomepageStage.setScene(
                    ShopkeeperLogin.loginscene());
        });

        // =========================================================
        // BACK BUTTON BOX
        // =========================================================

        HBox backToLoginBox = new HBox(
                backToLoginButton);

        backToLoginBox.setAlignment(
                Pos.CENTER);

        backToLoginBox.setPadding(
                new Insets(
                        0,
                        0,
                        3,
                        0));

        // =========================================================
        // SPACER
        // =========================================================

        Region backSpacer = new Region();

        VBox.setVgrow(
                backSpacer,
                Priority.ALWAYS);

        // =========================================================
        // FORM
        // =========================================================

        VBox getInfoBox = new VBox(
                5,
                text2,
                emailBox,
                text3,
                password,
                text4,
                confirmPassword,
                nextButtonBox,
                backSpacer,
                backToLoginBox);

        getInfoBox.setAlignment(
                Pos.TOP_LEFT);

        getInfoBox.setMaxWidth(
                400);

        getInfoBox.setPrefHeight(
                400);

        getInfoBox.setMinHeight(
                400);

        getInfoBox.setMaxHeight(
                400);

        getInfoBox.setPadding(
                new Insets(
                        18,
                        30,
                        12,
                        30));

        getInfoBox.setStyle(
                "-fx-background-color: #FFFFFF;");

        // =========================================================
        // LEFT SIDE
        // =========================================================

        VBox introVBox2 = new VBox(
                getInfoBox);

        introVBox2.setAlignment(
                Pos.CENTER);

        introVBox2.setPrefWidth(
                400);

        introVBox2.setPrefHeight(
                400);

        introVBox2.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 20px 0 0 20px;");

        // =========================================================
        // MAIN FORM BOX
        // =========================================================

        HBox inputInfoHBox1 = new HBox(
                introVBox2,
                introVBox3);

        inputInfoHBox1.setPrefWidth(
                800);

        inputInfoHBox1.setPrefHeight(
                400);

        inputInfoHBox1.setAlignment(
                Pos.CENTER);

        // =========================================================
        // SHADOW
        // =========================================================

        DropShadow shadow = new DropShadow();

        shadow.setRadius(
                18);

        shadow.setSpread(
                0.05);

        shadow.setOffsetX(
                0);

        shadow.setOffsetY(
                0);

        shadow.setColor(
                Color.rgb(
                        80,
                        50,
                        40,
                        0.15));

        inputInfoHBox1.setEffect(
                shadow);

        // =========================================================
        // FEATURE 1
        // =========================================================

        Text featureIcon1 = new Text(
                "ϟ");

        featureIcon1.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #C95016;");

        Text featureText1 = new Text(
                "Instant Setup");

        featureText1.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #252525;");

        Text featureSubText1 = new Text(
                "Go live in under 24 hours.");

        featureSubText1.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-fill: #777777;");

        VBox featureTextBox1 = new VBox(
                3,
                featureText1,
                featureSubText1);

        HBox featureBox1 = new HBox(
                12,
                featureIcon1,
                featureTextBox1);

        featureBox1.setAlignment(
                Pos.CENTER_LEFT);

        featureBox1.setPadding(
                new Insets(10));

        featureBox1.setPrefWidth(
                150);

        featureBox1.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #F0E4DE;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;");

        // =========================================================
        // FEATURE 2
        // =========================================================

        Text featureIcon2 = new Text(
                "▣");

        featureIcon2.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-fill: #C95016;");

        Text featureText2 = new Text(
                "Daily Payouts");

        featureText2.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #252525;");

        Text featureSubText2 = new Text(
                "Get money in your bank daily.");

        featureSubText2.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-fill: #777777;");

        VBox featureTextBox2 = new VBox(
                3,
                featureText2,
                featureSubText2);

        HBox featureBox2 = new HBox(
                12,
                featureIcon2,
                featureTextBox2);

        featureBox2.setAlignment(
                Pos.CENTER_LEFT);

        featureBox2.setPadding(
                new Insets(10));

        featureBox2.setPrefWidth(
                150);

        featureBox2.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #F0E4DE;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;");

        // =========================================================
        // FEATURE 3
        // =========================================================

        Text featureIcon3 = new Text(
                "⌁");

        featureIcon3.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-fill: #C95016;");

        Text featureText3 = new Text(
                "AI Insights");

        featureText3.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-fill: #252525;");

        Text featureSubText3 = new Text(
                "Know what's trending nearby.");

        featureSubText3.setStyle(
                "-fx-font-size: 10px;" +
                        "-fx-fill: #777777;");

        VBox featureTextBox3 = new VBox(
                3,
                featureText3,
                featureSubText3);

        HBox featureBox3 = new HBox(
                12,
                featureIcon3,
                featureTextBox3);

        featureBox3.setAlignment(
                Pos.CENTER_LEFT);

        featureBox3.setPadding(
                new Insets(10));

        featureBox3.setPrefWidth(
                150);

        featureBox3.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-border-color: #F0E4DE;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;");

        HBox featuresBox = new HBox(
                25,
                featureBox1,
                featureBox2,
                featureBox3);

        featuresBox.setAlignment(
                Pos.CENTER);

        // =========================================================
        // CENTER MAIN CONTENT
        // =========================================================

        VBox mainVBox1 = new VBox(
                16,
                pageIntroBox,
                inputInfoHBox1,
                featuresBox);

        mainVBox1.setAlignment(
                Pos.TOP_CENTER);

        mainVBox1.setStyle(
                "-fx-background-color: #F8FBF8;");

        // =========================================================
        // COMPLETE PAGE
        // =========================================================

        VBox centerPage = new VBox(
                mainVBox1);

        centerPage.setAlignment(
                Pos.TOP_CENTER);

        borderPane.setCenter(
                centerPage);

        // =========================================================
        // FOOTER
        // =========================================================

        VBox footerBox = ViewConstants.footer();

        borderPane.setBottom(
                footerBox);

        // =========================================================
        // SCENE
        // =========================================================

        shopkeeperScene = new Scene(
                borderPane,
                1280,
                650);

        shopkeeperScene.setFill(
                Color.web("#F8FBF8"));

        return shopkeeperScene;
    }

}
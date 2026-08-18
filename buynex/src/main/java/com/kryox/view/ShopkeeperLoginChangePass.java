package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ShopkeeperLoginChangePass {

    private static Scene loginChangePassScene;

    public static Scene loginChangePassScene() {

        // =========================================================
        // MAIN BORDERPANE
        // =========================================================

        BorderPane borderPane =
                new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8FBF8;"
        );


        // =========================================================
        // HEADER
        // =========================================================

        HBox headerBox =
                Constants.loginHeader();

        borderPane.setTop(
                headerBox
        );


        // =========================================================
        // CENTER BACKGROUND
        // =========================================================

        StackPane centerPane =
                new StackPane();

        centerPane.setAlignment(
                Pos.CENTER
        );

        centerPane.setPadding(
                new Insets(
                        12,
                        60,
                        12,
                        60
                )
        );

        centerPane.setStyle(
                "-fx-background-color: #F8FBF8;"
        );


        // =========================================================
        // MAIN CARD
        // SAME SIZE AS OTP PAGE
        // =========================================================

        HBox mainCard =
                new HBox();

        mainCard.setAlignment(
                Pos.CENTER
        );

        mainCard.setPrefWidth(
                900
        );

        mainCard.setPrefHeight(
                440
        );

        mainCard.setMaxWidth(
                900
        );

        mainCard.setMaxHeight(
                440
        );

        mainCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;"
        );


        // =========================================================
        // CARD SHADOW
        // =========================================================

        DropShadow cardShadow =
                new DropShadow();

        cardShadow.setRadius(
                18
        );

        cardShadow.setSpread(
                0.01
        );

        cardShadow.setOffsetX(
                0
        );

        cardShadow.setOffsetY(
                5
        );

        cardShadow.setColor(
                Color.rgb(
                        50,
                        40,
                        35,
                        0.13
                )
        );

        mainCard.setEffect(
                cardShadow
        );


        // =========================================================
        // LEFT PANE
        // CHANGE PASSWORD
        // =========================================================

        VBox leftBox =
                new VBox();

        leftBox.setAlignment(
                Pos.TOP_CENTER
        );

        leftBox.setSpacing(
                0
        );

        leftBox.setPrefWidth(
                510
        );

        leftBox.setMinWidth(
                510
        );

        leftBox.setMaxWidth(
                510
        );

        leftBox.setPrefHeight(
                440
        );

        leftBox.setMinHeight(
                440
        );

        leftBox.setMaxHeight(
                440
        );

        leftBox.setPadding(
                new Insets(
                        23,
                        38,
                        20,
                        38
                )
        );

        leftBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18 0 0 18;"
        );


        // =========================================================
        // FORM BOX
        // =========================================================

        VBox formBox =
                new VBox();

        formBox.setAlignment(
                Pos.TOP_LEFT
        );

        formBox.setSpacing(
                0
        );

        formBox.setPrefWidth(
                380
        );

        formBox.setMinWidth(
                380
        );

        formBox.setMaxWidth(
                380
        );


        // =========================================================
        // LOGO
        // =========================================================

        Label logoIcon =
                new Label(
                        "✦"
                );

        logoIcon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        logoIcon.setTextFill(
                Color.WHITE
        );


        StackPane logoIconBox =
                new StackPane();

        logoIconBox.setPrefWidth(
                38
        );

        logoIconBox.setPrefHeight(
                38
        );

        logoIconBox.setMaxWidth(
                38
        );

        logoIconBox.setMaxHeight(
                38
        );

        logoIconBox.setStyle(
                "-fx-background-color: #B84D00;" +
                "-fx-background-radius: 9;"
        );

        logoIconBox.getChildren().add(
                logoIcon
        );


        Label logoText =
                new Label(
                        "BuyNeX"
                );

        logoText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        23
                )
        );

        logoText.setTextFill(
                Color.web("#B84D00")
        );


        HBox logoBox =
                new HBox(
                        8
                );

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.getChildren().addAll(
                logoIconBox,
                logoText
        );


        // =========================================================
        // TITLE
        // =========================================================

        Label changePasswordTitle =
                new Label(
                        "Create New Password"
                );

        changePasswordTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        27
                )
        );

        changePasswordTitle.setTextFill(
                Color.web("#111111")
        );

        changePasswordTitle.setPadding(
                new Insets(
                        24,
                        0,
                        6,
                        0
                )
        );


        // =========================================================
        // DESCRIPTION
        // =========================================================

        Label description =
                new Label(
                        "Create a new password for your BuyNeX account.\n" +
                        "Make sure it is strong and easy for you to remember."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        description.setTextFill(
                Color.web("#59483F")
        );

        description.setLineSpacing(
                2
        );

        description.setPadding(
                new Insets(
                        0,
                        0,
                        15,
                        0
                )
        );


        // =========================================================
        // NEW PASSWORD LABEL
        // =========================================================

        Label newPasswordLabel =
                new Label(
                        "NEW PASSWORD"
                );

        newPasswordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        newPasswordLabel.setTextFill(
                Color.web("#55433B")
        );

        newPasswordLabel.setPadding(
                new Insets(
                        0,
                        0,
                        6,
                        2
                )
        );


        // =========================================================
        // NEW PASSWORD
        // =========================================================

        PasswordField newPassword =
                createPasswordField(
                        "Enter new password"
                );


        // =========================================================
        // CONFIRM PASSWORD LABEL
        // =========================================================

        Label confirmPasswordLabel =
                new Label(
                        "CONFIRM PASSWORD"
                );

        confirmPasswordLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        confirmPasswordLabel.setTextFill(
                Color.web("#55433B")
        );

        confirmPasswordLabel.setPadding(
                new Insets(
                        12,
                        0,
                        6,
                        2
                )
        );


        // =========================================================
        // CONFIRM PASSWORD
        // =========================================================

        PasswordField confirmPassword =
                createPasswordField(
                        "Re-enter new password"
                );


        // =========================================================
        // PASSWORD REQUIREMENT
        // =========================================================

        Label passwordRequirement =
                new Label(
                        "●  Use at least 8 characters with a mix of letters and numbers."
                );

        passwordRequirement.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        passwordRequirement.setTextFill(
                Color.web("#806E65")
        );

        passwordRequirement.setPadding(
                new Insets(
                        7,
                        0,
                        0,
                        2
                )
        );


        // =========================================================
        // UPDATE PASSWORD BUTTON
        // =========================================================

        Button updatePasswordButton =
                new Button(
                        "Update Password     →"
                );

        updatePasswordButton.setPrefWidth(
                380
        );

        updatePasswordButton.setMinWidth(
                380
        );

        updatePasswordButton.setMaxWidth(
                380
        );

        updatePasswordButton.setPrefHeight(
                40
        );

        updatePasswordButton.setMinHeight(
                40
        );

        updatePasswordButton.setMaxHeight(
                40
        );

        updatePasswordButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        updatePasswordButton.setTextFill(
                Color.WHITE
        );

        updatePasswordButton.setStyle(
                "-fx-background-color: #B84D00;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;" +
                "-fx-cursor: hand;"
        );


        // =========================================================
        // BUTTON SHADOW
        // =========================================================

        DropShadow buttonShadow =
                new DropShadow();

        buttonShadow.setRadius(
                7
        );

        buttonShadow.setSpread(
                0.01
        );

        buttonShadow.setOffsetY(
                2
        );

        buttonShadow.setColor(
                Color.rgb(
                        120,
                        55,
                        15,
                        0.20
                )
        );

        updatePasswordButton.setEffect(
                buttonShadow
        );


        // =========================================================
        // BUTTON HOVER
        // =========================================================

        updatePasswordButton.setOnMouseEntered(
                e -> {

                    updatePasswordButton.setStyle(
                            "-fx-background-color: #9F4100;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                }
        );


        updatePasswordButton.setOnMouseExited(
                e -> {

                    updatePasswordButton.setStyle(
                            "-fx-background-color: #B84D00;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                }
        );


        // =========================================================
        // UPDATE PASSWORD ACTION
        // FIXED VARIABLE NAMES
        // =========================================================

        updatePasswordButton.setOnAction(
                e -> {

                    String enteredNewPassword =
                            newPassword.getText();

                    String enteredConfirmPassword =
                            confirmPassword.getText();


                    if (
                            enteredNewPassword.isEmpty()
                            || enteredConfirmPassword.isEmpty()
                    ) {

                        System.out.println(
                                "Please enter both passwords."
                        );

                        return;
                    }


                    if (
                            !enteredNewPassword.equals(
                                    enteredConfirmPassword
                            )
                    ) {

                        System.out.println(
                                "Passwords do not match."
                        );

                        return;
                    }


                    if (
                            enteredNewPassword.length() < 8
                    ) {

                        System.out.println(
                                "Password must contain at least 8 characters."
                        );

                        return;
                    }


                    System.out.println(
                            "Password updated successfully."
                    );


                    // =================================================
                    // AFTER SUCCESSFUL PASSWORD CHANGE
                    // =================================================

                    Main.primaryStage.setScene(
                            ShopkeeperLogin.loginscene()
                    );

                }
        );


        VBox.setMargin(
                updatePasswordButton,
                new Insets(
                        12,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // BACK TO LOGIN BUTTON
        // =========================================================

        Button backToLoginButton =
                new Button(
                        "←  Back to Login"
                );

        backToLoginButton.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        backToLoginButton.setTextFill(
                Color.web("#59483F")
        );

        backToLoginButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );


        // =========================================================
        // BACK BUTTON HOVER
        // =========================================================

        backToLoginButton.setOnMouseEntered(
                e -> {

                    backToLoginButton.setTextFill(
                            Color.web("#A94600")
                    );

                }
        );


        backToLoginButton.setOnMouseExited(
                e -> {

                    backToLoginButton.setTextFill(
                            Color.web("#59483F")
                    );

                }
        );


        // =========================================================
        // BACK BUTTON ACTION
        // =========================================================

        backToLoginButton.setOnAction(
                e -> {

                    Main.primaryStage.setScene(
                            ShopkeeperLogin.loginscene()
                    );

                }
        );


        HBox backBox =
                new HBox(
                        backToLoginButton
                );

        backBox.setAlignment(
                Pos.CENTER
        );

        backBox.setPrefWidth(
                380
        );

        backBox.setPadding(
                new Insets(
                        7,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // ADD LEFT CONTENT
        // =========================================================

        formBox.getChildren().addAll(
                logoBox,
                changePasswordTitle,
                description,
                newPasswordLabel,
                newPassword,
                confirmPasswordLabel,
                confirmPassword,
                passwordRequirement,
                updatePasswordButton,
                backBox
        );


        leftBox.getChildren().add(
                formBox
        );


        // =========================================================
        // RIGHT PANE
        // SAME AS OTP VERIFICATION PAGE
        // =========================================================

        StackPane rightPane =
                new StackPane();

        rightPane.setPrefWidth(
                390
        );

        rightPane.setMinWidth(
                390
        );

        rightPane.setMaxWidth(
                390
        );

        rightPane.setPrefHeight(
                440
        );

        rightPane.setMinHeight(
                440
        );

        rightPane.setMaxHeight(
                440
        );


        // =========================================================
        // RIGHT IMAGE
        // =========================================================

        Image image =
                new Image(
                        "/assets/images/Login.png"
                );


        ImageView imageView =
                new ImageView(
                        image
                );

        imageView.setFitWidth(
                390
        );

        imageView.setFitHeight(
                440
        );

        imageView.setPreserveRatio(
                false
        );

        imageView.setSmooth(
                true
        );


        // =========================================================
        // IMAGE CLIP
        // =========================================================

        Rectangle imageClip =
                new Rectangle(
                        390,
                        440
                );

        imageClip.setArcWidth(
                28
        );

        imageClip.setArcHeight(
                28
        );

        imageView.setClip(
                imageClip
        );


        // =========================================================
        // DARK OVERLAY
        // =========================================================

        Rectangle darkOverlay =
                new Rectangle(
                        390,
                        440
                );

        darkOverlay.setFill(
                Color.rgb(
                        0,
                        0,
                        0,
                        0.38
                )
        );

        darkOverlay.setArcWidth(
                28
        );

        darkOverlay.setArcHeight(
                28
        );


        // =========================================================
        // RIGHT CONTENT
        // =========================================================

        VBox rightContent =
                new VBox();

        rightContent.setAlignment(
                Pos.BOTTOM_LEFT
        );

        rightContent.setSpacing(
                0
        );

        rightContent.setPrefWidth(
                390
        );

        rightContent.setPrefHeight(
                440
        );

        rightContent.setPadding(
                new Insets(
                        0,
                        32,
                        38,
                        32
                )
        );


        // =========================================================
        // MERCHANT PORTAL BADGE
        // =========================================================

        Label badgeDot =
                new Label(
                        "●"
                );

        badgeDot.setFont(
                Font.font(
                        "Arial",
                        9
                )
        );

        badgeDot.setTextFill(
                Color.web("#B84D00")
        );


        Label badgeText =
                new Label(
                        "MERCHANT PORTAL"
                );

        badgeText.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        badgeText.setTextFill(
                Color.WHITE
        );


        HBox badge =
                new HBox(
                        6
                );

        badge.setAlignment(
                Pos.CENTER_LEFT
        );

        badge.setPadding(
                new Insets(
                        7,
                        12,
                        7,
                        12
                )
        );

        badge.setStyle(
                "-fx-background-color: rgba(50,40,35,0.65);" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: rgba(255,255,255,0.20);" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 20;"
        );

        badge.getChildren().addAll(
                badgeDot,
                badgeText
        );


        // =========================================================
        // BUSINESS TITLE
        // =========================================================

        Label businessTitle =
                new Label(
                        "Empower Your Business."
                );

        businessTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        27
                )
        );

        businessTitle.setTextFill(
                Color.WHITE
        );

        businessTitle.setWrapText(
                true
        );

        businessTitle.setPadding(
                new Insets(
                        18,
                        0,
                        11,
                        0
                )
        );


        // =========================================================
        // BUSINESS DESCRIPTION
        // =========================================================

        Label businessDescription =
                new Label(
                        "Secure, seamless access to your hyperlocal shopping\n" +
                        "ecosystem."
                );

        businessDescription.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        businessDescription.setTextFill(
                Color.WHITE
        );

        businessDescription.setOpacity(
                0.90
        );

        businessDescription.setLineSpacing(
                3
        );

        businessDescription.setTextAlignment(
                TextAlignment.LEFT
        );


        rightContent.getChildren().addAll(
                badge,
                businessTitle,
                businessDescription
        );


        // =========================================================
        // RIGHT PANE LAYERS
        // =========================================================

        rightPane.getChildren().addAll(
                imageView,
                darkOverlay,
                rightContent
        );


        // =========================================================
        // ADD LEFT + RIGHT
        // =========================================================

        mainCard.getChildren().addAll(
                leftBox,
                rightPane
        );


        // =========================================================
        // CENTER
        // =========================================================

        centerPane.getChildren().add(
                mainCard
        );

        borderPane.setCenter(
                centerPane
        );


        // =========================================================
        // FOOTER
        // =========================================================

        VBox footerBox =
                Constants.loginFooter();

        borderPane.setBottom(
                footerBox
        );


        // =========================================================
        // SCENE
        // =========================================================

        loginChangePassScene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        loginChangePassScene.setFill(
                Color.web("#F8FBF8")
        );


        return loginChangePassScene;
    }


    // =============================================================
    // CREATE PASSWORD FIELD
    // =============================================================

    private static PasswordField createPasswordField(
            String prompt
    ) {

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                prompt
        );

        passwordField.setPrefWidth(
                380
        );

        passwordField.setMinWidth(
                380
        );

        passwordField.setMaxWidth(
                380
        );

        passwordField.setPrefHeight(
                42
        );

        passwordField.setMinHeight(
                42
        );

        passwordField.setMaxHeight(
                42
        );

        passwordField.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        passwordField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E4C9BD;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 13 0 13;" +
                "-fx-prompt-text-fill: #9A8B84;"
        );


        // =========================================================
        // FIELD SHADOW
        // =========================================================

        DropShadow passwordShadow =
                new DropShadow();

        passwordShadow.setRadius(
                4
        );

        passwordShadow.setSpread(
                0.01
        );

        passwordShadow.setOffsetY(
                1
        );

        passwordShadow.setColor(
                Color.rgb(
                        80,
                        50,
                        35,
                        0.07
                )
        );

        passwordField.setEffect(
                passwordShadow
        );


        return passwordField;
    }
}
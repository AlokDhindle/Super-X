
package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class ShopkeeperLoginVerify {

    private static Scene loginVerifyScene;

    public static Scene loginVerifyScene() {

        // =========================================================
        // MAIN BORDERPANE
        // =========================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8FBF8;"
        );


        // =========================================================
        // HEADER
        // SAME AS SEND OTP PAGE
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
        // SAME SIZE AS SEND OTP
        // 900 x 440
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
        // LEFT SECTION
        // OTP VERIFICATION
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
                new Label("✦");

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

        Label otpTitle =
                new Label(
                        "Verify OTP"
                );

        otpTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        28
                )
        );

        otpTitle.setTextFill(
                Color.web("#111111")
        );

        otpTitle.setPadding(
                new Insets(
                        27,
                        0,
                        7,
                        0
                )
        );


        // =========================================================
        // DESCRIPTION
        // =========================================================

        Label description =
                new Label(
                        "Enter the 6-digit OTP sent to your mobile number.\n" +
                        "The OTP is valid for 10 minutes."
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
                        17,
                        0
                )
        );


        // =========================================================
        // OTP LABEL
        // =========================================================

        Label otpLabel =
                new Label(
                        "ENTER OTP"
                );

        otpLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        otpLabel.setTextFill(
                Color.web("#55433B")
        );

        otpLabel.setPadding(
                new Insets(
                        0,
                        0,
                        7,
                        2
                )
        );


        // =========================================================
        // OTP FIELDS
        // =========================================================

        TextField otp1 =
                createOtpField();

        TextField otp2 =
                createOtpField();

        TextField otp3 =
                createOtpField();

        TextField otp4 =
                createOtpField();

        TextField otp5 =
                createOtpField();

        TextField otp6 =
                createOtpField();


        // =========================================================
        // OTP NAVIGATION
        // =========================================================

        setupOtpNavigation(
                otp1,
                otp2,
                null
        );

        setupOtpNavigation(
                otp2,
                otp3,
                otp1
        );

        setupOtpNavigation(
                otp3,
                otp4,
                otp2
        );

        setupOtpNavigation(
                otp4,
                otp5,
                otp3
        );

        setupOtpNavigation(
                otp5,
                otp6,
                otp4
        );

        setupOtpNavigation(
                otp6,
                null,
                otp5
        );


        // =========================================================
        // OTP BOX
        // =========================================================

        HBox otpBox =
                new HBox(
                        10
                );

        otpBox.setAlignment(
                Pos.CENTER_LEFT
        );

        otpBox.setPrefWidth(
                380
        );

        otpBox.setMinWidth(
                380
        );

        otpBox.setMaxWidth(
                380
        );

        otpBox.getChildren().addAll(
                otp1,
                otp2,
                otp3,
                otp4,
                otp5,
                otp6
        );


        // =========================================================
        // VERIFY & CONTINUE BUTTON
        // =========================================================

        Button verifyButton =
                new Button(
                        "Verify & Continue     →"
                );

        verifyButton.setPrefWidth(
                380
        );

        verifyButton.setMinWidth(
                380
        );

        verifyButton.setMaxWidth(
                380
        );

        verifyButton.setPrefHeight(
                40
        );

        verifyButton.setMinHeight(
                40
        );

        verifyButton.setMaxHeight(
                40
        );

        verifyButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        verifyButton.setTextFill(
                Color.WHITE
        );

        verifyButton.setStyle(
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

        verifyButton.setEffect(
                buttonShadow
        );


        // =========================================================
        // BUTTON HOVER
        // =========================================================

        verifyButton.setOnMouseEntered(
                e -> {

                    verifyButton.setStyle(
                            "-fx-background-color: #9F4100;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                }
        );


        verifyButton.setOnMouseExited(
                e -> {

                    verifyButton.setStyle(
                            "-fx-background-color: #B84D00;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-radius: 8;" +
                            "-fx-cursor: hand;"
                    );

                }
        );


        // =========================================================
        // VERIFY ACTION
        // =========================================================

        verifyButton.setOnAction(
                e -> {

                    String enteredOtp =
                            otp1.getText()
                            + otp2.getText()
                            + otp3.getText()
                            + otp4.getText()
                            + otp5.getText()
                            + otp6.getText();

                    System.out.println(
                            "Entered OTP: " + enteredOtp
                    );

                    // Add OTP verification logic here.
                    Main.primaryStage.setScene(ShopkeeperLoginChangePass.loginChangePassScene());

                }
        );


        VBox.setMargin(
                verifyButton,
                new Insets(
                        12,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // RESEND OTP
        // =========================================================

        Label resendText =
                new Label(
                        "Didn't receive the OTP?"
                );

        resendText.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        resendText.setTextFill(
                Color.web("#59483F")
        );


        Button resendButton =
                new Button(
                        "Resend OTP"
                );

        resendButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        resendButton.setTextFill(
                Color.web("#A94600")
        );

        resendButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0 0 0 5;" +
                "-fx-cursor: hand;"
        );


        resendButton.setOnMouseEntered(
                e ->
                        resendButton.setTextFill(
                                Color.web("#7F3500")
                        )
        );


        resendButton.setOnMouseExited(
                e ->
                        resendButton.setTextFill(
                                Color.web("#A94600")
                        )
        );


        resendButton.setOnAction(
                e -> {

                    System.out.println(
                            "Resend OTP clicked"
                    );

                }
        );


        HBox resendBox =
                new HBox();

        resendBox.setAlignment(
                Pos.CENTER
        );

        resendBox.setPrefWidth(
                380
        );

        resendBox.setPadding(
                new Insets(
                        8,
                        0,
                        0,
                        0
                )
        );

        resendBox.getChildren().addAll(
                resendText,
                resendButton
        );


        // =========================================================
        // CHANGE MOBILE NUMBER
        // =========================================================

        Button backButton =
                new Button(
                        "←  Change Mobile Number"
                );

        backButton.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        backButton.setTextFill(
                Color.web("#59483F")
        );

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );


        backButton.setOnMouseEntered(
                e ->
                        backButton.setTextFill(
                                Color.web("#A94600")
                        )
        );


        backButton.setOnMouseExited(
                e ->
                        backButton.setTextFill(
                                Color.web("#59483F")
                        )
        );


        backButton.setOnAction(
                e -> {

                    Main.primaryStage.setScene(
                            ShopkeeperLoginSendOtp.sendOtpScene()
                    );

                }
        );


        HBox backBox =
                new HBox(
                        backButton
                );

        backBox.setAlignment(
                Pos.CENTER
        );

        backBox.setPrefWidth(
                380
        );

        backBox.setPadding(
                new Insets(
                        6,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // FORM CONTENT
        // =========================================================

        formBox.getChildren().addAll(
                logoBox,
                otpTitle,
                description,
                otpLabel,
                otpBox,
                verifyButton,
                resendBox,
                backBox
        );


        leftBox.getChildren().add(
                formBox
        );


        // =========================================================
        // RIGHT PANE
        // SAME AS SEND OTP PAGE
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
        // IMAGE
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
                ));

        darkOverlay.setArcWidth(
                28
        );

        darkOverlay.setArcHeight(
                28
        );


        // =========================================================
        // RIGHT CONTENT
        // SAME AS SEND OTP PAGE
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
        // SAME AS SEND OTP PAGE
        // =========================================================

        VBox footerBox =
                Constants.loginFooter();

        borderPane.setBottom(
                footerBox
        );


        // =========================================================
        // SCENE
        // =========================================================

        loginVerifyScene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        loginVerifyScene.setFill(
                Color.web("#F8FBF8")
        );


        return loginVerifyScene;
    }


    // =============================================================
    // CREATE OTP FIELD
    // =============================================================

    private static TextField createOtpField() {

        TextField otpField =
                new TextField();

        otpField.setPrefWidth(
                52
        );

        otpField.setMinWidth(
                52
        );

        otpField.setMaxWidth(
                52
        );

        otpField.setPrefHeight(
                48
        );

        otpField.setMinHeight(
                48
        );

        otpField.setMaxHeight(
                48
        );

        otpField.setAlignment(
                Pos.CENTER
        );

        otpField.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        otpField.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E4C9BD;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0;"
        );


        DropShadow otpShadow =
                new DropShadow();

        otpShadow.setRadius(
                4
        );

        otpShadow.setSpread(
                0.01
        );

        otpShadow.setOffsetY(
                1
        );

        otpShadow.setColor(
                Color.rgb(
                        80,
                        50,
                        35,
                        0.07
                )
        );

        otpField.setEffect(
                otpShadow
        );


        return otpField;
    }


    // =============================================================
    // OTP NAVIGATION
    // =============================================================

    private static void setupOtpNavigation(
            TextField current,
            TextField next,
            TextField previous
    ) {

        current.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue.length() > 1) {

                        current.setText(
                                newValue.substring(
                                        0,
                                        1
                                )
                        );
                    }

                    if (
                            newValue.length() == 1
                            && next != null
                    ) {

                        next.requestFocus();
                    }
                }
        );


        current.setOnKeyPressed(
                event -> {

                    switch (
                            event.getCode()
                    ) {

                        case BACK_SPACE:

                            if (
                                    current.getText().isEmpty()
                                    && previous != null
                            ) {

                                previous.requestFocus();
                            }

                            break;

                        default:
                            break;
                    }
                }
        );
    }
}
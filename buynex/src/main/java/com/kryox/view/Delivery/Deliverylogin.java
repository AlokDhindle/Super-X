
package com.kryox.view.Delivery;

import com.kryox.controller.Delivery.DeliveryLoginController;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Deliverylogin {

    private static final String BG_COLOR = "#F5E0D3";

    private static final String ORANGE_GRADIENT =
            "linear-gradient(to right, #D66F1C, #F39A52)";

    private static final String ORANGE_GRADIENT_HOVER =
            "linear-gradient(to right, #C55E13, #E68A3D)";

    private static final DeliveryLoginController loginController =
            new DeliveryLoginController();

    public static Scene deliveryLoginScene() {

        BorderPane root = buildView();

        Scene scene =
                new Scene(
                        root,
                        1550,
                        850
                );

        scene.setFill(
                Color.web(BG_COLOR)
        );

        return scene;
    }

    public static BorderPane buildView() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " +
                        BG_COLOR + ";"
        );

        BorderPane topBar =
                new BorderPane();

        topBar.setPrefHeight(65);
        topBar.setMinHeight(65);
        topBar.setMaxHeight(65);

        topBar.setStyle(
                "-fx-background-color: #E69A5B;" +
                "-fx-border-color: transparent;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0 35 0 30;"
        );

        Text logo =
                new Text("BuyNeX");

        logo.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-fill: #B84208;" +
                "-fx-font-weight: bold;"
        );

        Button backToHome = new Button("← Back to Home");
        backToHome.setPrefHeight(36);
        backToHome.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B84208;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #B84208;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 6 16 6 16;" +
                "-fx-cursor: hand;"
        );
        backToHome.setOnMouseEntered(e -> backToHome.setStyle(
                "-fx-background-color: #B84208;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #B84208;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 6 16 6 16;" +
                "-fx-cursor: hand;"
        ));
        backToHome.setOnMouseExited(e -> backToHome.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B84208;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #B84208;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 6 16 6 16;" +
                "-fx-cursor: hand;"
        ));
        backToHome.setOnAction(e -> Homepage.showHomepage());

        HBox logoBox =
                new HBox(15, backToHome, logo);

        logoBox.setAlignment(
                Pos.CENTER_LEFT
        );

        logoBox.setPadding(
                new Insets(
                        0,
                        0,
                        0,
                        25
                )
        );

        topBar.setLeft(
                logoBox
        );

        Text needHelpTxt =
                new Text("Need Help?");

        needHelpTxt.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #333333;" +
                "-fx-cursor: hand;"
        );

        needHelpTxt.setOnMouseClicked(e -> {

            if (Homepage.HomepageStage != null) {

                Homepage.HomepageStage.setScene(
                        DeliverySupport.supportScene(
                                "LOGIN"
                        )
                );

                Homepage.HomepageStage.show();
            }
        });

        Text supportLink =
                new Text("ⓘ Support");

        supportLink.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #B84208;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        supportLink.setOnMouseClicked(e -> {

            if (Homepage.HomepageStage != null) {

                Homepage.HomepageStage.setScene(
                        DeliverySupport.supportScene(
                                "LOGIN"
                        )
                );

                Homepage.HomepageStage.show();
            }
        });

        HBox supportBox =
                new HBox(
                        8,
                        needHelpTxt,
                        supportLink
                );

        supportBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        supportBox.setPadding(
                new Insets(
                        0,
                        25,
                        0,
                        0
                )
        );

        topBar.setRight(
                supportBox
        );

        root.setTop(
                topBar
        );

        HBox hbox =
                new HBox();

        hbox.setFillHeight(
                true
        );

        ImageView imageView =
                new ImageView();

        try {

            Image image =
                    new Image(
                            Deliverylogin.class
                                    .getClassLoader()
                                    .getResourceAsStream(
                                            "requirements/delivery.jpeg"
                                    )
                    );

            if (image == null ||
                    image.isError()) {

                image =
                        new Image(
                                "file:src/main/resources/requirements/delivery.jpeg"
                        );
            }

            imageView.setImage(
                    image
            );

        } catch (Exception ignored) {

            try {

                imageView.setImage(
                        new Image(
                                "assets\\images\\delivery.jpeg"
                        )
                );

            } catch (Exception ignoredAlt) {

                System.out.println(
                        "Delivery image could not be loaded."
                );
            }
        }

        imageView.setPreserveRatio(
                true
        );

        imageView.setFitWidth(
                520
        );

        imageView.setFitHeight(
                720
        );

        imageView.setSmooth(
                true
        );

        VBox leftVBox =
                new VBox();

        leftVBox.setAlignment(
                Pos.CENTER
        );

        leftVBox.setPadding(
                new Insets(
                        25,
                        50,
                        25,
                        50
                )
        );

        leftVBox.setStyle(
                "-fx-background-color: #EBCCB7;"
        );

        leftVBox.getChildren().add(
                imageView
        );

        HBox.setHgrow(
                leftVBox,
                Priority.ALWAYS
        );

        VBox rightSide =
                new VBox();

        rightSide.setAlignment(
                Pos.CENTER
        );

        rightSide.setStyle(
                "-fx-background-color: #EBCCB7;"
        );

        rightSide.setPadding(
                new Insets(
                        30,
                        50,
                        30,
                        50
                )
        );

        HBox.setHgrow(
                rightSide,
                Priority.ALWAYS
        );

        VBox loginCard =
                new VBox(18);

        loginCard.setPadding(
                new Insets(35)
        );

        loginCard.setPrefWidth(
                460
        );

        loginCard.setMaxWidth(
                460
        );

        loginCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(" +
                        "gaussian," +
                        "rgba(0,0,0,0.12)," +
                        "12,0,0,4);"
        );

        Text heading =
                new Text(
                        "Welcome Back,\nDelivery Partner"
                );

        heading.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-fill: #111111;" +
                "-fx-font-weight: bold;"
        );

        Text description =
                new Text(
                        "Login to manage deliveries, earnings, and live orders."
                );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );

        Label emailLabel =
                new Label("Email");

        emailLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #444444;" +
                "-fx-font-weight: bold;"
        );

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefHeight(
                42
        );

        emailField.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #444444;" +
                "-fx-font-weight: bold;"
        );

        PasswordField passwordField =
                new PasswordField();
        passwordField.setPromptText("••••••••");
        passwordField.setPrefHeight(42);
        passwordField.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );

        TextField plainPasswordField = new TextField();
        plainPasswordField.setPromptText("••••••••");
        plainPasswordField.setPrefHeight(42);
        plainPasswordField.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );
        plainPasswordField.setManaged(false);
        plainPasswordField.setVisible(false);
        plainPasswordField.textProperty().bindBidirectional(passwordField.textProperty());

        javafx.scene.layout.StackPane passStack = new javafx.scene.layout.StackPane(passwordField, plainPasswordField);

        CheckBox showPassCb = new CheckBox("Show Password");
        showPassCb.setStyle("-fx-font-size: 12px; -fx-text-fill: #555555;");
        showPassCb.setOnAction(e -> {
            if (showPassCb.isSelected()) {
                passwordField.setVisible(false);
                passwordField.setManaged(false);
                plainPasswordField.setVisible(true);
                plainPasswordField.setManaged(true);
            } else {
                plainPasswordField.setVisible(false);
                plainPasswordField.setManaged(false);
                passwordField.setVisible(true);
                passwordField.setManaged(true);
            }
        });

        CheckBox rememberMe =
                new CheckBox(
                        "Remember Me"
                );

        rememberMe.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #555555;"
        );

        HBox optionsBox = new HBox(15, rememberMe, showPassCb);
        optionsBox.setAlignment(Pos.CENTER_LEFT);

        Button loginButton =
                new Button(
                        "Login  →"
                );

        loginButton.setPrefHeight(
                44
        );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: " +
                        ORANGE_GRADIENT + ";" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        loginButton.setOnMouseEntered(
                e -> loginButton.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: " +
                                ORANGE_GRADIENT_HOVER + ";" +
                        "-fx-background-radius: 7;" +
                        "-fx-cursor: hand;"
                )
        );

        loginButton.setOnMouseExited(
                e -> loginButton.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: " +
                                ORANGE_GRADIENT + ";" +
                        "-fx-background-radius: 7;" +
                        "-fx-cursor: hand;"
                )
        );

        loginButton.setOnAction(e -> {

            String emailInput =
                    emailField.getText() != null
                            ? emailField.getText().trim()
                            : "";

            String passInput =
                    passwordField.getText() != null
                            ? passwordField.getText()
                            : "";

            System.out.println(
                    "================================"
            );

            System.out.println(
                    "DELIVERY LOGIN BUTTON CLICKED"
            );

            System.out.println(
                    "Email: " + emailInput
            );

            System.out.println(
                    "================================"
            );

            loginController.handleLogin(
                    emailInput,
                    passInput
            );
        });

        Label orDivider =
                new Label(
                        "───────────────  OR  ───────────────"
                );

        orDivider.setMaxWidth(
                Double.MAX_VALUE
        );

        orDivider.setAlignment(
                Pos.CENTER
        );

        orDivider.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #888888;"
        );

        Text registerTxt =
                new Text(
                        "New to BuyNeX?"
                );

        registerTxt.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #333333;"
        );

        Button signupBtn =
                new Button(
                        "Become a Delivery Partner"
                );

        signupBtn.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #c95b14;" +
                "-fx-border-color: transparent;" +
                "-fx-background-color: transparent;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        signupBtn.setOnAction(e -> {

            if (Homepage.HomepageStage != null) {

                Homepage.HomepageStage.setScene(
                        DeliveryRegistration2.registrationScene()
                );

                Homepage.HomepageStage.show();
            }
        });

        HBox registerBox =
                new HBox(
                        4,
                        registerTxt,
                        signupBtn
                );

        registerBox.setAlignment(
                Pos.CENTER
        );

        VBox form =
                new VBox(10);

        form.setFillWidth(
                true
        );

        form.getChildren().addAll(
                description,
                emailLabel,
                emailField,
                passwordLabel,
                passStack,
                optionsBox,
                loginButton,
                orDivider,
                registerBox
        );

        loginCard.getChildren().addAll(
                heading,
                form
        );

        rightSide.getChildren().add(
                loginCard
        );

        hbox.getChildren().addAll(
                leftVBox,
                rightSide
        );

        root.setCenter(
                hbox
        );

        return root;
    }
}


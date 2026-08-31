package com.kryox.view.Delivery;

import com.kryox.controller.Delivery.DeliveryLoginController;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Deliverylogin {

    private static final DeliveryLoginController loginController =
            new DeliveryLoginController();

    private static Scene delivaryScene;

    public static Scene getDelivaryScene() {

        BorderPane root = buildView();

        delivaryScene = new Scene(
                root,
                1530,
                850
        );

        return delivaryScene;
    }

    public static void show(Stage primaryStage) {

        if (primaryStage == null) {
            primaryStage = Homepage.HomepageStage;
        }

        if (primaryStage == null) {
            return;
        }

        primaryStage.setScene(
                getDelivaryScene()
        );

        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private static BorderPane buildView() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #eee5df;"
        );

        BorderPane topBar =
                new BorderPane();

        topBar.setPrefHeight(58);
        topBar.setMinHeight(58);
        topBar.setMaxHeight(58);

        topBar.setStyle(
                "-fx-background-color: #eee5df;" +
                "-fx-border-color: #e0d5ce;" +
                "-fx-border-width: 0 0 1 0;"
        );

        Text logo =
                new Text("BuyNeX");

        logo.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-fill: #F36A00;" +
                "-fx-font-weight: bold;"
        );

        HBox logoBox =
                new HBox(logo);

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
                "-fx-fill: #333333;"
        );

        Text supportLink =
                new Text("ⓘ Support");

        supportLink.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #F36A00;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        supportLink.setOnMouseClicked(e ->
                DeliverySupport.show(
                        Homepage.HomepageStage,
                        "LOGIN"
                )
        );

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

            if (!image.isError()) {
                imageView.setImage(image);
            }

        } catch (Exception e) {

            System.out.println(
                    "Delivery image could not be loaded."
            );
        }

        imageView.setPreserveRatio(
                true
        );

        imageView.setFitWidth(
                460
        );

        imageView.setFitHeight(
                700
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
                        20,
                        40,
                        20,
                        40
                )
        );

        leftVBox.setStyle(
                "-fx-background-color: #f3f3fa;"
        );

        leftVBox.getChildren()
                .add(
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
                "-fx-background-color: #f8f8ff;"
        );

        rightSide.setPadding(
                new Insets(
                        20,
                        30,
                        20,
                        30
                )
        );

        HBox.setHgrow(
                rightSide,
                Priority.ALWAYS
        );

        VBox loginCard =
                new VBox(18);

        loginCard.setPadding(
                new Insets(30)
        );

        loginCard.setPrefWidth(
                430
        );

        loginCard.setMaxWidth(
                430
        );

        loginCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.1),12,0,0,4);"
        );

        Text heading =
                new Text(
                        "Welcome Back,\nDelivery Partner"
                );

        heading.setStyle(
                "-fx-font-size: 26px;" +
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
                40
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

        passwordField.setPromptText(
                "••••••••"
        );

        passwordField.setPrefHeight(
                40
        );

        passwordField.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-background-color: #faf8fc;" +
                "-fx-border-color: #e1dce5;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-padding: 0 12 0 12;"
        );

        CheckBox rememberMe =
                new CheckBox(
                        "Remember Me"
                );

        Button loginButton =
                new Button(
                        "Login  →"
                );

        loginButton.setPrefHeight(
                42
        );

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #F36A00;" +
                "-fx-background-radius: 7;" +
                "-fx-cursor: hand;"
        );

        loginButton.setOnAction(e -> {

            String emailInput =
                    emailField
                            .getText()
                            .trim();

            String passInput =
                    passwordField
                            .getText();

            if (emailInput.isEmpty()) {

                emailField.requestFocus();
                return;
            }

            if (passInput.isEmpty()) {

                passwordField.requestFocus();
                return;
            }

            loginController.handleLogin(
                    emailInput,
                    passInput,
                    Homepage.HomepageStage
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

        Text registerTxt =
                new Text(
                        "New to BuyNeX?"
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

            try {

                Class<?> clazz =
                        Class.forName(
                                "com.kryox.view.Delivery.DeliveryRegistration2"
                        );

                Object result =
                        clazz.getMethod(
                                "getRegistrationScene"
                        )
                                .invoke(null);

                if (result instanceof Scene) {

                    Homepage.HomepageStage
                            .setScene(
                                    (Scene) result
                            );
                }

            } catch (Exception ex) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setHeaderText(null);
                alert.setContentText(
                        "DeliveryRegistration2 page is not active. Uncomment/fix that file first."
                );

                alert.showAndWait();
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

        form.getChildren()
                .addAll(
                        description,
                        emailLabel,
                        emailField,
                        passwordLabel,
                        passwordField,
                        rememberMe,
                        loginButton,
                        orDivider,
                        registerBox
                );

        loginCard.getChildren()
                .addAll(
                        heading,
                        form
                );

        rightSide.getChildren()
                .add(
                        loginCard
                );

        hbox.getChildren()
                .addAll(
                        leftVBox,
                        rightSide
                );

        root.setCenter(
                hbox
        );

        return root;
    }
}

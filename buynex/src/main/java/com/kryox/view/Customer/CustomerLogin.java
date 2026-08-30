package com.kryox.view.Customer;

import com.kryox.controller.Customer.Userstorecontroller;
import com.kryox.controller.Customer.controler;
import com.kryox.model.Customer.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CustomerLogin {

    private Scene LoginScene;

    Scene getLoginScene() {

        // ================= HEADER =================

        Button backButton = new Button("←  Back");

        backButton.setPrefSize(
                90,
                38
        );

        backButton.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-text-fill:#8A3600;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:19;" +
                "-fx-border-color:#E7BDA6;" +
                "-fx-border-radius:19;" +
                "-fx-cursor:hand;"
        );

        backButton.setOnMouseEntered(event -> {

            backButton.setStyle(
                    "-fx-background-color:#FF6500;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-size:13px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:19;" +
                    "-fx-border-color:#FF6500;" +
                    "-fx-border-radius:19;" +
                    "-fx-cursor:hand;"
            );
        });

        backButton.setOnMouseExited(event -> {

            backButton.setStyle(
                    "-fx-background-color:#FFF0E7;" +
                    "-fx-text-fill:#8A3600;" +
                    "-fx-font-size:13px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:19;" +
                    "-fx-border-color:#E7BDA6;" +
                    "-fx-border-radius:19;" +
                    "-fx-cursor:hand;"
            );
        });

        backButton.setOnAction(event -> {

            try {

                Homepage homePage =
                        new Homepage();

                homePage.start(
                        Homepage.HomepageStage
                );

            } catch (Exception exception) {

                exception.printStackTrace();
            }
        });


        Text t1 = new Text("BuyNeX");

        t1.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#9C3700;"
        );

        HBox hb1 = new HBox(
                18,
                backButton,
                t1
        );

        hb1.setAlignment(
                Pos.CENTER_LEFT
        );

        Text t2 = new Text("Support");

        t2.setStyle(
                "-fx-font-size:14px;" +
                "-fx-fill:#5C4033;"
        );

        Text t3 = new Text("Partner Login");

        t3.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#C65A1E;"
        );

        HBox hb2 = new HBox(20, t2, t3);
        hb2.setAlignment(Pos.CENTER_RIGHT);

        BorderPane header = new BorderPane();

        header.setLeft(hb1);
        header.setRight(hb2);

        header.setPadding(
                new Insets(20, 35, 20, 35)
        );

        header.setStyle(
                "-fx-background-color:#EEE5DF;"
        );

        // ================= LEFT IMAGE =================

        Image img = new Image(
                "assets\\images\\hyperlocal.png"
        );

        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(550);
        imageView.setFitHeight(420);

        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Rectangle clip = new Rectangle(550, 420);

        clip.setArcWidth(30);
        clip.setArcHeight(30);

        imageView.setClip(clip);

        // ================= SHOP CARD =================

        Image img1 = new Image(
                "assets\\logo\\shopping_bag.png"
        );

        ImageView imageView1 = new ImageView(img1);

        imageView1.setFitWidth(25);
        imageView1.setFitHeight(25);

        Label lbl1 = new Label("500+ Shops");

        lbl1.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#4A4A4A;"
        );

        VBox vbShop = new VBox(
                8,
                imageView1,
                lbl1
        );

        vbShop.setAlignment(Pos.CENTER);

        vbShop.setPrefSize(140, 80);
        vbShop.setMinSize(140, 80);

        vbShop.setStyle(
                "-fx-background-color:#F3F4F6;" +
                "-fx-background-radius:18;" +
                "-fx-border-radius:18;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.15),15,0.2,2,3);"
        );

        // ================= DELIVERY CARD =================

        Image img2 = new Image(
                "assets\\logo\\fast_delivery.png"
        );

        ImageView imageView2 = new ImageView(img2);

        imageView2.setFitWidth(25);
        imageView2.setFitHeight(25);

        Label lbl2 = new Label("Fast Delivery");

        lbl2.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#4A4A4A;"
        );

        VBox vbDelivery = new VBox(
                8,
                imageView2,
                lbl2
        );

        vbDelivery.setAlignment(Pos.CENTER);

        vbDelivery.setPrefSize(140, 80);
        vbDelivery.setMinSize(140, 80);

        vbDelivery.setStyle(
                "-fx-background-color:#F3F4F6;" +
                "-fx-background-radius:18;" +
                "-fx-border-radius:18;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.15),15,0.2,2,3);"
        );

        // ================= AI CARD =================

        Image img3 = new Image(
                "assets\\logo\\ai_powered.png"
        );

        ImageView imageView3 = new ImageView(img3);

        imageView3.setFitWidth(25);
        imageView3.setFitHeight(25);

        Label lbl3 = new Label("AI Powered");

        lbl3.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#4A4A4A;"
        );

        VBox vbAI = new VBox(
                8,
                imageView3,
                lbl3
        );

        vbAI.setAlignment(Pos.CENTER);

        vbAI.setPrefSize(140, 80);
        vbAI.setMinSize(140, 80);

        vbAI.setStyle(
                "-fx-background-color:#F3F4F6;" +
                "-fx-background-radius:18;" +
                "-fx-border-radius:18;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.15),15,0.2,2,3);"
        );

        // ================= CARDS =================

        HBox hbCards = new HBox(
                20,
                vbShop,
                vbDelivery,
                vbAI
        );

        hbCards.setAlignment(Pos.CENTER);
        hbCards.setMaxWidth(550);

        VBox vbLeft = new VBox(
                20,
                imageView,
                hbCards
        );

        vbLeft.setAlignment(Pos.TOP_CENTER);
        vbLeft.setPadding(new Insets(20));

        // ================= LOGIN BOX =================

        VBox loginBox = new VBox(15);

        loginBox.setPrefSize(380, 400);
        loginBox.setMaxWidth(380);

        loginBox.setPadding(
                new Insets(30)
        );

        loginBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.12),15,0,2,3);"
        );

        // ================= WELCOME =================

        Text welcome = new Text("Welcome Back!");

        welcome.setStyle(
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#202020;"
        );

        Text desc = new Text(
                "Login to discover nearby shops, AI-powered\n" +
                "deals, and local offers."
        );

        desc.setStyle(
                "-fx-font-size:15px;" +
                "-fx-fill:#666666;"
        );

        // ================= EMAIL =================

        Label mobileLbl = new Label(
                "Mobile Number or Email Address"
        );

        mobileLbl.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        TextField mobileField = new TextField();

        mobileField.setPromptText(
                "Enter mobile or email"
        );

        mobileField.setPrefHeight(45);
        mobileField.setMaxWidth(
                Double.MAX_VALUE
        );

        // ================= PASSWORD =================

        Label passLbl = new Label("Password");

        passLbl.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;"
        );

        PasswordField passField = new PasswordField();

        passField.setPromptText("Password");

        passField.setPrefHeight(45);

        passField.setMaxWidth(
                Double.MAX_VALUE
        );

        // ================= ERROR MESSAGE =================

        Label errorLabel = new Label();

        errorLabel.setText("");

        errorLabel.setStyle(
                "-fx-text-fill:#E53935;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        // ================= REMEMBER =================

        CheckBox cb = new CheckBox(
                "Remember Me"
        );

        Hyperlink forgot = new Hyperlink(
                "Forgot Password?"
        );

        forgot.setBorder(Border.EMPTY);

        HBox remember = new HBox(
                10,
                cb,
                forgot
        );

        remember.setAlignment(
                Pos.CENTER_LEFT
        );

        // ================= LOGIN BUTTON =================

        Button loginBtn = new Button("Login");

        loginBtn.setPrefHeight(45);

        loginBtn.setMaxWidth(
                Double.MAX_VALUE
        );

        loginBtn.setStyle(
                "-fx-background-color:#FF7A1A;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        controler controler =
                new controler();

        // ================= LOGIN ACTION =================

        loginBtn.setOnAction(event -> {

            String email =
                    mobileField.getText().trim();

            String password =
                    passField.getText();

            // Pehle error clear karo
            errorLabel.setText("");

            // Empty field check
            if (email.isEmpty() ||
                    password.isEmpty()) {

                errorLabel.setText(
                        "Invalid email or password"
                );

                return;
            }

            // Database login check
            boolean flag =
                    controler.logIn(
                            email,
                            password
                    );

            // ================= SUCCESS =================

            if (flag) {

                Userstorecontroller us =
                        new Userstorecontroller();

                String role =
                        us.getrole(email);

                // ================= SHOPKEEPER =================

                if ("shopkeeper".equalsIgnoreCase(role)) {

                    String userId =
                            controler.userId;

                    System.out.println(
                            "Login done successfully"
                    );

                    Dashbord db =
                            new Dashbord(userId);

                    Homepage.HomepageStage.setScene(
                            db.getDashbordScene()
                    );

                }

                // ================= WRONG ROLE =================

                else {

                    errorLabel.setText(
                            "Invalid email or password"
                    );
                }

            }

            // ================= LOGIN FAILED =================

            else {

                errorLabel.setText(
                        "Invalid email or password"
                );

                System.out.println(
                        "Login failed"
                );
            }
        });

        // ================= OR =================

        Separator s1 = new Separator();
        Separator s2 = new Separator();

        s1.setPrefWidth(90);
        s2.setPrefWidth(90);

        Label orLabel = new Label(
                "OR CONTINUE WITH"
        );

        orLabel.setStyle(
                "-fx-font-size:11px;" +
                "-fx-text-fill:#7A7A7A;" +
                "-fx-font-weight:bold;"
        );

        HBox hbOr = new HBox(
                10,
                s1,
                orLabel,
                s2
        );

        hbOr.setAlignment(
                Pos.CENTER
        );

        // ================= SOCIAL =================

        Button google = new Button("Google");

        google.setPrefWidth(140);
        google.setPrefHeight(40);

        Button apple = new Button("Apple");

        apple.setPrefWidth(140);
        apple.setPrefHeight(40);

        HBox social = new HBox(
                20,
                google,
                apple
        );

        social.setAlignment(
                Pos.CENTER
        );

        // ================= REGISTER =================

        Label l1 = new Label(
                "Don't have an account? "
        );

        Hyperlink registerNow =
                new Hyperlink("Register Now");

        registerNow.setBorder(
                Border.EMPTY
        );

        registerNow.setOnAction(event -> {

            Customer_Registration ar =
                    new Customer_Registration();

            Homepage.HomepageStage.setScene(
                    ar.getRagisterScene()
            );
        });

        HBox register = new HBox(
                l1,
                registerNow
        );

        register.setAlignment(
                Pos.CENTER
        );

        // ================= LOGIN CONTENT =================

        loginBox.getChildren().addAll(
                welcome,
                desc,
                mobileLbl,
                mobileField,
                passLbl,
                passField,
                errorLabel,
                remember,
                loginBtn,
                hbOr,
                social,
                register
        );

        // ================= CENTER =================

        HBox center = new HBox(
                60,
                vbLeft,
                loginBox
        );

        center.setStyle(
                "-fx-background-color:#eee5df;"
        );

        center.setAlignment(
                Pos.CENTER
        );

        center.setPadding(
                new Insets(30)
        );

        // ================= MAIN BORDER PANE =================

        BorderPane bp = new BorderPane();

        bp.setTop(header);
        bp.setCenter(center);

        bp.setStyle(
                "-fx-background-color:#FAF8F6;"
        );

        // ================= SCENE =================

        Scene scene = new Scene(
                bp,
                1550,
                850
        );

        LoginScene = scene;

        return LoginScene;
    }
}       
package com.kryox.view.Customer;

import javafx.application.Application;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Customer_Registration extends Application {

    @Override
    public void start(Stage myStage) throws Exception {
        Text t1 = new Text("BuyNeX");
        t1.setStyle("-fx-font-size:28px;-fx-font-weight:bold;-fx-fill:#9C3700;");

        HBox hb1 = new HBox(t1);
        hb1.setAlignment(Pos.CENTER);

        Text t2 = new Text("Support");
        t2.setStyle("-fx-font-size:14px;-fx-fill:#5C4033;");

        Text t3 = new Text("Partner Login");
        t3.setStyle("-fx-font-size:14px;-fx-font-weight:bold;-fx-fill:#C65A1E;");

        HBox hb2 = new HBox(20, t2, t3);
        hb2.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hb3 = new HBox(20, hb1, spacer, hb2);
        hb3.setPadding(new Insets(20, 30, 20, 30));
        hb3.setAlignment(Pos.CENTER_LEFT);

        Text heading = new Text("Create Your BuyNeX\nAccount");
        heading.setStyle("-fx-font-family:'Poppins';-fx-font-size:36px;-fx-font-weight:bold;-fx-fill:#202020;");
        heading.setTextAlignment(TextAlignment.CENTER);

        Text desc = new Text("Join BuyNeX and explore the best nearby shopping\nexperience.");
        desc.setStyle("-fx-font-family:'Poppins'; -fx-font-size:12px;-fx-fill:#6B6B6B;");
        desc.setTextAlignment(TextAlignment.CENTER);

        VBox titleBox = new VBox(8);
        titleBox.getChildren().addAll(heading, desc);
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setPadding(new Insets(5, 0, 0, 0));

        VBox registerBox = new VBox(10);
        registerBox.setPrefWidth(520);
        registerBox.setMaxWidth(520);
        registerBox.setPadding(new Insets(20));
        registerBox.setStyle("-fx-background-color:white;-fx-background-radius:20;-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.12),15,0,2,3);");

        Label nameLbl = new Label("Full Name");
        TextField nameField = new TextField();
        nameField.setPromptText("John Doe");
        nameField.setPrefHeight(32);

        Label mobileLbl = new Label("Mobile Number");
        TextField mobileField = new TextField();
        mobileField.setPromptText("+1 (555) 000-0000");
        mobileField.setPrefHeight(42);
        mobileField.setPrefWidth(200);
        Button verifyBtn = new Button("Verify");
        verifyBtn.setPrefSize(90, 42);
        verifyBtn.setStyle(
                "-fx-background-color:white;-fx-border-color:#D2691E;-fx-border-radius:10;-fx-background-radius:10;-fx-text-fill:#C65A1E;-fx-font-weight:bold;");
        HBox mobileBox = new HBox(10, mobileField, verifyBtn);

        Label emailLbl = new Label("Email Address");
        TextField emailField = new TextField();
        emailField.setPromptText("john@example.com");
        emailField.setPrefHeight(42);

        Label passLbl = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.setPrefHeight(42);
        passField.setPrefWidth(230);

        Label confirmLbl = new Label("Confirm Password");
        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm Password");
        confirmField.setPrefHeight(42);
        confirmField.setPrefWidth(230);

        VBox passBox = new VBox(5, passLbl, passField);
        VBox confirmBox = new VBox(5, confirmLbl, confirmField);
        HBox passwordRow = new HBox(15, passBox, confirmBox);

        Label locationLbl = new Label("Location");
        TextField locationField = new TextField();
        locationField.setPromptText("Enter delivery address manually");
        locationField.setPrefHeight(42);

        Button detectBtn = new Button("Auto Detect Current Location");

        detectBtn.setPrefWidth(480);
        detectBtn.setPrefHeight(42);
        detectBtn.setStyle("-fx-background-color:#F3F3F3;-fx-background-radius:10;");

        CheckBox terms = new CheckBox("I agree to the Terms & Conditions and Privacy Policy.");

        Button createBtn = new Button("Create Account");
        createBtn.setPrefWidth(480);
        createBtn.setPrefHeight(45);
        createBtn.setStyle(
                " -fx-background-color:#FF8300;-fx-text-fill:white;-fx-font-size:15px;-fx-font-weight:bold;-fx-background-radius:10;");

        Separator s1 = new Separator();
        Separator s2 = new Separator();
        Label or = new Label("OR");
        HBox orBox = new HBox(10, s1, or, s2);
        orBox.setAlignment(Pos.CENTER);

        Button google = new Button("Continue with Google");
        google.setPrefWidth(480);
        google.setPrefHeight(42);

        Label alreadyLbl = new Label("Already have an account? ");
        alreadyLbl.setStyle("-fx-font-size:13px;-fx-text-fill:#555555;");
        Hyperlink loginLink = new Hyperlink("Login Here");
        loginLink.setBorder(Border.EMPTY);
        loginLink.setStyle("-fx-font-size:13px;-fx-text-fill:#C65A1E;-fx-font-weight:bold;");

        HBox loginBox = new HBox(3, alreadyLbl, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        HBox nearbyBox = new HBox(10);
        nearbyBox.setAlignment(Pos.CENTER_LEFT);
        nearbyBox.setPadding(new Insets(8, 14, 8, 10));
        nearbyBox.setStyle("-fx-background-color: #F8FAFC;-fx-background-radius: 12;-fx-border-color: #E2E8F0;-fx-border-radius: 12;");
        Label shopIcon = new Label("🏪");
        shopIcon.setStyle("-fx-font-size: 20px;");
        VBox textBox = new VBox(2);
        Label title = new Label("Nearby Shops");
        title.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-text-fill: #1E293B;");
        Label active = new Label("24 hrs Active Now");
        active.setStyle("-fx-font-size: 11px;-fx-text-fill: #16A34A;-fx-font-weight: bold;");
        textBox.getChildren().addAll(title, active);
        nearbyBox.getChildren().addAll(shopIcon, textBox);
        registerBox.getChildren().addAll(
                nameLbl,
                nameField,
                mobileLbl,
                mobileBox,
                emailLbl,
                emailField,
                passwordRow,
                locationLbl,
                locationField,
                detectBtn,
                terms,
                createBtn,
                orBox,
                google,
                loginBox
                
        );
      

        VBox mainBox = new VBox(20, titleBox, registerBox);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(0, 0, 10, 0));
        mainBox.setMaxWidth(150);
        mainBox.setPrefHeight(300);

      

        VBox root = new VBox();
        root.getChildren().addAll(hb3, mainBox);
        root.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 1500, 900);

        BorderPane.setMargin(mainBox, Insets.EMPTY);

        myStage.setScene(scene);
        myStage.setMaximized(true);

        myStage.show();

    }

}

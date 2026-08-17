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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerLogin extends Application {

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

        HBox hb3 = new HBox(1200, hb1, hb2);
        hb3.setPadding(new Insets(20));
        hb3.setAlignment(Pos.CENTER);

        Image img = new Image("assets\\images\\hyperlocal.png");
        ImageView imageView = new ImageView(img);

        imageView.setFitWidth(550);
        imageView.setFitHeight(420);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        imageView.setCache(true);

        Rectangle clip = new Rectangle();
        clip.setWidth(550);
        clip.setHeight(420);
        clip.setArcWidth(30);
        clip.setArcHeight(30);

        imageView.setClip(clip);

        Image img1 = new Image("assets\\logo\\shopping_bag.png");
        ImageView imageView1 = new ImageView(img1);

        imageView1.setFitWidth(25); 
        imageView1.setFitHeight(25);

        Label lbl1 = new Label("500+ Shops");
        lbl1.setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-text-fill:#4A4A4A;");

        VBox vbShop = new VBox(8, imageView1, lbl1);
        vbShop.setAlignment(Pos.CENTER);
        vbShop.setPrefSize(140,80);
        vbShop.setStyle("-fx-background-color:#F8F8F8;-fx-background-radius:18;");
        vbShop.setStyle(" -fx-background-color: #F3F4F6;-fx-background-radius: 18;-fx-border-radius: 18;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.15), 15, 0.2, 2, 3);");

        Image img2 = new Image("assets\\logo\\fast_delivery.png");
        ImageView imageView2 = new ImageView(img2);

        imageView2.setFitWidth(25);
        imageView2.setFitHeight(25);

        Label lbl2 = new Label("Fast Delivery");
        lbl2.setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-text-fill:#4A4A4A;");

        VBox vbDelivery = new VBox(8, imageView2, lbl2);
        vbDelivery.setAlignment(Pos.CENTER);
        vbDelivery.setPrefSize(140,80);
        vbDelivery.setStyle("-fx-background-color:#F8F8F8;-fx-background-radius:18;");
        vbDelivery.setStyle(" -fx-background-color: #F3F4F6;-fx-background-radius: 18;-fx-border-radius: 18;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.15), 15, 0.2, 2, 3);");

        
        Image img3 = new Image("assets\\logo\\ai_powered.png");
        ImageView imageView3 = new ImageView(img3);

        imageView3.setFitWidth(25);
        imageView3.setFitHeight(25);

        Label lbl3 = new Label("AI Powered");
        lbl3.setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-text-fill:#4A4A4A;");

        VBox vbAI = new VBox(8, imageView3, lbl3);
        vbAI.setAlignment(Pos.CENTER);
        vbAI.setPrefSize(140,80);
        vbAI.setStyle("-fx-background-color:#F8F8F8;-fx-background-radius:18;");
        vbAI.setStyle(" -fx-background-color: #F3F4F6;-fx-background-radius: 18;-fx-border-radius: 18;-fx-effect: dropshadow(gaussian,rgba(0,0,0,0.15), 15, 0.2, 2, 3);");

        HBox hbCards = new HBox(20, vbShop, vbDelivery, vbAI);
        hbCards.setPrefWidth(550);
        hbCards.setAlignment(Pos.CENTER);

        VBox vbLeft = new VBox(20, imageView, hbCards);
        vbLeft.setPadding(new Insets(20));
        vbLeft.setAlignment(Pos.TOP_LEFT);


        VBox loginBox = new VBox(15);
        loginBox.setPrefSize(380, 400);
        loginBox.setPadding(new Insets(30));
        loginBox.setTranslateY(-30);
        
        loginBox.setStyle("-fx-background-color: white;-fx-background-radius: 20;-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12),15,0,2,3);");

        Text welcome = new Text("Welcome Back!");
        welcome.setStyle("-fx-font-size:32px;-fx-font-weight:bold;-fx-fill:#202020;");

        Text desc = new Text("Login to discover nearby shops, AI-powered\ndeals, and local offers.");
        desc.setStyle("-fx-font-size:15px;-fx-fill:#666666;");

        Label mobileLbl = new Label("Mobile Number or Email Address");
        mobileLbl.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField mobileField = new TextField();
        mobileField.setPromptText("Enter mobile or email");
        mobileField.setPrefHeight(45);
        mobileField.setPrefWidth(320);

        Label passLbl = new Label("Password");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.setPrefHeight(45);

        CheckBox cb = new CheckBox("Remember Me");
        Hyperlink forgot = new Hyperlink("Forgot Password?");
        forgot.setBorder(Border.EMPTY);

        HBox remember = new HBox(90, cb, forgot);
        remember.setAlignment(Pos.CENTER_LEFT);

        Button loginBtn = new Button("Login");
        loginBtn.setPrefWidth(320);
        loginBtn.setPrefHeight(45);
        loginBtn.setStyle("-fx-background-color:#FF7A1A;-fx-text-fill:white;-fx-font-size:15px;-fx-font-weight:bold;-fx-background-radius:10;");
        
        Button google = new Button("Google");
        google.setPrefWidth(140);

        Button apple = new Button("Apple");
        apple.setPrefWidth(140);

        HBox social = new HBox(20, google, apple);
        social.setAlignment(Pos.CENTER);

        HBox register = new HBox();
        Label l1 = new Label("Don't have an account? ");

        Hyperlink registerNow = new Hyperlink("Register Now");
        registerNow.setBorder(Border.EMPTY);

        Separator s1 = new Separator();
        Separator s2 = new Separator();

        s1.setPrefWidth(90);
        s2.setPrefWidth(90);

        Label orLabel = new Label("OR CONTINUE WITH");
        orLabel.setStyle("-fx-font-size:11px;-fx-text-fill:#7A7A7A;-fx-font-weight:bold;");

        HBox hbOr = new HBox(10, s1, orLabel, s2);
        hbOr.setAlignment(Pos.CENTER);

        register.getChildren().addAll(l1, registerNow);
        loginBox.getChildren().addAll(
                welcome,
                desc,
                mobileLbl,
                mobileField,
                passLbl,
                passField,
                remember,
                loginBtn,
                hbOr,
                social,
                register
        );

        
        BorderPane bp=new BorderPane();
        HBox center = new HBox(60);

        center.setPadding(new Insets(30));

        center.getChildren().addAll(vbLeft, loginBox);
        center.setAlignment(Pos.TOP_CENTER);

        bp.setCenter(center);
        bp.setTop(hb3);
    
        Scene scene=new Scene(bp );
        myStage.setScene(scene);
        myStage.setMaximized(true);

        myStage.show();
        
    }

    
}

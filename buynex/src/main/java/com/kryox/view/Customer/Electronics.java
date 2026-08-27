package com.kryox.view.Customer;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;


public class Electronics  {
        private Scene Electrinicscene;
         private String userId;

    public Electronics(String userId) {
        this.userId = userId;
    }

        Scene getElectrScene(){
                // =====================================================
        // SHADOWS
        // =====================================================

        DropShadow shadow = new DropShadow();
        shadow.setRadius(18);
        shadow.setSpread(0.05);
        shadow.setOffsetX(5);
        shadow.setOffsetY(0);
        shadow.setColor(Color.rgb(0, 0, 0, 0.14));

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(14);
        cardShadow.setOffsetY(5);
        cardShadow.setSpread(0.02);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.10));

        DropShadow productShadow = new DropShadow();
        productShadow.setRadius(8);
        productShadow.setOffsetY(2);
        productShadow.setSpread(0.02);
        productShadow.setColor(Color.rgb(0, 0, 0, 0.08));

        // =====================================================
        // LOGO
        // =====================================================

        Label name = new Label("BuyNeX");
        name.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #E87500;"
        );

        // =====================================================
        // PREMIUM SHOPPER
        // =====================================================

        HBox premiumBox = new HBox(9);
        premiumBox.setPrefSize(205, 58);
        premiumBox.setMinSize(205, 58);
        premiumBox.setMaxSize(205, 58);
        premiumBox.setAlignment(Pos.CENTER_LEFT);
        premiumBox.setPadding(new Insets(8, 13, 8, 13));
        premiumBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #E9E2EA;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 15;"
        );
        premiumBox.setEffect(cardShadow);

        VBox textBox = new VBox(3);
        Label title = new Label("Premium Shopper");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label subtitle = new Label("● AI Assistant Active");
        subtitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        textBox.getChildren().addAll(title, subtitle);
        premiumBox.getChildren().add(textBox);

        // =====================================================
        // DASHBOARD
        // =====================================================

        Image di = new Image("/assets/images/Dashbord/dashboard.png");
        ImageView div = new ImageView(di);
        div.setFitHeight(21);
        div.setFitWidth(21);
        div.setPreserveRatio(true);

        Button lefButton1 = new Button("Dashboard");
        lefButton1.setPrefWidth(125);
        lefButton1.setPrefHeight(38);
        lefButton1.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        HBox hbInDashboard = new HBox(17, div, lefButton1);
        hbInDashboard.setPrefWidth(205);
        hbInDashboard.setMinWidth(205);
        hbInDashboard.setMaxWidth(205);
        hbInDashboard.setPrefHeight(42);
        hbInDashboard.setMinHeight(42);
        hbInDashboard.setMaxHeight(42);
        hbInDashboard.setPadding(new Insets(2, 8, 2, 13));
        hbInDashboard.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

        hbInDashboard.setOnMouseEntered(e -> {
            hbInDashboard.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
            lefButton1.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        hbInDashboard.setOnMouseExited(e -> {
            hbInDashboard.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
            lefButton1.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        // =====================================================
        // NEARBY SHOPS
        // =====================================================

        Image di2 = new Image("/assets/images/store.png");
        ImageView div2 = new ImageView(di2);
        div2.setFitHeight(23);
        div2.setFitWidth(23);
        div2.setPreserveRatio(true);

        Button lefButton2 = new Button("Nearby Shops");
        lefButton2.setPrefWidth(125);
        lefButton2.setPrefHeight(38);
        lefButton2.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );
        lefButton2.setOnAction(event->{
                neaby_shope ns=new neaby_shope(userId);
                Homepage.HomepageStage.setScene(ns.getNearby_shopes(null));
        });

        HBox hbInDashboard2 = new HBox(17, div2, lefButton2);
        hbInDashboard2.setPrefWidth(205);
        hbInDashboard2.setMinWidth(205);
        hbInDashboard2.setMaxWidth(205);
        hbInDashboard2.setPrefHeight(42);
        hbInDashboard2.setMinHeight(42);
        hbInDashboard2.setMaxHeight(42);
        hbInDashboard2.setPadding(new Insets(2, 8, 2, 13));
        hbInDashboard2.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard2.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

        hbInDashboard2.setOnMouseEntered(e -> {
            hbInDashboard2.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
            lefButton2.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        hbInDashboard2.setOnMouseExited(e -> {
            hbInDashboard2.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
            lefButton2.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        // =====================================================
        // DEALS
        // =====================================================

        Image di3 = new Image("/assets/images/Dashbord/hot-sale.png");
        ImageView div3 = new ImageView(di3);
        div3.setFitHeight(23);
        div3.setFitWidth(23);
        div3.setPreserveRatio(true);

        Button lefButton3 = new Button("Deals");
        lefButton3.setPrefWidth(125);
        lefButton3.setPrefHeight(38);
        lefButton3.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        HBox hbInDashboard3 = new HBox(17, div3, lefButton3);
        hbInDashboard3.setPrefWidth(205);
        hbInDashboard3.setMinWidth(205);
        hbInDashboard3.setMaxWidth(205);
        hbInDashboard3.setPrefHeight(42);
        hbInDashboard3.setMinHeight(42);
        hbInDashboard3.setMaxHeight(42);
        hbInDashboard3.setPadding(new Insets(2, 8, 2, 13));
        hbInDashboard3.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard3.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

        hbInDashboard3.setOnMouseEntered(e -> {
            hbInDashboard3.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
            lefButton3.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        hbInDashboard3.setOnMouseExited(e -> {
            hbInDashboard3.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
            lefButton3.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        // =====================================================
        // MY ORDERS
        // =====================================================

        Image di4 = new Image("/assets/images/Dashbord/package.png");
        ImageView div4 = new ImageView(di4);
        div4.setFitHeight(23);
        div4.setFitWidth(23);
        div4.setPreserveRatio(true);

        Button lefButton4 = new Button("My Orders");
        lefButton4.setPrefWidth(125);
        lefButton4.setPrefHeight(38);
        lefButton4.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        HBox hbInDashboard4 = new HBox(17, div4, lefButton4);
        hbInDashboard4.setPrefWidth(205);
        hbInDashboard4.setMinWidth(205);
        hbInDashboard4.setMaxWidth(205);
        hbInDashboard4.setPrefHeight(42);
        hbInDashboard4.setMinHeight(42);
        hbInDashboard4.setMaxHeight(42);
        hbInDashboard4.setPadding(new Insets(2, 8, 2, 13));
        hbInDashboard4.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard4.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

        hbInDashboard4.setOnMouseEntered(e -> {
            hbInDashboard4.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
            lefButton4.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        hbInDashboard4.setOnMouseExited(e -> {
            hbInDashboard4.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
            lefButton4.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        // =====================================================
        // ANALYTICS
        // =====================================================

        Image di5 = new Image("/assets/images/Dashbord/line-chart.png");
        ImageView div5 = new ImageView(di5);
        div5.setFitHeight(23);
        div5.setFitWidth(23);
        div5.setPreserveRatio(true);

        Button lefButton5 = new Button("Analytics");
        lefButton5.setPrefWidth(125);
        lefButton5.setPrefHeight(38);
        lefButton5.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        HBox hbInDashboard5 = new HBox(17, div5, lefButton5);
        hbInDashboard5.setPrefWidth(205);
        hbInDashboard5.setMinWidth(205);
        hbInDashboard5.setMaxWidth(205);
        hbInDashboard5.setPrefHeight(42);
        hbInDashboard5.setMinHeight(42);
        hbInDashboard5.setMaxHeight(42);
        hbInDashboard5.setPadding(new Insets(2, 8, 2, 13));
        hbInDashboard5.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard5.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

        hbInDashboard5.setOnMouseEntered(e -> {
            hbInDashboard5.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
            lefButton5.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        hbInDashboard5.setOnMouseExited(e -> {
            hbInDashboard5.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
            lefButton5.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-border-width: 0;" +
                    "-fx-padding: 0;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-cursor: hand;"
            );
        });

        // =====================================================
        // UPGRADE CARD
        // =====================================================

        VBox upgradeCard = new VBox(7);
        upgradeCard.setPrefWidth(205);
        upgradeCard.setMinWidth(205);
        upgradeCard.setMaxWidth(205);
        upgradeCard.setPrefHeight(112);
        upgradeCard.setPadding(new Insets(15));
        upgradeCard.setAlignment(Pos.CENTER_LEFT);

        LinearGradient upgradeGradient = new LinearGradient(
                0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#25262A")),
                new Stop(1, Color.web("#45474D"))
        );

        upgradeCard.setBackground(
                new Background(
                        new BackgroundFill(
                                upgradeGradient,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        Label upgradeTitle = new Label("Unlock Gold");
        upgradeTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label upgradeText = new Label("Smarter deals & exclusive rewards");
        upgradeText.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #BEBFC3;"
        );

        Button upgradeGold = new Button("Upgrade to Gold");
        upgradeGold.setPrefWidth(175);
        upgradeGold.setPrefHeight(30);
        upgradeGold.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        upgradeCard.getChildren().addAll(upgradeTitle, upgradeText, upgradeGold);

        // =====================================================
        // SETTINGS
        // =====================================================

        Image di6 = new Image("/assets/images/Dashbord/category.png");
        ImageView div6 = new ImageView(di6);
        div6.setFitHeight(19);
        div6.setFitWidth(19);
        div6.setPreserveRatio(true);

        Button lefButton6 = new Button("Settings");
        lefButton6.setPrefWidth(135);
        lefButton6.setPrefHeight(34);
        lefButton6.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );
        lefButton6.setOnAction(event->{
                Seting se=new Seting(userId);
                Runnable callbacktoDashborad=new Runnable() {
                        public void run(){
                                
                        }
                };
                Homepage.HomepageStage.setScene(se.getSetingscene(callbacktoDashborad=null));
        });

        HBox hbInDashboard6 = new HBox(10, div6, lefButton6);
        hbInDashboard6.setPrefWidth(205);
        hbInDashboard6.setMinWidth(205);
        hbInDashboard6.setMaxWidth(205);
        hbInDashboard6.setPrefHeight(34);
        hbInDashboard6.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard6.setPadding(new Insets(0, 8, 0, 18));

        // =====================================================
        // HELP
        // =====================================================

        Image di7 = new Image("/assets/images/Dashbord/question.png");
        ImageView div7 = new ImageView(di7);
        div7.setFitHeight(19);
        div7.setFitWidth(19);
        div7.setPreserveRatio(true);

        Button lefButton7 = new Button("Help & Support");
        lefButton7.setPrefWidth(135);
        lefButton7.setPrefHeight(34);
        lefButton7.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 500;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );

        HBox hbInDashboard7 = new HBox(10, div7, lefButton7);
        hbInDashboard7.setPrefWidth(205);
        hbInDashboard7.setMinWidth(205);
        hbInDashboard7.setMaxWidth(205);
        hbInDashboard7.setPrefHeight(34);
        hbInDashboard7.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard7.setPadding(new Insets(0, 8, 0, 18));

        // =====================================================
        // LEFT BOX
        // =====================================================

        VBox leftBox = new VBox(14);
        leftBox.setPrefWidth(245);
        leftBox.setMinWidth(245);
        leftBox.setMaxWidth(245);
        leftBox.setPrefHeight(800);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setPadding(new Insets(27, 20, 20, 20));
        leftBox.setStyle("-fx-background-color: #ebccb7;");
        leftBox.setEffect(shadow);

        Region sidebarSpacer = new Region();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

        leftBox.getChildren().addAll(
                name,
                premiumBox,
                hbInDashboard,
                hbInDashboard2,
                hbInDashboard3,
                hbInDashboard4,
                hbInDashboard5,
                sidebarSpacer,
                upgradeCard,
                hbInDashboard6,
                hbInDashboard7
        );

        // =====================================================
        // TOP NAVIGATION
        // =====================================================

        Button t1 = new Button("Offers");
        Button t2 = new Button("Shops");
        Button t3 = new Button("Support");

        String topButtonStyle = "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;";

        t1.setStyle(topButtonStyle);
        t2.setStyle(topButtonStyle);
        t3.setStyle(topButtonStyle);

        t1.setOnMouseEntered(e -> t1.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;"
        ));

        t1.setOnMouseExited(e -> t1.setStyle(topButtonStyle));

        t2.setOnMouseEntered(e -> t2.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;"
        ));

        t2.setOnMouseExited(e -> t2.setStyle(topButtonStyle));

        t3.setOnMouseEntered(e -> t3.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;"
        ));

        t3.setOnMouseExited(e -> t3.setStyle(topButtonStyle));

        HBox topLinks = new HBox(6, t1, t2, t3);
        topLinks.setAlignment(Pos.CENTER_LEFT);

        // =====================================================
        // SEARCH
        // =====================================================

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search products, shops or deals with AI...");
        searchBox.setPrefWidth(310);
        searchBox.setPrefHeight(39);
        searchBox.setStyle(
                "-fx-background-color: #F8F7FA;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #E5E1E8;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0 16 0 16;" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #444444;" +
                "-fx-prompt-text-fill: #999999;"
        );


        // =====================================================
        // LOCATION
        // =====================================================

        Label locationIcon = new Label("📍");
        Label locationText = new Label("Downtown Manhattan⌄");
        locationText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #555555;"
        );

        HBox locationBox = new HBox(4, locationIcon, locationText);
        locationBox.setAlignment(Pos.CENTER_LEFT);
        locationBox.setTranslateX(-200);

        // =====================================================
        // ACTIONS
        // =====================================================

        Button b1 = new Button("🔔");
        Button b2 = new Button("🛒");
        Button b3 = new Button("●");

        String actionStyle = "-fx-background-color: white;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #E7E2E9;" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        b1.setPrefSize(37, 37);
        b2.setPrefSize(37, 37);
        b3.setPrefSize(37, 37);

        b1.setStyle(actionStyle);
        b2.setStyle(actionStyle);
        b3.setStyle(actionStyle);

        String actionHoverStyle = "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #FFBD95;" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        b1.setOnMouseEntered(e -> b1.setStyle(actionHoverStyle));
        b1.setOnMouseExited(e -> b1.setStyle(actionStyle));

        b2.setOnMouseEntered(e -> b2.setStyle(actionHoverStyle));
        b2.setOnMouseExited(e -> b2.setStyle(actionStyle));

        b3.setOnMouseEntered(e -> b3.setStyle(actionHoverStyle));
        b3.setOnMouseExited(e -> b3.setStyle(actionStyle));
        b2.setOnAction(evene->{
                ShoppingCartUI sui=new ShoppingCartUI(userId);
                Homepage.HomepageStage.setScene(sui.getaddcartScene());
        });

        HBox actionBox = new HBox(7, b1, b2, b3);
        actionBox.setAlignment(Pos.CENTER_RIGHT);
        actionBox.setTranslateX(-200);

        // =====================================================
        // NAVIGATION SPACERS
        // =====================================================

        Region navSpacer1 = new Region();
        HBox.setHgrow(navSpacer1, Priority.ALWAYS);
        Region navSpacer2 = new Region();
        HBox.setHgrow(navSpacer2, Priority.ALWAYS);

        // =====================================================
        // NAV BOX
        // =====================================================

        HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
        navBox.setPrefHeight(68);
        navBox.setPadding(new Insets(12, 24, 12, 24));
        navBox.setTranslateX(-25);
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-color: #ebccb7;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // =====================================================
        // HERO BOX
        // =====================================================

        HBox hbright = new HBox(35);
        hbright.setPrefWidth(1200);
        hbright.setPrefHeight(315);
        hbright.setPadding(new Insets(34, 40, 34, 40));
        hbright.setAlignment(Pos.CENTER_LEFT);

        LinearGradient darkGradient = new LinearGradient(
                0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#242529")),
                new Stop(0.55, Color.web("#303136")),
                new Stop(1.0, Color.web("#563A2B"))
        );

        hbright.setBackground(
                new Background(
                        new BackgroundFill(
                                darkGradient,
                                new CornerRadii(22),
                                Insets.EMPTY
                        )
                )
        );
        hbright.setEffect(cardShadow);

        // =====================================================
        // HERO LEFT CONTENT
        // =====================================================

        VBox leftContent = new VBox(13);
        leftContent.setPrefWidth(600);
        leftContent.setAlignment(Pos.CENTER_LEFT);

        Label badge = new Label("✦  NEXT-GEN INTELLIGENCE");
        badge.setStyle(
                "-fx-background-color: #503629;" +
                "-fx-text-fill: #FF9D67;" +
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 7 13 7 13;"
        );

        Text heading = new Text("What are you\nlooking for today?");
        heading.setStyle(
                "-fx-fill: white;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: 900;"
        );

        Text description = new Text(
                "Your hyper-local AI is ready to source, compare,\n" +
                "and deliver from your favorite downtown spots."
        );
        description.setStyle(
                "-fx-fill: #C9C9C9;" +
                "-fx-font-size: 11px;"
        );

        Button groceries = new Button("♧  Combine Groceries");
        groceries.setPrefHeight(42);
        groceries.setStyle(
                "-fx-background-color: #45464B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 16 10 16;" +
                "-fx-cursor: hand;"
        );
        groceries.setOnAction(event->{

        });
        

        Button bestPrice = new Button("⌁  Find Best Price");
        bestPrice.setPrefHeight(42);
        bestPrice.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 17 10 17;" +
                "-fx-cursor: hand;"
        );

        HBox buttons = new HBox(10, groceries, bestPrice);
        leftContent.getChildren().addAll(badge, heading, description, buttons);

        // =====================================================
        // AI RECOMMENDATION CARD
        // =====================================================

        VBox notification = new VBox(13);
        notification.setPrefWidth(300);
        notification.setMinWidth(300);
        notification.setMaxWidth(300);
        notification.setPrefHeight(245);
        notification.setPadding(new Insets(20));
        notification.setAlignment(Pos.TOP_LEFT);
        notification.setStyle(
                "-fx-background-color: #ECEAE9;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: rgba(255,255,255,0.35);" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );
        notification.setEffect(cardShadow);

        Label aiCircle = new Label("✦");
        aiCircle.setPrefSize(38, 38);
        aiCircle.setAlignment(Pos.CENTER);
        aiCircle.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;"
        );

        VBox aiHeading = new VBox(2);
        Label aiTitle = new Label("AI Recommendation");
        aiTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label aiStatus = new Label("PERSONALIZED FOR YOU");
        aiStatus.setStyle(
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        aiHeading.getChildren().addAll(aiTitle, aiStatus);

        HBox aiHeader = new HBox(10, aiCircle, aiHeading);
        aiHeader.setAlignment(Pos.CENTER_LEFT);

        Text notificationText = new Text(
                "You usually order coffee beans on Tuesdays.\n\n" +
                "Artisan Pantry has your favorite\n" +
                "brand in stock today."
        );
        notificationText.setStyle(
                "-fx-fill: #444444;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Region notificationSpacer = new Region();
        VBox.setVgrow(notificationSpacer, Priority.ALWAYS);

        Button dismiss = new Button("Dismiss");
        dismiss.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 9px;" +
                "-fx-cursor: hand;"
        );

        Button checkStock = new Button("Check Stock");
        checkStock.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8 13 8 13;" +
                "-fx-cursor: hand;"
        );

        HBox notificationButtons = new HBox(8, dismiss, checkStock);
        notificationButtons.setAlignment(Pos.CENTER_RIGHT);

        notification.getChildren().addAll(
                aiHeader,
                notificationText,
                notificationSpacer,
                notificationButtons
        );

        hbright.getChildren().addAll(leftContent, notification);

        // =====================================================
        // CATEGORY TITLE
        // =====================================================

        HBox categoryHeader = new HBox();
        categoryHeader.setAlignment(Pos.CENTER_LEFT);

        Label categoryTitle = new Label("Browse by Category");
        categoryTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Label viewAll = new Label("View all  →");
        viewAll.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-cursor: hand;"
        );

        Region categorySpacer = new Region();
        HBox.setHgrow(categorySpacer, Priority.ALWAYS);

        categoryHeader.getChildren().addAll(categoryTitle, categorySpacer, viewAll);

        // =====================================================
        // CATEGORY BUTTONS
        // =====================================================

        Button grocerie = new Button("🛒\nGroceries");
        grocerie.setOnAction(event->{
                Electronics electronics=new Electronics(userId);

        });
        Button electronics = new Button("▣\nElectronics");
        Button fashion = new Button("♧\nFashion");
        Button health = new Button("✚\nHealth");
        Button home = new Button("⌂\nHome");
        Button gifts = new Button("♧\nGifts");
        Button beauty = new Button("✦\nBeauty");
        Button pharmacy = new Button("✚\nPharmacy");
        Button sports = new Button("⚽\nSports");
        Button furniture = new Button("⌂\nFurniture");
        Button toys = new Button("♟\nToys");
        Button stationery = new Button("✎\nStationery");

        String categoryStyle = "-fx-background-color: white;" +
                "-fx-text-fill: #D94F0B;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #E8E3EA;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 15;" +
                "-fx-padding: 10;" +
                "-fx-cursor: hand;";

        String categoryHoverStyle = "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #FFB889;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 15;" +
                "-fx-padding: 10;" +
                "-fx-cursor: hand;";

        Button[] categoryButtons = {
                grocerie, electronics, fashion, health, home, gifts,
                beauty, pharmacy, sports, furniture, toys, stationery
        };

        for (Button button : categoryButtons) {
            button.setPrefWidth(105);
            button.setPrefHeight(78);
            button.setMinWidth(105);
            button.setMinHeight(78);
            button.setMaxWidth(105);
            button.setMaxHeight(78);
            button.setStyle(categoryStyle);

            button.setOnMouseEntered(e -> button.setStyle(categoryHoverStyle));
            button.setOnMouseExited(e -> button.setStyle(categoryStyle));
        }

        // =====================================================
        // CATEGORY ROWS
        // =====================================================

        HBox categoriesRow1 = new HBox(12, grocerie, electronics, fashion, health, home, gifts,beauty, pharmacy, sports, furniture, toys, stationery);
        categoriesRow1.setAlignment(Pos.CENTER_LEFT);

        

        // =====================================================
        // CATEGORY SECTION
        // =====================================================

        VBox categorySection = new VBox(13, categoryHeader, categoriesRow1);
        categorySection.setPadding(new Insets(0, 8, 20, 8));

        // =====================================================
        // RIGHT VBOX WITH DASHBOARD COMPONENTS + ELECTRONICS
        // =====================================================

        VBox rightContent = new VBox(22);
        rightContent.setPadding(new Insets(0, 26, 20, 26));
        rightContent.setStyle("-fx-background-color: #F7F5F8;");

        // =====================================================
        // MAIN LAYOUT - HBox with Filters (Left) and Products (Right)
        // =====================================================

        HBox mainLayout = new HBox(25);
        mainLayout.setPadding(new Insets(0, 0, 0, 0));
        mainLayout.setAlignment(Pos.TOP_LEFT);

        // =====================================================
        // LEFT SIDE - FILTERS
        // =====================================================

        VBox filtersBox = new VBox(8);
        filtersBox.setPrefWidth(280);
        filtersBox.setMinWidth(280);
        filtersBox.setMaxWidth(280);
        filtersBox.setPadding(new Insets(20, 20, 20, 20));
        filtersBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        filtersBox.setEffect(productShadow);

        // Filters Title
        Label filtersTitle = new Label("# Filters");
        filtersTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 22px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        // BRAND Section
        Label brandTitle = new Label("BRAND");
        brandTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;" +
                "-fx-padding: 10 0 5 0;"
        );

        VBox brandBox = new VBox(6);
        String[] brands = {"Apple", "Samsung", "Sony", "Dell", "Logitech"};
        for (String brand : brands) {
            HBox brandItem = new HBox(8);
            brandItem.setAlignment(Pos.CENTER_LEFT);
            Label checkMark = new Label("☐");
            checkMark.setStyle("-fx-font-size: 14px; -fx-text-fill: #777777;");
            Label brandLabel = new Label(brand);
            brandLabel.setStyle(
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #444444;"
            );
            brandItem.getChildren().addAll(checkMark, brandLabel);
            brandBox.getChildren().add(brandItem);
        }

        // CATEGORY Section
        Label categoryTitle2 = new Label("CATEGORY");
        categoryTitle2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;" +
                "-fx-padding: 10 0 5 0;"
        );

        VBox categoryBox = new VBox(6);
        String[] categories = {"Laptops", "Smartphones", "Audio", "Accessories"};
        for (String cat : categories) {
            HBox catItem = new HBox(8);
            catItem.setAlignment(Pos.CENTER_LEFT);
            Label checkMark = new Label("☐");
            checkMark.setStyle("-fx-font-size: 14px; -fx-text-fill: #777777;");
            Label catLabel = new Label(cat);
            catLabel.setStyle(
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #444444;"
            );
            catItem.getChildren().addAll(checkMark, catLabel);
            categoryBox.getChildren().add(catItem);
        }

        // Divider
        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #EAE6EC;");
        divider.setPadding(new Insets(5, 0, 5, 0));

        // PRICE RANGE Section
        Label priceTitle = new Label("PRICE RANGE");
        priceTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;" +
                "-fx-padding: 10 0 5 0;"
        );

        Label priceRange = new Label("$ Min - $ Max");
        priceRange.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        Label priceValue = new Label("$10 - $2000+");
        priceValue.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 600;" +
                "-fx-text-fill: #222222;"
        );

        // RATING Section
        Label ratingTitle = new Label("RATING");
        ratingTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;" +
                "-fx-padding: 10 0 5 0;"
        );

        Label ratingValue = new Label("★★★★★ 4.0 & Up");
        ratingValue.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 600;" +
                "-fx-text-fill: #FF6900;"
        );

        // Add all to filters
        filtersBox.getChildren().addAll(
                filtersTitle,
                brandTitle, brandBox,
                categoryTitle2, categoryBox,
                divider,
                priceTitle, priceRange, priceValue,
                ratingTitle, ratingValue
        );

        // =====================================================
        // RIGHT SIDE - ELECTRONICS CONTENT
        // =====================================================

        VBox productsContent = new VBox(15);
        productsContent.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(productsContent, Priority.ALWAYS);

        // Electronics Title
        Label electronicsTitle = new Label("# Electronics");
        electronicsTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        // =====================================================
        // FEATURED PROMOTION BANNER
        // =====================================================

        VBox promotionBanner = new VBox(6);
        promotionBanner.setPadding(new Insets(20, 25, 20, 25));
        promotionBanner.setStyle(
                "-fx-background-color: linear-gradient(to right, #1A1A2E, #16213E);" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;"
        );
        promotionBanner.setEffect(cardShadow);

        Label promoLabel = new Label("Featured Promotion");
        promoLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #FF9D67;"
        );

        Label promoTitle = new Label("Next-Gen Tech");
        promoTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: white;"
        );

        Label promoDesc = new Label("Up to 30% off on premium laptops and smartphones.");
        promoDesc.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #CCCCCC;"
        );

        Button shopSale = new Button("Shop the Sale →");
        shopSale.setPrefWidth(140);
        shopSale.setPrefHeight(36);
        shopSale.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-cursor: hand;"
        );

        shopSale.setOnMouseEntered(e -> {
            shopSale.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-cursor: hand;"
            );
        });

        shopSale.setOnMouseExited(e -> {
            shopSale.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-cursor: hand;"
            );
        });

        promotionBanner.getChildren().addAll(promoLabel, promoTitle, promoDesc, shopSale);

        // =====================================================
        // PRODUCTS SECTION - VERTICAL PRODUCT CARDS
        // =====================================================

        // Cart data for this page
        java.util.List<String> cartItems = new java.util.ArrayList<>();

        VBox productsBox = new VBox(12);
        productsBox.setPadding(new Insets(5, 0, 10, 0));

        HBox productGrid = new HBox(16);
        productGrid.setAlignment(Pos.TOP_LEFT);
        productGrid.setFillHeight(false);

        // -----------------------------------------------------
        // Product Card 1 - MacBook Pro M3
        // -----------------------------------------------------
        VBox product1 = new VBox(9);
        product1.setPrefWidth(285);
        product1.setMinWidth(285);
        product1.setMaxWidth(285);
        product1.setPrefHeight(350);
        product1.setPadding(new Insets(12));
        product1.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1;"
        );
        product1.setEffect(productShadow);

        VBox imageBox1 = new VBox();
        imageBox1.setAlignment(Pos.CENTER);
        imageBox1.setPrefSize(261, 205);
        imageBox1.setMinSize(261, 205);
        imageBox1.setMaxSize(261, 205);
        imageBox1.setStyle(
                "-fx-background-color: #F7F7F7;" +
                "-fx-background-radius: 12;"
        );

        java.net.URL imageUrl1 = getClass().getResource("/assets/images/Electronics/macbook.png");
        if (imageUrl1 != null) {
            ImageView productImage1 = new ImageView(new Image(imageUrl1.toExternalForm()));
            productImage1.setFitWidth(230);
            productImage1.setFitHeight(180);
            productImage1.setPreserveRatio(true);
            imageBox1.getChildren().add(productImage1);
        } else {
            Label fallback1 = new Label("💻");
            fallback1.setStyle("-fx-font-size: 75px;");
            imageBox1.getChildren().add(fallback1);
        }

        Label name1 = new Label("MacBook Pro M3");
        name1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label brand1 = new Label("Apple");
        brand1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox bottom1 = new HBox();
        bottom1.setAlignment(Pos.CENTER_LEFT);

        Label price1 = new Label("$1,999");
        price1.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Region priceSpacer1 = new Region();
        HBox.setHgrow(priceSpacer1, Priority.ALWAYS);

        Button addBtn1 = new Button("+  Add to Cart");
        addBtn1.setPrefHeight(34);
        addBtn1.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-cursor: hand;"
        );
        addBtn1.setOnAction(e -> {
            cartItems.add("MacBook Pro M3 - $1,999");
            addBtn1.setText("✓ Added");
            addBtn1.setStyle(
                    "-fx-background-color: #2E9B57;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 17;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-cursor: hand;"
            );
            b2.setText("🛒 " + cartItems.size());
        });

        bottom1.getChildren().addAll(price1, priceSpacer1, addBtn1);
        product1.getChildren().addAll(imageBox1, name1, brand1, bottom1);

        // -----------------------------------------------------
        // Product Card 2 - Samsung Galaxy S24 Ultra
        // -----------------------------------------------------
        VBox product2 = new VBox(9);
        product2.setPrefWidth(285);
        product2.setMinWidth(285);
        product2.setMaxWidth(285);
        product2.setPrefHeight(350);
        product2.setPadding(new Insets(12));
        product2.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1;"
        );
        product2.setEffect(productShadow);

        VBox imageBox2 = new VBox();
        imageBox2.setAlignment(Pos.CENTER);
        imageBox2.setPrefSize(261, 205);
        imageBox2.setMinSize(261, 205);
        imageBox2.setMaxSize(261, 205);
        imageBox2.setStyle(
                "-fx-background-color: #F7F7F7;" +
                "-fx-background-radius: 12;"
        );

        java.net.URL imageUrl2 = getClass().getResource("/assets/images/Electronics/s24.png");
        if (imageUrl2 != null) {
            ImageView productImage2 = new ImageView(new Image(imageUrl2.toExternalForm()));
            productImage2.setFitWidth(210);
            productImage2.setFitHeight(180);
            productImage2.setPreserveRatio(true);
            imageBox2.getChildren().add(productImage2);
        } else {
            Label fallback2 = new Label("📱");
            fallback2.setStyle("-fx-font-size: 75px;");
            imageBox2.getChildren().add(fallback2);
        }

        Label name2 = new Label("Samsung Galaxy S24 Ultra");
        name2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label brand2 = new Label("Samsung  •  5% OFF");
        brand2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox bottom2 = new HBox();
        bottom2.setAlignment(Pos.CENTER_LEFT);

        Label price2 = new Label("$1,199");
        price2.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Region priceSpacer2 = new Region();
        HBox.setHgrow(priceSpacer2, Priority.ALWAYS);

        Button addBtn2 = new Button("+  Add to Cart");
        addBtn2.setPrefHeight(34);
        addBtn2.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-cursor: hand;"
        );
        addBtn2.setOnAction(e -> {
            cartItems.add("Samsung Galaxy S24 Ultra - $1,199");
            addBtn2.setText("✓ Added");
            addBtn2.setStyle(
                    "-fx-background-color: #2E9B57;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 17;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-cursor: hand;"
            );
            b2.setText("🛒 " + cartItems.size());
        });

        bottom2.getChildren().addAll(price2, priceSpacer2, addBtn2);
        product2.getChildren().addAll(imageBox2, name2, brand2, bottom2);

        // -----------------------------------------------------
        // Product Card 3 - Sony WH-1000XM5
        // -----------------------------------------------------
        VBox product3 = new VBox(9);
        product3.setPrefWidth(285);
        product3.setMinWidth(285);
        product3.setMaxWidth(285);
        product3.setPrefHeight(350);
        product3.setPadding(new Insets(12));
        product3.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1;"
        );
        product3.setEffect(productShadow);

        VBox imageBox3 = new VBox();
        imageBox3.setAlignment(Pos.CENTER);
        imageBox3.setPrefSize(261, 205);
        imageBox3.setMinSize(261, 205);
        imageBox3.setMaxSize(261, 205);
        imageBox3.setStyle(
                "-fx-background-color: #F7F7F7;" +
                "-fx-background-radius: 12;"
        );

        java.net.URL imageUrl3 = getClass().getResource("/assets/images/Electronics/sony-headphones.png");
        if (imageUrl3 != null) {
            ImageView productImage3 = new ImageView(new Image(imageUrl3.toExternalForm()));
            productImage3.setFitWidth(220);
            productImage3.setFitHeight(175);
            productImage3.setPreserveRatio(true);
            imageBox3.getChildren().add(productImage3);
        } else {
            Label fallback3 = new Label("🎧");
            fallback3.setStyle("-fx-font-size: 75px;");
            imageBox3.getChildren().add(fallback3);
        }

        Label name3 = new Label("Sony WH-1000XM5 Headphones");
        name3.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label brand3 = new Label("Sony  •  ⭐ 4.7");
        brand3.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox bottom3 = new HBox();
        bottom3.setAlignment(Pos.CENTER_LEFT);

        Label price3 = new Label("$349");
        price3.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Region priceSpacer3 = new Region();
        HBox.setHgrow(priceSpacer3, Priority.ALWAYS);

        Button addBtn3 = new Button("+  Add to Cart");
        addBtn3.setPrefHeight(34);
        addBtn3.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17;" +
                "-fx-padding: 0 12 0 12;" +
                "-fx-cursor: hand;"
        );
        addBtn3.setOnAction(e -> {
            cartItems.add("Sony WH-1000XM5 Headphones - $349");
            addBtn3.setText("✓ Added");
            addBtn3.setStyle(
                    "-fx-background-color: #2E9B57;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 17;" +
                    "-fx-padding: 0 12 0 12;" +
                    "-fx-cursor: hand;"
            );
            b2.setText("🛒 " + cartItems.size());
        });

        bottom3.getChildren().addAll(price3, priceSpacer3, addBtn3);
        product3.getChildren().addAll(imageBox3, name3, brand3, bottom3);

        // Three vertical cards in one horizontal row, like the screenshot.
        productGrid.getChildren().addAll(product1, product2, product3);
        productsBox.getChildren().add(productGrid);

        // =====================================================
        // ADD TO RIGHT CONTENT
        // =====================================================

        productsContent.getChildren().addAll(
                electronicsTitle,
                promotionBanner,
                productsBox
        );

        // =====================================================
        // ADD TO MAIN LAYOUT
        // =====================================================

        mainLayout.getChildren().addAll(filtersBox, productsContent);

        // =====================================================
        // ADD TO RIGHT VBOX
        // =====================================================

        rightContent.getChildren().addAll(
                navBox,
                hbright,
                categorySection,
                mainLayout
        );

        // =====================================================
        // SUBTLE ORANGE BACKGROUND GLOW
        // =====================================================

        RadialGradient orangeGlow1 = new RadialGradient(
                0, 0, 0.84, 0.16, 0.42, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#FF9148", 0.25)),
                new Stop(0.40, Color.web("#FFD1B4", 0.12)),
                new Stop(1.0, Color.TRANSPARENT)
        );

        rightContent.setBackground(
                new Background(
                        new BackgroundFill(
                                orangeGlow1,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(rightContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // =====================================================
        // RIGHT VBOX FINAL
        // =====================================================

        VBox Rightvbox = new VBox(0);
        Rightvbox.setPadding(new Insets(0, 0, 0, 0));
        Rightvbox.setStyle("-fx-background-color: #F7F5F8;");
        Rightvbox.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // =====================================================
        // BORDER PANE
        // =====================================================

        BorderPane mainBox = new BorderPane();
        mainBox.setLeft(leftBox);
        mainBox.setCenter(Rightvbox);
        mainBox.setStyle("-fx-background-color: #F7F5F8;");

        // =====================================================
        // SCENE
        // =====================================================

        Scene sc = new Scene(mainBox, 1530, 850);
        Electrinicscene=sc;
                return Electrinicscene;
        }

   

    
}
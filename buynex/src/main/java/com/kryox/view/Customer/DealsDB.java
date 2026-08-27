package com.kryox.view.Customer;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
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
import java.net.URL;


public class DealsDB  {
public String userId;

        public DealsDB(String userId) {
        this.userId = userId;
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("❌ Resource not found: " + path);
            return new Image(
                "data:image/png;base64,"
                + "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII="
            );
        }

        return new Image(url.toExternalForm());
    }
        private Scene dealsScene;
        Scene getDealScene(Runnable callbacktodashboar){
                
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

        Image di = loadImage("/assets/images/Dashbord/dashboard.png");
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
        lefButton1.setOnAction(event->{
                callbacktodashboar.run();
                
        });

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

        // Hover effect for Dashboard
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

        Image di2 = loadImage("/assets/images/store.png");
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

        Image di3 = loadImage("/assets/images/Dashbord/hot-sale.png");
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

        Image di4 = loadImage("/assets/images/Dashbord/package.png");
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
        lefButton4.setOnAction(event->{
                My_orderAllorder my=new My_orderAllorder(userId);
                Homepage.HomepageStage.setScene(my.getAllorderScene());
        });

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

        Image di5 = loadImage("/assets/images/Dashbord/line-chart.png");
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
        lefButton5.setOnAction(e->{
                Analytics as=new Analytics(userId);
                Homepage.HomepageStage.setScene(as.getAnalyticscene(callbacktodashboar));
        });

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

        Image di6 = loadImage("/assets/images/Dashbord/category.png");
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

        Image di7 = loadImage("/assets/images/Dashbord/question.png");
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
        leftBox.setStyle("-fx-background-color: #ebccb7");
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

        HBox actionBox = new HBox(7, b1, b2, b3);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        // =====================================================
        // NAVIGATION SPACERS
        // =====================================================

        Region navSpacer1 = new Region();
        HBox.setHgrow(navSpacer1, Priority.ALWAYS);
        Region navSpacer2 = new Region();
        HBox.setHgrow(navSpacer2, Priority.ALWAYS);

        // =====================================================
        // NAV BOX
        // ====================================================

        HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
        navBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox, Priority.ALWAYS);
        searchBox.setMaxWidth(Double.MAX_VALUE);
        navBox.setPrefHeight(68);
        navBox.setPadding(new Insets(12, 24, 12, 24));
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-color: #ebccb7;" +
                "-fx-border-width: 0 0 1 0;"
        );
        navBox.setTranslateX(0);
        navBox.setStyle(
        "-fx-background-color:#ebccb7;" +
        "-fx-border-width:0;"
        );

        navBox.setPrefHeight(68);
        navBox.setMaxWidth(Double.MAX_VALUE);
                
        // =====================================================
        // HERO BOX
        // =====================================================

        
        BorderPane topPane = new BorderPane();
        topPane.setTop(navBox);
        topPane.setStyle("-fx-background-color:#ebccb7;");
        topPane.setPrefHeight(68);

        VBox Rightvbox = new VBox();
        Rightvbox.setFillWidth(true);
        Rightvbox.setMaxWidth(Double.MAX_VALUE);
        Rightvbox.getChildren().add(topPane);

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

        Rightvbox.setBackground(
                new Background(
                        new BackgroundFill(
                                orangeGlow1,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );
        

        // =====================================================
        // BORDER PANE
        // =====================================================
        BorderPane mainBox = new BorderPane();

        mainBox.setLeft(leftBox);
        mainBox.setCenter(Rightvbox);

        BorderPane.setAlignment(leftBox, Pos.TOP_LEFT);
        BorderPane.setAlignment(Rightvbox, Pos.TOP_LEFT);

        BorderPane.setAlignment(Rightvbox, Pos.TOP_LEFT);

        HBox mainContent = new HBox(24);
        mainContent.setAlignment(Pos.TOP_LEFT);
        mainContent.setPadding(new Insets(24));
        mainContent.setFillHeight(true);
        mainContent.setAlignment(Pos.TOP_LEFT);

        Label filterTitle = new Label("Filters");

        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();

        Label categoryLabel = new Label("Category");

        CheckBox grocery = new CheckBox("Grocery");
        CheckBox electronics = new CheckBox("Electronics");
        CheckBox homeKitchen = new CheckBox("Home & Kitchen");
        CheckBox fashion = new CheckBox("Fashion");

        Label priceLabel = new Label("Price Range");

        Slider slider = new Slider(0,1000,500);

        Label discountLabel = new Label("Discount");

        CheckBox tenPercent = new CheckBox("10% or more");
        CheckBox thirtyPercent = new CheckBox("30% or more");
        CheckBox fiftyPercent = new CheckBox("50% or more");

        Button resetButton = new Button("Reset Filters");

        VBox filterBox = new VBox(18);
        VBox contentBox = new VBox(25);

        mainContent.getChildren().addAll(filterBox, contentBox);
        contentBox.setPrefWidth(920);
        contentBox.setMinWidth(920);
        contentBox.setMaxWidth(920);
        contentBox.setSpacing(22);

        contentBox.setSpacing(20);
        filterBox.setPrefWidth(230);
        filterBox.setMinWidth(230);
        filterBox.setMaxWidth(230);

        filterBox.setPrefHeight(500);
        filterBox.setMinHeight(500);
        filterBox.setMaxHeight(500);

        filterBox.setPadding(new Insets(22, 20, 20, 20));
        filterBox.setSpacing(14);

        filterBox.setStyle(
    "-fx-background-color: white;" +
    "-fx-background-radius: 18;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 2);"
);


       

        // LEFT TEXT
        VBox heroTextVBox = new VBox(5);
        heroTextVBox.setPrefWidth(390);
        heroTextVBox.setMinWidth(390);
        heroTextVBox.setMaxWidth(390);

        heroTextVBox.setAlignment(Pos.CENTER_LEFT);
        heroTextVBox.setPadding(new Insets(20, 10, 20, 30));
     

        Label heroTitle = new Label("Premium\nOrganic\nGrocery\nBundle");
        heroTitle.setStyle(
        "-fx-font-size:34;" +
        "-fx-font-weight:bold;" +
        "-fx-text-fill:white;"
        );
        heroTitle.setStyle(
    "-fx-font-size: 25px;" +
    "-fx-font-weight: bold;" +
    "-fx-text-fill: white;"
);

        Label heroDesc = new Label(
        "Farm-fresh, hyper-local ingredients\n delivered to your door."
        );
        
        heroDesc.setStyle(
        "-fx-font-size:14;" +
        "-fx-text-fill:white;"
        );

        heroTextVBox.getChildren().addAll(heroTitle, heroDesc);
        HBox heroHBox = new HBox();

        heroHBox.setPrefHeight(245);

        heroHBox.setStyle(
        "-fx-background-color:#D56E18;" +
        "-fx-background-radius:18;"
        );

        Image heroImg = loadImage("/assets/images/image.png");

        ImageView heroImageView = new ImageView(heroImg);
        heroImageView.setFitWidth(530);
        heroImageView.setFitHeight(210);
        heroImageView.setPreserveRatio(true);
        heroImageView.setSmooth(true);
       
                
        heroHBox.setPrefWidth(920);
        heroHBox.setMinWidth(920);
        heroHBox.setMaxWidth(920);

        heroHBox.setPrefHeight(210);
        heroHBox.setMinHeight(210);
        heroHBox.setMaxHeight(210);

        heroHBox.setAlignment(Pos.CENTER_LEFT);
        heroHBox.setPadding(new Insets(0));

       filterBox.getChildren().addAll(

    filterTitle,
    separator1,

    categoryLabel,
    grocery,
    electronics,
    homeKitchen,
    fashion,

    separator2,

    priceLabel,
    slider,

    separator3,

    discountLabel,
    tenPercent,
    thirtyPercent,
    fiftyPercent,

    resetButton

);
        heroHBox.getChildren().addAll(

        heroTextVBox,
        heroImageView

        );

        contentBox.getChildren().add(heroHBox);


        // =====================================================
        // TODAY'S BEST DISCOUNTS
        // =====================================================

        HBox discountHeader = new HBox();
        discountHeader.setAlignment(Pos.CENTER_LEFT);
        discountHeader.setPrefWidth(920);
        discountHeader.setMinWidth(920);
        discountHeader.setMaxWidth(920);
        

        Label discountTitle = new Label("Today's Best Discounts");
        discountTitle.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );
        discountTitle.setStyle(
    "-fx-font-size: 21px;" +
    "-fx-font-weight: bold;" +
    "-fx-text-fill: #171717;"
);

        Region discountSpacer = new Region();
        HBox.setHgrow(discountSpacer, Priority.ALWAYS);

        Button viewAll = new Button("View All →");
        viewAll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        discountHeader.getChildren().addAll(
                discountTitle,
                discountSpacer,
                viewAll
        );


        // =====================================================
        // PRODUCT CARDS
        // =====================================================

        
        HBox productCards = new HBox(18);
        productCards.setAlignment(Pos.TOP_LEFT);
        productCards.setPrefWidth(920);
        productCards.setMinWidth(920);
        productCards.setMaxWidth(920);


        // =====================================================
        // CARD 1 - ARTISAN SOURDOUGH
        // =====================================================

        VBox card1 = new VBox(7);
        card1.setPrefWidth(280);
        card1.setMinWidth(280);
        card1.setMaxWidth(280);

        card1.setPrefHeight(300);
        card1.setMinHeight(300);
        card1.setMaxHeight(300);

        card1.setPadding(new Insets(7));
        card1.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );


        // Image
        Image productImg1 = loadImage("/assets/images/img1.png");

        ImageView productView1 = new ImageView(productImg1);
        productView1.setFitWidth(264);
        productView1.setFitHeight(145);
        productView1.setPreserveRatio(true);


        // Discount label
        Label discount1 = new Label("-40%");
        discount1.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 7 4 7;" +
                "-fx-background-radius: 5;"
        );

        HBox discountBox1 = new HBox(discount1);
        discountBox1.setTranslateX(-2);
        discountBox1.setTranslateY(-108);


        // Rating
        Label rating1 = new Label("☆ 4.8 (124)");
        rating1.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        Label productName1 = new Label("Artisan Sourdough...");
        productName1.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label productDesc1 = new Label("Freshly baked, local...");
        productDesc1.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceBox1 = new HBox(7);
        priceBox1.setAlignment(Pos.CENTER_LEFT);

        Label price1 = new Label("$5.99");
        price1.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        Label oldPrice1 = new Label("$9.99");
        oldPrice1.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;" +
                "-fx-strikethrough: true;"
        );

        Label cart1 = new Label("🛒");
        cart1.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-background-radius: 50;" +
                "-fx-padding: 5;" +
                "-fx-font-size: 9px;"
        );

        Region priceSpacer1 = new Region();
        HBox.setHgrow(priceSpacer1, Priority.ALWAYS);

        priceBox1.getChildren().addAll(
                price1,
                oldPrice1,
                priceSpacer1,
                cart1
        );

        card1.getChildren().addAll(
                productView1,
                rating1,
                productName1,
                productDesc1,
                priceBox1
        );


        // =====================================================
        // CARD 2 - ORGANIC AVOCADO
        // =====================================================

        VBox card2 = new VBox(7);
        card2.setPrefWidth(280);
        card2.setMinWidth(280);
        card2.setMaxWidth(280);

        card2.setPrefHeight(300);
        card2.setMinHeight(300);
        card2.setMaxHeight(300);

        card2.setPadding(new Insets(7));

        card2.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );

        Image productImg2 = loadImage("/assets/images/avocado.png");

        ImageView productView2 = new ImageView(productImg2);
        productView2.setFitWidth(264);
        productView2.setFitHeight(145);
        productView2.setPreserveRatio(true);

        Label discount2 = new Label("-25%");
        discount2.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 7 4 7;" +
                "-fx-background-radius: 5;"
        );

        Label rating2 = new Label("☆ 4.9 (89)");
        rating2.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        Label productName2 = new Label("Organic Hass...");
        productName2.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label productDesc2 = new Label("Pack of 4, Farm direct");
        productDesc2.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceBox2 = new HBox(7);
        priceBox2.setAlignment(Pos.CENTER_LEFT);

        Label price2 = new Label("$6.49");
        price2.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        Label oldPrice2 = new Label("$8.65");
        oldPrice2.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;" +
                "-fx-strikethrough: true;"
        );

        Label cart2 = new Label("🛒");
        cart2.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-background-radius: 50;" +
                "-fx-padding: 5;" +
                "-fx-font-size: 9px;"
        );

        Region priceSpacer2 = new Region();
        HBox.setHgrow(priceSpacer2, Priority.ALWAYS);

        priceBox2.getChildren().addAll(
                price2,
                oldPrice2,
                priceSpacer2,
                cart2
        );

        card2.getChildren().addAll(
                productView2,
                rating2,
                productName2,
                productDesc2,
                priceBox2
        );


        // =====================================================
        // CARD 3 - SONICPRO WIRELESS
        // =====================================================

        VBox card3 = new VBox(7);
        card3.setPrefWidth(280);
        card3.setMinWidth(280);
        card3.setMaxWidth(280);

        card3.setPrefHeight(300);
        card3.setMinHeight(300);
        card3.setMaxHeight(300);

        card3.setPadding(new Insets(7));
                card3.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );

        Image productImg3 = loadImage("/assets/images/headphone.png");

        ImageView productView3 = new ImageView(productImg3);
        productView3.setFitWidth(264);
        productView3.setFitHeight(145);
        productView3.setPreserveRatio(true);

        productView1.setSmooth(true);
        productView2.setSmooth(true);
        productView3.setSmooth(true);

        Label discount3 = new Label("-55%");
        discount3.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 7 4 7;" +
                "-fx-background-radius: 5;"
        );

        Label rating3 = new Label("☆ 4.6 (102)");
        rating3.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        Label productName3 = new Label("SonicPro Wireless...");
        productName3.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label productDesc3 = new Label("Over-ear, 30h battery");
        productDesc3.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceBox3 = new HBox(7);
        priceBox3.setAlignment(Pos.CENTER_LEFT);

        Label price3 = new Label("$89.00");
        price3.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        Label oldPrice3 = new Label("$199.00");
        oldPrice3.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;" +
                "-fx-strikethrough: true;"
        );

        Label cart3 = new Label("🛒");
        cart3.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-background-radius: 50;" +
                "-fx-padding: 5;" +
                "-fx-font-size: 9px;"
        );

        Region priceSpacer3 = new Region();
        HBox.setHgrow(priceSpacer3, Priority.ALWAYS);

        priceBox3.getChildren().addAll(
                price3,
                oldPrice3,
                priceSpacer3,
                cart3
        );

        card3.getChildren().addAll(
                productView3,
                rating3,
                productName3,
                productDesc3,
                priceBox3
        );


        // =====================================================
        // ADD CARDS
        // =====================================================

        productCards.getChildren().addAll(
                card1,
                card2,
                card3
        );


        // =====================================================
        // ADD DISCOUNTS SECTION TO CONTENT
        // =====================================================

        contentBox.getChildren().addAll(
                discountHeader,
                productCards
        );


             
                

       

        // =====================================================
        // SCENE
        // =====================================================

        Rightvbox.getChildren().add(mainContent);

        dealsScene = new Scene(mainBox, 1500, 800);
                return dealsScene;
        }

}
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;

public class Dashbord  {
    private String userId;

    public Dashbord(String userId) {
        this.userId = userId;
    }

    private Scene Dashbordscene;

    public Scene getDashbordScene(){
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

        Label name = new Label("BuyNeX");
        name.setStyle(
        "-fx-font-family: 'Montserrat';" +
        "-fx-font-size: 28px;" +
        "-fx-font-weight: 900;" +
        "-fx-text-fill: #E87500;"
        );

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
        String currentPlan = CustomerManagePlan.CustomerPlanState.getCurrentPlan(userId);
        String shopperTitle = "Gold".equalsIgnoreCase(currentPlan) ? "👑 Gold Shopper" : ("Platinum".equalsIgnoreCase(currentPlan) ? "💎 Platinum VIP" : "Premium Shopper");
        Label title = new Label(shopperTitle);
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

        lefButton1.setOnAction(e -> CustomerNavigation.navigateToDashboard(userId));

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
            Runnable callbackDashbord=new Runnable() {
                public void run(){
                    backTodashbord();

                }
            };
            Homepage.HomepageStage.setScene(ns.getNearby_shopes(callbackDashbord));
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
        lefButton3.setOnAction(event->{
            DealsDB d=new DealsDB(userId);
            Runnable rn=new Runnable() {
                public void run(){
                    backTodashbord();
                }
            };
            Homepage.HomepageStage.setScene(d.getDealScene(rn));

        });

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
        lefButton4.setOnAction(event->{
            My_orderAllorder moa=new My_orderAllorder(userId);
            Homepage.HomepageStage.setScene(moa.getAllorderScene());
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
        lefButton5.setOnAction(event->{
            Analytics an=new Analytics(userId);
            Runnable rnA=new Runnable() {
                public void run(){
                    backTodashbord();
                }

            };
            Homepage.HomepageStage.setScene(an.getAnalyticscene(rnA));
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

        VBox upgradeCard = CustomerPlanUpgradeCard.createUpgradeCard(userId);

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
                    backTodashbord();
                }
            };
            Homepage.HomepageStage.setScene(se.getSetingscene(callbacktoDashborad));
        });

        HBox hbInDashboard6 = new HBox(10, div6, lefButton6);
        hbInDashboard6.setPrefWidth(205);
        hbInDashboard6.setMinWidth(205);
        hbInDashboard6.setMaxWidth(205);
        hbInDashboard6.setPrefHeight(34);
        hbInDashboard6.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard6.setPadding(new Insets(0, 8, 0, 18));

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
        lefButton7.setOnAction(e -> {
            Helppage hp = new Helppage(userId);
            Homepage.HomepageStage.setScene(hp.getHelpScene(this::backTodashbord));
        });

        HBox hbInDashboard7 = new HBox(10, div7, lefButton7);
        hbInDashboard7.setPrefWidth(205);
        hbInDashboard7.setMinWidth(205);
        hbInDashboard7.setMaxWidth(205);
        hbInDashboard7.setPrefHeight(34);
        hbInDashboard7.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard7.setPadding(new Insets(0, 8, 0, 18));

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

        Button t1 = new Button("Offers");
        Button t2 = new Button("Shops");
        Button t3 = new Button("Support");

        t1.setOnAction(e -> CustomerNavigation.navigateToDeals(userId));
        t2.setOnAction(e -> CustomerNavigation.navigateToNearbyShops(userId));
        t3.setOnAction(e -> CustomerNavigation.navigateToHelp(userId));

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

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search products, shops or deals with AI...");
        searchBox.setPrefWidth(300);
        searchBox.setPrefHeight(39);
        searchBox.setStyle(
        "-fx-background-color: transparent;" +
        "-fx-border-width: 0;" +
        "-fx-padding: 0 10 0 14;" +
        "-fx-font-size: 11px;" +
        "-fx-text-fill: #444444;" +
        "-fx-prompt-text-fill: #999999;"
        );

        Button searchBtn = new Button("🔍");
        searchBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #FF6900;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 0 10 0 0;"
        );
        searchBtn.setOnAction(e -> {
            CustomerNavigation.performSearch(userId, searchBox.getText(), this::backTodashbord);
        });

        searchBox.setOnAction(e -> {
            CustomerNavigation.performSearch(userId, searchBox.getText(), this::backTodashbord);
        });

        HBox searchContainer = new HBox(searchBox, searchBtn);
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setPrefHeight(39);
        searchContainer.setStyle(
            "-fx-background-color: #F8F7FA;" +
            "-fx-background-radius: 20;" +
            "-fx-border-color: #E5E1E8;" +
            "-fx-border-radius: 20;" +
            "-fx-border-width: 1;"
        );

        Label locationIcon = new Label("📍");
        Label locationText = new Label("Downtown Manhattan⌄");
        locationText.setStyle(
        "-fx-font-size: 10px;" +
        "-fx-font-weight: bold;" +
        "-fx-text-fill: #555555;"
        );

        HBox locationBox = new HBox(4, locationIcon, locationText);
        locationBox.setAlignment(Pos.CENTER_LEFT);
        locationBox.setStyle("-fx-cursor: hand;");
        locationBox.setOnMouseClicked(e -> CustomerNavigation.navigateToNearbyShops(userId));

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

        b1.setOnAction(e -> CustomerNavigation.navigateToNotifications(userId));
        b2.setOnAction(e -> CustomerNavigation.navigateToCart(userId));
        b3.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

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

        HBox actionBox = new HBox(10, b1, b2, b3);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        Region navSpacer1 = new Region();
        HBox.setHgrow(navSpacer1, Priority.ALWAYS);
        Region navSpacer2 = new Region();
        HBox.setHgrow(navSpacer2, Priority.ALWAYS);

        HBox navBox = new HBox(12, topLinks, navSpacer1, searchContainer, navSpacer2, locationBox, actionBox);
        navBox.setPrefHeight(68);
        navBox.setPadding(new Insets(12, 24, 12, 24));
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setStyle(
        "-fx-background-color: #ebccb7;" +
        "-fx-border-color: #dfc1ac;" +
        "-fx-border-width: 0 0 1 0;"
        );

        HBox hbright = CustomerAIHeroBanner.createHeroBanner(
                userId,
                () -> CustomerNavigation.navigateToGroceries(userId),
                () -> CustomerNavigation.navigateToDeals(userId)
        );


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
        viewAll.setOnMouseClicked(e -> {
            Electronics es = new Electronics(userId, "All");
            Homepage.HomepageStage.setScene(es.getElectrScene(this::backTodashbord));
        });

        Region categorySpacer = new Region();
        HBox.setHgrow(categorySpacer, Priority.ALWAYS);

        categoryHeader.getChildren().addAll(categoryTitle, categorySpacer, viewAll);

        Button grocerie = new Button("🛒\nGroceries");
        grocerie.setOnAction(event->{
            Grocries gr=new Grocries(userId);
            Runnable callbacToDashbord=new Runnable() {
                public void run(){
                    backTodashbord();
                }
            };
            Homepage.HomepageStage.setScene(gr.getGrocriescene(callbacToDashbord));
        });

        Button electronics = new Button("▣\nElectronics");
        electronics.setOnAction(event->{
            Electronics es=new Electronics(userId);
            Runnable rn=new Runnable() {
                public void run(){
                    backTodashbord();
                }
            };

            Homepage.HomepageStage.setScene(es.getElectrScene(rn));
        });
        Button fashion = new Button("♧\nFashion");
        fashion.setOnAction(e -> openCategoryView(userId, "Fashion"));

        Button health = new Button("✚\nHealth");
        health.setOnAction(e -> openCategoryView(userId, "Health"));

        Button home = new Button("⌂\nHome");
        home.setOnAction(e -> openCategoryView(userId, "Home"));

        Button gifts = new Button("♧\nGifts");
        gifts.setOnAction(e -> openCategoryView(userId, "Gifts"));

        Button beauty = new Button("✦\nBeauty");
        beauty.setOnAction(e -> openCategoryView(userId, "Beauty"));

        Button pharmacy = new Button("✚\nPharmacy");
        pharmacy.setOnAction(e -> openCategoryView(userId, "Pharmacy"));

        Button sports = new Button("⚽\nSports");
        sports.setOnAction(e -> openCategoryView(userId, "Sports"));

        Button furniture = new Button("⌂\nFurniture");
        furniture.setOnAction(e -> openCategoryView(userId, "Furniture"));

        Button toys = new Button("♟\nToys");
        toys.setOnAction(e -> openCategoryView(userId, "Toys"));

        Button stationery = new Button("✎\nStationery");
        stationery.setOnAction(e -> openCategoryView(userId, "Stationery"));

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

        HBox categoriesRow1 = new HBox(12, grocerie, electronics, fashion, health, home, gifts);
        categoriesRow1.setAlignment(Pos.CENTER_LEFT);

        HBox categoriesRow2 = new HBox(12, beauty, pharmacy, sports, furniture, toys, stationery);
        categoriesRow2.setAlignment(Pos.CENTER_LEFT);

        VBox categorySection = new VBox(13, categoryHeader, categoriesRow1, categoriesRow2);
        categorySection.setPadding(new Insets(0, 0, 20, 0));

        VBox specialCampaignsSection = CustomerCampaignSection.createSpecialCampaignsSection(userId, this::backTodashbord);

        VBox dashContent = new VBox(22, hbright, specialCampaignsSection, categorySection);
        dashContent.setPadding(new Insets(20, 26, 25, 26));

        ScrollPane contentScroll = new ScrollPane(dashContent);
        contentScroll.setFitToWidth(true);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        contentScroll.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");
        VBox.setVgrow(contentScroll, Priority.ALWAYS);

        VBox Rightvbox = new VBox(0, navBox, contentScroll);
        Rightvbox.setFillWidth(true);
        Rightvbox.setStyle("-fx-background-color: #F7F5F8;");

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

        BorderPane mainBox = new BorderPane();
        mainBox.setLeft(leftBox);
        mainBox.setCenter(Rightvbox);
        mainBox.setStyle("-fx-background-color: #F7F5F8;");

        Image chatbotImage = new Image("/assets/images/chatbot (1).png");
        ImageView chatbotView = new ImageView(chatbotImage);

        chatbotView.setFitWidth(32);
        chatbotView.setFitHeight(32);
        chatbotView.setPreserveRatio(true);

        Button chatbotButton = new Button();
        chatbotButton.setGraphic(chatbotView);
        chatbotButton.setPrefSize(50, 50);
        chatbotButton.setMinSize(50, 50);
        chatbotButton.setMaxSize(50, 50);

        chatbotButton.setStyle(
        "-fx-background-color: #FF6900;" +
        "-fx-background-radius: 50%;" +
        "-fx-border-color: white;" +
        "-fx-border-width: 2;" +
        "-fx-border-radius: 50%;" +
        "-fx-padding: 8;" +
        "-fx-cursor: hand;"
        );

        DropShadow chatbotShadow = new DropShadow();
        chatbotShadow.setRadius(12);
        chatbotShadow.setOffsetX(0);
        chatbotShadow.setOffsetY(4);
        chatbotShadow.setSpread(0.10);
        chatbotShadow.setColor(Color.rgb(0, 0, 0, 0.25));
        chatbotButton.setEffect(chatbotShadow);

        chatbotButton.setOnMouseEntered(e -> {
            chatbotButton.setStyle(
            "-fx-background-color: #FF8533;" +
            "-fx-background-radius: 50%;" +
            "-fx-border-color: white;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 50%;" +
            "-fx-padding: 8;" +
            "-fx-cursor: hand;"
            );
            chatbotButton.setScaleX(1.08);
            chatbotButton.setScaleY(1.08);
        });

        chatbotButton.setOnMouseExited(e -> {
            chatbotButton.setStyle(
            "-fx-background-color: #FF6900;" +
            "-fx-background-radius: 50%;" +
            "-fx-border-color: white;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 50%;" +
            "-fx-padding: 8;" +
            "-fx-cursor: hand;"
            );
            chatbotButton.setScaleX(1.0);
            chatbotButton.setScaleY(1.0);
        });
        chatbotButton.setOnAction(event->{
            SmartAssistantUI chAssistantUI=new SmartAssistantUI();
            Homepage.HomepageStage.setScene(chAssistantUI.getSmartAssisstantui());
        });

        StackPane root = new StackPane();
        root.getChildren().add(mainBox);

        StackPane.setAlignment(chatbotButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(chatbotButton, new Insets(0, 25, 25, 0));

        root.getChildren().add(chatbotButton);

        Scene sc = new Scene(root, 1550, 850);
        Dashbordscene=sc;
        return Dashbordscene;
    }

    public void backTodashbord(){
        Homepage.HomepageStage.setScene(Dashbordscene);
    }

    private void openCategoryView(String uId, String catName) {
        Electronics catView = new Electronics(uId, catName);
        Runnable rn = this::backTodashbord;
        Homepage.HomepageStage.setScene(catView.getElectrScene(rn));
    }
}

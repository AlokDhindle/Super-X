package com.kryox.view.Admin;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SettingsPage {

    public Scene getUserScene() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #eee5df;");

        VBox left = new VBox(25);
        left.setPrefWidth(210);
        left.setPadding(new Insets(30, 15, 20, 15));
        left.setStyle("-fx-background-color: #ebccb7");

        Text logo = new Text("Admin Panel");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logo.setFill(Color.web("#A83E00"));

        Text subLogo = new Text("Marketplace Controller");
        subLogo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        subLogo.setFill(Color.web("#999999"));

        VBox logoBox = new VBox(4);
        logoBox.getChildren().addAll(logo, subLogo);

        VBox menu = new VBox(4);

        Image dashboardImage = new Image(getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());
        ImageView dashboardIcon = new ImageView(dashboardImage);
        Text dashboardText = new Text("Dashboard");
        dashboardIcon.setFitWidth(21);
        dashboardIcon.setFitHeight(21);
        dashboardIcon.setPreserveRatio(true);
        dashboardText.setFont(Font.font("Arial", 14));
        
        HBox dashboard = new HBox(10);
        dashboard.setAlignment(Pos.CENTER_LEFT);
        dashboard.setPadding(new Insets(10, 12, 10, 12));
        dashboard.setStyle("-fx-background-color:transparent;");
        dashboard.getChildren().addAll(dashboardIcon, dashboardText);

        Image usersImage = new Image("assets\\images\\admin\\admin_logo.png");
        ImageView usersIcon = new ImageView(usersImage);
        Text usersText = new Text("Users");
        usersIcon.setFitWidth(21);
        usersIcon.setFitHeight(21);
        usersIcon.setPreserveRatio(true);
        usersText.setFont(Font.font("Arial", 14));
        HBox users = new HBox(10);
        users.setAlignment(Pos.CENTER_LEFT);
        users.setPadding(new Insets(10, 12, 10, 12));
        users.setStyle("-fx-background-color:transparent;");
        users.getChildren().addAll(usersIcon, usersText);

        Image shopsImage = new Image(getClass().getResource("/assets/images/admin/shop.png").toExternalForm());
        ImageView shopsIcon = new ImageView(shopsImage);
        Text shopsText = new Text("Shops");
        shopsIcon.setFitWidth(21);
        shopsIcon.setFitHeight(21);
        shopsIcon.setPreserveRatio(true);
        shopsText.setFont(Font.font("Arial", 14));
        HBox shops = new HBox(10);
        shops.setAlignment(Pos.CENTER_LEFT);
        shops.setPadding(new Insets(10, 12, 10, 12));
        shops.setStyle("-fx-background-color:transparent;");
        shops.getChildren().addAll(shopsIcon, shopsText);

        Text deliveryIcon = new Text("🚚");
        deliveryIcon.setFont(Font.font("Arial", 18));

        Text deliveryText = new Text("Delivery");
        deliveryText.setFont(Font.font("Arial", 14));

        HBox delivery = new HBox(10);
        delivery.setAlignment(Pos.CENTER_LEFT);
        delivery.setPadding(new Insets(10, 12, 10, 12));
        delivery.setStyle("-fx-background-color:transparent;");
        delivery.getChildren().addAll(deliveryIcon, deliveryText);

        delivery.setOnMouseEntered(e -> {
            delivery.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            deliveryText.setFill(Color.WHITE);
            deliveryText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition deliveryAnimation = new ScaleTransition(
                    Duration.millis(120), delivery);
            deliveryAnimation.setToX(1.03);
            deliveryAnimation.setToY(1.03);
            deliveryAnimation.play();
        });

        delivery.setOnMouseExited(e -> {
            delivery.setStyle("-fx-background-color:transparent;");
            deliveryText.setFill(Color.web("#333333"));
            deliveryText.setFont(Font.font("Arial", 14));

            ScaleTransition deliveryAnimation = new ScaleTransition(
                    Duration.millis(120), delivery);
            deliveryAnimation.setToX(1);
            deliveryAnimation.setToY(1);
            deliveryAnimation.play();
        });

        Image offersImage = new Image(getClass().getResource("/assets/images/admin/tag.png").toExternalForm());
        ImageView offersIcon = new ImageView(offersImage);
        Text offersText = new Text("Offers");
        offersIcon.setFitWidth(21);
        offersIcon.setFitHeight(21);
        offersIcon.setPreserveRatio(true);
        offersText.setFont(Font.font("Arial", 14));
        HBox offers = new HBox(10);
        offers.setAlignment(Pos.CENTER_LEFT);
        offers.setPadding(new Insets(10, 12, 10, 12));
        offers.setStyle("-fx-background-color:transparent;");
        offers.getChildren().addAll(offersIcon, offersText);

        Image analyticsImage = new Image(getClass().getResource("/assets/images/admin/stats.png").toExternalForm());
        ImageView analyticsIcon = new ImageView(analyticsImage);
        Text analyticsText = new Text("Analytics");
        analyticsIcon.setFitWidth(21);
        analyticsIcon.setFitHeight(21);
        analyticsIcon.setPreserveRatio(true);
        analyticsText.setFont(Font.font("Arial", 14));
        HBox analytics = new HBox(10);
        analytics.setAlignment(Pos.CENTER_LEFT);
        analytics.setPadding(new Insets(10, 12, 10, 12));
        analytics.setStyle("-fx-background-color:transparent;");
        analytics.getChildren().addAll(analyticsIcon, analyticsText);

        Image settingsImage = new Image(getClass().getResource("/assets/images/admin/setting.png").toExternalForm());
        ImageView settingsIcon = new ImageView(settingsImage);
        Text settingsText = new Text("Settings");
        settingsIcon.setFitWidth(21);
        settingsIcon.setFitHeight(21);
        settingsIcon.setPreserveRatio(true);
        settingsText.setFont(Font.font("Arial", 14));
        HBox settings = new HBox(10);
        settings.setAlignment(Pos.CENTER_LEFT);
        settings.setPadding(new Insets(10, 12, 10, 12));
        settings.setStyle("-fx-background-color:#FF6500; -fx-background-radius:10;");
        settings.getChildren().addAll(settingsIcon, settingsText);

        menu.getChildren().addAll(dashboard, users, shops, delivery, offers, analytics, settings);

        VBox bottomMenu = new VBox(4);
        Image supportImage = new Image(getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
        ImageView supportIcon = new ImageView(supportImage);
        Text supportText = new Text("Support");
        supportIcon.setFitWidth(21);
        supportIcon.setFitHeight(21);
        supportIcon.setPreserveRatio(true);
        supportText.setFont(Font.font("Arial", 14));
        HBox support = new HBox(10);
        support.setAlignment(Pos.CENTER_LEFT);
        support.setPadding(new Insets(10, 12, 10, 12));
        support.setStyle("-fx-background-color:transparent;");
        support.getChildren().addAll(supportIcon, supportText);
        bottomMenu.getChildren().add(support);

        dashboard.setOnMouseEntered(e -> {
            dashboard.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            dashboardText.setFill(Color.WHITE);
            dashboardText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition dashboardAnimation = new ScaleTransition(
                    Duration.millis(120), dashboard);
            dashboardAnimation.setToX(1.03);
            dashboardAnimation.setToY(1.03);
            dashboardAnimation.play();
        });

        dashboard.setOnMouseExited(e -> {
            dashboard.setStyle("-fx-background-color:transparent;");
            dashboardText.setFill(Color.web("#333333"));
            dashboardText.setFont(Font.font("Arial", 14));

            ScaleTransition dashboardAnimation = new ScaleTransition(
                    Duration.millis(120), dashboard);
            dashboardAnimation.setToX(1);
            dashboardAnimation.setToY(1);
            dashboardAnimation.play();
        });

        users.setOnMouseEntered(e -> {
            users.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
            usersText.setFill(Color.WHITE);
            usersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition usersAnimation = new ScaleTransition(
                    Duration.millis(120), users);
            usersAnimation.setToX(1.03);
            usersAnimation.setToY(1.03);
            usersAnimation.play();
        });

        users.setOnMouseExited(e -> {
            users.setStyle("-fx-background-color:transparent;");
            usersText.setFill(Color.web("#333333"));
            usersText.setFont(Font.font("Arial", 14));

            ScaleTransition usersAnimation = new ScaleTransition(
                    Duration.millis(120), users);
            usersAnimation.setToX(1);
            usersAnimation.setToY(1);
            usersAnimation.play();
        });

        shops.setOnMouseEntered(e -> {
            shops.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            shopsText.setFill(Color.WHITE);
            shopsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition shopsAnimation = new ScaleTransition(
                    Duration.millis(120), shops);
            shopsAnimation.setToX(1.03);
            shopsAnimation.setToY(1.03);
            shopsAnimation.play();
        });

        shops.setOnMouseExited(e -> {
            shops.setStyle("-fx-background-color:transparent;");
            shopsText.setFill(Color.web("#333333"));
            shopsText.setFont(Font.font("Arial", 14));

            ScaleTransition shopsAnimation = new ScaleTransition(
                    Duration.millis(120), shops);
            shopsAnimation.setToX(1);
            shopsAnimation.setToY(1);
            shopsAnimation.play();
        });

        offers.setOnMouseEntered(e -> {
            offers.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            offersText.setFill(Color.WHITE);
            offersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition offersAnimation = new ScaleTransition(
                    Duration.millis(120), offers);
            offersAnimation.setToX(1.03);
            offersAnimation.setToY(1.03);
            offersAnimation.play();
        });

        offers.setOnMouseExited(e -> {
            offers.setStyle("-fx-background-color:transparent;");
            offersText.setFill(Color.web("#333333"));
            offersText.setFont(Font.font("Arial", 14));

            ScaleTransition offersAnimation = new ScaleTransition(
                    Duration.millis(120), offers);
            offersAnimation.setToX(1);
            offersAnimation.setToY(1);
            offersAnimation.play();
        });

        analytics.setOnMouseEntered(e -> {
            analytics.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            analyticsText.setFill(Color.WHITE);
            analyticsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition analyticsAnimation = new ScaleTransition(
                    Duration.millis(120), analytics);
            analyticsAnimation.setToX(1.03);
            analyticsAnimation.setToY(1.03);
            analyticsAnimation.play();
        });

        analytics.setOnMouseExited(e -> {
            analytics.setStyle("-fx-background-color:transparent;");
            analyticsText.setFill(Color.web("#333333"));
            analyticsText.setFont(Font.font("Arial", 14));

            ScaleTransition analyticsAnimation = new ScaleTransition(
                    Duration.millis(120), analytics);
            analyticsAnimation.setToX(1);
            analyticsAnimation.setToY(1);
            analyticsAnimation.play();
        });

        settings.setOnMouseEntered(e -> {
            settings.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            settingsText.setFill(Color.WHITE);
            settingsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition settingsAnimation = new ScaleTransition(
                    Duration.millis(120), settings);
            settingsAnimation.setToX(1.03);
            settingsAnimation.setToY(1.03);
            settingsAnimation.play();
        });

        settings.setOnMouseExited(e -> {
            settings.setStyle("-fx-background-color:#FF6500; -fx-background-radius:10;");
            settingsText.setFill(Color.web("#333333"));
            settingsText.setFont(Font.font("Arial", 14));

            ScaleTransition settingsAnimation = new ScaleTransition(
                    Duration.millis(120), settings);
            settingsAnimation.setToX(1);
            settingsAnimation.setToY(1);
            settingsAnimation.play();
        });

        support.setOnMouseEntered(e -> {
            support.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            supportText.setFill(Color.WHITE);
            supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            ScaleTransition supportAnimation = new ScaleTransition(
                    Duration.millis(120), support);
            supportAnimation.setToX(1.03);
            supportAnimation.setToY(1.03);
            supportAnimation.play();
        });

        support.setOnMouseExited(e -> {
            support.setStyle("-fx-background-color:transparent;");
            supportText.setFill(Color.web("#333333"));
            supportText.setFont(Font.font("Arial", 14));

            ScaleTransition supportAnimation = new ScaleTransition(
                    Duration.millis(120), support);
            supportAnimation.setToX(1);
            supportAnimation.setToY(1);
            supportAnimation.play();
        });

        dashboard.setOnMouseClicked(e -> {
            AdminDashboardPage dashboardPage = new AdminDashboardPage();
            Homepage.HomepageStage.setScene(dashboardPage.getUserScene());
        });

        users.setOnMouseClicked(e -> {
            UserManagementPage userPage = new UserManagementPage();
            Homepage.HomepageStage.setScene(userPage.getUserScene());
        });

        shops.setOnMouseClicked(e -> {
            ShopVerificationPage shopPage = new ShopVerificationPage();
            Homepage.HomepageStage.setScene(shopPage.getUserScene());
        });

        delivery.setOnMouseClicked(e -> {
            DeliveryVerificationPage deliveryPage = new DeliveryVerificationPage();
            Homepage.HomepageStage.setScene(deliveryPage.getUserScene());
        });

        offers.setOnMouseClicked(e -> {
            OfferPage offerPage = new OfferPage();
            Homepage.HomepageStage.setScene(offerPage.getUserScene());
        });

        analytics.setOnMouseClicked(e -> {
            AdminAnalyticsPage analyticsPage = new AdminAnalyticsPage();
            Homepage.HomepageStage.setScene(analyticsPage.getUserScene());
        });

        settings.setOnMouseClicked(e -> {
            SettingsPage settingsPage = new SettingsPage();
            Homepage.HomepageStage.setScene(settingsPage.getUserScene());
        });
        support.setOnMouseClicked(e->{
                SupportPage supports = new SupportPage();
                Homepage.HomepageStage.setScene(supports.getUserScene());
        });

        Region leftGrow = new Region();
        VBox.setVgrow(leftGrow, Priority.ALWAYS);

        
        // PROFILE
        // =========================

        AdminProfileCard adminProfileCard =
                new AdminProfileCard();

        HBox profile =
                adminProfileCard.getProfileCard();

        Separator leftSeparator = new Separator();

        left.getChildren().addAll(
                logoBox,
                menu,
                leftSeparator,
                bottomMenu,
                leftGrow,
                profile
        );
        root.setLeft(left);

        VBox rightBox = new VBox(20);
        rightBox.setPadding(new Insets(25));
        rightBox.setStyle("-fx-background-color: #eee5df;");

        HBox top = new HBox(20);
        top.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText("Search orders, shops, or users...");
        search.setPrefSize(360, 42);
        search.setStyle("-fx-background-color:#F2F0F5; -fx-background-radius:20; -fx-font-size:14px;");

        Region topGrow = new Region();
        HBox.setHgrow(topGrow, Priority.ALWAYS);

        HBox topRight =
                createTopActions();
        top.getChildren().addAll(search, topGrow, topRight);

        VBox content = new VBox(20);
        content.setPadding(new Insets(5, 5, 25, 5));

        Text title = new Text("Platform Configuration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        Text subtitle = new Text(
                "Manage core system parameters, financial structures, and security protocols for the BuyNeX ecosystem.");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setFill(Color.web("#666666"));

        VBox heading = new VBox(6, title, subtitle);

        HBox settingBoxes = new HBox(18);
        settingBoxes.setAlignment(Pos.TOP_LEFT);

        VBox settingMenu = new VBox(14);
        settingMenu.setPrefWidth(220);
        settingMenu.setPadding(new Insets(20));
        settingMenu.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Button identityButton = new Button("▣  Platform Identity");
        Button commissionButton = new Button("▣  Commission & Payouts  ›");
        Button securityButton = new Button("⬟  Security");
        Button apiButton = new Button("◆  API Integrations");
        Button payoutButton = new Button("⟳  Payout Cycles");

        identityButton.setMaxWidth(Double.MAX_VALUE);
        identityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px;");

        commissionButton.setMaxWidth(Double.MAX_VALUE);
        commissionButton.setStyle("-fx-background-color:#F3F1F7; -fx-text-fill:#9B3100;"
                + "-fx-font-weight:bold; -fx-alignment:CENTER-LEFT; -fx-padding:13;");

        securityButton.setMaxWidth(Double.MAX_VALUE);
        securityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px;");

        apiButton.setMaxWidth(Double.MAX_VALUE);
        apiButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px;");

        payoutButton.setMaxWidth(Double.MAX_VALUE);
        payoutButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px;");

        settingMenu.getChildren().addAll(
                identityButton,
                commissionButton,
                securityButton,
                apiButton,
                payoutButton);

        VBox centerBox = new VBox(18);
        HBox.setHgrow(centerBox, Priority.ALWAYS);

        VBox commissionCard = new VBox(14);
        commissionCard.setPrefWidth(560);
        commissionCard.setPadding(new Insets(20));
        commissionCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text commissionTitle = new Text("Category Commission Rates");
        commissionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        Text commissionSub = new Text("Set the default percentage taken per transaction by category.");
        commissionSub.setFont(Font.font("Arial", 14));
        commissionSub.setFill(Color.web("#666666"));
        Text groceryIcon = new Text("◉");
        groceryIcon.setFont(Font.font("Arial", 18));
        Text groceryText = new Text("Grocery & Daily Essentials");
        groceryText.setFont(Font.font("Arial", 14));
        TextField groceryRate = new TextField("2.5  %");
        Region groceryGrow = new Region();
        HBox.setHgrow(groceryGrow, Priority.ALWAYS);
        groceryRate.setPrefWidth(110);
        groceryRate.setAlignment(Pos.CENTER_RIGHT);
        groceryRate.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1;"
                + "-fx-border-radius:8; -fx-background-radius:8;");
        HBox groceryBox = new HBox(12);
        groceryBox.setAlignment(Pos.CENTER_LEFT);
        groceryBox.setPadding(new Insets(12));
        groceryBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10;"
                + "-fx-border-color:#E5CDC1; -fx-border-radius:10;");
        groceryBox.getChildren().addAll(groceryIcon, groceryText, groceryGrow, groceryRate);

        Text electronicsIcon = new Text("▣");
        electronicsIcon.setFont(Font.font("Arial", 18));
        Text electronicsText = new Text("Electronics & Gadgets");
        electronicsText.setFont(Font.font("Arial", 14));
        TextField electronicsRate = new TextField("4.0  %");
        Region electronicsGrow = new Region();
        HBox.setHgrow(electronicsGrow, Priority.ALWAYS);
        electronicsRate.setPrefWidth(110);
        electronicsRate.setAlignment(Pos.CENTER_RIGHT);
        electronicsRate.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1;"
                + "-fx-border-radius:8; -fx-background-radius:8;");
        HBox electronicsBox = new HBox(12);
        electronicsBox.setAlignment(Pos.CENTER_LEFT);
        electronicsBox.setPadding(new Insets(12));
        electronicsBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10;"
                + "-fx-border-color:#E5CDC1; -fx-border-radius:10;");
        electronicsBox.getChildren().addAll(
                electronicsIcon, electronicsText, electronicsGrow, electronicsRate);

        Text fashionIcon = new Text("♧");
        fashionIcon.setFont(Font.font("Arial", 18));
        Text fashionText = new Text("Fashion & Apparel");
        fashionText.setFont(Font.font("Arial", 14));
        TextField fashionRate = new TextField("5.5  %");
        Region fashionGrow = new Region();
        HBox.setHgrow(fashionGrow, Priority.ALWAYS);
        fashionRate.setPrefWidth(110);
        fashionRate.setAlignment(Pos.CENTER_RIGHT);
        fashionRate.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1;"
                + "-fx-border-radius:8; -fx-background-radius:8;");
        HBox fashionBox = new HBox(12);
        fashionBox.setAlignment(Pos.CENTER_LEFT);
        fashionBox.setPadding(new Insets(12));
        fashionBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10;"
                + "-fx-border-color:#E5CDC1; -fx-border-radius:10;");
        fashionBox.getChildren().addAll(fashionIcon, fashionText, fashionGrow, fashionRate);

        commissionCard.getChildren().addAll(
                commissionTitle,
                commissionSub,
                groceryBox,
                electronicsBox,
                fashionBox);

        VBox financialCard = new VBox(14);
        financialCard.setPrefWidth(560);
        financialCard.setPadding(new Insets(20));
        financialCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text financialTitle = new Text("Financial Thresholds");
        financialTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        TextField payout = new TextField("$  50.00");
        TextField fee = new TextField("$  0.30");
        payout.setPrefHeight(50);
        fee.setPrefHeight(50);
        payout.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1;"
                + "-fx-border-radius:8; -fx-background-radius:8;");
        fee.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1;"
                + "-fx-border-radius:8; -fx-background-radius:8;");

        Text payoutLabel = new Text("Minimum Payout Threshold");
        payoutLabel.setFont(Font.font("Arial", 14));
        VBox payoutBox = new VBox(7);
        payoutBox.getChildren().addAll(payoutLabel, payout);

        Text feeLabel = new Text("Standard Platform Fee (Flat)");
        feeLabel.setFont(Font.font("Arial", 14));
        VBox feeBox = new VBox(7);
        feeBox.getChildren().addAll(feeLabel, fee);
        HBox.setHgrow(payoutBox, Priority.ALWAYS);
        HBox.setHgrow(feeBox, Priority.ALWAYS);
        HBox fields = new HBox(18, payoutBox, feeBox);
        financialCard.getChildren().addAll(financialTitle, fields);
        centerBox.getChildren().addAll(commissionCard, financialCard);

        VBox statusBox = new VBox(18);
        statusBox.setPrefWidth(230);

        VBox systemCard = new VBox(14);
        systemCard.setPrefWidth(230);
        systemCard.setPadding(new Insets(20));
        systemCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text systemTitle = new Text("●  System Status");
        systemTitle.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        systemTitle.setFill(Color.web("#9B3100"));
        Text uptimeText = new Text("Server Uptime");
        uptimeText.setFont(Font.font("Arial", 14));
        Text uptimeValue = new Text("99.99%");
        uptimeValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region uptimeGrow = new Region();
        HBox.setHgrow(uptimeGrow, Priority.ALWAYS);
        HBox uptimeBox = new HBox();
        uptimeBox.getChildren().addAll(uptimeText, uptimeGrow, uptimeValue);

        Text sessionText = new Text("Active Sessions");
        sessionText.setFont(Font.font("Arial", 14));
        Text sessionValue = new Text("12");
        sessionValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region sessionGrow = new Region();
        HBox.setHgrow(sessionGrow, Priority.ALWAYS);
        HBox sessionBox = new HBox();
        sessionBox.getChildren().addAll(sessionText, sessionGrow, sessionValue);

        Text latencyText = new Text("API Latency");
        latencyText.setFont(Font.font("Arial", 14));
        Text latencyValue = new Text("42ms");
        latencyValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region latencyGrow = new Region();
        HBox.setHgrow(latencyGrow, Priority.ALWAYS);
        HBox latencyBox = new HBox();
        latencyBox.getChildren().addAll(latencyText, latencyGrow, latencyValue);

        Separator statusSeparator1 = new Separator();
        Separator statusSeparator2 = new Separator();

        systemCard.getChildren().addAll(
                systemTitle,
                uptimeBox,
                statusSeparator1,
                sessionBox,
                statusSeparator2,
                latencyBox);

        VBox auditCard = new VBox(14);
        auditCard.setPrefWidth(230);
        auditCard.setPadding(new Insets(20));
        auditCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text auditTitle = new Text("Security Audit");
        auditTitle.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        Text score = new Text("98 / 100");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        Button report = new Button("View Full Report");
        report.setMaxWidth(Double.MAX_VALUE);
        report.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");
        Text mfaText = new Text("⊙  MFA enforced for Admins");
        mfaText.setFont(Font.font("Arial", 13));
        Text encryptionText = new Text("⊙  Data encryption active");
        encryptionText.setFont(Font.font("Arial", 13));
        Text apiPendingText = new Text("ⓘ  1 API key pending");
        apiPendingText.setFont(Font.font("Arial", 13));

        auditCard.getChildren().addAll(
                auditTitle,
                score,
                mfaText,
                encryptionText,
                apiPendingText,
                report);
        statusBox.getChildren().addAll(systemCard, auditCard);

        settingBoxes.getChildren().addAll(settingMenu, centerBox, statusBox);

        Button discard = new Button("Discard Changes");
        discard.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");
        Button save = new Button("Save Configuration");
        discard.setPrefSize(190, 50);
        save.setPrefSize(215, 50);
        save.setStyle("-fx-background-color:#D94F00; -fx-text-fill:white; -fx-font-weight:bold; -fx-font-size:14px;");
        HBox actions = new HBox(15, discard, save);
        actions.setAlignment(Pos.CENTER);

        content.getChildren().addAll(heading, settingBoxes, actions);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: #eee5df; -fx-border-color:transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        rightBox.getChildren().addAll(top, scroll);
        root.setCenter(rightBox);

        return new Scene(root, 1550, 850);
    }



    private HBox createTopActions() {

        Image notificationImage = new Image(
                getClass().getResource("/assets/images/admin/bell.png").toExternalForm());

        ImageView notificationIcon = new ImageView(notificationImage);
        notificationIcon.setFitWidth(22);
        notificationIcon.setFitHeight(22);
        notificationIcon.setPreserveRatio(true);

        Text notificationName = new Text("Notifications");
        notificationName.setFont(Font.font("Arial", 14));

        HBox notificationAction = new HBox(
                6,
                notificationIcon,
                notificationName);

        notificationAction.setAlignment(Pos.CENTER_LEFT);
        notificationAction.setStyle("-fx-cursor:hand;");

        Image chatbotImage = new Image(
                getClass().getResource("/assets/images/admin/message.png").toExternalForm());

        ImageView chatbotIcon = new ImageView(chatbotImage);
        chatbotIcon.setFitWidth(22);
        chatbotIcon.setFitHeight(22);
        chatbotIcon.setPreserveRatio(true);

        Text chatbotName = new Text("ChatBot");
        chatbotName.setFont(Font.font("Arial", 14));

        HBox chatbotAction = new HBox(
                6,
                chatbotIcon,
                chatbotName);

        chatbotAction.setAlignment(Pos.CENTER_LEFT);
        chatbotAction.setStyle("-fx-cursor:hand;");

        chatbotAction.setOnMouseClicked(e -> {

            SmartAssistantUI chatPage =
                    new SmartAssistantUI();

            Homepage.HomepageStage.setScene(
                    chatPage.getUserScene());
        });

        javafx.stage.Popup notificationPopup =
                new javafx.stage.Popup();

        Text notificationTitle =
                new Text("Notifications");

        notificationTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        Button markRead =
                new Button("Mark all as read");

        markRead.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#E65300;" +
                "-fx-font-size:13px;");

        Region notificationGrow =
                new Region();

        HBox.setHgrow(
                notificationGrow,
                Priority.ALWAYS);

        HBox notificationHeader =
                new HBox(
                        notificationTitle,
                        notificationGrow,
                        markRead);

        notificationHeader.setAlignment(
                Pos.CENTER_LEFT);

        Text notificationText1 =
                new Text(
                        "New Shop Registration\n" +
                        "Tech Haven needs verification\n" +
                        "2 mins ago");

        notificationText1.setStyle(
                "-fx-font-size:13px;");

        VBox notification1 =
                new VBox(notificationText1);

        notification1.setPadding(
                new Insets(12));

        notification1.setStyle(
                "-fx-background-color:#FFF4ED;" +
                "-fx-background-radius:8;");

        Text notificationText2 =
                new Text(
                        "New User Joined\n" +
                        "New customer account created\n" +
                        "10 mins ago");

        notificationText2.setStyle(
                "-fx-font-size:13px;");

        VBox notification2 =
                new VBox(notificationText2);

        notification2.setPadding(
                new Insets(12));

        notification2.setStyle(
                "-fx-background-color:#F4FFF7;" +
                "-fx-background-radius:8;");

        Text notificationText3 =
                new Text(
                        "Flagged Account\n" +
                        "Suspicious activity detected\n" +
                        "1 hour ago");

        notificationText3.setStyle(
                "-fx-font-size:13px;");

        VBox notification3 =
                new VBox(notificationText3);

        notification3.setPadding(
                new Insets(12));

        notification3.setStyle(
                "-fx-background-color:#FFF5F5;" +
                "-fx-background-radius:8;");

        VBox notificationBox =
                new VBox(
                        12,
                        notificationHeader,
                        new Separator(),
                        notification1,
                        notification2,
                        notification3);

        notificationBox.setPrefWidth(330);
        notificationBox.setPadding(
                new Insets(18));

        notificationBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#E5E1E8;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;" +
                "-fx-effect:dropshadow(gaussian," +
                "rgba(0,0,0,0.18),15,0,0,5);");

        notificationPopup
                .getContent()
                .add(notificationBox);

        notificationAction.setOnMouseClicked(e -> {

            if (notificationPopup.isShowing()) {

                notificationPopup.hide();

            } else {

                javafx.geometry.Bounds bellPosition =
                        notificationAction.localToScreen(
                                notificationAction.getBoundsInLocal());

                if (bellPosition != null) {

                    notificationPopup.show(
                            notificationAction,
                            bellPosition.getMaxX() - 330,
                            bellPosition.getMaxY() + 10);
                }
            }
        });

        markRead.setOnAction(e -> {

            notification1.setStyle(
                    "-fx-background-color:white;");

            notification2.setStyle(
                    "-fx-background-color:white;");

            notification3.setStyle(
                    "-fx-background-color:white;");
        });

        HBox topActions =
                new HBox(
                        24,
                        notificationAction,
                        chatbotAction);

        topActions.setAlignment(
                Pos.CENTER_RIGHT);

        return topActions;
    }

}
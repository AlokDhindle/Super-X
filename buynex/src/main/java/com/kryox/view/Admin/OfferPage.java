package com.kryox.view.Admin;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class OfferPage {

        public Scene getUserScene() {
                VBox left = new VBox();

                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));

                left.setStyle(
                                "-fx-background-color: #ebccb7");

                Text logo = new Text("Admin Panel");

                logo.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                logo.setFill(
                                Color.web("#A83E00"));

                Text controller = new Text(
                                "Marketplace Controller");

                controller.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                controller.setFill(
                                Color.web("#999999"));

                VBox logoBox = new VBox(
                                4,
                                logo,
                                controller);

                VBox menu = new VBox();

                menu.setSpacing(4);

                HBox dashboard = new HBox();

                dashboard.setSpacing(10);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setPadding(
                                new Insets(10, 12, 10, 12));
                dashboard.setPrefWidth(180);

                dashboard.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img1 = new Image(
                                getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());

                ImageView iv1 = new ImageView(img1);

                iv1.setFitWidth(20);
                iv1.setFitHeight(20);
                iv1.setPreserveRatio(true);

                Text dashboardText = new Text(
                                "Dashboard");

                dashboardText.setFill(
                                Color.web("#333333"));

                dashboardText.setFont(
                                Font.font("Arial", 14));

                dashboard.getChildren().addAll(
                                iv1,
                                dashboardText);

                dashboard.setOnMouseEntered(e -> {

                        dashboard.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(Color.WHITE);

                        dashboardText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                dashboard.setOnMouseExited(e -> {

                        dashboard.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(
                                        Color.web("#333333"));

                        dashboardText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                dashboard.setOnMouseClicked(e -> {

                        AdminDashboardPage dashboardPage = new AdminDashboardPage();

                        Homepage.HomepageStage.setScene(
                                        dashboardPage.getUserScene());
                });

                HBox users = new HBox();

                users.setSpacing(10);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setPadding(
                                new Insets(10, 12, 10, 12));
                users.setPrefWidth(180);

                users.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-background-radius:10;");

                Image img2 = new Image("assets\\images\\admin\\admin_logo.png");

                ImageView iv2 = new ImageView(img2);

                iv2.setFitWidth(20);
                iv2.setFitHeight(20);
                iv2.setPreserveRatio(true);

                Text usersText = new Text("Users");

                usersText.setFill(Color.WHITE);

                usersText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                users.getChildren().addAll(
                                iv2,
                                usersText);

                users.setOnMouseEntered(e -> {

                        users.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.WHITE);

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        users);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                users.setOnMouseExited(e -> {

                        users.setStyle(
                                        "-fx-background-color:transprant;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(
                                        Color.web("#333333"));

                        usersText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        users);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                users.setOnMouseClicked(e -> {

                        UserManagementPage userPage = new UserManagementPage();

                        Homepage.HomepageStage.setScene(
                                        userPage.getUserScene());
                });

                HBox shops = new HBox();

                shops.setSpacing(10);
                shops.setAlignment(Pos.CENTER_LEFT);
                shops.setPadding(
                                new Insets(10, 12, 10, 12));
                shops.setPrefWidth(180);

                shops.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img3 = new Image(
                                getClass().getResource("/assets/images/admin/shop.png").toExternalForm());

                ImageView iv3 = new ImageView(img3);

                iv3.setFitWidth(20);
                iv3.setFitHeight(20);
                iv3.setPreserveRatio(true);

                Text shopsText = new Text("Shops");

                shopsText.setFill(
                                Color.web("#333333"));

                shopsText.setFont(
                                Font.font("Arial", 14));

                shops.getChildren().addAll(
                                iv3,
                                shopsText);

                shops.setOnMouseEntered(e -> {

                        shops.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(Color.WHITE);

                        shopsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        shops);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                shops.setOnMouseExited(e -> {

                        shops.setStyle(
                                        "-fx-background-color:transprant;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(
                                        Color.web("#333333"));

                        shopsText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        shops);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                shops.setOnMouseClicked(e -> {

                        ShopVerificationPage shopPage = new ShopVerificationPage();

                        Homepage.HomepageStage.setScene(
                                        shopPage.getUserScene());
                });

                // =========================
                // DELIVERY
                // =========================

                HBox delivery = new HBox();
                delivery.setSpacing(10);
                delivery.setAlignment(Pos.CENTER_LEFT);
                delivery.setPadding(new Insets(10, 12, 10, 12));
                delivery.setPrefWidth(180);
                delivery.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Text deliveryIcon = new Text("🚚");
                deliveryIcon.setFont(Font.font("Arial", 18));

                Text deliveryText = new Text("Delivery");
                deliveryText.setFill(Color.web("#333333"));
                deliveryText.setFont(Font.font("Arial", 14));

                delivery.getChildren().addAll(
                                deliveryIcon,
                                deliveryText);

                delivery.setOnMouseEntered(e -> {
                        delivery.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        deliveryText.setFill(Color.WHITE);
                        deliveryText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        delivery);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                delivery.setOnMouseExited(e -> {
                        delivery.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        deliveryText.setFill(Color.web("#333333"));
                        deliveryText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        delivery);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                delivery.setOnMouseClicked(e -> {

                        DeliveryVerificationPage deliveryPage =
                                        new DeliveryVerificationPage();

                        Homepage.HomepageStage.setScene(
                                        deliveryPage.getUserScene());
                });

                HBox offers = new HBox();

                offers.setSpacing(10);
                offers.setAlignment(Pos.CENTER_LEFT);
                offers.setPadding(
                                new Insets(10, 12, 10, 12));
                offers.setPrefWidth(180);

                offers.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img4 = new Image(
                                getClass().getResource("/assets/images/admin/tag.png").toExternalForm());

                ImageView iv4 = new ImageView(img4);

                iv4.setFitWidth(20);
                iv4.setFitHeight(20);
                iv4.setPreserveRatio(true);

                Text offersText = new Text("Offers");

                offersText.setFill(
                                Color.web("#333333"));

                offersText.setFont(
                                Font.font("Arial", 14));

                offers.getChildren().addAll(
                                iv4,
                                offersText);

                offers.setOnMouseEntered(e -> {

                        offers.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(Color.WHITE);

                        offersText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        offers);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                offers.setOnMouseExited(e -> {

                        offers.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(
                                        Color.web("#333333"));

                        offersText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        offers);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                offers.setOnMouseClicked(e -> {

                        OfferPage offerPage = new OfferPage();

                        Homepage.HomepageStage.setScene(
                                        offerPage.getUserScene());
                });

                HBox analytics = new HBox();

                analytics.setSpacing(10);
                analytics.setAlignment(Pos.CENTER_LEFT);
                analytics.setPadding(
                                new Insets(10, 12, 10, 12));
                analytics.setPrefWidth(180);

                analytics.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img5 = new Image(
                                getClass().getResource("/assets/images/admin/stats.png").toExternalForm());

                ImageView iv5 = new ImageView(img5);

                iv5.setFitWidth(20);
                iv5.setFitHeight(20);
                iv5.setPreserveRatio(true);

                Text analyticsText = new Text(
                                "Analytics");

                analyticsText.setFill(
                                Color.web("#333333"));

                analyticsText.setFont(
                                Font.font("Arial", 14));

                analytics.getChildren().addAll(
                                iv5,
                                analyticsText);

                analytics.setOnMouseEntered(e -> {

                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(Color.WHITE);

                        analyticsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        analytics);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                analytics.setOnMouseExited(e -> {

                        analytics.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(
                                        Color.web("#333333"));

                        analyticsText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        analytics);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                analytics.setOnMouseClicked(e -> {

                        AdminAnalyticsPage analyticsPage = new AdminAnalyticsPage();

                        Homepage.HomepageStage.setScene(
                                        analyticsPage.getUserScene());
                });

                menu.getChildren().addAll(
                                dashboard,
                                users,
                                shops,
                                delivery,
                                offers,
                                analytics);

                VBox bottomMenu = new VBox();

                bottomMenu.setSpacing(4);

                HBox settings = new HBox();

                settings.setSpacing(10);
                settings.setAlignment(Pos.CENTER_LEFT);
                settings.setPadding(
                                new Insets(10, 12, 10, 12));
                settings.setPrefWidth(180);

                settings.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img6 = new Image(
                                getClass().getResource("/assets/images/admin/setting.png").toExternalForm());

                ImageView iv6 = new ImageView(img6);

                iv6.setFitWidth(20);
                iv6.setFitHeight(20);
                iv6.setPreserveRatio(true);

                Text settingsText = new Text(
                                "Settings");

                settingsText.setFill(
                                Color.web("#333333"));

                settingsText.setFont(
                                Font.font("Arial", 14));

                settings.getChildren().addAll(
                                iv6,
                                settingsText);
                settings.setOnMouseEntered(e -> {

                        settings.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        settingsText.setFill(Color.WHITE);

                        settingsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        settings);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                settings.setOnMouseExited(e -> {

                        settings.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        settingsText.setFill(
                                        Color.web("#333333"));

                        settingsText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        settings);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                settings.setOnMouseClicked(e -> {

                        SettingsPage userPage = new SettingsPage();

                        Homepage.HomepageStage.setScene(
                                        userPage.getUserScene());
                });
                
                HBox support = new HBox();

                support.setSpacing(10);
                support.setAlignment(Pos.CENTER_LEFT);
                support.setPadding(
                                new Insets(10, 12, 10, 12));
                support.setPrefWidth(180);

                support.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img7 = new Image(
                                getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());

                ImageView iv7 = new ImageView(img7);

                iv7.setFitWidth(20);
                iv7.setFitHeight(20);
                iv7.setPreserveRatio(true);

                Text supportText = new Text(
                                "Support");

                supportText.setFill(
                                Color.web("#333333"));

                supportText.setFont(
                                Font.font("Arial", 14));

                support.getChildren().addAll(
                                iv7,
                                supportText);

                bottomMenu.getChildren().addAll(
                                settings,
                                support);
                support.setOnMouseEntered(e -> {

                        support.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        supportText.setFill(Color.WHITE);

                        supportText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        support);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                support.setOnMouseExited(e -> {

                        support.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        supportText.setFill(
                                        Color.web("#333333"));

                        supportText.setFont(
                                        Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        support);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                supportText.setOnMouseClicked(e -> {

                        SupportPage userPage = new SupportPage();

                        Homepage.HomepageStage.setScene(
                                        userPage.getUserScene());
                });

                
                // =========================
                // PROFILE
                // =========================

                AdminProfileCard adminProfileCard =
                                new AdminProfileCard();

                HBox profile =
                                adminProfileCard.getProfileCard();

                Region leftGrow = new Region();

                VBox.setVgrow(
                                leftGrow,
                                Priority.ALWAYS);

                left.getChildren().addAll(
                                logoBox,
                                menu,
                                new Separator(),
                                bottomMenu,
                                leftGrow,
                                profile);

                // =========================
                // CENTER CONTENT
                // =========================

                VBox centerContent = new VBox();
                centerContent.setSpacing(20);
                centerContent.setPadding(new Insets(30));
                centerContent.setStyle("-fx-background-color: #eee5df;");

                // =========================
                // PAGE TITLE
                // =========================

                Text pageTitle = new Text("Offers & Promotions");
                pageTitle.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 32));

                Text pageSubtitle = new Text(
                                "Manage network-wide campaigns and monitor shop performance.");
                pageSubtitle.setFont(
                                Font.font("Georgia", 16));
                pageSubtitle.setFill(Color.web("#777777"));

                VBox titleBox = new VBox(6);
                titleBox.getChildren().addAll(
                                pageTitle,
                                pageSubtitle);

                // =========================
                // MAIN CONTENT
                // =========================

                HBox mainContent = new HBox();
                mainContent.setSpacing(25);

                // =========================
                // LEFT FILTER BOX
                // =========================

                VBox filterBox = new VBox();
                filterBox.setSpacing(10);
                filterBox.setPadding(new Insets(10));
                filterBox.setPrefWidth(175);
                filterBox.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;" +
                                                "-fx-border-color:#E4C7B7;" +
                                                "-fx-border-radius:14;");

                // Marketwide
                HBox marketwide = new HBox(10);
                marketwide.setAlignment(Pos.CENTER_LEFT);
                marketwide.setPadding(new Insets(10));
                marketwide.setStyle(
                                "-fx-background-color:#FFD9C5;" +
                                                "-fx-background-radius:9;");

                Text marketIcon = new Text("◉");
                marketIcon.setFont(Font.font("Georgia", 16));
                marketIcon.setFill(Color.web("#A83E00"));

                Text marketText = new Text("Marketwide");
                marketText.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 15));
                marketText.setFill(Color.web("#8E3D18"));

                marketwide.getChildren().addAll(
                                marketIcon,
                                marketText);

                // Shop Specific
                HBox shopSpecific = new HBox(10);
                shopSpecific.setAlignment(Pos.CENTER_LEFT);
                shopSpecific.setPadding(new Insets(8));

                Text shopIcon = new Text("▣");
                shopIcon.setFont(Font.font("Georgia", 16));
                Text shopText = new Text("Shop-\nSpecific");

                shopText.setFont(
                                Font.font("Georgia", 14));

                shopSpecific.getChildren().addAll(
                                shopIcon,
                                shopText);

                // Seasonal
                HBox seasonal = new HBox(10);
                seasonal.setAlignment(Pos.CENTER_LEFT);
                seasonal.setPadding(new Insets(8));

                Text seasonalIcon = new Text("□");
                seasonalIcon.setFont(Font.font("Georgia", 16));
                Text seasonalText = new Text("Seasonal");

                seasonalText.setFont(
                                Font.font("Georgia", 14));

                seasonal.getChildren().addAll(
                                seasonalIcon,
                                seasonalText);

                // AI Suggested
                HBox aiSuggested = new HBox(10);
                aiSuggested.setAlignment(Pos.CENTER_LEFT);
                aiSuggested.setPadding(new Insets(8));

                Text aiIcon = new Text("✣");
                aiIcon.setFont(Font.font("Georgia", 16));
                Text aiText = new Text("AI-Suggested");

                aiText.setFont(
                                Font.font("Georgia", 14));

                aiSuggested.getChildren().addAll(
                                aiIcon,
                                aiText);

                filterBox.getChildren().addAll(
                                marketwide,
                                shopSpecific,
                                seasonal,
                                aiSuggested);

                // =========================
                // CENTER PROMOTIONS
                // =========================

                VBox promotions = new VBox();
                promotions.setSpacing(18);
                promotions.setPrefWidth(630);

                // =========================
                // CREATE CAMPAIGN HEADER
                // =========================

                HBox campaignHeader = new HBox();
                campaignHeader.setAlignment(Pos.CENTER_LEFT);
                campaignHeader.setPadding(new Insets(18));

                Text showing = new Text(
                                "Showing Active Marketwide promotions");

                showing.setFont(
                                Font.font("Georgia", 15));

                Region headerGrow = new Region();
                HBox.setHgrow(
                                headerGrow,
                                Priority.ALWAYS);

                Button createCampaign = new Button("+  Create Global Campaign");

                createCampaign.setPrefHeight(44);
                createCampaign.setStyle(
                                "-fx-background-color:#B84300;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-background-radius:8;");

                campaignHeader.getChildren().addAll(
                                showing,
                                headerGrow,
                                createCampaign);

                campaignHeader.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;" +
                                                "-fx-border-color:#E4C7B7;" +
                                                "-fx-border-radius:14;");
                createCampaign.setOnAction(event ->{
                        CreateCampaignPage campaign = new CreateCampaignPage();
                        Homepage.HomepageStage.setScene(campaign.getCampaignScene());
                });
                // =========================
                // PROMOTION CARD 1
                // =========================

                VBox promotion1 = new VBox();
                promotion1.setSpacing(12);
                promotion1.setPadding(new Insets(20));
                promotion1.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;" +
                                                "-fx-border-color:#E4C7B7;" +
                                                "-fx-border-radius:14;");

                HBox promotionTitle1 = new HBox();
                promotionTitle1.setAlignment(Pos.CENTER_LEFT);

                Text summer = new Text("Summer Tech Fest");
                summer.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 19));

                Text active = new Text("Active");
                active.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 13));
                active.setFill(Color.web("#8E3D18"));

                Region grow1 = new Region();
                HBox.setHgrow(
                                grow1,
                                Priority.ALWAYS);

                promotionTitle1.getChildren().addAll(
                                summer,
                                grow1,
                                active);

                Text summerDescription = new Text(
                                "15% off electronics network-wide to boost Q3 electronics slump.");

                summerDescription.setFont(
                                Font.font("Georgia", 14));
                summerDescription.setFill(
                                Color.web("#777777"));

                Separator separator1 = new Separator();

                HBox stats1 = new HBox();
                stats1.setSpacing(70);

                VBox conversion = new VBox(5);

                Text conversionTitle = new Text("Conversion Rate");
                conversionTitle.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                conversionTitle.setFill(Color.GRAY);

                Text conversionValue = new Text("4.2% ↗");
                conversionValue.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));

                conversion.getChildren().addAll(
                                conversionTitle,
                                conversionValue);

                VBox shopsCount = new VBox(5);

                Text shopsTitle = new Text("Participating Shops");
                shopsTitle.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                shopsTitle.setFill(Color.GRAY);

                Text shopsValue = new Text("142");
                shopsValue.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));

                shopsCount.getChildren().addAll(
                                shopsTitle,
                                shopsValue);

                VBox networkLift = new VBox(5);

                Text liftTitle = new Text("Est. Network Lift");
                liftTitle.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                liftTitle.setFill(Color.GRAY);

                Text liftValue = new Text("+$42k");
                liftValue.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));
                liftValue.setFill(Color.web("#A83E00"));

                networkLift.getChildren().addAll(
                                liftTitle,
                                liftValue);

                stats1.getChildren().addAll(
                                conversion,
                                shopsCount,
                                networkLift);

                promotion1.getChildren().addAll(
                                promotionTitle1,
                                summerDescription,
                                separator1,
                                stats1);

                // =========================
                // PROMOTION CARD 2
                // =========================

                VBox promotion2 = new VBox();
                promotion2.setSpacing(12);
                promotion2.setPadding(new Insets(20));
                promotion2.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;" +
                                                "-fx-border-color:#E4C7B7;" +
                                                "-fx-border-radius:14;");

                HBox promotionTitle2 = new HBox();
                promotionTitle2.setAlignment(Pos.CENTER_LEFT);

                Text groceries = new Text("Fresh Groceries Push");
                groceries.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 19));
                groceries.setFill(Color.web("#555555"));

                Text paused = new Text("Paused");
                paused.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 13));
                paused.setFill(Color.GRAY);

                Region grow2 = new Region();
                HBox.setHgrow(
                                grow2,
                                Priority.ALWAYS);

                promotionTitle2.getChildren().addAll(
                                groceries,
                                grow2,
                                paused);

                Text groceryDescription = new Text(
                                "Free delivery on orders over $50 for fresh produce partners.");

                groceryDescription.setFont(
                                Font.font("Georgia", 14));
                groceryDescription.setFill(
                                Color.web("#888888"));

                Separator separator2 = new Separator();

                HBox stats2 = new HBox();
                stats2.setSpacing(70);

                VBox conversion2 = new VBox(5);

                Text conversionTitle2 = new Text("Conversion Rate");
                conversionTitle2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                conversionTitle2.setFill(Color.GRAY);

                Text conversionValue2 = new Text("--");
                conversionValue2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));

                conversion2.getChildren().addAll(
                                conversionTitle2,
                                conversionValue2);

                VBox shopsCount2 = new VBox(5);

                Text shopsTitle2 = new Text("Participating Shops");
                shopsTitle2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                shopsTitle2.setFill(Color.GRAY);

                Text shopsValue2 = new Text("45");
                shopsValue2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));

                shopsCount2.getChildren().addAll(
                                shopsTitle2,
                                shopsValue2);

                VBox networkLift2 = new VBox(5);

                Text liftTitle2 = new Text("Est. Network Lift");
                liftTitle2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));
                liftTitle2.setFill(Color.GRAY);

                Text liftValue2 = new Text("--");
                liftValue2.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 18));

                networkLift2.getChildren().addAll(
                                liftTitle2,
                                liftValue2);

                stats2.getChildren().addAll(
                                conversion2,
                                shopsCount2,
                                networkLift2);

                promotion2.getChildren().addAll(
                                promotionTitle2,
                                groceryDescription,
                                separator2,
                                stats2);

                // Add promotion cards
                promotions.getChildren().addAll(
                                campaignHeader,
                                promotion1,
                                promotion2);

                VBox aiPromotion = new VBox();
                aiPromotion.setSpacing(15);
                aiPromotion.setPadding(new Insets(20));
                aiPromotion.setPrefWidth(275);

                aiPromotion.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;" +
                                                "-fx-border-color:#B84300;" +
                                                "-fx-border-width:2;" +
                                                "-fx-border-radius:14;");

                Text aiPromotionTitle = new Text(
                                "✣  AI Promotion\n    Engine");

                aiPromotionTitle.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 20));

                Text aiDescription = new Text(
                                "Real-time insights to optimize\nnetwork GMV.");

                aiDescription.setFont(
                                Font.font("Georgia", 14));
                aiDescription.setFill(
                                Color.web("#666666"));

                Text needsBoost = new Text(
                                "NEEDS BOOST (TRENDING\nDOWN)");

                needsBoost.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));

                // Chips
                HBox chip1 = new HBox();
                chip1.setPadding(new Insets(7, 12, 7, 12));
                chip1.setStyle(
                                "-fx-background-color:#F1ECEF;" +
                                                "-fx-background-radius:15;");

                Text chipText1 = new Text("Winter Apparel");
                chipText1.setFont(
                                Font.font("Georgia", 12));

                chip1.getChildren().add(chipText1);

                HBox chip2 = new HBox();
                chip2.setPadding(new Insets(7, 12, 7, 12));
                chip2.setStyle(
                                "-fx-background-color:#F1ECEF;" +
                                                "-fx-background-radius:15;");

                Text chipText2 = new Text("Home Goods");
                chipText2.setFont(
                                Font.font("Georgia", 12));

                chip2.getChildren().add(chipText2);

                HBox chip3 = new HBox();
                chip3.setPadding(new Insets(7, 12, 7, 12));
                chip3.setStyle(
                                "-fx-background-color:#F1ECEF;" +
                                                "-fx-background-radius:15;");

                Text chipText3 = new Text("Pet Supplies");
                chipText3.setFont(
                                Font.font("Georgia", 12));

                chip3.getChildren().add(chipText3);

                Text generateIdeas = new Text(
                                "Generate Campaign Ideas  →");

                generateIdeas.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 13));
                generateIdeas.setFill(
                                Color.web("#8E3D18"));

                Separator aiSeparator = new Separator();

                Text predicted = new Text(
                                "PREDICTED ROI (ACTIVE)");

                predicted.setFont(
                                Font.font("Georgia", FontWeight.BOLD, 12));

                Text roi1 = new Text(
                                "Summer Tech Fest                         2.4x");

                roi1.setFont(
                                Font.font("Georgia", 13));

                Text roi2 = new Text(
                                "Local Artisan Week                       1.8x");

                roi2.setFont(
                                Font.font("Georgia", 13));

                aiPromotion.getChildren().addAll(
                                aiPromotionTitle,
                                aiDescription,
                                needsBoost,
                                chip1,
                                chip2,
                                chip3,
                                generateIdeas,
                                aiSeparator,
                                predicted,
                                roi1,
                                roi2);

                mainContent.getChildren().addAll(
                                filterBox,
                                promotions,
                                aiPromotion);

                centerContent.getChildren().addAll(
                                titleBox,
                                mainContent);
                // =========================
                // MAIN BORDER PANE
                // =========================

                BorderPane root = new BorderPane();

                root.setLeft(left);

                root.setCenter(centerContent);
                root.setStyle("-fx-background-color: #eee5df;");

                Scene scene = new Scene(root, 1550, 850);

                return scene;
        }
}
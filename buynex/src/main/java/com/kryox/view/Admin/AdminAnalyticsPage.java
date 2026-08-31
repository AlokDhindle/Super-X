package com.kryox.view.Admin;

import java.util.ArrayList;

import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AdminAnalyticsPage {

        public Scene getUserScene() {

                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color:#F9F7FB;");

                VBox left = new VBox();
                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));
                left.setStyle("-fx-background-color: #ebccb7");

                Text logo = new Text("Admin Panel");
                logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                logo.setFill(Color.web("#A83E00"));

                Text adminPanel = new Text("Marketplace Controller");
                adminPanel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                adminPanel.setFill(Color.web("#999999"));

                VBox logoBox = new VBox(4, logo, adminPanel);

                VBox menu = new VBox();
                menu.setSpacing(4);

                // =========================
                // DASHBOARD
                // =========================

                HBox dashboard = new HBox();
                dashboard.setSpacing(10);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setPadding(new Insets(10, 12, 10, 12));
                dashboard.setPrefWidth(180);
                dashboard.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img1 = new Image(getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());
                ImageView iv1 = new ImageView(img1);
                iv1.setFitWidth(20);
                iv1.setFitHeight(20);
                iv1.setPreserveRatio(true);

                Text dashboardText = new Text("Dashboard");
                dashboardText.setFill(Color.web("#333333"));
                dashboardText.setFont(Font.font("Arial", 14));

                dashboard.getChildren().addAll(iv1, dashboardText);

                dashboard.setOnMouseEntered(e -> {
                        dashboard.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(Color.WHITE);
                        dashboardText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                dashboard.setOnMouseExited(e -> {
                        dashboard.setStyle(
                                        "-fx-background-color:transprant;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(Color.web("#333333"));
                        dashboardText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                dashboard.setOnMouseClicked(event ->{
                       AdminDashboardPage dashboardPage = new AdminDashboardPage();
                       Homepage.HomepageStage.setScene(dashboardPage.getUserScene()); 
                });

                // =========================
                // USERS
                // =========================

                HBox users = new HBox();
                users.setSpacing(10);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setPadding(new Insets(10, 12, 10, 12));
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
                usersText.setFill(Color.web("#333333"));
                usersText.setFont(Font.font("Arial", 14));

                users.getChildren().addAll(iv2, usersText);

                users.setOnMouseEntered(e -> {
                        users.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.WHITE);
                        usersText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        users);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                users.setOnMouseExited(e -> {
                        users.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.web("#333333"));
                        usersText.setFont(Font.font("Arial", 14));

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

                // =========================
                // SHOPS
                // =========================

                HBox shops = new HBox();
                shops.setSpacing(10);
                shops.setAlignment(Pos.CENTER_LEFT);
                shops.setPadding(new Insets(10, 12, 10, 12));
                shops.setPrefWidth(180);
                shops.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img3 = new Image(getClass().getResource("/assets/images/admin/shop.png").toExternalForm());
                ImageView iv3 = new ImageView(img3);
                iv3.setFitWidth(20);
                iv3.setFitHeight(20);
                iv3.setPreserveRatio(true);

                Text shopsText = new Text("Shops");
                shopsText.setFill(Color.web("#333333"));
                shopsText.setFont(Font.font("Arial", 14));

                shops.getChildren().addAll(iv3, shopsText);

                shops.setOnMouseEntered(e -> {
                        shops.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(Color.WHITE);
                        shopsText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        shops);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                shops.setOnMouseExited(e -> {
                        shops.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(Color.web("#333333"));
                        shopsText.setFont(Font.font("Arial", 14));

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
                offers.setPadding(new Insets(10, 12, 10, 12));
                offers.setPrefWidth(180);
                offers.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img4 = new Image(getClass().getResource("/assets/images/admin/tag.png").toExternalForm());
                ImageView iv4 = new ImageView(img4);
                iv4.setFitWidth(20);
                iv4.setFitHeight(20);
                iv4.setPreserveRatio(true);

                Text offersText = new Text("Offers");
                offersText.setFill(Color.web("#333333"));
                offersText.setFont(Font.font("Arial", 14));

                offers.getChildren().addAll(iv4, offersText);

                offers.setOnMouseEntered(e -> {
                        offers.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(Color.WHITE);
                        offersText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        offers);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                offers.setOnMouseExited(e -> {
                        offers.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(Color.web("#333333"));
                        offersText.setFont(Font.font("Arial", 14));

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
                analytics.setPadding(new Insets(10, 12, 10, 12));
                analytics.setPrefWidth(180);
                analytics.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img5 = new Image(getClass().getResource("/assets/images/admin/stats.png").toExternalForm());
                ImageView iv5 = new ImageView(img5);
                iv5.setFitWidth(20);
                iv5.setFitHeight(20);
                iv5.setPreserveRatio(true);

                Text analyticsText = new Text("Analytics");
                analyticsText.setFill(Color.web("#333333"));
                analyticsText.setFont(Font.font("Arial", 14));

                analytics.getChildren().addAll(iv5, analyticsText);

                analytics.setOnMouseEntered(e -> {
                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(Color.WHITE);
                        analyticsText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        analytics);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                analytics.setOnMouseExited(e -> {
                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(Color.web("#333333"));
                        analyticsText.setFont(Font.font("Arial", 14));

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
                settings.setPadding(new Insets(10, 12, 10, 12));
                settings.setPrefWidth(180);
                settings.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img6 = new Image(getClass().getResource("/assets/images/admin/setting.png").toExternalForm());
                ImageView iv6 = new ImageView(img6);
                iv6.setFitWidth(20);
                iv6.setFitHeight(20);
                iv6.setPreserveRatio(true);

                Text settingsText = new Text("Settings");
                settingsText.setFill(Color.web("#333333"));
                settingsText.setFont(Font.font("Arial", 14));

                settings.getChildren().addAll(iv6, settingsText);
                settings.setOnMouseEntered(e -> {
                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        settingsText.setFill(Color.WHITE);
                        settingsText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

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

                        settingsText.setFill(Color.web("#333333"));
                        settingsText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        settings);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                settings.setOnMouseClicked(e -> {

                        SettingsPage setting = new SettingsPage();

                        Homepage.HomepageStage.setScene(setting.getUserScene());
                });

                HBox support = new HBox();
                support.setSpacing(10);
                support.setAlignment(Pos.CENTER_LEFT);
                support.setPadding(new Insets(10, 12, 10, 12));
                support.setPrefWidth(180);
                support.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img7 = new Image(getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
                ImageView iv7 = new ImageView(img7);
                iv7.setFitWidth(20);
                iv7.setFitHeight(20);
                iv7.setPreserveRatio(true);

                Text supportText = new Text("Support");
                supportText.setFill(Color.web("#333333"));
                supportText.setFont(Font.font("Arial", 14));

                support.getChildren().addAll(iv7, supportText);

                bottomMenu.getChildren().addAll(
                                settings,
                                support);

                support.setOnMouseEntered(e -> {
                        support.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        supportText.setFill(Color.WHITE);
                        supportText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 14));

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

                        supportText.setFill(Color.web("#333333"));
                        supportText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        support);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                support.setOnMouseClicked(e -> {

                        SupportPage supports  = new SupportPage();

                        Homepage.HomepageStage.setScene(supports.getUserScene());
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

                root.setLeft(left);

                // =========================================================
                // RIGHT MAIN CONTENT
                // =========================================================

                VBox rightBox = new VBox();

                rightBox.setSpacing(18);
                rightBox.setPadding(new Insets(25));

                rightBox.setStyle(
                                "-fx-background-color: #eee5df;");

                // =========================================================
                // HEADER
                // =========================================================

                Text title = new Text(
                                "AI Insights & Analytics");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                28));

                Text subtitle = new Text(
                                "Real-time performance metrics and predictive modeling for Zone B.");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                subtitle.setFill(
                                Color.web("#777777"));

                VBox heading = new VBox(
                                4,
                                title,
                                subtitle);

                Button excel = new Button("Excel");

                excel.setStyle(
                                "-fx-background-color:#F1EFF2;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-padding:8 16 8 16;");

                Button pdf = new Button("PDF Report");

                pdf.setTextFill(Color.WHITE);

                pdf.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-padding:8 16 8 16;");

                HBox buttons = new HBox(
                                10,
                                excel,
                                pdf);

                buttons.setAlignment(
                                Pos.CENTER_RIGHT);

                HBox top = new HBox();

                top.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                heading,
                                Priority.ALWAYS);

                top.getChildren().addAll(
                                heading,
                                buttons);

                // =========================================================
                // STAT CARDS
                // =========================================================

                VBox card1 = createStatCard(
                                "TOTAL REVENUE",
                                "$124,592",
                                "+12.5%");

                VBox card2 = createStatCard(
                                "ACTIVE SHOPS",
                                "1,240",
                                "+4.2%");

                VBox card3 = createStatCard(
                                "ORDER VOLUME",
                                "8,902",
                                "+8.5%");

                VBox card4 = createStatCard(
                                "FRAUD ALERTS",
                                "14",
                                "Action Needed");

                HBox cards = new HBox(
                                12,
                                card1,
                                card2,
                                card3,
                                card4);

                // =========================================================
                // REVENUE HEATMAP
                // =========================================================

                Text revenueTitle = new Text(
                                "Revenue Heatmap");

                revenueTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                VBox heatmap = new VBox(3);

                for (int i = 0; i < 4; i++) {

                        HBox row = new HBox(3);

                        for (int j = 0; j < 7; j++) {

                                Rectangle box = new Rectangle(
                                                44,
                                                38);

                                if (i == 0) {

                                        if (j == 1 || j == 4) {
                                                box.setFill(
                                                                Color.web("#D19B73"));
                                        } else if (j == 3 || j == 5) {
                                                box.setFill(
                                                                Color.web("#C46121"));
                                        } else {
                                                box.setFill(
                                                                Color.web("#EAD7C8"));
                                        }

                                } else if (i == 1) {

                                        if (j == 1 || j == 4 || j == 6) {
                                                box.setFill(
                                                                Color.web("#D19B73"));
                                        } else if (j == 2) {
                                                box.setFill(
                                                                Color.web("#C46121"));
                                        } else {
                                                box.setFill(
                                                                Color.web("#EAD7C8"));
                                        }

                                } else if (i == 2) {

                                        if (j == 1 || j == 5) {
                                                box.setFill(
                                                                Color.web("#D19B73"));
                                        } else if (j == 2) {
                                                box.setFill(
                                                                Color.web("#C46121"));
                                        } else {
                                                box.setFill(
                                                                Color.web("#EAD7C8"));
                                        }

                                } else {

                                        if (j == 0 || j == 3 || j == 6) {
                                                box.setFill(
                                                                Color.web("#D19B73"));
                                        } else {
                                                box.setFill(
                                                                Color.web("#EAD7C8"));
                                        }
                                }

                                row.getChildren().add(box);
                        }

                        heatmap.getChildren().add(row);
                }

                VBox revenue = new VBox(
                                14,
                                revenueTitle,
                                heatmap);

                revenue.setPadding(
                                new Insets(15));

                revenue.setPrefWidth(680);
                revenue.setPrefHeight(275);

                revenue.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#EEEEEE;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                // =========================================================
                // AI RECOMMENDATIONS
                // =========================================================

                Text aiTitle = new Text(
                                "✦ AI Recommendations");

                aiTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                Text ai1 = new Text(
                                "Inventory Surge Predicted\n" +
                                                "High demand predicted for Groceries in Zone B.\n" +
                                                "For the next 48 hours. Suggest flash sales.");

                ai1.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                Text ai2 = new Text(
                                "Security Alert\n" +
                                                "Fraud risk detected for User #882.\n" +
                                                "Pattern match: Unusual transaction volume.");

                ai2.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                Text ai3 = new Text(
                                "Operational Efficiency\n" +
                                                "Delivery routes in Zone C are 15% inefficient.\n" +
                                                "Reroute optimization available.");

                ai3.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                VBox ai1Box = new VBox(ai1);

                ai1Box.setPadding(
                                new Insets(10));

                ai1Box.setStyle(
                                "-fx-background-color:#FFF5F0;" +
                                                "-fx-background-radius:6;");

                VBox ai2Box = new VBox(ai2);

                ai2Box.setPadding(
                                new Insets(10));

                ai2Box.setStyle(
                                "-fx-background-color:#FFF0EE;" +
                                                "-fx-background-radius:6;");

                VBox ai3Box = new VBox(ai3);

                ai3Box.setPadding(
                                new Insets(10));

                ai3Box.setStyle(
                                "-fx-background-color:#F2F2F2;" +
                                                "-fx-background-radius:6;");

                Button view = new Button(
                                "View AI Insights");

                view.setPrefWidth(190);

                VBox ai = new VBox(
                                10,
                                aiTitle,
                                ai1Box,
                                ai2Box,
                                ai3Box,
                                view);

                ai.setPadding(
                                new Insets(15));

                ai.setPrefWidth(380);
                ai.setPrefHeight(275);

                ai.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E85B00;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                HBox middle = new HBox(
                                15,
                                revenue,
                                ai);

                // =========================================================
                // SALES FORECAST
                // =========================================================

                Text sales = new Text(
                                "Sales Forecast");

                sales.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                StackPane salesGraph = new StackPane();

                salesGraph.setPrefHeight(155);

                Image analysImage = new Image(getClass().getResource("/assets/images/admin/analysisgraph.png").toExternalForm());
                ImageView img = new ImageView(analysImage);
                img.setFitWidth(440);
                img.setFitHeight(150);
                img.setPreserveRatio(true);

                salesGraph.getChildren().add(
                                img);

                Text mon = new Text("Mon");
                Text tue = new Text("Tue");
                Text wed = new Text("Wed");
                Text thu = new Text("Thu");
                Text fri = new Text("Fri");
                Text sat = new Text("Sat");
                Text sun = new Text("Sun");
                

                HBox days = new HBox(
                        32,
                        mon,
                        tue,
                        wed,
                        thu,
                        fri,
                        sat,
                        sun);

                days.setAlignment(Pos.CENTER);
                days.setPrefWidth(440);

                VBox salesBox = new VBox(
                                12,
                                sales,
                                salesGraph,
                                days);

                salesBox.setPadding(
                                new Insets(15));

                salesBox.setPrefWidth(550);
                salesBox.setPrefHeight(165);

                salesBox.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#EEEEEE;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                // =========================================================
                // CATEGORY DISTRIBUTION
                // =========================================================

                Text category = new Text(
                                "Category Distribution");

                category.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                // FIX:
                // The original code used "chart" without declaring it.
                // Here we create a proper donut chart.

                StackPane chart = createCategoryChart();

                Text categoryText = new Text(
                                "● Groceries (45%)\n" +
                                                "● Electronics (30%)\n" +
                                                "● Fashion (15%)\n" +
                                                "● Others (10%)");

                categoryText.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                HBox categoryData = new HBox(
                                25,
                                chart,
                                categoryText);

                categoryData.setAlignment(
                                Pos.CENTER_LEFT);

                VBox categoryBox = new VBox(
                                18,
                                category,
                                categoryData);

                categoryBox.setPadding(
                                new Insets(15));

                categoryBox.setPrefWidth(555);
                categoryBox.setPrefHeight(165);

                categoryBox.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#EEEEEE;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                HBox charts = new HBox(
                                15,
                                salesBox,
                                categoryBox);

                // =========================================================
                // OFFER PERFORMANCE
                // =========================================================

                Text offerTitle = new Text(
                                "Offer Performance");

                offerTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                Text audit = new Text(
                                "Detailed Audit ↗");

                audit.setFill(
                                Color.web("#A83E00"));

                audit.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                Region offerSpace = new Region();

                HBox.setHgrow(
                                offerSpace,
                                Priority.ALWAYS);

                HBox offerTop = new HBox(
                                offerTitle,
                                offerSpace,
                                audit);

                offerTop.setAlignment(
                                Pos.CENTER_LEFT);

                Text h1 = new Text("CAMPAIGN NAME");
                Text h2 = new Text("CATEGORY");
                Text h3 = new Text("REDEMPTIONS");
                Text h4 = new Text("CONVERSION RATE");
                Text h5 = new Text("NET PROFIT");
                Text h6 = new Text("STATUS");

                Text[] headers = {
                                h1, h2, h3, h4, h5, h6
                };

                for (Text h : headers) {

                        h.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        11));

                        h.setFill(
                                        Color.web("#654A3E"));
                }

                h1.setWrappingWidth(190);
                h2.setWrappingWidth(145);
                h3.setWrappingWidth(140);
                h4.setWrappingWidth(200);
                h5.setWrappingWidth(145);
                h6.setWrappingWidth(120);

                HBox header = new HBox(
                                h1,
                                h2,
                                h3,
                                h4,
                                h5,
                                h6);

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setPadding(
                                new Insets(
                                                15,
                                                12,
                                                15,
                                                12));

                VBox offerRows = new VBox();
                offerRows.setSpacing(0);

                Text loadingOffers = new Text(
                                "Loading offers from Firestore...");

                loadingOffers.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                loadingOffers.setFill(
                                Color.web("#777777"));

                VBox loadingBox = new VBox(
                                loadingOffers);

                loadingBox.setPadding(
                                new Insets(
                                                20,
                                                12,
                                                20,
                                                12));

                offerRows.getChildren().add(
                                loadingBox);

                ScrollPane offerScroll =
                                new ScrollPane(
                                                offerRows);

                offerScroll.setFitToWidth(
                                true);

                offerScroll.setPrefHeight(
                                260);

                offerScroll.setMaxHeight(
                                320);

                offerScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                offerScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                offerScroll.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background:transparent;" +
                                                "-fx-border-color:transparent;");

                VBox offer = new VBox(
                                0,
                                offerTop,
                                header,
                                new Separator(),
                                offerScroll);

                offer.setPadding(
                                new Insets(15));

                offer.setMinHeight(250);

                offer.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#EEEEEE;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                new Thread(() -> {

                        ArrayList<OfferModel> allOffers =
                                        OfferController
                                                        .getAllOffersForAdmin();

                        Platform.runLater(() -> {

                                offerRows
                                                .getChildren()
                                                .clear();

                                if (allOffers == null ||
                                                allOffers.isEmpty()) {

                                        Text emptyText =
                                                        new Text(
                                                                        "No offers found.");

                                        emptyText.setFont(
                                                        Font.font(
                                                                        "Arial",
                                                                        13));

                                        emptyText.setFill(
                                                        Color.web(
                                                                        "#777777"));

                                        VBox emptyBox =
                                                        new VBox(
                                                                        emptyText);

                                        emptyBox.setPadding(
                                                        new Insets(
                                                                        20,
                                                                        12,
                                                                        20,
                                                                        12));

                                        offerRows
                                                        .getChildren()
                                                        .add(
                                                                        emptyBox);

                                        return;
                                }

                                for (int i = 0;
                                                i < allOffers.size();
                                                i++) {

                                        OfferModel offerModel =
                                                        allOffers.get(i);

                                        HBox row =
                                                        createOfferPerformanceRow(
                                                                        offerModel);

                                        offerRows
                                                        .getChildren()
                                                        .add(
                                                                        row);

                                        if (i <
                                                        allOffers.size()
                                                                        - 1) {

                                                offerRows
                                                                .getChildren()
                                                                .add(
                                                                                new Separator());
                                        }
                                }
                        });

                }).start();

                rightBox.getChildren().addAll(
                                top,
                                cards,
                                middle,
                                charts,
                                offer);

                // =========================================================
                // SCROLLPANE
                // =========================================================

                ScrollPane scrollPane = new ScrollPane();

                scrollPane.setContent(
                                rightBox);

                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color:#FBF9FC;" +
                                                "-fx-border-color:transparent;");

                // =========================================================
                // ROOT
                // =========================================================

                BorderPane rootpane = new BorderPane();

                root.setLeft(left);
                root.setCenter(scrollPane);

                root.setStyle(
                                "-fx-background-color: #eee5df;");

                // =========================================================
                // SCENE
                // =========================================================

                Scene scene = new Scene(
                                root,
                                1550,
                                850);

                return scene;
        }

        private HBox createOfferPerformanceRow(
                        OfferModel offerModel) {

                String campaignName =
                                safeText(
                                                offerModel.getOfferName(),
                                                "Unnamed Offer");

                String category =
                                safeText(
                                                offerModel.getCategory(),
                                                "General");

                int redemptions =
                                Math.max(
                                                0,
                                                offerModel.getRedemptions());

                int totalViews =
                                Math.max(
                                                0,
                                                offerModel.getTotalViews());

                double conversionRate = 0;

                if (totalViews > 0) {

                        conversionRate =
                                        (redemptions * 100.0)
                                                        / totalViews;
                }

                double netProfit =
                                offerModel.getNetProfit();

                String status =
                                resolveOfferStatus(
                                                offerModel);

                Text campaignText =
                                new Text(
                                                campaignName);

                campaignText.setWrappingWidth(
                                190);

                campaignText.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                Label categoryLabel =
                                createCategoryLabel(
                                                category);

                VBox categoryBox =
                                new VBox(
                                                categoryLabel);

                categoryBox.setPrefWidth(
                                145);

                Text redemptionText =
                                new Text(
                                                String.format(
                                                                "%,d",
                                                                redemptions));

                redemptionText.setWrappingWidth(
                                140);

                Rectangle conversionBar =
                                new Rectangle(
                                                Math.max(
                                                                2,
                                                                Math.min(
                                                                                75,
                                                                                conversionRate
                                                                                                * 0.75)),
                                                5);

                conversionBar.setFill(
                                Color.web(
                                                "#A83E00"));

                Text conversionText =
                                new Text(
                                                String.format(
                                                                "%.0f%%",
                                                                conversionRate));

                HBox conversionBox =
                                new HBox(
                                                10,
                                                conversionBar,
                                                conversionText);

                conversionBox.setAlignment(
                                Pos.CENTER_LEFT);

                conversionBox.setPrefWidth(
                                200);

                String profitValue =
                                String.format(
                                                "%s$%,.2f",
                                                netProfit >= 0
                                                                ? "+"
                                                                : "-",
                                                Math.abs(
                                                                netProfit));

                Text profitText =
                                new Text(
                                                profitValue);

                profitText.setWrappingWidth(
                                145);

                profitText.setFill(
                                netProfit >= 0
                                                ? Color.web(
                                                                "#A83E00")
                                                : Color.web(
                                                                "#C0392B"));

                boolean active =
                                "Active"
                                                .equalsIgnoreCase(
                                                                status);

                Label statusLabel =
                                createStatusLabel(
                                                status,
                                                active);

                VBox statusBox =
                                new VBox(
                                                statusLabel);

                statusBox.setPrefWidth(
                                120);

                HBox row =
                                new HBox(
                                                campaignText,
                                                categoryBox,
                                                redemptionText,
                                                conversionBox,
                                                profitText,
                                                statusBox);

                row.setAlignment(
                                Pos.CENTER_LEFT);

                row.setPadding(
                                new Insets(
                                                15,
                                                12,
                                                15,
                                                12));

                return row;
        }

        private String safeText(
                        String value,
                        String defaultValue) {

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value.trim();
        }

        private String resolveOfferStatus(
                        OfferModel offerModel) {

                String status =
                                offerModel.getStatus();

                if (status == null ||
                                status.trim().isEmpty()) {

                        return "Inactive";
                }

                if ("ACTIVE"
                                .equalsIgnoreCase(
                                                status)) {

                        return "Active";
                }

                if ("SCHEDULED"
                                .equalsIgnoreCase(
                                                status)) {

                        return "Scheduled";
                }

                if ("EXPIRED"
                                .equalsIgnoreCase(
                                                status)) {

                        return "Expired";
                }

                if ("INACTIVE"
                                .equalsIgnoreCase(
                                                status)) {

                        return "Inactive";
                }

                return status;
        }

        // =============================================================
        // CATEGORY DONUT CHART
        // =============================================================

        private StackPane createCategoryChart() {

                StackPane chart = new StackPane();

                chart.setPrefSize(
                                130,
                                130);

                // Background circle

                Circle background = new Circle(
                                48);

                background.setFill(
                                Color.web("#F1E8E2"));

                // Groceries - 45%

                Arc groceries = new Arc(
                                0,
                                0,
                                50,
                                50,
                                0,
                                162);

                groceries.setType(
                                ArcType.ROUND);

                groceries.setFill(
                                Color.web("#A83E00"));

                // Electronics - 30%

                Arc electronics = new Arc(
                                0,
                                0,
                                50,
                                50,
                                162,
                                108);

                electronics.setType(
                                ArcType.ROUND);

                electronics.setFill(
                                Color.web("#D1793D"));

                // Fashion - 15%

                Arc fashion = new Arc(
                                0,
                                0,
                                50,
                                50,
                                270,
                                54);

                fashion.setType(
                                ArcType.ROUND);

                fashion.setFill(
                                Color.web("#D9A47C"));

                // Others - 10%

                Arc others = new Arc(
                                0,
                                0,
                                50,
                                50,
                                324,
                                36);

                others.setType(
                                ArcType.ROUND);

                others.setFill(
                                Color.web("#E8CDBB"));

                // Center circle creates donut effect

                Circle center = new Circle(
                                32,
                                Color.WHITE);

                Text percent = new Text("100%");

                percent.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                Text market = new Text(
                                "Market Share");

                market.setFont(
                                Font.font(
                                                "Arial",
                                                10));

                market.setFill(
                                Color.web("#777777"));

                VBox percentBox = new VBox(
                                0,
                                percent,
                                market);

                percentBox.setAlignment(
                                Pos.CENTER);

                chart.getChildren().addAll(
                                background,
                                groceries,
                                electronics,
                                fashion,
                                others,
                                center,
                                percentBox);

                return chart;
        }

        // =============================================================
        // CATEGORY LABEL
        // =============================================================

        private Label createCategoryLabel(
                        String value) {

                Label label = new Label(value);

                label.setStyle(
                                "-fx-background-color:#EEECEF;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-padding:6 13 6 13;");

                return label;
        }

        // =============================================================
        // STATUS LABEL
        // =============================================================

        private Label createStatusLabel(
                        String value,
                        boolean active) {

                Label label = new Label(value);

                if (active) {

                        label.setStyle(
                                        "-fx-background-color:#FFF1EC;" +
                                                        "-fx-text-fill:#A83E00;" +
                                                        "-fx-background-radius:12;" +
                                                        "-fx-font-size:13px;" +
                                                        "-fx-padding:6 13 6 13;");

                } else {

                        label.setStyle(
                                        "-fx-background-color:#E7E5E8;" +
                                                        "-fx-text-fill:#555555;" +
                                                        "-fx-background-radius:12;" +
                                                        "-fx-font-size:13px;" +
                                                        "-fx-padding:6 13 6 13;");
                }

                return label;
        }

        // =============================================================
        // MENU ITEM
        // =============================================================

        private HBox createMenuItem(
                        String imagePath,
                        String textValue,
                        boolean active) {

                HBox item = new HBox();

                item.setSpacing(10);

                item.setAlignment(
                                Pos.CENTER_LEFT);

                item.setPadding(
                                new Insets(
                                                10,
                                                12,
                                                10,
                                                12));

                item.setPrefWidth(180);

                String normalStyle;

                if (active) {

                        normalStyle = "-fx-background-color:#FF6500;" +
                                        "-fx-background-radius:10;";

                } else {

                        normalStyle = "-fx-background-color:transparent;" +
                                        "-fx-background-radius:10;";
                }

                item.setStyle(normalStyle);

                // ---------------------------------------------------------
                // IMAGE
                // ---------------------------------------------------------

                try {

                        if (getClass().getResource(imagePath) != null) {

                                Image image = new Image(
                                                getClass()
                                                                .getResource(imagePath)
                                                                .toExternalForm());

                                ImageView imageView = new ImageView(
                                                image);

                                imageView.setFitWidth(20);
                                imageView.setFitHeight(20);
                                imageView.setPreserveRatio(true);

                                item.getChildren().add(
                                                imageView);
                        }

                } catch (Exception ignored) {
                        // Prevent missing icon from crashing the application
                }

                // ---------------------------------------------------------
                // TEXT
                // ---------------------------------------------------------

                Text text = new Text(
                                textValue);

                if (active) {

                        text.setFill(Color.WHITE);

                        text.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                } else {

                        text.setFill(
                                        Color.web("#333333"));

                        text.setFont(
                                        Font.font(
                                                        "Arial",
                                                        14));
                }

                item.getChildren().add(text);

                // ---------------------------------------------------------
                // HOVER
                // ---------------------------------------------------------

                item.setOnMouseEntered(e -> {

                        item.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        text.setFill(Color.WHITE);

                        text.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        14));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        item);

                        st.setToX(1.03);
                        st.setToY(1.03);

                        st.play();
                });

                item.setOnMouseExited(e -> {

                        item.setStyle(normalStyle);

                        if (active) {

                                text.setFill(Color.WHITE);

                                text.setFont(
                                                Font.font(
                                                                "Arial",
                                                                FontWeight.BOLD,
                                                                14));

                        } else {

                                text.setFill(
                                                Color.web("#333333"));

                                text.setFont(
                                                Font.font(
                                                                "Arial",
                                                                14));
                        }

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        item);

                        st.setToX(1);
                        st.setToY(1);

                        st.play();
                });

                return item;
        }

        // =============================================================
        // STAT CARD
        // =============================================================

        private VBox createStatCard(
                        String heading,
                        String value,
                        String percentage) {

                Text t = new Text(
                                heading);

                t.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

                t.setFill(
                                Color.web("#777777"));

                Text v = new Text(
                                value);

                v.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                v.setFill(
                                Color.web("#A83E00"));

                Text p = new Text(
                                percentage);

                p.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                p.setFill(
                                Color.web("#777777"));

                VBox card = new VBox(
                                7,
                                t,
                                v,
                                p);

                card.setPadding(
                                new Insets(12));

                card.setPrefWidth(185);
                card.setMinHeight(90);

                card.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#EEEEEE;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;");

                return card;
        }

}
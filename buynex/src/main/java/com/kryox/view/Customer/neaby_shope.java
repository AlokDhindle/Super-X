package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class neaby_shope {
        public String userId;

        public neaby_shope(String userId) {
                this.userId = userId;
        }

        // IMPORTANT: Replace this placeholder with your NEW restricted Google Maps API
        // key.
        // Do not use the key that was exposed in the screenshot.
        private static final String GOOGLE_MAPS_API_KEY = "AIzaSyD4JhVH741cVb2aSRmCrUyAMX_6biP1DLA";

        private Scene nearByscene;

        Scene getNearby_shopes(Runnable callbackDashbord) {

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
                                                "-fx-text-fill: #E87500;");

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
                                                "-fx-border-radius: 15;");
                premiumBox.setEffect(cardShadow);

                VBox textBox = new VBox(3);
                Label title = new Label("Premium Shopper");
                title.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #222222;");

                Label subtitle = new Label("● AI Assistant Active");
                subtitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #FF6900;");

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
                                                "-fx-cursor: hand;");
                lefButton1.setOnAction(event -> {
                        callbackDashbord.run();
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
                                                        "-fx-cursor: hand;");
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
                                                        "-fx-cursor: hand;");
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
                                                "-fx-cursor: hand;");

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
                                                        "-fx-cursor: hand;");
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
                                                        "-fx-cursor: hand;");
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
                                                "-fx-cursor: hand;");
                lefButton3.setOnAction(event -> {
                        DealsDB d = new DealsDB(userId);
                        Runnable rn = new Runnable() {
                                public void run() {
                                        backnerbyshoe();
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
                                                        "-fx-cursor: hand;");
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
                                                        "-fx-cursor: hand;");
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
                                                "-fx-cursor: hand;");
                lefButton4.setOnAction(event -> {
                        My_orderAllorder moa = new My_orderAllorder(userId);
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
                                                        "-fx-cursor: hand;");
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
                                                        "-fx-cursor: hand;");
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
                                                "-fx-cursor: hand;");
                lefButton5.setOnAction(event -> {
                        Analytics an = new Analytics(userId);
                        Runnable ra = new Runnable() {
                                public void run() {
                                        backnerbyshoe();
                                }
                        };
                        Homepage.HomepageStage.setScene(an.getAnalyticscene(ra));
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
                                                        "-fx-cursor: hand;");
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
                                                        "-fx-cursor: hand;");
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
                                new Stop(1, Color.web("#45474D")));

                upgradeCard.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                upgradeGradient,
                                                                new CornerRadii(17),
                                                                Insets.EMPTY)));

                Label upgradeTitle = new Label("Unlock Gold");
                upgradeTitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                Label upgradeText = new Label("Smarter deals & exclusive rewards");
                upgradeText.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: #BEBFC3;");

                Button upgradeGold = new Button("Upgrade to Gold");
                upgradeGold.setPrefWidth(175);
                upgradeGold.setPrefHeight(30);
                upgradeGold.setStyle(
                                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-cursor: hand;");

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
                                                "-fx-cursor: hand;");

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
                                                "-fx-cursor: hand;");
                lefButton6.setOnAction(event -> {
                        Seting se = new Seting(userId);
                        Runnable callbacktoDashborad = new Runnable() {
                                public void run() {

                                }
                        };
                        Homepage.HomepageStage.setScene(se.getSetingscene(callbacktoDashborad = null));
                });

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
                                hbInDashboard7);

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
                                                "-fx-cursor: hand;"));

                t1.setOnMouseExited(e -> t1.setStyle(topButtonStyle));

                t2.setOnMouseEntered(e -> t2.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6 8 6 8;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;"));

                t2.setOnMouseExited(e -> t2.setStyle(topButtonStyle));

                t3.setOnMouseEntered(e -> t3.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6 8 6 8;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;"));

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
                                                "-fx-prompt-text-fill: #999999;");

                // =====================================================
                // LOCATION
                // =====================================================

                Label locationIcon = new Label("📍");
                Label locationText = new Label("Downtown Manhattan⌄");
                locationText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #555555;");

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
                navBox.setPrefHeight(68);
                navBox.setPadding(new Insets(12, 24, 12, 24));
                navBox.setAlignment(Pos.CENTER_LEFT);
                navBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-color: #ebccb7;" +
                                                "-fx-border-width: 0 0 1 0;");
                navBox.setTranslateX(-28);

                // =====================================================
                // NEARBY SHOPS CONTENT + GOOGLE MAP
                // =====================================================

                // Shop list shown immediately beside the existing sidebar.
                VBox shopList = new VBox(12);
                shopList.setPrefWidth(335);
                shopList.setMinWidth(315);
                shopList.setPadding(new Insets(18, 12, 18, 12));
                shopList.setStyle("-fx-background-color: #F7F5F8;");

                Label nearbyTitle = new Label("Nearby Shops");
                nearbyTitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                Label foundLabel = new Label("24 found");
                foundLabel.setStyle(
                                "-fx-background-color: #EEEAF0;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-padding: 5 10 5 10;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #777777;");

                HBox titleRow = new HBox(8, nearbyTitle, new Region(), foundLabel);
                HBox.setHgrow(titleRow.getChildren().get(1), Priority.ALWAYS);
                titleRow.setAlignment(Pos.CENTER_LEFT);

                // Filter buttons
                Button openNowButton = new Button("Open Now");
                Button topRatedButton = new Button("Top Rated");
                Button nearestButton = new Button("Nearest");
                Button fastDeliveryButton = new Button("Fast Delivery");

                String filterStyle = "-fx-background-color: white;" +
                                "-fx-text-fill: #666666;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 15;" +
                                "-fx-border-color: #E5E0E8;" +
                                "-fx-border-radius: 15;" +
                                "-fx-padding: 7 10 7 10;" +
                                "-fx-cursor: hand;";

                String activeFilterStyle = "-fx-background-color: #FF6900;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 15;" +
                                "-fx-padding: 7 12 7 12;" +
                                "-fx-cursor: hand;";

                openNowButton.setStyle(activeFilterStyle);
                topRatedButton.setStyle(filterStyle);
                nearestButton.setStyle(filterStyle);
                fastDeliveryButton.setStyle(filterStyle);

                HBox filterRow = new HBox(
                                7,
                                openNowButton,
                                topRatedButton,
                                nearestButton,
                                fastDeliveryButton);
                filterRow.setAlignment(Pos.CENTER_LEFT);

                VBox cardsBox = new VBox(12);

                cardsBox.getChildren().addAll(
                                createShopCard(
                                                "Core2web",
                                                "Grocery",
                                                "0.8 miles away",
                                                "15 min",
                                                "$0 fee",
                                                "4.8",
                                                true,
                                                18.4577,
                                                73.8245),
                                createShopCard(
                                                "Tech Haven",
                                                "Electronics",
                                                "1.2 miles away",
                                                "30 min",
                                                "$2.99 fee",
                                                "4.9",
                                                true,
                                                40.7505,
                                                -73.9934),
                                createShopCard(
                                                "Nature's Pharmacy",
                                                "Health",
                                                "0.5 miles away",
                                                "20 min",
                                                "Opens 9 AM",
                                                "4.7",
                                                false,
                                                40.7306,
                                                -73.9866));

                ScrollPane shopScroll = new ScrollPane(cardsBox);
                shopScroll.setFitToWidth(true);
                shopScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                shopScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                shopScroll.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");
                VBox.setVgrow(shopScroll, Priority.ALWAYS);

                shopList.getChildren().addAll(titleRow, filterRow, shopScroll);

                // =====================================================
                // GOOGLE MAP - NO SEPARATE HTML FILE REQUIRED
                // =====================================================

                WebView mapWebView = new WebView();
                mapWebView.setContextMenuEnabled(false);
                mapWebView.setPrefSize(900, 700);
                mapWebView.setMinSize(500, 450);

                WebEngine mapEngine = mapWebView.getEngine();
                mapEngine.setJavaScriptEnabled(true);

                String mapHtml = createGoogleMapHtml();

                // Load the generated Google Maps HTML into the JavaFX WebView.
                mapEngine.loadContent(mapHtml);

                // Print WebView/JavaScript errors to the console.
                mapEngine.setOnError(event -> {
                        System.err.println("Google Maps WebEngine error: " + event.getMessage());
                });

                mapEngine.getLoadWorker().exceptionProperty().addListener((obs, oldEx, newEx) -> {
                        if (newEx != null) {
                                System.err.println("Google Maps load error: " + newEx.getMessage());
                        }
                });

                StackPane mapContainer = new StackPane(mapWebView);
                mapContainer.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: #E6E1E8;" +
                                                "-fx-border-radius: 16;");
                mapContainer.setEffect(cardShadow);
                HBox.setHgrow(mapContainer, Priority.ALWAYS);

                // List is on the right of the old sidebar; map is to the right of list.
                HBox nearbyContent = new HBox(0, shopList, mapContainer);
                nearbyContent.setAlignment(Pos.TOP_LEFT);
                VBox.setVgrow(nearbyContent, Priority.ALWAYS);

                // =====================================================
                // RIGHT CONTENT
                // =====================================================

                VBox Rightvbox = new VBox(12, navBox, nearbyContent);
                Rightvbox.setPadding(new Insets(0, 26, 20, 0));
                Rightvbox.setStyle("-fx-background-color: #eee5df");
                VBox.setVgrow(nearbyContent, Priority.ALWAYS);

                // =====================================================
                // SUBTLE ORANGE BACKGROUND GLOW
                // =====================================================

                RadialGradient orangeGlow1 = new RadialGradient(
                                0, 0, 0.84, 0.16, 0.42, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0.0, Color.web("#FF9148", 0.25)),
                                new Stop(0.40, Color.web("#FFD1B4", 0.12)),
                                new Stop(1.0, Color.TRANSPARENT));

                Rightvbox.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                orangeGlow1,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                // =====================================================
                // BORDER PANE
                // =====================================================

                BorderPane mainBox = new BorderPane();
                mainBox.setLeft(leftBox);
                mainBox.setCenter(Rightvbox);
                mainBox.setStyle("-fx-background-color: #eee5df");

                // =====================================================
                // SCENE
                // =====================================================

                Scene sc = new Scene(mainBox, 1530, 850);
                nearByscene = sc;

                return nearByscene;
        }

        // =====================================================
        // SHOP CARD
        // =====================================================

        private VBox createShopCard(
                        String shopName,
                        String category,
                        String distance,
                        String deliveryTime,
                        String fee,
                        String rating,
                        boolean open,
                        double latitude,
                        double longitude) {

                VBox card = new VBox(7);
                card.setPrefWidth(300);
                card.setMinWidth(285);
                card.setPadding(new Insets(12));
                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 14;" +
                                                "-fx-border-color: #E9E3EB;" +
                                                "-fx-border-radius: 14;");
                card.setEffect(new DropShadow(
                                10,
                                Color.rgb(0, 0, 0, 0.08)));

                HBox topRow = new HBox(10);
                topRow.setAlignment(Pos.CENTER_LEFT);

                StackPane imageBox = new StackPane();
                imageBox.setPrefSize(62, 58);
                imageBox.setMinSize(62, 58);
                imageBox.setMaxSize(62, 58);
                imageBox.setStyle(
                                "-fx-background-color: #F0ECE9;" +
                                                "-fx-background-radius: 9;");

                Label shopEmoji = new Label(
                                category.equalsIgnoreCase("Grocery") ? "🛒"
                                                : category.equalsIgnoreCase("Electronics") ? "💻" : "💊");
                shopEmoji.setStyle("-fx-font-size: 24px;");
                imageBox.getChildren().add(shopEmoji);

                VBox info = new VBox(3);
                HBox.setHgrow(info, Priority.ALWAYS);

                Label shopNameLabel = new Label(shopName);
                shopNameLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 700;" +
                                                "-fx-text-fill: #333333;");

                Label categoryLabel = new Label(
                                "▣ " + category + " • " + distance);
                categoryLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: #777777;");

                Label deliveryLabel = new Label(
                                "◷ " + deliveryTime + "    " + fee);
                deliveryLabel.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #888888;");

                info.getChildren().addAll(
                                shopNameLabel,
                                categoryLabel,
                                deliveryLabel);

                Label ratingLabel = new Label("★ " + rating);
                ratingLabel.setStyle(
                                "-fx-background-color: #F4F1F5;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-padding: 4 6 4 6;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #555555;");

                topRow.getChildren().addAll(imageBox, info, ratingLabel);

                Label statusLabel = new Label(open ? "OPEN NOW" : "CLOSED");
                statusLabel.setStyle(
                                "-fx-background-color: " +
                                                (open ? "#FFF0E7;" : "#EEEEF0;") +
                                                "-fx-background-radius: 6;" +
                                                "-fx-padding: 3 7 3 7;" +
                                                "-fx-font-size: 7px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " +
                                                (open ? "#FF6900;" : "#888888;"));

                Button viewShop = new Button("View Shop  →");
                viewShop.setPrefHeight(34);
                viewShop.setMaxWidth(Double.MAX_VALUE);
                viewShop.setStyle(
                                "-fx-background-color: " +
                                                (open ? "#FF6900;" : "#E8E8EA;") +
                                                "-fx-text-fill: " +
                                                (open ? "white;" : "#999999;") +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-cursor: hand;");

                Button directions = new Button("⌖ Directions");
                directions.setPrefHeight(34);
                directions.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: #CFC9D1;" +
                                                "-fx-border-radius: 9;" +
                                                "-fx-background-radius: 9;" +
                                                "-fx-cursor: hand;");

                HBox actionRow = new HBox(7, viewShop, directions);
                HBox.setHgrow(viewShop, Priority.ALWAYS);
                actionRow.setAlignment(Pos.CENTER_LEFT);

                card.getChildren().addAll(
                                topRow,
                                statusLabel,
                                actionRow);

                // Clicking Directions opens the location in Google Maps.
                directions.setOnAction(event -> {
                        String url = "https://www.google.com/maps/dir/?api=1&destination=" + latitude + "," + longitude;

                        getHostServicesSafely(url);
                });

                return card;
        }

        // =====================================================
        // GOOGLE MAP HTML CREATED INSIDE JAVA
        // =====================================================

        private String createGoogleMapHtml() {

                String safeKey = GOOGLE_MAPS_API_KEY
                                .replace("\\", "\\\\")
                                .replace("'", "\\'");

                return """
                                <!DOCTYPE html>
                                <html>
                                <head>
                                <meta charset="UTF-8">
                                <meta name="viewport"
                                      content="initial-scale=1.0, width=device-width">
                                <style>
                                    html, body {
                                        width: 100%%;
                                        height: 100%%;
                                        margin: 0;
                                        padding: 0;
                                        overflow: hidden;
                                    }

                                    #map {
                                        width: 100%%;
                                        height: 100%%;
                                    }
                                </style>
                                </head>

                                <body>
                                    <div id="map"></div>

                                    <script>
                                        let map;

                                        const shops = [

                                            {
                                                name: "Tech Haven",
                                                lat: 40.7505,
                                                lng: -73.9934
                                            },
                                            {
                                                name: "Nature's Pharmacy",
                                                lat: 40.7306,
                                                lng: -73.9866
                                            }
                                        ];

                                        function initMap() {

                                            const center = {
                                                lat: 18.4577,
                                                lng: 73.8245
                                            };

                                            map = new google.maps.Map(
                                                document.getElementById("map"),
                                                {
                                                    center: center,
                                                    zoom: 12,
                                                    mapTypeControl: false,
                                                    streetViewControl: false,
                                                    fullscreenControl: true,
                                                    zoomControl: true
                                                }
                                            );

                                            shops.forEach(function(shop) {

                                                const marker =
                                                    new google.maps.Marker({
                                                        position: {
                                                            lat: shop.lat,
                                                            lng: shop.lng
                                                        },
                                                        map: map,
                                                        title: shop.name
                                                    });

                                                const info =
                                                    new google.maps.InfoWindow({
                                                        content:
                                                            "<div style='font-family:Arial;padding:4px'>" +
                                                            "<b>" + shop.name + "</b>" +
                                                            "</div>"
                                                    });

                                                marker.addListener(
                                                    "click",
                                                    function() {
                                                        info.open({
                                                            anchor: marker,
                                                            map: map
                                                        });
                                                    }
                                                );
                                            });
                                        }
                                    </script>

                                    <script async defer
                                        src="https://maps.googleapis.com/maps/api/js?key=%s&callback=initMap">
                                    </script>
                                </body>
                                </html>
                                """
                                .formatted(safeKey);
        }

        private void getHostServicesSafely(String url) {

                try {
                        java.awt.Desktop.getDesktop().browse(
                                        java.net.URI.create(url));

                } catch (Exception ex) {
                        System.out.println(
                                        "Could not open Google Maps: " +
                                                        ex.getMessage());
                }
        }

        public void backnerbyshoe() {
                Homepage.HomepageStage.setScene(nearByscene);
        }

}
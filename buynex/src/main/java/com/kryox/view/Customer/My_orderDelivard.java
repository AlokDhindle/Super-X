package com.kryox.view.Customer;

import javafx.application.Application;
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
import javafx.stage.Stage;

public class My_orderDelivard {
        public String userId;

        public My_orderDelivard(String userId) {
                this.userId = userId;
        }

        private Scene Delivardscene;

        Scene getDelivaryScene(Runnable callbacktoShipped) {
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

                DropShadow smallShadow = new DropShadow();
                smallShadow.setRadius(8);
                smallShadow.setOffsetY(2);
                smallShadow.setSpread(0.02);
                smallShadow.setColor(Color.rgb(0, 0, 0, 0.08));

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
                        Homepage.HomepageStage.setScene(an.getAnalyticscene(null));
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
                // =====================================================

                HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
                navBox.setPrefHeight(68);
                navBox.setPadding(new Insets(12, 24, 12, 24));
                navBox.setAlignment(Pos.CENTER_LEFT);
                navBox.setStyle(
                                "-fx-background-color: #ebccb7;" +
                                                "-fx-border-width: 0 0 1 0;");
                navBox.setTranslateX(-28);

                // =====================================================
                // ORDERS MAIN CONTENT
                // =====================================================

                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(20, 25, 30, 25));
                mainContent.setStyle("-fx-background-color: #eee5df");

                // =====================================================
                // PAGE TITLE
                // =====================================================

                Label pageTitle = new Label("# My Orders");
                pageTitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                // =====================================================
                // ORDER TABS - All Orders | Ongoing | Shipped | Delivered | Cancelled
                // =====================================================

                HBox tabsBox = new HBox(15);
                tabsBox.setAlignment(Pos.CENTER_LEFT);
                tabsBox.setPadding(new Insets(10, 0, 15, 0));

                Button allOrdersButton = new Button("All Orders");
                allOrdersButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;");

                Button ongoingButton = new Button("Ongoing");
                ongoingButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;");

                Button shippedButton = new Button("Shipped");
                shippedButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;");
                shippedButton.setOnAction(event -> {
                        callbacktoShipped.run();
                });

                Button deliveredButton = new Button("Delivered");
                deliveredButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 700;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: #FF6900;" +
                                                "-fx-cursor: hand;");

                Button cancelledButton = new Button("Cancelled");
                cancelledButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-cursor: hand;");
                cancelledButton.setOnAction(event -> {
                        My_orderCancle Moc = new My_orderCancle(userId);
                        Runnable callback = new Runnable() {
                                public void run() {
                                        backtoDelivary();
                                }
                        };
                        Homepage.HomepageStage.setScene(Moc.getCanclScene(callback));
                });

                tabsBox.getChildren().addAll(
                                allOrdersButton,
                                ongoingButton,
                                shippedButton,
                                deliveredButton,
                                cancelledButton);

                // =====================================================
                // ORDERS LIST
                // =====================================================

                VBox ordersList = new VBox(15);
                ordersList.setPadding(new Insets(5, 0, 0, 0));

                // =====================================================
                // ORDER 1 - ELECTRONICS
                // =====================================================

                VBox order1 = createOrderCard(
                                "ELECTRONICS",
                                "BNX-99821",
                                "Sony WH-1000XM5 Wireless Headphones",
                                "Ordered on Oct 12, 2023 • Qty: 1",
                                "$348.00",
                                new String[] { "View Details", "Download Invoice", "Buy Again" },
                                "Delivered",
                                "🎧");
                ordersList.getChildren().add(order1);

                // =====================================================
                // ADD ALL TO MAIN CONTENT
                // =====================================================

                mainContent.getChildren().addAll(
                                pageTitle,
                                tabsBox,
                                ordersList);

                // =====================================================
                // SCROLL PANE
                // =====================================================

                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(mainContent);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                // =====================================================
                // RIGHT VBOX
                // =====================================================

                VBox Rightvbox = new VBox(0);
                Rightvbox.setFillWidth(true);
                Rightvbox.setPadding(new Insets(0, 0, 20, 0));
                Rightvbox.setStyle("-fx-background-color: #eee5df");
                Rightvbox.getChildren().addAll(navBox, scrollPane);
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

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
                Delivardscene = sc;
                return Delivardscene;
        }

        // =====================================================
        // HELPER METHOD: Create Order Card
        // =====================================================

        private VBox createOrderCard(String category, String orderId, String productName,
                        String orderDetails, String price, String[] actions, String status, String icon) {

                VBox card = new VBox(10);
                card.setPadding(new Insets(18, 20, 18, 20));
                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-border-color: #EAE6EC;" +
                                                "-fx-border-radius: 12;" +
                                                "-fx-border-width: 1;");

                DropShadow cardShadow = new DropShadow();
                cardShadow.setRadius(8);
                cardShadow.setOffsetY(2);
                cardShadow.setSpread(0.02);
                cardShadow.setColor(Color.rgb(0, 0, 0, 0.06));
                card.setEffect(cardShadow);

                // Top Row: Category + Order ID + Status
                HBox topRow = new HBox(10);
                topRow.setAlignment(Pos.CENTER_LEFT);

                Label iconLabel = new Label(icon);
                iconLabel.setStyle("-fx-font-size: 18px;");

                Label categoryLabel = new Label(category + " • ORDER ID: " + orderId);
                categoryLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-text-fill: #777777;");

                Region topSpacer = new Region();
                HBox.setHgrow(topSpacer, Priority.ALWAYS);

                Label statusLabel = new Label(status);
                String statusColor = "#FF6900";
                if (status.equals("Delivered")) {
                        statusColor = "#4CAF50";
                } else if (status.equals("Dispatched")) {
                        statusColor = "#2196F3";
                } else if (status.equals("Processing")) {
                        statusColor = "#FF9800";
                }
                statusLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: 700;" +
                                                "-fx-text-fill: " + statusColor + ";" +
                                                "-fx-background-color: " + statusColor + "20;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-padding: 4 12 4 12;");

                topRow.getChildren().addAll(iconLabel, categoryLabel, topSpacer, statusLabel);

                // Product Name
                Label nameLabel = new Label(productName);
                nameLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: 700;" +
                                                "-fx-text-fill: #222222;");

                // Order Details
                Label detailsLabel = new Label(orderDetails);
                detailsLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #777777;");

                // Bottom Row: Price + Actions
                HBox bottomRow = new HBox(15);
                bottomRow.setAlignment(Pos.CENTER_LEFT);

                Label priceLabel = new Label("Total Price");
                priceLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #777777;");

                Label priceValue = new Label(price);
                priceValue.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                VBox priceBox = new VBox(0, priceLabel, priceValue);
                priceBox.setAlignment(Pos.CENTER_LEFT);

                Region bottomSpacer = new Region();
                HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

                HBox actionBox = new HBox(10);
                actionBox.setAlignment(Pos.CENTER_RIGHT);

                for (String action : actions) {
                        Button actionBtn = new Button(action);
                        String btnStyle = "-fx-background-color: transparent;" +
                                        "-fx-text-fill: #FF6900;" +
                                        "-fx-font-size: 11px;" +
                                        "-fx-font-weight: 600;" +
                                        "-fx-border-color: #FF6900;" +
                                        "-fx-border-width: 1;" +
                                        "-fx-border-radius: 20;" +
                                        "-fx-padding: 6 16 6 16;" +
                                        "-fx-cursor: hand;";

                        if (action.equals("Cancel Order")) {
                                btnStyle = "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF4444;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-border-color: #FF4444;" +
                                                "-fx-border-width: 1;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-padding: 6 16 6 16;" +
                                                "-fx-cursor: hand;";
                        }

                        actionBtn.setStyle(btnStyle);

                        actionBtn.setOnMouseEntered(e -> {
                                if (action.equals("Cancel Order")) {
                                        actionBtn.setStyle(
                                                        "-fx-background-color: #FF4444;" +
                                                                        "-fx-text-fill: white;" +
                                                                        "-fx-font-size: 11px;" +
                                                                        "-fx-font-weight: 600;" +
                                                                        "-fx-border-color: #FF4444;" +
                                                                        "-fx-border-width: 1;" +
                                                                        "-fx-border-radius: 20;" +
                                                                        "-fx-padding: 6 16 6 16;" +
                                                                        "-fx-cursor: hand;");
                                } else {
                                        actionBtn.setStyle(
                                                        "-fx-background-color: #FF6900;" +
                                                                        "-fx-text-fill: white;" +
                                                                        "-fx-font-size: 11px;" +
                                                                        "-fx-font-weight: 600;" +
                                                                        "-fx-border-color: #FF6900;" +
                                                                        "-fx-border-width: 1;" +
                                                                        "-fx-border-radius: 20;" +
                                                                        "-fx-padding: 6 16 6 16;" +
                                                                        "-fx-cursor: hand;");
                                }
                        });

                        actionBtn.setOnMouseExited(e -> {
                                if (action.equals("Cancel Order")) {
                                        actionBtn.setStyle(
                                                        "-fx-background-color: transparent;" +
                                                                        "-fx-text-fill: #FF4444;" +
                                                                        "-fx-font-size: 11px;" +
                                                                        "-fx-font-weight: 600;" +
                                                                        "-fx-border-color: #FF4444;" +
                                                                        "-fx-border-width: 1;" +
                                                                        "-fx-border-radius: 20;" +
                                                                        "-fx-padding: 6 16 6 16;" +
                                                                        "-fx-cursor: hand;");
                                } else {
                                        actionBtn.setStyle(
                                                        "-fx-background-color: transparent;" +
                                                                        "-fx-text-fill: #FF6900;" +
                                                                        "-fx-font-size: 11px;" +
                                                                        "-fx-font-weight: 600;" +
                                                                        "-fx-border-color: #FF6900;" +
                                                                        "-fx-border-width: 1;" +
                                                                        "-fx-border-radius: 20;" +
                                                                        "-fx-padding: 6 16 6 16;" +
                                                                        "-fx-cursor: hand;");
                                }
                        });

                        actionBtn.setOnAction(e -> {
                                System.out.println(action + " clicked for order: " + orderId);
                        });

                        actionBox.getChildren().add(actionBtn);
                }

                bottomRow.getChildren().addAll(priceBox, bottomSpacer, actionBox);

                card.getChildren().addAll(topRow, nameLabel, detailsLabel, bottomRow);

                // Hover effect
                card.setOnMouseEntered(e -> {
                        card.setStyle(
                                        "-fx-background-color: #FFFFFF;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-border-color: #FFD8C4;" +
                                                        "-fx-border-radius: 12;" +
                                                        "-fx-border-width: 1.5;");
                        DropShadow hoverShadow = new DropShadow();
                        hoverShadow.setRadius(12);
                        hoverShadow.setOffsetY(4);
                        hoverShadow.setSpread(0.04);
                        hoverShadow.setColor(Color.rgb(0, 0, 0, 0.10));
                        card.setEffect(hoverShadow);
                });

                card.setOnMouseExited(e -> {
                        card.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-background-radius: 12;" +
                                                        "-fx-border-color: #EAE6EC;" +
                                                        "-fx-border-radius: 12;" +
                                                        "-fx-border-width: 1;");
                        card.setEffect(cardShadow);
                });

                return card;
        }

        public void backtoDelivary() {
                Homepage.HomepageStage.setScene(Delivardscene);
        }

}

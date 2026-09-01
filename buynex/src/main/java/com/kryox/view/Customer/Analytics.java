package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class Analytics {
        public String userId;

        public Analytics(String userId) {
                this.userId = userId;
        }

        private Scene Analyticscene;

        DropShadow cardShadow = new DropShadow();

        public VBox createSummaryCard(String label, String value, String icon) {
                VBox card = new VBox(4);
                card.setPadding(new Insets(18, 20, 18, 20));
                card.setAlignment(Pos.CENTER_LEFT);
                card.setStyle("-fx-background-color: white;-fx-background-radius: 14;-fx-border-color: #E9E2EA;-fx-border-radius: 14;-fx-border-width: 1;");
                card.setEffect(cardShadow);

                Label iconLabel = new Label(icon);
                iconLabel.setStyle("-fx-font-size: 18px;");

                Label valueLabel = new Label(value);
                valueLabel.setStyle(
                                "-fx-font-family: 'Montserrat';-fx-font-size: 20px;-fx-font-weight: bold;-fx-text-fill: #222222;");

                Label descLabel = new Label(label);
                descLabel.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 10px;-fx-text-fill: #888888;");

                card.getChildren().addAll(iconLabel, valueLabel, descLabel);
                return card;
        }

        public Scene getAnalyticscene(Runnable callbacktodashboard) {

                DropShadow shadow = new DropShadow();
                shadow.setRadius(18);
                shadow.setSpread(0.05);
                shadow.setOffsetX(5);
                shadow.setOffsetY(0);
                shadow.setColor(Color.rgb(0, 0, 0, 0.14));

                Label name = new Label("BuyNeX");
                name.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 28px;-fx-font-weight: 900;-fx-text-fill: #E87500;");

                HBox premiumBox = new HBox(9);
                premiumBox.setPrefSize(223, 66);
                premiumBox.setMinSize(223, 66);
                premiumBox.setMaxSize(223, 66);
                premiumBox.setAlignment(Pos.CENTER_LEFT);
                premiumBox.setPadding(new Insets(8, 13, 8, 13));
                premiumBox.setStyle(
                                "-fx-background-color: #FFFFFF;-fx-background-radius: 15;-fx-border-color: #E9E2EA;-fx-border-width: 1;-fx-border-radius: 15;");
                premiumBox.setEffect(cardShadow);

                VBox textBox = new VBox(3);
                Label title = new Label("Premium Shopper");
                title.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 12px;-fx-font-weight: bold;-fx-text-fill: #222222;");

                Label subtitle = new Label("● AI Assistant Active");
                subtitle.setStyle(
                                "-fx-font-family: 'Montserrat';-fx-font-size: 9px;-fx-font-weight: bold;-fx-text-fill: #FF6900;");

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
                                "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                lefButton1.setOnAction(event -> {
                        backtodashbord();

                });

                HBox hbInDashboard = new HBox(17, div, lefButton1);
                hbInDashboard.setPrefWidth(223);
                hbInDashboard.setMinWidth(223);
                hbInDashboard.setMaxWidth(223);
                hbInDashboard.setPrefHeight(42);
                hbInDashboard.setMinHeight(42);
                hbInDashboard.setMaxHeight(42);
                hbInDashboard.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");

                hbInDashboard.setOnMouseEntered(e -> {
                        hbInDashboard.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                        lefButton1.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                hbInDashboard.setOnMouseExited(e -> {
                        hbInDashboard.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");
                        lefButton1.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
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
                                "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                lefButton2.setOnAction(event -> {
                        neaby_shope ns = new neaby_shope(userId);
                        Runnable ra = new Runnable() {
                                public void run() {
                                        backtoAnalitics();
                                }
                        };
                        Homepage.HomepageStage.setScene(ns.getNearby_shopes(ra));
                });

                HBox hbInDashboard2 = new HBox(17, div2, lefButton2);
                hbInDashboard2.setPrefWidth(223);
                hbInDashboard2.setMinWidth(223);
                hbInDashboard2.setMaxWidth(223);
                hbInDashboard2.setPrefHeight(42);
                hbInDashboard2.setMinHeight(42);
                hbInDashboard2.setMaxHeight(42);
                hbInDashboard2.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard2.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard2.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");

                hbInDashboard2.setOnMouseEntered(e -> {
                        hbInDashboard2.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                        lefButton2.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                hbInDashboard2.setOnMouseExited(e -> {
                        hbInDashboard2.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");
                        lefButton2.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
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
                                "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");

                HBox hbInDashboard3 = new HBox(17, div3, lefButton3);
                hbInDashboard3.setPrefWidth(223);
                hbInDashboard3.setMinWidth(223);
                hbInDashboard3.setMaxWidth(223);
                hbInDashboard3.setPrefHeight(42);
                hbInDashboard3.setMinHeight(42);
                hbInDashboard3.setMaxHeight(42);
                hbInDashboard3.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard3.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard3.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");

                hbInDashboard3.setOnMouseEntered(e -> {
                        hbInDashboard3.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                        lefButton3.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                hbInDashboard3.setOnMouseExited(e -> {
                        hbInDashboard3.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");
                        lefButton3.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
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
                                "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                lefButton4.setOnAction(event -> {
                        My_orderAllorder moa = new My_orderAllorder(userId);
                        Homepage.HomepageStage.setScene(moa.getAllorderScene());
                });

                HBox hbInDashboard4 = new HBox(17, div4, lefButton4);
                hbInDashboard4.setPrefWidth(223);
                hbInDashboard4.setMinWidth(223);
                hbInDashboard4.setMaxWidth(223);
                hbInDashboard4.setPrefHeight(42);
                hbInDashboard4.setMinHeight(42);
                hbInDashboard4.setMaxHeight(42);
                hbInDashboard4.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard4.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard4.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");

                hbInDashboard4.setOnMouseEntered(e -> {
                        hbInDashboard4.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                        lefButton4.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                hbInDashboard4.setOnMouseExited(e -> {
                        hbInDashboard4.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");
                        lefButton4.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
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
                                "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");

                HBox hbInDashboard5 = new HBox(17, div5, lefButton5);
                hbInDashboard5.setPrefWidth(223);
                hbInDashboard5.setMinWidth(223);
                hbInDashboard5.setMaxWidth(223);
                hbInDashboard5.setPrefHeight(42);
                hbInDashboard5.setMinHeight(42);
                hbInDashboard5.setMaxHeight(42);
                hbInDashboard5.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard5.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard5.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                lefButton5.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");

                hbInDashboard5.setOnMouseEntered(e -> {
                        hbInDashboard5.setStyle("-fx-background-color: #FF6900;-fx-background-radius: 12;");
                        lefButton5.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: white;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: bold;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                hbInDashboard5.setOnMouseExited(e -> {
                        hbInDashboard5.setStyle("-fx-background-color: transparent;-fx-background-radius: 12;");
                        lefButton5.setStyle(
                                        "-fx-background-color: transparent;-fx-text-fill: #333333;-fx-font-size: 12px;-fx-font-family: 'Montserrat';-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                });

                VBox upgradeCard = new VBox(7);
                upgradeCard.setPrefWidth(223);
                upgradeCard.setMinWidth(223);
                upgradeCard.setMaxWidth(223);
                upgradeCard.setPrefHeight(123);
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
                upgradeTitle.setStyle("-fx-font-size: 12px;-fx-font-weight: bold;-fx-text-fill: white;");

                Label upgradeText = new Label("Smarter deals & exclusive rewards");
                upgradeText.setStyle("-fx-font-size: 8px;-fx-text-fill: #BEBFC3;");

                Button upgradeGold = new Button("Upgrade to Gold");
                upgradeGold.setPrefWidth(193);
                upgradeGold.setPrefHeight(30);
                upgradeGold.setStyle(
                                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-cursor: hand;");

                upgradeCard.getChildren().addAll(upgradeTitle, upgradeText, upgradeGold);

                Image di6 = new Image("/assets/images/Dashbord/category.png");
                ImageView div6 = new ImageView(di6);
                div6.setFitHeight(19);
                div6.setFitWidth(19);
                div6.setPreserveRatio(true);

                Button lefButton6 = new Button("Settings");
                lefButton6.setPrefWidth(135);
                lefButton6.setPrefHeight(34);
                lefButton6.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #666666;-fx-font-size: 11px;-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");
                lefButton6.setOnAction(event -> {
                        Seting seting = new Seting(userId);
                        Runnable rn = new Runnable() {
                                public void run() {
                                        backtodashbord();
                                }
                        };

                        Homepage.HomepageStage.setScene(seting.getSetingscene(rn));
                });

                HBox hbInDashboard6 = new HBox(10, div6, lefButton6);
                hbInDashboard6.setPrefWidth(223);
                hbInDashboard6.setMinWidth(223);
                hbInDashboard6.setMaxWidth(223);
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
                                "-fx-background-color: transparent;-fx-text-fill: #666666;-fx-font-size: 11px;-fx-font-weight: 500;-fx-border-width: 0;-fx-padding: 0;-fx-alignment: CENTER_LEFT;-fx-cursor: hand;");

                HBox hbInDashboard7 = new HBox(10, div7, lefButton7);
                hbInDashboard7.setPrefWidth(223);
                hbInDashboard7.setMinWidth(223);
                hbInDashboard7.setMaxWidth(223);
                hbInDashboard7.setPrefHeight(34);
                hbInDashboard7.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard7.setPadding(new Insets(0, 8, 0, 18));

                VBox leftBox = new VBox(14);
                leftBox.setPrefWidth(267);
                leftBox.setMinWidth(267);
                leftBox.setMaxWidth(267);
                leftBox.setPrefHeight(800);
                leftBox.setAlignment(Pos.TOP_CENTER);
                leftBox.setPadding(new Insets(27, 22, 20, 22));
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
                                "-fx-background-color: transparent;-fx-text-fill: #FF6900;-fx-font-size: 10px;-fx-font-weight: bold;-fx-padding: 6 8 6 8;-fx-border-width: 0;-fx-cursor: hand;"));

                t1.setOnMouseExited(e -> t1.setStyle(topButtonStyle));

                t2.setOnMouseEntered(e -> t2.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #FF6900;-fx-font-size: 10px;-fx-font-weight: bold;-fx-padding: 6 8 6 8;-fx-border-width: 0;-fx-cursor: hand;"));

                t2.setOnMouseExited(e -> t2.setStyle(topButtonStyle));

                t3.setOnMouseEntered(e -> t3.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #FF6900;-fx-font-size: 10px;-fx-font-weight: bold;-fx-padding: 6 8 6 8;-fx-border-width: 0;-fx-cursor: hand;"));

                t3.setOnMouseExited(e -> t3.setStyle(topButtonStyle));
                HBox topLinks = new HBox(6, t1, t2, t3);
                topLinks.setAlignment(Pos.CENTER_LEFT);

                TextField searchBox = new TextField();
                searchBox.setPromptText("Search products, shops or deals with AI...");
                searchBox.setPrefWidth(340);
                searchBox.setPrefHeight(40);
                searchBox.setStyle(
                                "-fx-background-color: #F8F7FA;-fx-background-radius: 20;-fx-border-color: #E5E1E8;-fx-border-radius: 20;-fx-border-width: 1;-fx-padding: 0 16 0 16;-fx-font-size: 10px;-fx-text-fill: #444444;-fx-prompt-text-fill: #999999;");

                Label locationIcon = new Label("📍");
                Label locationText = new Label("Downtown Manhattan⌄");
                locationText.setStyle("-fx-font-size: 10px;-fx-font-weight: bold;-fx-text-fill: #555555;");

                HBox locationBox = new HBox(4, locationIcon, locationText);
                locationBox.setAlignment(Pos.CENTER_LEFT);

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
                actionBox.setTranslateX(0);
                actionBox.setAlignment(Pos.CENTER_RIGHT);

                Region navSpacer1 = new Region();
                HBox.setHgrow(navSpacer1, Priority.ALWAYS);
                Region navSpacer2 = new Region();
                HBox.setHgrow(navSpacer2, Priority.ALWAYS);

                HBox navBox = new HBox(
                                12,
                                topLinks,
                                navSpacer1,
                                searchBox,
                                navSpacer2,
                                locationBox,
                                actionBox);

                navBox.setPrefHeight(72);
                navBox.setMinHeight(72);
                navBox.setMaxHeight(72);

                navBox.setMaxWidth(Double.MAX_VALUE);
                HBox.setHgrow(navBox, Priority.ALWAYS);

                navBox.setPadding(new Insets(14, 25, 14, 25));
                navBox.setAlignment(Pos.CENTER_LEFT);
                navBox.setFillHeight(true);

                navBox.setStyle("-fx-background-color: #ebccb7;-fx-border-width: 0 0 1 0;");

                Label analyticsTitle = new Label("Analytics");
                analyticsTitle.setStyle(
                                "-fx-font-family: 'Montserrat';-fx-font-size: 24px;-fx-font-weight: 700;-fx-text-fill: #222222;");

                Label analyticsSubtitle = new Label("Track your spending, savings, and shopping habits.");
                analyticsSubtitle.setStyle("-fx-font-family: 'Montserrat';-fx-font-size: 12px;-fx-text-fill: #666666;");

                VBox headerBox = new VBox(4, analyticsTitle, analyticsSubtitle);
                headerBox.setPadding(new Insets(0, 0, 4, 0));

                HBox summaryCards = new HBox(18);
                summaryCards.setAlignment(Pos.CENTER_LEFT);
                summaryCards.setFillHeight(true);
                summaryCards.getChildren().addAll(
                                createSummaryCard("TOTAL SPENDING", "\u20B92,450.80", "💰"),
                                createSummaryCard("ORDERS", "18", "📦"),
                                createSummaryCard("AVG. ORDER VALUE", "\u20B9136.15", "📊"),
                                createSummaryCard("REFUNDS", "\u20B945.00", "↩️"));

                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setLabel("Week");
                xAxis.getCategories().addAll("Week 1", "Week 2", "Week 3", "Week 4");

                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Spending (\u20B9)");
                yAxis.setAutoRanging(false);
                yAxis.setLowerBound(0);
                yAxis.setUpperBound(2500);
                yAxis.setTickUnit(500);

                LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
                lineChart.setTitle("Spending Trends");
                lineChart.setPrefHeight(380);
                lineChart.setPrefWidth(1000);
                lineChart.setAnimated(false);

                lineChart.setStyle("-fx-background-color: transparent;");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Weekly Spending");
                series.getData().add(new XYChart.Data<>("Week 1", 1200));
                series.getData().add(new XYChart.Data<>("Week 2", 1500));
                series.getData().add(new XYChart.Data<>("Week 3", 2000));
                series.getData().add(new XYChart.Data<>("Week 4", 1800));

                lineChart.getData().add(series);

                lineChart.setStyle("-fx-background-color: transparent;-fx-legend-visible: true;");

                StackPane chartContainer = new StackPane(lineChart);
                chartContainer.setStyle(
                                "-fx-background-color: white;-fx-background-radius: 14;-fx-border-color: #E9E2EA;-fx-border-radius: 14;-fx-border-width: 1;");
                chartContainer.setPadding(new Insets(22));
                chartContainer.setEffect(cardShadow);

                VBox chartBox = new VBox(chartContainer);
                chartBox.setPadding(new Insets(20, 0, 0, 0));

                VBox analyticsContent = new VBox(18, headerBox, summaryCards, chartBox);
                analyticsContent.setPadding(new Insets(30, 25, 30, 25));

                VBox Rightvbox = new VBox(22, navBox, analyticsContent);

                Rightvbox.setFillWidth(true);
                Rightvbox.setPadding(new Insets(0, 0, 20, 0));
                Rightvbox.setStyle("-fx-background-color: #eee5df");

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

                BorderPane mainBox = new BorderPane();
                mainBox.setLeft(leftBox);
                mainBox.setCenter(Rightvbox);
                mainBox.setStyle("-fx-background-color: #eee5df");

                Scene sc = new Scene(mainBox, 1550, 850);
                Analyticscene = sc;

                return Analyticscene;
        }

        public void backtoAnalitics() {
                Homepage.HomepageStage.setScene(Analyticscene);
        }

        public void backtodashbord() {
                Dashbord dashbord = new Dashbord(userId);
                Homepage.HomepageStage.setScene(dashbord.getDashbordScene());
        }

}
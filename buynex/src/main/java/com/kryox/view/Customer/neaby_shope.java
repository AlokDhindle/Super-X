
package com.kryox.view.Customer;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javafx.application.Platform;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class neaby_shope {
        public String userId;

        public neaby_shope(String userId) {
                this.userId = userId;
        }

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
                shopList.setMinWidth(335);
                shopList.setMaxWidth(335);
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

                // =====================================================
                // OPENSTREETMAP MAP - NATIVE JAVAFX
                //
                // Created here (before the shop cards) so the cards'
                // "View Shop" buttons can be wired directly to this
                // same map instance and draw routes on it.
                // =====================================================

                // =====================================================
                // FIXED MAP SIZE
                // =====================================================

                final double MAP_WIDTH = 900;
                final double MAP_HEIGHT = 700;

                OsmMapView mapView = new OsmMapView();

                mapView.setPrefWidth(MAP_WIDTH);
                mapView.setMinWidth(MAP_WIDTH);
                mapView.setMaxWidth(MAP_WIDTH);

                mapView.setPrefHeight(MAP_HEIGHT);
                mapView.setMinHeight(MAP_HEIGHT);
                mapView.setMaxHeight(MAP_HEIGHT);

                StackPane mapContainer = new StackPane();
                mapContainer.getChildren().add(mapView);

                mapContainer.setPrefWidth(MAP_WIDTH);
                mapContainer.setMinWidth(MAP_WIDTH);
                mapContainer.setMaxWidth(MAP_WIDTH);

                mapContainer.setPrefHeight(MAP_HEIGHT);
                mapContainer.setMinHeight(MAP_HEIGHT);
                mapContainer.setMaxHeight(MAP_HEIGHT);

                mapContainer.setAlignment(Pos.CENTER);

                mapContainer.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 16;" +
                                                "-fx-border-color: #E6E1E8;" +
                                                "-fx-border-radius: 16;");

                mapContainer.setEffect(cardShadow);

                // -----------------------------------------------------
                // FIX: clip mapContainer to its own bounds.
                //
                // Region/StackPane/Pane do NOT clip children to their
                // layout bounds by default. OsmMapView draws OSM tiles
                // at raw pixel coordinates (tilePane.setLayoutX/Y) which
                // routinely fall outside the visible 900x700 box while
                // panning/zooming. Without a clip, those tiles paint
                // straight through and cover whatever is drawn after
                // this node in the scene graph (here: the sidebar),
                // which is exactly the "map takes over the whole
                // window" bug seen in the screenshot.
                //
                // Binding a Rectangle clip to mapContainer's own
                // width/height guarantees nothing the map draws can
                // ever escape its container, regardless of window size.
                // -----------------------------------------------------
                Rectangle mapContainerClip = new Rectangle();
                mapContainerClip.widthProperty().bind(mapContainer.widthProperty());
                mapContainerClip.heightProperty().bind(mapContainer.heightProperty());
                mapContainerClip.setArcWidth(32);
                mapContainerClip.setArcHeight(32);
                mapContainer.setClip(mapContainerClip);

                // IMPORTANT:
                // HBox.setHgrow(mapContainer, Priority.ALWAYS)
                // is intentionally NOT used because the map must
                // remain inside the fixed 900 x 700 frame.

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
                                                40.7505,
                                                73.8245,
                                                mapView),
                                createShopCard(
                                                "Tech Haven",
                                                "Electronics",
                                                "1.2 miles away",
                                                "30 min",
                                                "$2.99 fee",
                                                "4.9",
                                                true,
                                                40.7505,
                                                73.8245,
                                                mapView),
                                createShopCard(
                                                "Nature's Pharmacy",
                                                "Health",
                                                "0.5 miles away",
                                                "20 min",
                                                "Opens 9 AM",
                                                "4.7",
                                                false,
                                                40.7306,
                                                73.8245,
                                                mapView));

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

                HBox nearbyContent = new HBox(
                                20,
                                shopList,
                                mapContainer);

                nearbyContent.setAlignment(Pos.TOP_LEFT);
                nearbyContent.setFillHeight(false);

                // =====================================================
                // RIGHT CONTENT
                // =====================================================

                VBox Rightvbox = new VBox(12, navBox, nearbyContent);
                Rightvbox.setPadding(new Insets(0, 26, 20, 0));
                Rightvbox.setStyle("-fx-background-color: #eee5df");

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
                        double longitude,
                        OsmMapView mapView) {

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

                // Clicking Directions still opens the route in the browser
                // via Google Maps -> unchanged from before.
                directions.setOnAction(event -> {
                        String url = "https://www.google.com/maps/dir/?api=1&destination=" + latitude + "," + longitude;

                        getHostServicesSafely(url);
                });

                // -----------------------------------------------------
                // Clicking "View Shop" draws the exact start -> destination
                // route directly on our own embedded OsmMapView (no browser,
                // no Google Maps redirect). The map fetches a real road
                // route from OSRM and falls back to a straight line if
                // the routing request fails for any reason.
                // -----------------------------------------------------
                viewShop.setOnAction(event -> {
                        mapView.showRouteFromCurrentLocation(latitude, longitude);
                });

                return card;
        }

        // =====================================================
        // OPENSTREETMAP NATIVE JAVAFX MAP
        //
        // This does NOT use WebView, Google Maps JavaScript,
        // Leaflet or any external JavaScript library.
        //
        // OSM tiles are downloaded directly and displayed in
        // JavaFX ImageViews. This avoids the JavaFX WebView
        // rendering problems with modern Leaflet maps.
        // =====================================================

        private static class OsmMapView extends StackPane {

                private static final int TILE_SIZE = 256;
                private static final int MAX_ZOOM = 19;
                private static final int MIN_ZOOM = 2;

                private static final double INITIAL_LATITUDE = 18.4577;
                private static final double INITIAL_LONGITUDE = 73.8245;
                private static final int INITIAL_ZOOM = 12;

                private final Pane tilePane = new Pane();
                private final Pane routePane = new Pane();
                private final Pane markerPane = new Pane();

                private final HttpClient httpClient =
                                HttpClient.newBuilder()
                                                .followRedirects(
                                                                HttpClient.Redirect.NORMAL)
                                                .build();

                private final Map<String, Image> imageCache =
                                new ConcurrentHashMap<>();

                private double centerLatitude = INITIAL_LATITUDE;
                private double centerLongitude = INITIAL_LONGITUDE;
                private int zoom = INITIAL_ZOOM;

                // Active turn-by-turn route (drawn on routePane), if any.
                private List<double[]> routePoints = null;
                private double routeStartLat;
                private double routeStartLon;
                private double routeEndLat;
                private double routeEndLon;

                private double mousePressedX;
                private double mousePressedY;
                private double pressedCenterX;
                private double pressedCenterY;

                private long renderNumber = 0;

                OsmMapView() {

                        setStyle("-fx-background-color: #E9E7E3;");

                        tilePane.setMouseTransparent(true);
                        routePane.setMouseTransparent(true);
                        markerPane.setMouseTransparent(false);

                        getChildren().addAll(
                                        tilePane,
                                        routePane,
                                        markerPane);

                        // -----------------------------------------------------
                        // FIX: clip this StackPane (and therefore tilePane /
                        // markerPane) to its own real layout bounds so tiles
                        // and markers can never be painted outside the map
                        // box, no matter what world-pixel coordinates the
                        // tile-loading math produces during pan/zoom.
                        // -----------------------------------------------------
                        Rectangle selfClip = new Rectangle();
                        selfClip.widthProperty().bind(widthProperty());
                        selfClip.heightProperty().bind(heightProperty());
                        setClip(selfClip);

                        createZoomControls();
                        createAttribution();

                        widthProperty().addListener(
                                        (obs, oldValue, newValue) ->
                                                        scheduleRender());

                        heightProperty().addListener(
                                        (obs, oldValue, newValue) ->
                                                        scheduleRender());

                        setOnMousePressed(this::handleMousePressed);
                        setOnMouseDragged(this::handleMouseDragged);
                        setOnMouseReleased(event -> {
                                scheduleRender();
                        });

                        Platform.runLater(this::renderMap);
                }

                private void createZoomControls() {

                        VBox zoomBox = new VBox(2);
                        zoomBox.setAlignment(Pos.CENTER);
                        zoomBox.setPadding(new Insets(5));

                        Button plus = new Button("+");
                        Button minus = new Button("−");

                        plus.setPrefSize(38, 38);
                        minus.setPrefSize(38, 38);

                        String zoomButtonStyle =
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 20px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 7;" +
                                                        "-fx-border-color: #DDDDDD;" +
                                                        "-fx-border-radius: 7;" +
                                                        "-fx-cursor: hand;";

                        plus.setStyle(zoomButtonStyle);
                        minus.setStyle(zoomButtonStyle);

                        plus.setOnAction(event -> zoomIn());
                        minus.setOnAction(event -> zoomOut());

                        zoomBox.getChildren().addAll(
                                        plus,
                                        minus);

                        StackPane.setAlignment(
                                        zoomBox,
                                        Pos.TOP_RIGHT);

                        getChildren().add(zoomBox);
                }

                private void createAttribution() {

                        Label attribution =
                                        new Label(
                                                        "© OpenStreetMap contributors");

                        attribution.setStyle(
                                        "-fx-background-color: rgba(255,255,255,0.90);" +
                                                        "-fx-text-fill: #555555;" +
                                                        "-fx-font-size: 9px;" +
                                                        "-fx-padding: 3 6 3 6;" +
                                                        "-fx-background-radius: 4;");

                        StackPane.setAlignment(
                                        attribution,
                                        Pos.BOTTOM_RIGHT);

                        StackPane.setMargin(
                                        attribution,
                                        new Insets(0, 8, 8, 0));

                        getChildren().add(attribution);
                }

                private void handleMousePressed(MouseEvent event) {

                        mousePressedX = event.getX();
                        mousePressedY = event.getY();

                        pressedCenterX =
                                        longitudeToWorldX(
                                                        centerLongitude,
                                                        zoom);

                        pressedCenterY =
                                        latitudeToWorldY(
                                                        centerLatitude,
                                                        zoom);
                }

                private void handleMouseDragged(MouseEvent event) {

                        double deltaX =
                                        mousePressedX - event.getX();

                        double deltaY =
                                        mousePressedY - event.getY();

                        double worldX =
                                        pressedCenterX + deltaX;

                        double worldY =
                                        pressedCenterY + deltaY;

                        centerLongitude =
                                        worldXToLongitude(
                                                        worldX,
                                                        zoom);

                        centerLatitude =
                                        worldYToLatitude(
                                                        worldY,
                                                        zoom);

                        centerLatitude =
                                        Math.max(
                                                        -85.05112878,
                                                        Math.min(
                                                                        85.05112878,
                                                                        centerLatitude));

                        renderMap();
                }

                private void zoomIn() {

                        if (zoom >= MAX_ZOOM) {
                                return;
                        }

                        zoom++;
                        renderMap();
                }

                private void zoomOut() {

                        if (zoom <= MIN_ZOOM) {
                                return;
                        }

                        zoom--;
                        renderMap();
                }

                private void scheduleRender() {

                        Platform.runLater(this::renderMap);
                }

                private void renderMap() {

                        if (getWidth() < 10 ||
                                        getHeight() < 10) {
                                return;
                        }

                        final long currentRender =
                                        ++renderNumber;

                        double centerWorldX =
                                        longitudeToWorldX(
                                                        centerLongitude,
                                                        zoom);

                        double centerWorldY =
                                        latitudeToWorldY(
                                                        centerLatitude,
                                                        zoom);

                        double leftWorld =
                                        centerWorldX - getWidth() / 2.0;

                        double topWorld =
                                        centerWorldY - getHeight() / 2.0;

                        tilePane.getChildren().clear();
                        routePane.getChildren().clear();
                        markerPane.getChildren().clear();

                        int firstTileX =
                                        (int) Math.floor(
                                                        leftWorld / TILE_SIZE) - 1;

                        int lastTileX =
                                        (int) Math.floor(
                                                        (leftWorld + getWidth()) /
                                                                        TILE_SIZE) + 1;

                        int firstTileY =
                                        (int) Math.floor(
                                                        topWorld / TILE_SIZE) - 1;

                        int lastTileY =
                                        (int) Math.floor(
                                                        (topWorld + getHeight()) /
                                                                        TILE_SIZE) + 1;

                        int maxTile =
                                        (1 << zoom) - 1;

                        for (int tileX = firstTileX;
                                        tileX <= lastTileX;
                                        tileX++) {

                                int wrappedX =
                                                ((tileX % (maxTile + 1))
                                                                + (maxTile + 1))
                                                                % (maxTile + 1);

                                for (int tileY = firstTileY;
                                                tileY <= lastTileY;
                                                tileY++) {

                                        if (tileY < 0 ||
                                                        tileY > maxTile) {
                                                continue;
                                        }

                                        double imageX =
                                                        tileX * TILE_SIZE
                                                                        - leftWorld;

                                        double imageY =
                                                        tileY * TILE_SIZE
                                                                        - topWorld;

                                        loadTile(
                                                        zoom,
                                                        wrappedX,
                                                        tileY,
                                                        imageX,
                                                        imageY,
                                                        currentRender);
                                }
                        }

                        addShopMarkers(
                                        leftWorld,
                                        topWorld);

                        drawRoute(
                                        leftWorld,
                                        topWorld);
                }

                private void loadTile(
                                int tileZoom,
                                int tileX,
                                int tileY,
                                double imageX,
                                double imageY,
                                long currentRender) {

                        String key =
                                        tileZoom + "/" +
                                                        tileX + "/" +
                                                        tileY;

                        Image cached =
                                        imageCache.get(key);

                        if (cached != null &&
                                        !cached.isError()) {

                                addTileImage(
                                                cached,
                                                imageX,
                                                imageY);

                                return;
                        }

                        String tileUrl =
                                        "https://tile.openstreetmap.org/" +
                                                        tileZoom + "/" +
                                                        tileX + "/" +
                                                        tileY + ".png";

                        HttpRequest request =
                                        HttpRequest.newBuilder()
                                                        .uri(URI.create(tileUrl))
                                                        .header(
                                                                        "User-Agent",
                                                                        "BuyNeX-JavaFX/1.0")
                                                        .GET()
                                                        .build();

                        httpClient.sendAsync(
                                        request,
                                        HttpResponse.BodyHandlers.ofByteArray()
                        ).thenApply(response -> {

                                if (response.statusCode() != 200) {
                                        throw new RuntimeException(
                                                        "OSM tile HTTP " +
                                                                        response.statusCode());
                                }

                                return new Image(
                                                new ByteArrayInputStream(
                                                                response.body()));
                        }).thenAccept(image -> {

                                imageCache.put(key, image);

                                Platform.runLater(() -> {

                                        if (currentRender != renderNumber) {
                                                return;
                                        }

                                        addTileImage(
                                                        image,
                                                        imageX,
                                                        imageY);
                                });

                        }).exceptionally(error -> {

                                System.err.println(
                                                "Could not load OSM tile " +
                                                                key + ": " +
                                                                error.getMessage());

                                return null;
                        });
                }

                private void addTileImage(
                                Image image,
                                double x,
                                double y) {

                        ImageView imageView =
                                        new ImageView(image);

                        imageView.setFitWidth(TILE_SIZE);
                        imageView.setFitHeight(TILE_SIZE);
                        imageView.setPreserveRatio(false);
                        imageView.setSmooth(true);

                        imageView.setLayoutX(x);
                        imageView.setLayoutY(y);

                        tilePane.getChildren().add(
                                        imageView);
                }

                private void addShopMarkers(
                                double leftWorld,
                                double topWorld) {

                        addMarker(
                                        "Core2web",
                                        "Grocery",
                                        18.4577,
                                        73.8245,
                                        leftWorld,
                                        topWorld);

                        addMarker(
                                        "Tech Haven",
                                        "Electronics",
                                        18.5204,
                                        73.8567,
                                        leftWorld,
                                        topWorld);

                        addMarker(
                                        "Nature's Pharmacy",
                                        "Health",
                                        18.5074,
                                        73.8077,
                                        leftWorld,
                                        topWorld);
                }

                private void addMarker(
                                String name,
                                String category,
                                double latitude,
                                double longitude,
                                double leftWorld,
                                double topWorld) {

                        double worldX =
                                        longitudeToWorldX(
                                                        longitude,
                                                        zoom);

                        double worldY =
                                        latitudeToWorldY(
                                                        latitude,
                                                        zoom);

                        double x =
                                        worldX - leftWorld;

                        double y =
                                        worldY - topWorld;

                        Circle marker =
                                        new Circle(
                                                        8,
                                                        Color.web("#FF6900"));

                        marker.setStroke(Color.WHITE);
                        marker.setStrokeWidth(3);

                        Tooltip tooltip =
                                        new Tooltip(
                                                        name +
                                                                        "\n" +
                                                                        category);

                        Tooltip.install(
                                        marker,
                                        tooltip);

                        marker.setOnMouseClicked(event -> {

                                marker.setFill(
                                                Color.web("#E87500"));

                                event.consume();
                        });

                        marker.setLayoutX(x);
                        marker.setLayoutY(y);

                        markerPane.getChildren().add(
                                        marker);
                }

                // =====================================================
                // ROUTING (drawn directly on this embedded map, no
                // browser / Google Maps redirect involved)
                // =====================================================

                /**
                 * Convenience entry point used by the "View Shop" button:
                 * routes from this map's initial/current-location point
                 * to the given shop destination.
                 */
                void showRouteFromCurrentLocation(
                                double destinationLatitude,
                                double destinationLongitude) {

                        showRoute(
                                        INITIAL_LATITUDE,
                                        INITIAL_LONGITUDE,
                                        destinationLatitude,
                                        destinationLongitude);
                }

                /**
                 * Fetches a real road route between the two points from
                 * the public OSRM routing service and draws it on this
                 * map. If the request fails for any reason (no internet,
                 * service unavailable, etc.) it falls back to a straight
                 * line between the two points so the feature still works.
                 */
                void showRoute(
                                double startLatitude,
                                double startLongitude,
                                double endLatitude,
                                double endLongitude) {

                        String osrmUrl =
                                        "https://router.project-osrm.org/route/v1/driving/" +
                                                        startLongitude + "," + startLatitude + ";" +
                                                        endLongitude + "," + endLatitude +
                                                        "?overview=full&geometries=geojson";

                        HttpRequest request =
                                        HttpRequest.newBuilder()
                                                        .uri(URI.create(osrmUrl))
                                                        .header(
                                                                        "User-Agent",
                                                                        "BuyNeX-JavaFX/1.0")
                                                        .GET()
                                                        .build();

                        httpClient.sendAsync(
                                        request,
                                        HttpResponse.BodyHandlers.ofString()
                        ).thenApply(response -> {

                                if (response.statusCode() != 200) {
                                        throw new RuntimeException(
                                                        "OSRM routing HTTP " +
                                                                        response.statusCode());
                                }

                                return parseRouteCoordinates(
                                                response.body());

                        }).thenAccept(points -> {

                                Platform.runLater(() -> {

                                        applyRoute(
                                                        points.size() >= 2
                                                                        ? points
                                                                        : straightLine(
                                                                                        startLatitude,
                                                                                        startLongitude,
                                                                                        endLatitude,
                                                                                        endLongitude),
                                                        startLatitude,
                                                        startLongitude,
                                                        endLatitude,
                                                        endLongitude);
                                });

                        }).exceptionally(error -> {

                                System.err.println(
                                                "Could not fetch route, falling back to a straight line: " +
                                                                error.getMessage());

                                Platform.runLater(() -> {

                                        applyRoute(
                                                        straightLine(
                                                                        startLatitude,
                                                                        startLongitude,
                                                                        endLatitude,
                                                                        endLongitude),
                                                        startLatitude,
                                                        startLongitude,
                                                        endLatitude,
                                                        endLongitude);
                                });

                                return null;
                        });
                }

                private static List<double[]> straightLine(
                                double startLatitude,
                                double startLongitude,
                                double endLatitude,
                                double endLongitude) {

                        List<double[]> points = new ArrayList<>();

                        points.add(
                                        new double[] {
                                                        startLatitude,
                                                        startLongitude });

                        points.add(
                                        new double[] {
                                                        endLatitude,
                                                        endLongitude });

                        return points;
                }

                /**
                 * Stores the route, re-centers/zooms the map so the whole
                 * route is visible, and triggers a re-render (which draws
                 * the route via drawRoute()).
                 */
                private void applyRoute(
                                List<double[]> points,
                                double startLatitude,
                                double startLongitude,
                                double endLatitude,
                                double endLongitude) {

                        this.routePoints = points;
                        this.routeStartLat = startLatitude;
                        this.routeStartLon = startLongitude;
                        this.routeEndLat = endLatitude;
                        this.routeEndLon = endLongitude;

                        double minLat = Math.min(startLatitude, endLatitude);
                        double maxLat = Math.max(startLatitude, endLatitude);
                        double minLon = Math.min(startLongitude, endLongitude);
                        double maxLon = Math.max(startLongitude, endLongitude);

                        for (double[] point : points) {
                                minLat = Math.min(minLat, point[0]);
                                maxLat = Math.max(maxLat, point[0]);
                                minLon = Math.min(minLon, point[1]);
                                maxLon = Math.max(maxLon, point[1]);
                        }

                        fitToBounds(
                                        minLat,
                                        maxLat,
                                        minLon,
                                        maxLon);

                        renderMap();
                }

                /**
                 * Centers the map on the given bounding box and picks the
                 * highest zoom level at which the whole box still fits
                 * comfortably inside the current viewport.
                 */
                private void fitToBounds(
                                double minLatitude,
                                double maxLatitude,
                                double minLongitude,
                                double maxLongitude) {

                        double viewWidth = Math.max(getWidth(), 200);
                        double viewHeight = Math.max(getHeight(), 200);

                        int bestZoom = MIN_ZOOM;

                        for (int candidateZoom = MAX_ZOOM;
                                        candidateZoom >= MIN_ZOOM;
                                        candidateZoom--) {

                                double x1 = longitudeToWorldX(minLongitude, candidateZoom);
                                double x2 = longitudeToWorldX(maxLongitude, candidateZoom);
                                double y1 = latitudeToWorldY(minLatitude, candidateZoom);
                                double y2 = latitudeToWorldY(maxLatitude, candidateZoom);

                                double spanX = Math.abs(x2 - x1);
                                double spanY = Math.abs(y2 - y1);

                                // Leave ~20% padding around the route.
                                if (spanX <= viewWidth * 0.8 &&
                                                spanY <= viewHeight * 0.8) {

                                        bestZoom = candidateZoom;
                                        break;
                                }
                        }

                        centerLatitude = (minLatitude + maxLatitude) / 2.0;
                        centerLongitude = (minLongitude + maxLongitude) / 2.0;
                        zoom = bestZoom;
                }

                /**
                 * Draws the currently active route (if any) as a polyline,
                 * plus start/end markers, on routePane. Called every time
                 * renderMap() runs so the route tracks pan/zoom/resize.
                 */
                private void drawRoute(
                                double leftWorld,
                                double topWorld) {

                        if (routePoints == null ||
                                        routePoints.size() < 2) {
                                return;
                        }

                        Polyline line = new Polyline();

                        for (double[] point : routePoints) {

                                double worldX =
                                                longitudeToWorldX(point[1], zoom)
                                                                - leftWorld;

                                double worldY =
                                                latitudeToWorldY(point[0], zoom)
                                                                - topWorld;

                                line.getPoints().addAll(worldX, worldY);
                        }

                        line.setStroke(Color.web("#2E7DFF"));
                        line.setStrokeWidth(5);
                        line.setStrokeLineCap(StrokeLineCap.ROUND);
                        line.setStrokeLineJoin(StrokeLineJoin.ROUND);

                        routePane.getChildren().add(line);

                        addRouteEndpointMarker(
                                        routeStartLat,
                                        routeStartLon,
                                        leftWorld,
                                        topWorld,
                                        Color.web("#2E7DFF"),
                                        "Start");

                        addRouteEndpointMarker(
                                        routeEndLat,
                                        routeEndLon,
                                        leftWorld,
                                        topWorld,
                                        Color.web("#1EAE55"),
                                        "Destination");
                }

                private void addRouteEndpointMarker(
                                double latitude,
                                double longitude,
                                double leftWorld,
                                double topWorld,
                                Color color,
                                String label) {

                        double worldX = longitudeToWorldX(longitude, zoom);
                        double worldY = latitudeToWorldY(latitude, zoom);

                        double x = worldX - leftWorld;
                        double y = worldY - topWorld;

                        Circle marker = new Circle(9, color);
                        marker.setStroke(Color.WHITE);
                        marker.setStrokeWidth(3);

                        Tooltip.install(
                                        marker,
                                        new Tooltip(label));

                        marker.setLayoutX(x);
                        marker.setLayoutY(y);

                        routePane.getChildren().add(marker);
                }

                /**
                 * Minimal, dependency-free extraction of the
                 * "geometry.coordinates" [[lon,lat], ...] array from an
                 * OSRM GeoJSON route response, without pulling in a JSON
                 * library. Returns an empty list if parsing fails, which
                 * triggers the straight-line fallback.
                 */
                private static List<double[]> parseRouteCoordinates(
                                String json) {

                        List<double[]> points = new ArrayList<>();

                        try {
                                int geometryIndex = json.indexOf("\"geometry\"");

                                int coordinatesIndex =
                                                json.indexOf(
                                                                "\"coordinates\"",
                                                                Math.max(geometryIndex, 0));

                                if (coordinatesIndex < 0) {
                                        return points;
                                }

                                int start = json.indexOf("[[", coordinatesIndex);
                                int end = json.indexOf("]]", start);

                                if (start < 0 || end < 0) {
                                        return points;
                                }

                                String coordsBlock =
                                                json.substring(start + 2, end);

                                String[] pairs =
                                                coordsBlock.split("\\],\\[");

                                for (String pair : pairs) {

                                        String[] parts = pair.split(",");

                                        if (parts.length < 2) {
                                                continue;
                                        }

                                        double lon = Double.parseDouble(parts[0].trim());
                                        double lat = Double.parseDouble(parts[1].trim());

                                        points.add(new double[] { lat, lon });
                                }

                        } catch (Exception parseError) {

                                System.err.println(
                                                "Could not parse OSRM route response: " +
                                                                parseError.getMessage());

                                points.clear();
                        }

                        return points;
                }

                private static double longitudeToWorldX(
                                double longitude,
                                int zoomLevel) {

                        double mapSize =
                                        TILE_SIZE *
                                                        Math.pow(
                                                                        2,
                                                                        zoomLevel);

                        return (longitude + 180.0) /
                                        360.0 *
                                        mapSize;
                }

                private static double latitudeToWorldY(
                                double latitude,
                                int zoomLevel) {

                        double mapSize =
                                        TILE_SIZE *
                                                        Math.pow(
                                                                        2,
                                                                        zoomLevel);

                        double latitudeRadians =
                                        Math.toRadians(latitude);

                        double y =
                                        (1.0 -
                                                        Math.log(
                                                                        Math.tan(latitudeRadians) +
                                                                                        (1.0 /
                                                                                                        Math.cos(latitudeRadians)))
                                                                        / Math.PI)
                                                        / 2.0;

                        return y * mapSize;
                }

                private static double worldXToLongitude(
                                double worldX,
                                int zoomLevel) {

                        double mapSize =
                                        TILE_SIZE *
                                                        Math.pow(
                                                                        2,
                                                                        zoomLevel);

                        return worldX /
                                        mapSize *
                                        360.0 -
                                        180.0;
                }

                private static double worldYToLatitude(
                                double worldY,
                                int zoomLevel) {

                        double mapSize =
                                        TILE_SIZE *
                                                        Math.pow(
                                                                        2,
                                                                        zoomLevel);

                        double y =
                                        1.0 -
                                                        (2.0 * worldY /
                                                                        mapSize);

                        return Math.toDegrees(
                                        Math.atan(
                                                        Math.sinh(
                                                                        y * Math.PI)));
                }
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
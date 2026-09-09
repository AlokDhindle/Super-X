package com.kryox.view.Customer;

import java.util.List;


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


public class My_orderAllorder  {
        public String userId;
        public My_orderAllorder(String userId) {
        this.userId = userId;
    }

        private Scene orderAllScene;

        
        public Scene getMy_OrderAllScene() {
                return getMy_OrderAllScene(() -> CustomerNavigation.navigateToDashboard(userId));
        }

        public Scene getAllorderScene() {
                return getMy_OrderAllScene();
        }

        public Scene getAllorderScene(Runnable callback) {
                return getMy_OrderAllScene(callback);
        }

        public Scene getMy_OrderAllScene(Runnable callback) {
                

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

                Label name = new Label("BuyNeX");
                name.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: 900;" +
                                                "-fx-text-fill: #E87500;");

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

                lefButton2.setOnAction(e -> CustomerNavigation.navigateToNearbyShops(userId));

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

                lefButton3.setOnAction(e -> CustomerNavigation.navigateToDeals(userId));

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

                lefButton4.setOnAction(e -> CustomerNavigation.navigateToOrders(userId));

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
                        lefButton5.setOnAction(event->{
                                Analytics an=new Analytics(userId);
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
                                                "-fx-cursor: hand;");

                lefButton6.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

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
                                                "-fx-cursor: hand;");

                lefButton7.setOnAction(e -> CustomerNavigation.navigateToHelp(userId));

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
                leftBox.setStyle("-fx-background-color: #EBCCB7");
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

                t1.setOnAction(e -> CustomerNavigation.navigateToDeals(userId));
                t2.setOnAction(e -> CustomerNavigation.navigateToNearbyShops(userId));
                t3.setOnAction(e -> CustomerNavigation.navigateToHelp(userId));

                HBox topLinks = new HBox(6, t1, t2, t3);
                topLinks.setAlignment(Pos.CENTER_LEFT);

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

                searchBox.setOnAction(e -> {
                    CustomerNavigation.performSearch(userId, searchBox.getText(), () -> CustomerNavigation.navigateToOrders(userId));
                });

                Label locationIcon = new Label("📍");
                Label locationText = new Label("Downtown Manhattan⌄");
                locationText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #555555;");

                HBox locationBox = new HBox(4, locationIcon, locationText);
                locationBox.setAlignment(Pos.CENTER_LEFT);
                locationBox.setStyle("-fx-cursor: hand;");
                locationBox.setOnMouseClicked(e -> CustomerNavigation.navigateToNearbyShops(userId));

                Button b1 = new Button("🔔");
                Button b2 = new Button("🛒");
                Button b3 = new Button("●");

                b1.setOnAction(e -> CustomerNavigation.navigateToNotifications(userId));
                b2.setOnAction(e -> CustomerNavigation.navigateToCart(userId));
                b3.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

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
                b2.setOnAction(event->{
                        ShoppingCartUI sc=new ShoppingCartUI(userId);
                        Homepage.HomepageStage.setScene(sc.getaddcartScene());
                });

                b3.setOnMouseEntered(e -> b3.setStyle(actionHoverStyle));
                b3.setOnMouseExited(e -> b3.setStyle(actionStyle));

                HBox actionBox = new HBox(7, b1, b2, b3);
                actionBox.setAlignment(Pos.CENTER_RIGHT);

                Region navSpacer1 = new Region();
                HBox.setHgrow(navSpacer1, Priority.ALWAYS);
                Region navSpacer2 = new Region();
                HBox.setHgrow(navSpacer2, Priority.ALWAYS);

                HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
                navBox.setPrefHeight(68);
                navBox.setPadding(new Insets(12, 24, 12, 24));
                navBox.setAlignment(Pos.CENTER_LEFT);
                navBox.setStyle(
                                "-fx-background-color: #EBCCB7;" +
                                                "-fx-border-width: 0 0 1 0;");
                navBox.setTranslateX(-28);

                VBox mainContent = new VBox(20);
                mainContent.setPadding(new Insets(20, 25, 30, 25));
                mainContent.setStyle("-fx-background-color: #EEE5DE");

                Label pageTitle = new Label("# My Orders");
                pageTitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                HBox tabsBox = new HBox(15);
                tabsBox.setAlignment(Pos.CENTER_LEFT);
                tabsBox.setPadding(new Insets(10, 0, 15, 0));

                Button allOrders = new Button("All Orders");
                allOrders.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0 0 2 0;" +
                                                "-fx-border-color: #FF6900;" +
                                                "-fx-cursor: hand;");

                Button ongoing = new Button("Ongoing");
                ongoing.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;");
                ongoing.setOnAction(event -> {
                        My_orderOngoing mo=new My_orderOngoing(userId);
                        Runnable callbacktoallorder=new Runnable() {
                                public void run(){
                                        callback.run();

                                }
                        };
                       Homepage.HomepageStage.setScene(mo.getOngoiScene(callbacktoallorder));

                        

                      

                });

                Button shipped = new Button("Shipped");
                shipped.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;");

                Button delivered = new Button("Delivered");
                delivered.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;");

                Button cancelled = new Button("Cancelled");
                cancelled.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: 600;" +
                                                "-fx-padding: 8 16 8 16;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;");

                tabsBox.getChildren().add(allOrders);
                tabsBox.getChildren().add(ongoing);
                tabsBox.getChildren().add(shipped);
                tabsBox.getChildren().add(delivered);
                tabsBox.getChildren().add(cancelled);

                VBox ordersList = new VBox(15);
                ordersList.setPadding(new Insets(5, 0, 0, 0));

                loadCartProducts(ordersList);

                mainContent.getChildren().addAll(
                                pageTitle,
                                tabsBox,
                                ordersList);

                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(mainContent);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                VBox Rightvbox = new VBox(0);
                Rightvbox.setPadding(new Insets(0, 0, 0, 0));
                Rightvbox.setStyle("-fx-background-color: #EEE5DE");
                Rightvbox.getChildren().addAll(navBox, scrollPane);
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

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
                mainBox.setStyle("-fx-background-color:#EEE5DE");

                Scene sc = new Scene(mainBox, 1550, 850);
                orderAllScene=sc;
                return orderAllScene;
        }
        

        private void loadCartProducts(VBox ordersList) {

                com.kryox.dao.Customer.OrderDAO customerOrderDAO = new com.kryox.dao.Customer.OrderDAO();

                try {

                        if (userId == null || userId.trim().isEmpty()) {

                                ordersList.getChildren().clear();

                                Label errorLabel = new Label("User not logged in.");
                                errorLabel.setStyle(
                                                "-fx-font-family: 'Montserrat';" +
                                                                "-fx-font-size: 15px;" +
                                                                "-fx-font-weight: 600;" +
                                                                "-fx-text-fill: #C62828;");

                                errorLabel.setPadding(new Insets(30, 0, 30, 0));
                                ordersList.getChildren().add(errorLabel);
                                return;
                        }

                        System.out.println("MY ORDERS USER ID = [" + userId + "]");

                        List<com.kryox.model.Shopkeeper.OrderModel> placedOrders =
                                        customerOrderDAO.getCustomerOrders(userId);

                        ordersList.getChildren().clear();

                        if (placedOrders == null || placedOrders.isEmpty()) {

                                Label noOrders = new Label("No orders placed yet.");
                                noOrders.setStyle(
                                                "-fx-font-family: 'Montserrat';" +
                                                                "-fx-font-size: 16px;" +
                                                                "-fx-font-weight: 600;" +
                                                                "-fx-text-fill: #777777;");

                                noOrders.setPadding(new Insets(30, 0, 30, 0));
                                ordersList.getChildren().add(noOrders);
                                return;
                        }

                        for (com.kryox.model.Shopkeeper.OrderModel order : placedOrders) {

                                if (order == null) {
                                        continue;
                                }

                                String orderId = safeValue(order.getOrderId(), "N/A");
                                String orderDate = safeValue(order.getOrderDate(), "Today");
                                String status = safeValue(order.getOrderStatus(), "NEW");

                                String productName = "Items (" + (order.getProducts() != null ? order.getProducts().size() : 0) + ")";
                                if (order.getProducts() != null && !order.getProducts().isEmpty()) {
                                        var p1 = order.getProducts().get(0);
                                        productName = safeValue(p1.getProductName(), "Product");
                                        if (order.getProducts().size() > 1) {
                                                productName += " + " + (order.getProducts().size() - 1) + " more";
                                        }
                                }

                                String orderDetails = "Date: " + orderDate + " • Status: " + status;
                                String price = String.format("₹%.2f", order.getTotalAmount());

                                String[] actions = getActionsForStatus(status);

                                VBox orderCard = createOrderCard(
                                                "ORDER",
                                                orderId,
                                                productName,
                                                orderDetails,
                                                price,
                                                actions,
                                                status,
                                                "📦");

                                ordersList.getChildren().add(orderCard);
                        }

                } catch (Exception e) {

                        e.printStackTrace();

                        ordersList.getChildren().clear();

                        Label errorLabel =
                                        new Label("Unable to load orders.");

                        errorLabel.setStyle(
                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: 600;" +
                                                        "-fx-text-fill: #C62828;");

                        errorLabel.setPadding(new Insets(30, 0, 30, 0));

                        ordersList.getChildren().add(errorLabel);
                }
        }

        private String[] getActionsForStatus(String status) {

                if (status == null) {
                        return new String[] { "View Details" };
                }

                switch (status.trim().toLowerCase()) {

                        case "delivered":
                                return new String[] {
                                                "View Details",
                                                "Download Invoice",
                                                "Buy Again"
                                };

                        case "dispatched":
                        case "shipped":
                        case "out for delivery":
                                return new String[] {
                                                "View Details",
                                                "Track Order"
                                };

                        case "processing":
                        case "ongoing":
                                return new String[] {
                                                "Cancel Order",
                                                "View Details"
                                };

                        case "cancelled":
                                return new String[] {
                                                "View Details",
                                                "Buy Again"
                                };

                        default:
                                return new String[] {
                                                "View Details"
                                };
                }
        }

        private String safeValue(String value, String defaultValue) {

                if (value == null || value.trim().isEmpty()) {
                        return defaultValue;
                }

                return value;
        }

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

                // First action button
                if (actions.length > 0) {
                        Button firstButton = new Button(actions[0]);

                        firstButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #FF6900;" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-font-weight: 600;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-radius: 20;" +
                                                        "-fx-padding: 6 16 6 16;" +
                                                        "-fx-cursor: hand;");

                        firstButton.setOnAction(e -> System.out.println(actions[0] + " clicked for order: " + orderId));

                        actionBox.getChildren().add(firstButton);
                }

                // Second action button
                if (actions.length > 1) {
                        Button secondButton = new Button(actions[1]);

                        secondButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #FF6900;" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-font-weight: 600;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-radius: 20;" +
                                                        "-fx-padding: 6 16 6 16;" +
                                                        "-fx-cursor: hand;");

                        secondButton.setOnAction(
                                        e -> System.out.println(actions[1] + " clicked for order: " + orderId));

                        actionBox.getChildren().add(secondButton);
                }

                // Third action button
                if (actions.length > 2) {
                        Button thirdButton = new Button(actions[2]);

                        thirdButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #FF6900;" +
                                                        "-fx-font-size: 11px;" +
                                                        "-fx-font-weight: 600;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-radius: 20;" +
                                                        "-fx-padding: 6 16 6 16;" +
                                                        "-fx-cursor: hand;");

                        thirdButton.setOnAction(e -> System.out.println(actions[2] + " clicked for order: " + orderId));

                        actionBox.getChildren().add(thirdButton);
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
        public void backtoallorder(){
                Homepage.HomepageStage.setScene(orderAllScene);
        }

}




                
               

        
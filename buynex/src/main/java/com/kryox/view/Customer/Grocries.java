package com.kryox.view.Customer;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

import javafx.scene.paint.Stop;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Text;


public class Grocries  {

        private Scene GrocriesScene;

        // Shared cart for all products on this screen
        private final List<String> cartItems = new ArrayList<>();
        private int cartCount = 0;

        /**
         * Loads a product image from src/main/resources.
         * If the image is not present, an emoji is shown so the UI still runs.
         */
        private HBox createProductImage(String imagePath, String fallbackEmoji) {
                HBox box = new HBox();
                box.setPrefSize(90, 90);
                box.setMinSize(90, 90);
                box.setMaxSize(90, 90);
                box.setAlignment(Pos.CENTER);
                box.setStyle(
                        "-fx-background-color: #FFF7F2;" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-color: #FFE2D2;" +
                        "-fx-border-radius: 14;" +
                        "-fx-border-width: 1;"
                );

                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                        ImageView imageView = new ImageView(new Image(imageUrl.toExternalForm()));
                        imageView.setFitWidth(78);
                        imageView.setFitHeight(78);
                        imageView.setPreserveRatio(true);
                        box.getChildren().add(imageView);
                } else {
                        Label fallback = new Label(fallbackEmoji);
                        fallback.setStyle("-fx-font-size: 42px;");
                        box.getChildren().add(fallback);
                }

                return box;
        }

        private void addProductToCart(Button button, String productName, String price) {
                cartItems.add(productName + " - " + price);
                cartCount++;
                button.setText("✓");
                button.setStyle(
                        "-fx-background-color: #2E9B57;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 50%;" +
                        "-fx-cursor: hand;"
                );
        }

        Scene getGrocriescene(Runnable callbacktoRunnable){
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
        lefButton1.setOnAction(event->{
                callbacktoRunnable.run();
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
                neaby_shope bs=new neaby_shope();
                Homepage.HomepageStage.setScene(bs.getNearby_shopes(callbacktoRunnable));
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
                 Seting se=new Seting();
                Runnable callbacktoDashborad=new Runnable() {
                        public void run(){
                                
                        }
                };
                Homepage.HomepageStage.setScene(se.getSetingscene(callbacktoDashborad=null));
        });

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
        searchBox.setTranslateX(-100);

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
        locationBox.setTranslateX(-200);

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

        b2.setOnAction(e -> {
                
                ShoppingCartUI sp=new ShoppingCartUI();
                Homepage.HomepageStage.setScene(sp.getaddcartScene());
        });

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
        actionBox.setTranslateX(-200);

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
                "-fx-background-color: white;" +
                "-fx-background-color: #ebccb7;" +
                "-fx-border-width: 0 0 1 0;"
        );
        navBox.setTranslateX(-28);

        // =====================================================
        // HERO BOX
        // =====================================================

        HBox hbright = new HBox(35);
        hbright.setPrefWidth(1200);
        hbright.setPrefHeight(315);
        hbright.setPadding(new Insets(34, 40, 34, 40));
        hbright.setAlignment(Pos.CENTER_LEFT);

        LinearGradient darkGradient = new LinearGradient(
                0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#242529")),
                new Stop(0.55, Color.web("#303136")),
                new Stop(1.0, Color.web("#563A2B"))
        );

        hbright.setBackground(
                new Background(
                        new BackgroundFill(
                                darkGradient,
                                new CornerRadii(22),
                                Insets.EMPTY
                        )
                )
        );
        hbright.setEffect(cardShadow);

        // =====================================================
        // HERO LEFT CONTENT
        // =====================================================

        VBox leftContent = new VBox(13);
        leftContent.setPrefWidth(600);
        leftContent.setAlignment(Pos.CENTER_LEFT);

        Label badge = new Label("✦  NEXT-GEN INTELLIGENCE");
        badge.setStyle(
                "-fx-background-color: #503629;" +
                "-fx-text-fill: #FF9D67;" +
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 7 13 7 13;"
        );

        Text heading = new Text("What are you\nlooking for today?");
        heading.setStyle(
                "-fx-fill: white;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: 900;"
        );

        Text description = new Text(
                "Your hyper-local AI is ready to source, compare,\n" +
                "and deliver from your favorite downtown spots."
        );
        description.setStyle(
                "-fx-fill: #C9C9C9;" +
                "-fx-font-size: 11px;"
        );

        Button groceries = new Button("♧  Combine Groceries");
        groceries.setPrefHeight(42);
        groceries.setStyle(
                "-fx-background-color: #45464B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 16 10 16;" +
                "-fx-cursor: hand;"
        );

        Button bestPrice = new Button("⌁  Find Best Price");
        bestPrice.setPrefHeight(42);
        bestPrice.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 17 10 17;" +
                "-fx-cursor: hand;"
        );

        HBox buttons = new HBox(10, groceries, bestPrice);
        leftContent.getChildren().addAll(badge, heading, description, buttons);

        // =====================================================
        // AI RECOMMENDATION CARD
        // =====================================================

        VBox notification = new VBox(13);
        notification.setPrefWidth(300);
        notification.setMinWidth(300);
        notification.setMaxWidth(300);
        notification.setPrefHeight(245);
        notification.setPadding(new Insets(20));
        notification.setAlignment(Pos.TOP_LEFT);
        notification.setStyle(
                "-fx-background-color: #ECEAE9;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: rgba(255,255,255,0.35);" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );
        notification.setEffect(cardShadow);
        

        Label aiCircle = new Label("✦");
        aiCircle.setPrefSize(38, 38);
        aiCircle.setAlignment(Pos.CENTER);
        aiCircle.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;"
        );

        VBox aiHeading = new VBox(2);
        Label aiTitle = new Label("AI Recommendation");
        aiTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label aiStatus = new Label("PERSONALIZED FOR YOU");
        aiStatus.setStyle(
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        aiHeading.getChildren().addAll(aiTitle, aiStatus);

        HBox aiHeader = new HBox(10, aiCircle, aiHeading);
        aiHeader.setAlignment(Pos.CENTER_LEFT);

        Text notificationText = new Text(
                "You usually order coffee beans on Tuesdays.\n\n" +
                "Artisan Pantry has your favorite\n" +
                "brand in stock today."
        );
        notificationText.setStyle(
                "-fx-fill: #444444;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Region notificationSpacer = new Region();
        VBox.setVgrow(notificationSpacer, Priority.ALWAYS);

        Button dismiss = new Button("Dismiss");
        dismiss.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 9px;" +
                "-fx-cursor: hand;"
        );

        Button checkStock = new Button("Check Stock");
        checkStock.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8 13 8 13;" +
                "-fx-cursor: hand;"
        );

        HBox notificationButtons = new HBox(8, dismiss, checkStock);
        notificationButtons.setAlignment(Pos.CENTER_RIGHT);

        notification.getChildren().addAll(
                aiHeader,
                notificationText,
                notificationSpacer,
                notificationButtons
        );

        hbright.getChildren().addAll(leftContent, notification);

        // =====================================================
        // CATEGORY TITLE
        // =====================================================

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

        Region categorySpacer = new Region();
        HBox.setHgrow(categorySpacer, Priority.ALWAYS);

        categoryHeader.getChildren().addAll(categoryTitle, categorySpacer, viewAll);

        // =====================================================
        // CATEGORY BUTTONS
        // =====================================================

        Button grocerie = new Button("🛒\nGroceries");
        Button electronics = new Button("▣\nElectronics");
        electronics.setOnAction(event->{
                Electronics gs=new Electronics();
                Homepage.HomepageStage.setScene(gs.getElectrScene());
        });
        Button fashion = new Button("♧\nFashion");
        Button health = new Button("✚\nHealth");
        Button home = new Button("⌂\nHome");
        Button gifts = new Button("♧\nGifts");
        Button beauty = new Button("✦\nBeauty");
        Button pharmacy = new Button("✚\nPharmacy");
        Button sports = new Button("⚽\nSports");
        Button furniture = new Button("⌂\nFurniture");
        Button toys = new Button("♟\nToys");
        Button stationery = new Button("✎\nStationery");

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

        // =====================================================
        // CATEGORY ROWS
        // =====================================================

        HBox categoriesRow1 = new HBox(12, grocerie, electronics, fashion, health, home, gifts,beauty, pharmacy, sports, furniture, toys, stationery);
        categoriesRow1.setAlignment(Pos.CENTER_LEFT);

        
        // =====================================================
        // CATEGORY SECTION
        // =====================================================

        VBox categorySection = new VBox(13, categoryHeader, categoriesRow1);
        categorySection.setPadding(new Insets(0, 8, 20, 8));

        // =====================================================
        // RIGHT VBOX
        // =====================================================

        DropShadow cardShadow3 = new DropShadow();
        cardShadow3.setRadius(10);
        cardShadow3.setOffsetY(3);
        cardShadow3.setSpread(0.02);
        cardShadow3.setColor(Color.rgb(0, 0, 0, 0.08));

        DropShadow buttonShadow = new DropShadow();
        buttonShadow.setRadius(8);
        buttonShadow.setOffsetY(2);
        buttonShadow.setColor(Color.rgb(0, 0, 0, 0.12));

        // =====================================================
        // HEADER: Groceries
        // =====================================================

        Label title1= new Label("# Groceries");
        title1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Label subtitle1 = new Label("120+ Items found in Manhattan, NY");
        subtitle1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #777777;"
        );

        Label sortLabel = new Label("Sort by: Popularity ⌄");
        sortLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;" +
                "-fx-cursor: hand;"
        );

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        HBox headerRow = new HBox(15, title1, headerSpacer, sortLabel);
        headerRow.setAlignment(Pos.CENTER_LEFT);
        headerRow.setPadding(new Insets(0, 0, 10, 0));

        VBox headerSection = new VBox(5, headerRow, subtitle1);
        headerSection.setPadding(new Insets(20, 20, 10, 20));

        // =====================================================
        // DIVIDER
        // =====================================================

        Region divider = new Region();
        divider.setPrefHeight(1);
        divider.setStyle("-fx-background-color: #EAE6EC;");
        divider.setPadding(new Insets(0, 20, 0, 20));

        // =====================================================
        // MAIN CONTENT VBOX
        // =====================================================

        VBox content = new VBox(12);
        content.setPadding(new Insets(0, 20, 20, 20));

        // =====================================================
        // PRODUCT 1: Organic Hass Avocados
        // =====================================================

        VBox card1 = new VBox(5);
        card1.setPrefWidth(280);
        card1.setMinWidth(280);
        card1.setMaxWidth(280);
        card1.setPrefHeight(380);
        card1.setPadding(new Insets(15, 16, 15, 16));
        card1.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        card1.setEffect(cardShadow3);

        VBox productRow1 = new VBox(12);
        productRow1.setAlignment(Pos.CENTER);

        // Product image
        HBox imagePlaceholder1 = createProductImage(
                "/assets/images/products/avocado.png",
                "🥑"
        );

        VBox productDetails1 = new VBox(3);
        productDetails1.setAlignment(Pos.CENTER);

        Label name1 = new Label("Organic Hass Avocados");
        name1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label desc1 = new Label("Pack of 4 • Fresh Produce");
        desc1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceRow1 = new HBox(8);
        priceRow1.setAlignment(Pos.CENTER_LEFT);

        Label price1 = new Label("$6.99");
        price1.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Label discount1 = new Label("20% OFF");
        discount1.setStyle(
                "-fx-background-color: #FFE8E0;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: 700;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 2 10 2 10;"
        );

        Label originalPrice1 = new Label("$8.99");
        originalPrice1.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #AAAAAA;" +
                "-fx-strikethrough: true;"
        );

        priceRow1.getChildren().addAll(price1, discount1, originalPrice1);
        productDetails1.getChildren().addAll(name1, desc1, priceRow1);

        Button addToCart1 = new Button("+");
        addToCart1.setPrefSize(30, 30);
        addToCart1.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        );

        addToCart1.setOnMouseEntered(e -> {
            addToCart1.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart1.setOnMouseExited(e -> {
            addToCart1.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart1.setOnAction(e -> {
                addProductToCart(addToCart1, "Organic Hass Avocados", "$6.99");
        });

        productRow1.getChildren().addAll(imagePlaceholder1, productDetails1, addToCart1);
        card1.getChildren().add(productRow1);

        // Hover effect for card1
        card1.setOnMouseEntered(e -> {
            card1.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #FFD8C4;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1.5;"
            );
            DropShadow hoverShadow = new DropShadow();
            hoverShadow.setRadius(12);
            hoverShadow.setOffsetY(4);
            hoverShadow.setSpread(0.04);
            hoverShadow.setColor(Color.rgb(0, 0, 0, 0.10));
            card1.setEffect(hoverShadow);
        });

        card1.setOnMouseExited(e -> {
            card1.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #EAE6EC;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1;"
            );
            card1.setEffect(cardShadow3);
        });

        // =====================================================
        // PRODUCT 2: Fresh Farm Whole Milk
        // =====================================================

        VBox card2 = new VBox(5);
        card2.setPrefWidth(280);
        card2.setMinWidth(280);
        card2.setMaxWidth(280);
        card2.setPrefHeight(380);
        card2.setPadding(new Insets(15, 16, 15, 16));
        card2.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        card2.setEffect(cardShadow3);

        VBox productRow2 = new VBox(12);
        productRow2.setAlignment(Pos.CENTER);

        HBox imagePlaceholder2 = createProductImage(
                "/assets/images/products/milk.png",
                "🥛"
        );

        VBox productDetails2 = new VBox(3);
        productDetails2.setAlignment(Pos.CENTER);

        Label name2 = new Label("Fresh Farm Whole Milk");
        name2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label desc2 = new Label("1L Bottle • Dairy");
        desc2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceRow2 = new HBox(8);
        priceRow2.setAlignment(Pos.CENTER_LEFT);

        Label price2 = new Label("$3.29");
        price2.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        priceRow2.getChildren().addAll(price2);
        productDetails2.getChildren().addAll(name2, desc2, priceRow2);

        Button addToCart2 = new Button("+");
        addToCart2.setPrefSize(30, 30);
        addToCart2.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        );

        addToCart2.setOnMouseEntered(e -> {
            addToCart2.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart2.setOnMouseExited(e -> {
            addToCart2.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart2.setOnAction(e -> {
                addProductToCart(addToCart2, "Fresh Farm Whole Milk", "$3.29");
        });

        productRow2.getChildren().addAll(imagePlaceholder2, productDetails2, addToCart2);
        card2.getChildren().add(productRow2);

        // Hover effect for card2
        card2.setOnMouseEntered(e -> {
            card2.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #FFD8C4;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1.5;"
            );
            DropShadow hoverShadow = new DropShadow();
            hoverShadow.setRadius(12);
            hoverShadow.setOffsetY(4);
            hoverShadow.setSpread(0.04);
            hoverShadow.setColor(Color.rgb(0, 0, 0, 0.10));
            card2.setEffect(hoverShadow);
        });

        card2.setOnMouseExited(e -> {
            card2.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #EAE6EC;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1;"
            );
            card2.setEffect(cardShadow3);
        });

        // =====================================================
        // PRODUCT 3: Artisan Sourdough Loaf
        // =====================================================

        VBox card3 = new VBox(5);
        card3.setPrefWidth(280);
        card3.setMinWidth(280);
        card3.setMaxWidth(280);
        card3.setPrefHeight(380);
        card3.setPadding(new Insets(15, 16, 15, 16));
        card3.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        card3.setEffect(cardShadow3);

        VBox productRow3 = new VBox(12);
        productRow3.setAlignment(Pos.CENTER);

        HBox imagePlaceholder3 = createProductImage(
                "/assets/images/products/bread.png",
                "🍞"
        );

        VBox productDetails3 = new VBox(3);
        productDetails3.setAlignment(Pos.CENTER);

        Label name3 = new Label("Artisan Sourdough Loaf");
        name3.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label desc3 = new Label("500g • Bakery");
        desc3.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceRow3 = new HBox(8);
        priceRow3.setAlignment(Pos.CENTER_LEFT);

        Label price3 = new Label("$6.50");
        price3.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        priceRow3.getChildren().addAll(price3);
        productDetails3.getChildren().addAll(name3, desc3, priceRow3);

        Button addToCart3 = new Button("+");
        addToCart3.setPrefSize(30, 30);
        addToCart3.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        );

        addToCart3.setOnMouseEntered(e -> {
            addToCart3.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart3.setOnMouseExited(e -> {
            addToCart3.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart3.setOnAction(e -> {
                addProductToCart(addToCart3, "Artisan Sourdough Loaf", "$6.50");
        });

        productRow3.getChildren().addAll(imagePlaceholder3, productDetails3, addToCart3);
        card3.getChildren().add(productRow3);

        // Hover effect for card3
        card3.setOnMouseEntered(e -> {
            card3.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #FFD8C4;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1.5;"
            );
            DropShadow hoverShadow = new DropShadow();
            hoverShadow.setRadius(12);
            hoverShadow.setOffsetY(4);
            hoverShadow.setSpread(0.04);
            hoverShadow.setColor(Color.rgb(0, 0, 0, 0.10));
            card3.setEffect(hoverShadow);
        });

        card3.setOnMouseExited(e -> {
            card3.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #EAE6EC;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1;"
            );
            card3.setEffect(cardShadow3);
        });

        // =====================================================
        // LIGHTNING DELIVERY BANNER
        // =====================================================

        HBox deliveryBanner = new HBox(15);
        deliveryBanner.setAlignment(Pos.CENTER_LEFT);
        deliveryBanner.setPadding(new Insets(18, 20, 18, 20));
        deliveryBanner.setStyle(
                "-fx-background-color: #FFF3EA;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #FFD8C4;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        deliveryBanner.setEffect(cardShadow3);

        Label lightningIcon = new Label("⚡");
        lightningIcon.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-padding: 0 5 0 0;"
        );

        VBox deliveryText = new VBox(2);
        Label deliveryTitle = new Label("LIGHTNING DELIVERY");
        deliveryTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #FF6900;"
        );

        Label deliveryDesc = new Label("Freshness delivered in 15 minutes.");
        deliveryDesc.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #444444;"
        );

        deliveryText.getChildren().addAll(deliveryTitle, deliveryDesc);

        Region deliverySpacer = new Region();
        HBox.setHgrow(deliverySpacer, Priority.ALWAYS);

        Button shopExpress = new Button("Shop Express →");
        shopExpress.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-cursor: hand;"
        );
        shopExpress.setEffect(buttonShadow);

        shopExpress.setOnMouseEntered(e -> {
            shopExpress.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-padding: 8 18 8 18;" +
                    "-fx-cursor: hand;"
            );
        });

        shopExpress.setOnMouseExited(e -> {
            shopExpress.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-padding: 8 18 8 18;" +
                    "-fx-cursor: hand;"
            );
        });

        deliveryBanner.getChildren().addAll(lightningIcon, deliveryText, deliverySpacer, shopExpress);

        // =====================================================
        // PRODUCT 4: Premium Yellow Bananas
        // =====================================================

        VBox card4 = new VBox(5);
        card4.setPrefWidth(280);
        card4.setMinWidth(280);
        card4.setMaxWidth(280);
        card4.setPrefHeight(380);
        card4.setPadding(new Insets(15, 16, 15, 16));
        card4.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #EAE6EC;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );
        card4.setEffect(cardShadow3);

        VBox productRow4 = new VBox(12);
        productRow4.setAlignment(Pos.CENTER);

        HBox imagePlaceholder4 = createProductImage(
                "/assets/images/products/banana.png",
                "🍌"
        );

        VBox productDetails4 = new VBox(3);
        productDetails4.setAlignment(Pos.CENTER);

        Label name4 = new Label("Premium Yellow Bananas");
        name4.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );

        Label desc4 = new Label("1 Bunch (Approx 1kg) • Fruits");
        desc4.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #777777;"
        );

        HBox priceRow4 = new HBox(8);
        priceRow4.setAlignment(Pos.CENTER_LEFT);

        Label price4 = new Label("$1.99");
        price4.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        priceRow4.getChildren().addAll(price4);
        productDetails4.getChildren().addAll(name4, desc4, priceRow4);

        Button addToCart4 = new Button("+");
        addToCart4.setPrefSize(30, 30);
        addToCart4.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        );

        addToCart4.setOnMouseEntered(e -> {
            addToCart4.setStyle(
                    "-fx-background-color: #E85A00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart4.setOnMouseExited(e -> {
            addToCart4.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 50%;" +
                    "-fx-cursor: hand;"
            );
        });

        addToCart4.setOnAction(e -> {
                addProductToCart(addToCart4, "Premium Yellow Bananas", "$1.99");
        });

        productRow4.getChildren().addAll(imagePlaceholder4, productDetails4, addToCart4);
        card4.getChildren().add(productRow4);

        // Hover effect for card4
        card4.setOnMouseEntered(e -> {
            card4.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #FFD8C4;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1.5;"
            );
            DropShadow hoverShadow = new DropShadow();
            hoverShadow.setRadius(12);
            hoverShadow.setOffsetY(4);
            hoverShadow.setSpread(0.04);
            hoverShadow.setColor(Color.rgb(0, 0, 0, 0.10));
            card4.setEffect(hoverShadow);
        });

        card4.setOnMouseExited(e -> {
            card4.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #EAE6EC;" +
                    "-fx-border-radius: 12;" +
                    "-fx-border-width: 1;"
            );
            card4.setEffect(cardShadow3);
        });

        // =====================================================
        // PRODUCTS GRID LAYOUT (SIDE BY SIDE)
        // =====================================================

        FlowPane productsGrid = new FlowPane(15, 15);
        productsGrid.setAlignment(Pos.TOP_LEFT);
        productsGrid.setPadding(new Insets(10, 0, 10, 0));
        productsGrid.setStyle("-fx-background-color: transparent;");
        productsGrid.setPrefWrapLength(1400);
        productsGrid.getChildren().addAll(card1, card2, card3, card4);

        // =====================================================
        // LOAD MORE BUTTON
        // =====================================================

        Button loadMore = new Button("Load More Items");
        loadMore.setPrefWidth(200);
        loadMore.setPrefHeight(42);
        loadMore.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1.5;" +
                "-fx-cursor: hand;"
        );
        loadMore.setEffect(cardShadow3);
         loadMore.setOnMouseEntered(e -> {
            loadMore.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-color: #FF6900;" +
                    "-fx-border-radius: 20;" +
                    "-fx-border-width: 1.5;" +
                    "-fx-cursor: hand;"
            );
        });

        loadMore.setOnMouseExited(e -> {
            loadMore.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #FF6900;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-color: #FF6900;" +
                    "-fx-border-radius: 20;" +
                    "-fx-border-width: 1.5;" +
                    "-fx-cursor: hand;"
            );
        });

        HBox loadMoreContainer = new HBox(loadMore);
        loadMoreContainer.setAlignment(Pos.CENTER);
        loadMoreContainer.setPadding(new Insets(20, 0, 20, 0));

        // =====================================================
        // ADD ALL TO CONTENT
        // =====================================================

        content.getChildren().addAll(navBox,hbright, categorySection,
                productsGrid,
                deliveryBanner,
                loadMoreContainer
        );
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // =====================================================
        // RIGHT VBOX FINAL
        // =====================================================

        VBox Rightvbox = new VBox(0);
        Rightvbox.setPadding(new Insets(0, 0, 0, 0));
        Rightvbox.setStyle("-fx-background-color: #F7F5F8;");
        Rightvbox.getChildren().add(scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // =====================================================
        // BORDER PANE
        // =====================================================

        BorderPane mainBox = new BorderPane();
        mainBox.setLeft(leftBox);
        mainBox.setCenter(Rightvbox);
        
        mainBox.setStyle("-fx-background-color: #F7F5F8;");

        // =====================================================
        // SCENE
        // =====================================================

        Scene sc = new Scene(mainBox, 1500, 800);

        GrocriesScene=sc;


                return GrocriesScene;
        }

   

    
}

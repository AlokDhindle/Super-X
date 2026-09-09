package com.kryox.view.Customer;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.model.Shopkeeper.ProductModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public class DealsDB {

    public String userId;
    private Scene dealsScene;
    private Runnable currentCallback;

    public DealsDB(String userId) {
        this.userId = (userId != null && !userId.isBlank()) ? userId : "guest";
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);

        if (url == null) {
            System.err.println("❌ Resource not found: " + path);
            return new Image(
                "data:image/png;base64,"
                + "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII="
            );
        }

        return new Image(url.toExternalForm());
    }

    public Scene getDealScene(Runnable callbacktodashboar) {
        this.currentCallback = callbacktodashboar;

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

        Image di = loadImage("/assets/images/Dashbord/dashboard.png");
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
        lefButton1.setOnAction(event -> {
            if (callbacktodashboar != null) {
                callbacktodashboar.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
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
        hbInDashboard.setOnMouseClicked(e -> lefButton1.fire());

        Image di2 = loadImage("/assets/images/store.png");
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
        hbInDashboard2.setOnMouseClicked(e -> lefButton2.fire());

        Image di3 = loadImage("/assets/images/Dashbord/hot-sale.png");
        ImageView div3 = new ImageView(di3);
        div3.setFitHeight(23);
        div3.setFitWidth(23);
        div3.setPreserveRatio(true);

        Button lefButton3 = new Button("Deals");
        lefButton3.setPrefWidth(125);
        lefButton3.setPrefHeight(38);
        lefButton3.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 12px;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-weight: bold;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-cursor: hand;"
        );
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

        Image di4 = loadImage("/assets/images/Dashbord/package.png");
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
        lefButton4.setOnAction(event -> CustomerNavigation.navigateToOrders(userId));

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
        hbInDashboard4.setOnMouseClicked(e -> lefButton4.fire());

        Image di5 = loadImage("/assets/images/Dashbord/line-chart.png");
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
        lefButton5.setOnAction(e -> CustomerNavigation.navigateToAnalytics(userId));

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
        hbInDashboard5.setOnMouseClicked(e -> lefButton5.fire());

        VBox upgradeCard = CustomerPlanUpgradeCard.createUpgradeCard(userId);

        Image di6 = loadImage("/assets/images/Dashbord/category.png");
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
        lefButton6.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

        HBox hbInDashboard6 = new HBox(10, div6, lefButton6);
        hbInDashboard6.setPrefWidth(205);
        hbInDashboard6.setMinWidth(205);
        hbInDashboard6.setMaxWidth(205);
        hbInDashboard6.setPrefHeight(34);
        hbInDashboard6.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard6.setPadding(new Insets(0, 8, 0, 18));
        hbInDashboard6.setOnMouseClicked(e -> lefButton6.fire());

        Image di7 = loadImage("/assets/images/Dashbord/question.png");
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
        lefButton7.setOnAction(e -> CustomerNavigation.navigateToHelp(userId));

        HBox hbInDashboard7 = new HBox(10, div7, lefButton7);
        hbInDashboard7.setPrefWidth(205);
        hbInDashboard7.setMinWidth(205);
        hbInDashboard7.setMaxWidth(205);
        hbInDashboard7.setPrefHeight(34);
        hbInDashboard7.setAlignment(Pos.CENTER_LEFT);
        hbInDashboard7.setPadding(new Insets(0, 8, 0, 18));
        hbInDashboard7.setOnMouseClicked(e -> lefButton7.fire());

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
                hbInDashboard7
        );

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
                "-fx-prompt-text-fill: #999999;"
        );

        t1.setOnAction(e -> {
            searchBox.setText("");
        });

        Label locationIcon = new Label("📍");
        Label locationText = new Label("Downtown Manhattan⌄");
        locationText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #555555;"
        );

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

        b1.setOnAction(e -> CustomerNavigation.navigateToNotifications(userId));
        b2.setOnAction(e -> CustomerNavigation.navigateToCart(userId));
        b3.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

        HBox actionBox = new HBox(7, b1, b2, b3);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        Region navSpacer1 = new Region();
        HBox.setHgrow(navSpacer1, Priority.ALWAYS);
        Region navSpacer2 = new Region();
        HBox.setHgrow(navSpacer2, Priority.ALWAYS);

        HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
        navBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(searchBox, Priority.ALWAYS);
        searchBox.setMaxWidth(Double.MAX_VALUE);
        navBox.setPrefHeight(68);
        navBox.setPadding(new Insets(12, 24, 12, 24));
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setStyle(
                "-fx-background-color:#ebccb7;" +
                "-fx-border-width:0;"
        );

        BorderPane topPane = new BorderPane();
        topPane.setTop(navBox);
        topPane.setStyle("-fx-background-color:#ebccb7;");
        topPane.setPrefHeight(68);

        VBox Rightvbox = new VBox();
        Rightvbox.setFillWidth(true);
        Rightvbox.setMaxWidth(Double.MAX_VALUE);
        Rightvbox.getChildren().add(topPane);

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

        HBox mainContent = new HBox(24);
        mainContent.setAlignment(Pos.TOP_LEFT);
        mainContent.setPadding(new Insets(24));
        mainContent.setFillHeight(true);

        Label filterTitle = new Label("Filters");
        filterTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Separator separatorSort = new Separator();
        Separator separator1 = new Separator();
        Separator separator2 = new Separator();
        Separator separator3 = new Separator();

        // 🔘 SORT BY SECTION (Visible & Interactive)
        Label sortSectionLabel = new Label("Sort By");
        sortSectionLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        ComboBox<String> sortDropdown = new ComboBox<>();
        sortDropdown.getItems().addAll(
                "🔥 Highest Discount",
                "💰 Price: Low to High",
                "💎 Price: High to Low",
                "🔤 Name: A to Z",
                "🎁 Biggest Savings"
        );
        sortDropdown.setValue("🔥 Highest Discount");
        sortDropdown.setPrefWidth(190);
        sortDropdown.setStyle(
                "-fx-background-color: #FFF6EE;" +
                "-fx-border-color: #FF7000;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;" +
                "-fx-cursor: hand;"
        );

        Label activeSortBadge = new Label("⚡ Active: Highest Discount");
        activeSortBadge.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E87500;" +
                "-fx-background-color: #FFF2E9;" +
                "-fx-padding: 3 8 3 8;" +
                "-fx-background-radius: 6;"
        );

        // 🏷️ CATEGORY SECTION (High-Contrast, Visible Labels)
        Label categoryLabel = new Label("Category");
        categoryLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        CheckBox grocery = createFilterCheckBox("🛒  Grocery");
        CheckBox electronics = createFilterCheckBox("⚡  Electronics");
        CheckBox homeKitchen = createFilterCheckBox("🏠  Home & Kitchen");
        CheckBox fashion = createFilterCheckBox("👕  Fashion");

        Label priceLabel = new Label("Price Range");
        priceLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label priceValueLabel = new Label("Up to ₹1000");
        priceValueLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #FF6900; -fx-font-weight: bold;");

        Slider slider = new Slider(0, 1000, 1000);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);

        // 🏷️ DISCOUNT SECTION (High-Contrast, Visible Labels)
        Label discountLabel = new Label("Discount");
        discountLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        CheckBox tenPercent = createFilterCheckBox("🏷️  10% or more");
        CheckBox thirtyPercent = createFilterCheckBox("🔥  30% or more");
        CheckBox fiftyPercent = createFilterCheckBox("💥  50% or more");

        Button resetButton = new Button("Reset Filters");
        resetButton.setStyle(
                "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #FFBD95;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        );

        VBox filterBox = new VBox(10);
        VBox contentBox = new VBox(22);

        mainContent.getChildren().addAll(filterBox, contentBox);
        contentBox.setPrefWidth(920);
        contentBox.setMinWidth(920);
        contentBox.setMaxWidth(920);

        filterBox.setPrefWidth(230);
        filterBox.setMinWidth(230);
        filterBox.setMaxWidth(230);
        filterBox.setPadding(new Insets(18));
        filterBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 2);"
        );

        filterBox.getChildren().addAll(
                filterTitle,
                separatorSort,
                sortSectionLabel,
                sortDropdown,
                activeSortBadge,
                separator1,
                categoryLabel,
                grocery,
                electronics,
                homeKitchen,
                fashion,
                separator2,
                priceLabel,
                priceValueLabel,
                slider,
                separator3,
                discountLabel,
                tenPercent,
                thirtyPercent,
                fiftyPercent,
                resetButton
        );

        // HERO BANNER
        VBox heroTextVBox = new VBox(5);
        heroTextVBox.setPrefWidth(390);
        heroTextVBox.setMinWidth(390);
        heroTextVBox.setMaxWidth(390);
        heroTextVBox.setAlignment(Pos.CENTER_LEFT);
        heroTextVBox.setPadding(new Insets(20, 10, 20, 30));

        Label heroTitle = new Label("Exclusive\nSuper-Saver\nDeals Bundle");
        heroTitle.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label heroDesc = new Label("Dynamic hyper-local deals & verified discounts delivered to your door.");
        heroDesc.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: white;"
        );

        heroTextVBox.getChildren().addAll(heroTitle, heroDesc);
        HBox heroHBox = new HBox();
        heroHBox.setStyle(
                "-fx-background-color: #D56E18;" +
                "-fx-background-radius: 18;"
        );

        Image heroImg = loadImage("/assets/images/image.png");
        ImageView heroImageView = new ImageView(heroImg);
        heroImageView.setFitWidth(480);
        heroImageView.setFitHeight(200);
        heroImageView.setPreserveRatio(true);
        heroImageView.setSmooth(true);

        heroHBox.setPrefWidth(920);
        heroHBox.setMinWidth(920);
        heroHBox.setMaxWidth(920);
        heroHBox.setPrefHeight(210);
        heroHBox.setMinHeight(210);
        heroHBox.setMaxHeight(210);
        heroHBox.setAlignment(Pos.CENTER_LEFT);
        heroHBox.setPadding(new Insets(0));

        heroHBox.getChildren().addAll(heroTextVBox, heroImageView);
        contentBox.getChildren().add(heroHBox);

        // 🔥 SPECIAL CAMPAIGNS (Dynamic Approved Marketplace Campaigns)
        VBox specialCampaigns = CustomerCampaignSection.createSpecialCampaignsSection(
                userId,
                () -> Homepage.HomepageStage.setScene(getDealScene(currentCallback))
        );
        contentBox.getChildren().add(specialCampaigns);

        // ACTIVE OFFERS & PROMO CODES BANNER
        VBox offersSection = createOffersBannerSection();
        contentBox.getChildren().add(offersSection);

        // DISCOUNTS HEADER
        HBox discountHeader = new HBox(12);
        discountHeader.setAlignment(Pos.CENTER_LEFT);
        discountHeader.setPrefWidth(920);

        Label discountTitle = new Label("Today's Best Discounts");
        discountTitle.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        Label dealsCountLabel = new Label(" (Loading...)");
        dealsCountLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        HBox titleBox = new HBox(discountTitle, dealsCountLabel);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Region discountSpacer = new Region();
        HBox.setHgrow(discountSpacer, Priority.ALWAYS);

        // Header Quick Sort Box
        HBox headerSortBox = new HBox(6);
        headerSortBox.setAlignment(Pos.CENTER_LEFT);
        Label headerSortPrompt = new Label("Sort:");
        headerSortPrompt.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #666666;");

        ComboBox<String> headerSortCombo = new ComboBox<>();
        headerSortCombo.getItems().addAll(
                "🔥 Highest Discount",
                "💰 Price: Low to High",
                "💎 Price: High to Low",
                "🔤 Name: A to Z",
                "🎁 Biggest Savings"
        );
        headerSortCombo.setValue("🔥 Highest Discount");
        headerSortCombo.setPrefWidth(165);
        headerSortCombo.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #FFCBA4;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;" +
                "-fx-cursor: hand;"
        );
        headerSortBox.getChildren().addAll(headerSortPrompt, headerSortCombo);

        Button viewAll = new Button("View All →");
        viewAll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        discountHeader.getChildren().addAll(
                titleBox,
                discountSpacer,
                headerSortBox,
                viewAll
        );

        // DYNAMIC PRODUCT GRID
        FlowPane productCardsGrid = new FlowPane();
        productCardsGrid.setHgap(18);
        productCardsGrid.setVgap(18);
        productCardsGrid.setPrefWrapLength(920);
        productCardsGrid.setAlignment(Pos.TOP_LEFT);

        contentBox.getChildren().addAll(
                discountHeader,
                productCardsGrid
        );

        Rightvbox.getChildren().add(mainContent);

        // LOAD DATA & ATTACH DYNAMIC FILTER LISTENERS
        List<ProductModel> allProducts = loadAllDealsProducts();

        Runnable filterUpdater = () -> applyFilters(
                allProducts,
                productCardsGrid,
                searchBox,
                grocery, electronics, homeKitchen, fashion,
                slider, priceValueLabel,
                tenPercent, thirtyPercent, fiftyPercent,
                sortDropdown,
                dealsCountLabel
        );

        // Synchronize Sidebar Sort & Header Sort Dropdowns
        sortDropdown.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(headerSortCombo.getValue())) {
                headerSortCombo.setValue(newVal);
            }
            if (newVal != null) {
                String cleanName = newVal.replaceAll("[^a-zA-Z0-9 :\\(\\)]", "").trim();
                activeSortBadge.setText("⚡ " + cleanName);
            }
            filterUpdater.run();
        });

        headerSortCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.equals(sortDropdown.getValue())) {
                sortDropdown.setValue(newVal);
            }
        });

        searchBox.textProperty().addListener((obs, oldVal, newVal) -> filterUpdater.run());
        searchBox.setOnAction(e -> {
            filterUpdater.run();
            String q = searchBox.getText() != null ? searchBox.getText().trim() : "";
            if (!q.isEmpty() && productCardsGrid.getChildren().isEmpty()) {
                CustomerNavigation.performSearch(userId, q, () -> CustomerNavigation.navigateToDeals(userId));
            }
        });
        slider.valueProperty().addListener((obs, oldVal, newVal) -> filterUpdater.run());

        grocery.setOnAction(e -> filterUpdater.run());
        electronics.setOnAction(e -> filterUpdater.run());
        homeKitchen.setOnAction(e -> filterUpdater.run());
        fashion.setOnAction(e -> filterUpdater.run());

        tenPercent.setOnAction(e -> filterUpdater.run());
        thirtyPercent.setOnAction(e -> filterUpdater.run());
        fiftyPercent.setOnAction(e -> filterUpdater.run());

        resetButton.setOnAction(e -> {
            grocery.setSelected(false);
            electronics.setSelected(false);
            homeKitchen.setSelected(false);
            fashion.setSelected(false);

            tenPercent.setSelected(false);
            thirtyPercent.setSelected(false);
            fiftyPercent.setSelected(false);

            slider.setValue(1000);
            searchBox.setText("");
            sortDropdown.setValue("🔥 Highest Discount");
            headerSortCombo.setValue("🔥 Highest Discount");
            activeSortBadge.setText("⚡ Active: Highest Discount");
            filterUpdater.run();
        });

        viewAll.setOnAction(e -> resetButton.fire());

        // Run initial filter to populate dynamic cards
        filterUpdater.run();

        ScrollPane centerScrollPane = new ScrollPane(Rightvbox);
        centerScrollPane.setFitToWidth(true);
        centerScrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        mainBox.setCenter(centerScrollPane);

        dealsScene = new Scene(mainBox, 1500, 800);
        return dealsScene;
    }

    private List<ProductModel> loadAllDealsProducts() {
        List<ProductModel> products = new ArrayList<>();
        try {
            ProductController controller = new ProductController();
            ArrayList<ProductModel> fetched = controller.fetchProducts();
            if (fetched != null && !fetched.isEmpty()) {
                for (ProductModel pm : fetched) {
                    if (pm != null) {
                        products.add(pm);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("DealsDB fetch error: " + e.getMessage());
        }

        // Fallback sample deal products if database has no entries yet
        if (products.isEmpty()) {
            products.add(new ProductModel("d1", "Artisan Sourdough Bread", "Grocery", "Bakery", "Freshly baked sourdough, local ingredients", "SKU1", "BC1", 9.99, 4.00, 5.99, 40.0, 0.0, 50, 5, "pcs", "Active", "B1", "No", "2026-01-01", "2026-09-10", "/assets/images/img1.png"));
            products.add(new ProductModel("d2", "Organic Hass Avocado", "Grocery", "FarmFresh", "Pack of 4, Farm direct fresh organic avocados", "SKU2", "BC2", 8.65, 3.50, 6.49, 25.0, 0.0, 40, 5, "pack", "Active", "B2", "No", "2026-01-01", "2026-09-15", "/assets/images/avocado.png"));
            products.add(new ProductModel("d3", "SonicPro Wireless Headphones", "Electronics", "SonicPro", "Over-ear bluetooth, 30h battery life", "SKU3", "BC3", 199.00, 50.00, 89.00, 55.0, 0.0, 30, 2, "unit", "Active", "B3", "No", "2026-01-01", "2028-09-01", "/assets/images/headphone.png"));
            products.add(new ProductModel("d4", "Smart Fitness Watch Ultra", "Electronics", "TechFit", "Heart rate monitor, GPS, water resistant", "SKU4", "BC4", 149.99, 45.00, 79.99, 46.0, 0.0, 25, 3, "unit", "Active", "B4", "No", "2026-01-01", "2028-01-01", "/assets/images/Dashbord/dashboard.png"));
            products.add(new ProductModel("d5", "Raw Organic Forest Honey", "Grocery", "NatureBest", "100% pure raw unprocessed wildflower honey", "SKU5", "BC5", 14.99, 5.00, 9.99, 33.0, 0.0, 60, 10, "jar", "Active", "B5", "No", "2026-01-01", "2027-12-01", "/assets/images/image.png"));
            products.add(new ProductModel("d6", "Ergonomic Office Chair", "Home & Kitchen", "FlexiSeat", "High back lumbar support mesh desk chair", "SKU6", "BC6", 249.00, 90.00, 159.00, 36.0, 0.0, 15, 2, "unit", "Active", "B6", "No", "2026-01-01", "2029-01-01", "/assets/images/store.png"));
            products.add(new ProductModel("d7", "Designer Cotton Casual Hoodie", "Fashion", "UrbanStyle", "Premium fleece lining comfortable street hoodie", "SKU7", "BC7", 59.99, 15.00, 34.99, 41.0, 0.0, 45, 5, "unit", "Active", "B7", "No", "2026-01-01", "2029-01-01", "/assets/images/img1.png"));
            products.add(new ProductModel("d8", "Stainless Steel Air Fryer 5.5L", "Home & Kitchen", "ChefPro", "Rapid air circulation digital touch control", "SKU8", "BC8", 129.99, 40.00, 74.99, 42.0, 0.0, 20, 4, "unit", "Active", "B8", "No", "2026-01-01", "2028-01-01", "/assets/images/avocado.png"));
        }

        return products;
    }

    private CheckBox createFilterCheckBox(String text) {
        CheckBox cb = new CheckBox(text);
        cb.setStyle(
                "-fx-font-family: 'Montserrat', sans-serif;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #2B2B2B;" +
                "-fx-cursor: hand;"
        );
        return cb;
    }

    private static class FilteredDealItem {
        ProductModel product;
        double dealPrice;
        double originalMrp;
        double discPercent;
        double savings;

        FilteredDealItem(ProductModel product, double dealPrice, double originalMrp, double discPercent) {
            this.product = product;
            this.dealPrice = dealPrice;
            this.originalMrp = originalMrp;
            this.discPercent = discPercent;
            this.savings = Math.max(0, originalMrp - dealPrice);
        }
    }

    private void applyFilters(
            List<ProductModel> allProducts,
            FlowPane productCardsGrid,
            TextField searchBox,
            CheckBox grocery, CheckBox electronics, CheckBox homeKitchen, CheckBox fashion,
            Slider slider, Label priceValueLabel,
            CheckBox tenPercent, CheckBox thirtyPercent, CheckBox fiftyPercent,
            ComboBox<String> sortDropdown,
            Label productCountLabel
    ) {
        String query = searchBox.getText() == null ? "" : searchBox.getText().trim().toLowerCase();
        double maxPrice = slider.getValue();

        priceValueLabel.setText(String.format("Up to ₹%.0f", maxPrice));

        boolean catGrocery = grocery.isSelected();
        boolean catElec = electronics.isSelected();
        boolean catHome = homeKitchen.isSelected();
        boolean catFashion = fashion.isSelected();
        boolean anyCatSelected = catGrocery || catElec || catHome || catFashion;

        boolean d10 = tenPercent.isSelected();
        boolean d30 = thirtyPercent.isSelected();
        boolean d50 = fiftyPercent.isSelected();

        productCardsGrid.getChildren().clear();
        List<FilteredDealItem> matchedDeals = new ArrayList<>();

        for (ProductModel p : allProducts) {
            double rawPrice = p.getSellingPrice() != null ? p.getSellingPrice() : 0.0;
            double rawMrp = p.getMrp() != null ? p.getMrp() : rawPrice;
            Double disc = p.getDiscount();

            double discPercent = 0.0;
            double dealPrice = rawPrice;
            double originalMrp = rawMrp;

            if (disc != null && disc > 0) {
                discPercent = disc;
                if (rawMrp > rawPrice && rawPrice > 0) {
                    originalMrp = rawMrp;
                    dealPrice = rawPrice;
                } else {
                    originalMrp = rawMrp > 0 ? rawMrp : rawPrice;
                    dealPrice = Math.round(originalMrp * (1.0 - (discPercent / 100.0)) * 100.0) / 100.0;
                }
            } else if (rawMrp > rawPrice && rawPrice > 0) {
                discPercent = Math.round(((rawMrp - rawPrice) / rawMrp) * 100.0);
                originalMrp = rawMrp;
                dealPrice = rawPrice;
            } else {
                // Default featured deal discount for Deals section
                discPercent = 15.0;
                originalMrp = rawMrp > 0 ? rawMrp : (rawPrice > 0 ? rawPrice : 100.0);
                dealPrice = Math.round(originalMrp * 0.85 * 100.0) / 100.0;
            }

            if (dealPrice > maxPrice) continue;

            if (!query.isEmpty()) {
                String nameStr = p.getProductName() == null ? "" : p.getProductName().toLowerCase();
                String catStr = p.getCategory() == null ? "" : p.getCategory().toLowerCase();
                String brandStr = p.getBrand() == null ? "" : p.getBrand().toLowerCase();
                String descStr = p.getDescriptionValue() == null ? "" : p.getDescriptionValue().toLowerCase();

                if (!nameStr.contains(query) && !catStr.contains(query) && !brandStr.contains(query) && !descStr.contains(query)) {
                    continue;
                }
            }

            if (anyCatSelected) {
                String cat = p.getCategory() == null ? "" : p.getCategory().toLowerCase();
                boolean matchesCat = false;
                if (catGrocery && (cat.contains("groc") || cat.contains("food") || cat.contains("bakery"))) matchesCat = true;
                if (catElec && (cat.contains("elec") || cat.contains("tech") || cat.contains("audio"))) matchesCat = true;
                if (catHome && (cat.contains("home") || cat.contains("kitch") || cat.contains("furnit"))) matchesCat = true;
                if (catFashion && (cat.contains("fash") || cat.contains("cloth") || cat.contains("wear"))) matchesCat = true;

                if (!matchesCat) continue;
            }

            if (d50 && discPercent < 50) continue;
            if (d30 && discPercent < 30) continue;
            if (d10 && discPercent < 10) continue;

            matchedDeals.add(new FilteredDealItem(p, dealPrice, originalMrp, discPercent));
        }

        // Apply Sorting based on selected criteria
        String sortOption = (sortDropdown != null && sortDropdown.getValue() != null)
                ? sortDropdown.getValue()
                : "🔥 Highest Discount";

        if (sortOption.contains("Low to High")) {
            matchedDeals.sort((a, b) -> Double.compare(a.dealPrice, b.dealPrice));
        } else if (sortOption.contains("High to Low")) {
            matchedDeals.sort((a, b) -> Double.compare(b.dealPrice, a.dealPrice));
        } else if (sortOption.contains("Name")) {
            matchedDeals.sort((a, b) -> {
                String n1 = a.product.getProductName() != null ? a.product.getProductName() : "";
                String n2 = b.product.getProductName() != null ? b.product.getProductName() : "";
                return n1.compareToIgnoreCase(n2);
            });
        } else if (sortOption.contains("Savings")) {
            matchedDeals.sort((a, b) -> Double.compare(b.savings, a.savings));
        } else {
            // Default "🔥 Highest Discount"
            matchedDeals.sort((a, b) -> Double.compare(b.discPercent, a.discPercent));
        }

        for (FilteredDealItem item : matchedDeals) {
            VBox card = createProductCard(item.product, item.dealPrice, item.originalMrp, item.discPercent);
            productCardsGrid.getChildren().add(card);
        }

        int count = matchedDeals.size();
        if (productCountLabel != null) {
            productCountLabel.setText(" (" + count + " Deals Available)");
        }

        if (count == 0) {
            VBox emptyBox = new VBox(10);
            emptyBox.setAlignment(Pos.CENTER);
            emptyBox.setPrefWidth(900);
            emptyBox.setPadding(new Insets(40));

            Label emptyLabel = new Label("🔍 No deals match your criteria.");
            emptyLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #888888;");
            Label emptySub = new Label("Try adjusting search terms, price slider, or discount filters.");
            emptySub.setStyle("-fx-font-size: 12px; -fx-text-fill: #AAAAAA;");

            emptyBox.getChildren().addAll(emptyLabel, emptySub);
            productCardsGrid.getChildren().add(emptyBox);
        }
    }

    private VBox createProductCard(ProductModel p, double dealPrice, double originalMrp, double discPercent) {
        VBox card = new VBox(7);
        card.setPrefWidth(280);
        card.setMinWidth(280);
        card.setMaxWidth(280);
        card.setPrefHeight(300);
        card.setMinHeight(300);
        card.setMaxHeight(300);
        card.setPadding(new Insets(10));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);" +
                "-fx-cursor: hand;"
        );

        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(232, 117, 0, 0.22), 14, 0, 0, 4);" +
                "-fx-cursor: hand;"
        ));
        card.setOnMouseExited(e -> card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);" +
                "-fx-cursor: hand;"
        ));

        Image productImg;
        if (p.getImageUrl() != null && !p.getImageUrl().isBlank()) {
            try {
                if (p.getImageUrl().startsWith("http")) {
                    productImg = new Image(p.getImageUrl(), 260, 135, true, true, true);
                } else {
                    productImg = loadImage(p.getImageUrl());
                }
            } catch (Exception ex) {
                productImg = loadImage("/assets/images/img1.png");
            }
        } else {
            productImg = loadImage("/assets/images/img1.png");
        }

        ImageView productView = new ImageView(productImg);
        productView.setFitWidth(260);
        productView.setFitHeight(135);
        productView.setPreserveRatio(true);
        productView.setSmooth(true);

        Label discountBadge = new Label(String.format("-%.0f%%", discPercent));
        discountBadge.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 7 4 7;" +
                "-fx-background-radius: 5;"
        );

        Label rating = new Label("★ 4.8 (Deal)");
        rating.setStyle("-fx-font-size: 9px; -fx-text-fill: #FF9800; -fx-font-weight: bold;");

        Region topSpacer = new Region();
        HBox topCardRow = new HBox(discountBadge, topSpacer, rating);
        HBox.setHgrow(topSpacer, Priority.ALWAYS);
        topCardRow.setAlignment(Pos.CENTER_LEFT);

        Label productName = new Label(p.getProductName() == null ? "Product Deal" : p.getProductName());
        productName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label shopLabel = new Label();
        shopLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #B84200;");
        CustomerShopResolver.bindShopName(shopLabel, p.getShopkeeperUid());

        Label productDesc = new Label(p.getDescriptionValue() == null ? "Exclusive offer discount" : p.getDescriptionValue());
        productDesc.setStyle("-fx-font-size: 9px; -fx-text-fill: #777777;");

        Label priceLabel = new Label(String.format("₹%.2f", dealPrice));
        priceLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #FF6900;");

        Label oldPriceLabel = new Label(originalMrp > dealPrice ? String.format("₹%.2f", originalMrp) : "");
        oldPriceLabel.setStyle("-fx-font-size: 9px; -fx-text-fill: #999999; -fx-strikethrough: true;");

        Button cartBtn = new Button("🛒 Add");
        cartBtn.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-background-radius: 12;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 10 4 10;" +
                "-fx-font-size: 10px;" +
                "-fx-cursor: hand;"
        );

        cartBtn.setOnAction(e -> {
            CARTcontroller cl = new CARTcontroller();
            cl.addTocart(userId, p.getProductName(), dealPrice, p.getCategory(), 1, p.getShopkeeperUid());
            cartBtn.setText("✓ Added!");
            cartBtn.setStyle(
                    "-fx-background-color: #4CAF50;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 4 10 4 10;" +
                    "-fx-font-size: 10px;"
            );
        });

        Region priceSpacer = new Region();
        HBox priceBox = new HBox(6, priceLabel, oldPriceLabel, priceSpacer, cartBtn);
        HBox.setHgrow(priceSpacer, Priority.ALWAYS);
        priceBox.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().addAll(productView, topCardRow, productName, shopLabel, productDesc, priceBox);

        card.setOnMouseClicked(e -> {
            if (e.getTarget() instanceof javafx.scene.Node) {
                javafx.scene.Node target = (javafx.scene.Node) e.getTarget();
                while (target != null && target != card) {
                    if (target instanceof Button) {
                        return;
                    }
                    target = target.getParent();
                }
            }

            ProductModel dealProduct = new ProductModel(
                    p.getProductId(), p.getProductName(), p.getCategory(), p.getBrand(),
                    p.getDescriptionValue(), p.getSku(), p.getBarcode(), originalMrp,
                    p.getCostPrice(), dealPrice, discPercent, p.getTax(),
                    p.getStockQuantity(), p.getLowStockLimit(), p.getUnit(), p.getStatus(),
                    p.getBatchNumber(), p.getExpiryTracking(), p.getManufacturingDate(),
                    p.getExpiryDate(), p.getImageUrl()
            );
            dealProduct.setShopkeeperUid(p.getShopkeeperUid());

            CustomerProductDetails details = new CustomerProductDetails(userId, dealProduct, () -> {
                Homepage.HomepageStage.setScene(getDealScene(currentCallback));
            });
            Homepage.HomepageStage.setScene(details.getScene());
        });

        return card;
    }

    private VBox createOffersBannerSection() {
        VBox container = new VBox(10);
        container.setPrefWidth(920);

        HBox titleRow = new HBox(8);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("🏷️ Active Shopkeeper Offers & Promo Codes");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1E1E24;");

        Label subtitle = new Label("• Tap 'Apply to Cart' to lock in your discount!");
        subtitle.setStyle("-fx-font-size: 11px; -fx-text-fill: #E87500; -fx-font-weight: bold;");

        titleRow.getChildren().addAll(title, subtitle);

        HBox cardsBox = new HBox(12);
        cardsBox.setAlignment(Pos.CENTER_LEFT);

        List<OfferModel> offers = CartOfferManager.getAvailableOffers();
        Label feedbackLabel = new Label("");
        feedbackLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        for (OfferModel offer : offers) {
            VBox offerCard = new VBox(6);
            offerCard.setPadding(new Insets(12, 14, 12, 14));
            offerCard.setPrefWidth(215);
            offerCard.setMinWidth(215);
            offerCard.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: #FFD4BC;" +
                    "-fx-border-width: 1.2;" +
                    "-fx-border-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 6, 0, 0, 2);"
            );

            HBox top = new HBox(6);
            top.setAlignment(Pos.CENTER_LEFT);

            Label codeLabel = new Label(offer.getPromoCode());
            codeLabel.setStyle(
                    "-fx-background-color: #FFF0E6;" +
                    "-fx-text-fill: #FF6900;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 11px;" +
                    "-fx-padding: 3 7 3 7;" +
                    "-fx-background-radius: 6;" +
                    "-fx-border-color: #FFBD95;" +
                    "-fx-border-radius: 6;"
            );

            Region sp = new Region();
            HBox.setHgrow(sp, Priority.ALWAYS);

            String discText = (offer.getDiscountType() != null && offer.getDiscountType().toLowerCase().contains("percent"))
                    ? String.format("%.0f%% OFF", offer.getDiscountValue())
                    : String.format("₹%.0f OFF", offer.getDiscountValue());
            Label discLabel = new Label(discText);
            discLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: 900; -fx-text-fill: #2E7D32;");

            top.getChildren().addAll(codeLabel, sp, discLabel);

            Label nameLabel = new Label(offer.getOfferName());
            nameLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #333333;");
            nameLabel.setWrapText(true);

            Label catLabel = new Label("Category: " + (offer.getCategory() != null ? offer.getCategory() : "General"));
            catLabel.setStyle("-fx-font-size: 9px; -fx-text-fill: #888888;");

            Button applyBtn = new Button("Apply to Cart");
            applyBtn.setMaxWidth(Double.MAX_VALUE);
            applyBtn.setPrefHeight(26);
            applyBtn.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-cursor: hand;"
            );

            applyBtn.setOnAction(e -> {
                CartOfferManager.setPreappliedCode(offer.getPromoCode());
                applyBtn.setText("✓ Applied!");
                applyBtn.setStyle(
                        "-fx-background-color: #2E7D32;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;"
                );
                feedbackLabel.setText("🎉 Offer '" + offer.getPromoCode() + "' (" + discText + ") applied to your cart! Go to Cart 🛒 to view discount.");
            });

            offerCard.getChildren().addAll(top, nameLabel, catLabel, applyBtn);
            cardsBox.getChildren().add(offerCard);
        }

        ScrollPane scroll = new ScrollPane(cardsBox);
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");

        container.getChildren().addAll(titleRow, scroll, feedbackLabel);
        return container;
    }
}
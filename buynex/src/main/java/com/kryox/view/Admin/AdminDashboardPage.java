package com.kryox.view.Admin;

import java.util.List;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.dao.Shopkeeper.ShopkeeperDAO;
import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminDashboardPage {

        private Scene dashboardScene;

        public Scene getUserScene() {

                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color: #eee5df;");

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
                                        "-fx-background-color:#D94F00;" +
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
                                        "-fx-background-color:transparent;" +
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

                settings.setOnMouseEntered(e -> {
                        settings.setStyle(
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
                settings.setOnMouseClicked(event ->{
                       SettingsPage setting = new SettingsPage();
                       Homepage.HomepageStage.setScene(setting.getUserScene()); 
                });

                // =========================
                // SUPPORT HOVER
                // =========================

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
                support.setOnMouseClicked(event ->{
                        SupportPage supports = new SupportPage();
                        Homepage.HomepageStage.setScene(supports.getUserScene());

                });

                // =========================
                // PROFILE
                // =========================

                AdminProfileCard adminProfileCard = new AdminProfileCard();
                HBox profile = adminProfileCard.getProfileCard();

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

                // =========================
                // CENTER
                // =========================

                StackPane centerWrapper = new StackPane();

                VBox center = new VBox();
                center.setSpacing(20);
                center.setPadding(new Insets(25));
                center.setMaxWidth(1120);
                center.setMaxHeight(Double.MAX_VALUE);
                center.setStyle("-fx-background-color: #eee5df;");

                // =========================
                // TOP
                // =========================

                HBox top = new HBox();
                top.setAlignment(Pos.CENTER_LEFT);
                top.setSpacing(20);

                Text searchIcn = new Text("⌕");
                TextField search = new TextField();
                search.setPromptText(
                                "Search orders, shops, or users...");
                search.setPrefWidth(340);
                search.setPrefHeight(42);
                search.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:20;" +
                                                "-fx-font-size:14px;");

                Image aiChatbotImage = new Image(getClass().getResource("/assets/images/admin/message.png").toExternalForm());
                ImageView iv8 = new ImageView(aiChatbotImage);
                iv8.setFitWidth(24);
                iv8.setFitHeight(24);
                iv8.setPreserveRatio(true);
                // Icons 
                iv8.setOnMouseClicked(e -> {
                        SmartAssistantUI chatPage = new SmartAssistantUI();

                        Homepage.HomepageStage.setScene(
                                        chatPage.getUserScene());
                });

                Image notification = new Image(getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView iv9 = new ImageView(notification);
                iv9.setFitWidth(24);
                iv9.setFitHeight(24);
                iv9.setPreserveRatio(true);

                Popup notificationPopup = new Popup();

                Text notificationTitle = new Text("Notifications");
                notificationTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                Button markRead = new Button("Mark all as read");

                markRead.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E65300;" +
                                                "-fx-font-size:13px;");

                Region notificationGrow = new Region();

                HBox.setHgrow(
                                notificationGrow,
                                Priority.ALWAYS);

                HBox notificationHeader = new HBox(
                                notificationTitle,
                                notificationGrow,
                                markRead);

                notificationHeader.setAlignment(
                                Pos.CENTER_LEFT);

                Circle dot1 = new Circle(
                                5,
                                Color.web("#FF6500"));

                Text notificationText1 = new Text(
                                "New Shop Registration\n" +
                                                "Tech Haven needs verification\n" +
                                                "2 mins ago");

                notificationText1.setStyle(
                                "-fx-font-size:13px;");

                HBox notification1 = new HBox(
                                12,
                                dot1,
                                notificationText1);

                notification1.setAlignment(
                                Pos.CENTER_LEFT);

                notification1.setPadding(
                                new Insets(12));

                notification1.setStyle(
                                "-fx-background-color:#FFF4ED;" +
                                                "-fx-background-radius:8;");

                Circle dot2 = new Circle(
                                5,
                                Color.web("#21B55A"));

                Text notificationText2 = new Text(
                                "New User Joined\n" +
                                                "New customer account created\n" +
                                                "10 mins ago");

                notificationText2.setStyle(
                                "-fx-font-size:13px;");

                HBox notification2 = new HBox(
                                12,
                                dot2,
                                notificationText2);

                notification2.setAlignment(
                                Pos.CENTER_LEFT);

                notification2.setPadding(
                                new Insets(12));

                notification2.setStyle(
                                "-fx-background-color:#F4FFF7;" +
                                                "-fx-background-radius:8;");

                Circle dot3 = new Circle(
                                5,
                                Color.web("#E53935"));

                Text notificationText3 = new Text(
                                "Flagged Account\n" +
                                                "Suspicious activity detected\n" +
                                                "1 hour ago");

                notificationText3.setStyle(
                                "-fx-font-size:13px;");

                HBox notification3 = new HBox(
                                12,
                                dot3,
                                notificationText3);

                notification3.setAlignment(
                                Pos.CENTER_LEFT);

                notification3.setPadding(
                                new Insets(12));

                notification3.setStyle(
                                "-fx-background-color:#FFF5F5;" +
                                                "-fx-background-radius:8;");

                Button viewAll = new Button(
                                "View All Notifications");

                viewAll.setMaxWidth(
                                Double.MAX_VALUE);

                viewAll.setPrefHeight(42);

                viewAll.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:13px;");

                VBox notificationBox = new VBox(
                                12,
                                notificationHeader,
                                new Separator(),
                                notification1,
                                notification2,
                                notification3,
                                viewAll);

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

                iv9.setOnMouseClicked(e -> {
                        iv9.setOnMouseClicked(event -> {

                                if (notificationPopup.isShowing()) {

                                        notificationPopup.hide();

                                } else {

                                        Bounds bellPosition = iv9.localToScreen(
                                                        iv9.getBoundsInLocal());

                                        notificationPopup.show(
                                                        iv9,
                                                        bellPosition.getMaxX() - 330,
                                                        bellPosition.getMaxY() + 12);
                                }
                        });

                });

                Text admin = new Text(
                                " Admin Portal");
                admin.setFont(
                                Font.font("Arial", FontWeight.BOLD, 15));

                Region topGrow = new Region();
                HBox.setHgrow(
                                topGrow,
                                Priority.ALWAYS);

                HBox topRight = new HBox(
                                20,
                                iv8,
                                iv9,
                                admin);

                topRight.setAlignment(
                                Pos.CENTER_RIGHT);

                top.getChildren().addAll(
                                search,
                                topGrow,
                                topRight);

                // =========================
                // CARDS
                // =========================

                HBox cards = new HBox();
                cards.setSpacing(16);

                // Card 1
                VBox card1 = new VBox();
                card1.setSpacing(14);
                card1.setPadding(new Insets(18));
                card1.setPrefHeight(115);
                card1.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;");

                Rectangle card1Bg = new Rectangle(
                                42,
                                42);
                card1Bg.setArcWidth(12);
                card1Bg.setArcHeight(12);
                card1Bg.setFill(
                                Color.web("#FDE4D8"));

                Text card1Icon = new Text("\uD83D\uDC64");
                card1Icon.setFont(Font.font(23));
                card1Icon.setFill(
                                Color.web("#B84300"));

                StackPane card1IconBox = new StackPane(
                                card1Bg,
                                card1Icon);

                Text card1Badge = new Text(
                                "+12% \u2197");
                card1Badge.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                card1Badge.setFill(
                                Color.web("#1FA64B"));

                Region card1Grow = new Region();
                HBox.setHgrow(
                                card1Grow,
                                Priority.ALWAYS);

                HBox card1Top = new HBox(
                                card1IconBox,
                                card1Grow,
                                card1Badge);

                Text card1Title = new Text(
                                "Total Customers");
                card1Title.setFont(
                                Font.font("Arial", 14));
                card1Title.setFill(
                                Color.web("#777777"));

                Text card1Value = new Text("24,512");
                card1Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card1.getChildren().addAll(
                                card1Top,
                                card1Title,
                                card1Value);

                // Card 2
                VBox card2 = new VBox();
                card2.setSpacing(14);
                card2.setPadding(new Insets(18));
                card2.setPrefHeight(115);
                card2.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;");

                Rectangle card2Bg = new Rectangle(
                                42,
                                42);
                card2Bg.setArcWidth(12);
                card2Bg.setArcHeight(12);
                card2Bg.setFill(
                                Color.web("#FDE4D8"));

                Text card2Icon = new Text("\uD83C\uDFEA");
                card2Icon.setFont(Font.font(23));
                card2Icon.setFill(
                                Color.web("#B84300"));

                StackPane card2IconBox = new StackPane(
                                card2Bg,
                                card2Icon);

                Text card2Badge = new Text(
                                "+5% \u2197");
                card2Badge.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                card2Badge.setFill(
                                Color.web("#1FA64B"));

                Region card2Grow = new Region();
                HBox.setHgrow(
                                card2Grow,
                                Priority.ALWAYS);

                HBox card2Top = new HBox(
                                card2IconBox,
                                card2Grow,
                                card2Badge);

                Text card2Title = new Text(
                                "Total Shopkeepers");
                card2Title.setFont(
                                Font.font("Arial", 14));
                card2Title.setFill(
                                Color.web("#777777"));

                Text card2Value = new Text("...");
                card2Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card2.getChildren().addAll(
                                card2Top,
                                card2Title,
                                card2Value);

                // Card 3
                VBox card3 = new VBox();
                card3.setSpacing(14);
                card3.setPadding(new Insets(18));
                card3.setPrefHeight(115);
                card3.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;");

                Rectangle card3Bg = new Rectangle(
                                42,
                                42);
                card3Bg.setArcWidth(12);
                card3Bg.setArcHeight(12);
                card3Bg.setFill(
                                Color.web("#FDE4D8"));

                Text card3Icon = new Text("\uD83D\uDCB0");
                card3Icon.setFont(Font.font(23));
                card3Icon.setFill(
                                Color.web("#B84300"));

                StackPane card3IconBox = new StackPane(
                                card3Bg,
                                card3Icon);

                Text card3Badge = new Text(
                                "+18% \u2197");
                card3Badge.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                card3Badge.setFill(
                                Color.web("#B21F0C"));

                Region card3Grow = new Region();
                HBox.setHgrow(
                                card3Grow,
                                Priority.ALWAYS);

                HBox card3Top = new HBox(
                                card3IconBox,
                                card3Grow,
                                card3Badge);

                Text card3Title = new Text(
                                "Total Revenue");
                card3Title.setFont(
                                Font.font("Arial", 14));
                card3Title.setFill(
                                Color.web("#777777"));

                Text card3Value = new Text(
                                "$142,900");
                card3Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card3.getChildren().addAll(
                                card3Top,
                                card3Title,
                                card3Value);

                // Card 4
                VBox card4 = new VBox();
                card4.setSpacing(14);
                card4.setPadding(new Insets(18));
                card4.setPrefHeight(115);
                card4.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:14;");

                Rectangle card4Bg = new Rectangle(
                                42,
                                42);
                card4Bg.setArcWidth(12);
                card4Bg.setArcHeight(12);
                card4Bg.setFill(
                                Color.web("#FCE1E1"));

                Text card4Icon = new Text("\uD83D\uDEE1");
                card4Icon.setFont(Font.font(23));
                card4Icon.setFill(
                                Color.web("#C0392B"));

                StackPane card4IconBox = new StackPane(
                                card4Bg,
                                card4Icon);

                Text card4Badge = new Text(
                                "URGENT");
                card4Badge.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                card4Badge.setFill(
                                Color.web("#C0392B"));

                Region card4Grow = new Region();
                HBox.setHgrow(
                                card4Grow,
                                Priority.ALWAYS);

                HBox card4Top = new HBox(
                                card4IconBox,
                                card4Grow,
                                card4Badge);

                Text card4Title = new Text(
                                "Pending Verifications");
                card4Title.setFont(
                                Font.font("Arial", 14));
                card4Title.setFill(
                                Color.web("#777777"));

                Text card4Value = new Text("...");
                card4Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                card4.getChildren().addAll(
                                card4Top,
                                card4Title,
                                card4Value);

                HBox.setHgrow(
                                card1,
                                Priority.ALWAYS);
                HBox.setHgrow(
                                card2,
                                Priority.ALWAYS);
                HBox.setHgrow(
                                card3,
                                Priority.ALWAYS);
                HBox.setHgrow(
                                card4,
                                Priority.ALWAYS);

                cards.getChildren().addAll(
                                card1,
                                card2,
                                card3,
                                card4);

                // =========================
                // MIDDLE
                // =========================

                HBox middle = new HBox();
                middle.setSpacing(18);
                middle.setAlignment(Pos.CENTER);

                VBox sales = new VBox();
                sales.setSpacing(15);
                sales.setPadding(new Insets(20));
                sales.setPrefWidth(620);
                sales.setPrefHeight(430);
                sales.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;");

                Text salesTitle = new Text(
                                "Monthly Sales & Revenue");
                salesTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text salesText = new Text(
                                "Performance overview for the current fiscal year");
                salesText.setFont(
                                Font.font("Arial", 13));
                salesText.setFill(Color.GRAY);

                VBox salesHeaderText = new VBox(
                                4,
                                salesTitle,
                                salesText);

                Text revenueTab = new Text(
                                "Revenue");
                revenueTab.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                Text ordersTab = new Text(
                                "Orders");
                ordersTab.setFont(
                                Font.font("Arial", 13));
                ordersTab.setFill(Color.GRAY);

                HBox tabs = new HBox(
                                15,
                                revenueTab,
                                ordersTab);
                tabs.setAlignment(
                                Pos.CENTER_RIGHT);

                Region salesTopGrow = new Region();
                HBox.setHgrow(
                                salesTopGrow,
                                Priority.ALWAYS);

                HBox salesTop = new HBox(
                                salesHeaderText,
                                salesTopGrow,
                                tabs);

                salesTop.setAlignment(
                                Pos.CENTER_LEFT);

                VBox chart = new VBox();
                chart.setAlignment(
                                Pos.BOTTOM_CENTER);
                chart.setPadding(
                                new Insets(10, 0, 0, 0));

                HBox bars = new HBox();
                bars.setSpacing(8);
                bars.setAlignment(
                                Pos.BOTTOM_CENTER);

                double[] heights = {
                                120, 180, 145, 245,
                                165, 285, 200, 170,
                                235, 130, 120, 95
                };

                for (double height : heights) {

                        Rectangle bar = new Rectangle(
                                        28,
                                        height);

                        bar.setArcWidth(6);
                        bar.setArcHeight(6);
                        bar.setFill(
                                        Color.web("#FFF0EA"));

                        bars.getChildren().add(bar);
                }

                chart.getChildren().add(bars);

                VBox.setVgrow(
                                chart,
                                Priority.ALWAYS);

                sales.getChildren().addAll(
                                salesTop,
                                chart);

                // =========================
                // AI INSIGHTS
                // =========================

                VBox insights = new VBox();
                insights.setSpacing(14);
                insights.setPadding(new Insets(20));
                insights.setPrefWidth(300);
                insights.setMinWidth(300);
                insights.setPrefHeight(430);

                insights.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#FF6B00;" +
                                                "-fx-border-radius:15;" +
                                                "-fx-border-width:1.5;");

                Text insightTitle = new Text(
                                "\u26A1  AI Insights");
                insightTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text trending = new Text(
                                "TRENDING NOW");
                trending.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                trending.setFill(
                                Color.web("#B84300"));

                VBox audio = new VBox(4);
                audio.setPadding(
                                new Insets(10));
                audio.setStyle(
                                "-fx-background-color:#FFF7F3;" +
                                                "-fx-background-radius:8;");

                Text audioTitle = new Text(
                                "Smart Audio Pro");
                audioTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                Text audioText = new Text(
                                "+240% surge in last 24h");
                audioText.setFont(
                                Font.font("Arial", 11));
                audioText.setFill(
                                Color.web("#777777"));

                audio.getChildren().addAll(
                                audioTitle,
                                audioText);

                VBox grocery = new VBox(4);
                grocery.setPadding(
                                new Insets(10));
                grocery.setStyle(
                                "-fx-background-color:#FFF7F3;" +
                                                "-fx-background-radius:8;");

                Text groceryTitle = new Text(
                                "Fresh Groceries");
                groceryTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                Text groceryText = new Text(
                                "Consistently high demand");
                groceryText.setFont(
                                Font.font("Arial", 11));
                groceryText.setFill(
                                Color.web("#777777"));

                grocery.getChildren().addAll(
                                groceryTitle,
                                groceryText);

                Text growth = new Text(
                                "GROWTH SUGGESTIONS");
                growth.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                growth.setFill(
                                Color.web("#B84300"));

                VBox suggestions = new VBox();
                suggestions.setSpacing(10);
                suggestions.setPadding(
                                new Insets(12));
                suggestions.setStyle(
                                "-fx-background-color:#FFF7F3;" +
                                                "-fx-background-radius:10;");

                Text suggestion1 = new Text(
                                "Increase flash sale frequency in the Downtown sector to capitalize on peak 6 PM traffic.");
                suggestion1.setFont(
                                Font.font("Arial", 12));
                suggestion1.setWrappingWidth(250);

                Text suggestion2 = new Text(
                                "Onboard 15+ more Health & Wellness vendors to meet unfulfilled search queries.");
                suggestion2.setFont(
                                Font.font("Arial", 12));
                suggestion2.setWrappingWidth(250);

                suggestions.getChildren().addAll(
                                suggestion1,
                                suggestion2);

                Button report = new Button(
                                "Generate Full Report");
                report.setPrefWidth(260);
                report.setPrefHeight(42);
                report.setStyle(
                                "-fx-background-color:#B84300;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                Region insightsGrow = new Region();

                VBox.setVgrow(
                                insightsGrow,
                                Priority.ALWAYS);

                insights.getChildren().addAll(
                                insightTitle,
                                trending,
                                audio,
                                grocery,
                                growth,
                                suggestions,
                                insightsGrow,
                                report);

                middle.getChildren().addAll(
                                sales,
                                insights);

                // =========================
                // BOTTOM
                // =========================

                HBox bottom = new HBox();
                bottom.setSpacing(18);

                // =================================================
                // SHOP VERIFICATIONS
                // =================================================

                VBox shopVerification = new VBox();
                shopVerification.setSpacing(10);
                shopVerification.setPadding(new Insets(18));
                shopVerification.setPrefWidth(440);
                shopVerification.setPrefHeight(260);
                shopVerification.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;");

                Text shopTitle = new Text("Shop Verifications");
                shopTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text shopViewAll = new Text("View All");
                shopViewAll.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));
                shopViewAll.setFill(Color.web("#B84300"));
                shopViewAll.setStyle("-fx-cursor: hand;");

                Region shopHeaderGrow = new Region();
                HBox.setHgrow(shopHeaderGrow, Priority.ALWAYS);

                HBox shopHeader = new HBox(
                                shopTitle,
                                shopHeaderGrow,
                                shopViewAll);

                shopHeader.setAlignment(Pos.CENTER_LEFT);

                VBox shopTable = new VBox();
                shopTable.setSpacing(2);

                HBox tableHeader = new HBox();
                tableHeader.setPadding(
                                new Insets(8, 0, 8, 0));

                Text th1 = new Text("SHOP NAME");
                Text th2 = new Text("OWNER");
                Text th3 = new Text("CATEGORY");
                Text th4 = new Text("STATUS");

                th1.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));
                th2.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));
                th3.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));
                th4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                th1.setFill(Color.web("#999999"));
                th2.setFill(Color.web("#999999"));
                th3.setFill(Color.web("#999999"));
                th4.setFill(Color.web("#999999"));

                th1.setWrappingWidth(90);
                th2.setWrappingWidth(100);
                th3.setWrappingWidth(80);

                tableHeader.getChildren().addAll(
                                th1,
                                th2,
                                th3,
                                th4);

                Text loadingShops = new Text(
                                "Loading shop verification data...");

                loadingShops.setFont(
                                Font.font("Arial", 12));

                loadingShops.setFill(
                                Color.web("#777777"));

                VBox shopRows = new VBox();
                shopRows.setSpacing(2);
                shopRows.getChildren().add(loadingShops);

                ScrollPane shopTableScroll =
                                new ScrollPane(shopRows);

                shopTableScroll.setFitToWidth(true);
                shopTableScroll.setPrefHeight(165);
                shopTableScroll.setMaxHeight(165);

                shopTableScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                shopTableScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                shopTableScroll.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background:transparent;" +
                                                "-fx-border-color:transparent;");

                shopTable.getChildren().addAll(
                                tableHeader,
                                new Separator(),
                                shopTableScroll);

                shopVerification.getChildren().addAll(
                                shopHeader,
                                shopTable);

                boolean[] shopExpanded = { false };

                shopViewAll.setOnMouseClicked(e -> {

                        shopExpanded[0] =
                                        !shopExpanded[0];

                        if (shopExpanded[0]) {

                                double expandedHeight =
                                                Math.max(
                                                                165,
                                                                shopRows
                                                                                .getChildren()
                                                                                .size()
                                                                                * 54.0);

                                shopTableScroll.setPrefHeight(
                                                expandedHeight);

                                shopTableScroll.setMaxHeight(
                                                expandedHeight);

                                shopTableScroll.setVbarPolicy(
                                                ScrollPane.ScrollBarPolicy.NEVER);

                                shopVerification.setPrefHeight(
                                                expandedHeight + 95);

                                shopViewAll.setText(
                                                "Collapse");

                        } else {

                                shopTableScroll.setPrefHeight(
                                                165);

                                shopTableScroll.setMaxHeight(
                                                165);

                                shopTableScroll.setVbarPolicy(
                                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                                shopVerification.setPrefHeight(
                                                260);

                                shopViewAll.setText(
                                                "View All");
                        }
                });

                // =================================================
                // DELIVERY PARTNER VERIFICATIONS
                // =================================================

                VBox deliveryVerification = new VBox();
                deliveryVerification.setSpacing(10);
                deliveryVerification.setPadding(
                                new Insets(18));
                deliveryVerification.setPrefWidth(440);
                deliveryVerification.setPrefHeight(260);
                deliveryVerification.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;");

                Text deliveryVerificationTitle =
                                new Text(
                                                "Delivery Partner Verifications");

                deliveryVerificationTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text deliveryViewAll =
                                new Text("View All");

                deliveryViewAll.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                deliveryViewAll.setFill(
                                Color.web("#B84300"));

                deliveryViewAll.setStyle(
                                "-fx-cursor: hand;");

                Region deliveryHeaderGrow =
                                new Region();

                HBox.setHgrow(
                                deliveryHeaderGrow,
                                Priority.ALWAYS);

                HBox deliveryVerificationHeader =
                                new HBox(
                                                deliveryVerificationTitle,
                                                deliveryHeaderGrow,
                                                deliveryViewAll);

                deliveryVerificationHeader.setAlignment(
                                Pos.CENTER_LEFT);

                VBox deliveryTable =
                                new VBox();

                deliveryTable.setSpacing(2);

                HBox deliveryTableHeader =
                                new HBox();

                deliveryTableHeader.setPadding(
                                new Insets(
                                                8,
                                                0,
                                                8,
                                                0));

                Text dth1 =
                                new Text("PARTNER");

                Text dth2 =
                                new Text("VEHICLE");

                Text dth3 =
                                new Text("NUMBER");

                Text dth4 =
                                new Text("STATUS");

                dth1.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                dth2.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                dth3.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                dth4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                dth1.setFill(
                                Color.web("#999999"));

                dth2.setFill(
                                Color.web("#999999"));

                dth3.setFill(
                                Color.web("#999999"));

                dth4.setFill(
                                Color.web("#999999"));

                dth1.setWrappingWidth(110);
                dth2.setWrappingWidth(90);
                dth3.setWrappingWidth(120);

                deliveryTableHeader.getChildren()
                                .addAll(
                                                dth1,
                                                dth2,
                                                dth3,
                                                dth4);

                Text loadingDelivery =
                                new Text(
                                                "Loading delivery partner verification data...");

                loadingDelivery.setFont(
                                Font.font(
                                                "Arial",
                                                12));

                loadingDelivery.setFill(
                                Color.web("#777777"));

                VBox deliveryRows =
                                new VBox();

                deliveryRows.setSpacing(2);

                deliveryRows.getChildren()
                                .add(
                                                loadingDelivery);

                ScrollPane deliveryTableScroll =
                                new ScrollPane(
                                                deliveryRows);

                deliveryTableScroll.setFitToWidth(
                                true);

                deliveryTableScroll.setPrefHeight(
                                165);

                deliveryTableScroll.setMaxHeight(
                                165);

                deliveryTableScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                deliveryTableScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                deliveryTableScroll.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background:transparent;" +
                                                "-fx-border-color:transparent;");

                deliveryTable.getChildren()
                                .addAll(
                                                deliveryTableHeader,
                                                new Separator(),
                                                deliveryTableScroll);

                deliveryVerification.getChildren()
                                .addAll(
                                                deliveryVerificationHeader,
                                                deliveryTable);

                boolean[] deliveryExpanded =
                                { false };

                deliveryViewAll.setOnMouseClicked(
                                e -> {

                                        deliveryExpanded[0] =
                                                        !deliveryExpanded[0];

                                        if (deliveryExpanded[0]) {

                                                double expandedHeight =
                                                                Math.max(
                                                                                165,
                                                                                deliveryRows
                                                                                                .getChildren()
                                                                                                .size()
                                                                                                * 54.0);

                                                deliveryTableScroll.setPrefHeight(
                                                                expandedHeight);

                                                deliveryTableScroll.setMaxHeight(
                                                                expandedHeight);

                                                deliveryTableScroll.setVbarPolicy(
                                                                ScrollPane.ScrollBarPolicy.NEVER);

                                                deliveryVerification.setPrefHeight(
                                                                expandedHeight + 95);

                                                deliveryViewAll.setText(
                                                                "Collapse");

                                        } else {

                                                deliveryTableScroll.setPrefHeight(
                                                                165);

                                                deliveryTableScroll.setMaxHeight(
                                                                165);

                                                deliveryTableScroll.setVbarPolicy(
                                                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                                                deliveryVerification.setPrefHeight(
                                                                260);

                                                deliveryViewAll.setText(
                                                                "View All");
                                        }
                                });

                // =================================================
                // LOAD SHOP + DELIVERY DATA FROM FIRESTORE
                // =================================================

                ShopkeeperDAO shopkeeperDAO =
                                new ShopkeeperDAO();

                DeliveryPartnerDAO deliveryPartnerDAO =
                                new DeliveryPartnerDAO();

                Thread verificationDashboardThread =
                                new Thread(() -> {

                                        List<QueryDocumentSnapshot> allShopkeepers =
                                                        shopkeeperDAO
                                                                        .getAllShopkeepers();

                                        List<QueryDocumentSnapshot> allDeliveryPartners =
                                                        deliveryPartnerDAO
                                                                        .getAllPartners();

                                        int shopPendingCount = 0;

                                        for (QueryDocumentSnapshot document :
                                                        allShopkeepers) {

                                                Boolean approved =
                                                                document
                                                                                .getBoolean(
                                                                                                "approved");

                                                if (!Boolean.TRUE.equals(
                                                                approved)) {

                                                        shopPendingCount++;
                                                }
                                        }

                                        int deliveryPendingCount = 0;

                                        for (QueryDocumentSnapshot document :
                                                        allDeliveryPartners) {

                                                Boolean approved =
                                                                document
                                                                                .getBoolean(
                                                                                                "approved");

                                                String status =
                                                                document
                                                                                .getString(
                                                                                                "status");

                                                boolean isApproved =
                                                                Boolean.TRUE.equals(
                                                                                approved)
                                                                                || "APPROVED"
                                                                                                .equalsIgnoreCase(
                                                                                                                status);

                                                boolean isRejected =
                                                                "REJECTED"
                                                                                .equalsIgnoreCase(
                                                                                                status);

                                                if (!isApproved &&
                                                                !isRejected) {

                                                        deliveryPendingCount++;
                                                }
                                        }

                                        int totalPending =
                                                        shopPendingCount
                                                                        + deliveryPendingCount;

                                        Platform.runLater(() -> {

                                                card2Value.setText(
                                                                String.valueOf(
                                                                                allShopkeepers
                                                                                                .size()));

                                                card4Value.setText(
                                                                String.valueOf(
                                                                                totalPending));

                                                shopRows.getChildren()
                                                                .clear();

                                                if (allShopkeepers
                                                                .isEmpty()) {

                                                        Text noShops =
                                                                        new Text(
                                                                                        "No shop verification requests found.");

                                                        noShops.setFont(
                                                                        Font.font(
                                                                                        "Arial",
                                                                                        12));

                                                        noShops.setFill(
                                                                        Color.web(
                                                                                        "#777777"));

                                                        shopRows
                                                                        .getChildren()
                                                                        .add(
                                                                                        noShops);

                                                } else {

                                                        for (QueryDocumentSnapshot document :
                                                                        allShopkeepers) {

                                                                HBox row =
                                                                                createShopVerificationRow(
                                                                                                document);

                                                                shopRows
                                                                                .getChildren()
                                                                                .add(
                                                                                                row);
                                                        }
                                                }

                                                deliveryRows
                                                                .getChildren()
                                                                .clear();

                                                if (allDeliveryPartners
                                                                .isEmpty()) {

                                                        Text noDelivery =
                                                                        new Text(
                                                                                        "No delivery partner verification requests found.");

                                                        noDelivery.setFont(
                                                                        Font.font(
                                                                                        "Arial",
                                                                                        12));

                                                        noDelivery.setFill(
                                                                        Color.web(
                                                                                        "#777777"));

                                                        deliveryRows
                                                                        .getChildren()
                                                                        .add(
                                                                                        noDelivery);

                                                } else {

                                                        for (QueryDocumentSnapshot document :
                                                                        allDeliveryPartners) {

                                                                HBox row =
                                                                                createDeliveryVerificationRow(
                                                                                                document);

                                                                deliveryRows
                                                                                .getChildren()
                                                                                .add(
                                                                                                row);
                                                        }
                                                }
                                        });
                                });

                verificationDashboardThread.setDaemon(
                                true);

                verificationDashboardThread.start();

                // =========================
                // DAILY ACTIVE USERS
                // =========================

                VBox dailyUsers = new VBox();
                dailyUsers.setSpacing(15);
                dailyUsers.setPadding(
                                new Insets(18));
                dailyUsers.setPrefWidth(420);
                dailyUsers.setPrefHeight(260);
                dailyUsers.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;");

                Text dailyTitle = new Text(
                                "Daily Active Users");
                dailyTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text lastDays = new Text(
                                "Last 7 Days");
                lastDays.setFont(
                                Font.font("Arial", 13));
                lastDays.setFill(Color.GRAY);

                Region dailyHeaderGrow = new Region();

                HBox.setHgrow(
                                dailyHeaderGrow,
                                Priority.ALWAYS);

                HBox dailyHeader = new HBox(
                                dailyTitle,
                                dailyHeaderGrow,
                                lastDays);

                dailyHeader.setAlignment(
                                Pos.CENTER_LEFT);
                Image graph = new Image(getClass().getResource("/assets/images/admin/graph.png").toExternalForm());
                ImageView iv10 = new ImageView(graph);

                HBox graphBox = new HBox(iv10);
                graphBox.setPrefHeight(120);
                graphBox.setAlignment(
                                Pos.CENTER);

                VBox.setVgrow(
                                graphBox,
                                Priority.ALWAYS);

                Text peakLabel = new Text(
                                "PEAK TIME");
                peakLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                peakLabel.setFill(
                                Color.web("#999999"));

                Text peakValue = new Text(
                                "19:00 - 21:00");
                peakValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                VBox peak = new VBox(3);
                peak.getChildren().addAll(
                                peakLabel,
                                peakValue);

                Text sessionLabel = new Text(
                                "AVG SESSION");
                sessionLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                sessionLabel.setFill(
                                Color.web("#999999"));

                Text sessionValue = new Text(
                                "12m 45s");
                sessionValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                VBox session = new VBox(3);
                session.getChildren().addAll(
                                sessionLabel,
                                sessionValue);

                HBox stats = new HBox(40);
                stats.getChildren().addAll(
                                peak,
                                session);

                dailyUsers.getChildren().addAll(
                                dailyHeader,
                                graphBox,
                                stats);

                HBox.setHgrow(
                                shopVerification,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                dailyUsers,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                deliveryVerification,
                                Priority.ALWAYS);

                bottom.getChildren().addAll(
                                shopVerification,
                                dailyUsers);

                HBox deliveryVerificationRow =
                                new HBox();

                deliveryVerificationRow.getChildren()
                                .add(
                                                deliveryVerification);

                HBox.setHgrow(
                                deliveryVerification,
                                Priority.ALWAYS);

                // =========================
                // ADD CENTER COMPONENTS
                // =========================

                center.getChildren().addAll(
                                top,
                                cards,
                                middle,
                                bottom,
                                deliveryVerificationRow);

                centerWrapper.getChildren().add(center);

                StackPane.setAlignment(
                                center,
                                Pos.TOP_CENTER);

                ScrollPane centerScroll = new ScrollPane(centerWrapper);
                centerScroll.setFitToWidth(true);
                centerScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                centerScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                centerScroll.setPannable(true);
                centerScroll.setStyle(
                                "-fx-background:#eee5df;" +
                                "-fx-background-color:#eee5df;" +
                                "-fx-border-color:transparent;"
                        );

                root.setCenter(centerScroll);


                // =========================
                // SCENE
                // =========================

                Scene scene = new Scene(root, 1500, 850);
                dashboardScene = scene;
                return dashboardScene;
        }


        private HBox createShopVerificationRow(
                        QueryDocumentSnapshot document) {

                String shopName =
                                getShopValue(
                                                document,
                                                "shopNameValue",
                                                "Unnamed Shop");

                String ownerName =
                                getShopValue(
                                                document,
                                                "ownerNameValue",
                                                "-");

                String category =
                                getShopValue(
                                                document,
                                                "categoryValue",
                                                "-");

                Boolean approved =
                                document.getBoolean("approved");

                String status =
                                Boolean.TRUE.equals(approved)
                                                ? "Verified"
                                                : "Pending";

                HBox row = new HBox();
                row.setPadding(new Insets(8, 0, 8, 0));

                Text shopNameText = new Text(shopName);
                Text ownerNameText = new Text(ownerName);
                Text categoryText = new Text(category);
                Text statusText = new Text(status);

                shopNameText.setWrappingWidth(90);
                ownerNameText.setWrappingWidth(100);
                categoryText.setWrappingWidth(80);

                shopNameText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                ownerNameText.setFont(Font.font("Arial", 13));
                categoryText.setFont(Font.font("Arial", 13));

                statusText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                if (Boolean.TRUE.equals(approved)) {
                        statusText.setFill(Color.web("#1FA64B"));
                } else {
                        statusText.setFill(Color.web("#E08A00"));
                }

                row.getChildren().addAll(
                                shopNameText,
                                ownerNameText,
                                categoryText,
                                statusText);

                return row;
        }

        private HBox createDeliveryVerificationRow(
                        QueryDocumentSnapshot document) {

                String fullName =
                                getDeliveryValue(
                                                document,
                                                "fullName",
                                                "Partner");

                String vehicleType =
                                getDeliveryValue(
                                                document,
                                                "vehicleType",
                                                "-");

                String vehicleNumber =
                                getDeliveryValue(
                                                document,
                                                "vehicleNumber",
                                                "-");

                Boolean approved =
                                document.getBoolean(
                                                "approved");

                String rawStatus =
                                document.getString(
                                                "status");

                String status;

                if (Boolean.TRUE.equals(approved) ||
                                "APPROVED"
                                                .equalsIgnoreCase(
                                                                rawStatus)) {

                        status = "Verified";

                } else if ("REJECTED"
                                .equalsIgnoreCase(
                                                rawStatus)) {

                        status = "Rejected";

                } else {

                        status = "Pending";
                }

                HBox row = new HBox();
                row.setPadding(
                                new Insets(
                                                8,
                                                0,
                                                8,
                                                0));

                Text nameText =
                                new Text(fullName);

                Text vehicleText =
                                new Text(vehicleType);

                Text numberText =
                                new Text(vehicleNumber);

                Text statusText =
                                new Text(status);

                nameText.setWrappingWidth(110);
                vehicleText.setWrappingWidth(90);
                numberText.setWrappingWidth(120);

                nameText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                vehicleText.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                numberText.setFont(
                                Font.font(
                                                "Arial",
                                                13));

                statusText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                if ("Verified"
                                .equals(status)) {

                        statusText.setFill(
                                        Color.web(
                                                        "#1FA64B"));

                } else if ("Rejected"
                                .equals(status)) {

                        statusText.setFill(
                                        Color.web(
                                                        "#C0392B"));

                } else {

                        statusText.setFill(
                                        Color.web(
                                                        "#E08A00"));
                }

                row.getChildren()
                                .addAll(
                                                nameText,
                                                vehicleText,
                                                numberText,
                                                statusText);

                return row;
        }

        private String getDeliveryValue(
                        QueryDocumentSnapshot document,
                        String fieldName,
                        String defaultValue) {

                String value =
                                document.getString(
                                                fieldName);

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value;
        }

        private String getShopValue(
                        QueryDocumentSnapshot document,
                        String fieldName,
                        String defaultValue) {

                String value = document.getString(fieldName);

                if (value == null ||
                                value.trim().isEmpty()) {

                        return defaultValue;
                }

                return value;
        }

}
package com.kryox.view.Admin;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
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
                root.setStyle("-fx-background-color:#F9F7FB;");

                VBox left = new VBox();
                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));
                left.setStyle("-fx-background-color:#F3E3D3;");

                Text logo = new Text("Admin Panel");
                logo.setFont(Font.font("Arial", FontWeight.BOLD, 21));
                logo.setFill(Color.web("#A83E00"));

                Text adminPanel = new Text("Marketplace Controller");
                adminPanel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
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
                iv1.setFitWidth(16);
                iv1.setFitHeight(16);
                iv1.setPreserveRatio(true);

                Text dashboardText = new Text("Dashboard");
                dashboardText.setFill(Color.web("#333333"));
                dashboardText.setFont(Font.font("Arial", 12));

                dashboard.getChildren().addAll(iv1, dashboardText);

                dashboard.setOnMouseEntered(e -> {
                        dashboard.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(Color.WHITE);
                        dashboardText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        dashboardText.setFont(Font.font("Arial", 12));

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

                Image img2 = new Image(getClass().getResource("/assets/images/admin/admin logo.png").toExternalForm());
                ImageView iv2 = new ImageView(img2);
                iv2.setFitWidth(16);
                iv2.setFitHeight(16);
                iv2.setPreserveRatio(true);

                Text usersText = new Text("Users");
                usersText.setFill(Color.web("#333333"));
                usersText.setFont(Font.font("Arial", 12));

                users.getChildren().addAll(iv2, usersText);

                users.setOnMouseEntered(e -> {
                        users.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.WHITE);
                        usersText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        usersText.setFont(Font.font("Arial", 12));

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
                iv3.setFitWidth(16);
                iv3.setFitHeight(16);
                iv3.setPreserveRatio(true);

                Text shopsText = new Text("Shops");
                shopsText.setFill(Color.web("#333333"));
                shopsText.setFont(Font.font("Arial", 12));

                shops.getChildren().addAll(iv3, shopsText);

                shops.setOnMouseEntered(e -> {
                        shops.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(Color.WHITE);
                        shopsText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        shopsText.setFont(Font.font("Arial", 12));

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
                iv4.setFitWidth(16);
                iv4.setFitHeight(16);
                iv4.setPreserveRatio(true);

                Text offersText = new Text("Offers");
                offersText.setFill(Color.web("#333333"));
                offersText.setFont(Font.font("Arial", 12));

                offers.getChildren().addAll(iv4, offersText);

                offers.setOnMouseEntered(e -> {
                        offers.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(Color.WHITE);
                        offersText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        offersText.setFont(Font.font("Arial", 12));

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
                iv5.setFitWidth(16);
                iv5.setFitHeight(16);
                iv5.setPreserveRatio(true);

                Text analyticsText = new Text("Analytics");
                analyticsText.setFill(Color.web("#333333"));
                analyticsText.setFont(Font.font("Arial", 12));

                analytics.getChildren().addAll(iv5, analyticsText);

                analytics.setOnMouseEntered(e -> {
                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(Color.WHITE);
                        analyticsText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        analyticsText.setFont(Font.font("Arial", 12));

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
                iv6.setFitWidth(16);
                iv6.setFitHeight(16);
                iv6.setPreserveRatio(true);

                Text settingsText = new Text("Settings");
                settingsText.setFill(Color.web("#333333"));
                settingsText.setFont(Font.font("Arial", 12));

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
                iv7.setFitWidth(16);
                iv7.setFitHeight(16);
                iv7.setPreserveRatio(true);

                Text supportText = new Text("Support");
                supportText.setFill(Color.web("#333333"));
                supportText.setFont(Font.font("Arial", 12));

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
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        settingsText.setFont(Font.font("Arial", 12));

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
                                        Font.font("Arial", FontWeight.BOLD, 12));

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
                        supportText.setFont(Font.font("Arial", 12));

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

                Button exit = new Button("Exit");

                HBox profile = new HBox(
                                exit);

                profile.setAlignment(Pos.CENTER_LEFT);
                profile.setPadding(new Insets(10));
                profile.setStyle(
                                "-fx-background-color:#E4E2E7;" +
                                                "-fx-background-radius:12;");

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
                center.setMaxHeight(850);

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
                search.setPrefHeight(38);
                search.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:20;");

                Image aiChatbotImage = new Image(getClass().getResource("/assets/images/admin/message.png").toExternalForm());
                ImageView iv8 = new ImageView(aiChatbotImage);
                iv8.setFitWidth(20);
                iv8.setFitHeight(20);
                iv8.setPreserveRatio(true);
                // Icons 
                iv8.setOnMouseClicked(e -> {
                        SmartAssistantUI chatPage = new SmartAssistantUI();

                        Homepage.HomepageStage.setScene(
                                        chatPage.getUserScene());
                });

                Image notification = new Image(getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView iv9 = new ImageView(notification);
                iv9.setFitWidth(20);
                iv9.setFitHeight(20);
                iv9.setPreserveRatio(true);

                Popup notificationPopup = new Popup();

                Text notificationTitle = new Text("Notifications");
                notificationTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Button markRead = new Button("Mark all as read");

                markRead.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E65300;" +
                                                "-fx-font-size:11px;");

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
                                "-fx-font-size:11px;");

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
                                "-fx-font-size:11px;");

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
                                "-fx-font-size:11px;");

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

                viewAll.setPrefHeight(38);

                viewAll.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;");

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
                                Font.font("Arial", FontWeight.BOLD, 13));

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
                card1Icon.setFont(Font.font(19));
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
                                                11));
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
                                Font.font("Arial", 12));
                card1Title.setFill(
                                Color.web("#777777"));

                Text card1Value = new Text("24,512");
                card1Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

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
                card2Icon.setFont(Font.font(19));
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
                                                11));
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
                                Font.font("Arial", 12));
                card2Title.setFill(
                                Color.web("#777777"));

                Text card2Value = new Text("1,284");
                card2Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

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
                card3Icon.setFont(Font.font(19));
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
                                                11));
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
                                Font.font("Arial", 12));
                card3Title.setFill(
                                Color.web("#777777"));

                Text card3Value = new Text(
                                "$142,900");
                card3Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

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
                card4Icon.setFont(Font.font(19));
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
                                                11));
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
                                Font.font("Arial", 12));
                card4Title.setFill(
                                Color.web("#777777"));

                Text card4Value = new Text("43");
                card4Value.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

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
                                                14));

                Text salesText = new Text(
                                "Performance overview for the current fiscal year");
                salesText.setFont(
                                Font.font("Arial", 11));
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
                                                11));

                Text ordersTab = new Text(
                                "Orders");
                ordersTab.setFont(
                                Font.font("Arial", 11));
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
                                                14));

                Text trending = new Text(
                                "TRENDING NOW");
                trending.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));
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
                                                12));

                Text audioText = new Text(
                                "+240% surge in last 24h");
                audioText.setFont(
                                Font.font("Arial", 9));
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
                                                12));

                Text groceryText = new Text(
                                "Consistently high demand");
                groceryText.setFont(
                                Font.font("Arial", 9));
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
                                                9));
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
                                Font.font("Arial", 10));
                suggestion1.setWrappingWidth(250);

                Text suggestion2 = new Text(
                                "Onboard 15+ more Health & Wellness vendors to meet unfulfilled search queries.");
                suggestion2.setFont(
                                Font.font("Arial", 10));
                suggestion2.setWrappingWidth(250);

                suggestions.getChildren().addAll(
                                suggestion1,
                                suggestion2);

                Button report = new Button(
                                "Generate Full Report");
                report.setPrefWidth(260);
                report.setPrefHeight(38);
                report.setStyle(
                                "-fx-background-color:#B84300;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-weight:bold;");

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

                VBox shopVerification = new VBox();
                shopVerification.setSpacing(10);
                shopVerification.setPadding(
                                new Insets(18));
                shopVerification.setPrefWidth(440);
                shopVerification.setPrefHeight(260);
                shopVerification.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;");

                Text shopTitle = new Text(
                                "Shop Verifications");
                shopTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                Text shopViewAll = new Text(
                                "View All");
                shopViewAll.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                shopViewAll.setFill(
                                Color.web("#B84300"));

                Region shopHeaderGrow = new Region();

                HBox.setHgrow(
                                shopHeaderGrow,
                                Priority.ALWAYS);

                HBox shopHeader = new HBox(
                                shopTitle,
                                shopHeaderGrow,
                                shopViewAll);

                shopHeader.setAlignment(
                                Pos.CENTER_LEFT);

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
                                                10));
                th2.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));
                th3.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));
                th4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

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

                HBox row1 = new HBox();
                row1.setPadding(
                                new Insets(8, 0, 8, 0));

                Text r1c1 = new Text("Urban Mart");
                Text r1c2 = new Text("Mark Spencer");
                Text r1c3 = new Text("Grocery");
                Text r1c4 = new Text("Pending");

                r1c1.setWrappingWidth(90);
                r1c2.setWrappingWidth(100);
                r1c3.setWrappingWidth(80);

                r1c1.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                r1c2.setFont(
                                Font.font("Arial", 11));
                r1c3.setFont(
                                Font.font("Arial", 11));
                r1c4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));
                r1c4.setFill(
                                Color.web("#E08A00"));

                row1.getChildren().addAll(
                                r1c1,
                                r1c2,
                                r1c3,
                                r1c4);

                HBox row2 = new HBox();
                row2.setPadding(
                                new Insets(8, 0, 8, 0));

                Text r2c1 = new Text("Tech Haven");
                Text r2c2 = new Text("Sarah Chen");
                Text r2c3 = new Text("Electronics");
                Text r2c4 = new Text("Verified");

                r2c1.setWrappingWidth(90);
                r2c2.setWrappingWidth(100);
                r2c3.setWrappingWidth(80);

                r2c1.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                r2c2.setFont(
                                Font.font("Arial", 11));
                r2c3.setFont(
                                Font.font("Arial", 11));
                r2c4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));
                r2c4.setFill(
                                Color.web("#1FA64B"));

                row2.getChildren().addAll(
                                r2c1,
                                r2c2,
                                r2c3,
                                r2c4);

                HBox row3 = new HBox();
                row3.setPadding(
                                new Insets(8, 0, 8, 0));

                Text r3c1 = new Text("Flora Design");
                Text r3c2 = new Text("Emma Wilson");
                Text r3c3 = new Text("Lifestyle");
                Text r3c4 = new Text("Flagged");

                r3c1.setWrappingWidth(90);
                r3c2.setWrappingWidth(100);
                r3c3.setWrappingWidth(80);

                r3c1.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));
                r3c2.setFont(
                                Font.font("Arial", 11));
                r3c3.setFont(
                                Font.font("Arial", 11));
                r3c4.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));
                r3c4.setFill(
                                Color.web("#C0392B"));

                row3.getChildren().addAll(
                                r3c1,
                                r3c2,
                                r3c3,
                                r3c4);

                shopTable.getChildren().addAll(
                                tableHeader,
                                new Separator(),
                                row1,
                                row2,
                                row3);

                shopVerification.getChildren().addAll(
                                shopHeader,
                                shopTable);

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
                                                14));

                Text lastDays = new Text(
                                "Last 7 Days");
                lastDays.setFont(
                                Font.font("Arial", 11));
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
                                                9));
                peakLabel.setFill(
                                Color.web("#999999"));

                Text peakValue = new Text(
                                "19:00 - 21:00");
                peakValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

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
                                                9));
                sessionLabel.setFill(
                                Color.web("#999999"));

                Text sessionValue = new Text(
                                "12m 45s");
                sessionValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

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

                bottom.getChildren().addAll(
                                shopVerification,
                                dailyUsers);

                // =========================
                // ADD CENTER COMPONENTS
                // =========================

                center.getChildren().addAll(
                                top,
                                cards,
                                middle,
                                bottom);

                centerWrapper.getChildren().add(center);

                StackPane.setAlignment(
                                center,
                                Pos.CENTER);

                root.setCenter(centerWrapper);

                // =========================
                // SCENE
                // =========================

                Scene scene = new Scene(root, 1500, 850);
                dashboardScene = scene;
                return dashboardScene;
        }

}
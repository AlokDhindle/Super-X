package com.kryox.view.Admin;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Bounds;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Popup;
import javafx.util.Duration;

public class UserManagementPage {

        public Scene getUserScene() {
                VBox left = new VBox();

                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));

                left.setStyle(
                                "-fx-background-color:#F3E3D3;");

                Text logo = new Text("Admin Panel");

                logo.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                24));

                logo.setFill(
                                Color.web("#A83E00"));

                Text controller = new Text(
                                "Marketplace Controller");

                controller.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                controller.setFill(
                                Color.web("#999999"));

                VBox logoBox = new VBox(
                                4,
                                logo,
                                controller);

                VBox menu = new VBox();

                menu.setSpacing(4);

                HBox dashboard = new HBox();

                dashboard.setSpacing(10);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setPadding(
                                new Insets(10, 12, 10, 12));
                dashboard.setPrefWidth(180);

                dashboard.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img1 = new Image(
                                getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());

                ImageView iv1 = new ImageView(img1);

                iv1.setFitWidth(20);
                iv1.setFitHeight(20);
                iv1.setPreserveRatio(true);

                Text dashboardText = new Text(
                                "Dashboard");

                dashboardText.setFill(
                                Color.web("#333333"));

                dashboardText.setFont(
                                Font.font("Arial", 15));

                dashboard.getChildren().addAll(
                                iv1,
                                dashboardText);

                dashboard.setOnMouseEntered(e -> {

                        dashboard.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(Color.WHITE);

                        dashboardText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                dashboard.setOnMouseExited(e -> {

                        dashboard.setStyle(
                                        "-fx-background-color:transparent;" +
                                                        "-fx-background-radius:10;");

                        dashboardText.setFill(
                                        Color.web("#333333"));

                        dashboardText.setFont(
                                        Font.font("Arial", 15));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        dashboard);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                dashboard.setOnMouseClicked(e -> {

                        AdminDashboardPage dashboardPage = new AdminDashboardPage();

                        Homepage.HomepageStage.setScene(
                                        dashboardPage.getUserScene());
                });

                HBox users = new HBox();

                users.setSpacing(10);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setPadding(
                                new Insets(10, 12, 10, 12));
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

                usersText.setFill(Color.WHITE);

                usersText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                15));

                users.getChildren().addAll(
                                iv2,
                                usersText);

                users.setOnMouseEntered(e -> {

                        users.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.WHITE);

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        users);

                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                users.setOnMouseExited(e -> {

                        users.setStyle(
                                        "-fx-background-color:#FF6500;" +
                                                        "-fx-background-radius:10;");

                        usersText.setFill(Color.WHITE);

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        users);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                HBox shops = new HBox();

                shops.setSpacing(10);
                shops.setAlignment(Pos.CENTER_LEFT);
                shops.setPadding(
                                new Insets(10, 12, 10, 12));
                shops.setPrefWidth(180);

                shops.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img3 = new Image(
                                getClass().getResource("/assets/images/admin/shop.png").toExternalForm());

                ImageView iv3 = new ImageView(img3);

                iv3.setFitWidth(20);
                iv3.setFitHeight(20);
                iv3.setPreserveRatio(true);

                Text shopsText = new Text("Shops");

                shopsText.setFill(
                                Color.web("#333333"));

                shopsText.setFont(
                                Font.font("Arial", 15));

                shops.getChildren().addAll(
                                iv3,
                                shopsText);

                shops.setOnMouseEntered(e -> {

                        shops.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        shopsText.setFill(Color.WHITE);

                        shopsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

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

                        shopsText.setFill(
                                        Color.web("#333333"));

                        shopsText.setFont(
                                        Font.font("Arial", 15));

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
                offers.setPadding(
                                new Insets(10, 12, 10, 12));
                offers.setPrefWidth(180);

                offers.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img4 = new Image(
                                getClass().getResource("/assets/images/admin/tag.png").toExternalForm());

                ImageView iv4 = new ImageView(img4);

                iv4.setFitWidth(20);
                iv4.setFitHeight(20);
                iv4.setPreserveRatio(true);

                Text offersText = new Text("Offers");

                offersText.setFill(
                                Color.web("#333333"));

                offersText.setFont(
                                Font.font("Arial", 15));

                offers.getChildren().addAll(
                                iv4,
                                offersText);

                offers.setOnMouseEntered(e -> {

                        offers.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        offersText.setFill(Color.WHITE);

                        offersText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

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

                        offersText.setFill(
                                        Color.web("#333333"));

                        offersText.setFont(
                                        Font.font("Arial", 15));

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
                analytics.setPadding(
                                new Insets(10, 12, 10, 12));
                analytics.setPrefWidth(180);

                analytics.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img5 = new Image(
                                getClass().getResource("/assets/images/admin/stats.png").toExternalForm());

                ImageView iv5 = new ImageView(img5);

                iv5.setFitWidth(20);
                iv5.setFitHeight(20);
                iv5.setPreserveRatio(true);

                Text analyticsText = new Text(
                                "Analytics");

                analyticsText.setFill(
                                Color.web("#333333"));

                analyticsText.setFont(
                                Font.font("Arial", 15));

                analytics.getChildren().addAll(
                                iv5,
                                analyticsText);

                analytics.setOnMouseEntered(e -> {

                        analytics.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        analyticsText.setFill(Color.WHITE);

                        analyticsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

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

                        analyticsText.setFill(
                                        Color.web("#333333"));

                        analyticsText.setFont(
                                        Font.font("Arial", 15));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        analytics);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
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
                settings.setPadding(
                                new Insets(10, 12, 10, 12));
                settings.setPrefWidth(180);

                settings.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img6 = new Image(
                                getClass().getResource("/assets/images/admin/setting.png").toExternalForm());

                ImageView iv6 = new ImageView(img6);

                iv6.setFitWidth(20);
                iv6.setFitHeight(20);
                iv6.setPreserveRatio(true);

                Text settingsText = new Text(
                                "Settings");

                settingsText.setFill(
                                Color.web("#333333"));

                settingsText.setFont(
                                Font.font("Arial", 15));

                settings.getChildren().addAll(
                                iv6,
                                settingsText);

                HBox support = new HBox();

                support.setSpacing(10);
                support.setAlignment(Pos.CENTER_LEFT);
                support.setPadding(
                                new Insets(10, 12, 10, 12));
                support.setPrefWidth(180);

                support.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-background-radius:10;");

                Image img7 = new Image(
                                getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());

                ImageView iv7 = new ImageView(img7);

                iv7.setFitWidth(20);
                iv7.setFitHeight(20);
                iv7.setPreserveRatio(true);

                Text supportText = new Text(
                                "Support");

                supportText.setFill(
                                Color.web("#333333"));

                supportText.setFont(
                                Font.font("Arial", 15));

                support.getChildren().addAll(
                                iv7,
                                supportText);

                bottomMenu.getChildren().addAll(
                                settings,
                                support);

                settings.setOnMouseEntered(e -> {

                        settings.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        settingsText.setFill(Color.WHITE);

                        settingsText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

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

                        settingsText.setFill(
                                        Color.web("#333333"));

                        settingsText.setFont(
                                        Font.font("Arial", 15));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        settings);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                support.setOnMouseEntered(e -> {

                        support.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        supportText.setFill(Color.WHITE);

                        supportText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

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

                        supportText.setFill(
                                        Color.web("#333333"));

                        supportText.setFont(
                                        Font.font("Arial", 15));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        support);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                javafx.scene.shape.Circle avatar = new javafx.scene.shape.Circle(
                                19,
                                Color.web("#D9B79C"));

                Text alex = new Text("Alex Rivera");

                alex.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                14));

                Text superAdmin = new Text(
                                "Super Admin");

                superAdmin.setFont(
                                Font.font("Arial", 12));

                superAdmin.setFill(
                                Color.web("#777777"));

                VBox names = new VBox(
                                2,
                                alex,
                                superAdmin);

                HBox profile = new HBox(
                                10,
                                avatar,
                                names);

                profile.setAlignment(
                                Pos.CENTER_LEFT);

                profile.setPadding(
                                new Insets(10));

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

                VBox rightBox = new VBox();

                rightBox.setSpacing(20);
                rightBox.setPadding(
                                new Insets(20, 25, 20, 25));

                rightBox.setStyle(
                                "-fx-background-color:#FAF8FC;");

                Text searchIcon = new Text("⌕");

                searchIcon.setFont(
                                Font.font(24));

                TextField topSearch = new TextField();

                topSearch.setPromptText(
                                "Search anything...");

                topSearch.setPrefWidth(290);

                topSearch.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:18;" +
                                                "-fx-font-size:15px;");

                HBox topSearchBox = new HBox(
                                8,
                                searchIcon,
                                topSearch);

                topSearchBox.setStyle(
                                "-fx-background-color:#eee5df");

                topSearchBox.setAlignment(
                                Pos.CENTER_LEFT);

                Image aiChatbotImage = new Image(getClass().getResource("/assets/images/admin/message.png").toExternalForm());
                ImageView iv8 = new ImageView(aiChatbotImage);
                iv8.setFitWidth(22);
                iv8.setFitHeight(22);
                iv8.setPreserveRatio(true);

                Image notification = new Image(getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView iv9 = new ImageView(notification);
                iv9.setFitWidth(22);
                iv9.setFitHeight(22);
                // iv9.setPreserveRatio(true);

                Popup notificationPopup = new Popup();

                Text notificationTitle = new Text("Notifications");
                notificationTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                22));

                Button markRead = new Button("Mark all as read");
                markRead.setStyle(
                                "-fx-background-color:transparent;" +
                                                "-fx-text-fill:#E65300;" +
                                                "-fx-font-size:15px;");

                Region notificationGrow = new Region();
                HBox.setHgrow(notificationGrow, Priority.ALWAYS);

                HBox notificationHeader = new HBox(
                                notificationTitle,
                                notificationGrow,
                                markRead);

                notificationHeader.setAlignment(Pos.CENTER_LEFT);

                Circle dot1 = new Circle(5, Color.web("#FF6500"));

                Text notificationText1 = new Text(
                                "New Shop Registration\n" +
                                                "Tech Haven needs verification\n" +
                                                "2 mins ago");

                notificationText1.setStyle("-fx-font-size:15px;");

                HBox notification1 = new HBox(
                                12,
                                dot1,
                                notificationText1);

                notification1.setAlignment(Pos.CENTER_LEFT);
                notification1.setPadding(new Insets(12));
                notification1.setStyle(
                                "-fx-background-color:#FFF4ED;" +
                                                "-fx-background-radius:8;");

                Circle dot2 = new Circle(5, Color.web("#21B55A"));

                Text notificationText2 = new Text(
                                "New User Joined\n" +
                                                "New customer account created\n" +
                                                "10 mins ago");

                notificationText2.setStyle("-fx-font-size:15px;");

                HBox notification2 = new HBox(
                                12,
                                dot2,
                                notificationText2);

                notification2.setAlignment(Pos.CENTER_LEFT);
                notification2.setPadding(new Insets(12));
                notification2.setStyle(
                                "-fx-background-color:#F4FFF7;" +
                                                "-fx-background-radius:8;");

                Circle dot3 = new Circle(5, Color.web("#E53935"));

                Text notificationText3 = new Text(
                                "Flagged Account\n" +
                                                "Suspicious activity detected\n" +
                                                "1 hour ago");

                notificationText3.setStyle("-fx-font-size:15px;");

                HBox notification3 = new HBox(
                                12,
                                dot3,
                                notificationText3);

                notification3.setAlignment(Pos.CENTER_LEFT);
                notification3.setPadding(new Insets(12));
                notification3.setStyle(
                                "-fx-background-color:#FFF5F5;" +
                                                "-fx-background-radius:8;");

                Button viewAll = new Button("View All Notifications");
                viewAll.setMaxWidth(Double.MAX_VALUE);
                viewAll.setPrefHeight(42);
                viewAll.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:15px;");

                VBox notificationBox = new VBox(
                                12,
                                notificationHeader,
                                new Separator(),
                                notification1,
                                notification2,
                                notification3,
                                viewAll);

                notificationBox.setPrefWidth(330);
                notificationBox.setPadding(new Insets(18));
                notificationBox.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-width:1;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-effect:dropshadow(gaussian," +
                                                "rgba(0,0,0,0.18),15,0,0,5);");

                notificationPopup.getContent().add(notificationBox);

                iv9.setOnMouseClicked(e -> {

                        if (notificationPopup.isShowing()) {

                                notificationPopup.hide();

                        } else {

                                Bounds bellPosition = iv9.localToScreen(
                                                iv9.getBoundsInLocal());

                                if (bellPosition != null) {

                                        notificationPopup.show(
                                                        iv9,
                                                        bellPosition.getMaxX() - 330,
                                                        bellPosition.getMaxY() + 12);
                                }
                        }
                });

                markRead.setOnAction(e -> {

                        notification1.setStyle("-fx-background-color:white;");
                        notification2.setStyle("-fx-background-color:white;");
                        notification3.setStyle("-fx-background-color:white;");
                });

                Text adminName = new Text("Alex");

                adminName.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                HBox topRight = new HBox(18, iv8, iv9, adminName);

                Region topGrow = new Region();

                HBox.setHgrow(
                                topGrow,
                                Priority.ALWAYS);

                HBox top = new HBox(
                                topSearchBox,
                                topGrow,
                                topRight);

                top.setAlignment(
                                Pos.CENTER_LEFT);

                Text pageTitle = new Text(
                                "User Management");

                pageTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                32));

                Text pageSubtitle = new Text(
                                "Manage and monitor customers and shopkeeper accounts.");

                pageSubtitle.setFont(
                                Font.font("Arial", 15));

                pageSubtitle.setFill(
                                Color.web("#777777"));

                VBox titleBox = new VBox(
                                5,
                                pageTitle,
                                pageSubtitle);

                Button addUser = new Button(
                                "+  Add New User");

                addUser.setPrefWidth(160);
                addUser.setPrefHeight(46);

                addUser.setStyle(
                                "-fx-background-color:#F65F00;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:17px;" +
                                                "-fx-background-radius:7;");
                addUser.setOnMouseClicked(event ->{

                        AddNewUserPage newuser = new AddNewUserPage();
                        Homepage.HomepageStage.setScene(newuser.getAddUserScene());
                });
                Button export = new Button(
                                "↓  Export");

                export.setPrefWidth(110);
                export.setPrefHeight(46);

                export.setStyle(
                                "-fx-background-color:#E7E5EB;" +
                                                "-fx-text-fill:#222222;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:15px;" +
                                                "-fx-background-radius:7;");
                
                HBox titleButtons = new HBox(
                                10,
                                addUser,
                                export);

                titleButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                Region headerGrow = new Region();

                HBox.setHgrow(
                                headerGrow,
                                Priority.ALWAYS);

                HBox pageHeader = new HBox(
                                titleBox,
                                headerGrow,
                                titleButtons);

                pageHeader.setAlignment(
                                Pos.CENTER_LEFT);

                Text searchLabel = new Text(
                                "Search Name or Email");

                searchLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                TextField userSearch = new TextField();

                userSearch.setPromptText(
                                "e.g. John Doe");

                userSearch.setPrefWidth(300);
                userSearch.setPrefHeight(42);

                userSearch.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:15px;");

                VBox searchBox = new VBox(
                                5,
                                searchLabel,
                                userSearch);

                Text roleLabel = new Text("Role");

                roleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                Button roleButton = new Button(
                                "All Roles        ˅");

                roleButton.setPrefWidth(150);
                roleButton.setPrefHeight(42);

                roleButton.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-alignment:CENTER_LEFT;" +
                                                "-fx-font-size:15px;");

                VBox roleBox = new VBox(
                                5,
                                roleLabel,
                                roleButton);

                Text statusLabel = new Text("Status");

                statusLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                13));

                Button statusButton = new Button(
                                "All Status        ˅");

                statusButton.setPrefWidth(150);
                statusButton.setPrefHeight(42);

                statusButton.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-alignment:CENTER_LEFT;" +
                                                "-fx-font-size:15px;");

                VBox statusBox = new VBox(
                                5,
                                statusLabel,
                                statusButton);

                Button filter = new Button("≡");

                filter.setPrefWidth(40);
                filter.setPrefHeight(42);

                filter.setStyle(
                                "-fx-background-color:#E8E5EB;" +
                                                "-fx-text-fill:#A83E00;" +
                                                "-fx-background-radius:7;");

                HBox filters = new HBox(
                                16,
                                searchBox,
                                roleBox,
                                statusBox,
                                filter);

                filters.setAlignment(
                                Pos.BOTTOM_LEFT);

                filters.setPadding(
                                new Insets(15));

                VBox filterPanel = new VBox(
                                filters);

                filterPanel.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text shopkeeperSectionTitle = new Text("Shopkeepers");
                shopkeeperSectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                shopkeeperSectionTitle.setFill(Color.web("#A83E00"));

                Text shopNameHeader = new Text("Name");
                Text shopRoleHeader = new Text("Role");
                Text shopStatusHeader = new Text("Status");
                Text shopLoginHeader = new Text("Last Login");
                Text shopActionsHeader = new Text("Actions");

                shopNameHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                shopRoleHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                shopStatusHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                shopLoginHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                shopActionsHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");

                VBox shopNameHeaderBox = new VBox(shopNameHeader);
                shopNameHeaderBox.setPrefWidth(220);

                VBox shopRoleHeaderBox = new VBox(shopRoleHeader);
                shopRoleHeaderBox.setPrefWidth(150);

                VBox shopStatusHeaderBox = new VBox(shopStatusHeader);
                shopStatusHeaderBox.setPrefWidth(140);

                VBox shopLoginHeaderBox = new VBox(shopLoginHeader);
                shopLoginHeaderBox.setPrefWidth(160);

                VBox shopActionsHeaderBox = new VBox(shopActionsHeader);
                shopActionsHeaderBox.setPrefWidth(150);

                HBox shopTableHeader = new HBox(
                                shopNameHeaderBox,
                                shopRoleHeaderBox,
                                shopStatusHeaderBox,
                                shopLoginHeaderBox,
                                shopActionsHeaderBox);

                shopTableHeader.setAlignment(Pos.CENTER_LEFT);
                shopTableHeader.setPadding(new Insets(12));
                shopTableHeader.setStyle("-fx-background-color:#F8F5FA;");

                Image userImage1 = null;
                ImageView userImageView1 = new ImageView(userImage1);
                userImageView1.setFitWidth(40);
                userImageView1.setFitHeight(40);
                userImageView1.setPreserveRatio(false);
                userImageView1.setClip(new Circle(20, 20, 20));

                Text user1 = new Text("Marcus Kinsley\nmarcus@localhost.com");
                user1.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

                HBox userBox1 = new HBox(10, userImageView1, user1);
                userBox1.setAlignment(Pos.CENTER_LEFT);
                userBox1.setPrefWidth(220);

                Text role1 = new Text("Shopkeeper");
                role1.setStyle("-fx-font-size:14px;-fx-background-color:#E5E3E4;-fx-padding:5px 10px;-fx-background-radius:12;");
                role1.setWrappingWidth(150);

                Text status1 = new Text("● Active");
                status1.setStyle("-fx-font-size:14px;-fx-fill:#1FA64B;-fx-font-weight:bold;");
                status1.setWrappingWidth(140);

                Text login1 = new Text("2 hours ago");
                login1.setFont(Font.font("Arial", 14));
                login1.setWrappingWidth(160);

                Text action1 = new Text("◉   ✎   ⊘");
                action1.setStyle("-fx-font-size:20px;-fx-fill:#666666;");
                action1.setWrappingWidth(150);

                HBox row1 = new HBox(userBox1, role1, status1, login1, action1);
                row1.setAlignment(Pos.CENTER_LEFT);
                row1.setPadding(new Insets(12));
                row1.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                Image userImage4 = null;
                ImageView userImageView4 = new ImageView(userImage4);
                userImageView4.setFitWidth(40);
                userImageView4.setFitHeight(40);
                userImageView4.setPreserveRatio(false);
                userImageView4.setClip(new Circle(20, 20, 20));

                Text user4 = new Text("Elena Rodriguez\nelena@freshfoods.io");
                user4.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

                HBox userBox4 = new HBox(10, userImageView4, user4);
                userBox4.setAlignment(Pos.CENTER_LEFT);
                userBox4.setPrefWidth(220);

                Text role4 = new Text("Shopkeeper");
                role4.setStyle("-fx-font-size:14px;-fx-background-color:#E5E3E4;-fx-padding:5px 10px;-fx-background-radius:12;");
                role4.setWrappingWidth(150);

                Text status4 = new Text("● Active");
                status4.setStyle("-fx-font-size:14px;-fx-fill:#1FA64B;-fx-font-weight:bold;");
                status4.setWrappingWidth(140);

                Text login4 = new Text("3 mins ago");
                login4.setFont(Font.font("Arial", 14));
                login4.setWrappingWidth(160);

                Text action4 = new Text("◉   ✎   ⊘");
                action4.setStyle("-fx-font-size:20px;-fx-fill:#666666;");
                action4.setWrappingWidth(150);

                HBox row4 = new HBox(userBox4, role4, status4, login4, action4);
                row4.setAlignment(Pos.CENTER_LEFT);
                row4.setPadding(new Insets(12));

                VBox shopkeeperTable = new VBox(
                                shopkeeperSectionTitle,
                                shopTableHeader,
                                row1,
                                row4);

                shopkeeperTable.setSpacing(0);
                shopkeeperTable.setPadding(new Insets(15));
                shopkeeperTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text customerSectionTitle = new Text("Customers");
                customerSectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                customerSectionTitle.setFill(Color.web("#A83E00"));

                Text customerNameHeader = new Text("Name");
                Text customerRoleHeader = new Text("Role");
                Text customerStatusHeader = new Text("Status");
                Text customerLoginHeader = new Text("Last Login");
                Text customerActionsHeader = new Text("Actions");

                customerNameHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                customerRoleHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                customerStatusHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                customerLoginHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                customerActionsHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");

                VBox customerNameHeaderBox = new VBox(customerNameHeader);
                customerNameHeaderBox.setPrefWidth(220);

                VBox customerRoleHeaderBox = new VBox(customerRoleHeader);
                customerRoleHeaderBox.setPrefWidth(150);

                VBox customerStatusHeaderBox = new VBox(customerStatusHeader);
                customerStatusHeaderBox.setPrefWidth(140);

                VBox customerLoginHeaderBox = new VBox(customerLoginHeader);
                customerLoginHeaderBox.setPrefWidth(160);

                VBox customerActionsHeaderBox = new VBox(customerActionsHeader);
                customerActionsHeaderBox.setPrefWidth(150);

                HBox customerTableHeader = new HBox(
                                customerNameHeaderBox,
                                customerRoleHeaderBox,
                                customerStatusHeaderBox,
                                customerLoginHeaderBox,
                                customerActionsHeaderBox);

                customerTableHeader.setAlignment(Pos.CENTER_LEFT);
                customerTableHeader.setPadding(new Insets(12));
                customerTableHeader.setStyle("-fx-background-color:#F8F5FA;");

                Image userImage2 = null;
                ImageView userImageView2 = new ImageView(userImage2);
                userImageView2.setFitWidth(40);
                userImageView2.setFitHeight(40);
                userImageView2.setPreserveRatio(false);
                userImageView2.setClip(new Circle(20, 20, 20));

                Text user2 = new Text("Sarah Chen\nsarah.c@gmail.com");
                user2.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

                HBox userBox2 = new HBox(10, userImageView2, user2);
                userBox2.setAlignment(Pos.CENTER_LEFT);
                userBox2.setPrefWidth(220);

                Text role2 = new Text("Customer");
                role2.setStyle("-fx-font-size:14px;-fx-background-color:#E5E3E4;-fx-padding:5px 10px;-fx-background-radius:12;");
                role2.setWrappingWidth(150);

                Text status2 = new Text("● Suspended");
                status2.setStyle("-fx-font-size:14px;-fx-fill:#E53935;-fx-font-weight:bold;");
                status2.setWrappingWidth(140);

                Text login2 = new Text("Yesterday, 4:15 PM");
                login2.setFont(Font.font("Arial", 14));
                login2.setWrappingWidth(160);

                Text action2 = new Text("◉   ✎   ↶");
                action2.setStyle("-fx-font-size:20px;-fx-fill:#666666;");
                action2.setWrappingWidth(150);

                HBox row2 = new HBox(userBox2, role2, status2, login2, action2);
                row2.setAlignment(Pos.CENTER_LEFT);
                row2.setPadding(new Insets(12));
                row2.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                Image userImage3 = null;
                ImageView userImageView3 = new ImageView(userImage3);
                userImageView3.setFitWidth(40);
                userImageView3.setFitHeight(40);
                userImageView3.setPreserveRatio(false);
                userImageView3.setClip(new Circle(20, 20, 20));

                Text user3 = new Text("Ben Jameson\nben.j@outlook.com");
                user3.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

                HBox userBox3 = new HBox(10, userImageView3, user3);
                userBox3.setAlignment(Pos.CENTER_LEFT);
                userBox3.setPrefWidth(220);

                Text role3 = new Text("Customer");
                role3.setStyle("-fx-font-size:14px;-fx-background-color:#E5E3E4;-fx-padding:5px 10px;-fx-background-radius:12;");
                role3.setWrappingWidth(150);

                Text status3 = new Text("● Active");
                status3.setStyle("-fx-font-size:14px;-fx-fill:#1FA64B;-fx-font-weight:bold;");
                status3.setWrappingWidth(140);

                Text login3 = new Text("Jun 12, 09:30 AM");
                login3.setFont(Font.font("Arial", 14));
                login3.setWrappingWidth(160);

                Text action3 = new Text("◉   ✎   ⊘");
                action3.setStyle("-fx-font-size:20px;-fx-fill:#666666;");
                action3.setWrappingWidth(150);

                HBox row3 = new HBox(userBox3, role3, status3, login3, action3);
                row3.setAlignment(Pos.CENTER_LEFT);
                row3.setPadding(new Insets(12));

                VBox customerTable = new VBox(
                                customerSectionTitle,
                                customerTableHeader,
                                row2,
                                row3);

                customerTable.setSpacing(0);
                customerTable.setPadding(new Insets(15));
                customerTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text deliverySectionTitle = new Text("Delivery Partners");
                deliverySectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                deliverySectionTitle.setFill(Color.web("#A83E00"));

                Text deliveryNameHeader = new Text("Name");
                Text deliveryRoleHeader = new Text("Role");
                Text deliveryStatusHeader = new Text("Status");
                Text deliveryLoginHeader = new Text("Last Login");
                Text deliveryActionsHeader = new Text("Actions");

                deliveryNameHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                deliveryRoleHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                deliveryStatusHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                deliveryLoginHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");
                deliveryActionsHeader.setStyle("-fx-font-size:14px;-fx-fill:#666666;");

                VBox deliveryNameHeaderBox = new VBox(deliveryNameHeader);
                deliveryNameHeaderBox.setPrefWidth(220);

                VBox deliveryRoleHeaderBox = new VBox(deliveryRoleHeader);
                deliveryRoleHeaderBox.setPrefWidth(150);

                VBox deliveryStatusHeaderBox = new VBox(deliveryStatusHeader);
                deliveryStatusHeaderBox.setPrefWidth(140);

                VBox deliveryLoginHeaderBox = new VBox(deliveryLoginHeader);
                deliveryLoginHeaderBox.setPrefWidth(160);

                VBox deliveryActionsHeaderBox = new VBox(deliveryActionsHeader);
                deliveryActionsHeaderBox.setPrefWidth(150);

                HBox deliveryTableHeader = new HBox(
                                deliveryNameHeaderBox,
                                deliveryRoleHeaderBox,
                                deliveryStatusHeaderBox,
                                deliveryLoginHeaderBox,
                                deliveryActionsHeaderBox);

                deliveryTableHeader.setAlignment(Pos.CENTER_LEFT);
                deliveryTableHeader.setPadding(new Insets(12));
                deliveryTableHeader.setStyle("-fx-background-color:#F8F5FA;");

                Image deliveryImage = null;
                ImageView deliveryImageView = new ImageView(deliveryImage);
                deliveryImageView.setFitWidth(40);
                deliveryImageView.setFitHeight(40);
                deliveryImageView.setPreserveRatio(false);
                deliveryImageView.setClip(new Circle(20, 20, 20));

                Text deliveryUser = new Text("Aarav Patel\naarav.delivery@gmail.com");
                deliveryUser.setStyle("-fx-font-size:14px;-fx-font-weight:bold;");

                HBox deliveryUserBox = new HBox(10, deliveryImageView, deliveryUser);
                deliveryUserBox.setAlignment(Pos.CENTER_LEFT);
                deliveryUserBox.setPrefWidth(220);

                Text deliveryRole = new Text("Delivery Partner");
                deliveryRole.setStyle("-fx-font-size:14px;-fx-background-color:#E5E3E4;-fx-padding:5px 10px;-fx-background-radius:12;");
                deliveryRole.setWrappingWidth(150);

                Text deliveryStatus = new Text("● Active");
                deliveryStatus.setStyle("-fx-font-size:14px;-fx-fill:#1FA64B;-fx-font-weight:bold;");
                deliveryStatus.setWrappingWidth(140);

                Text deliveryLogin = new Text("5 mins ago");
                deliveryLogin.setFont(Font.font("Arial", 14));
                deliveryLogin.setWrappingWidth(160);

                Text deliveryAction = new Text("◉   ✎   ⊘");
                deliveryAction.setStyle("-fx-font-size:20px;-fx-fill:#666666;");
                deliveryAction.setWrappingWidth(150);

                HBox deliveryRow = new HBox(
                                deliveryUserBox,
                                deliveryRole,
                                deliveryStatus,
                                deliveryLogin,
                                deliveryAction);

                deliveryRow.setAlignment(Pos.CENTER_LEFT);
                deliveryRow.setPadding(new Insets(12));

                VBox deliveryTable = new VBox(
                                deliverySectionTitle,
                                deliveryTableHeader,
                                deliveryRow);

                deliveryTable.setSpacing(0);
                deliveryTable.setPadding(new Insets(15));
                deliveryTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text showing = new Text("Showing 1 to 5 of 258 users");
                showing.setStyle("-fx-font-size:14px;-fx-fill:#555555;");

                Button previous = new Button("<");
                previous.setPrefWidth(30);
                previous.setPrefHeight(34);
                previous.setStyle("-fx-font-size:14px;");

                Button page1 = new Button("1");
                page1.setPrefWidth(30);
                page1.setPrefHeight(34);
                page1.setStyle(
                                "-fx-background-color:#B84300;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:5;" +
                                                "-fx-font-size:14px;");

                Button page2 = new Button("2");
                page2.setPrefWidth(30);
                page2.setPrefHeight(34);
                page2.setStyle("-fx-font-size:14px;");

                Button page3 = new Button("3");
                page3.setPrefWidth(30);
                page3.setPrefHeight(34);
                page3.setStyle("-fx-font-size:14px;");

                Text dots = new Text("...");

                Button page64 = new Button("64");
                page64.setPrefWidth(30);
                page64.setPrefHeight(34);
                page64.setStyle("-fx-font-size:14px;");

                Button next = new Button(">");
                next.setPrefWidth(30);
                next.setPrefHeight(34);
                next.setStyle("-fx-font-size:14px;");

                HBox pages = new HBox(5, previous, page1, page2, page3, dots, page64, next);
                pages.setAlignment(Pos.CENTER_RIGHT);

                Region pageGrow = new Region();
                HBox.setHgrow(pageGrow, Priority.ALWAYS);

                HBox tableBottom = new HBox(showing, pageGrow, pages);
                tableBottom.setAlignment(Pos.CENTER_LEFT);
                tableBottom.setSpacing(20);
                tableBottom.setPadding(new Insets(10));

                VBox userTable = new VBox(
                                15,
                                shopkeeperTable,
                                customerTable,
                                deliveryTable,
                                tableBottom);

                Text newUsers = new Text(
                                "New Users\n+42 this week");

                newUsers.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                VBox stat1 = new VBox(
                                5,
                                newUsers);

                stat1.setPadding(
                                new Insets(15));

                stat1.setPrefWidth(250);

                stat1.setStyle(
                                "-fx-background-color:#FFD9C5;" +
                                                "-fx-background-radius:10;");

                Text activeRates = new Text(
                                "Active Rates\n94.2%");

                activeRates.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;");

                VBox stat2 = new VBox(
                                5,
                                activeRates);

                stat2.setPadding(
                                new Insets(15));

                stat2.setPrefWidth(250);

                stat2.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-border-radius:10;");

                Text flagged = new Text(
                                "Flagged Accounts\n12");

                flagged.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-fill:#C0392B;");

                VBox stat3 = new VBox(
                                5,
                                flagged);

                stat3.setPadding(
                                new Insets(15));

                stat3.setPrefWidth(250);

                stat3.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-background-radius:10;" +
                                                "-fx-border-radius:10;");

                HBox statistics = new HBox(
                                18,
                                stat1,
                                stat2,
                                stat3);

                statistics.setAlignment(
                                Pos.CENTER);

                Text footer = new Text(
                                "© 2024 HyperLocal Admin Dashboard. All Rights Reserved.");

                footer.setStyle(
                                "-fx-font-size:12px;" +
                                                "-fx-fill:#666666;");

                HBox footerBox = new HBox(
                                footer);

                footerBox.setAlignment(
                                Pos.CENTER);

                footerBox.setPadding(
                                new Insets(12));

                rightBox.getChildren().addAll(
                                top,
                                pageHeader,
                                filterPanel,
                                userTable,
                                statistics,
                                footerBox);

                BorderPane root = new BorderPane();

                root.setLeft(left);

                javafx.scene.control.ScrollPane centerScroll = new javafx.scene.control.ScrollPane(rightBox);
                centerScroll.setFitToWidth(true);
                centerScroll.setHbarPolicy(javafx.scene.control.ScrollPane.ScrollBarPolicy.NEVER);
                centerScroll.setStyle("-fx-background-color:transparent;-fx-background:#FAF8FC;");
                root.setCenter(centerScroll);

                root.setStyle(
                                "-fx-background-color:#FAF8FC;");

                Scene scene = new Scene(
                                root,
                                1550,
                                850);
                return scene;
        }

}
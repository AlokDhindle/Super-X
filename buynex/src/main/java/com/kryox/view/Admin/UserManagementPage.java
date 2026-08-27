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
                                                21));

                logo.setFill(
                                Color.web("#A83E00"));

                Text controller = new Text(
                                "Marketplace Controller");

                controller.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

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

                iv1.setFitWidth(16);
                iv1.setFitHeight(16);
                iv1.setPreserveRatio(true);

                Text dashboardText = new Text(
                                "Dashboard");

                dashboardText.setFill(
                                Color.web("#333333"));

                dashboardText.setFont(
                                Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

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

                Image img2 = new Image(
                                getClass().getResource("/assets/images/admin/admin logo.png").toExternalForm());

                ImageView iv2 = new ImageView(img2);

                iv2.setFitWidth(16);
                iv2.setFitHeight(16);
                iv2.setPreserveRatio(true);

                Text usersText = new Text("Users");

                usersText.setFill(Color.WHITE);

                usersText.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

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

                iv3.setFitWidth(16);
                iv3.setFitHeight(16);
                iv3.setPreserveRatio(true);

                Text shopsText = new Text("Shops");

                shopsText.setFill(
                                Color.web("#333333"));

                shopsText.setFont(
                                Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

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

                iv4.setFitWidth(16);
                iv4.setFitHeight(16);
                iv4.setPreserveRatio(true);

                Text offersText = new Text("Offers");

                offersText.setFill(
                                Color.web("#333333"));

                offersText.setFont(
                                Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

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

                iv5.setFitWidth(16);
                iv5.setFitHeight(16);
                iv5.setPreserveRatio(true);

                Text analyticsText = new Text(
                                "Analytics");

                analyticsText.setFill(
                                Color.web("#333333"));

                analyticsText.setFont(
                                Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

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

                iv6.setFitWidth(16);
                iv6.setFitHeight(16);
                iv6.setPreserveRatio(true);

                Text settingsText = new Text(
                                "Settings");

                settingsText.setFill(
                                Color.web("#333333"));

                settingsText.setFont(
                                Font.font("Arial", 12));

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

                iv7.setFitWidth(16);
                iv7.setFitHeight(16);
                iv7.setPreserveRatio(true);

                Text supportText = new Text(
                                "Support");

                supportText.setFill(
                                Color.web("#333333"));

                supportText.setFont(
                                Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

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
                                                        12));

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
                                        Font.font("Arial", 12));

                        ScaleTransition st = new ScaleTransition(
                                        Duration.millis(120),
                                        support);

                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                javafx.scene.shape.Circle avatar = new javafx.scene.shape.Circle(
                                16,
                                Color.web("#D9B79C"));

                Text alex = new Text("Alex Rivera");

                alex.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                11));

                Text superAdmin = new Text(
                                "Super Admin");

                superAdmin.setFont(
                                Font.font("Arial", 9));

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
                                Font.font(20));

                TextField topSearch = new TextField();

                topSearch.setPromptText(
                                "Search anything...");

                topSearch.setPrefWidth(260);

                topSearch.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:18;");

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
                iv8.setFitWidth(18);
                iv8.setFitHeight(18);
                iv8.setPreserveRatio(true);

                Image notification = new Image(getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView iv9 = new ImageView(notification);
                iv9.setFitWidth(18);
                iv9.setFitHeight(18);
                // iv9.setPreserveRatio(true);

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

                notificationText1.setStyle("-fx-font-size:11px;");

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

                notificationText2.setStyle("-fx-font-size:11px;");

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

                notificationText3.setStyle("-fx-font-size:11px;");

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
                                                15));

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
                                                25));

                Text pageSubtitle = new Text(
                                "Manage and monitor customers and shopkeeper accounts.");

                pageSubtitle.setFont(
                                Font.font("Arial", 12));

                pageSubtitle.setFill(
                                Color.web("#777777"));

                VBox titleBox = new VBox(
                                5,
                                pageTitle,
                                pageSubtitle);

                Button addUser = new Button(
                                "+  Add New User");

                addUser.setPrefWidth(145);
                addUser.setPrefHeight(40);

                addUser.setStyle(
                                "-fx-background-color:#F65F00;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;");
                addUser.setOnMouseClicked(event ->{

                        AddNewUserPage newuser = new AddNewUserPage();
                        Homepage.HomepageStage.setScene(newuser.getAddUserScene());
                });
                Button export = new Button(
                                "↓  Export");

                export.setPrefWidth(100);
                export.setPrefHeight(40);

                export.setStyle(
                                "-fx-background-color:#E7E5EB;" +
                                                "-fx-text-fill:#222222;" +
                                                "-fx-font-weight:bold;" +
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
                                                9));

                TextField userSearch = new TextField();

                userSearch.setPromptText(
                                "e.g. John Doe");

                userSearch.setPrefWidth(300);
                userSearch.setPrefHeight(36);

                userSearch.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;");

                VBox searchBox = new VBox(
                                5,
                                searchLabel,
                                userSearch);

                Text roleLabel = new Text("Role");

                roleLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                Button roleButton = new Button(
                                "All Roles        ˅");

                roleButton.setPrefWidth(150);
                roleButton.setPrefHeight(36);

                roleButton.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-alignment:CENTER_LEFT;");

                VBox roleBox = new VBox(
                                5,
                                roleLabel,
                                roleButton);

                Text statusLabel = new Text("Status");

                statusLabel.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                Button statusButton = new Button(
                                "All Status        ˅");

                statusButton.setPrefWidth(150);
                statusButton.setPrefHeight(36);

                statusButton.setStyle(
                                "-fx-background-color:#F8F5FA;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-alignment:CENTER_LEFT;");

                VBox statusBox = new VBox(
                                5,
                                statusLabel,
                                statusButton);

                Button filter = new Button("≡");

                filter.setPrefWidth(40);
                filter.setPrefHeight(36);

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

                Text nameHeader = new Text("Name");
                Text roleHeader = new Text("Role");
                Text statusHeader = new Text("Status");
                Text loginHeader = new Text("Last Login");
                Text actionsHeader = new Text("Actions");

                nameHeader.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#666666;");

                roleHeader.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#666666;");

                statusHeader.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#666666;");

                loginHeader.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#666666;");

                actionsHeader.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#666666;");

                VBox nameHeaderBox = new VBox(nameHeader);
                nameHeaderBox.setPrefWidth(220);

                VBox roleHeaderBox = new VBox(roleHeader);
                roleHeaderBox.setPrefWidth(150);

                VBox statusHeaderBox = new VBox(statusHeader);
                statusHeaderBox.setPrefWidth(140);

                VBox loginHeaderBox = new VBox(loginHeader);
                loginHeaderBox.setPrefWidth(160);

                VBox actionsHeaderBox = new VBox(actionsHeader);
                actionsHeaderBox.setPrefWidth(150);

                HBox tableHeader = new HBox(
                                nameHeaderBox,
                                roleHeaderBox,
                                statusHeaderBox,
                                loginHeaderBox,
                                actionsHeaderBox);

                tableHeader.setAlignment(Pos.CENTER_LEFT);

                tableHeader.setPadding(
                                new Insets(12));

                Image userImage1 = null;
                ImageView userImageView1 = new ImageView(userImage1);
                userImageView1.setFitWidth(34);
                userImageView1.setFitHeight(34);
                userImageView1.setPreserveRatio(false);
                userImageView1.setClip(new Circle(17, 17, 17));

                Text user1 = new Text(
                                "Marcus Kinsley\nmarcus@localhost.com");

                user1.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");
                HBox userBox1 = new HBox(10, userImageView1, user1);
                userBox1.setAlignment(Pos.CENTER_LEFT);
                userBox1.setPrefWidth(220);

                Text role1 = new Text("Shopkeeper");

                role1.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-background-color:#E5E3E4;" +
                                                "-fx-padding:5px 10px;" +
                                                "-fx-background-radius:12;");
                role1.setWrappingWidth(150);

                Text status1 = new Text(
                                "● Active");

                status1.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#1FA64B;" +
                                                "-fx-font-weight:bold;");
                status1.setWrappingWidth(140);

                Text login1 = new Text(
                                "2 hours ago");

                login1.setFont(Font.font("Arial", 10));
                login1.setWrappingWidth(160);

                Text action1 = new Text("◉   ✎   ⊘");

                action1.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-fill:#666666;");
                action1.setWrappingWidth(150);

                HBox row1 = new HBox(
                                userBox1,
                                role1,
                                status1,
                                login1,
                                action1);

                row1.setSpacing(0);

                row1.setAlignment(
                                Pos.CENTER_LEFT);

                row1.setPadding(
                                new Insets(12));
                row1.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                Image userImage2 = null;
                ImageView userImageView2 = new ImageView(userImage2);
                userImageView2.setFitWidth(34);
                userImageView2.setFitHeight(34);
                userImageView2.setPreserveRatio(false);
                userImageView2.setClip(new Circle(17, 17, 17));

                Text user2 = new Text(
                                "Sarah Chen\nsarah.c@gmail.com");

                user2.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");
                HBox userBox2 = new HBox(10, userImageView2, user2);
                userBox2.setAlignment(Pos.CENTER_LEFT);
                userBox2.setPrefWidth(220);

                Text role2 = new Text("Customer");

                role2.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-background-color:#E5E3E4;" +
                                                "-fx-padding:5px 10px;" +
                                                "-fx-background-radius:12;");
                role2.setWrappingWidth(150);

                Text status2 = new Text(
                                "● Suspended");

                status2.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#E53935;" +
                                                "-fx-font-weight:bold;");
                status2.setWrappingWidth(140);

                Text login2 = new Text(
                                "Yesterday, 4:15 PM");

                login2.setFont(
                                Font.font("Arial", 10));
                login2.setWrappingWidth(160);

                Text action2 = new Text(
                                "◉   ✎   ↶");

                action2.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-fill:#666666;");
                action2.setWrappingWidth(150);

                HBox row2 = new HBox(
                                userBox2,
                                role2,
                                status2,
                                login2,
                                action2);

                row2.setSpacing(0);

                row2.setAlignment(
                                Pos.CENTER_LEFT);

                row2.setPadding(
                                new Insets(12));
                row2.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                Image userImage3 = null;
                ImageView userImageView3 = new ImageView(userImage3);
                userImageView3.setFitWidth(34);
                userImageView3.setFitHeight(34);
                userImageView3.setPreserveRatio(false);
                userImageView3.setClip(new Circle(17, 17, 17));

                Text user3 = new Text(
                                "Ben Jameson\nben.j@outlook.com");

                user3.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");
                HBox userBox3 = new HBox(10, userImageView3, user3);
                userBox3.setAlignment(Pos.CENTER_LEFT);
                userBox3.setPrefWidth(220);

                Text role3 = new Text("Customer");

                role3.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-background-color:#E5E3E4;" +
                                                "-fx-padding:5px 10px;" +
                                                "-fx-background-radius:12;");
                role3.setWrappingWidth(150);

                Text status3 = new Text(
                                "● Active");

                status3.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#1FA64B;" +
                                                "-fx-font-weight:bold;");
                status3.setWrappingWidth(140);

                Text login3 = new Text(
                                "Jun 12, 09:30 AM");

                login3.setFont(
                                Font.font("Arial", 10));
                login3.setWrappingWidth(160);

                Text action3 = new Text(
                                "◉   ✎   ⊘");

                action3.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-fill:#666666;");
                action3.setWrappingWidth(150);

                HBox row3 = new HBox(
                                userBox3,
                                role3,
                                status3,
                                login3,
                                action3);

                row3.setSpacing(0);

                row3.setAlignment(
                                Pos.CENTER_LEFT);

                row3.setPadding(
                                new Insets(12));
                row3.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                Image userImage4 = null;
                ImageView userImageView4 = new ImageView(userImage4);
                userImageView4.setFitWidth(34);
                userImageView4.setFitHeight(34);
                userImageView4.setPreserveRatio(false);
                userImageView4.setClip(new Circle(17, 17, 17));

                Text user4 = new Text(
                                "Elena Rodriguez\nelena@freshfoods.io");

                user4.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");
                HBox userBox4 = new HBox(10, userImageView4, user4);
                userBox4.setAlignment(Pos.CENTER_LEFT);
                userBox4.setPrefWidth(220);

                Text role4 = new Text("Shopkeeper");

                role4.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-background-color:#E5E3E4;" +
                                                "-fx-padding:5px 10px;" +
                                                "-fx-background-radius:12;");
                role4.setWrappingWidth(150);

                Text status4 = new Text(
                                "● Active");

                status4.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#1FA64B;" +
                                                "-fx-font-weight:bold;");
                status4.setWrappingWidth(140);

                Text login4 = new Text(
                                "3 mins ago");

                login4.setFont(
                                Font.font("Arial", 10));
                login4.setWrappingWidth(160);

                Text action4 = new Text(
                                "◉   ✎   ⊘");

                action4.setStyle(
                                "-fx-font-size:15px;" +
                                                "-fx-fill:#666666;");
                action4.setWrappingWidth(150);

                HBox row4 = new HBox(
                                userBox4,
                                role4,
                                status4,
                                login4,
                                action4);

                row4.setSpacing(0);

                row4.setAlignment(
                                Pos.CENTER_LEFT);

                row4.setPadding(
                                new Insets(12));
                row4.setStyle("-fx-border-color:transparent transparent #F0EDF2 transparent;");

                VBox table = new VBox(tableHeader, row1, row2, row3, row4);

                table.setStyle(
                                "-fx-background-color:white;");

                Text showing = new Text(
                                "Showing 1 to 4 of 258 users");

                showing.setStyle(
                                "-fx-font-size:10px;" +
                                                "-fx-fill:#555555;");

                Button previous = new Button("<");
                previous.setPrefWidth(30);

                Button page1 = new Button("1");
                page1.setPrefWidth(30);

                page1.setStyle(
                                "-fx-background-color:#B84300;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:5;");

                Button page2 = new Button("2");
                page2.setPrefWidth(30);

                Button page3 = new Button("3");
                page3.setPrefWidth(30);

                Text dots = new Text("...");

                Button page64 = new Button("64");
                page64.setPrefWidth(30);

                Button next = new Button(">");
                next.setPrefWidth(30);

                HBox pages = new HBox(
                                5,
                                previous,
                                page1,
                                page2,
                                page3,
                                dots,
                                page64,
                                next);

                pages.setAlignment(
                                Pos.CENTER_RIGHT);

                Region pageGrow = new Region();
                HBox.setHgrow(pageGrow, Priority.ALWAYS);

                HBox tableBottom = new HBox(
                                showing,
                                pageGrow,
                                pages);

                tableBottom.setAlignment(
                                Pos.CENTER_LEFT);

                tableBottom.setSpacing(20);

                tableBottom.setPadding(
                                new Insets(10));

                VBox userTable = new VBox(
                                table,
                                tableBottom);

                userTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text newUsers = new Text(
                                "New Users\n+42 this week");

                newUsers.setStyle(
                                "-fx-font-size:12px;" +
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
                                "-fx-font-size:12px;" +
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
                                "-fx-font-size:12px;" +
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
                                "-fx-font-size:9px;" +
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
                root.setCenter(rightBox);

                root.setStyle(
                                "-fx-background-color:#FAF8FC;");

                Scene scene = new Scene(
                                root,
                                1550,
                                850);
                return scene;
        }

}
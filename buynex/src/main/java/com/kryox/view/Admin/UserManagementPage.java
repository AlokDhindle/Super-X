package com.kryox.view.Admin;

import java.util.List;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Customer.Userstorecontroller;
import com.kryox.model.Customer.User;
import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
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
                                "-fx-background-color: #ebccb7");

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
                deliveryText.setFont(Font.font("Arial", 15));

                delivery.getChildren().addAll(
                                deliveryIcon,
                                deliveryText);

                delivery.setOnMouseEntered(e -> {
                        delivery.setStyle(
                                        "-fx-background-color:#D94F00;" +
                                                        "-fx-background-radius:10;");

                        deliveryText.setFill(Color.WHITE);
                        deliveryText.setFont(
                                        Font.font("Arial", FontWeight.BOLD, 15));

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
                        deliveryText.setFont(Font.font("Arial", 15));

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
                                delivery,
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
                settings.setOnMouseClicked(e->{
                        SettingsPage setting = new SettingsPage();
                        Homepage.HomepageStage.setScene(setting.getUserScene());
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
                support.setOnMouseClicked(e->{
                        SupportPage supports = new SupportPage();
                        Homepage.HomepageStage.setScene(supports.getUserScene());
                });

                // PROFILE
                // =========================

                AdminProfileCard adminProfileCard =
                                new AdminProfileCard();

                HBox profile =
                                adminProfileCard.getProfileCard();

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
                rightBox.setPadding(new Insets(20, 25, 20, 25));

                rightBox.setStyle(
                                "-fx-background-color: #eee5df;");

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

                HBox topRight =
                                createTopActions();

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
                shopNameHeaderBox.setMinWidth(220);
                shopNameHeaderBox.setMaxWidth(220);

                VBox shopRoleHeaderBox = new VBox(shopRoleHeader);
                shopRoleHeaderBox.setPrefWidth(150);
                shopRoleHeaderBox.setMinWidth(150);
                shopRoleHeaderBox.setMaxWidth(150);

                VBox shopStatusHeaderBox = new VBox(shopStatusHeader);
                shopStatusHeaderBox.setPrefWidth(140);
                shopStatusHeaderBox.setMinWidth(140);
                shopStatusHeaderBox.setMaxWidth(140);

                VBox shopLoginHeaderBox = new VBox(shopLoginHeader);
                shopLoginHeaderBox.setPrefWidth(160);
                shopLoginHeaderBox.setMinWidth(160);
                shopLoginHeaderBox.setMaxWidth(160);

                VBox shopActionsHeaderBox = new VBox(shopActionsHeader);
                shopActionsHeaderBox.setPrefWidth(150);
                shopActionsHeaderBox.setMinWidth(150);
                shopActionsHeaderBox.setMaxWidth(150);

                HBox shopTableHeader = new HBox(
                                shopNameHeaderBox,
                                shopRoleHeaderBox,
                                shopStatusHeaderBox,
                                shopLoginHeaderBox,
                                shopActionsHeaderBox);

                shopTableHeader.setAlignment(Pos.CENTER_LEFT);
                shopTableHeader.setPadding(new Insets(12));
                shopTableHeader.setStyle("-fx-background-color:#F8F5FA;");

                VBox shopkeeperRows = new VBox();

                Text loadingShopkeepers = new Text("Loading shopkeepers...");
                loadingShopkeepers.setStyle("-fx-font-size:14px;-fx-fill:#777777;");
                shopkeeperRows.getChildren().add(loadingShopkeepers);

                VBox shopkeeperTable = new VBox(
                                shopkeeperSectionTitle,
                                shopTableHeader,
                                shopkeeperRows);

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
                customerNameHeaderBox.setMinWidth(220);
                customerNameHeaderBox.setMaxWidth(220);

                VBox customerRoleHeaderBox = new VBox(customerRoleHeader);
                customerRoleHeaderBox.setPrefWidth(150);
                customerRoleHeaderBox.setMinWidth(150);
                customerRoleHeaderBox.setMaxWidth(150);

                VBox customerStatusHeaderBox = new VBox(customerStatusHeader);
                customerStatusHeaderBox.setPrefWidth(140);
                customerStatusHeaderBox.setMinWidth(140);
                customerStatusHeaderBox.setMaxWidth(140);

                VBox customerLoginHeaderBox = new VBox(customerLoginHeader);
                customerLoginHeaderBox.setPrefWidth(160);
                customerLoginHeaderBox.setMinWidth(160);
                customerLoginHeaderBox.setMaxWidth(160);

                VBox customerActionsHeaderBox = new VBox(customerActionsHeader);
                customerActionsHeaderBox.setPrefWidth(150);
                customerActionsHeaderBox.setMinWidth(150);
                customerActionsHeaderBox.setMaxWidth(150);

                HBox customerTableHeader = new HBox(
                                customerNameHeaderBox,
                                customerRoleHeaderBox,
                                customerStatusHeaderBox,
                                customerLoginHeaderBox,
                                customerActionsHeaderBox);

                customerTableHeader.setAlignment(Pos.CENTER_LEFT);
                customerTableHeader.setPadding(new Insets(12));
                customerTableHeader.setStyle("-fx-background-color:#F8F5FA;");

                VBox customerRows = new VBox();

                Text loadingCustomers = new Text("Loading customers...");
                loadingCustomers.setStyle("-fx-font-size:14px;-fx-fill:#777777;");
                customerRows.getChildren().add(loadingCustomers);

                VBox customerTable = new VBox(
                                customerSectionTitle,
                                customerTableHeader,
                                customerRows);

                customerTable.setSpacing(0);
                customerTable.setPadding(new Insets(15));
                customerTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Thread userThread = new Thread(() -> {

                        try {
                                Firestore db = Firebaseconfig.gFirestore();

                                QuerySnapshot snapshot = db
                                                .collection("User")
                                                .get()
                                                .get();

                                List<QueryDocumentSnapshot> allUsers = snapshot.getDocuments();

                                Platform.runLater(() -> {

                                        shopkeeperRows.getChildren().clear();
                                        customerRows.getChildren().clear();

                                        int shopkeeperCount = 0;
                                        int customerCount = 0;

                                        for (QueryDocumentSnapshot document : allUsers) {

                                                String userRole = getStringValue(
                                                                document,
                                                                "role");

                                                if (userRole.isBlank()) {
                                                        userRole = "Customer";
                                                }

                                                if (userRole.equalsIgnoreCase("Shopkeeper")) {
                                                        shopkeeperCount++;

                                                        HBox row = createDatabaseUserRow(
                                                                        document,
                                                                        "Shopkeeper");

                                                        shopkeeperRows.getChildren().add(row);

                                                } else if (userRole.equalsIgnoreCase("Customer")) {
                                                        customerCount++;

                                                        HBox row = createDatabaseUserRow(
                                                                        document,
                                                                        "Customer");

                                                        customerRows.getChildren().add(row);
                                                }
                                        }

                                        if (shopkeeperCount == 0) {
                                                Text noShopkeeper = new Text(
                                                                "No shopkeepers found in Firebase.");

                                                noShopkeeper.setStyle(
                                                                "-fx-font-size:14px;" +
                                                                                "-fx-fill:#777777;");

                                                shopkeeperRows.getChildren().add(noShopkeeper);
                                        }

                                        if (customerCount == 0) {
                                                Text noCustomer = new Text(
                                                                "No customers found in Firebase.");

                                                noCustomer.setStyle(
                                                                "-fx-font-size:14px;" +
                                                                                "-fx-fill:#777777;");

                                                customerRows.getChildren().add(noCustomer);
                                        }
                                });

                        } catch (Exception exception) {

                                exception.printStackTrace();

                                Platform.runLater(() -> {

                                        shopkeeperRows.getChildren().clear();
                                        customerRows.getChildren().clear();

                                        Text shopError = new Text(
                                                        "Unable to load shopkeepers.");

                                        shopError.setStyle(
                                                        "-fx-font-size:14px;" +
                                                                        "-fx-fill:#E53935;");

                                        Text customerError = new Text(
                                                        "Unable to load customers.");

                                        customerError.setStyle(
                                                        "-fx-font-size:14px;" +
                                                                        "-fx-fill:#E53935;");

                                        shopkeeperRows.getChildren().add(shopError);
                                        customerRows.getChildren().add(customerError);
                                });
                        }
                });

                userThread.setDaemon(true);
                userThread.start();

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

                deliveryUser.setWrappingWidth(155);

                HBox deliveryUserBox = new HBox(10, deliveryImageView, deliveryUser);
                deliveryUserBox.setAlignment(Pos.CENTER_LEFT);
                deliveryUserBox.setPrefWidth(220);
                deliveryUserBox.setMinWidth(220);
                deliveryUserBox.setMaxWidth(220);

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
                                "-fx-background-color: #eee5df;");

                Scene scene = new Scene(
                                root,
                                1550,
                                850);
                return scene;
        }


        private HBox createDatabaseUserRow(
                        QueryDocumentSnapshot document,
                        String fallbackRole) {

                String name = getStringValue(document, "name");
                String email = getStringValue(document, "email");
                String role = getStringValue(document, "role");
                String status = getStringValue(document, "status");
                String profileUrl = getStringValue(document, "profileImageUrl");

                if (name.isBlank()) {
                        name = "Unknown";
                }

                if (email.isBlank()) {
                        email = "-";
                }

                if (role.isBlank()) {
                        role = fallbackRole;
                }

                if (status.isBlank()) {
                        status = "Active";
                }

                StackPane avatar = new StackPane();
                avatar.setPrefSize(40, 40);
                avatar.setMinSize(40, 40);
                avatar.setMaxSize(40, 40);

                Circle avatarCircle = new Circle(
                                20,
                                Color.web("#FFE5D5"));

                avatar.getChildren().add(avatarCircle);

                if (!profileUrl.isBlank()) {
                        try {
                                Image image = new Image(
                                                profileUrl,
                                                40,
                                                40,
                                                false,
                                                true,
                                                true);

                                ImageView imageView = new ImageView(image);
                                imageView.setFitWidth(40);
                                imageView.setFitHeight(40);
                                imageView.setPreserveRatio(false);
                                imageView.setClip(new Circle(20, 20, 20));

                                avatar.getChildren().add(imageView);

                        } catch (Exception ignored) {
                                String firstLetter = name.substring(0, 1).toUpperCase();

                                Text avatarText = new Text(firstLetter);
                                avatarText.setFont(
                                                Font.font(
                                                                "Arial",
                                                                FontWeight.BOLD,
                                                                15));

                                avatarText.setFill(Color.web("#A83E00"));
                                avatar.getChildren().add(avatarText);
                        }

                } else {
                        String firstLetter = name.substring(0, 1).toUpperCase();

                        Text avatarText = new Text(firstLetter);
                        avatarText.setFont(
                                        Font.font(
                                                        "Arial",
                                                        FontWeight.BOLD,
                                                        15));

                        avatarText.setFill(Color.web("#A83E00"));
                        avatar.getChildren().add(avatarText);
                }

                Text userInfo = new Text(
                                name + "\n" + email);

                userInfo.setStyle(
                                "-fx-font-size:14px;" +
                                                "-fx-font-weight:bold;");

                userInfo.setWrappingWidth(155);

                HBox nameBox = new HBox(
                                10,
                                avatar,
                                userInfo);

                nameBox.setAlignment(Pos.CENTER_LEFT);
                nameBox.setPrefWidth(220);
                nameBox.setMinWidth(220);
                nameBox.setMaxWidth(220);

                Text roleText = new Text(role);
                roleText.setStyle(
                                "-fx-font-size:14px;" +
                                                "-fx-background-color:#E5E3E4;" +
                                                "-fx-padding:5px 10px;" +
                                                "-fx-background-radius:12;");
                roleText.setWrappingWidth(150);

                Text statusText = new Text("● " + status);

                if (status.equalsIgnoreCase("Active")) {
                        statusText.setStyle(
                                        "-fx-font-size:14px;" +
                                                        "-fx-fill:#1FA64B;" +
                                                        "-fx-font-weight:bold;");
                } else {
                        statusText.setStyle(
                                        "-fx-font-size:14px;" +
                                                        "-fx-fill:#D9534F;" +
                                                        "-fx-font-weight:bold;");
                }

                statusText.setWrappingWidth(140);

                Text lastLogin = new Text("-");
                lastLogin.setFont(Font.font("Arial", 14));
                lastLogin.setWrappingWidth(160);

                Text action = new Text("◉   ✎   ⊘");
                action.setStyle(
                                "-fx-font-size:20px;" +
                                                "-fx-fill:#666666;");
                action.setWrappingWidth(150);

                HBox row = new HBox(
                                nameBox,
                                roleText,
                                statusText,
                                lastLogin,
                                action);

                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12));
                row.setStyle(
                                "-fx-border-color:transparent transparent #F0EDF2 transparent;");

                return row;
        }

        private String getStringValue(
                        QueryDocumentSnapshot document,
                        String fieldName) {

                Object value = document.get(fieldName);

                if (value == null) {
                        return "";
                }

                return String.valueOf(value).trim();
        }



    private HBox createTopActions() {

        Image notificationImage = new Image(
                getClass().getResource("/assets/images/admin/bell.png").toExternalForm());

        ImageView notificationIcon = new ImageView(notificationImage);
        notificationIcon.setFitWidth(22);
        notificationIcon.setFitHeight(22);
        notificationIcon.setPreserveRatio(true);

        Text notificationName = new Text("Notifications");
        notificationName.setFont(Font.font("Arial", 14));

        HBox notificationAction = new HBox(
                6,
                notificationIcon,
                notificationName);

        notificationAction.setAlignment(Pos.CENTER_LEFT);
        notificationAction.setStyle("-fx-cursor:hand;");

        Image chatbotImage = new Image(
                getClass().getResource("/assets/images/admin/message.png").toExternalForm());

        ImageView chatbotIcon = new ImageView(chatbotImage);
        chatbotIcon.setFitWidth(22);
        chatbotIcon.setFitHeight(22);
        chatbotIcon.setPreserveRatio(true);

        Text chatbotName = new Text("ChatBot");
        chatbotName.setFont(Font.font("Arial", 14));

        HBox chatbotAction = new HBox(
                6,
                chatbotIcon,
                chatbotName);

        chatbotAction.setAlignment(Pos.CENTER_LEFT);
        chatbotAction.setStyle("-fx-cursor:hand;");

        chatbotAction.setOnMouseClicked(e -> {

            SmartAssistantUI chatPage =
                    new SmartAssistantUI();

            Homepage.HomepageStage.setScene(
                    chatPage.getUserScene());
        });

        javafx.stage.Popup notificationPopup =
                new javafx.stage.Popup();

        Text notificationTitle =
                new Text("Notifications");

        notificationTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        Button markRead =
                new Button("Mark all as read");

        markRead.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:#E65300;" +
                "-fx-font-size:13px;");

        Region notificationGrow =
                new Region();

        HBox.setHgrow(
                notificationGrow,
                Priority.ALWAYS);

        HBox notificationHeader =
                new HBox(
                        notificationTitle,
                        notificationGrow,
                        markRead);

        notificationHeader.setAlignment(
                Pos.CENTER_LEFT);

        Text notificationText1 =
                new Text(
                        "New Shop Registration\n" +
                        "Tech Haven needs verification\n" +
                        "2 mins ago");

        notificationText1.setStyle(
                "-fx-font-size:13px;");

        VBox notification1 =
                new VBox(notificationText1);

        notification1.setPadding(
                new Insets(12));

        notification1.setStyle(
                "-fx-background-color:#FFF4ED;" +
                "-fx-background-radius:8;");

        Text notificationText2 =
                new Text(
                        "New User Joined\n" +
                        "New customer account created\n" +
                        "10 mins ago");

        notificationText2.setStyle(
                "-fx-font-size:13px;");

        VBox notification2 =
                new VBox(notificationText2);

        notification2.setPadding(
                new Insets(12));

        notification2.setStyle(
                "-fx-background-color:#F4FFF7;" +
                "-fx-background-radius:8;");

        Text notificationText3 =
                new Text(
                        "Flagged Account\n" +
                        "Suspicious activity detected\n" +
                        "1 hour ago");

        notificationText3.setStyle(
                "-fx-font-size:13px;");

        VBox notification3 =
                new VBox(notificationText3);

        notification3.setPadding(
                new Insets(12));

        notification3.setStyle(
                "-fx-background-color:#FFF5F5;" +
                "-fx-background-radius:8;");

        VBox notificationBox =
                new VBox(
                        12,
                        notificationHeader,
                        new Separator(),
                        notification1,
                        notification2,
                        notification3);

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

        notificationAction.setOnMouseClicked(e -> {

            if (notificationPopup.isShowing()) {

                notificationPopup.hide();

            } else {

                javafx.geometry.Bounds bellPosition =
                        notificationAction.localToScreen(
                                notificationAction.getBoundsInLocal());

                if (bellPosition != null) {

                    notificationPopup.show(
                            notificationAction,
                            bellPosition.getMaxX() - 330,
                            bellPosition.getMaxY() + 10);
                }
            }
        });

        markRead.setOnAction(e -> {

            notification1.setStyle(
                    "-fx-background-color:white;");

            notification2.setStyle(
                    "-fx-background-color:white;");

            notification3.setStyle(
                    "-fx-background-color:white;");
        });

        HBox topActions =
                new HBox(
                        24,
                        notificationAction,
                        chatbotAction);

        topActions.setAlignment(
                Pos.CENTER_RIGHT);

        return topActions;
    }

}
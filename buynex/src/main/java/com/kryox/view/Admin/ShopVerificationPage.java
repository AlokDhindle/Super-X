package com.kryox.view.Admin;

import java.awt.Desktop;
import java.net.URI;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShopVerificationPage {

        public Scene getUserScene() {
                
                BorderPane root = new BorderPane();
                root.setStyle("-fx-background-color:#F9F7FB;");

                VBox left = new VBox();
                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));
                left.setStyle("-fx-background-color:#F3E3D3;");

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
                                        "-fx-background-color:transprant;" +
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
                dashboard.setOnMouseClicked(e -> {

                        AdminDashboardPage dashboardPage = new AdminDashboardPage();

                        Homepage.HomepageStage.setScene(
                                        dashboardPage.getUserScene());
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
                                        "-fx-background-color:#D94F00;" +
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
                settings.setOnMouseClicked(e -> {

                        SettingsPage settingsPage = new SettingsPage();

                        Homepage.HomepageStage.setScene(
                                        settingsPage.getUserScene());
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
                support.setOnMouseClicked(e -> {

                        SupportPage supportPage = new SupportPage();

                        Homepage.HomepageStage.setScene(
                                        supportPage.getUserScene());
                });

                // =========================
                // PROFILE
                // =========================

                Button exit = new Button("Exit");
                exit.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

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
                
                VBox rightBox = new VBox();
                rightBox.setSpacing(20);
                rightBox.setPadding(new Insets(30));

                HBox pageHeader = new HBox();

                pageHeader.setAlignment(
                                Pos.CENTER_LEFT);

                VBox headingText = new VBox(4);

                Text title = new Text(
                                "Shop Verification");

                title.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                32));

                title.setFill(
                                Color.web("#B84300"));

                Text subtitle = new Text(
                                "Review and approve pending registrations to maintain ecosystem quality.");

                subtitle.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                subtitle.setFill(
                                Color.web("#777777"));

                headingText.getChildren().addAll(
                                title,
                                subtitle);

                Region headerSpace = new Region();

                HBox.setHgrow(
                                headerSpace,
                                Priority.ALWAYS);

                // =====================================================
                // PENDING BOX
                // =====================================================

                VBox pending = new VBox(2);

                pending.setAlignment(
                                Pos.CENTER);

                pending.setPadding(
                                new Insets(8, 15, 8, 15));

                pending.setStyle(
                                "-fx-background-color:#EBE9EF;" +
                                                "-fx-background-radius:8;");

                Text pendingTitle = new Text(
                                "PENDING");

                pendingTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

                pendingTitle.setFill(
                                Color.GRAY);

                Text pendingValue = new Text(
                                "14");

                pendingValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                pending.getChildren().addAll(
                                pendingTitle,
                                pendingValue);

                // =====================================================
                // TODAY BOX
                // =====================================================

                VBox today = new VBox(2);

                today.setAlignment(
                                Pos.CENTER);

                today.setPadding(
                                new Insets(8, 15, 8, 15));

                today.setStyle(
                                "-fx-background-color:#EBE9EF;" +
                                                "-fx-background-radius:8;");

                Text todayTitle = new Text(
                                "TODAY");

                todayTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                10));

                todayTitle.setFill(
                                Color.GRAY);

                Text todayValue = new Text(
                                "06");

                todayValue.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                20));

                todayValue.setFill(
                                Color.web("#B84300"));

                today.getChildren().addAll(
                                todayTitle,
                                todayValue);

                pageHeader.getChildren().addAll(
                                headingText,
                                headerSpace,
                                pending,
                                today);

                // =====================================================
                // APPROVAL HEADER
                // =====================================================

                HBox approvalHeader = new HBox();

                approvalHeader.setAlignment(
                                Pos.CENTER_LEFT);

                Text approvalTitle = new Text(
                                "▣  Pending Approvals");

                approvalTitle.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                18));

                Region approvalSpace = new Region();

                HBox.setHgrow(
                                approvalSpace,
                                Priority.ALWAYS);

                Text filter = new Text(
                                "Filter By Category ≡");

                filter.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                12));

                filter.setFill(
                                Color.web("#B84300"));

                approvalHeader.getChildren().addAll(
                                approvalTitle,
                                approvalSpace,
                                filter);

                // =====================================================
                // MAIN CONTENT
                // =====================================================

                HBox mainContent = new HBox(15);

                mainContent.setAlignment(
                                Pos.TOP_LEFT);

                // =====================================================
                // LEFT SIDE - SHOP CARDS
                // =====================================================

                VBox shopCards = new VBox(14);

                shopCards.setPrefWidth(520);

                // =====================================================
                // CARD 1
                // =====================================================

                VBox card1 = new VBox(12);

                card1.setPadding(
                                new Insets(14));

                card1.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:12;");

                HBox card1Top = new HBox(10);

                card1Top.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle card1Image = new Rectangle(
                                44,
                                44);

                card1Image.setArcWidth(10);
                card1Image.setArcHeight(10);

                card1Image.setFill(
                                Color.web("#EFF0D9"));

                VBox card1Info = new VBox(3);

                Text card1Name = new Text(
                                "Green Root Grocers");

                card1Name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text card1Owner = new Text(
                                "Owner: Rajesh Kumar • Registered 2h ago");

                card1Owner.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                card1Owner.setFill(
                                Color.GRAY);

                card1Info.getChildren().addAll(
                                card1Name,
                                card1Owner);

                Region card1Space = new Region();

                HBox.setHgrow(
                                card1Space,
                                Priority.ALWAYS);

                Text card1Category = new Text(
                                "GROCERY");

                card1Category.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                card1Category.setFill(
                                Color.GRAY);

                card1Top.getChildren().addAll(
                                card1Image,
                                card1Info,
                                card1Space,
                                card1Category);

                HBox card1Bottom = new HBox(12);

                card1Bottom.setAlignment(
                                Pos.CENTER_LEFT);

                Text license1 = new Text(
                                "▧ Business License");

                license1.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Text gst1 = new Text(
                                "▧ GST Certificate");

                gst1.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Region card1BottomSpace = new Region();

                HBox.setHgrow(
                                card1BottomSpace,
                                Priority.ALWAYS);

                Button view1 = new Button(
                                "View Documents");

                view1.setPrefHeight(36);

                view1.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-font-size:12px;");

                card1Bottom.getChildren().addAll(
                                license1,
                                gst1,
                                card1BottomSpace,
                                view1);

                card1.getChildren().addAll(
                                card1Top,
                                card1Bottom);

                // =====================================================
                // CARD 2
                // =====================================================

                VBox card2 = new VBox(12);

                card2.setPadding(
                                new Insets(14));

                card2.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:12;");

                HBox card2Top = new HBox(10);

                card2Top.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle card2Image = new Rectangle(
                                44,
                                44);

                card2Image.setArcWidth(10);
                card2Image.setArcHeight(10);

                card2Image.setFill(
                                Color.web("#E5E7E9"));

                VBox card2Info = new VBox(3);

                Text card2Name = new Text(
                                "Apex Electronics");

                card2Name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text card2Owner = new Text(
                                "Owner: Sarah Jenkins • Registered 5h ago");

                card2Owner.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                card2Owner.setFill(
                                Color.GRAY);

                card2Info.getChildren().addAll(
                                card2Name,
                                card2Owner);

                Region card2Space = new Region();

                HBox.setHgrow(
                                card2Space,
                                Priority.ALWAYS);

                Text card2Category = new Text(
                                "ELECTRONICS");

                card2Category.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                card2Category.setFill(
                                Color.GRAY);

                card2Top.getChildren().addAll(
                                card2Image,
                                card2Info,
                                card2Space,
                                card2Category);

                HBox card2Bottom = new HBox(12);

                card2Bottom.setAlignment(
                                Pos.CENTER_LEFT);

                Text license2 = new Text(
                                "▧ Business License");

                license2.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Text gst2 = new Text(
                                "▧ GST Certificate");

                gst2.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Region card2BottomSpace = new Region();

                HBox.setHgrow(
                                card2BottomSpace,
                                Priority.ALWAYS);

                Button view2 = new Button(
                                "View Documents");

                view2.setPrefHeight(36);

                view2.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:12px;");

                card2Bottom.getChildren().addAll(
                                license2,
                                gst2,
                                card2BottomSpace,
                                view2);

                card2.getChildren().addAll(
                                card2Top,
                                card2Bottom);

                // =====================================================
                // CARD 3
                // =====================================================

                VBox card3 = new VBox(12);

                card3.setPadding(
                                new Insets(14));

                card3.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:12;");

                HBox card3Top = new HBox(10);

                card3Top.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle card3Image = new Rectangle(
                                44,
                                44);

                card3Image.setArcWidth(10);
                card3Image.setArcHeight(10);

                card3Image.setFill(
                                Color.web("#F0EEEE"));

                VBox card3Info = new VBox(3);

                Text card3Name = new Text(
                                "Velvet Bakes");

                card3Name.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                17));

                Text card3Owner = new Text(
                                "Owner: Anita Desai • Registered Yesterday");

                card3Owner.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                card3Owner.setFill(
                                Color.GRAY);

                card3Info.getChildren().addAll(
                                card3Name,
                                card3Owner);

                Region card3Space = new Region();

                HBox.setHgrow(
                                card3Space,
                                Priority.ALWAYS);

                Text card3Category = new Text(
                                "BAKERY");

                card3Category.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                9));

                card3Category.setFill(
                                Color.GRAY);

                card3Top.getChildren().addAll(
                                card3Image,
                                card3Info,
                                card3Space,
                                card3Category);

                HBox card3Bottom = new HBox(12);

                card3Bottom.setAlignment(
                                Pos.CENTER_LEFT);

                Text license3 = new Text(
                                "▧ Business License");

                license3.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Text gst3 = new Text(
                                "⚠ GST Missing");

                gst3.setFont(
                                Font.font(
                                                "Arial",
                                                11));

                Region card3BottomSpace = new Region();

                HBox.setHgrow(
                                card3BottomSpace,
                                Priority.ALWAYS);

                Button view3 = new Button(
                                "View Documents");

                view3.setPrefHeight(36);

                view3.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:12px;");

                card3Bottom.getChildren().addAll(
                                license3,
                                gst3,
                                card3BottomSpace,
                                view3);

                card3.getChildren().addAll(
                                card3Top,
                                card3Bottom);

                shopCards.getChildren().addAll(
                                card1,
                                card2,
                                card3);

                // =====================================================
                // RIGHT REVIEW PANEL
                // =====================================================

                VBox reviewPanel = new VBox(12);

                reviewPanel.setPrefWidth(560);
                reviewPanel.setMinWidth(500);
                reviewPanel.setPrefHeight(520);

                reviewPanel.setAlignment(
                                Pos.CENTER);

                reviewPanel.setStyle(
                                "-fx-background-color:#FAF9FC;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#E8DCD5;" +
                                                "-fx-border-radius:15;");

                Text check = new Text(
                                "✓");

                check.setFont(
                                Font.font(
                                                "Arial",
                                                FontWeight.BOLD,
                                                42));

                check.setFill(
                                Color.web("#E5D3CB"));

                Text reviewText = new Text(
                                "Select a\nregistration to\nbegin the review\nprocess.");

                reviewText.setFont(
                                Font.font(
                                                "Arial",
                                                14));

                reviewText.setFill(
                                Color.web("#999999"));

                reviewText.setTextAlignment(
                                javafx.scene.text.TextAlignment.CENTER);

                reviewPanel.getChildren().addAll(
                                check,
                                reviewText);

                HBox.setHgrow(reviewPanel, Priority.ALWAYS);

                view1.setOnAction(e -> {
                        card1.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;" +
                                                        "-fx-border-color:#FF6500;" +
                                                        "-fx-border-width:2;" +
                                                        "-fx-border-radius:12;");

                        card2.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        card3.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        showDocumentReview(
                                        reviewPanel,
                                        "Green Root Grocers",
                                        "Owner: Rajesh Kumar",
                                        "GROCERY",
                                        "BUSINESS_LICENSE_CLOUDINARY_URL",
                                        "GST_CERTIFICATE_CLOUDINARY_URL");
                });

                view2.setOnAction(e -> {
                        card2.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;" +
                                                        "-fx-border-color:#FF6500;" +
                                                        "-fx-border-width:2;" +
                                                        "-fx-border-radius:12;");

                        card1.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        card3.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        showDocumentReview(
                                        reviewPanel,
                                        "Apex Electronics",
                                        "Owner: Sarah Jenkins",
                                        "ELECTRONICS",
                                        "BUSINESS_LICENSE_CLOUDINARY_URL",
                                        "GST_CERTIFICATE_CLOUDINARY_URL");
                });

                view3.setOnAction(e -> {
                        card3.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;" +
                                                        "-fx-border-color:#FF6500;" +
                                                        "-fx-border-width:2;" +
                                                        "-fx-border-radius:12;");

                        card1.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        card2.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-background-radius:12;");

                        showDocumentReview(
                                        reviewPanel,
                                        "Velvet Bakes",
                                        "Owner: Anita Desai",
                                        "BAKERY",
                                        "BUSINESS_LICENSE_CLOUDINARY_URL",
                                        null);
                });

                // =====================================================
                // MAIN CONTENT ADD
                // =====================================================

                mainContent.getChildren().addAll(
                                shopCards,
                                reviewPanel);

                // =====================================================
                // CENTER ADD
                // =====================================================

                rightBox.getChildren().addAll(
                                pageHeader,
                                approvalHeader,
                                mainContent);

                root.setLeft(left);
                root.setCenter(rightBox);

                root.setStyle(
                                "-fx-background-color:#FAF8FC;");
                root.setCenter(rightBox);

                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                                root,
                                1550,
                                850);

                return scene;
        }

        public void showDocumentReview(
                        VBox reviewPanel,
                        String shopName,
                        String ownerName,
                        String category,
                        String businessLicenseUrl,
                        String gstCertificateUrl) {

                reviewPanel.getChildren().clear();
                reviewPanel.setAlignment(Pos.TOP_LEFT);
                reviewPanel.setSpacing(18);
                reviewPanel.setPadding(new Insets(25));
                reviewPanel.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:15;" +
                                                "-fx-border-color:#E8DCD5;" +
                                                "-fx-border-radius:15;");

                Text panelTitle = new Text("Document Review");
                panelTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

                Rectangle shopImage = new Rectangle(52, 52);
                shopImage.setArcWidth(10);
                shopImage.setArcHeight(10);
                shopImage.setFill(Color.web("#EFF0D9"));

                Text shopNameText = new Text(shopName);
                shopNameText.setFont(Font.font("Arial", FontWeight.BOLD, 19));

                Text ownerText = new Text(ownerName);
                ownerText.setFont(Font.font("Arial", 12));
                ownerText.setFill(Color.GRAY);

                VBox shopInfo = new VBox(4, shopNameText, ownerText);

                Region shopSpace = new Region();
                HBox.setHgrow(shopSpace, Priority.ALWAYS);

                Label categoryLabel = new Label(category);
                categoryLabel.setStyle(
                                "-fx-background-color:#F7E5D7;" +
                                                "-fx-padding:5 12 5 12;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-size:11px;");

                HBox shopHeader = new HBox(
                                12,
                                shopImage,
                                shopInfo,
                                shopSpace,
                                categoryLabel);
                shopHeader.setAlignment(Pos.CENTER_LEFT);

                Text licenseIcon = new Text("▣");
                licenseIcon.setFont(Font.font("Arial", 26));

                Text licenseName = new Text("Business License");
                licenseName.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Region licenseSpace = new Region();
                HBox.setHgrow(licenseSpace, Priority.ALWAYS);

                Label licenseStatus = new Label("Submitted");
                licenseStatus.setStyle(
                                "-fx-background-color:#DFF2DA;" +
                                                "-fx-text-fill:#26733C;" +
                                                "-fx-padding:4 10 4 10;" +
                                                "-fx-background-radius:7;" +
                                                        "-fx-font-size:12px;");

                Button viewLicense = new Button("View");
                viewLicense.setPrefSize(100, 38);
                viewLicense.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-text-fill:#FF6500;" +
                                                "-fx-border-color:#FF6500;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                HBox licenseRow = new HBox(
                                15,
                                licenseIcon,
                                licenseName,
                                licenseSpace,
                                licenseStatus,
                                viewLicense);
                licenseRow.setAlignment(Pos.CENTER_LEFT);
                licenseRow.setPadding(new Insets(15, 0, 15, 0));
                licenseRow.setStyle(
                                "-fx-border-color:#EEE9F0;" +
                                                "-fx-border-width:1 0 1 0;");

                Text gstIcon = new Text("▣");
                gstIcon.setFont(Font.font("Arial", 26));

                Text gstName = new Text("GST Certificate");
                gstName.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Region gstSpace = new Region();
                HBox.setHgrow(gstSpace, Priority.ALWAYS);

                boolean gstAvailable = gstCertificateUrl != null &&
                                !gstCertificateUrl.isBlank();

                Label gstStatus = new Label(
                                gstAvailable ? "Submitted" : "Missing");

                if (gstAvailable) {
                        gstStatus.setStyle(
                                        "-fx-background-color:#DFF2DA;" +
                                                        "-fx-text-fill:#26733C;" +
                                                        "-fx-padding:4 10 4 10;" +
                                                        "-fx-background-radius:7;" +
                                                        "-fx-font-size:12px;");
                } else {
                        gstStatus.setStyle(
                                        "-fx-background-color:#FFE1E1;" +
                                                        "-fx-text-fill:#B22222;" +
                                                        "-fx-padding:4 10 4 10;" +
                                                        "-fx-background-radius:7;");
                }

                Button viewGst = new Button("View");
                viewGst.setPrefSize(100, 38);
                viewGst.setDisable(!gstAvailable);
                viewGst.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-text-fill:#FF6500;" +
                                                "-fx-border-color:#FF6500;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                HBox gstRow = new HBox(
                                15,
                                gstIcon,
                                gstName,
                                gstSpace,
                                gstStatus,
                                viewGst);
                gstRow.setAlignment(Pos.CENTER_LEFT);
                gstRow.setPadding(new Insets(15, 0, 15, 0));
                gstRow.setStyle(
                                "-fx-border-color:#EEE9F0;" +
                                                "-fx-border-width:0 0 1 0;");

                Label noteLabel = new Label("Review Note (optional)");
                noteLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

                TextArea reviewNote = new TextArea();
                reviewNote.setPromptText("Add a note for the shopkeeper...");
                reviewNote.setPrefRowCount(4);
                reviewNote.setWrapText(true);
                reviewNote.setStyle("-fx-font-size:13px;");

                Button reject = new Button("Reject");
                reject.setPrefSize(130, 44);
                reject.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-text-fill:#E53935;" +
                                                "-fx-border-color:#E53935;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                Button approve = new Button("Approve Shop");
                approve.setPrefSize(160, 44);
                approve.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:13px;");

                Region actionSpace = new Region();
                HBox.setHgrow(actionSpace, Priority.ALWAYS);

                HBox actions = new HBox(reject, actionSpace, approve);
                actions.setAlignment(Pos.CENTER_LEFT);

                viewLicense.setOnAction(e ->
                                openDocument(businessLicenseUrl));

                viewGst.setOnAction(e ->
                                openDocument(gstCertificateUrl));

                reject.setOnAction(e -> {
                        if (reviewNote.getText().isBlank()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setHeaderText(null);
                                alert.setContentText(
                                                "Reject करण्याआधी reason लिहा.");
                                alert.showAndWait();
                                return;
                        }

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText(
                                        shopName + " registration rejected.");
                        alert.showAndWait();
                });

                approve.setOnAction(e -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText(
                                        shopName + " approved successfully.");
                        alert.showAndWait();
                });

                reviewPanel.getChildren().addAll(
                                panelTitle,
                                shopHeader,
                                licenseRow,
                                gstRow,
                                noteLabel,
                                reviewNote,
                                actions);
        }

        public void openDocument(String documentUrl) {

                if (documentUrl == null ||
                                documentUrl.isBlank() ||
                                documentUrl.contains("CLOUDINARY_URL")) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText(null);
                        alert.setContentText("Document URL उपलब्ध नाही.");
                        alert.showAndWait();
                        return;
                }

                try {
                        Desktop.getDesktop().browse(new URI(documentUrl));
                } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Document open करता आली नाही.");
                        alert.showAndWait();
                        e.printStackTrace();
                }
        }

}
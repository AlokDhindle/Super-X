package com.kryox.view.Admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SupportPage {
        private Scene SupportScene;
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
                                        "-fx-background-color:transparent;" +
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
                                        "-fx-background-color:#D94F00;" +
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

                root.setLeft(left);

                VBox rightBox = new VBox(18);
                rightBox.setPadding(new Insets(20, 25, 20, 25));
                rightBox.setStyle("-fx-background-color:#FAF8FC;");

                Text searchIcon = new Text("⌕");
                searchIcon.setFont(Font.font(24));
                TextField topSearch = new TextField();
                topSearch.setPromptText("Search orders, shops, or users...");
                topSearch.setPrefWidth(350);
                topSearch.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:18;" +
                                                "-fx-font-size:14px;");
                HBox topSearchBox = new HBox(8, searchIcon, topSearch);
                topSearchBox.setAlignment(Pos.CENTER_LEFT);

                HBox topRight =
                                createTopActions();

                Region topGrow = new Region();
                HBox.setHgrow(topGrow, Priority.ALWAYS);
                HBox top = new HBox(topSearchBox, topGrow, topRight);
                top.setAlignment(Pos.CENTER_LEFT);

                Text title = new Text("Support Center");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 29));
                Text subtitle = new Text("Get help and manage support requests.");
                subtitle.setFont(Font.font("Arial", 14));
                subtitle.setFill(Color.web("#777777"));
                VBox heading = new VBox(5, title, subtitle);

                Text openNumber = new Text("18");
                openNumber.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                Text openLabel = new Text("Open Tickets");
                openLabel.setFont(Font.font("Arial", 13));
                openLabel.setFill(Color.web("#666666"));
                VBox openText = new VBox(7, openLabel, openNumber);
                Text openIcon = new Text("☏");
                openIcon.setFont(Font.font(30));
                openIcon.setFill(Color.web("#FF6500"));
                HBox openCard = new HBox(20, openIcon, openText);
                openCard.setAlignment(Pos.CENTER_LEFT);
                openCard.setPadding(new Insets(20));
                openCard.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text resolvedNumber = new Text("24");
                resolvedNumber.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                Text resolvedLabel = new Text("Resolved Today");
                resolvedLabel.setFont(Font.font("Arial", 13));
                resolvedLabel.setFill(Color.web("#666666"));
                VBox resolvedText = new VBox(7, resolvedLabel, resolvedNumber);
                Text resolvedIcon = new Text("✓");
                resolvedIcon.setFont(Font.font(30));
                resolvedIcon.setFill(Color.web("#1FA64B"));
                HBox resolvedCard = new HBox(20, resolvedIcon, resolvedText);
                resolvedCard.setAlignment(Pos.CENTER_LEFT);
                resolvedCard.setPadding(new Insets(20));
                resolvedCard.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                Text responseNumber = new Text("12 mins");
                responseNumber.setFont(Font.font("Arial", FontWeight.BOLD, 25));
                Text responseLabel = new Text("Average Response");
                responseLabel.setFont(Font.font("Arial", 13));
                responseLabel.setFill(Color.web("#666666"));
                VBox responseText = new VBox(7, responseLabel, responseNumber);
                Text responseIcon = new Text("◷");
                responseIcon.setFont(Font.font(30));
                responseIcon.setFill(Color.web("#E39A00"));
                HBox responseCard = new HBox(20, responseIcon, responseText);
                responseCard.setAlignment(Pos.CENTER_LEFT);
                responseCard.setPadding(new Insets(20));
                responseCard.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                HBox.setHgrow(openCard, Priority.ALWAYS);
                HBox.setHgrow(resolvedCard, Priority.ALWAYS);
                HBox.setHgrow(responseCard, Priority.ALWAYS);
                openCard.setMaxWidth(Double.MAX_VALUE);
                resolvedCard.setMaxWidth(Double.MAX_VALUE);
                responseCard.setMaxWidth(Double.MAX_VALUE);
                HBox cards = new HBox(18, openCard, resolvedCard, responseCard);

                Text ticketTitle = new Text("Support Tickets");
                ticketTitle.setFont(Font.font("Arial", FontWeight.BOLD, 19));
                TextField ticketSearch = new TextField();
                ticketSearch.setPromptText("Search tickets...");
                ticketSearch.setPrefWidth(230);
                ticketSearch.setStyle("-fx-font-size:13px;");
                ComboBox<String> ticketStatus = new ComboBox<>();
                ticketStatus.getItems().addAll("All Status", "Open", "In Progress", "Resolved", "Closed");
                ticketStatus.setValue("All Status");
                ticketStatus.setStyle("-fx-font-size:13px;");

                Region ticketGrow = new Region();
                HBox.setHgrow(ticketGrow, Priority.ALWAYS);
                HBox ticketHeader = new HBox(12, ticketTitle, ticketGrow, ticketSearch, ticketStatus);
                ticketHeader.setAlignment(Pos.CENTER_LEFT);

                String headerStyle = "-fx-font-size:12px;-fx-fill:#777777;-fx-font-weight:bold;";
                Text idHeader = new Text("Ticket");
                Text userHeader = new Text("User");
                Text subjectHeader = new Text("Subject");
                Text priorityHeader = new Text("Priority");
                Text statusHeader = new Text("Status");
                Text dateHeader = new Text("Date");
                Text actionHeader = new Text("Action");
                idHeader.setStyle(headerStyle);
                userHeader.setStyle(headerStyle);
                subjectHeader.setStyle(headerStyle);
                priorityHeader.setStyle(headerStyle);
                statusHeader.setStyle(headerStyle);
                dateHeader.setStyle(headerStyle);
                actionHeader.setStyle(headerStyle);
                idHeader.setWrappingWidth(85);
                userHeader.setWrappingWidth(135);
                subjectHeader.setWrappingWidth(170);
                priorityHeader.setWrappingWidth(80);
                statusHeader.setWrappingWidth(95);
                dateHeader.setWrappingWidth(145);
                actionHeader.setWrappingWidth(50);
                HBox tableHeader = new HBox(
                                idHeader, userHeader, subjectHeader, priorityHeader,
                                statusHeader, dateHeader, actionHeader);
                tableHeader.setPadding(new Insets(12, 5, 12, 5));

                VBox ticketRows = new VBox();

                String[][] data = {
                                { "#TKT-1024", "mark@example.com", "Unable to verify shop", "High", "Open", "May 13, 10:30 AM" },
                                { "#TKT-1023", "sarah@example.com", "Payout not received", "Medium", "In Progress", "May 13, 09:15 AM" },
                                { "#TKT-1022", "john@example.com", "Product approval issue", "Low", "Resolved", "May 12, 04:45 PM" },
                                { "#TKT-1021", "emma@example.com", "Account suspended", "High", "Closed", "May 12, 11:20 AM" }
                };

                for (String[] item : data) {
                        Text id = new Text(item[0]);
                        Text userEmail = new Text(item[1]);
                        Text subject = new Text(item[2]);
                        Text priority = new Text(item[3]);
                        Text currentStatus = new Text(item[4]);
                        Text date = new Text(item[5]);
                        Text action = new Text("◉");
                        id.setFont(Font.font("Arial", 12));
                        userEmail.setFont(Font.font("Arial", 12));
                        subject.setFont(Font.font("Arial", 12));
                        priority.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        currentStatus.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        date.setFont(Font.font("Arial", 12));
                        action.setFont(Font.font("Arial", 14));
                        id.setWrappingWidth(85);
                        userEmail.setWrappingWidth(135);
                        subject.setWrappingWidth(170);
                        priority.setWrappingWidth(80);
                        currentStatus.setWrappingWidth(95);
                        date.setWrappingWidth(145);
                        action.setWrappingWidth(50);
                        priority.setFill(item[3].equals("High") ? Color.web("#E53935")
                                        : item[3].equals("Medium") ? Color.web("#E39A00") : Color.web("#1FA64B"));
                        currentStatus.setFill(item[4].equals("Open") ? Color.web("#1FA64B")
                                        : item[4].equals("In Progress") ? Color.web("#2878D0") : Color.web("#666666"));
                        HBox row = new HBox(id, userEmail, subject, priority, currentStatus, date, action);
                        row.setAlignment(Pos.CENTER_LEFT);
                        row.setPadding(new Insets(13, 5, 13, 5));
                        row.setStyle("-fx-border-color:transparent transparent #EEEAF0 transparent;");
                        ticketRows.getChildren().add(row);
                }

                VBox ticketTable = new VBox(ticketHeader, tableHeader, ticketRows);
                ticketTable.setPadding(new Insets(18));
                ticketTable.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");
                HBox.setHgrow(ticketTable, Priority.ALWAYS);

                Text createTitle = new Text("Create Support Ticket");
                createTitle.setFont(Font.font("Arial", FontWeight.BOLD, 19));

                TextField userEmailInput = new TextField();
                userEmailInput.setPromptText("Enter user email");
                TextField subjectInput = new TextField();
                subjectInput.setPromptText("Enter subject");
                ComboBox<String> categoryInput = new ComboBox<>();
                categoryInput.setPromptText("Select category");
                categoryInput.getItems().addAll("Account", "Shop Verification", "Payment", "Product", "Technical");
                ComboBox<String> priorityInput = new ComboBox<>();
                priorityInput.setPromptText("Select priority");
                priorityInput.getItems().addAll("Low", "Medium", "High");
                TextArea descriptionInput = new TextArea();
                descriptionInput.setPromptText("Describe your issue in detail...");
                descriptionInput.setPrefRowCount(4);
                descriptionInput.setStyle("-fx-font-size:13px;");

                userEmailInput.setPrefHeight(44);
                userEmailInput.setStyle("-fx-font-size:13px;");
                subjectInput.setPrefHeight(44);
                subjectInput.setStyle("-fx-font-size:13px;");
                categoryInput.setPrefHeight(44);
                categoryInput.setStyle("-fx-font-size:13px;");
                priorityInput.setPrefHeight(44);
                priorityInput.setStyle("-fx-font-size:13px;");
                categoryInput.setMaxWidth(Double.MAX_VALUE);
                priorityInput.setMaxWidth(Double.MAX_VALUE);

                Label emailLabel = new Label("User Email");
                emailLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");
                VBox emailBox = new VBox(5, emailLabel, userEmailInput);
                Label subjectLabel = new Label("Subject");
                subjectLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");
                VBox subjectBox = new VBox(5, subjectLabel, subjectInput);
                Label categoryLabel = new Label("Category");
                categoryLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");
                VBox categoryBox = new VBox(5, categoryLabel, categoryInput);
                Label priorityLabel = new Label("Priority");
                priorityLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");
                VBox priorityBox = new VBox(5, priorityLabel, priorityInput);
                Label descriptionLabel = new Label("Description");
                descriptionLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");
                VBox descriptionBox = new VBox(5, descriptionLabel, descriptionInput);

                Button submit = new Button("Submit Ticket");
                submit.setMaxWidth(Double.MAX_VALUE);
                submit.setPrefHeight(46);
                submit.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-font-size:14px;" +
                                                "-fx-background-radius:7;");

                VBox createTicket = new VBox(
                                14, createTitle, emailBox, subjectBox,
                                categoryBox, priorityBox, descriptionBox, submit);
                createTicket.setPrefWidth(350);
                createTicket.setPadding(new Insets(18));
                createTicket.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                AtomicInteger ticketNumber = new AtomicInteger(1025);

                submit.setOnAction(e -> {
                        if (userEmailInput.getText().isBlank() ||
                                        subjectInput.getText().isBlank() ||
                                        categoryInput.getValue() == null ||
                                        priorityInput.getValue() == null ||
                                        descriptionInput.getText().isBlank()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setHeaderText(null);
                                alert.setContentText("Please fill all ticket fields.");
                                alert.showAndWait();
                                return;
                        }

                        String ticketId = "#TKT-" + ticketNumber.getAndIncrement();
                        String dateValue = LocalDateTime.now().format(
                                        DateTimeFormatter.ofPattern("MMM dd, hh:mm a"));

                        Text id = new Text(ticketId);
                        Text userEmail = new Text(userEmailInput.getText());
                        Text subject = new Text(subjectInput.getText());
                        Text priority = new Text(priorityInput.getValue());
                        Text currentStatus = new Text("Open");
                        Text date = new Text(dateValue);
                        Text action = new Text("◉");
                        id.setFont(Font.font("Arial", 12));
                        userEmail.setFont(Font.font("Arial", 12));
                        subject.setFont(Font.font("Arial", 12));
                        priority.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        currentStatus.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                        date.setFont(Font.font("Arial", 12));
                        action.setFont(Font.font("Arial", 14));
                        id.setWrappingWidth(85);
                        userEmail.setWrappingWidth(135);
                        subject.setWrappingWidth(170);
                        priority.setWrappingWidth(80);
                        currentStatus.setWrappingWidth(95);
                        date.setWrappingWidth(145);
                        action.setWrappingWidth(50);
                        priority.setFill(priorityInput.getValue().equals("High") ? Color.web("#E53935")
                                        : priorityInput.getValue().equals("Medium") ? Color.web("#E39A00")
                                                        : Color.web("#1FA64B"));
                        currentStatus.setFill(Color.web("#1FA64B"));

                        HBox newRow = new HBox(id, userEmail, subject, priority, currentStatus, date, action);
                        newRow.setAlignment(Pos.CENTER_LEFT);
                        newRow.setPadding(new Insets(13, 5, 13, 5));
                        newRow.setStyle("-fx-border-color:transparent transparent #EEEAF0 transparent;");
                        ticketRows.getChildren().add(0, newRow);

                        userEmailInput.clear();
                        subjectInput.clear();
                        categoryInput.setValue(null);
                        priorityInput.setValue(null);
                        descriptionInput.clear();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Ticket " + ticketId + " submitted successfully.");
                        alert.showAndWait();
                });

                Text faqTitle = new Text("Frequently Asked Questions");
                faqTitle.setFont(Font.font("Arial", FontWeight.BOLD, 19));
                Button faq1 = new Button("How do I verify a shop?                                      ˅");
                Button faq2 = new Button("How do I manage flagged accounts?                       ˅");
                Button faq3 = new Button("How do I contact technical support?                    ˅");
                faq1.setMaxWidth(Double.MAX_VALUE);
                faq1.setStyle("-fx-font-size:13px;-fx-padding:10;");
                faq2.setMaxWidth(Double.MAX_VALUE);
                faq2.setStyle("-fx-font-size:13px;-fx-padding:10;");
                faq3.setMaxWidth(Double.MAX_VALUE);
                faq3.setStyle("-fx-font-size:13px;-fx-padding:10;");
                faq1.setAlignment(Pos.CENTER_LEFT);
                faq2.setAlignment(Pos.CENTER_LEFT);
                faq3.setAlignment(Pos.CENTER_LEFT);
                VBox faq = new VBox(8, faqTitle, faq1, faq2, faq3);
                faq.setPadding(new Insets(15));
                faq.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E8E4EA;" +
                                                "-fx-border-radius:10;" +
                                                "-fx-background-radius:10;");

                VBox leftContent = new VBox(18, ticketTable, faq);
                HBox.setHgrow(leftContent, Priority.ALWAYS);
                HBox mainContent = new HBox(18, leftContent, createTicket);
                VBox.setVgrow(mainContent, Priority.ALWAYS);

                rightBox.getChildren().addAll(top, heading, cards, mainContent);

                BorderPane main = new BorderPane();
                main.setLeft(left);
                main.setCenter(rightBox);
                main.setStyle("-fx-background-color:#FAF8FC;");

                Scene scene = new Scene(main, 1550, 850);
                SupportScene = scene;
                return SupportScene;
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
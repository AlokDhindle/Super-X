package com.kryox.view.Admin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.util.Duration;

public class SupportPage {

        private Scene supportScene;

        private static class TicketItem {
                String id;
                String email;
                String subject;
                String category;
                String priority;
                String status;
                String date;
                String description;

                TicketItem(String id, String email, String subject, String category, String priority, String status, String date, String description) {
                        this.id = id;
                        this.email = email;
                        this.subject = subject;
                        this.category = category;
                        this.priority = priority;
                        this.status = status;
                        this.date = date;
                        this.description = description;
                }
        }

        private List<TicketItem> ticketList = new ArrayList<>();
        private AtomicInteger ticketNumberCounter = new AtomicInteger(1025);

        private Text openNumberText;
        private Text resolvedNumberText;
        private VBox notificationItemsBox = new VBox(10);
        private Text notificationBadgeText = new Text("0");
        private StackPane badgeContainer = new StackPane();

        public SupportPage() {
                // Initialize default sample support tickets
                ticketList.add(new TicketItem("#TKT-1024", "mark@example.com", "Unable to verify shop", "Shop Verification", "High", "Open", "May 13, 10:30 AM", "Shop verification documents failing on step 2 approval."));
                ticketList.add(new TicketItem("#TKT-1023", "sarah@example.com", "Payout not received", "Payment", "Medium", "In Progress", "May 13, 09:15 AM", "Weekly bank payout transfer pending processing."));
                ticketList.add(new TicketItem("#TKT-1022", "john@example.com", "Product approval issue", "Product", "Low", "Resolved", "May 12, 04:45 PM", "Product image resolution check passed."));
                ticketList.add(new TicketItem("#TKT-1021", "emma@example.com", "Account suspended", "Account", "High", "Closed", "May 12, 11:20 AM", "Account security lockout reviewed."));
        }

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

                HBox dashboard = new HBox();
                dashboard.setSpacing(10);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setPadding(new Insets(10, 12, 10, 12));
                dashboard.setPrefWidth(180);
                dashboard.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

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
                        dashboard.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        dashboardText.setFill(Color.WHITE);
                        dashboardText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), dashboard);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                dashboard.setOnMouseExited(e -> {
                        dashboard.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        dashboardText.setFill(Color.web("#333333"));
                        dashboardText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), dashboard);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                dashboard.setOnMouseClicked(e -> {
                        AdminDashboardPage dbPage = new AdminDashboardPage();
                        Homepage.HomepageStage.setScene(dbPage.getUserScene());
                });

                HBox users = new HBox();
                users.setSpacing(10);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setPadding(new Insets(10, 12, 10, 12));
                users.setPrefWidth(180);
                users.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

                Image img2 = new Image(getClass().getResource("/assets/images/admin/admin_logo.png").toExternalForm());
                ImageView iv2 = new ImageView(img2);
                iv2.setFitWidth(20);
                iv2.setFitHeight(20);
                iv2.setPreserveRatio(true);

                Text usersText = new Text("Users");
                usersText.setFill(Color.web("#333333"));
                usersText.setFont(Font.font("Arial", 14));

                users.getChildren().addAll(iv2, usersText);

                users.setOnMouseEntered(e -> {
                        users.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        usersText.setFill(Color.WHITE);
                        usersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), users);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                users.setOnMouseExited(e -> {
                        users.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        usersText.setFill(Color.web("#333333"));
                        usersText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), users);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                users.setOnMouseClicked(e -> {
                        UserManagementPage userPage = new UserManagementPage();
                        Homepage.HomepageStage.setScene(userPage.getUserScene());
                });

                HBox shops = new HBox();
                shops.setSpacing(10);
                shops.setAlignment(Pos.CENTER_LEFT);
                shops.setPadding(new Insets(10, 12, 10, 12));
                shops.setPrefWidth(180);
                shops.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

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
                        shops.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        shopsText.setFill(Color.WHITE);
                        shopsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), shops);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                shops.setOnMouseExited(e -> {
                        shops.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        shopsText.setFill(Color.web("#333333"));
                        shopsText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), shops);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                shops.setOnMouseClicked(e -> {
                        ShopVerificationPage shopPage = new ShopVerificationPage();
                        Homepage.HomepageStage.setScene(shopPage.getUserScene());
                });

                HBox delivery = new HBox();
                delivery.setSpacing(10);
                delivery.setAlignment(Pos.CENTER_LEFT);
                delivery.setPadding(new Insets(10, 12, 10, 12));
                delivery.setPrefWidth(180);
                delivery.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

                Text deliveryIcon = new Text("🚚");
                deliveryIcon.setFont(Font.font("Arial", 18));

                Text deliveryText = new Text("Delivery");
                deliveryText.setFill(Color.web("#333333"));
                deliveryText.setFont(Font.font("Arial", 14));

                delivery.getChildren().addAll(deliveryIcon, deliveryText);

                delivery.setOnMouseEntered(e -> {
                        delivery.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        deliveryText.setFill(Color.WHITE);
                        deliveryText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), delivery);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                delivery.setOnMouseExited(e -> {
                        delivery.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        deliveryText.setFill(Color.web("#333333"));
                        deliveryText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), delivery);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                delivery.setOnMouseClicked(e -> {
                        DeliveryVerificationPage deliveryPage = new DeliveryVerificationPage();
                        Homepage.HomepageStage.setScene(deliveryPage.getUserScene());
                });

                HBox offers = new HBox();
                offers.setSpacing(10);
                offers.setAlignment(Pos.CENTER_LEFT);
                offers.setPadding(new Insets(10, 12, 10, 12));
                offers.setPrefWidth(180);
                offers.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

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
                        offers.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        offersText.setFill(Color.WHITE);
                        offersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), offers);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                offers.setOnMouseExited(e -> {
                        offers.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        offersText.setFill(Color.web("#333333"));
                        offersText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), offers);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                offers.setOnMouseClicked(e -> {
                        OfferPage offerPage = new OfferPage();
                        Homepage.HomepageStage.setScene(offerPage.getUserScene());
                });

                HBox analytics = new HBox();
                analytics.setSpacing(10);
                analytics.setAlignment(Pos.CENTER_LEFT);
                analytics.setPadding(new Insets(10, 12, 10, 12));
                analytics.setPrefWidth(180);
                analytics.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

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
                        analytics.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        analyticsText.setFill(Color.WHITE);
                        analyticsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), analytics);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                analytics.setOnMouseExited(e -> {
                        analytics.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        analyticsText.setFill(Color.web("#333333"));
                        analyticsText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), analytics);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                analytics.setOnMouseClicked(e -> {
                        AdminAnalyticsPage analyticsPage = new AdminAnalyticsPage();
                        Homepage.HomepageStage.setScene(analyticsPage.getUserScene());
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
                settings.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");

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
                support.setStyle("-fx-background-color:#FF6500; -fx-background-radius:10;");

                Image img7 = new Image(getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
                ImageView iv7 = new ImageView(img7);
                iv7.setFitWidth(20);
                iv7.setFitHeight(20);
                iv7.setPreserveRatio(true);

                Text supportText = new Text("Support");
                supportText.setFill(Color.WHITE);
                supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                support.getChildren().addAll(iv7, supportText);

                bottomMenu.getChildren().addAll(settings, support);

                settings.setOnMouseEntered(e -> {
                        settings.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        settingsText.setFill(Color.WHITE);
                        settingsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), settings);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                settings.setOnMouseExited(e -> {
                        settings.setStyle("-fx-background-color:transparent; -fx-background-radius:10;");
                        settingsText.setFill(Color.web("#333333"));
                        settingsText.setFont(Font.font("Arial", 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), settings);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                settings.setOnMouseClicked(event -> {
                        SettingsPage setting = new SettingsPage();
                        Homepage.HomepageStage.setScene(setting.getUserScene());
                });

                support.setOnMouseEntered(e -> {
                        support.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
                        supportText.setFill(Color.WHITE);
                        supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), support);
                        st.setToX(1.03);
                        st.setToY(1.03);
                        st.play();
                });

                support.setOnMouseExited(e -> {
                        support.setStyle("-fx-background-color:#FF6500; -fx-background-radius:10;");
                        supportText.setFill(Color.WHITE);
                        supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                        ScaleTransition st = new ScaleTransition(Duration.millis(120), support);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });
                support.setOnMouseClicked(event -> {
                        SupportPage supports = new SupportPage();
                        Homepage.HomepageStage.setScene(supports.getUserScene());
                });

                AdminProfileCard adminProfileCard = new AdminProfileCard();
                HBox profile = adminProfileCard.getProfileCard();

                Region leftGrow = new Region();
                VBox.setVgrow(leftGrow, Priority.ALWAYS);

                left.getChildren().addAll(
                                logoBox,
                                menu,
                                new Separator(),
                                bottomMenu,
                                leftGrow,
                                profile);

                root.setLeft(left);

                VBox rightBox = new VBox(22);
                rightBox.setPadding(new Insets(24, 28, 30, 28));
                rightBox.setStyle("-fx-background-color: #eee5df;");

                Text searchIconText = new Text("🔍");
                searchIconText.setFont(Font.font(14));
                searchIconText.setFill(Color.web("#8C7D73"));

                TextField topSearch = new TextField();
                topSearch.setPromptText("Search orders, shops, or users...");
                topSearch.setPrefWidth(350);
                topSearch.setPrefHeight(40);
                topSearch.setStyle(
                                "-fx-background-color: white;" +
                                "-fx-border-color: #DDD2C8;" +
                                "-fx-border-radius: 20;" +
                                "-fx-background-radius: 20;" +
                                "-fx-font-size: 13px;" +
                                "-fx-padding: 0 16 0 16;" +
                                "-fx-text-fill: #1C1917;");

                HBox topSearchBox = new HBox(10, searchIconText, topSearch);
                topSearchBox.setAlignment(Pos.CENTER_LEFT);

                HBox topRight = createTopActions();

                Region topGrow = new Region();
                HBox.setHgrow(topGrow, Priority.ALWAYS);
                HBox top = new HBox(topSearchBox, topGrow, topRight);
                top.setAlignment(Pos.CENTER_LEFT);

                Text title = new Text("Support Center");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
                title.setFill(Color.web("#1C1917"));

                Text subtitle = new Text("Get help, review user inquiries, and manage support tickets in real-time.");
                subtitle.setFont(Font.font("Arial", 14));
                subtitle.setFill(Color.web("#78716C"));
                VBox heading = new VBox(4, title, subtitle);

                openNumberText = new Text("2");
                HBox openCard = createStatCard("Open Tickets", openNumberText, "✉", "#FF6500", "#FFF0E6");

                resolvedNumberText = new Text("1");
                HBox resolvedCard = createStatCard("Resolved Today", resolvedNumberText, "✓", "#1E8E3E", "#EBF7EE");

                Text responseNumber = new Text("12 mins");
                HBox responseCard = createStatCard("Average Response", responseNumber, "⏱", "#D97706", "#FFF8E7");

                HBox.setHgrow(openCard, Priority.ALWAYS);
                HBox.setHgrow(resolvedCard, Priority.ALWAYS);
                HBox.setHgrow(responseCard, Priority.ALWAYS);
                openCard.setMaxWidth(Double.MAX_VALUE);
                resolvedCard.setMaxWidth(Double.MAX_VALUE);
                responseCard.setMaxWidth(Double.MAX_VALUE);
                HBox cards = new HBox(18, openCard, resolvedCard, responseCard);

                Text ticketTitle = new Text("Support Tickets");
                ticketTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                ticketTitle.setFill(Color.web("#1C1917"));

                TextField ticketSearch = new TextField();
                ticketSearch.setPromptText("Search tickets...");
                ticketSearch.setPrefWidth(220);
                ticketSearch.setPrefHeight(36);
                ticketSearch.setStyle(
                                "-fx-background-color: #FAF8F6;" +
                                "-fx-border-color: #DDD2C8;" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;" +
                                "-fx-font-size: 13px;" +
                                "-fx-padding: 0 12 0 12;");

                ComboBox<String> ticketStatus = new ComboBox<>();
                ticketStatus.getItems().addAll("All Status", "Open", "In Progress", "Resolved", "Closed");
                ticketStatus.setValue("All Status");
                ticketStatus.setPrefHeight(36);
                ticketStatus.setStyle(
                                "-fx-background-color: #FAF8F6;" +
                                "-fx-border-color: #DDD2C8;" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;" +
                                "-fx-font-size: 13px;");

                Region ticketGrow = new Region();
                HBox.setHgrow(ticketGrow, Priority.ALWAYS);
                HBox ticketHeader = new HBox(12, ticketTitle, ticketGrow, ticketSearch, ticketStatus);
                ticketHeader.setAlignment(Pos.CENTER_LEFT);

                String headerStyle = "-fx-font-size: 12px; -fx-fill: #78716C; -fx-font-weight: bold;";
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
                userHeader.setWrappingWidth(140);
                subjectHeader.setWrappingWidth(170);
                priorityHeader.setWrappingWidth(85);
                statusHeader.setWrappingWidth(100);
                dateHeader.setWrappingWidth(140);
                actionHeader.setWrappingWidth(50);

                HBox tableHeader = new HBox(
                                idHeader, userHeader, subjectHeader, priorityHeader,
                                statusHeader, dateHeader, actionHeader);
                tableHeader.setAlignment(Pos.CENTER_LEFT);
                tableHeader.setPadding(new Insets(10, 12, 10, 12));
                tableHeader.setStyle("-fx-background-color: #F7F3EE; -fx-background-radius: 8;");

                VBox ticketRows = new VBox();

                // Connect live search and status filter handlers
                topSearch.textProperty().addListener((obs, oldVal, newVal) -> {
                        String clean = newVal != null ? newVal : "";
                        if (!clean.equals(ticketSearch.getText())) {
                                ticketSearch.setText(clean);
                        }
                        renderTicketTable(ticketRows, clean, ticketStatus.getValue());
                });
                topSearch.setOnAction(e -> renderTicketTable(ticketRows, topSearch.getText(), ticketStatus.getValue()));
                searchIconText.setStyle("-fx-cursor: hand;");
                searchIconText.setOnMouseClicked(e -> renderTicketTable(ticketRows, topSearch.getText(), ticketStatus.getValue()));

                ticketSearch.textProperty().addListener((obs, oldVal, newVal) -> {
                        String clean = newVal != null ? newVal : "";
                        if (!clean.equals(topSearch.getText())) {
                                topSearch.setText(clean);
                        }
                        renderTicketTable(ticketRows, clean, ticketStatus.getValue());
                });

                ticketStatus.valueProperty().addListener((obs, oldVal, newVal) -> {
                        renderTicketTable(ticketRows, ticketSearch.getText(), newVal);
                });

                // Initial render of tickets table
                renderTicketTable(ticketRows, "", "All Status");

                VBox ticketTable = new VBox(12, ticketHeader, tableHeader, ticketRows);
                ticketTable.setPadding(new Insets(20));
                ticketTable.setStyle(
                                "-fx-background-color: white;" +
                                "-fx-border-color: #E2D7CE;" +
                                "-fx-border-radius: 12;" +
                                "-fx-background-radius: 12;" +
                                "-fx-effect: dropshadow(gaussian, rgba(168,62,0,0.04), 10, 0, 0, 2);");
                HBox.setHgrow(ticketTable, Priority.ALWAYS);

                // Ticket Creation Form
                Text createTitle = new Text("Create Support Ticket");
                createTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                createTitle.setFill(Color.web("#1C1917"));

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
                descriptionInput.setPromptText("Describe issue in detail...");
                descriptionInput.setPrefRowCount(4);

                String fieldStyle = "-fx-background-color: #FAF8F6; -fx-border-color: #DDD2C8; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-size: 13px; -fx-padding: 8 12 8 12;";
                String fieldFocusStyle = "-fx-background-color: white; -fx-border-color: #FF6500; -fx-border-radius: 8; -fx-background-radius: 8; -fx-font-size: 13px; -fx-padding: 8 12 8 12;";

                userEmailInput.setStyle(fieldStyle);
                userEmailInput.setPrefHeight(40);
                attachFocusStyle(userEmailInput, fieldStyle, fieldFocusStyle);

                subjectInput.setStyle(fieldStyle);
                subjectInput.setPrefHeight(40);
                attachFocusStyle(subjectInput, fieldStyle, fieldFocusStyle);

                categoryInput.setStyle(fieldStyle);
                categoryInput.setPrefHeight(40);
                categoryInput.setMaxWidth(Double.MAX_VALUE);

                priorityInput.setStyle(fieldStyle);
                priorityInput.setPrefHeight(40);
                priorityInput.setMaxWidth(Double.MAX_VALUE);

                descriptionInput.setStyle(fieldStyle);
                attachFocusStyle(descriptionInput, fieldStyle, fieldFocusStyle);

                String labelStyle = "-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #44403C;";
                Label emailLabel = new Label("User Email");
                emailLabel.setStyle(labelStyle);
                VBox emailBox = new VBox(5, emailLabel, userEmailInput);

                Label subjectLabel = new Label("Subject");
                subjectLabel.setStyle(labelStyle);
                VBox subjectBox = new VBox(5, subjectLabel, subjectInput);

                Label categoryLabel = new Label("Category");
                categoryLabel.setStyle(labelStyle);
                VBox categoryBox = new VBox(5, categoryLabel, categoryInput);

                Label priorityLabel = new Label("Priority");
                priorityLabel.setStyle(labelStyle);
                VBox priorityBox = new VBox(5, priorityLabel, priorityInput);

                Label descriptionLabel = new Label("Description");
                descriptionLabel.setStyle(labelStyle);
                VBox descriptionBox = new VBox(5, descriptionLabel, descriptionInput);

                Button submit = new Button("Submit Ticket");
                submit.setMaxWidth(Double.MAX_VALUE);
                submit.setPrefHeight(44);
                submit.setStyle(
                                "-fx-background-color: #FF6500;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 14px;" +
                                "-fx-background-radius: 8;" +
                                "-fx-cursor: hand;" +
                                "-fx-effect: dropshadow(gaussian, rgba(255,101,0,0.35), 8, 0, 0, 2);");

                submit.setOnMouseEntered(e -> submit.setStyle(
                                "-fx-background-color: #E05500;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 14px;" +
                                "-fx-background-radius: 8;" +
                                "-fx-cursor: hand;" +
                                "-fx-effect: dropshadow(gaussian, rgba(224,85,0,0.45), 10, 0, 0, 2);"));
                submit.setOnMouseExited(e -> submit.setStyle(
                                "-fx-background-color: #FF6500;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-size: 14px;" +
                                "-fx-background-radius: 8;" +
                                "-fx-cursor: hand;" +
                                "-fx-effect: dropshadow(gaussian, rgba(255,101,0,0.35), 8, 0, 0, 2);"));

                VBox createTicket = new VBox(
                                12, createTitle, emailBox, subjectBox,
                                categoryBox, priorityBox, descriptionBox, submit);
                createTicket.setPrefWidth(350);
                createTicket.setPadding(new Insets(20));
                createTicket.setStyle(
                                "-fx-background-color: white;" +
                                "-fx-border-color: #E2D7CE;" +
                                "-fx-border-radius: 12;" +
                                "-fx-background-radius: 12;" +
                                "-fx-effect: dropshadow(gaussian, rgba(168,62,0,0.04), 10, 0, 0, 2);");

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

                        String ticketId = "#TKT-" + ticketNumberCounter.getAndIncrement();
                        String dateValue = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, hh:mm a"));

                        TicketItem newTicket = new TicketItem(
                                        ticketId,
                                        userEmailInput.getText().trim(),
                                        subjectInput.getText().trim(),
                                        categoryInput.getValue(),
                                        priorityInput.getValue(),
                                        "Open",
                                        dateValue,
                                        descriptionInput.getText().trim());

                        ticketList.add(0, newTicket);
                        renderTicketTable(ticketRows, ticketSearch.getText(), ticketStatus.getValue());

                        userEmailInput.clear();
                        subjectInput.clear();
                        categoryInput.setValue(null);
                        priorityInput.setValue(null);
                        descriptionInput.clear();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Support Ticket Created");
                        alert.setContentText("Ticket " + ticketId + " submitted and added to live queue successfully.");
                        alert.showAndWait();
                });

                // FAQ Section with Expandable Content
                Text faqTitle = new Text("Frequently Asked Questions");
                faqTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
                faqTitle.setFill(Color.web("#1C1917"));

                VBox faqBox1 = createFaqItem("How do I verify a shop application?",
                                "Go to the 'Shops' tab on the left sidebar. Review the shop license & GST documents submitted by the shopkeeper, then click 'Approve' to activate the merchant shop on BuyNeX.");

                VBox faqBox2 = createFaqItem("How do I manage flagged user or merchant accounts?",
                                "Navigate to 'Users' or 'Shops' tab, select the account and review flagged security alerts. You can suspend, reinstate, or send warnings directly.");

                VBox faqBox3 = createFaqItem("How do I contact core technical support?",
                                "For critical server or database emergencies, contact dev-support@buynex.com or launch an urgent ticket directly in this Support Center.");

                VBox faq = new VBox(12, faqTitle, faqBox1, faqBox2, faqBox3);
                faq.setPadding(new Insets(20));
                faq.setStyle(
                                "-fx-background-color: white;" +
                                "-fx-border-color: #E2D7CE;" +
                                "-fx-border-radius: 12;" +
                                "-fx-background-radius: 12;" +
                                "-fx-effect: dropshadow(gaussian, rgba(168,62,0,0.04), 10, 0, 0, 2);");

                VBox leftContent = new VBox(18, ticketTable, faq);
                HBox.setHgrow(leftContent, Priority.ALWAYS);
                HBox mainContent = new HBox(18, leftContent, createTicket);
                VBox.setVgrow(mainContent, Priority.ALWAYS);

                rightBox.getChildren().addAll(top, heading, cards, mainContent);

                ScrollPane scrollPane = new ScrollPane(rightBox);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(false);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setStyle("-fx-background-color: #eee5df; -fx-background: #eee5df; -fx-border-color: transparent;");

                root.setCenter(scrollPane);

                Scene scene = new Scene(root, 1550, 850);
                supportScene = scene;
                return supportScene;
        }

        private HBox createStatCard(String title, Text numberText, String iconChar, String iconColor, String iconBgColor) {
                StackPane iconCircle = new StackPane();
                iconCircle.setPrefSize(48, 48);
                iconCircle.setMinSize(48, 48);
                iconCircle.setMaxSize(48, 48);
                iconCircle.setStyle("-fx-background-color: " + iconBgColor + "; -fx-background-radius: 24;");

                Text icon = new Text(iconChar);
                icon.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                icon.setFill(Color.web(iconColor));
                iconCircle.getChildren().add(icon);

                Text label = new Text(title);
                label.setFont(Font.font("Arial", 13));
                label.setFill(Color.web("#78716C"));

                numberText.setFont(Font.font("Arial", FontWeight.BOLD, 26));
                numberText.setFill(Color.web("#1C1917"));

                VBox textCol = new VBox(4, label, numberText);

                HBox card = new HBox(16, iconCircle, textCol);
                card.setAlignment(Pos.CENTER_LEFT);
                card.setPadding(new Insets(18, 22, 18, 22));
                card.setStyle(
                                "-fx-background-color: white;" +
                                "-fx-border-color: #E2D7CE;" +
                                "-fx-border-radius: 12;" +
                                "-fx-background-radius: 12;" +
                                "-fx-effect: dropshadow(gaussian, rgba(168,62,0,0.05), 10, 0, 0, 2);");

                card.setOnMouseEntered(e -> {
                        ScaleTransition st = new ScaleTransition(Duration.millis(120), card);
                        st.setToX(1.02);
                        st.setToY(1.02);
                        st.play();
                });
                card.setOnMouseExited(e -> {
                        ScaleTransition st = new ScaleTransition(Duration.millis(120), card);
                        st.setToX(1.0);
                        st.setToY(1.0);
                        st.play();
                });

                return card;
        }

        private void attachFocusStyle(javafx.scene.control.TextInputControl control, String regularStyle, String focusStyle) {
                control.focusedProperty().addListener((obs, wasFocused, isFocused) -> {
                        if (isFocused) {
                                control.setStyle(focusStyle);
                        } else {
                                control.setStyle(regularStyle);
                        }
                });
        }

        private void renderTicketTable(VBox ticketRows, String searchQuery, String statusFilter) {
                ticketRows.getChildren().clear();
                String q = (searchQuery == null) ? "" : searchQuery.trim().toLowerCase();
                String st = (statusFilter == null) ? "All Status" : statusFilter;

                List<TicketItem> filtered = ticketList.stream().filter(item -> {
                        boolean matchQ = q.isEmpty() || item.id.toLowerCase().contains(q)
                                        || item.email.toLowerCase().contains(q)
                                        || item.subject.toLowerCase().contains(q)
                                        || item.category.toLowerCase().contains(q);

                        boolean matchSt = st.equalsIgnoreCase("All Status") || item.status.equalsIgnoreCase(st);
                        return matchQ && matchSt;
                }).collect(Collectors.toList());

                if (filtered.isEmpty()) {
                        Text noMsg = new Text("No matching support tickets found.");
                        noMsg.setFont(Font.font("Arial", 13));
                        noMsg.setFill(Color.web("#78716C"));
                        HBox emptyRow = new HBox(noMsg);
                        emptyRow.setPadding(new Insets(20));
                        emptyRow.setAlignment(Pos.CENTER);
                        ticketRows.getChildren().add(emptyRow);
                } else {
                        for (TicketItem item : filtered) {
                                ticketRows.getChildren().add(createTicketRow(item, ticketRows, searchQuery, statusFilter));
                        }
                }

                updateStatCards();
        }

        private Label createBadge(String text, String bgHex, String fgHex) {
                Label badge = new Label(text);
                badge.setStyle(
                                "-fx-background-color: " + bgHex + ";" +
                                "-fx-text-fill: " + fgHex + ";" +
                                "-fx-font-size: 11px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 12;" +
                                "-fx-padding: 3 8 3 8;" +
                                "-fx-alignment: center;");
                return badge;
        }

        private HBox createTicketRow(TicketItem item, VBox ticketRows, String searchQuery, String statusFilter) {
                Text id = new Text(item.id);
                Text userEmail = new Text(item.email);
                Text subject = new Text(item.subject);
                Text date = new Text(item.date);

                id.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                id.setFill(Color.web("#A83E00"));

                userEmail.setFont(Font.font("Arial", 12));
                userEmail.setFill(Color.web("#292524"));

                subject.setFont(Font.font("Arial", 12));
                subject.setFill(Color.web("#292524"));

                date.setFont(Font.font("Arial", 12));
                date.setFill(Color.web("#78716C"));

                id.setWrappingWidth(85);
                userEmail.setWrappingWidth(140);
                subject.setWrappingWidth(170);
                date.setWrappingWidth(140);

                Label priorityBadge;
                if ("High".equalsIgnoreCase(item.priority)) {
                        priorityBadge = createBadge(item.priority, "#FEE2E2", "#B91C1C");
                } else if ("Medium".equalsIgnoreCase(item.priority)) {
                        priorityBadge = createBadge(item.priority, "#FEF3C7", "#B45309");
                } else {
                        priorityBadge = createBadge(item.priority, "#DCFCE7", "#15803D");
                }
                HBox priorityBox = new HBox(priorityBadge);
                priorityBox.setPrefWidth(85);
                priorityBox.setAlignment(Pos.CENTER_LEFT);

                Label statusBadge;
                if ("Open".equalsIgnoreCase(item.status)) {
                        statusBadge = createBadge(item.status, "#DCFCE7", "#15803D");
                } else if ("In Progress".equalsIgnoreCase(item.status)) {
                        statusBadge = createBadge(item.status, "#DBEAFE", "#1D4ED8");
                } else if ("Resolved".equalsIgnoreCase(item.status)) {
                        statusBadge = createBadge(item.status, "#F3F4F6", "#4B5563");
                } else {
                        statusBadge = createBadge(item.status, "#F5F5F5", "#9E9E9E");
                }
                HBox statusBox = new HBox(statusBadge);
                statusBox.setPrefWidth(100);
                statusBox.setAlignment(Pos.CENTER_LEFT);

                StackPane actionBtn = new StackPane();
                Text actionIcon = new Text("⚙");
                actionIcon.setFont(Font.font("Arial", 14));
                actionIcon.setFill(Color.web("#78716C"));
                actionBtn.getChildren().add(actionIcon);
                actionBtn.setPrefSize(28, 28);
                actionBtn.setStyle("-fx-background-color: #F7F3EE; -fx-background-radius: 14; -fx-cursor: hand;");

                actionBtn.setOnMouseEntered(e -> {
                        actionBtn.setStyle("-fx-background-color: #FFEDE0; -fx-background-radius: 14; -fx-cursor: hand;");
                        actionIcon.setFill(Color.web("#FF6500"));
                });
                actionBtn.setOnMouseExited(e -> {
                        actionBtn.setStyle("-fx-background-color: #F7F3EE; -fx-background-radius: 14; -fx-cursor: hand;");
                        actionIcon.setFill(Color.web("#78716C"));
                });

                HBox actionBox = new HBox(actionBtn);
                actionBox.setPrefWidth(50);
                actionBox.setAlignment(Pos.CENTER_LEFT);

                HBox row = new HBox(id, userEmail, subject, priorityBox, statusBox, date, actionBox);
                row.setAlignment(Pos.CENTER_LEFT);
                row.setPadding(new Insets(12, 12, 12, 12));
                row.setStyle("-fx-background-color: transparent; -fx-border-color: transparent transparent #EAE3DC transparent; -fx-background-radius: 6;");

                row.setOnMouseEntered(e -> {
                        row.setStyle("-fx-background-color: #FAF5F0; -fx-border-color: transparent transparent #EAE3DC transparent; -fx-background-radius: 6;");
                });
                row.setOnMouseExited(e -> {
                        row.setStyle("-fx-background-color: transparent; -fx-border-color: transparent transparent #EAE3DC transparent; -fx-background-radius: 6;");
                });

                // Click handler to open ticket options & change status
                actionBtn.setOnMouseClicked(e -> showTicketDetailsDialog(item, ticketRows, searchQuery, statusFilter));
                row.setOnMouseClicked(e -> {
                        if (e.getClickCount() == 2) {
                                showTicketDetailsDialog(item, ticketRows, searchQuery, statusFilter);
                        }
                });

                return row;
        }

        private void showTicketDetailsDialog(TicketItem item, VBox ticketRows, String searchQuery, String statusFilter) {
                Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
                dialog.setTitle("Manage Ticket " + item.id);
                dialog.setHeaderText("Ticket Details: " + item.subject);
                dialog.setContentText(
                                "User: " + item.email + "\n" +
                                                "Category: " + item.category + "\n" +
                                                "Priority: " + item.priority + "\n" +
                                                "Current Status: " + item.status + "\n" +
                                                "Date Created: " + item.date + "\n\n" +
                                                "Description:\n" + item.description + "\n\n" +
                                                "Choose next status for this ticket:");

                Button btnOpen = new Button("Mark Open");
                Button btnInProgress = new Button("Mark In Progress");
                Button btnResolved = new Button("Mark Resolved");
                Button btnClosed = new Button("Mark Closed");

                btnOpen.setStyle("-fx-background-color: #15803D; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");
                btnInProgress.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");
                btnResolved.setStyle("-fx-background-color: #4B5563; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");
                btnClosed.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");

                btnOpen.setOnAction(e -> {
                        item.status = "Open";
                        dialog.close();
                        renderTicketTable(ticketRows, searchQuery, statusFilter);
                });
                btnInProgress.setOnAction(e -> {
                        item.status = "In Progress";
                        dialog.close();
                        renderTicketTable(ticketRows, searchQuery, statusFilter);
                });
                btnResolved.setOnAction(e -> {
                        item.status = "Resolved";
                        dialog.close();
                        renderTicketTable(ticketRows, searchQuery, statusFilter);
                });
                btnClosed.setOnAction(e -> {
                        item.status = "Closed";
                        dialog.close();
                        renderTicketTable(ticketRows, searchQuery, statusFilter);
                });

                HBox btnBox = new HBox(10, btnOpen, btnInProgress, btnResolved, btnClosed);
                btnBox.setPadding(new Insets(10, 0, 0, 0));
                dialog.getDialogPane().setExpandableContent(btnBox);
                dialog.getDialogPane().setExpanded(true);
                dialog.showAndWait();
        }

        private void updateStatCards() {
                if (openNumberText != null && resolvedNumberText != null) {
                        long openCount = ticketList.stream().filter(t -> "Open".equalsIgnoreCase(t.status) || "In Progress".equalsIgnoreCase(t.status)).count();
                        long resolvedCount = ticketList.stream().filter(t -> "Resolved".equalsIgnoreCase(t.status)).count();
                        openNumberText.setText(String.valueOf(openCount));
                        resolvedNumberText.setText(String.valueOf(resolvedCount));
                }
        }

        private VBox createFaqItem(String question, String answer) {
                Button faqBtn = new Button(question + "   ▾");
                faqBtn.setMaxWidth(Double.MAX_VALUE);
                faqBtn.setStyle(
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-text-fill: #292524;" +
                                "-fx-padding: 12 14;" +
                                "-fx-background-color: #FAF6F2;" +
                                "-fx-border-color: #E6DDD5;" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;" +
                                "-fx-cursor: hand;");
                faqBtn.setAlignment(Pos.CENTER_LEFT);

                Text ansText = new Text(answer);
                ansText.setFont(Font.font("Arial", 13));
                ansText.setFill(Color.web("#57534E"));
                ansText.setWrappingWidth(680);

                VBox ansBox = new VBox(ansText);
                ansBox.setPadding(new Insets(10, 16, 12, 16));
                ansBox.setStyle("-fx-background-color: white; -fx-border-color: #EAE3DC; -fx-border-radius: 8; -fx-background-radius: 8;");
                ansBox.setVisible(false);
                ansBox.setManaged(false);

                faqBtn.setOnAction(e -> {
                        boolean isVisible = ansBox.isVisible();
                        ansBox.setVisible(!isVisible);
                        ansBox.setManaged(!isVisible);
                        faqBtn.setText(question + (isVisible ? "   ▾" : "   ▴"));
                        if (!isVisible) {
                                faqBtn.setStyle(
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #A83E00;" +
                                                "-fx-padding: 12 14;" +
                                                "-fx-background-color: #FFF2E8;" +
                                                "-fx-border-color: #FFCCAA;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-cursor: hand;");
                        } else {
                                faqBtn.setStyle(
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #292524;" +
                                                "-fx-padding: 12 14;" +
                                                "-fx-background-color: #FAF6F2;" +
                                                "-fx-border-color: #E6DDD5;" +
                                                "-fx-border-radius: 8;" +
                                                "-fx-background-radius: 8;" +
                                                "-fx-cursor: hand;");
                        }
                });

                return new VBox(6, faqBtn, ansBox);
        }

        private VBox createNotificationCard(String title, String body, String bgHex) {
                Text t = new Text(title);
                t.setFont(Font.font("Arial", FontWeight.BOLD, 13));
                t.setFill(Color.web("#333333"));

                Text b = new Text(body);
                b.setFont(Font.font("Arial", 12));
                b.setFill(Color.web("#666666"));
                b.setWrappingWidth(290);

                VBox box = new VBox(4, t, b);
                box.setPadding(new Insets(10, 12, 10, 12));
                box.setStyle("-fx-background-color:" + bgHex + "; -fx-background-radius:8;");
                return box;
        }

        private HBox createTopActions() {
                StackPane bellStack = new StackPane();

                Image notificationImage = new Image(
                                getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView notificationIcon = new ImageView(notificationImage);
                notificationIcon.setFitWidth(22);
                notificationIcon.setFitHeight(22);
                notificationIcon.setPreserveRatio(true);

                Rectangle badgeBg = new Rectangle(16, 16);
                badgeBg.setArcWidth(16);
                badgeBg.setArcHeight(16);
                badgeBg.setFill(Color.web("#FF3B30"));

                notificationBadgeText.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                notificationBadgeText.setFill(Color.WHITE);

                badgeContainer.getChildren().setAll(badgeBg, notificationBadgeText);
                badgeContainer.setAlignment(Pos.CENTER);
                StackPane.setAlignment(badgeContainer, Pos.TOP_RIGHT);
                badgeContainer.setTranslateX(6);
                badgeContainer.setTranslateY(-6);
                badgeContainer.setVisible(false);

                bellStack.getChildren().addAll(notificationIcon, badgeContainer);

                Text notificationName = new Text("Notifications");
                notificationName.setFont(Font.font("Arial", 14));

                HBox notificationAction = new HBox(6, bellStack, notificationName);
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

                HBox chatbotAction = new HBox(6, chatbotIcon, chatbotName);
                chatbotAction.setAlignment(Pos.CENTER_LEFT);
                chatbotAction.setStyle("-fx-cursor:hand;");

                chatbotAction.setOnMouseClicked(e -> {
                        SmartAssistantUI chatPage = new SmartAssistantUI();
                        Homepage.HomepageStage.setScene(chatPage.getUserScene());
                });

                Popup notificationPopup = new Popup();

                Text notificationTitle = new Text("Notifications");
                notificationTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));

                Button markRead = new Button("Mark all as read");
                markRead.setStyle(
                                "-fx-background-color:transparent; -fx-text-fill:#E65300; -fx-font-size:12px; -fx-cursor:hand;");

                Region notificationGrow = new Region();
                HBox.setHgrow(notificationGrow, Priority.ALWAYS);

                HBox notificationHeader = new HBox(notificationTitle, notificationGrow, markRead);
                notificationHeader.setAlignment(Pos.CENTER_LEFT);

                notificationItemsBox.getChildren().clear();
                notificationItemsBox.getChildren().add(createNotificationCard("New Ticket Created", "#TKT-1024 requires admin response.", "#FFF4ED"));
                notificationItemsBox.getChildren().add(createNotificationCard("Payment Query", "#TKT-1023 payout inquiry in progress.", "#F4FFF7"));

                long openCount = ticketList.stream().filter(t -> "Open".equalsIgnoreCase(t.status) || "In Progress".equalsIgnoreCase(t.status)).count();
                if (openCount > 0) {
                        notificationBadgeText.setText(String.valueOf(openCount));
                        badgeContainer.setVisible(true);
                }

                ScrollPane notifScroll = new ScrollPane(notificationItemsBox);
                notifScroll.setFitToWidth(true);
                notifScroll.setPrefHeight(220);
                notifScroll.setMaxHeight(260);
                notifScroll.setStyle(
                                "-fx-background-color:transparent; -fx-background:transparent; -fx-border-color:transparent;");

                VBox notificationBox = new VBox(12, notificationHeader, new Separator(), notifScroll);
                notificationBox.setPrefWidth(340);
                notificationBox.setPadding(new Insets(16));
                notificationBox.setStyle(
                                "-fx-background-color:white; -fx-border-color:#DECEBE; -fx-border-width:1; -fx-border-radius:12; -fx-background-radius:12; -fx-effect:dropshadow(gaussian, rgba(0,0,0,0.12),15,0,0,5);");

                notificationPopup.getContent().add(notificationBox);

                notificationAction.setOnMouseClicked(e -> {
                        if (notificationPopup.isShowing()) {
                                notificationPopup.hide();
                        } else {
                                javafx.geometry.Bounds bellPosition = notificationAction.localToScreen(
                                                notificationAction.getBoundsInLocal());
                                if (bellPosition != null) {
                                        notificationPopup.show(
                                                        notificationAction,
                                                        bellPosition.getMaxX() - 340,
                                                        bellPosition.getMaxY() + 10);
                                }
                        }
                });

                markRead.setOnAction(e -> {
                        notificationItemsBox.getChildren().forEach(node -> {
                                if (node instanceof VBox) {
                                        node.setStyle("-fx-background-color:#F8F8F8; -fx-background-radius:8;");
                                }
                        });
                        badgeContainer.setVisible(false);
                });

                HBox topActions = new HBox(24, notificationAction, chatbotAction);
                topActions.setAlignment(Pos.CENTER_RIGHT);

                return topActions;
        }

}
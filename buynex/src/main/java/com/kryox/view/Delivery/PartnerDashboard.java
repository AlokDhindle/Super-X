// package com.kryox.view.Delivery;

// import com.kryox.config.DelivrayFirebaseConfig;
// import com.kryox.model.Delivery.PartnerConstants;
// import com.google.cloud.firestore.DocumentSnapshot;
// import com.google.cloud.firestore.Firestore;
// import com.google.cloud.firestore.ListenerRegistration;

// import javafx.application.Platform;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ContextMenu;
// import javafx.scene.control.Label;
// import javafx.scene.control.MenuItem;
// import javafx.scene.control.ProgressBar;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.BorderPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;

// import java.util.ArrayList;
// import java.util.List;

// public class PartnerDashboard {

//     private static final String ORANGE_PRIMARY = "#f46a06";
//     private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
//     private static final String BG_COLOR = "#fbfbfe";
//     private static final String BORDER_COLOR = "#f0edf2";
//     private static final String SIDEBAR_BG = "#ffffff";

//     private static final List<Button> navButtons = new ArrayList<>();
//     private static ListenerRegistration dashboardOrderListener;

//     // =========================================================================
//     // DYNAMIC FIRESTORE-READY DATA MODEL FOR PARTNER DASHBOARD
//     // =========================================================================
//     public static class DashboardData {
//         public String partnerName;
//         public String partnerTier;

//         public boolean isOnline = true;
//         public String rankingNotice = "You're ranked in the top 5% of partners this week.";

//         // Key Metric 1: Deliveries
//         public int todayDeliveries = 24;
//         public String deliveriesGrowth = "+12%";
//         public int deliveriesGoal = 30;

//         // Key Metric 2: Current Earnings (INR ₹)
//         public double currentEarnings = 342.15;
//         public double currentTips = 84.50;
//         public double earningsGoalProgress = 0.65; // 65%

//         // Key Metric 3: Ratings
//         public double averageRating = 4.92;
//         public int totalReviews = 92;

//         // High Demand Surge Card
//         public String surgeLocation = "FC Road & Deccan, Pune";
//         public String surgeMultiplier = "2.5x higher";

//         // Weekly Target Goal Card
//         public double weeklyGoalPercent = 0.80; // 80%
//         public int deliveriesLeftForBonus = 12;
//         public double bonusReward = 100.00;

//         // Dynamic List of Recent Orders
//         public List<RecentOrderRecord> recentOrders = new ArrayList<>();

//         public DashboardData() {
//             this.partnerName = PartnerConstants.FULL_NAME;
//             this.partnerTier = PartnerConstants.PARTNER_TIER;
//             loadDummyOrders();
//         }

//         public void loadDummyOrders() {
//             recentOrders.clear();
//             recentOrders.add(
//                     new RecentOrderRecord("DNX-5281", "Pick up in 5mins", 124.00, "IN TRANSIT", "#f97316", "#ffedd5"));
//             recentOrders.add(
//                     new RecentOrderRecord("DNX-9075", "0.4 km away", 189.00, "COMPLETED", "#16a34a", "#dcfce7"));
//             recentOrders.add(
//                     new RecentOrderRecord("DNX-9209", "15 mins ago", 85.00, "COMPLETED", "#16a34a", "#dcfce7"));
//         }
//     }

//     public static class RecentOrderRecord {
//         public String orderId;
//         public String subtext;
//         public double amount;
//         public String statusText;
//         public String statusColor;
//         public String statusBg;

//         public RecentOrderRecord(String orderId, String subtext, double amount, String statusText, String statusColor,
//                 String statusBg) {
//             this.orderId = orderId;
//             this.subtext = subtext;
//             this.amount = amount;
//             this.statusText = statusText;
//             this.statusColor = statusColor;
//             this.statusBg = statusBg;
//         }
//     }

//     public static void show(Stage primaryStage) {
//         DashboardData data = new DashboardData();
//         show(primaryStage, data);
//         attachRealtimeDashboardListener(primaryStage, data);
//     }

//     public static void show(Stage primaryStage, DashboardData data) {
//         BorderPane root = new BorderPane();
//         root.setStyle("-fx-background-color: " + BG_COLOR + ";");

//         // 1. Top Bar
//         root.setTop(createTopHeader(primaryStage, data));

//         // 2. Sidebar
//         root.setLeft(createSidebar(primaryStage, data));

//         // 3. Center Dashboard View inside ScrollPane
//         VBox dashboardContent = createDashboardView(primaryStage, data);
//         ScrollPane scrollPane = new ScrollPane(dashboardContent);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setFitToHeight(false);
//         scrollPane.setPannable(true);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-background: " + BG_COLOR + ";" +
//                         "-fx-border-color: transparent;");

//         root.setCenter(scrollPane);

//         if (primaryStage.getScene() == null) {
//             Scene scene = new Scene(root);
//             primaryStage.setScene(scene);
//         } else {
//             primaryStage.getScene().setRoot(root);
//         }

//         primaryStage.setTitle("BuyNeX - Partner Dashboard");
//         primaryStage.setMaximized(true);
//         primaryStage.show();
//     }

//     // =========================================================================
//     // REALTIME FIRESTORE LISTENER
//     // =========================================================================
//     private static void attachRealtimeDashboardListener(Stage primaryStage, DashboardData data) {
//         try {
//             if (dashboardOrderListener != null) {
//                 dashboardOrderListener.remove();
//             }

//             Firestore db = DelivrayFirebaseConfig.getFireStore();
//             dashboardOrderListener = db.collection("orders").addSnapshotListener((snapshots, error) -> {
//                 if (error != null || snapshots == null) {
//                     return;
//                 }

//                 Platform.runLater(() -> {
//                     List<RecentOrderRecord> liveOrders = new ArrayList<>();
//                     double earned = 0.0;
//                     int deliveredCount = 0;

//                     for (DocumentSnapshot doc : snapshots.getDocuments()) {
//                         String status = doc.getString("status");
//                         if (status == null) status = "PLACED";

//                         String partnerId = doc.getString("deliveryPartnerId");
//                         boolean isForMe = partnerId == null || partnerId.isEmpty()
//                                 || (PartnerConstants.UID != null && partnerId.equals(partnerId));

//                         if (isForMe) {
//                             String orderId = doc.getId();
//                             String displayId = "DNX-" + (orderId.length() > 4 ? orderId.substring(0, 4).toUpperCase() : orderId);
//                             String subtext = doc.getString("shopName") != null ? doc.getString("shopName") : "Store Order";

//                             double amt = 120.00;
//                             if (doc.get("totalAmount") != null) {
//                                 try {
//                                     amt = Double.parseDouble(doc.get("totalAmount").toString());
//                                 } catch (Exception ignored) {}
//                             }

//                             String statusText = status.toUpperCase();
//                             String statusColor = "#f97316";
//                             String statusBg = "#ffedd5";

//                             if ("DELIVERED".equalsIgnoreCase(status) || "COMPLETED".equalsIgnoreCase(status)) {
//                                 statusColor = "#16a34a";
//                                 statusBg = "#dcfce7";
//                                 statusText = "COMPLETED";
//                                 earned += amt;
//                                 deliveredCount++;
//                             } else if ("ACCEPTED".equalsIgnoreCase(status)) {
//                                 statusColor = "#2563eb";
//                                 statusBg = "#dbeafe";
//                                 statusText = "IN TRANSIT";
//                             }

//                             liveOrders.add(new RecentOrderRecord(displayId, subtext, amt, statusText, statusColor, statusBg));
//                         }
//                     }

//                     if (!liveOrders.isEmpty()) {
//                         data.recentOrders = liveOrders;
//                         data.todayDeliveries = deliveredCount > 0 ? deliveredCount : liveOrders.size();
//                         data.currentEarnings = earned > 0 ? earned : data.currentEarnings;
//                         PartnerDashboard.show(primaryStage, data);
//                     }
//                 });
//             });
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//     }

//     // =========================================================================
//     // TOP HEADER
//     // =========================================================================
//     private static BorderPane createTopHeader(Stage primaryStage, DashboardData data) {
//         BorderPane topBar = new BorderPane();
//         topBar.setPrefHeight(60);
//         topBar.setStyle(
//                 "-fx-background-color: white;" +
//                         "-fx-border-color: " + BORDER_COLOR + ";" +
//                         "-fx-border-width: 0 0 1 0;" +
//                         "-fx-padding: 0 35 0 30;");

//         Text title = new Text("Partner Dashboard");
//         title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #a94717;");
//         topBar.setLeft(new HBox(title));
//         ((HBox) topBar.getLeft()).setAlignment(Pos.CENTER_LEFT);

//         HBox rightControls = new HBox(16);
//         rightControls.setAlignment(Pos.CENTER_RIGHT);

//         // Online / Offline Status Badge
//         HBox statusPill = new HBox(8);
//         statusPill.setAlignment(Pos.CENTER);
//         statusPill.setPadding(new Insets(4, 10, 4, 10));
//         statusPill.setStyle(data.isOnline
//                 ? "-fx-background-color: #dcfce7; -fx-background-radius: 14;"
//                 : "-fx-background-color: #f1f3f7; -fx-background-radius: 14;");

//         Label statusLbl = new Label(data.isOnline ? "ONLINE" : "OFFLINE");
//         statusLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: "
//                 + (data.isOnline ? "#16a34a;" : "#7d8592;"));
//         Circle toggleDot = new Circle(5, data.isOnline ? Color.web("#16a34a") : Color.web("#cbd0d8"));
//         statusPill.getChildren().addAll(statusLbl, toggleDot);

//         Label bellIcon = new Label("🔔");
//         bellIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
//         bellIcon.setOnMouseClicked(e -> PartnerNotifications.show(primaryStage, "DASHBOARD"));

//         Label chatIcon = new Label("💬");
//         chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
//         chatIcon.setOnMouseClicked(e -> PartnerChatSupport.show(primaryStage, "DASHBOARD"));

//         StackPane userAvatarPane = createAvatarNode(15);
//         userAvatarPane.setStyle("-fx-cursor: hand;");

//         ContextMenu userMenu = new ContextMenu();
//         userMenu.setStyle(
//                 "-fx-background-color: white;" +
//                         "-fx-background-radius: 8;" +
//                         "-fx-border-radius: 8;" +
//                         "-fx-border-color: #e5e7eb;" +
//                         "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

//         MenuItem itemProfile = new MenuItem("👤   View Profile & Settings");
//         itemProfile.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
//         itemProfile.setOnAction(e -> PartnerSettings.show(primaryStage));

//         MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
//         itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
//         itemAvailability.setOnAction(e -> PartnerAvailability.show(primaryStage));

//         MenuItem itemLogout = new MenuItem("↪   Logout");
//         itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
//         itemLogout.setOnAction(e -> {
//             if (dashboardOrderListener != null) dashboardOrderListener.remove();
//             PartnerConstants.clear();
//             Deliverylogin.show(primaryStage);
//         });

//         userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

//         userAvatarPane.setOnMouseClicked(e -> {
//             if (!userMenu.isShowing()) {
//                 userMenu.show(userAvatarPane, javafx.geometry.Side.BOTTOM, -120, 8);
//             } else {
//                 userMenu.hide();
//             }
//         });

//         rightControls.getChildren().addAll(statusPill, bellIcon, chatIcon, userAvatarPane);
//         topBar.setRight(rightControls);

//         return topBar;
//     }

//     // =========================================================================
//     // 1. SIDEBAR NAVIGATION
//     // =========================================================================
//     private static VBox createSidebar(Stage primaryStage, DashboardData data) {
//         VBox sidebar = new VBox(12);
//         sidebar.setPrefWidth(220);
//         sidebar.setMinWidth(220);
//         sidebar.setMaxWidth(220);
//         sidebar.setPadding(new Insets(20, 16, 25, 16));
//         sidebar.setStyle(
//                 "-fx-background-color: " + SIDEBAR_BG + ";" +
//                         "-fx-border-color: " + BORDER_COLOR + ";" +
//                         "-fx-border-width: 0 1 0 0;");

//         navButtons.clear();

//         Text logo = new Text("BuyNeX");
//         logo.setStyle("-fx-font-size: 26px; -fx-fill: " + ORANGE_GRADIENT + "; -fx-font-weight: bold;");
//         VBox logoBox = new VBox(logo);
//         logoBox.setPadding(new Insets(0, 0, 15, 8));

//         Runnable openDashboardTask = () -> PartnerDashboard.show(primaryStage, data);
//         Runnable openDeliveriesTask = () -> PartnerDeliveries.show(primaryStage);
//         Runnable openNavigationTask = () -> PartnerNavigation.show(primaryStage);
//         Runnable openEarningsTask = () -> PartnerEarnings.show(primaryStage);
//         Runnable openAvailabilityTask = () -> PartnerAvailability.show(primaryStage);
//         Runnable openSettingsTask = () -> PartnerSettings.show(primaryStage);
//         Runnable logoutTask = () -> {
//             if (dashboardOrderListener != null) dashboardOrderListener.remove();
//             PartnerConstants.clear();
//             Deliverylogin.show(primaryStage);
//         };

//         Button btnDashboard = createNavButton("▤   Dashboard");
//         Button btnDeliveries = createNavButton("📦   My Deliveries");
//         Button btnNavigation = createNavButton("🧭   Navigation");
//         Button btnEarnings = createNavButton("💵   Earnings");
//         Button btnAvailability = createNavButton("⏱   Availability");
//         Button btnSettings = createNavButton("⚙   Settings");

//         btnDashboard.setOnAction(e -> {
//             setActiveButton(btnDashboard);
//             openDashboardTask.run();
//         });

//         btnDeliveries.setOnAction(e -> {
//             setActiveButton(btnDeliveries);
//             openDeliveriesTask.run();
//         });

//         btnNavigation.setOnAction(e -> {
//             setActiveButton(btnNavigation);
//             openNavigationTask.run();
//         });

//         btnEarnings.setOnAction(e -> {
//             setActiveButton(btnEarnings);
//             openEarningsTask.run();
//         });

//         btnAvailability.setOnAction(e -> {
//             setActiveButton(btnAvailability);
//             openAvailabilityTask.run();
//         });

//         btnSettings.setOnAction(e -> {
//             setActiveButton(btnSettings);
//             openSettingsTask.run();
//         });

//         setActiveButton(btnDashboard);

//         VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability);

//         Region spacer = new Region();
//         VBox.setVgrow(spacer, Priority.ALWAYS);

//         // Dynamic Profile Card
//         VBox profileCard = new VBox(4);
//         profileCard.setPadding(new Insets(10, 12, 10, 12));
//         profileCard.setStyle(
//                 "-fx-background-color: #f8f8fb;" +
//                         "-fx-background-radius: 10;" +
//                         "-fx-border-color: #e5e7eb;" +
//                         "-fx-border-radius: 10;" +
//                         "-fx-cursor: hand;");

//         HBox statusBox = new HBox(5);
//         statusBox.setAlignment(Pos.CENTER_LEFT);
//         Circle activeDot = new Circle(4, data.isOnline ? Color.web("#22c55e") : Color.web("#9ca3af"));
//         Label activeText = new Label(data.isOnline ? "Active Now" : "Inactive");
//         activeText.setStyle("-fx-font-size: 9px; -fx-text-fill: " + (data.isOnline ? "#22c55e;" : "#9ca3af;")
//                 + " -fx-font-weight: bold;");
//         statusBox.getChildren().addAll(activeDot, activeText);

//         HBox userBox = new HBox(8);
//         userBox.setAlignment(Pos.CENTER_LEFT);

//         StackPane avatar = createAvatarNode(14);

//         VBox userDetails = new VBox(1);
//         Label userName = new Label(PartnerConstants.FULL_NAME);
//         userName.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #222;");
//         Label userRole = new Label(PartnerConstants.PARTNER_TIER);
//         userRole.setStyle("-fx-font-size: 9px; -fx-text-fill: #888;");
//         userDetails.getChildren().addAll(userName, userRole);
//         userBox.getChildren().addAll(avatar, userDetails);

//         profileCard.getChildren().addAll(statusBox, userBox);
//         profileCard.setOnMouseClicked(e -> PartnerProfile.show(primaryStage));

//         Button btnLogout = new Button("↪   Logout");
//         btnLogout.setMaxWidth(Double.MAX_VALUE);
//         btnLogout.setAlignment(Pos.CENTER_LEFT);
//         btnLogout.setPrefHeight(34);
//         btnLogout.setStyle(
//                 "-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
//         btnLogout.setOnAction(e -> logoutTask.run());

//         VBox bottomNav = new VBox(4, profileCard, btnSettings, btnLogout);
//         sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
//         return sidebar;
//     }

//     // =========================================================================
//     // 2. MAIN DASHBOARD CONTENT AREA
//     // =========================================================================
//     private static VBox createDashboardView(Stage primaryStage, DashboardData data) {
//         VBox main = new VBox(22);
//         main.setPadding(new Insets(24, 35, 60, 35));
//         main.setFillWidth(true);
//         main.setMinHeight(Region.USE_PREF_SIZE);

//         BorderPane header = new BorderPane();
//         VBox welcomeBox = new VBox(3);
//         Text greeting = new Text("Hello, " + data.partnerName.split(" ")[0] + "!");
//         greeting.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #111827;");
//         Text subGreeting = new Text(data.rankingNotice);
//         subGreeting.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
//         welcomeBox.getChildren().addAll(greeting, subGreeting);
//         header.setLeft(welcomeBox);

//         HBox actionBtns = new HBox(10);
//         actionBtns.setAlignment(Pos.CENTER_RIGHT);

//         Button btnGoOnline = new Button(data.isOnline ? "● Go Offline" : "⚡ Go Online");
//         btnGoOnline.setPrefHeight(36);
//         btnGoOnline.setStyle("-fx-background-color: " + (data.isOnline ? "#374151" : ORANGE_PRIMARY)
//                 + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 8; -fx-padding: 0 16 0 16; -fx-cursor: hand;");
//         btnGoOnline.setOnAction(e -> {
//             data.isOnline = !data.isOnline;
//             PartnerDashboard.show(primaryStage, data);
//         });

//         Button btnTasks = new Button("▤ View Tasks");
//         btnTasks.setPrefHeight(36);
//         btnTasks.setStyle(
//                 "-fx-background-color: white; -fx-border-color: #d1d5db; -fx-text-fill: #374151; -fx-font-size: 12px; -fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; -fx-padding: 0 16 0 16; -fx-cursor: hand;");
//         btnTasks.setOnAction(e -> PartnerDeliveries.show(primaryStage));

//         actionBtns.getChildren().addAll(btnGoOnline, btnTasks);
//         header.setRight(actionBtns);

//         HBox statsRow = new HBox(16);
//         statsRow.setFillHeight(true);
//         VBox statDeliveries = createStatCard("Today's Deliveries", String.valueOf(data.todayDeliveries),
//                 data.deliveriesGrowth, "Goal: " + data.deliveriesGoal + " Deliveries", "🚚");
//         VBox statEarnings = createEarningsCard(data);
//         VBox statRatings = createRatingCard(data);
//         statsRow.getChildren().addAll(statDeliveries, statEarnings, statRatings);
//         HBox.setHgrow(statDeliveries, Priority.ALWAYS);
//         HBox.setHgrow(statEarnings, Priority.ALWAYS);
//         HBox.setHgrow(statRatings, Priority.ALWAYS);

//         HBox lowerRow = new HBox(20);
//         lowerRow.setFillHeight(true);
//         VBox recentOrdersCol = createRecentOrdersSection(primaryStage, data);
//         VBox sideWidgetsCol = createSideWidgetsSection(data);
//         lowerRow.getChildren().addAll(recentOrdersCol, sideWidgetsCol);
//         HBox.setHgrow(recentOrdersCol, Priority.ALWAYS);
//         HBox.setHgrow(sideWidgetsCol, Priority.NEVER);

//         main.getChildren().addAll(header, statsRow, lowerRow);
//         return main;
//     }

//     // =========================================================================
//     // 3. DYNAMIC RECENT ORDERS SECTION
//     // =========================================================================
//     private static VBox createRecentOrdersSection(Stage primaryStage, DashboardData data) {
//         VBox container = new VBox(12);

//         BorderPane titleRow = new BorderPane();
//         Text title = new Text("Recent Orders");
//         title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-fill: #111827;");
//         titleRow.setLeft(title);

//         Label viewAll = new Label("View All");
//         viewAll.setStyle(
//                 "-fx-font-size: 11px; -fx-text-fill: " + ORANGE_PRIMARY + "; -fx-font-weight: bold; -fx-cursor: hand;");
//         viewAll.setOnMouseClicked(e -> PartnerDeliveries.show(primaryStage));
//         titleRow.setRight(viewAll);

//         container.getChildren().add(titleRow);

//         for (RecentOrderRecord order : data.recentOrders) {
//             container.getChildren().add(createOrderCard(primaryStage, order));
//         }

//         return container;
//     }

//     private static VBox createOrderCard(Stage primaryStage, RecentOrderRecord order) {
//         VBox card = new VBox();
//         card.setPadding(new Insets(12, 16, 12, 16));
//         card.setStyle(
//                 "-fx-background-color: white; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: "
//                         + BORDER_COLOR + "; -fx-border-width: 1;");

//         BorderPane row = new BorderPane();

//         HBox left = new HBox(12);
//         left.setAlignment(Pos.CENTER_LEFT);

//         VBox iconBox = new VBox();
//         iconBox.setAlignment(Pos.CENTER);
//         iconBox.setPrefSize(40, 40);
//         iconBox.setStyle("-fx-background-color: #f7f5f9; -fx-background-radius: 8;");
//         Label iconLbl = new Label("🍱");
//         iconLbl.setStyle("-fx-font-size: 16px;");
//         iconBox.getChildren().add(iconLbl);

//         VBox info = new VBox(2);
//         Label idLbl = new Label("Order #" + order.orderId);
//         idLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");
//         Label subLbl = new Label(order.subtext);
//         subLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
//         info.getChildren().addAll(idLbl, subLbl);

//         left.getChildren().addAll(iconBox, info);
//         row.setLeft(left);

//         HBox right = new HBox(14);
//         right.setAlignment(Pos.CENTER_RIGHT);

//         VBox priceInfo = new VBox(2);
//         priceInfo.setAlignment(Pos.CENTER_RIGHT);
//         Label priceLbl = new Label("₹" + String.format("%.2f", order.amount));
//         priceLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");

//         Label badge = new Label(order.statusText);
//         badge.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: " + order.statusColor
//                 + "; -fx-background-color: " + order.statusBg + "; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
//         priceInfo.getChildren().addAll(priceLbl, badge);

//         Button detailsBtn = new Button("Details");
//         detailsBtn.setPrefHeight(28);
//         detailsBtn.setStyle(
//                 "-fx-background-color: #1f2937; -fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-cursor: hand;");
//         detailsBtn.setOnAction(e -> PartnerDeliveries.show(primaryStage));

//         right.getChildren().addAll(priceInfo, detailsBtn);
//         row.setRight(right);

//         card.getChildren().add(row);
//         return card;
//     }

//     // =========================================================================
//     // 4. SIDE WIDGETS SECTION
//     // =========================================================================
//     private static VBox createSideWidgetsSection(DashboardData data) {
//         VBox container = new VBox(14);
//         container.setPrefWidth(260);
//         container.setMinWidth(260);

//         // Demand Surge Card
//         VBox demandCard = new VBox(8);
//         demandCard.setPadding(new Insets(14));
//         demandCard.setStyle("-fx-background-color: #2b303c; -fx-background-radius: 12;");

//         HBox liveBadgeBox = new HBox();
//         liveBadgeBox.setAlignment(Pos.TOP_RIGHT);
//         Label liveBadge = new Label("● Live Now");
//         liveBadge.setStyle(
//                 "-fx-background-color: rgba(255,255,255,0.2); -fx-text-fill: white; -fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 3 8 3 8; -fx-background-radius: 10;");
//         liveBadgeBox.getChildren().add(liveBadge);

//         Region spacer = new Region();
//         spacer.setPrefHeight(35);

//         Label locTitle = new Label(data.surgeLocation);
//         locTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: white;");
//         Label locSub = new Label("Current demand is " + data.surgeMultiplier);
//         locSub.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");
//         demandCard.getChildren().addAll(liveBadgeBox, spacer, locTitle, locSub);

//         // Target Goal Card
//         VBox goalCard = new VBox(6);
//         goalCard.setPadding(new Insets(14));
//         goalCard.setStyle("-fx-background-color: " + ORANGE_GRADIENT + "; -fx-background-radius: 12;");

//         BorderPane goalTitleRow = new BorderPane();
//         Label goalTitle = new Label("Weekly Goal");
//         goalTitle.setStyle("-fx-font-size: 11px; -fx-text-fill: #fef08a; -fx-font-weight: bold;");
//         Label trophy = new Label("🏆");
//         trophy.setStyle("-fx-font-size: 12px;");
//         goalTitleRow.setLeft(goalTitle);
//         goalTitleRow.setRight(trophy);

//         Label goalVal = new Label((int) (data.weeklyGoalPercent * 100) + "% Reached");
//         goalVal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");

//         ProgressBar pb = new ProgressBar(data.weeklyGoalPercent);
//         pb.setPrefHeight(6);
//         pb.setMaxWidth(Double.MAX_VALUE);
//         pb.setStyle("-fx-accent: white;");

//         Label goalDesc = new Label(
//                 data.deliveriesLeftForBonus + " more deliveries to earn ₹" + (int) data.bonusReward + " bonus!");
//         goalDesc.setStyle("-fx-font-size: 9px; -fx-text-fill: #ffedd5;");
//         goalCard.getChildren().addAll(goalTitleRow, goalVal, pb, goalDesc);

//         container.getChildren().addAll(demandCard, goalCard);
//         return container;
//     }

//     // =========================================================================
//     // 5. STATS CARD HELPERS
//     // =========================================================================
//     private static VBox createStatCard(String label, String value, String badgeText, String footer, String icon) {
//         VBox card = new VBox(10);
//         card.setPadding(new Insets(16));
//         card.setStyle(
//                 "-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: "
//                         + BORDER_COLOR + "; -fx-border-width: 1;");

//         BorderPane topRow = new BorderPane();
//         Label iconLbl = new Label(icon);
//         iconLbl.setStyle("-fx-font-size: 14px;");
//         topRow.setLeft(iconLbl);

//         if (badgeText != null) {
//             Label badge = new Label(badgeText);
//             badge.setStyle(
//                     "-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #16a34a; -fx-background-color: #dcfce7; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
//             topRow.setRight(badge);
//         }

//         Label lbl = new Label(label);
//         lbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

//         Label val = new Label(value);
//         val.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #111827;");

//         Label foot = new Label(footer);
//         foot.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

//         card.getChildren().addAll(topRow, lbl, val, foot);
//         return card;
//     }

//     private static VBox createEarningsCard(DashboardData data) {
//         VBox card = new VBox(10);
//         card.setPadding(new Insets(16));
//         card.setStyle(
//                 "-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: "
//                         + BORDER_COLOR + "; -fx-border-width: 1;");

//         BorderPane topRow = new BorderPane();
//         Label icon = new Label("💵");
//         Label tips = new Label("₹" + String.format("%.2f", data.currentTips) + " Tips");
//         tips.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #d97706;");
//         topRow.setLeft(icon);
//         topRow.setRight(tips);

//         Label lbl = new Label("Current Earnings");
//         lbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

//         Label val = new Label("₹" + String.format("%.2f", data.currentEarnings));
//         val.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #111827;");

//         ProgressBar pb = new ProgressBar(data.earningsGoalProgress);
//         pb.setPrefHeight(4);
//         pb.setMaxWidth(Double.MAX_VALUE);
//         pb.setStyle("-fx-accent: #B84208;");

//         card.getChildren().addAll(topRow, lbl, val, pb);
//         return card;
//     }

//     private static VBox createRatingCard(DashboardData data) {
//         VBox card = new VBox(10);
//         card.setPadding(new Insets(16));
//         card.setStyle(
//                 "-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: "
//                         + BORDER_COLOR + "; -fx-border-width: 1;");

//         BorderPane topRow = new BorderPane();
//         Label icon = new Label("⭐");
//         Label reviews = new Label(data.totalReviews + " Reviews");
//         reviews.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
//         topRow.setLeft(icon);
//         topRow.setRight(reviews);

//         Label lbl = new Label("Average Rating");
//         lbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

//         Label val = new Label(String.format("%.2f", data.averageRating));
//         val.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #111827;");

//         Label stars = new Label("★★★★★");
//         stars.setStyle("-fx-font-size: 11px; -fx-text-fill: #f59e0b;");

//         card.getChildren().addAll(topRow, lbl, val, stars);
//         return card;
//     }

//     // =========================================================================
//     // DYNAMIC AVATAR BUILDER
//     // =========================================================================
//     private static StackPane createAvatarNode(double radius) {
//         StackPane avatarPane = new StackPane();
//         avatarPane.setPrefSize(radius * 2, radius * 2);
//         avatarPane.setMaxSize(radius * 2, radius * 2);

//         Circle bg = new Circle(radius, Color.web("#fed7aa"));

//         if (PartnerConstants.PROFILE_PHOTO_URL != null && !PartnerConstants.PROFILE_PHOTO_URL.trim().isEmpty()) {
//             try {
//                 ImageView imgView = new ImageView(new Image(PartnerConstants.PROFILE_PHOTO_URL, true));
//                 imgView.setFitWidth(radius * 2);
//                 imgView.setFitHeight(radius * 2);
//                 imgView.setPreserveRatio(false);

//                 Circle clip = new Circle(radius, radius, radius);
//                 imgView.setClip(clip);

//                 avatarPane.getChildren().addAll(bg, imgView);
//                 return avatarPane;
//             } catch (Exception ignored) {}
//         }

//         String initials = "P";
//         if (PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.trim().isEmpty()) {
//             String[] parts = PartnerConstants.FULL_NAME.trim().split("\\s+");
//             if (parts.length >= 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
//                 initials = ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
//             } else if (!parts[0].isEmpty()) {
//                 initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
//             }
//         }

//         Label initialLabel = new Label(initials);
//         initialLabel.setStyle("-fx-font-size: " + (radius * 0.7) + "px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
//         avatarPane.getChildren().addAll(bg, initialLabel);

//         return avatarPane;
//     }

//     // =========================================================================
//     // SIDEBAR INTERACTIVITY HELPERS
//     // =========================================================================
//     private static Button createNavButton(String text) {
//         Button btn = new Button(text);
//         btn.setMaxWidth(Double.MAX_VALUE);
//         btn.setAlignment(Pos.CENTER_LEFT);
//         btn.setPrefHeight(38);
//         applyInactiveStyle(btn);
//         navButtons.add(btn);
//         return btn;
//     }

//     private static void setActiveButton(Button targetBtn) {
//         for (Button btn : navButtons) {
//             applyInactiveStyle(btn);
//         }
//         targetBtn.setStyle(
//                 "-fx-background-color: " + ORANGE_PRIMARY + ";" +
//                         "-fx-text-fill: white;" +
//                         "-fx-font-weight: bold;" +
//                         "-fx-font-size: 12px;" +
//                         "-fx-background-radius: 8;" +
//                         "-fx-padding: 0 14 0 14;" +
//                         "-fx-cursor: hand;");
//         targetBtn.setOnMouseEntered(null);
//         targetBtn.setOnMouseExited(null);
//     }

//     private static void applyInactiveStyle(Button btn) {
//         btn.setStyle(
//                 "-fx-background-color: transparent;" +
//                         "-fx-text-fill: #6b7280;" +
//                         "-fx-font-size: 12px;" +
//                         "-fx-font-weight: normal;" +
//                         "-fx-background-radius: 8;" +
//                         "-fx-padding: 0 14 0 14;" +
//                         "-fx-cursor: hand;");
//         btn.setOnMouseEntered(e -> {
//             if (!btn.getStyle().contains(ORANGE_PRIMARY)) {
//                 btn.setStyle(
//                         "-fx-background-color: #fcece3;" +
//                                 "-fx-text-fill: #a94717;" +
//                                 "-fx-font-size: 12px;" +
//                                 "-fx-background-radius: 8;" +
//                                 "-fx-padding: 0 14 0 14;" +
//                                 "-fx-cursor: hand;");
//             }
//         });
//         btn.setOnMouseExited(e -> {
//             if (!btn.getStyle().contains(ORANGE_PRIMARY)) {
//                 btn.setStyle(
//                         "-fx-background-color: transparent;" +
//                                 "-fx-text-fill: #6b7280;" +
//                                 "-fx-font-size: 12px;" +
//                                 "-fx-padding: 0 14 0 14;" +
//                                 "-fx-cursor: hand;");
//             }
//         });
//     }
// }
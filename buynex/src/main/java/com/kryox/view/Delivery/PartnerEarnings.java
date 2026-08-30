package com.kryox.view.Delivery;

import com.kryox.config.FirebaseConfig;
import com.kryox.model.Delivery.PartnerConstants;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PartnerEarnings {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String SIDEBAR_BG = "#ffffff";

    private static ListenerRegistration earningsListener;

    // =========================================================================
    // FIRESTORE-READY DYNAMIC EARNINGS DATA MODEL
    // =========================================================================
    public static class EarningsData {
        public String partnerName;
        public String partnerTier;
        public String selectedPeriod = "August 2026 (This Month)";

        // Top Cards Metrics (in INR ₹)
        public double weeklyEarnings = 1450.00;
        public String weeklyGrowth = "+15%";
        public double monthlyTotal = 5820.50;
        public String monthlyGrowth = "+11%";
        public double dailyAverage = 187.75;
        public int avgDeliveriesPerDay = 16;
        public double avgPerOrder = 4.80;

        // Chart View Toggle State
        public boolean isWeeklyChart = false;

        // Transactions List
        public List<TransactionRecord> transactions = new ArrayList<>();

        public EarningsData() {
            this.partnerName = PartnerConstants.FULL_NAME;
            this.partnerTier = PartnerConstants.PARTNER_TIER;
            loadMonthData("August 2026 (This Month)");
        }

        public void loadMonthData(String period) {
            this.selectedPeriod = period;
            this.transactions.clear();

            if (period.contains("August 2026")) {
                this.weeklyEarnings = 1450.00;
                this.weeklyGrowth = "+15%";
                this.monthlyTotal = 5820.50;
                this.monthlyGrowth = "+11%";
                this.dailyAverage = 187.75;
                this.avgDeliveriesPerDay = 16;
                this.avgPerOrder = 4.80;

                transactions.add(new TransactionRecord("ORD-98210", "Aug 17, 2026", 52.00, 5.20, "Paid"));
                transactions.add(new TransactionRecord("ORD-98205", "Aug 16, 2026", 24.50, 2.45, "Paid"));
                transactions.add(new TransactionRecord("ORD-97992", "Aug 15, 2026", 140.00, 14.00, "Paid"));
                transactions.add(new TransactionRecord("ORD-97814", "Aug 14, 2026", 35.00, 3.50, "Pending"));
                transactions.add(new TransactionRecord("ORD-97621", "Aug 12, 2026", 92.40, 9.24, "Paid"));
                transactions.add(new TransactionRecord("ORD-97402", "Aug 10, 2026", 64.00, 6.40, "Paid"));
                transactions.add(new TransactionRecord("ORD-97118", "Aug 08, 2026", 18.00, 1.80, "Paid"));
                transactions.add(new TransactionRecord("ORD-96901", "Aug 05, 2026", 115.50, 11.55, "Paid"));
                transactions.add(new TransactionRecord("ORD-96740", "Aug 02, 2026", 42.00, 4.20, "Paid"));

            } else if (period.contains("July 2026")) {
                this.weeklyEarnings = 1248.50;
                this.weeklyGrowth = "+12%";
                this.monthlyTotal = 4892.20;
                this.monthlyGrowth = "+8%";
                this.dailyAverage = 163.07;
                this.avgDeliveriesPerDay = 14;
                this.avgPerOrder = 4.20;

                transactions.add(new TransactionRecord("ORD-90210", "July 24, 2026", 42.00, 4.20, "Paid"));
                transactions.add(new TransactionRecord("ORD-90211", "July 24, 2026", 18.50, 1.85, "Pending"));
                transactions.add(new TransactionRecord("ORD-89542", "July 23, 2026", 124.30, 12.43, "Paid"));
                transactions.add(new TransactionRecord("ORD-88319", "July 23, 2026", 31.00, 3.10, "Paid"));
                transactions.add(new TransactionRecord("ORD-87132", "July 22, 2026", 88.40, 8.84, "Failed"));
                transactions.add(new TransactionRecord("ORD-86920", "July 18, 2026", 56.00, 5.60, "Paid"));
                transactions.add(new TransactionRecord("ORD-86401", "July 12, 2026", 74.50, 7.45, "Paid"));

            } else if (period.contains("June 2026")) {
                this.weeklyEarnings = 1120.00;
                this.weeklyGrowth = "+6%";
                this.monthlyTotal = 4310.80;
                this.monthlyGrowth = "+5%";
                this.dailyAverage = 143.69;
                this.avgDeliveriesPerDay = 12;
                this.avgPerOrder = 3.95;

                transactions.add(new TransactionRecord("ORD-82104", "June 28, 2026", 38.00, 3.80, "Paid"));
                transactions.add(new TransactionRecord("ORD-81992", "June 25, 2026", 64.20, 6.42, "Paid"));
                transactions.add(new TransactionRecord("ORD-80415", "June 20, 2026", 110.00, 11.00, "Paid"));
                transactions.add(new TransactionRecord("ORD-79881", "June 14, 2026", 29.50, 2.95, "Paid"));
                transactions.add(new TransactionRecord("ORD-79210", "June 08, 2026", 92.00, 9.20, "Paid"));
                transactions.add(new TransactionRecord("ORD-78904", "June 03, 2026", 45.00, 4.50, "Paid"));

            } else if (period.contains("May 2026")) {
                this.weeklyEarnings = 980.00;
                this.weeklyGrowth = "+4%";
                this.monthlyTotal = 3920.00;
                this.monthlyGrowth = "+3%";
                this.dailyAverage = 130.66;
                this.avgDeliveriesPerDay = 11;
                this.avgPerOrder = 3.80;

                transactions.add(new TransactionRecord("ORD-74102", "May 29, 2026", 45.00, 4.50, "Paid"));
                transactions.add(new TransactionRecord("ORD-73911", "May 22, 2026", 80.00, 8.00, "Paid"));
                transactions.add(new TransactionRecord("ORD-72418", "May 15, 2026", 55.50, 5.55, "Paid"));
                transactions.add(new TransactionRecord("ORD-71009", "May 08, 2026", 22.00, 2.20, "Paid"));
                transactions.add(new TransactionRecord("ORD-70881", "May 02, 2026", 68.00, 6.80, "Paid"));
            }
        }
    }

    public static class TransactionRecord {
        public String orderId;
        public String date;
        public double amount;
        public double commission;
        public String status;

        public TransactionRecord(String orderId, String date, double amount, double commission, String status) {
            this.orderId = orderId;
            this.date = date;
            this.amount = amount;
            this.commission = commission;
            this.status = status;
        }
    }

    public static void show(Scene scene) {
        EarningsData data = new EarningsData();
        show(scene, data);
        attachRealtimeEarningsListener(scene, data);
    }

    public static void show(Scene scene, EarningsData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Header Search Bar
        root.setTop(createTopHeader(scene));

        // 2. Left Sidebar Navigation
        root.setLeft(createSidebar(scene, data));

        // 3. Scrollable Main Content
        VBox mainContent = createMainContent(scene, data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: " + BG_COLOR + ";" +
                        "-fx-border-color: transparent;");

        root.setCenter(scrollPane);

        if (scene != null) {
            scene.setRoot(root);
        }
    }

    // =========================================================================
    // REALTIME FIRESTORE EARNINGS SYNC
    // =========================================================================
    private static void attachRealtimeEarningsListener(Scene scene, EarningsData data) {
        try {
            if (earningsListener != null) {
                earningsListener.remove();
            }

            Firestore db = FirebaseConfig.getFireStore();
            earningsListener = db.collection("orders").addSnapshotListener((snapshots, error) -> {
                if (error != null || snapshots == null) {
                    return;
                }

                Platform.runLater(() -> {
                    List<TransactionRecord> realTxList = new ArrayList<>();
                    double totalEarned = 0.0;

                    for (DocumentSnapshot doc : snapshots.getDocuments()) {
                        String status = doc.getString("status");
                        String partnerId = doc.getString("deliveryPartnerId");

                        boolean isMine = (PartnerConstants.UID != null && !PartnerConstants.UID.isEmpty())
                                ? PartnerConstants.UID.equals(partnerId)
                                : true;

                        if ("DELIVERED".equalsIgnoreCase(status) && isMine) {
                            String orderId = doc.getId();
                            String displayId = "ORD-" + (orderId.length() > 5 ? orderId.substring(0, 5).toUpperCase() : orderId);
                            String dateStr = doc.getString("deliveredAt") != null ? doc.getString("deliveredAt") : "Today";

                            double orderAmount = 50.00;
                            if (doc.get("totalAmount") != null) {
                                try {
                                    orderAmount = Double.parseDouble(doc.get("totalAmount").toString());
                                } catch (Exception ignored) {}
                            }

                            double comm = orderAmount * 0.10;
                            realTxList.add(new TransactionRecord(displayId, dateStr, orderAmount, comm, "Paid"));
                            totalEarned += orderAmount;
                        }
                    }

                    if (!realTxList.isEmpty()) {
                        data.transactions = realTxList;
                        data.monthlyTotal = totalEarned;
                        data.weeklyEarnings = totalEarned * 0.40;
                        data.dailyAverage = totalEarned / Math.max(1, realTxList.size());
                        data.avgDeliveriesPerDay = realTxList.size();
                        data.avgPerOrder = totalEarned / Math.max(1, realTxList.size());
                        PartnerEarnings.show(scene, data);
                    }
                });
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // =========================================================================
    // TOP SEARCH HEADER BAR
    // =========================================================================
    private static BorderPane createTopHeader(Scene scene) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-padding: 0 35 0 30;");

        HBox searchContainer = new HBox(8);
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setMaxWidth(360);
        searchContainer.setPrefHeight(34);
        searchContainer.setPadding(new Insets(0, 12, 0, 12));
        searchContainer.setStyle(
                "-fx-background-color: #f8f8fb;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-border-radius: 18;" +
                        "-fx-background-radius: 18;");

        Label searchIcon = new Label("🔍");
        searchIcon.setStyle("-fx-font-size: 11px; -fx-text-fill: #9ca3af;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search orders...");
        searchField.setStyle(
                "-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 12px; -fx-prompt-text-fill: #9ca3af;");
        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchContainer.getChildren().addAll(searchIcon, searchField);
        topBar.setCenter(searchContainer);

        HBox rightIcons = new HBox(16);
        rightIcons.setAlignment(Pos.CENTER_RIGHT);

        Label notifIcon = new Label("🔔");
        notifIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        notifIcon.setOnMouseClicked(e -> PartnerNotifications.show(scene, "EARNINGS"));

        Label chatIcon = new Label("💬");
        chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        chatIcon.setOnMouseClicked(e -> PartnerChatSupport.show(scene, "EARNINGS"));

        StackPane userAvatarPane = createAvatarNode(15);
        userAvatarPane.setStyle("-fx-cursor: hand;");

        ContextMenu userMenu = new ContextMenu();
        userMenu.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        MenuItem itemProfile = new MenuItem("👤   View Profile & Settings");
        itemProfile.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemProfile.setOnAction(e -> PartnerSettings.show(scene));

        MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
        itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemAvailability.setOnAction(e -> PartnerAvailability.show(scene));

        MenuItem itemLogout = new MenuItem("↪   Logout");
        itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemLogout.setOnAction(e -> {
            if (earningsListener != null) earningsListener.remove();
            PartnerConstants.clear();
            Deliverylogin.show(scene);
        });

        userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

        userAvatarPane.setOnMouseClicked(e -> {
            if (!userMenu.isShowing()) {
                userMenu.show(userAvatarPane, Side.BOTTOM, -120, 8);
            } else {
                userMenu.hide();
            }
        });

        rightIcons.getChildren().addAll(notifIcon, chatIcon, userAvatarPane);
        topBar.setRight(rightIcons);

        return topBar;
    }

    // =========================================================================
    // 1. LEFT SIDEBAR
    // =========================================================================
    private static VBox createSidebar(Scene scene, EarningsData data) {
        VBox sidebar = new VBox(12);
        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.setPadding(new Insets(20, 16, 25, 16));
        sidebar.setStyle(
                "-fx-background-color: " + SIDEBAR_BG + ";" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 0 1 0 0;");

        Text logo = new Text("BuyNeX");
        logo.setStyle("-fx-font-size: 26px; -fx-fill: " + ORANGE_GRADIENT + "; -fx-font-weight: bold;");
        VBox logoBox = new VBox(logo);
        logoBox.setPadding(new Insets(0, 0, 15, 8));

        Runnable openDashboardTask = () -> PartnerDashboard.show(scene);
        Runnable openDeliveriesTask = () -> PartnerDeliveries.show(scene);
        Runnable openNavigationTask = () -> PartnerNavigation.show(scene);
        Runnable openEarningsTask = () -> PartnerEarnings.show(scene, data);
        Runnable openAvailabilityTask = () -> PartnerAvailability.show(scene);
        Runnable openSettingsTask = () -> PartnerSettings.show(scene);
        Runnable logoutTask = () -> {
            if (earningsListener != null) earningsListener.remove();
            PartnerConstants.clear();
            Deliverylogin.show(scene);
        };

        Button btnDashboard = createNavButton("▤   Dashboard", false);
        btnDashboard.setOnAction(e -> openDashboardTask.run());

        Button btnDeliveries = createNavButton("📦   My Deliveries", false);
        btnDeliveries.setOnAction(e -> openDeliveriesTask.run());

        Button btnNavigation = createNavButton("🧭   Navigation", false);
        btnNavigation.setOnAction(e -> openNavigationTask.run());

        Button btnEarnings = createNavButton("💵   Earnings", true);
        btnEarnings.setOnAction(e -> openEarningsTask.run());

        Button btnAvailability = createNavButton("⏱   Availability", false);
        btnAvailability.setOnAction(e -> openAvailabilityTask.run());

        VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Dynamic Profile Card
        VBox profileCard = new VBox(4);
        profileCard.setPadding(new Insets(10, 12, 10, 12));
        profileCard.setStyle(
                "-fx-background-color: #f8f8fb;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-border-radius: 10;" +
                        "-fx-cursor: hand;");

        HBox userBox = new HBox(8);
        userBox.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatarNode(14);

        VBox userDetails = new VBox(1);
        Label userName = new Label(PartnerConstants.FULL_NAME);
        userName.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label userRole = new Label(PartnerConstants.PARTNER_TIER);
        userRole.setStyle("-fx-font-size: 9px; -fx-text-fill: #6b7280;");
        userDetails.getChildren().addAll(userName, userRole);

        userBox.getChildren().addAll(avatar, userDetails);
        profileCard.getChildren().add(userBox);
        profileCard.setOnMouseClicked(e -> PartnerProfile.show(scene));

        Button btnSettings = createNavButton("⚙   Settings", false);
        btnSettings.setOnAction(e -> openSettingsTask.run());

        Button btnLogout = new Button("↪   Logout");
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setAlignment(Pos.CENTER_LEFT);
        btnLogout.setPrefHeight(34);
        btnLogout.setStyle(
                "-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
        btnLogout.setOnAction(e -> logoutTask.run());

        VBox bottomNav = new VBox(6, profileCard, btnSettings, btnLogout);
        sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
        return sidebar;
    }

    // =========================================================================
    // 2. MAIN REPORT BODY
    // =========================================================================
    private static VBox createMainContent(Scene scene, EarningsData data) {
        VBox main = new VBox(22);
        main.setPadding(new Insets(26, 35, 60, 35));
        main.setFillWidth(true);

        BorderPane headerRow = new BorderPane();
        VBox titleBox = new VBox(3);
        Text title = new Text("Earnings Report");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #111827;");
        Text subTitle = new Text("Reviewing performance & payout records for: " + data.selectedPeriod);
        subTitle.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);
        headerRow.setLeft(titleBox);

        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_RIGHT);

        Button btnPeriodDropdown = new Button("📅   " + data.selectedPeriod + "   ⌄");
        btnPeriodDropdown.setPrefHeight(36);
        btnPeriodDropdown.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #d1d5db;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1f2937;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0 14 0 14;");

        ContextMenu monthMenu = new ContextMenu();
        String[] availableMonths = new String[] {
                "August 2026 (This Month)",
                "July 2026",
                "June 2026",
                "May 2026"
        };

        for (String month : availableMonths) {
            MenuItem item = new MenuItem((month.equals(data.selectedPeriod) ? "✓   " : "   ") + month);
            item.setStyle("-fx-font-size: 11px; -fx-padding: 6 12 6 12;");
            item.setOnAction(e -> {
                data.loadMonthData(month);
                PartnerEarnings.show(scene, data);
            });
            monthMenu.getItems().add(item);
        }

        btnPeriodDropdown.setOnAction(e -> {
            if (!monthMenu.isShowing()) {
                monthMenu.show(btnPeriodDropdown, Side.BOTTOM, 0, 4);
            } else {
                monthMenu.hide();
            }
        });

        Button btnExport = new Button("Export Data");
        btnExport.setPrefHeight(36);
        btnExport.setStyle(
                "-fx-background-color: " + ORANGE_GRADIENT + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 11px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-padding: 0 16 0 16;" +
                        "-fx-cursor: hand;");
        btnExport.setOnAction(e -> {
            btnExport.setText("✓ Exported (" + data.selectedPeriod + ")");
            btnExport.setStyle(
                    "-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-background-radius: 8; -fx-padding: 0 16 0 16;");
        });

        actions.getChildren().addAll(btnPeriodDropdown, btnExport);
        headerRow.setRight(actions);

        // 1. Top 3 Metrics Row
        HBox topMetrics = new HBox(16);
        VBox cardWeekly = createMetricCard("Weekly Earnings", "₹" + String.format("%,.2f", data.weeklyEarnings),
                data.weeklyGrowth, "💳", true);
        VBox cardMonthly = createMetricCard("Monthly Total", "₹" + String.format("%,.2f", data.monthlyTotal),
                data.monthlyGrowth, "💼", false);
        VBox cardDaily = createDailyAvgCard(data);

        topMetrics.getChildren().addAll(cardWeekly, cardMonthly, cardDaily);
        HBox.setHgrow(cardWeekly, Priority.ALWAYS);
        HBox.setHgrow(cardMonthly, Priority.ALWAYS);
        HBox.setHgrow(cardDaily, Priority.ALWAYS);

        // 2. Middle Spline Graph Area Card
        VBox middleChartBox = createOverviewChartCard(scene, data);

        // 3. Bottom Transaction History Card
        VBox bottomHistoryBox = createTransactionHistoryCard(data);

        main.getChildren().addAll(headerRow, topMetrics, middleChartBox, bottomHistoryBox);
        return main;
    }

    // =========================================================================
    // 3. TOP KPI CARDS
    // =========================================================================
    private static VBox createMetricCard(String label, String value, String growth, String icon,
                                         boolean isOrangeBadge) {
        VBox card = createCard();
        card.setPadding(new Insets(16));

        BorderPane top = new BorderPane();
        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size: 14px;");

        Label badge = new Label(growth);
        badge.setStyle(
                "-fx-font-size: 10px; -fx-font-weight: bold; " +
                        "-fx-text-fill: " + (isOrangeBadge ? "#c2410c;" : "#15803d;") +
                        "-fx-background-color: " + (isOrangeBadge ? "#ffedd5;" : "#dcfce7;") +
                        "-fx-background-radius: 12; -fx-padding: 2 6 2 6;");
        top.setLeft(iconLbl);
        top.setRight(badge);

        VBox bottom = new VBox(2);
        bottom.setPadding(new Insets(12, 0, 0, 0));
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        Label v = new Label(value);
        v.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        bottom.getChildren().addAll(l, v);

        card.getChildren().addAll(top, bottom);
        return card;
    }

    private static VBox createDailyAvgCard(EarningsData data) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: #1e293b;" +
                        "-fx-background-radius: 12;");

        BorderPane top = new BorderPane();
        VBox left = new VBox(2);
        Label title = new Label("Daily Average");
        title.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");
        Label val = new Label("₹" + String.format("%,.2f", data.dailyAverage));
        val.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
        left.getChildren().addAll(title, val);

        Label chartIcon = new Label("📊");
        chartIcon.setStyle("-fx-font-size: 16px; -fx-text-fill: " + ORANGE_PRIMARY + ";");
        top.setLeft(left);
        top.setRight(chartIcon);

        HBox stats = new HBox(16);
        stats.setPadding(new Insets(6, 0, 0, 0));
        stats.setStyle("-fx-border-color: #334155; -fx-border-width: 1 0 0 0;");

        VBox s1 = new VBox(1);
        Label s1L = new Label("AVG. DELIVERIES");
        s1L.setStyle("-fx-font-size: 8px; -fx-text-fill: #94a3b8; -fx-font-weight: bold;");
        Label s1V = new Label(data.avgDeliveriesPerDay + " / day");
        s1V.setStyle("-fx-font-size: 10px; -fx-text-fill: #e2e8f0; -fx-font-weight: bold;");
        s1.getChildren().addAll(s1L, s1V);

        VBox s2 = new VBox(1);
        Label s2L = new Label("TRIP AVERAGE");
        s2L.setStyle("-fx-font-size: 8px; -fx-text-fill: #94a3b8; -fx-font-weight: bold;");
        Label s2V = new Label("₹" + String.format("%.2f", data.avgPerOrder) + " / order");
        s2V.setStyle("-fx-font-size: 10px; -fx-text-fill: #e2e8f0; -fx-font-weight: bold;");
        s2.getChildren().addAll(s2L, s2V);

        stats.getChildren().addAll(s1, s2);
        card.getChildren().addAll(top, stats);
        return card;
    }

    // =========================================================================
    // 4. OVERVIEW AREA CHART
    // =========================================================================
    private static VBox createOverviewChartCard(Scene scene, EarningsData data) {
        VBox card = createCard();
        card.setPadding(new Insets(20));

        BorderPane header = new BorderPane();
        VBox left = new VBox(2);
        Label title = new Label("Earnings Overview (" + data.selectedPeriod + ")");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label sub = new Label("Revenue progression curve for the selected period");
        sub.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        left.getChildren().addAll(title, sub);

        HBox toggle = new HBox();
        toggle.setStyle("-fx-background-color: #f3f4f6; -fx-background-radius: 6; -fx-padding: 2;");

        Button btnDaily = new Button("Daily");
        Button btnWeekly = new Button("Weekly");

        if (!data.isWeeklyChart) {
            btnDaily.setStyle(
                    "-fx-background-color: white; -fx-text-fill: #111827; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 3 10 3 10; -fx-cursor: hand;");
            btnWeekly.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: #6b7280; -fx-font-size: 10px; -fx-padding: 3 10 3 10; -fx-cursor: hand;");
        } else {
            btnWeekly.setStyle(
                    "-fx-background-color: white; -fx-text-fill: #111827; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 5; -fx-padding: 3 10 3 10; -fx-cursor: hand;");
            btnDaily.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: #6b7280; -fx-font-size: 10px; -fx-padding: 3 10 3 10; -fx-cursor: hand;");
        }

        btnDaily.setOnAction(e -> {
            data.isWeeklyChart = false;
            PartnerEarnings.show(scene, data);
        });

        btnWeekly.setOnAction(e -> {
            data.isWeeklyChart = true;
            PartnerEarnings.show(scene, data);
        });

        toggle.getChildren().addAll(btnDaily, btnWeekly);
        header.setLeft(left);
        header.setRight(toggle);

        StackPane graphPane = new StackPane();
        graphPane.setPrefHeight(170);
        graphPane.setPadding(new Insets(10, 0, 0, 0));

        Path filledArea = new Path();
        Path line = new Path();

        if (!data.isWeeklyChart) {
            filledArea.getElements().addAll(
                    new MoveTo(0, 110),
                    new CubicCurveTo(120, 130, 240, 60, 380, 80),
                    new CubicCurveTo(480, 95, 600, 30, 780, 10),
                    new LineTo(780, 160),
                    new LineTo(0, 160));
            line.getElements().addAll(
                    new MoveTo(0, 110),
                    new CubicCurveTo(120, 130, 240, 60, 380, 80),
                    new CubicCurveTo(480, 95, 600, 30, 780, 10));
        } else {
            filledArea.getElements().addAll(
                    new MoveTo(0, 140),
                    new CubicCurveTo(150, 90, 300, 110, 450, 50),
                    new CubicCurveTo(550, 30, 680, 40, 780, 20),
                    new LineTo(780, 160),
                    new LineTo(0, 160));
            line.getElements().addAll(
                    new MoveTo(0, 140),
                    new CubicCurveTo(150, 90, 300, 110, 450, 50),
                    new CubicCurveTo(550, 30, 680, 40, 780, 20));
        }

        LinearGradient gradient = new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#fed7aa", 0.7)),
                new Stop(1, Color.web("#fed7aa", 0.05)));
        filledArea.setFill(gradient);
        filledArea.setStroke(Color.TRANSPARENT);

        line.setStroke(Color.web(ORANGE_PRIMARY));
        line.setStrokeWidth(2.5);
        line.setFill(Color.TRANSPARENT);

        graphPane.getChildren().addAll(filledArea, line);

        HBox dates = new HBox();
        dates.setPadding(new Insets(6, 10, 0, 10));

        String prefix = data.selectedPeriod.substring(0, 3);
        if (!data.isWeeklyChart) {
            dates.getChildren().addAll(
                    createAxisDate(prefix + " 1"), createAxisDate(prefix + " 8"),
                    createAxisDate(prefix + " 15"), createAxisDate(prefix + " 22"), createAxisDate(prefix + " 29"));
        } else {
            dates.getChildren().addAll(
                    createAxisDate("Week 1"), createAxisDate("Week 2"),
                    createAxisDate("Week 3"), createAxisDate("Week 4"));
        }
        dates.getChildren().forEach(n -> HBox.setHgrow(n, Priority.ALWAYS));

        card.getChildren().addAll(header, graphPane, dates);
        return card;
    }

    private static Label createAxisDate(String text) {
        Label l = new Label(text);
        l.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");
        l.setMaxWidth(Double.MAX_VALUE);
        l.setAlignment(Pos.CENTER);
        return l;
    }

    // =========================================================================
    // 5. TRANSACTION HISTORY TABLE
    // =========================================================================
    private static VBox createTransactionHistoryCard(EarningsData data) {
        VBox card = createCard();
        card.setPadding(new Insets(20));

        BorderPane header = new BorderPane();
        Label title = new Label("Transaction History (" + data.selectedPeriod + ")");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #111827;");

        Label countBadge = new Label(data.transactions.size() + " Records");
        countBadge.setStyle(
                "-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #6b7280; -fx-background-color: #f3f4f6; -fx-background-radius: 6; -fx-padding: 3 8 3 8;");

        header.setLeft(title);
        header.setRight(countBadge);

        // Table Header
        HBox tableHeader = new HBox();
        tableHeader.setPadding(new Insets(14, 10, 8, 10));
        tableHeader.setStyle("-fx-border-color: #f3f4f6; -fx-border-width: 0 0 1 0;");

        tableHeader.getChildren().addAll(
                createColHeader("ORDER ID", 120),
                createColHeader("DATE", 130),
                createColHeader("AMOUNT", 110),
                createColHeader("COMMISSION", 110),
                createColHeader("STATUS", 110),
                createColHeader("ACTIONS", 80));

        // Scrollable Rows Container
        VBox rowsContainer = new VBox();
        for (TransactionRecord tx : data.transactions) {
            rowsContainer.getChildren().add(createTransactionRow(tx));
        }

        ScrollPane rowsScroll = new ScrollPane(rowsContainer);
        rowsScroll.setFitToWidth(true);
        rowsScroll.setPannable(true);
        rowsScroll.setPrefHeight(230);
        rowsScroll.setMaxHeight(230);
        rowsScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        rowsScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        rowsScroll.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background: white;" +
                        "-fx-border-color: transparent;");

        card.getChildren().addAll(header, tableHeader, rowsScroll);
        return card;
    }

    private static HBox createTransactionRow(TransactionRecord tx) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(11, 10, 11, 10));
        row.setStyle("-fx-border-color: #f9fafb; -fx-border-width: 0 0 1 0;");

        Label id = new Label(tx.orderId);
        id.setPrefWidth(120);
        id.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");

        Label date = new Label(tx.date);
        date.setPrefWidth(130);
        date.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        Label amt = new Label("₹" + String.format("%.2f", tx.amount));
        amt.setPrefWidth(110);
        amt.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label comm = new Label("-₹" + String.format("%.2f", tx.commission));
        comm.setPrefWidth(110);
        comm.setStyle("-fx-font-size: 11px; -fx-text-fill: #ef4444;");

        Label status = new Label(tx.status);
        status.setPrefWidth(110);
        if ("Paid".equalsIgnoreCase(tx.status)) {
            status.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #15803d;");
        } else if ("Pending".equalsIgnoreCase(tx.status)) {
            status.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        } else {
            status.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #dc2626;");
        }

        Label action = new Label("•••");
        action.setPrefWidth(80);
        action.setStyle("-fx-font-size: 11px; -fx-text-fill: #9ca3af; -fx-cursor: hand;");

        row.getChildren().addAll(id, date, amt, comm, status, action);
        return row;
    }

    private static Label createColHeader(String text, double width) {
        Label l = new Label(text);
        l.setPrefWidth(width);
        l.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #9ca3af;");
        return l;
    }

    // =========================================================================
    // DYNAMIC AVATAR BUILDER
    // =========================================================================
    private static StackPane createAvatarNode(double radius) {
        StackPane avatarPane = new StackPane();
        avatarPane.setPrefSize(radius * 2, radius * 2);
        avatarPane.setMaxSize(radius * 2, radius * 2);

        Circle bg = new Circle(radius, Color.web("#fed7aa"));

        if (PartnerConstants.PROFILE_PHOTO_URL != null && !PartnerConstants.PROFILE_PHOTO_URL.trim().isEmpty()) {
            try {
                ImageView imgView = new ImageView(new Image(PartnerConstants.PROFILE_PHOTO_URL, true));
                imgView.setFitWidth(radius * 2);
                imgView.setFitHeight(radius * 2);
                imgView.setPreserveRatio(false);

                Circle clip = new Circle(radius, radius, radius);
                imgView.setClip(clip);

                avatarPane.getChildren().addAll(bg, imgView);
                return avatarPane;
            } catch (Exception ignored) {}
        }

        String initials = "P";
        if (PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.trim().isEmpty()) {
            String[] parts = PartnerConstants.FULL_NAME.trim().split("\\s+");
            if (parts.length >= 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                initials = ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
            } else if (!parts[0].isEmpty()) {
                initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
            }
        }

        Label initialLabel = new Label(initials);
        initialLabel.setStyle("-fx-font-size: " + (radius * 0.7) + "px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        avatarPane.getChildren().addAll(bg, initialLabel);

        return avatarPane;
    }

    private static VBox createCard() {
        VBox card = new VBox(12);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 1;");
        return card;
    }

    private static Button createNavButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPrefHeight(38);

        if (active) {
            btn.setStyle(
                    "-fx-background-color: " + ORANGE_PRIMARY + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 12px;" +
                            "-fx-background-radius: 8;" +
                            "-fx-padding: 0 14 0 14;" +
                            "-fx-cursor: hand;");
        } else {
            btn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: #6b7280;" +
                            "-fx-font-size: 12px;" +
                            "-fx-padding: 0 14 0 14;" +
                            "-fx-cursor: hand;");
            btn.setOnMouseEntered(e -> btn.setStyle(
                    "-fx-background-color: #fcece3; -fx-text-fill: #a94717; -fx-font-size: 12px; -fx-background-radius: 8; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
            btn.setOnMouseExited(e -> btn.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: #6b7280; -fx-font-size: 12px; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
        }
        return btn;
    }
}
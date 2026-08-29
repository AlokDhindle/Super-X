package com.kryox.view.Delivery;

import com.kryox.config.FirebaseConfig;
import com.kryox.model.Delivery.PartnerConstants;
import com.google.cloud.firestore.Firestore;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class PartnerAvailability {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String SIDEBAR_BG = "#ffffff";

    private static final double REGULAR_HOURLY_RATE = 180.00;
    private static final double PEAK_HOURLY_BONUS = 60.00;

    // =========================================================================
    // DYNAMIC FIRESTORE-READY AVAILABILITY DATA MODEL
    // =========================================================================
    public static class AvailabilityData {
        public String partnerName;
        public String partnerTier;
        public boolean isOnline = true;

        public boolean autoAcceptOrders = true;
        public String selectedZone = "Downtown & Deccan Gymkhana";

        public Map<String, Boolean> activeShifts = new HashMap<>();

        public int scheduledHours = 0;
        public int completedHours = 14;
        public double baseRate = 0.0;
        public double peakHoursBonus = 0.0;
        public double projectedTotal = 0.0;

        public AvailabilityData() {
            this.partnerName = PartnerConstants.FULL_NAME;
            this.partnerTier = PartnerConstants.PARTNER_TIER;

            activeShifts.put("Mon_Morning", true);
            activeShifts.put("Mon_Evening", true);
            activeShifts.put("Tue_Afternoon", true);
            activeShifts.put("Wed_Morning", true);
            activeShifts.put("Thu_Evening", true);
            activeShifts.put("Fri_Evening", true);
            activeShifts.put("Sat_Evening", true);

            recalculateMetrics();
        }

        public void recalculateMetrics() {
            int regularShiftCount = 0;
            int peakShiftCount = 0;

            for (Map.Entry<String, Boolean> entry : activeShifts.entrySet()) {
                if (Boolean.TRUE.equals(entry.getValue())) {
                    if (entry.getKey().endsWith("Evening")) {
                        peakShiftCount++;
                    } else {
                        regularShiftCount++;
                    }
                }
            }

            int regularHours = regularShiftCount * 4;
            int peakHours = peakShiftCount * 4;
            this.scheduledHours = regularHours + peakHours;

            this.baseRate = this.scheduledHours * REGULAR_HOURLY_RATE;
            this.peakHoursBonus = peakHours * PEAK_HOURLY_BONUS;
            this.projectedTotal = this.baseRate + this.peakHoursBonus;
        }

        public double getProgressPercentage() {
            if (scheduledHours == 0)
                return 0.0;
            return Math.min(1.0, (double) completedHours / scheduledHours);
        }
    }

    public static void show(Stage primaryStage) {
        show(primaryStage, new AvailabilityData());
    }

    public static void show(Stage primaryStage, AvailabilityData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(primaryStage, data));

        // 2. Left Sidebar Navigation
        root.setLeft(createSidebar(primaryStage, data));

        // 3. Main Content Area
        VBox mainContent = createMainContent(primaryStage, data);
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

        if (primaryStage.getScene() == null) {
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            primaryStage.getScene().setRoot(root);
        }

        primaryStage.setTitle("BuyNeX - Manage Availability");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // =========================================================================
    // TOP HEADER
    // =========================================================================
    private static BorderPane createTopHeader(Stage primaryStage, AvailabilityData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #f0edf2;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-padding: 0 35 0 30;");

        Text title = new Text("Available");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #a94717;");
        topBar.setLeft(new HBox(title));
        ((HBox) topBar.getLeft()).setAlignment(Pos.CENTER_LEFT);

        HBox rightControls = new HBox(16);
        rightControls.setAlignment(Pos.CENTER_RIGHT);

        HBox statusPill = new HBox(8);
        statusPill.setAlignment(Pos.CENTER);
        statusPill.setPadding(new Insets(4, 10, 4, 10));
        statusPill.setStyle(data.isOnline
                ? "-fx-background-color: #dcfce7; -fx-background-radius: 14;"
                : "-fx-background-color: #f1f3f7; -fx-background-radius: 14;");

        Label statusLbl = new Label(data.isOnline ? "ONLINE" : "OFFLINE");
        statusLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: "
                + (data.isOnline ? "#16a34a;" : "#7d8592;"));
        Circle toggleDot = new Circle(5, data.isOnline ? Color.web("#16a34a") : Color.web("#cbd0d8"));
        statusPill.getChildren().addAll(statusLbl, toggleDot);

        Label bellIcon = new Label("🔔");
        bellIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        bellIcon.setOnMouseClicked(e -> PartnerNotifications.show(primaryStage, "AVAILABILITY"));

        Label chatIcon = new Label("💬");
        chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        chatIcon.setOnMouseClicked(e -> PartnerChatSupport.show(primaryStage, "AVAILABILITY"));

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
        itemProfile.setOnAction(e -> PartnerSettings.show(primaryStage));

        MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
        itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemAvailability.setOnAction(e -> PartnerAvailability.show(primaryStage));

        MenuItem itemLogout = new MenuItem("↪   Logout");
        itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemLogout.setOnAction(e -> {
            PartnerConstants.clear();
            Deliverylogin.show(primaryStage);
        });

        userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

        userAvatarPane.setOnMouseClicked(e -> {
            if (!userMenu.isShowing()) {
                userMenu.show(userAvatarPane, javafx.geometry.Side.BOTTOM, -120, 8);
            } else {
                userMenu.hide();
            }
        });

        rightControls.getChildren().addAll(statusPill, bellIcon, chatIcon, userAvatarPane);
        topBar.setRight(rightControls);

        return topBar;
    }

    // =========================================================================
    // 1. LEFT SIDEBAR
    // =========================================================================
    private static VBox createSidebar(Stage primaryStage, AvailabilityData data) {
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

        Runnable openDashboardTask = () -> PartnerDashboard.show(primaryStage);
        Runnable openDeliveriesTask = () -> PartnerDeliveries.show(primaryStage);
        Runnable openNavigationTask = () -> PartnerNavigation.show(primaryStage);
        Runnable openEarningsTask = () -> PartnerEarnings.show(primaryStage);
        Runnable openAvailabilityTask = () -> PartnerAvailability.show(primaryStage, data);
        Runnable openSettingsTask = () -> PartnerSettings.show(primaryStage);
        Runnable logoutTask = () -> {
            PartnerConstants.clear();
            Deliverylogin.show(primaryStage);
        };

        Button btnDashboard = createNavButton("▤   Dashboard", false);
        btnDashboard.setOnAction(e -> openDashboardTask.run());

        Button btnDeliveries = createNavButton("📦   My Deliveries", false);
        btnDeliveries.setOnAction(e -> openDeliveriesTask.run());

        Button btnNavigation = createNavButton("🧭   Navigation", false);
        btnNavigation.setOnAction(e -> openNavigationTask.run());

        Button btnEarnings = createNavButton("💵   Earnings", false);
        btnEarnings.setOnAction(e -> openEarningsTask.run());

        Button btnAvailability = createNavButton("⏱   Availability", true);
        btnAvailability.setOnAction(e -> openAvailabilityTask.run());

        VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Dynamic Bottom Profile Card
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

        profileCard.setOnMouseClicked(e -> PartnerProfile.show(primaryStage));

        Button btnSettings = createNavButton("⚙   Settings", false);
        btnSettings.setOnAction(e -> openSettingsTask.run());

        Button btnLogout = new Button("↪   Logout");
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setAlignment(Pos.CENTER_LEFT);
        btnLogout.setPrefHeight(34);
        btnLogout.setStyle(
                "-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
        btnLogout.setOnAction(e -> logoutTask.run());

        VBox bottomNav = new VBox(4, profileCard, btnSettings, btnLogout);
        sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
        return sidebar;
    }

    // =========================================================================
    // 2. MAIN CONTENT
    // =========================================================================
    private static VBox createMainContent(Stage primaryStage, AvailabilityData data) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(24, 35, 60, 35));
        content.setFillWidth(true);

        BorderPane headerRow = new BorderPane();

        VBox titleBox = new VBox(4);
        Text title = new Text("Manage Availability");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #111827;");
        Text subTitle = new Text(
                "Tap shifts below to adjust your active hours. Earnings & summaries update automatically.");
        subTitle.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);
        headerRow.setLeft(titleBox);

        Button btnSave = new Button("Save Schedule");
        btnSave.setPrefHeight(36);
        btnSave.setPadding(new Insets(0, 18, 0, 18));
        btnSave.setStyle(
                "-fx-background-color: #93380b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 12px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;");

        // Firestore Sync Handler
        btnSave.setOnAction(e -> {
            btnSave.setText("Saving...");
            btnSave.setDisable(true);

            new Thread(() -> {
                try {
                    Firestore db = FirebaseConfig.getFireStore();
                    if (PartnerConstants.UID != null && !PartnerConstants.UID.isEmpty()) {
                        Map<String, Object> scheduleMap = new HashMap<>();
                        scheduleMap.put("autoAcceptOrders", data.autoAcceptOrders);
                        scheduleMap.put("operatingZone", data.selectedZone);
                        scheduleMap.put("scheduledHours", data.scheduledHours);
                        scheduleMap.put("projectedEarnings", data.projectedTotal);
                        scheduleMap.put("activeShifts", data.activeShifts);

                        db.collection("delivery_partners")
                                .document(PartnerConstants.UID)
                                .update("availability", scheduleMap)
                                .get();
                    }

                    Platform.runLater(() -> {
                        btnSave.setText("✓ Schedule Synced!");
                        btnSave.setStyle(
                                "-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 8;");
                        btnSave.setDisable(false);
                    });
                } catch (Exception ex) {
                    Platform.runLater(() -> {
                        btnSave.setText("Save Failed");
                        btnSave.setStyle(
                                "-fx-background-color: #dc2626; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 8;");
                        btnSave.setDisable(false);
                    });
                    ex.printStackTrace();
                }
            }).start();
        });

        headerRow.setRight(btnSave);
        BorderPane.setAlignment(btnSave, Pos.CENTER_RIGHT);

        HBox bodyColumns = new HBox(22);
        bodyColumns.setFillHeight(true);

        // Left Column: Preferences + Interactive 7-Day Shift Grid
        VBox leftColumn = new VBox(20);
        leftColumn.getChildren().addAll(
                createPreferencesCard(data),
                createInteractiveWeeklyGrid(primaryStage, data));
        HBox.setHgrow(leftColumn, Priority.ALWAYS);

        // Right Column: Dynamic Projected Earnings + Dynamic Week Summary + View Past Weeks Button
        VBox rightColumn = new VBox(18);
        rightColumn.setPrefWidth(290);
        rightColumn.setMinWidth(290);
        rightColumn.setMaxWidth(290);
        rightColumn.getChildren().addAll(
                createProjectedEarningsCard(data),
                createWeekSummaryCard(data),
                createPastWeeksButton(primaryStage));
        HBox.setHgrow(rightColumn, Priority.NEVER);

        bodyColumns.getChildren().addAll(leftColumn, rightColumn);
        content.getChildren().addAll(headerRow, bodyColumns);
        return content;
    }

    private static VBox createPreferencesCard(AvailabilityData data) {
        VBox card = createCard();
        card.setPadding(new Insets(16, 20, 16, 20));

        BorderPane row1 = new BorderPane();
        VBox r1Text = new VBox(2);
        Label autoTitle = new Label("Auto-Accept Orders");
        autoTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label autoSub = new Label("Automatically accept incoming delivery requests during active shifts");
        autoSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        r1Text.getChildren().addAll(autoTitle, autoSub);
        row1.setLeft(r1Text);

        StackPane toggleTrack = new StackPane();
        Rectangle track = new Rectangle(40, 22);
        track.setArcWidth(22);
        track.setArcHeight(22);
        track.setFill(data.autoAcceptOrders ? Color.web(ORANGE_PRIMARY) : Color.web("#d1d5db"));

        Circle thumb = new Circle(8, Color.WHITE);
        StackPane.setAlignment(thumb, data.autoAcceptOrders ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        StackPane.setMargin(thumb, new Insets(0, 3, 0, 3));
        toggleTrack.getChildren().addAll(track, thumb);
        toggleTrack.setStyle("-fx-cursor: hand;");

        toggleTrack.setOnMouseClicked(e -> {
            data.autoAcceptOrders = !data.autoAcceptOrders;
            track.setFill(data.autoAcceptOrders ? Color.web(ORANGE_PRIMARY) : Color.web("#d1d5db"));
            StackPane.setAlignment(thumb, data.autoAcceptOrders ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        });

        row1.setRight(toggleTrack);
        BorderPane.setAlignment(toggleTrack, Pos.CENTER_RIGHT);

        Region sep = new Region();
        sep.setPrefHeight(1);
        sep.setStyle("-fx-background-color: " + BORDER_COLOR + ";");

        HBox row2 = new HBox(16);
        row2.setAlignment(Pos.CENTER_LEFT);

        VBox r2Text = new VBox(2);
        Label prefTitle = new Label("Primary Operating Zone");
        prefTitle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        Label prefSub = new Label("Focus delivery alerts in selected sector");
        prefSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #9ca3af;");
        r2Text.getChildren().addAll(prefTitle, prefSub);

        ComboBox<String> zoneDropdown = new ComboBox<>();
        zoneDropdown.getItems().addAll(
                "Downtown & Deccan Gymkhana",
                "Kothrud & Karve Road",
                "Hinjawadi IT Corridor",
                "Viman Nagar & Kharadi",
                "Hadapsar / Magarpatta Hub");
        zoneDropdown.setValue(data.selectedZone);
        zoneDropdown.setPrefHeight(32);
        zoneDropdown.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #374151;" +
                        "-fx-cursor: hand;");
        zoneDropdown.setOnAction(e -> data.selectedZone = zoneDropdown.getValue());

        Region r2Spacer = new Region();
        HBox.setHgrow(r2Spacer, Priority.ALWAYS);

        row2.getChildren().addAll(r2Text, r2Spacer, zoneDropdown);

        card.getChildren().addAll(row1, sep, row2);
        return card;
    }

    // =========================================================================
    // 3. INTERACTIVE 7-DAY SHIFT SCHEDULER
    // =========================================================================
    private static VBox createInteractiveWeeklyGrid(Stage primaryStage, AvailabilityData data) {
        VBox card = createCard();
        card.setPadding(new Insets(0));

        VBox headerWrapper = new VBox();
        Region topOrangeLine = new Region();
        topOrangeLine.setPrefHeight(3);
        topOrangeLine.setStyle("-fx-background-color: " + ORANGE_PRIMARY + "; -fx-background-radius: 12 12 0 0;");

        BorderPane header = new BorderPane();
        header.setPadding(new Insets(16, 20, 14, 20));
        header.setStyle("-fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 0 1 0;");

        HBox titleBox = new HBox(8);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        Label calIcon = new Label("📅");
        calIcon.setStyle("-fx-font-size: 14px;");
        Label title = new Label("Weekly Shift Schedule");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        titleBox.getChildren().addAll(calIcon, title);
        header.setLeft(titleBox);

        Label legend = new Label("● Green = Active Shift (4h)   |   ⚡ Orange = Peak Bonus (+₹60/h)");
        legend.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280; -fx-font-weight: bold;");
        header.setRight(legend);
        BorderPane.setAlignment(legend, Pos.CENTER_RIGHT);

        headerWrapper.getChildren().addAll(topOrangeLine, header);

        // Schedule Grid
        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(10);
        grid.setPadding(new Insets(18, 20, 20, 20));

        String[] days = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        String[] shifts = { "Morning", "Afternoon", "Evening" };
        String[] shiftLabels = { "Morning\n(08:00 - 12:00)", "Afternoon\n(12:00 - 16:00)",
                "Peak Evening ⚡\n(17:00 - 21:00)" };

        for (int col = 0; col < days.length; col++) {
            Label dayLbl = new Label(days[col]);
            dayLbl.setMaxWidth(Double.MAX_VALUE);
            dayLbl.setAlignment(Pos.CENTER);
            dayLbl.setStyle(
                    "-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #374151; -fx-padding: 0 0 4 0;");
            grid.add(dayLbl, col + 1, 0);
        }

        for (int row = 0; row < shifts.length; row++) {
            Label shiftLbl = new Label(shiftLabels[row]);
            shiftLbl.setWrapText(true);
            shiftLbl.setPrefWidth(110);
            shiftLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: "
                    + (row == 2 ? "#b45309;" : "#6b7280;"));
            grid.add(shiftLbl, 0, row + 1);

            for (int col = 0; col < days.length; col++) {
                String shiftKey = days[col] + "_" + shifts[row];
                boolean isActive = Boolean.TRUE.equals(data.activeShifts.get(shiftKey));
                boolean isPeak = (row == 2);

                Button slotBtn = createShiftSlotButton(shiftKey, isActive, isPeak);
                slotBtn.setOnAction(e -> {
                    boolean newState = !Boolean.TRUE.equals(data.activeShifts.get(shiftKey));
                    data.activeShifts.put(shiftKey, newState);
                    data.recalculateMetrics();
                    PartnerAvailability.show(primaryStage, data);
                });

                grid.add(slotBtn, col + 1, row + 1);
            }
        }

        grid.getChildren().forEach(node -> {
            if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) > 0) {
                GridPane.setHgrow(node, Priority.ALWAYS);
            }
        });

        card.getChildren().addAll(headerWrapper, grid);
        return card;
    }

    private static Button createShiftSlotButton(String shiftKey, boolean isActive, boolean isPeak) {
        Button btn = new Button(isActive ? (isPeak ? "⚡ ACTIVE" : "✓ ACTIVE") : "OFF");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(42);

        if (isActive) {
            if (isPeak) {
                btn.setStyle(
                        "-fx-background-color: #ffedd5;" +
                                "-fx-border-color: " + ORANGE_PRIMARY + ";" +
                                "-fx-border-radius: 6;" +
                                "-fx-background-radius: 6;" +
                                "-fx-text-fill: #9a3412;" +
                                "-fx-font-size: 10px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-cursor: hand;");
            } else {
                btn.setStyle(
                        "-fx-background-color: #dcfce7;" +
                                "-fx-border-color: #86efac;" +
                                "-fx-border-radius: 6;" +
                                "-fx-background-radius: 6;" +
                                "-fx-text-fill: #15803d;" +
                                "-fx-font-size: 10px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-cursor: hand;");
            }
        } else {
            btn.setStyle(
                    "-fx-background-color: #fafafc;" +
                            "-fx-border-color: #e5e7eb;" +
                            "-fx-border-radius: 6;" +
                            "-fx-background-radius: 6;" +
                            "-fx-text-fill: #9ca3af;" +
                            "-fx-font-size: 10px;" +
                            "-fx-cursor: hand;");
        }

        return btn;
    }

    // =========================================================================
    // 4. DYNAMIC PROJECTED EARNINGS CARD
    // =========================================================================
    private static VBox createProjectedEarningsCard(AvailabilityData data) {
        VBox card = createCard();
        card.setPadding(new Insets(18));

        BorderPane header = new BorderPane();
        Label title = new Label("Projected Earnings");
        title.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b5563; -fx-font-weight: bold;");
        Label trendIcon = new Label("📈");
        trendIcon.setStyle("-fx-font-size: 14px;");
        header.setLeft(title);
        header.setRight(trendIcon);

        HBox valRow = new HBox(1);
        valRow.setAlignment(Pos.BASELINE_LEFT);
        Label dollar = new Label("₹" + String.format("%,.0f", data.projectedTotal));
        dollar.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label cents = new Label(".00");
        cents.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        valRow.getChildren().addAll(dollar, cents);

        Label subtitle = new Label("Based on " + data.scheduledHours + " active hours scheduled this week.");
        subtitle.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");

        Region sep = new Region();
        sep.setPrefHeight(1);
        sep.setStyle("-fx-background-color: " + BORDER_COLOR + ";");
        VBox.setMargin(sep, new Insets(6, 0, 6, 0));

        BorderPane peakRow = new BorderPane();
        Label peakLbl = new Label("Peak Surge Bonus (⚡)");
        peakLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        Label peakVal = new Label("+₹" + String.format("%.2f", data.peakHoursBonus));
        peakVal.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: " + ORANGE_PRIMARY + ";");
        peakRow.setLeft(peakLbl);
        peakRow.setRight(peakVal);

        BorderPane baseRow = new BorderPane();
        Label baseLbl = new Label("Base Rate (₹180/hr)");
        baseLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        Label baseVal = new Label("₹" + String.format("%.2f", data.baseRate));
        baseVal.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        baseRow.setLeft(baseLbl);
        baseRow.setRight(baseVal);

        card.getChildren().addAll(header, valRow, subtitle, sep, peakRow, baseRow);
        return card;
    }

    // =========================================================================
    // 5. DYNAMIC WEEK SUMMARY CARD
    // =========================================================================
    private static VBox createWeekSummaryCard(AvailabilityData data) {
        VBox card = createCard();
        card.setPadding(new Insets(18));

        Label title = new Label("Week Summary");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        BorderPane schedRow = new BorderPane();
        schedRow.setPadding(new Insets(10, 14, 10, 14));
        schedRow.setStyle("-fx-background-color: #f9fafb; -fx-background-radius: 8;");

        HBox schedLeft = new HBox(8);
        schedLeft.setAlignment(Pos.CENTER_LEFT);
        Label clock = new Label("🕒");
        clock.setStyle("-fx-font-size: 11px; -fx-text-fill: #9a3412;");
        Label schedLbl = new Label("Scheduled");
        schedLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        schedLeft.getChildren().addAll(clock, schedLbl);

        Label schedVal = new Label(data.scheduledHours + "h 00m");
        schedVal.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        schedRow.setLeft(schedLeft);
        schedRow.setRight(schedVal);

        BorderPane compRow = new BorderPane();
        compRow.setPadding(new Insets(10, 14, 10, 14));
        compRow.setStyle("-fx-background-color: #f9fafb; -fx-background-radius: 8;");

        HBox compLeft = new HBox(8);
        compLeft.setAlignment(Pos.CENTER_LEFT);
        Label check = new Label("✓");
        check.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #16a34a;");
        Label compLbl = new Label("Completed");
        compLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        compLeft.getChildren().addAll(check, compLbl);

        Label compVal = new Label(data.completedHours + "h 30m");
        compVal.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        compRow.setLeft(compLeft);
        compRow.setRight(compVal);

        BorderPane progMeta = new BorderPane();
        Label progLbl = new Label("Weekly Completion Target");
        progLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        Label progVal = new Label((int) (data.getProgressPercentage() * 100) + "%");
        progVal.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #9a3412;");
        progMeta.setLeft(progLbl);
        progMeta.setRight(progVal);

        ProgressBar progressBar = new ProgressBar(data.getProgressPercentage());
        progressBar.setMaxWidth(Double.MAX_VALUE);
        progressBar.setPrefHeight(6);
        progressBar.setStyle("-fx-accent: #93380b;");

        VBox progBox = new VBox(6, progMeta, progressBar);
        progBox.setPadding(new Insets(4, 0, 0, 0));

        card.getChildren().addAll(title, schedRow, compRow, progBox);
        return card;
    }

    // =========================================================================
    // 6. DYNAMIC "VIEW PAST WEEKS" ACTION BUTTON
    // =========================================================================
    private static Button createPastWeeksButton(Stage primaryStage) {
        Button btn = new Button("↺   View Past Weeks");
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(40);
        btn.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #d1d5db;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: #374151;" +
                        "-fx-font-size: 12px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;");
        btn.setOnAction(e -> PastWeeksHistory.show(primaryStage));
        return btn;
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
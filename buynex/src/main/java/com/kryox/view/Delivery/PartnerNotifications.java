package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PartnerNotifications {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY NOTIFICATION DATA MODEL
    // =========================================================================
    public static class NotificationData {
        public String partnerName = "Alex Walker";
        public String activeFilter = "ALL"; // "ALL", "UNREAD", "PAYOUTS", "ORDERS"
        public List<NotificationItem> notifications = new ArrayList<>();

        public NotificationData() {
            // Default dynamic sample feed
            notifications.add(new NotificationItem("N-101", "ORDER", "New Delivery Request #BN-4920", "Pickup at Whole Foods Market (1.2 km). Estimated payout: ₹145.50.", "2 mins ago", false));
            notifications.add(new NotificationItem("N-102", "PAYOUT", "Weekly Payout Credited", "₹4,892.20 transferred successfully to HDFC Bank (•••• 8219).", "2 hours ago", false));
            notifications.add(new NotificationItem("N-103", "ALERT", "High Surge Active in Downtown", "Earn 1.5x bonus on orders delivered between 6:00 PM and 9:00 PM.", "5 hours ago", true));
            notifications.add(new NotificationItem("N-104", "SYSTEM", "Document Verified", "Your Driving License (MH-142011) has been approved by compliance.", "1 day ago", true));
        }
    }

    public static class NotificationItem {
        public String id;
        public String type; // "ORDER", "PAYOUT", "ALERT", "SYSTEM"
        public String title;
        public String description;
        public String timestamp;
        public boolean isRead;

        public NotificationItem(String id, String type, String title, String description, String timestamp, boolean isRead) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.description = description;
            this.timestamp = timestamp;
            this.isRead = isRead;
        }
    }

    public static void show(Scene scene) {
        show(scene, "DASHBOARD", new NotificationData());
    }

    public static void show(Scene scene, String returnScreen) {
        show(scene, returnScreen, new NotificationData());
    }

    public static void show(Scene scene, String returnScreen, NotificationData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(scene, returnScreen, data));

        // 2. Center Notifications Content
        VBox mainContent = createMainContent(scene, returnScreen, data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        if (scene != null) {
            scene.setRoot(root);
        }
    }

    // =========================================================================
    // TOP HEADER
    // =========================================================================
    private static BorderPane createTopHeader(Scene scene, String returnScreen, NotificationData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 35 0 25;"
        );

        // Dynamic Back Button
        String backLabel = "←  Back to " + formatScreenName(returnScreen);
        Button btnBack = new Button(backLabel);
        btnBack.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #374151;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;"
        );

        btnBack.setOnAction(e -> navigateBack(scene, returnScreen));

        Text logo = new Text("Notifications Center");
        logo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, logo);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        // Header Actions: Mark All Read
        Button btnMarkAllRead = new Button("Mark all as read");
        btnMarkAllRead.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + ORANGE_PRIMARY + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
        btnMarkAllRead.setOnAction(e -> {
            for (NotificationItem item : data.notifications) {
                item.isRead = true;
            }
            show(scene, returnScreen, data);
        });

        topBar.setRight(btnMarkAllRead);
        BorderPane.setAlignment(btnMarkAllRead, Pos.CENTER_RIGHT);

        return topBar;
    }

    // =========================================================================
    // MAIN NOTIFICATIONS FEED
    // =========================================================================
    private static VBox createMainContent(Scene scene, String returnScreen, NotificationData data) {
        VBox content = new VBox(20);
        content.setPadding(new Insets(24, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox container = new VBox(16);
        container.setMaxWidth(780);

        // Filter Pills Row
        HBox filterBar = new HBox(10);
        filterBar.setAlignment(Pos.CENTER_LEFT);

        filterBar.getChildren().addAll(
                createFilterPill(scene, returnScreen, data, "ALL", "All Notifications"),
                createFilterPill(scene, returnScreen, data, "UNREAD", "Unread"),
                createFilterPill(scene, returnScreen, data, "ORDERS", "Orders"),
                createFilterPill(scene, returnScreen, data, "PAYOUTS", "Payouts")
        );

        // Dynamic Notifications Card Container
        VBox listContainer = new VBox(10);

        for (NotificationItem item : data.notifications) {
            // Apply filter logic
            if ("UNREAD".equals(data.activeFilter) && item.isRead) continue;
            if ("ORDERS".equals(data.activeFilter) && !"ORDER".equals(item.type)) continue;
            if ("PAYOUTS".equals(data.activeFilter) && !"PAYOUT".equals(item.type)) continue;

            listContainer.getChildren().add(createNotificationCard(scene, item));
        }

        if (listContainer.getChildren().isEmpty()) {
            VBox emptyBox = new VBox(8);
            emptyBox.setAlignment(Pos.CENTER);
            emptyBox.setPadding(new Insets(60));
            Label emptyIcon = new Label("🔔");
            emptyIcon.setStyle("-fx-font-size: 32px; -fx-text-fill: #9ca3af;");
            Label emptyText = new Label("No notifications in this category");
            emptyText.setStyle("-fx-font-size: 13px; -fx-text-fill: #6b7280;");
            emptyBox.getChildren().addAll(emptyIcon, emptyText);
            listContainer.getChildren().add(emptyBox);
        }

        container.getChildren().addAll(filterBar, listContainer);
        content.getChildren().add(container);
        return content;
    }

    private static Button createFilterPill(Scene scene, String returnScreen, NotificationData data, String filterKey, String title) {
        boolean active = filterKey.equalsIgnoreCase(data.activeFilter);
        Button pill = new Button(title);
        pill.setPrefHeight(32);
        pill.setStyle(
                active
                        ? "-fx-background-color: #93380b; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 16; -fx-padding: 0 16 0 16;"
                        : "-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-text-fill: #4b5563; -fx-font-size: 11px; -fx-background-radius: 16; -fx-border-radius: 16; -fx-padding: 0 16 0 16; -fx-cursor: hand;"
        );

        pill.setOnAction(e -> {
            data.activeFilter = filterKey;
            show(scene, returnScreen, data);
        });

        return pill;
    }

    private static HBox createNotificationCard(Scene scene, NotificationItem item) {
        HBox card = new HBox(14);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(14, 18, 14, 18));
        card.setStyle(
                "-fx-background-color: " + (item.isRead ? "white;" : "#fffbf8;") +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 10;" +
                "-fx-border-color: " + (item.isRead ? BORDER_COLOR : "#fed7aa") + ";" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;"
        );

        // Dynamic Icon Box based on notification type
        StackPane iconBox = new StackPane();
        Circle iconBg = new Circle(18);
        Label iconLbl = new Label();
        iconLbl.setStyle("-fx-font-size: 14px;");

        if ("ORDER".equals(item.type)) {
            iconBg.setFill(Color.web("#ffedd5"));
            iconLbl.setText("📦");
        } else if ("PAYOUT".equals(item.type)) {
            iconBg.setFill(Color.web("#dcfce7"));
            iconLbl.setText("💵");
        } else if ("ALERT".equals(item.type)) {
            iconBg.setFill(Color.web("#fee2e2"));
            iconLbl.setText("⚡");
        } else {
            iconBg.setFill(Color.web("#f3f4f6"));
            iconLbl.setText("ⓘ");
        }

        iconBox.getChildren().addAll(iconBg, iconLbl);

        // Content
        VBox textGroup = new VBox(3);
        Label titleLbl = new Label(item.title);
        titleLbl.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label descLbl = new Label(item.description);
        descLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        descLbl.setWrapText(true);

        Label timeLbl = new Label(item.timestamp);
        timeLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        textGroup.getChildren().addAll(titleLbl, descLbl, timeLbl);
        HBox.setHgrow(textGroup, Priority.ALWAYS);

        // Unread Indicator Dot
        if (!item.isRead) {
            Circle unreadDot = new Circle(4, Color.web(ORANGE_PRIMARY));
            card.getChildren().addAll(iconBox, textGroup, unreadDot);
        } else {
            card.getChildren().addAll(iconBox, textGroup);
        }

        // Click to read/navigate
        card.setOnMouseClicked(e -> {
            item.isRead = true;
            if ("ORDER".equals(item.type)) {
                PartnerDeliveries.show(scene);
            } else if ("PAYOUT".equals(item.type)) {
                PartnerEarnings.show(scene);
            }
        });

        return card;
    }

    private static String formatScreenName(String key) {
        if ("DELIVERIES".equalsIgnoreCase(key)) return "My Deliveries";
        if ("NAVIGATION".equalsIgnoreCase(key)) return "Navigation";
        if ("EARNINGS".equalsIgnoreCase(key)) return "Earnings";
        if ("AVAILABILITY".equalsIgnoreCase(key)) return "Availability";
        if ("SETTINGS".equalsIgnoreCase(key)) return "Settings";
        return "Dashboard";
    }

    private static void navigateBack(Scene scene, String returnScreen) {
        if ("DELIVERIES".equalsIgnoreCase(returnScreen)) PartnerDeliveries.show(scene);
        else if ("NAVIGATION".equalsIgnoreCase(returnScreen)) PartnerNavigation.show(scene);
        else if ("EARNINGS".equalsIgnoreCase(returnScreen)) PartnerEarnings.show(scene);
        else if ("AVAILABILITY".equalsIgnoreCase(returnScreen)) PartnerAvailability.show(scene);
        else if ("SETTINGS".equalsIgnoreCase(returnScreen)) PartnerSettings.show(scene);
        else PartnerDashboard.show(scene);
    }
}
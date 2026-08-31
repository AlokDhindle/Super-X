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
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY NOTIFICATION DATA MODEL
    // =========================================================================
    public static class NotificationData {
        public String partnerName = "Alex Walker";
        public String activeFilter = "ALL";
        public List<NotificationItem> notifications = new ArrayList<>();

        public NotificationData() {
            notifications.add(new NotificationItem("N-101", "ORDER", "New Delivery Request #BN-4920", "Pickup at Whole Foods Market (1.2 km). Estimated payout: ₹145.50.", "2 mins ago", false));
            notifications.add(new NotificationItem("N-102", "PAYOUT", "Weekly Payout Credited", "₹4,892.20 transferred successfully to HDFC Bank (•••• 8219).", "2 hours ago", false));
            notifications.add(new NotificationItem("N-103", "ALERT", "High Surge Active in Downtown", "Earn 1.5x bonus on orders delivered between 6:00 PM and 9:00 PM.", "5 hours ago", true));
            notifications.add(new NotificationItem("N-104", "SYSTEM", "Document Verified", "Your Driving License has been approved by compliance.", "1 day ago", true));
        }
    }

    public static class NotificationItem {
        public String id;
        public String type;
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

    // =========================================================================
    // STATIC SCENE FACTORY METHODS
    // =========================================================================
    public static Scene partnerNotificationsScene() {
        return partnerNotificationsScene("DASHBOARD", new NotificationData());
    }

    public static Scene partnerNotificationsScene(String returnScreen) {
        return partnerNotificationsScene(returnScreen, new NotificationData());
    }

    public static Scene partnerNotificationsScene(String returnScreen, NotificationData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        root.setTop(createTopHeader(returnScreen, data));

        VBox mainContent = createMainContent(returnScreen, data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static BorderPane createTopHeader(String returnScreen, NotificationData data) {
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

        btnBack.setOnAction(e -> navigateBack(returnScreen));

        Text logo = new Text("Notifications Center");
        logo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, logo);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

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
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerNotificationsScene(returnScreen, data));
            }
        });

        topBar.setRight(btnMarkAllRead);
        BorderPane.setAlignment(btnMarkAllRead, Pos.CENTER_RIGHT);

        return topBar;
    }

    private static VBox createMainContent(String returnScreen, NotificationData data) {
        VBox content = new VBox(20);
        content.setPadding(new Insets(24, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox container = new VBox(16);
        container.setMaxWidth(780);

        HBox filterBar = new HBox(10);
        filterBar.setAlignment(Pos.CENTER_LEFT);

        filterBar.getChildren().addAll(
                createFilterPill(returnScreen, data, "ALL", "All Notifications"),
                createFilterPill(returnScreen, data, "UNREAD", "Unread"),
                createFilterPill(returnScreen, data, "ORDERS", "Orders"),
                createFilterPill(returnScreen, data, "PAYOUTS", "Payouts")
        );

        VBox listContainer = new VBox(10);

        for (NotificationItem item : data.notifications) {
            if ("UNREAD".equals(data.activeFilter) && item.isRead) continue;
            if ("ORDERS".equals(data.activeFilter) && !"ORDER".equals(item.type)) continue;
            if ("PAYOUTS".equals(data.activeFilter) && !"PAYOUT".equals(item.type)) continue;

            listContainer.getChildren().add(createNotificationCard(item));
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

    private static Button createFilterPill(String returnScreen, NotificationData data, String filterKey, String title) {
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
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerNotificationsScene(returnScreen, data));
            }
        });

        return pill;
    }

    private static HBox createNotificationCard(NotificationItem item) {
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

        if (!item.isRead) {
            Circle unreadDot = new Circle(4, Color.web(ORANGE_PRIMARY));
            card.getChildren().addAll(iconBox, textGroup, unreadDot);
        } else {
            card.getChildren().addAll(iconBox, textGroup);
        }

        card.setOnMouseClicked(e -> {
            item.isRead = true;
            if (Homepage.HomepageStage != null) {
                if ("ORDER".equals(item.type)) {
                    Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
                } else if ("PAYOUT".equals(item.type)) {
                    Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
                }
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

    private static void navigateBack(String returnScreen) {
        if (Homepage.HomepageStage == null) return;
        if ("DELIVERIES".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
        else if ("NAVIGATION".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
        else if ("EARNINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
        else if ("AVAILABILITY".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
        else if ("SETTINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
        else Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
    }
}
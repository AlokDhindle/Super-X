package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class NearbyDeliveries {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY NEARBY DELIVERIES DATA MODEL
    // =========================================================================
    public static class NearbyData {
        public String partnerName = "Alex Walker";
        public String currentZone = "Downtown & Deccan Gymkhana";
        public double radiusKm = 2.5;
        public int totalNearbyFound = 4;
        public List<NearbyOrderItem> orders = new ArrayList<>();

        public NearbyData() {
            // Default sample orders (populated from Firestore geo-queries)
            orders.add(new NearbyOrderItem("BN-7712", "0.4 km away", "Fresh Organic Bakery", "Bakery & Confectionery", "12 Baker St, Deccan", "Rahul Deshmukh", "Flat 302, Green Park Apts", 85.00, 3, "2 items (Breads, Croissants)", "12 mins"));
            orders.add(new NearbyOrderItem("BN-4920", "0.9 km away", "Nature's Basket Grocery", "Grocery & Essentials", "4920 Central Ave, Plaza", "Sarah Jenkins", "102 Highland Terrace, Apt 4C", 145.50, 8, "Grocery Haul (Veggies, Milk)", "15 mins"));
            orders.add(new NearbyOrderItem("BN-6031", "1.4 km away", "Cafe Goodluck", "Food & Beverages", "Fergusson College Rd", "Aman Sharma", "B-14 Symbiosis Hostel", 95.00, 4, "Bun Maska & Irani Chai", "18 mins"));
            orders.add(new NearbyOrderItem("BN-5102", "1.9 km away", "Digital Horizon Tech", "Electronics & Accessories", "88 Tech Plaza, FC Road", "Mark Robertson", "704 Skyline Residency", 210.00, 1, "Fast Charger & Cable", "22 mins"));
        }
    }

    public static class NearbyOrderItem {
        public String id;
        public String distanceText;
        public String storeName;
        public String storeCategory;
        public String storeAddress;
        public String customerName;
        public String customerAddress;
        public double payout; // INR ₹
        public int itemCount;
        public String itemsSummary;
        public String estDuration;
        public boolean isAccepted = false;

        public NearbyOrderItem(String id, String distanceText, String storeName, String storeCategory, String storeAddress,
                               String customerName, String customerAddress, double payout, int itemCount, String itemsSummary, String estDuration) {
            this.id = id;
            this.distanceText = distanceText;
            this.storeName = storeName;
            this.storeCategory = storeCategory;
            this.storeAddress = storeAddress;
            this.customerName = customerName;
            this.customerAddress = customerAddress;
            this.payout = payout;
            this.itemCount = itemCount;
            this.itemsSummary = itemsSummary;
            this.estDuration = estDuration;
        }
    }

    public static void show(Scene scene) {
        show(scene, new NearbyData());
    }

    public static void show(Scene scene, NearbyData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(scene, data));

        // 2. Center Content
        VBox mainContent = createMainContent(scene, data);
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
    private static BorderPane createTopHeader(Scene scene, NearbyData data) {
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

        Button btnBack = new Button("←  Back to Queue");
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
        btnBack.setOnAction(e -> PartnerDeliveries.show(scene));

        Text title = new Text("Nearby Delivery Requests");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        HBox locPill = new HBox(6);
        locPill.setAlignment(Pos.CENTER_RIGHT);
        Circle green = new Circle(4, Color.web("#16a34a"));
        Label locText = new Label("📍 Current Zone: " + data.currentZone);
        locText.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        locPill.getChildren().addAll(green, locText);
        topBar.setRight(locPill);

        return topBar;
    }

    // =========================================================================
    // MAIN CONTENT VIEW
    // =========================================================================
    private static VBox createMainContent(Scene scene, NearbyData data) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(24, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox wrapper = new VBox(20);
        wrapper.setMaxWidth(880);

        // Proximity Filter Bar Card
        BorderPane filterCard = new BorderPane();
        filterCard.setPadding(new Insets(16, 20, 16, 20));
        filterCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);"
        );

        VBox filterLeft = new VBox(2);
        Text radTitle = new Text("Radius Search: " + String.format("%.1f", data.radiusKm) + " km");
        radTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #111827;");
        Label radSub = new Label("Showing " + data.orders.size() + " active pickups within your immediate vicinity.");
        radSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        filterLeft.getChildren().addAll(radTitle, radSub);
        filterCard.setLeft(filterLeft);

        HBox sliderBox = new HBox(12);
        sliderBox.setAlignment(Pos.CENTER_RIGHT);
        Label minLbl = new Label("0.5 km");
        minLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        Slider radiusSlider = new Slider(0.5, 5.0, data.radiusKm);
        radiusSlider.setPrefWidth(160);
        radiusSlider.setStyle("-fx-accent: " + ORANGE_PRIMARY + ";");
        radiusSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            data.radiusKm = newVal.doubleValue();
            radTitle.setText("Radius Search: " + String.format("%.1f", data.radiusKm) + " km");
        });

        Label maxLbl = new Label("5.0 km");
        maxLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        sliderBox.getChildren().addAll(minLbl, radiusSlider, maxLbl);
        filterCard.setRight(sliderBox);

        // Orders List Grid / Cards Container
        VBox ordersContainer = new VBox(14);
        for (NearbyOrderItem order : data.orders) {
            ordersContainer.getChildren().add(createNearbyOrderCard(scene, data, order));
        }

        wrapper.getChildren().addAll(filterCard, ordersContainer);
        content.getChildren().add(wrapper);
        return content;
    }

    // =========================================================================
    // NEARBY ORDER ITEM CARD (WITH LIVE ACCEPT TO NAVIGATION)
    // =========================================================================
    private static VBox createNearbyOrderCard(Scene scene, NearbyData data, NearbyOrderItem item) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + (item.isAccepted ? "#86efac" : BORDER_COLOR) + ";" +
                "-fx-border-width: " + (item.isAccepted ? "1.5" : "1") + ";" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);"
        );

        // Header Row: Distance Capsule, ID & Instant Payout (INR ₹)
        BorderPane header = new BorderPane();

        HBox tagGroup = new HBox(8);
        tagGroup.setAlignment(Pos.CENTER_LEFT);

        Label distPill = new Label("📍 " + item.distanceText);
        distPill.setStyle("-fx-background-color: #ffedd5; -fx-text-fill: #c2410c; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 3 8 3 8;");

        Label idLbl = new Label("Order #" + item.id);
        idLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");

        Label timeLbl = new Label("•  " + item.estDuration + " trip");
        timeLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        tagGroup.getChildren().addAll(distPill, idLbl, timeLbl);
        header.setLeft(tagGroup);

        Label payoutLbl = new Label("₹" + String.format("%.2f", item.payout));
        payoutLbl.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #15803d;");
        header.setRight(payoutLbl);

        // Location Path (Pickup Store -> Customer Dropoff)
        HBox pathRow = new HBox(20);
        pathRow.setAlignment(Pos.CENTER_LEFT);
        pathRow.setPadding(new Insets(4, 0, 4, 0));

        VBox storeBox = new VBox(2);
        Label storeTag = new Label("STORE PICKUP");
        storeTag.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: #9a3412;");
        Label storeName = new Label("🏪 " + item.storeName);
        storeName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label storeAddr = new Label(item.storeAddress);
        storeAddr.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        storeBox.getChildren().addAll(storeTag, storeName, storeAddr);
        HBox.setHgrow(storeBox, Priority.ALWAYS);

        Label arrow = new Label(" ➔ ");
        arrow.setStyle("-fx-font-size: 14px; -fx-text-fill: #9ca3af;");

        VBox custBox = new VBox(2);
        Label custTag = new Label("CUSTOMER DROPOFF");
        custTag.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: " + ORANGE_PRIMARY + ";");
        Label custName = new Label("👤 " + item.customerName);
        custName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label custAddr = new Label(item.customerAddress);
        custAddr.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        custBox.getChildren().addAll(custTag, custName, custAddr);
        HBox.setHgrow(custBox, Priority.ALWAYS);

        pathRow.getChildren().addAll(storeBox, arrow, custBox);

        // Footer Actions & Items Summary
        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(8, 0, 0, 0));
        footer.setStyle("-fx-border-color: #f3f4f6; -fx-border-width: 1 0 0 0;");

        Label itemsText = new Label("📦 " + item.itemCount + " Items: " + item.itemsSummary);
        itemsText.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        footer.setLeft(itemsText);
        BorderPane.setAlignment(itemsText, Pos.CENTER_LEFT);

        if (item.isAccepted) {
            Button btnNavigate = new Button("Start Live Navigation  ➤");
            btnNavigate.setPrefHeight(34);
            btnNavigate.setStyle(
                    "-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 0 16 0 16; -fx-cursor: hand;"
            );
            btnNavigate.setOnAction(e -> {
                PartnerNavigation.TripData trip = new PartnerNavigation.TripData();
                trip.orderNumber = "Order #" + item.id;
                trip.pickupName = item.storeName;
                trip.pickupAddress = item.storeAddress;
                trip.dropoffName = item.customerName;
                trip.dropoffAddress = item.customerAddress;
                trip.orderEarnings = item.payout;
                PartnerNavigation.show(scene, trip);
            });
            footer.setRight(btnNavigate);
        } else {
            HBox actBtns = new HBox(8);
            actBtns.setAlignment(Pos.CENTER_RIGHT);

            Button btnDecline = new Button("Pass");
            btnDecline.setPrefHeight(32);
            btnDecline.setStyle("-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 6; -fx-background-radius: 6; -fx-text-fill: #4b5563; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 12 0 12; -fx-cursor: hand;");
            btnDecline.setOnAction(e -> {
                data.orders.remove(item);
                show(scene, data);
            });

            Button btnAccept = new Button("Accept (₹" + (int)item.payout + ")");
            btnAccept.setPrefHeight(32);
            btnAccept.setStyle("-fx-background-color: " + ORANGE_GRADIENT + "; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 0 16 0 16; -fx-cursor: hand;");
            btnAccept.setOnAction(e -> {
                item.isAccepted = true;
                show(scene, data);
            });

            actBtns.getChildren().addAll(btnDecline, btnAccept);
            footer.setRight(actBtns);
        }

        card.getChildren().addAll(header, pathRow, footer);
        return card;
    }
}
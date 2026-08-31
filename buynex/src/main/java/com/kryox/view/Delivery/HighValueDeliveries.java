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

public class HighValueDeliveries {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY HIGH VALUE DATA MODEL
    // =========================================================================
    public static class HighValueData {
        public String partnerName = "Alex Walker";
        public String currentZone = "Pune Tech Corridor & Prime Hubs";
        public double minPayoutFilter = 150.0;
        public List<HighValueOrderItem> orders = new ArrayList<>();

        public HighValueData() {
            orders.add(new HighValueOrderItem(
                    "BN-9921",
                    380.00,
                    45.00,
                    "Apple Authorised Store",
                    "Electronics & Luxury Goods",
                    "Pavilion Mall, Senapati Bapat Rd",
                    "Vikramaditya Roy",
                    "Villa 14, Supreme Palms, Baner",
                    "iPad Air & Magic Keyboard",
                    "High Security OTP • Fragile Handling",
                    "6.4 km",
                    "25 mins"
            ));

            orders.add(new HighValueOrderItem(
                    "BN-8804",
                    260.00,
                    30.00,
                    "Gourmet Delight Fine Dine",
                    "Premium Multi-Cuisine Catering",
                    "Kalyani Nagar Main Road",
                    "Meera Nambiar",
                    "Penthouse 1201, Waterfront Tower",
                    "Multi-Course Banquet Pack (4 Bags)",
                    "Insulated Bag Required • Thermal Seal",
                    "4.2 km",
                    "20 mins"
            ));

            orders.add(new HighValueOrderItem(
                    "BN-7740",
                    210.00,
                    25.00,
                    "Chroma Flagship Tech",
                    "Consumer Electronics",
                    "Amanora Town Centre, Hadapsar",
                    "Rohit Kulkarni",
                    "Tower 3, Magarpatta City",
                    "Noise Cancelling Headphones & Smartwatch",
                    "Signature Required Upon Delivery",
                    "5.1 km",
                    "22 mins"
            ));

            orders.add(new HighValueOrderItem(
                    "BN-6518",
                    175.50,
                    20.00,
                    "Artisan Hampers & Florals",
                    "Luxury Gift Hampers",
                    "Koregaon Park North Main Rd",
                    "Ananya Deshpande",
                    "Bunglow No. 8, Lane 5, KP",
                    "Exotic Flower Box & Imported Chocolates",
                    "Handle With Care • Upright Position",
                    "3.0 km",
                    "15 mins"
            ));
        }
    }

    public static class HighValueOrderItem {
        public String id;
        public double partnerPayout;
        public double includedTip;
        public String storeName;
        public String storeCategory;
        public String storeAddress;
        public String customerName;
        public String customerAddress;
        public String itemsSummary;
        public String specialHandlingTag;
        public String distanceText;
        public String estDuration;
        public boolean isAccepted = false;

        public HighValueOrderItem(String id, double partnerPayout, double includedTip, String storeName,
                                  String storeCategory, String storeAddress, String customerName,
                                  String customerAddress, String itemsSummary, String specialHandlingTag,
                                  String distanceText, String estDuration) {
            this.id = id;
            this.partnerPayout = partnerPayout;
            this.includedTip = includedTip;
            this.storeName = storeName;
            this.storeCategory = storeCategory;
            this.storeAddress = storeAddress;
            this.customerName = customerName;
            this.customerAddress = customerAddress;
            this.itemsSummary = itemsSummary;
            this.specialHandlingTag = specialHandlingTag;
            this.distanceText = distanceText;
            this.estDuration = estDuration;
        }
    }

    // =========================================================================
    // STATIC SCENE FACTORY METHODS (SHOPKEEPER PATTERN)
    // =========================================================================
    public static Scene highValueDeliveriesScene() {
        return highValueDeliveriesScene(new HighValueData());
    }

    public static Scene highValueDeliveriesScene(HighValueData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(data));

        // 2. Center Content inside ScrollPane
        VBox mainContent = createMainContent(data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    // =========================================================================
    // TOP HEADER
    // =========================================================================
    private static BorderPane createTopHeader(HighValueData data) {
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
        btnBack.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
            }
        });

        Text title = new Text("High Value Delivery Queue");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        HBox primePill = new HBox(6);
        primePill.setAlignment(Pos.CENTER_RIGHT);
        Circle goldDot = new Circle(4, Color.web("#d97706"));
        Label primeText = new Label("⭐ Prime Incentive Surge Active");
        primeText.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        primePill.setStyle("-fx-background-color: #fef3c7; -fx-background-radius: 12; -fx-padding: 4 10 4 10;");
        primePill.getChildren().addAll(goldDot, primeText);
        topBar.setRight(primePill);

        return topBar;
    }

    // =========================================================================
    // MAIN CONTENT VIEW
    // =========================================================================
    private static VBox createMainContent(HighValueData data) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(24, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox wrapper = new VBox(20);
        wrapper.setMaxWidth(880);

        BorderPane filterCard = new BorderPane();
        filterCard.setPadding(new Insets(16, 20, 16, 20));
        filterCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #fef08a #e5e7eb #e5e7eb #e5e7eb;" +
                "-fx-border-width: 3 1 1 1;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 8, 0, 0, 2);"
        );

        VBox filterLeft = new VBox(2);
        Text filterTitle = new Text("Minimum Payout Filter: ₹" + (int) data.minPayoutFilter + "+");
        filterTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-fill: #111827;");
        Label filterSub = new Label("Showing premium orders with elevated delivery fees, bulky order boosts, and direct customer tips.");
        filterSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        filterLeft.getChildren().addAll(filterTitle, filterSub);
        filterCard.setLeft(filterLeft);

        HBox sliderBox = new HBox(12);
        sliderBox.setAlignment(Pos.CENTER_RIGHT);
        Label minLbl = new Label("₹100");
        minLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        Slider payoutSlider = new Slider(100, 500, data.minPayoutFilter);
        payoutSlider.setPrefWidth(160);
        payoutSlider.setStyle("-fx-accent: #93380b;");
        payoutSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            data.minPayoutFilter = newVal.doubleValue();
            filterTitle.setText("Minimum Payout Filter: ₹" + (int) data.minPayoutFilter + "+");
        });

        Label maxLbl = new Label("₹500");
        maxLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        sliderBox.getChildren().addAll(minLbl, payoutSlider, maxLbl);
        filterCard.setRight(sliderBox);

        VBox ordersContainer = new VBox(14);
        for (HighValueOrderItem order : data.orders) {
            ordersContainer.getChildren().add(createHighValueCard(data, order));
        }

        wrapper.getChildren().addAll(filterCard, ordersContainer);
        content.getChildren().add(wrapper);
        return content;
    }

    // =========================================================================
    // HIGH VALUE ORDER CARD
    // =========================================================================
    private static VBox createHighValueCard(HighValueData data, HighValueOrderItem item) {
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

        BorderPane header = new BorderPane();

        HBox tagGroup = new HBox(8);
        tagGroup.setAlignment(Pos.CENTER_LEFT);

        Label premiumBadge = new Label("💎 HIGH VALUE");
        premiumBadge.setStyle("-fx-background-color: #fef3c7; -fx-text-fill: #b45309; -fx-font-size: 10px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 3 8 3 8;");

        Label idLbl = new Label("Order #" + item.id);
        idLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");

        Label distLbl = new Label("•  " + item.distanceText + " (" + item.estDuration + ")");
        distLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        tagGroup.getChildren().addAll(premiumBadge, idLbl, distLbl);
        header.setLeft(tagGroup);

        VBox payoutBox = new VBox(1);
        payoutBox.setAlignment(Pos.TOP_RIGHT);
        Label payoutLbl = new Label("₹" + String.format("%.2f", item.partnerPayout));
        payoutLbl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #15803d;");

        Label tipSub = new Label("Includes ₹" + (int) item.includedTip + " Tip");
        tipSub.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #d97706;");
        payoutBox.getChildren().addAll(payoutLbl, tipSub);
        header.setRight(payoutBox);

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
        Label custTag = new Label("VIP CUSTOMER DROPOFF");
        custTag.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: " + ORANGE_PRIMARY + ";");
        Label custName = new Label("👤 " + item.customerName);
        custName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label custAddr = new Label(item.customerAddress);
        custAddr.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        custBox.getChildren().addAll(custTag, custName, custAddr);
        HBox.setHgrow(custBox, Priority.ALWAYS);

        pathRow.getChildren().addAll(storeBox, arrow, custBox);

        HBox specRow = new HBox(8);
        specRow.setAlignment(Pos.CENTER_LEFT);
        specRow.setPadding(new Insets(6, 10, 6, 10));
        specRow.setStyle("-fx-background-color: #fafafc; -fx-background-radius: 6; -fx-border-color: #f1f3f7; -fx-border-radius: 6;");

        Label alertIcon = new Label("🛡️");
        alertIcon.setStyle("-fx-font-size: 12px;");

        Label specText = new Label("Requirement: " + item.specialHandlingTag + "  |  Package: " + item.itemsSummary);
        specText.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        specRow.getChildren().addAll(alertIcon, specText);

        BorderPane footer = new BorderPane();
        footer.setPadding(new Insets(6, 0, 0, 0));
        footer.setStyle("-fx-border-color: #f3f4f6; -fx-border-width: 1 0 0 0;");

        Label guaranteeText = new Label("✓ Instant Payout Credited Upon Dropoff Confirmation");
        guaranteeText.setStyle("-fx-font-size: 10px; -fx-text-fill: #15803d; -fx-font-weight: bold;");
        footer.setLeft(guaranteeText);
        BorderPane.setAlignment(guaranteeText, Pos.CENTER_LEFT);

        if (item.isAccepted) {
            Button btnNavigate = new Button("Start High-Priority Navigation  ➤");
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
                trip.orderEarnings = item.partnerPayout;
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene(trip));
                }
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
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(highValueDeliveriesScene(data));
                }
            });

            Button btnAccept = new Button("Accept (₹" + (int) item.partnerPayout + ")");
            btnAccept.setPrefHeight(32);
            btnAccept.setStyle("-fx-background-color: " + ORANGE_GRADIENT + "; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 0 16 0 16; -fx-cursor: hand;");
            btnAccept.setOnAction(e -> {
                item.isAccepted = true;
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(highValueDeliveriesScene(data));
                }
            });

            actBtns.getChildren().addAll(btnDecline, btnAccept);
            footer.setRight(actBtns);
        }

        card.getChildren().addAll(header, pathRow, specRow, footer);
        return card;
    }
}
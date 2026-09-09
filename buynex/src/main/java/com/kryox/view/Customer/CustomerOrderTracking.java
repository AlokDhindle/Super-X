package com.kryox.view.Customer;


import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;


import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Dedicated Customer Order Tracking View with Live Interactive Map.
 * Replaces the Delivery Partner Navigation screen for Customer order tracking.
 */
public class CustomerOrderTracking {

    private final String userId;
    private final OrderModel order;
    private final Runnable backCallback;

    private ListenerRegistration liveOrderListener;
    private CustomerOsmMapView mapView;

    // Dynamic UI elements that update on live Firestore changes
    private Label statusBadgeLabel;
    private Label riderNameLabel;
    private Label riderPhoneLabel;
    private Label riderDistanceLabel;
    private Label hudEtaLabel;
    private Label hudSubLabel;

    public CustomerOrderTracking(String userId, OrderModel order, Runnable backCallback) {
        this.userId = userId;
        this.order = order;
        this.backCallback = backCallback;
    }

    public Scene getTrackingScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #EEE5DE;");

        // Top Navigation Header
        root.setTop(createTopHeader());

        // Center: Live Interactive OpenStreetMap View
        StackPane mapArea = createMapArea();
        root.setCenter(mapArea);

        // Right: Customer Order Tracking Details Panel
        VBox rightDetailsPanel = createRightPanel();
        root.setRight(rightDetailsPanel);

        // Attach Realtime Listener for this specific order
        attachRealtimeOrderListener();

        Scene scene = new Scene(root, 1530, 850);
        return scene;
    }

    private HBox createTopHeader() {
        HBox header = new HBox(15);
        header.setPrefHeight(68);
        header.setPadding(new Insets(12, 28, 12, 28));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle(
                "-fx-background-color: #EBCCB7;" +
                "-fx-border-color: #dfbfab;" +
                "-fx-border-width: 0 0 1 0;"
        );

        DropShadow headerShadow = new DropShadow();
        headerShadow.setRadius(8);
        headerShadow.setOffsetY(2);
        headerShadow.setColor(Color.rgb(0, 0, 0, 0.08));
        header.setEffect(headerShadow);

        // Back to Orders Button
        Button backBtn = new Button("← Back to Orders");
        backBtn.setPrefHeight(38);
        backBtn.setPadding(new Insets(6, 18, 6, 16));
        backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 700;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 20;" +
                "-fx-cursor: hand;"
        );
        backBtn.setOnMouseEntered(e -> {
            backBtn.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: 700;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-color: #FF6900;" +
                    "-fx-border-width: 1.2;" +
                    "-fx-border-radius: 20;" +
                    "-fx-cursor: hand;"
            );
        });
        backBtn.setOnMouseExited(e -> {
            backBtn.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #FF6900;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: 700;" +
                    "-fx-background-radius: 20;" +
                    "-fx-border-color: #FF6900;" +
                    "-fx-border-width: 1.2;" +
                    "-fx-border-radius: 20;" +
                    "-fx-cursor: hand;"
            );
        });
        backBtn.setOnAction(e -> {
            cleanupListener();
            if (backCallback != null) {
                backCallback.run();
            } else {
                CustomerNavigation.navigateToOrders(userId);
            }
        });

        // App Logo & Title
        Label brand = new Label("BuyNeX");
        brand.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #E87500;"
        );

        Region div1 = new Region();
        div1.setPrefWidth(2);
        div1.setPrefHeight(22);
        div1.setStyle("-fx-background-color: #d1b8a5;");

        VBox titleBox = new VBox(2);
        Label title = new Label("Order Tracking");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );
        String orderIdStr = (order != null && order.getOrderId() != null) ? order.getOrderId() : "BX-N/A";
        Label subTitle = new Label("Order #" + orderIdStr);
        subTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 600;" +
                "-fx-text-fill: #666666;"
        );
        titleBox.getChildren().addAll(title, subTitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Live Status Pill
        HBox livePill = new HBox(8);
        livePill.setAlignment(Pos.CENTER);
        livePill.setPadding(new Insets(6, 14, 6, 14));
        livePill.setStyle(
                "-fx-background-color: #dcfce7;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #86efac;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 20;"
        );

        Circle pulseDot = new Circle(5, Color.web("#16a34a"));
        Label liveText = new Label("LIVE TRACKING ACTIVE");
        liveText.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #15803d;"
        );
        livePill.getChildren().addAll(pulseDot, liveText);

        header.getChildren().addAll(backBtn, brand, div1, titleBox, spacer, livePill);
        return header;
    }

    private StackPane createMapArea() {
        StackPane mapStack = new StackPane();
        mapStack.setStyle("-fx-background-color: #E8ECEF;");

        // Native OpenStreetMap Component
        mapView = new CustomerOsmMapView(order);
        mapStack.getChildren().add(mapView);

        // Top-Left Floating HUD Card: Order Status & ETA
        VBox topHud = new VBox(6);
        topHud.setMaxWidth(380);
        topHud.setMaxHeight(Region.USE_PREF_SIZE);
        topHud.setPadding(new Insets(16, 20, 16, 20));
        topHud.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.95);" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: rgba(235, 204, 183, 0.8);" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 16;"
        );
        DropShadow hudShadow = new DropShadow();
        hudShadow.setRadius(16);
        hudShadow.setOffsetY(4);
        hudShadow.setColor(Color.rgb(0, 0, 0, 0.12));
        topHud.setEffect(hudShadow);

        HBox hudHeader = new HBox(10);
        hudHeader.setAlignment(Pos.CENTER_LEFT);

        Label scooterIcon = new Label("🛵");
        scooterIcon.setStyle("-fx-font-size: 22px;");

        VBox hudTexts = new VBox(2);
        statusBadgeLabel = new Label("Out for Delivery");
        statusBadgeLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #FF6900;"
        );

        hudEtaLabel = new Label("Estimated Arrival: 15 - 20 mins");
        hudEtaLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #222222;"
        );
        hudTexts.getChildren().addAll(statusBadgeLabel, hudEtaLabel);

        hudHeader.getChildren().addAll(scooterIcon, hudTexts);

        hudSubLabel = new Label("Your delivery partner is on the way to your address.");
        hudSubLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #666666;"
        );

        topHud.getChildren().addAll(hudHeader, hudSubLabel);
        StackPane.setAlignment(topHud, Pos.TOP_LEFT);
        StackPane.setMargin(topHud, new Insets(20, 0, 0, 20));
        mapStack.getChildren().add(topHud);

        // Bottom Floating Rider Pill on Map
        HBox bottomRiderPill = new HBox(12);
        bottomRiderPill.setAlignment(Pos.CENTER_LEFT);
        bottomRiderPill.setPadding(new Insets(10, 18, 10, 18));
        bottomRiderPill.setMaxWidth(420);
        bottomRiderPill.setMaxHeight(Region.USE_PREF_SIZE);
        bottomRiderPill.setStyle(
                "-fx-background-color: rgba(34, 34, 34, 0.92);" +
                "-fx-background-radius: 24;" +
                "-fx-border-color: rgba(255, 105, 0, 0.5);" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 24;"
        );
        DropShadow riderShadow = new DropShadow();
        riderShadow.setRadius(12);
        riderShadow.setOffsetY(3);
        riderShadow.setColor(Color.rgb(0, 0, 0, 0.20));
        bottomRiderPill.setEffect(riderShadow);

        Label rIcon = new Label("📍");
        rIcon.setStyle("-fx-font-size: 18px;");

        String rName = (order != null && order.getRiderName() != null) ? order.getRiderName() : "Rahul Sharma";
        String rDist = (order != null && order.getRiderDistance() != null) ? order.getRiderDistance() : "0.8 km away (within 1.5km radius)";

        riderDistanceLabel = new Label("🛵 " + rName + " is " + rDist);
        riderDistanceLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: white;"
        );

        bottomRiderPill.getChildren().addAll(rIcon, riderDistanceLabel);
        StackPane.setAlignment(bottomRiderPill, Pos.BOTTOM_LEFT);
        StackPane.setMargin(bottomRiderPill, new Insets(0, 0, 22, 20));
        mapStack.getChildren().add(bottomRiderPill);

        return mapStack;
    }

    private VBox createRightPanel() {
        VBox panel = new VBox(16);
        panel.setPrefWidth(430);
        panel.setMinWidth(430);
        panel.setMaxWidth(430);
        panel.setPadding(new Insets(20, 22, 24, 22));
        panel.setStyle("-fx-background-color: #eee5df;");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(panel);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: #dfbfab;" +
                "-fx-border-width: 0 0 0 1;"
        );
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox rightWrapper = new VBox(scrollPane);
        rightWrapper.setPrefWidth(430);
        rightWrapper.setMinWidth(430);
        rightWrapper.setMaxWidth(430);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        // Panel Title
        Label panelTitle = new Label("Delivery Details");
        panelTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );
        panel.getChildren().add(panelTitle);

        // Card 1: Delivery Partner Card
        panel.getChildren().add(createDeliveryPartnerCard());

        // Card 2: Visual Delivery Stepper
        panel.getChildren().add(createTimelineStepperCard());

        // Card 3: Route & Address Card
        panel.getChildren().add(createAddressesCard());

        // Card 4: Order Summary Card
        panel.getChildren().add(createOrderSummaryCard());

        // Card 5: Customer Support Help Button
        Button helpBtn = new Button("Need Help with this Order?");
        helpBtn.setPrefWidth(386);
        helpBtn.setPrefHeight(42);
        helpBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 700;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-width: 1.2;" +
                "-fx-border-radius: 12;" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );
        helpBtn.setOnAction(e -> {
            cleanupListener();
            CustomerNavigation.navigateToHelp(userId);
        });
        panel.getChildren().add(helpBtn);

        return rightWrapper;
    }

    private VBox createDeliveryPartnerCard() {
        VBox card = createCardBase();

        Label cardHeader = new Label("Delivery Partner");
        cardHeader.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #888888;" +
                "-fx-padding: 0 0 6 0;"
        );

        HBox partnerRow = new HBox(12);
        partnerRow.setAlignment(Pos.CENTER_LEFT);

        // Avatar
        StackPane avatarPane = new StackPane();
        Circle bgCircle = new Circle(24);
        LinearGradient grad = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#FF6900")),
                new Stop(1, Color.web("#FF9E59"))
        );
        bgCircle.setFill(grad);
        Label riderEmoji = new Label("🛵");
        riderEmoji.setStyle("-fx-font-size: 20px;");
        avatarPane.getChildren().addAll(bgCircle, riderEmoji);

        // Name & Verification
        VBox infoBox = new VBox(2);
        String nameStr = (order != null && order.getRiderName() != null) ? order.getRiderName() : "Rahul Sharma";
        riderNameLabel = new Label(nameStr);
        riderNameLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        HBox subRow = new HBox(8);
        subRow.setAlignment(Pos.CENTER_LEFT);
        Label badge = new Label("✓ Verified Partner");
        badge.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #15803d;" +
                "-fx-background-color: #dcfce7;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 2 6 2 6;"
        );
        Label rating = new Label("★ 4.9 (350+ orders)");
        rating.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: 600;" +
                "-fx-text-fill: #777777;"
        );
        subRow.getChildren().addAll(badge, rating);
        infoBox.getChildren().addAll(riderNameLabel, subRow);

        partnerRow.getChildren().addAll(avatarPane, infoBox);

        // Vehicle info
        Label vehicleInfo = new Label("🛵 Honda Activa • White (MH 12 AB 1234)");
        vehicleInfo.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #555555;" +
                "-fx-padding: 4 0 4 0;"
        );

        // Action Buttons: Call & Message
        HBox actionRow = new HBox(10);
        actionRow.setAlignment(Pos.CENTER);

        String phoneStr = (order != null && order.getRiderPhone() != null) ? order.getRiderPhone() : "+91 98765 43210";
        riderPhoneLabel = new Label(phoneStr);

        Button callBtn = new Button("📞 Call Partner");
        callBtn.setPrefHeight(34);
        callBtn.setPrefWidth(160);
        callBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-background-radius: 18;" +
                "-fx-cursor: hand;"
        );
        callBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Calling Delivery Partner");
            alert.setHeaderText("Connecting to " + nameStr);
            alert.setContentText("Dialing: " + phoneStr + "\nPlease make sure your phone has mobile network coverage.");
            alert.showAndWait();
        });

        Button msgBtn = new Button("💬 Message");
        msgBtn.setPrefHeight(34);
        msgBtn.setPrefWidth(140);
        msgBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-border-color: #E2DFE5;" +
                "-fx-border-radius: 18;" +
                "-fx-background-radius: 18;" +
                "-fx-cursor: hand;"
        );
        msgBtn.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Chat with Delivery Partner");
            alert.setHeaderText("Quick Chat with " + nameStr);
            alert.setContentText("Status: Partner is driving. Updates will be shown live on this map.");
            alert.showAndWait();
        });

        actionRow.getChildren().addAll(callBtn, msgBtn);

        card.getChildren().addAll(cardHeader, partnerRow, vehicleInfo, actionRow);
        return card;
    }

    private VBox createTimelineStepperCard() {
        VBox card = createCardBase();

        Label cardHeader = new Label("Order Journey");
        cardHeader.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #888888;" +
                "-fx-padding: 0 0 6 0;"
        );

        VBox stepsBox = new VBox(10);
        stepsBox.getChildren().addAll(
                createStepRow("✓", "Order Confirmed", "Your order was placed & accepted", true, false),
                createStepRow("✓", "Packed & Ready", "Order prepared by store & handed over", true, false),
                createStepRow("🛵", "Out for Delivery", "Delivery partner on the way to you", true, true),
                createStepRow("🏠", "Delivered", "Arriving at your doorstep", false, false)
        );

        card.getChildren().addAll(cardHeader, stepsBox);
        return card;
    }

    private HBox createStepRow(String icon, String title, String desc, boolean completed, boolean active) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);

        StackPane stepCircle = new StackPane();
        Circle c = new Circle(14);
        if (active) {
            c.setFill(Color.web("#FF6900"));
        } else if (completed) {
            c.setFill(Color.web("#22c55e"));
        } else {
            c.setFill(Color.web("#E2E4E8"));
        }

        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-text-fill: " + (completed || active ? "white" : "#888888") + "; -fx-font-size: 11px; -fx-font-weight: bold;");
        stepCircle.getChildren().addAll(c, iconLbl);

        VBox text = new VBox(1);
        Label titleLbl = new Label(title);
        titleLbl.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: " + (active ? "800" : "700") + ";" +
                "-fx-text-fill: " + (active ? "#FF6900" : (completed ? "#222222" : "#888888")) + ";"
        );
        Label descLbl = new Label(desc);
        descLbl.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;"
        );
        text.getChildren().addAll(titleLbl, descLbl);

        row.getChildren().addAll(stepCircle, text);
        return row;
    }

    private VBox createAddressesCard() {
        VBox card = createCardBase();

        Label cardHeader = new Label("Route Locations");
        cardHeader.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #888888;" +
                "-fx-padding: 0 0 6 0;"
        );

        String shop = (order != null && order.getShopName() != null) ? order.getShopName() : "BuyNeX Partner Store";
        String customer = (order != null && order.getCustomerName() != null) ? order.getCustomerName() : "Customer Residence";

        HBox storeRow = new HBox(8);
        storeRow.setAlignment(Pos.CENTER_LEFT);
        Label sIcon = new Label("🏪");
        sIcon.setStyle("-fx-font-size: 14px;");
        VBox sBox = new VBox(1);
        Label sTitle = new Label("Pickup: " + shop);
        sTitle.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-font-weight: 700; -fx-text-fill: #333333;");
        Label sSub = new Label("Store Location (Order dispatched)");
        sSub.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 10px; -fx-text-fill: #777777;");
        sBox.getChildren().addAll(sTitle, sSub);
        storeRow.getChildren().addAll(sIcon, sBox);

        HBox dropRow = new HBox(8);
        dropRow.setAlignment(Pos.CENTER_LEFT);
        Label dIcon = new Label("📍");
        dIcon.setStyle("-fx-font-size: 14px;");
        VBox dBox = new VBox(1);
        Label dTitle = new Label("Deliver to: " + customer);
        dTitle.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-font-weight: 700; -fx-text-fill: #333333;");
        Label dSub = new Label("Registered Home Delivery Address");
        dSub.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 10px; -fx-text-fill: #777777;");
        dBox.getChildren().addAll(dTitle, dSub);
        dropRow.getChildren().addAll(dIcon, dBox);

        card.getChildren().addAll(cardHeader, storeRow, dropRow);
        return card;
    }

    private VBox createOrderSummaryCard() {
        VBox card = createCardBase();

        Label cardHeader = new Label("Order Summary");
        cardHeader.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 700;" +
                "-fx-text-fill: #888888;" +
                "-fx-padding: 0 0 6 0;"
        );

        VBox itemsBox = new VBox(4);
        if (order != null && order.getProducts() != null && !order.getProducts().isEmpty()) {
            for (OrderItemModel item : order.getProducts()) {
                if (item == null) continue;
                String pName = item.getProductName() != null ? item.getProductName() : "Product";
                int qty = item.getQuantity() > 0 ? item.getQuantity() : 1;
                double pPrice = item.getPrice();

                HBox itemRow = new HBox();
                Label itemText = new Label(qty + "x " + pName);
                itemText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-text-fill: #444444;");
                Region sp = new Region();
                HBox.setHgrow(sp, Priority.ALWAYS);
                Label priceText = new Label(String.format("₹%.2f", pPrice * qty));
                priceText.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-font-weight: 600; -fx-text-fill: #444444;");
                itemRow.getChildren().addAll(itemText, sp, priceText);
                itemsBox.getChildren().add(itemRow);
            }
        } else {
            Label noItems = new Label("1x Order Items Package");
            noItems.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-text-fill: #555555;");
            itemsBox.getChildren().add(noItems);
        }

        Region div = new Region();
        div.setPrefHeight(1);
        div.setStyle("-fx-background-color: #EFEBEA; -fx-padding: 4 0 4 0;");

        HBox totalRow = new HBox();
        Label totalLabel = new Label("Total Paid");
        totalLabel.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 12px; -fx-font-weight: 800; -fx-text-fill: #222222;");
        Region sp2 = new Region();
        HBox.setHgrow(sp2, Priority.ALWAYS);
        double totalAmt = order != null ? order.getTotalAmount() : 0.0;
        Label totalVal = new Label(String.format("₹%.2f", totalAmt));
        totalVal.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 14px; -fx-font-weight: 900; -fx-text-fill: #FF6900;");
        totalRow.getChildren().addAll(totalLabel, sp2, totalVal);

        card.getChildren().addAll(cardHeader, itemsBox, div, totalRow);
        return card;
    }

    private VBox createCardBase() {
        VBox card = new VBox(8);
        card.setPadding(new Insets(14, 16, 14, 16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #E8E2E9;" +
                "-fx-border-radius: 14;" +
                "-fx-border-width: 1;"
        );
        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(8);
        cardShadow.setOffsetY(2);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.05));
        card.setEffect(cardShadow);
        return card;
    }

    private void attachRealtimeOrderListener() {
        if (order == null || order.getOrderId() == null) return;
        try {
            Firestore db = Firebaseconfig.gFirestore();
            liveOrderListener = db.collection("Orders").document(order.getOrderId())
                    .addSnapshotListener((snapshot, error) -> {
                        if (error != null) {
                            System.err.println("Customer Tracking Listener Error: " + error.getMessage());
                            return;
                        }

                        if (snapshot != null && snapshot.exists()) {
                            Platform.runLater(() -> {
                                String updatedStatus = snapshot.getString("orderStatus");
                                String updatedRider = snapshot.getString("riderName");
                                String updatedPhone = snapshot.getString("riderPhone");
                                String updatedDist = snapshot.getString("riderDistance");

                                if (updatedStatus != null && statusBadgeLabel != null) {
                                    statusBadgeLabel.setText(updatedStatus);
                                }
                                if (updatedRider != null && riderNameLabel != null) {
                                    riderNameLabel.setText(updatedRider);
                                }
                                if (updatedPhone != null && riderPhoneLabel != null) {
                                    riderPhoneLabel.setText(updatedPhone);
                                }
                                if (updatedDist != null && riderDistanceLabel != null) {
                                    riderDistanceLabel.setText("🛵 " + (updatedRider != null ? updatedRider : "Partner") + " is " + updatedDist);
                                }

                                Double rLat = snapshot.getDouble("riderLat");
                                Double rLng = snapshot.getDouble("riderLng");
                                if (rLat != null && rLng != null && mapView != null) {
                                    mapView.updateRiderPosition(rLat, rLng);
                                }
                            });
                        }
                    });
        } catch (Exception ex) {
            System.err.println("Customer Tracking failed to attach listener: " + ex.getMessage());
        }
    }

    private void cleanupListener() {
        if (liveOrderListener != null) {
            try {
                liveOrderListener.remove();
            } catch (Exception ignored) {}
            liveOrderListener = null;
        }
    }

    private static class CustomerOsmMapView extends StackPane {
        private static final int TILE_SIZE = 256;
        private static final int MAX_ZOOM = 18;
        private static final int MIN_ZOOM = 3;

        private final Pane tilePane = new Pane();
        private final Pane routePane = new Pane();
        private final Pane markerPane = new Pane();

        private final HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        private final Map<String, Image> imageCache = new ConcurrentHashMap<>();

        private double centerLatitude = 18.5139;
        private double centerLongitude = 73.8322;
        private int zoom = 14;

        private List<double[]> routePoints = new ArrayList<>();
        private double pickupLat = 18.5204;
        private double pickupLng = 73.8567;
        private String pickupName = "Store";
        private String pickupAddress = "Pickup Store";

        private double dropoffLat = 18.5074;
        private double dropoffLng = 73.8077;
        private String dropoffName = "Customer";
        private String dropoffAddress = "Home Delivery";

        private double riderLat = 18.5150;
        private double riderLng = 73.8350;
        private String riderName = "Rahul Sharma";

        private double mousePressedX;
        private double mousePressedY;
        private double pressedCenterX;
        private double pressedCenterY;
        private long renderNumber = 0;

        public CustomerOsmMapView(OrderModel order) {
            setStyle("-fx-background-color: #E8ECEF;");

            tilePane.setMouseTransparent(true);
            routePane.setMouseTransparent(true);
            markerPane.setMouseTransparent(false);

            getChildren().addAll(tilePane, routePane, markerPane);

            Rectangle clip = new Rectangle();
            clip.widthProperty().bind(widthProperty());
            clip.heightProperty().bind(heightProperty());
            setClip(clip);

            createControls();
            createAttribution();

            setOnMousePressed(this::handleMousePressed);
            setOnMouseDragged(this::handleMouseDragged);
            setOnMouseReleased(e -> scheduleRender());

            setOnScroll(e -> {
                if (e.getDeltaY() > 0) {
                    zoomIn();
                } else if (e.getDeltaY() < 0) {
                    zoomOut();
                }
                e.consume();
            });

            widthProperty().addListener((obs, oldVal, newVal) -> scheduleRender());
            heightProperty().addListener((obs, oldVal, newVal) -> scheduleRender());

            if (order != null) {
                this.pickupLat = order.getShopLat() != 0.0 ? order.getShopLat() : 18.5204;
                this.pickupLng = order.getShopLng() != 0.0 ? order.getShopLng() : 73.8567;
                this.pickupName = order.getShopName() != null ? order.getShopName() : "Store";
                this.pickupAddress = "Dispatched Store Location";

                this.dropoffName = order.getCustomerName() != null ? order.getCustomerName() : "Customer";
                this.dropoffAddress = order.getCustomerPhone() != null ? ("Phone: " + order.getCustomerPhone()) : "Home Delivery";

                this.riderName = order.getRiderName() != null ? order.getRiderName() : "Rahul Sharma";
            }

            // Rider located midway on route initially
            this.riderLat = (pickupLat * 0.45) + (dropoffLat * 0.55);
            this.riderLng = (pickupLng * 0.45) + (dropoffLng * 0.55);

            this.centerLatitude = (pickupLat + dropoffLat) / 2.0;
            this.centerLongitude = (pickupLng + dropoffLng) / 2.0;

            Platform.runLater(this::fetchAndDrawRoute);
        }

        public void updateRiderPosition(double lat, double lng) {
            this.riderLat = lat;
            this.riderLng = lng;
            renderMap();
        }

        private void createControls() {
            VBox controlBox = new VBox(6);
            controlBox.setAlignment(Pos.CENTER);
            controlBox.setPadding(new Insets(14));

            Button btnZoomIn = new Button("+");
            Button btnZoomOut = new Button("−");
            Button btnRecenter = new Button("🎯");

            String buttonStyle =
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #1f2937;" +
                    "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-radius: 8;" +
                    "-fx-border-color: #e5e7eb;" +
                    "-fx-border-width: 1;" +
                    "-fx-pref-width: 38px;" +
                    "-fx-pref-height: 38px;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 6, 0, 0, 2);";

            btnZoomIn.setStyle(buttonStyle);
            btnZoomOut.setStyle(buttonStyle);
            btnRecenter.setStyle(buttonStyle);

            btnZoomIn.setOnAction(e -> zoomIn());
            btnZoomOut.setOnAction(e -> zoomOut());
            btnRecenter.setOnAction(e -> recenterRoute());

            controlBox.getChildren().addAll(btnZoomIn, btnZoomOut, btnRecenter);
            StackPane.setAlignment(controlBox, Pos.TOP_RIGHT);
            StackPane.setMargin(controlBox, new Insets(20, 20, 0, 0));
            getChildren().add(controlBox);
        }

        private void createAttribution() {
            Label attribution = new Label("© OpenStreetMap contributors");
            attribution.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.92);" +
                    "-fx-text-fill: #4b5563;" +
                    "-fx-font-size: 9px;" +
                    "-fx-padding: 3 8 3 8;" +
                    "-fx-background-radius: 4;" +
                    "-fx-border-color: rgba(0,0,0,0.06);" +
                    "-fx-border-radius: 4;"
            );
            StackPane.setAlignment(attribution, Pos.BOTTOM_RIGHT);
            StackPane.setMargin(attribution, new Insets(0, 16, 12, 0));
            getChildren().add(attribution);
        }

        private void recenterRoute() {
            if (routePoints != null && !routePoints.isEmpty()) {
                applyRoute(routePoints);
            } else {
                fetchAndDrawRoute();
            }
        }

        private void handleMousePressed(MouseEvent event) {
            mousePressedX = event.getX();
            mousePressedY = event.getY();
            pressedCenterX = longitudeToWorldX(centerLongitude, zoom);
            pressedCenterY = latitudeToWorldY(centerLatitude, zoom);
        }

        private void handleMouseDragged(MouseEvent event) {
            double deltaX = mousePressedX - event.getX();
            double deltaY = mousePressedY - event.getY();

            double worldX = pressedCenterX + deltaX;
            double worldY = pressedCenterY + deltaY;

            centerLongitude = worldXToLongitude(worldX, zoom);
            centerLatitude = worldYToLatitude(worldY, zoom);
            centerLatitude = Math.max(-85.05112878, Math.min(85.05112878, centerLatitude));

            renderMap();
        }

        private void zoomIn() {
            if (zoom >= MAX_ZOOM) return;
            zoom++;
            renderMap();
        }

        private void zoomOut() {
            if (zoom <= MIN_ZOOM) return;
            zoom--;
            renderMap();
        }

        private void scheduleRender() {
            Platform.runLater(this::renderMap);
        }

        private void fetchAndDrawRoute() {
            String osrmUrl = "https://router.project-osrm.org/route/v1/driving/" +
                    pickupLng + "," + pickupLat + ";" +
                    dropoffLng + "," + dropoffLat +
                    "?overview=full&geometries=geojson";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(osrmUrl))
                    .header("User-Agent", "BuyNeX-JavaFX-CustomerTracking/1.0")
                    .GET()
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        if (response.statusCode() != 200) {
                            throw new RuntimeException("OSRM HTTP " + response.statusCode());
                        }
                        return parseRouteCoordinates(response.body());
                    })
                    .thenAccept(points -> {
                        Platform.runLater(() -> {
                            List<double[]> finalPoints = (points != null && points.size() >= 2)
                                    ? points
                                    : straightLine(pickupLat, pickupLng, dropoffLat, dropoffLng);
                            applyRoute(finalPoints);
                        });
                    })
                    .exceptionally(err -> {
                        System.err.println("Customer route fetch failed, fallback to direct line: " + err.getMessage());
                        Platform.runLater(() -> applyRoute(straightLine(pickupLat, pickupLng, dropoffLat, dropoffLng)));
                        return null;
                    });
        }

        private void applyRoute(List<double[]> points) {
            this.routePoints = points;
            double minLat = Math.min(pickupLat, dropoffLat);
            double maxLat = Math.max(pickupLat, dropoffLat);
            double minLon = Math.min(pickupLng, dropoffLng);
            double maxLon = Math.max(pickupLng, dropoffLng);

            for (double[] pt : points) {
                minLat = Math.min(minLat, pt[0]);
                maxLat = Math.max(maxLat, pt[0]);
                minLon = Math.min(minLon, pt[1]);
                maxLon = Math.max(maxLon, pt[1]);
            }

            fitToBounds(minLat, maxLat, minLon, maxLon);
            renderMap();
        }

        private void fitToBounds(double minLat, double maxLat, double minLon, double maxLon) {
            double viewW = Math.max(getWidth(), 300);
            double viewH = Math.max(getHeight(), 300);

            int bestZoom = MIN_ZOOM;
            for (int candidateZoom = MAX_ZOOM; candidateZoom >= MIN_ZOOM; candidateZoom--) {
                double x1 = longitudeToWorldX(minLon, candidateZoom);
                double x2 = longitudeToWorldX(maxLon, candidateZoom);
                double y1 = latitudeToWorldY(minLat, candidateZoom);
                double y2 = latitudeToWorldY(maxLat, candidateZoom);

                double spanX = Math.abs(x2 - x1);
                double spanY = Math.abs(y2 - y1);

                if (spanX <= viewW * 0.65 && spanY <= viewH * 0.65) {
                    bestZoom = candidateZoom;
                    break;
                }
            }

            this.centerLatitude = (minLat + maxLat) / 2.0;
            this.centerLongitude = (minLon + maxLon) / 2.0;
            this.zoom = bestZoom;
        }

        private void renderMap() {
            if (getWidth() < 10 || getHeight() < 10) {
                return;
            }

            final long currentRender = ++renderNumber;

            double centerWorldX = longitudeToWorldX(centerLongitude, zoom);
            double centerWorldY = latitudeToWorldY(centerLatitude, zoom);

            double leftWorld = centerWorldX - getWidth() / 2.0;
            double topWorld = centerWorldY - getHeight() / 2.0;

            tilePane.getChildren().clear();
            routePane.getChildren().clear();
            markerPane.getChildren().clear();

            int firstTileX = (int) Math.floor(leftWorld / TILE_SIZE) - 1;
            int lastTileX = (int) Math.floor((leftWorld + getWidth()) / TILE_SIZE) + 1;
            int firstTileY = (int) Math.floor(topWorld / TILE_SIZE) - 1;
            int lastTileY = (int) Math.floor((topWorld + getHeight()) / TILE_SIZE) + 1;

            int maxTile = (1 << zoom) - 1;

            for (int tileX = firstTileX; tileX <= lastTileX; tileX++) {
                int wrappedX = ((tileX % (maxTile + 1)) + (maxTile + 1)) % (maxTile + 1);

                for (int tileY = firstTileY; tileY <= lastTileY; tileY++) {
                    if (tileY < 0 || tileY > maxTile) {
                        continue;
                    }

                    double imgX = tileX * TILE_SIZE - leftWorld;
                    double imgY = tileY * TILE_SIZE - topWorld;

                    loadTile(zoom, wrappedX, tileY, imgX, imgY, currentRender);
                }
            }

            drawRoute(leftWorld, topWorld);
            drawMarkers(leftWorld, topWorld);
        }

        private void loadTile(int tileZoom, int tileX, int tileY, double imgX, double imgY, long currentRender) {
            String key = tileZoom + "/" + tileX + "/" + tileY;
            Image cached = imageCache.get(key);

            if (cached != null && !cached.isError()) {
                addTileImage(cached, imgX, imgY);
                return;
            }

            String tileUrl = "https://tile.openstreetmap.org/" + tileZoom + "/" + tileX + "/" + tileY + ".png";
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(tileUrl))
                    .header("User-Agent", "BuyNeX-JavaFX-CustomerTracking/1.0")
                    .GET()
                    .build();

            httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofByteArray())
                    .thenApply(resp -> {
                        if (resp.statusCode() != 200) {
                            throw new RuntimeException("OSM tile HTTP " + resp.statusCode());
                        }
                        return new Image(new ByteArrayInputStream(resp.body()));
                    })
                    .thenAccept(img -> {
                        imageCache.put(key, img);
                        Platform.runLater(() -> {
                            if (currentRender != renderNumber) return;
                            addTileImage(img, imgX, imgY);
                        });
                    })
                    .exceptionally(err -> null);
        }

        private void addTileImage(Image image, double x, double y) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(TILE_SIZE);
            imageView.setFitHeight(TILE_SIZE);
            imageView.setPreserveRatio(false);
            imageView.setSmooth(true);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            tilePane.getChildren().add(imageView);
        }

        private void drawRoute(double leftWorld, double topWorld) {
            if (routePoints == null || routePoints.size() < 2) {
                return;
            }

            Polyline outline = new Polyline();
            Polyline line = new Polyline();

            for (double[] pt : routePoints) {
                double worldX = longitudeToWorldX(pt[1], zoom) - leftWorld;
                double worldY = latitudeToWorldY(pt[0], zoom) - topWorld;
                outline.getPoints().addAll(worldX, worldY);
                line.getPoints().addAll(worldX, worldY);
            }

            // Outline glow
            outline.setStroke(Color.web("#B84208", 0.40));
            outline.setStrokeWidth(9);
            outline.setStrokeLineCap(StrokeLineCap.ROUND);
            outline.setStrokeLineJoin(StrokeLineJoin.ROUND);

            // Vibrant route line
            line.setStroke(Color.web("#FF6900"));
            line.setStrokeWidth(5);
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.setStrokeLineJoin(StrokeLineJoin.ROUND);

            routePane.getChildren().addAll(outline, line);
        }

        private void drawMarkers(double leftWorld, double topWorld) {
            // 1. Store Pin
            addLocationPin(pickupLat, pickupLng, leftWorld, topWorld,
                    "#B84208", "🏪", "STORE", pickupName, pickupAddress);

            // 2. Rider Pin (Scooter on the way)
            addLocationPin(riderLat, riderLng, leftWorld, topWorld,
                    "#FF6900", "🛵", "RIDER", riderName, "Delivery Partner in Transit");

            // 3. Customer Destination Pin
            addLocationPin(dropoffLat, dropoffLng, leftWorld, topWorld,
                    "#15803d", "📍", "HOME", dropoffName, dropoffAddress);
        }

        private void addLocationPin(double lat, double lon, double leftWorld, double topWorld,
                                    String pinColor, String icon, String tag, String name, String addr) {
            double worldX = longitudeToWorldX(lon, zoom) - leftWorld;
            double worldY = latitudeToWorldY(lat, zoom) - topWorld;

            VBox pinContainer = new VBox(2);
            pinContainer.setAlignment(Pos.CENTER);

            StackPane pinHead = new StackPane();
            Circle circle = new Circle(14, Color.web(pinColor));
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(2.5);

            Label iconLabel = new Label(icon);
            iconLabel.setStyle("-fx-text-fill: white; -fx-font-size: 11px;");
            pinHead.getChildren().addAll(circle, iconLabel);

            HBox badge = new HBox(4);
            badge.setAlignment(Pos.CENTER);
            badge.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-padding: 3 8 3 8;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: " + pinColor + ";" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 8, 0, 0, 2);"
            );

            Label tagLbl = new Label(tag);
            tagLbl.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: " + pinColor + ";");
            String displayStr = (name != null && name.length() > 18) ? name.substring(0, 16) + "…" : (name != null ? name : "");
            Label nameLbl = new Label(displayStr);
            nameLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #111827;");
            badge.getChildren().addAll(tagLbl, nameLbl);

            Tooltip tip = new Tooltip(tag + ": " + name + "\n" + addr);
            Tooltip.install(pinContainer, tip);

            pinContainer.getChildren().addAll(pinHead, badge);

            pinContainer.setLayoutX(worldX - 60);
            pinContainer.setLayoutY(worldY - 14);
            pinContainer.setPrefWidth(120);

            markerPane.getChildren().add(pinContainer);
        }

        private static double longitudeToWorldX(double longitude, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            return (longitude + 180.0) / 360.0 * mapSize;
        }

        private static double latitudeToWorldY(double latitude, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            double latitudeRadians = Math.toRadians(latitude);
            double y = (1.0 - Math.log(Math.tan(latitudeRadians) + (1.0 / Math.cos(latitudeRadians))) / Math.PI) / 2.0;
            return y * mapSize;
        }

        private static double worldXToLongitude(double worldX, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            return worldX / mapSize * 360.0 - 180.0;
        }

        private static double worldYToLatitude(double worldY, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            double y = 1.0 - (2.0 * worldY / mapSize);
            return Math.toDegrees(Math.atan(Math.sinh(y * Math.PI)));
        }

        private static List<double[]> parseRouteCoordinates(String json) {
            List<double[]> points = new ArrayList<>();
            try {
                int geometryIndex = json.indexOf("\"geometry\"");
                int coordinatesIndex = json.indexOf("\"coordinates\"", Math.max(geometryIndex, 0));
                if (coordinatesIndex < 0) return points;

                int start = json.indexOf("[[", coordinatesIndex);
                int end = json.indexOf("]]", start);
                if (start < 0 || end < 0) return points;

                String coordsBlock = json.substring(start + 2, end);
                String[] pairs = coordsBlock.split("\\],\\s*\\[");
                for (String pair : pairs) {
                    String[] parts = pair.split(",");
                    if (parts.length < 2) continue;
                    double lon = Double.parseDouble(parts[0].trim());
                    double lat = Double.parseDouble(parts[1].trim());
                    points.add(new double[]{lat, lon});
                }
            } catch (Exception ex) {
                System.err.println("OSRM parse error: " + ex.getMessage());
                points.clear();
            }
            return points;
        }

        private static List<double[]> straightLine(double sLat, double sLon, double eLat, double eLon) {
            List<double[]> list = new ArrayList<>();
            list.add(new double[]{sLat, sLon});
            list.add(new double[]{eLat, eLon});
            return list;
        }
    }
}

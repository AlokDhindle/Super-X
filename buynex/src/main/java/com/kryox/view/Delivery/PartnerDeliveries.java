package com.kryox.view.Delivery;


import com.kryox.config.DelivrayFirebaseConfig;

import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartnerDeliveries {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String SIDEBAR_BG = "#ffffff";

    private static ListenerRegistration orderListenerRegistration;

    // =========================================================================
    // DYNAMIC DATA MODELS
    // =========================================================================
    public static class DeliveryQueueData {
        public String partnerName;
        public String partnerTier;
        public int availableRequestsCount = 0;
        public List<OrderSummary> queueOrders = new ArrayList<>();
        public OrderDetail activeOrder;

        public DeliveryQueueData() {
            this.partnerName = PartnerConstants.FULL_NAME;
            this.partnerTier = PartnerConstants.PARTNER_TIER;
            loadDummyOrders();
        }

        public void loadDummyOrders() {
            queueOrders.clear();
            queueOrders.add(new OrderSummary(
                    "BN-4920", "ASSIGNED", "Premium Grocery Haul",
                    "Sarah Jenkins", "1.2 km", "Whole Foods Market",
                    "4920 Central Avenue", true));
            queueOrders.add(new OrderSummary(
                    "BN-5102", "AVAILABLE", "Tech Accessories",
                    "Mark Robertson", "3.8 km", "Digital Horizon Store",
                    "88 Tech Plaza", false));
            queueOrders.add(new OrderSummary(
                    "BN-5088", "AVAILABLE", "Organic Bakery Box",
                    "Elena Glass", "0.6 km", "The Artisan Oven",
                    "12 Baker Street", false));

            activeOrder = new OrderDetail(
                    "BN-4920", "Assigned to you 5 mins ago",
                    "Whole Foods Market", "4920 Central Ave, Plaza District\nFloor 1, North Wing Entrance",
                    "Private Residence", "102 Highland Terrace, Apt 4C\nSecurity Code: 0842",
                    "14 Minutes", 124.50, 8, "PENDING");

            availableRequestsCount = queueOrders.size();
        }
    }

    public static class OrderSummary {
        public String id;
        public String statusBadge;
        public String title;
        public String customerName;
        public String distance;
        public String storeName;
        public String storeAddress;
        public boolean isSelected;

        public OrderSummary(String id, String statusBadge, String title, String customerName, String distance,
                            String storeName, String storeAddress, boolean isSelected) {
            this.id = id;
            this.statusBadge = statusBadge;
            this.title = title;
            this.customerName = customerName;
            this.distance = distance;
            this.storeName = storeName;
            this.storeAddress = storeAddress;
            this.isSelected = isSelected;
        }
    }

    public static class OrderDetail {
        public String id;
        public String assignedTime;
        public String pickupStore;
        public String pickupDetails;
        public String deliveryName;
        public String deliveryDetails;
        public String etaText;
        public double totalValue;
        public int itemCount;
        public String orderStatus;

        public OrderDetail(String id, String assignedTime, String pickupStore, String pickupDetails,
                           String deliveryName, String deliveryDetails, String etaText, double totalValue,
                           int itemCount, String orderStatus) {
            this.id = id;
            this.assignedTime = assignedTime;
            this.pickupStore = pickupStore;
            this.pickupDetails = pickupDetails;
            this.deliveryName = deliveryName;
            this.deliveryDetails = deliveryDetails;
            this.etaText = etaText;
            this.totalValue = totalValue;
            this.itemCount = itemCount;
            this.orderStatus = orderStatus;
        }
    }

    // =========================================================================
    // STATIC SCENE FACTORY METHODS
    // =========================================================================
    public static Scene partnerDeliveriesScene() {
        DeliveryQueueData data = new DeliveryQueueData();
        Scene scene = partnerDeliveriesScene(data);
        attachRealtimeOrderListener(data);
        return scene;
    }

    public static Scene partnerDeliveriesScene(DeliveryQueueData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        root.setTop(createTopHeader());
        root.setLeft(createSidebar(data));

        VBox mainContent = createMainContent(data);
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

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static void attachRealtimeOrderListener(DeliveryQueueData data) {
        try {
            if (orderListenerRegistration != null) {
                orderListenerRegistration.remove();
            }

            Firestore db = DelivrayFirebaseConfig.getFireStore();
            orderListenerRegistration = db.collection("orders").addSnapshotListener((snapshots, error) -> {
                if (error != null) {
                    System.err.println("Firestore Listener Error: " + error.getMessage());
                    return;
                }

                Platform.runLater(() -> {
                    List<OrderSummary> liveOrders = new ArrayList<>();
                    String currentActiveId = (data.activeOrder != null) ? data.activeOrder.id : "";
                    OrderDetail matchedActive = null;

                    if (snapshots != null && !snapshots.isEmpty()) {
                        for (DocumentSnapshot doc : snapshots.getDocuments()) {
                            String status = doc.getString("status");
                            if (status == null) status = "PLACED";

                            String partnerId = doc.getString("deliveryPartnerId");
                            boolean isForMe = partnerId == null || partnerId.isEmpty()
                                    || (PartnerConstants.UID != null && partnerId.equals(PartnerConstants.UID));

                            if (!"DELIVERED".equalsIgnoreCase(status) && !"CANCELLED".equalsIgnoreCase(status) && isForMe) {
                                String orderId = doc.getId();
                                String title = doc.getString("orderTitle") != null ? doc.getString("orderTitle") : "Express Package";
                                String customerName = doc.getString("customerName") != null ? doc.getString("customerName") : "Customer";
                                String distance = doc.getString("distance") != null ? doc.getString("distance") : "1.8 km";
                                String shopName = doc.getString("shopName") != null ? doc.getString("shopName") : "Local Store";
                                String shopAddress = doc.getString("shopAddress") != null ? doc.getString("shopAddress") : "Pune, Maharashtra";
                                String custAddress = doc.getString("customerAddress") != null ? doc.getString("customerAddress") : "Pune, Maharashtra";

                                double totalAmount = 150.00;
                                if (doc.get("totalAmount") != null) {
                                    try {
                                        totalAmount = Double.parseDouble(doc.get("totalAmount").toString());
                                    } catch (Exception ignored) {}
                                }

                                int itemsCount = 3;
                                if (doc.get("itemCount") != null) {
                                    try {
                                        itemsCount = Integer.parseInt(doc.get("itemCount").toString());
                                    } catch (Exception ignored) {}
                                }

                                boolean isSelected = orderId.equals(currentActiveId) || (liveOrders.isEmpty() && currentActiveId.isEmpty());

                                OrderSummary summary = new OrderSummary(
                                        orderId, status, title, customerName, distance, shopName, shopAddress, isSelected
                                );
                                liveOrders.add(summary);

                                if (isSelected) {
                                    matchedActive = new OrderDetail(
                                            orderId, "Received just now", shopName, shopAddress,
                                            customerName + " (Residence)", custAddress,
                                            "12 Minutes", totalAmount, itemsCount, status
                                    );
                                }
                            }
                        }
                    }

                    if (!liveOrders.isEmpty()) {
                        data.queueOrders = liveOrders;
                        data.availableRequestsCount = liveOrders.size();
                        if (matchedActive != null) {
                            data.activeOrder = matchedActive;
                        } else {
                            OrderSummary first = liveOrders.get(0);
                            first.isSelected = true;
                            data.activeOrder = new OrderDetail(
                                    first.id, "Received just now", first.storeName, first.storeAddress,
                                    first.customerName + " (Residence)", "Delivery Destination",
                                    "12 Minutes", 150.00, 3, first.statusBadge
                            );
                        }
                    } else {
                        data.loadDummyOrders();
                    }

                    if (Homepage.HomepageStage != null) {
                        Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
                    }
                });
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static BorderPane createTopHeader() {
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
        notifIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNotifications.partnerNotificationsScene("DELIVERIES"));
            }
        });

        Label chatIcon = new Label("💬");
        chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        chatIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerChatSupport.partnerChatSupportScene("DELIVERIES"));
            }
        });

        Label helpIcon = new Label("❓");
        helpIcon.setStyle("-fx-font-size: 14px; -fx-cursor: hand;");
        helpIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(DeliverySupport.supportScene("DELIVERIES"));
            }
        });

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
        itemProfile.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
            }
        });

        MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
        itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        MenuItem itemLogout = new MenuItem("↪   Logout");
        itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemLogout.setOnAction(e -> {
            if (orderListenerRegistration != null) orderListenerRegistration.remove();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

        userAvatarPane.setOnMouseClicked(e -> {
            if (!userMenu.isShowing()) {
                userMenu.show(userAvatarPane, javafx.geometry.Side.BOTTOM, -120, 8);
            } else {
                userMenu.hide();
            }
        });

        rightIcons.getChildren().addAll(notifIcon, chatIcon, helpIcon, userAvatarPane);
        topBar.setRight(rightIcons);

        return topBar;
    }

    private static VBox createSidebar(DeliveryQueueData data) {
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

        Button btnDashboard = createNavButton("▤   Dashboard", false);
        btnDashboard.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
            }
        });

        Button btnDeliveries = createNavButton("📦   My Deliveries", true);
        btnDeliveries.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene(data));
            }
        });

        Button btnNavigation = createNavButton("🧭   Navigation", false);
        btnNavigation.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
            }
        });

        Button btnEarnings = createNavButton("💵   Earnings", false);
        btnEarnings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
            }
        });

        Button btnAvailability = createNavButton("⏱   Availability", false);
        btnAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

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
        profileCard.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerProfile.partnerProfileScene());
            }
        });

        Button btnSettings = createNavButton("⚙   Settings", false);
        btnSettings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
            }
        });

        Button btnLogout = new Button("↪   Logout");
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setAlignment(Pos.CENTER_LEFT);
        btnLogout.setPrefHeight(34);
        btnLogout.setStyle(
                "-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
        btnLogout.setOnAction(e -> {
            if (orderListenerRegistration != null) orderListenerRegistration.remove();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        VBox bottomNav = new VBox(6, profileCard, btnSettings, btnLogout);
        sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
        return sidebar;
    }

    private static VBox createMainContent(DeliveryQueueData data) {
        VBox main = new VBox(20);
        main.setPadding(new Insets(24, 30, 40, 30));
        main.setFillWidth(true);

        BorderPane headerRow = new BorderPane();
        VBox titleBox = new VBox(2);
        Text title = new Text("Active Queue");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #111827;");
        Label subTitle = new Label(data.availableRequestsCount + " Requests Available");
        subTitle.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);
        headerRow.setLeft(titleBox);

        HBox filterPills = new HBox(8);
        filterPills.setAlignment(Pos.CENTER_RIGHT);

        Button btnNearMe = new Button("Near Me");
        btnNearMe.setStyle(
                "-fx-background-color:linear-gradient(to right, #B84208, #F36A00); -fx-text-fill: #ffffff; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 5 14 5 14; -fx-cursor: hand;");

        Button btnHighValue = new Button("High Value");
        btnHighValue.setStyle(
                "-fx-background-color:linear-gradient(to right, #B84208, #F36A00); -fx-text-fill: #ffffff; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 5 14 5 14; -fx-cursor: hand;");

        btnNearMe.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(NearbyDeliveries.nearbyDeliveriesScene());
            }
        });
        btnHighValue.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(HighValueDeliveries.highValueDeliveriesScene());
            }
        });

        filterPills.getChildren().addAll(btnNearMe, btnHighValue);
        headerRow.setRight(filterPills);

        HBox bodySplit = new HBox(22);
        bodySplit.setFillHeight(true);

        VBox queueCol = new VBox(14);
        queueCol.setPrefWidth(360);
        queueCol.setMinWidth(360);
        queueCol.setMaxWidth(360);

        for (OrderSummary order : data.queueOrders) {
            queueCol.getChildren().add(createQueueOrderCard(data, order));
        }

        VBox detailsCol = new VBox(16);
        HBox.setHgrow(detailsCol, Priority.ALWAYS);

        if (data.activeOrder != null) {
            detailsCol.getChildren().addAll(
                    createMapSnapshotCard(data.activeOrder),
                    createOrderDetailsCard(data, data.activeOrder));
        }

        bodySplit.getChildren().addAll(queueCol, detailsCol);
        main.getChildren().addAll(headerRow, bodySplit);
        return main;
    }

    private static VBox createQueueOrderCard(DeliveryQueueData data, OrderSummary order) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(14));
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: " + (order.isSelected ? ORANGE_PRIMARY : BORDER_COLOR) + ";" +
                        "-fx-border-width: " + (order.isSelected ? "1.5" : "1") + ";" +
                        "-fx-cursor: hand;");

        BorderPane topRow = new BorderPane();
        Label badge = new Label(order.statusBadge);
        if ("ACCEPTED".equalsIgnoreCase(order.statusBadge)) {
            badge.setStyle(
                    "-fx-background-color: #dcfce7; -fx-text-fill: #15803d; -fx-font-size: 8px; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        } else if ("ASSIGNED".equalsIgnoreCase(order.statusBadge) || "PLACED".equalsIgnoreCase(order.statusBadge)) {
            badge.setStyle(
                    "-fx-background-color: #ffedd5; -fx-text-fill: #c2410c; -fx-font-size: 8px; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        } else if ("REJECTED".equalsIgnoreCase(order.statusBadge)) {
            badge.setStyle(
                    "-fx-background-color: #fee2e2; -fx-text-fill: #b91c1c; -fx-font-size: 8px; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        } else {
            badge.setStyle(
                    "-fx-background-color: #f3f4f6; -fx-text-fill: #4b5563; -fx-font-size: 8px; -fx-font-weight: bold; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        }

        Label idLbl = new Label("ID: #" + (order.id.length() > 6 ? order.id.substring(0, 6).toUpperCase() : order.id));
        idLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");
        topRow.setLeft(badge);
        topRow.setRight(idLbl);

        VBox titleBox = new VBox(2);
        Label titleLbl = new Label(order.title);
        titleLbl.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label custLbl = new Label("👤   " + order.customerName + "   •   " + order.distance);
        custLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(titleLbl, custLbl);

        HBox storeBox = new HBox(8);
        storeBox.setPadding(new Insets(6, 8, 6, 8));
        storeBox.setAlignment(Pos.CENTER_LEFT);
        storeBox.setStyle("-fx-background-color: #f9fafb; -fx-background-radius: 6;");

        Label storeIcon = new Label("🏪");
        storeIcon.setStyle("-fx-font-size: 10px;");

        VBox storeMeta = new VBox(1);
        Label storeName = new Label(order.storeName);
        storeName.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        Label storeAddr = new Label(order.storeAddress);
        storeAddr.setStyle("-fx-font-size: 9px; -fx-text-fill: #9ca3af;");
        storeMeta.getChildren().addAll(storeName, storeAddr);

        storeBox.getChildren().addAll(storeIcon, storeMeta);
        card.getChildren().addAll(topRow, titleBox, storeBox);

        card.setOnMouseClicked(e -> {
            for (OrderSummary os : data.queueOrders) {
                os.isSelected = false;
            }
            order.isSelected = true;

            data.activeOrder = new OrderDetail(
                    order.id, "Assigned to you",
                    order.storeName, order.storeAddress,
                    order.customerName + " (Residence)", "Selected Delivery Destination",
                    "12 Minutes", 150.00, 3, order.statusBadge);

            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
            }
        });

        return card;
    }

    private static StackPane createMapSnapshotCard(OrderDetail active) {
        StackPane mapCard = new StackPane();
        mapCard.setPrefHeight(150);
        mapCard.setMinHeight(150);
        mapCard.setMaxHeight(150);
        mapCard.setStyle("-fx-background-color: #dce3e8; -fx-background-radius: 12; -fx-border-radius: 12;");

        ImageView mapView = new ImageView();
        mapView.fitWidthProperty().bind(mapCard.widthProperty());
        mapView.setFitHeight(150);
        mapView.setPreserveRatio(false);

        try {
            Image img = new Image(
                    "https://staticmap.openstreetmap.de/staticmap.php?center=18.5204,73.8567&zoom=13&size=700x150&maptype=mapnik",
                    true);
            mapView.setImage(img);
        } catch (Exception ignored) {
        }

        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(mapCard.widthProperty());
        clip.setHeight(150);
        clip.setArcWidth(24);
        clip.setArcHeight(24);
        mapCard.setClip(clip);

        mapCard.getChildren().add(mapView);

        BorderPane hud = new BorderPane();
        hud.setMaxHeight(46);
        hud.setPadding(new Insets(6, 14, 6, 14));
        hud.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.95);" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);");

        HBox etaBox = new HBox(8);
        etaBox.setAlignment(Pos.CENTER_LEFT);
        Label carIcon = new Label("🚗");
        carIcon.setStyle("-fx-font-size: 13px; -fx-text-fill: " + ORANGE_PRIMARY + ";");

        VBox etaMeta = new VBox(1);
        Label etaTitle = new Label("ETA TO DESTINATION");
        etaTitle.setStyle("-fx-font-size: 8px; -fx-text-fill: #9ca3af; -fx-font-weight: bold;");
        Label etaVal = new Label(active.etaText);
        etaVal.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        etaMeta.getChildren().addAll(etaTitle, etaVal);
        etaBox.getChildren().addAll(carIcon, etaMeta);
        hud.setLeft(etaBox);

        Button btnOpenMaps = new Button("Open Maps");
        btnOpenMaps.setPrefHeight(30);
        btnOpenMaps.setStyle(
                "-fx-background-color: #1e293b;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-padding: 0 12 0 12;" +
                        "-fx-cursor: hand;");
        btnOpenMaps.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
            }
        });
        hud.setRight(btnOpenMaps);
        BorderPane.setAlignment(btnOpenMaps, Pos.CENTER_RIGHT);

        StackPane.setAlignment(hud, Pos.BOTTOM_CENTER);
        StackPane.setMargin(hud, new Insets(0, 12, 12, 12));
        mapCard.getChildren().add(hud);

        return mapCard;
    }

    private static VBox createOrderDetailsCard(DeliveryQueueData data, OrderDetail active) {
        VBox card = createCard();
        card.setPadding(new Insets(18));

        BorderPane header = new BorderPane();
        VBox titleBox = new VBox(2);
        Label title = new Label("Order Details");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label time = new Label(active.assignedTime);
        time.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(title, time);
        header.setLeft(titleBox);

        HBox btnActions = new HBox(8);
        btnActions.setAlignment(Pos.CENTER_RIGHT);

        boolean isAccepted = "ACCEPTED".equalsIgnoreCase(active.orderStatus);
        boolean isRejected = "REJECTED".equalsIgnoreCase(active.orderStatus);

        if (isAccepted) {
            Button btnStartNav = new Button("Start Navigation   ➤");
            btnStartNav.setPrefHeight(32);
            btnStartNav.setStyle(
                    "-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 0 18 0 18; -fx-cursor: hand;");
            btnStartNav.setOnAction(e -> {
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
                }
            });
            btnActions.getChildren().add(btnStartNav);
        } else if (isRejected) {
            Label rejectedLbl = new Label("Order Declined");
            rejectedLbl.setStyle(
                    "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #dc2626; -fx-background-color: #fee2e2; -fx-padding: 6 12 6 12; -fx-background-radius: 6;");
            btnActions.getChildren().add(rejectedLbl);
        } else {
            Button btnReject = new Button("Reject");
            btnReject.setPrefHeight(32);
            btnReject.setStyle(
                    "-fx-background-color: white; -fx-border-color: #d1d5db; -fx-border-radius: 6; -fx-background-radius: 6; -fx-text-fill: #374151; -fx-font-size: 11px; -fx-font-weight: bold; -fx-padding: 0 14 0 14; -fx-cursor: hand;");

            Button btnAccept = new Button("Accept");
            btnAccept.setPrefHeight(32);
            btnAccept.setStyle(
                    "-fx-background-color: " + ORANGE_GRADIENT
                            + "; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 6; -fx-padding: 0 18 0 18; -fx-cursor: hand;");

            btnAccept.setOnAction(e -> {
                active.orderStatus = "ACCEPTED";
                new Thread(() -> {
                    try {
                        Firestore db = DelivrayFirebaseConfig.getFireStore();
                        Map<String, Object> update = new HashMap<>();
                        update.put("status", "ACCEPTED");
                        update.put("deliveryPartnerId", PartnerConstants.UID);
                        db.collection("orders").document(active.id).update(update).get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();

                for (OrderSummary os : data.queueOrders) {
                    if (os.id.equalsIgnoreCase(active.id)) {
                        os.statusBadge = "ACCEPTED";
                        break;
                    }
                }
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
                }
            });

            btnReject.setOnAction(e -> {
                active.orderStatus = "REJECTED";
                new Thread(() -> {
                    try {
                        Firestore db = DelivrayFirebaseConfig.getFireStore();
                        db.collection("orders").document(active.id).update("status", "REJECTED").get();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();

                for (OrderSummary os : data.queueOrders) {
                    if (os.id.equalsIgnoreCase(active.id)) {
                        os.statusBadge = "REJECTED";
                        break;
                    }
                }
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
                }
            });

            btnActions.getChildren().addAll(btnReject, btnAccept);
        }

        header.setRight(btnActions);

        HBox bodyRow = new HBox(20);
        bodyRow.setPadding(new Insets(10, 0, 10, 0));

        VBox locCol = new VBox(12);
        locCol.getChildren().addAll(
                createLocationBlock("PICKUP POINT", active.pickupStore, active.pickupDetails, "#93380b"),
                createLocationBlock("DELIVERY POINT", active.deliveryName, active.deliveryDetails, ORANGE_PRIMARY));
        HBox.setHgrow(locCol, Priority.ALWAYS);

        VBox timelineCol = new VBox(6);
        timelineCol.setPrefWidth(180);
        timelineCol.setMinWidth(180);

        Label tlHeader = new Label("TIMELINE");
        tlHeader.setStyle("-fx-font-size: 9px; -fx-text-fill: #9ca3af; -fx-font-weight: bold;");

        timelineCol.getChildren().addAll(
                tlHeader,
                createTimelineStep("Order Received", "10:45 AM", true),
                createTimelineStep("Preparing Order", "Expected 10:58 AM", isAccepted),
                createTimelineStep("Picked Up", isAccepted ? "Next Step" : "Pending", false),
                createTimelineStep("Delivered", "Estimated 11:20 AM", false));

        bodyRow.getChildren().addAll(locCol, timelineCol);

        VBox pkgBox = new VBox(8);
        pkgBox.setPadding(new Insets(10, 0, 0, 0));
        pkgBox.setStyle("-fx-border-color: #f3f4f6; -fx-border-width: 1 0 0 0;");

        BorderPane pkgMeta = new BorderPane();
        Label pkgTitle = new Label("Package Content (" + active.itemCount + " Items)");
        pkgTitle.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label pkgVal = new Label("₹" + String.format("%.2f", active.totalValue) + " Total Value");
        pkgVal.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        pkgMeta.setLeft(pkgTitle);
        pkgMeta.setRight(pkgVal);

        HBox thumbs = new HBox(8);
        thumbs.setAlignment(Pos.CENTER_LEFT);
        thumbs.getChildren().addAll(
                createThumbPill("🍞"),
                createThumbPill("🥛"),
                createThumbPill("🍎"),
                createMorePill("+5"));

        pkgBox.getChildren().addAll(pkgMeta, thumbs);

        card.getChildren().addAll(header, bodyRow, pkgBox);
        return card;
    }

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

    private static VBox createLocationBlock(String tag, String title, String details, String color) {
        VBox box = new VBox(2);
        HBox tagRow = new HBox(4);
        tagRow.setAlignment(Pos.CENTER_LEFT);
        Circle dot = new Circle(3, Color.web(color));
        Label tagLbl = new Label(tag);
        tagLbl.setStyle("-fx-font-size: 8px; -fx-text-fill: #9ca3af; -fx-font-weight: bold;");
        tagRow.getChildren().addAll(dot, tagLbl);

        Label titleLbl = new Label(title);
        titleLbl.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label detLbl = new Label(details);
        detLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");

        box.getChildren().addAll(tagRow, titleLbl, detLbl);
        return box;
    }

    private static HBox createTimelineStep(String label, String time, boolean isDone) {
        HBox step = new HBox(8);
        step.setAlignment(Pos.CENTER_LEFT);

        Circle indicator = new Circle(3.5, isDone ? Color.web(ORANGE_PRIMARY) : Color.web("#d1d5db"));
        VBox meta = new VBox(0);
        Label lbl = new Label(label);
        lbl.setStyle(
                "-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: " + (isDone ? "#111827;" : "#9ca3af;"));
        Label t = new Label(time);
        t.setStyle("-fx-font-size: 8px; -fx-text-fill: #9ca3af;");
        meta.getChildren().addAll(lbl, t);

        step.getChildren().addAll(indicator, meta);
        return step;
    }

    private static StackPane createThumbPill(String emoji) {
        StackPane p = new StackPane();
        p.setPrefSize(34, 34);
        p.setStyle(
                "-fx-background-color: #f9fafb; -fx-border-color: #e5e7eb; -fx-border-radius: 6; -fx-background-radius: 6;");
        Label l = new Label(emoji);
        l.setStyle("-fx-font-size: 14px;");
        p.getChildren().add(l);
        return p;
    }

    private static StackPane createMorePill(String text) {
        StackPane p = new StackPane();
        p.setPrefSize(34, 34);
        p.setStyle("-fx-background-color: #f3f4f6; -fx-border-radius: 6; -fx-background-radius: 6;");
        Label l = new Label(text);
        l.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #6b7280;");
        p.getChildren().add(l);
        return p;
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
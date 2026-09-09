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
    private static final String BG_COLOR = "#EEE5DE";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String SIDEBAR_BG = "#EBCCB7";

    private static ListenerRegistration orderListenerRegistration;

    public static class DeliveryQueueData {
        public String partnerName;
        public String partnerTier;
        public int availableRequestsCount = 0;
        public String activeFilter = "ALL";
        public List<OrderSummary> queueOrders = new ArrayList<>();
        public OrderDetail activeOrder;

        public DeliveryQueueData() {
            this.partnerName = PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.isBlank() ? PartnerConstants.FULL_NAME : "Rahul Sharma (Rider)";
            this.partnerTier = PartnerConstants.PARTNER_TIER != null && !PartnerConstants.PARTNER_TIER.isBlank() ? PartnerConstants.PARTNER_TIER : "Standard Partner";
            loadFirestoreOrders();
        }

        public void loadFirestoreOrders() {
            queueOrders.clear();
            activeOrder = null;
            try {
                var firestoreRequests = com.kryox.controller.Shopkeeper.OrderController.getDeliveryRequests();
                if (firestoreRequests != null && !firestoreRequests.isEmpty()) {
                    boolean first = true;
                    for (com.kryox.model.Shopkeeper.OrderModel om : firestoreRequests) {
                        String st = om.getOrderStatus();
                        if (st == null || (!"REQUESTING_DELIVERY".equalsIgnoreCase(st) && !"OUT_FOR_DELIVERY".equalsIgnoreCase(st) && !"ACCEPTED".equalsIgnoreCase(st))) {
                            continue;
                        }

                        String oid = om.getOrderId() != null ? om.getOrderId() : "BN-1001";
                        String statusBadge = "REQUESTING_DELIVERY".equalsIgnoreCase(st) ? "REQUESTED" : ("OUT_FOR_DELIVERY".equalsIgnoreCase(st) ? "ASSIGNED" : "ACCEPTED");

                        String title = "Package (" + (om.getProducts() != null ? om.getProducts().size() : 1) + " Items)";
                        if (om.getProducts() != null && !om.getProducts().isEmpty() && om.getProducts().get(0) != null) {
                            String pName = om.getProducts().get(0).getProductName();
                            if (pName != null && !pName.isBlank()) {
                                title = pName;
                            }
                        }

                        String cName = om.getCustomerName() != null && !om.getCustomerName().isBlank() ? om.getCustomerName() : "Customer";
                        String sName = om.getShopName() != null && !om.getShopName().isBlank() ? om.getShopName() : ("Shopkeeper #" + (om.getShopkeeperUid() != null ? om.getShopkeeperUid() : "Default"));
                        String sAddr = String.format("Lat %.4f, Lng %.4f • 1.5km Zone", om.getShopLat(), om.getShopLng());
                        String cAddr = om.getCustomerPhone() != null && !om.getCustomerPhone().isBlank() ? ("Phone: " + om.getCustomerPhone() + " • Customer Residence") : "Customer Home Address";
                        String dist = (om.getRiderDistance() != null && !om.getRiderDistance().isBlank()) ? om.getRiderDistance() : "0.8 km";

                        OrderSummary summary = new OrderSummary(oid, statusBadge, title, cName, dist, sName, sAddr, first);
                        summary.rawOrder = om;
                        queueOrders.add(summary);

                        if (first) {
                            activeOrder = new OrderDetail(
                                oid, "Received recently", sName, sAddr + "\nShopkeeper Pickup Location",
                                cName + " (Residence)", cAddr, "14 Minutes", om.getTotalAmount() > 0 ? om.getTotalAmount() : 150.00,
                                om.getProducts() != null ? om.getProducts().size() : 1, st
                            );
                            activeOrder.rawOrder = om;
                            first = false;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

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
        public com.kryox.model.Shopkeeper.OrderModel rawOrder;

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
        public com.kryox.model.Shopkeeper.OrderModel rawOrder;

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

        Scene scene = new Scene(root, 1550, 850);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }    private static void attachRealtimeOrderListener(DeliveryQueueData data) {
        try {
            if (orderListenerRegistration != null) {
                orderListenerRegistration.remove();
            }

            Firestore db = com.kryox.config.Firebaseconfig.gFirestore();
            orderListenerRegistration = db.collection("Orders").addSnapshotListener((snapshots, error) -> {
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
                            try {
                                com.kryox.model.Shopkeeper.OrderModel om = doc.toObject(com.kryox.model.Shopkeeper.OrderModel.class);
                                if (om == null) om = new com.kryox.model.Shopkeeper.OrderModel();
                                om.setOrderId(doc.getId());

                                String status = om.getOrderStatus() != null ? om.getOrderStatus() : doc.getString("status");
                                if (status == null) status = "NEW";

                                if ("REQUESTING_DELIVERY".equalsIgnoreCase(status) || "OUT_FOR_DELIVERY".equalsIgnoreCase(status) || "ACCEPTED".equalsIgnoreCase(status)) {
                                    String orderId = doc.getId();
                                    String title = "Express Package";
                                    if (om.getProducts() != null && !om.getProducts().isEmpty() && om.getProducts().get(0) != null) {
                                        title = om.getProducts().get(0).getProductName();
                                    } else if (doc.getString("orderTitle") != null) {
                                        title = doc.getString("orderTitle");
                                    }

                                    String customerName = om.getCustomerName() != null && !om.getCustomerName().isBlank() ? om.getCustomerName() : (doc.getString("customerName") != null ? doc.getString("customerName") : "Customer");
                                    String distance = om.getRiderDistance() != null && !om.getRiderDistance().isBlank() ? om.getRiderDistance() : (doc.getString("distance") != null ? doc.getString("distance") : "0.8 km");
                                    String shopName = om.getShopName() != null && !om.getShopName().isBlank() ? om.getShopName() : (doc.getString("shopName") != null ? doc.getString("shopName") : "Local Store");
                                    String shopAddress = String.format("Lat %.4f, Lng %.4f • 1.5km Zone", om.getShopLat(), om.getShopLng());
                                    String custAddress = om.getCustomerPhone() != null && !om.getCustomerPhone().isBlank() ? ("Phone: " + om.getCustomerPhone()) : "Customer Delivery Location";

                                    double totalAmount = om.getTotalAmount() > 0 ? om.getTotalAmount() : 150.00;
                                    int itemsCount = om.getProducts() != null ? om.getProducts().size() : 1;
                                    String statusBadge = "REQUESTING_DELIVERY".equalsIgnoreCase(status) ? "REQUESTED" : ("OUT_FOR_DELIVERY".equalsIgnoreCase(status) ? "ASSIGNED" : ("ACCEPTED".equalsIgnoreCase(status) ? "ACCEPTED" : "AVAILABLE"));

                                    boolean isSelected = orderId.equals(currentActiveId) || (liveOrders.isEmpty() && currentActiveId.isEmpty());

                                    OrderSummary summary = new OrderSummary(
                                            orderId, statusBadge, title, customerName, distance, shopName, shopAddress, isSelected
                                    );
                                    summary.rawOrder = om;
                                    liveOrders.add(summary);

                                    if (isSelected) {
                                        matchedActive = new OrderDetail(
                                                orderId, "Received just now", shopName, shopAddress,
                                                customerName + " (Residence)", custAddress,
                                                "12 Minutes", totalAmount, itemsCount, status
                                        );
                                        matchedActive.rawOrder = om;
                                    }
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
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
                                    "12 Minutes", first.rawOrder != null && first.rawOrder.getTotalAmount() > 0 ? first.rawOrder.getTotalAmount() : 150.00,
                                    first.rawOrder != null && first.rawOrder.getProducts() != null ? first.rawOrder.getProducts().size() : 3,
                                    first.statusBadge
                            );
                            data.activeOrder.rawOrder = first.rawOrder;
                        }

                        if (Homepage.HomepageStage != null) {
                            Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
                        }
                    } else {
                        data.queueOrders.clear();
                        data.availableRequestsCount = 0;
                        data.activeOrder = null;
                        if (Homepage.HomepageStage != null) {
                            Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
                        }
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
                "-fx-background-color: #EBCCB7;" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-padding: 0 35 0 30;");

        Text title = new Text("My Deliveries");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #a94717;");
        HBox leftGroup = new HBox(title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        leftGroup.setStyle("-fx-background-color: #EBCCB7;");
        topBar.setLeft(leftGroup);

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

        List<OrderSummary> filteredOrders = new ArrayList<>(data.queueOrders);

        // Ensure activeOrder is synced with filteredOrders
        boolean activeFound = false;
        if (data.activeOrder != null) {
            for (OrderSummary os : filteredOrders) {
                if (os.id.equalsIgnoreCase(data.activeOrder.id)) {
                    activeFound = true;
                    os.isSelected = true;
                } else {
                    os.isSelected = false;
                }
            }
        }
        if (!activeFound && !filteredOrders.isEmpty()) {
            OrderSummary first = filteredOrders.get(0);
            for (OrderSummary os : filteredOrders) {
                os.isSelected = os == first;
            }
            data.activeOrder = new OrderDetail(
                    first.id, "Assigned to you",
                    first.storeName, first.storeAddress,
                    first.customerName + " (Residence)", "Selected Delivery Destination",
                    "12 Minutes", first.rawOrder != null && first.rawOrder.getTotalAmount() > 0 ? first.rawOrder.getTotalAmount() : 150.00,
                    first.rawOrder != null && first.rawOrder.getProducts() != null ? first.rawOrder.getProducts().size() : 3,
                    first.statusBadge);
            data.activeOrder.rawOrder = first.rawOrder;
        } else if (filteredOrders.isEmpty()) {
            data.activeOrder = null;
        }

        BorderPane headerRow = new BorderPane();
        VBox titleBox = new VBox(2);
        Text title = new Text("Active Queue");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #111827;");

        Label subTitle = new Label(filteredOrders.size() + " Requests Available");
        subTitle.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);
        headerRow.setLeft(titleBox);

        HBox filterPills = new HBox(8);
        filterPills.setAlignment(Pos.CENTER_RIGHT);

        String activeStyle = "-fx-background-color:linear-gradient(to right, #B84208, #F36A00); -fx-text-fill: #ffffff; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-padding: 5 14 5 14; -fx-cursor: hand;";

        Button btnAll = new Button("All");
        btnAll.setStyle(activeStyle);

        btnAll.setOnAction(e -> {
            data.activeFilter = "ALL";
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerDeliveriesScene(data));
            }
        });

        filterPills.getChildren().add(btnAll);
        headerRow.setRight(filterPills);

        HBox bodySplit = new HBox(22);
        bodySplit.setFillHeight(true);

        VBox queueCol = new VBox(14);
        queueCol.setPrefWidth(360);
        queueCol.setMinWidth(360);
        queueCol.setMaxWidth(360);

        if (filteredOrders.isEmpty()) {
            VBox emptyBox = new VBox(10);
            emptyBox.setAlignment(Pos.CENTER);
            emptyBox.setPadding(new Insets(30, 16, 30, 16));
            emptyBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: " + BORDER_COLOR + "; -fx-border-radius: 10;");

            Label emptyIcon = new Label("📦");
            emptyIcon.setStyle("-fx-font-size: 26px;");

            Label emptyText = new Label("No Requested Orders");
            emptyText.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #374151;");

            Label emptySub = new Label("Only orders broadcasted by shopkeepers with 'REQUESTING_DELIVERY' status will appear here to accept.");
            emptySub.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af; -fx-text-alignment: center;");
            emptySub.setWrapText(true);

            emptyBox.getChildren().addAll(emptyIcon, emptyText, emptySub);
            queueCol.getChildren().add(emptyBox);
        } else {
            for (OrderSummary order : filteredOrders) {
                queueCol.getChildren().add(createQueueOrderCard(data, order));
            }
        }

        VBox detailsCol = new VBox(16);
        HBox.setHgrow(detailsCol, Priority.ALWAYS);

        if (data.activeOrder != null) {
            detailsCol.getChildren().addAll(
                    createMapSnapshotCard(data.activeOrder),
                    createOrderDetailsCard(data, data.activeOrder));
        } else {
            VBox emptyDetails = new VBox(12);
            emptyDetails.setAlignment(Pos.CENTER);
            emptyDetails.setPadding(new Insets(60, 20, 60, 20));
            emptyDetails.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: " + BORDER_COLOR + ";");

            Label pIcon = new Label("📑");
            pIcon.setStyle("-fx-font-size: 32px;");

            Label pText = new Label("No Active Order Selected");
            pText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #6b7280;");

            emptyDetails.getChildren().addAll(pIcon, pText);
            detailsCol.getChildren().add(emptyDetails);
        }

        bodySplit.getChildren().addAll(queueCol, detailsCol);
        main.getChildren().addAll(headerRow, bodySplit);
        return main;
    }

    private static double parseDistanceKm(String distStr) {
        if (distStr == null || distStr.isBlank()) return 1.0;
        try {
            String cleaned = distStr.replaceAll("[^0-9.]", " ").trim();
            String[] parts = cleaned.split("\\s+");
            if (parts.length > 0 && !parts[0].isEmpty()) {
                return Double.parseDouble(parts[0]);
            }
        } catch (Exception ignored) {}
        return 1.0;
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

            var om = order.rawOrder;
            String cAddr = (om != null && om.getCustomerPhone() != null && !om.getCustomerPhone().isBlank()) ? ("Phone: " + om.getCustomerPhone()) : "Selected Delivery Destination";
            double amt = (om != null && om.getTotalAmount() > 0) ? om.getTotalAmount() : 150.00;
            int itemsCount = (om != null && om.getProducts() != null && !om.getProducts().isEmpty()) ? om.getProducts().size() : 1;

            data.activeOrder = new OrderDetail(
                    order.id, "Received recently",
                    order.storeName, order.storeAddress,
                    order.customerName + " (Residence)", cAddr,
                    "12 Minutes", amt, itemsCount, order.statusBadge);
            data.activeOrder.rawOrder = om;

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
                PartnerNavigation.TripData trip = new PartnerNavigation.TripData();
                trip.orderNumber = "Order #" + (active.id != null ? active.id : "BN-1001");
                trip.pickupName = active.pickupStore != null ? active.pickupStore : "Local Store";
                trip.pickupAddress = active.pickupDetails != null ? active.pickupDetails : "Shopkeeper Location";
                trip.dropoffName = active.deliveryName != null ? active.deliveryName : "Customer Residence";
                trip.dropoffAddress = active.deliveryDetails != null ? active.deliveryDetails : "Customer Destination";
                trip.orderEarnings = active.totalValue > 0 ? active.totalValue : 150.00;
                Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene(trip));
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
                        if (active.rawOrder != null) {
                            com.kryox.controller.Shopkeeper.OrderController.assignDeliveryPartner(
                                active.rawOrder,
                                data.partnerName != null ? data.partnerName : "Rahul Sharma (Rider)",
                                "+91 98765 43210",
                                "0.8 km (1.5km radius)"
                            );
                        } else {
                            com.kryox.model.Shopkeeper.OrderModel om = new com.kryox.model.Shopkeeper.OrderModel();
                            om.setOrderId(active.id);
                            com.kryox.controller.Shopkeeper.OrderController.assignDeliveryPartner(
                                om,
                                data.partnerName != null ? data.partnerName : "Rahul Sharma (Rider)",
                                "+91 98765 43210",
                                "0.8 km (1.5km radius)"
                            );
                        }
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

        if (active.rawOrder != null && active.rawOrder.getProducts() != null && !active.rawOrder.getProducts().isEmpty()) {
            var items = active.rawOrder.getProducts();
            int limit = Math.min(items.size(), 4);
            for (int i = 0; i < limit; i++) {
                var it = items.get(i);
                if (it != null) {
                    String pName = it.getProductName() != null && !it.getProductName().isBlank() ? it.getProductName() : "Product";
                    int qty = it.getQuantity() > 0 ? it.getQuantity() : 1;
                    thumbs.getChildren().add(createProductPill(qty, pName, it.getImageUrl()));
                }
            }
            if (items.size() > 4) {
                thumbs.getChildren().add(createMorePill("+" + (items.size() - 4)));
            }
        } else {
            thumbs.getChildren().add(createProductPill(1, "Items Package", null));
        }

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

    private static HBox createProductPill(int qty, String name, String imgUrl) {
        HBox p = new HBox(6);
        p.setAlignment(Pos.CENTER_LEFT);
        p.setPadding(new Insets(4, 10, 4, 10));
        p.setStyle("-fx-background-color: #f9fafb; -fx-border-color: #e5e7eb; -fx-border-radius: 8; -fx-background-radius: 8;");

        if (imgUrl != null && !imgUrl.isBlank()) {
            try {
                ImageView iv = new ImageView(new Image(imgUrl, 24, 24, true, true, true));
                p.getChildren().add(iv);
            } catch (Exception ignored) {
                Label l = new Label("📦");
                l.setStyle("-fx-font-size: 12px;");
                p.getChildren().add(l);
            }
        } else {
            Label l = new Label("📦");
            l.setStyle("-fx-font-size: 12px;");
            p.getChildren().add(l);
        }

        Label text = new Label(qty + "x " + name);
        text.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #374151;");
        p.getChildren().add(text);
        return p;
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

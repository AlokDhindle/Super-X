package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OrderController;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.config.Firebaseconfig;
import com.kryox.view.Customer.Homepage;

import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;

import java.time.LocalDate;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class ShopkeeperOrderNew {

    /*
     * REAL-TIME LISTENER FOR THIS SHOPKEEPER ONLY
     *
     * Firestore structure:
     *
     * Shopkeepers/{shopkeeperUid}/Orders/{date}/OrderList/{orderId}
     *
     * We intentionally listen directly to this shopkeeper's path.
     * This avoids collectionGroup("OrderList").whereEqualTo("shopkeeperUid", ...)
     * which requires a Firestore COLLECTION_GROUP index.
     */
    private static ListenerRegistration newOrderListener;

    // Empty-state label used by the New Orders screen.
    // This was referenced in ordersScene() but was never declared.
    private static final Label noOrdersLabel =
            new Label("No new orders available.");

    static {
        noOrdersLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-text-fill: #777777;"
        );
        noOrdersLabel.setPadding(
                new Insets(50, 0, 0, 0)
        );
    }

    public static Scene ordersScene() {

        // Border Pane

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #EEE5DF;"
        );


        // Header

        HBox headerMainBox = ViewConstants.header();

        // Header background
        headerMainBox.setStyle(
                "-fx-background-color: #EBCCB7;"
        );

        borderPane.setTop(headerMainBox);


        // Sidebar

        VBox sidebar = createSidebar();
        borderPane.setLeft(sidebar);
        // Center

        VBox centerMain = new VBox();

        centerMain.setPadding(
                new Insets(30, 25, 20, 25)
        );

        centerMain.setSpacing(15);

        centerMain.setStyle(
                "-fx-background-color: #EEE5DF;"
        );


        // Title

        Text pageTitle =
                new Text("Orders Management");

        pageTitle.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #1E1E24;"
        );


        Text pageSubtitle =
                new Text(
                        "Manage incoming requests and active deliveries."
                );

        pageSubtitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-fill: #604D43;"
        );


        VBox titleBox = new VBox(
                5,
                pageTitle,
                pageSubtitle
        );


        HBox titleRow =
                new HBox(titleBox);

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        // Status Buttons

        HBox statusBar = new HBox();

        statusBar.setAlignment(
                Pos.CENTER
        );

        statusBar.setPrefHeight(75);

        statusBar.setSpacing(0);


        Button newOrderButton =
                ViewConstants.createStatusButton(
                        "New Order"
                );


        Button preparingButton =
                ViewConstants.createStatusButton(
                        "Preparing"
                );


        Button readyButton =
                ViewConstants.createStatusButton(
                        "✓ Ready"
                );


        Button deliveryButton =
                ViewConstants.createStatusButton(
                        "Out for Delivery"
                );


        Button completedButton =
                ViewConstants.createStatusButton(
                        "✓ Completed"
                );


        HBox.setHgrow(
                newOrderButton,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                preparingButton,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                readyButton,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                deliveryButton,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                completedButton,
                Priority.ALWAYS
        );


        statusBar.getChildren().addAll(
                newOrderButton,
                preparingButton,
                readyButton,
                deliveryButton,
                completedButton
        );


        // Select New Order Button

        ViewConstants.setSelectedStatusButton(
                newOrderButton
        );


        // Status Button Actions

        newOrderButton.setOnAction(e -> {
            stopNewOrderListener();
            OrderController.stopOrderListener();

            Homepage.HomepageStage.setScene(
                    ShopkeeperOrderNew.ordersScene()
            );
        });


        preparingButton.setOnAction(e -> {
            stopNewOrderListener();
            OrderController.stopOrderListener();

            Homepage.HomepageStage.setScene(
                    ShopkeeperOrderPreparing.ordersScene()
            );
        });


        readyButton.setOnAction(e -> {
            stopNewOrderListener();
            OrderController.stopOrderListener();

            Homepage.HomepageStage.setScene(
                    ShopkeeperOrderReady.ordersScene()
            );
        });


        deliveryButton.setOnAction(e -> {
            stopNewOrderListener();
            OrderController.stopOrderListener();

            Homepage.HomepageStage.setScene(
                    ShopkeeperOrderOut.ordersScene()
            );
        });


        completedButton.setOnAction(e -> {
            stopNewOrderListener();
            OrderController.stopOrderListener();

            Homepage.HomepageStage.setScene(
                    ShopkeeperOrderCompleted.ordersScene()
            );
        });


        // Order List

        VBox orderList =
                new VBox(20);

        orderList.setPadding(
                new Insets(10, 0, 20, 0)
        );


        // Real-Time New Order Listener

        /*
         * IMPORTANT FIX:
         *
         * Do NOT use:
         *
         * collectionGroup("OrderList")
         *     .whereEqualTo("shopkeeperUid", uid)
         *
         * because that query requires a COLLECTION_GROUP index.
         *
         * Instead, directly listen to:
         *
         * Shopkeepers/{currentUid}/Orders/{today}/OrderList
         *
         * and filter only orderStatus == NEW.
         */
        stopNewOrderListener();

        String currentShopkeeperUid = ShopkeeperLogController.getShopkeeperUid();
        if (currentShopkeeperUid == null || currentShopkeeperUid.trim().isEmpty()) {
            if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getShopkeeperUid() != null) {
                currentShopkeeperUid = ViewConstants.shopkeeperModel.getShopkeeperUid();
            }
        }

        if (currentShopkeeperUid == null ||
                currentShopkeeperUid.trim().isEmpty()) {

            System.out.println(
                    "ERROR: Shopkeeper UID is missing. Cannot load new orders."
            );

            orderList.getChildren().clear();
            orderList.getChildren().add(noOrdersLabel);

        } else {

            System.out.println(
                    "============================================"
            );
            System.out.println(
                    "LOADING NEW ORDERS FOR SHOPKEEPER: " + currentShopkeeperUid
            );
            System.out.println(
                    "============================================"
            );

            // Load initial NEW orders from all date subcollections
            loadNewOrders(orderList);

            // Listen for real-time order updates
            OrderController.listenForNewOrders(newOrder -> {
                Platform.runLater(() -> {
                    System.out.println("Real-time new order received: " + newOrder.getOrderId());
                    loadNewOrders(orderList);
                });
            });
        }

        // Scroll Pane

        ScrollPane orderScrollPane =
                new ScrollPane(
                        orderList
                );

        orderScrollPane.setFitToWidth(true);

        orderScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        orderScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        orderScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );


        VBox.setVgrow(
                orderScrollPane,
                Priority.ALWAYS
        );


        // Add Center Content

        centerMain.getChildren().addAll(
                titleRow,
                statusBar,
                orderScrollPane
        );

        borderPane.setCenter(
                centerMain
        );


        // Footer

        VBox footerBox =
                ViewConstants.footer();

        borderPane.setBottom(
                footerBox
        );


        // Scene

        Scene ordersScene =
                new Scene(
                        borderPane,
                        1550,
                        850
                );

        ordersScene.setFill(
                Color.web("#EEE5DF")
        );

        return ordersScene;
    }


    // Load New Orders from All Date Subcollections

    private static void loadNewOrders(VBox orderList) {

        for (int i = orderList.getChildren().size() - 1; i >= 0; i--) {
            javafx.scene.Node node = orderList.getChildren().get(i);
            if (node.getUserData() != null) {
                orderList.getChildren().remove(i);
            }
        }

        ArrayList<OrderModel> newOrders = OrderController.getNewOrders();

        if (newOrders == null || newOrders.isEmpty()) {
            if (!orderList.getChildren().contains(noOrdersLabel)) {
                orderList.getChildren().add(noOrdersLabel);
            }
            System.out.println("No NEW orders found.");
            return;
        }

        orderList.getChildren().remove(noOrdersLabel);

        int displayedOrders = 0;
        for (OrderModel order : newOrders) {
            if (order == null) continue;
            if (!"NEW".equalsIgnoreCase(safe(order.getOrderStatus()))) continue;

            if (containsOrder(orderList, order.getOrderId())) {
                continue;
            }

            try {
                VBox orderCardWrapper = createNewOrderCard(order, orderList);
                orderCardWrapper.setUserData(order.getOrderId());
                orderList.getChildren().add(orderCardWrapper);
                displayedOrders++;
                System.out.println("NEW ORDER DISPLAYED: " + order.getOrderId());
            } catch (Exception ex) {
                System.out.println("ERROR DISPLAYING ORDER: " + order.getOrderId());
                ex.printStackTrace();
            }
        }

        if (displayedOrders == 0) {
            if (!orderList.getChildren().contains(noOrdersLabel)) {
                orderList.getChildren().add(noOrdersLabel);
            }
        } else {
            orderList.getChildren().remove(noOrdersLabel);
        }

        updateEmptyOrderMessage(orderList, noOrdersLabel);
    }


    // Stop Local New Order Listener

    private static void stopNewOrderListener() {

        if (newOrderListener != null) {

            try {
                newOrderListener.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }

            newOrderListener = null;
        }
    }


    // Check Whether Order Card Already Exists

    private static boolean containsOrder(
            VBox orderList,
            String orderId) {

        for (javafx.scene.Node node :
                orderList.getChildren()) {

            Object data =
                    node.getUserData();

            if (data != null &&
                    orderId.equals(
                            String.valueOf(data)
                    )) {

                return true;
            }
        }

        return false;
    }


    // Remove Order Card by Order ID

    private static void removeOrderCard(
            VBox orderList,
            String orderId) {

        javafx.scene.Node nodeToRemove = null;

        for (javafx.scene.Node node :
                orderList.getChildren()) {

            Object data =
                    node.getUserData();

            if (data != null &&
                    orderId.equals(
                            String.valueOf(data)
                    )) {

                nodeToRemove = node;
                break;
            }
        }

        if (nodeToRemove != null) {
            orderList.getChildren().remove(
                    nodeToRemove
            );
        }
    }


    // Empty Order Message

    private static void updateEmptyOrderMessage(
            VBox orderList,
            Label noOrdersLabel) {

        boolean hasOrderCard = false;

        for (javafx.scene.Node node :
                orderList.getChildren()) {

            if (node.getUserData() != null) {
                hasOrderCard = true;
                break;
            }
        }

        if (hasOrderCard) {

            orderList.getChildren().remove(
                    noOrdersLabel
            );

        } else if (!orderList.getChildren().contains(
                noOrdersLabel
        )) {

            orderList.getChildren().add(
                    noOrdersLabel
            );
        }
    }


    // Show Empty Message after Accept / Decline

    private static void showEmptyMessageIfNeeded(
            VBox orderList) {

        for (javafx.scene.Node node :
                orderList.getChildren()) {

            if (node.getUserData() != null) {
                return;
            }
        }

        if (!orderList.getChildren().contains(noOrdersLabel)) {
            orderList.getChildren().add(noOrdersLabel);
        }
    }


    // Create Dynamic Order Card

    private static VBox createNewOrderCard(
            OrderModel order,
            VBox orderList) {


        HBox card = new HBox();

        card.setMinHeight(255);

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #DEDDE2;" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;"
        );


        DropShadow shadow =
                new DropShadow();

        shadow.setRadius(5);

        shadow.setOffsetY(2);

        shadow.setColor(
                Color.rgb(
                        0,
                        0,
                        0,
                        0.08
                )
        );

        card.setEffect(shadow);


        // Orange Left Border

        VBox orangeBorder =
                new VBox();

        orangeBorder.setPrefWidth(4);

        orangeBorder.setStyle(
                "-fx-background-color: #B94F00;" +
                "-fx-background-radius: 12px 0 0 12px;"
        );


        // Left Content

        VBox leftContent =
                new VBox(12);

        leftContent.setPadding(
                new Insets(
                        20,
                        18,
                        18,
                        20
                )
        );


        // Order ID + Status

        Text orderText =
                new Text(
                        "#" + safe(
                                order.getOrderId()
                        )
                );

        orderText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #202027;"
        );


        Text statusText =
                new Text(
                        safe(
                                order.getOrderStatus()
                        )
                );

        statusText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #542600;" +
                "-fx-background-color: #FF6900;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 9px 10px;"
        );


        HBox orderHeader =
                new HBox(
                        12,
                        orderText,
                        statusText
                );

        orderHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        // Customer Information

        Text customerIcon =
                new Text("♙");

        customerIcon.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-fill: #604D43;"
        );


        Text customerText =
                new Text(
                        safe(
                                order.getCustomerName()
                        )
                );

        customerText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #604D43;"
        );


        Text dot =
                new Text("•");

        dot.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #B8B5B5;"
        );


        Text calendarIcon =
                new Text("□");

        calendarIcon.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-fill: #604D43;"
        );


        Text timeText =
                new Text(
                        safe(
                                order.getOrderDate()
                        )
                );

        timeText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #604D43;"
        );


        HBox customerRow =
                new HBox(
                        10,
                        customerIcon,
                        customerText,
                        dot,
                        calendarIcon,
                        timeText
                );

        customerRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // Products Box

        VBox productsBox =
                new VBox(8);

        productsBox.setPadding(
                new Insets(
                        11,
                        12,
                        10,
                        12
                )
        );

        productsBox.setStyle(
                "-fx-background-color: #F2F1F6;" +
                "-fx-background-radius: 8px;"
        );


        // Dynamic Product Rows

        if (order.getProducts() != null) {

            for (OrderItemModel item
                    : order.getProducts()) {

                HBox productRow =
                        createProductRow(
                                item
                        );

                productsBox.getChildren().add(
                        productRow
                );
            }
        }


        leftContent.getChildren().addAll(
                orderHeader,
                customerRow,
                productsBox
        );


        HBox.setHgrow(
                leftContent,
                Priority.ALWAYS
        );


        // Right Content

        VBox rightContent =
                new VBox(10);

        rightContent.setPrefWidth(190);

        rightContent.setMinWidth(190);

        rightContent.setPadding(
                new Insets(
                        20,
                        20,
                        20,
                        20
                )
        );

        rightContent.setAlignment(
                Pos.CENTER
        );

        rightContent.setStyle(
                "-fx-border-color: #E1DFE4;" +
                "-fx-border-width: 0 0 0 1px;"
        );


        // Total Amount

        Text totalLabel =
                new Text(
                        "Total Amount"
                );

        totalLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #604D43;"
        );


        Text total =
                new Text(
                        "₹ " +
                        String.format(
                                "%.2f",
                                order.getTotalAmount()
                        )
                );

        total.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #202027;"
        );


        VBox amountBox =
                new VBox(
                        3,
                        totalLabel,
                        total
                );

        amountBox.setAlignment(
                Pos.CENTER_RIGHT
        );


        // Accept Button

        Button acceptButton =
                new Button(
                        "✓  Accept Order"
                );

        acceptButton.setPrefWidth(170);

        acceptButton.setPrefHeight(45);

        acceptButton.setStyle(
                "-fx-background-color: #B94F00;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );


        acceptButton.setOnMouseEntered(e ->
                acceptButton.setStyle(
                        "-fx-background-color: #963F00;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );


        acceptButton.setOnMouseExited(e ->
                acceptButton.setStyle(
                        "-fx-background-color: #B94F00;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );


        acceptButton.setOnAction(e -> {
            acceptButton.setDisable(true);

            boolean updated =
                    OrderController.acceptOrder(
                            order
                    );

            if (updated) {
                VBox wrapper =
                        (VBox) card.getParent();

                if (wrapper != null) {
                    orderList.getChildren().remove(
                            wrapper
                    );
                }

                updateEmptyOrderMessage(orderList, noOrdersLabel);

                System.out.println(
                        "Order accepted: "
                        + order.getOrderId()
                );
            } else {
                acceptButton.setDisable(false);
            }
        });


        // Decline Button

        Button declineButton =
                new Button(
                        "×  Decline"
                );

        declineButton.setPrefWidth(170);

        declineButton.setPrefHeight(45);

        declineButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #292931;" +
                "-fx-font-size: 15px;" +
                "-fx-border-color: #E5B49C;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );


        declineButton.setOnMouseEntered(e ->
                declineButton.setStyle(
                        "-fx-background-color: #FFF5F0;" +
                        "-fx-text-fill: #292931;" +
                        "-fx-font-size: 15px;" +
                        "-fx-border-color: #B94F00;" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );


        declineButton.setOnMouseExited(e ->
                declineButton.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-text-fill: #292931;" +
                        "-fx-font-size: 15px;" +
                        "-fx-border-color: #E5B49C;" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-cursor: hand;"
                )
        );


        declineButton.setOnAction(e -> {

            boolean updated =
                    OrderController.declineOrder(
                            order
                    );


            if (updated) {

                VBox wrapper =
                        (VBox) card.getParent();

                orderList.getChildren().remove(
                        wrapper
                );

                System.out.println(
                        "Order declined: "
                        + order.getOrderId()
                );
            }
        });


        VBox buttonsBox =
                new VBox(
                        10,
                        acceptButton,
                        declineButton
                );

        buttonsBox.setAlignment(
                Pos.CENTER
        );


        rightContent.getChildren().addAll(
                amountBox,
                buttonsBox
        );


        VBox.setVgrow(
                amountBox,
                Priority.ALWAYS
        );


        // Add Everything to Card

        card.getChildren().addAll(
                orangeBorder,
                leftContent,
                rightContent
        );


        // Wrapper

        VBox wrapper =
                new VBox(
                        card
                );

        return wrapper;
    }


    // Create Dynamic Product Row

    private static HBox createProductRow(
            OrderItemModel item) {

        HBox productRow =
                new HBox();

        productRow.setSpacing(10);

        productRow.setAlignment(
                Pos.CENTER_LEFT
        );


        Text quantityText =
                new Text(
                        item.getQuantity() + "x"
                );

        quantityText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #22222A;"
        );


        Text productName =
                new Text(
                        safe(
                                item.getProductName()
                        )
                );

        productName.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #292931;"
        );


        HBox.setHgrow(
                productName,
                Priority.ALWAYS
        );


        Text price =
                new Text(
                        "₹ " +
                        String.format(
                                "%.2f",
                                item.getTotalPrice()
                        )
                );

        price.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #604D43;"
        );


        productRow.getChildren().addAll(
                quantityText,
                productName,
                price
        );


        return productRow;
    }


    // Null Safe String

    private static String safe(
            String value) {

        return value == null
                ? ""
                : value;
    }

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #EBCCB7;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                true);

                Button bookingsButton = ViewConstants.createDashboardButton(
                                "📅",
                                "Bookings",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                false);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                false);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                false);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                bookingsButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                bookingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperBookedProducts
                                                                .bookedProductsScene()));
                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));



                offersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));


                settingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }
}

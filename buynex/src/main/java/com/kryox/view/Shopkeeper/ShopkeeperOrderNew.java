package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.control.Shopkeeper.OrderController;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;

import java.util.ArrayList;

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

    public static Scene ordersScene() {

        // ============================================================
        // BORDER PANE
        // ============================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8F7FC;"
        );


        // ============================================================
        // HEADER
        // ============================================================

        HBox headerMainBox = ViewConstants.header();

        borderPane.setTop(headerMainBox);


        // ============================================================
        // SIDEBAR
        // ============================================================

        VBox sidebar = createSidebar();
        borderPane.setLeft(sidebar);
        // ============================================================
        // CENTER
        // ============================================================

        VBox centerMain = new VBox();

        centerMain.setPadding(
                new Insets(30, 25, 20, 25)
        );

        centerMain.setSpacing(15);

        centerMain.setStyle(
                "-fx-background-color: #F8F7FC;"
        );


        // ============================================================
        // TITLE
        // ============================================================

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


        // ============================================================
        // STATUS BUTTONS
        // ============================================================

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


        // ============================================================
        // SELECT NEW ORDER BUTTON
        // ============================================================

        ViewConstants.setSelectedStatusButton(
                newOrderButton
        );


        // ============================================================
        // STATUS BUTTON ACTIONS
        // ============================================================

        newOrderButton.setOnAction(e ->
                Main.primaryStage.setScene(
                        ShopkeeperOrderNew.ordersScene()
                )
        );


        preparingButton.setOnAction(e ->
                Main.primaryStage.setScene(
                        ShopkeeperOrderPreparing.ordersScene()
                )
        );


        readyButton.setOnAction(e ->
                Main.primaryStage.setScene(
                        ShopkeeperOrderReady.ordersScene()
                )
        );


        deliveryButton.setOnAction(e ->
                Main.primaryStage.setScene(
                        ShopkeeperOrderOut.ordersScene()
                )
        );


        completedButton.setOnAction(e ->
                Main.primaryStage.setScene(
                        ShopkeeperOrderCompleted.ordersScene()
                )
        );


        // ============================================================
        // ORDER LIST
        // ============================================================

        VBox orderList =
                new VBox(20);

        orderList.setPadding(
                new Insets(10, 0, 20, 0)
        );


        // ============================================================
        // FETCH NEW ORDERS FROM FIRESTORE
        // ============================================================

        ArrayList<OrderModel> orders =
                OrderController.getNewOrders();


        if (orders != null && !orders.isEmpty()) {

            for (OrderModel order : orders) {

                VBox orderCard =
                        createNewOrderCard(
                                order,
                                orderList
                        );

                orderList.getChildren().add(
                        orderCard
                );
            }

        } else {

            Label noOrdersLabel =
                    new Label(
                            "No new orders available."
                    );

            noOrdersLabel.setStyle(
                    "-fx-font-size: 17px;" +
                    "-fx-text-fill: #777777;"
            );

            noOrdersLabel.setPadding(
                    new Insets(50, 0, 0, 0)
            );

            orderList.getChildren().add(
                    noOrdersLabel
            );
        }


        // ============================================================
        // SCROLL PANE
        // ============================================================

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


        // ============================================================
        // ADD CENTER CONTENT
        // ============================================================

        centerMain.getChildren().addAll(
                titleRow,
                statusBar,
                orderScrollPane
        );

        borderPane.setCenter(
                centerMain
        );


        // ============================================================
        // FOOTER
        // ============================================================

        VBox footerBox =
                ViewConstants.footer();

        borderPane.setBottom(
                footerBox
        );


        // ============================================================
        // SCENE
        // ============================================================

        Scene ordersScene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        ordersScene.setFill(
                Color.web("#F8F7FC")
        );

        return ordersScene;
    }


    // ================================================================
    // CREATE DYNAMIC ORDER CARD
    // ================================================================

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


        // ============================================================
        // ORANGE LEFT BORDER
        // ============================================================

        VBox orangeBorder =
                new VBox();

        orangeBorder.setPrefWidth(4);

        orangeBorder.setStyle(
                "-fx-background-color: #B94F00;" +
                "-fx-background-radius: 12px 0 0 12px;"
        );


        // ============================================================
        // LEFT CONTENT
        // ============================================================

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


        // ============================================================
        // ORDER ID + STATUS
        // ============================================================

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


        // ============================================================
        // CUSTOMER INFORMATION
        // ============================================================

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


        // ============================================================
        // PRODUCTS BOX
        // ============================================================

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


        // ============================================================
        // DYNAMIC PRODUCT ROWS
        // ============================================================

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


        // ============================================================
        // RIGHT CONTENT
        // ============================================================

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


        // ============================================================
        // TOTAL AMOUNT
        // ============================================================

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


        // ============================================================
        // ACCEPT BUTTON
        // ============================================================

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

            boolean updated =
                    OrderController.acceptOrder(
                            order
                    );


            if (updated) {

                VBox wrapper =
                        (VBox) card.getParent();

                orderList.getChildren().remove(
                        wrapper
                );

                System.out.println(
                        "Order accepted: "
                        + order.getOrderId()
                );
            }
        });


        // ============================================================
        // DECLINE BUTTON
        // ============================================================

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


        // ============================================================
        // ADD EVERYTHING TO CARD
        // ============================================================

        card.getChildren().addAll(
                orangeBorder,
                leftContent,
                rightContent
        );


        // ============================================================
        // WRAPPER
        // ============================================================

        VBox wrapper =
                new VBox(
                        card
                );

        return wrapper;
    }


    // ================================================================
    // CREATE DYNAMIC PRODUCT ROW
    // ================================================================

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


    // ================================================================
    // NULL SAFE STRING
    // ================================================================

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
                                "-fx-background-color: #F5F4F9;" +
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
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));



                offersButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));


                settingsButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }
}

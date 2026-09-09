
package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OrderController;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.view.Customer.Homepage;

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

public class ShopkeeperOrderPreparing {

        public static Scene ordersScene() {

                // Border Pane

                BorderPane borderPane = new BorderPane();

                borderPane.setStyle(
                                "-fx-background-color: #EEE5DF;");

                // Header

                HBox headerMainBox = ViewConstants.header();

                // Header background
                headerMainBox.setStyle(
                                "-fx-background-color: #EBCCB7;");

                borderPane.setTop(headerMainBox);

                // Sidebar

                VBox sidebar = createSidebar();
                borderPane.setLeft(sidebar);
                // Center - Preparing Orders

                VBox centerMain = new VBox();

                centerMain.setPadding(
                                new Insets(30, 25, 20, 25));

                centerMain.setSpacing(15);

                centerMain.setStyle(
                                "-fx-background-color: #EEE5DF;");

                // Title

                Text pageTitle = new Text("Orders Management");

                pageTitle.setStyle(
                                "-fx-font-size: 32px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #1E1E24;");

                Text pageSubtitle = new Text(
                                "Manage incoming requests and active deliveries.");

                pageSubtitle.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-fill: #604D43;");

                VBox titleBox = new VBox(
                                5,
                                pageTitle,
                                pageSubtitle);

                HBox titleRow = new HBox(
                                titleBox);

                titleRow.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                // Status Buttons

                HBox statusBar = new HBox();

                statusBar.setAlignment(
                                Pos.CENTER);

                statusBar.setPrefHeight(75);

                statusBar.setSpacing(0);

                // New Order

                Button newOrderButton = ViewConstants.createStatusButton(
                                "New Order");

                // Preparing

                Button preparingButton = ViewConstants.createStatusButton(
                                "Preparing");

                // Ready

                Button readyButton = ViewConstants.createStatusButton(
                                "✓ Ready");

                // Out for Delivery

                Button deliveryButton = ViewConstants.createStatusButton(
                                "Out for Delivery");

                // Completed

                Button completedButton = ViewConstants.createStatusButton(
                                "✓ Completed");

                // Button Widths

                HBox.setHgrow(
                                newOrderButton,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                preparingButton,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                readyButton,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                deliveryButton,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                completedButton,
                                Priority.ALWAYS);

                // Add Status Buttons

                statusBar.getChildren().addAll(
                                newOrderButton,
                                preparingButton,
                                readyButton,
                                deliveryButton,
                                completedButton);

                // Preparing Selected by Default

                ViewConstants.setSelectedStatusButton(
                                preparingButton);

                // Status Button Actions

                newOrderButton.setOnAction(e -> {

                        System.out.println("New Order clicked");
                        Homepage.HomepageStage.setScene(ShopkeeperOrderNew.ordersScene());

                });

                preparingButton.setOnAction(e -> {

                        System.out.println("Preparing clicked");
                });

                readyButton.setOnAction(e -> {

                        System.out.println("Ready clicked");
                        Homepage.HomepageStage.setScene(ShopkeeperOrderReady.ordersScene());

                });

                deliveryButton.setOnAction(e -> {

                        System.out.println("Out for Delivery clicked");
                        Homepage.HomepageStage.setScene(ShopkeeperOrderOut.ordersScene());
                });

                completedButton.setOnAction(e -> {

                        System.out.println("Completed clicked");
                        Homepage.HomepageStage.setScene(ShopkeeperOrderCompleted.ordersScene());
                });

                // Order List

                VBox orderList = new VBox(20);

                orderList.setPadding(
                                new Insets(10, 0, 20, 0));

                ArrayList<OrderModel> preparingOrders = OrderController.getPreparingOrders();

                if (preparingOrders == null || preparingOrders.isEmpty()) {
                        Label noOrdersLabel = new Label("No orders currently in preparation.");
                        noOrdersLabel.setStyle("-fx-font-size: 17px; -fx-text-fill: #777777; -fx-padding: 50 0 0 0;");
                        orderList.getChildren().add(noOrdersLabel);
                } else {
                        for (OrderModel order : preparingOrders) {
                                if (order == null) continue;

                                String p1Name = "", p1Qty = "", p1Price = "";
                                String p2Name = "", p2Qty = "", p2Price = "";

                                if (order.getProducts() != null && !order.getProducts().isEmpty()) {
                                        var p1 = order.getProducts().get(0);
                                        p1Name = p1.getProductName() != null ? p1.getProductName() : "";
                                        p1Qty = p1.getQuantity() + "x";
                                        p1Price = String.format("₹%.2f", p1.getTotalPrice());

                                        if (order.getProducts().size() > 1) {
                                                var p2 = order.getProducts().get(1);
                                                p2Name = p2.getProductName() != null ? p2.getProductName() : "";
                                                p2Qty = p2.getQuantity() + "x";
                                                p2Price = String.format("₹%.2f", p2.getTotalPrice());
                                        }
                                }

                                VBox orderCard = createPreparingOrderCard(
                                                order,
                                                "#" + (order.getOrderId() != null ? order.getOrderId() : ""),
                                                order.getOrderDate() != null ? order.getOrderDate() : "Today",
                                                order.getCustomerName() != null ? order.getCustomerName() : "Customer",
                                                "",
                                                p1Qty, p1Name, p1Price,
                                                p2Qty, p2Name, p2Price,
                                                "",
                                                String.format("₹%.2f", order.getTotalAmount())
                                );

                                orderList.getChildren().add(orderCard);
                        }
                }

                // Scroll Pane (Only order list scrolls)

                ScrollPane orderScrollPane = new ScrollPane(
                                orderList);

                orderScrollPane.setFitToWidth(
                                true);

                orderScrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                orderScrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                orderScrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");

                VBox.setVgrow(
                                orderScrollPane,
                                Priority.ALWAYS);

                // Add Center Content

                centerMain.getChildren().addAll(
                                titleRow,
                                statusBar,
                                orderScrollPane);

                // Set Center

                borderPane.setCenter(
                                centerMain);

                // Footer

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // Scene

                Scene ordersScene = new Scene(
                                borderPane,
                                1550,
                                850);

                ordersScene.setFill(
                                Color.web("#EEE5DF"));

                return ordersScene;
        }

        // Create Preparing Order Card

        private static VBox createPreparingOrderCard(
                        OrderModel order,
                        String orderNumber,
                        String elapsedTime,
                        String customerName,
                        String distance,
                        String quantity1,
                        String product1,
                        String price1,
                        String quantity2,
                        String product2,
                        String price2,
                        String specialNote,
                        String totalAmount) {

                // Main Card

                HBox card = new HBox();

                card.setMinHeight(205);
                card.setPrefHeight(205);

                card.setMaxWidth(
                                Double.MAX_VALUE);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-border-color: #DEDDE2;" +
                                                "-fx-border-radius: 12px;" +
                                                "-fx-border-width: 1px;");

                DropShadow shadow = new DropShadow();

                shadow.setRadius(5);
                shadow.setOffsetY(2);

                shadow.setColor(
                                Color.rgb(
                                                0,
                                                0,
                                                0,
                                                0.08));

                card.setEffect(
                                shadow);

                // Orange Left Border

                VBox orangeBorder = new VBox();

                orangeBorder.setPrefWidth(4);

                orangeBorder.setStyle(
                                "-fx-background-color: #B94F00;" +
                                                "-fx-background-radius: 12px 0 0 12px;");

                // Left Content

                VBox leftContent = new VBox(10);

                leftContent.setPadding(
                                new Insets(
                                                20,
                                                18,
                                                18,
                                                20));

                // Order Number + Preparing Status

                Text orderText = new Text(orderNumber);

                orderText.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202027;");

                Text preparingText = new Text(
                                "PREPARING • " + elapsedTime);

                preparingText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #3A2418;" +
                                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 9px 10px;");

                HBox orderHeader = new HBox(
                                12,
                                orderText,
                                preparingText);

                orderHeader.setAlignment(
                                Pos.CENTER_LEFT);

                // Customer Information

                Text customerIcon = new Text("♙");

                customerIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #604D43;");

                Text customerText = new Text(customerName);

                customerText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #604D43;");

                Text dot = new Text("•");

                dot.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #B8B5B5;");

                Text locationIcon = new Text("⌖");

                locationIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #604D43;");

                Text distanceText = new Text(distance);

                distanceText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #604D43;");

                HBox customerRow = new HBox(
                                10,
                                customerIcon,
                                customerText,
                                dot,
                                locationIcon,
                                distanceText);

                customerRow.setAlignment(
                                Pos.CENTER_LEFT);

                // Products Box

                VBox productsBox = new VBox(7);

                productsBox.setPadding(
                                new Insets(
                                                11,
                                                12,
                                                10,
                                                12));

                productsBox.setStyle(
                                "-fx-background-color: #F2F1F6;" +
                                                "-fx-background-radius: 8px;");

                // Product 1

                HBox productRow1 = new HBox();

                Text qty1 = new Text(quantity1);

                qty1.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #22222A;");

                Text productName1 = new Text(product1);

                productName1.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #292931;");

                Text productPrice1 = new Text(price1);

                productPrice1.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-fill: #604D43;");

                HBox.setHgrow(
                                productName1,
                                Priority.ALWAYS);

                productRow1.setSpacing(10);

                productRow1.getChildren().addAll(
                                qty1,
                                productName1,
                                productPrice1);

                // Product 2

                HBox productRow2 = new HBox();

                Text qty2 = new Text(quantity2);

                qty2.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #22222A;");

                Text productName2 = new Text(product2);

                productName2.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #292931;");

                Text productPrice2 = new Text(price2);

                productPrice2.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-fill: #604D43;");

                HBox.setHgrow(
                                productName2,
                                Priority.ALWAYS);

                productRow2.setSpacing(10);

                productRow2.getChildren().addAll(
                                qty2,
                                productName2,
                                productPrice2);

                productsBox.getChildren().addAll(
                                productRow1,
                                productRow2);

                // Special Note

                if (!specialNote.isEmpty()) {

                        Text noteText = new Text(specialNote);

                        noteText.setStyle(
                                        "-fx-font-size: 12px;" +
                                                        "-fx-fill: #604D43;");

                        productsBox.getChildren().add(
                                        noteText);
                }

                leftContent.getChildren().addAll(
                                orderHeader,
                                customerRow,
                                productsBox);

                HBox.setHgrow(
                                leftContent,
                                Priority.ALWAYS);

                // Right Content

                VBox rightContent = new VBox();

                rightContent.setPrefWidth(
                                190);

                rightContent.setMinWidth(
                                190);

                rightContent.setPadding(
                                new Insets(
                                                20,
                                                20,
                                                20,
                                                20));

                rightContent.setAlignment(
                                Pos.CENTER);

                rightContent.setStyle(
                                "-fx-border-color: #E1DFE4;" +
                                                "-fx-border-width: 0 0 0 1px;");

                // Total Amount

                Text totalLabel = new Text("Total Amount");

                totalLabel.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-fill: #604D43;");

                Text total = new Text(totalAmount);

                total.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #202027;");

                VBox amountBox = new VBox(
                                3,
                                totalLabel,
                                total);

                amountBox.setAlignment(
                                Pos.CENTER_RIGHT);

                // Mark as Ready Button

                Button readyButton = new Button("✓  Mark as Ready");

                readyButton.setPrefWidth(
                                170);

                readyButton.setPrefHeight(
                                45);

                readyButton.setStyle(
                                "-fx-background-color: #B94F00;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                readyButton.setOnMouseEntered(e -> {

                        readyButton.setStyle(
                                        "-fx-background-color: #963F00;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                readyButton.setOnMouseExited(e -> {

                        readyButton.setStyle(
                                        "-fx-background-color: #B94F00;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                readyButton.setOnAction(e -> {
                        boolean updated = OrderController.markOrderReady(order);
                        if (updated) {
                                System.out.println(orderNumber + " - Mark as Ready clicked & updated to READY in Firestore");
                                Homepage.HomepageStage.setScene(ShopkeeperOrderPreparing.ordersScene());
                        }
                });

                // Cancel Button

                Button cancelButton = new Button("×  Cancel Order");

                cancelButton.setPrefWidth(
                                170);

                cancelButton.setPrefHeight(
                                45);

                cancelButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #292931;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-border-color: #E5B49C;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                cancelButton.setOnMouseEntered(e -> {

                        cancelButton.setStyle(
                                        "-fx-background-color: #FFF5F0;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #B94F00;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                cancelButton.setOnMouseExited(e -> {

                        cancelButton.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #E5B49C;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                cancelButton.setOnAction(e -> {
                        boolean updated = OrderController.declineOrder(order);
                        if (updated) {
                                System.out.println(orderNumber + " - Cancel Order clicked & updated to DECLINED in Firestore");
                                Homepage.HomepageStage.setScene(ShopkeeperOrderPreparing.ordersScene());
                        }
                });

                // Button Box

                VBox buttonsBox = new VBox(
                                10,
                                readyButton,
                                cancelButton);

                buttonsBox.setAlignment(
                                Pos.CENTER);

                // Right Content

                rightContent.getChildren().addAll(
                                amountBox,
                                buttonsBox);

                VBox.setVgrow(
                                amountBox,
                                Priority.ALWAYS);

                // Add everything to card

                card.getChildren().addAll(
                                orangeBorder,
                                leftContent,
                                rightContent);

                // Wrapper

                VBox wrapper = new VBox(card);

                return wrapper;
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
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
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
package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
                                "-fx-background-color: #F8F7FC;");

                // ============================================================
                // HEADER
                // ============================================================

                HBox headerMainBox = ViewConstants.header();

                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = new VBox();

                sidebar.setMinWidth(260);
                sidebar.setMaxWidth(260);

                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                // ============================================================
                // SHOPKEEPER PROFILE
                // ============================================================

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(30, 20, 30, 20));

                // ============================================================
                // DASHBOARD BUTTON
                // ============================================================

                Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", false);

                // ============================================================
                // ORDERS BUTTON
                // ============================================================

                Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", true);

                // ============================================================
                // INVENTORY BUTTON
                // ============================================================

                Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);

                // ============================================================
                // OFFERS BUTTON
                // ============================================================

                Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", false);

                // ============================================================
                // ANALYTICS BUTTON
                // ============================================================

                Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);

                // ============================================================
                // SETTINGS BUTTON
                // ============================================================

                Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", false);

                // ============================================================
                // SUPPORT BUTTON
                // ============================================================

                Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

                // ============================================================
                // SIDEBAR MENU
                // ============================================================

                VBox sidebarMenu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                sidebarMenu.setPadding(
                                new Insets(0, 8, 0, 8));

                // ============================================================
                // LOGOUT BUTTON
                // ============================================================

                VBox logoutBox = ViewConstants.logoutBox();

                VBox.setVgrow(
                                sidebarMenu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                sidebarMenu,
                                logoutBox);

                borderPane.setLeft(sidebar);

                // ============================================================
                // BUTTON ACTIONS
                // ============================================================

                dashboardButton.setOnAction(e -> {

                        System.out.println(
                                        "Dashboard clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperDashboard.dashboardScene());
                });

                ordersButton.setOnAction(e -> {

                        System.out.println(
                                        "Orders clicked");
                });

                inventoryButton.setOnAction(e -> {

                        System.out.println(
                                        "Inventory clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperInventory.inventoryScene());
                });

                offersButton.setOnAction(e -> {

                        System.out.println(
                                        "Offers clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperOffers.offersScene());
                });

                analyticsButton.setOnAction(e -> {

                        System.out.println(
                                        "Analytics clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperAnalytics.analyticsScene());
                });

                settingsButton.setOnAction(e -> {

                        System.out.println(
                                        "Settings clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperSettings.settingsScene());
                });

                supportButton.setOnAction(e -> {

                        System.out.println(
                                        "Support clicked");

                        Main.primaryStage.setScene(
                                        ShopkeeperSupport.supportScene());
                });

                // ============================================================
                // CENTER
                // NEW / ACTIVE ORDERS
                // ============================================================

                VBox centerMain = new VBox();

                centerMain.setPadding(
                                new Insets(30, 25, 20, 25));

                centerMain.setSpacing(15);

                centerMain.setStyle(
                                "-fx-background-color: #F8F7FC;");

                // ============================================================
                // TITLE
                // ============================================================

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

                HBox titleRow = new HBox(titleBox);

                titleRow.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                // ============================================================
                // STATUS BUTTONS
                // ============================================================

                HBox statusBar = new HBox();

                statusBar.setAlignment(
                                Pos.CENTER);

                statusBar.setPrefHeight(75);

                statusBar.setSpacing(0);

                // NEW ORDER

                Button newOrderButton = ViewConstants.createStatusButton(
                                "New Order");

                // PREPARING

                Button preparingButton = ViewConstants.createStatusButton(
                                "Preparing");

                // READY

                Button readyButton = ViewConstants.createStatusButton(
                                "✓ Ready");

                // OUT FOR DELIVERY

                Button deliveryButton = ViewConstants.createStatusButton(
                                "Out for Delivery");

                // COMPLETED

                Button completedButton = ViewConstants.createStatusButton(
                                "✓ Completed");

                // ============================================================
                // BUTTON WIDTHS
                // ============================================================

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

                statusBar.getChildren().addAll(
                                newOrderButton,
                                preparingButton,
                                readyButton,
                                deliveryButton,
                                completedButton);

                // ============================================================
                // SET SELECTED STATUS BUTTON
                // ============================================================

                ViewConstants.setSelectedStatusButton(newOrderButton);

                // ============================================================
                // STATUS BUTTON ACTIONS
                // ============================================================

                newOrderButton.setOnAction(e -> {
                        System.out.println("New Order clicked");

                });

                preparingButton.setOnAction(e -> {
                        System.out.println("Preparing clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderPreparing.ordersScene());
                });

                readyButton.setOnAction(e -> {
                        System.out.println("Ready clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderReady.ordersScene());
                });

                deliveryButton.setOnAction(e -> {
                        System.out.println("Out for Delivery clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderOut.ordersScene());
                });

                completedButton.setOnAction(e -> {
                        System.out.println("Completed clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderCompleted.ordersScene());
                });

                // ============================================================
                // ORDER LIST
                // ============================================================

                VBox orderList = new VBox(20);

                orderList.setPadding(
                                new Insets(10, 0, 20, 0));

                // ============================================================
                // ORDER #4598
                // ============================================================

                VBox order4598 = createNewOrderCard(
                                "#4598",
                                "PENDING",
                                "James Wilson",
                                "Just now • 2:15 PM",
                                "3x",
                                "Spicy Tuna Rolls",
                                "$36.00",
                                "1x",
                                "Miso Soup",
                                "$4.50",
                                "$40.50");

                // ============================================================
                // ORDER #4599
                // ============================================================

                VBox order4599 = createNewOrderCard(
                                "#4599",
                                "NEW",
                                "Elena Rodriguez",
                                "2 mins ago • 2:13 PM",
                                "1x",
                                "Garden Salad",
                                "$12.00",
                                "2x",
                                "Fresh Lemonade",
                                "$9.00",
                                "$21.00");

                orderList.getChildren().addAll(
                                order4598,
                                order4599);

                // ============================================================
                // SCROLL PANE
                // ONLY ORDER LIST SCROLLS
                // ============================================================

                ScrollPane orderScrollPane = new ScrollPane(
                                orderList);

                orderScrollPane.setFitToWidth(true);

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

                // ============================================================
                // ADD CENTER CONTENT
                // ============================================================

                centerMain.getChildren().addAll(
                                titleRow,
                                statusBar,
                                orderScrollPane);

                // ============================================================
                // SET CENTER
                // ============================================================

                borderPane.setCenter(
                                centerMain);

                // ============================================================
                // FOOTER
                // ============================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ============================================================
                // SCENE
                // ============================================================

                Scene ordersScene = new Scene(
                                borderPane,
                                1280,
                                650);

                ordersScene.setFill(
                                Color.web("#F8F7FC"));

                return ordersScene;
        }

        // ================================================================
        // CREATE ORDER CARD FOR NEW ORDERS
        // ================================================================

        private static VBox createNewOrderCard(
                        String orderNumber,
                        String status,
                        String customerName,
                        String orderTime,
                        String quantity1,
                        String product1,
                        String price1,
                        String quantity2,
                        String product2,
                        String price2,
                        String totalAmount) {

                HBox card = new HBox();

                card.setMinHeight(255);
                card.setPrefHeight(255);
                card.setMaxWidth(Double.MAX_VALUE);

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

                card.setEffect(shadow);

                // ============================================================
                // ORANGE LEFT BORDER
                // ============================================================

                VBox orangeBorder = new VBox();

                orangeBorder.setPrefWidth(4);

                orangeBorder.setStyle(
                                "-fx-background-color: #B94F00;" +
                                                "-fx-background-radius: 12px 0 0 12px;");

                // ============================================================
                // LEFT CONTENT
                // ============================================================

                VBox leftContent = new VBox(12);

                leftContent.setPadding(
                                new Insets(
                                                20,
                                                18,
                                                18,
                                                20));

                // ============================================================
                // ORDER NUMBER + STATUS
                // ============================================================

                Text orderText = new Text(orderNumber);

                orderText.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202027;");

                Text statusText = new Text(status);

                statusText.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #542600;" +
                                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-padding: 9px 10px;");

                HBox orderHeader = new HBox(
                                12,
                                orderText,
                                statusText);

                orderHeader.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // CUSTOMER INFORMATION
                // ============================================================

                Text customerIcon = new Text("♙");

                customerIcon.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-fill: #604D43;");

                Text customerText = new Text(customerName);

                customerText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #604D43;");

                Text dot = new Text("•");

                dot.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #B8B5B5;");

                Text calendarIcon = new Text("□");

                calendarIcon.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-fill: #604D43;");

                Text timeText = new Text(orderTime);

                timeText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #604D43;");

                HBox customerRow = new HBox(
                                10,
                                customerIcon,
                                customerText,
                                dot,
                                calendarIcon,
                                timeText);

                customerRow.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // PRODUCTS BOX
                // ============================================================

                VBox productsBox = new VBox(8);

                productsBox.setPadding(
                                new Insets(
                                                11,
                                                12,
                                                10,
                                                12));

                productsBox.setStyle(
                                "-fx-background-color: #F2F1F6;" +
                                                "-fx-background-radius: 8px;");

                // ============================================================
                // PRODUCT 1
                // ============================================================

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

                // ============================================================
                // PRODUCT 2
                // ============================================================

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

                leftContent.getChildren().addAll(
                                orderHeader,
                                customerRow,
                                productsBox);

                HBox.setHgrow(
                                leftContent,
                                Priority.ALWAYS);

                // ============================================================
                // RIGHT CONTENT
                // ============================================================

                VBox rightContent = new VBox(10);

                rightContent.setPrefWidth(190);
                rightContent.setMinWidth(190);

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

                // ============================================================
                // TOTAL AMOUNT
                // ============================================================

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

                // ============================================================
                // ACCEPT BUTTON
                // ============================================================

                Button acceptButton = new Button("✓  Accept Order");

                acceptButton.setPrefWidth(170);
                acceptButton.setPrefHeight(45);

                acceptButton.setStyle(
                                "-fx-background-color: #B94F00;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                acceptButton.setOnMouseEntered(e -> {
                        acceptButton.setStyle(
                                        "-fx-background-color: #963F00;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                acceptButton.setOnMouseExited(e -> {
                        acceptButton.setStyle(
                                        "-fx-background-color: #B94F00;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                acceptButton.setOnAction(e -> {
                        System.out.println(
                                        orderNumber +
                                                        " - Accept Order clicked");
                });

                // ============================================================
                // DECLINE BUTTON
                // ============================================================

                Button declineButton = new Button("×  Decline");

                declineButton.setPrefWidth(170);
                declineButton.setPrefHeight(45);

                declineButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #292931;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-border-color: #E5B49C;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                declineButton.setOnMouseEntered(e -> {
                        declineButton.setStyle(
                                        "-fx-background-color: #FFF5F0;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #B94F00;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                declineButton.setOnMouseExited(e -> {
                        declineButton.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #E5B49C;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                declineButton.setOnAction(e -> {
                        System.out.println(
                                        orderNumber +
                                                        " - Decline Order clicked");
                });

                VBox buttonsBox = new VBox(
                                10,
                                acceptButton,
                                declineButton);

                buttonsBox.setAlignment(
                                Pos.CENTER);

                rightContent.getChildren().addAll(
                                amountBox,
                                buttonsBox);

                VBox.setVgrow(
                                amountBox,
                                Priority.ALWAYS);

                // ============================================================
                // ADD EVERYTHING TO CARD
                // ============================================================

                card.getChildren().addAll(
                                orangeBorder,
                                leftContent,
                                rightContent);

                // ============================================================
                // WRAPPER
                // ============================================================

                VBox wrapper = new VBox(card);

                return wrapper;
        }
}
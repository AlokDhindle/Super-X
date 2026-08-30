package com.kryox.view.Shopkeeper;

import org.checkerframework.checker.lock.qual.Holding;

import com.kryox.Main;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperOrderCompleted {

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

                VBox sidebar = createSidebar();
                borderPane.setLeft(sidebar);



                // ============================================================
                // CENTER
                // COMPLETED ORDERS PAGE
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

                HBox titleRow = new HBox(
                                titleBox);

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

                // ============================================================
                // ADD STATUS BUTTONS
                // ============================================================

                statusBar.getChildren().addAll(
                                newOrderButton,
                                preparingButton,
                                readyButton,
                                deliveryButton,
                                completedButton);

                // ============================================================
                // COMPLETED SELECTED
                // ============================================================

                ViewConstants.setSelectedStatusButton(
                                completedButton);

                // ============================================================
                // STATUS BUTTON ACTIONS
                // ============================================================

                newOrderButton.setOnAction(e -> {

                        Homepage.HomepageStage.setScene(
                                        ShopkeeperOrderNew.ordersScene());
                });

                preparingButton.setOnAction(e -> {

                        Homepage.HomepageStage.setScene(
                                        ShopkeeperOrderPreparing.ordersScene());
                });

                readyButton.setOnAction(e -> {

                        Homepage.HomepageStage.setScene(
                                        ShopkeeperOrderReady.ordersScene());
                });

                deliveryButton.setOnAction(e -> {

                        Homepage.HomepageStage.setScene(
                                        ShopkeeperOrderOut.ordersScene());
                });

                completedButton.setOnAction(e -> {

                        System.out.println(
                                        "Completed clicked");
                });

                // ============================================================
                // COMPLETED ORDER LIST
                // ============================================================

                VBox orderList = new VBox(20);

                orderList.setPadding(
                                new Insets(10, 0, 20, 0));

                // ============================================================
                // ORDER #4592
                // ============================================================

                VBox order4592 = createCompletedOrderCard(
                                "#4592",
                                "Sarah Jenkins",
                                "Oct 24, 2023 • 12:45 PM",
                                "2x",
                                "Organic Avocado Toast",
                                "$24.00",
                                "1x",
                                "Cold Brew Coffee",
                                "$5.50",
                                "$29.50");

                // ============================================================
                // ORDER #4593
                // ============================================================

                VBox order4593 = createCompletedOrderCard(
                                "#4593",
                                "Michael Chen",
                                "Oct 24, 2023 • 11:30 AM",
                                "1x",
                                "Sourdough Loaf",
                                "$8.00",
                                "1x",
                                "Artisanal Butter",
                                "$6.50",
                                "$14.50");

                orderList.getChildren().addAll(
                                order4592,
                                order4593);

                // ============================================================
                // SCROLL PANE
                // ONLY ORDER LIST SCROLLS
                // ============================================================

                ScrollPane orderScrollPane = new ScrollPane(orderList);

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

        // =================================================================
        // CREATE COMPLETED ORDER CARD
        // =================================================================

        private static VBox createCompletedOrderCard(
                        String orderNumber,
                        String customerName,
                        String dateTime,
                        String quantity1,
                        String product1,
                        String price1,
                        String quantity2,
                        String product2,
                        String price2,
                        String totalAmount) {

                // ============================================================
                // MAIN CARD
                // ============================================================

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

                card.setEffect(shadow);

                // ============================================================
                // COMPLETED LEFT BORDER
                // ============================================================

                VBox completedBorder = new VBox();

                completedBorder.setPrefWidth(4);

                completedBorder.setStyle(
                                "-fx-background-color: #555555;" +
                                                "-fx-background-radius: 12px 0 0 12px;");

                // ============================================================
                // LEFT CONTENT
                // ============================================================

                VBox leftContent = new VBox(10);

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

                Text statusText = new Text("DELIVERED");

                statusText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #604D43;" +
                                                "-fx-background-color: #E5E4E9;" +
                                                "-fx-background-radius: 6px;" +
                                                "-fx-padding: 9px 10px;");

                HBox orderHeader = new HBox(
                                12,
                                orderText,
                                statusText);

                orderHeader.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // CUSTOMER + DATE
                // ============================================================

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

                Text calendarIcon = new Text("▣");

                calendarIcon.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-fill: #604D43;");

                Text dateText = new Text(dateTime);

                dateText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #604D43;");

                HBox customerRow = new HBox(
                                9,
                                customerIcon,
                                customerText,
                                dot,
                                calendarIcon,
                                dateText);

                customerRow.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // PRODUCTS BOX
                // ============================================================

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

                // ============================================================
                // ADD LEFT CONTENT
                // ============================================================

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

                VBox rightContent = new VBox();

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
                // DOWNLOAD INVOICE BUTTON
                // ============================================================

                Button invoiceButton = new Button("↓  Download Invoice");

                invoiceButton.setPrefWidth(170);

                invoiceButton.setPrefHeight(45);

                invoiceButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #292931;" +
                                                "-fx-font-size: 15px;" +
                                                "-fx-border-color: #E5B49C;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                invoiceButton.setOnMouseEntered(e -> {

                        invoiceButton.setStyle(
                                        "-fx-background-color: #FFF5F0;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #B94F00;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                invoiceButton.setOnMouseExited(e -> {

                        invoiceButton.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #292931;" +
                                                        "-fx-font-size: 15px;" +
                                                        "-fx-border-color: #E5B49C;" +
                                                        "-fx-border-radius: 7px;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-cursor: hand;");
                });

                invoiceButton.setOnAction(e -> {

                        System.out.println(
                                        orderNumber +
                                                        " - Download Invoice clicked");
                });

                // ============================================================
                // BUTTON BOX
                // ============================================================

                VBox buttonsBox = new VBox(
                                10,
                                invoiceButton);

                buttonsBox.setAlignment(
                                Pos.CENTER);

                // ============================================================
                // ADD RIGHT CONTENT
                // ============================================================

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
                                completedBorder,
                                leftContent,
                                rightContent);

                // ============================================================
                // WRAPPER
                // ============================================================

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
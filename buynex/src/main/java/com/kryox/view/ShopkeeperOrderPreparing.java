
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperOrderPreparing {

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

        HBox headerMainBox = Constants.header();

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

        HBox profileBox = Constants.letfProfileBox();

        profileBox.setAlignment(
                Pos.CENTER_LEFT);

        profileBox.setPadding(
                new Insets(30, 20, 30, 20));

        // ============================================================
        // DASHBOARD BUTTON
        // ============================================================

        Text dashboardIcon = new Text("▦");

        dashboardIcon.setStyle(
                "-fx-font-size: 29px;" +
                "-fx-fill: #333333;");

        Text dashboardText = new Text("Dashboard");

        dashboardText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #333333;");

        HBox dashboardContent =
                new HBox(
                        16,
                        dashboardIcon,
                        dashboardText);

        dashboardContent.setAlignment(
                Pos.CENTER_LEFT);

        Button dashboardButton = new Button();

        dashboardButton.setGraphic(
                dashboardContent);

        dashboardButton.setPrefWidth(244);
        dashboardButton.setPrefHeight(51);

        dashboardButton.setAlignment(
                Pos.CENTER_LEFT);

        dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        dashboardButton.setOnMouseEntered(e -> {

            dashboardButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        dashboardButton.setOnMouseExited(e -> {

            dashboardButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // ORDERS BUTTON
        // ============================================================

        Text ordersIcon = new Text("🛒");

        ordersIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #555555;");

        Text ordersText = new Text("Orders");

        ordersText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox ordersContent =
                new HBox(
                        13,
                        ordersIcon,
                        ordersText);

        ordersContent.setAlignment(
                Pos.CENTER_LEFT);

        Button ordersButton = new Button();

        ordersButton.setGraphic(
                ordersContent);

        ordersButton.setPrefWidth(244);
        ordersButton.setPrefHeight(51);

        ordersButton.setAlignment(
                Pos.CENTER_LEFT);

        ordersButton.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;");

        // ============================================================
        // INVENTORY BUTTON
        // ============================================================

        Text inventoryIcon = new Text("📋");

        inventoryIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #555555;");

        Text inventoryText = new Text("Inventory");

        inventoryText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox inventoryContent =
                new HBox(
                        15,
                        inventoryIcon,
                        inventoryText);

        inventoryContent.setAlignment(
                Pos.CENTER_LEFT);

        Button inventoryButton = new Button();

        inventoryButton.setGraphic(
                inventoryContent);

        inventoryButton.setPrefWidth(244);
        inventoryButton.setPrefHeight(51);

        inventoryButton.setAlignment(
                Pos.CENTER_LEFT);

        inventoryButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        inventoryButton.setOnMouseEntered(e -> {

            inventoryButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        inventoryButton.setOnMouseExited(e -> {

            inventoryButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // OFFERS BUTTON
        // ============================================================

        Text offersIcon = new Text("🎁");

        offersIcon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-fill: #555555;");

        Text offersText = new Text("Offers");

        offersText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox offersContent =
                new HBox(
                        14,
                        offersIcon,
                        offersText);

        offersContent.setAlignment(
                Pos.CENTER_LEFT);

        Button offersButton = new Button();

        offersButton.setGraphic(
                offersContent);

        offersButton.setPrefWidth(244);
        offersButton.setPrefHeight(51);

        offersButton.setAlignment(
                Pos.CENTER_LEFT);

        offersButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        offersButton.setOnMouseEntered(e -> {

            offersButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        offersButton.setOnMouseExited(e -> {

            offersButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // ANALYTICS BUTTON
        // ============================================================

        Text analyticsIcon = new Text("📊");

        analyticsIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #555555;");

        Text analyticsText = new Text("Analytics");

        analyticsText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox analyticsContent =
                new HBox(
                        15,
                        analyticsIcon,
                        analyticsText);

        analyticsContent.setAlignment(
                Pos.CENTER_LEFT);

        Button analyticsButton = new Button();

        analyticsButton.setGraphic(
                analyticsContent);

        analyticsButton.setPrefWidth(244);
        analyticsButton.setPrefHeight(51);

        analyticsButton.setAlignment(
                Pos.CENTER_LEFT);

        analyticsButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        analyticsButton.setOnMouseEntered(e -> {

            analyticsButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        analyticsButton.setOnMouseExited(e -> {

            analyticsButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // SETTINGS BUTTON
        // ============================================================

        Text settingsIcon = new Text("⚙");

        settingsIcon.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #555555;");

        Text settingsText = new Text("Settings");

        settingsText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox settingsContent =
                new HBox(
                        15,
                        settingsIcon,
                        settingsText);

        settingsContent.setAlignment(
                Pos.CENTER_LEFT);

        Button settingsButton = new Button();

        settingsButton.setGraphic(
                settingsContent);

        settingsButton.setPrefWidth(244);
        settingsButton.setPrefHeight(51);

        settingsButton.setAlignment(
                Pos.CENTER_LEFT);

        settingsButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        settingsButton.setOnMouseEntered(e -> {

            settingsButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        settingsButton.setOnMouseExited(e -> {

            settingsButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // SUPPORT BUTTON
        // ============================================================

        Text supportIcon = new Text("?");

        supportIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #555555;");

        Text supportText = new Text("Support");

        supportText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox supportContent =
                new HBox(
                        20,
                        supportIcon,
                        supportText);

        supportContent.setAlignment(
                Pos.CENTER_LEFT);

        Button supportButton = new Button();

        supportButton.setGraphic(
                supportContent);

        supportButton.setPrefWidth(244);
        supportButton.setPrefHeight(51);

        supportButton.setAlignment(
                Pos.CENTER_LEFT);

        supportButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        supportButton.setOnMouseEntered(e -> {

            supportButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        supportButton.setOnMouseExited(e -> {

            supportButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // SIDEBAR MENU
        // ============================================================

        VBox sidebarMenu =
                new VBox(
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

        VBox logoutBox =
                Constants.logoutBox();

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
        // PREPARING ORDERS
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

        Text pageTitle =
                new Text("Orders Management");

        pageTitle.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #1E1E24;");

        Text pageSubtitle =
                new Text(
                        "Manage incoming requests and active deliveries.");

        pageSubtitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-fill: #604D43;");

        VBox titleBox =
                new VBox(
                        5,
                        pageTitle,
                        pageSubtitle);

        // ============================================================
        // MANUAL ORDER BUTTON
        // ============================================================

        Button manualOrderButton =
                new Button("+  Manual Order");

        manualOrderButton.setPrefWidth(150);
        manualOrderButton.setPrefHeight(50);

        manualOrderButton.setStyle(
                "-fx-background-color: #B94F00;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 25px;" +
                "-fx-cursor: hand;");

        manualOrderButton.setOnMouseEntered(e -> {

            manualOrderButton.setStyle(
                    "-fx-background-color: #963F00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 25px;" +
                    "-fx-cursor: hand;");
        });

        manualOrderButton.setOnMouseExited(e -> {

            manualOrderButton.setStyle(
                    "-fx-background-color: #B94F00;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 15px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 25px;" +
                    "-fx-cursor: hand;");
        });

        HBox titleRow =
                new HBox(
                        titleBox,
                        manualOrderButton);

        titleRow.setAlignment(
                Pos.CENTER_LEFT);

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS);

        manualOrderButton.setOnAction(e -> {

            System.out.println(
                    "Manual Order clicked");
        });

        // ============================================================
        // STATUS BUTTONS
        // ============================================================

        HBox statusBar = new HBox();

        statusBar.setAlignment(
                Pos.CENTER);

        statusBar.setPrefHeight(75);

        statusBar.setSpacing(0);

        // ============================================================
        // NEW ORDER
        // ============================================================

        Button newOrderButton =
                Constants.createStatusButton(
                        "New Order");

        // ============================================================
        // PREPARING
        // ============================================================

        Button preparingButton =
                Constants.createStatusButton(
                        "Preparing");

        // ============================================================
        // READY
        // ============================================================

        Button readyButton =
                Constants.createStatusButton(
                        "✓ Ready");

        // ============================================================
        // OUT FOR DELIVERY
        // ============================================================

        Button deliveryButton =
                Constants.createStatusButton(
                        "Out for Delivery");

        // ============================================================
        // COMPLETED
        // ============================================================

        Button completedButton =
                Constants.createStatusButton(
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
        // PREPARING SELECTED BY DEFAULT
        // ============================================================

        Constants.setSelectedStatusButton(
                preparingButton);

        // ============================================================
        // STATUS BUTTON ACTIONS
        // ============================================================

        newOrderButton.setOnAction(e -> {

            System.out.println("New Order clicked");
            Main.primaryStage.setScene(ShopkeeperOrderNew.ordersScene());

        });

        preparingButton.setOnAction(e -> {

            System.out.println("Preparing clicked");
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

        VBox orderList =
                new VBox(20);

        orderList.setPadding(
                new Insets(10, 0, 20, 0));

        // ============================================================
        // ORDER #4592
        // ============================================================

        VBox order4592 =
                createPreparingOrderCard(
                        "#4592",
                        "15M ELAPSED",
                        "Sarah Jenkins",
                        "2.4 km away",
                        "2x",
                        "Organic Avocado Toast",
                        "$24.00",
                        "1x",
                        "Cold Brew Coffee",
                        "$5.50",
                        "Special Note: No cilantro please!",
                        "$29.50");

        // ============================================================
        // ORDER #4593
        // ============================================================

        VBox order4593 =
                createPreparingOrderCard(
                        "#4593",
                        "4M ELAPSED",
                        "Michael Chen",
                        "1.1 km away",
                        "1x",
                        "Sourdough Loaf",
                        "$8.00",
                        "1x",
                        "Artisanal Butter",
                        "$6.50",
                        "",
                        "$14.50");

        orderList.getChildren().addAll(
                order4592,
                order4593);

        // ============================================================
        // SCROLL PANE
        // ONLY ORDER LIST SCROLLS
        // ============================================================

        ScrollPane orderScrollPane =
                new ScrollPane(
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

        VBox footerBox =
                Constants.footer();

        borderPane.setBottom(
                footerBox);

        // ============================================================
        // SCENE
        // ============================================================

        Scene ordersScene =
                new Scene(
                        borderPane,
                        1280,
                        650);

        ordersScene.setFill(
                Color.web("#F8F7FC"));

        return ordersScene;
    }














    // ================================================================
    // CREATE PREPARING ORDER CARD
    // ================================================================

    private static VBox createPreparingOrderCard(
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

        // ============================================================
        // MAIN CARD
        // ============================================================

        HBox card =
                new HBox();

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

        DropShadow shadow =
                new DropShadow();

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

        // ============================================================
        // ORANGE LEFT BORDER
        // ============================================================

        VBox orangeBorder =
                new VBox();

        orangeBorder.setPrefWidth(4);

        orangeBorder.setStyle(
                "-fx-background-color: #B94F00;" +
                "-fx-background-radius: 12px 0 0 12px;");

        // ============================================================
        // LEFT CONTENT
        // ============================================================

        VBox leftContent =
                new VBox(10);

        leftContent.setPadding(
                new Insets(
                        20,
                        18,
                        18,
                        20));

        // ============================================================
        // ORDER NUMBER + PREPARING STATUS
        // ============================================================

        Text orderText =
                new Text(orderNumber);

        orderText.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #202027;");

        Text preparingText =
                new Text(
                        "PREPARING • " + elapsedTime);

        preparingText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #3A2418;" +
                "-fx-background-color: #FF6900;" +
                "-fx-background-radius: 6px;" +
                "-fx-padding: 9px 10px;");

        HBox orderHeader =
                new HBox(
                        12,
                        orderText,
                        preparingText);

        orderHeader.setAlignment(
                Pos.CENTER_LEFT);

        // ============================================================
        // CUSTOMER INFORMATION
        // ============================================================

        Text customerIcon =
                new Text("♙");

        customerIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #604D43;");

        Text customerText =
                new Text(customerName);

        customerText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #604D43;");

        Text dot =
                new Text("•");

        dot.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #B8B5B5;");

        Text locationIcon =
                new Text("⌖");

        locationIcon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-fill: #604D43;");

        Text distanceText =
                new Text(distance);

        distanceText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #604D43;");

        HBox customerRow =
                new HBox(
                        10,
                        customerIcon,
                        customerText,
                        dot,
                        locationIcon,
                        distanceText);

        customerRow.setAlignment(
                Pos.CENTER_LEFT);

        // ============================================================
        // PRODUCTS BOX
        // ============================================================

        VBox productsBox =
                new VBox(7);

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

        HBox productRow1 =
                new HBox();

        Text qty1 =
                new Text(quantity1);

        qty1.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #22222A;");

        Text productName1 =
                new Text(product1);

        productName1.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #292931;");

        Text productPrice1 =
                new Text(price1);

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

        HBox productRow2 =
                new HBox();

        Text qty2 =
                new Text(quantity2);

        qty2.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #22222A;");

        Text productName2 =
                new Text(product2);

        productName2.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #292931;");

        Text productPrice2 =
                new Text(price2);

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
        // SPECIAL NOTE
        // ============================================================

        if (!specialNote.isEmpty()) {

            Text noteText =
                    new Text(specialNote);

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

        // ============================================================
        // RIGHT CONTENT
        // ============================================================

        VBox rightContent =
                new VBox();

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

        // ============================================================
        // TOTAL AMOUNT
        // ============================================================

        Text totalLabel =
                new Text("Total Amount");

        totalLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #604D43;");

        Text total =
                new Text(totalAmount);

        total.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #202027;");

        VBox amountBox =
                new VBox(
                        3,
                        totalLabel,
                        total);

        amountBox.setAlignment(
                Pos.CENTER_RIGHT);

        // ============================================================
        // MARK AS READY BUTTON
        // ============================================================

        Button readyButton =
                new Button("✓  Mark as Ready");

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

            System.out.println(
                    orderNumber +
                    " - Mark as Ready clicked");
        });

        // ============================================================
        // CANCEL BUTTON
        // ============================================================

        Button cancelButton =
                new Button("×  Cancel Order");

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

            System.out.println(
                    orderNumber +
                    " - Cancel Order clicked");
        });

        // ============================================================
        // BUTTON BOX
        // ============================================================

        VBox buttonsBox =
                new VBox(
                        10,
                        readyButton,
                        cancelButton);

        buttonsBox.setAlignment(
                Pos.CENTER);

        // ============================================================
        // RIGHT CONTENT
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
                orangeBorder,
                leftContent,
                rightContent);

        // ============================================================
        // WRAPPER
        // ============================================================

        VBox wrapper =
                new VBox(card);

        return wrapper;
    }
}
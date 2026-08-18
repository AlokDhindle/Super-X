package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ShopkeeperInventory {

    public static Scene inventoryScene() {

        // ================================================================
        // FIXED STAGE DIMENSIONS
        // ================================================================

        final double STAGE_WIDTH = 1280;
        final double STAGE_HEIGHT = 650;

        final double HEADER_HEIGHT = 70;
        final double FOOTER_HEIGHT = 60;
        final double SIDEBAR_WIDTH = 260;

        final double CENTER_WIDTH =
                STAGE_WIDTH - SIDEBAR_WIDTH;

        final double CENTER_HEIGHT =
                STAGE_HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT;

        BorderPane borderPane = new BorderPane();

        // ================================================================
        // MAIN BORDER PANE
        // ================================================================

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
            profileBox.setAlignment(Pos.CENTER_LEFT);
            profileBox.setPadding(new Insets(30, 20, 30, 20));

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
                HBox dashboardContent = new HBox(
                                16,
                                dashboardIcon,
                                dashboardText);
                dashboardContent.setAlignment(Pos.CENTER_LEFT);
                Button dashboardButton = new Button();
                dashboardButton.setGraphic(dashboardContent);
                dashboardButton.setPrefWidth(244);
                dashboardButton.setPrefHeight(51);
                dashboardButton.setAlignment(Pos.CENTER_LEFT);
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
                HBox ordersContent = new HBox(
                                13,
                                ordersIcon,
                                ordersText);
                ordersContent.setAlignment(Pos.CENTER_LEFT);
                Button ordersButton = new Button();
                ordersButton.setGraphic(ordersContent);
                ordersButton.setPrefWidth(244);
                ordersButton.setPrefHeight(51);
                ordersButton.setAlignment(Pos.CENTER_LEFT);
                ordersButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");

                ordersButton.setOnMouseEntered(e -> {
                        ordersButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                ordersButton.setOnMouseExited(e -> {
                        ordersButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

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
                HBox inventoryContent = new HBox(
                                15,
                                inventoryIcon,
                                inventoryText);
                inventoryContent.setAlignment(Pos.CENTER_LEFT);
                Button inventoryButton = new Button();
                inventoryButton.setGraphic(inventoryContent);
                inventoryButton.setPrefWidth(244);
                inventoryButton.setPrefHeight(51);
                inventoryButton.setAlignment(Pos.CENTER_LEFT);
                inventoryButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");


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
                HBox offersContent = new HBox(
                                14,
                                offersIcon,
                                offersText);
                offersContent.setAlignment(Pos.CENTER_LEFT);
                Button offersButton = new Button();
                offersButton.setGraphic(offersContent);
                offersButton.setPrefWidth(244);
                offersButton.setPrefHeight(51);
                offersButton.setAlignment(Pos.CENTER_LEFT);
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
                HBox analyticsContent = new HBox(
                                15,
                                analyticsIcon,
                                analyticsText);
                analyticsContent.setAlignment(Pos.CENTER_LEFT);
                Button analyticsButton = new Button();
                analyticsButton.setGraphic(analyticsContent);
                analyticsButton.setPrefWidth(244);
                analyticsButton.setPrefHeight(51);
                analyticsButton.setAlignment(Pos.CENTER_LEFT);
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
                HBox settingsContent = new HBox(
                                15,
                                settingsIcon,
                                settingsText);
                settingsContent.setAlignment(Pos.CENTER_LEFT);
                Button settingsButton = new Button();
                settingsButton.setGraphic(settingsContent);
                settingsButton.setPrefWidth(244);
                settingsButton.setPrefHeight(51);
                settingsButton.setAlignment(Pos.CENTER_LEFT);
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
                HBox supportContent = new HBox(
                                20,
                                supportIcon,
                                supportText);
                supportContent.setAlignment(Pos.CENTER_LEFT);
                Button supportButton = new Button();
                supportButton.setGraphic(supportContent);
                supportButton.setPrefWidth(244);
                supportButton.setPrefHeight(51);
                supportButton.setAlignment(Pos.CENTER_LEFT);
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

                VBox sidebarMenu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                sidebarMenu.setPadding(new Insets(0, 8, 0, 8));

                // ============================================================
                // LOGOUT BUTTON
                // ============================================================


                VBox logoutBox = Constants.logoutBox();

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

                        System.out.println("Dashboard clicked");
                        Main.primaryStage.setScene(ShopkeeperDashboard.dashboardScene());

                });

                ordersButton.setOnAction(e -> {

                        System.out.println("Orders clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderReady.ordersScene());


                });

                inventoryButton.setOnAction(e -> {

                        System.out.println("Inventory clicked");

                });

                offersButton.setOnAction(e -> {

                        System.out.println("Offers clicked");
                        Main.primaryStage.setScene(ShopkeeperOffers.offersScene());
                });

                analyticsButton.setOnAction(e -> {

                        System.out.println("Analytics clicked");
                        Main.primaryStage.setScene(ShopkeeperAnalytics.analyticsScene());

                });

                settingsButton.setOnAction(e -> {

                        System.out.println("Settings clicked");
                        Main.primaryStage.setScene(ShopkeeperSettings.settingsScene());

                });

                supportButton.setOnAction(e -> {

                        System.out.println("Support clicked");
                        Main.primaryStage.setScene(ShopkeeperSupport.supportScene());

                });





        // ================================================================
        // FOOTER
        // ================================================================

                VBox footerBox = Constants.footer();
                // SET BOTTOM
                borderPane.setBottom(footerBox);

























        // ================================================================
        // CENTER AREA
        // ================================================================

        VBox centerContent =
                new VBox();

        centerContent.setPrefWidth(
                CENTER_WIDTH);

        centerContent.setMinWidth(
                CENTER_WIDTH);

        centerContent.setMaxWidth(
                CENTER_WIDTH);

        centerContent.setPrefHeight(
                CENTER_HEIGHT);

        centerContent.setPadding(
                new Insets(
                        20));

        centerContent.setSpacing(
                14);

        centerContent.setStyle(
                "-fx-background-color: #F8F7FC;");

        // ================================================================
        // PAGE TITLE
        // ================================================================

        Text inventoryTitle =
                new Text(
                        "Inventory & Stock");

        inventoryTitle.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text inventorySubtitle =
                new Text(
                        "Manage your catalog, stock levels, and automated restocking.");

        inventorySubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;");

        VBox titleBox =
                new VBox(
                        4,
                        inventoryTitle,
                        inventorySubtitle);

        titleBox.setPrefHeight(55);

        titleBox.setMinHeight(55);

        titleBox.setMaxHeight(55);

        // ================================================================
        // TOP BUTTONS
        // ================================================================

        Button bulkUploadButton =
                new Button(
                        "⇧  Bulk Upload");

        bulkUploadButton.setPrefWidth(125);
        bulkUploadButton.setMinWidth(125);
        bulkUploadButton.setMaxWidth(125);

        bulkUploadButton.setPrefHeight(40);

        bulkUploadButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #B9A9A1;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #49382F;" +
                "-fx-cursor: hand;");

        Button newProductButton =
                new Button(
                        "+  New Product");

        newProductButton.setPrefWidth(135);
        newProductButton.setMinWidth(135);
        newProductButton.setMaxWidth(135);

        newProductButton.setPrefHeight(40);

        newProductButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;");

        HBox topButtons =
                new HBox(
                        10,
                        bulkUploadButton,
                        newProductButton);

        topButtons.setAlignment(
                Pos.CENTER_RIGHT);

        HBox titleRow =
                new HBox(
                        titleBox,
                        topButtons);

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS);

        titleRow.setPrefHeight(55);

        titleRow.setMinHeight(55);

        titleRow.setMaxHeight(55);

        titleRow.setAlignment(
                Pos.CENTER);

        // ================================================================
        // AUTO RESTOCK CARD
        // ================================================================

        Circle restockCircle =
                new Circle(22);

        restockCircle.setFill(
                Color.web("#FFE1D1"));

        Text restockIcon =
                new Text("✦");

        restockIcon.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #A94408;");

        StackPane restockIconBox =
                new StackPane(
                        restockCircle,
                        restockIcon);

        restockIconBox.setPrefWidth(44);
        restockIconBox.setMinWidth(44);
        restockIconBox.setMaxWidth(44);

        restockIconBox.setPrefHeight(44);

        Text restockTitle =
                new Text(
                        "Auto-Restock Recommendation");

        restockTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #1D1D1D;");

        Text restockDescription =
                new Text(
                        "Based on your weekly sales trends, 12 items are projected to run out before the weekend rush.");

        restockDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #666666;");

        VBox restockText =
                new VBox(
                        4,
                        restockTitle,
                        restockDescription);

        HBox.setHgrow(
                restockText,
                Priority.ALWAYS);

        Button reviewApproveButton =
                new Button(
                        "Review & Approve");

        reviewApproveButton.setPrefWidth(135);
        reviewApproveButton.setMinWidth(135);
        reviewApproveButton.setMaxWidth(135);

        reviewApproveButton.setPrefHeight(38);

        reviewApproveButton.setStyle(
                "-fx-background-color: #ECEAF0;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;");

        HBox restockCard =
                new HBox(
                        15,
                        restockIconBox,
                        restockText,
                        reviewApproveButton);

        restockCard.setPrefHeight(82);

        restockCard.setMinHeight(82);

        restockCard.setMaxHeight(82);

        restockCard.setPadding(
                new Insets(
                        12,
                        16,
                        12,
                        16));

        restockCard.setAlignment(
                Pos.CENTER_LEFT);

        restockCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #C85B22;" +
                "-fx-border-width: 1.5px;" +
                "-fx-border-radius: 11px;" +
                "-fx-background-radius: 11px;");

        // ================================================================
        // SEARCH + FILTER
        // ================================================================

        Text searchIcon =
                new Text("⌕");

        searchIcon.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-fill: #555555;");

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search inventory by name, SKU...");

        searchField.setPrefWidth(300);

        searchField.setMinWidth(300);

        searchField.setMaxWidth(300);

        searchField.setPrefHeight(38);

        searchField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 5px;");

        HBox searchBox =
                new HBox(
                        7,
                        searchIcon,
                        searchField);

        searchBox.setPrefWidth(330);

        searchBox.setMinWidth(330);

        searchBox.setMaxWidth(330);

        searchBox.setPrefHeight(42);

        searchBox.setMinHeight(42);

        searchBox.setMaxHeight(42);

        searchBox.setAlignment(
                Pos.CENTER_LEFT);

        searchBox.setPadding(
                new Insets(
                        0,
                        8,
                        0,
                        10));

        searchBox.setStyle(
                "-fx-background-color: #FAF8FC;" +
                "-fx-border-color: #D9CCC6;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;");

        Text smartFilters =
                new Text(
                        "SMART FILTERS:");

        smartFilters.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        CheckBox outOfStock =
                new CheckBox(
                        "Out of Stock");

        outOfStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #333333;");

        CheckBox lowStock =
                new CheckBox(
                        "Low Stock");

        lowStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #333333;");

        CheckBox nearExpiry =
                new CheckBox(
                        "Near Expiry");

        nearExpiry.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #333333;");

        HBox filterOne =
                new HBox(
                        outOfStock);

        filterOne.setAlignment(
                Pos.CENTER);

        filterOne.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9));

        filterOne.setStyle(
                "-fx-background-color: #FFF4F2;" +
                "-fx-border-color: #E7D4CF;" +
                "-fx-border-radius: 15px;" +
                "-fx-background-radius: 15px;");

        HBox filterTwo =
                new HBox(
                        lowStock);

        filterTwo.setAlignment(
                Pos.CENTER);

        filterTwo.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9));

        filterTwo.setStyle(
                "-fx-background-color: #FFF0E8;" +
                "-fx-border-color: #E7C4B0;" +
                "-fx-border-radius: 15px;" +
                "-fx-background-radius: 15px;");

        HBox filterThree =
                new HBox(
                        nearExpiry);

        filterThree.setAlignment(
                Pos.CENTER);

        filterThree.setPadding(
                new Insets(
                        5,
                        9,
                        5,
                        9));

        filterThree.setStyle(
                "-fx-background-color: #FFF0E2;" +
                "-fx-border-color: #E4B28C;" +
                "-fx-border-radius: 15px;" +
                "-fx-background-radius: 15px;");

        HBox filterBox =
                new HBox(
                        8,
                        smartFilters,
                        filterOne,
                        filterTwo,
                        filterThree);

        filterBox.setAlignment(
                Pos.CENTER_LEFT);

        HBox searchFilterRow =
                new HBox(
                        15,
                        searchBox,
                        filterBox);

        searchFilterRow.setPrefHeight(50);

        searchFilterRow.setMinHeight(50);

        searchFilterRow.setMaxHeight(50);

        searchFilterRow.setAlignment(
                Pos.CENTER_LEFT);

        searchFilterRow.setPadding(
                new Insets(
                        4,
                        8,
                        4,
                        8));

        searchFilterRow.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;");

        // ================================================================
        // TABLE DIMENSIONS
        //
        // Center usable width:
        //
        // 1020 - 40 padding = 980
        //
        // Table columns:
        //
        // 230 + 90 + 150 + 85 + 120 + 110 + 55 = 840
        //
        // Remaining space = controlled empty space.
        // ================================================================

        final double ITEM_WIDTH = 230;
        final double CATEGORY_WIDTH = 90;
        final double STOCK_WIDTH = 150;
        final double PRICE_WIDTH = 85;
        final double EXPIRY_WIDTH = 120;
        final double STATUS_WIDTH = 110;
        final double ACTION_WIDTH = 55;

        // ================================================================
        // TABLE HEADER
        // ================================================================

        Text itemHeader =
                new Text("ITEM NAME");

        itemHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane itemHeaderBox =
                new StackPane(
                        itemHeader);

        itemHeaderBox.setPrefWidth(
                ITEM_WIDTH);

        itemHeaderBox.setMinWidth(
                ITEM_WIDTH);

        itemHeaderBox.setMaxWidth(
                ITEM_WIDTH);

        Text categoryHeader =
                new Text("CATEGORY");

        categoryHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane categoryHeaderBox =
                new StackPane(
                        categoryHeader);

        categoryHeaderBox.setPrefWidth(
                CATEGORY_WIDTH);

        categoryHeaderBox.setMinWidth(
                CATEGORY_WIDTH);

        categoryHeaderBox.setMaxWidth(
                CATEGORY_WIDTH);

        Text stockHeader =
                new Text("STOCK / LIMIT");

        stockHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane stockHeaderBox =
                new StackPane(
                        stockHeader);

        stockHeaderBox.setPrefWidth(
                STOCK_WIDTH);

        stockHeaderBox.setMinWidth(
                STOCK_WIDTH);

        stockHeaderBox.setMaxWidth(
                STOCK_WIDTH);

        Text priceHeader =
                new Text("UNIT PRICE");

        priceHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane priceHeaderBox =
                new StackPane(
                        priceHeader);

        priceHeaderBox.setPrefWidth(
                PRICE_WIDTH);

        priceHeaderBox.setMinWidth(
                PRICE_WIDTH);

        priceHeaderBox.setMaxWidth(
                PRICE_WIDTH);

        Text expiryHeader =
                new Text("EXPIRY");

        expiryHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane expiryHeaderBox =
                new StackPane(
                        expiryHeader);

        expiryHeaderBox.setPrefWidth(
                EXPIRY_WIDTH);

        expiryHeaderBox.setMinWidth(
                EXPIRY_WIDTH);

        expiryHeaderBox.setMaxWidth(
                EXPIRY_WIDTH);

        Text statusHeader =
                new Text("STATUS");

        statusHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane statusHeaderBox =
                new StackPane(
                        statusHeader);

        statusHeaderBox.setPrefWidth(
                STATUS_WIDTH);

        statusHeaderBox.setMinWidth(
                STATUS_WIDTH);

        statusHeaderBox.setMaxWidth(
                STATUS_WIDTH);

        Text actionHeader =
                new Text("ACTION");

        actionHeader.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;");

        StackPane actionHeaderBox =
                new StackPane(
                        actionHeader);

        actionHeaderBox.setPrefWidth(
                ACTION_WIDTH);

        actionHeaderBox.setMinWidth(
                ACTION_WIDTH);

        actionHeaderBox.setMaxWidth(
                ACTION_WIDTH);

        HBox tableHeader =
                new HBox(
                        itemHeaderBox,
                        categoryHeaderBox,
                        stockHeaderBox,
                        priceHeaderBox,
                        expiryHeaderBox,
                        statusHeaderBox,
                        actionHeaderBox);

        tableHeader.setPrefHeight(38);

        tableHeader.setMinHeight(38);

        tableHeader.setMaxHeight(38);

        tableHeader.setAlignment(
                Pos.CENTER_LEFT);

        tableHeader.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        tableHeader.setStyle(
                "-fx-background-color: #F7F5F8;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-width: 0 0 1px 0;");

        // ================================================================
        // ROW 1
        // ================================================================

        Text eggsIcon =
                new Text("◉");

        eggsIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #666666;");

        StackPane eggsIconBox =
                new StackPane(
                        eggsIcon);

        eggsIconBox.setPrefWidth(38);
        eggsIconBox.setMinWidth(38);
        eggsIconBox.setMaxWidth(38);

        eggsIconBox.setPrefHeight(38);

        eggsIconBox.setStyle(
                "-fx-background-color: #E8E6E9;" +
                "-fx-background-radius: 5px;");

        Text eggsName =
                new Text(
                        "Organic Brown Eggs");

        eggsName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;");

        Text eggsSku =
                new Text(
                        "SKU: DAIR-001");

        eggsSku.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-fill: #666666;");

        VBox eggsNameBox =
                new VBox(
                        2,
                        eggsName,
                        eggsSku);

        HBox eggsItem =
                new HBox(
                        9,
                        eggsIconBox,
                        eggsNameBox);

        eggsItem.setAlignment(
                Pos.CENTER_LEFT);

        eggsItem.setPrefWidth(
                ITEM_WIDTH);

        eggsItem.setMinWidth(
                ITEM_WIDTH);

        eggsItem.setMaxWidth(
                ITEM_WIDTH);

        Text eggsCategoryText =
                new Text("Dairy");

        eggsCategoryText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5F514A;");

        StackPane eggsCategory =
                new StackPane(
                        eggsCategoryText);

        eggsCategory.setPrefWidth(
                CATEGORY_WIDTH);

        eggsCategory.setMinWidth(
                CATEGORY_WIDTH);

        eggsCategory.setMaxWidth(
                CATEGORY_WIDTH);

        Text eggsStock =
                new Text(
                        "12 / 30 pkgs");

        eggsStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #333333;");

        Region eggsProgressBack =
                new Region();

        eggsProgressBack.setPrefWidth(115);
        eggsProgressBack.setPrefHeight(6);

        eggsProgressBack.setStyle(
                "-fx-background-color: #E3E1E5;" +
                "-fx-background-radius: 10px;");

        Region eggsProgress =
                new Region();

        eggsProgress.setPrefWidth(48);
        eggsProgress.setPrefHeight(6);

        eggsProgress.setStyle(
                "-fx-background-color: #F29B72;" +
                "-fx-background-radius: 10px;");

        StackPane eggsProgressBox =
                new StackPane(
                        eggsProgressBack,
                        eggsProgress);

        eggsProgressBox.setAlignment(
                Pos.CENTER_LEFT);

        VBox eggsStockBox =
                new VBox(
                        4,
                        eggsStock,
                        eggsProgressBox);

        eggsStockBox.setPrefWidth(
                STOCK_WIDTH);

        eggsStockBox.setMinWidth(
                STOCK_WIDTH);

        eggsStockBox.setMaxWidth(
                STOCK_WIDTH);

        Text eggsPrice =
                new Text("$4.99");

        eggsPrice.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #222222;");

        StackPane eggsPriceBox =
                new StackPane(
                        eggsPrice);

        eggsPriceBox.setPrefWidth(
                PRICE_WIDTH);

        eggsPriceBox.setMinWidth(
                PRICE_WIDTH);

        eggsPriceBox.setMaxWidth(
                PRICE_WIDTH);

        Text eggsExpiry =
                new Text(
                        "Oct 24, 2023");

        eggsExpiry.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #333333;");

        StackPane eggsExpiryBox =
                new StackPane(
                        eggsExpiry);

        eggsExpiryBox.setPrefWidth(
                EXPIRY_WIDTH);

        eggsExpiryBox.setMinWidth(
                EXPIRY_WIDTH);

        eggsExpiryBox.setMaxWidth(
                EXPIRY_WIDTH);

        Text eggsStatusText =
                new Text(
                        "Low Stock");

        eggsStatusText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A94408;");

        StackPane eggsStatus =
                new StackPane(
                        eggsStatusText);

        eggsStatus.setPrefWidth(
                STATUS_WIDTH);

        eggsStatus.setMinWidth(
                STATUS_WIDTH);

        eggsStatus.setMaxWidth(
                STATUS_WIDTH);

        eggsStatus.setStyle(
                "-fx-background-color: #FFF0E8;" +
                "-fx-background-radius: 12px;");

        Button eggsAction =
                new Button("⋮");

        eggsAction.setPrefWidth(
                ACTION_WIDTH);

        eggsAction.setMinWidth(
                ACTION_WIDTH);

        eggsAction.setMaxWidth(
                ACTION_WIDTH);

        eggsAction.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 17px;" +
                "-fx-cursor: hand;");

        HBox eggsRow =
                new HBox(
                        eggsItem,
                        eggsCategory,
                        eggsStockBox,
                        eggsPriceBox,
                        eggsExpiryBox,
                        eggsStatus,
                        eggsAction);

        eggsRow.setPrefHeight(58);

        eggsRow.setMinHeight(58);

        eggsRow.setMaxHeight(58);

        eggsRow.setAlignment(
                Pos.CENTER_LEFT);

        eggsRow.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        eggsRow.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E7DFDA;" +
                "-fx-border-width: 0 0 1px 0;");

        // ================================================================
        // ROW 2
        // ================================================================

        Text breadIcon =
                new Text("♒");

        breadIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #666666;");

        StackPane breadIconBox =
                new StackPane(
                        breadIcon);

        breadIconBox.setPrefWidth(38);
        breadIconBox.setMinWidth(38);
        breadIconBox.setMaxWidth(38);

        breadIconBox.setPrefHeight(38);

        breadIconBox.setStyle(
                "-fx-background-color: #E8E6E9;" +
                "-fx-background-radius: 5px;");

        Text breadName =
                new Text(
                        "Whole Wheat Bread");

        breadName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;");

        Text breadSku =
                new Text(
                        "SKU: BAK-042");

        breadSku.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-fill: #666666;");

        VBox breadNameBox =
                new VBox(
                        2,
                        breadName,
                        breadSku);

        HBox breadItem =
                new HBox(
                        9,
                        breadIconBox,
                        breadNameBox);

        breadItem.setAlignment(
                Pos.CENTER_LEFT);

        breadItem.setPrefWidth(
                ITEM_WIDTH);

        breadItem.setMinWidth(
                ITEM_WIDTH);

        breadItem.setMaxWidth(
                ITEM_WIDTH);

        Text breadCategoryText =
                new Text("Bakery");

        breadCategoryText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5F514A;");

        StackPane breadCategory =
                new StackPane(
                        breadCategoryText);

        breadCategory.setPrefWidth(
                CATEGORY_WIDTH);

        breadCategory.setMinWidth(
                CATEGORY_WIDTH);

        breadCategory.setMaxWidth(
                CATEGORY_WIDTH);

        Text breadStock =
                new Text(
                        "45 / 20 loaves");

        breadStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #333333;");

        Region breadProgressBack =
                new Region();

        breadProgressBack.setPrefWidth(115);
        breadProgressBack.setPrefHeight(6);

        breadProgressBack.setStyle(
                "-fx-background-color: #E3E1E5;" +
                "-fx-background-radius: 10px;");

        Region breadProgress =
                new Region();

        breadProgress.setPrefWidth(105);
        breadProgress.setPrefHeight(6);

        breadProgress.setStyle(
                "-fx-background-color: #A94408;" +
                "-fx-background-radius: 10px;");

        StackPane breadProgressBox =
                new StackPane(
                        breadProgressBack,
                        breadProgress);

        breadProgressBox.setAlignment(
                Pos.CENTER_LEFT);

        VBox breadStockBox =
                new VBox(
                        4,
                        breadStock,
                        breadProgressBox);

        breadStockBox.setPrefWidth(
                STOCK_WIDTH);

        breadStockBox.setMinWidth(
                STOCK_WIDTH);

        breadStockBox.setMaxWidth(
                STOCK_WIDTH);

        Text breadPrice =
                new Text("$3.49");

        breadPrice.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #222222;");

        StackPane breadPriceBox =
                new StackPane(
                        breadPrice);

        breadPriceBox.setPrefWidth(
                PRICE_WIDTH);

        breadPriceBox.setMinWidth(
                PRICE_WIDTH);

        breadPriceBox.setMaxWidth(
                PRICE_WIDTH);

        Text breadExpiry =
                new Text(
                        "Oct 14, 2023");

        breadExpiry.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A94408;");

        StackPane breadExpiryBox =
                new StackPane(
                        breadExpiry);

        breadExpiryBox.setPrefWidth(
                EXPIRY_WIDTH);

        breadExpiryBox.setMinWidth(
                EXPIRY_WIDTH);

        breadExpiryBox.setMaxWidth(
                EXPIRY_WIDTH);

        Text breadStatusText =
                new Text(
                        "Near Expiry");

        breadStatusText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A94408;");

        StackPane breadStatus =
                new StackPane(
                        breadStatusText);

        breadStatus.setPrefWidth(
                STATUS_WIDTH);

        breadStatus.setMinWidth(
                STATUS_WIDTH);

        breadStatus.setMaxWidth(
                STATUS_WIDTH);

        breadStatus.setStyle(
                "-fx-background-color: #FFF0E8;" +
                "-fx-background-radius: 12px;");

        Button breadAction =
                new Button("⋮");

        breadAction.setPrefWidth(
                ACTION_WIDTH);

        breadAction.setMinWidth(
                ACTION_WIDTH);

        breadAction.setMaxWidth(
                ACTION_WIDTH);

        breadAction.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 17px;" +
                "-fx-cursor: hand;");

        HBox breadRow =
                new HBox(
                        breadItem,
                        breadCategory,
                        breadStockBox,
                        breadPriceBox,
                        breadExpiryBox,
                        breadStatus,
                        breadAction);

        breadRow.setPrefHeight(58);

        breadRow.setMinHeight(58);

        breadRow.setMaxHeight(58);

        breadRow.setAlignment(
                Pos.CENTER_LEFT);

        breadRow.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        breadRow.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E7DFDA;" +
                "-fx-border-width: 0 0 1px 0;");

        // ================================================================
        // ROW 3
        // ================================================================

        Text milkIcon =
                new Text("▣");

        milkIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #666666;");

        StackPane milkIconBox =
                new StackPane(
                        milkIcon);

        milkIconBox.setPrefWidth(38);
        milkIconBox.setMinWidth(38);
        milkIconBox.setMaxWidth(38);

        milkIconBox.setPrefHeight(38);

        milkIconBox.setStyle(
                "-fx-background-color: #E8E6E9;" +
                "-fx-background-radius: 5px;");

        Text milkName =
                new Text(
                        "Almond Milk 1L");

        milkName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;");

        Text milkSku =
                new Text(
                        "SKU: DAIR-088");

        milkSku.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-fill: #666666;");

        VBox milkNameBox =
                new VBox(
                        2,
                        milkName,
                        milkSku);

        HBox milkItem =
                new HBox(
                        9,
                        milkIconBox,
                        milkNameBox);

        milkItem.setAlignment(
                Pos.CENTER_LEFT);

        milkItem.setPrefWidth(
                ITEM_WIDTH);

        milkItem.setMinWidth(
                ITEM_WIDTH);

        milkItem.setMaxWidth(
                ITEM_WIDTH);

        Text milkCategoryText =
                new Text("Dairy");

        milkCategoryText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5F514A;");

        StackPane milkCategory =
                new StackPane(
                        milkCategoryText);

        milkCategory.setPrefWidth(
                CATEGORY_WIDTH);

        milkCategory.setMinWidth(
                CATEGORY_WIDTH);

        milkCategory.setMaxWidth(
                CATEGORY_WIDTH);

        Text milkStock =
                new Text(
                        "0 / 50 btls");

        milkStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #A91D1D;");

        Region milkProgressBack =
                new Region();

        milkProgressBack.setPrefWidth(115);
        milkProgressBack.setPrefHeight(6);

        milkProgressBack.setStyle(
                "-fx-background-color: #E3E1E5;" +
                "-fx-background-radius: 10px;");

        StackPane milkProgressBox =
                new StackPane(
                        milkProgressBack);

        milkProgressBox.setAlignment(
                Pos.CENTER_LEFT);

        VBox milkStockBox =
                new VBox(
                        4,
                        milkStock,
                        milkProgressBox);

        milkStockBox.setPrefWidth(
                STOCK_WIDTH);

        milkStockBox.setMinWidth(
                STOCK_WIDTH);

        milkStockBox.setMaxWidth(
                STOCK_WIDTH);

        Text milkPrice =
                new Text("$5.20");

        milkPrice.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #222222;");

        StackPane milkPriceBox =
                new StackPane(
                        milkPrice);

        milkPriceBox.setPrefWidth(
                PRICE_WIDTH);

        milkPriceBox.setMinWidth(
                PRICE_WIDTH);

        milkPriceBox.setMaxWidth(
                PRICE_WIDTH);

        Text milkExpiry =
                new Text("-");

        milkExpiry.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #555555;");

        StackPane milkExpiryBox =
                new StackPane(
                        milkExpiry);

        milkExpiryBox.setPrefWidth(
                EXPIRY_WIDTH);

        milkExpiryBox.setMinWidth(
                EXPIRY_WIDTH);

        milkExpiryBox.setMaxWidth(
                EXPIRY_WIDTH);

        Text milkStatusText =
                new Text(
                        "Out of Stock");

        milkStatusText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A91D1D;");

        StackPane milkStatus =
                new StackPane(
                        milkStatusText);

        milkStatus.setPrefWidth(
                STATUS_WIDTH);

        milkStatus.setMinWidth(
                STATUS_WIDTH);

        milkStatus.setMaxWidth(
                STATUS_WIDTH);

        milkStatus.setStyle(
                "-fx-background-color: #FFDCDC;" +
                "-fx-background-radius: 12px;");

        Button milkAction =
                new Button("⋮");

        milkAction.setPrefWidth(
                ACTION_WIDTH);

        milkAction.setMinWidth(
                ACTION_WIDTH);

        milkAction.setMaxWidth(
                ACTION_WIDTH);

        milkAction.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 17px;" +
                "-fx-cursor: hand;");

        HBox milkRow =
                new HBox(
                        milkItem,
                        milkCategory,
                        milkStockBox,
                        milkPriceBox,
                        milkExpiryBox,
                        milkStatus,
                        milkAction);

        milkRow.setPrefHeight(58);

        milkRow.setMinHeight(58);

        milkRow.setMaxHeight(58);

        milkRow.setAlignment(
                Pos.CENTER_LEFT);

        milkRow.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        milkRow.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E7DFDA;" +
                "-fx-border-width: 0 0 1px 0;");

        // ================================================================
        // ROW 4
        // ================================================================

        Text avocadoIcon =
                new Text("●");

        avocadoIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #666666;");

        StackPane avocadoIconBox =
                new StackPane(
                        avocadoIcon);

        avocadoIconBox.setPrefWidth(38);
        avocadoIconBox.setMinWidth(38);
        avocadoIconBox.setMaxWidth(38);

        avocadoIconBox.setPrefHeight(38);

        avocadoIconBox.setStyle(
                "-fx-background-color: #E8E6E9;" +
                "-fx-background-radius: 5px;");

        Text avocadoName =
                new Text(
                        "Avocado");

        avocadoName.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;");

        Text avocadoSku =
                new Text(
                        "SKU: PROD-102");

        avocadoSku.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-fill: #666666;");

        VBox avocadoNameBox =
                new VBox(
                        2,
                        avocadoName,
                        avocadoSku);

        HBox avocadoItem =
                new HBox(
                        9,
                        avocadoIconBox,
                        avocadoNameBox);

        avocadoItem.setAlignment(
                Pos.CENTER_LEFT);

        avocadoItem.setPrefWidth(
                ITEM_WIDTH);

        avocadoItem.setMinWidth(
                ITEM_WIDTH);

        avocadoItem.setMaxWidth(
                ITEM_WIDTH);

        Text avocadoCategoryText =
                new Text("Produce");

        avocadoCategoryText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5F514A;");

        StackPane avocadoCategory =
                new StackPane(
                        avocadoCategoryText);

        avocadoCategory.setPrefWidth(
                CATEGORY_WIDTH);

        avocadoCategory.setMinWidth(
                CATEGORY_WIDTH);

        avocadoCategory.setMaxWidth(
                CATEGORY_WIDTH);

        Text avocadoStock =
                new Text(
                        "85 / 40 pcs");

        avocadoStock.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #333333;");

        Region avocadoProgressBack =
                new Region();

        avocadoProgressBack.setPrefWidth(115);
        avocadoProgressBack.setPrefHeight(6);

        avocadoProgressBack.setStyle(
                "-fx-background-color: #E3E1E5;" +
                "-fx-background-radius: 10px;");

        Region avocadoProgress =
                new Region();

        avocadoProgress.setPrefWidth(105);
        avocadoProgress.setPrefHeight(6);

        avocadoProgress.setStyle(
                "-fx-background-color: #B88B73;" +
                "-fx-background-radius: 10px;");

        StackPane avocadoProgressBox =
                new StackPane(
                        avocadoProgressBack,
                        avocadoProgress);

        avocadoProgressBox.setAlignment(
                Pos.CENTER_LEFT);

        VBox avocadoStockBox =
                new VBox(
                        4,
                        avocadoStock,
                        avocadoProgressBox);

        avocadoStockBox.setPrefWidth(
                STOCK_WIDTH);

        avocadoStockBox.setMinWidth(
                STOCK_WIDTH);

        avocadoStockBox.setMaxWidth(
                STOCK_WIDTH);

        Text avocadoPrice =
                new Text("$1.50");

        avocadoPrice.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #222222;");

        StackPane avocadoPriceBox =
                new StackPane(
                        avocadoPrice);

        avocadoPriceBox.setPrefWidth(
                PRICE_WIDTH);

        avocadoPriceBox.setMinWidth(
                PRICE_WIDTH);

        avocadoPriceBox.setMaxWidth(
                PRICE_WIDTH);

        Text avocadoExpiry =
                new Text(
                        "Oct 20, 2023");

        avocadoExpiry.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #333333;");

        StackPane avocadoExpiryBox =
                new StackPane(
                        avocadoExpiry);

        avocadoExpiryBox.setPrefWidth(
                EXPIRY_WIDTH);

        avocadoExpiryBox.setMinWidth(
                EXPIRY_WIDTH);

        avocadoExpiryBox.setMaxWidth(
                EXPIRY_WIDTH);

        Text avocadoStatusText =
                new Text(
                        "Healthy");

        avocadoStatusText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #333333;");

        StackPane avocadoStatus =
                new StackPane(
                        avocadoStatusText);

        avocadoStatus.setPrefWidth(
                STATUS_WIDTH);

        avocadoStatus.setMinWidth(
                STATUS_WIDTH);

        avocadoStatus.setMaxWidth(
                STATUS_WIDTH);

        avocadoStatus.setStyle(
                "-fx-background-color: #EAE8EC;" +
                "-fx-background-radius: 12px;");

        Button avocadoAction =
                new Button("⋮");

        avocadoAction.setPrefWidth(
                ACTION_WIDTH);

        avocadoAction.setMinWidth(
                ACTION_WIDTH);

        avocadoAction.setMaxWidth(
                ACTION_WIDTH);

        avocadoAction.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 17px;" +
                "-fx-cursor: hand;");

        HBox avocadoRow =
                new HBox(
                        avocadoItem,
                        avocadoCategory,
                        avocadoStockBox,
                        avocadoPriceBox,
                        avocadoExpiryBox,
                        avocadoStatus,
                        avocadoAction);

        avocadoRow.setPrefHeight(58);

        avocadoRow.setMinHeight(58);

        avocadoRow.setMaxHeight(58);

        avocadoRow.setAlignment(
                Pos.CENTER_LEFT);

        avocadoRow.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        avocadoRow.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E7DFDA;" +
                "-fx-border-width: 0 0 1px 0;");

        // ================================================================
        // TABLE BODY
        // ================================================================

        VBox tableBody =
                new VBox(
                        eggsRow,
                        breadRow,
                        milkRow,
                        avocadoRow);

        tableBody.setPrefHeight(232);

        tableBody.setMinHeight(232);

        tableBody.setMaxHeight(232);

        // ================================================================
        // PAGINATION
        // ================================================================

        Text showingText =
                new Text(
                        "Showing 1-4 of 240 items");

        showingText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #666666;");

        Region paginationSpace =
                new Region();

        HBox.setHgrow(
                paginationSpace,
                Priority.ALWAYS);

        Button previousButton =
                new Button(
                        "Previous");

        previousButton.setPrefWidth(75);
        previousButton.setMinWidth(75);
        previousButton.setMaxWidth(75);

        previousButton.setPrefHeight(30);

        previousButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D9CCC6;" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-font-size: 10px;" +
                "-fx-cursor: hand;");

        Button nextButton =
                new Button(
                        "Next");

        nextButton.setPrefWidth(60);
        nextButton.setMinWidth(60);
        nextButton.setMaxWidth(60);

        nextButton.setPrefHeight(30);

        nextButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #D9CCC6;" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;" +
                "-fx-font-size: 10px;" +
                "-fx-cursor: hand;");

        HBox pagination =
                new HBox(
                        showingText,
                        paginationSpace,
                        previousButton,
                        nextButton);

        pagination.setPrefHeight(42);

        pagination.setMinHeight(42);

        pagination.setMaxHeight(42);

        pagination.setAlignment(
                Pos.CENTER_LEFT);

        pagination.setPadding(
                new Insets(
                        0,
                        10,
                        0,
                        10));

        // ================================================================
        // INVENTORY TABLE
        // ================================================================

        VBox inventoryTable =
                new VBox(
                        tableHeader,
                        tableBody,
                        pagination);

        inventoryTable.setPrefWidth(980);

        inventoryTable.setMinWidth(980);

        inventoryTable.setMaxWidth(980);

        inventoryTable.setPrefHeight(312);

        inventoryTable.setMinHeight(312);

        inventoryTable.setMaxHeight(312);

        inventoryTable.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;");

        DropShadow tableShadow =
                new DropShadow();

        tableShadow.setRadius(6);

        tableShadow.setOffsetY(1);

        tableShadow.setColor(
                Color.rgb(
                        70,
                        50,
                        40,
                        0.08));

        inventoryTable.setEffect(
                tableShadow);

        // ================================================================
        // ADD ALL CENTER COMPONENTS
        // ================================================================

        centerContent.getChildren().addAll(
                titleRow,
                restockCard,
                searchFilterRow,
                inventoryTable);

        // ================================================================
        // CENTER SCROLL PANE
        // ================================================================

        ScrollPane centerScroll =
                new ScrollPane(
                        centerContent);

        centerScroll.setPrefWidth(
                CENTER_WIDTH);

        centerScroll.setMinWidth(
                CENTER_WIDTH);

        centerScroll.setMaxWidth(
                CENTER_WIDTH);

        centerScroll.setPrefHeight(
                CENTER_HEIGHT);

        centerScroll.setFitToWidth(true);

        centerScroll.setFitToHeight(true);

        centerScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        centerScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        centerScroll.setStyle(
                "-fx-background-color: #F8F7FC;" +
                "-fx-border-color: transparent;");

        borderPane.setCenter(
                centerScroll);


        // ================================================================
        // BUTTON ACTIONS
        // ================================================================



        bulkUploadButton.setOnAction(e -> {

            System.out.println(
                    "Bulk Upload clicked");
        });

        newProductButton.setOnAction(e -> {

            System.out.println(
                    "New Product clicked");
        });

        reviewApproveButton.setOnAction(e -> {

            System.out.println(
                    "Review & Approve clicked");
        });

        eggsAction.setOnAction(e -> {

            System.out.println(
                    "Organic Brown Eggs clicked");
        });

        breadAction.setOnAction(e -> {

            System.out.println(
                    "Whole Wheat Bread clicked");
        });

        milkAction.setOnAction(e -> {

            System.out.println(
                    "Almond Milk clicked");
        });

        avocadoAction.setOnAction(e -> {

            System.out.println(
                    "Avocado clicked");
        });

        previousButton.setOnAction(e -> {

            System.out.println(
                    "Previous clicked");
        });

        nextButton.setOnAction(e -> {

            System.out.println(
                    "Next clicked");
        });

        // ================================================================
        // SCENE
        // ================================================================

        Scene inventoryScene =
                new Scene(
                        borderPane,
                        STAGE_WIDTH,
                        STAGE_HEIGHT);

        inventoryScene.setFill(
                Color.web("#F8F7FC"));

        return inventoryScene;
    }
}
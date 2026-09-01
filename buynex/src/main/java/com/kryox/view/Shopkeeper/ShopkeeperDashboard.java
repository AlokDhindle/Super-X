package com.kryox.view.Shopkeeper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.controller.Shopkeeper.OrderController;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.model.Shopkeeper.ProductModel;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ShopkeeperDashboard {



    public static Scene dashboardScene() {

        // ============================================================
        // FETCH PRODUCTS
        // ============================================================

        ArrayList<ProductModel> products =
                ProductController.fetchProducts();

        if (products == null) {
            products = new ArrayList<>();
        }


        // ============================================================
        // FETCH ORDERS
        // ============================================================

        ArrayList<OrderModel> orders =
                OrderController.getAllOrders();

        if (orders == null) {
            orders = new ArrayList<>();
        }


        // ============================================================
        // FETCH OFFERS
        // ============================================================

        ArrayList<OfferModel> offers =
                OfferController.getOffers();

        if (offers == null) {
            offers = new ArrayList<>();
        }


        // ============================================================
        // CALCULATE DASHBOARD VALUES
        // ============================================================

        double todaySales =
                calculateTodaySales(orders);

        double totalRevenue =
                calculateTotalRevenue(orders);

        int activeOrders =
                calculateActiveOrders(orders);

        int readyOrders =
                calculateReadyOrders(orders);

        int lowStockProducts =
                calculateLowStock(products);

        int outOfStockProducts =
                calculateOutOfStock(products);

        int nearExpiryProducts =
                calculateNearExpiry(products);

        int activeOffers =
                calculateActiveOffers(offers);


        // ============================================================
        // MAIN BORDER PANE
        // ============================================================

        BorderPane borderPane =
                new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #F8F7FC;"
        );


        // ============================================================
        // HEADER
        // ============================================================

        HBox headerMainBox =
                ViewConstants.header();

        borderPane.setTop(
                headerMainBox
        );


        // ============================================================
        // SIDEBAR
        // ============================================================

        VBox sidebar =
                createSidebar();

        borderPane.setLeft(
                sidebar
        );


        // ============================================================
        // DASHBOARD TITLE
        // ============================================================

        Text dashboardTitle =
                new Text(
                        "Dashboard Overview"
                );

        dashboardTitle.setStyle(
                "-fx-font-size: 31px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: Arial;" +
                "-fx-fill: #151515;"
        );


        Text dashboardSubTitle =
                new Text(
                        "Welcome back. Here is your store's performance at a glance."
                );

        dashboardSubTitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-family: Arial;" +
                "-fx-fill: #666666;"
        );


        VBox titleBox =
                new VBox(
                        5,
                        dashboardTitle,
                        dashboardSubTitle
                );


        // ============================================================
        // STATISTICS CARDS
        // ============================================================

        VBox salesCard =
                createStatCard(
                        "TODAY'S SALES",
                        "₹",
                        formatCurrency(todaySales),
                        "Completed orders today"
                );


        VBox activeOrdersCard =
                createStatCard(
                        "ACTIVE ORDERS",
                        "●",
                        String.valueOf(activeOrders),
                        readyOrders + " ready for pickup"
                );


        VBox revenueCard =
                createStatCard(
                        "TOTAL REVENUE",
                        "₹",
                        formatCurrency(totalRevenue),
                        "From completed orders"
                );


        VBox productsCard =
                createStatCard(
                        "TOTAL PRODUCTS",
                        "□",
                        String.valueOf(products.size()),
                        "Products in inventory"
                );


        HBox statCards =
                new HBox(
                        17,
                        salesCard,
                        activeOrdersCard,
                        revenueCard,
                        productsCard
                );

        statCards.setAlignment(
                Pos.CENTER
        );


        // ============================================================
        // URGENT ALERTS BOX
        // ============================================================

        VBox alertsBox =
                createAlertsBox(
                        lowStockProducts,
                        outOfStockProducts,
                        nearExpiryProducts
                );


        // ============================================================
        // INVENTORY SUMMARY
        // ============================================================

        VBox inventoryInsight =
                createInventoryInsight(
                        products,
                        lowStockProducts,
                        outOfStockProducts,
                        nearExpiryProducts
                );


        // ============================================================
        // ACTIVE OFFERS BOX
        // ============================================================

        VBox activeOffersBox =
                createActiveOffersBox(
                        activeOffers
                );


        // ============================================================
        // LEFT CONTENT
        // ============================================================

        VBox leftContent =
                new VBox(
                        18,
                        alertsBox,
                        inventoryInsight,
                        activeOffersBox
                );

        leftContent.setPrefWidth(
                315
        );


        // ============================================================
        // PRODUCT INVENTORY TITLE
        // ============================================================

        Text inventoryTitle =
                new Text(
                        "Product Inventory"
                );

        inventoryTitle.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #161616;"
        );


        Text productCount =
                new Text(
                        products.size() + " products"
                );

        productCount.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #A04B23;"
        );


        HBox inventoryTitleBox =
                new HBox(
                        inventoryTitle,
                        productCount
                );

        HBox.setHgrow(
                inventoryTitle,
                Priority.ALWAYS
        );

        inventoryTitleBox.setAlignment(
                Pos.CENTER_LEFT
        );


        // ============================================================
        // TABLE HEADER
        // ============================================================

        HBox tableHeader =
                createTableHeader();


        // ============================================================
        // DYNAMIC PRODUCT ROWS
        // ============================================================

        VBox productRows =
                new VBox();


        if (products.isEmpty()) {

            Text noProducts =
                    new Text(
                            "No products available."
                    );

            noProducts.setStyle(
                    "-fx-font-size: 15px;" +
                    "-fx-fill: #777777;"
            );


            VBox noProductBox =
                    new VBox(
                            noProducts
                    );

            noProductBox.setAlignment(
                    Pos.CENTER
            );

            noProductBox.setPrefHeight(
                    200
            );


            productRows.getChildren().add(
                    noProductBox
            );

        } else {

            for (ProductModel product : products) {

                HBox productRow =
                        createProductRow(
                                product
                        );

                productRows.getChildren().add(
                        productRow
                );
            }
        }


        // ============================================================
        // INVENTORY TABLE
        // ============================================================

        VBox inventoryTable =
                new VBox(
                        tableHeader,
                        productRows
                );


        // ============================================================
        // INVENTORY SCROLL PANE
        // ============================================================

        ScrollPane inventoryScrollPane =
                new ScrollPane(
                        inventoryTable
                );

        inventoryScrollPane.setFitToWidth(
                true
        );

        inventoryScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        inventoryScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        inventoryScrollPane.setPrefHeight(
                500
        );

        inventoryScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );


        // ============================================================
        // INVENTORY CARD
        // ============================================================

        VBox inventoryCard =
                new VBox(
                        20,
                        inventoryTitleBox,
                        inventoryScrollPane
                );

        inventoryCard.setPadding(
                new Insets(25)
        );

        inventoryCard.setPrefWidth(
                665
        );

        inventoryCard.setPrefHeight(
                610
        );

        inventoryCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E0DCD9;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 18px;" +
                "-fx-background-radius: 18px;"
        );


        DropShadow inventoryShadow =
                new DropShadow();

        inventoryShadow.setRadius(
                10
        );

        inventoryShadow.setSpread(
                0.02
        );

        inventoryShadow.setColor(
                Color.rgb(
                        80,
                        60,
                        50,
                        0.08
                )
        );

        inventoryCard.setEffect(
                inventoryShadow
        );


        // ============================================================
        // LOWER CONTENT
        // ============================================================

        HBox lowerContent =
                new HBox(
                        25,
                        leftContent,
                        inventoryCard
                );

        lowerContent.setAlignment(
                Pos.TOP_CENTER
        );


        // ============================================================
        // CENTER CONTENT
        // ============================================================

        VBox centerContent =
                new VBox(
                        25,
                        titleBox,
                        statCards,
                        lowerContent
                );

        centerContent.setAlignment(
                Pos.TOP_CENTER
        );

        centerContent.setPadding(
                new Insets(25)
        );

        centerContent.setStyle(
                "-fx-background-color: #F8F7FC;"
        );


        // ============================================================
        // MAIN SCROLL PANE
        // ============================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        centerContent
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setFitToHeight(
                false
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: #F8F7FC;" +
                "-fx-border-color: transparent;"
        );


        borderPane.setCenter(
                scrollPane
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
        // CREATE SCENE
        // ============================================================

        Scene dashboardScene =
                new Scene(
                        borderPane,
                       1550,850
                );

        dashboardScene.setFill(
                Color.web("#F8F7FC")
        );

        return dashboardScene;
    }


    // ================================================================
    // SIDEBAR
    // ================================================================

    private static VBox createSidebar() {

        VBox sidebar =
                new VBox();

        sidebar.setMinWidth(260);
        sidebar.setMaxWidth(260);

        sidebar.setStyle(
                "-fx-background-color: #F5F4F9;" +
                "-fx-border-color: #E3C7BA;" +
                "-fx-border-width: 0 1px 0 0;"
        );


        HBox profileBox =
                ViewConstants.letfProfileBox();

        profileBox.setAlignment(
                Pos.CENTER_LEFT
        );

        profileBox.setPadding(
                new Insets(
                        30,
                        20,
                        30,
                        20
                )
        );


        Button dashboardButton =
                ViewConstants.createDashboardButton(
                        "★",
                        "Dashboard",
                        true
                );


        Button ordersButton =
                ViewConstants.createDashboardButton(
                        "🛒",
                        "Orders",
                        false
                );


        Button inventoryButton =
                ViewConstants.createDashboardButton(
                        "📋",
                        "Inventory",
                        false
                );


        Button offersButton =
                ViewConstants.createDashboardButton(
                        "🎁",
                        "Offers",
                        false
                );


        Button analyticsButton =
                ViewConstants.createDashboardButton(
                        "📊",
                        "Analytics",
                        false
                );


        Button settingsButton =
                ViewConstants.createDashboardButton(
                        "⚙",
                        "Settings",
                        false
                );


        Button supportButton =
                ViewConstants.createDashboardButton(
                        "?",
                        "Support",
                        false
                );


        VBox sidebarMenu =
                new VBox(
                        5,
                        dashboardButton,
                        ordersButton,
                        inventoryButton,
                        offersButton,
                        analyticsButton,
                        settingsButton,
                        supportButton
                );

        sidebarMenu.setPadding(
                new Insets(
                        0,
                        8,
                        0,
                        8
                )
        );


        // VBox logoutBox =
        //         ViewConstants.logoutBox();


        VBox.setVgrow(
                sidebarMenu,
                Priority.ALWAYS
        );


        sidebar.getChildren().addAll(
                profileBox,
                sidebarMenu
                // logoutBox
        );


        // ============================================================
        // NAVIGATION
        // ============================================================

        dashboardButton.setOnAction(
                e ->
                       Homepage.HomepageStage.setScene(
                                ShopkeeperDashboard.dashboardScene()
                        )
        );


        ordersButton.setOnAction(
                e ->
                Homepage.HomepageStage.setScene(
                                ShopkeeperOrderNew.ordersScene()
                        )
        );


        inventoryButton.setOnAction(
                e ->
                        Homepage.HomepageStage.setScene(
                                ShopkeeperInventory.inventoryScene()
                        )
        );


        offersButton.setOnAction(
                e ->
                        Homepage.HomepageStage.setScene(
                                ShopkeeperOffers.offersScene()
                        )
        );


        analyticsButton.setOnAction(
                e ->
                        Homepage.HomepageStage.setScene(
                                ShopkeeperAnalytics.analyticsScene()
                        )
        );


        settingsButton.setOnAction(
                e ->
                        Homepage.HomepageStage.setScene(
                                ShopkeeperSettings.settingsScene()
                        )
        );


        supportButton.setOnAction(
                e ->
                        Homepage.HomepageStage.setScene(
                                ShopkeeperSupport.supportScene()
                        )
        );


        return sidebar;
    }


    // ================================================================
    // STAT CARD
    // ================================================================

    private static VBox createStatCard(
            String title,
            String icon,
            String value,
            String bottomText) {

        Text titleText =
                new Text(title);

        titleText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );


        Text iconText =
                new Text(icon);

        iconText.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-fill: #A94A18;"
        );


        Circle iconCircle =
                new Circle(17);

        iconCircle.setFill(
                Color.web("#FFE5D3")
        );


        StackPane iconBox =
                new StackPane(
                        iconCircle,
                        iconText
                );


        HBox titleRow =
                new HBox(
                        10,
                        titleText,
                        iconBox
                );

        HBox.setHgrow(
                titleText,
                Priority.ALWAYS
        );

        titleRow.setAlignment(
                Pos.CENTER
        );


        Text valueText =
                new Text(value);

        valueText.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        Text bottom =
                new Text(bottomText);

        bottom.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );


        VBox card =
                new VBox(
                        14,
                        titleRow,
                        valueText,
                        bottom
                );

        card.setPadding(
                new Insets(20)
        );

        card.setPrefWidth(
                235
        );

        card.setPrefHeight(
                165
        );

        card.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #DFDAD7;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 17px;" +
                "-fx-background-radius: 17px;"
        );

        return card;
    }


    // ================================================================
    // URGENT ALERTS
    // ================================================================

    private static VBox createAlertsBox(
            int lowStockCount,
            int outOfStockCount,
            int nearExpiryCount) {

                Text alertIcon = new Text("🚨");
                alertIcon.setStyle(
                                "-fx-font-size: 21px;"
                                                + "-fx-fill: #B51D1D;");
                Text urgentTitle = new Text("URGENT ALERTS");
                urgentTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #B51D1D;");
                HBox urgentTitleBox = new HBox(
                                10,
                                alertIcon,
                                urgentTitle);

                // LOW STOCK
                Text lowStockIcon = new Text("⚠");
                lowStockIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #B51D1D;");

                Text lowStockTitle =  new Text(
                        "Low Stock: " +
                                lowStockCount +
                                " products"
                );
                lowStockTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #222222;");
                HBox lowStockTitleBox = new HBox(
                                10,
                                lowStockIcon,
                                lowStockTitle);


                // LOW STOCK
                Text outOfStockIcon = new Text("⚠");
                outOfStockIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #B51D1D;");

                Text outOfStockTitle =                new Text(
                        "Out of Stock: " +
                                outOfStockCount +
                                " products"
                );
                outOfStockTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #222222;");
                HBox outOfStockTitleBox = new HBox(
                                10,
                                outOfStockIcon,
                                outOfStockTitle);



        Text nearExpiry =
                new Text(
                        "Near Expiry: " +
                                nearExpiryCount +
                                " products"
                );

        nearExpiry.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #333333;"
        );


        VBox box =
                new VBox(
                        16,
                        urgentTitleBox,
                        lowStockTitleBox,
                        outOfStockTitleBox,
                        nearExpiry
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(315);
        box.setPrefHeight(180);

        box.setStyle(
                "-fx-background-color: #FFF3F3;" +
                "-fx-border-color: #E8BDBD;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 18px;" +
                "-fx-background-radius: 18px;"
        );

        return box;
    }


    // ================================================================
    // INVENTORY SUMMARY
    // ================================================================

    private static VBox createInventoryInsight(
            ArrayList<ProductModel> products,
            int lowStockCount,
            int outOfStockCount,
            int nearExpiryCount) {

        Text title =
                new Text(
                        "INVENTORY SUMMARY"
                );

        title.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #C65417;"
        );


        Text totalProducts =
                new Text(
                        "Total Products: " +
                                products.size()
                );

        totalProducts.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #202020;"
        );


        Text lowStock =
                new Text(
                        "Low Stock: " +
                                lowStockCount
                );

        lowStock.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );


        Text outOfStock =
                new Text(
                        "Out of Stock: " +
                                outOfStockCount
                );

        outOfStock.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #C62828;"
        );


        Text nearExpiry =
                new Text(
                        "Near Expiry: " +
                                nearExpiryCount
                );

        nearExpiry.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );


        VBox box =
                new VBox(
                        14,
                        title,
                        totalProducts,
                        lowStock,
                        outOfStock,
                        nearExpiry
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(315);
        box.setPrefHeight(225);

        box.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E3DCD8;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 18px;" +
                "-fx-background-radius: 18px;"
        );

        return box;
    }


    // ================================================================
    // ACTIVE OFFERS BOX
    // ================================================================

    private static VBox createActiveOffersBox(
            int activeOffers) {

        Text title =
                new Text(
                        "ACTIVE OFFERS"
                );

        title.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #C65417;"
        );


        Text offerCount =
                new Text(
                        String.valueOf(
                                activeOffers
                        )
                );

        offerCount.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #202020;"
        );


        String descriptionText;

        if (activeOffers == 0) {

            descriptionText =
                    "No active offers currently.";

        } else if (activeOffers == 1) {

            descriptionText =
                    "1 offer is currently active.";

        } else {

            descriptionText =
                    activeOffers +
                            " offers are currently active.";
        }


        Text description =
                new Text(
                        descriptionText
                );

        description.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );


        VBox box =
                new VBox(
                        12,
                        title,
                        offerCount,
                        description
                );

        box.setPadding(
                new Insets(20)
        );

        box.setPrefWidth(315);
        box.setPrefHeight(150);

        box.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E3DCD8;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 18px;" +
                "-fx-background-radius: 18px;"
        );

        return box;
    }


    // ================================================================
    // TABLE HEADER - DISC REMOVED
    // ================================================================

    private static HBox createTableHeader() {

        HBox header =
                new HBox(
                        createHeaderCell(
                                "PRODUCT",
                                180
                        ),
                        createHeaderCell(
                                "CATEGORY",
                                140
                        ),
                        createHeaderCell(
                                "SKU",
                                90
                        ),
                        createHeaderCell(
                                "STOCK",
                                110
                        ),
                        createHeaderCell(
                                "PRICE",
                                110
                        )
                );

        header.setPadding(
                new Insets(
                        12,
                        10,
                        12,
                        15
                )
        );

        header.setStyle(
                "-fx-background-color: #F1F0F5;"
        );

        return header;
    }


    // ================================================================
    // HEADER CELL
    // ================================================================

    private static HBox createHeaderCell(
            String text,
            double width) {

        Text headerText =
                new Text(text);

        headerText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;"
        );


        HBox box =
                new HBox(
                        headerText
                );

        box.setMinWidth(width);
        box.setPrefWidth(width);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        return box;
    }


    // ================================================================
    // PRODUCT ROW - DISC REMOVED
    // ================================================================

    private static HBox createProductRow(
            ProductModel product) {

        // ============================================================
        // PRODUCT NAME
        // ============================================================

        Text productIcon =
                new Text("▧");

        productIcon.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-fill: #555555;"
        );


        Text productName =
                new Text(
                        safeText(
                                product.getProductName()
                        )
                );

        productName.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #222222;"
        );


        HBox productBox =
                new HBox(
                        12,
                        productIcon,
                        productName
                );

        productBox.setMinWidth(180);
        productBox.setPrefWidth(180);

        productBox.setAlignment(
                Pos.CENTER_LEFT
        );


        // ============================================================
        // CATEGORY
        // ============================================================

        HBox categoryBox =
                createRowCell(
                        safeText(
                                product.getCategory()
                        ),
                        140,
                        "#666666"
                );


        // ============================================================
        // SKU
        // ============================================================

        HBox skuBox =
                createRowCell(
                        safeText(
                                product.getSku()
                        ),
                        90,
                        "#444444"
                );


        // ============================================================
        // STOCK
        // ============================================================

        String stockText =
                product.getStockQuantity()
                        + " "
                        + safeText(
                        product.getUnit()
                );


        String stockColor;

        if (product.getStockQuantity() <= 0) {

            stockColor = "#C62828";

        } else if (
                product.getStockQuantity()
                        <= product.getLowStockLimit()
        ) {

            stockColor = "#E67E22";

        } else {

            stockColor = "#444444";
        }


        HBox stockBox =
                createRowCell(
                        stockText,
                        110,
                        stockColor
                );


        // ============================================================
        // PRICE
        // ============================================================

        String priceText;

        if (product.getSellingPrice() == null) {

            priceText = "-";

        } else {

            priceText =
                    "₹" +
                            String.format(
                                    "%.2f",
                                    product.getSellingPrice()
                            );
        }


        HBox priceBox =
                createRowCell(
                        priceText,
                        110,
                        "#333333"
                );


        // ============================================================
        // COMPLETE ROW
        // ============================================================

        HBox row =
                new HBox(
                        productBox,
                        categoryBox,
                        skuBox,
                        stockBox,
                        priceBox
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        15,
                        10,
                        15,
                        15
                )
        );

        row.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E1DDDA;" +
                "-fx-border-width: 0 0 1px 0;"
        );

        return row;
    }


    // ================================================================
    // ROW CELL
    // ================================================================

    private static HBox createRowCell(
            String value,
            double width,
            String color) {

        Text text =
                new Text(value);

        text.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: " +
                color +
                ";"
        );


        HBox box =
                new HBox(
                        text
                );

        box.setMinWidth(width);
        box.setPrefWidth(width);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        return box;
    }


    // ================================================================
    // TODAY SALES
    // ================================================================

    private static double calculateTodaySales(
            ArrayList<OrderModel> orders) {

        double sales = 0;

        String today =
                LocalDate.now().toString();


        for (OrderModel order : orders) {

            if (order.getOrderDate() == null
                    || order.getOrderStatus() == null) {

                continue;
            }


            if (today.equals(
                    order.getOrderDate()
            )
                    && "COMPLETED".equalsIgnoreCase(
                    order.getOrderStatus()
            )) {

                sales +=
                        order.getTotalAmount();
            }
        }

        return sales;
    }


    // ================================================================
    // TOTAL REVENUE
    // ================================================================

    private static double calculateTotalRevenue(
            ArrayList<OrderModel> orders) {

        double revenue = 0;


        for (OrderModel order : orders) {

            if (order.getOrderStatus() == null) {
                continue;
            }


            if ("COMPLETED".equalsIgnoreCase(
                    order.getOrderStatus()
            )) {

                revenue +=
                        order.getTotalAmount();
            }
        }

        return revenue;
    }


    // ================================================================
    // ACTIVE ORDERS
    // ================================================================

    private static int calculateActiveOrders(
            ArrayList<OrderModel> orders) {

        int count = 0;


        for (OrderModel order : orders) {

            String status =
                    order.getOrderStatus();


            if (status == null) {
                continue;
            }


            if ("NEW".equalsIgnoreCase(status)
                    || "PREPARING".equalsIgnoreCase(status)
                    || "READY".equalsIgnoreCase(status)
                    || "OUT_FOR_DELIVERY".equalsIgnoreCase(status)) {

                count++;
            }
        }

        return count;
    }


    // ================================================================
    // READY ORDERS
    // ================================================================

    private static int calculateReadyOrders(
            ArrayList<OrderModel> orders) {

        int count = 0;


        for (OrderModel order : orders) {

            if (order.getOrderStatus() == null) {
                continue;
            }


            if ("READY".equalsIgnoreCase(
                    order.getOrderStatus()
            )) {

                count++;
            }
        }

        return count;
    }


    // ================================================================
    // LOW STOCK
    // ================================================================

    private static int calculateLowStock(
            ArrayList<ProductModel> products) {

        int count = 0;


        for (ProductModel product : products) {

            int stock =
                    product.getStockQuantity();

            int lowLimit =
                    product.getLowStockLimit();


            // Low stock does not include out of stock

            if (stock > 0
                    && stock <= lowLimit) {

                count++;
            }
        }

        return count;
    }


    // ================================================================
    // OUT OF STOCK
    // ================================================================

    private static int calculateOutOfStock(
            ArrayList<ProductModel> products) {

        int count = 0;


        for (ProductModel product : products) {

            if (product.getStockQuantity() <= 0) {

                count++;
            }
        }

        return count;
    }


    // ================================================================
    // NEAR EXPIRY
    // ================================================================

    private static int calculateNearExpiry(
            ArrayList<ProductModel> products) {

        int count = 0;

        LocalDate today =
                LocalDate.now();


        for (ProductModel product : products) {

            try {

                if (!"Has Expiry".equalsIgnoreCase(
                        product.getExpiryTracking()
                )) {

                    continue;
                }


                LocalDate expiryDate =
                        LocalDate.parse(
                                product.getExpiryDate()
                        );


                if (expiryDate == null) {
                    continue;
                }


                long daysRemaining =
                        ChronoUnit.DAYS.between(
                                today,
                                expiryDate
                        );


                // Product expires within next 7 days

                if (daysRemaining >= 0
                        && daysRemaining <= 7) {

                    count++;
                }

            } catch (Exception e) {

                System.out.println(
                        "Error checking expiry for product: "
                                + product.getProductName()
                );
            }
        }

        return count;
    }


    // ================================================================
    // ACTIVE OFFERS
    // ================================================================

    private static int calculateActiveOffers(
            ArrayList<OfferModel> offers) {

        int count = 0;

        LocalDate today =
                LocalDate.now();


        for (OfferModel offer : offers) {

            try {

                if (offer.getStatus() == null) {
                    continue;
                }


                if (!"ACTIVE".equalsIgnoreCase(
                        offer.getStatus()
                )) {

                    continue;
                }


                LocalDate startDate =
                        LocalDate.parse(
                                offer.getStartDate()
                        );


                LocalDate endDate =
                        LocalDate.parse(
                                offer.getEndDate()
                        );


                boolean hasStarted =
                        !today.isBefore(
                                startDate
                        );


                boolean hasNotExpired =
                        !today.isAfter(
                                endDate
                        );


                if (hasStarted
                        && hasNotExpired) {

                    count++;
                }

            } catch (Exception e) {

                System.out.println(
                        "Invalid offer date"
                );
            }
        }

        return count;
    }


    // ================================================================
    // FORMAT CURRENCY
    // ================================================================

    private static String formatCurrency(
            double amount) {

        return String.format(
                "%,.2f",
                amount
        );
    }


    // ================================================================
    // SAFE TEXT
    // ================================================================

    private static String safeText(
            String value) {

        if (value == null
                || value.isBlank()) {

            return "-";
        }

        return value;
    }
}
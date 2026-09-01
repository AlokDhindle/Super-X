package com.kryox.view.Shopkeeper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.scene.text.Text;

public class ShopkeeperInventory {




    private static VBox inventoryList;

    private static List<ProductModel> allProducts =
            new ArrayList<>();

    private static Label totalProductsValue;
    private static Label totalStockValue;
    private static Label lowStockValue;
    private static Label outOfStockValue;


    // ================================================================
    // INVENTORY PAGE
    // ================================================================

    public static Scene inventoryScene() {

        allProducts = new ArrayList<>(
                ProductController.fetchProducts()
        );


        BorderPane borderPane = new BorderPane();


        // ============================================================
        // HEADER
        // ============================================================

        HBox headerMainBox =
                ViewConstants.header();

        // Header background
        headerMainBox.setStyle(
                "-fx-background-color: #EBCCB7;"
        );

        borderPane.setTop(
                headerMainBox
        );


        // ============================================================
        // SIDEBAR
        // ============================================================

        borderPane.setLeft(
                createSidebar()
        );


        // ============================================================
        // FOOTER
        // ============================================================

        borderPane.setBottom(
                ViewConstants.footer()
        );


        // ============================================================
        // MAIN CONTENT
        // ============================================================

        VBox centerContent = new VBox();

        centerContent.setPadding(
                new Insets(20)
        );

        centerContent.setSpacing(14);

        centerContent.setStyle(
                "-fx-background-color: #EEE5DF;"
        );


        // ============================================================
        // TITLE
        // ============================================================

        Text title = new Text(
                "Inventory & Stock"
        );

        title.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        Text subtitle = new Text(
                "Manage your products, stock levels, expiry dates, and inventory."
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );


        VBox titleBox = new VBox(
                4,
                title,
                subtitle
        );


        Button addProductButton = new Button(
                "+  New Product"
        );

        addProductButton.setPrefWidth(145);
        addProductButton.setPrefHeight(42);

        addProductButton.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );


        HBox titleRow = new HBox(
                titleBox,
                addProductButton
        );

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        // ============================================================
        // SUMMARY
        // ============================================================

        totalProductsValue = new Label("0");
        totalStockValue = new Label("0");
        lowStockValue = new Label("0");
        outOfStockValue = new Label("0");


        HBox summaryRow = new HBox(
                12,

                createSummaryCard(
                        "TOTAL PRODUCTS",
                        totalProductsValue
                ),

                createSummaryCard(
                        "TOTAL STOCK",
                        totalStockValue
                ),

                createSummaryCard(
                        "LOW STOCK",
                        lowStockValue
                ),

                createSummaryCard(
                        "OUT OF STOCK",
                        outOfStockValue
                )
        );


        // ============================================================
        // SEARCH
        // ============================================================

        TextField searchField =
                new TextField();

        searchField.setPromptText(
                "Search product, brand or SKU..."
        );

        searchField.setPrefWidth(300);
        searchField.setPrefHeight(38);

        searchField.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #D9CCC6;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 12px;"
        );


        // ============================================================
        // CATEGORY FILTER
        // ============================================================

        ComboBox<String> categoryFilter =
                new ComboBox<>();

        categoryFilter.getItems().add(
                "All Categories"
        );


        for (ProductModel product : allProducts) {

            String category =
                    safe(product.getCategory());

            if (!category.isBlank()
                    && !categoryFilter.getItems().contains(category)) {

                categoryFilter.getItems().add(
                        category
                );
            }
        }


        categoryFilter.setValue(
                "All Categories"
        );

        categoryFilter.setPrefWidth(150);
        categoryFilter.setPrefHeight(38);


        // ============================================================
        // STATUS FILTER
        // ============================================================

        ComboBox<String> statusFilter =
                new ComboBox<>();

        statusFilter.getItems().addAll(
                "All Status",
                "ACTIVE",
                "INACTIVE",
                "OUT_OF_STOCK"
        );

        statusFilter.setValue(
                "All Status"
        );

        statusFilter.setPrefWidth(130);
        statusFilter.setPrefHeight(38);


        // ============================================================
        // CHECKBOXES
        // ============================================================

        CheckBox lowStockFilter =
                new CheckBox("Low Stock");

        CheckBox outOfStockFilter =
                new CheckBox("Out of Stock");

        CheckBox nearExpiryFilter =
                new CheckBox("Near Expiry");


        HBox filterRow = new HBox(
                10,
                searchField,
                categoryFilter,
                statusFilter,
                lowStockFilter,
                outOfStockFilter,
                nearExpiryFilter
        );

        filterRow.setAlignment(
                Pos.CENTER_LEFT
        );

        filterRow.setPadding(
                new Insets(10)
        );

        filterRow.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;"
        );


        // ============================================================
        // TABLE HEADER
        // ============================================================

        HBox tableHeader =
                createTableHeader();


        // ============================================================
        // INVENTORY LIST
        // ============================================================

        inventoryList = new VBox();

        inventoryList.setStyle(
                "-fx-background-color: white;"
        );


        rebuildInventory();


        VBox inventoryContainer =
                new VBox(
                        tableHeader,
                        inventoryList
                );

        inventoryContainer.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );


        // ============================================================
        // FILTER EVENTS
        // ============================================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) ->

                        refreshInventory(
                                newValue,
                                categoryFilter.getValue(),
                                statusFilter.getValue(),
                                lowStockFilter.isSelected(),
                                outOfStockFilter.isSelected(),
                                nearExpiryFilter.isSelected()
                        )
        );


        categoryFilter.setOnAction(
                event -> refreshInventory(
                        searchField.getText(),
                        categoryFilter.getValue(),
                        statusFilter.getValue(),
                        lowStockFilter.isSelected(),
                        outOfStockFilter.isSelected(),
                        nearExpiryFilter.isSelected()
                )
        );


        statusFilter.setOnAction(
                event -> refreshInventory(
                        searchField.getText(),
                        categoryFilter.getValue(),
                        statusFilter.getValue(),
                        lowStockFilter.isSelected(),
                        outOfStockFilter.isSelected(),
                        nearExpiryFilter.isSelected()
                )
        );


        lowStockFilter.setOnAction(
                event -> refreshInventory(
                        searchField.getText(),
                        categoryFilter.getValue(),
                        statusFilter.getValue(),
                        lowStockFilter.isSelected(),
                        outOfStockFilter.isSelected(),
                        nearExpiryFilter.isSelected()
                )
        );


        outOfStockFilter.setOnAction(
                event -> refreshInventory(
                        searchField.getText(),
                        categoryFilter.getValue(),
                        statusFilter.getValue(),
                        lowStockFilter.isSelected(),
                        outOfStockFilter.isSelected(),
                        nearExpiryFilter.isSelected()
                )
        );


        nearExpiryFilter.setOnAction(
                event -> refreshInventory(
                        searchField.getText(),
                        categoryFilter.getValue(),
                        statusFilter.getValue(),
                        lowStockFilter.isSelected(),
                        outOfStockFilter.isSelected(),
                        nearExpiryFilter.isSelected()
                )
        );


        // ============================================================
        // ADD PRODUCT BUTTON
        // ============================================================

        addProductButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperInventoryAddProduct
                                .addProductScene()
                )
        );




        centerContent.getChildren().addAll(
                titleRow,
                summaryRow,
                filterRow,
                inventoryContainer
        );


        ScrollPane scrollPane =
                new ScrollPane(centerContent);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: #EEE5DF;" +
                "-fx-border-color: transparent;"
        );


        borderPane.setCenter(scrollPane);


        return new Scene(
                borderPane,
                ViewConstants.STAGE_WIDTH,
                ViewConstants.STAGE_HEIGHT
        );
    }


    // ================================================================
    // PRODUCT ROW
    // ================================================================

    private static HBox createProductRow(
            ProductModel product) {


        final double ITEM_WIDTH = 330;
        final double CATEGORY_WIDTH = 110;
        final double STOCK_WIDTH = 110;
        final double PRICE_WIDTH = 100;
        final double EXPIRY_WIDTH = 120;
        final double STATUS_WIDTH = 120;
        final double ACTION_WIDTH = 50;


        // ============================================================
        // PRODUCT IMAGE
        // ============================================================

        StackPane imageBox =
                createProductImage(
                        product.getImageUrl()
                );


        // ============================================================
        // PRODUCT NAME
        // ============================================================

        Text productName =
                new Text(
                        safe(product.getProductName())
                );

        productName.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;"
        );


        // ============================================================
        // BRAND
        // ============================================================

        Text brand =
                new Text(
                        safe(product.getBrand())
                );

        brand.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #666666;"
        );


        // ============================================================
        // SKU
        // ============================================================

        Text sku =
                new Text(
                        "SKU: "
                                + safe(product.getSku())
                );

        sku.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #888888;"
        );


        VBox productText =
                new VBox(
                        5,
                        productName,
                        brand,
                        sku
                );

        productText.setAlignment(
                Pos.CENTER_LEFT
        );


        HBox itemBox =
                new HBox(
                        18,
                        imageBox,
                        productText
                );

        itemBox.setAlignment(
                Pos.CENTER_LEFT
        );

        setFixedWidth(
                itemBox,
                ITEM_WIDTH
        );


        // ============================================================
        // CATEGORY
        // ============================================================

        Text category =
                new Text(
                        safe(product.getCategory())
                );

        category.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #5F514A;"
        );


        StackPane categoryBox =
                new StackPane(category);

        setFixedWidth(
                categoryBox,
                CATEGORY_WIDTH
        );


        // ============================================================
        // STOCK
        // ============================================================

        Text stockText =
                new Text(
                        product.getStockQuantity()
                                + " "
                                + safe(product.getUnit())
                );

        stockText.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: "
                                + getStockColor(product)
                                + ";"
        );


        StackPane stockBox =
                new StackPane(stockText);

        setFixedWidth(
                stockBox,
                STOCK_WIDTH
        );


        // ============================================================
        // PRICE
        // ============================================================

        double sellingPrice =
                product.getSellingPrice() == null
                        ? 0.0
                        : product.getSellingPrice();


        Text price =
                new Text(
                        "₹"
                                + String.format(
                                        "%.2f",
                                        sellingPrice
                                )
                );

        price.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;"
        );


        StackPane priceBox =
                new StackPane(price);

        setFixedWidth(
                priceBox,
                PRICE_WIDTH
        );


        // ============================================================
        // EXPIRY
        // ============================================================

        Text expiry =
                new Text(
                        getExpiryText(product)
                );

        expiry.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: "
                                + getExpiryColor(product)
                                + ";"
        );


        StackPane expiryBox =
                new StackPane(expiry);

        setFixedWidth(
                expiryBox,
                EXPIRY_WIDTH
        );


        // ============================================================
        // STATUS
        // ============================================================

        Text statusText =
                new Text(
                        getDisplayStatus(product)
                );

        statusText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: "
                                + getStatusTextColor(product)
                                + ";"
        );


        StackPane statusBox =
                new StackPane(statusText);

        setFixedWidth(
                statusBox,
                STATUS_WIDTH
        );

        statusBox.setPadding(
                new Insets(
                        8,
                        12,
                        8,
                        12
                )
        );

        statusBox.setStyle(
                "-fx-background-color: "
                                + getStatusBackground(product)
                                + ";" +
                "-fx-background-radius: 18px;"
        );


        // ============================================================
        // ACTION BUTTON
        // ============================================================

        Button actionButton =
                new Button("⋮");

        actionButton.setPrefWidth(
                ACTION_WIDTH
        );

        actionButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 22px;" +
                "-fx-cursor: hand;"
        );

        actionButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperProductDetails.productDetailsScene(product)
                )
        );


        // ============================================================
        // ROW
        // ============================================================

        HBox row =
                new HBox(
                        itemBox,
                        categoryBox,
                        stockBox,
                        priceBox,
                        expiryBox,
                        statusBox,
                        actionButton
                );


        row.setPrefHeight(120);
        row.setMinHeight(120);
        row.setMaxHeight(120);


        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        row.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E7DFDA;" +
                "-fx-border-width: 0 0 1px 0;"
        );


        return row;
    }


    // ================================================================
    // PRODUCT IMAGE
    // ================================================================

    private static StackPane createProductImage(
            String imageUrl) {

        final double IMAGE_SIZE = 90;

        final double RADIUS =
                IMAGE_SIZE / 2;


        StackPane imageBox =
                new StackPane();


        imageBox.setPrefSize(
                IMAGE_SIZE,
                IMAGE_SIZE
        );

        imageBox.setMinSize(
                IMAGE_SIZE,
                IMAGE_SIZE
        );

        imageBox.setMaxSize(
                IMAGE_SIZE,
                IMAGE_SIZE
        );


        Circle background =
                new Circle(RADIUS);

        background.setFill(
                Color.web("#EEECEF")
        );


        // ============================================================
        // PRODUCT IMAGE
        // ============================================================

        if (imageUrl != null
                && !imageUrl.isBlank()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                IMAGE_SIZE,
                                IMAGE_SIZE,
                                true,
                                true,
                                true
                        );


                ImageView imageView =
                        new ImageView(image);


                imageView.setFitWidth(
                        IMAGE_SIZE
                );

                imageView.setFitHeight(
                        IMAGE_SIZE
                );


                imageView.setPreserveRatio(
                        false
                );


                Circle clip =
                        new Circle(
                                RADIUS,
                                RADIUS,
                                RADIUS
                        );


                imageView.setClip(
                        clip
                );


                imageBox.getChildren().add(
                        imageView
                );


                return imageBox;

            } catch (Exception ignored) {
            }
        }


        // ============================================================
        // PLACEHOLDER
        // ============================================================

        Text placeholder =
                new Text("IMG");

        placeholder.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #777777;"
        );


        imageBox.getChildren().addAll(
                background,
                placeholder
        );


        return imageBox;
    }


    // ================================================================
    // EXPIRY CHECK
    // ================================================================

    private static boolean hasExpiry(
            ProductModel product) {

        return "Has Expiry".equalsIgnoreCase(
                safe(product.getExpiryTracking())
        );
    }


    private static String getExpiryText(
            ProductModel product) {


        if (!hasExpiry(product)
                || product.getExpiryDate() == null) {

            return "-";
        }


        LocalDate expiryDate =
                LocalDate.parse(product.getExpiryDate());


        long days =
                ChronoUnit.DAYS.between(
                        LocalDate.now(),
                        expiryDate
                );


        if (days < 0) {

            return "EXPIRED";
        }


        if (days == 0) {

            return "Today";
        }


        if (days == 1) {

            return "1 day";
        }


        if (days <= 7) {

            return days + " days";
        }


        return expiryDate.toString();
    }


    private static boolean isNearExpiry(
            ProductModel product) {


        if (!hasExpiry(product)
                || product.getExpiryDate() == null) {

            return false;
        }


        long days =
                ChronoUnit.DAYS.between(
                        LocalDate.now(),
                        LocalDate.parse(product.getExpiryDate())
                );


        return days >= 0
                && days <= 7;
    }


    private static String getExpiryColor(
            ProductModel product) {


        if (!hasExpiry(product)
                || product.getExpiryDate() == null) {

            return "#777777";
        }


        long days =
                ChronoUnit.DAYS.between(
                        LocalDate.now(),
                        LocalDate.parse(product.getExpiryDate())
                );


        if (days < 0) {

            return "#A91D1D";
        }


        if (days <= 7) {

            return "#A94408";
        }


        return "#333333";
    }


    // ================================================================
    // STOCK
    // ================================================================

    private static boolean isLowStock(
            ProductModel product) {

        return product.getStockQuantity() > 0
                && product.getLowStockLimit() > 0
                && product.getStockQuantity()
                <= product.getLowStockLimit();
    }


    private static String getStockColor(
            ProductModel product) {


        if (product.getStockQuantity() <= 0) {

            return "#A91D1D";
        }


        if (isLowStock(product)) {

            return "#A94408";
        }


        return "#246B3A";
    }


    // ================================================================
    // STATUS
    // ================================================================

    private static String getDisplayStatus(
            ProductModel product) {


        if (product.getStockQuantity() <= 0
                || "OUT_OF_STOCK".equalsIgnoreCase(
                        safe(product.getStatus())
                )) {

            return "Out of Stock";
        }


        if (isLowStock(product)) {

            return "Low Stock";
        }


        if (isNearExpiry(product)) {

            return "Near Expiry";
        }


        if ("INACTIVE".equalsIgnoreCase(
                safe(product.getStatus())
        )) {

            return "Inactive";
        }


        return "Active";
    }


    private static String getStatusTextColor(
            ProductModel product) {


        String status =
                getDisplayStatus(product);


        if ("Out of Stock".equals(status)) {

            return "#A91D1D";
        }


        if ("Low Stock".equals(status)
                || "Near Expiry".equals(status)) {

            return "#A94408";
        }


        if ("Inactive".equals(status)) {

            return "#666666";
        }


        return "#246B3A";
    }


    private static String getStatusBackground(
            ProductModel product) {


        String status =
                getDisplayStatus(product);


        if ("Out of Stock".equals(status)) {

            return "#FFDCDC";
        }


        if ("Low Stock".equals(status)
                || "Near Expiry".equals(status)) {

            return "#FFF0E8";
        }


        if ("Inactive".equals(status)) {

            return "#EAE8EC";
        }


        return "#E4F3E8";
    }


    // ================================================================
    // REFRESH INVENTORY
    // ================================================================

    private static void refreshInventory(
            String search,
            String category,
            String status,
            boolean lowStock,
            boolean outOfStock,
            boolean nearExpiry) {


        inventoryList.getChildren().clear();


        String searchText =
                search == null
                        ? ""
                        : search.trim()
                                .toLowerCase();


        for (ProductModel product : allProducts) {


            boolean matchesSearch =
                    searchText.isEmpty()

                            ||

                            safe(
                                    product.getProductName()
                            ).toLowerCase()
                                    .contains(searchText)

                            ||

                            safe(
                                    product.getBrand()
                            ).toLowerCase()
                                    .contains(searchText)

                            ||

                            safe(
                                    product.getSku()
                            ).toLowerCase()
                                    .contains(searchText);


            if (!matchesSearch) {

                continue;
            }


            if (!"All Categories".equals(category)
                    && !safe(product.getCategory())
                    .equalsIgnoreCase(category)) {

                continue;
            }


            if (!"All Status".equals(status)) {

                boolean matchesStatus =
                        safe(product.getStatus())
                                .equalsIgnoreCase(status);


                if ("OUT_OF_STOCK".equals(status)
                        && product.getStockQuantity() <= 0) {

                    matchesStatus = true;
                }


                if (!matchesStatus) {

                    continue;
                }
            }


            if (lowStock
                    && !isLowStock(product)) {

                continue;
            }


            if (outOfStock
                    && product.getStockQuantity() > 0) {

                continue;
            }


            if (nearExpiry
                    && !isNearExpiry(product)) {

                continue;
            }


            inventoryList.getChildren().add(
                    createProductRow(product)
            );
        }


        updateSummary();
    }


    // ================================================================
    // REBUILD INVENTORY
    // ================================================================

    private static void rebuildInventory() {


        if (inventoryList == null) {

            return;
        }


        inventoryList.getChildren().clear();


        for (ProductModel product : allProducts) {

            inventoryList.getChildren().add(
                    createProductRow(product)
            );
        }


        updateSummary();
    }


    // ================================================================
    // UPDATE SUMMARY
    // ================================================================

    private static void updateSummary() {


        if (totalProductsValue == null) {

            return;
        }


        int totalProducts =
                allProducts.size();

        int totalStock = 0;
        int lowStock = 0;
        int outOfStock = 0;


        for (ProductModel product : allProducts) {


            totalStock +=
                    Math.max(
                            0,
                            product.getStockQuantity()
                    );


            if (product.getStockQuantity() <= 0) {

                outOfStock++;

            } else if (isLowStock(product)) {

                lowStock++;
            }
        }


        totalProductsValue.setText(
                String.valueOf(totalProducts)
        );

        totalStockValue.setText(
                String.valueOf(totalStock)
        );

        lowStockValue.setText(
                String.valueOf(lowStock)
        );

        outOfStockValue.setText(
                String.valueOf(outOfStock)
        );
    }



    // ================================================================
    // TABLE HEADER
    // ================================================================

    private static HBox createTableHeader() {


        HBox header =
                new HBox(
                        createHeaderCell(
                                "ITEM",
                                330
                        ),

                        createHeaderCell(
                                "CATEGORY",
                                110
                        ),

                        createHeaderCell(
                                "STOCK",
                                110
                        ),

                        createHeaderCell(
                                "PRICE",
                                100
                        ),

                        createHeaderCell(
                                "EXPIRY",
                                120
                        ),

                        createHeaderCell(
                                "STATUS",
                                120
                        ),

                        createHeaderCell(
                                "ACTION",
                                50
                        )
                );


        header.setPrefHeight(42);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPadding(
                new Insets(
                        0,
                        15,
                        0,
                        15
                )
        );

        header.setStyle(
                "-fx-background-color: #F7F5F8;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-width: 0 0 1px 0;"
        );


        return header;
    }


    private static StackPane createHeaderCell(
            String text,
            double width) {


        Text label =
                new Text(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #555555;"
        );


        StackPane box =
                new StackPane(label);


        setFixedWidth(
                box,
                width
        );


        return box;
    }


    // ================================================================
    // SUMMARY CARD
    // ================================================================

    private static VBox createSummaryCard(
            String title,
            Label value) {


        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #777777;"
        );


        value.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );


        VBox card =
                new VBox(
                        3,
                        titleLabel,
                        value
                );


        card.setPadding(
                new Insets(
                        10,
                        14,
                        10,
                        14
                )
        );

        card.setPrefWidth(155);

        card.setMinWidth(155);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #DED5D0;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;"
        );


        return card;
    }


    // ================================================================
    // SIDEBAR
    // ================================================================

    private static VBox createSidebar() {


        VBox sidebar =
                new VBox();


        sidebar.setMinWidth(
                ViewConstants.SIDEBAR_WIDTH
        );

        sidebar.setMaxWidth(
                ViewConstants.SIDEBAR_WIDTH
        );

        sidebar.setStyle(
                "-fx-background-color: #EBCCB7;" +
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
                        false
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
                        true
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


        VBox menu =
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


        menu.setPadding(
                new Insets(
                        0,
                        8,
                        0,
                        8
                )
        );


        // VBox logout =
        //         ViewConstants.logoutBox();


        VBox.setVgrow(
                menu,
                Priority.ALWAYS
        );


        sidebar.getChildren().addAll(
                profileBox,
                menu
                // logout
        );


        dashboardButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperDashboard
                                .dashboardScene()
                )
        );


        ordersButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperOrderReady
                                .ordersScene()
                )
        );


        offersButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperOffers
                                .offersScene()
                )
        );


        analyticsButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperAnalytics
                                .analyticsScene()
                )
        );


        settingsButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperSettings
                                .settingsScene()
                )
        );


        supportButton.setOnAction(
                event -> Homepage.HomepageStage.setScene(
                        ShopkeeperSupport
                                .supportScene()
                )
        );


        return sidebar;
    }









    
    // ================================================================
    // UTILITY
    // ================================================================

    private static void setFixedWidth(
            Region region,
            double width) {


        region.setPrefWidth(width);
        region.setMinWidth(width);
        region.setMaxWidth(width);
    }


    private static String safe(
            String value) {


        return value == null
                ? ""
                : value;
    }
}

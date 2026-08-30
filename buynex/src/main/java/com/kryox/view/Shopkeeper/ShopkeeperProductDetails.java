package com.kryox.view.Shopkeeper;

import java.time.LocalDate;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ShopkeeperProductDetails {




    public static Scene productDetailsScene(
            ProductModel product
    ) {


        BorderPane root =
                new BorderPane();


        root.setStyle(
                "-fx-background-color: #F8F7FC;"
        );


        // ========================================================
        // HEADER
        // ========================================================

        root.setTop(
                ViewConstants.header()
        );


        // ========================================================
        // SIDEBAR
        // ========================================================

        root.setLeft(
                createSidebar()
        );


        // ========================================================
        // FOOTER
        // ========================================================

        root.setBottom(
                ViewConstants.footer()
        );


        // ========================================================
        // MAIN CONTENT
        // ========================================================

        VBox mainContent =
                new VBox(20);

        mainContent.setPadding(
                new Insets(
                        25,
                        30,
                        30,
                        30
                )
        );


        // ========================================================
        // PAGE HEADER
        // ========================================================

        Text title =
                new Text(
                        "Product Details"
                );

        title.setStyle(
                "-fx-font-size: 26px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-fill: #242424;"
        );


        Text subtitle =
                new Text(
                        "View and manage product information"
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;"
                        + "-fx-fill: #777777;"
        );


        VBox titleBox =
                new VBox(
                        5,
                        title,
                        subtitle
                );


        // ========================================================
        // BACK BUTTON
        // ========================================================

        Button backButton =
                new Button(
                        "← Back"
                );

        backButton.setPrefHeight(
                38
        );

        backButton.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #DDDDDD;"
                        + "-fx-border-radius: 8px;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-font-size: 13px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );


        backButton.setOnAction(
                event -> {

                    Homepage.HomepageStage.setScene(
                            ShopkeeperInventory.inventoryScene()
                    );
                }
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        HBox pageHeader =
                new HBox(
                        titleBox,
                        spacer,
                        backButton
                );

        pageHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        // ========================================================
        // PRODUCT IMAGE CARD
        // ========================================================

        VBox productImageCard =
                createProductImageCard(
                        product
                );


        // ========================================================
        // PRODUCT INFORMATION CARD
        // ========================================================

        VBox productInformationCard =
                createCard();


        Text informationTitle =
                createSectionTitle(
                        "Product Information"
                );


        TextField productIdField =
                createTextField(
                        safe(
                                product.getProductId()
                        )
                );

        productIdField.setEditable(
                false
        );


        TextField productNameField =
                createTextField(
                        safe(
                                product.getProductName()
                        )
                );


        TextField categoryField =
                createTextField(
                        safe(
                                product.getCategory()
                        )
                );


        TextField brandField =
                createTextField(
                        safe(
                                product.getBrand()
                        )
                );


        TextField skuField =
                createTextField(
                        safe(
                                product.getSku()
                        )
                );


        TextField barcodeField =
                createTextField(
                        safe(
                                product.getBarcode()
                        )
                );


        TextArea descriptionArea =
                new TextArea(
                        safe(
                                product.getDescriptionValue()
                        )
                );

        descriptionArea.setPrefHeight(
                90
        );

        descriptionArea.setWrapText(
                true
        );

        descriptionArea.setStyle(
                "-fx-font-size: 13px;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-border-radius: 8px;"
                        + "-fx-border-color: #DDDDDD;"
        );


        GridPane productGrid =
                new GridPane();

        productGrid.setHgap(
                20
        );

        productGrid.setVgap(
                16
        );


        addTwoColumns(
                productGrid
        );


        productGrid.add(
                createFieldBox(
                        "Product ID",
                        productIdField
                ),
                0,
                0
        );


        productGrid.add(
                createFieldBox(
                        "Product Name",
                        productNameField
                ),
                1,
                0
        );


        productGrid.add(
                createFieldBox(
                        "Category",
                        categoryField
                ),
                0,
                1
        );


        productGrid.add(
                createFieldBox(
                        "Brand",
                        brandField
                ),
                1,
                1
        );


        productGrid.add(
                createFieldBox(
                        "SKU",
                        skuField
                ),
                0,
                2
        );


        productGrid.add(
                createFieldBox(
                        "Barcode",
                        barcodeField
                ),
                1,
                2
        );


        productInformationCard.getChildren().addAll(
                informationTitle,
                productGrid,
                createFieldBox(
                        "Description",
                        descriptionArea
                )
        );


        // ========================================================
        // PRICING CARD
        // ========================================================

        VBox pricingCard =
                createCard();


        Text pricingTitle =
                createSectionTitle(
                        "Pricing Information"
                );


        TextField mrpField =
                createTextField(
                        doubleValue(
                                product.getMrp()
                        )
                );


        TextField costPriceField =
                createTextField(
                        doubleValue(
                                product.getCostPrice()
                        )
                );


        TextField sellingPriceField =
                createTextField(
                        doubleValue(
                                product.getSellingPrice()
                        )
                );


        TextField discountField =
                createTextField(
                        doubleValue(
                                product.getDiscount()
                        )
                );


        TextField taxField =
                createTextField(
                        doubleValue(
                                product.getTax()
                        )
                );


        GridPane pricingGrid =
                new GridPane();

        pricingGrid.setHgap(
                20
        );

        pricingGrid.setVgap(
                16
        );


        addThreeColumns(
                pricingGrid
        );


        pricingGrid.add(
                createFieldBox(
                        "MRP (₹)",
                        mrpField
                ),
                0,
                0
        );


        pricingGrid.add(
                createFieldBox(
                        "Cost Price (₹)",
                        costPriceField
                ),
                1,
                0
        );


        pricingGrid.add(
                createFieldBox(
                        "Selling Price (₹)",
                        sellingPriceField
                ),
                2,
                0
        );


        pricingGrid.add(
                createFieldBox(
                        "Discount (%)",
                        discountField
                ),
                0,
                1
        );


        pricingGrid.add(
                createFieldBox(
                        "Tax (%)",
                        taxField
                ),
                1,
                1
        );


        pricingCard.getChildren().addAll(
                pricingTitle,
                pricingGrid
        );


        // ========================================================
        // INVENTORY CARD
        // ========================================================

        VBox inventoryCard =
                createCard();


        Text inventoryTitle =
                createSectionTitle(
                        "Inventory Information"
                );


        TextField stockQuantityField =
                createTextField(
                        String.valueOf(
                                product.getStockQuantity()
                        )
                );


        TextField lowStockLimitField =
                createTextField(
                        String.valueOf(
                                product.getLowStockLimit()
                        )
                );


        TextField unitField =
                createTextField(
                        safe(
                                product.getUnit()
                        )
                );


        TextField statusField =
                createTextField(
                        safe(
                                product.getStatus()
                        )
                );


        TextField batchNumberField =
                createTextField(
                        safe(
                                product.getBatchNumber()
                        )
                );


        GridPane inventoryGrid =
                new GridPane();

        inventoryGrid.setHgap(
                20
        );

        inventoryGrid.setVgap(
                16
        );


        addThreeColumns(
                inventoryGrid
        );


        inventoryGrid.add(
                createFieldBox(
                        "Stock Quantity",
                        stockQuantityField
                ),
                0,
                0
        );


        inventoryGrid.add(
                createFieldBox(
                        "Low Stock Limit",
                        lowStockLimitField
                ),
                1,
                0
        );


        inventoryGrid.add(
                createFieldBox(
                        "Unit",
                        unitField
                ),
                2,
                0
        );


        inventoryGrid.add(
                createFieldBox(
                        "Status",
                        statusField
                ),
                0,
                1
        );


        inventoryGrid.add(
                createFieldBox(
                        "Batch Number",
                        batchNumberField
                ),
                1,
                1
        );


        inventoryCard.getChildren().addAll(
                inventoryTitle,
                inventoryGrid
        );


        // ========================================================
        // EXPIRY CARD
        // ========================================================

        VBox expiryCard =
                createCard();


        Text expiryTitle =
                createSectionTitle(
                        "Expiry Information"
                );


        ComboBox<String> expiryTrackingCombo =
                new ComboBox<>();

        expiryTrackingCombo.getItems().addAll(
                "Has Expiry",
                "No Expiry"
        );

        expiryTrackingCombo.setValue(
                safe(
                        product.getExpiryTracking()
                )
        );

        styleComboBox(
                expiryTrackingCombo
        );


        DatePicker manufacturingDatePicker =
                new DatePicker(
                        parseDate(
                                product.getManufacturingDate()
                        )
                );


        DatePicker expiryDatePicker =
                new DatePicker(
                        parseDate(
                                product.getExpiryDate()
                        )
                );


        styleDatePicker(
                manufacturingDatePicker
        );

        styleDatePicker(
                expiryDatePicker
        );


        boolean hasExpiry =
                "Has Expiry".equalsIgnoreCase(
                        product.getExpiryTracking()
                );


        manufacturingDatePicker.setDisable(
                !hasExpiry
        );

        expiryDatePicker.setDisable(
                !hasExpiry
        );


        expiryTrackingCombo.setOnAction(
                event -> {

                    boolean expiryEnabled =
                            "Has Expiry".equalsIgnoreCase(
                                    expiryTrackingCombo.getValue()
                            );


                    manufacturingDatePicker.setDisable(
                            !expiryEnabled
                    );


                    expiryDatePicker.setDisable(
                            !expiryEnabled
                    );


                    if (!expiryEnabled) {

                        manufacturingDatePicker.setValue(
                                null
                        );

                        expiryDatePicker.setValue(
                                null
                        );
                    }
                }
        );


        GridPane expiryGrid =
                new GridPane();

        expiryGrid.setHgap(
                20
        );

        expiryGrid.setVgap(
                16
        );


        addThreeColumns(
                expiryGrid
        );


        expiryGrid.add(
                createFieldBox(
                        "Expiry Tracking",
                        expiryTrackingCombo
                ),
                0,
                0
        );


        expiryGrid.add(
                createFieldBox(
                        "Manufacturing Date",
                        manufacturingDatePicker
                ),
                1,
                0
        );


        expiryGrid.add(
                createFieldBox(
                        "Expiry Date",
                        expiryDatePicker
                ),
                2,
                0
        );


        expiryCard.getChildren().addAll(
                expiryTitle,
                expiryGrid
        );


        // ========================================================
        // IMAGE URL CARD
        // ========================================================

        VBox imageUrlCard =
                createCard();


        Text imageTitle =
                createSectionTitle(
                        "Product Image"
                );


        TextField imageUrlField =
                createTextField(
                        safe(
                                product.getImageUrl()
                        )
                );


        imageUrlCard.getChildren().addAll(
                imageTitle,
                createFieldBox(
                        "Image URL",
                        imageUrlField
                )
        );


        // ========================================================
        // DELETE BUTTON
        // ========================================================

        Button deleteButton =
                new Button(
                        "Delete Product"
                );

        deleteButton.setPrefHeight(
                42
        );

        deleteButton.setStyle(
                "-fx-background-color: #FFFFFF;"
                        + "-fx-border-color: #D9534F;"
                        + "-fx-border-radius: 8px;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-text-fill: #D9534F;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );


        // ========================================================
        // SAVE BUTTON
        // ========================================================

        Button saveButton =
                new Button(
                        "Save Changes"
                );

        saveButton.setPrefHeight(
                42
        );

        saveButton.setStyle(
                "-fx-background-color: #B24E0A;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-cursor: hand;"
        );


        Region actionSpacer =
                new Region();

        HBox.setHgrow(
                actionSpacer,
                Priority.ALWAYS
        );


        HBox actionBox =
                new HBox(
                        15,
                        deleteButton,
                        actionSpacer,
                        saveButton
                );

        actionBox.setAlignment(
                Pos.CENTER
        );


        // ========================================================
        // SAVE ACTION
        // ========================================================

        saveButton.setOnAction(
                event -> {

                    try {

                        double mrp =
                                Double.parseDouble(
                                        mrpField
                                                .getText()
                                                .trim()
                                );


                        double costPrice =
                                Double.parseDouble(
                                        costPriceField
                                                .getText()
                                                .trim()
                                );


                        double sellingPrice =
                                Double.parseDouble(
                                        sellingPriceField
                                                .getText()
                                                .trim()
                                );


                        double discount =
                                Double.parseDouble(
                                        discountField
                                                .getText()
                                                .trim()
                                );


                        double tax =
                                Double.parseDouble(
                                        taxField
                                                .getText()
                                                .trim()
                                );


                        int stockQuantity =
                                Integer.parseInt(
                                        stockQuantityField
                                                .getText()
                                                .trim()
                                );


                        int lowStockLimit =
                                Integer.parseInt(
                                        lowStockLimitField
                                                .getText()
                                                .trim()
                                );


                        if (mrp < 0
                                || costPrice < 0
                                || sellingPrice < 0
                                || discount < 0
                                || discount > 100
                                || tax < 0
                                || stockQuantity < 0
                                || lowStockLimit < 0) {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Invalid Input",
                                    "Please enter valid product values."
                            );

                            return;
                        }


                        String expiryTracking =
                                expiryTrackingCombo
                                        .getValue();


                        String manufacturingDate =
                                "";


                        String expiryDate =
                                "";


                        if ("Has Expiry".equalsIgnoreCase(
                                expiryTracking
                        )) {


                            LocalDate manufacturingDateValue =
                                    manufacturingDatePicker
                                            .getValue();


                            LocalDate expiryDateValue =
                                    expiryDatePicker
                                            .getValue();


                            if (manufacturingDateValue == null
                                    || expiryDateValue == null) {

                                showAlert(
                                        Alert.AlertType.ERROR,
                                        "Date Required",
                                        "Please select manufacturing and expiry dates."
                                );

                                return;
                            }


                            if (expiryDateValue.isBefore(
                                    manufacturingDateValue
                            )) {

                                showAlert(
                                        Alert.AlertType.ERROR,
                                        "Invalid Date",
                                        "Expiry date cannot be before manufacturing date."
                                );

                                return;
                            }


                            manufacturingDate =
                                    manufacturingDateValue
                                            .toString();


                            expiryDate =
                                    expiryDateValue
                                            .toString();
                        }


                        ProductModel updatedProduct =
                                new ProductModel(

                                        product.getProductId(),

                                        productNameField
                                                .getText()
                                                .trim(),

                                        categoryField
                                                .getText()
                                                .trim(),

                                        brandField
                                                .getText()
                                                .trim(),

                                        descriptionArea
                                                .getText()
                                                .trim(),

                                        skuField
                                                .getText()
                                                .trim(),

                                        barcodeField
                                                .getText()
                                                .trim(),

                                        mrp,

                                        costPrice,

                                        sellingPrice,

                                        discount,

                                        tax,

                                        stockQuantity,

                                        lowStockLimit,

                                        unitField
                                                .getText()
                                                .trim(),

                                        statusField
                                                .getText()
                                                .trim(),

                                        batchNumberField
                                                .getText()
                                                .trim(),

                                        expiryTracking,

                                        manufacturingDate,

                                        expiryDate,

                                        imageUrlField
                                                .getText()
                                                .trim()
                                );


                        ProductController.updateProduct(
                                updatedProduct
                        );


                        showAlert(
                                Alert.AlertType.INFORMATION,
                                "Success",
                                "Product updated successfully."
                        );


                        Homepage.HomepageStage.setScene(
                                ShopkeeperInventory.inventoryScene()
                        );


                    } catch (
                            NumberFormatException exception
                    ) {

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Invalid Input",
                                "Please enter valid numbers."
                        );
                    }
                }
        );


        // ========================================================
        // DELETE ACTION
        // ========================================================

        deleteButton.setOnAction(
                event -> {


                    Alert confirmation =
                            new Alert(
                                    Alert.AlertType.CONFIRMATION
                            );


                    confirmation.setTitle(
                            "Delete Product"
                    );


                    confirmation.setHeaderText(
                            "Delete "
                                    + product.getProductName()
                                    + "?"
                    );


                    confirmation.setContentText(
                            "This action cannot be undone."
                    );


                    confirmation.showAndWait()
                            .ifPresent(
                                    response -> {


                                        if (response
                                                == ButtonType.OK) {


                                            ProductController
                                                    .deleteProduct(

                                                            product
                                                                    .getProductId()
                                                    );


                                            Homepage.HomepageStage
                                                    .setScene(

                                                            ShopkeeperInventory
                                                                    .inventoryScene()
                                                    );
                                        }
                                    }
                            );
                }
        );


        // ========================================================
        // ADD TO MAIN CONTENT
        // ========================================================

        mainContent.getChildren().addAll(

                pageHeader,

                productImageCard,

                productInformationCard,

                pricingCard,

                inventoryCard,

                expiryCard,

                imageUrlCard,

                actionBox
        );


        // ========================================================
        // SCROLL PANE
        // ========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: #F8F7FC;"
                        + "-fx-background-color: #F8F7FC;"
                        + "-fx-border-color: transparent;"
        );


        root.setCenter(
                scrollPane
        );




        return new Scene(
                root
        );
    }


    // ============================================================
    // PRODUCT IMAGE CARD
    // ============================================================

    private static VBox createProductImageCard(
            ProductModel product
    ) {


        VBox card =
                createCard();

        card.setAlignment(
                Pos.CENTER
        );


        StackPane imageBox =
                new StackPane();


        Rectangle background =
                new Rectangle(
                        200,
                        200
                );

        background.setArcWidth(
                20
        );

        background.setArcHeight(
                20
        );

        background.setFill(
                Color.web(
                        "#F2F2F2"
                )
        );


        imageBox.getChildren().add(
                background
        );


        if (product.getImageUrl() != null
                && !product.getImageUrl().isBlank()) {

            try {

                Image image =
                        new Image(
                                product.getImageUrl(),
                                true
                        );


                ImageView imageView =
                        new ImageView(
                                image
                        );


                imageView.setFitWidth(
                        190
                );

                imageView.setFitHeight(
                        190
                );

                imageView.setPreserveRatio(
                        true
                );


                imageBox.getChildren().add(
                        imageView
                );


            } catch (Exception exception) {

                Label noImage =
                        new Label(
                                "No Image"
                        );

                imageBox.getChildren().add(
                        noImage
                );
            }

        } else {

            Label noImage =
                    new Label(
                            "No Image"
                    );

            noImage.setStyle(
                    "-fx-text-fill: #888888;"
            );

            imageBox.getChildren().add(
                    noImage
            );
        }


        Text productName =
                new Text(
                        safe(
                                product.getProductName()
                        )
                );


        productName.setStyle(
                "-fx-font-size: 22px;"
                        + "-fx-font-weight: bold;"
        );


        Text productId =
                new Text(
                        "Product ID: "
                                + safe(
                                product.getProductId()
                        )
                );


        productId.setStyle(
                "-fx-font-size: 13px;"
                        + "-fx-fill: #777777;"
        );


        card.getChildren().addAll(
                imageBox,
                productName,
                productId
        );


        return card;
    }


    // ============================================================
    // CARD
    // ============================================================

    private static VBox createCard() {


        VBox card =
                new VBox(18);


        card.setPadding(
                new Insets(22)
        );


        card.setStyle(
                "-fx-background-color: white;"
                        + "-fx-background-radius: 12px;"
                        + "-fx-border-radius: 12px;"
                        + "-fx-border-color: #E5E5E5;"
        );


        return card;
    }


    // ============================================================
    // SECTION TITLE
    // ============================================================

    private static Text createSectionTitle(
            String title
    ) {


        Text text =
                new Text(
                        title
                );


        text.setStyle(
                "-fx-font-size: 18px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-fill: #292929;"
        );


        return text;
    }


    // ============================================================
    // FIELD BOX
    // ============================================================

    private static VBox createFieldBox(
            String labelText,
            Node field
    ) {


        Label label =
                new Label(
                        labelText
                );


        label.setStyle(
                "-fx-font-size: 12px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-text-fill: #666666;"
        );


        VBox box =
                new VBox(
                        6,
                        label,
                        field
                );


        VBox.setVgrow(
                field,
                Priority.NEVER
        );


        return box;
    }


    // ============================================================
    // TEXT FIELD
    // ============================================================

    private static TextField createTextField(
            String value
    ) {


        TextField field =
                new TextField(
                        value
                );


        field.setPrefHeight(
                40
        );


        field.setMaxWidth(
                Double.MAX_VALUE
        );


        field.setStyle(
                "-fx-background-color: white;"
                        + "-fx-border-color: #DDDDDD;"
                        + "-fx-border-radius: 8px;"
                        + "-fx-background-radius: 8px;"
                        + "-fx-font-size: 13px;"
        );


        return field;
    }


    // ============================================================
    // COMBO BOX STYLE
    // ============================================================

    private static void styleComboBox(
            ComboBox<String> comboBox
    ) {


        comboBox.setPrefHeight(
                40
        );


        comboBox.setMaxWidth(
                Double.MAX_VALUE
        );
    }


    // ============================================================
    // DATE PICKER STYLE
    // ============================================================

    private static void styleDatePicker(
            DatePicker datePicker
    ) {


        datePicker.setPrefHeight(
                40
        );


        datePicker.setMaxWidth(
                Double.MAX_VALUE
        );
    }


    // ============================================================
    // TWO COLUMNS
    // ============================================================

    private static void addTwoColumns(
            GridPane grid
    ) {


        ColumnConstraints column1 =
                new ColumnConstraints();

        column1.setPercentWidth(
                50
        );


        ColumnConstraints column2 =
                new ColumnConstraints();

        column2.setPercentWidth(
                50
        );


        grid.getColumnConstraints().addAll(
                column1,
                column2
        );
    }


    // ============================================================
    // THREE COLUMNS
    // ============================================================

    private static void addThreeColumns(
            GridPane grid
    ) {


        for (int i = 0; i < 3; i++) {


            ColumnConstraints column =
                    new ColumnConstraints();


            column.setPercentWidth(
                    33.33
            );


            grid.getColumnConstraints().add(
                    column
            );
        }
    }


    // ============================================================
    // PARSE DATE
    // ============================================================

    private static LocalDate parseDate(
            String date
    ) {


        if (date == null
                || date.isBlank()) {

            return null;
        }


        try {

            return LocalDate.parse(
                    date
            );

        } catch (Exception exception) {

            return null;
        }
    }


    // ============================================================
    // DOUBLE VALUE
    // ============================================================

    private static String doubleValue(
            Double value
    ) {


        if (value == null) {

            return "0";
        }


        return String.valueOf(
                value
        );
    }


    // ============================================================
    // SAFE STRING
    // ============================================================

    private static String safe(
            String value
    ) {


        if (value == null) {

            return "";
        }


        return value;
    }


    // ============================================================
    // ALERT
    // ============================================================

    private static void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {


        Alert alert =
                new Alert(
                        type
                );


        alert.setTitle(
                title
        );


        alert.setHeaderText(
                null
        );


        alert.setContentText(
                message
        );


        alert.showAndWait();
    }


    // ============================================================
    // SIDEBAR
    // ============================================================

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


        VBox.setVgrow(
                menu,
                Priority.ALWAYS
        );


        sidebar.getChildren().addAll(
                profileBox,
                menu
        );


        return sidebar;
    }


}
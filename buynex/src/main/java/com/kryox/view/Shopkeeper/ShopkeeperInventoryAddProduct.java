package com.kryox.view.Shopkeeper;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ConstantsMethods;
import com.kryox.controller.Shopkeeper.ImageUploadController;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ShopkeeperInventoryAddProduct {

        static FileChooser fileChooser;
        static File selectedFile;

        public static Scene addProductScene() {

                final double STAGE_WIDTH = 1280;
                final double STAGE_HEIGHT = 650;
                final double HEADER_HEIGHT = 70;
                final double FOOTER_HEIGHT = 60;
                final double SIDEBAR_WIDTH = 260;
                final double CENTER_WIDTH = STAGE_WIDTH - SIDEBAR_WIDTH;
                final double CENTER_HEIGHT = STAGE_HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT;

                BorderPane borderPane = new BorderPane();

                // ================================================================
                // HEADER
                // ================================================================

                HBox headerMainBox = ViewConstants.header();

                // Header background
                headerMainBox.setStyle(
                                "-fx-background-color: #EBCCB7;");

                borderPane.setTop(
                                headerMainBox);

                // ================================================================
                // SIDEBAR
                // ================================================================
                VBox sidebar = createSidebar();
                borderPane.setLeft(
                                sidebar);

                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ================================================================
                // CENTER
                // ================================================================

                VBox centerContent = new VBox();

                centerContent.setPrefWidth(
                                CENTER_WIDTH);

                centerContent.setMinWidth(
                                CENTER_WIDTH);

                centerContent.setMaxWidth(
                                CENTER_WIDTH);

                centerContent.setPadding(
                                new Insets(18, 22, 18, 22));

                centerContent.setSpacing(14);

                centerContent.setStyle(
                                "-fx-background-color: #EEE5DF;");

                // ================================================================
                // BREADCRUMB
                // ================================================================

                Text inventoryBreadcrumb = new Text("INVENTORY");

                inventoryBreadcrumb.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #65564F;");

                Text arrow = new Text("  ›  ");

                arrow.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #777777;");

                Text addProductBreadcrumb = new Text("ADD PRODUCT");

                addProductBreadcrumb.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #A94408;");

                HBox breadcrumb = new HBox(
                                4,
                                inventoryBreadcrumb,
                                arrow,
                                addProductBreadcrumb);

                breadcrumb.setAlignment(
                                Pos.CENTER_LEFT);

                // ================================================================
                // TITLE
                // ================================================================

                Text pageTitle = new Text("Add New Product");

                pageTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202020;");

                // ================================================================
                // SCAN
                // ================================================================

                Text scanIcon = new Text("▥");

                scanIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #A94408;");

                Text scanText = new Text("Scan Product");

                scanText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #A94408;");

                HBox scanContent = new HBox(
                                10,
                                scanIcon,
                                scanText);

                scanContent.setAlignment(
                                Pos.CENTER);

                Button scanProductButton = new Button();

                scanProductButton.setGraphic(
                                scanContent);

                scanProductButton.setPrefWidth(175);
                scanProductButton.setMinWidth(175);
                scanProductButton.setMaxWidth(175);
                scanProductButton.setPrefHeight(40);

                scanProductButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #A94408;" +
                                                "-fx-border-width: 1.5px;" +
                                                "-fx-border-radius: 7px;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                Text aiIcon = new Text("✦");

                aiIcon.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #A94408;");

                Text aiText = new Text("AI-POWERED\nAUTOFILL");

                aiText.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #A94408;");

                HBox aiBadge = new HBox(
                                5,
                                aiIcon,
                                aiText);

                aiBadge.setAlignment(
                                Pos.CENTER);

                aiBadge.setPrefWidth(140);
                aiBadge.setMinWidth(140);
                aiBadge.setMaxWidth(140);
                aiBadge.setPrefHeight(24);

                aiBadge.setStyle(
                                "-fx-background-color: #F4E5E0;" +
                                                "-fx-border-color: #E5B8A5;" +
                                                "-fx-border-radius: 12px;" +
                                                "-fx-background-radius: 12px;");

                HBox scanRow = new HBox(
                                22,
                                scanProductButton,
                                aiBadge);

                scanRow.setAlignment(
                                Pos.CENTER_LEFT);

                // ================================================================
                // DESCRIPTION
                // ================================================================

                Text description = new Text(
                                "Enter the details of your new product below. Ensure high-quality images and accurate\n"
                                                + "pricing to attract more local buyers.");

                description.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #6B5B53;");

                // ================================================================
                // BASIC DETAILS
                // ================================================================

                VBox basicCard = new VBox();

                basicCard.setPadding(
                                new Insets(18));

                basicCard.setSpacing(10);

                basicCard.setPrefWidth(560);
                basicCard.setMinWidth(560);
                basicCard.setMaxWidth(560);

                basicCard.setPrefHeight(390);

                basicCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;");

                Text basicIcon = new Text("ⓘ");

                basicIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-fill: #B24E0A;");

                Text basicTitle = new Text("Basic Details");

                basicTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252525;");

                HBox basicTitleRow = new HBox(
                                9,
                                basicIcon,
                                basicTitle);

                basicTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                // Product name
                Label productNameLabel = new Label("PRODUCT NAME");

                productNameLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextField productNameField = new TextField();

                productNameField.setPromptText(
                                "e.g., Organic Honey 500g");

                productNameField.setPrefHeight(38);

                productNameField.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                VBox productNameBox = new VBox(
                                4,
                                productNameLabel,
                                productNameField);

                // Category
                Label categoryLabel = new Label("CATEGORY");

                categoryLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                ComboBox<String> categoryCombo = new ComboBox<>();

                categoryCombo.getItems().addAll(
                                "Grocery",
                                "Dairy",
                                "Bakery",
                                "Produce",
                                "Beverages",
                                "Snacks",
                                "Personal Care",
                                "Household");

                categoryCombo.setPromptText(
                                "Select Category");

                categoryCombo.setPrefHeight(38);
                categoryCombo.setMaxWidth(
                                Double.MAX_VALUE);

                categoryCombo.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox categoryBox = new VBox(
                                4,
                                categoryLabel,
                                categoryCombo);

                // SKU
                Label skuLabel = new Label("SKU (STOCK KEEPING UNIT)");

                skuLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextField skuField = new TextField();

                skuField.setPromptText(
                                "e.g., ORG-HON-500");

                skuField.setPrefHeight(38);

                skuField.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                VBox skuBox = new VBox(
                                4,
                                skuLabel,
                                skuField);

                HBox categorySkuRow = new HBox(
                                22,
                                categoryBox,
                                skuBox);

                HBox.setHgrow(
                                categoryBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                skuBox,
                                Priority.ALWAYS);

                // Brand
                Label brandLabel = new Label("BRAND");

                brandLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextField brandField = new TextField();

                brandField.setPromptText(
                                "e.g., Tata");

                brandField.setPrefHeight(38);

                brandField.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                VBox brandBox = new VBox(
                                4,
                                brandLabel,
                                brandField);

                // Barcode
                Label barcodeLabel = new Label("BARCODE");

                barcodeLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextField barcodeField = new TextField();

                barcodeField.setPromptText(
                                "e.g., 8901234567890");

                barcodeField.setPrefHeight(38);

                barcodeField.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                VBox barcodeBox = new VBox(
                                4,
                                barcodeLabel,
                                barcodeField);

                HBox brandBarcodeRow = new HBox(
                                22,
                                brandBox,
                                barcodeBox);

                HBox.setHgrow(
                                brandBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                barcodeBox,
                                Priority.ALWAYS);

                // Description
                Label descLabel = new Label("DESCRIPTION");

                descLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextArea descriptionArea = new TextArea();

                descriptionArea.setPromptText(
                                "Describe the product, its features, and benefits...");

                descriptionArea.setPrefHeight(70);
                descriptionArea.setWrapText(true);

                descriptionArea.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 10px;");

                VBox descriptionBox = new VBox(
                                4,
                                descLabel,
                                descriptionArea);

                basicCard.getChildren().addAll(
                                basicTitleRow,
                                productNameBox,
                                categorySkuRow,
                                brandBarcodeRow,
                                descriptionBox);

                // ================================================================
                // PRICING
                // ================================================================

                VBox pricingCard = new VBox();

                pricingCard.setPadding(
                                new Insets(18));

                pricingCard.setSpacing(10);

                pricingCard.setPrefWidth(270);
                pricingCard.setMinWidth(270);
                pricingCard.setMaxWidth(270);

                pricingCard.setPrefHeight(305);

                pricingCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;");

                Text pricingIcon = new Text("◇");

                pricingIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #B24E0A;");

                Text pricingTitle = new Text("Pricing");

                pricingTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252525;");

                HBox pricingTitleRow = new HBox(
                                9,
                                pricingIcon,
                                pricingTitle);

                pricingTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                TextField mrpField = createMoneyField("0.00");

                VBox mrpContainer = createMoneyContainer(
                                "ORIGINAL PRICE (MRP)",
                                mrpField,
                                false);

                TextField costPriceField = createMoneyField("0.00");

                VBox costPriceContainer = createMoneyContainer(
                                "COST PRICE",
                                costPriceField,
                                false);

                TextField sellingPriceField = createMoneyField("0.00");

                VBox sellingContainer = createMoneyContainer(
                                "SELLING PRICE",
                                sellingPriceField,
                                true);

                TextField discountField = createPercentField("0");

                VBox discountContainer = createPercentContainer(
                                "DISCOUNT (%)",
                                discountField);

                TextField taxField = createPercentField("0");

                VBox taxContainer = createPercentContainer(
                                "TAX (%)",
                                taxField);

                pricingCard.getChildren().addAll(
                                pricingTitleRow,
                                mrpContainer,
                                costPriceContainer,
                                sellingContainer,
                                discountContainer,
                                taxContainer);

                // ================================================================
                // INVENTORY
                // ================================================================

                VBox stockCard = new VBox();

                stockCard.setPadding(
                                new Insets(18));

                stockCard.setSpacing(10);

                stockCard.setPrefWidth(270);
                stockCard.setMinWidth(270);
                stockCard.setMaxWidth(270);

                stockCard.setPrefHeight(365);

                stockCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;");

                Text stockIcon = new Text("▣");

                stockIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #B24E0A;");

                Text stockTitle = new Text("Inventory Stock");

                stockTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252525;");

                HBox stockTitleRow = new HBox(
                                9,
                                stockIcon,
                                stockTitle);

                stockTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                TextField quantityField = new TextField("0");

                VBox quantityBox = createNormalField(
                                "STOCK QUANTITY",
                                quantityField);

                TextField thresholdField = new TextField("5");

                VBox thresholdBox = createNormalField(
                                "LOW STOCK ALERT THRESHOLD",
                                thresholdField);

                // UNIT — method scope
                ComboBox<String> unitCombo = new ComboBox<>();

                unitCombo.getItems().addAll(
                                "Piece",
                                "Kg",
                                "Gram",
                                "Litre",
                                "Millilitre",
                                "Pack",
                                "Box",
                                "Dozen");

                unitCombo.setValue(
                                "Piece");

                unitCombo.setPrefHeight(38);
                unitCombo.setMaxWidth(
                                Double.MAX_VALUE);

                unitCombo.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox unitBox = new VBox(
                                4,
                                new Label("UNIT"),
                                unitCombo);

                ComboBox<String> statusCombo = new ComboBox<>();

                statusCombo.getItems().addAll(
                                "ACTIVE",
                                "INACTIVE",
                                "OUT_OF_STOCK");

                statusCombo.setValue(
                                "ACTIVE");

                statusCombo.setPrefHeight(38);
                statusCombo.setMaxWidth(
                                Double.MAX_VALUE);

                statusCombo.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox statusBox = new VBox(
                                4,
                                new Label("STATUS"),
                                statusCombo);

                HBox unitStatusRow = new HBox(
                                14,
                                unitBox,
                                statusBox);

                HBox.setHgrow(
                                unitBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                statusBox,
                                Priority.ALWAYS);

                // ================================================================
                // BATCH NUMBER
                // ================================================================

                Label batchLabel = new Label("BATCH NUMBER");

                batchLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                TextField batchNumberField = new TextField();

                batchNumberField.setPromptText(
                                "e.g., BATCH-2026-001");

                batchNumberField.setPrefHeight(38);

                batchNumberField.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                VBox batchBox = new VBox(
                                4,
                                batchLabel,
                                batchNumberField);

                // ================================================================
                // EXPIRY TRACKING
                // ================================================================

                Label expiryTrackingLabel = new Label("EXPIRY TRACKING");

                expiryTrackingLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                ComboBox<String> expiryTrackingCombo = new ComboBox<>();

                expiryTrackingCombo.getItems().addAll(
                                "No Expiry",
                                "Has Expiry");

                expiryTrackingCombo.setValue(
                                "No Expiry");

                expiryTrackingCombo.setPrefHeight(38);
                expiryTrackingCombo.setMaxWidth(
                                Double.MAX_VALUE);

                expiryTrackingCombo.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox expiryTrackingBox = new VBox(
                                4,
                                expiryTrackingLabel,
                                expiryTrackingCombo);

                HBox batchExpiryRow = new HBox(
                                14,
                                batchBox,
                                expiryTrackingBox);

                HBox.setHgrow(
                                batchBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                expiryTrackingBox,
                                Priority.ALWAYS);

                // ================================================================
                // MANUFACTURING DATE
                // ================================================================

                Label manufacturingLabel = new Label("MANUFACTURING DATE");

                manufacturingLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                DatePicker manufacturingDatePicker = new DatePicker();

                manufacturingDatePicker.setPrefHeight(38);
                manufacturingDatePicker.setMaxWidth(
                                Double.MAX_VALUE);

                manufacturingDatePicker.setPromptText(
                                "Select date");

                manufacturingDatePicker.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox manufacturingDateBox = new VBox(
                                4,
                                manufacturingLabel,
                                manufacturingDatePicker);

                // ================================================================
                // EXPIRY DATE
                // ================================================================

                Label expiryLabel = new Label("EXPIRY DATE");

                expiryLabel.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                DatePicker expiryDatePicker = new DatePicker();

                expiryDatePicker.setPrefHeight(38);
                expiryDatePicker.setMaxWidth(
                                Double.MAX_VALUE);

                expiryDatePicker.setPromptText(
                                "Select expiry date");

                expiryDatePicker.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox expiryDateBox = new VBox(
                                4,
                                expiryLabel,
                                expiryDatePicker);

                HBox datesRow = new HBox(
                                14,
                                manufacturingDateBox,
                                expiryDateBox);

                HBox.setHgrow(
                                manufacturingDateBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                expiryDateBox,
                                Priority.ALWAYS);

                manufacturingDatePicker.setDisable(true);
                expiryDatePicker.setDisable(true);

                expiryTrackingCombo.setOnAction(e -> {

                        boolean hasExpiry = "Has Expiry".equals(
                                        expiryTrackingCombo.getValue());

                        manufacturingDatePicker.setDisable(
                                        !hasExpiry);

                        expiryDatePicker.setDisable(
                                        !hasExpiry);

                        if (!hasExpiry) {

                                manufacturingDatePicker.setValue(
                                                null);

                                expiryDatePicker.setValue(
                                                null);
                        }
                });

                expiryDatePicker.setDayCellFactory(
                                picker -> new javafx.scene.control.DateCell() {

                                        @Override
                                        public void updateItem(
                                                        LocalDate date,
                                                        boolean empty) {

                                                super.updateItem(
                                                                date,
                                                                empty);

                                                if (date != null &&
                                                                manufacturingDatePicker
                                                                                .getValue() != null) {

                                                        setDisable(
                                                                        date.isBefore(
                                                                                        manufacturingDatePicker
                                                                                                        .getValue()));
                                                }
                                        }
                                });

                stockCard.getChildren().addAll(
                                stockTitleRow,
                                quantityBox,
                                thresholdBox,
                                unitStatusRow,
                                batchExpiryRow,
                                datesRow);

                // ================================================================
                // MEDIA
                // ================================================================

                VBox mediaCard = new VBox();

                mediaCard.setPadding(
                                new Insets(18));

                mediaCard.setSpacing(12);

                mediaCard.setPrefWidth(560);
                mediaCard.setMinWidth(560);
                mediaCard.setMaxWidth(560);

                mediaCard.setPrefHeight(245);

                mediaCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;");

                Text mediaIcon = new Text("▧");

                mediaIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #B24E0A;");

                Text mediaTitle = new Text("Media");

                mediaTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252525;");

                HBox mediaTitleRow = new HBox(
                                9,
                                mediaIcon,
                                mediaTitle);

                mediaTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                Text uploadIcon = new Text("☁");

                uploadIcon.setStyle(
                                "-fx-font-size: 31px;" +
                                                "-fx-fill: #A94408;");

                Text uploadText = new Text(
                                "Drag & drop product images here");

                uploadText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-fill: #333333;");

                Text browseText = new Text(
                                "or click to browse from your computer");

                browseText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #6A5146;");

                Text fileTypeText = new Text(
                                "JPG, PNG, WEBP up to 5MB");

                fileTypeText.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #999999;");

                VBox uploadContent = new VBox(
                                5,
                                uploadIcon,
                                uploadText,
                                browseText,
                                fileTypeText);

                uploadContent.setAlignment(
                                Pos.CENTER);

                ImageView productImagePreview = new ImageView();

                productImagePreview.setFitWidth(100);
                productImagePreview.setFitHeight(100);
                productImagePreview.setPreserveRatio(true);
                productImagePreview.setVisible(false);

                Text selectedImageText = new Text("No image selected");

                selectedImageText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #777777;");

                VBox uploadStack = new VBox(
                                8,
                                uploadContent,
                                productImagePreview,
                                selectedImageText);

                uploadStack.setAlignment(
                                Pos.CENTER);

                StackPane uploadArea = new StackPane(
                                uploadStack);

                uploadArea.setPrefHeight(155);
                uploadArea.setMinHeight(155);

                uploadArea.setStyle(
                                "-fx-background-color: #F7F6FA;" +
                                                "-fx-border-color: #E6B79F;" +
                                                "-fx-border-width: 1.5px;" +
                                                "-fx-border-style: dashed;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-cursor: hand;");

                mediaCard.getChildren().addAll(
                                mediaTitleRow,
                                uploadArea);

                // ================================================================
                // SELECTED IMAGE
                // ================================================================

                final File[] selectedProductImage = new File[1];

                // ================================================================
                // COLUMNS
                // ================================================================

                VBox leftColumn = new VBox(
                                14,
                                basicCard,
                                mediaCard);

                leftColumn.setPrefWidth(560);
                leftColumn.setMinWidth(560);
                leftColumn.setMaxWidth(560);

                VBox rightColumn = new VBox(
                                14,
                                pricingCard,
                                stockCard);

                rightColumn.setPrefWidth(270);
                rightColumn.setMinWidth(270);
                rightColumn.setMaxWidth(270);

                HBox formRow = new HBox(
                                22,
                                leftColumn,
                                rightColumn);

                formRow.setAlignment(
                                Pos.TOP_LEFT);

                // ================================================================
                // BUTTONS
                // ================================================================

                Region buttonSpace = new Region();

                HBox.setHgrow(
                                buttonSpace,
                                Priority.ALWAYS);

                Button discardButton = new Button("Discard");

                discardButton.setPrefWidth(135);
                discardButton.setMinWidth(135);
                discardButton.setMaxWidth(135);
                discardButton.setPrefHeight(38);

                discardButton.setStyle(
                                "-fx-background-color: #ECEAF0;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                Text publishIcon = new Text("⇧");

                publishIcon.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: white;");

                Text publishText = new Text("Publish Product");

                publishText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;");

                HBox publishContent = new HBox(
                                8,
                                publishIcon,
                                publishText);

                publishContent.setAlignment(
                                Pos.CENTER);

                Button publishButton = new Button();

                publishButton.setGraphic(
                                publishContent);

                publishButton.setPrefWidth(200);
                publishButton.setMinWidth(200);
                publishButton.setMaxWidth(200);
                publishButton.setPrefHeight(38);

                publishButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                HBox bottomButtons = new HBox(
                                20,
                                buttonSpace,
                                discardButton,
                                publishButton);

                bottomButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                // ================================================================
                // CENTER CONTENT
                // ================================================================

                centerContent.getChildren().addAll(
                                breadcrumb,
                                pageTitle,
                                scanRow,
                                description,
                                formRow,
                                bottomButtons);

                // ================================================================
                // SCROLL
                // ================================================================

                ScrollPane centerScroll = new ScrollPane(
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

                centerScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                centerScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                centerScroll.setStyle(
                                "-fx-background-color: #EEE5DF;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                centerScroll);

                // ================================================================
                // SCAN
                // ================================================================

                scanProductButton.setOnAction(e -> System.out.println(
                                "Scan Product clicked"));

                // ================================================================
                // IMAGE UPLOAD
                // ================================================================

                uploadArea.setOnMouseClicked(e -> {

                        fileChooser = new FileChooser();

                        fileChooser.setTitle(
                                        "Select Product Image");

                        fileChooser.getExtensionFilters().add(
                                        new FileChooser.ExtensionFilter(
                                                        "Image Files (*.jpg, *.jpeg, *.png, *.webp)",
                                                        "*.jpg",
                                                        "*.jpeg",
                                                        "*.png",
                                                        "*.webp"));

                        selectedFile = fileChooser.showOpenDialog(
                                        Homepage.HomepageStage);

                        if (selectedFile != null) {

                                selectedProductImage[0] = selectedFile;

                                Image image = new Image(
                                                selectedFile.toURI()
                                                                .toString(),
                                                100,
                                                100,
                                                true,
                                                true);

                                productImagePreview.setImage(
                                                image);

                                productImagePreview.setVisible(
                                                true);

                                uploadContent.setVisible(
                                                false);

                                selectedImageText.setText(
                                                selectedFile.getName());
                        }
                });

                // ================================================================
                // DISCARD
                // ================================================================

                discardButton.setOnAction(e -> Homepage.HomepageStage.setScene(
                                ShopkeeperInventory.inventoryScene()));

                // ================================================================
                // PUBLISH PRODUCT
                // ================================================================

                publishButton.setOnAction(e -> {

                        String productName = productNameField.getText().trim();

                        String category = categoryCombo.getValue();

                        String brand = brandField.getText().trim();

                        String descriptionValue = descriptionArea.getText().trim();

                        String sku = skuField.getText().trim();

                        String barcode = barcodeField.getText().trim();

                        Double mrp = Double.parseDouble(mrpField.getText().trim());

                        Double costPrice = Double.parseDouble(costPriceField.getText().trim());

                        Double sellingPrice = Double.parseDouble(sellingPriceField.getText().trim());

                        Double discount = Double.parseDouble(discountField.getText().trim());

                        Double tax = Double.parseDouble(taxField.getText().trim());

                        int stockQuantity = Integer.parseInt(quantityField.getText().trim());

                        int lowStockLimit = Integer.parseInt(thresholdField.getText().trim());

                        String unit = unitCombo.getValue();

                        String status = statusCombo.getValue();

                        String batchNumber = batchNumberField.getText().trim();

                        String expiryTracking = expiryTrackingCombo.getValue();

                        String manufacturingDate = null;
                        String expiryDate = null;

                        if ("Has Expiry".equals(expiryTracking)) {
                        if (manufacturingDatePicker.getValue() != null) {
                                manufacturingDate = manufacturingDatePicker.getValue().toString();
                        }

                        if (expiryDatePicker.getValue() != null) {
                                expiryDate = expiryDatePicker.getValue().toString();
                        }
                        }

                        if (productName.isEmpty() ||
                                        category == null ||
                                        category.isBlank()) {

                                System.out.println(
                                                "Product name and category are required.");
                                ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Error",
                                                "Product name and category are required.");

                                return;
                        }

                        if ("Has Expiry".equals(expiryTracking)) {

                                if (manufacturingDate == null ||
                                                expiryDate == null) {

                                        System.out.println(
                                                        "Manufacturing date and expiry date are required "
                                                                        + "when expiry tracking is enabled.");
                                        ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Error",
                                                        "Manufacturing date and expiry date are required when expiry tracking is enabled.");

                                        return;
                                }

                                if (LocalDate.parse(expiryDate).isBefore(
        LocalDate.parse(manufacturingDate))){

                                        System.out.println(
                                                        "Expiry date cannot be before manufacturing date.");
                                        ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Error",
                                                        "Expiry date cannot be before manufacturing date.");

                                        return;
                                }
                        }

                        try {

                                if (mrp < 0 ||
                                                costPrice < 0 ||
                                                sellingPrice < 0 ||
                                                discount < 0 ||
                                                discount > 100 ||
                                                tax < 0 ||
                                                stockQuantity < 0 ||
                                                lowStockLimit < 0) {

                                        System.out.println(
                                                        "Please enter valid numeric values.");
                                        ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Error",
                                                        "Please enter valid numeric values.");

                                        return;
                                }
                                String id = UUID.randomUUID()
                                                .toString()
                                                .replace("-", "")
                                                .replace("_", "")
                                                .replace(":", "")
                                                .replace(" ", "")
                                                .substring(0, 6)
                                                .toUpperCase();
                                String productId = "PRODUCT-" + id;

                                if (selectedFile == null) {
                                        System.out.println("Please select an image.");
                                        ConstantsMethods.showAlert(Alert.AlertType.ERROR, "Error",
                                                        "Please select an image.");
                                        return;
                                }
                                ImageUploadController imageUploadController = new ImageUploadController();
                                String imageUrl = imageUploadController.imageUpload(selectedFile);

                                ProductModel productModel = new ProductModel(productId, productName, category, brand,
                                                descriptionValue, sku, barcode, mrp, costPrice, sellingPrice, discount,
                                                tax, stockQuantity, lowStockLimit, unit, status, batchNumber,
                                                expiryTracking, manufacturingDate, expiryDate, imageUrl);

                                ProductController.addProduct(productModel);

                                ConstantsMethods.showAlert(Alert.AlertType.INFORMATION, "Success",
                                                "Product added successfully.");

                                Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory.inventoryScene());

                        } catch (NumberFormatException ex) {

                                System.out.println(
                                                "Please enter valid numbers for "
                                                                + "MRP, cost price, selling price, "
                                                                + "discount, tax, stock quantity, "
                                                                + "and low-stock threshold.");
                        }
                });

                // ================================================================
                // SCENE
                // ================================================================

                Scene addProductScene = new Scene(
                                borderPane,
                                1550,
                                850);

                addProductScene.setFill(
                                Color.web("#EEE5DF"));

                return addProductScene;
        }

        // ================================================================
        // HELPER METHODS
        // ================================================================

        private static TextField createMoneyField(
                        String value) {

                TextField field = new TextField(value);

                field.setPrefHeight(38);

                field.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                return field;
        }

        private static VBox createMoneyContainer(
                        String labelText,
                        TextField field,
                        boolean sellingPrice) {

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                HBox moneyBox = new HBox(
                                8,
                                new Text("₹"),
                                field);

                moneyBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text currency = (Text) moneyBox.getChildren().get(0);

                currency.setStyle(
                                sellingPrice
                                                ? "-fx-font-size: 13px;" +
                                                                "-fx-fill: #A94408;" +
                                                                "-fx-font-weight: bold;"
                                                : "-fx-font-size: 13px;" +
                                                                "-fx-fill: #6B5144;");

                if (sellingPrice) {

                        field.setStyle(
                                        "-fx-background-color: #FBF4F0;" +
                                                        "-fx-background-radius: 7px;" +
                                                        "-fx-border-color: transparent;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-text-fill: #A94408;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-padding: 0 12px;");
                }

                HBox.setHgrow(
                                field,
                                Priority.ALWAYS);

                return new VBox(
                                4,
                                label,
                                moneyBox);
        }

        private static TextField createPercentField(
                        String value) {

                TextField field = new TextField(value);

                field.setPrefHeight(38);

                field.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                return field;
        }

        private static VBox createPercentContainer(
                        String labelText,
                        TextField field) {

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                HBox percentBox = new HBox(
                                8,
                                field,
                                new Text("%"));

                percentBox.setAlignment(
                                Pos.CENTER_LEFT);

                ((Text) percentBox.getChildren().get(1))
                                .setStyle(
                                                "-fx-font-size: 13px;" +
                                                                "-fx-fill: #6B5144;");

                HBox.setHgrow(
                                field,
                                Priority.ALWAYS);

                return new VBox(
                                4,
                                label,
                                percentBox);
        }

        private static VBox createNormalField(
                        String labelText,
                        TextField field) {

                Label label = new Label(labelText);

                label.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;");

                field.setPrefHeight(38);

                field.setStyle(
                                "-fx-background-color: #F4F3F7;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-padding: 0 12px;");

                return new VBox(
                                4,
                                label,
                                field);
        }

        // ================================================================
        // SIDEBAR
        // ================================================================

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
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                true);

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

                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                return sidebar;
        }
}
package com.kryox.view.Customer;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.model.Shopkeeper.ProductModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
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

public class CustomerProductDetails {

    private final String userId;
    private final ProductModel product;
    private final Runnable backCallback;
    private int selectedQuantity = 1;

    public CustomerProductDetails(String userId, ProductModel product, Runnable backCallback) {
        this.userId = (userId != null && !userId.isBlank()) ? userId : "guest";
        this.product = product != null ? product : new ProductModel();
        this.backCallback = backCallback;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F8F7FA;");

        // Top Navigation Header
        root.setTop(createTopBar());

        // Body Content
        VBox contentBox = createDetailsBody();

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: #F8F7FA; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        return new Scene(root, 1550, 850);
    }

    private HBox createTopBar() {
        HBox topBar = new HBox(16);
        topBar.setPrefHeight(68);
        topBar.setPadding(new Insets(12, 32, 12, 32));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle(
                "-fx-background-color: #ebccb7;" +
                "-fx-border-color: #dfc1ac;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Back Button
        Button backBtn = new Button("← Back");
        backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #D9BDAC;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-cursor: hand;"
        );
        backBtn.setOnMouseEntered(e -> backBtn.setStyle(
                "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-cursor: hand;"
        ));
        backBtn.setOnMouseExited(e -> backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #D9BDAC;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-cursor: hand;"
        ));
        backBtn.setOnAction(e -> {
            if (backCallback != null) {
                backCallback.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
        });

        // Breadcrumb
        String cat = (product.getCategory() != null && !product.getCategory().isBlank()) ? product.getCategory() : "Store";
        String pName = (product.getProductName() != null && !product.getProductName().isBlank()) ? product.getProductName() : "Product Details";
        Label breadcrumb = new Label("Home  /  " + cat + "  /  " + pName);
        breadcrumb.setStyle("-fx-font-size: 12px; -fx-text-fill: #604D43; -fx-font-weight: 600;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Cart Button
        Button cartButton = new Button("🛒 Cart");
        cartButton.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 8 18 8 18;" +
                "-fx-cursor: hand;"
        );
        cartButton.setOnAction(e -> CustomerNavigation.navigateToCart(userId));

        topBar.getChildren().addAll(backBtn, breadcrumb, spacer, cartButton);
        return topBar;
    }

    private VBox createDetailsBody() {
        VBox container = new VBox(24);
        container.setPadding(new Insets(30, 48, 40, 48));
        container.setMaxWidth(1300);
        container.setAlignment(Pos.TOP_CENTER);

        HBox mainSplit = new HBox(40);
        mainSplit.setAlignment(Pos.TOP_CENTER);

        // Left Column: Image Box & Trust Badges
        VBox leftColumn = createLeftColumn();

        // Right Column: Product Info, Price, Quantity & Actions
        VBox rightColumn = createRightColumn();

        HBox.setHgrow(leftColumn, Priority.SOMETIMES);
        HBox.setHgrow(rightColumn, Priority.ALWAYS);

        mainSplit.getChildren().addAll(leftColumn, rightColumn);
        container.getChildren().add(mainSplit);

        return container;
    }

    private VBox createLeftColumn() {
        VBox left = new VBox(20);
        left.setPrefWidth(460);
        left.setMinWidth(420);
        left.setMaxWidth(500);

        // Image Card
        StackPane imageCard = new StackPane();
        imageCard.setPrefSize(460, 420);
        imageCard.setMinSize(420, 380);
        imageCard.setMaxSize(500, 460);
        imageCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #E7E3EC;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1;"
        );

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(16);
        cardShadow.setOffsetY(6);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.08));
        imageCard.setEffect(cardShadow);

        String imgUrl = product.getImageUrl();
        ImageView imageView = new ImageView();
        imageView.setFitWidth(360);
        imageView.setFitHeight(320);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        boolean loaded = false;
        if (imgUrl != null && !imgUrl.isBlank()) {
            try {
                Image img = new Image(imgUrl, 360, 320, true, true, true);
                if (!img.isError()) {
                    imageView.setImage(img);
                    loaded = true;
                }
            } catch (Exception ignored) {}
        }

        if (!loaded) {
            Label fallbackIcon = new Label("📦");
            fallbackIcon.setStyle("-fx-font-size: 90px; -fx-text-fill: #D3CAD7;");
            imageCard.getChildren().add(fallbackIcon);
        } else {
            imageCard.getChildren().add(imageView);
        }

        // Discount tag on top-left of image
        Double disc = product.getDiscount();
        if (disc != null && disc > 0) {
            Label discBadge = new Label(String.format("🔥 %.0f%% OFF", disc));
            discBadge.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 6 12 6 12;" +
                    "-fx-background-radius: 12;"
            );
            StackPane.setAlignment(discBadge, Pos.TOP_LEFT);
            StackPane.setMargin(discBadge, new Insets(16, 0, 0, 16));
            imageCard.getChildren().add(discBadge);
        }

        // Trust Highlights Box
        VBox trustBox = new VBox(10);
        trustBox.setPadding(new Insets(16));
        trustBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #ECE8EE;" +
                "-fx-border-radius: 14;"
        );

        Label t1 = new Label("⚡ Hyperlocal Delivery in 30 Mins");
        t1.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");
        Label t2 = new Label("🛡️ 7-Day Hassle-Free Replacement / Return");
        t2.setStyle("-fx-font-size: 11px; -fx-text-fill: #555555;");
        Label t3 = new Label("🔒 100% Genuine & Verified Store Product");
        t3.setStyle("-fx-font-size: 11px; -fx-text-fill: #555555;");

        trustBox.getChildren().addAll(t1, t2, t3);

        left.getChildren().addAll(imageCard, trustBox);
        return left;
    }

    private VBox createRightColumn() {
        VBox right = new VBox(16);
        right.setAlignment(Pos.TOP_LEFT);

        // Brand & Category Row
        String brand = (product.getBrand() != null && !product.getBrand().isBlank()) ? product.getBrand() : "Brand";
        String category = (product.getCategory() != null && !product.getCategory().isBlank()) ? product.getCategory() : "General";

        Label brandLabel = new Label(brand.toUpperCase());
        brandLabel.setStyle(
                "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 10 4 10;" +
                "-fx-background-radius: 8;"
        );

        Label catLabel = new Label("▣ " + category);
        catLabel.setStyle(
                "-fx-background-color: #F0EDF3;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 10 4 10;" +
                "-fx-background-radius: 8;"
        );

        HBox tagRow = new HBox(10, brandLabel, catLabel);
        tagRow.setAlignment(Pos.CENTER_LEFT);

        // Product Name
        String name = (product.getProductName() != null && !product.getProductName().isBlank()) ? product.getProductName() : "Product Details";
        Label nameLabel = new Label(name);
        nameLabel.setWrapText(true);
        nameLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 26px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #1E1E24;"
        );

        Label shopBadge = new Label();
        shopBadge.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #B84200;");
        CustomerShopResolver.bindShopName(shopBadge, product.getShopkeeperUid(), "🏪 Sold by: ");

        // Ratings Badge
        Label ratingStar = new Label("★ 4.8");
        ratingStar.setStyle(
                "-fx-background-color: #2E7D32;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 3 8 3 8;" +
                "-fx-background-radius: 6;"
        );
        Label ratingCount = new Label("(128 Verified Ratings & Reviews)");
        ratingCount.setStyle("-fx-font-size: 11px; -fx-text-fill: #777777;");
        HBox ratingRow = new HBox(8, ratingStar, ratingCount);
        ratingRow.setAlignment(Pos.CENTER_LEFT);

        // Pricing Box
        double rawSellingPrice = product.getSellingPrice() != null ? product.getSellingPrice() : 0.0;
        double rawMrp = product.getMrp() != null ? product.getMrp() : rawSellingPrice;
        Double disc = product.getDiscount();

        double discount = 0.0;
        double sellingPrice = rawSellingPrice;
        double mrp = rawMrp;

        if (disc != null && disc > 0) {
            discount = disc;
            if (rawMrp > rawSellingPrice && rawSellingPrice > 0) {
                mrp = rawMrp;
                sellingPrice = rawSellingPrice;
            } else {
                mrp = rawMrp > 0 ? rawMrp : rawSellingPrice;
                sellingPrice = Math.round(mrp * (1.0 - (discount / 100.0)) * 100.0) / 100.0;
            }
        } else if (rawMrp > rawSellingPrice && rawSellingPrice > 0) {
            discount = Math.round(((rawMrp - rawSellingPrice) / rawMrp) * 100.0);
            mrp = rawMrp;
            sellingPrice = rawSellingPrice;
        } else {
            discount = 0.0;
            mrp = rawMrp > 0 ? rawMrp : rawSellingPrice;
            sellingPrice = mrp;
        }

        HBox priceRow = new HBox(12);
        priceRow.setAlignment(Pos.BASELINE_LEFT);

        Label sellingPriceLabel = new Label(String.format("₹%.2f", sellingPrice));
        sellingPriceLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: 900; -fx-text-fill: #1E1E24;");

        Label mrpLabel = new Label(mrp > sellingPrice ? String.format("₹%.2f", mrp) : "");
        mrpLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #999999; -fx-strikethrough: true;");

        Label saveLabel = new Label(mrp > sellingPrice ? String.format("Save ₹%.2f (%.0f%% OFF)", mrp - sellingPrice, discount) : "");
        saveLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        if (mrp > sellingPrice) {
            priceRow.getChildren().addAll(sellingPriceLabel, mrpLabel, saveLabel);
        } else {
            priceRow.getChildren().addAll(sellingPriceLabel);
        }

        Label taxLabel = new Label("Inclusive of all taxes • Instant delivery available");
        taxLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #777777;");

        // Stock Status
        int stock = Math.max(0, product.getStockQuantity());
        Label stockBadge = new Label(stock > 0 ? ("● In Stock (" + stock + " available)") : "● Currently Out of Stock");
        stockBadge.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                (stock > 0 ? "-fx-text-fill: #2E7D32;" : "-fx-text-fill: #D32F2F;")
        );

        // Quantity Selector Row
        HBox qtyRow = new HBox(12);
        qtyRow.setAlignment(Pos.CENTER_LEFT);

        Label qtyTitle = new Label("Quantity:");
        qtyTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label qtyDisplay = new Label("1");
        qtyDisplay.setPrefWidth(36);
        qtyDisplay.setAlignment(Pos.CENTER);
        qtyDisplay.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Button minusBtn = new Button("−");
        Button plusBtn = new Button("+");

        String btnCircleStyle =
                "-fx-background-color: #F0EDF3;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-min-width: 32px;" +
                "-fx-min-height: 32px;" +
                "-fx-max-width: 32px;" +
                "-fx-max-height: 32px;" +
                "-fx-cursor: hand;";

        minusBtn.setStyle(btnCircleStyle);
        plusBtn.setStyle(btnCircleStyle);

        minusBtn.setOnAction(e -> {
            if (selectedQuantity > 1) {
                selectedQuantity--;
                qtyDisplay.setText(String.valueOf(selectedQuantity));
            }
        });

        plusBtn.setOnAction(e -> {
            int max = stock > 0 ? stock : 10;
            if (selectedQuantity < max) {
                selectedQuantity++;
                qtyDisplay.setText(String.valueOf(selectedQuantity));
            }
        });

        HBox qtySelector = new HBox(6, minusBtn, qtyDisplay, plusBtn);
        qtySelector.setAlignment(Pos.CENTER_LEFT);
        qtyRow.getChildren().addAll(qtyTitle, qtySelector);

        // Action Buttons: Add to Cart & Buy Now
        HBox actionButtons = new HBox(16);
        actionButtons.setAlignment(Pos.CENTER_LEFT);

        Button addToCartBtn = new Button("🛒 Add to Cart");
        addToCartBtn.setPrefHeight(48);
        addToCartBtn.setPrefWidth(190);
        addToCartBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );

        Button buyNowBtn = new Button("⚡ Buy Now");
        buyNowBtn.setPrefHeight(48);
        buyNowBtn.setPrefWidth(180);
        buyNowBtn.setStyle(
                "-fx-background-color: #242529;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-cursor: hand;"
        );

        final double finalSellingPrice = sellingPrice;

        if (stock <= 0) {
            addToCartBtn.setDisable(true);
            addToCartBtn.setText("Out of Stock");
            addToCartBtn.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: #666666; -fx-background-radius: 12;");
            buyNowBtn.setDisable(true);
            buyNowBtn.setStyle("-fx-background-color: #EEEEEE; -fx-text-fill: #999999; -fx-background-radius: 12;");
        } else {
            addToCartBtn.setOnAction(e -> {
                try {
                    CARTcontroller cartController = new CARTcontroller();
                    cartController.addTocart(
                            userId,
                            product.getProductId(),
                            name,
                            finalSellingPrice,
                            category,
                            selectedQuantity,
                            product.getShopkeeperUid()
                    );
                    addToCartBtn.setText("✓ Added to Cart!");
                    addToCartBtn.setStyle(
                            "-fx-background-color: #2E7D32;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 12;"
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            buyNowBtn.setOnAction(e -> {
                try {
                    CARTcontroller cartController = new CARTcontroller();
                    cartController.addTocart(
                            userId,
                            product.getProductId(),
                            name,
                            finalSellingPrice,
                            category,
                            selectedQuantity,
                            product.getShopkeeperUid()
                    );
                    CustomerNavigation.navigateToCart(userId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        actionButtons.getChildren().addAll(addToCartBtn, buyNowBtn);

        // Description Section
        VBox descBox = new VBox(8);
        descBox.setPadding(new Insets(18));
        descBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #ECE8EE;" +
                "-fx-border-radius: 14;"
        );

        Label descHeader = new Label("Product Description & Specifications");
        descHeader.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #1E1E24;");

        String desc = product.getDescriptionValue();
        if (desc == null || desc.isBlank()) {
            desc = "This premium quality " + name + " by " + brand + " is sourced from verified neighborhood merchants. Carefully packed and inspected to ensure authentic freshness and quality standards.";
        }

        Text descText = new Text(desc);
        descText.setWrappingWidth(560);
        descText.setStyle("-fx-font-size: 13px; -fx-fill: #555555;");

        descBox.getChildren().addAll(descHeader, descText);

        // Merchant Details Box
        HBox sellerBox = new HBox(12);
        sellerBox.setAlignment(Pos.CENTER_LEFT);
        sellerBox.setPadding(new Insets(12, 16, 12, 16));
        sellerBox.setStyle(
                "-fx-background-color: #F8F5F2;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #EBDCCE;" +
                "-fx-border-radius: 10;"
        );

        Label shopIcon = new Label("🏪");
        shopIcon.setStyle("-fx-font-size: 20px;");

        VBox shopInfo = new VBox(2);
        Label shopTitle = new Label();
        shopTitle.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        CustomerShopResolver.bindShopName(shopTitle, product.getShopkeeperUid(), "Sold & Dispatched by ");

        Label shopSub = new Label("Location: Local Downtown Hub (1.2 km away) • Quick Pickup Available");
        shopSub.setStyle("-fx-font-size: 10px; -fx-text-fill: #777777;");
        shopInfo.getChildren().addAll(shopTitle, shopSub);

        sellerBox.getChildren().addAll(shopIcon, shopInfo);

        right.getChildren().addAll(
                tagRow,
                nameLabel,
                shopBadge,
                ratingRow,
                priceRow,
                taxLabel,
                stockBadge,
                qtyRow,
                actionButtons,
                descBox,
                sellerBox
        );

        return right;
    }
}

package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;

import javafx.scene.paint.Stop;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.ProductModel;
import javafx.scene.text.Text;

import com.kryox.view.Customer.ShoppingCartUI;
import com.kryox.view.Customer.neaby_shope;
import com.kryox.view.Customer.Seting;
import com.kryox.view.Customer.Electronics;
import com.kryox.view.Customer.Homepage;

public class Grocries {
        public String userId;

        private final ProductController productController =
                        new ProductController();

        public Grocries(String userId) {
                this.userId = userId;
        }

        private Scene GrocriesScene;

        private HBox createProductImage(
                        String imagePath,
                        String fallbackEmoji) {

                HBox box = new HBox();

                box.setPrefSize(90, 90);
                box.setMinSize(90, 90);
                box.setMaxSize(90, 90);
                box.setAlignment(Pos.CENTER);

                box.setStyle(
                                "-fx-background-color: #FFF7F2;" +
                                "-fx-background-radius: 14;" +
                                "-fx-border-color: #FFE2D2;" +
                                "-fx-border-radius: 14;" +
                                "-fx-border-width: 1;");

                try {

                        Image image = null;

                        if (imagePath != null &&
                                        !imagePath.trim().isEmpty()) {

                                String path =
                                                imagePath.trim();

                                // First try local resources.
                                URL resource =
                                                getClass().getResource(path);

                                if (resource != null) {

                                        image =
                                                        new Image(
                                                                        resource.toExternalForm(),
                                                                        78,
                                                                        78,
                                                                        true,
                                                                        true);
                                }

                                // If not a local resource, try URL.
                                if (image == null &&
                                                (path.startsWith("http://") ||
                                                path.startsWith("https://"))) {

                                        image =
                                                        new Image(
                                                                        path,
                                                                        78,
                                                                        78,
                                                                        true,
                                                                        true,
                                                                        true);
                                }
                        }

                        if (image != null &&
                                        !image.isError()) {

                                ImageView imageView =
                                                new ImageView(image);

                                imageView.setFitWidth(78);
                                imageView.setFitHeight(78);
                                imageView.setPreserveRatio(true);

                                box.getChildren().add(
                                                imageView);

                                return box;
                        }

                } catch (Exception e) {

                        System.out.println(
                                        "Product image could not be loaded: "
                                                        + imagePath);
                }

                Label fallback =
                                new Label(
                                                fallbackEmoji == null ||
                                                fallbackEmoji.isEmpty()
                                                                ? "🛒"
                                                                : fallbackEmoji);

                fallback.setStyle(
                                "-fx-font-size: 42px;");

                box.getChildren().add(fallback);

                return box;
        }


        // ============================================================
        // VIEW PRODUCT MODEL
        //
        // Only the required 11 fields are used by this View.
        // ============================================================

        private static class Product {

                private final String imageUrl;
                private final String status;
                private final int stockQuantity;
                private final int lowStockLimit;

                private final Double costPrice;
                private final Double sellingPrice;
                private final Double discount;

                private final String productName;
                private final String category;
                private final String brand;
                private final String descriptionValue;

                Product(
                                String imageUrl,
                                String status,
                                int stockQuantity,
                                int lowStockLimit,
                                Double costPrice,
                                Double sellingPrice,
                                Double discount,
                                String productName,
                                String category,
                                String brand,
                                String descriptionValue) {

                        this.imageUrl = imageUrl;
                        this.status = status;
                        this.stockQuantity = stockQuantity;
                        this.lowStockLimit = lowStockLimit;
                        this.costPrice = costPrice;
                        this.sellingPrice = sellingPrice;
                        this.discount = discount;
                        this.productName = productName;
                        this.category = category;
                        this.brand = brand;
                        this.descriptionValue = descriptionValue;
                }
        }

        // ============================================================
        // FETCH PRODUCTS THROUGH CONTROLLER
        // ============================================================

        private List<Product> getProducts() {

                List<Product> products =
                                new ArrayList<>();

                try {

                        ArrayList<ProductModel> firebaseProducts =
                                        productController.fetchProducts();

                        if (firebaseProducts == null ||
                                        firebaseProducts.isEmpty()) {

                                System.out.println(
                                                "No products found for shopkeeper UID: "
                                                                + com.kryox.controller.Shopkeeper.ShopkeeperLogController
                                                                                .getShopkeeperUid());

                                return products;
                        }

                        for (ProductModel productModel :
                                        firebaseProducts) {

                                if (productModel == null) {
                                        continue;
                                }

                                Product product =
                                                new Product(

                                                                productModel.getImageUrl(),

                                                                productModel.getStatus(),

                                                                productModel.getStockQuantity(),

                                                                productModel.getLowStockLimit(),

                                                                productModel.getCostPrice(),

                                                                productModel.getSellingPrice(),

                                                                productModel.getDiscount(),

                                                                productModel.getProductName(),

                                                                productModel.getCategory(),

                                                                productModel.getBrand(),

                                                                productModel.getDescriptionValue()
                                                );

                                products.add(product);
                        }

                        System.out.println(
                                        "Products loaded in Groceries View: "
                                                        + products.size());

                } catch (Exception e) {

                        System.out.println(
                                        "ERROR: Unable to fetch products.");

                        e.printStackTrace();
                }

                return products;
        }

        // ============================================================
        // CREATE PRODUCT CARD DYNAMICALLY
        // ============================================================
        private VBox createProductCard(Product product,
                                        DropShadow cardShadow3) {

                VBox card = new VBox(5);
                card.setPrefWidth(280);
                card.setMinWidth(280);
                card.setMaxWidth(280);
                card.setPrefHeight(380);
                card.setPadding(new Insets(15, 16, 15, 16));

                String normalStyle =
                                "-fx-background-color: white;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-color: #EAE6EC;" +
                                "-fx-border-radius: 12;" +
                                "-fx-border-width: 1;";

                String hoverStyle =
                                "-fx-background-color: #FFFFFF;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-color: #FFD8C4;" +
                                "-fx-border-radius: 12;" +
                                "-fx-border-width: 1.5;";

                card.setStyle(normalStyle);
                card.setEffect(cardShadow3);

                VBox productRow = new VBox(12);
                productRow.setAlignment(Pos.CENTER);

                HBox imagePlaceholder =
                                createProductImage(
                                                product.imageUrl,
                                                "🛒");

                VBox productDetails = new VBox(3);
                productDetails.setAlignment(Pos.CENTER);

                Label nameLabel = new Label(
                                product.productName == null
                                                ? "Unnamed Product"
                                                : product.productName);
                nameLabel.setWrapText(true);
                nameLabel.setAlignment(Pos.CENTER);
                nameLabel.setMaxWidth(240);
                nameLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: 700;" +
                                "-fx-text-fill: #222222;");

                Label descLabel = new Label(
                                product.descriptionValue == null
                                                ? ""
                                                : product.descriptionValue);
                descLabel.setWrapText(true);
                descLabel.setAlignment(Pos.CENTER);
                descLabel.setMaxWidth(240);
                descLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 11px;" +
                                "-fx-text-fill: #777777;");

                HBox priceRow = new HBox(8);
                priceRow.setAlignment(Pos.CENTER);

                double sellingPrice =
                                product.sellingPrice == null
                                                ? 0.0
                                                : product.sellingPrice;

                double costPrice =
                                product.costPrice == null
                                                ? sellingPrice
                                                : product.costPrice;

                Label priceLabel =
                                new Label(
                                                "$" +
                                                String.format(
                                                                "%.2f",
                                                                sellingPrice));

                priceLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 16px;" +
                                "-fx-font-weight: 800;" +
                                "-fx-text-fill: #222222;");

                priceRow.getChildren().add(priceLabel);

                if (costPrice > sellingPrice) {

                        double discount =
                                        product.discount != null
                                                        ? product.discount
                                                        : ((costPrice > 0)
                                                                        ? ((costPrice - sellingPrice) /
                                                                                costPrice) * 100.0
                                                                        : 0.0);

                        Label discountLabel =
                                        new Label(String.format("%.0f%% OFF", discount));

                        discountLabel.setStyle(
                                        "-fx-background-color: #FFE8E0;" +
                                        "-fx-text-fill: #FF6900;" +
                                        "-fx-font-size: 10px;" +
                                        "-fx-font-weight: 700;" +
                                        "-fx-background-radius: 12;" +
                                        "-fx-padding: 2 10 2 10;");

                        Label originalPriceLabel =
                                        new Label("$" +
                                                        String.format("%.2f",
                                                                        costPrice));

                        originalPriceLabel.setStyle(
                                        "-fx-font-size: 11px;" +
                                        "-fx-text-fill: #AAAAAA;" +
                                        "-fx-strikethrough: true;");

                        priceRow.getChildren().addAll(
                                        discountLabel,
                                        originalPriceLabel);
                }


                String statusText =
                                product.status == null ||
                                product.status.isEmpty()
                                                ? "Available"
                                                : product.status;

                Label stockLabel = new Label(
                                statusText +
                                " • Stock: " +
                                product.stockQuantity);

                stockLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 10px;" +
                                "-fx-text-fill: #777777;");

                productDetails.getChildren().add(stockLabel);

                productDetails.getChildren().addAll(
                                nameLabel,
                                descLabel,
                                priceRow);

                // ====================================================
                // QUANTITY CONTROLS
                // ====================================================
                int availableStock =
                                Math.max(0, product.stockQuantity);

                Label quantityLabel =
                                new Label(
                                                availableStock > 0
                                                                ? "1"
                                                                : "0");
                quantityLabel.setPrefWidth(30);
                quantityLabel.setAlignment(Pos.CENTER);
                quantityLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-text-fill: #222222;");

                Button minusButton = new Button("-");
                minusButton.setPrefSize(30, 30);
                minusButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 50%;" +
                                "-fx-cursor: hand;");

                Button plusButton = new Button("+");
                plusButton.setPrefSize(30, 30);
                plusButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 50%;" +
                                "-fx-cursor: hand;");

                minusButton.setOnAction(e -> {
                        int quantity =
                                        Integer.parseInt(quantityLabel.getText());

                        if (quantity > 1) {
                                quantity--;
                                quantityLabel.setText(
                                                String.valueOf(quantity));
                        }
                });

                plusButton.setOnAction(e -> {
                        int quantity =
                                        Integer.parseInt(quantityLabel.getText());

                        if (quantity < availableStock) {
                                quantity++;
                                quantityLabel.setText(
                                                String.valueOf(quantity));
                        }
                });

                HBox quantityBox =
                                new HBox(8, minusButton, quantityLabel, plusButton);
                quantityBox.setAlignment(Pos.CENTER);

                // ====================================================
                // ADD TO CART
                // ====================================================
                Button addToCart = new Button("Add to Cart");
                addToCart.setPrefHeight(36);
                addToCart.setMaxWidth(Double.MAX_VALUE);
                addToCart.setStyle(
                                "-fx-background-color: #FF6900;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 11px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 18;" +
                                "-fx-padding: 8 18 8 18;" +
                                "-fx-cursor: hand;");

                addToCart.setOnAction(e -> {
                        int quantity =
                                        Integer.parseInt(quantityLabel.getText());

                        if (quantity <= 0 ||
                                        availableStock <= 0) {

                                System.out.println(
                                                "Product is out of stock: "
                                                                + product.productName);

                                return;
                        }

                        CARTcontroller cl =
                                        new CARTcontroller();

                        cl.addTocart(
                                        userId,
                                        product.productName,
                                        sellingPrice,
                                        product.brand,
                                        quantity);
                });

                productRow.getChildren().addAll(
                                imagePlaceholder,
                                productDetails,
                                quantityBox,
                                addToCart);

                card.getChildren().add(productRow);

                // ====================================================
                // HOVER EFFECT
                // ====================================================
                card.setOnMouseEntered(e -> {
                        card.setStyle(hoverStyle);

                        DropShadow hoverShadow = new DropShadow();
                        hoverShadow.setRadius(12);
                        hoverShadow.setOffsetY(4);
                        hoverShadow.setSpread(0.04);
                        hoverShadow.setColor(
                                        Color.rgb(0, 0, 0, 0.10));

                        card.setEffect(hoverShadow);
                });

                card.setOnMouseExited(e -> {
                        card.setStyle(normalStyle);
                        card.setEffect(cardShadow3);
                });

                return card;
        }

        Scene getGrocriescene(Runnable callbacktoRunnable) {

                DropShadow shadow = new DropShadow();
                shadow.setRadius(18);
                shadow.setSpread(0.05);
                shadow.setOffsetX(5);
                shadow.setOffsetY(0);
                shadow.setColor(Color.rgb(0, 0, 0, 0.14));

                DropShadow cardShadow = new DropShadow();
                cardShadow.setRadius(14);
                cardShadow.setOffsetY(5);
                cardShadow.setSpread(0.02);
                cardShadow.setColor(Color.rgb(0, 0, 0, 0.10));

                Label name = new Label("BuyNeX");
                name.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: 900;" +
                                                "-fx-text-fill: #E87500;");

                HBox premiumBox = new HBox(9);
                premiumBox.setPrefSize(205, 58);
                premiumBox.setMinSize(205, 58);
                premiumBox.setMaxSize(205, 58);
                premiumBox.setAlignment(Pos.CENTER_LEFT);
                premiumBox.setPadding(new Insets(8, 13, 8, 13));
                premiumBox.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-background-radius: 15;" +
                                                "-fx-border-color: #E9E2EA;" +
                                                "-fx-border-width: 1;" +
                                                "-fx-border-radius: 15;");
                premiumBox.setEffect(cardShadow);

                VBox textBox = new VBox(3);
                Label title = new Label("Premium Shopper");
                title.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #222222;");

                Label subtitle = new Label("● AI Assistant Active");
                subtitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #FF6900;");

                textBox.getChildren().addAll(title, subtitle);
                premiumBox.getChildren().add(textBox);

                Image di = new Image("/assets/images/Dashbord/dashboard.png");
                ImageView div = new ImageView(di);
                div.setFitHeight(21);
                div.setFitWidth(21);
                div.setPreserveRatio(true);

                Button lefButton1 = new Button("Dashboard");
                lefButton1.setPrefWidth(125);
                lefButton1.setPrefHeight(38);
                lefButton1.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");
                lefButton1.setOnAction(event -> {
                        callbacktoRunnable.run();
                });

                HBox hbInDashboard = new HBox(17, div, lefButton1);
                hbInDashboard.setPrefWidth(205);
                hbInDashboard.setMinWidth(205);
                hbInDashboard.setMaxWidth(205);
                hbInDashboard.setPrefHeight(42);
                hbInDashboard.setMinHeight(42);
                hbInDashboard.setMaxHeight(42);
                hbInDashboard.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

                hbInDashboard.setOnMouseEntered(e -> {
                        hbInDashboard.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
                        lefButton1.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                hbInDashboard.setOnMouseExited(e -> {
                        hbInDashboard.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
                        lefButton1.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                Image di2 = new Image("/assets/images/store.png");
                ImageView div2 = new ImageView(di2);
                div2.setFitHeight(23);
                div2.setFitWidth(23);
                div2.setPreserveRatio(true);

                Button lefButton2 = new Button("Nearby Shops");
                lefButton2.setPrefWidth(125);
                lefButton2.setPrefHeight(38);
                lefButton2.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");
                lefButton2.setOnAction(event -> {
                        neaby_shope bs = new neaby_shope(userId);
                        Homepage.HomepageStage.setScene(bs.getNearby_shopes(callbacktoRunnable));
                });

                HBox hbInDashboard2 = new HBox(17, div2, lefButton2);
                hbInDashboard2.setPrefWidth(205);
                hbInDashboard2.setMinWidth(205);
                hbInDashboard2.setMaxWidth(205);
                hbInDashboard2.setPrefHeight(42);
                hbInDashboard2.setMinHeight(42);
                hbInDashboard2.setMaxHeight(42);
                hbInDashboard2.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard2.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard2.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

                hbInDashboard2.setOnMouseEntered(e -> {
                        hbInDashboard2.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
                        lefButton2.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                hbInDashboard2.setOnMouseExited(e -> {
                        hbInDashboard2.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
                        lefButton2.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                Image di3 = new Image("/assets/images/Dashbord/hot-sale.png");
                ImageView div3 = new ImageView(di3);
                div3.setFitHeight(23);
                div3.setFitWidth(23);
                div3.setPreserveRatio(true);

                Button lefButton3 = new Button("Deals");
                lefButton3.setPrefWidth(125);
                lefButton3.setPrefHeight(38);
                lefButton3.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");

                HBox hbInDashboard3 = new HBox(17, div3, lefButton3);
                hbInDashboard3.setPrefWidth(205);
                hbInDashboard3.setMinWidth(205);
                hbInDashboard3.setMaxWidth(205);
                hbInDashboard3.setPrefHeight(42);
                hbInDashboard3.setMinHeight(42);
                hbInDashboard3.setMaxHeight(42);
                hbInDashboard3.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard3.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard3.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

                hbInDashboard3.setOnMouseEntered(e -> {
                        hbInDashboard3.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
                        lefButton3.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                hbInDashboard3.setOnMouseExited(e -> {
                        hbInDashboard3.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
                        lefButton3.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                Image di4 = new Image("/assets/images/Dashbord/package.png");
                ImageView div4 = new ImageView(di4);
                div4.setFitHeight(23);
                div4.setFitWidth(23);
                div4.setPreserveRatio(true);

                Button lefButton4 = new Button("My Orders");
                lefButton4.setPrefWidth(125);
                lefButton4.setPrefHeight(38);
                lefButton4.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");

                HBox hbInDashboard4 = new HBox(17, div4, lefButton4);
                hbInDashboard4.setPrefWidth(205);
                hbInDashboard4.setMinWidth(205);
                hbInDashboard4.setMaxWidth(205);
                hbInDashboard4.setPrefHeight(42);
                hbInDashboard4.setMinHeight(42);
                hbInDashboard4.setMaxHeight(42);
                hbInDashboard4.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard4.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard4.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

                hbInDashboard4.setOnMouseEntered(e -> {
                        hbInDashboard4.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
                        lefButton4.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                hbInDashboard4.setOnMouseExited(e -> {
                        hbInDashboard4.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
                        lefButton4.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                Image di5 = new Image("/assets/images/Dashbord/line-chart.png");
                ImageView div5 = new ImageView(di5);
                div5.setFitHeight(23);
                div5.setFitWidth(23);
                div5.setPreserveRatio(true);

                Button lefButton5 = new Button("Analytics");
                lefButton5.setPrefWidth(125);
                lefButton5.setPrefHeight(38);
                lefButton5.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #333333;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");

                HBox hbInDashboard5 = new HBox(17, div5, lefButton5);
                hbInDashboard5.setPrefWidth(205);
                hbInDashboard5.setMinWidth(205);
                hbInDashboard5.setMaxWidth(205);
                hbInDashboard5.setPrefHeight(42);
                hbInDashboard5.setMinHeight(42);
                hbInDashboard5.setMaxHeight(42);
                hbInDashboard5.setPadding(new Insets(2, 8, 2, 13));
                hbInDashboard5.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard5.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");

                hbInDashboard5.setOnMouseEntered(e -> {
                        hbInDashboard5.setStyle("-fx-background-color: #FF6900;" + "-fx-background-radius: 12;");
                        lefButton5.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                hbInDashboard5.setOnMouseExited(e -> {
                        hbInDashboard5.setStyle("-fx-background-color: transparent;" + "-fx-background-radius: 12;");
                        lefButton5.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #333333;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-family: 'Montserrat';" +
                                                        "-fx-font-weight: 500;" +
                                                        "-fx-border-width: 0;" +
                                                        "-fx-padding: 0;" +
                                                        "-fx-alignment: CENTER_LEFT;" +
                                                        "-fx-cursor: hand;");
                });

                VBox upgradeCard = new VBox(7);
                upgradeCard.setPrefWidth(205);
                upgradeCard.setMinWidth(205);
                upgradeCard.setMaxWidth(205);
                upgradeCard.setPrefHeight(112);
                upgradeCard.setPadding(new Insets(15));
                upgradeCard.setAlignment(Pos.CENTER_LEFT);

                LinearGradient upgradeGradient = new LinearGradient(
                                0, 0, 1, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#25262A")),
                                new Stop(1, Color.web("#45474D")));

                upgradeCard.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                upgradeGradient,
                                                                new CornerRadii(17),
                                                                Insets.EMPTY)));

                Label upgradeTitle = new Label("Unlock Gold");
                upgradeTitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: white;");

                Label upgradeText = new Label("Smarter deals & exclusive rewards");
                upgradeText.setStyle(
                                "-fx-font-size: 8px;" +
                                                "-fx-text-fill: #BEBFC3;");

                Button upgradeGold = new Button("Upgrade to Gold");
                upgradeGold.setPrefWidth(175);
                upgradeGold.setPrefHeight(30);
                upgradeGold.setStyle(
                                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-cursor: hand;");

                upgradeCard.getChildren().addAll(upgradeTitle, upgradeText, upgradeGold);

                Image di6 = new Image("/assets/images/Dashbord/category.png");
                ImageView div6 = new ImageView(di6);
                div6.setFitHeight(19);
                div6.setFitWidth(19);
                div6.setPreserveRatio(true);

                Button lefButton6 = new Button("Settings");
                lefButton6.setPrefWidth(135);
                lefButton6.setPrefHeight(34);
                lefButton6.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");
                lefButton6.setOnAction(event -> {
                        Seting se = new Seting(userId);
                        Runnable callbacktoDashborad = new Runnable() {
                                public void run() {

                                }
                        };
                        Homepage.HomepageStage.setScene(se.getSetingscene(callbacktoDashborad));
                });

                HBox hbInDashboard6 = new HBox(10, div6, lefButton6);
                hbInDashboard6.setPrefWidth(205);
                hbInDashboard6.setMinWidth(205);
                hbInDashboard6.setMaxWidth(205);
                hbInDashboard6.setPrefHeight(34);
                hbInDashboard6.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard6.setPadding(new Insets(0, 8, 0, 18));

                Image di7 = new Image("/assets/images/Dashbord/question.png");
                ImageView div7 = new ImageView(di7);
                div7.setFitHeight(19);
                div7.setFitWidth(19);
                div7.setPreserveRatio(true);

                Button lefButton7 = new Button("Help & Support");
                lefButton7.setPrefWidth(135);
                lefButton7.setPrefHeight(34);
                lefButton7.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #666666;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: 500;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-padding: 0;" +
                                                "-fx-alignment: CENTER_LEFT;" +
                                                "-fx-cursor: hand;");

                HBox hbInDashboard7 = new HBox(10, div7, lefButton7);
                hbInDashboard7.setPrefWidth(205);
                hbInDashboard7.setMinWidth(205);
                hbInDashboard7.setMaxWidth(205);
                hbInDashboard7.setPrefHeight(34);
                hbInDashboard7.setAlignment(Pos.CENTER_LEFT);
                hbInDashboard7.setPadding(new Insets(0, 8, 0, 18));

                VBox leftBox = new VBox(14);
                leftBox.setPrefWidth(245);
                leftBox.setMinWidth(245);
                leftBox.setMaxWidth(245);
                leftBox.setPrefHeight(800);
                leftBox.setAlignment(Pos.TOP_CENTER);
                leftBox.setPadding(new Insets(27, 20, 20, 20));
                leftBox.setStyle("-fx-background-color: #ebccb7;");
                leftBox.setEffect(shadow);

                Region sidebarSpacer = new Region();
                VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);

                leftBox.getChildren().addAll(
                                name,
                                premiumBox,
                                hbInDashboard,
                                hbInDashboard2,
                                hbInDashboard3,
                                hbInDashboard4,
                                hbInDashboard5,
                                sidebarSpacer,
                                upgradeCard,
                                hbInDashboard6,
                                hbInDashboard7);

                Button t1 = new Button("Offers");
                Button t2 = new Button("Shops");
                Button t3 = new Button("Support");

                String topButtonStyle = "-fx-background-color: transparent;" +
                                "-fx-text-fill: #666666;" +
                                "-fx-font-size: 10px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-padding: 6 8 6 8;" +
                                "-fx-border-width: 0;" +
                                "-fx-cursor: hand;";

                t1.setStyle(topButtonStyle);
                t2.setStyle(topButtonStyle);
                t3.setStyle(topButtonStyle);

                t1.setOnMouseEntered(e -> t1.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6 8 6 8;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;"));

                t1.setOnMouseExited(e -> t1.setStyle(topButtonStyle));

                t2.setOnMouseEntered(e -> t2.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6 8 6 8;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;"));

                t2.setOnMouseExited(e -> t2.setStyle(topButtonStyle));

                t3.setOnMouseEntered(e -> t3.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-padding: 6 8 6 8;" +
                                                "-fx-border-width: 0;" +
                                                "-fx-cursor: hand;"));

                t3.setOnMouseExited(e -> t3.setStyle(topButtonStyle));

                HBox topLinks = new HBox(6, t1, t2, t3);
                topLinks.setAlignment(Pos.CENTER_LEFT);

                TextField searchBox = new TextField();
                searchBox.setPromptText("Search products, shops or deals with AI...");
                searchBox.setPrefWidth(310);
                searchBox.setPrefHeight(39);
                searchBox.setStyle(
                                "-fx-background-color: #F8F7FA;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: #E5E1E8;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-border-width: 1;" +
                                                "-fx-padding: 0 16 0 16;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #444444;" +
                                                "-fx-prompt-text-fill: #999999;");
                searchBox.setTranslateX(-100);

                Label locationIcon = new Label("📍");
                Label locationText = new Label("Downtown Manhattan⌄");
                locationText.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #555555;");

                HBox locationBox = new HBox(4, locationIcon, locationText);
                locationBox.setAlignment(Pos.CENTER_LEFT);
                locationBox.setTranslateX(-200);

                Button b1 = new Button("🔔");
                Button b2 = new Button("🛒");
                Button b3 = new Button("●");

                String actionStyle = "-fx-background-color: white;" +
                                "-fx-text-fill: #555555;" +
                                "-fx-font-size: 13px;" +
                                "-fx-background-radius: 11;" +
                                "-fx-border-color: #E7E2E9;" +
                                "-fx-border-radius: 11;" +
                                "-fx-border-width: 1;" +
                                "-fx-padding: 0;" +
                                "-fx-cursor: hand;";

                b1.setPrefSize(37, 37);
                b2.setPrefSize(37, 37);
                b3.setPrefSize(37, 37);

                b1.setStyle(actionStyle);
                b2.setStyle(actionStyle);
                b3.setStyle(actionStyle);

                b2.setOnAction(e -> {

                        ShoppingCartUI sp = new ShoppingCartUI(userId);
                        Homepage.HomepageStage.setScene(sp.getaddcartScene());
                });

                String actionHoverStyle = "-fx-background-color: #FFF2E9;" +
                                "-fx-text-fill: #FF6900;" +
                                "-fx-font-size: 13px;" +
                                "-fx-background-radius: 11;" +
                                "-fx-border-color: #FFBD95;" +
                                "-fx-border-radius: 11;" +
                                "-fx-border-width: 1;" +
                                "-fx-padding: 0;" +
                                "-fx-cursor: hand;";

                b1.setOnMouseEntered(e -> b1.setStyle(actionHoverStyle));
                b1.setOnMouseExited(e -> b1.setStyle(actionStyle));

                b2.setOnMouseEntered(e -> b2.setStyle(actionHoverStyle));
                b2.setOnMouseExited(e -> b2.setStyle(actionStyle));

                b3.setOnMouseEntered(e -> b3.setStyle(actionHoverStyle));
                b3.setOnMouseExited(e -> b3.setStyle(actionStyle));

                HBox actionBox = new HBox(7, b1, b2, b3);
                actionBox.setAlignment(Pos.CENTER_RIGHT);
                actionBox.setTranslateX(-200);

                Region navSpacer1 = new Region();
                HBox.setHgrow(navSpacer1, Priority.ALWAYS);
                Region navSpacer2 = new Region();
                HBox.setHgrow(navSpacer2, Priority.ALWAYS);

                HBox navBox = new HBox(12, topLinks, navSpacer1, searchBox, navSpacer2, locationBox, actionBox);
                navBox.setPrefHeight(68);
                navBox.setPadding(new Insets(12, 24, 12, 24));
                navBox.setAlignment(Pos.CENTER_LEFT);
                navBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-color: #ebccb7;" +
                                                "-fx-border-width: 0 0 1 0;");
                navBox.setTranslateX(-28);

                HBox hbright = new HBox(35);
                hbright.setPrefWidth(1200);
                hbright.setPrefHeight(315);
                hbright.setPadding(new Insets(34, 40, 34, 40));
                hbright.setAlignment(Pos.CENTER_LEFT);

                LinearGradient darkGradient = new LinearGradient(
                                0, 0, 1, 0, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0.0, Color.web("#242529")),
                                new Stop(0.55, Color.web("#303136")),
                                new Stop(1.0, Color.web("#563A2B")));

                hbright.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                darkGradient,
                                                                new CornerRadii(22),
                                                                Insets.EMPTY)));
                hbright.setEffect(cardShadow);

                VBox leftContent = new VBox(13);
                leftContent.setPrefWidth(600);
                leftContent.setAlignment(Pos.CENTER_LEFT);

                Label badge = new Label("✦  NEXT-GEN INTELLIGENCE");
                badge.setStyle(
                                "-fx-background-color: #503629;" +
                                                "-fx-text-fill: #FF9D67;" +
                                                "-fx-font-size: 8px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-padding: 7 13 7 13;");

                Text heading = new Text("What are you\nlooking for today?");
                heading.setStyle(
                                "-fx-fill: white;" +
                                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: 900;");

                Text description = new Text(
                                "Your hyper-local AI is ready to source, compare,\n" +
                                                "and deliver from your favorite downtown spots.");
                description.setStyle(
                                "-fx-fill: #C9C9C9;" +
                                                "-fx-font-size: 11px;");

                Button groceries = new Button("♧  Combine Groceries");
                groceries.setPrefHeight(42);
                groceries.setStyle(
                                "-fx-background-color: #45464B;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 11;" +
                                                "-fx-padding: 10 16 10 16;" +
                                                "-fx-cursor: hand;");

                Button bestPrice = new Button("⌁  Find Best Price");
                bestPrice.setPrefHeight(42);
                bestPrice.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 11;" +
                                                "-fx-padding: 10 17 10 17;" +
                                                "-fx-cursor: hand;");

                HBox buttons = new HBox(10, groceries, bestPrice);
                leftContent.getChildren().addAll(badge, heading, description, buttons);

                VBox notification = new VBox(13);
                notification.setPrefWidth(300);
                notification.setMinWidth(300);
                notification.setMaxWidth(300);
                notification.setPrefHeight(245);
                notification.setPadding(new Insets(20));
                notification.setAlignment(Pos.TOP_LEFT);
                notification.setStyle(
                                "-fx-background-color: #ECEAE9;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-color: rgba(255,255,255,0.35);" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-border-width: 1;");
                notification.setEffect(cardShadow);

                Label aiCircle = new Label("✦");
                aiCircle.setPrefSize(38, 38);
                aiCircle.setAlignment(Pos.CENTER);
                aiCircle.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 50%;");

                VBox aiHeading = new VBox(2);
                Label aiTitle = new Label("AI Recommendation");
                aiTitle.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #333333;");

                Label aiStatus = new Label("PERSONALIZED FOR YOU");
                aiStatus.setStyle(
                                "-fx-font-size: 7px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #FF6900;");

                aiHeading.getChildren().addAll(aiTitle, aiStatus);

                HBox aiHeader = new HBox(10, aiCircle, aiHeading);
                aiHeader.setAlignment(Pos.CENTER_LEFT);

                Text notificationText = new Text(
                                "You usually order coffee beans on Tuesdays.\n\n" +
                                                "Artisan Pantry has your favorite\n" +
                                                "brand in stock today.");
                notificationText.setStyle(
                                "-fx-fill: #444444;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;");

                Region notificationSpacer = new Region();
                VBox.setVgrow(notificationSpacer, Priority.ALWAYS);

                Button dismiss = new Button("Dismiss");
                dismiss.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #777777;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-cursor: hand;");

                Button checkStock = new Button("Check Stock");
                checkStock.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10;" +
                                                "-fx-padding: 8 13 8 13;" +
                                                "-fx-cursor: hand;");

                HBox notificationButtons = new HBox(8, dismiss, checkStock);
                notificationButtons.setAlignment(Pos.CENTER_RIGHT);

                notification.getChildren().addAll(
                                aiHeader,
                                notificationText,
                                notificationSpacer,
                                notificationButtons);

                hbright.getChildren().addAll(leftContent, notification);

                HBox categoryHeader = new HBox();
                categoryHeader.setAlignment(Pos.CENTER_LEFT);

                Label categoryTitle = new Label("Browse by Category");
                categoryTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                Label viewAll = new Label("View all  →");
                viewAll.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-cursor: hand;");

                Region categorySpacer = new Region();
                HBox.setHgrow(categorySpacer, Priority.ALWAYS);

                categoryHeader.getChildren().addAll(categoryTitle, categorySpacer, viewAll);

                Button grocerie = new Button("🛒\nGroceries");
                Button electronics = new Button("▣\nElectronics");
                electronics.setOnAction(event -> {
                        Electronics gs = new Electronics(
                                        userId);
                        Homepage.HomepageStage.setScene(gs.getElectrScene());
                });
                Button fashion = new Button("♧\nFashion");
                Button health = new Button("✚\nHealth");
                Button home = new Button("⌂\nHome");
                Button gifts = new Button("♧\nGifts");
                Button beauty = new Button("✦\nBeauty");
                Button pharmacy = new Button("✚\nPharmacy");
                Button sports = new Button("⚽\nSports");
                Button furniture = new Button("⌂\nFurniture");
                Button toys = new Button("♟\nToys");
                Button stationery = new Button("✎\nStationery");

                String categoryStyle = "-fx-background-color: white;" +
                                "-fx-text-fill: #D94F0B;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 15;" +
                                "-fx-border-color: #E8E3EA;" +
                                "-fx-border-width: 1;" +
                                "-fx-border-radius: 15;" +
                                "-fx-padding: 10;" +
                                "-fx-cursor: hand;";

                String categoryHoverStyle = "-fx-background-color: #FFF2E9;" +
                                "-fx-text-fill: #FF6900;" +
                                "-fx-font-size: 9px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 15;" +
                                "-fx-border-color: #FFB889;" +
                                "-fx-border-width: 1;" +
                                "-fx-border-radius: 15;" +
                                "-fx-padding: 10;" +
                                "-fx-cursor: hand;";

                Button[] categoryButtons = {
                                grocerie, electronics, fashion, health, home, gifts,
                                beauty, pharmacy, sports, furniture, toys, stationery
                };

                for (Button button : categoryButtons) {
                        button.setPrefWidth(105);
                        button.setPrefHeight(78);
                        button.setMinWidth(105);
                        button.setMinHeight(78);
                        button.setMaxWidth(105);
                        button.setMaxHeight(78);
                        button.setStyle(categoryStyle);

                        button.setOnMouseEntered(e -> button.setStyle(categoryHoverStyle));
                        button.setOnMouseExited(e -> button.setStyle(categoryStyle));
                }

                HBox categoriesRow1 = new HBox(12, grocerie, electronics, fashion, health, home, gifts, beauty,
                                pharmacy, sports, furniture, toys, stationery);
                categoriesRow1.setAlignment(Pos.CENTER_LEFT);

                VBox categorySection = new VBox(13, categoryHeader, categoriesRow1);
                categorySection.setPadding(new Insets(0, 8, 20, 8));

                DropShadow cardShadow3 = new DropShadow();
                cardShadow3.setRadius(10);
                cardShadow3.setOffsetY(3);
                cardShadow3.setSpread(0.02);
                cardShadow3.setColor(Color.rgb(0, 0, 0, 0.08));

                DropShadow buttonShadow = new DropShadow();
                buttonShadow.setRadius(8);
                buttonShadow.setOffsetY(2);
                buttonShadow.setColor(Color.rgb(0, 0, 0, 0.12));

                Label title1 = new Label("# Groceries");
                title1.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 24px;" +
                                                "-fx-font-weight: 800;" +
                                                "-fx-text-fill: #222222;");

                List<Product> products =
                                getProducts();

                Label subtitle1 =
                                new Label(
                                                products.size()
                                                                + " Items found");
                subtitle1.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-text-fill: #777777;");

                Label sortLabel = new Label("Sort by: Popularity ⌄");
                sortLabel.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #222222;" +
                                                "-fx-cursor: hand;");

                Region headerSpacer = new Region();
                HBox.setHgrow(headerSpacer, Priority.ALWAYS);

                HBox headerRow = new HBox(15, title1, headerSpacer, sortLabel);
                headerRow.setAlignment(Pos.CENTER_LEFT);
                headerRow.setPadding(new Insets(0, 0, 10, 0));

                VBox headerSection = new VBox(5, headerRow, subtitle1);
                headerSection.setPadding(new Insets(20, 20, 10, 20));

                Region divider = new Region();
                divider.setPrefHeight(1);
                divider.setStyle("-fx-background-color: #EAE6EC;");
                divider.setPadding(new Insets(0, 20, 0, 20));

                VBox content = new VBox(12);
                content.setPadding(new Insets(0, 20, 20, 20));

                // ============================================================
                // DYNAMIC PRODUCT LIST / GRID
                // Products are already fetched through ProductController.
                // ============================================================
                FlowPane productsGrid = new FlowPane(15, 15);
                productsGrid.setAlignment(Pos.TOP_LEFT);
                productsGrid.setPadding(new Insets(10, 0, 10, 0));
                productsGrid.setStyle("-fx-background-color: transparent;");
                productsGrid.setPrefWrapLength(1400);

                if (products.isEmpty()) {

                        Label emptyLabel =
                                        new Label(
                                                        "No products available right now.");

                        emptyLabel.setStyle(
                                        "-fx-font-family: 'Montserrat';" +
                                        "-fx-font-size: 14px;" +
                                        "-fx-text-fill: #777777;" +
                                        "-fx-padding: 30;");

                        productsGrid.getChildren().add(
                                        emptyLabel);

                } else {

                        for (Product product : products) {

                                productsGrid.getChildren().add(
                                                createProductCard(
                                                                product,
                                                                cardShadow3));
                        }
                }

                // ============================================================
                // DELIVERY BANNER
                // ============================================================
                HBox deliveryBanner = new HBox(15);
                deliveryBanner.setAlignment(Pos.CENTER_LEFT);
                deliveryBanner.setPadding(new Insets(18, 20, 18, 20));
                deliveryBanner.setStyle(
                                "-fx-background-color: #FFF3EA;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-color: #FFD8C4;" +
                                "-fx-border-radius: 12;" +
                                "-fx-border-width: 1;");
                deliveryBanner.setEffect(cardShadow3);

                Label lightningIcon = new Label("⚡");
                lightningIcon.setStyle(
                                "-fx-font-size: 28px;" +
                                "-fx-padding: 0 5 0 0;");

                VBox deliveryText = new VBox(2);

                Label deliveryTitle =
                                new Label("LIGHTNING DELIVERY");
                deliveryTitle.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: 800;" +
                                "-fx-text-fill: #FF6900;");

                Label deliveryDesc =
                                new Label("Freshness delivered in 15 minutes.");
                deliveryDesc.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                "-fx-font-size: 11px;" +
                                "-fx-text-fill: #444444;");

                deliveryText.getChildren().addAll(
                                deliveryTitle,
                                deliveryDesc);

                Region deliverySpacer = new Region();
                HBox.setHgrow(deliverySpacer, Priority.ALWAYS);

                Button shopExpress =
                                new Button("Shop Express →");

                shopExpress.setStyle(
                                "-fx-background-color: #FF6900;" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 11px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 20;" +
                                "-fx-padding: 8 18 8 18;" +
                                "-fx-cursor: hand;");

                shopExpress.setEffect(buttonShadow);

                shopExpress.setOnMouseEntered(e -> {
                        shopExpress.setStyle(
                                        "-fx-background-color: #E85A00;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-size: 11px;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-background-radius: 20;" +
                                        "-fx-padding: 8 18 8 18;" +
                                        "-fx-cursor: hand;");
                });

                shopExpress.setOnMouseExited(e -> {
                        shopExpress.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-font-size: 11px;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-background-radius: 20;" +
                                        "-fx-padding: 8 18 8 18;" +
                                        "-fx-cursor: hand;");
                });

                deliveryBanner.getChildren().addAll(
                                lightningIcon,
                                deliveryText,
                                deliverySpacer,
                                shopExpress);

                Button loadMore = new Button("Load More Items");
                loadMore.setPrefWidth(200);
                loadMore.setPrefHeight(42);
                loadMore.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: #FF6900;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20;" +
                                                "-fx-border-color: #FF6900;" +
                                                "-fx-border-radius: 20;" +
                                                "-fx-border-width: 1.5;" +
                                                "-fx-cursor: hand;");
                loadMore.setEffect(cardShadow3);
                loadMore.setOnMouseEntered(e -> {
                        loadMore.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-text-fill: white;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 20;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-radius: 20;" +
                                                        "-fx-border-width: 1.5;" +
                                                        "-fx-cursor: hand;");
                });

                loadMore.setOnMouseExited(e -> {
                        loadMore.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-text-fill: #FF6900;" +
                                                        "-fx-font-size: 12px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-background-radius: 20;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-radius: 20;" +
                                                        "-fx-border-width: 1.5;" +
                                                        "-fx-cursor: hand;");
                });

                HBox loadMoreContainer = new HBox(loadMore);
                loadMoreContainer.setAlignment(Pos.CENTER);
                loadMoreContainer.setPadding(new Insets(20, 0, 20, 0));

                content.getChildren().addAll(navBox, hbright, categorySection,
                                productsGrid,
                                deliveryBanner,
                                loadMoreContainer);
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(content);
                scrollPane.setFitToWidth(true);
                scrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");
                scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                VBox Rightvbox = new VBox(0);
                Rightvbox.setPadding(new Insets(0, 0, 0, 0));
                Rightvbox.setStyle("-fx-background-color: #F7F5F8;");
                Rightvbox.getChildren().add(scrollPane);
                VBox.setVgrow(scrollPane, Priority.ALWAYS);

                BorderPane mainBox = new BorderPane();
                mainBox.setLeft(leftBox);
                mainBox.setCenter(Rightvbox);

                mainBox.setStyle("-fx-background-color: #F7F5F8;");

                Scene sc = new Scene(mainBox, 1550, 850);

                GrocriesScene = sc;

                return GrocriesScene;
        }

        public void backtogrosary() {
                Homepage.HomepageStage.setScene(GrocriesScene);
        }

}

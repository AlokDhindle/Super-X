package com.kryox.view.Customer;


import java.util.ArrayList;
import java.util.List;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.Shopkeeper.ProductModel;

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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Electronics {

    private Scene Electrinicscene;
    private final String userId;
    private String categoryName = "Electronics";
    private Runnable currentReturnCallback;

    private static final String ORANGE = "#FF6900";
    private static final String ORANGE_DARK = "#D9570A";
    private static final String ORANGE_LIGHT = "#FFF1E8";
    private static final String CREAM = "#EBCCB7";
    private static final String PAGE_BG = "#F7F5F8";
    private static final String CARD_BG = "#FFFFFF";
    private static final String TEXT = "#202124";
    private static final String MUTED = "#77747A";
    private static final String BORDER = "#E9E4EA";

    private String searchQuery = "";

    public Electronics(String userId) {
        this.userId = userId;
        this.categoryName = "Electronics";
        this.searchQuery = "";
    }

    public Electronics(String userId, String categoryName) {
        this.userId = userId;
        this.categoryName = (categoryName != null && !categoryName.isBlank()) ? categoryName : "Electronics";
        this.searchQuery = "";
    }

    public Electronics(String userId, String categoryName, String searchQuery) {
        this.userId = userId;
        this.categoryName = (categoryName != null && !categoryName.isBlank()) ? categoryName : "Search Results";
        this.searchQuery = (searchQuery != null) ? searchQuery.trim() : "";
    }

    public Scene getElectrScene(Runnable returnToElectronics) {
        this.currentReturnCallback = returnToElectronics;

        DropShadow sidebarShadow = new DropShadow();
        sidebarShadow.setRadius(18);
        sidebarShadow.setOffsetX(3);
        sidebarShadow.setOffsetY(0);
        sidebarShadow.setColor(Color.rgb(0, 0, 0, 0.10));

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(16);
        cardShadow.setOffsetY(6);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.09));

        DropShadow softShadow = new DropShadow();
        softShadow.setRadius(10);
        softShadow.setOffsetY(3);
        softShadow.setColor(Color.rgb(0, 0, 0, 0.07));

        VBox leftBox = CustomerSidebar.createSidebar(userId, "Electronics");

        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(0, 28, 35, 28)
        );

        content.setStyle(
                "-fx-background-color: " + PAGE_BG + ";"
        );

        HBox navBox =
                new HBox(14);

        navBox.setPrefHeight(72);
        navBox.setMinHeight(72);

        navBox.setAlignment(
                Pos.CENTER_LEFT
        );

        navBox.setPadding(
                new Insets(12, 20, 12, 20)
        );

        navBox.setStyle(
                "-fx-background-color: " + CREAM + ";" +
                "-fx-border-color: transparent transparent #DFC1AC transparent;" +
                "-fx-border-width: 0 0 1 0;"
        );

        Button offers =
                createTopButton("Offers");

        Button shops =
                createTopButton("Shops");

        Button support =
                createTopButton("Support");

        HBox topLinks =
                new HBox(4);

        topLinks.setAlignment(
                Pos.CENTER_LEFT
        );

        topLinks.getChildren().addAll(
                offers,
                shops,
                support
        );

        offers.setOnAction(e -> CustomerNavigation.navigateToDeals(userId));
        shops.setOnAction(e -> CustomerNavigation.navigateToNearbyShops(userId));
        support.setOnAction(e -> CustomerNavigation.navigateToHelp(userId));

        Region navSpacer1 =
                new Region();

        HBox.setHgrow(
                navSpacer1,
                Priority.ALWAYS
        );

        // SEARCH
        TextField searchBox =
                new TextField();

        searchBox.setPromptText(
                "Search products, shops or deals with AI..."
        );

        if (this.searchQuery != null && !this.searchQuery.isBlank()) {
            searchBox.setText(this.searchQuery);
        }

        searchBox.setPrefWidth(310);
        searchBox.setMinWidth(220);
        searchBox.setPrefHeight(40);

        searchBox.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0 10 0 16;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #9A969C;"
        );

        Button searchBtn = new Button("🔍");
        searchBtn.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-text-fill: #FF6900;" +
            "-fx-font-size: 13px;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 0 10 0 0;"
        );

        HBox searchContainer = new HBox(searchBox, searchBtn);
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setPrefHeight(40);
        searchContainer.setStyle(
            "-fx-background-color: #FAF9FB;" +
            "-fx-background-radius: 21;" +
            "-fx-border-color: #E5DDE4;" +
            "-fx-border-radius: 21;" +
            "-fx-border-width: 1;"
        );

        Region navSpacer2 =
                new Region();

        HBox.setHgrow(
                navSpacer2,
                Priority.ALWAYS
        );

        // LOCATION
        HBox locationBox =
                new HBox(5);

        locationBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Label locationIcon =
                new Label("⌖");

        locationIcon.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #555555;"
        );

        Label locationText =
                new Label(
                        "Downtown Manhattan⌄"
                );

        locationText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #555555;"
        );

        locationBox.getChildren().addAll(
                locationIcon,
                locationText
        );
        locationBox.setStyle("-fx-cursor: hand;");
        locationBox.setOnMouseClicked(e -> CustomerNavigation.navigateToNearbyShops(userId));

        // ACTION BUTTONS
        HBox actions =
                new HBox(8);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button notificationButton =
                createActionButton("🔔");

        Button cartButton =
                createActionButton("🛒");

        Button profileButton =
                createActionButton("●");

        notificationButton.setOnAction(e -> CustomerNavigation.navigateToNotifications(userId));
        cartButton.setOnAction(e -> CustomerNavigation.navigateToCart(userId));
        profileButton.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

        actions.getChildren().addAll(
                notificationButton,
                cartButton,
                profileButton
        );

        navBox.getChildren().addAll(
                topLinks,
                navSpacer1,
                searchContainer,
                navSpacer2,
                locationBox,
                actions
        );

        HBox hero = CustomerAIHeroBanner.createHeroBanner(
                userId,
                () -> CustomerNavigation.navigateToGroceries(userId),
                () -> CustomerNavigation.navigateToDeals(userId)
        );

        VBox categorySection =
                new VBox(13);

        HBox categoryHeader =
                new HBox();

        categoryHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        Label categoryTitle =
                new Label(
                        "Browse by Category"
                );

        categoryTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region categorySpacer =
                new Region();

        HBox.setHgrow(
                categorySpacer,
                Priority.ALWAYS
        );

        Label viewAll =
                new Label(
                        "View all  →"
                );

        viewAll.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";" +
                "-fx-cursor: hand;"
        );

        categoryHeader.getChildren().addAll(
                categoryTitle,
                categorySpacer,
                viewAll
        );

        // FLOWPANE fixes category overflow
        FlowPane categories =
                new FlowPane();

        categories.setHgap(12);
        categories.setVgap(10);
        categories.setAlignment(
                Pos.CENTER_LEFT
        );

        categories.setPrefWrapLength(
                1100
        );

        String[] categoryNames = {
                "🛒  Groceries",
                "▣  Electronics",
                "♧  Fashion",
                "✚  Health",
                "⌂  Home",
                "♧  Gifts",
                "✦  Beauty",
                "✚  Pharmacy",
                "⚽  Sports",
                "⌂  Furniture",
                "♟  Toys",
                "✎  Stationery"
        };

        for (String category : categoryNames) {

            Button categoryButton =
                    new Button(category);

            categoryButton.setPrefSize(
                    105,
                    60
            );

            categoryButton.setMinSize(
                    105,
                    60
            );

            categoryButton.setMaxSize(
                    105,
                    60
            );

            categoryButton.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #D94F0B;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 15;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 15;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: hand;"
            );

            categoryButton.setOnMouseEntered(
                    e -> categoryButton.setStyle(
                            "-fx-background-color: " + ORANGE_LIGHT + ";" +
                            "-fx-text-fill: " + ORANGE + ";" +
                            "-fx-font-size: 12px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-color: #FFB889;" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-width: 1;" +
                            "-fx-cursor: hand;"
                    )
            );

            categoryButton.setOnMouseExited(
                    e -> categoryButton.setStyle(
                            "-fx-background-color: white;" +
                            "-fx-text-fill: #D94F0B;" +
                            "-fx-font-size: 12px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-color: " + BORDER + ";" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-width: 1;" +
                            "-fx-cursor: hand;"
                    )
            );

            final String rawCategory = category;
            categoryButton.setOnAction(e -> {
                String cleanCat = rawCategory.replaceAll("^[^a-zA-Z]+", "").trim();
                if ("Groceries".equalsIgnoreCase(cleanCat)) {
                    CustomerNavigation.navigateToGroceries(userId);
                } else if ("Electronics".equalsIgnoreCase(cleanCat)) {
                    CustomerNavigation.navigateToElectronics(userId);
                } else {
                    Electronics catPage = new Electronics(userId, cleanCat);
                    Homepage.HomepageStage.setScene(catPage.getElectrScene(returnToElectronics));
                }
            });

            categories.getChildren().add(
                    categoryButton
            );
        }

        categorySection.getChildren().addAll(
                categoryHeader,
                categories
        );

        HBox mainLayout =
                new HBox(22);

        mainLayout.setAlignment(
                Pos.TOP_LEFT
        );

        VBox filtersBox =
                new VBox(10);

        filtersBox.setPrefWidth(250);
        filtersBox.setMinWidth(250);
        filtersBox.setMaxWidth(250);

        filtersBox.setPadding(
                new Insets(19)
        );

        filtersBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        filtersBox.setEffect(
                softShadow
        );

        Label filterTitle =
                new Label("Filters");

        filterTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 21px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label brandTitle =
                createFilterHeading("BRAND");

        VBox brandBox =
                new VBox(7);

        String[] brands = {
                "Apple",
                "Samsung",
                "Sony",
                "Dell",
                "Logitech"
        };

        for (String brand : brands) {
            brandBox.getChildren().add(
                    createFilterItem(brand)
            );
        }

        Label categoryFilterTitle =
                createFilterHeading("CATEGORY");

        VBox categoryFilterBox =
                new VBox(7);

        String[] filterCategories = {
                "Laptops",
                "Smartphones",
                "Audio",
                "Accessories"
        };

        for (String category : filterCategories) {
            categoryFilterBox.getChildren().add(
                    createFilterItem(category)
            );
        }

        Region divider =
                new Region();

        divider.setPrefHeight(1);

        divider.setMaxWidth(
                Double.MAX_VALUE
        );

        divider.setStyle(
                "-fx-background-color: #EAE6EC;"
        );

        Label priceTitle =
                createFilterHeading(
                        "PRICE RANGE"
                );

        Label priceValue =
                new Label(
                        "₹10  —  ₹2,000+"
                );

        priceValue.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #444444;"
        );

        StackPane priceBar =
                new StackPane();

        priceBar.setPrefHeight(5);

        Region priceTrack =
                new Region();

        priceTrack.setPrefHeight(5);

        priceTrack.setMaxWidth(
                Double.MAX_VALUE
        );

        priceTrack.setStyle(
                "-fx-background-color: #FFE0CC;" +
                "-fx-background-radius: 5;"
        );

        Region priceProgress =
                new Region();

        priceProgress.setPrefHeight(5);
        priceProgress.setPrefWidth(155);

        priceProgress.setMaxWidth(
                Double.MAX_VALUE
        );

        priceProgress.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-background-radius: 5;"
        );

        StackPane.setAlignment(
                priceTrack,
                Pos.CENTER_LEFT
        );

        StackPane.setAlignment(
                priceProgress,
                Pos.CENTER_LEFT
        );

        priceBar.getChildren().addAll(
                priceTrack,
                priceProgress
        );

        Label ratingTitle =
                createFilterHeading("RATING");

        Label rating =
                new Label(
                        "★★★★★   4.0 & Up"
                );

        rating.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        filtersBox.getChildren().addAll(
                filterTitle,
                brandTitle,
                brandBox,
                categoryFilterTitle,
                categoryFilterBox,
                divider,
                priceTitle,
                priceValue,
                priceBar,
                ratingTitle,
                rating
        );

        VBox productsContent =
                new VBox(15);

        HBox.setHgrow(
                productsContent,
                Priority.ALWAYS
        );

        productsContent.setAlignment(
                Pos.TOP_LEFT
        );

        // PRODUCT HEADER
        HBox productHeading =
                new HBox();

        productHeading.setAlignment(
                Pos.CENTER_LEFT
        );

        Label electronicsTitle =
                new Label(categoryName);

        electronicsTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 23px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Region productHeaderSpacer =
                new Region();

        HBox.setHgrow(
                productHeaderSpacer,
                Priority.ALWAYS
        );

        Label productCount =
                new Label(
                        "0 products"
                );

        productCount.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8A858B;"
        );

        productHeading.getChildren().addAll(
                electronicsTitle,
                productHeaderSpacer,
                productCount
        );

        HBox promotion =
                new HBox();

        promotion.setMinHeight(126);
        promotion.setPrefHeight(126);

        promotion.setPadding(
                new Insets(18, 22, 18, 22)
        );

        promotion.setAlignment(
                Pos.CENTER_LEFT
        );

        LinearGradient promotionGradient =
                new LinearGradient(
                        0,
                        0,
                        1,
                        0,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#191A2C")
                        ),
                        new Stop(
                                1,
                                Color.web("#262E4E")
                        )
                );

        promotion.setBackground(
                new Background(
                        new BackgroundFill(
                                promotionGradient,
                                new CornerRadii(18),
                                Insets.EMPTY
                        )
                )
        );

        promotion.setEffect(
                cardShadow
        );

        VBox promotionText =
                new VBox(5);

        Label promoSmall =
                new Label(
                        "FEATURED PROMOTION"
                );

        promoSmall.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #FF9D67;"
        );

        Label promoTitle =
                new Label(
                        "Next-Gen Tech"
                );

        promoTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: white;"
        );

        Label promoDescription =
                new Label(
                        "Premium laptops, smartphones & audio essentials — up to 30% off."
                );

        promoDescription.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #C7C9D2;"
        );

        Button shopSale =
                new Button(
                        "Shop the Sale  →"
                );

        shopSale.setPrefSize(
                137,
                34
        );

        shopSale.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-cursor: hand;"
        );

        promotionText.getChildren().addAll(
                promoSmall,
                promoTitle,
                promoDescription,
                shopSale
        );

        Region promoSpacer =
                new Region();

        HBox.setHgrow(
                promoSpacer,
                Priority.ALWAYS
        );

        Label promoBadge =
                new Label(
                        "30%\nOFF"
                );

        promoBadge.setAlignment(
                Pos.CENTER
        );

        promoBadge.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 900;" +
                "-fx-background-radius: 45;" +
                "-fx-padding: 13 12 13 12;"
        );

        promotion.getChildren().addAll(
                promotionText,
                promoSpacer,
                promoBadge
        );

        FlowPane productGrid =
                new FlowPane();

        productGrid.setHgap(18);
        productGrid.setVgap(20);

        productGrid.setAlignment(
                Pos.TOP_LEFT
        );

        /*
         * FlowPane automatically moves cards to the next row.
         */
        productGrid.setPrefWrapLength(
                850
        );

        List<String> cartItems =
                new ArrayList<>();

        try {

                /*
                 * ProductController initializes ProductDAO.
                 * ProductDAO reads Products from all Shopkeepers.
                 */
                ProductController productController =
                        new ProductController();

                ArrayList<ProductModel> firebaseProducts =
                        productController.fetchProducts();

                java.util.function.Consumer<String> filterProducts = (query) -> {
                    productGrid.getChildren().clear();
                    int matchCount = 0;
                    String q = (query != null) ? query.trim().toLowerCase() : "";

                    if (firebaseProducts != null) {
                        for (ProductModel product : firebaseProducts) {
                            if (product == null) continue;

                            String category = product.getCategory();

                            // Category match (if user is searching globally or in Search Results, allow cross-category match)
                            boolean catMatch = isCategoryMatch(category, this.categoryName);
                            if (!catMatch && ("Search Results".equalsIgnoreCase(this.categoryName) || !q.isEmpty())) {
                                catMatch = true;
                            }

                            if (!catMatch) continue;

                            // Query match
                            if (!q.isEmpty()) {
                                String name = product.getProductName() != null ? product.getProductName().toLowerCase() : "";
                                String brand = product.getBrand() != null ? product.getBrand().toLowerCase() : "";
                                String cat = product.getCategory() != null ? product.getCategory().toLowerCase() : "";
                                String desc = product.getDescriptionValue() != null ? product.getDescriptionValue().toLowerCase() : "";

                                if (!name.contains(q) && !brand.contains(q) && !cat.contains(q) && !desc.contains(q)) {
                                    continue;
                                }
                            }

                            VBox productCard = createProductCard(product, cartItems, cartButton);
                            productGrid.getChildren().add(productCard);
                            matchCount++;
                        }
                    }

                    productCount.setText(matchCount + " products");

                    if (matchCount == 0) {
                        Label noProducts = new Label(
                                !q.isEmpty()
                                        ? "No products found matching \"" + query + "\"."
                                        : "No " + this.categoryName + " products found right now."
                        );
                        noProducts.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-text-fill: #77747A;" +
                                "-fx-padding: 30;"
                        );
                        productGrid.getChildren().add(noProducts);
                    }
                };

                // Initial render
                filterProducts.accept(this.searchQuery);

                // Real-time typing & submit listeners
                searchBox.textProperty().addListener((obs, oldVal, newVal) -> filterProducts.accept(newVal));
                searchBox.setOnAction(e -> filterProducts.accept(searchBox.getText()));
                searchBtn.setOnAction(e -> filterProducts.accept(searchBox.getText()));

        } catch (Exception e) {

                System.out.println(
                        "ERROR: Electronics products could not be loaded."
                );

                e.printStackTrace();

                productCount.setText(
                        "0 products"
                );

                Label errorLabel =
                        new Label(
                                "Unable to load Electronics products."
                        );

                errorLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #B00020;" +
                        "-fx-padding: 30;"
                );

                productGrid.getChildren().add(
                        errorLabel
                );
        }

        productsContent.getChildren().addAll(
                productHeading,
                promotion,
                productGrid
        );

        mainLayout.getChildren().addAll(
                filtersBox,
                productsContent
        );

        content.getChildren().addAll(
                navBox,
                hero,
                categorySection,
                mainLayout
        );

        RadialGradient glow =
                new RadialGradient(
                        0,
                        0,
                        0.85,
                        0.12,
                        0.28,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0.0,
                                Color.web("#FF9148", 0.16)
                        ),
                        new Stop(
                                0.45,
                                Color.web("#FFD1B4", 0.07)
                        ),
                        new Stop(
                                1.0,
                                Color.TRANSPARENT
                        )
                );

        content.setBackground(
                new Background(
                        new BackgroundFill(
                                glow,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        ScrollPane scrollPane =
                new ScrollPane();

        scrollPane.setContent(
                content
        );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background-color: " + PAGE_BG + ";" +
                "-fx-background: " + PAGE_BG + ";" +
                "-fx-border-color: transparent;"
        );

        BorderPane mainBox =
                new BorderPane();

        mainBox.setLeft(
                leftBox
        );

        mainBox.setCenter(
                scrollPane
        );

        mainBox.setStyle(
                "-fx-background-color: " + PAGE_BG + ";"
        );

        Scene scene =
                new Scene(
                        mainBox,
                        1530,
                        850
                );

        Electrinicscene =
                scene;

        return Electrinicscene;
    }

    private VBox createProductCard(
            ProductModel product,
            List<String> cartItems,
            Button cartButton
    ) {

        String productName =
                safeText(
                        product.getProductName(),
                        "Unnamed Product"
                );

        String brand =
                safeText(
                        product.getBrand(),
                        "Brand"
                );

        String category =
                safeText(
                        product.getCategory(),
                        "Electronics"
                );

        String imageUrl =
                safeText(
                        product.getImageUrl(),
                        ""
                );

        double sellingPrice =
                product.getSellingPrice() != null
                        ? product.getSellingPrice()
                        : 0.0;

        double discount =
                product.getDiscount() != null
                        ? product.getDiscount()
                        : 0.0;

        int stockQuantity =
                Math.max(
                        0,
                        product.getStockQuantity()
                );

        String status =
                safeText(
                        product.getStatus(),
                        ""
                );

        VBox card =
                new VBox(9);

        card.setPrefWidth(270);
        card.setMinWidth(270);
        card.setMaxWidth(270);

        card.setPrefHeight(382);
        card.setMinHeight(382);
        card.setMaxHeight(382);

        card.setPadding(
                new Insets(12)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;"
        );

        DropShadow shadow =
                new DropShadow();

        shadow.setRadius(13);
        shadow.setOffsetY(5);
        shadow.setColor(
                Color.rgb(0, 0, 0, 0.08)
        );

        card.setEffect(shadow);

        card.setOnMouseEntered(e -> {

            card.setTranslateY(-4);

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-color: #FFB889;" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: hand;"
            );
        });

        card.setOnMouseExited(e -> {

            card.setTranslateY(0);

            card.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-width: 1;" +
                    "-fx-cursor: hand;"
            );
        });

        StackPane imageBox =
                new StackPane();

        imageBox.setPrefSize(
                246,
                165
        );

        imageBox.setMinSize(
                246,
                165
        );

        imageBox.setMaxSize(
                246,
                165
        );

        imageBox.setAlignment(
                Pos.CENTER
        );

        imageBox.setStyle(
                "-fx-background-color: #F8F7F8;" +
                "-fx-background-radius: 14;"
        );

        Label categoryBadge =
                new Label(category);

        categoryBadge.setStyle(
                "-fx-background-color: " + ORANGE_LIGHT + ";" +
                "-fx-text-fill: " + ORANGE_DARK + ";" +
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5 8 5 8;"
        );

        StackPane.setAlignment(
                categoryBadge,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                categoryBadge,
                new Insets(9, 0, 0, 9)
        );

        if (!imageUrl.isEmpty()) {

            try {

                Image image =
                        new Image(
                                imageUrl,
                                205,
                                140,
                                true,
                                true,
                                true
                        );

                if (!image.isError()) {

                    ImageView imageView =
                            new ImageView(image);

                    imageView.setFitWidth(205);
                    imageView.setFitHeight(140);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);

                    imageBox.getChildren().add(
                            imageView
                    );

                } else {

                    addFallbackIcon(
                            imageBox
                    );
                }

            } catch (Exception e) {

                addFallbackIcon(
                        imageBox
                );
            }

        } else {

            addFallbackIcon(
                    imageBox
            );
        }

        imageBox.getChildren().add(
                categoryBadge
        );

        Label name =
                new Label(productName);

        name.setWrapText(true);
        name.setMaxWidth(245);

        name.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label shopLabel = new Label();
        shopLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #B84200;"
        );
        CustomerShopResolver.bindShopName(shopLabel, product.getShopkeeperUid());

        HBox meta =
                new HBox(6);

        meta.setAlignment(
                Pos.CENTER_LEFT
        );

        Label brandLabel =
                new Label(brand);

        brandLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region metaSpacer =
                new Region();

        HBox.setHgrow(
                metaSpacer,
                Priority.ALWAYS
        );

        String statusText =
                status.isEmpty()
                        ? "Available"
                        : status;

        Label statusLabel =
                new Label(statusText);

        statusLabel.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        meta.getChildren().addAll(
                brandLabel,
                metaSpacer,
                statusLabel
        );

        VBox priceBox =
                new VBox(1);

        Label priceLabel =
                new Label(
                        String.format(
                                "₹%.2f",
                                sellingPrice
                        )
                );

        priceLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label delivery =
                new Label(
                        discount > 0
                                ? String.format(
                                        "● %.0f%% OFF",
                                        discount
                                )
                                : "● Fast delivery"
                );

        delivery.setStyle(
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8B888D;"
        );

        priceBox.getChildren().addAll(
                priceLabel,
                delivery
        );

        Label quantityLabel =
                new Label("1");

        quantityLabel.setPrefWidth(
                30
        );

        quantityLabel.setAlignment(
                Pos.CENTER
        );

        quantityLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        String quantityButtonStyle =
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;" +
                "-fx-min-width: 28px;" +
                "-fx-min-height: 28px;" +
                "-fx-max-width: 28px;" +
                "-fx-max-height: 28px;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        Button minusButton =
                new Button("-");

        minusButton.setStyle(
                quantityButtonStyle
        );

        Button plusButton =
                new Button("+");

        plusButton.setStyle(
                quantityButtonStyle
        );

        HBox quantityBox =
                new HBox(
                        6,
                        minusButton,
                        quantityLabel,
                        plusButton
                );

        quantityBox.setAlignment(
                Pos.CENTER
        );

        minusButton.setOnAction(e -> {

            int quantity =
                    Integer.parseInt(
                            quantityLabel.getText()
                    );

            if (quantity > 1) {

                quantity--;

                quantityLabel.setText(
                        String.valueOf(quantity)
                );
            }
        });

        plusButton.setOnAction(e -> {

            int quantity =
                    Integer.parseInt(
                            quantityLabel.getText()
                    );

            if (quantity < stockQuantity) {

                quantity++;

                quantityLabel.setText(
                        String.valueOf(quantity)
                );
            }
        });

        Button addButton =
                new Button("+ Add");

        addButton.setPrefSize(
                75,
                32
        );

        addButton.setMinSize(
                75,
                32
        );

        addButton.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 16;" +
                "-fx-cursor: hand;"
        );

        // Disable Add when stock is zero
        if (stockQuantity <= 0) {

            addButton.setDisable(true);
            addButton.setText(
                    "Out of Stock"
            );

        } else {

            addButton.setOnAction(e -> {

                try {

                    int quantity =
                            Integer.parseInt(
                                    quantityLabel.getText()
                            );

                    if (quantity > stockQuantity) {
                        quantity = stockQuantity;
                    }

                    CARTcontroller cl =
                            new CARTcontroller();

                    cl.addTocart(
                            userId,
                            productName,
                            sellingPrice,
                            "Electronics",
                            quantity,
                            product.getShopkeeperUid()
                    );

                    cartItems.add(
                            productName +
                            " x " +
                            quantity
                    );

                    cartButton.setText(
                            "🛒 " +
                            cartItems.size()
                    );

                    addButton.setText(
                            "✓ Added"
                    );

                    addButton.setStyle(
                            "-fx-background-color: #2E9B57;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 9px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 16;" +
                            "-fx-cursor: hand;"
                    );

                } catch (Exception ex) {

                    System.out.println(
                            "ERROR adding product to cart."
                    );

                    ex.printStackTrace();
                }
            });
        }

        HBox bottom =
                new HBox(7);

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        bottom.getChildren().addAll(
                priceBox,
                bottomSpacer,
                quantityBox,
                addButton
        );

        card.getChildren().addAll(
                imageBox,
                name,
                shopLabel,
                meta,
                bottom
        );

        card.setOnMouseClicked(e -> {
            if (e.getTarget() instanceof javafx.scene.Node) {
                javafx.scene.Node target = (javafx.scene.Node) e.getTarget();
                while (target != null && target != card) {
                    if (target instanceof Button) {
                        return;
                    }
                    target = target.getParent();
                }
            }
            CustomerProductDetails details = new CustomerProductDetails(userId, product, () -> {
                Homepage.HomepageStage.setScene(getElectrScene(currentReturnCallback));
            });
            Homepage.HomepageStage.setScene(details.getScene());
        });

        return card;
    }

    private String safeText(
            String value,
            String defaultValue
    ) {

        if (value == null ||
                value.trim().isEmpty()) {

            return defaultValue;
        }

        return value.trim();
    }

    private void addFallbackIcon(
            StackPane imageBox
    ) {

        Label fallback =
                new Label("💻");

        fallback.setStyle(
                "-fx-font-size: 70px;"
        );

        imageBox.getChildren().add(
                fallback
        );
    }

    private HBox createSidebarRow(
            String icon,
            String text
    ) {

        HBox row =
                new HBox(15);

        row.setPrefSize(
                205,
                42
        );

        row.setMinSize(
                205,
                42
        );

        row.setMaxSize(
                205,
                42
        );

        row.setPadding(
                new Insets(2, 8, 2, 13)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 12;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefWidth(
                25
        );

        iconLabel.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-text-fill: #242424;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 500;" +
                "-fx-text-fill: #333333;"
        );

        row.getChildren().addAll(
                iconLabel,
                textLabel
        );

        row.setOnMouseEntered(e -> {

            row.setStyle(
                    "-fx-background-color: " + ORANGE + ";" +
                    "-fx-background-radius: 12;"
            );

            iconLabel.setStyle(
                    "-fx-font-size: 19px;" +
                    "-fx-text-fill: white;"
            );

            textLabel.setStyle(
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: white;"
            );
        });

        row.setOnMouseExited(e -> {

            row.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-background-radius: 12;"
            );

            iconLabel.setStyle(
                    "-fx-font-size: 19px;" +
                    "-fx-text-fill: #242424;"
            );

            textLabel.setStyle(
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-weight: 500;" +
                    "-fx-text-fill: #333333;"
            );
        });

        return row;
    }

    private HBox createSmallSidebarRow(
            String icon,
            String text
    ) {

        HBox row =
                new HBox(10);

        row.setPrefSize(
                205,
                34
        );

        row.setMinSize(
                205,
                34
        );

        row.setMaxSize(
                205,
                34
        );

        row.setPadding(
                new Insets(0, 8, 0, 18)
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 10;"
        );

        Label iconLabel =
                new Label(icon);

        iconLabel.setPrefWidth(
                20
        );

        iconLabel.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #555555;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 500;" +
                "-fx-text-fill: #666666;"
        );

        row.getChildren().addAll(
                iconLabel,
                textLabel
        );

        row.setOnMouseEntered(e -> {

            row.setStyle(
                    "-fx-background-color: rgba(255,105,0,0.10);" +
                    "-fx-background-radius: 10;"
            );

            textLabel.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + ORANGE + ";"
            );
        });

        row.setOnMouseExited(e -> {

            row.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-background-radius: 10;"
            );

            textLabel.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: 500;" +
                    "-fx-text-fill: #666666;"
            );
        });

        return row;
    }

    private Label createFilterHeading(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #333333;" +
                "-fx-padding: 7 0 3 0;"
        );

        return label;
    }

    private HBox createFilterItem(
            String text
    ) {

        HBox row =
                new HBox(8);

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label box =
                new Label("□");

        box.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #88838A;"
        );

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #555158;"
        );

        row.getChildren().addAll(
                box,
                label
        );

        return row;
    }

    private Button createTopButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(
                e -> button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: " + ORANGE + ";" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 6 8 6 8;" +
                        "-fx-border-width: 0;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(
                e -> button.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #666666;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 6 8 6 8;" +
                        "-fx-border-width: 0;" +
                        "-fx-cursor: hand;"
                )
        );

        return button;
    }

    private Button createActionButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefSize(
                39,
                39
        );

        button.setMinSize(
                39,
                39
        );

        button.setMaxSize(
                39,
                39
        );

        String normal =
                "-fx-background-color: white;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #E5DDE4;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: " + ORANGE_LIGHT + ";" +
                "-fx-text-fill: " + ORANGE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #FFBD95;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(
                e -> button.setStyle(hover)
        );

        button.setOnMouseExited(
                e -> button.setStyle(normal)
        );

        return button;
    }

    public void backToElectronics() {

        if (Electrinicscene != null) {

            Homepage.HomepageStage.setScene(
                    Electrinicscene
            );
        }
    }

    private boolean isCategoryMatch(String prodCat, String targetCat) {
        if (targetCat == null || targetCat.isBlank() || "Search Results".equalsIgnoreCase(targetCat) || "All".equalsIgnoreCase(targetCat) || "All Products".equalsIgnoreCase(targetCat)) {
            return true;
        }
        if (prodCat == null) return false;
        String pc = prodCat.trim().toLowerCase();
        String tc = targetCat.trim().toLowerCase();

        if (pc.equalsIgnoreCase(tc) || pc.contains(tc) || tc.contains(pc)) return true;

        if (tc.contains("fashion") && (pc.contains("fashion") || pc.contains("cloth") || pc.contains("apparel"))) return true;
        if (tc.contains("health") && (pc.contains("health") || pc.contains("medic") || pc.contains("wellness"))) return true;
        if (tc.contains("home") && (pc.contains("home") || pc.contains("kitchen") || pc.contains("decor"))) return true;
        if (tc.contains("gift") && (pc.contains("gift") || pc.contains("toy") || pc.contains("craft"))) return true;
        if (tc.contains("sport") && (pc.contains("sport") || pc.contains("fitness") || pc.contains("gym"))) return true;
        if (tc.contains("furnit") && (pc.contains("furnit") || pc.contains("bed") || pc.contains("table") || pc.contains("chair"))) return true;
        if (tc.contains("toy") && (pc.contains("toy") || pc.contains("game") || pc.contains("kid"))) return true;
        if (tc.contains("stationer") && (pc.contains("stationer") || pc.contains("book") || pc.contains("pen"))) return true;
        if (tc.contains("beaut") && (pc.contains("beaut") || pc.contains("cosmet") || pc.contains("skin"))) return true;
        if (tc.contains("pharmac") && (pc.contains("pharmac") || pc.contains("medic") || pc.contains("drug"))) return true;

        return false;
    }
}

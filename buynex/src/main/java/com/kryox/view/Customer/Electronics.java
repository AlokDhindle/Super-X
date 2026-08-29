package com.kryox.view.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.kryox.controller.Customer.CARTcontroller;

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

    // =========================================================
    // BUY NEX PREMIUM THEME
    // =========================================================
    private static final String ORANGE = "#FF6900";
    private static final String ORANGE_DARK = "#D9570A";
    private static final String ORANGE_LIGHT = "#FFF1E8";
    private static final String CREAM = "#EBCCB7";
    private static final String PAGE_BG = "#F7F5F8";
    private static final String CARD_BG = "#FFFFFF";
    private static final String TEXT = "#202124";
    private static final String MUTED = "#77747A";
    private static final String BORDER = "#E9E4EA";

    public Electronics(String userId) {
        this.userId = userId;
    }

    // =========================================================
    // MAIN SCENE
    // =========================================================
    public Scene getElectrScene() {

        // ---------------------------------------------------------
        // SHADOWS
        // ---------------------------------------------------------
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

        // =========================================================
        // LEFT SIDEBAR
        // =========================================================
        VBox leftBox = new VBox(12);

        leftBox.setPrefWidth(245);
        leftBox.setMinWidth(245);
        leftBox.setMaxWidth(245);

        leftBox.setPadding(new Insets(28, 20, 20, 20));
        leftBox.setAlignment(Pos.TOP_CENTER);

        leftBox.setStyle(
                "-fx-background-color: " + CREAM + ";"
        );

        leftBox.setEffect(sidebarShadow);

        // LOGO
        Label logo = new Label("BuyNeX");

        logo.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 29px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #E87500;"
        );

        // PREMIUM SHOPPER BOX
        HBox premiumBox = new HBox();

        premiumBox.setPrefSize(205, 64);
        premiumBox.setMinSize(205, 64);
        premiumBox.setMaxSize(205, 64);

        premiumBox.setPadding(
                new Insets(10, 14, 10, 14)
        );

        premiumBox.setAlignment(Pos.CENTER_LEFT);

        premiumBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: #EEE5E8;" +
                "-fx-border-radius: 17;" +
                "-fx-border-width: 1;"
        );

        premiumBox.setEffect(softShadow);

        VBox premiumText = new VBox(3);

        Label premiumTitle = new Label("Premium Shopper");

        premiumTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        Label premiumStatus = new Label(
                "●  AI Assistant Active"
        );

        premiumStatus.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        premiumText.getChildren().addAll(
                premiumTitle,
                premiumStatus
        );

        premiumBox.getChildren().add(
                premiumText
        );

        // =========================================================
        // SIDEBAR MENU
        // =========================================================
        VBox menuBox = new VBox(5);

        HBox dashboardRow =
                createSidebarRow("▦", "Dashboard");

        HBox nearbyRow =
                createSidebarRow("⌂", "Nearby Shops");

        HBox dealsRow =
                createSidebarRow("♢", "Deals");

        HBox ordersRow =
                createSidebarRow("□", "My Orders");

        HBox analyticsRow =
                createSidebarRow("⌁", "Analytics");

        // ---------------------------------------------------------
        // NEARBY SHOPS NAVIGATION
        // ---------------------------------------------------------
        nearbyRow.setOnMouseClicked(e -> {

            neaby_shope ns =
                    new neaby_shope(userId);

            Runnable callback =
                    this::backToElectronics;

            Homepage.HomepageStage.setScene(
                    ns.getNearby_shopes(callback)
            );
        });

        menuBox.getChildren().addAll(
                dashboardRow,
                nearbyRow,
                dealsRow,
                ordersRow,
                analyticsRow
        );

        // =========================================================
        // SIDEBAR SPACER
        // =========================================================
        Region sidebarSpacer =
                new Region();

        VBox.setVgrow(
                sidebarSpacer,
                Priority.ALWAYS
        );

        // =========================================================
        // GOLD UPGRADE CARD
        // =========================================================
        VBox upgradeCard =
                new VBox(8);

        upgradeCard.setPrefSize(205, 118);
        upgradeCard.setMinSize(205, 118);
        upgradeCard.setMaxSize(205, 118);

        upgradeCard.setPadding(
                new Insets(15)
        );

        upgradeCard.setAlignment(
                Pos.CENTER_LEFT
        );

        LinearGradient upgradeGradient =
                new LinearGradient(
                        0,
                        0,
                        1,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#242529")
                        ),
                        new Stop(
                                1,
                                Color.web("#45474D")
                        )
                );

        upgradeCard.setBackground(
                new Background(
                        new BackgroundFill(
                                upgradeGradient,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        Label upgradeTitle =
                new Label("Unlock Gold");

        upgradeTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: white;"
        );

        Label upgradeText =
                new Label(
                        "Smarter deals & exclusive rewards"
                );

        upgradeText.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #C6C6C9;"
        );

        Button upgradeButton =
                new Button("Upgrade to Gold");

        upgradeButton.setMaxWidth(
                Double.MAX_VALUE
        );

        upgradeButton.setPrefHeight(31);

        upgradeButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FF995A);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        upgradeCard.getChildren().addAll(
                upgradeTitle,
                upgradeText,
                upgradeButton
        );

        // SETTINGS
        HBox settingsRow =
                createSmallSidebarRow(
                        "⚙",
                        "Settings"
                );

        settingsRow.setOnMouseClicked(e -> {

            Seting se =
                    new Seting(userId);

            Runnable callback =
                    this::backToElectronics;

            Homepage.HomepageStage.setScene(
                    se.getSetingscene(callback)
            );
        });

        // HELP
        HBox helpRow =
                createSmallSidebarRow(
                        "?",
                        "Help & Support"
                );

        leftBox.getChildren().addAll(
                logo,
                premiumBox,
                menuBox,
                sidebarSpacer,
                upgradeCard,
                settingsRow,
                helpRow
        );

        // =========================================================
        // MAIN CONTENT
        // =========================================================
        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(0, 28, 35, 28)
        );

        content.setStyle(
                "-fx-background-color: " + PAGE_BG + ";"
        );

        // =========================================================
        // TOP NAVIGATION
        // =========================================================
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

        searchBox.setPrefWidth(340);
        searchBox.setMinWidth(250);
        searchBox.setPrefHeight(40);

        searchBox.setStyle(
                "-fx-background-color: #FAF9FB;" +
                "-fx-background-radius: 21;" +
                "-fx-border-color: #E5DDE4;" +
                "-fx-border-radius: 21;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0 17 0 17;" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #333333;" +
                "-fx-prompt-text-fill: #9A969C;"
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

        // ACTION BUTTONS
        HBox actions =
                new HBox(8);

        actions.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button notificationButton =
                createActionButton("♧");

        Button cartButton =
                createActionButton("🛒");

        Button profileButton =
                createActionButton("●");

        // CART NAVIGATION
        cartButton.setOnAction(e -> {

            ShoppingCartUI sui =
                    new ShoppingCartUI(userId);

            Homepage.HomepageStage.setScene(
                    sui.getaddcartScene()
            );
        });

        actions.getChildren().addAll(
                notificationButton,
                cartButton,
                profileButton
        );

        navBox.getChildren().addAll(
                topLinks,
                navSpacer1,
                searchBox,
                navSpacer2,
                locationBox,
                actions
        );

        // =========================================================
        // HERO
        // =========================================================
        HBox hero =
                new HBox(28);

        hero.setPrefHeight(280);
        hero.setMinHeight(280);

        hero.setPadding(
                new Insets(30, 34, 30, 34)
        );

        hero.setAlignment(
                Pos.CENTER_LEFT
        );

        LinearGradient heroGradient =
                new LinearGradient(
                        0,
                        0,
                        1,
                        0,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#242529")
                        ),
                        new Stop(
                                0.58,
                                Color.web("#303136")
                        ),
                        new Stop(
                                1,
                                Color.web("#50372B")
                        )
                );

        hero.setBackground(
                new Background(
                        new BackgroundFill(
                                heroGradient,
                                new CornerRadii(24),
                                Insets.EMPTY
                        )
                )
        );

        hero.setEffect(cardShadow);

        // HERO LEFT
        VBox heroLeft =
                new VBox(12);

        heroLeft.setPrefWidth(560);

        heroLeft.setAlignment(
                Pos.CENTER_LEFT
        );

        Label badge =
                new Label(
                        "✦  NEXT-GEN INTELLIGENCE"
                );

        badge.setStyle(
                "-fx-background-color: #503629;" +
                "-fx-text-fill: #FF9D67;" +
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-padding: 7 13 7 13;"
        );

        Text heroTitle =
                new Text(
                        "What are you\nlooking for today?"
                );

        heroTitle.setStyle(
                "-fx-fill: white;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 29px;" +
                "-fx-font-weight: 900;"
        );

        Text heroDescription =
                new Text(
                        "Your hyper-local AI is ready to source, compare,\n" +
                        "and deliver from your favorite downtown spots."
                );

        heroDescription.setStyle(
                "-fx-fill: #C8C8CA;" +
                "-fx-font-size: 11px;"
        );

        HBox heroButtons =
                new HBox(10);

        Button combineButton =
                new Button(
                        "♧  Combine Groceries"
                );

        combineButton.setPrefHeight(41);

        combineButton.setStyle(
                "-fx-background-color: #45464B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 16 10 16;" +
                "-fx-cursor: hand;"
        );

        Button bestPriceButton =
                new Button(
                        "⌁  Find Best Price"
                );

        bestPriceButton.setPrefHeight(41);

        bestPriceButton.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 18 10 18;" +
                "-fx-cursor: hand;"
        );

        heroButtons.getChildren().addAll(
                combineButton,
                bestPriceButton
        );

        heroLeft.getChildren().addAll(
                badge,
                heroTitle,
                heroDescription,
                heroButtons
        );

        // =========================================================
        // AI RECOMMENDATION CARD
        // =========================================================
        VBox aiCard =
                new VBox(10);

        aiCard.setPrefWidth(300);
        aiCard.setMinWidth(280);
        aiCard.setMaxWidth(310);

        aiCard.setPrefHeight(220);

        aiCard.setPadding(
                new Insets(19)
        );

        aiCard.setAlignment(
                Pos.TOP_LEFT
        );

        aiCard.setStyle(
                "-fx-background-color: #F0EEED;" +
                "-fx-background-radius: 19;" +
                "-fx-border-color: rgba(255,255,255,0.40);" +
                "-fx-border-radius: 19;" +
                "-fx-border-width: 1;"
        );

        HBox aiHeader =
                new HBox(10);

        aiHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane aiIconCircle =
                new StackPane();

        Circle circle =
                new Circle(
                        19,
                        Color.web(ORANGE)
                );

        Label star =
                new Label("✦");

        star.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        aiIconCircle.getChildren().addAll(
                circle,
                star
        );

        VBox aiHeading =
                new VBox(2);

        Label aiTitle =
                new Label(
                        "AI Recommendation"
                );

        aiTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #303030;"
        );

        Label aiStatus =
                new Label(
                        "PERSONALIZED FOR YOU"
                );

        aiStatus.setStyle(
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        aiHeading.getChildren().addAll(
                aiTitle,
                aiStatus
        );

        aiHeader.getChildren().addAll(
                aiIconCircle,
                aiHeading
        );

        Text aiText =
                new Text(
                        "You usually order coffee beans on Tuesdays.\n\n" +
                        "Artisan Pantry has your favorite\n" +
                        "brand in stock today."
                );

        aiText.setStyle(
                "-fx-fill: #444444;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Region aiSpacer =
                new Region();

        VBox.setVgrow(
                aiSpacer,
                Priority.ALWAYS
        );

        HBox aiButtons =
                new HBox(8);

        aiButtons.setAlignment(
                Pos.CENTER_RIGHT
        );

        Button dismiss =
                new Button("Dismiss");

        dismiss.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 9px;" +
                "-fx-cursor: hand;"
        );

        dismiss.setOnAction(
                e -> aiCard.setVisible(false)
        );

        Button checkStock =
                new Button("Check Stock");

        checkStock.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8 13 8 13;" +
                "-fx-cursor: hand;"
        );

        aiButtons.getChildren().addAll(
                dismiss,
                checkStock
        );

        aiCard.getChildren().addAll(
                aiHeader,
                aiText,
                aiSpacer,
                aiButtons
        );

        Region heroSpacer =
                new Region();

        HBox.setHgrow(
                heroSpacer,
                Priority.ALWAYS
        );

        hero.getChildren().addAll(
                heroLeft,
                heroSpacer,
                aiCard
        );

        // =========================================================
        // CATEGORY SECTION
        // =========================================================
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
                            "-fx-font-size: 1px;" +
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
                            "-fx-font-size: 14px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 15;" +
                            "-fx-border-color: " + BORDER + ";" +
                            "-fx-border-radius: 15;" +
                            "-fx-border-width: 1;" +
                            "-fx-cursor: hand;"
                    )
            );

            categories.getChildren().add(
                    categoryButton
            );
        }

        categorySection.getChildren().addAll(
                categoryHeader,
                categories
        );

        // =========================================================
        // MAIN PRODUCTS LAYOUT
        // =========================================================
        HBox mainLayout =
                new HBox(22);

        mainLayout.setAlignment(
                Pos.TOP_LEFT
        );

        // =========================================================
        // FILTERS
        // =========================================================
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
                        "$10  —  $2,000+"
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

        // =========================================================
        // PRODUCTS CONTENT
        // =========================================================
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
                new Label("Electronics");

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
                        "12 products"
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

        // =========================================================
        // PROMOTION BANNER
        // =========================================================
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

        // =========================================================
        // PRODUCT GRID
        // =========================================================
        FlowPane productGrid =
                new FlowPane();

        productGrid.setHgap(15);
        productGrid.setVgap(15);

        productGrid.setAlignment(
                Pos.TOP_LEFT
        );

        /*
         * IMPORTANT:
         * FlowPane is used instead of HBox.
         * Cards will automatically move to the next row
         * instead of getting cut off.
         */
        productGrid.setPrefWrapLength(
                850
        );

        List<String> cartItems =
                new ArrayList<>();

        // CARD 1
        VBox product1 =
                createProductCard(
                        "MacBook Pro M3",
                        "Apple",
                        "$1,999",
                        "Premium Laptop",
                        "macbook.png",
                        "💻",
                        "★★★★★  4.9",
                        cartItems,
                        cartButton
                );

        // CARD 2
        VBox product2 =
                createProductCard(
                        "Galaxy S24 Ultra",
                        "Samsung  •  5% OFF",
                        "$1,199",
                        "Flagship Smartphone",
                        "s24.png",
                        "📱",
                        "★★★★★  4.8",
                        cartItems,
                        cartButton
                );

        // CARD 3
        VBox product3 =
                createProductCard(
                        "WH-1000XM5",
                        "Sony",
                        "$349",
                        "Noise Cancelling Audio",
                        "sony-headphones.png",
                        "🎧",
                        "★★★★★  4.7",
                        cartItems,
                        cartButton
                );

        productGrid.getChildren().addAll(
                product1,
                product2,
                product3
        );

        productsContent.getChildren().addAll(
                productHeading,
                promotion,
                productGrid
        );

        mainLayout.getChildren().addAll(
                filtersBox,
                productsContent
        );

        // =========================================================
        // ADD EVERYTHING TO CONTENT
        // =========================================================
        content.getChildren().addAll(
                navBox,
                hero,
                categorySection,
                mainLayout
        );

        // =========================================================
        // BACKGROUND GLOW
        // =========================================================
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

        // =========================================================
        // SCROLL PANE
        // =========================================================
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

        // =========================================================
        // ROOT BORDERPANE
        // =========================================================
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

        // =========================================================
        // SCENE
        // =========================================================
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

    // =============================================================
    // PREMIUM PRODUCT CARD
    // =============================================================
    // =============================================================
    // PREMIUM PRODUCT CARD
    // =============================================================
    private VBox createProductCard(
            String productName,
            String brand,
            String price,
            String category,
            String imageName,
            String fallbackIcon,
            String rating,
            List<String> cartItems,
            Button cartButton
    ) {

        VBox card = new VBox(9);

        card.setPrefWidth(270);
        card.setMinWidth(270);
        card.setMaxWidth(270);

        card.setPrefHeight(365);
        card.setMinHeight(365);
        card.setMaxHeight(365);

        card.setPadding(new Insets(12));

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;"
        );

        DropShadow shadow = new DropShadow();
        shadow.setRadius(13);
        shadow.setOffsetY(5);
        shadow.setColor(Color.rgb(0, 0, 0, 0.08));

        card.setEffect(shadow);

        // =========================================================
        // HOVER
        // =========================================================
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

        // =========================================================
        // IMAGE BOX
        // =========================================================
        StackPane imageBox = new StackPane();

        imageBox.setPrefSize(246, 165);
        imageBox.setMinSize(246, 165);
        imageBox.setMaxSize(246, 165);
        imageBox.setAlignment(Pos.CENTER);

        imageBox.setStyle(
                "-fx-background-color: #F8F7F8;" +
                "-fx-background-radius: 14;"
        );

        // CATEGORY BADGE
        Label categoryBadge = new Label(category);

        categoryBadge.setStyle(
                "-fx-background-color: " + ORANGE_LIGHT + ";" +
                "-fx-text-fill: " + ORANGE_DARK + ";" +
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 5 8 5 8;"
        );

        StackPane.setAlignment(categoryBadge, Pos.TOP_LEFT);
        StackPane.setMargin(categoryBadge, new Insets(9, 0, 0, 9));

        // PRODUCT IMAGE
        URL imageUrl = getClass().getResource(
                "/assets/images/Electronics/" + imageName
        );

        if (imageUrl != null) {

            ImageView imageView = new ImageView(
                    new Image(imageUrl.toExternalForm())
            );

            imageView.setFitWidth(205);
            imageView.setFitHeight(140);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            imageBox.getChildren().add(imageView);

        } else {

            Label fallback = new Label(fallbackIcon);
            fallback.setStyle("-fx-font-size: 70px;");

            imageBox.getChildren().add(fallback);
        }

        imageBox.getChildren().add(categoryBadge);

        // =========================================================
        // PRODUCT NAME
        // =========================================================
        Label name = new Label(productName);

        name.setWrapText(true);
        name.setMaxWidth(245);

        name.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        // =========================================================
        // BRAND + RATING
        // =========================================================
        HBox meta = new HBox(6);
        meta.setAlignment(Pos.CENTER_LEFT);

        Label brandLabel = new Label(brand);

        brandLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region metaSpacer = new Region();
        HBox.setHgrow(metaSpacer, Priority.ALWAYS);

        Label ratingLabel = new Label(rating);

        ratingLabel.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        meta.getChildren().addAll(
                brandLabel,
                metaSpacer,
                ratingLabel
        );

        // =========================================================
        // BOTTOM SECTION
        // =========================================================
        VBox priceBox = new VBox(1);

        Label priceLabel = new Label(price);

        priceLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label delivery = new Label("● Fast delivery");

        delivery.setStyle(
                "-fx-font-size: 7px;" +
                "-fx-text-fill: #8B888D;"
        );

        priceBox.getChildren().addAll(
                priceLabel,
                delivery
        );

        // =========================================================
        // QUANTITY
        // =========================================================
        int stockQuantity = 10;

        Label quantityLabel = new Label("1");

        quantityLabel.setPrefWidth(30);
        quantityLabel.setAlignment(Pos.CENTER);

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

        Button minusButton = new Button("-");
        minusButton.setStyle(quantityButtonStyle);

        Button plusButton = new Button("+");
        plusButton.setStyle(quantityButtonStyle);

        HBox quantityBox = new HBox(
                6,
                minusButton,
                quantityLabel,
                plusButton
        );

        quantityBox.setAlignment(Pos.CENTER);

        minusButton.setOnAction(e -> {

            int quantity = Integer.parseInt(quantityLabel.getText());

            if (quantity > 1) {
                quantity--;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });

        plusButton.setOnAction(e -> {

            int quantity = Integer.parseInt(quantityLabel.getText());

            if (quantity < stockQuantity) {
                quantity++;
                quantityLabel.setText(String.valueOf(quantity));
            }
        });

        // =========================================================
        // ADD TO CART
        // =========================================================
        Button addButton = new Button("+ Add");

        addButton.setPrefSize(75, 32);
        addButton.setMinSize(75, 32);

        addButton.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 16;" +
                "-fx-cursor: hand;"
        );

        addButton.setOnAction(e -> {

            try {

                int quantity =
                        Integer.parseInt(quantityLabel.getText());

                // "$1,999" -> 1999.0
                double priceValue =
                        Double.parseDouble(
                                price.replace("$", "")
                                      .replace(",", "")
                                      .trim()
                        );

                CARTcontroller cl =
                        new CARTcontroller();

                // Electronics page ke liye correct shop/category
                cl.addTocart(
                        userId,
                        productName,
                        priceValue,
                        "Electronics",
                        quantity
                );

                // Cart counter update
                cartItems.add(
                        productName + " x " + quantity
                );

                cartButton.setText(
                        "🛒 " + cartItems.size()
                );

                addButton.setText("✓ Added");

                addButton.setStyle(
                        "-fx-background-color: #2E9B57;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 9px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 16;" +
                        "-fx-cursor: hand;"
                );

            } catch (NumberFormatException ex) {

                System.out.println(
                        "Invalid product price: " + price
                );

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =========================================================
        // BOTTOM ROW
        // =========================================================
        HBox bottom = new HBox(7);

        bottom.setAlignment(Pos.CENTER_LEFT);

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        bottom.getChildren().addAll(
                priceBox,
                bottomSpacer,
                quantityBox,
                addButton
        );

        // =========================================================
        // FINAL CARD
        // =========================================================
        card.getChildren().addAll(
                imageBox,
                name,
                meta,
                bottom
        );

        return card;
    }


    // =============================================================
    // SIDEBAR ROW
    // =============================================================
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

    // =============================================================
    // SMALL SIDEBAR ROW
    // =============================================================
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

    // =============================================================
    // FILTER HEADING
    // =============================================================
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

    // =============================================================
    // FILTER ITEM
    // =============================================================
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

    // =============================================================
    // TOP BUTTON
    // =============================================================
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

    // =============================================================
    // ACTION BUTTON
    // =============================================================
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

    // =============================================================
    // BACK TO ELECTRONICS
    // =============================================================
    public void backToElectronics() {

        if (Electrinicscene != null) {

            Homepage.HomepageStage.setScene(
                    Electrinicscene
            );
        }
    }
}

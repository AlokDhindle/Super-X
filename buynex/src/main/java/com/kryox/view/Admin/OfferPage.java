package com.kryox.view.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kryox.controller.CampaignController;
import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.CampaignModel;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.view.Customer.Homepage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class OfferPage {

    private String activeFilter = "Marketwide";
    private final List<OfferModel> dbOffers = new ArrayList<>();
    private VBox cardsListVBox;
    private Text showingText;

    private HBox marketwideBox;
    private HBox shopSpecificBox;
    private HBox seasonalBox;
    private HBox aiSuggestedBox;
    private HBox campaignRequestsBox;

    private Text marketIcon;
    private Text marketText;
    private Text shopIcon;
    private Text shopText;
    private Text seasonalIcon;
    private Text seasonalText;
    private Text aiIcon;
    private Text aiText;
    private Text campaignIcon;
    private Text campaignText;
    private Label campaignBadge;

    public Scene getUserScene() {
        VBox left = new VBox();
        left.setPrefWidth(210);
        left.setSpacing(28);
        left.setPadding(new Insets(30, 15, 20, 15));
        left.setStyle("-fx-background-color: #ebccb7;");

        Text logo = new Text("Admin Panel");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logo.setFill(Color.web("#A83E00"));

        Text controller = new Text("Marketplace Controller");
        controller.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        controller.setFill(Color.web("#999999"));

        VBox logoBox = new VBox(4, logo, controller);

        VBox menu = new VBox();
        menu.setSpacing(4);

        // Sidebar - Dashboard
        HBox dashboard = new HBox();
        dashboard.setSpacing(10);
        dashboard.setAlignment(Pos.CENTER_LEFT);
        dashboard.setPadding(new Insets(10, 12, 10, 12));
        dashboard.setPrefWidth(180);
        dashboard.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img1 = new Image(getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());
        ImageView iv1 = new ImageView(img1);
        iv1.setFitWidth(20);
        iv1.setFitHeight(20);
        iv1.setPreserveRatio(true);

        Text dashboardText = new Text("Dashboard");
        dashboardText.setFill(Color.web("#333333"));
        dashboardText.setFont(Font.font("Arial", 14));

        dashboard.getChildren().addAll(iv1, dashboardText);
        dashboard.setOnMouseEntered(e -> {
            dashboard.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            dashboardText.setFill(Color.WHITE);
            dashboardText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        dashboard.setOnMouseExited(e -> {
            dashboard.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            dashboardText.setFill(Color.web("#333333"));
            dashboardText.setFont(Font.font("Arial", 14));
        });
        dashboard.setOnMouseClicked(e -> {
            AdminDashboardPage dashboardPage = new AdminDashboardPage();
            Homepage.HomepageStage.setScene(dashboardPage.getUserScene());
        });

        // Sidebar - Users
        HBox users = new HBox();
        users.setSpacing(10);
        users.setAlignment(Pos.CENTER_LEFT);
        users.setPadding(new Insets(10, 12, 10, 12));
        users.setPrefWidth(180);
        users.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img2 = new Image(getClass().getResource("/assets/images/admin/admin_logo.png").toExternalForm());
        ImageView iv2 = new ImageView(img2);
        iv2.setFitWidth(20);
        iv2.setFitHeight(20);
        iv2.setPreserveRatio(true);

        Text usersText = new Text("Users");
        usersText.setFill(Color.web("#333333"));
        usersText.setFont(Font.font("Arial", 14));

        users.getChildren().addAll(iv2, usersText);
        users.setOnMouseEntered(e -> {
            users.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            usersText.setFill(Color.WHITE);
            usersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        users.setOnMouseExited(e -> {
            users.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            usersText.setFill(Color.web("#333333"));
            usersText.setFont(Font.font("Arial", 14));
        });
        users.setOnMouseClicked(e -> {
            UserManagementPage userPage = new UserManagementPage();
            Homepage.HomepageStage.setScene(userPage.getUserScene());
        });

        // Sidebar - Shops
        HBox shops = new HBox();
        shops.setSpacing(10);
        shops.setAlignment(Pos.CENTER_LEFT);
        shops.setPadding(new Insets(10, 12, 10, 12));
        shops.setPrefWidth(180);
        shops.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img3 = new Image(getClass().getResource("/assets/images/admin/shop.png").toExternalForm());
        ImageView iv3 = new ImageView(img3);
        iv3.setFitWidth(20);
        iv3.setFitHeight(20);
        iv3.setPreserveRatio(true);

        Text shopsText = new Text("Shops");
        shopsText.setFill(Color.web("#333333"));
        shopsText.setFont(Font.font("Arial", 14));

        shops.getChildren().addAll(iv3, shopsText);
        shops.setOnMouseEntered(e -> {
            shops.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            shopsText.setFill(Color.WHITE);
            shopsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        shops.setOnMouseExited(e -> {
            shops.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            shopsText.setFill(Color.web("#333333"));
            shopsText.setFont(Font.font("Arial", 14));
        });
        shops.setOnMouseClicked(e -> {
            ShopVerificationPage shopPage = new ShopVerificationPage();
            Homepage.HomepageStage.setScene(shopPage.getUserScene());
        });

        // Sidebar - Delivery
        HBox delivery = new HBox();
        delivery.setSpacing(10);
        delivery.setAlignment(Pos.CENTER_LEFT);
        delivery.setPadding(new Insets(10, 12, 10, 12));
        delivery.setPrefWidth(180);
        delivery.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Text deliveryIcon = new Text("🚚");
        deliveryIcon.setFont(Font.font("Arial", 18));
        Text deliveryText = new Text("Delivery");
        deliveryText.setFill(Color.web("#333333"));
        deliveryText.setFont(Font.font("Arial", 14));

        delivery.getChildren().addAll(deliveryIcon, deliveryText);
        delivery.setOnMouseEntered(e -> {
            delivery.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            deliveryText.setFill(Color.WHITE);
            deliveryText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        delivery.setOnMouseExited(e -> {
            delivery.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            deliveryText.setFill(Color.web("#333333"));
            deliveryText.setFont(Font.font("Arial", 14));
        });
        delivery.setOnMouseClicked(e -> {
            DeliveryVerificationPage deliveryPage = new DeliveryVerificationPage();
            Homepage.HomepageStage.setScene(deliveryPage.getUserScene());
        });

        // Sidebar - Offers (ACTIVE PAGE)
        HBox offers = new HBox();
        offers.setSpacing(10);
        offers.setAlignment(Pos.CENTER_LEFT);
        offers.setPadding(new Insets(10, 12, 10, 12));
        offers.setPrefWidth(180);
        offers.setStyle("-fx-background-color:#FF6500;-fx-background-radius:10;");

        Image img4 = new Image(getClass().getResource("/assets/images/admin/tag.png").toExternalForm());
        ImageView iv4 = new ImageView(img4);
        iv4.setFitWidth(20);
        iv4.setFitHeight(20);
        iv4.setPreserveRatio(true);

        Text offersText = new Text("Offers");
        offersText.setFill(Color.WHITE);
        offersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        offers.getChildren().addAll(iv4, offersText);

        // Sidebar - Analytics
        HBox analytics = new HBox();
        analytics.setSpacing(10);
        analytics.setAlignment(Pos.CENTER_LEFT);
        analytics.setPadding(new Insets(10, 12, 10, 12));
        analytics.setPrefWidth(180);
        analytics.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img5 = new Image(getClass().getResource("/assets/images/admin/stats.png").toExternalForm());
        ImageView iv5 = new ImageView(img5);
        iv5.setFitWidth(20);
        iv5.setFitHeight(20);
        iv5.setPreserveRatio(true);

        Text analyticsText = new Text("Analytics");
        analyticsText.setFill(Color.web("#333333"));
        analyticsText.setFont(Font.font("Arial", 14));

        analytics.getChildren().addAll(iv5, analyticsText);
        analytics.setOnMouseEntered(e -> {
            analytics.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            analyticsText.setFill(Color.WHITE);
            analyticsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        analytics.setOnMouseExited(e -> {
            analytics.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            analyticsText.setFill(Color.web("#333333"));
            analyticsText.setFont(Font.font("Arial", 14));
        });
        analytics.setOnMouseClicked(e -> {
            AdminAnalyticsPage analyticsPage = new AdminAnalyticsPage();
            Homepage.HomepageStage.setScene(analyticsPage.getUserScene());
        });

        menu.getChildren().addAll(dashboard, users, shops, delivery, offers, analytics);

        VBox bottomMenu = new VBox();
        bottomMenu.setSpacing(4);

        HBox settings = new HBox();
        settings.setSpacing(10);
        settings.setAlignment(Pos.CENTER_LEFT);
        settings.setPadding(new Insets(10, 12, 10, 12));
        settings.setPrefWidth(180);
        settings.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img6 = new Image(getClass().getResource("/assets/images/admin/setting.png").toExternalForm());
        ImageView iv6 = new ImageView(img6);
        iv6.setFitWidth(20);
        iv6.setFitHeight(20);
        iv6.setPreserveRatio(true);

        Text settingsText = new Text("Settings");
        settingsText.setFill(Color.web("#333333"));
        settingsText.setFont(Font.font("Arial", 14));

        settings.getChildren().addAll(iv6, settingsText);
        settings.setOnMouseEntered(e -> {
            settings.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            settingsText.setFill(Color.WHITE);
            settingsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        settings.setOnMouseExited(e -> {
            settings.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            settingsText.setFill(Color.web("#333333"));
            settingsText.setFont(Font.font("Arial", 14));
        });
        settings.setOnMouseClicked(e -> {
            SettingsPage userPage = new SettingsPage();
            Homepage.HomepageStage.setScene(userPage.getUserScene());
        });

        HBox support = new HBox();
        support.setSpacing(10);
        support.setAlignment(Pos.CENTER_LEFT);
        support.setPadding(new Insets(10, 12, 10, 12));
        support.setPrefWidth(180);
        support.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");

        Image img7 = new Image(getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
        ImageView iv7 = new ImageView(img7);
        iv7.setFitWidth(20);
        iv7.setFitHeight(20);
        iv7.setPreserveRatio(true);

        Text supportText = new Text("Support");
        supportText.setFill(Color.web("#333333"));
        supportText.setFont(Font.font("Arial", 14));

        support.getChildren().addAll(iv7, supportText);
        support.setOnMouseEntered(e -> {
            support.setStyle("-fx-background-color:#D94F00;-fx-background-radius:10;");
            supportText.setFill(Color.WHITE);
            supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        });
        support.setOnMouseExited(e -> {
            support.setStyle("-fx-background-color:transparent;-fx-background-radius:10;");
            supportText.setFill(Color.web("#333333"));
            supportText.setFont(Font.font("Arial", 14));
        });
        support.setOnMouseClicked(e -> {
            SupportPage userPage = new SupportPage();
            Homepage.HomepageStage.setScene(userPage.getUserScene());
        });

        bottomMenu.getChildren().addAll(settings, support);

        AdminProfileCard adminProfileCard = new AdminProfileCard();
        HBox profile = adminProfileCard.getProfileCard();

        Region leftGrow = new Region();
        VBox.setVgrow(leftGrow, Priority.ALWAYS);

        left.getChildren().addAll(logoBox, menu, new Separator(), bottomMenu, leftGrow, profile);

        VBox centerContent = new VBox();
        centerContent.setSpacing(20);
        centerContent.setPadding(new Insets(30));
        centerContent.setStyle("-fx-background-color: #eee5df;");

        Text pageTitle = new Text("Offers & Promotions");
        pageTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 32));

        Text pageSubtitle = new Text("Manage network-wide campaigns and monitor shop performance.");
        pageSubtitle.setFont(Font.font("Georgia", 16));
        pageSubtitle.setFill(Color.web("#777777"));

        VBox titleBox = new VBox(6, pageTitle, pageSubtitle);
        Region titleGrow = new Region();
        HBox.setHgrow(titleGrow, Priority.ALWAYS);

        HBox topActions = createTopActions();
        HBox pageTop = new HBox(titleBox, titleGrow, topActions);
        pageTop.setAlignment(Pos.CENTER_LEFT);

        // MAIN CONTENT (Filter box + Promotions + AI Panel)
        HBox mainContent = new HBox();
        mainContent.setSpacing(25);
        mainContent.setPrefHeight(670);
        mainContent.setMinHeight(670);

        // LEFT FILTER BOX (4 Interactive Sub-Tab Buttons)
        VBox filterBox = new VBox();
        filterBox.setSpacing(10);
        filterBox.setPadding(new Insets(10));
        filterBox.setPrefWidth(175);
        filterBox.setPrefHeight(670);
        filterBox.setMinHeight(670);
        filterBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#E4C7B7;" +
                "-fx-border-radius:14;"
        );

        // 1. Marketwide Tab
        marketwideBox = new HBox(10);
        marketwideBox.setAlignment(Pos.CENTER_LEFT);
        marketwideBox.setPadding(new Insets(10));
        marketwideBox.setStyle("-fx-cursor:hand;");

        marketIcon = new Text("◉");
        marketIcon.setFont(Font.font("Georgia", 16));

        marketText = new Text("Marketwide");
        marketText.setFont(Font.font("Georgia", 15));

        marketwideBox.getChildren().addAll(marketIcon, marketText);

        // 2. Shop-Specific Tab
        shopSpecificBox = new HBox(10);
        shopSpecificBox.setAlignment(Pos.CENTER_LEFT);
        shopSpecificBox.setPadding(new Insets(10));
        shopSpecificBox.setStyle("-fx-cursor:hand;");

        shopIcon = new Text("▣");
        shopIcon.setFont(Font.font("Georgia", 16));

        shopText = new Text("Shop-\nSpecific");
        shopText.setFont(Font.font("Georgia", 14));

        shopSpecificBox.getChildren().addAll(shopIcon, shopText);

        // 3. Seasonal Tab
        seasonalBox = new HBox(10);
        seasonalBox.setAlignment(Pos.CENTER_LEFT);
        seasonalBox.setPadding(new Insets(10));
        seasonalBox.setStyle("-fx-cursor:hand;");

        seasonalIcon = new Text("□");
        seasonalIcon.setFont(Font.font("Georgia", 16));

        seasonalText = new Text("Seasonal");
        seasonalText.setFont(Font.font("Georgia", 14));

        seasonalBox.getChildren().addAll(seasonalIcon, seasonalText);

        // 4. AI-Suggested Tab
        aiSuggestedBox = new HBox(10);
        aiSuggestedBox.setAlignment(Pos.CENTER_LEFT);
        aiSuggestedBox.setPadding(new Insets(10));
        aiSuggestedBox.setStyle("-fx-cursor:hand;");

        aiIcon = new Text("✣");
        aiIcon.setFont(Font.font("Georgia", 16));

        aiText = new Text("AI-Suggested");
        aiText.setFont(Font.font("Georgia", 14));

        aiSuggestedBox.getChildren().addAll(aiIcon, aiText);

        // 5. Campaign Requests Tab (Shopkeeper submissions for Admin review)
        campaignRequestsBox = new HBox(8);
        campaignRequestsBox.setAlignment(Pos.CENTER_LEFT);
        campaignRequestsBox.setPadding(new Insets(10));
        campaignRequestsBox.setStyle("-fx-cursor:hand;");

        campaignIcon = new Text("📢");
        campaignIcon.setFont(Font.font("Georgia", 16));

        campaignText = new Text("Campaign\nRequests");
        campaignText.setFont(Font.font("Georgia", 13));

        campaignBadge = new Label("🔴 " + CampaignController.getPendingCount());
        campaignBadge.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #D32F2F; -fx-background-color: #FFEBEE; -fx-background-radius: 8px; -fx-padding: 2 6 2 6;");

        campaignRequestsBox.getChildren().addAll(campaignIcon, campaignText, campaignBadge);

        filterBox.getChildren().addAll(marketwideBox, shopSpecificBox, seasonalBox, aiSuggestedBox, campaignRequestsBox);

        // CENTER PROMOTIONS AREA
        VBox promotionsArea = new VBox();
        promotionsArea.setSpacing(18);
        promotionsArea.setPrefWidth(630);
        promotionsArea.setPrefHeight(670);
        promotionsArea.setMinHeight(670);

        // Header with "+ Create Global Campaign" button
        HBox campaignHeader = new HBox();
        campaignHeader.setAlignment(Pos.CENTER_LEFT);
        campaignHeader.setPadding(new Insets(18));
        campaignHeader.setPrefHeight(90);
        campaignHeader.setMinHeight(90);
        campaignHeader.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#E4C7B7;" +
                "-fx-border-radius:14;"
        );

        showingText = new Text("Showing Active Marketwide promotions");
        showingText.setFont(Font.font("Georgia", 15));

        Region headerGrow = new Region();
        HBox.setHgrow(headerGrow, Priority.ALWAYS);

        Button createCampaign = new Button("+  Create Global Campaign");
        createCampaign.setPrefHeight(44);
        createCampaign.setStyle(
                "-fx-background-color:#B84300;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;" +
                "-fx-background-radius:8;" +
                "-fx-cursor:hand;"
        );
        createCampaign.setOnAction(event -> {
            CreateCampaignPage campaign = new CreateCampaignPage();
            Homepage.HomepageStage.setScene(campaign.getCampaignScene());
        });

        campaignHeader.getChildren().addAll(showingText, headerGrow, createCampaign);

        // Scrollable list of cards
        cardsListVBox = new VBox(18);
        cardsListVBox.setPadding(new Insets(2));

        ScrollPane scrollCards = new ScrollPane(cardsListVBox);
        scrollCards.setFitToWidth(true);
        scrollCards.setPrefHeight(560);
        scrollCards.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background:transparent;" +
                "-fx-border-color:transparent;"
        );

        promotionsArea.getChildren().addAll(campaignHeader, scrollCards);

        // RIGHT AI PROMOTION PANEL
        VBox aiPromotion = new VBox();
        aiPromotion.setSpacing(15);
        aiPromotion.setPadding(new Insets(20));
        aiPromotion.setPrefWidth(275);
        aiPromotion.setPrefHeight(670);
        aiPromotion.setMinHeight(670);
        aiPromotion.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#B84300;" +
                "-fx-border-width:2;" +
                "-fx-border-radius:14;"
        );

        Text aiPromotionTitle = new Text("✣  AI Promotion\n    Engine");
        aiPromotionTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 20));

        Text aiDescription = new Text("Real-time insights to optimize\nnetwork GMV.");
        aiDescription.setFont(Font.font("Georgia", 14));
        aiDescription.setFill(Color.web("#666666"));

        Text needsBoost = new Text("NEEDS BOOST (TRENDING\nDOWN)");
        needsBoost.setFont(Font.font("Georgia", FontWeight.BOLD, 12));

        HBox chip1 = createChip("Winter Apparel");
        HBox chip2 = createChip("Home Goods");
        HBox chip3 = createChip("Pet Supplies");

        HBox generateIdeasBox = new HBox();
        Text generateIdeas = new Text("Generate Campaign Ideas  →");
        generateIdeas.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        generateIdeas.setFill(Color.web("#8E3D18"));
        generateIdeasBox.getChildren().add(generateIdeas);
        generateIdeasBox.setStyle("-fx-cursor:hand;");
        generateIdeasBox.setOnMouseClicked(e -> {
            CreateCampaignPage campaign = new CreateCampaignPage();
            Homepage.HomepageStage.setScene(campaign.getCampaignScene());
        });

        Separator aiSeparator = new Separator();

        Text predicted = new Text("PREDICTED ROI (ACTIVE)");
        predicted.setFont(Font.font("Georgia", FontWeight.BOLD, 12));

        Text roi1 = new Text("Summer Tech Fest                         2.4x");
        roi1.setFont(Font.font("Georgia", 13));

        Text roi2 = new Text("Local Artisan Week                       1.8x");
        roi2.setFont(Font.font("Georgia", 13));

        aiPromotion.getChildren().addAll(
                aiPromotionTitle,
                aiDescription,
                needsBoost,
                chip1,
                chip2,
                chip3,
                generateIdeasBox,
                aiSeparator,
                predicted,
                roi1,
                roi2
        );

        mainContent.getChildren().addAll(filterBox, promotionsArea, aiPromotion);
        centerContent.getChildren().addAll(pageTop, mainContent);

        BorderPane root = new BorderPane();
        root.setLeft(left);
        root.setCenter(centerContent);
        root.setStyle("-fx-background-color: #eee5df;");

        // Wire sub-tab click handlers
        marketwideBox.setOnMouseClicked(e -> selectTab("Marketwide"));
        shopSpecificBox.setOnMouseClicked(e -> selectTab("Shop-Specific"));
        seasonalBox.setOnMouseClicked(e -> selectTab("Seasonal"));
        aiSuggestedBox.setOnMouseClicked(e -> selectTab("AI-Suggested"));
        campaignRequestsBox.setOnMouseClicked(e -> selectTab("Campaign Requests"));

        // Initialize active tab visual state
        updateTabStyles();
        renderCardsForActiveTab();

        // Asynchronously load real offers from Firestore/OfferController
        new Thread(() -> {
            try {
                ArrayList<OfferModel> fetched = OfferController.getAllOffersForAdmin();
                if (fetched != null && !fetched.isEmpty()) {
                    Platform.runLater(() -> {
                        dbOffers.clear();
                        dbOffers.addAll(fetched);
                        renderCardsForActiveTab();
                    });
                }
            } catch (Exception ex) {
                System.out.println("Error fetching admin offers: " + ex.getMessage());
            }
        }).start();

        return new Scene(root, 1550, 850);
    }

    private HBox createChip(String label) {
        HBox chip = new HBox();
        chip.setPadding(new Insets(7, 12, 7, 12));
        chip.setStyle("-fx-background-color:#F1ECEF;-fx-background-radius:15;");
        Text t = new Text(label);
        t.setFont(Font.font("Georgia", 12));
        chip.getChildren().add(t);
        return chip;
    }

    private void selectTab(String tabName) {
        this.activeFilter = tabName;
        updateTabStyles();
        if ("Campaign Requests".equalsIgnoreCase(tabName)) {
            showingText.setText("Showing Shopkeeper Campaign Requests (Admin Review & Approvals)");
        } else {
            showingText.setText("Showing Active " + tabName + " promotions");
        }
        renderCardsForActiveTab();
    }

    private void updateTabStyles() {
        resetTabStyle(marketwideBox, marketIcon, marketText);
        resetTabStyle(shopSpecificBox, shopIcon, shopText);
        resetTabStyle(seasonalBox, seasonalIcon, seasonalText);
        resetTabStyle(aiSuggestedBox, aiIcon, aiText);
        if (campaignRequestsBox != null) {
            resetTabStyle(campaignRequestsBox, campaignIcon, campaignText);
        }

        if ("Marketwide".equalsIgnoreCase(activeFilter)) {
            setTabActive(marketwideBox, marketIcon, marketText);
        } else if ("Shop-Specific".equalsIgnoreCase(activeFilter)) {
            setTabActive(shopSpecificBox, shopIcon, shopText);
        } else if ("Seasonal".equalsIgnoreCase(activeFilter)) {
            setTabActive(seasonalBox, seasonalIcon, seasonalText);
        } else if ("AI-Suggested".equalsIgnoreCase(activeFilter)) {
            setTabActive(aiSuggestedBox, aiIcon, aiText);
        } else if ("Campaign Requests".equalsIgnoreCase(activeFilter) || "Campaign-Requests".equalsIgnoreCase(activeFilter)) {
            if (campaignRequestsBox != null) {
                setTabActive(campaignRequestsBox, campaignIcon, campaignText);
            }
        }
    }

    private void setTabActive(HBox box, Text icon, Text text) {
        box.setStyle(
                "-fx-background-color:#FFD9C5;" +
                "-fx-background-radius:9;" +
                "-fx-cursor:hand;"
        );
        icon.setFill(Color.web("#A83E00"));
        text.setFont(Font.font("Georgia", FontWeight.BOLD, 15));
        text.setFill(Color.web("#8E3D18"));
    }

    private void resetTabStyle(HBox box, Text icon, Text text) {
        box.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:9;" +
                "-fx-cursor:hand;"
        );
        icon.setFill(Color.web("#333333"));
        text.setFont(Font.font("Georgia", 14));
        text.setFill(Color.web("#333333"));
    }

    private void renderCardsForActiveTab() {
        cardsListVBox.getChildren().clear();

        // Update badge counter
        if (campaignBadge != null) {
            campaignBadge.setText("🔴 " + CampaignController.getPendingCount());
        }

        if ("Campaign Requests".equalsIgnoreCase(activeFilter) || "Campaign-Requests".equalsIgnoreCase(activeFilter)) {
            renderCampaignRequestsList();
            return;
        }

        List<OfferModel> matchingDbOffers = new ArrayList<>();
        for (OfferModel om : dbOffers) {
            String cat = om.getCategory() != null ? om.getCategory() : "";
            if (activeFilter.equalsIgnoreCase("Marketwide") && (cat.equalsIgnoreCase("General") || cat.equalsIgnoreCase("Marketwide") || cat.trim().isEmpty())) {
                matchingDbOffers.add(om);
            } else if (activeFilter.equalsIgnoreCase("Shop-Specific") && (cat.toLowerCase().contains("shop") || !cat.equalsIgnoreCase("General"))) {
                matchingDbOffers.add(om);
            } else if (activeFilter.equalsIgnoreCase("Seasonal") && (cat.toLowerCase().contains("season") || cat.toLowerCase().contains("fest"))) {
                matchingDbOffers.add(om);
            } else if (activeFilter.equalsIgnoreCase("AI-Suggested") && (cat.toLowerCase().contains("ai") || cat.toLowerCase().contains("smart"))) {
                matchingDbOffers.add(om);
            }
        }

        // 1. Render DB offers if present
        for (OfferModel om : matchingDbOffers) {
            String title = om.getOfferName() != null ? om.getOfferName() : "Promotional Offer";
            String desc = om.getDescription() != null ? om.getDescription() : "Special discount campaign for BuyNeX customers.";
            String status = om.getStatus() != null ? om.getStatus() : "Active";

            double views = om.getTotalViews();
            double red = om.getRedemptions();
            String convRate = (views > 0) ? String.format("%.1f%% ↗", (red * 100.0 / views)) : "4.2% ↗";
            String shopCount = om.getTotalUsageLimit() > 0 ? String.valueOf(om.getTotalUsageLimit()) : "120";
            String netLift = om.getNetProfit() > 0 ? String.format("+₹%.0fk", om.getNetProfit() / 1000.0) : "+₹35k";

            cardsListVBox.getChildren().add(createCampaignCard(om, title, status, desc, convRate, shopCount, netLift));
        }

        // 2. Render default tailored campaigns for each tab so page is always rich
        if ("Marketwide".equalsIgnoreCase(activeFilter)) {
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Summer Tech Fest",
                    "Active",
                    "15% off electronics network-wide to boost Q3 electronics slump.",
                    "4.2% ↗",
                    "142",
                    "+₹42k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Fresh Groceries Push",
                    "Paused",
                    "Free delivery on orders over ₹50 for fresh produce partners.",
                    "--",
                    "45",
                    "--"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Super-X Mega Marketplace Sale",
                    "Active",
                    "Flat 20% cashback across all categories for prime marketplace members.",
                    "6.8% ↗",
                    "280",
                    "+₹88k"
            ));
        } else if ("Shop-Specific".equalsIgnoreCase(activeFilter)) {
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Local Artisan Spotlight",
                    "Active",
                    "Exclusive 10% instant rebate for handcrafted & local vendor products.",
                    "5.1% ↗",
                    "34",
                    "+₹15k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Boutique Apparel Launch",
                    "Active",
                    "Targeted store vouchers generated for top-tier clothing shopkeepers.",
                    "3.9% ↗",
                    "22",
                    "+₹19k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Neighborhood Organic Produce",
                    "Paused",
                    "Store-specific discount codes for verified organic merchant partners.",
                    "--",
                    "15",
                    "--"
            ));
        } else if ("Seasonal".equalsIgnoreCase(activeFilter)) {
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Diwali & Festive Harvest Rush",
                    "Active",
                    "Seasonal festival coupon package up to 30% off on festive gift boxes & sweets.",
                    "8.4% ↗",
                    "310",
                    "+₹125k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Monsoon Shield Tech Bonanza",
                    "Active",
                    "Special seasonal warranty bundle + 12% off waterproof electronics.",
                    "4.6% ↗",
                    "95",
                    "+₹31k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Winter Collection Early Bird",
                    "Paused",
                    "Early winter apparel vouchers for registered loyalty customers.",
                    "--",
                    "68",
                    "--"
            ));
        } else if ("AI-Suggested".equalsIgnoreCase(activeFilter)) {
            cardsListVBox.getChildren().add(createCampaignCard(
                    "AI Dynamic Surge Savings",
                    "Active",
                    "Automated real-time price optimization during off-peak afternoon hours.",
                    "7.3% ↗",
                    "195",
                    "+₹64k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Smart Cross-Category Bundle",
                    "Active",
                    "AI recommendation engine linking grocery items with home appliances.",
                    "6.2% ↗",
                    "150",
                    "+₹48k"
            ));
            cardsListVBox.getChildren().add(createCampaignCard(
                    "Dormant User Winback AI",
                    "Active",
                    "Machine-learning targeted ₹10 voucher sent to users inactive > 30 days.",
                    "9.1% ↗",
                    "230",
                    "+₹92k"
            ));
        }
    }

    private void renderCampaignRequestsList() {
        cardsListVBox.getChildren().clear();
        List<CampaignModel> list = CampaignController.getAllCampaignRequests();

        // Sort so PENDING requests appear at the very top
        list.sort((a, b) -> {
            boolean aPend = "PENDING".equalsIgnoreCase(a.getStatus());
            boolean bPend = "PENDING".equalsIgnoreCase(b.getStatus());
            if (aPend && !bPend) return -1;
            if (!aPend && bPend) return 1;
            return 0;
        });

        int pendingCount = 0;
        for (CampaignModel c : list) {
            if ("PENDING".equalsIgnoreCase(c.getStatus())) {
                pendingCount++;
            }
        }
        if (campaignBadge != null) {
            campaignBadge.setText("🔴 " + pendingCount);
        }

        if (list.isEmpty()) {
            VBox empty = new VBox(10);
            empty.setAlignment(Pos.CENTER);
            empty.setPadding(new Insets(40));
            empty.setStyle("-fx-background-color:white;-fx-background-radius:14;-fx-border-color:#E4C7B7;-fx-border-radius:14;");
            Text noRequests = new Text("No campaign requests submitted yet.");
            noRequests.setFont(Font.font("Georgia", 16));
            noRequests.setFill(Color.GRAY);
            empty.getChildren().add(noRequests);
            cardsListVBox.getChildren().add(empty);
            return;
        }

        for (CampaignModel c : list) {
            cardsListVBox.getChildren().add(createCampaignRequestCard(c));
        }
    }

    private VBox createCampaignRequestCard(CampaignModel c) {
        VBox card = new VBox(14);
        card.setPadding(new Insets(20));
        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#E4C7B7;" +
                "-fx-border-radius:14;" +
                "-fx-effect:dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // Header: Badge + Campaign Title + Status
        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER_LEFT);

        Label newRequestTag = new Label("CAMPAIGN REQUEST");
        newRequestTag.setStyle("-fx-background-color:#FFF0E6;-fx-text-fill:#C64A00;-fx-font-weight:bold;-fx-font-size:11px;-fx-padding:3 8 3 8;-fx-background-radius:6;");

        Text campaignTitle = new Text(c.getTitle() != null ? c.getTitle() : "Promotional Campaign");
        campaignTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        campaignTitle.setFill(Color.web("#1E1E24"));

        Region sp1 = new Region();
        HBox.setHgrow(sp1, Priority.ALWAYS);

        Label statusLabel = new Label();
        statusLabel.setPadding(new Insets(4, 12, 4, 12));
        statusLabel.setStyle("-fx-font-weight:bold;-fx-font-size:12px;-fx-background-radius:12;");

        boolean isPending = "PENDING".equalsIgnoreCase(c.getStatus());
        boolean isApproved = "APPROVED".equalsIgnoreCase(c.getStatus());
        boolean isRejected = "REJECTED".equalsIgnoreCase(c.getStatus());

        if (isApproved) {
            statusLabel.setText("✓ APPROVED");
            statusLabel.setStyle("-fx-background-color:#E8F5E9;-fx-text-fill:#2E7D32;-fx-font-weight:bold;-fx-font-size:12px;-fx-background-radius:12;");
        } else if (isRejected) {
            statusLabel.setText("✕ REJECTED");
            statusLabel.setStyle("-fx-background-color:#FFEBEE;-fx-text-fill:#C62828;-fx-font-weight:bold;-fx-font-size:12px;-fx-background-radius:12;");
        } else {
            statusLabel.setText("⏳ PENDING REVIEW");
            statusLabel.setStyle("-fx-background-color:#FFF8E1;-fx-text-fill:#F57F17;-fx-font-weight:bold;-fx-font-size:12px;-fx-background-radius:12;");
        }

        topRow.getChildren().addAll(newRequestTag, campaignTitle, sp1, statusLabel);

        // Shopkeeper & Store Details
        HBox shopInfoRow = new HBox(20);
        shopInfoRow.setAlignment(Pos.CENTER_LEFT);

        Text shopkeeperInfo = new Text("👤 Shopkeeper: " + (c.getShopkeeperName() != null ? c.getShopkeeperName() : "Partner Merchant"));
        shopkeeperInfo.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        shopkeeperInfo.setFill(Color.web("#444444"));

        Text storeInfo = new Text("🏪 Store: " + (c.getStoreName() != null ? c.getStoreName() : "BuyNex Partner Store"));
        storeInfo.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        storeInfo.setFill(Color.web("#B84300"));

        Text reqDate = new Text("📅 Requested: " + (c.getCreatedAt() != null ? c.getCreatedAt().substring(0, Math.min(10, c.getCreatedAt().length())) : "Today"));
        reqDate.setFont(Font.font("Arial", 12));
        reqDate.setFill(Color.GRAY);

        shopInfoRow.getChildren().addAll(shopkeeperInfo, storeInfo, reqDate);

        // Description
        Text descText = new Text(c.getDescription() != null ? c.getDescription() : "No description provided.");
        descText.setFont(Font.font("Georgia", 13));
        descText.setFill(Color.web("#666666"));
        descText.setWrappingWidth(590);

        // Offer Metrics: Discount, Dates, Category
        HBox metrics = new HBox(30);
        metrics.setPadding(new Insets(10, 14, 10, 14));
        metrics.setStyle("-fx-background-color:#FAF8FA;-fx-background-radius:8;-fx-border-color:#E9E2EA;-fx-border-radius:8;");

        final String discValStr = (c.getDiscountType() != null && c.getDiscountType().contains("FLAT"))
                ? ("₹" + (int) c.getDiscount() + " OFF")
                : ((int) c.getDiscount() + "% OFF");
        VBox dBox = new VBox(2, new Text("DISCOUNT"), new Text(discValStr));
        ((Text) dBox.getChildren().get(0)).setStyle("-fx-font-size:10px;-fx-font-weight:bold;-fx-fill:#888888;");
        ((Text) dBox.getChildren().get(1)).setStyle("-fx-font-size:15px;-fx-font-weight:bold;-fx-fill:#C84B00;");

        VBox startBox = new VBox(2, new Text("START DATE"), new Text(c.getStartDate() != null ? c.getStartDate() : "--"));
        ((Text) startBox.getChildren().get(0)).setStyle("-fx-font-size:10px;-fx-font-weight:bold;-fx-fill:#888888;");
        ((Text) startBox.getChildren().get(1)).setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-fill:#333333;");

        VBox endBox = new VBox(2, new Text("END DATE"), new Text(c.getEndDate() != null ? c.getEndDate() : "--"));
        ((Text) endBox.getChildren().get(0)).setStyle("-fx-font-size:10px;-fx-font-weight:bold;-fx-fill:#888888;");
        ((Text) endBox.getChildren().get(1)).setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-fill:#333333;");

        VBox catBox = new VBox(2, new Text("CATEGORY"), new Text(c.getApplicableCategories() != null ? c.getApplicableCategories() : "All Categories"));
        ((Text) catBox.getChildren().get(0)).setStyle("-fx-font-size:10px;-fx-font-weight:bold;-fx-fill:#888888;");
        ((Text) catBox.getChildren().get(1)).setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-fill:#333333;");

        metrics.getChildren().addAll(dBox, startBox, endBox, catBox);

        // If rejected, show rejection info
        if (isRejected && c.getRejectionReason() != null) {
            Label rejNotice = new Label("Rejection Reason: " + c.getRejectionReason());
            rejNotice.setStyle("-fx-background-color:#FFEBEE;-fx-text-fill:#C62828;-fx-font-weight:bold;-fx-font-size:12px;-fx-padding:6 10 6 10;-fx-background-radius:6;");
            card.getChildren().add(rejNotice);
        } else if (isApproved && c.getApprovedAt() != null) {
            Label appNotice = new Label("✓ Live for customers | Approved on " + c.getApprovedAt().substring(0, Math.min(10, c.getApprovedAt().length())) + " by " + (c.getApprovedBy() != null ? c.getApprovedBy() : "Admin"));
            appNotice.setStyle("-fx-background-color:#E8F5E9;-fx-text-fill:#2E7D32;-fx-font-weight:bold;-fx-font-size:12px;-fx-padding:6 10 6 10;-fx-background-radius:6;");
            card.getChildren().add(appNotice);
        }

        // Action Buttons Row
        HBox actionsRow = new HBox(12);
        actionsRow.setAlignment(Pos.CENTER_RIGHT);

        Button viewDetailsBtn = new Button("View Details");
        viewDetailsBtn.setPrefHeight(36);
        viewDetailsBtn.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#C84B00;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-text-fill:#C84B00;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:12px;" +
                "-fx-cursor:hand;"
        );
        viewDetailsBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campaign Request Details");
            alert.setHeaderText("Campaign: " + c.getTitle());
            alert.setContentText(
                    "Shopkeeper: " + c.getShopkeeperName() + "\n" +
                    "Store: " + c.getStoreName() + "\n" +
                    "Discount: " + discValStr + " (" + c.getDiscountType() + ")\n" +
                    "Validity: " + c.getStartDate() + " to " + c.getEndDate() + "\n" +
                    "Applicable Categories: " + c.getApplicableCategories() + "\n" +
                    "Minimum Purchase: ₹" + c.getMinimumOrderAmount() + "\n" +
                    "Maximum Discount: ₹" + c.getMaximumDiscount() + "\n" +
                    "Status: " + c.getStatus() + "\n\n" +
                    "Terms and Conditions:\n" + (c.getTermsAndConditions() != null ? c.getTermsAndConditions() : "Standard marketplace terms apply.")
            );
            alert.showAndWait();
        });

        actionsRow.getChildren().add(viewDetailsBtn);

        Button deleteCampBtn = new Button("🗑  DELETE");
        deleteCampBtn.setPrefHeight(36);
        deleteCampBtn.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#C62828;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-text-fill:#C62828;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:12px;" +
                "-fx-cursor:hand;"
        );
        deleteCampBtn.setOnAction(e -> {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Delete Campaign");
            confirmAlert.setHeaderText("Delete Campaign: " + c.getTitle());
            confirmAlert.setContentText("Are you sure you want to permanently delete this campaign request?");
            Optional<ButtonType> res = confirmAlert.showAndWait();
            if (res.isPresent() && res.get() == ButtonType.OK) {
                CampaignController.deleteCampaign(c.getCampaignId());
                renderCampaignRequestsList();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Campaign Deleted");
                alert.setHeaderText(null);
                alert.setContentText("The campaign request has been deleted successfully.");
                alert.showAndWait();
            }
        });
        actionsRow.getChildren().add(deleteCampBtn);

        if (isPending) {
            Button acceptBtn = new Button("✓  ACCEPT");
            acceptBtn.setPrefHeight(36);
            acceptBtn.setStyle(
                    "-fx-background-color:#2E7D32;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-weight:bold;" +
                    "-fx-font-size:12px;" +
                    "-fx-background-radius:7;" +
                    "-fx-cursor:hand;"
            );
            acceptBtn.setOnAction(e -> {
                CampaignController.approveCampaign(c.getCampaignId(), "Admin");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Campaign Approved");
                alert.setHeaderText("Campaign Request Approved");
                alert.setContentText(
                        "The campaign '" + c.getTitle() + "' has been APPROVED.\n\n" +
                        "1. Status updated: PENDING → APPROVED\n" +
                        "2. Notification sent to shopkeeper '" + c.getShopkeeperName() + "'.\n" +
                        "3. Customer live notification dispatched.\n" +
                        "4. The campaign is now eligible and active on the Customer Portal during its validity dates."
                );
                alert.showAndWait();
                renderCampaignRequestsList();
            });

            Button rejectBtn = new Button("✕  REJECT");
            rejectBtn.setPrefHeight(36);
            rejectBtn.setStyle(
                    "-fx-background-color:#FFEBEE;" +
                    "-fx-text-fill:#C62828;" +
                    "-fx-border-color:#C62828;" +
                    "-fx-border-radius:7;" +
                    "-fx-background-radius:7;" +
                    "-fx-font-weight:bold;" +
                    "-fx-font-size:12px;" +
                    "-fx-cursor:hand;"
            );
            rejectBtn.setOnAction(e -> {
                TextInputDialog dialog = new TextInputDialog("Discount exceeds allowable threshold");
                dialog.setTitle("Reject Campaign");
                dialog.setHeaderText("Reject Campaign: " + c.getTitle());
                dialog.setContentText("Please provide reason for rejection (Shopkeeper will see this):");
                Optional<String> res = dialog.showAndWait();
                if (res.isPresent()) {
                    String reason = res.get().trim();
                    if (reason.isEmpty()) {
                        reason = "Campaign does not meet marketplace discount guidelines.";
                    }
                    CampaignController.rejectCampaign(c.getCampaignId(), "Admin", reason);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Campaign Rejected");
                    alert.setHeaderText("Campaign Request Rejected");
                    alert.setContentText(
                            "The campaign '" + c.getTitle() + "' was marked as REJECTED.\n\n" +
                            "Reason: " + reason + "\n\n" +
                            "Shopkeeper notification has been sent. This campaign will NOT appear on the Customer Portal."
                    );
                    alert.showAndWait();
                    renderCampaignRequestsList();
                }
            });

            actionsRow.getChildren().addAll(rejectBtn, acceptBtn);
        }

        Region rGrow = new Region();
        HBox.setHgrow(rGrow, Priority.ALWAYS);
        HBox footer = new HBox(rGrow, actionsRow);

        card.getChildren().addAll(topRow, shopInfoRow, descText, metrics, footer);
        return card;
    }

    private VBox createCampaignCard(
            String title,
            String status,
            String description,
            String convRate,
            String shopCount,
            String netLift
    ) {
        return createCampaignCard(null, title, status, description, convRate, shopCount, netLift);
    }

    private VBox createCampaignCard(
            OfferModel om,
            String title,
            String status,
            String description,
            String convRate,
            String shopCount,
            String netLift
    ) {
        VBox card = new VBox();
        card.setSpacing(12);
        card.setPadding(new Insets(20));
        card.setPrefHeight(230);
        card.setMinHeight(230);
        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:14;" +
                "-fx-border-color:#E4C7B7;" +
                "-fx-border-radius:14;"
        );

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Georgia", FontWeight.BOLD, 19));
        if ("Paused".equalsIgnoreCase(status)) {
            titleText.setFill(Color.web("#555555"));
        }

        Text statusText = new Text(status);
        statusText.setFont(Font.font("Georgia", FontWeight.BOLD, 13));
        if ("Active".equalsIgnoreCase(status)) {
            statusText.setFill(Color.web("#8E3D18"));
        } else {
            statusText.setFill(Color.GRAY);
        }

        Region grow = new Region();
        HBox.setHgrow(grow, Priority.ALWAYS);

        HBox rightHeaderBox = new HBox(12);
        rightHeaderBox.setAlignment(Pos.CENTER_RIGHT);
        rightHeaderBox.getChildren().add(statusText);

        if (om != null) {
            Button deleteOfferBtn = new Button("🗑 Delete Offer");
            deleteOfferBtn.setStyle(
                    "-fx-background-color: #FFF5F5;" +
                    "-fx-text-fill: #D32F2F;" +
                    "-fx-border-color: #FFCDD2;" +
                    "-fx-border-radius: 6px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );
            deleteOfferBtn.setOnAction(e -> {
                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Delete Offer");
                confirmAlert.setHeaderText("Delete Offer: " + title);
                confirmAlert.setContentText("Are you sure you want to permanently delete this offer?");
                Optional<ButtonType> res = confirmAlert.showAndWait();
                if (res.isPresent() && res.get() == ButtonType.OK) {
                    if (om.getOfferId() != null) {
                        OfferController.deleteOffer(om.getOfferId());
                        dbOffers.remove(om);
                    }
                    renderCardsForActiveTab();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Offer Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("The offer has been deleted successfully.");
                    alert.showAndWait();
                }
            });
            rightHeaderBox.getChildren().add(deleteOfferBtn);
        }

        titleBox.getChildren().addAll(titleText, grow, rightHeaderBox);

        Text descText = new Text(description);
        descText.setFont(Font.font("Georgia", 14));
        descText.setFill(Color.web("#777777"));

        Separator sep = new Separator();

        HBox stats = new HBox();
        stats.setSpacing(70);

        VBox conversion = new VBox(5);
        Text conversionTitle = new Text("Conversion Rate");
        conversionTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        conversionTitle.setFill(Color.GRAY);

        Text conversionValue = new Text(convRate);
        conversionValue.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        conversion.getChildren().addAll(conversionTitle, conversionValue);

        VBox shopsBox = new VBox(5);
        Text shopsTitle = new Text("Participating Shops");
        shopsTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        shopsTitle.setFill(Color.GRAY);

        Text shopsValue = new Text(shopCount);
        shopsValue.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        shopsBox.getChildren().addAll(shopsTitle, shopsValue);

        VBox networkLift = new VBox(5);
        Text liftTitle = new Text("Est. Network Lift");
        liftTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        liftTitle.setFill(Color.GRAY);

        Text liftValue = new Text(netLift);
        liftValue.setFont(Font.font("Georgia", FontWeight.BOLD, 18));
        if (!"--".equals(netLift)) {
            liftValue.setFill(Color.web("#A83E00"));
        }

        networkLift.getChildren().addAll(liftTitle, liftValue);
        stats.getChildren().addAll(conversion, shopsBox, networkLift);

        Region cardGrow = new Region();
        VBox.setVgrow(cardGrow, Priority.ALWAYS);

        card.getChildren().addAll(titleBox, descText, cardGrow, sep, stats);
        return card;
    }

    private HBox createTopActions() {
        Image notificationImage = new Image(
                getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
        ImageView notificationIcon = new ImageView(notificationImage);
        notificationIcon.setFitWidth(22);
        notificationIcon.setFitHeight(22);
        notificationIcon.setPreserveRatio(true);

        Text notificationName = new Text("Notifications");
        notificationName.setFont(Font.font("Arial", 14));

        HBox notificationAction = new HBox(6, notificationIcon, notificationName);
        notificationAction.setAlignment(Pos.CENTER_LEFT);
        notificationAction.setStyle("-fx-cursor:hand;");

        Image chatbotImage = new Image(
                getClass().getResource("/assets/images/admin/message.png").toExternalForm());
        ImageView chatbotIcon = new ImageView(chatbotImage);
        chatbotIcon.setFitWidth(22);
        chatbotIcon.setFitHeight(22);
        chatbotIcon.setPreserveRatio(true);

        Text chatbotName = new Text("ChatBot");
        chatbotName.setFont(Font.font("Arial", 14));

        HBox chatbotAction = new HBox(6, chatbotIcon, chatbotName);
        chatbotAction.setAlignment(Pos.CENTER_LEFT);
        chatbotAction.setStyle("-fx-cursor:hand;");

        chatbotAction.setOnMouseClicked(e -> {
            SmartAssistantUI chatPage = new SmartAssistantUI();
            Homepage.HomepageStage.setScene(chatPage.getUserScene());
        });

        javafx.stage.Popup notificationPopup = new javafx.stage.Popup();
        Text notificationTitle = new Text("Notifications");
        notificationTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Button markRead = new Button("Mark all as read");
        markRead.setStyle("-fx-background-color:transparent;-fx-text-fill:#E65300;-fx-font-size:13px;");

        Region notificationGrow = new Region();
        HBox.setHgrow(notificationGrow, Priority.ALWAYS);

        HBox notificationHeader = new HBox(notificationTitle, notificationGrow, markRead);
        notificationHeader.setAlignment(Pos.CENTER_LEFT);

        Text notificationText1 = new Text("New Shop Registration\nTech Haven needs verification\n2 mins ago");
        notificationText1.setStyle("-fx-font-size:13px;");
        VBox notification1 = new VBox(notificationText1);
        notification1.setPadding(new Insets(12));
        notification1.setStyle("-fx-background-color:#FFF4ED;-fx-background-radius:8;");

        Text notificationText2 = new Text("New User Joined\nNew customer account created\n10 mins ago");
        notificationText2.setStyle("-fx-font-size:13px;");
        VBox notification2 = new VBox(notificationText2);
        notification2.setPadding(new Insets(12));
        notification2.setStyle("-fx-background-color:#F4FFF7;-fx-background-radius:8;");

        Text notificationText3 = new Text("Flagged Account\nSuspicious activity detected\n1 hour ago");
        notificationText3.setStyle("-fx-font-size:13px;");
        VBox notification3 = new VBox(notificationText3);
        notification3.setPadding(new Insets(12));
        notification3.setStyle("-fx-background-color:#FFF5F5;-fx-background-radius:8;");

        VBox notificationBox = new VBox(12, notificationHeader, new Separator(), notification1, notification2, notification3);
        notificationBox.setPrefWidth(330);
        notificationBox.setPadding(new Insets(18));
        notificationBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#E5E1E8;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.18),15,0,0,5);"
        );

        notificationPopup.getContent().add(notificationBox);

        notificationAction.setOnMouseClicked(e -> {
            if (notificationPopup.isShowing()) {
                notificationPopup.hide();
            } else {
                javafx.geometry.Bounds bellPosition = notificationAction.localToScreen(notificationAction.getBoundsInLocal());
                if (bellPosition != null) {
                    notificationPopup.show(notificationAction, bellPosition.getMaxX() - 330, bellPosition.getMaxY() + 10);
                }
            }
        });

        markRead.setOnAction(e -> {
            notification1.setStyle("-fx-background-color:white;");
            notification2.setStyle("-fx-background-color:white;");
            notification3.setStyle("-fx-background-color:white;");
        });

        HBox topActions = new HBox(24, notificationAction, chatbotAction);
        topActions.setAlignment(Pos.CENTER_RIGHT);
        return topActions;
    }
}

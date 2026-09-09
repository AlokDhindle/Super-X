package com.kryox.view.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.model.CampaignModel;
import com.kryox.model.Shopkeeper.ProductModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerCampaignDetails {

    private final String userId;
    private final CampaignModel campaign;
    private final Runnable backCallback;

    public CustomerCampaignDetails(String userId, CampaignModel campaign, Runnable backCallback) {
        this.userId = (userId != null && !userId.isBlank()) ? userId : "guest";
        this.campaign = campaign;
        this.backCallback = backCallback;
    }

    private Image loadImage(String path) {
        try {
            if (path != null && (path.startsWith("file:") || path.startsWith("http:") || path.startsWith("https:"))) {
                return new Image(path, true);
            }
            if (path != null && !path.isBlank()) {
                URL url = getClass().getResource(path);
                if (url != null) {
                    return new Image(url.toExternalForm());
                }
            }
        } catch (Exception ignored) {}

        // Safe graceful fallback image
        try {
            URL fallbackUrl = getClass().getResource("/assets/images/image.png");
            if (fallbackUrl != null) {
                return new Image(fallbackUrl.toExternalForm());
            }
        } catch (Exception ignored) {}

        return null;
    }

    public Scene getScene() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F8F5F2;");

        // ---------------- TOP BAR ----------------
        HBox topBar = new HBox(16);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(14, 36, 14, 36));
        topBar.setStyle("-fx-background-color: #ebccb7; -fx-border-color: #E2D9D2; -fx-border-width: 0 0 1 0;");

        Button backButton = new Button("←  Back");
        backButton.setPrefHeight(38);
        backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnAction(e -> {
            if (backCallback != null) {
                backCallback.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
        });

        Label brandLabel = new Label("BuyNeX");
        brandLabel.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 24px; -fx-font-weight: 900; -fx-text-fill: #E87500;");

        Label pageTitle = new Label("Campaign Details & Special Deals");
        pageTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #444444;");

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        Button cartBtn = new Button("🛒 Go to Cart");
        cartBtn.setPrefHeight(38);
        cartBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        cartBtn.setOnAction(e -> CustomerNavigation.navigateToCart(userId));

        topBar.getChildren().addAll(backButton, brandLabel, pageTitle, topSpacer, cartBtn);
        root.setTop(topBar);

        // ---------------- SCROLLABLE CENTER CONTENT ----------------
        VBox contentBox = new VBox(24);
        contentBox.setPadding(new Insets(24, 40, 40, 40));
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(1200);

        // 1. HERO CAMPAIGN BANNER CARD
        VBox heroCard = new VBox(16);
        heroCard.setPadding(new Insets(28));
        heroCard.setMaxWidth(1160);
        heroCard.setPrefWidth(1160);
        heroCard.setStyle(
                "-fx-background-color: linear-gradient(to right, #B84300, #E65300, #FF7B25);" +
                "-fx-background-radius: 18px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 14, 0, 0, 4);"
        );

        HBox heroTopRow = new HBox(12);
        heroTopRow.setAlignment(Pos.CENTER_LEFT);

        Label campaignBadge = new Label("🎉 OFFICIAL MARKETPLACE CAMPAIGN");
        campaignBadge.setStyle("-fx-background-color: rgba(255,255,255,0.22); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-padding: 4 10 4 10; -fx-background-radius: 10px;");

        String discTag = (int) campaign.getDiscount() + "% OFF";
        if (campaign.getDiscountType() != null && campaign.getDiscountType().contains("FLAT")) {
            discTag = "₹" + (int) campaign.getDiscount() + " OFF";
        }
        Label discBadge = new Label("🔥 " + discTag);
        discBadge.setStyle("-fx-background-color: white; -fx-text-fill: #B84300; -fx-font-weight: 900; -fx-font-size: 13px; -fx-padding: 4 12 4 12; -fx-background-radius: 10px;");

        Region heroSp = new Region();
        HBox.setHgrow(heroSp, Priority.ALWAYS);

        Label activeBadge = new Label("● ACTIVE NOW");
        activeBadge.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-padding: 4 10 4 10; -fx-background-radius: 10px;");

        heroTopRow.getChildren().addAll(campaignBadge, discBadge, heroSp, activeBadge);

        String mainTitle = campaign.getTitle() != null ? campaign.getTitle().trim() : "BuyNex Special Campaign";
        Label campaignName = new Label(mainTitle);
        campaignName.setStyle("-fx-font-size: 32px; -fx-font-weight: 900; -fx-text-fill: white;");
        campaignName.setWrapText(true);

        String desc = campaign.getDescription() != null ? campaign.getDescription().trim() : "Enjoy exclusive savings on participating products.";
        Label campaignDesc = new Label(desc);
        campaignDesc.setStyle("-fx-font-size: 14px; -fx-text-fill: #FFF1E8; -fx-line-spacing: 2px;");
        campaignDesc.setWrapText(true);

        // Store & Validity Meta Row
        HBox metaRow = new HBox(20);
        metaRow.setAlignment(Pos.CENTER_LEFT);
        metaRow.setPadding(new Insets(10, 16, 10, 16));
        metaRow.setStyle("-fx-background-color: rgba(0,0,0,0.22); -fx-background-radius: 12px;");

        Label storeLabel = new Label("🏪 Organized by: " + (campaign.getStoreName() != null ? campaign.getStoreName() : "BuyNex Verified Merchants"));
        storeLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label datesLabel = new Label("📅 Valid: " + (campaign.getStartDate() != null ? campaign.getStartDate() : "--") + " to " + (campaign.getEndDate() != null ? campaign.getEndDate() : "--"));
        datesLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #FFE0CE;");

        Label categoryLabel = new Label("🏷️ Category: " + (campaign.getApplicableCategories() != null ? campaign.getApplicableCategories() : "All Categories"));
        categoryLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #FFE0CE;");

        metaRow.getChildren().addAll(storeLabel, datesLabel, categoryLabel);

        heroCard.getChildren().addAll(heroTopRow, campaignName, campaignDesc, metaRow);
        contentBox.getChildren().add(heroCard);

        // 2. TERMS & PURCHASE CONDITIONS CARD
        HBox termsCard = new HBox(24);
        termsCard.setPadding(new Insets(18, 22, 18, 22));
        termsCard.setMaxWidth(1160);
        termsCard.setPrefWidth(1160);
        termsCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #E6DFD9;" +
                "-fx-border-radius: 14px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.03), 6, 0, 0, 2);"
        );

        VBox leftInfo = new VBox(6);
        leftInfo.setPrefWidth(320);
        Label minPurchase = new Label("Minimum Purchase: ₹" + (campaign.getMinimumOrderAmount() > 0 ? (int) campaign.getMinimumOrderAmount() : "None"));
        minPurchase.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label maxDiscount = new Label("Maximum Discount: ₹" + (campaign.getMaximumDiscount() > 0 ? (int) campaign.getMaximumDiscount() : "Unlimited"));
        maxDiscount.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label discountType = new Label("Discount Model: " + (campaign.getDiscountType() != null ? campaign.getDiscountType() : "Standard Percentage"));
        discountType.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");

        leftInfo.getChildren().addAll(minPurchase, maxDiscount, discountType);

        Separator vertSep = new Separator(javafx.geometry.Orientation.VERTICAL);

        VBox rightTerms = new VBox(4);
        HBox.setHgrow(rightTerms, Priority.ALWAYS);
        Label termsTitle = new Label("Campaign Terms & Conditions");
        termsTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #9C3700;");

        Label termsBody = new Label(campaign.getTermsAndConditions() != null ? campaign.getTermsAndConditions() : "1. Discount automatically calculated on eligible items.\n2. Valid for limited campaign period.");
        termsBody.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666; -fx-line-spacing: 2px;");
        termsBody.setWrapText(true);

        rightTerms.getChildren().addAll(termsTitle, termsBody);
        termsCard.getChildren().addAll(leftInfo, vertSep, rightTerms);
        contentBox.getChildren().add(termsCard);

        // 3. CAMPAIGN PRODUCTS SECTION
        VBox productsSection = new VBox(14);
        productsSection.setMaxWidth(1160);
        productsSection.setPrefWidth(1160);

        HBox prodHeader = new HBox(12);
        prodHeader.setAlignment(Pos.CENTER_LEFT);

        Label prodTitle = new Label("CAMPAIGN PRODUCTS");
        prodTitle.setStyle("-fx-font-size: 21px; -fx-font-weight: 900; -fx-text-fill: #171717;");

        Label discNotice = new Label("• " + discTag + " applied directly on all products below!");
        discNotice.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");

        prodHeader.getChildren().addAll(prodTitle, discNotice);
        productsSection.getChildren().add(prodHeader);

        // Dynamic Product Grid
        FlowPane productGrid = new FlowPane();
        productGrid.setHgap(20);
        productGrid.setVgap(20);
        productGrid.setPrefWrapLength(1160);
        productGrid.setAlignment(Pos.TOP_LEFT);

        List<ProductModel> products = loadCampaignProducts();
        for (ProductModel p : products) {
            productGrid.getChildren().add(createCampaignProductCard(p));
        }

        productsSection.getChildren().add(productGrid);
        contentBox.getChildren().add(productsSection);

        VBox outerWrapper = new VBox(contentBox);
        outerWrapper.setAlignment(Pos.TOP_CENTER);

        ScrollPane scroll = new ScrollPane(outerWrapper);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");

        root.setCenter(scroll);

        return new Scene(root, 1550, 850);
    }

    private List<ProductModel> loadCampaignProducts() {
        List<ProductModel> list = new ArrayList<>();
        try {
            ProductController controller = new ProductController();
            ArrayList<ProductModel> fetched = controller.fetchProducts();
            if (fetched != null) {
                for (ProductModel p : fetched) {
                    if (p != null) {
                        // Match category or shopkeeper if specific
                        if (campaign.getShopkeeperId() != null && campaign.getShopkeeperId().equalsIgnoreCase(p.getShopkeeperUid())) {
                            list.add(p);
                        } else if (campaign.getApplicableCategories() == null
                                || campaign.getApplicableCategories().contains("All")
                                || (p.getCategory() != null && campaign.getApplicableCategories().toLowerCase().contains(p.getCategory().toLowerCase()))) {
                            list.add(p);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching campaign products: " + e.getMessage());
        }

        // Guaranteed rich demo products if store catalog is empty
        if (list.isEmpty()) {
            list.add(new ProductModel("cp1", "Parle-G Gold Premium Pack", "Grocery", "Parle", "Crisp golden wheat glucose biscuits, teatime favorite", "SKU-PG1", "BC-PG1", 50.0, 30.0, 50.0, 0.0, 0.0, 100, 10, "pack", "Active", "B1", "No", "2026-01-01", "2026-12-01", "/assets/images/image.png"));
            list.add(new ProductModel("cp2", "Organic Basmati Royal Rice 5kg", "Grocery", "Daawat", "Long grain aromatic aged basmati royal rice", "SKU-BR2", "BC-BR2", 499.0, 300.0, 499.0, 0.0, 0.0, 45, 5, "bag", "Active", "B2", "No", "2026-01-01", "2027-01-01", "/assets/images/image1.png"));
            list.add(new ProductModel("cp3", "Tata Tea Gold Leaf 1kg", "Grocery", "Tata", "Gently rolled premium tea leaves with 15% long leaves", "SKU-TT3", "BC-TT3", 420.0, 280.0, 420.0, 0.0, 0.0, 60, 5, "pack", "Active", "B3", "No", "2026-01-01", "2027-06-01", "/assets/images/avocado.png"));
            list.add(new ProductModel("cp4", "Fortune Sunlite Refined Oil 5L", "Grocery", "Fortune", "Light and healthy cooking oil enriched with vitamins", "SKU-FO4", "BC-FO4", 650.0, 480.0, 650.0, 0.0, 0.0, 30, 4, "can", "Active", "B4", "No", "2026-01-01", "2027-01-01", "/assets/images/image2.png"));
            list.add(new ProductModel("cp5", "Cadbury Celebrations Festive Pack", "Grocery", "Cadbury", "Rich assortment of assorted chocolate bars for gifting", "SKU-CB5", "BC-CB5", 250.0, 150.0, 250.0, 0.0, 0.0, 80, 10, "box", "Active", "B5", "No", "2026-01-01", "2026-11-01", "/assets/images/image.png"));
            list.add(new ProductModel("cp6", "Haldiram's Festive Kaju Katli 500g", "Grocery", "Haldirams", "Pure cashew traditional royal Indian sweet dessert", "SKU-KK6", "BC-KK6", 380.0, 240.0, 380.0, 0.0, 0.0, 40, 5, "box", "Active", "B6", "No", "2026-01-01", "2026-10-25", "/assets/images/image1.png"));
        }

        return list;
    }

    private VBox createCampaignProductCard(ProductModel p) {
        double originalMrp = p.getSellingPrice() > 0 ? p.getSellingPrice() : (p.getCostPrice() > 0 ? p.getCostPrice() : 100.0);
        double campaignPrice = campaign.calculateDiscountedPrice(originalMrp);
        double discPercent = originalMrp > 0 ? Math.round(((originalMrp - campaignPrice) / originalMrp) * 100.0) : campaign.getDiscount();

        VBox card = new VBox(8);
        card.setPadding(new Insets(12));
        card.setPrefWidth(215);
        card.setMinWidth(215);
        card.setMaxWidth(215);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #E2DDD8;" +
                "-fx-border-radius: 14;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);"
        );

        // Product Image
        Image img = loadImage(p.getImageUrl());
        StackPane imageWrap = new StackPane();
        imageWrap.setPrefSize(191, 120);
        imageWrap.setStyle("-fx-background-color: #FAF8F5; -fx-background-radius: 10;");

        if (img != null) {
            ImageView pView = new ImageView(img);
            pView.setFitWidth(180);
            pView.setFitHeight(115);
            pView.setPreserveRatio(true);
            imageWrap.getChildren().add(pView);
        } else {
            Label noImg = new Label("🛍️");
            noImg.setStyle("-fx-font-size: 32px;");
            imageWrap.getChildren().add(noImg);
        }

        // Top tag row
        Label discountBadge = new Label("-" + (int) discPercent + "% OFF");
        discountBadge.setStyle("-fx-background-color: #FF6900; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 10px; -fx-padding: 3 6 3 6; -fx-background-radius: 6;");

        Label inStockBadge = new Label("● In Stock");
        inStockBadge.setStyle("-fx-text-fill: #2E7D32; -fx-font-weight: bold; -fx-font-size: 10px;");

        Region topSp = new Region();
        HBox.setHgrow(topSp, Priority.ALWAYS);
        HBox tagRow = new HBox(discountBadge, topSp, inStockBadge);
        tagRow.setAlignment(Pos.CENTER_LEFT);

        Label name = new Label(p.getProductName() != null ? p.getProductName() : "Product");
        name.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #222222;");
        name.setPrefHeight(36);
        name.setWrapText(true);

        Label brand = new Label((p.getBrand() != null ? p.getBrand() + " • " : "") + (p.getCategory() != null ? p.getCategory() : "General"));
        brand.setStyle("-fx-font-size: 10px; -fx-text-fill: #888888;");

        // Price Row
        Label dealPriceLabel = new Label(String.format("₹%.2f", campaignPrice));
        dealPriceLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: 900; -fx-text-fill: #FF6900;");

        Label originalLabel = new Label(String.format("₹%.2f", originalMrp));
        originalLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #999999; -fx-strikethrough: true;");

        Region prSp = new Region();
        HBox.setHgrow(prSp, Priority.ALWAYS);

        HBox priceRow = new HBox(6, dealPriceLabel, originalLabel, prSp);
        priceRow.setAlignment(Pos.BASELINE_LEFT);

        // Add to Cart Button
        Button addBtn = new Button("🛒 Add to Cart");
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setPrefHeight(34);
        addBtn.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        String shopUid = (p.getShopkeeperUid() != null && !p.getShopkeeperUid().isBlank())
                ? p.getShopkeeperUid()
                : (campaign.getShopkeeperId() != null ? campaign.getShopkeeperId() : "shop_default");

        addBtn.setOnAction(e -> {
            CARTcontroller cl = new CARTcontroller();
            cl.addTocart(userId, p.getProductName(), campaignPrice, p.getCategory(), 1, shopUid);
            addBtn.setText("✓ Added to Cart!");
            addBtn.setStyle(
                    "-fx-background-color: #2E7D32;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 11px;" +
                    "-fx-background-radius: 8;"
            );
        });

        card.getChildren().addAll(imageWrap, tagRow, name, brand, priceRow, addBtn);
        return card;
    }
}

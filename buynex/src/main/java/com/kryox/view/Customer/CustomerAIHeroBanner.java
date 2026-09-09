package com.kryox.view.Customer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.dao.Customer.OrderDAO;
import com.kryox.model.Customer.Productcart;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.model.Shopkeeper.ProductModel;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CustomerAIHeroBanner {

    public static class AIRecommendation {
        public String title;
        public String status;
        public String message;
        public String actionLabel;
        public Runnable action;

        public AIRecommendation(String title, String status, String message, String actionLabel, Runnable action) {
            this.title = title;
            this.status = status;
            this.message = message;
            this.actionLabel = actionLabel;
            this.action = action;
        }
    }

    private static int currentRecIndex = 0;
    private static final List<AIRecommendation> RECOMMENDATIONS = new ArrayList<>();

    public static HBox createHeroBanner(String userId, Runnable combineAction, Runnable bestPriceAction) {
        String effectiveUserId = (userId != null && !userId.isBlank()) ? userId : "guest";

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(14);
        cardShadow.setOffsetY(5);
        cardShadow.setSpread(0.02);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.10));

        HBox hbright = new HBox(35);
        hbright.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(hbright, Priority.ALWAYS);
        hbright.setPrefHeight(315);
        hbright.setMinHeight(315);
        hbright.setPadding(new Insets(34, 40, 34, 40));
        hbright.setAlignment(Pos.CENTER_LEFT);

        LinearGradient darkGradient = new LinearGradient(
                0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#242529")),
                new Stop(0.55, Color.web("#303136")),
                new Stop(1.0, Color.web("#563A2B"))
        );

        hbright.setBackground(new Background(new BackgroundFill(darkGradient, new CornerRadii(22), Insets.EMPTY)));
        hbright.setEffect(cardShadow);

        // 1. LEFT CONTENT
        VBox leftContent = new VBox(13);
        leftContent.setPrefWidth(600);
        HBox.setHgrow(leftContent, Priority.ALWAYS);
        leftContent.setAlignment(Pos.CENTER_LEFT);

        Label badge = new Label("✦  NEXT-GEN INTELLIGENCE • LIVE");
        badge.setStyle(
                "-fx-background-color: #503629;" +
                "-fx-text-fill: #FF9D67;" +
                "-fx-font-size: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 7 13 7 13;"
        );

        // Dynamic Greeting based on current time & day
        int hour = LocalTime.now().getHour();
        String dayName = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String greetingPrefix;
        if (hour < 12) {
            greetingPrefix = "Good morning!\nWhat are you looking for today?";
        } else if (hour < 17) {
            greetingPrefix = "Good afternoon!\nWhat are you looking for today?";
        } else {
            greetingPrefix = "Good evening!\nWhat are you looking for today?";
        }

        Text heading = new Text(greetingPrefix);
        heading.setStyle(
                "-fx-fill: white;" +
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: 900;"
        );

        Text description = new Text(
                "Your hyper-local AI is ready for " + dayName + " orders,\n" +
                "sourcing live inventory and instant delivery from verified stores."
        );
        description.setStyle("-fx-fill: #C9C9C9; -fx-font-size: 11px;");

        // Action Buttons
        Button groceries = new Button("♧  Combine Groceries");
        groceries.setPrefHeight(42);
        groceries.setStyle(
                "-fx-background-color: #45464B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 16 10 16;" +
                "-fx-cursor: hand;"
        );
        groceries.setOnAction(e -> {
            if (combineAction != null) {
                combineAction.run();
            } else {
                CustomerNavigation.navigateToGroceries(effectiveUserId);
            }
        });

        Button bestPrice = new Button("⌁  Find Best Price");
        bestPrice.setPrefHeight(42);
        bestPrice.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 11;" +
                "-fx-padding: 10 17 10 17;" +
                "-fx-cursor: hand;"
        );
        bestPrice.setOnAction(e -> {
            if (bestPriceAction != null) {
                bestPriceAction.run();
            } else {
                CustomerNavigation.navigateToDeals(effectiveUserId);
            }
        });

        HBox buttons = new HBox(10, groceries, bestPrice);
        leftContent.getChildren().addAll(badge, heading, description, buttons);

        // 2. RIGHT DYNAMIC AI RECOMMENDATION CARD
        VBox notification = new VBox(12);
        notification.setPrefWidth(310);
        notification.setMinWidth(290);
        notification.setMaxWidth(320);
        notification.setPrefHeight(255);
        notification.setPadding(new Insets(18));
        notification.setAlignment(Pos.TOP_LEFT);
        notification.setStyle(
                "-fx-background-color: #ECEAE9;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: rgba(255,255,255,0.35);" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );
        notification.setEffect(cardShadow);

        Label aiCircle = new Label("✦");
        aiCircle.setPrefSize(38, 38);
        aiCircle.setAlignment(Pos.CENTER);
        aiCircle.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 50%;"
        );

        VBox aiHeading = new VBox(2);
        Label aiTitle = new Label("AI Recommendation");
        aiTitle.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label aiStatus = new Label("PERSONALIZED FOR YOU • " + dayName.toUpperCase());
        aiStatus.setStyle("-fx-font-size: 7px; -fx-font-weight: bold; -fx-text-fill: #FF6900;");
        aiHeading.getChildren().addAll(aiTitle, aiStatus);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        // Refresh / Cycle next tip button
        Button cycleBtn = new Button("↻");
        cycleBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 4;"
        );

        HBox aiHeader = new HBox(8, aiCircle, aiHeading, headerSpacer, cycleBtn);
        aiHeader.setAlignment(Pos.CENTER_LEFT);

        Text notificationText = new Text();
        notificationText.setStyle("-fx-fill: #333333; -fx-font-size: 10.5px; -fx-font-weight: bold;");
        notificationText.setWrappingWidth(265);

        Region notificationSpacer = new Region();
        VBox.setVgrow(notificationSpacer, Priority.ALWAYS);

        Button dismiss = new Button("Dismiss");
        dismiss.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 9px;" +
                "-fx-cursor: hand;"
        );

        Button checkStock = new Button("Check Stock");
        checkStock.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-cursor: hand;"
        );

        HBox notificationButtons = new HBox(8, dismiss, checkStock);
        notificationButtons.setAlignment(Pos.CENTER_RIGHT);

        notification.getChildren().addAll(aiHeader, notificationText, notificationSpacer, notificationButtons);
        hbright.getChildren().addAll(leftContent, notification);

        // Initialize Dynamic Recommendations with current day context
        initDefaultRecommendations(effectiveUserId, dayName);

        Runnable renderCurrentRecommendation = () -> {
            if (RECOMMENDATIONS.isEmpty()) return;
            if (currentRecIndex >= RECOMMENDATIONS.size()) currentRecIndex = 0;
            AIRecommendation rec = RECOMMENDATIONS.get(currentRecIndex);

            aiTitle.setText(rec.title);
            aiStatus.setText(rec.status);
            notificationText.setText(rec.message);
            checkStock.setText(rec.actionLabel);
            checkStock.setOnAction(e -> {
                if (rec.action != null) rec.action.run();
            });
        };

        renderCurrentRecommendation.run();

        // Manual cycle action
        cycleBtn.setOnAction(e -> {
            currentRecIndex = (currentRecIndex + 1) % Math.max(1, RECOMMENDATIONS.size());
            FadeTransition ft = new FadeTransition(Duration.millis(200), notificationText);
            ft.setFromValue(0.3);
            ft.setToValue(1.0);
            renderCurrentRecommendation.run();
            ft.play();
        });

        // Auto-rotation timer: smoothly cycles recommendations every 8 seconds
        Timeline autoCycleTimer = new Timeline(new KeyFrame(Duration.seconds(8), evt -> {
            if (!RECOMMENDATIONS.isEmpty()) {
                currentRecIndex = (currentRecIndex + 1) % RECOMMENDATIONS.size();
                FadeTransition ft = new FadeTransition(Duration.millis(250), notificationText);
                ft.setFromValue(0.2);
                ft.setToValue(1.0);
                renderCurrentRecommendation.run();
                ft.play();
            }
        }));
        autoCycleTimer.setCycleCount(Timeline.INDEFINITE);
        autoCycleTimer.play();

        // Pause rotation on hover so user can read / interact comfortably
        notification.setOnMouseEntered(e -> autoCycleTimer.pause());
        notification.setOnMouseExited(e -> autoCycleTimer.play());

        dismiss.setOnAction(e -> {
            autoCycleTimer.stop();
            notification.setVisible(false);
            notification.setManaged(false);
        });

        // Asynchronously fetch user purchase history, cart items & live products
        loadLiveProductRecommendations(effectiveUserId, dayName, () -> {
            Platform.runLater(renderCurrentRecommendation);
        });

        return hbright;
    }

    private static void initDefaultRecommendations(String userId, String dayName) {
        RECOMMENDATIONS.clear();

        // 1. Day of week pattern (matching mockup structure dynamically)
        RECOMMENDATIONS.add(new AIRecommendation(
                "AI Recommendation",
                "PERSONALIZED FOR YOU • " + dayName.toUpperCase(),
                "You usually order fresh essentials on " + dayName + "s.\n\nLocal neighborhood stores have your favorite brands in stock today.",
                "Check Stock",
                () -> CustomerNavigation.navigateToGroceries(userId)
        ));

        // 2. Best Deals & Offers
        RECOMMENDATIONS.add(new AIRecommendation(
                "Exclusive Daily Deals",
                "DISCOUNTS ACTIVE TODAY",
                "Special " + dayName + " Deals are live!\n\nSave up to 30% with verified promo codes on top products.",
                "View Deals",
                () -> CustomerNavigation.navigateToDeals(userId)
        ));

        // 3. Fast Neighborhood Delivery
        RECOMMENDATIONS.add(new AIRecommendation(
                "Hyper-Local Express",
                "STORES OPEN NEAR YOU",
                "Verified merchants in your area are currently accepting instant orders with 15-min delivery.",
                "Explore Shops",
                () -> CustomerNavigation.navigateToNearbyShops(userId)
        ));
    }

    private static void loadLiveProductRecommendations(String userId, String dayName, Runnable onLoaded) {
        new Thread(() -> {
            try {
                List<AIRecommendation> dynamicList = new ArrayList<>();

                // A. Check user's actual order history
                String userFavProduct = null;
                String userFavShop = null;
                try {
                    OrderDAO orderDAO = new OrderDAO();
                    List<OrderModel> orders = orderDAO.getCustomerOrders(userId);
                    if (orders != null && !orders.isEmpty()) {
                        for (OrderModel order : orders) {
                            if (order != null && order.getProducts() != null && !order.getProducts().isEmpty()) {
                                for (OrderItemModel item : order.getProducts()) {
                                    if (item != null && item.getProductName() != null && !item.getProductName().isBlank()) {
                                        userFavProduct = item.getProductName().trim();
                                        userFavShop = (item.getShopName() != null && !item.getShopName().isBlank())
                                                ? item.getShopName().trim()
                                                : ((order.getShopName() != null && !order.getShopName().isBlank())
                                                        ? order.getShopName().trim()
                                                        : CustomerShopResolver.getShopName(order.getShopkeeperUid()));
                                        break;
                                    }
                                }
                            }
                            if (userFavProduct != null) break;
                        }
                    }
                } catch (Exception e) {
                    // Ignore order history error
                }

                // B. Check user's current shopping cart
                int cartItemCount = 0;
                String cartFirstItemName = null;
                try {
                    CARTcontroller cartCtrl = new CARTcontroller();
                    List<Productcart> cartItems = cartCtrl.getCart(userId);
                    if (cartItems != null && !cartItems.isEmpty()) {
                        cartItemCount = cartItems.size();
                        cartFirstItemName = cartItems.get(0).getName();
                    }
                } catch (Exception e) {
                    // Ignore cart error
                }

                // C. Fetch catalog products and live deals
                ProductController controller = new ProductController();
                ArrayList<ProductModel> products = controller.fetchProducts();

                ProductModel topDeal = null;
                ProductModel sampleProduct = null;
                if (products != null && !products.isEmpty()) {
                    for (ProductModel p : products) {
                        if (p != null) {
                            if (sampleProduct == null) sampleProduct = p;
                            if (p.getDiscount() != null && p.getDiscount() > 0) {
                                if (topDeal == null || p.getDiscount() > topDeal.getDiscount()) {
                                    topDeal = p;
                                }
                            }
                        }
                    }
                }

                // 1. PRIMARY RECOMMENDATION: Personalized Restock Alert matching user mockup format!
                if (userFavProduct != null && userFavShop != null) {
                    dynamicList.add(new AIRecommendation(
                            "Smart Restock Alert",
                            "PERSONALIZED FOR YOU • " + dayName.toUpperCase(),
                            "You usually order " + userFavProduct + " on " + dayName + "s.\n\n" + userFavShop + " has your favorite brand in stock today.",
                            "Check Stock",
                            () -> CustomerNavigation.navigateToGroceries(userId)
                    ));
                } else if (sampleProduct != null) {
                    String prodName = sampleProduct.getProductName() != null ? sampleProduct.getProductName() : "daily essentials";
                    String shopName = CustomerShopResolver.getShopName(sampleProduct.getShopkeeperUid());
                    dynamicList.add(new AIRecommendation(
                            "AI Recommendation",
                            "PERSONALIZED FOR YOU • " + dayName.toUpperCase(),
                            "You usually order " + prodName + " on " + dayName + "s.\n\n" + shopName + " has your favorite brand in stock today.",
                            "Check Stock",
                            () -> CustomerNavigation.navigateToGroceries(userId)
                    ));
                }

                // 2. ACTIVE CART REMINDER (if cart has items)
                if (cartItemCount > 0) {
                    String itemHint = (cartFirstItemName != null && !cartFirstItemName.isBlank()) ? " including " + cartFirstItemName : "";
                    dynamicList.add(new AIRecommendation(
                            "Smart Cart Reminder",
                            "ITEMS SAVED IN CART",
                            "You have " + cartItemCount + " items waiting" + itemHint + ".\n\nComplete checkout today for instant same-day delivery to your doorstep.",
                            "Go to Cart",
                            () -> CustomerNavigation.navigateToCart(userId)
                    ));
                }

                // 3. LIVE FEATURED DEAL RECOMMENDATION
                if (topDeal != null) {
                    String dealTitle = topDeal.getProductName() != null ? topDeal.getProductName() : "Exclusive Offer";
                    String dealStore = CustomerShopResolver.getShopName(topDeal.getShopkeeperUid());
                    int disc = topDeal.getDiscount().intValue();
                    double price = topDeal.getSellingPrice() != null ? topDeal.getSellingPrice() : 0.0;
                    String priceText = price > 0 ? " for only ₹" + (int) price : "";

                    dynamicList.add(new AIRecommendation(
                            "🔥 Today's Top Deal",
                            disc + "% OFF • " + dayName.toUpperCase(),
                            dealTitle + " is on sale at " + dealStore + priceText + "!\n\nSave big on today's featured discount.",
                            "View Deal",
                            () -> CustomerNavigation.navigateToDeals(userId)
                    ));
                }

                // 4. HYPER-LOCAL NEARBY STORE RECOMMENDATION
                if (products != null && products.size() > 1) {
                    ProductModel second = products.get(1);
                    String store = CustomerShopResolver.getShopName(second.getShopkeeperUid());
                    dynamicList.add(new AIRecommendation(
                            "Express Local Delivery",
                            "OPEN STORES NEAR YOU",
                            store + " has popular " + (second.getCategory() != null ? second.getCategory() : "items") + " ready for fast 15-minute delivery.",
                            "Explore Shops",
                            () -> CustomerNavigation.navigateToNearbyShops(userId)
                    ));
                }

                if (!dynamicList.isEmpty()) {
                    Platform.runLater(() -> {
                        RECOMMENDATIONS.clear();
                        RECOMMENDATIONS.addAll(dynamicList);
                        if (onLoaded != null) onLoaded.run();
                    });
                }
            } catch (Exception e) {
                // Fallback safely retained
            }
        }).start();
    }
}

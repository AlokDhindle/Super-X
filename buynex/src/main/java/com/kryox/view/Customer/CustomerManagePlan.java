package com.kryox.view.Customer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import com.google.cloud.firestore.Firestore;
import com.kryox.config.Firebaseconfig;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CustomerManagePlan {

    public static class CustomerPlanState {
        public static String currentPlan = "Free"; // "Free", "Gold", "Platinum"
        public static String billingCycle = "Monthly";
        public static String renewalDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
        public static double monthlyPrice = 0.0;

        private static final Preferences PREFS = Preferences.userNodeForPackage(CustomerManagePlan.class);

        public static String getCurrentPlan(String userId) {
            if (userId != null && !userId.isBlank()) {
                String safeKey = userId.trim().replaceAll("[^a-zA-Z0-9_]", "_");
                String saved = PREFS.get("plan_" + safeKey, null);
                if (saved != null && !saved.isBlank()) {
                    currentPlan = saved;
                    billingCycle = PREFS.get("cycle_" + safeKey, billingCycle);
                    renewalDate = PREFS.get("date_" + safeKey, renewalDate);
                    monthlyPrice = PREFS.getDouble("price_" + safeKey, monthlyPrice);
                }
            }
            return currentPlan;
        }

        public static void setPlan(String userId, String planKey, String cycle, double price, String date) {
            currentPlan = planKey;
            billingCycle = cycle;
            monthlyPrice = price;
            renewalDate = date;

            if (userId != null && !userId.isBlank()) {
                String safeKey = userId.trim().replaceAll("[^a-zA-Z0-9_]", "_");
                PREFS.put("plan_" + safeKey, planKey);
                PREFS.put("cycle_" + safeKey, cycle);
                PREFS.put("date_" + safeKey, date);
                PREFS.putDouble("price_" + safeKey, price);
                try {
                    PREFS.flush();
                } catch (Exception ignored) {}

                if (userId.contains("@")) {
                    new Thread(() -> {
                        try {
                            Firestore db = Firebaseconfig.gFirestore();
                            if (db != null) {
                                Map<String, Object> update = new HashMap<>();
                                update.put("membershipPlan", planKey);
                                update.put("billingCycle", cycle);
                                update.put("planRenewalDate", date);
                                db.collection("User").document(userId.trim()).update(update);
                            }
                        } catch (Exception ignored) {}
                    }).start();
                }
            }
        }
    }

    private final String userId;
    private Runnable backCallback;
    private boolean isYearly = false;

    private StackPane rootStack;
    private VBox toastBox;
    private Label toastLabel;
    private VBox activeMembershipCard;
    private HBox planCardsRow;

    public CustomerManagePlan(String userId) {
        this.userId = (userId != null && !userId.isBlank()) ? userId : "guest";
        CustomerPlanState.getCurrentPlan(this.userId);
    }

    public Scene getManagePlanScene(Runnable onBack) {
        this.backCallback = onBack;

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #F8F6F8;");

        // Top Navigation Bar
        HBox navBar = createNavBar();
        borderPane.setTop(navBar);

        // Center Scroll Content
        VBox contentBox = new VBox(24);
        contentBox.setPadding(new Insets(24, 38, 40, 38));
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setStyle("-fx-background-color: #F8F6F8;");

        // 1. Header Banner
        VBox heroBanner = createHeroBanner();

        // 2. Current Active Membership Overview Card
        activeMembershipCard = createActiveMembershipCard();

        // 3. Billing Switcher (Monthly / Annual)
        HBox billingToggle = createBillingToggle();

        // 4. Three Tier Cards (Free, Gold, Platinum VIP)
        planCardsRow = createPlanCardsRow();

        // 5. Perks & Benefits Comparison Table
        VBox comparisonSection = createComparisonSection();

        // 6. FAQ Section
        VBox faqSection = createFAQSection();

        contentBox.getChildren().addAll(
                heroBanner,
                activeMembershipCard,
                billingToggle,
                planCardsRow,
                comparisonSection,
                faqSection
        );

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");

        borderPane.setCenter(scrollPane);

        rootStack = new StackPane();
        rootStack.getChildren().add(borderPane);

        // Setup Floating Toast Notification
        setupToast();

        return new Scene(rootStack, 1550, 850);
    }

    private HBox createNavBar() {
        HBox nav = new HBox(16);
        nav.setPadding(new Insets(14, 28, 14, 28));
        nav.setAlignment(Pos.CENTER_LEFT);
        nav.setStyle("-fx-background-color: #ebccb7; -fx-border-color: #dfc1ac; -fx-border-width: 0 0 1 0;");

        Button backBtn = new Button("← Back to Dashboard");
        backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #dfc1ac;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 8 16 8 16;" +
                "-fx-cursor: hand;"
        );
        backBtn.setOnAction(e -> {
            if (backCallback != null) {
                backCallback.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
        });

        Label brand = new Label("BuyNeX Privilege");
        brand.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #FF6900;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label membershipTag = new Label("★ VIP MEMBERSHIP CLUB");
        membershipTag.setStyle(
                "-fx-background-color: #503629;" +
                "-fx-text-fill: #FF9D67;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 6 14 6 14;"
        );

        nav.getChildren().addAll(backBtn, brand, spacer, membershipTag);
        return nav;
    }

    private VBox createHeroBanner() {
        VBox banner = new VBox(8);
        banner.setMaxWidth(1100);
        banner.setAlignment(Pos.CENTER);
        banner.setPadding(new Insets(24, 20, 16, 20));

        Label topPill = new Label("✦  UPGRADE YOUR SHOPPING EXPERIENCE");
        topPill.setStyle(
                "-fx-background-color: rgba(255,105,0,0.12);" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: 800;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 6 14 6 14;"
        );

        Text title = new Text("Choose Your BuyNeX Membership Plan");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: 900;" +
                "-fx-fill: #222222;"
        );

        Text sub = new Text("Unlock unlimited free 15-min delivery, exclusive extra store discounts, zero platform fees, and dedicated AI concierge.");
        sub.setStyle("-fx-font-size: 13px; -fx-fill: #666666;");

        banner.getChildren().addAll(topPill, title, sub);
        return banner;
    }

    private VBox createActiveMembershipCard() {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(14);
        shadow.setOffsetY(4);
        shadow.setColor(Color.rgb(0, 0, 0, 0.06));

        VBox card = new VBox(14);
        card.setMaxWidth(1100);
        card.setPadding(new Insets(20, 26, 20, 26));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #EFE8E3;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1.2;"
        );
        card.setEffect(shadow);

        HBox topRow = new HBox(12);
        topRow.setAlignment(Pos.CENTER_LEFT);

        Label currentBadge = new Label("CURRENT MEMBERSHIP");
        currentBadge.setStyle("-fx-font-size: 9px; -fx-font-weight: 800; -fx-text-fill: #888888;");

        Region topSpacer = new Region();
        HBox.setHgrow(topSpacer, Priority.ALWAYS);

        Label statusPill = new Label("● ACTIVE");
        statusPill.setStyle(
                "-fx-background-color: #E6F8EB;" +
                "-fx-text-fill: #1B8A3D;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: 800;" +
                "-fx-background-radius: 14;" +
                "-fx-padding: 4 10 4 10;"
        );

        topRow.getChildren().addAll(currentBadge, topSpacer, statusPill);

        HBox mainRow = new HBox(24);
        mainRow.setAlignment(Pos.CENTER_LEFT);

        VBox planInfo = new VBox(4);
        String planDisplay = "Gold".equalsIgnoreCase(CustomerPlanState.currentPlan)
                ? "BuyNeX Gold Privilege"
                : ("Platinum".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "BuyNeX Platinum VIP" : "Standard Shopper");

        Label planTitle = new Label(planDisplay);
        planTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 19px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + ("Free".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "#333333" : "#FF6900") + ";"
        );

        String subtitleText = "Free".equalsIgnoreCase(CustomerPlanState.currentPlan)
                ? "Standard shopping tier with basic public deals and delivery rates."
                : "Active " + CustomerPlanState.billingCycle + " membership • Renews on " + CustomerPlanState.renewalDate;

        Label planSub = new Label(subtitleText);
        planSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        planInfo.getChildren().addAll(planTitle, planSub);

        Region infoSpacer = new Region();
        HBox.setHgrow(infoSpacer, Priority.ALWAYS);

        // Perks Chips
        HBox perksBox = new HBox(10);
        perksBox.setAlignment(Pos.CENTER_RIGHT);

        String p1 = "Gold".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "✓ Free 15-Min Delivery" : ("Platinum".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "✓ Unlimited Free Delivery" : "Standard Delivery");
        String p2 = "Gold".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "✓ Extra 15% Off Deals" : ("Platinum".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "✓ Flat 25% Off Stores" : "Standard Deals");
        String p3 = "Free".equalsIgnoreCase(CustomerPlanState.currentPlan) ? "Standard Support" : "✓ 24/7 AI Concierge";

        perksBox.getChildren().addAll(createPill(p1, true), createPill(p2, true), createPill(p3, true));

        mainRow.getChildren().addAll(planInfo, infoSpacer, perksBox);
        card.getChildren().addAll(topRow, mainRow);

        return card;
    }

    private Label createPill(String text, boolean active) {
        Label pill = new Label(text);
        pill.setStyle(
                "-fx-background-color: " + (active ? "#FFF3EB" : "#F0ECE9") + ";" +
                "-fx-text-fill: " + (active ? "#FF6900" : "#777777") + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 6 12 6 12;"
        );
        return pill;
    }

    private HBox createBillingToggle() {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(6, 0, 8, 0));

        Label monthlyLabel = new Label("Monthly Billing");
        monthlyLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: " + (!isYearly ? "900" : "500") + "; -fx-text-fill: " + (!isYearly ? "#222222" : "#888888") + ";");

        Button switchBtn = new Button(isYearly ? "●  Annual (Save 25%)" : "Monthly  ●");
        switchBtn.setStyle(
                "-fx-background-color: " + (isYearly ? "#FF6900" : "#333336") + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 7 18 7 18;" +
                "-fx-cursor: hand;"
        );
        switchBtn.setOnAction(e -> {
            isYearly = !isYearly;
            refreshPlanCards();
        });

        Label yearlyLabel = new Label("Annual Billing (2 Months Free)");
        yearlyLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: " + (isYearly ? "900" : "500") + "; -fx-text-fill: " + (isYearly ? "#FF6900" : "#888888") + ";");

        row.getChildren().addAll(monthlyLabel, switchBtn, yearlyLabel);
        return row;
    }

    private HBox createPlanCardsRow() {
        HBox row = new HBox(22);
        row.setMaxWidth(1100);
        row.setAlignment(Pos.CENTER);

        // Tier 1: Standard Shopper (Free)
        VBox cardFree = createTierCard(
                "Standard Shopper",
                "Free",
                0,
                0,
                "Basic access to local shops and products.",
                new String[]{
                        "✓ Access to all neighborhood stores",
                        "✓ Standard delivery rates & timing",
                        "✓ Access to public deals & promotions",
                        "✕ No free express 15-min delivery",
                        "✕ No extra member discounts",
                        "✕ Standard platform & convenience fee"
                },
                "Free".equalsIgnoreCase(CustomerPlanState.currentPlan),
                false,
                false
        );

        // Tier 2: BuyNeX Gold (Most Popular)
        VBox cardGold = createTierCard(
                "BuyNeX Gold",
                "Gold",
                99,
                79,
                "Smarter deals, free delivery & exclusive rewards.",
                new String[]{
                        "✓ Unlimited Free 15-Min Delivery (Above ₹149)",
                        "✓ Extra 15% OFF on all Deals & Groceries",
                        "✓ Zero Platform & Packaging Fees",
                        "✓ 24/7 Priority AI Shopping Concierge",
                        "✓ 2X BuyNeX Reward Coins on every order",
                        "✓ Early access to flash promotions"
                },
                "Gold".equalsIgnoreCase(CustomerPlanState.currentPlan),
                true,
                false
        );

        // Tier 3: BuyNeX Platinum VIP (Elite)
        VBox cardPlatinum = createTierCard(
                "BuyNeX Platinum VIP",
                "Platinum",
                199,
                149,
                "Ultimate luxury shopping with personal concierge.",
                new String[]{
                        "✓ 100% Free Express Delivery (No min. order)",
                        "✓ Flat 25% OFF across all verified shops",
                        "✓ Dedicated Personal VIP Shopper & Butler",
                        "✓ 3X BuyNeX Reward Coins on every purchase",
                        "✓ Surprise Monthly Gift Box on orders > ₹500",
                        "✓ Instant no-questions-asked refund policy"
                },
                "Platinum".equalsIgnoreCase(CustomerPlanState.currentPlan),
                false,
                true
        );

        HBox.setHgrow(cardFree, Priority.ALWAYS);
        HBox.setHgrow(cardGold, Priority.ALWAYS);
        HBox.setHgrow(cardPlatinum, Priority.ALWAYS);

        row.getChildren().addAll(cardFree, cardGold, cardPlatinum);
        return row;
    }

    private VBox createTierCard(
            String title,
            String planKey,
            int monthlyRate,
            int yearlyRate,
            String description,
            String[] features,
            boolean isCurrent,
            boolean isPopular,
            boolean isVip
    ) {
        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(isPopular ? 20 : 12);
        cardShadow.setOffsetY(isPopular ? 8 : 4);
        cardShadow.setColor(Color.rgb(0, 0, 0, isPopular ? 0.12 : 0.05));

        VBox card = new VBox(14);
        card.setPadding(new Insets(24));
        card.setAlignment(Pos.TOP_LEFT);
        card.setEffect(cardShadow);

        String borderColor = isPopular ? "#FF6900" : (isVip ? "#4A3B32" : "#E2D9D2");
        double borderWidth = isPopular ? 2.0 : 1.0;

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: " + borderColor + ";" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: " + borderWidth + ";"
        );

        // Badge Row
        HBox badgeRow = new HBox(8);
        badgeRow.setAlignment(Pos.CENTER_LEFT);

        if (isPopular) {
            Label popLabel = new Label("🔥 MOST POPULAR • GOLD PRIVILEGE");
            popLabel.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: 900;" +
                    "-fx-background-radius: 12;" +
                    "-fx-padding: 4 10 4 10;"
            );
            badgeRow.getChildren().add(popLabel);
        } else if (isVip) {
            Label vipLabel = new Label("👑 ELITE VIP");
            vipLabel.setStyle(
                    "-fx-background-color: #333336;" +
                    "-fx-text-fill: #FFD700;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: 900;" +
                    "-fx-background-radius: 12;" +
                    "-fx-padding: 4 10 4 10;"
            );
            badgeRow.getChildren().add(vipLabel);
        } else {
            Label defLabel = new Label("STANDARD");
            defLabel.setStyle(
                    "-fx-background-color: #EDE8E5;" +
                    "-fx-text-fill: #777777;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: 900;" +
                    "-fx-background-radius: 12;" +
                    "-fx-padding: 4 10 4 10;"
            );
            badgeRow.getChildren().add(defLabel);
        }

        // Title & Description
        Label cardTitle = new Label(title);
        cardTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #222222;"
        );

        Label cardDesc = new Label(description);
        cardDesc.setStyle("-fx-font-size: 10.5px; -fx-text-fill: #777777;");
        cardDesc.setWrapText(true);

        // Price Row
        int effectivePrice = isYearly ? yearlyRate : monthlyRate;
        HBox priceBox = new HBox(4);
        priceBox.setAlignment(Pos.BASELINE_LEFT);

        Label currency = new Label("₹");
        currency.setStyle("-fx-font-size: 16px; -fx-font-weight: 900; -fx-text-fill: #222222;");

        Label priceText = new Label(String.valueOf(effectivePrice));
        priceText.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 32px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: " + (isPopular ? "#FF6900" : "#222222") + ";"
        );

        Label period = new Label("/month" + (isYearly ? " (billed yearly)" : ""));
        period.setStyle("-fx-font-size: 10px; -fx-text-fill: #888888;");

        priceBox.getChildren().addAll(currency, priceText, period);

        // Action Button
        Button actionBtn = new Button();
        actionBtn.setMaxWidth(Double.MAX_VALUE);
        actionBtn.setPrefHeight(38);

        if (isCurrent) {
            actionBtn.setText("✓ Current Membership");
            actionBtn.setStyle(
                    "-fx-background-color: #E8F5E9;" +
                    "-fx-text-fill: #2E7D32;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 12;"
            );
            actionBtn.setDisable(true);
        } else {
            actionBtn.setText("Upgrade to " + title);
            String btnBg = isPopular
                    ? "linear-gradient(to right, #FF6900, #FF9548)"
                    : (isVip ? "linear-gradient(to right, #333336, #504D4B)" : "#45464B");

            actionBtn.setStyle(
                    "-fx-background-color: " + btnBg + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 12;" +
                    "-fx-cursor: hand;"
            );
            actionBtn.setOnAction(e -> showSubscribeModal(title, planKey, effectivePrice, isYearly));
        }

        // Features List
        VBox featureList = new VBox(9);
        featureList.setPadding(new Insets(10, 0, 0, 0));

        for (String feat : features) {
            Label fLabel = new Label(feat);
            boolean isNegative = feat.startsWith("✕");
            fLabel.setStyle(
                    "-fx-font-size: 10.5px;" +
                    "-fx-text-fill: " + (isNegative ? "#AAAAAA" : "#444444") + ";" +
                    "-fx-font-weight: " + (isNegative ? "normal" : "500") + ";"
            );
            featureList.getChildren().add(fLabel);
        }

        card.getChildren().addAll(badgeRow, cardTitle, cardDesc, priceBox, actionBtn, featureList);
        return card;
    }

    private void showSubscribeModal(String planTitle, String planKey, int price, boolean yearly) {
        StackPane overlay = new StackPane();
        overlay.setStyle("-fx-background-color: rgba(0,0,0,0.55);");

        DropShadow modalShadow = new DropShadow();
        modalShadow.setRadius(24);
        modalShadow.setColor(Color.rgb(0, 0, 0, 0.25));

        VBox modal = new VBox(16);
        modal.setMaxWidth(480);
        modal.setPadding(new Insets(26));
        modal.setAlignment(Pos.TOP_LEFT);
        modal.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: rgba(255,255,255,0.8);" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1;"
        );
        modal.setEffect(modalShadow);

        Label modalTitle = new Label("Activate " + planTitle);
        modalTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #222222;"
        );

        Label cycleText = new Label("Billing Cycle: " + (yearly ? "Annual (Billed upfront with 25% discount)" : "Monthly recurring billing"));
        cycleText.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        // Price breakdown
        int baseAmount = yearly ? (price * 12) : price;
        double gst = Math.round(baseAmount * 0.18 * 100.0) / 100.0;
        double totalDue = baseAmount + gst;

        VBox breakdownBox = new VBox(8);
        breakdownBox.setPadding(new Insets(14));
        breakdownBox.setStyle("-fx-background-color: #F8F6F5; -fx-background-radius: 12;");

        breakdownBox.getChildren().addAll(
                createRow("Plan Subtotal:", "₹" + baseAmount),
                createRow("GST (18%):", "₹" + gst),
                createRow("VIP Member Discount:", "-₹0.00"),
                createRowBold("Total Payable:", "₹" + totalDue)
        );

        // Payment Method Radio Options
        Label methodLabel = new Label("Select Payment Method");
        methodLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        ToggleGroup payGroup = new ToggleGroup();
        RadioButton upiRadio = new RadioButton("UPI (Google Pay, PhonePe, Paytm)");
        upiRadio.setToggleGroup(payGroup);
        upiRadio.setSelected(true);
        upiRadio.setStyle("-fx-font-size: 11px; -fx-cursor: hand;");

        RadioButton cardRadio = new RadioButton("Credit / Debit Card (Visa, Mastercard, RuPay)");
        cardRadio.setToggleGroup(payGroup);
        cardRadio.setStyle("-fx-font-size: 11px; -fx-cursor: hand;");

        RadioButton walletRadio = new RadioButton("BuyNeX Cash & Net Banking");
        walletRadio.setToggleGroup(payGroup);
        walletRadio.setStyle("-fx-font-size: 11px; -fx-cursor: hand;");

        VBox methodsBox = new VBox(8, upiRadio, cardRadio, walletRadio);

        // Buttons Row
        HBox btnRow = new HBox(12);
        btnRow.setAlignment(Pos.CENTER_RIGHT);
        btnRow.setPadding(new Insets(10, 0, 0, 0));

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #777777;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );
        cancelBtn.setOnAction(e -> rootStack.getChildren().remove(overlay));

        Button confirmBtn = new Button("Confirm & Pay ₹" + (int) totalDue);
        confirmBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 12;" +
                "-fx-padding: 9 18 9 18;" +
                "-fx-cursor: hand;"
        );
        confirmBtn.setOnAction(e -> {
            String date = LocalDate.now().plusMonths(yearly ? 12 : 1).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));
            CustomerPlanState.setPlan(userId, planKey, yearly ? "Annual" : "Monthly", price, date);

            rootStack.getChildren().remove(overlay);
            refreshPlanCards();
            showToast("🎉 Congratulations! You have successfully upgraded to " + planTitle + ".");
        });

        btnRow.getChildren().addAll(cancelBtn, confirmBtn);

        modal.getChildren().addAll(
                modalTitle,
                cycleText,
                breakdownBox,
                methodLabel,
                methodsBox,
                btnRow
        );

        overlay.getChildren().add(modal);
        rootStack.getChildren().add(overlay);
    }

    private HBox createRow(String left, String right) {
        HBox r = new HBox();
        Label l = new Label(left);
        l.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label v = new Label(right);
        v.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        r.getChildren().addAll(l, sp, v);
        return r;
    }

    private HBox createRowBold(String left, String right) {
        HBox r = new HBox();
        Label l = new Label(left);
        l.setStyle("-fx-font-size: 12px; -fx-font-weight: 800; -fx-text-fill: #222222;");
        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);
        Label v = new Label(right);
        v.setStyle("-fx-font-size: 14px; -fx-font-weight: 900; -fx-text-fill: #FF6900;");
        r.getChildren().addAll(l, sp, v);
        return r;
    }

    private void refreshPlanCards() {
        VBox parent = (VBox) activeMembershipCard.getParent();
        if (parent != null) {
            int idxActive = parent.getChildren().indexOf(activeMembershipCard);
            int idxCards = parent.getChildren().indexOf(planCardsRow);

            VBox newActive = createActiveMembershipCard();
            HBox newCards = createPlanCardsRow();

            parent.getChildren().set(idxActive, newActive);
            parent.getChildren().set(idxCards, newCards);

            this.activeMembershipCard = newActive;
            this.planCardsRow = newCards;
        }
    }

    private VBox createComparisonSection() {
        VBox box = new VBox(14);
        box.setMaxWidth(1100);
        box.setPadding(new Insets(24, 26, 24, 26));
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: #EFE8E3;" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;"
        );

        Label title = new Label("Perks & Benefits Breakdown");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        VBox table = new VBox(6);
        table.getChildren().addAll(
                createComparisonRow("Benefit", "Standard", "BuyNeX Gold", "Platinum VIP", true),
                createComparisonRow("Express 15-Min Delivery", "₹29 - ₹49 fee", "FREE above ₹149", "100% FREE Always", false),
                createComparisonRow("Store Deals Discount", "Public Deals", "Extra 15% OFF", "Flat 25% OFF", false),
                createComparisonRow("Platform & Service Fee", "Standard ₹5", "Zero (Waived)", "Zero (Waived)", false),
                createComparisonRow("AI Shopping Concierge", "Basic Queries", "24/7 Smart Assistant", "Dedicated Butler", false),
                createComparisonRow("Reward Coins Earning", "1X Coins", "2X Coins", "3X Coins + Gifts", false)
        );

        box.getChildren().addAll(title, table);
        return box;
    }

    private HBox createComparisonRow(String col1, String col2, String col3, String col4, boolean isHeader) {
        HBox r = new HBox(12);
        r.setPadding(new Insets(8, 12, 8, 12));
        r.setStyle(
                isHeader
                        ? "-fx-background-color: #F6F3F1; -fx-background-radius: 8;"
                        : "-fx-border-color: #F0ECE9; -fx-border-width: 0 0 1 0;"
        );

        Label l1 = new Label(col1);
        l1.setPrefWidth(260);
        l1.setStyle("-fx-font-size: 11px; -fx-font-weight: " + (isHeader ? "900" : "bold") + "; -fx-text-fill: " + (isHeader ? "#222222" : "#333333") + ";");

        Label l2 = new Label(col2);
        l2.setPrefWidth(220);
        l2.setStyle("-fx-font-size: 11px; -fx-font-weight: " + (isHeader ? "900" : "500") + "; -fx-text-fill: " + (isHeader ? "#222222" : "#666666") + ";");

        Label l3 = new Label(col3);
        l3.setPrefWidth(220);
        l3.setStyle("-fx-font-size: 11px; -fx-font-weight: " + (isHeader ? "900" : "bold") + "; -fx-text-fill: " + (isHeader ? "#222222" : "#FF6900") + ";");

        Label l4 = new Label(col4);
        l4.setPrefWidth(220);
        l4.setStyle("-fx-font-size: 11px; -fx-font-weight: " + (isHeader ? "900" : "bold") + "; -fx-text-fill: " + (isHeader ? "#222222" : "#8E44AD") + ";");

        r.getChildren().addAll(l1, l2, l3, l4);
        return r;
    }

    private VBox createFAQSection() {
        VBox box = new VBox(12);
        box.setMaxWidth(1100);
        box.setPadding(new Insets(16, 26, 24, 26));

        Label title = new Label("Frequently Asked Questions");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 800;" +
                "-fx-text-fill: #222222;"
        );

        box.getChildren().addAll(
                title,
                createFaqItem("How does the Free 15-Minute Delivery work?", "Gold and Platinum members enjoy automated fee waivers on orders above ₹149, serviced directly by verified neighborhood express riders."),
                createFaqItem("Can I switch from Monthly to Annual billing anytime?", "Yes, you can upgrade to Annual billing at any time to lock in the 25% discount, and any remaining days will be prorated automatically."),
                createFaqItem("Are Gold discounts applicable on top of existing shop discounts?", "Absolutely! Gold and Platinum discounts stack with existing shopkeeper offers and coupons in your shopping cart.")
        );

        return box;
    }

    private VBox createFaqItem(String question, String answer) {
        VBox item = new VBox(4);
        item.setPadding(new Insets(8, 0, 8, 0));

        Label q = new Label("Q: " + question);
        q.setStyle("-fx-font-size: 11.5px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        Label a = new Label(answer);
        a.setStyle("-fx-font-size: 10.5px; -fx-text-fill: #666666;");
        a.setWrapText(true);

        item.getChildren().addAll(q, a);
        return item;
    }

    private void setupToast() {
        toastBox = new VBox();
        toastBox.setMaxWidth(450);
        toastBox.setPadding(new Insets(14, 22, 14, 22));
        toastBox.setAlignment(Pos.CENTER);
        toastBox.setStyle(
                "-fx-background-color: #242529;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 16;" +
                "-fx-border-width: 1.5;"
        );

        toastLabel = new Label();
        toastLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: white;");
        toastLabel.setWrapText(true);

        toastBox.getChildren().add(toastLabel);
        toastBox.setVisible(false);
        toastBox.setMouseTransparent(true);

        StackPane.setAlignment(toastBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(toastBox, new Insets(0, 0, 36, 0));

        rootStack.getChildren().add(toastBox);
    }

    private void showToast(String message) {
        toastLabel.setText(message);
        toastBox.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), toastBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition stay = new PauseTransition(Duration.seconds(3.5));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), toastBox);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> toastBox.setVisible(false));

        fadeIn.setOnFinished(e -> stay.play());
        stay.setOnFinished(e -> fadeOut.play());

        fadeIn.play();
    }
}

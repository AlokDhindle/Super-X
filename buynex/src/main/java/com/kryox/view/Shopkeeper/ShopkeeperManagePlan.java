package com.kryox.view.Shopkeeper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.kryox.dao.Shopkeeper.ShopkeeperDAO;
import com.kryox.model.Shopkeeper.ShopkeeperModel;
import com.kryox.view.Customer.Homepage;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class ShopkeeperManagePlan {

    private static boolean isYearly = false;
    private static StackPane rootStack;
    private static VBox toastBox;
    private static Label toastLabel;
    private static VBox activeSubscriptionCard;
    private static HBox planCardsRow;

    public static Scene managePlanScene() {
        BorderPane borderPane = new BorderPane();

        // 1. Header
        HBox headerMainBox = ViewConstants.header();
        headerMainBox.setStyle("-fx-background-color: #EBCCB7; -fx-border-color: #E3C7BA; -fx-border-width: 0 0 1px 0;");
        borderPane.setTop(headerMainBox);

        // 2. Sidebar
        VBox sidebar = ShopkeeperProfile.createSidebar();
        borderPane.setLeft(sidebar);

        // 3. Footer
        VBox footerBox = ViewConstants.footer();
        borderPane.setBottom(footerBox);

        // 4. Center Content Container
        VBox centerContent = new VBox(22);
        centerContent.setPadding(new Insets(24, 32, 32, 32));
        centerContent.setStyle("-fx-background-color: #EEE5DF;");

        // Top Navigation & Title Row
        VBox topHeaderBox = createTopHeader();

        // Current Active Plan Summary Card
        activeSubscriptionCard = createActiveSubscriptionCard();

        // Billing Cycle Toggle (Monthly vs Yearly)
        HBox billingToggleRow = createBillingToggle();

        // Plan Comparison Cards Row
        planCardsRow = createPlanCardsRow();

        // Billing History & Invoices
        VBox billingHistorySection = createBillingHistorySection();

        // FAQ & Perks Section
        VBox faqSection = createFAQSection();

        centerContent.getChildren().addAll(
                topHeaderBox,
                activeSubscriptionCard,
                billingToggleRow,
                planCardsRow,
                billingHistorySection,
                faqSection
        );

        ScrollPane scrollPane = new ScrollPane(centerContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: #EEE5DF; -fx-border-color: transparent;");

        borderPane.setCenter(scrollPane);

        // Root StackPane to support modal overlays and toasts
        rootStack = new StackPane();
        rootStack.getChildren().add(borderPane);

        // Floating Toast Notification Setup
        toastBox = new VBox();
        toastBox.setAlignment(Pos.CENTER);
        toastBox.setPadding(new Insets(12, 24, 12, 24));
        toastBox.setMaxWidth(480);
        toastBox.setMaxHeight(50);
        toastBox.setStyle(
                "-fx-background-color: #1E4620;" +
                "-fx-background-radius: 24px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.25), 10, 0, 0, 4);"
        );
        toastLabel = new Label();
        toastLabel.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-font-weight: bold;");
        toastBox.getChildren().add(toastLabel);
        toastBox.setVisible(false);
        toastBox.setOpacity(0);
        StackPane.setAlignment(toastBox, Pos.TOP_CENTER);
        StackPane.setMargin(toastBox, new Insets(85, 0, 0, 0));
        rootStack.getChildren().add(toastBox);

        Scene scene = new Scene(rootStack, 1550, 850);
        scene.setFill(Color.web("#EEE5DF"));
        return scene;
    }

    private static VBox createTopHeader() {
        VBox headerBox = new VBox(10);

        // Back to Profile button & breadcrumbs
        HBox navRow = new HBox(14);
        navRow.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back to Profile");
        backButton.setPrefHeight(34);
        backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B84200;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-color: #B84200;" +
                "-fx-border-radius: 17px;" +
                "-fx-border-width: 1.2px;" +
                "-fx-padding: 6 16;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e -> backButton.setStyle(
                "-fx-background-color: #B84200;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-color: #B84200;" +
                "-fx-border-radius: 17px;" +
                "-fx-border-width: 1.2px;" +
                "-fx-padding: 6 16;" +
                "-fx-cursor: hand;"
        ));
        backButton.setOnMouseExited(e -> backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B84200;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 17px;" +
                "-fx-border-color: #B84200;" +
                "-fx-border-radius: 17px;" +
                "-fx-border-width: 1.2px;" +
                "-fx-padding: 6 16;" +
                "-fx-cursor: hand;"
        ));
        backButton.setOnAction(e -> {
            Homepage.HomepageStage.setScene(ShopkeeperProfile.profileScene());
        });

        Label breadcrumb = new Label("Merchant Account  /  Profile  /  Manage Subscription");
        breadcrumb.setStyle("-fx-font-size: 12px; -fx-text-fill: #694B3D;");

        navRow.getChildren().addAll(backButton, breadcrumb);

        Text pageTitle = new Text("Manage Subscription Plan");
        pageTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text pageSubtitle = new Text(
                "Choose the right plan to expand your store catalog, unlock AI chatbot assistant, create promotional deals, and maximize revenue."
        );
        pageSubtitle.setStyle("-fx-font-size: 13px; -fx-fill: #694B3D;");

        headerBox.getChildren().addAll(navRow, pageTitle, pageSubtitle);
        return headerBox;
    }

    private static VBox createActiveSubscriptionCard() {
        VBox card = new VBox(14);
        card.setPadding(new Insets(20, 24, 20, 24));
        card.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #E8E2EA;" +
                "-fx-border-radius: 14px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(105, 75, 61, 0.08), 8, 0, 0, 3);"
        );

        String currentPlanName = getCurrentPlanName();
        String currentAmount = getCurrentPlanAmount();
        String currentRenewal = getCurrentPlanRenewal();
        String currentCycle = getCurrentPlanCycle();

        HBox topRow = new HBox(16);
        topRow.setAlignment(Pos.CENTER_LEFT);

        // Icon badge
        StackPane iconBadge = new StackPane();
        iconBadge.setPrefSize(48, 48);
        iconBadge.setStyle("-fx-background-color: #FFE5D4; -fx-background-radius: 12px;");
        Text icon = new Text("👑");
        icon.setStyle("-fx-font-size: 24px;");
        iconBadge.getChildren().add(icon);

        VBox planInfo = new VBox(3);
        HBox titleStatusRow = new HBox(10);
        titleStatusRow.setAlignment(Pos.CENTER_LEFT);

        Text planTitle = new Text("Merchant " + currentPlanName + " Subscription");
        planTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #171717;");

        Label statusBadge = new Label("● ACTIVE");
        statusBadge.setStyle(
                "-fx-background-color: #E6F4EA;" +
                "-fx-text-fill: #137333;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 3 9;" +
                "-fx-background-radius: 10px;"
        );
        titleStatusRow.getChildren().addAll(planTitle, statusBadge);

        Text subtitle = new Text("Billed " + currentCycle + " • Auto-renews on " + currentRenewal + " • Current rate: " + currentAmount);
        subtitle.setStyle("-fx-font-size: 12px; -fx-fill: #694B3D;");
        planInfo.getChildren().addAll(titleStatusRow, subtitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button cancelPlanBtn = new Button("Cancel Auto-Renew");
        cancelPlanBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #A62B0A;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #A62B0A;" +
                "-fx-border-radius: 8px;" +
                "-fx-padding: 6 12;" +
                "-fx-cursor: hand;"
        );
        cancelPlanBtn.setOnAction(e -> {
            showToast("Auto-renewal has been paused. Your current plan remains active until " + currentRenewal + ".");
        });

        topRow.getChildren().addAll(iconBadge, planInfo, spacer, cancelPlanBtn);

        // Quota & Feature Meters
        HBox metersRow = new HBox(20);
        metersRow.setPadding(new Insets(12, 16, 12, 16));
        metersRow.setStyle("-fx-background-color: #F7F1EE; -fx-background-radius: 10px;");

        int productLimit = currentPlanName.equalsIgnoreCase("Starter") ? 30 :
                           currentPlanName.equalsIgnoreCase("Pro") ? 500 : 9999;
        String productLimitText = productLimit > 5000 ? "Unlimited" : "42 / " + productLimit + " items";
        double productProgress = currentPlanName.equalsIgnoreCase("Starter") ? (42.0 / 30.0) : (42.0 / 500.0);
        if (productProgress > 1.0) productProgress = 1.0;

        VBox meter1 = createMeter("Product Catalog Quota", productLimitText, productProgress);
        VBox meter2 = createMeter("Platform Transaction Fee", currentPlanName.equalsIgnoreCase("Starter") ? "5.0%" : currentPlanName.equalsIgnoreCase("Pro") ? "1.5%" : "0.0% (Zero Fee)", 1.0);
        VBox meter3 = createMeter("AI Chatbot Queries", currentPlanName.equalsIgnoreCase("Starter") ? "Locked" : "Unlimited", 1.0);
        VBox meter4 = createMeter("Deals & Promo Coupons", currentPlanName.equalsIgnoreCase("Starter") ? "Locked" : "Active & Unlimited", 1.0);

        HBox.setHgrow(meter1, Priority.ALWAYS);
        HBox.setHgrow(meter2, Priority.ALWAYS);
        HBox.setHgrow(meter3, Priority.ALWAYS);
        HBox.setHgrow(meter4, Priority.ALWAYS);

        metersRow.getChildren().addAll(meter1, meter2, meter3, meter4);

        card.getChildren().addAll(topRow, metersRow);
        return card;
    }

    private static VBox createMeter(String label, String value, double progress) {
        VBox box = new VBox(4);
        Text l = new Text(label);
        l.setStyle("-fx-font-size: 11px; -fx-fill: #694B3D; -fx-font-weight: bold;");

        Text v = new Text(value);
        v.setStyle("-fx-font-size: 13px; -fx-fill: #171717; -fx-font-weight: bold;");

        ProgressBar bar = new ProgressBar(progress);
        bar.setPrefWidth(220);
        bar.setPrefHeight(6);
        bar.setStyle("-fx-accent: #B84200;");

        box.getChildren().addAll(l, v, bar);
        return box;
    }

    private static HBox createBillingToggle() {
        HBox toggleRow = new HBox(12);
        toggleRow.setAlignment(Pos.CENTER);
        toggleRow.setPadding(new Insets(10, 0, 10, 0));

        Label monthlyLabel = new Label("Monthly Billing");
        monthlyLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: " + (!isYearly ? "#B84200" : "#694B3D") + ";");

        Button switchBtn = new Button(isYearly ? "● Yearly" : "Monthly ●");
        switchBtn.setPrefWidth(120);
        switchBtn.setPrefHeight(34);
        switchBtn.setStyle(
                "-fx-background-color: #B84200;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20px;" +
                "-fx-cursor: hand;"
        );

        Label yearlyLabel = new Label("Annual Billing");
        yearlyLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: " + (isYearly ? "#B84200" : "#694B3D") + ";");

        Label discountPill = new Label("SAVE 20% ✨");
        discountPill.setStyle(
                "-fx-background-color: #FFDCC7;" +
                "-fx-text-fill: #A62B0A;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 3 8;" +
                "-fx-background-radius: 12px;"
        );

        switchBtn.setOnAction(e -> {
            isYearly = !isYearly;
            switchBtn.setText(isYearly ? "● Yearly" : "Monthly ●");
            monthlyLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: " + (!isYearly ? "#B84200" : "#694B3D") + ";");
            yearlyLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: " + (isYearly ? "#B84200" : "#694B3D") + ";");
            refreshPlanCards();
        });

        toggleRow.getChildren().addAll(monthlyLabel, switchBtn, yearlyLabel, discountPill);
        return toggleRow;
    }

    private static HBox createPlanCardsRow() {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER);

        // 1. Starter Plan
        VBox starterCard = buildPlanCard(
                "Starter",
                "Basic Seller",
                "Free Forever",
                "₹0",
                isYearly ? "Free / forever" : "Free / month",
                "Perfect for local individual sellers and hobby shops.",
                new String[]{
                        "✓ Up to 30 Active Products",
                        "✓ Standard 5% Transaction Fee",
                        "✓ Basic Order & Inventory Management",
                        "✓ Daily Sales Overview",
                        "✓ Standard Email Support",
                        "✗ Deals & Promo Codes Creation",
                        "✗ AI Chatbot Storefront Assistant",
                        "✗ Priority Search Placement"
                },
                false,
                false
        );

        // 2. Merchant Pro Plan (Recommended)
        VBox proCard = buildPlanCard(
                "Pro",
                "Merchant Pro",
                "★ MOST POPULAR",
                isYearly ? "₹39" : "₹49",
                isYearly ? "₹39 / mo (billed ₹468/yr)" : "₹49 / month",
                "Best for established stores looking to boost sales with deals & AI.",
                new String[]{
                        "✓ Up to 500 Active Products",
                        "✓ Reduced 1.5% Transaction Fee",
                        "✓ Create Unlimited Deals & Promo Codes",
                        "✓ AI Chatbot Assistant for Customers",
                        "✓ Real-time Analytics & Sales Forecast",
                        "✓ Priority Local Storefront Search",
                        "✓ Low Stock SMS & Push Alerts",
                        "✓ 24/7 Priority Live Support"
                },
                true,
                false
        );

        // 3. Enterprise Elite Plan
        VBox enterpriseCard = buildPlanCard(
                "Enterprise",
                "Enterprise Elite",
                "👑 UNLIMITED & DEDICATED",
                isYearly ? "₹119" : "₹149",
                isYearly ? "₹119 / mo (billed ₹1,428/yr)" : "₹149 / month",
                "For high-volume retail chains and multi-branch enterprises.",
                new String[]{
                        "✓ Unlimited Products & Catalog",
                        "✓ 0% Transaction Fee (Zero Commission)",
                        "✓ Multi-Outlet & Branch Inventory Sync",
                        "✓ Dedicated VIP Account Manager",
                        "✓ Custom Store Theme & Verified Gold Badge",
                        "✓ Instant 1-Hour Bank Payouts",
                        "✓ Custom AI Model Trained on Your Store",
                        "✓ Targeted Marketing & Automated Push"
                },
                false,
                true
        );

        HBox.setHgrow(starterCard, Priority.ALWAYS);
        HBox.setHgrow(proCard, Priority.ALWAYS);
        HBox.setHgrow(enterpriseCard, Priority.ALWAYS);

        row.getChildren().addAll(starterCard, proCard, enterpriseCard);
        return row;
    }

    private static VBox buildPlanCard(
            String planId,
            String displayName,
            String ribbonText,
            String priceMain,
            String priceSubtitle,
            String description,
            String[] features,
            boolean isPopular,
            boolean isEnterprise
    ) {
        VBox card = new VBox(14);
        card.setPadding(new Insets(24, 22, 24, 22));
        card.setPrefWidth(380);

        String currentPlan = getCurrentPlanName();
        boolean isCurrent = currentPlan.equalsIgnoreCase(planId);

        String borderStyle = isCurrent
                ? "-fx-border-color: #2E7D32; -fx-border-width: 2.5px; -fx-border-radius: 14px;"
                : isPopular
                ? "-fx-border-color: #B84200; -fx-border-width: 2.5px; -fx-border-radius: 14px;"
                : "-fx-border-color: #E8E2EA; -fx-border-width: 1.2px; -fx-border-radius: 14px;";

        String bgStyle = isCurrent
                ? "-fx-background-color: #F4FAF5;"
                : isPopular
                ? "-fx-background-color: #FFF9F6;"
                : "-fx-background-color: #FCFAFD;";

        card.setStyle(
                bgStyle +
                borderStyle +
                "-fx-background-radius: 14px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(105, 75, 61, 0.10), 10, 0, 0, 4);"
        );

        // Ribbon / Tag Row
        HBox tagRow = new HBox(8);
        tagRow.setAlignment(Pos.CENTER_LEFT);

        if (isCurrent) {
            Label currentTag = new Label("● CURRENT ACTIVE PLAN");
            currentTag.setStyle(
                    "-fx-background-color: #E6F4EA;" +
                    "-fx-text-fill: #137333;" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 4 10;" +
                    "-fx-background-radius: 10px;"
            );
            tagRow.getChildren().add(currentTag);
        } else if (ribbonText != null && !ribbonText.isBlank()) {
            Label ribbon = new Label(ribbonText);
            ribbon.setStyle(
                    "-fx-background-color: " + (isPopular ? "#FFE0CC" : "#F0E4FA") + ";" +
                    "-fx-text-fill: " + (isPopular ? "#A62B0A" : "#6A1B9A") + ";" +
                    "-fx-font-size: 10px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 4 10;" +
                    "-fx-background-radius: 10px;"
            );
            tagRow.getChildren().add(ribbon);
        }

        // Title
        Text title = new Text(displayName);
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text desc = new Text(description);
        desc.setWrappingWidth(320);
        desc.setStyle("-fx-font-size: 12px; -fx-fill: #694B3D;");

        // Price Row
        HBox priceBox = new HBox(4);
        priceBox.setAlignment(Pos.BASELINE_LEFT);

        Text price = new Text(priceMain);
        price.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text period = new Text(priceMain.equals("₹0") ? " forever" : " / mo");
        period.setStyle("-fx-font-size: 14px; -fx-fill: #694B3D; -fx-font-weight: bold;");

        priceBox.getChildren().addAll(price, period);

        Text subPrice = new Text(priceSubtitle);
        subPrice.setStyle("-fx-font-size: 11px; -fx-fill: #888888;");

        // Action Button
        Button actionButton = new Button();
        actionButton.setMaxWidth(Double.MAX_VALUE);
        actionButton.setPrefHeight(42);

        if (isCurrent) {
            actionButton.setText("✓ Current Plan");
            actionButton.setDisable(true);
            actionButton.setStyle(
                    "-fx-background-color: #E6F4EA;" +
                    "-fx-text-fill: #137333;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-opacity: 0.9;"
            );
        } else {
            String btnText = planId.equalsIgnoreCase("Starter") ? "Downgrade to Starter" :
                             planId.equalsIgnoreCase("Enterprise") ? "Upgrade to Enterprise" : "Upgrade to Pro";
            actionButton.setText(btnText);
            actionButton.setStyle(
                    "-fx-background-color: " + (isPopular ? "#B84200" : "#171717") + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-cursor: hand;"
            );
            actionButton.setOnMouseEntered(e -> actionButton.setStyle(
                    "-fx-background-color: " + (isPopular ? "#963400" : "#333333") + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-cursor: hand;"
            ));
            actionButton.setOnMouseExited(e -> actionButton.setStyle(
                    "-fx-background-color: " + (isPopular ? "#B84200" : "#171717") + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-cursor: hand;"
            ));

            actionButton.setOnAction(e -> {
                showConfirmationModal(planId, displayName, priceMain, isYearly ? "Annual" : "Monthly");
            });
        }

        // Features List
        VBox featuresList = new VBox(8);
        featuresList.setPadding(new Insets(10, 0, 10, 0));

        Text featuresHeader = new Text("PLAN INCLUDES:");
        featuresHeader.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-fill: #694B3D;");
        featuresList.getChildren().add(featuresHeader);

        for (String feat : features) {
            boolean isExcluded = feat.startsWith("✗");
            Label item = new Label(feat);
            item.setWrapText(true);
            item.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: " + (isExcluded ? "#A09893" : "#292929") + ";" +
                    (isExcluded ? "-fx-opacity: 0.75;" : "-fx-font-weight: 500;")
            );
            featuresList.getChildren().add(item);
        }

        card.getChildren().addAll(tagRow, title, desc, priceBox, subPrice, actionButton, featuresList);
        return card;
    }

    private static void showConfirmationModal(String planId, String displayName, String price, String cycle) {
        StackPane modalOverlay = new StackPane();
        modalOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.55);");

        VBox dialog = new VBox(18);
        dialog.setPadding(new Insets(28, 32, 28, 32));
        dialog.setMaxWidth(520);
        dialog.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 16, 0, 0, 6);"
        );

        Text dialogTitle = new Text("Confirm Plan Change");
        dialogTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text dialogSub = new Text("Review your plan selection below. Your new features and benefits will activate immediately.");
        dialogSub.setWrappingWidth(450);
        dialogSub.setStyle("-fx-font-size: 12px; -fx-fill: #666666;");

        // Price summary box
        VBox summaryBox = new VBox(10);
        summaryBox.setPadding(new Insets(14, 16, 14, 16));
        summaryBox.setStyle("-fx-background-color: #FAF6F4; -fx-background-radius: 10px; -fx-border-color: #EEDFD7; -fx-border-radius: 10px;");

        HBox r1 = createSummaryRow("New Plan", displayName);
        HBox r2 = createSummaryRow("Billing Frequency", cycle);
        HBox r3 = createSummaryRow("Plan Price", price + (price.equals("₹0") ? "" : " / " + cycle.toLowerCase()));
        HBox r4 = createSummaryRow("GST (18%)", price.equals("₹0") ? "₹0.00" : (cycle.equalsIgnoreCase("Annual") ? "₹84.24" : "₹8.82"));
        HBox r5 = createSummaryRow("Total Amount Due", price.equals("₹0") ? "₹0.00" : (cycle.equalsIgnoreCase("Annual") ? "₹552.24" : "₹57.82"));

        summaryBox.getChildren().addAll(r1, r2, r3, r4, r5);

        // Payment method selector
        VBox paymentMethodBox = new VBox(8);
        Text payLabel = new Text("Select Payment Method:");
        payLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-fill: #171717;");

        ToggleGroup payGroup = new ToggleGroup();
        RadioButton upiRadio = new RadioButton("UPI (Google Pay, PhonePe, Paytm, BHIM)");
        upiRadio.setToggleGroup(payGroup);
        upiRadio.setSelected(true);

        RadioButton cardRadio = new RadioButton("Credit / Debit Card (Visa, MasterCard, RuPay)");
        cardRadio.setToggleGroup(payGroup);

        RadioButton walletRadio = new RadioButton("BuyNeX Merchant Store Wallet");
        walletRadio.setToggleGroup(payGroup);

        paymentMethodBox.getChildren().addAll(payLabel, upiRadio, cardRadio, walletRadio);

        // Buttons
        HBox buttonRow = new HBox(12);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefHeight(38);
        cancelBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #CCCCCC;" +
                "-fx-border-radius: 8px;" +
                "-fx-padding: 8 18;" +
                "-fx-cursor: hand;"
        );
        cancelBtn.setOnAction(e -> rootStack.getChildren().remove(modalOverlay));

        Button confirmBtn = new Button("Confirm & Activate " + displayName);
        confirmBtn.setPrefHeight(38);
        confirmBtn.setStyle(
                "-fx-background-color: #B84200;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 8 20;" +
                "-fx-cursor: hand;"
        );

        confirmBtn.setOnAction(e -> {
            // Apply Plan Update
            applyPlanUpdate(planId, price, cycle);
            rootStack.getChildren().remove(modalOverlay);
            refreshPlanCards();
            refreshActiveSubscriptionCard();
            showToast("🎉 Congratulations! Your plan has been successfully upgraded to " + displayName + ".");
        });

        buttonRow.getChildren().addAll(cancelBtn, confirmBtn);

        dialog.getChildren().addAll(dialogTitle, dialogSub, summaryBox, paymentMethodBox, buttonRow);
        modalOverlay.getChildren().add(dialog);
        rootStack.getChildren().add(modalOverlay);
    }

    private static HBox createSummaryRow(String label, String val) {
        HBox row = new HBox();
        Text l = new Text(label);
        l.setStyle("-fx-font-size: 12px; -fx-fill: #694B3D;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text v = new Text(val);
        v.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-fill: #171717;");

        row.getChildren().addAll(l, spacer, v);
        return row;
    }

    private static void applyPlanUpdate(String planId, String price, String cycle) {
        String formattedRenewal = LocalDate.now().plusDays(cycle.equalsIgnoreCase("Annual") ? 365 : 30)
                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"));

        String formattedAmount = price.equals("₹0") ? "Free" : (price + (cycle.equalsIgnoreCase("Annual") ? "/yr" : "/mo"));

        if (ViewConstants.shopkeeperModel == null) {
            ViewConstants.shopkeeperModel = new ShopkeeperModel();
        }

        ViewConstants.shopkeeperModel.setPlanName(planId);
        ViewConstants.shopkeeperModel.setPlanAmount(formattedAmount);
        ViewConstants.shopkeeperModel.setPlanBillingCycle(cycle);
        ViewConstants.shopkeeperModel.setPlanRenewalDate(formattedRenewal);
        ViewConstants.shopkeeperModel.setPlanStatus("ACTIVE");

        // Asynchronously update in Firestore
        new Thread(() -> {
            try {
                ShopkeeperDAO dao = new ShopkeeperDAO();
                String uid = ViewConstants.shopkeeperModel.getShopkeeperUid();
                dao.updateShopkeeperPlan(uid, planId, formattedAmount, cycle, formattedRenewal, "ACTIVE");
            } catch (Exception ex) {
                System.out.println("Async plan update error: " + ex.getMessage());
            }
        }).start();
    }

    private static void refreshPlanCards() {
        if (planCardsRow != null) {
            HBox newRow = createPlanCardsRow();
            planCardsRow.getChildren().setAll(newRow.getChildren());
        }
    }

    private static void refreshActiveSubscriptionCard() {
        if (activeSubscriptionCard != null) {
            VBox newCard = createActiveSubscriptionCard();
            activeSubscriptionCard.getChildren().setAll(newCard.getChildren());
        }
    }

    private static VBox createBillingHistorySection() {
        VBox section = new VBox(14);
        section.setPadding(new Insets(20, 24, 20, 24));
        section.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #E8E2EA;" +
                "-fx-border-radius: 14px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(105, 75, 61, 0.08), 8, 0, 0, 3);"
        );

        Text title = new Text("Billing History & Receipts");
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text subtitle = new Text("Download past invoices and tax receipts for your accounting and business tax filings.");
        subtitle.setStyle("-fx-font-size: 12px; -fx-fill: #694B3D;");

        VBox table = new VBox(8);
        table.setPadding(new Insets(10, 0, 0, 0));

        // Table Header
        HBox headerRow = createInvoiceRow("INVOICE ID", "BILLING DATE", "PLAN", "AMOUNT", "STATUS", "ACTION", true);
        table.getChildren().add(headerRow);

        String currentPlan = getCurrentPlanName();
        String currentAmount = getCurrentPlanAmount();

        // Sample Invoices
        HBox row1 = createInvoiceRow("INV-2026-089", LocalDate.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")), "Merchant " + currentPlan, currentAmount, "PAID", "Download Receipt", false);
        HBox row2 = createInvoiceRow("INV-2026-054", LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MMM dd, yyyy")), "Merchant Pro", "₹49/mo", "PAID", "Download Receipt", false);
        HBox row3 = createInvoiceRow("INV-2026-012", LocalDate.now().minusMonths(2).format(DateTimeFormatter.ofPattern("MMM dd, yyyy")), "Starter Plan", "Free", "PAID", "Download Receipt", false);

        table.getChildren().addAll(row1, row2, row3);
        section.getChildren().addAll(title, subtitle, table);
        return section;
    }

    private static HBox createInvoiceRow(String invId, String date, String plan, String amount, String status, String action, boolean isHeader) {
        HBox row = new HBox(12);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(10, 14, 10, 14));
        row.setStyle(isHeader ? "-fx-background-color: #F3EAE5; -fx-background-radius: 8px;" : "-fx-background-color: transparent; -fx-border-color: #F1EAE6; -fx-border-width: 0 0 1px 0;");

        Label col1 = new Label(invId);
        col1.setPrefWidth(140);
        col1.setStyle(isHeader ? "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;" : "-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #171717;");

        Label col2 = new Label(date);
        col2.setPrefWidth(140);
        col2.setStyle(isHeader ? "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;" : "-fx-font-size: 12px; -fx-text-fill: #555555;");

        Label col3 = new Label(plan);
        col3.setPrefWidth(160);
        col3.setStyle(isHeader ? "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;" : "-fx-font-size: 12px; -fx-text-fill: #222222;");

        Label col4 = new Label(amount);
        col4.setPrefWidth(110);
        col4.setStyle(isHeader ? "-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;" : "-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #171717;");

        Label col5;
        if (isHeader) {
            col5 = new Label(status);
            col5.setPrefWidth(110);
            col5.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;");
        } else {
            col5 = new Label("● " + status);
            col5.setPrefWidth(110);
            col5.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #137333; -fx-background-color: #E6F4EA; -fx-padding: 3 8; -fx-background-radius: 8px;");
        }

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        if (isHeader) {
            Label col6 = new Label(action);
            col6.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #694B3D;");
            row.getChildren().addAll(col1, col2, col3, col4, col5, spacer, col6);
        } else {
            Button dlBtn = new Button("📄 " + action);
            dlBtn.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #B84200;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-border-color: #B84200;" +
                    "-fx-border-radius: 6px;" +
                    "-fx-padding: 4 10;" +
                    "-fx-cursor: hand;"
            );
            dlBtn.setOnAction(e -> {
                showToast("Receipt " + invId + " downloaded to your system.");
            });
            row.getChildren().addAll(col1, col2, col3, col4, col5, spacer, dlBtn);
        }

        return row;
    }

    private static VBox createFAQSection() {
        VBox faqBox = new VBox(12);
        faqBox.setPadding(new Insets(20, 24, 20, 24));
        faqBox.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 14px;" +
                "-fx-border-color: #E8E2EA;" +
                "-fx-border-radius: 14px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(105, 75, 61, 0.08), 8, 0, 0, 3);"
        );

        Text faqTitle = new Text("Frequently Asked Questions");
        faqTitle.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-fill: #171717;");

        VBox q1 = createFAQItem(
                "Can I upgrade or downgrade my plan at any time?",
                "Yes! You can switch between Starter, Pro, and Enterprise at any time. When upgrading, changes take effect immediately with pro-rated billing."
        );
        VBox q2 = createFAQItem(
                "How does the 20% annual discount work?",
                "When you select Annual billing, you pay upfront for 12 months at the discounted rate (e.g. ₹39/mo instead of ₹49/mo for Pro), saving you significant platform costs."
        );
        VBox q3 = createFAQItem(
                "What happens to my active deals and coupons if I downgrade?",
                "Deals created under Pro or Enterprise remain active until their designated expiration date. New deals cannot be created under the Starter tier."
        );
        VBox q4 = createFAQItem(
                "How are platform commission fees calculated?",
                "The transaction fee is deducted automatically when customer payments are settled into your store account (Starter: 5%, Pro: 1.5%, Enterprise: 0%)."
        );

        faqBox.getChildren().addAll(faqTitle, q1, q2, q3, q4);
        return faqBox;
    }

    private static VBox createFAQItem(String question, String answer) {
        VBox item = new VBox(3);
        item.setPadding(new Insets(6, 0, 6, 0));

        Text q = new Text("Q: " + question);
        q.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-fill: #171717;");

        Text a = new Text(answer);
        a.setWrappingWidth(1100);
        a.setStyle("-fx-font-size: 12px; -fx-fill: #694B3D;");

        item.getChildren().addAll(q, a);
        return item;
    }

    private static void showToast(String message) {
        if (toastBox == null || toastLabel == null) return;
        toastLabel.setText(message);
        toastBox.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(250), toastBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition stay = new PauseTransition(Duration.seconds(3.5));

        FadeTransition fadeOut = new FadeTransition(Duration.millis(350), toastBox);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> toastBox.setVisible(false));

        fadeIn.setOnFinished(e -> stay.play());
        stay.setOnFinished(e -> fadeOut.play());
        fadeIn.play();
    }

    private static String getCurrentPlanName() {
        if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getPlanName() != null) {
            return ViewConstants.shopkeeperModel.getPlanName();
        }
        return "Pro";
    }

    private static String getCurrentPlanAmount() {
        if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getPlanAmount() != null) {
            return ViewConstants.shopkeeperModel.getPlanAmount();
        }
        return "₹49/mo";
    }

    private static String getCurrentPlanRenewal() {
        if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getPlanRenewalDate() != null) {
            return ViewConstants.shopkeeperModel.getPlanRenewalDate();
        }
        return "Nov 15, 2026";
    }

    private static String getCurrentPlanCycle() {
        if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getPlanBillingCycle() != null) {
            return ViewConstants.shopkeeperModel.getPlanBillingCycle();
        }
        return "Monthly";
    }
}

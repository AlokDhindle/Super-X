package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerSidebar {

    public static VBox createSidebar(String userId, String activeItem) {
        if (userId == null || userId.isBlank()) {
            userId = "guest";
        }
        final String uid = userId;

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
                "-fx-text-fill: #E87500;" +
                "-fx-cursor: hand;"
        );
        name.setOnMouseClicked(e -> CustomerNavigation.navigateToDashboard(uid));

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
                "-fx-border-radius: 15;"
        );
        premiumBox.setEffect(cardShadow);

        VBox textBox = new VBox(3);
        String currentPlan = CustomerManagePlan.CustomerPlanState.getCurrentPlan(uid);
        String shopperTitle = "Gold".equalsIgnoreCase(currentPlan) ? "👑 Gold Shopper" : ("Platinum".equalsIgnoreCase(currentPlan) ? "💎 Platinum VIP" : "Premium Shopper");
        Label title = new Label(shopperTitle);
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label subtitle = new Label("● AI Assistant Active");
        subtitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        textBox.getChildren().addAll(title, subtitle);
        premiumBox.getChildren().add(textBox);

        // 1. Dashboard
        HBox hbInDashboard = createNavItem(
                "/assets/images/Dashbord/dashboard.png",
                21,
                "Dashboard",
                "Dashboard".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToDashboard(uid)
        );

        // 2. Nearby Shops
        HBox hbInDashboard2 = createNavItem(
                "/assets/images/store.png",
                23,
                "Nearby Shops",
                "Nearby Shops".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToNearbyShops(uid)
        );

        // 3. Deals
        HBox hbInDashboard3 = createNavItem(
                "/assets/images/Dashbord/hot-sale.png",
                23,
                "Deals",
                "Deals".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToDeals(uid)
        );

        // 4. My Orders
        HBox hbInDashboard4 = createNavItem(
                "/assets/images/Dashbord/package.png",
                23,
                "My Orders",
                "My Orders".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToOrders(uid)
        );

        // 5. Analytics
        HBox hbInDashboard5 = createNavItem(
                "/assets/images/Dashbord/line-chart.png",
                23,
                "Analytics",
                "Analytics".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToAnalytics(uid)
        );

        VBox upgradeCard = CustomerPlanUpgradeCard.createUpgradeCard(uid);

        // 6. Settings
        HBox hbInDashboard6 = createBottomNavItem(
                "/assets/images/Dashbord/category.png",
                19,
                "Settings",
                "Settings".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToSettings(uid)
        );

        // 7. Help & Support
        HBox hbInDashboard7 = createBottomNavItem(
                "/assets/images/Dashbord/question.png",
                19,
                "Help & Support",
                "Help & Support".equalsIgnoreCase(activeItem) || "Help".equalsIgnoreCase(activeItem),
                () -> CustomerNavigation.navigateToHelp(uid)
        );

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
                hbInDashboard7
        );

        return leftBox;
    }

    private static HBox createNavItem(String iconPath, double iconSize, String text, boolean isActive, Runnable action) {
        ImageView iv = null;
        try {
            Image img = new Image(iconPath);
            iv = new ImageView(img);
            iv.setFitHeight(iconSize);
            iv.setFitWidth(iconSize);
            iv.setPreserveRatio(true);
        } catch (Exception ignored) {}

        Button btn = new Button(text);
        btn.setPrefWidth(125);
        btn.setPrefHeight(38);

        HBox row = new HBox(17);
        if (iv != null) {
            row.getChildren().addAll(iv, btn);
        } else {
            row.getChildren().add(btn);
        }

        row.setPrefWidth(205);
        row.setMinWidth(205);
        row.setMaxWidth(205);
        row.setPrefHeight(42);
        row.setMinHeight(42);
        row.setMaxHeight(42);
        row.setPadding(new Insets(2, 8, 2, 13));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-cursor: hand;");

        String defaultRowStyle = "-fx-background-color: transparent; -fx-background-radius: 12;";
        String defaultBtnStyle = "-fx-background-color: transparent; -fx-text-fill: #333333; -fx-font-size: 12px; -fx-font-family: 'Montserrat'; -fx-font-weight: 500; -fx-border-width: 0; -fx-padding: 0; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;";

        String activeRowStyle = "-fx-background-color: #FF6900; -fx-background-radius: 12;";
        String activeBtnStyle = "-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-family: 'Montserrat'; -fx-font-weight: bold; -fx-border-width: 0; -fx-padding: 0; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;";

        if (isActive) {
            row.setStyle(activeRowStyle);
            btn.setStyle(activeBtnStyle);
        } else {
            row.setStyle(defaultRowStyle);
            btn.setStyle(defaultBtnStyle);

            row.setOnMouseEntered(e -> {
                row.setStyle(activeRowStyle);
                btn.setStyle(activeBtnStyle);
            });

            row.setOnMouseExited(e -> {
                row.setStyle(defaultRowStyle);
                btn.setStyle(defaultBtnStyle);
            });
        }

        row.setOnMouseClicked(e -> {
            if (action != null) action.run();
        });
        btn.setOnAction(e -> {
            if (action != null) action.run();
        });

        return row;
    }

    private static HBox createBottomNavItem(String iconPath, double iconSize, String text, boolean isActive, Runnable action) {
        ImageView iv = null;
        try {
            Image img = new Image(iconPath);
            iv = new ImageView(img);
            iv.setFitHeight(iconSize);
            iv.setFitWidth(iconSize);
            iv.setPreserveRatio(true);
        } catch (Exception ignored) {}

        Button btn = new Button(text);
        btn.setPrefWidth(135);
        btn.setPrefHeight(34);

        HBox row = new HBox(10);
        if (iv != null) {
            row.getChildren().addAll(iv, btn);
        } else {
            row.getChildren().add(btn);
        }

        row.setPrefWidth(205);
        row.setMinWidth(205);
        row.setMaxWidth(205);
        row.setPrefHeight(34);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(0, 8, 0, 18));
        row.setStyle("-fx-cursor: hand;");

        String defaultBtnStyle = "-fx-background-color: transparent; -fx-text-fill: #666666; -fx-font-size: 11px; -fx-font-weight: 500; -fx-border-width: 0; -fx-padding: 0; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;";
        String activeBtnStyle = "-fx-background-color: transparent; -fx-text-fill: #FF6900; -fx-font-size: 11px; -fx-font-weight: bold; -fx-border-width: 0; -fx-padding: 0; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;";

        if (isActive) {
            row.setStyle("-fx-background-color: rgba(255, 105, 0, 0.12); -fx-background-radius: 8;");
            btn.setStyle(activeBtnStyle);
        } else {
            btn.setStyle(defaultBtnStyle);
            row.setOnMouseEntered(e -> {
                row.setStyle("-fx-background-color: rgba(255, 105, 0, 0.12); -fx-background-radius: 8;");
                btn.setStyle(activeBtnStyle);
            });
            row.setOnMouseExited(e -> {
                row.setStyle("-fx-background-color: transparent;");
                btn.setStyle(defaultBtnStyle);
            });
        }

        row.setOnMouseClicked(e -> {
            if (action != null) action.run();
        });
        btn.setOnAction(e -> {
            if (action != null) action.run();
        });

        return row;
    }

    public static HBox createTopNav(String userId, String activeAction, TextField externalSearchBox, Runnable searchReturnCallback) {
        if (userId == null || userId.isBlank()) {
            userId = "guest";
        }
        final String uid = userId;

        Button t1 = new Button("Offers");
        Button t2 = new Button("Shops");
        Button t3 = new Button("Support");

        t1.setOnAction(e -> CustomerNavigation.navigateToDeals(uid));
        t2.setOnAction(e -> CustomerNavigation.navigateToNearbyShops(uid));
        t3.setOnAction(e -> CustomerNavigation.navigateToHelp(uid));

        String topButtonStyle = "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;";

        String topButtonHoverStyle = "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;";

        t1.setStyle(topButtonStyle);
        t2.setStyle(topButtonStyle);
        t3.setStyle(topButtonStyle);

        t1.setOnMouseEntered(e -> t1.setStyle(topButtonHoverStyle));
        t1.setOnMouseExited(e -> t1.setStyle(topButtonStyle));
        t2.setOnMouseEntered(e -> t2.setStyle(topButtonHoverStyle));
        t2.setOnMouseExited(e -> t2.setStyle(topButtonStyle));
        t3.setOnMouseEntered(e -> t3.setStyle(topButtonHoverStyle));
        t3.setOnMouseExited(e -> t3.setStyle(topButtonStyle));

        HBox topLinks = new HBox(6, t1, t2, t3);
        topLinks.setAlignment(Pos.CENTER_LEFT);

        TextField searchBox = (externalSearchBox != null) ? externalSearchBox : new TextField();
        searchBox.setPromptText("Search products, shops or deals with AI...");
        searchBox.setPrefWidth(280);
        searchBox.setPrefHeight(39);
        searchBox.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-width: 0;" +
                "-fx-padding: 0 10 0 14;" +
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #444444;" +
                "-fx-prompt-text-fill: #999999;"
        );

        Button searchBtn = new Button("🔍");
        searchBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 10 0 0;"
        );

        Runnable doSearch = () -> {
            CustomerNavigation.performSearch(uid, searchBox.getText(), searchReturnCallback);
        };
        searchBtn.setOnAction(e -> doSearch.run());
        searchBox.setOnAction(e -> doSearch.run());

        HBox searchContainer = new HBox(searchBox, searchBtn);
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setPrefHeight(39);
        searchContainer.setStyle(
                "-fx-background-color: #F8F7FA;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #E5E1E8;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1;"
        );

        Label locationIcon = new Label("📍");
        Label locationText = new Label("Downtown Manhattan⌄");
        locationText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #555555;"
        );

        HBox locationBox = new HBox(4, locationIcon, locationText);
        locationBox.setAlignment(Pos.CENTER_LEFT);
        locationBox.setStyle("-fx-cursor: hand;");
        locationBox.setOnMouseClicked(e -> CustomerNavigation.navigateToNearbyShops(uid));

        Button b1 = new Button("🔔");
        Button b2 = new Button("🛒");
        Button b3 = new Button("●");

        b1.setPrefSize(37, 37);
        b2.setPrefSize(37, 37);
        b3.setPrefSize(37, 37);

        String actionStyle = "-fx-background-color: white;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #E7E2E9;" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        String actionHoverStyle = "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #FFBD95;" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        String actionActiveStyle = "-fx-background-color: #FFF2E9;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1.5;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        b1.setStyle("NOTIFICATIONS".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle);
        b2.setStyle("CART".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle);
        b3.setStyle("SETTINGS".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle);

        b1.setOnMouseEntered(e -> b1.setStyle(actionHoverStyle));
        b1.setOnMouseExited(e -> b1.setStyle("NOTIFICATIONS".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle));

        b2.setOnMouseEntered(e -> b2.setStyle(actionHoverStyle));
        b2.setOnMouseExited(e -> b2.setStyle("CART".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle));

        b3.setOnMouseEntered(e -> b3.setStyle(actionHoverStyle));
        b3.setOnMouseExited(e -> b3.setStyle("SETTINGS".equalsIgnoreCase(activeAction) ? actionActiveStyle : actionStyle));

        b1.setOnAction(e -> CustomerNavigation.navigateToNotifications(uid));
        b2.setOnAction(e -> CustomerNavigation.navigateToCart(uid));
        b3.setOnAction(e -> CustomerNavigation.navigateToSettings(uid));

        HBox actionBox = new HBox(10, b1, b2, b3);
        actionBox.setAlignment(Pos.CENTER_RIGHT);

        Region navSpacer1 = new Region();
        HBox.setHgrow(navSpacer1, Priority.ALWAYS);
        Region navSpacer2 = new Region();
        HBox.setHgrow(navSpacer2, Priority.ALWAYS);

        HBox navBox = new HBox(12, topLinks, navSpacer1, searchContainer, navSpacer2, locationBox, actionBox);
        navBox.setPrefHeight(68);
        navBox.setPadding(new Insets(12, 24, 12, 24));
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setStyle(
                "-fx-background-color: #ebccb7;" +
                "-fx-border-color: #dfc1ac;" +
                "-fx-border-width: 0 0 1 0;"
        );

        return navBox;
    }
}

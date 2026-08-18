package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShopkeeperNotification {

    public static Scene notificationScene() {

        // ============================================================
        // BORDER PANE
        // ============================================================

        BorderPane borderPane = new BorderPane();

        // ================================================================
        // MAIN BORDER PANE
        // ================================================================

        // ============================================================
        // HEADER
        // ============================================================

        Text headerBuyNex = new Text("BuyNeX");
        headerBuyNex.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';");

        LinearGradient orangeGradient = new LinearGradient(
                0, 0,
                1, 0,
                true,
                CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#A62B0A")),
                new Stop(0.45, Color.web("#D94D0A")),
                new Stop(1, Color.web("#F4770A")));

        headerBuyNex.setFill(orangeGradient);

        HBox headerLeftBox = new HBox(headerBuyNex);
        headerLeftBox.setAlignment(Pos.CENTER_LEFT);
        headerLeftBox.setPadding(new Insets(0, 0, 0, 25));

        // ============================================================
        // HEADER ICONS
        // ============================================================

        Button notificationIcon = new Button("🔔 Notifications");
        notificationIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: #FF6900;" +
                "-fx-background-radius: 9px;");

        Button botIcon = new Button("🤖 ChatBot");
        botIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: transparent;");

        Button profileIcon = new Button("👤 Profile");
        profileIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: transparent;");

        HBox headerIcons = new HBox(
                25,
                notificationIcon,
                botIcon,
                profileIcon);

        headerIcons.setAlignment(Pos.CENTER_RIGHT);

        headerIcons.setPadding(
                new Insets(0, 25, 0, 0));

        HBox headerMainBox = new HBox(
                headerLeftBox,
                headerIcons);

        HBox.setHgrow(headerLeftBox, Priority.ALWAYS);
        HBox.setHgrow(headerIcons, Priority.ALWAYS);

        headerMainBox.setAlignment(Pos.CENTER);
        headerMainBox.setPrefHeight(70);

        headerMainBox.setStyle(
                "-fx-background-color: #F8FBF8;" +
                "-fx-border-color: #F1E8E1;" +
                "-fx-border-width: 0 0 1px 0;");

        // ============================================================
        // HEADER ICONS BUTTON ACTIONS
        // ============================================================

        notificationIcon.setOnMouseClicked(e -> {
            System.out.println("Notification Icon clicked");
        });

        botIcon.setOnMouseClicked(e -> {
            System.out.println("ChatBot Icon clicked");
            Main.primaryStage.setScene(
                    ShopkeeperChatbot.chatbotScene());
        });

        profileIcon.setOnMouseClicked(e -> {
            System.out.println("Profile Icon clicked");
            Main.primaryStage.setScene(
                    ShopkeeperProfile.profileScene());
        });

        borderPane.setTop(headerMainBox);

        // ============================================================
        // SIDEBAR
        // ============================================================

        VBox sidebar = new VBox();

        sidebar.setMinWidth(260);
        sidebar.setMaxWidth(260);

        sidebar.setStyle(
                "-fx-background-color: #F5F4F9;" +
                "-fx-border-color: #E3C7BA;" +
                "-fx-border-width: 0 1px 0 0;");

        // ============================================================
        // SHOPKEEPER PROFILE
        // ============================================================

        HBox profileBox = Constants.letfProfileBox();

        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(
                new Insets(30, 20, 30, 20));

        // ============================================================
        // DASHBOARD BUTTON
        // ============================================================

        Text dashboardIcon = new Text("▦");

        dashboardIcon.setStyle(
                "-fx-font-size: 29px;" +
                "-fx-fill: #333333;");

        Text dashboardText = new Text("Dashboard");

        dashboardText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #333333;");

        HBox dashboardContent = new HBox(
                16,
                dashboardIcon,
                dashboardText);

        dashboardContent.setAlignment(Pos.CENTER_LEFT);

        Button dashboardButton = new Button();

        dashboardButton.setGraphic(dashboardContent);
        dashboardButton.setPrefWidth(244);
        dashboardButton.setPrefHeight(51);
        dashboardButton.setAlignment(Pos.CENTER_LEFT);

        dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        dashboardButton.setOnMouseEntered(e -> {
            dashboardButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        dashboardButton.setOnMouseExited(e -> {
            dashboardButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // ORDERS BUTTON
        // ============================================================

        Text ordersIcon = new Text("🛒");

        ordersIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #555555;");

        Text ordersText = new Text("Orders");

        ordersText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox ordersContent = new HBox(
                13,
                ordersIcon,
                ordersText);

        ordersContent.setAlignment(Pos.CENTER_LEFT);

        Button ordersButton = new Button();

        ordersButton.setGraphic(ordersContent);
        ordersButton.setPrefWidth(244);
        ordersButton.setPrefHeight(51);
        ordersButton.setAlignment(Pos.CENTER_LEFT);

        ordersButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        ordersButton.setOnMouseEntered(e -> {
            ordersButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        ordersButton.setOnMouseExited(e -> {
            ordersButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // INVENTORY BUTTON
        // ============================================================

        Text inventoryIcon = new Text("📋");

        inventoryIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #555555;");

        Text inventoryText = new Text("Inventory");

        inventoryText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox inventoryContent = new HBox(
                15,
                inventoryIcon,
                inventoryText);

        inventoryContent.setAlignment(Pos.CENTER_LEFT);

        Button inventoryButton = new Button();

        inventoryButton.setGraphic(inventoryContent);
        inventoryButton.setPrefWidth(244);
        inventoryButton.setPrefHeight(51);
        inventoryButton.setAlignment(Pos.CENTER_LEFT);

        inventoryButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        inventoryButton.setOnMouseEntered(e -> {
            inventoryButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        inventoryButton.setOnMouseExited(e -> {
            inventoryButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // OFFERS BUTTON
        // ============================================================

        Text offersIcon = new Text("🎁");

        offersIcon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-fill: #555555;");

        Text offersText = new Text("Offers");

        offersText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox offersContent = new HBox(
                14,
                offersIcon,
                offersText);

        offersContent.setAlignment(Pos.CENTER_LEFT);

        Button offersButton = new Button();

        offersButton.setGraphic(offersContent);
        offersButton.setPrefWidth(244);
        offersButton.setPrefHeight(51);
        offersButton.setAlignment(Pos.CENTER_LEFT);

        offersButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        offersButton.setOnMouseEntered(e -> {
            offersButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        offersButton.setOnMouseExited(e -> {
            offersButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // ANALYTICS BUTTON
        // ============================================================

        Text analyticsIcon = new Text("📊");

        analyticsIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #555555;");

        Text analyticsText = new Text("Analytics");

        analyticsText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox analyticsContent = new HBox(
                15,
                analyticsIcon,
                analyticsText);

        analyticsContent.setAlignment(Pos.CENTER_LEFT);

        Button analyticsButton = new Button();

        analyticsButton.setGraphic(analyticsContent);
        analyticsButton.setPrefWidth(244);
        analyticsButton.setPrefHeight(51);
        analyticsButton.setAlignment(Pos.CENTER_LEFT);

        analyticsButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        analyticsButton.setOnMouseEntered(e -> {
            analyticsButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        analyticsButton.setOnMouseExited(e -> {
            analyticsButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // SETTINGS BUTTON
        // ============================================================

        Text settingsIcon = new Text("⚙");

        settingsIcon.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #555555;");

        Text settingsText = new Text("Settings");

        settingsText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox settingsContent = new HBox(
                15,
                settingsIcon,
                settingsText);

        settingsContent.setAlignment(Pos.CENTER_LEFT);

        Button settingsButton = new Button();

        settingsButton.setGraphic(settingsContent);
        settingsButton.setPrefWidth(244);
        settingsButton.setPrefHeight(51);
        settingsButton.setAlignment(Pos.CENTER_LEFT);

        settingsButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        settingsButton.setOnMouseEntered(e -> {
            settingsButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        settingsButton.setOnMouseExited(e -> {
            settingsButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        // ============================================================
        // SUPPORT BUTTON
        // ============================================================

        Text supportIcon = new Text("?");

        supportIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #555555;");

        Text supportText = new Text("Support");

        supportText.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-fill: #555555;");

        HBox supportContent = new HBox(
                20,
                supportIcon,
                supportText);

        supportContent.setAlignment(Pos.CENTER_LEFT);

        Button supportButton = new Button();

        supportButton.setGraphic(supportContent);
        supportButton.setPrefWidth(244);
        supportButton.setPrefHeight(51);
        supportButton.setAlignment(Pos.CENTER_LEFT);

        supportButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        supportButton.setOnMouseEntered(e -> {
            supportButton.setStyle(
                    "-fx-background-color: #FF6900;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-cursor: hand;");
        });

        supportButton.setOnMouseExited(e -> {
            supportButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-cursor: hand;");
        });

        VBox sidebarMenu = new VBox(
                5,
                dashboardButton,
                ordersButton,
                inventoryButton,
                offersButton,
                analyticsButton,
                settingsButton,
                supportButton);

        sidebarMenu.setPadding(
                new Insets(0, 8, 0, 8));

        // ============================================================
        // LOGOUT BUTTON
        // ============================================================

        VBox logoutBox = Constants.logoutBox();

        VBox.setVgrow(
                sidebarMenu,
                Priority.ALWAYS);

        sidebar.getChildren().addAll(
                profileBox,
                sidebarMenu,
                logoutBox);

        borderPane.setLeft(sidebar);

        // ============================================================
        // BUTTON ACTIONS
        // ============================================================

        dashboardButton.setOnAction(e -> {

            System.out.println("Dashboard clicked");

            Main.primaryStage.setScene(
                    ShopkeeperDashboard.dashboardScene());

        });

        ordersButton.setOnAction(e -> {

            System.out.println("Orders clicked");

            Main.primaryStage.setScene(
                    ShopkeeperOrderReady.ordersScene());

        });

        inventoryButton.setOnAction(e -> {

            System.out.println("Inventory clicked");

            Main.primaryStage.setScene(
                    ShopkeeperInventory.inventoryScene());

        });

        offersButton.setOnAction(e -> {

            System.out.println("Offers clicked");

            Main.primaryStage.setScene(
                    ShopkeeperOffers.offersScene());

        });

        analyticsButton.setOnAction(e -> {

            System.out.println("Analytics clicked");

            Main.primaryStage.setScene(
                    ShopkeeperAnalytics.analyticsScene());

        });

        settingsButton.setOnAction(e -> {

            System.out.println("Settings clicked");

            Main.primaryStage.setScene(
                    ShopkeeperSettings.settingsScene());

        });

        supportButton.setOnAction(e -> {

            System.out.println("Support clicked");

            Main.primaryStage.setScene(
                    ShopkeeperSupport.supportScene());

        });

        // ============================================================
        // CENTER - NOTIFICATIONS PAGE
        // ============================================================

        VBox notificationPage = new VBox();

        notificationPage.setAlignment(Pos.TOP_CENTER);

        notificationPage.setPadding(
                new Insets(55, 40, 30, 40));

        notificationPage.setSpacing(25);

        notificationPage.setStyle(
                "-fx-background-color: white;");

        // ============================================================
        // NOTIFICATION TITLE
        // ============================================================

        Text notificationTitle = new Text("Notifications");

        notificationTitle.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #202124;");

        Text titleBell = new Text("♧");

        titleBell.setText("♧");

        titleBell.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-fill: #624638;");

        /*
         * Using a simple bell character here keeps the implementation
         * consistent with the basic JavaFX approach used in the
         * existing project.
         */
        titleBell.setText("♧");

        HBox titleBox = new HBox();

        titleBox.setPrefWidth(570);

        titleBox.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(notificationTitle);

        StackPane titleContainer = new StackPane();

        titleContainer.setPrefWidth(570);

        titleContainer.setPrefHeight(45);

        titleContainer.getChildren().add(titleBox);

        StackPane.setAlignment(
                titleBox,
                Pos.CENTER);

        Text bellRight = new Text("♧");

        bellRight.setText("♧");

        bellRight.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-fill: #624638;");

        titleContainer.getChildren().add(bellRight);

        StackPane.setAlignment(
                bellRight,
                Pos.CENTER_RIGHT);

        // ============================================================
        // NOTIFICATION LIST
        // ============================================================

        VBox notificationList = new VBox();

        notificationList.setSpacing(12);

        notificationList.setPadding(
                new Insets(0));

        notificationList.setPrefWidth(570);

        notificationList.setMaxWidth(570);

        // ============================================================
        // NOTIFICATION 1
        // ============================================================

        HBox orderIconCircle = new HBox();

        orderIconCircle.setPrefWidth(44);
        orderIconCircle.setPrefHeight(44);

        orderIconCircle.setMinWidth(44);
        orderIconCircle.setMinHeight(44);

        orderIconCircle.setMaxWidth(44);
        orderIconCircle.setMaxHeight(44);

        orderIconCircle.setAlignment(Pos.CENTER);

        orderIconCircle.setStyle(
                "-fx-background-color: #F1E6E0;" +
                "-fx-background-radius: 50px;");

        Text orderIcon = new Text("🛒");

        orderIcon.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #C85A0A;");

        orderIconCircle.getChildren().add(orderIcon);

        Text orderTitle = new Text("New Order #4592");

        orderTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #292929;");

        Text orderTime = new Text("2 minutes ago");

        orderTime.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #765F55;");

        VBox orderTextBox = new VBox(
                6,
                orderTitle,
                orderTime);

        orderTextBox.setAlignment(Pos.CENTER_LEFT);

        HBox orderNotification = new HBox(
                24,
                orderIconCircle,
                orderTextBox);

        orderNotification.setAlignment(
                Pos.CENTER_LEFT);

        orderNotification.setPadding(
                new Insets(20, 25, 20, 25));

        orderNotification.setPrefWidth(570);
        orderNotification.setPrefHeight(104);

        orderNotification.setStyle(
                "-fx-background-color: #F5F4F8;" +
                "-fx-background-radius: 12px;");

        // ============================================================
        // NOTIFICATION 2
        // ============================================================

        HBox stockIconCircle = new HBox();

        stockIconCircle.setPrefWidth(44);
        stockIconCircle.setPrefHeight(44);

        stockIconCircle.setMinWidth(44);
        stockIconCircle.setMinHeight(44);

        stockIconCircle.setMaxWidth(44);
        stockIconCircle.setMaxHeight(44);

        stockIconCircle.setAlignment(Pos.CENTER);

        stockIconCircle.setStyle(
                "-fx-background-color: #F7E4E6;" +
                "-fx-background-radius: 50px;");

        Text stockIcon = new Text("⚠");

        stockIcon.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-fill: #D92F2F;");

        stockIconCircle.getChildren().add(stockIcon);

        Text stockTitle = new Text(
                "Low Stock Alert: Organic Milk");

        stockTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #292929;");

        Text stockTime = new Text("15 minutes ago");

        stockTime.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #765F55;");

        VBox stockTextBox = new VBox(
                6,
                stockTitle,
                stockTime);

        stockTextBox.setAlignment(
                Pos.CENTER_LEFT);

        HBox stockNotification = new HBox(
                24,
                stockIconCircle,
                stockTextBox);

        stockNotification.setAlignment(
                Pos.CENTER_LEFT);

        stockNotification.setPadding(
                new Insets(20, 25, 20, 25));

        stockNotification.setPrefWidth(570);
        stockNotification.setPrefHeight(104);

        stockNotification.setStyle(
                "-fx-background-color: #F5F4F8;" +
                "-fx-background-radius: 12px;");

        // ============================================================
        // NOTIFICATION 3
        // ============================================================

        HBox aiIconCircle = new HBox();

        aiIconCircle.setPrefWidth(44);
        aiIconCircle.setPrefHeight(44);

        aiIconCircle.setMinWidth(44);
        aiIconCircle.setMinHeight(44);

        aiIconCircle.setMaxWidth(44);
        aiIconCircle.setMaxHeight(44);

        aiIconCircle.setAlignment(Pos.CENTER);

        aiIconCircle.setStyle(
                "-fx-background-color: #F1E6E0;" +
                "-fx-background-radius: 50px;");

        Text aiIcon = new Text("▣");

        aiIcon.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-fill: #C85A0A;");

        aiIconCircle.getChildren().add(aiIcon);

        Text aiTitle = new Text(
                "AI Insight: Sales up 12%");

        aiTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #292929;");

        Text aiTime = new Text("1 hour ago");

        aiTime.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #765F55;");

        VBox aiTextBox = new VBox(
                6,
                aiTitle,
                aiTime);

        aiTextBox.setAlignment(
                Pos.CENTER_LEFT);

        HBox aiNotification = new HBox(
                24,
                aiIconCircle,
                aiTextBox);

        aiNotification.setAlignment(
                Pos.CENTER_LEFT);

        aiNotification.setPadding(
                new Insets(20, 25, 20, 25));

        aiNotification.setPrefWidth(570);
        aiNotification.setPrefHeight(104);

        aiNotification.setStyle(
                "-fx-background-color: #F5F4F8;" +
                "-fx-background-radius: 12px;");

        // ============================================================
        // ADD NOTIFICATIONS TO LIST
        // ============================================================

        notificationList.getChildren().addAll(
                orderNotification,
                stockNotification,
                aiNotification);

        // ============================================================
        // SCROLL PANE
        // ============================================================

        ScrollPane notificationScrollPane =
                new ScrollPane(notificationList);

        notificationScrollPane.setPrefWidth(590);
        notificationScrollPane.setMaxWidth(590);

        notificationScrollPane.setPrefHeight(365);
        notificationScrollPane.setMaxHeight(365);

        notificationScrollPane.setFitToWidth(true);

        notificationScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        notificationScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        notificationScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;");

        // ============================================================
        // VIEW ALL ACTIVITY BUTTON
        // ============================================================

        Button viewAllButton =
                new Button("View All Activity");

        viewAllButton.setPrefWidth(150);
        viewAllButton.setPrefHeight(40);

        viewAllButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #B44A0A;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;");

        viewAllButton.setOnMouseEntered(e -> {

            viewAllButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #8F3505;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;");

        });

        viewAllButton.setOnMouseExited(e -> {

            viewAllButton.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #B44A0A;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;");

        });

        viewAllButton.setOnAction(e -> {

            System.out.println(
                    "View All Activity clicked");

        });

        // ============================================================
        // ADD CENTER COMPONENTS
        // ============================================================

        notificationPage.getChildren().addAll(
                titleContainer,
                notificationScrollPane,
                viewAllButton);

        // ============================================================
        // SET CENTER
        // ============================================================

        borderPane.setCenter(notificationPage);

        // ================================================================
        // FOOTER
        // ================================================================

        VBox footerBox = Constants.footer();

        // SET BOTTOM

        borderPane.setBottom(footerBox);

        // ============================================================
        // SCENE
        // ============================================================

        Scene notificationScene = new Scene(
                borderPane,
                1280,
                650);

        notificationScene.setFill(
                Color.web("#F8F7FC"));

        return notificationScene;
    }
}
package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class ShopkeeperChatbot {

    public static Scene chatbotScene() {

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
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9px;");

        Button botIcon = new Button("🤖 ChatBot");
        botIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: #FF6900;" +
                "-fx-background-radius: 9px;");

        Button profileIcon = new Button("👤 Profile");
        profileIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9px;");

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
        // HEADER ICON ACTIONS
        // ============================================================

        notificationIcon.setOnMouseClicked(e -> {
            System.out.println("Notification Icon clicked");
            Main.primaryStage.setScene(
                    ShopkeeperNotification.notificationScene());
        });

        botIcon.setOnMouseClicked(e -> {
            System.out.println("ChatBot Icon clicked");
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

        // ============================================================
        // SIDEBAR MENU
        // ============================================================

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

        // ================================================================
        // CENTER - CHATBOT PAGE
        // ================================================================

        VBox centerContainer = new VBox();

        centerContainer.setStyle(
                "-fx-background-color: #FFFFFF;");

        // ============================================================
        // CHATBOT TOP BAR
        // ============================================================

        Circle botCircle = new Circle(
                22,
                Color.web("#D8752C"));

        Text botSymbol = new Text("🤖");

        botSymbol.setStyle(
                "-fx-font-size: 19px;");

        StackPane botAvatar = new StackPane(
                botCircle,
                botSymbol);

        Circle onlineCircle = new Circle(
                5,
                Color.web("#18B957"));

        StackPane.setAlignment(
                onlineCircle,
                Pos.BOTTOM_RIGHT);

        StackPane.setMargin(
                onlineCircle,
                new Insets(0, 1, 1, 0));

        StackPane avatarWithStatus = new StackPane(
                botAvatar,
                onlineCircle);

        Text botTitle = new Text("BuyNeX AI");

        botTitle.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;");

        Text botSubtitle = new Text(
                "Intelligent Assistant");

        botSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #B34D0A;");

        VBox botNameBox = new VBox(
                1,
                botTitle,
                botSubtitle);

        botNameBox.setAlignment(
                Pos.CENTER_LEFT);

        HBox chatbotHeaderLeft = new HBox(
                12,
                avatarWithStatus,
                botNameBox);

        chatbotHeaderLeft.setAlignment(
                Pos.CENTER_LEFT);

        Text menuText = new Text("⋮");

        menuText.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #65483A;");

        Button menuButton = new Button();

        menuButton.setGraphic(menuText);

        menuButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-cursor: hand;");

        HBox chatbotHeader = new HBox(
                chatbotHeaderLeft,
                menuButton);

        HBox.setHgrow(
                chatbotHeaderLeft,
                Priority.ALWAYS);

        chatbotHeader.setAlignment(
                Pos.CENTER);

        chatbotHeader.setPadding(
                new Insets(14, 25, 14, 25));

        chatbotHeader.setStyle(
                "-fx-background-color: #FFFEFC;" +
                "-fx-border-color: #E8DDD6;" +
                "-fx-border-width: 0 0 1px 0;");

        // ============================================================
        // CHAT MESSAGE AREA
        // ============================================================

        VBox chatMessages = new VBox();

        chatMessages.setSpacing(18);

        chatMessages.setPadding(
                new Insets(25, 35, 25, 35));

        chatMessages.setFillWidth(true);

        // ============================================================
        // INITIAL BOT MESSAGE
        // ============================================================

        Circle firstBotCircle = new Circle(
                17,
                Color.web("#F7EDE6"));

        Text firstBotIcon = new Text("🤖");

        firstBotIcon.setStyle(
                "-fx-font-size: 14px;");

        StackPane firstBotAvatar = new StackPane(
                firstBotCircle,
                firstBotIcon);

        Label welcomeMessage = new Label(
                "Hello! I can help you analyze your sales data " +
                "or update your inventory. What would you like " +
                "to do today?");

        welcomeMessage.setWrapText(true);

        welcomeMessage.setStyle(
                "-fx-background-color: #F3F1F6;" +
                "-fx-background-radius: 0 12px 12px 12px;" +
                "-fx-padding: 14px;" +
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #29252A;");

        welcomeMessage.setMaxWidth(750);

        // ============================================================
        // QUICK ACTION BUTTONS
        // ============================================================

        Button analyzeSalesButton =
                new Button("Analyze\nSales");

        analyzeSalesButton.setPrefWidth(105);
        analyzeSalesButton.setPrefHeight(45);

        analyzeSalesButton.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E0D7D0;" +
                "-fx-border-radius: 22px;" +
                "-fx-background-radius: 22px;" +
                "-fx-text-fill: #B34D0A;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;");

        Button checkStockButton =
                new Button("Check\nStock");

        checkStockButton.setPrefWidth(105);
        checkStockButton.setPrefHeight(45);

        checkStockButton.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E0D7D0;" +
                "-fx-border-radius: 22px;" +
                "-fx-background-radius: 22px;" +
                "-fx-text-fill: #B34D0A;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;");

        HBox quickActions = new HBox(
                8,
                analyzeSalesButton,
                checkStockButton);

        quickActions.setAlignment(
                Pos.CENTER_LEFT);

        VBox firstBotMessage = new VBox(
                8,
                welcomeMessage,
                quickActions);

        firstBotMessage.setAlignment(
                Pos.TOP_LEFT);

        HBox firstMessageRow = new HBox(
                12,
                firstBotAvatar,
                firstBotMessage);

        firstMessageRow.setAlignment(
                Pos.TOP_LEFT);

        chatMessages.getChildren().add(
                firstMessageRow);

        // ============================================================
        // SCROLL PANE
        // ============================================================

        ScrollPane chatScrollPane =
                new ScrollPane(chatMessages);

        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setFitToHeight(false);

        chatScrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        chatScrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        chatScrollPane.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background: #FFFFFF;" +
                "-fx-border-color: transparent;");

        VBox.setVgrow(
                chatScrollPane,
                Priority.ALWAYS);

        // ============================================================
        // CHAT INPUT AREA
        // ============================================================

        Button attachmentButton =
                new Button("📎");

        attachmentButton.setPrefWidth(45);
        attachmentButton.setPrefHeight(45);

        attachmentButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-font-size: 19px;" +
                "-fx-text-fill: #6A5A50;" +
                "-fx-cursor: hand;");

        TextField chatInput =
                new TextField();

        chatInput.setPromptText(
                "Ask BuyNeX AI...");

        chatInput.setPrefHeight(45);

        chatInput.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #252228;" +
                "-fx-prompt-text-fill: #756B72;");

        HBox.setHgrow(
                chatInput,
                Priority.ALWAYS);

        Button sendButton =
                new Button("➤");

        sendButton.setPrefWidth(40);
        sendButton.setPrefHeight(40);

        sendButton.setStyle(
                "-fx-background-color: #B84D05;" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;");

        HBox inputBox = new HBox(
                5,
                attachmentButton,
                chatInput,
                sendButton);

        inputBox.setAlignment(
                Pos.CENTER);

        inputBox.setMaxWidth(620);
        inputBox.setPrefHeight(48);

        inputBox.setPadding(
                new Insets(2, 5, 2, 10));

        inputBox.setStyle(
                "-fx-background-color: #F1EFF4;" +
                "-fx-background-radius: 28px;");

        HBox inputWrapper = new HBox(
                inputBox);

        inputWrapper.setAlignment(
                Pos.CENTER);

        inputWrapper.setPadding(
                new Insets(18, 25, 8, 25));

        // ============================================================
        // DISCLAIMER
        // ============================================================

        Text disclaimer =
                new Text(
                        "AI can make mistakes. Verify important info.");

        disclaimer.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #77655A;");

        HBox disclaimerBox =
                new HBox(disclaimer);

        disclaimerBox.setAlignment(
                Pos.CENTER);

        disclaimerBox.setPadding(
                new Insets(0, 0, 12, 0));

        VBox inputArea = new VBox(
                inputWrapper,
                disclaimerBox);

        inputArea.setAlignment(
                Pos.CENTER);

        inputArea.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E6DDD7;" +
                "-fx-border-width: 1px 0 0 0;");

        // ============================================================
        // ADD CHATBOT CENTER COMPONENTS
        // ============================================================

        centerContainer.getChildren().addAll(
                chatbotHeader,
                chatScrollPane,
                inputArea);

        VBox.setVgrow(
                chatScrollPane,
                Priority.ALWAYS);

        borderPane.setCenter(
                centerContainer);

        // ============================================================
        // CHAT FUNCTIONS
        // ============================================================

        // ------------------------------------------------------------
        // ADD USER MESSAGE
        // ------------------------------------------------------------

        Runnable sendCurrentMessage = () -> {

            String message =
                    chatInput.getText().trim();

            if (message.isEmpty()) {
                return;
            }

            // USER AVATAR

            Circle userCircle = new Circle(
                    17,
                    Color.web("#F2E6DC"));

            Text userIcon = new Text("👤");

            userIcon.setStyle(
                    "-fx-font-size: 13px;");

            StackPane userAvatar =
                    new StackPane(
                            userCircle,
                            userIcon);

            // USER MESSAGE

            Label userMessage =
                    new Label(message);

            userMessage.setWrapText(true);

            userMessage.setMaxWidth(620);

            userMessage.setStyle(
                    "-fx-background-color: #B64C05;" +
                    "-fx-background-radius: 14px 14px 0 14px;" +
                    "-fx-padding: 12px 16px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: white;");

            HBox userBubble =
                    new HBox(userMessage);

            userBubble.setAlignment(
                    Pos.CENTER_RIGHT);

            HBox.setHgrow(
                    userBubble,
                    Priority.ALWAYS);

            HBox userRow =
                    new HBox(
                            userBubble,
                            userAvatar);

            userRow.setSpacing(10);

            userRow.setAlignment(
                    Pos.TOP_RIGHT);

            chatMessages.getChildren().add(
                    userRow);

            chatInput.clear();

            // SCROLL TO BOTTOM

            chatScrollPane.layout();

            chatScrollPane.setVvalue(1.0);

            // --------------------------------------------------------
            // SIMPLE BOT RESPONSE
            // --------------------------------------------------------

            Label botResponse =
                    new Label(
                            "Sure! I received your request. " +
                            "I can help you with sales, inventory, " +
                            "orders and other shopkeeper activities.");

            botResponse.setWrapText(true);

            botResponse.setMaxWidth(700);

            botResponse.setStyle(
                    "-fx-background-color: #F3F1F6;" +
                    "-fx-background-radius: 0 12px 12px 12px;" +
                    "-fx-padding: 13px 15px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: #29252A;");

            Circle responseCircle =
                    new Circle(
                            17,
                            Color.web("#F7EDE6"));

            Text responseIcon =
                    new Text("🤖");

            responseIcon.setStyle(
                    "-fx-font-size: 13px;");

            StackPane responseAvatar =
                    new StackPane(
                            responseCircle,
                            responseIcon);

            HBox responseRow =
                    new HBox(
                            12,
                            responseAvatar,
                            botResponse);

            responseRow.setAlignment(
                    Pos.TOP_LEFT);

            chatMessages.getChildren().add(
                    responseRow);

            chatMessages.layout();

            chatScrollPane.layout();

            chatScrollPane.setVvalue(1.0);
        };

        // ============================================================
        // SEND BUTTON
        // ============================================================

        sendButton.setOnAction(e ->
                sendCurrentMessage.run());

        // ENTER KEY SENDS MESSAGE

        chatInput.setOnAction(e ->
                sendCurrentMessage.run());

        // ============================================================
        // ANALYZE SALES BUTTON
        // ============================================================

        analyzeSalesButton.setOnAction(e -> {

            Label userMessage =
                    new Label(
                            "Analyze my sales.");

            userMessage.setStyle(
                    "-fx-background-color: #B64C05;" +
                    "-fx-background-radius: 14px 14px 0 14px;" +
                    "-fx-padding: 12px 16px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: white;");

            HBox userBubble =
                    new HBox(userMessage);

            userBubble.setAlignment(
                    Pos.CENTER_RIGHT);

            Circle userCircle =
                    new Circle(
                            17,
                            Color.web("#F2E6DC"));

            Text userIcon =
                    new Text("👤");

            userIcon.setStyle(
                    "-fx-font-size: 13px;");

            StackPane userAvatar =
                    new StackPane(
                            userCircle,
                            userIcon);

            HBox userRow =
                    new HBox(
                            userBubble,
                            userAvatar);

            userRow.setAlignment(
                    Pos.TOP_RIGHT);

            userRow.setSpacing(10);

            chatMessages.getChildren().add(
                    userRow);

            Label botResponse =
                    new Label(
                            "Sales analysis is ready. " +
                            "You can review today's sales, " +
                            "best-selling products and recent order trends.");

            botResponse.setWrapText(true);

            botResponse.setMaxWidth(700);

            botResponse.setStyle(
                    "-fx-background-color: #F3F1F6;" +
                    "-fx-background-radius: 0 12px 12px 12px;" +
                    "-fx-padding: 13px 15px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: #29252A;");

            Circle botCircle2 =
                    new Circle(
                            17,
                            Color.web("#F7EDE6"));

            Text botIcon2 =
                    new Text("🤖");

            botIcon2.setStyle(
                    "-fx-font-size: 13px;");

            StackPane botAvatar2 =
                    new StackPane(
                            botCircle2,
                            botIcon2);

            HBox botRow =
                    new HBox(
                            12,
                            botAvatar2,
                            botResponse);

            botRow.setAlignment(
                    Pos.TOP_LEFT);

            chatMessages.getChildren().add(
                    botRow);

            chatMessages.layout();

            chatScrollPane.layout();

            chatScrollPane.setVvalue(1.0);
        });

        // ============================================================
        // CHECK STOCK BUTTON
        // ============================================================

        checkStockButton.setOnAction(e -> {

            Label userMessage =
                    new Label(
                            "Check my stock.");

            userMessage.setStyle(
                    "-fx-background-color: #B64C05;" +
                    "-fx-background-radius: 14px 14px 0 14px;" +
                    "-fx-padding: 12px 16px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: white;");

            HBox userBubble =
                    new HBox(userMessage);

            userBubble.setAlignment(
                    Pos.CENTER_RIGHT);

            Circle userCircle =
                    new Circle(
                            17,
                            Color.web("#F2E6DC"));

            Text userIcon =
                    new Text("👤");

            userIcon.setStyle(
                    "-fx-font-size: 13px;");

            StackPane userAvatar =
                    new StackPane(
                            userCircle,
                            userIcon);

            HBox userRow =
                    new HBox(
                            userBubble,
                            userAvatar);

            userRow.setAlignment(
                    Pos.TOP_RIGHT);

            userRow.setSpacing(10);

            chatMessages.getChildren().add(
                    userRow);

            Label botResponse =
                    new Label(
                            "Your inventory can be checked here. " +
                            "I can help identify low-stock products " +
                            "and items that may need restocking.");

            botResponse.setWrapText(true);

            botResponse.setMaxWidth(700);

            botResponse.setStyle(
                    "-fx-background-color: #F3F1F6;" +
                    "-fx-background-radius: 0 12px 12px 12px;" +
                    "-fx-padding: 13px 15px;" +
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: #29252A;");

            Circle botCircle2 =
                    new Circle(
                            17,
                            Color.web("#F7EDE6"));

            Text botIcon2 =
                    new Text("🤖");

            botIcon2.setStyle(
                    "-fx-font-size: 13px;");

            StackPane botAvatar2 =
                    new StackPane(
                            botCircle2,
                            botIcon2);

            HBox botRow =
                    new HBox(
                            12,
                            botAvatar2,
                            botResponse);

            botRow.setAlignment(
                    Pos.TOP_LEFT);

            chatMessages.getChildren().add(
                    botRow);

            chatMessages.layout();

            chatScrollPane.layout();

            chatScrollPane.setVvalue(1.0);
        });

        // ============================================================
        // FOOTER
        // ============================================================

        VBox footerBox = Constants.footer();

        borderPane.setBottom(
                footerBox);

        // ============================================================
        // SCENE
        // ============================================================

        Scene chatbotScene =
                new Scene(
                        borderPane,
                        1280,
                        650);

        chatbotScene.setFill(
                Color.web("#F8F7FC"));

        return chatbotScene;
    }
}
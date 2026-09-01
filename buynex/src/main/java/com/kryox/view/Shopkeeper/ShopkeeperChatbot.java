package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.view.Customer.Homepage;

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

                   BorderPane borderPane = new BorderPane();



                // ============================================================
                // HEADER
                // ============================================================
                HBox headerMainBox = ViewConstants.header();

                // Header background
                headerMainBox.setStyle(
                                "-fx-background-color: #EBCCB7;");

                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = createSidebar();
                borderPane.setLeft(sidebar);

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

                Button analyzeSalesButton = new Button("Analyze\nSales");

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

                Button checkStockButton = new Button("Check\nStock");

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

                ScrollPane chatScrollPane = new ScrollPane(chatMessages);

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

                Button attachmentButton = new Button("📎");

                attachmentButton.setPrefWidth(45);
                attachmentButton.setPrefHeight(45);

                attachmentButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-font-size: 19px;" +
                                                "-fx-text-fill: #6A5A50;" +
                                                "-fx-cursor: hand;");

                TextField chatInput = new TextField();

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

                Button sendButton = new Button("➤");

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

                Text disclaimer = new Text(
                                "AI can make mistakes. Verify important info.");

                disclaimer.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #77655A;");

                HBox disclaimerBox = new HBox(disclaimer);

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

                        String message = chatInput.getText().trim();

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

                        StackPane userAvatar = new StackPane(
                                        userCircle,
                                        userIcon);

                        // USER MESSAGE

                        Label userMessage = new Label(message);

                        userMessage.setWrapText(true);

                        userMessage.setMaxWidth(620);

                        userMessage.setStyle(
                                        "-fx-background-color: #B64C05;" +
                                                        "-fx-background-radius: 14px 14px 0 14px;" +
                                                        "-fx-padding: 12px 16px;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-text-fill: white;");

                        HBox userBubble = new HBox(userMessage);

                        userBubble.setAlignment(
                                        Pos.CENTER_RIGHT);

                        HBox.setHgrow(
                                        userBubble,
                                        Priority.ALWAYS);

                        HBox userRow = new HBox(
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

                        Label botResponse = new Label(
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

                        Circle responseCircle = new Circle(
                                        17,
                                        Color.web("#F7EDE6"));

                        Text responseIcon = new Text("🤖");

                        responseIcon.setStyle(
                                        "-fx-font-size: 13px;");

                        StackPane responseAvatar = new StackPane(
                                        responseCircle,
                                        responseIcon);

                        HBox responseRow = new HBox(
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

                sendButton.setOnAction(e -> sendCurrentMessage.run());

                // ENTER KEY SENDS MESSAGE

                chatInput.setOnAction(e -> sendCurrentMessage.run());

                // ============================================================
                // ANALYZE SALES BUTTON
                // ============================================================

                analyzeSalesButton.setOnAction(e -> {

                        Label userMessage = new Label(
                                        "Analyze my sales.");

                        userMessage.setStyle(
                                        "-fx-background-color: #B64C05;" +
                                                        "-fx-background-radius: 14px 14px 0 14px;" +
                                                        "-fx-padding: 12px 16px;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-text-fill: white;");

                        HBox userBubble = new HBox(userMessage);

                        userBubble.setAlignment(
                                        Pos.CENTER_RIGHT);

                        Circle userCircle = new Circle(
                                        17,
                                        Color.web("#F2E6DC"));

                        Text userIcon = new Text("👤");

                        userIcon.setStyle(
                                        "-fx-font-size: 13px;");

                        StackPane userAvatar = new StackPane(
                                        userCircle,
                                        userIcon);

                        HBox userRow = new HBox(
                                        userBubble,
                                        userAvatar);

                        userRow.setAlignment(
                                        Pos.TOP_RIGHT);

                        userRow.setSpacing(10);

                        chatMessages.getChildren().add(
                                        userRow);

                        Label botResponse = new Label(
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

                        Circle botCircle2 = new Circle(
                                        17,
                                        Color.web("#F7EDE6"));

                        Text botIcon2 = new Text("🤖");

                        botIcon2.setStyle(
                                        "-fx-font-size: 13px;");

                        StackPane botAvatar2 = new StackPane(
                                        botCircle2,
                                        botIcon2);

                        HBox botRow = new HBox(
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

                        Label userMessage = new Label(
                                        "Check my stock.");

                        userMessage.setStyle(
                                        "-fx-background-color: #B64C05;" +
                                                        "-fx-background-radius: 14px 14px 0 14px;" +
                                                        "-fx-padding: 12px 16px;" +
                                                        "-fx-font-size: 14px;" +
                                                        "-fx-text-fill: white;");

                        HBox userBubble = new HBox(userMessage);

                        userBubble.setAlignment(
                                        Pos.CENTER_RIGHT);

                        Circle userCircle = new Circle(
                                        17,
                                        Color.web("#F2E6DC"));

                        Text userIcon = new Text("👤");

                        userIcon.setStyle(
                                        "-fx-font-size: 13px;");

                        StackPane userAvatar = new StackPane(
                                        userCircle,
                                        userIcon);

                        HBox userRow = new HBox(
                                        userBubble,
                                        userAvatar);

                        userRow.setAlignment(
                                        Pos.TOP_RIGHT);

                        userRow.setSpacing(10);

                        chatMessages.getChildren().add(
                                        userRow);

                        Label botResponse = new Label(
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

                        Circle botCircle2 = new Circle(
                                        17,
                                        Color.web("#F7EDE6"));

                        Text botIcon2 = new Text("🤖");

                        botIcon2.setStyle(
                                        "-fx-font-size: 13px;");

                        StackPane botAvatar2 = new StackPane(
                                        botCircle2,
                                        botIcon2);

                        HBox botRow = new HBox(
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

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ============================================================
                // SCENE
                // ============================================================

                Scene chatbotScene = new Scene(
                                borderPane,
                                ViewConstants.STAGE_WIDTH,
                                ViewConstants.STAGE_HEIGHT
                        );

                chatbotScene.setFill(
                                Color.web("#EEE5DF"));

                return chatbotScene;
        }

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #EBCCB7;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                false);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                false);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                false);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));

                offersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));

                analyticsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperAnalytics
                                                                .analyticsScene()));

                settingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }
}
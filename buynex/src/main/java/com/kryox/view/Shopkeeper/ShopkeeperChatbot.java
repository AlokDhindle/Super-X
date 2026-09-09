package com.kryox.view.Shopkeeper;

import com.kryox.controller.Shopkeeper.ShopkeeperChatbotController;
import com.kryox.controller.Shopkeeper.ShopkeeperChatbotController.StoreSnapshot;
import com.kryox.view.Customer.Homepage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ShopkeeperChatbot {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    public static Scene chatbotScene() {

        BorderPane borderPane = new BorderPane();

        // Header
        HBox headerMainBox = ViewConstants.header();
        headerMainBox.setStyle("-fx-background-color: #EBCCB7;");
        borderPane.setTop(headerMainBox);

        // Sidebar
        VBox sidebar = createSidebar();
        borderPane.setLeft(sidebar);

        // Center - Chatbot Page
        VBox centerContainer = new VBox();
        centerContainer.setStyle("-fx-background-color: #FFFFFF;");

        // 1. Chatbot Top Bar
        Circle botCircle = new Circle(22, Color.web("#D8752C"));
        Text botSymbol = new Text("🤖");
        botSymbol.setStyle("-fx-font-size: 19px;");

        StackPane botAvatar = new StackPane(botCircle, botSymbol);

        Circle onlineCircle = new Circle(5, Color.web("#18B957"));
        StackPane.setAlignment(onlineCircle, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(onlineCircle, new Insets(0, 1, 1, 0));

        StackPane avatarWithStatus = new StackPane(botAvatar, onlineCircle);

        Text botTitle = new Text("BuyNeX AI");
        botTitle.setStyle("-fx-font-size: 24px;-fx-font-weight: bold;-fx-fill: #222222;");

        Text botSubtitle = new Text("Intelligent Store Co-pilot • Gemini Powered");
        botSubtitle.setStyle("-fx-font-size: 13px;-fx-font-weight: bold;-fx-fill: #B34D0A;");

        VBox botNameBox = new VBox(1, botTitle, botSubtitle);
        botNameBox.setAlignment(Pos.CENTER_LEFT);

        HBox chatbotHeaderLeft = new HBox(12, avatarWithStatus, botNameBox);
        chatbotHeaderLeft.setAlignment(Pos.CENTER_LEFT);

        Text menuText = new Text("⋮");
        menuText.setStyle("-fx-font-size: 27px;-fx-font-weight: bold;-fx-fill: #65483A;");

        Button menuButton = new Button();
        menuButton.setGraphic(menuText);
        menuButton.setStyle("-fx-background-color: transparent;-fx-cursor: hand;");

        HBox chatbotHeader = new HBox(chatbotHeaderLeft, menuButton);
        HBox.setHgrow(chatbotHeaderLeft, Priority.ALWAYS);
        chatbotHeader.setAlignment(Pos.CENTER);
        chatbotHeader.setPadding(new Insets(14, 25, 14, 25));
        chatbotHeader.setStyle("-fx-background-color: #FFFEFC;-fx-border-color: #E8DDD6;-fx-border-width: 0 0 1px 0;");

        // 2. Chat Message Area
        VBox chatMessages = new VBox();
        chatMessages.setSpacing(18);
        chatMessages.setPadding(new Insets(25, 35, 25, 35));
        chatMessages.setFillWidth(true);

        // Scroll Pane
        ScrollPane chatScrollPane = new ScrollPane(chatMessages);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setFitToHeight(false);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        chatScrollPane.setStyle("-fx-background-color: #FFFFFF;-fx-background: #FFFFFF;-fx-border-color: transparent;");
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        // Chat Input Area Components
        Button attachmentButton = new Button("📎");
        attachmentButton.setPrefWidth(45);
        attachmentButton.setPrefHeight(45);
        attachmentButton.setStyle("-fx-background-color: transparent;-fx-font-size: 19px;-fx-text-fill: #6A5A50;-fx-cursor: hand;");

        TextField chatInput = new TextField();
        chatInput.setPromptText("Ask BuyNeX AI about your sales, stock, orders...");
        chatInput.setPrefHeight(45);
        chatInput.setStyle("-fx-background-color: transparent;-fx-border-color: transparent;-fx-font-size: 15px;-fx-text-fill: #252228;-fx-prompt-text-fill: #756B72;");
        HBox.setHgrow(chatInput, Priority.ALWAYS);

        Button sendButton = new Button("➤");
        sendButton.setPrefWidth(40);
        sendButton.setPrefHeight(40);
        sendButton.setStyle("-fx-background-color: #B84D05;-fx-background-radius: 50%;-fx-text-fill: white;-fx-font-size: 19px;-fx-font-weight: bold;-fx-cursor: hand;");

        HBox inputBox = new HBox(5, attachmentButton, chatInput, sendButton);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setMaxWidth(680);
        inputBox.setPrefHeight(48);
        inputBox.setPadding(new Insets(2, 5, 2, 10));
        inputBox.setStyle("-fx-background-color: #F1EFF4;-fx-background-radius: 28px;");

        HBox inputWrapper = new HBox(inputBox);
        inputWrapper.setAlignment(Pos.CENTER);
        inputWrapper.setPadding(new Insets(18, 25, 8, 25));

        Text disclaimer = new Text("BuyNeX AI is linked to your live database. Verify critical inventory updates.");
        disclaimer.setStyle("-fx-font-size: 11px;-fx-fill: #77655A;");

        HBox disclaimerBox = new HBox(disclaimer);
        disclaimerBox.setAlignment(Pos.CENTER);
        disclaimerBox.setPadding(new Insets(0, 0, 12, 0));

        VBox inputArea = new VBox(inputWrapper, disclaimerBox);
        inputArea.setAlignment(Pos.CENTER);
        inputArea.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #E6DDD7;-fx-border-width: 1px 0 0 0;");

        centerContainer.getChildren().addAll(chatbotHeader, chatScrollPane, inputArea);
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);
        borderPane.setCenter(centerContainer);

        // Logic flag to prevent concurrent requests
        AtomicBoolean isResponding = new AtomicBoolean(false);

        // Helper to smoothly scroll to bottom
        Runnable scrollToBottom = () -> Platform.runLater(() -> {
            chatMessages.layout();
            chatScrollPane.layout();
            chatScrollPane.setVvalue(1.0);
        });

        // Helper to add user message
        java.util.function.Consumer<String> addUserMessage = (text) -> {
            Circle userCircle = new Circle(17, Color.web("#F2E6DC"));
            Text userIcon = new Text("👤");
            userIcon.setStyle("-fx-font-size: 13px;");
            StackPane userAvatar = new StackPane(userCircle, userIcon);

            Label userMessage = new Label(text);
            userMessage.setWrapText(true);
            userMessage.setMaxWidth(620);
            userMessage.setStyle("-fx-background-color: #B64C05;-fx-background-radius: 14px 14px 0 14px;-fx-padding: 12px 16px;-fx-font-size: 14px;-fx-text-fill: white;");

            Text timestamp = new Text(LocalTime.now().format(TIME_FORMATTER));
            timestamp.setStyle("-fx-font-size: 10px;-fx-fill: #8D7F78;");

            VBox messageWithTime = new VBox(3, userMessage, timestamp);
            messageWithTime.setAlignment(Pos.TOP_RIGHT);

            HBox userRow = new HBox(10, messageWithTime, userAvatar);
            userRow.setAlignment(Pos.TOP_RIGHT);

            chatMessages.getChildren().add(userRow);
            scrollToBottom.run();
        };

        // Helper to add bot message
        java.util.function.Consumer<String> addBotMessage = (text) -> {
            Circle responseCircle = new Circle(17, Color.web("#F7EDE6"));
            Text responseIcon = new Text("🤖");
            responseIcon.setStyle("-fx-font-size: 13px;");
            StackPane responseAvatar = new StackPane(responseCircle, responseIcon);

            Label botResponse = new Label(text);
            botResponse.setWrapText(true);
            botResponse.setMaxWidth(700);
            botResponse.setStyle("-fx-background-color: #F6F4F8;-fx-background-radius: 0 14px 14px 14px;-fx-padding: 14px 18px;-fx-font-size: 14px;-fx-text-fill: #252228;-fx-border-color: #E6E1EB;-fx-border-radius: 0 14px 14px 14px;");

            Text timestamp = new Text(LocalTime.now().format(TIME_FORMATTER));
            timestamp.setStyle("-fx-font-size: 10px;-fx-fill: #8D7F78;");

            VBox botMsgBox = new VBox(3, botResponse, timestamp);
            botMsgBox.setAlignment(Pos.TOP_LEFT);

            HBox responseRow = new HBox(12, responseAvatar, botMsgBox);
            responseRow.setAlignment(Pos.TOP_LEFT);

            chatMessages.getChildren().add(responseRow);
            scrollToBottom.run();
        };

        // Helper to create a thinking indicator node
        java.util.function.Supplier<HBox> createThinkingNode = () -> {
            Circle thinkCircle = new Circle(17, Color.web("#F7EDE6"));
            Text thinkIcon = new Text("🤖");
            thinkIcon.setStyle("-fx-font-size: 13px;");
            StackPane thinkAvatar = new StackPane(thinkCircle, thinkIcon);

            Label thinkingLabel = new Label("🤖 BuyNeX AI is analyzing your store data...");
            thinkingLabel.setStyle("-fx-background-color: #F8F5F2;-fx-background-radius: 0 12px 12px 12px;-fx-padding: 10px 14px;-fx-font-size: 13px;-fx-text-fill: #A0521C;-fx-font-style: italic;");

            HBox row = new HBox(12, thinkAvatar, thinkingLabel);
            row.setAlignment(Pos.CENTER_LEFT);
            return row;
        };

        // Generic asynchronous executor for queries
        java.util.function.BiConsumer<String, java.util.function.Supplier<String>> executeQuery = (displayUserText, replySupplier) -> {
            if (isResponding.get()) {
                return;
            }
            isResponding.set(true);

            addUserMessage.accept(displayUserText);
            HBox thinkingRow = createThinkingNode.get();
            chatMessages.getChildren().add(thinkingRow);
            scrollToBottom.run();

            new Thread(() -> {
                try {
                    String reply = replySupplier.get();
                    Platform.runLater(() -> {
                        chatMessages.getChildren().remove(thinkingRow);
                        addBotMessage.accept(reply);
                        isResponding.set(false);
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() -> {
                        chatMessages.getChildren().remove(thinkingRow);
                        addBotMessage.accept("Sorry, an error occurred while analyzing your store data. Please try again.");
                        isResponding.set(false);
                    });
                }
            }).start();
        };

        // Method holder to populate initial welcome card and quick action chips
        final Runnable[] resetChatRef = new Runnable[1];

        Runnable setupWelcomeMessage = () -> {
            chatMessages.getChildren().clear();

            Circle firstBotCircle = new Circle(17, Color.web("#F7EDE6"));
            Text firstBotIcon = new Text("🤖");
            firstBotIcon.setStyle("-fx-font-size: 14px;");
            StackPane firstBotAvatar = new StackPane(firstBotCircle, firstBotIcon);

            StoreSnapshot snap = ShopkeeperChatbotController.getStoreSnapshot();

            String welcomeGreeting = "Hello " + snap.ownerName + "! 👋 Welcome to BuyNeX AI Assistant for '" + snap.shopName + "'.\n\n"
                    + "I have real-time access to your store database. You currently have " + snap.totalProducts + " active catalog products and "
                    + snap.totalOrders + " total orders on record.\n\n"
                    + "What would you like to analyze today? You can choose a quick action or type any question below!";

            Label welcomeMessage = new Label(welcomeGreeting);
            welcomeMessage.setWrapText(true);
            welcomeMessage.setStyle("-fx-background-color: #F3F1F6;-fx-background-radius: 0 12px 12px 12px;-fx-padding: 15px 18px;-fx-font-size: 14px;-fx-text-fill: #29252A;");
            welcomeMessage.setMaxWidth(750);

            // Dynamic Quick Action Buttons
            Button analyzeSalesButton = createQuickActionButton("📊 Analyze Sales");
            Button checkStockButton = createQuickActionButton("📦 Check Stock");
            Button pendingOrdersButton = createQuickActionButton("🛒 Pending Orders");
            Button topSellingButton = createQuickActionButton("🏆 Top Sellers");
            Button growthTipsButton = createQuickActionButton("💡 Growth Tips");
            Button clearChatButton = createQuickActionButton("🧹 Clear Chat");

            analyzeSalesButton.setOnAction(e -> executeQuery.accept("Analyze my sales and revenue.", () -> {
                StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
                return ShopkeeperChatbotController.formatSalesAnalysis(s);
            }));

            checkStockButton.setOnAction(e -> executeQuery.accept("Check my inventory and stock levels.", () -> {
                StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
                return ShopkeeperChatbotController.formatStockStatus(s);
            }));

            pendingOrdersButton.setOnAction(e -> executeQuery.accept("Show my pending and active orders.", () -> {
                StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
                return ShopkeeperChatbotController.formatOrdersStatus(s);
            }));

            topSellingButton.setOnAction(e -> executeQuery.accept("What are my top selling products?", () -> {
                StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
                return ShopkeeperChatbotController.formatTopSelling(s);
            }));

            growthTipsButton.setOnAction(e -> executeQuery.accept("Give me AI recommendations to grow my store.", () -> {
                StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
                return ShopkeeperChatbotController.formatGrowthTips(s);
            }));

            clearChatButton.setOnAction(e -> {
                if (resetChatRef[0] != null) {
                    resetChatRef[0].run();
                }
            });

            HBox quickActionsRow1 = new HBox(8, analyzeSalesButton, checkStockButton, pendingOrdersButton);
            quickActionsRow1.setAlignment(Pos.CENTER_LEFT);

            HBox quickActionsRow2 = new HBox(8, topSellingButton, growthTipsButton, clearChatButton);
            quickActionsRow2.setAlignment(Pos.CENTER_LEFT);

            VBox quickActionsBox = new VBox(6, quickActionsRow1, quickActionsRow2);
            quickActionsBox.setAlignment(Pos.CENTER_LEFT);
            quickActionsBox.setPadding(new Insets(5, 0, 0, 0));

            VBox firstBotMessage = new VBox(10, welcomeMessage, quickActionsBox);
            firstBotMessage.setAlignment(Pos.TOP_LEFT);

            HBox firstMessageRow = new HBox(12, firstBotAvatar, firstBotMessage);
            firstMessageRow.setAlignment(Pos.TOP_LEFT);

            chatMessages.getChildren().add(firstMessageRow);
            scrollToBottom.run();
        };

        resetChatRef[0] = setupWelcomeMessage;

        // Initialize welcome message
        setupWelcomeMessage.run();

        // Chat Sending logic from text field
        Runnable sendCurrentMessage = () -> {
            String message = chatInput.getText().trim();
            if (message.isEmpty() || isResponding.get()) {
                return;
            }
            chatInput.clear();
            executeQuery.accept(message, () -> ShopkeeperChatbotController.getBotReply(message));
        };

        sendButton.setOnAction(e -> sendCurrentMessage.run());
        chatInput.setOnAction(e -> sendCurrentMessage.run());

        // Context Menu on Top Header Menu (⋮)
        ContextMenu headerMenu = new ContextMenu();
        MenuItem refreshItem = new MenuItem("🔄 Refresh Store Snapshot");
        MenuItem clearItem = new MenuItem("🧹 Reset Conversation");
        MenuItem overviewItem = new MenuItem("ℹ️ View Store Info");

        refreshItem.setOnAction(e -> executeQuery.accept("Refresh my live store data snapshot.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return "🔄 Store data refreshed from live database!\n\n"
                    + "• Store Name: " + s.shopName + " (" + s.ownerName + ")\n"
                    + "• Total Products: " + s.totalProducts + " (Low: " + s.lowStockCount + ", Out: " + s.outOfStockCount + ")\n"
                    + "• Total Orders: " + s.totalOrders + " (New: " + s.newOrdersCount + ", Preparing: " + s.preparingOrdersCount + ")\n"
                    + "• Total Revenue: ₹" + String.format("%.2f", s.totalRevenue);
        }));

        clearItem.setOnAction(e -> {
            if (resetChatRef[0] != null) {
                resetChatRef[0].run();
            }
        });

        overviewItem.setOnAction(e -> executeQuery.accept("Give me an overview of my store profile.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return "🏪 Store Profile Snapshot:\n\n"
                    + "• Shop Name: " + s.shopName + "\n"
                    + "• Merchant: " + s.ownerName + "\n"
                    + "• Category: " + s.category + "\n"
                    + "• Catalog Size: " + s.totalProducts + " items\n"
                    + "• Total Order Value: ₹" + String.format("%.2f", s.totalRevenue);
        }));

        headerMenu.getItems().addAll(refreshItem, overviewItem, clearItem);
        menuButton.setOnAction(e -> headerMenu.show(menuButton, Side.BOTTOM, 0, 0));

        // Quick Attachment queries on 📎
        ContextMenu attachmentMenu = new ContextMenu();
        MenuItem attSales = new MenuItem("📊 Generate Sales & Revenue Report");
        MenuItem attStock = new MenuItem("📦 Audit Low Stock Inventory");
        MenuItem attOrders = new MenuItem("🛒 Active Order Status Breakdown");
        MenuItem attTips = new MenuItem("💡 Strategic Improvement Suggestions");

        attSales.setOnAction(e -> executeQuery.accept("Generate detailed sales report.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return ShopkeeperChatbotController.formatSalesAnalysis(s);
        }));

        attStock.setOnAction(e -> executeQuery.accept("Audit inventory and list critical items.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return ShopkeeperChatbotController.formatStockStatus(s);
        }));

        attOrders.setOnAction(e -> executeQuery.accept("Show active order status breakdown.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return ShopkeeperChatbotController.formatOrdersStatus(s);
        }));

        attTips.setOnAction(e -> executeQuery.accept("Provide strategic improvement suggestions.", () -> {
            StoreSnapshot s = ShopkeeperChatbotController.getStoreSnapshot();
            return ShopkeeperChatbotController.formatGrowthTips(s);
        }));

        attachmentMenu.getItems().addAll(attSales, attStock, attOrders, attTips);
        attachmentButton.setOnAction(e -> attachmentMenu.show(attachmentButton, Side.TOP, 0, -10));

        // Footer
        VBox footerBox = ViewConstants.footer();
        borderPane.setBottom(footerBox);

        // Scene
        Scene chatbotScene = new Scene(borderPane, 1550, 850);
        chatbotScene.setFill(Color.web("#EEE5DF"));

        return chatbotScene;
    }

    private static Button createQuickActionButton(String text) {
        Button btn = new Button(text);
        btn.setPrefHeight(36);
        btn.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #E0D7D0;-fx-border-radius: 18px;-fx-background-radius: 18px;-fx-text-fill: #B34D0A;-fx-font-size: 12px;-fx-font-weight: bold;-fx-padding: 6px 14px;-fx-cursor: hand;");

        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #B34D0A;-fx-border-color: #B34D0A;-fx-border-radius: 18px;-fx-background-radius: 18px;-fx-text-fill: #FFFFFF;-fx-font-size: 12px;-fx-font-weight: bold;-fx-padding: 6px 14px;-fx-cursor: hand;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #FFFFFF;-fx-border-color: #E0D7D0;-fx-border-radius: 18px;-fx-background-radius: 18px;-fx-text-fill: #B34D0A;-fx-font-size: 12px;-fx-font-weight: bold;-fx-padding: 6px 14px;-fx-cursor: hand;"));

        return btn;
    }

    private static VBox createSidebar() {

        VBox sidebar = new VBox();
        sidebar.setMinWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setMaxWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setStyle("-fx-background-color: #EBCCB7;-fx-border-color: #E3C7BA;-fx-border-width: 0 1px 0 0;");

        HBox profileBox = ViewConstants.letfProfileBox();
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(new Insets(30, 20, 30, 20));

        Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", false);
        Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", false);
        Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);
        Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", false);
        Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);
        Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", false);
        Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

        VBox menu = new VBox(5, dashboardButton, ordersButton, inventoryButton, offersButton, analyticsButton, settingsButton, supportButton);
        menu.setPadding(new Insets(0, 8, 0, 8));
        VBox.setVgrow(menu, Priority.ALWAYS);

        sidebar.getChildren().addAll(profileBox, menu);

        dashboardButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperDashboard.dashboardScene()));
        inventoryButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperInventory.inventoryScene()));
        ordersButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperOrderReady.ordersScene()));
        offersButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene()));
        analyticsButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperAnalytics.analyticsScene()));
        settingsButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperSettings.settingsScene()));
        supportButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperSupport.supportScene()));

        return sidebar;
    }
}
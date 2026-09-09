package com.kryox.view.Delivery;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kryox.controller.Delivery.DeliveryChatbotController;
import com.kryox.view.Customer.Homepage;

public class PartnerChatSupport {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#EEE5DE";
    private static final String BORDER_COLOR = "#f0edf2";

    public static class ChatWorkspaceData {
        public String partnerName = "Alex Walker";
        public String activeChannelId = "CH-AI-BOT";
        public List<ChatChannel> channels = new ArrayList<>();
        public Map<String, List<ChatMessage>> channelMessages = new HashMap<>();

        public ChatWorkspaceData() {
            // Channels
            channels.add(new ChatChannel("CH-AI-BOT", "🤖 Delivery AI Assistant", "Gemini AI • Online 24x7", "Instant assistance for routes, earnings & app", "Just now", 0, true));
            channels.add(new ChatChannel("CH-DISPATCH", "🎧 Dispatch Support Desk", "Agent Riya • Online", "Order address confirmed for #BN-4920", "Just now", 1, false));
            channels.add(new ChatChannel("CH-CUSTOMER", "👤 Sarah J. (Customer)", "Order #BN-4920", "Please leave it at door 4B, thank you!", "5m ago", 2, false));
            channels.add(new ChatChannel("CH-OPS", "⚡ Safety & Breakdown Hub", "24x7 Operations", "Roadside assistance protocol active", "Yesterday", 0, false));

            // AI Bot Messages
            List<ChatMessage> aiMsgs = new ArrayList<>();
            aiMsgs.add(new ChatMessage("M-001", "SUPPORT", "Delivery AI Bot", "Hello Alex! 🤖 I am your BuyNeX Delivery AI Assistant. How can I help you today with your route, delivery orders, or payouts?", "Just now"));
            channelMessages.put("CH-AI-BOT", aiMsgs);

            // Dispatch Desk Messages
            List<ChatMessage> dispatchMsgs = new ArrayList<>();
            dispatchMsgs.add(new ChatMessage("M-101", "SUPPORT", "Dispatch Support", "Hello Alex! How can we assist you on your active run?", "04:35 PM"));
            dispatchMsgs.add(new ChatMessage("M-102", "PARTNER", "Alex Walker", "Hi, the entry gate code for Order #BN-4920 is showing invalid.", "04:36 PM"));
            dispatchMsgs.add(new ChatMessage("M-103", "SUPPORT", "Dispatch Support", "Checking with customer... The updated code is 0842. Marking on your live map now.", "04:37 PM"));
            channelMessages.put("CH-DISPATCH", dispatchMsgs);

            // Customer Messages
            List<ChatMessage> customerMsgs = new ArrayList<>();
            customerMsgs.add(new ChatMessage("M-201", "CUSTOMER", "Sarah J.", "Hi Alex! Are you on the way with the grocery order?", "04:20 PM"));
            customerMsgs.add(new ChatMessage("M-202", "PARTNER", "Alex Walker", "Yes Sarah, picked up from Whole Foods. ETA is 14 minutes.", "04:22 PM"));
            customerMsgs.add(new ChatMessage("M-203", "CUSTOMER", "Sarah J.", "Please leave it at door 4B, thank you!", "04:25 PM"));
            channelMessages.put("CH-CUSTOMER", customerMsgs);

            // Safety / Operations Messages
            List<ChatMessage> opsMsgs = new ArrayList<>();
            opsMsgs.add(new ChatMessage("M-301", "SUPPORT", "Safety Operations", "Welcome to the 24x7 Emergency Hub. Tap here if you face vehicle breakdown or transit delays.", "Yesterday"));
            channelMessages.put("CH-OPS", opsMsgs);
        }

        public List<ChatMessage> getActiveMessages() {
            return channelMessages.computeIfAbsent(activeChannelId, k -> new ArrayList<>());
        }

        public ChatChannel getActiveChannel() {
            for (ChatChannel ch : channels) {
                if (ch.id.equalsIgnoreCase(activeChannelId)) {
                    return ch;
                }
            }
            return channels.isEmpty() ? null : channels.get(0);
        }
    }

    public static class ChatChannel {
        public String id;
        public String title;
        public String subtitle;
        public String lastMessage;
        public String timestamp;
        public int unreadCount;
        public boolean isSelected;

        public ChatChannel(String id, String title, String subtitle, String lastMessage, String timestamp, int unreadCount, boolean isSelected) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.lastMessage = lastMessage;
            this.timestamp = timestamp;
            this.unreadCount = unreadCount;
            this.isSelected = isSelected;
        }
    }

    public static class ChatMessage {
        public String id;
        public String senderType;
        public String senderName;
        public String messageText;
        public String timestamp;

        public ChatMessage(String id, String senderType, String senderName, String messageText, String timestamp) {
            this.id = id;
            this.senderType = senderType;
            this.senderName = senderName;
            this.messageText = messageText;
            this.timestamp = timestamp;
        }
    }

    public static Scene partnerChatSupportScene() {
        return partnerChatSupportScene("DASHBOARD", new ChatWorkspaceData());
    }

    public static Scene partnerChatSupportScene(String returnScreen) {
        return partnerChatSupportScene(returnScreen, new ChatWorkspaceData());
    }

    public static Scene partnerChatSupportScene(String returnScreen, ChatWorkspaceData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        root.setTop(createTopHeader(returnScreen, data));

        HBox chatBody = new HBox();
        chatBody.setFillHeight(true);

        VBox channelCol = createChannelSidebar(returnScreen, data);
        VBox activeChatCol = createConversationView(returnScreen, data);
        HBox.setHgrow(activeChatCol, Priority.ALWAYS);

        chatBody.getChildren().addAll(channelCol, activeChatCol);
        root.setCenter(chatBody);

        Scene scene = new Scene(root, 1550, 850);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static BorderPane createTopHeader(String returnScreen, ChatWorkspaceData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle("-fx-background-color: #EBCCB7;" + "-fx-border-color: " + BORDER_COLOR + ";" + "-fx-border-width: 0 0 1 0;" + "-fx-padding: 0 35 0 25;");

        String backLabel = "←   Back to " + formatScreenName(returnScreen);
        Button btnBack = new Button(backLabel);
        btnBack.setStyle("-fx-background-color: #f8f8fb;" + "-fx-border-color: #e5e7eb;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;" + "-fx-font-size: 12px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #374151;" + "-fx-cursor: hand;" + "-fx-padding: 6 14 6 14;");
        btnBack.setOnAction(e -> navigateBack(returnScreen));

        Text title = new Text("Delivery Partner AI Assistant & Live Dispatch");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        leftGroup.setStyle("-fx-background-color: #EBCCB7;");
        topBar.setLeft(leftGroup);

        HBox activeAgentPill = new HBox(8);
        activeAgentPill.setAlignment(Pos.CENTER_RIGHT);
        Circle green = new Circle(4, Color.web("#22c55e"));
        ChatChannel activeCh = data.getActiveChannel();
        Label l = new Label(activeCh != null ? activeCh.subtitle : "AI Assistant Active");
        l.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #15803d;");

        Label aiBadge = new Label("AI ENABLED");
        aiBadge.setStyle("-fx-background-color: #FF6900; -fx-text-fill: white; -fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 2 6 2 6; -fx-background-radius: 6;");

        activeAgentPill.getChildren().addAll(green, l, aiBadge);
        topBar.setRight(activeAgentPill);

        return topBar;
    }

    private static VBox createChannelSidebar(String returnScreen, ChatWorkspaceData data) {
        VBox col = new VBox(10);
        col.setPrefWidth(320);
        col.setMinWidth(320);
        col.setMaxWidth(320);
        col.setPadding(new Insets(16));
        col.setStyle("-fx-background-color: white; -fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 0 1 0 0;");

        Label heading = new Label("SUPPORT CHANNELS");
        heading.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #9ca3af; -fx-padding: 0 0 6 4;");
        col.getChildren().add(heading);

        VBox channelList = new VBox(8);
        for (ChatChannel channel : data.channels) {
            channelList.getChildren().add(createChannelItem(returnScreen, data, channel));
        }

        ScrollPane scroll = new ScrollPane(channelList);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: white; -fx-border-color: transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        col.getChildren().add(scroll);
        return col;
    }

    private static VBox createChannelItem(String returnScreen, ChatWorkspaceData data, ChatChannel channel) {
        VBox card = new VBox(4);
        card.setPadding(new Insets(12));
        card.setPickOnBounds(true);

        boolean isSelected = channel.id.equalsIgnoreCase(data.activeChannelId);

        card.setStyle("-fx-background-color: " + (isSelected ? "#fff7ed;" : "#fafafc;") + "-fx-background-radius: 8;" + "-fx-border-radius: 8;" + "-fx-border-color: " + (isSelected ? ORANGE_PRIMARY : "#f0edf2") + ";" + "-fx-border-width: 1;" + "-fx-cursor: hand;");

        BorderPane row1 = new BorderPane();
        Label name = new Label(channel.title);
        name.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        name.setMouseTransparent(true);

        Label time = new Label(channel.timestamp);
        time.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");
        time.setMouseTransparent(true);

        row1.setLeft(name);
        row1.setRight(time);

        BorderPane row2 = new BorderPane();
        Label sub = new Label(channel.subtitle);
        sub.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        sub.setMouseTransparent(true);
        row2.setLeft(sub);

        if (channel.unreadCount > 0 && !isSelected) {
            Label unreadBadge = new Label(String.valueOf(channel.unreadCount));
            unreadBadge.setStyle("-fx-background-color: " + ORANGE_PRIMARY + "; -fx-text-fill: white; -fx-font-size: 9px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-padding: 1 6 1 6;");
            unreadBadge.setMouseTransparent(true);
            row2.setRight(unreadBadge);
        }

        Label lastMsg = new Label(channel.lastMessage);
        lastMsg.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        lastMsg.setMouseTransparent(true);

        card.getChildren().addAll(row1, row2, lastMsg);

        card.setOnMouseClicked(e -> {
            for (ChatChannel ch : data.channels) {
                ch.isSelected = false;
            }
            channel.isSelected = true;
            channel.unreadCount = 0;
            data.activeChannelId = channel.id;
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerChatSupportScene(returnScreen, data));
            }
        });

        return card;
    }

    private static VBox createConversationView(String returnScreen, ChatWorkspaceData data) {
        VBox view = new VBox();
        view.setStyle("-fx-background-color: " + BG_COLOR + ";");

        VBox msgList = new VBox(14);
        msgList.setPadding(new Insets(20, 26, 20, 26));

        List<ChatMessage> currentMsgs = data.getActiveMessages();
        for (ChatMessage msg : currentMsgs) {
            msgList.getChildren().add(createMessageBubble(msg));
        }

        ScrollPane scrollThread = new ScrollPane(msgList);
        scrollThread.setFitToWidth(true);
        scrollThread.setPannable(true);
        scrollThread.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");
        scrollThread.setVvalue(1.0);
        VBox.setVgrow(scrollThread, Priority.ALWAYS);

        // Quick Suggestion Chips Box
        HBox quickChips = new HBox(8);
        quickChips.setAlignment(Pos.CENTER_LEFT);
        quickChips.setPadding(new Insets(10, 26, 6, 26));
        quickChips.setStyle("-fx-background-color: white; -fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 1 0 0 0;");

        Label quickLabel = new Label("💡 Quick Prompts:");
        quickLabel.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #705C52;");
        quickChips.getChildren().add(quickLabel);

        String[] prompts = {
            "🚪 Gate Code Needed",
            "📞 Customer Unreachable",
            "⏱ Traffic Delay (+10m)",
            "💰 Today's Earnings",
            "⚡ Vehicle Breakdown"
        };

        BorderPane inputBar = new BorderPane();
        inputBar.setPadding(new Insets(8, 26, 14, 26));
        inputBar.setStyle("-fx-background-color: white;");

        TextField txtInput = new TextField();
        txtInput.setPromptText("Type a message or ask the delivery AI...");
        txtInput.setPrefHeight(44);
        txtInput.setStyle("-fx-background-color: #faf8fc; -fx-border-color: #e1dce5; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 14 0 14; -fx-font-size: 13px;");
        BorderPane.setMargin(txtInput, new Insets(0, 10, 0, 0));
        inputBar.setCenter(txtInput);

        Button btnSend = new Button("Send   ➤");
        btnSend.setPrefHeight(44);
        btnSend.setStyle("-fx-background-color: " + ORANGE_GRADIENT + ";" + "-fx-text-fill: white;" + "-fx-font-size: 12px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 8;" + "-fx-padding: 0 18 0 18;" + "-fx-cursor: hand;");

        // Action to send message and trigger dynamic response
        java.util.function.Consumer<String> sendMessageConsumer = (textToSend) -> {
            if (textToSend == null || textToSend.trim().isEmpty()) {
                return;
            }

            String trimmedText = textToSend.trim();
            txtInput.clear();

            String timeNow = getCurrentFormattedTime();

            // 1. Add User message
            ChatMessage userMsg = new ChatMessage("M-" + System.currentTimeMillis(), "PARTNER", data.partnerName, trimmedText, timeNow);
            currentMsgs.add(userMsg);
            msgList.getChildren().add(createMessageBubble(userMsg));

            ChatChannel activeCh = data.getActiveChannel();
            if (activeCh != null) {
                activeCh.lastMessage = trimmedText;
                activeCh.timestamp = "Just now";
            }

            // Scroll down
            scrollThread.layout();
            scrollThread.setVvalue(1.0);

            // 2. Add Typing Indicator Bubble
            HBox typingBubble = createTypingIndicatorBubble(data.activeChannelId);
            msgList.getChildren().add(typingBubble);
            scrollThread.layout();
            scrollThread.setVvalue(1.0);

            // 3. Asynchronously fetch dynamic response from DeliveryChatbotController
            new Thread(() -> {
                try {
                    Thread.sleep(600); // realistic thinking latency
                } catch (InterruptedException ignored) {}

                String botReply = DeliveryChatbotController.getBotReply(data.activeChannelId, trimmedText, data.partnerName);

                Platform.runLater(() -> {
                    msgList.getChildren().remove(typingBubble);

                    String senderName;
                    if ("CH-CUSTOMER".equalsIgnoreCase(data.activeChannelId)) {
                        senderName = "Sarah J. (Customer)";
                    } else if ("CH-OPS".equalsIgnoreCase(data.activeChannelId)) {
                        senderName = "Safety Operations";
                    } else if ("CH-DISPATCH".equalsIgnoreCase(data.activeChannelId)) {
                        senderName = "Dispatch Support";
                    } else {
                        senderName = "Delivery AI Bot";
                    }

                    ChatMessage botMsg = new ChatMessage("M-" + System.currentTimeMillis(), "SUPPORT", senderName, botReply, getCurrentFormattedTime());
                    currentMsgs.add(botMsg);
                    msgList.getChildren().add(createMessageBubble(botMsg));

                    if (activeCh != null) {
                        activeCh.lastMessage = botReply;
                        activeCh.timestamp = "Just now";
                    }

                    scrollThread.layout();
                    scrollThread.setVvalue(1.0);
                });
            }).start();
        };

        // Wire chips
        for (String prompt : prompts) {
            Button chip = new Button(prompt);
            chip.setStyle("-fx-background-color: #FFF2E8; -fx-text-fill: #A94408; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-border-color: #FFD2B8; -fx-border-radius: 14; -fx-padding: 4 10 4 10; -fx-cursor: hand;");
            chip.setOnMouseEntered(e -> chip.setStyle("-fx-background-color: #FF6900; -fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-border-color: #FF6900; -fx-border-radius: 14; -fx-padding: 4 10 4 10; -fx-cursor: hand;"));
            chip.setOnMouseExited(e -> chip.setStyle("-fx-background-color: #FFF2E8; -fx-text-fill: #A94408; -fx-font-size: 11px; -fx-font-weight: bold; -fx-background-radius: 14; -fx-border-color: #FFD2B8; -fx-border-radius: 14; -fx-padding: 4 10 4 10; -fx-cursor: hand;"));
            chip.setOnAction(e -> sendMessageConsumer.accept(prompt));
            quickChips.getChildren().add(chip);
        }

        btnSend.setOnAction(e -> sendMessageConsumer.accept(txtInput.getText()));
        txtInput.setOnAction(e -> sendMessageConsumer.accept(txtInput.getText()));

        inputBar.setRight(btnSend);

        VBox bottomArea = new VBox(quickChips, inputBar);
        view.getChildren().addAll(scrollThread, bottomArea);
        return view;
    }

    private static HBox createTypingIndicatorBubble(String channelId) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        VBox bubble = new VBox(2);
        bubble.setMaxWidth(300);
        bubble.setPadding(new Insets(8, 14, 8, 14));
        bubble.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-border-radius: 12 12 12 2; -fx-background-radius: 12 12 12 2;");

        String indicatorText = "CH-CUSTOMER".equalsIgnoreCase(channelId) ? "Sarah J. is typing..."
                            : "CH-OPS".equalsIgnoreCase(channelId) ? "Safety Operations is reviewing..."
                            : "CH-DISPATCH".equalsIgnoreCase(channelId) ? "Agent Riya is typing..."
                            : "🤖 Delivery AI is thinking...";

        Label typingLabel = new Label(indicatorText);
        typingLabel.setStyle("-fx-font-size: 11px; -fx-font-style: italic; -fx-text-fill: #FF6900;");

        bubble.getChildren().add(typingLabel);
        row.getChildren().add(bubble);
        return row;
    }

    private static HBox createMessageBubble(ChatMessage msg) {
        boolean isMe = "PARTNER".equalsIgnoreCase(msg.senderType);
        HBox row = new HBox();
        row.setAlignment(isMe ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        VBox bubble = new VBox(4);
        bubble.setMaxWidth(520);
        bubble.setPadding(new Insets(10, 14, 10, 14));

        if (isMe) {
            bubble.setStyle("-fx-background-color: " + ORANGE_PRIMARY + "; -fx-background-radius: 12 12 2 12;");
        } else {
            bubble.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-border-radius: 12 12 12 2; -fx-background-radius: 12 12 12 2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 4, 0, 0, 1);");
        }

        Label sender = new Label(msg.senderName);
        sender.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: " + (isMe ? "#fed7aa;" : "#9ca3af;"));

        Label body = new Label(msg.messageText);
        body.setWrapText(true);
        body.setStyle("-fx-font-size: 13px; -fx-text-fill: " + (isMe ? "white;" : "#111827;"));

        Label time = new Label(msg.timestamp);
        time.setStyle("-fx-font-size: 8px; -fx-text-fill: " + (isMe ? "#ffedd5;" : "#9ca3af;"));
        BorderPane timePane = new BorderPane();
        timePane.setRight(time);

        bubble.getChildren().addAll(sender, body, timePane);
        row.getChildren().add(bubble);
        return row;
    }

    private static String getCurrentFormattedTime() {
        try {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (Exception e) {
            return "Just now";
        }
    }

    private static String formatScreenName(String key) {
        if ("DELIVERIES".equalsIgnoreCase(key)) return "My Deliveries";
        if ("NAVIGATION".equalsIgnoreCase(key)) return "Navigation";
        if ("EARNINGS".equalsIgnoreCase(key)) return "Earnings";
        if ("AVAILABILITY".equalsIgnoreCase(key)) return "Availability";
        if ("SETTINGS".equalsIgnoreCase(key)) return "Settings";
        if ("SUPPORT".equalsIgnoreCase(key)) return "Support Help Center";
        return "Dashboard";
    }

    private static void navigateBack(String returnScreen) {
        if (Homepage.HomepageStage == null) return;
        if ("DELIVERIES".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
        else if ("NAVIGATION".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
        else if ("EARNINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
        else if ("AVAILABILITY".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
        else if ("SETTINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
        else if ("SUPPORT".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(DeliverySupport.supportScene());
        else Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
    }
}
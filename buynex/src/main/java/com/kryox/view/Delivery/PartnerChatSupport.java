package com.kryox.view.Delivery;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kryox.view.Customer.Homepage;

public class PartnerChatSupport {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    public static class ChatWorkspaceData {
        public String partnerName = "Alex Walker";
        public String activeChannelId = "CH-DISPATCH";
        public List<ChatChannel> channels = new ArrayList<>();
        public Map<String, List<ChatMessage>> channelMessages = new HashMap<>();

        public ChatWorkspaceData() {

            channels.add(new ChatChannel("CH-DISPATCH", "🎧 Dispatch Support Desk", "Agent Riya • Online", "Order address confirmed for #BN-4920", "Just now", 1, true));
            channels.add(new ChatChannel("CH-CUSTOMER", "👤 Sarah J. (Customer)", "Order #BN-4920", "Please leave it at door 4B, thank you!", "5m ago", 2, false));
            channels.add(new ChatChannel("CH-OPS", "⚡ Safety & Breakdown Hub", "24x7 Operations", "Roadside assistance protocol active", "Yesterday", 0, false));

            List<ChatMessage> dispatchMsgs = new ArrayList<>();
            dispatchMsgs.add(new ChatMessage("M-101", "SUPPORT", "Dispatch Support", "Hello Alex! How can we assist you on your active run?", "04:35 PM"));
            dispatchMsgs.add(new ChatMessage("M-102", "PARTNER", "Alex Walker", "Hi, the entry gate code for Order #BN-4920 is showing invalid.", "04:36 PM"));
            dispatchMsgs.add(new ChatMessage("M-103", "SUPPORT", "Dispatch Support", "Checking with customer... The updated code is 0842. Marking on your live map now.", "04:37 PM"));
            channelMessages.put("CH-DISPATCH", dispatchMsgs);

            List<ChatMessage> customerMsgs = new ArrayList<>();
            customerMsgs.add(new ChatMessage("M-201", "CUSTOMER", "Sarah J.", "Hi Alex! Are you on the way with the grocery order?", "04:20 PM"));
            customerMsgs.add(new ChatMessage("M-202", "PARTNER", "Alex Walker", "Yes Sarah, picked up from Whole Foods. ETA is 14 minutes.", "04:22 PM"));
            customerMsgs.add(new ChatMessage("M-203", "CUSTOMER", "Sarah J.", "Please leave it at door 4B, thank you!", "04:25 PM"));
            channelMessages.put("CH-CUSTOMER", customerMsgs);

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

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static BorderPane createTopHeader(String returnScreen, ChatWorkspaceData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle("-fx-background-color: white;" + "-fx-border-color: " + BORDER_COLOR + ";" + "-fx-border-width: 0 0 1 0;" + "-fx-padding: 0 35 0 25;");

        String backLabel = "←   Back to " + formatScreenName(returnScreen);
        Button btnBack = new Button(backLabel);
        btnBack.setStyle("-fx-background-color: #f8f8fb;" + "-fx-border-color: #e5e7eb;" + "-fx-border-radius: 8;" + "-fx-background-radius: 8;" + "-fx-font-size: 12px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #374151;" + "-fx-cursor: hand;" + "-fx-padding: 6 14 6 14;");
        btnBack.setOnAction(e -> navigateBack(returnScreen));

        Text title = new Text("Live Partner Support & Dispatcher Chat");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        HBox activeAgentPill = new HBox(6);
        activeAgentPill.setAlignment(Pos.CENTER_RIGHT);
        Circle green = new Circle(4, Color.web("#22c55e"));
        ChatChannel activeCh = data.getActiveChannel();
        Label l = new Label(activeCh != null ? activeCh.subtitle : "Support Agent Online");
        l.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #15803d;");
        activeAgentPill.getChildren().addAll(green, l);
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

        Label heading = new Label("CONVERSATIONS");
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

        BorderPane inputBar = new BorderPane();
        inputBar.setPadding(new Insets(14, 26, 14, 26));
        inputBar.setStyle("-fx-background-color: white; -fx-border-color: " + BORDER_COLOR + "; -fx-border-width: 1 0 0 0;");

        TextField txtInput = new TextField();
        txtInput.setPromptText("Type your message here...");
        txtInput.setPrefHeight(42);
        txtInput.setStyle("-fx-background-color: #faf8fc; -fx-border-color: #e1dce5; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 0 14 0 14; -fx-font-size: 12px;");
        BorderPane.setMargin(txtInput, new Insets(0, 10, 0, 0));
        inputBar.setCenter(txtInput);

        Button btnSend = new Button("Send   ➤");
        btnSend.setPrefHeight(42);
        btnSend.setStyle("-fx-background-color: " + ORANGE_GRADIENT + ";" + "-fx-text-fill: white;" + "-fx-font-size: 12px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 8;" + "-fx-padding: 0 18 0 18;" + "-fx-cursor: hand;");

        Runnable sendTask = () -> {
            String text = txtInput.getText();
            if (text != null && !text.trim().isEmpty()) {
                ChatMessage newMsg = new ChatMessage("M-" + (currentMsgs.size() + 1), "PARTNER", data.partnerName, text.trim(), "Just now");
                currentMsgs.add(newMsg);

                ChatChannel activeCh = data.getActiveChannel();
                if (activeCh != null) {
                    activeCh.lastMessage = text.trim();
                    activeCh.timestamp = "Just now";
                }

                txtInput.clear();
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(partnerChatSupportScene(returnScreen, data));
                }
            }
        };

        btnSend.setOnAction(e -> sendTask.run());
        txtInput.setOnAction(e -> sendTask.run());

        inputBar.setRight(btnSend);
        view.getChildren().addAll(scrollThread, inputBar);
        return view;
    }

    private static HBox createMessageBubble(ChatMessage msg) {
        boolean isMe = "PARTNER".equalsIgnoreCase(msg.senderType);
        HBox row = new HBox();
        row.setAlignment(isMe ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        VBox bubble = new VBox(3);
        bubble.setMaxWidth(460);
        bubble.setPadding(new Insets(10, 14, 10, 14));

        if (isMe) {
            bubble.setStyle("-fx-background-color: " + ORANGE_PRIMARY + "; -fx-background-radius: 12 12 2 12;");
        } else {
            bubble.setStyle("-fx-background-color: white; -fx-border-color: #e5e7eb; -fx-border-radius: 12 12 12 2; -fx-background-radius: 12 12 12 2;");
        }

        Label sender = new Label(msg.senderName);
        sender.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: " + (isMe ? "#fed7aa;" : "#9ca3af;"));

        Label body = new Label(msg.messageText);
        body.setWrapText(true);
        body.setStyle("-fx-font-size: 12px; -fx-text-fill: " + (isMe ? "white;" : "#111827;"));

        Label time = new Label(msg.timestamp);
        time.setStyle("-fx-font-size: 8px; -fx-text-fill: " + (isMe ? "#ffedd5;" : "#9ca3af;"));
        BorderPane timePane = new BorderPane();
        timePane.setRight(time);

        bubble.getChildren().addAll(sender, body, timePane);
        row.getChildren().add(bubble);
        return row;
    }

    private static String formatScreenName(String key) {
        if ("DELIVERIES".equalsIgnoreCase(key)) return "My Deliveries";
        if ("NAVIGATION".equalsIgnoreCase(key)) return "Navigation";
        if ("EARNINGS".equalsIgnoreCase(key)) return "Earnings";
        if ("AVAILABILITY".equalsIgnoreCase(key)) return "Availability";
        if ("SETTINGS".equalsIgnoreCase(key)) return "Settings";
        return "Dashboard";
    }

    private static void navigateBack(String returnScreen) {
        if (Homepage.HomepageStage == null) return;
        if ("DELIVERIES".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
        else if ("NAVIGATION".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
        else if ("EARNINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
        else if ("AVAILABILITY".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
        else if ("SETTINGS".equalsIgnoreCase(returnScreen)) Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
        else Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
    }
}

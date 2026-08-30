package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.awt.Desktop;
import java.net.URI;

public class DeliverySupport {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    public static class SupportData {
        public String helplinePhone = "+91 1800 209 8822";
        public String supportEmail = "partner.support@buynex.com";
        public String operatingHours = "24x7 Priority Desk";
        public String activePartner = "Alex Walker";
    }

    public static void show(Scene scene) {
        show(scene, "LOGIN", new SupportData());
    }

    public static void show(Scene scene, String returnSource) {
        show(scene, returnSource, new SupportData());
    }

    public static void show(Scene scene, String returnSource, SupportData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar with Dynamic Back Button
        root.setTop(createTopHeader(scene, returnSource));

        // 2. Center Scrollable Content
        VBox mainContent = createMainContent(data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: " + BG_COLOR + ";" +
                "-fx-border-color: transparent;"
        );

        root.setCenter(scrollPane);

        if (scene != null) {
            scene.setRoot(root);
        }
    }

    private static BorderPane createTopHeader(Scene scene, String returnSource) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 35 0 25;"
        );

        String backTitle;
        if ("REGISTRATION".equalsIgnoreCase(returnSource)) {
            backTitle = "←  Back to Registration";
        } else if ("SUCCESS".equalsIgnoreCase(returnSource)) {
            backTitle = "←  Back to Application Status";
        } else if ("SETTINGS".equalsIgnoreCase(returnSource)) {
            backTitle = "←  Back to Settings";
        } else {
            backTitle = "←  Back to Login";
        }

        Button btnBack = new Button(backTitle);
        btnBack.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #374151;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;"
        );

        Runnable backTask = new Runnable() {
            @Override
            public void run() {
                if ("REGISTRATION".equalsIgnoreCase(returnSource)) {
                    DeliveryRegistration2.show(scene);
                } else if ("SUCCESS".equalsIgnoreCase(returnSource)) {
                    RegistrationSuccess.show(scene);
                } else if ("SETTINGS".equalsIgnoreCase(returnSource)) {
                    PartnerSettings.show(scene);
                } else {
                    Deliverylogin.show(scene);
                }
            }
        };
        btnBack.setOnAction(e -> backTask.run());

        Text logo = new Text("BuyNeX Support");
        logo.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-fill: " + ORANGE_GRADIENT + ";" +
                "-fx-font-weight: bold;"
        );

        HBox leftGroup = new HBox(16, btnBack, logo);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        HBox liveDeskPill = new HBox(6);
        liveDeskPill.setAlignment(Pos.CENTER_RIGHT);
        Circle greenDot = new Circle(4, Color.web("#22c55e"));
        Label liveText = new Label("24x7 Live Desk Active");
        liveText.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #15803d;");
        liveDeskPill.getChildren().addAll(greenDot, liveText);

        topBar.setRight(liveDeskPill);
        return topBar;
    }

    private static VBox createMainContent(SupportData data) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(26, 35, 60, 35));
        content.setFillWidth(true);

        VBox titleBox = new VBox(3);
        Text title = new Text("Delivery Partner Help Center");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #111827;");
        Text subTitle = new Text("Find answers, resolve delivery issues, or connect instantly with our support team.");
        subTitle.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);

        HBox contactCards = new HBox(16);
        VBox phoneCard = createContactCard("📞 Emergency Helpline", data.helplinePhone, "Toll-Free (24x7 Active)", () -> launchPhone(data.helplinePhone));
        VBox emailCard = createContactCard("✉ Email Support", data.supportEmail, "Response within 1 hour", () -> launchEmail(data.supportEmail));
        VBox chatCard = createContactCard("💬 Live Partner Chat", "Instant Agent Connect", "Avg wait: < 2 mins", () -> {});

        contactCards.getChildren().addAll(phoneCard, emailCard, chatCard);
        HBox.setHgrow(phoneCard, Priority.ALWAYS);
        HBox.setHgrow(emailCard, Priority.ALWAYS);
        HBox.setHgrow(chatCard, Priority.ALWAYS);

        HBox bodySplit = new HBox(22);
        bodySplit.setFillHeight(true);

        VBox faqColumn = createFaqSection();
        HBox.setHgrow(faqColumn, Priority.ALWAYS);

        VBox ticketColumn = createTicketFormCard();
        ticketColumn.setPrefWidth(380);
        ticketColumn.setMinWidth(380);
        ticketColumn.setMaxWidth(380);
        HBox.setHgrow(ticketColumn, Priority.NEVER);

        bodySplit.getChildren().addAll(faqColumn, ticketColumn);

        content.getChildren().addAll(titleBox, contactCards, bodySplit);
        return content;
    }

    private static VBox createContactCard(String title, String mainInfo, String subInfo, Runnable action) {
        VBox card = createCard();
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;" +
                "-fx-cursor: hand;"
        );

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label m = new Label(mainInfo);
        m.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: " + ORANGE_PRIMARY + ";");

        Label s = new Label(subInfo);
        s.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");

        card.getChildren().addAll(t, m, s);
        card.setOnMouseClicked(e -> action.run());
        return card;
    }

    private static VBox createFaqSection() {
        VBox card = createCard();
        card.setPadding(new Insets(20));

        Label title = new Label("Frequently Asked Questions");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        VBox faqList = new VBox(8);
        faqList.getChildren().addAll(
                createFaqRow("What should I do if a customer is unreachable at dropoff?", "Attempt calling the customer twice through the in-app dialer. If there is no response after 5 minutes, mark the order as 'Customer Unavailable' to receive return routing instructions."),
                createFaqRow("How and when are weekly earnings paid out?", "Weekly earnings are calculated every Sunday midnight and directly transferred to your registered bank account via IMPS/NEFT by Tuesday morning."),
                createFaqRow("How do I report vehicle breakdown during active transit?", "Tap the emergency button in Navigation or call the 24x7 Helpline immediately. Our dispatch team will reassign the order and assist with roadside support."),
                createFaqRow("How can I update my vehicle registration or license documents?", "Navigate to Settings → Personal & Vehicle Details and click 'Edit' to submit updated photos for instant verification.")
        );

        card.getChildren().addAll(title, faqList);
        return card;
    }

    private static VBox createFaqRow(String question, String answer) {
        VBox item = new VBox(4);
        item.setPadding(new Insets(10, 12, 10, 12));
        item.setStyle("-fx-background-color: #f9fafb; -fx-background-radius: 8;");

        BorderPane qRow = new BorderPane();
        Label qLbl = new Label("Q: " + question);
        qLbl.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");
        Label chevron = new Label("⌄");
        chevron.setStyle("-fx-font-size: 12px; -fx-text-fill: #9ca3af;");
        qRow.setLeft(qLbl);
        qRow.setRight(chevron);

        Label aLbl = new Label(answer);
        aLbl.setWrapText(true);
        aLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563; -fx-padding: 4 0 0 0;");

        item.getChildren().addAll(qRow, aLbl);
        return item;
    }

    private static VBox createTicketFormCard() {
        VBox card = createCard();
        card.setPadding(new Insets(20));

        Label title = new Label("Raise a Support Ticket");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label desc = new Label("Submit an issue report directly to our operations hub.");
        desc.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        TextField orderIdField = new TextField();
        orderIdField.setPromptText("Order ID (e.g. BX-9942 or ORD-90210)");
        orderIdField.setPrefHeight(38);
        orderIdField.setStyle("-fx-font-size: 11px; -fx-background-color: #faf8fc; -fx-border-color: #e1dce5; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 0 10 0 10;");

        TextField subjectField = new TextField();
        subjectField.setPromptText("Issue Subject (e.g. Payment discrepancy)");
        subjectField.setPrefHeight(38);
        subjectField.setStyle("-fx-font-size: 11px; -fx-background-color: #faf8fc; -fx-border-color: #e1dce5; -fx-border-radius: 6; -fx-background-radius: 6; -fx-padding: 0 10 0 10;");

        TextArea messageArea = new TextArea();
        messageArea.setPromptText("Describe what happened in detail...");
        messageArea.setPrefRowCount(4);
        messageArea.setStyle("-fx-font-size: 11px; -fx-background-color: #faf8fc; -fx-border-color: #e1dce5; -fx-border-radius: 6; -fx-background-radius: 6;");

        Button btnSubmit = new Button("Submit Ticket");
        btnSubmit.setPrefHeight(40);
        btnSubmit.setMaxWidth(Double.MAX_VALUE);
        btnSubmit.setStyle(
                "-fx-background-color: " + ORANGE_GRADIENT + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        btnSubmit.setOnAction(e -> {
            btnSubmit.setText("✓ Ticket #TK-8492 Raised!");
            btnSubmit.setStyle("-fx-background-color: #16a34a; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold; -fx-background-radius: 8;");
            orderIdField.clear();
            subjectField.clear();
            messageArea.clear();
        });

        card.getChildren().addAll(title, desc, orderIdField, subjectField, messageArea, btnSubmit);
        return card;
    }

    private static void launchPhone(String phone) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("tel:" + phone.replaceAll("[^0-9+]", "")));
            }
        } catch (Exception ignored) {}
    }

    private static void launchEmail(String email) {
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("mailto:" + email));
            }
        } catch (Exception ignored) {}
    }

    private static VBox createCard() {
        VBox card = new VBox(12);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );
        return card;
    }
}
package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Helppage {
    public String userId;

    public Helppage(String userId) {
        this.userId = userId;
    }

    private Scene HelpScene;
    private TextField searchField;

    public Scene getHelpScene() {
        return getHelpScene(null);
    }

    public Scene getHelpScene(Runnable callbacktosetion) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #EEE5DF;");

        // =====================================================
        // HEADER (Clean, properly spaced, responsive)
        // =====================================================
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 40, 0, 40));
        header.setPrefHeight(64);
        header.setStyle(
                "-fx-background-color: #EBCDB9;" +
                "-fx-border-color: #ded1c7;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Logo
        Label logo = new Label("EliteMarket");
        logo.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-cursor: hand;"
        );
        logo.setOnMouseClicked(e -> CustomerNavigation.navigateToDashboard(userId));

        // Nav Buttons
        Button shopBtn = createNavButton("Shop");
        Button ordersBtn = createNavButton("Orders");
        Button helpBtn = createNavButton("Help");
        Button accountBtn = createNavButton("Account");

        // Active Help button style
        helpBtn.setStyle(
                "-fx-background-color: rgba(169, 59, 11, 0.12);" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 6 12;" +
                "-fx-cursor: hand;"
        );

        shopBtn.setOnAction(e -> CustomerNavigation.navigateToDashboard(userId));
        ordersBtn.setOnAction(e -> CustomerNavigation.navigateToOrders(userId));
        helpBtn.setOnAction(e -> showMessage("Help Center", "You are already on the Help Center."));
        accountBtn.setOnAction(e -> CustomerNavigation.navigateToSettings(userId));

        HBox leftNav = new HBox(22, logo, shopBtn, ordersBtn, helpBtn, accountBtn);
        leftNav.setAlignment(Pos.CENTER_LEFT);

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        // Right Nav Icons
        Label cart = createHeaderIcon("🛒", "Cart");
        Label notification = createHeaderIcon("🔔", "Notifications");
        Label profile = createHeaderIcon("👤", "Profile");

        cart.setOnMouseClicked(e -> CustomerNavigation.navigateToCart(userId));
        notification.setOnMouseClicked(e -> CustomerNavigation.navigateToNotifications(userId));
        profile.setOnMouseClicked(e -> CustomerNavigation.navigateToSettings(userId));

        HBox rightNav = new HBox(12, cart, notification, profile);
        rightNav.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(leftNav, headerSpacer, rightNav);
        root.setTop(header);

        // =====================================================
        // CENTER CONTENT CONTAINER (Constrained width for elegance)
        // =====================================================
        VBox centerContainer = new VBox(28);
        centerContainer.setMaxWidth(1040);
        centerContainer.setPrefWidth(1040);
        centerContainer.setAlignment(Pos.TOP_CENTER);

        // ---------------- TITLE & SEARCH ----------------
        Label title = new Label("How can we help you today?");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1a1715;"
        );

        Label subTitle = new Label("Search our knowledge base or browse frequently asked questions below.");
        subTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #6d645e;");

        searchField = new TextField();
        searchField.setPromptText("🔍  Search for articles, questions, or topics...");
        searchField.setPrefWidth(540);
        searchField.setMaxWidth(540);
        searchField.setPrefHeight(44);
        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 22;" +
                "-fx-border-radius: 22;" +
                "-fx-border-color: #d9cec4;" +
                "-fx-border-width: 1.2;" +
                "-fx-padding: 0 18;" +
                "-fx-font-size: 12px;"
        );

        DropShadow searchShadow = new DropShadow();
        searchShadow.setRadius(10);
        searchShadow.setOffsetY(3);
        searchShadow.setColor(Color.rgb(0, 0, 0, 0.08));
        searchField.setEffect(searchShadow);

        HBox searchWrap = new HBox(searchField);
        searchWrap.setAlignment(Pos.CENTER);

        VBox titleBox = new VBox(10, title, subTitle, searchWrap);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10, 0, 10, 0));

        // ---------------- CATEGORY SECTION ----------------
        Label categoryTitle = new Label("Browse by Category");
        categoryTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #221d19;");

        VBox ordersCard = createCategoryCard(
                "📦",
                "My Orders",
                "Tracking, delivery status, returns, and order cancellations.",
                "My Orders"
        );

        VBox paymentCard = createCategoryCard(
                "💳",
                "Payments & Billing",
                "Payment options, UPI, invoices, refunds, and pricing policies.",
                "Payments & Billing"
        );

        VBox accountCard = createCategoryCard(
                "👤",
                "Account Settings",
                "Password change, profile updates, and address preferences.",
                "Account Settings"
        );

        VBox aiCard = createCategoryCard(
                "✨",
                "AI Assistant Help",
                "Smart product advice, deals finder, and recommendation tools.",
                "AI Assistant Help"
        );

        VBox securityCard = createCategoryCard(
                "🛡️",
                "Safety & Privacy",
                "Data security, reporting store issues, and account safety.",
                "Safety & Privacy"
        );

        HBox categoryRow1 = new HBox(16, ordersCard, paymentCard, accountCard);
        categoryRow1.setAlignment(Pos.CENTER);

        HBox categoryRow2 = new HBox(16, aiCard, securityCard);
        categoryRow2.setAlignment(Pos.CENTER);

        VBox categoryCardsBox = new VBox(14, categoryRow1, categoryRow2);
        categoryCardsBox.setAlignment(Pos.CENTER);

        VBox categorySection = new VBox(14, categoryTitle, categoryCardsBox);
        categorySection.setAlignment(Pos.TOP_LEFT);

        // ---------------- FREQUENTLY ASKED QUESTIONS SECTION ----------------
        Label faqSectionTitle = new Label("Frequently Asked Questions");
        faqSectionTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #221d19;");

        Label faqSectionSub = new Label("Quick answers to commonly asked questions. Tap any question to reveal the details.");
        faqSectionSub.setStyle("-fx-font-size: 12px; -fx-text-fill: #736962;");

        VBox faq1 = createFAQ(
                "How do I track my order?",
                "You can track your order in real time from the 'My Orders' section using your Order ID. Once shipped, live status and delivery updates appear automatically."
        );

        VBox faq2 = createFAQ(
                "What is your return and refund policy?",
                "Eligible items can be returned within 7 days of delivery. Once the merchant verifies the return, refunds are credited to your original payment method within 3 to 5 business days."
        );

        VBox faq3 = createFAQ(
                "How can I contact a seller directly?",
                "Open your order details or the shop's page and choose 'Contact Seller' or 'Visit Shop'. You can message or call local merchants directly regarding your items."
        );

        VBox faq4 = createFAQ(
                "How does the AI Shopping Assistant work?",
                "Our AI Assistant analyzes popular local deals, verifies seller ratings, and suggests the highest value options tailored to your shopping preferences."
        );

        List<VBox> allFaqs = List.of(faq1, faq2, faq3, faq4);

        VBox faqList = new VBox(10, faq1, faq2, faq3, faq4);

        VBox faqContainer = new VBox(12, faqSectionTitle, faqSectionSub, faqList);
        faqContainer.setPadding(new Insets(20));
        faqContainer.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #e5ded7;" +
                "-fx-border-radius: 12;"
        );

        DropShadow cardShadow = new DropShadow();
        cardShadow.setRadius(8);
        cardShadow.setOffsetY(3);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.05));
        faqContainer.setEffect(cardShadow);

        // ---------------- STILL NEED HELP BOX ----------------
        VBox supportBox = new VBox(12);
        supportBox.setAlignment(Pos.CENTER);
        supportBox.setPadding(new Insets(24, 20, 24, 20));
        supportBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #e5ded7;" +
                "-fx-border-radius: 12;"
        );
        supportBox.setEffect(cardShadow);

        Label supportTitle = new Label("Still need help?");
        supportTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1f1a17;");

        Label supportText = new Label("Our dedicated customer support team is available 24/7 to assist you with any questions or concerns.");
        supportText.setStyle("-fx-font-size: 12px; -fx-text-fill: #6e655f;");

        Button liveChat = new Button("💬  Live Chat");
        liveChat.setStyle(
                "-fx-background-color: #a93b0b;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 9 18;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );

        Button email = new Button("✉  Email Us");
        email.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-border-color: #b0a69d;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 9 18;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );

        Button call = new Button("☏  Call Support");
        call.setStyle(
                "-fx-background-color: #fff0e7;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #f7cfb8;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 9 18;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );

        Button backBtn = new Button("←  Back");
        backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-border-color: #a93b0b;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 9 20;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );

        liveChat.setOnAction(e -> showMessage("Live Chat", "Connecting you with our support team..."));

        email.setOnAction(e -> {
            try {
                String emailAddress = "sayalirepale2006@gmail.com";
                String subject = "EliteMarket Support";
                String body = "Hello EliteMarket Support,\n\nI need help regarding my order.";

                String encodedSubject = URLEncoder.encode(subject, StandardCharsets.UTF_8);
                String encodedBody = URLEncoder.encode(body, StandardCharsets.UTF_8);

                String url = "https://mail.google.com/mail/?view=cm&fs=1&to=" + emailAddress + "&su=" + encodedSubject + "&body=" + encodedBody;
                Desktop.getDesktop().browse(URI.create(url));
            } catch (Exception ex) {
                ex.printStackTrace();
                showMessage("Error", "Unable to open Gmail.");
            }
        });

        call.setOnAction(e -> showMessage("Call Support", "Call us at +91 7709701201"));

        backBtn.setOnAction(event -> {
            if (callbacktosetion != null) {
                callbacktosetion.run();
            } else {
                Seting se = new Seting(userId);
                Homepage.HomepageStage.setScene(se.getSetingscene(this::backtoDashboard));
            }
        });

        HBox supportButtons = new HBox(12, liveChat, email, call, backBtn);
        supportButtons.setAlignment(Pos.CENTER);

        supportBox.getChildren().addAll(supportTitle, supportText, supportButtons);

        // ---------------- DYNAMIC SEARCH FILTERING ----------------
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String search = newValue == null ? "" : newValue.toLowerCase().trim();

            boolean showOrders = search.isEmpty() || "my orders tracking return cancellation".contains(search);
            boolean showPayment = search.isEmpty() || "payments billing invoices upi refund".contains(search);
            boolean showAccount = search.isEmpty() || "account settings password profile".contains(search);
            boolean showAi = search.isEmpty() || "ai assistant help smart recommendations".contains(search);
            boolean showSecurity = search.isEmpty() || "safety privacy data protection security".contains(search);

            ordersCard.setVisible(showOrders);
            ordersCard.setManaged(showOrders);

            paymentCard.setVisible(showPayment);
            paymentCard.setManaged(showPayment);

            accountCard.setVisible(showAccount);
            accountCard.setManaged(showAccount);

            aiCard.setVisible(showAi);
            aiCard.setManaged(showAi);

            securityCard.setVisible(showSecurity);
            securityCard.setManaged(showSecurity);

            // Also filter FAQ items
            for (VBox faq : allFaqs) {
                Label qLbl = (Label) ((HBox) faq.getChildren().get(0)).getChildren().get(0);
                Label aLbl = (Label) faq.getChildren().get(1);
                String qText = qLbl.getText().toLowerCase();
                String aText = aLbl.getText().toLowerCase();

                boolean match = search.isEmpty() || qText.contains(search) || aText.contains(search);
                faq.setVisible(match);
                faq.setManaged(match);
            }
        });

        // Assemble All in Center Container
        centerContainer.getChildren().addAll(
                titleBox,
                categorySection,
                faqContainer,
                supportBox
        );

        VBox outerWrapper = new VBox(centerContainer);
        outerWrapper.setAlignment(Pos.TOP_CENTER);
        outerWrapper.setPadding(new Insets(30, 40, 40, 40));
        outerWrapper.setStyle("-fx-background-color: #EEE5DF;");

        ScrollPane scrollPane = new ScrollPane(outerWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1530, 850);
        HelpScene = scene;

        return HelpScene;
    }

    private Button createNavButton(String text) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #443e39;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 10;" +
                "-fx-cursor: hand;"
        );
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: rgba(169, 59, 11, 0.08);" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 6 10;" +
                "-fx-cursor: hand;"
        ));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #443e39;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 10;" +
                "-fx-cursor: hand;"
        ));
        return button;
    }

    private Label createHeaderIcon(String icon, String tooltip) {
        Label label = new Label(icon);
        label.setPrefSize(34, 34);
        label.setAlignment(Pos.CENTER);
        label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-background-color: rgba(255, 255, 255, 0.45);" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        );
        label.setOnMouseEntered(e -> label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-background-color: rgba(255, 255, 255, 0.9);" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        ));
        label.setOnMouseExited(e -> label.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-background-color: rgba(255, 255, 255, 0.45);" +
                "-fx-background-radius: 50%;" +
                "-fx-cursor: hand;"
        ));
        return label;
    }

    private VBox createCategoryCard(
            String icon,
            String title,
            String description,
            String popupTitle) {

        Label iconLabel = new Label(icon);
        iconLabel.setMinWidth(36);
        iconLabel.setMinHeight(36);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setStyle(
                "-fx-background-color: #fff0e7;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-size: 17px;"
        );

        Label titleLabel = new Label(title);
        titleLabel.setStyle(
                "-fx-font-size: 13.5px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #221d19;"
        );

        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setStyle(
                "-fx-font-size: 10.5px;" +
                "-fx-text-fill: #6d655f;" +
                "-fx-line-spacing: 1px;"
        );

        VBox textBox = new VBox(3, titleLabel, descriptionLabel);
        textBox.setAlignment(Pos.CENTER_LEFT);

        HBox cardContent = new HBox(12, iconLabel, textBox);
        cardContent.setAlignment(Pos.CENTER_LEFT);

        VBox card = new VBox(cardContent);
        card.setPrefWidth(330);
        card.setMinWidth(330);
        card.setMaxWidth(330);
        card.setPrefHeight(80);
        card.setMinHeight(80);
        card.setPadding(new Insets(12, 14, 12, 14));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #e5ded7;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        );

        DropShadow shadow = new DropShadow();
        shadow.setRadius(7);
        shadow.setOffsetY(2);
        shadow.setColor(Color.rgb(0, 0, 0, 0.05));
        card.setEffect(shadow);

        card.setOnMouseClicked(e -> showMessage(popupTitle, "You selected " + popupTitle + "."));

        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: #fffaf7;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #a93b0b;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        ));

        card.setOnMouseExited(e -> card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #e5ded7;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        ));

        return card;
    }

    private VBox createFAQ(String question, String answer) {
        Label questionLabel = new Label(question);
        questionLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #221d19;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label arrow = new Label("▾");
        arrow.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #a93b0b;");

        HBox questionRow = new HBox(questionLabel, spacer, arrow);
        questionRow.setAlignment(Pos.CENTER_LEFT);
        questionRow.setPadding(new Insets(12, 16, 12, 16));
        questionRow.setCursor(Cursor.HAND);
        questionRow.setStyle(
                "-fx-background-color: #fcfbfa;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #ebe4dd;" +
                "-fx-border-radius: 8;"
        );

        Label answerLabel = new Label(answer);
        answerLabel.setWrapText(true);
        answerLabel.setStyle(
                "-fx-font-size: 11.5px;" +
                "-fx-text-fill: #5b534d;" +
                "-fx-padding: 10 16 12 16;" +
                "-fx-line-spacing: 2px;"
        );
        answerLabel.setVisible(false);
        answerLabel.setManaged(false);

        VBox faqCard = new VBox(questionRow, answerLabel);
        faqCard.setStyle("-fx-background-color: transparent;");

        questionRow.setOnMouseEntered(e -> questionRow.setStyle(
                "-fx-background-color: #fff4ec;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #f0cfbc;" +
                "-fx-border-radius: 8;"
        ));

        questionRow.setOnMouseExited(e -> {
            if (!answerLabel.isVisible()) {
                questionRow.setStyle(
                        "-fx-background-color: #fcfbfa;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #ebe4dd;" +
                        "-fx-border-radius: 8;"
                );
            }
        });

        questionRow.setOnMouseClicked(e -> {
            boolean visible = !answerLabel.isVisible();
            answerLabel.setVisible(visible);
            answerLabel.setManaged(visible);
            arrow.setText(visible ? "▴" : "▾");

            if (visible) {
                questionRow.setStyle(
                        "-fx-background-color: #fff0e6;" +
                        "-fx-background-radius: 8 8 0 0;" +
                        "-fx-border-color: #e8caa9;" +
                        "-fx-border-radius: 8 8 0 0;"
                );
                answerLabel.setStyle(
                        "-fx-background-color: #fffdfb;" +
                        "-fx-background-radius: 0 0 8 8;" +
                        "-fx-border-color: transparent #e8caa9 #e8caa9 #e8caa9;" +
                        "-fx-border-radius: 0 0 8 8;" +
                        "-fx-font-size: 11.5px;" +
                        "-fx-text-fill: #4d443e;" +
                        "-fx-padding: 12 16 14 16;" +
                        "-fx-line-spacing: 2px;"
                );
            } else {
                questionRow.setStyle(
                        "-fx-background-color: #fcfbfa;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-color: #ebe4dd;" +
                        "-fx-border-radius: 8;"
                );
            }
        });

        return faqCard;
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void backtoDashboard() {
        Homepage.HomepageStage.setScene(HelpScene);
    }
}
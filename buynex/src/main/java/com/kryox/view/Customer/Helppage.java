package com.kryox.view.Customer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Helppage  {
        public String userId;


        public Helppage(String userId) {
        this.userId = userId;
    }
    private Scene HelpScene;

    private TextField searchField;
    Scene getHelpScene(Runnable callbacktosetion){
         // =====================================================
        // MAIN BORDERPANE
        // =====================================================

        BorderPane root = new BorderPane();

        root.setStyle(
        "-fx-background-color: #eee5df;"
        );


        // =====================================================
        // HEADER
        // =====================================================

        HBox header = new HBox();

        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(0, 48, 0, 48));
        header.setPrefHeight(65);

        header.setStyle(
                "-fx-background-color: #EBCDB9;" +
                "-fx-border-color: #eeeeee;" +
                "-fx-border-width: 0 0 1 0;"
        );


        // ---------------- LOGO ----------------

        Label logo = new Label("EliteMarket");

        logo.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #a93b0b;"
        );


        // ---------------- NAV BUTTONS ----------------

        Button shopBtn = createNavButton("Shop");

        Button ordersBtn = createNavButton("Orders");

        Button helpBtn = createNavButton("Help");

        Button accountBtn = createNavButton("Account");


        // Active Help button

        helpBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-size: 11px;" +
                "-fx-border-color: transparent transparent #a93b0b transparent;" +
                "-fx-border-width: 0 0 2 0;"
        );


        // ---------------- RIGHT SIDE ----------------

        Label cart = new Label("🛒");

        Label notification = new Label("♧");

        Label profile = new Label("👤");


        cart.setStyle("-fx-font-size: 16px;");

        notification.setStyle("-fx-font-size: 16px;");

        profile.setStyle("-fx-font-size: 18px;");


        // ---------------- LEFT NAV ----------------

        HBox leftNav = new HBox(35);

        leftNav.setAlignment(Pos.CENTER_LEFT);

        leftNav.getChildren().addAll(
                logo,
                shopBtn,
                ordersBtn,
                helpBtn,
                accountBtn
        );


        // ---------------- RIGHT NAV ----------------

        HBox rightNav = new HBox(20);

        rightNav.setAlignment(Pos.CENTER_RIGHT);

        rightNav.getChildren().addAll(
                cart,
                notification,
                profile
        );


        // ---------------- HEADER CONTENT ----------------

        HBox headerContent = new HBox();

        headerContent.setAlignment(Pos.CENTER);

        headerContent.getChildren().addAll(
                leftNav,
                rightNav
        );


        // Keep right side towards right

        rightNav.setTranslateX(250);


        header.getChildren().add(headerContent);

        root.setTop(header);


        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox contentBox = new VBox(25);

        contentBox.setAlignment(Pos.TOP_CENTER);

        contentBox.setPadding(
                new Insets(40, 48, 35, 48)
        );


        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
                "How can we help you today?"
        );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );


        // =====================================================
        // SEARCH FIELD
        // =====================================================

        searchField = new TextField();

        searchField.setPromptText(
                "⌕  Search for articles, questions, or topics..."
        );

        searchField.setPrefWidth(330);

        searchField.setPrefHeight(42);

        searchField.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-radius: 7;" +
                "-fx-border-color: #eeeeee;" +
                "-fx-padding: 0 12;" +
                "-fx-font-size: 11px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);"
        );


        // =====================================================
        // TITLE BOX
        // =====================================================

        VBox titleBox = new VBox(12);

        titleBox.setAlignment(Pos.CENTER);

        titleBox.getChildren().addAll(
                title,
                searchField
        );


        contentBox.getChildren().add(titleBox);


        // =====================================================
        // CATEGORY SECTION
        // =====================================================

        Label categoryTitle = new Label(
                "Browse by Category"
        );

        categoryTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );


        // =====================================================
        // CATEGORY ROW 1
        // =====================================================

        HBox categoryRow1 = new HBox(12);

        categoryRow1.setAlignment(Pos.CENTER);


        VBox ordersCard = createCategoryCard(
                "▣",
                "My Orders",
                "Tracking, returns, cancellations,\n"
                        + "and delivery issues.",
                "My Orders"
        );


        VBox paymentCard = createCategoryCard(
                "▤",
                "Payments & Billing",
                "Invoices, payment methods,\n"
                        + "refunds, and pricing.",
                "Payments & Billing"
        );


        VBox accountCard = createCategoryCard(
                "♙",
                "Account Settings",
                "Password reset, profile\n"
                        + "updates, and email preferences.",
                "Account Settings"
        );


        categoryRow1.getChildren().addAll(
                ordersCard,
                paymentCard,
                accountCard
        );


        // =====================================================
        // CATEGORY ROW 2
        // =====================================================

        HBox categoryRow2 = new HBox(12);

        categoryRow2.setAlignment(Pos.CENTER);


        VBox aiCard = createCategoryCard(
                "♟",
                "AI Assistant Help",
                "Using smart features,\n"
                        + "recommendations, and AI tools.",
                "AI Assistant Help"
        );


        VBox securityCard = createCategoryCard(
                "♢",
                "Safety & Privacy",
                "Data protection, reporting\n"
                        + "issues, and security settings.",
                "Safety & Privacy"
        );


        categoryRow2.getChildren().addAll(
                aiCard,
                securityCard
        );


        // =====================================================
        // CATEGORY SECTION BOX
        // =====================================================

        VBox categorySection = new VBox(12);

        categorySection.setAlignment(
                Pos.CENTER_LEFT
        );

        categorySection.getChildren().addAll(
                categoryTitle,
                categoryRow1,
                categoryRow2
        );


        contentBox.getChildren().add(
                categorySection
        );


        // =====================================================
        // FAQ BOX
        // =====================================================

        VBox faqBox = new VBox();

        faqBox.setPadding(
                new Insets(18, 20, 15, 20)
        );

        faqBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-color: #e7e3e8;" +
                "-fx-border-radius: 7;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 6, 0, 0, 1);"
        );


        Label faqTitle = new Label(
                "Frequently Asked Questions"
        );

        faqTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );


        faqBox.getChildren().add(
                faqTitle
        );


        // =====================================================
        // FAQ 1
        // =====================================================

        VBox faq1 = createFAQ(
                "How do I track my order?",
                "You can track your order from the My Orders section "
                        + "using your order ID."
        );


        // =====================================================
        // FAQ 2
        // =====================================================

        VBox faq2 = createFAQ(
                "What is your return policy?",
                "You can return eligible products within 7 days "
                        + "of delivery."
        );


        // =====================================================
        // FAQ 3
        // =====================================================

        VBox faq3 = createFAQ(
                "How can I contact a seller directly?",
                "Open your order details and select the Contact Seller "
                        + "option."
        );


        faqBox.getChildren().addAll(
                faq1,
                faq2,
                faq3
        );


        contentBox.getChildren().add(
                faqBox
        );


        // =====================================================
        // STILL NEED HELP
        // =====================================================

        VBox supportBox = new VBox(12);

        supportBox.setAlignment(
                Pos.CENTER
        );

        supportBox.setPadding(
                new Insets(20)
        );

        supportBox.setStyle(
                "-fx-background-color: #f7f4f8;" +
                "-fx-background-radius: 7;" +
                "-fx-border-color: #e8e3e9;" +
                "-fx-border-radius: 7;"
        );


        // =====================================================
        // SUPPORT TITLE
        // =====================================================

        Label supportTitle = new Label(
                "Still need help?"
        );

        supportTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;"
        );


        // =====================================================
        // SUPPORT DESCRIPTION
        // =====================================================

        Label supportText = new Label(
                "Our support team is available 24/7 to assist you with any questions or concerns you may\n"
                        + "have."
        );

        supportText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;"
        );

        supportText.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // LIVE CHAT BUTTON
        // =====================================================

        Button liveChat = new Button(
                "▣  Live Chat"
        );

        liveChat.setStyle(
                "-fx-background-color: #a93b0b;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;" +
                "-fx-padding: 8 15;" +
                "-fx-font-size: 10px;"
        );


        // =====================================================
        // EMAIL BUTTON
        // =====================================================

        Button email = new Button(
                "✉  Email Us"
        );

        email.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-border-color: #555555;" +
                "-fx-background-radius: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-padding: 8 15;" +
                "-fx-font-size: 10px;"
        );


        // =====================================================
        // CALL SUPPORT BUTTON
        // =====================================================

        Button call = new Button(
                "☏  Call Support"
        );

        call.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-padding: 8 15;" +
                "-fx-font-size: 10px;"
        );


        // =====================================================
        // BACK BUTTON
        // =====================================================

        Button backBtn = new Button(
                "←  Back"
        );

        backBtn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-border-color: #a93b0b;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-padding: 8 18;" +
                "-fx-font-size: 10px;"
        );
        backBtn.setOnAction(event->{
           callbacktosetion.run();
        });


        // =====================================================
        // SUPPORT BUTTON HBOX
        // =====================================================

        HBox supportButtons = new HBox(10);

        supportButtons.setAlignment(
                Pos.CENTER
        );

        supportButtons.getChildren().addAll(
                liveChat,
                email,
                call,
                backBtn
        );


        supportBox.getChildren().addAll(
                supportTitle,
                supportText,
                supportButtons
        );


        contentBox.getChildren().add(
                supportBox
        );


        // =====================================================
        // SEARCH FUNCTIONALITY
        // =====================================================

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    String search =
                            newValue.toLowerCase().trim();


                    ordersCard.setVisible(
                            search.isEmpty()
                                    || "my orders".contains(search)
                    );

                    ordersCard.setManaged(
                            search.isEmpty()
                                    || "my orders".contains(search)
                    );


                    paymentCard.setVisible(
                            search.isEmpty()
                                    || "payments billing".contains(search)
                    );

                    paymentCard.setManaged(
                            search.isEmpty()
                                    || "payments billing".contains(search)
                    );


                    accountCard.setVisible(
                            search.isEmpty()
                                    || "account settings".contains(search)
                    );

                    accountCard.setManaged(
                            search.isEmpty()
                                    || "account settings".contains(search)
                    );


                    aiCard.setVisible(
                            search.isEmpty()
                                    || "ai assistant help".contains(search)
                    );

                    aiCard.setManaged(
                            search.isEmpty()
                                    || "ai assistant help".contains(search)
                    );


                    securityCard.setVisible(
                            search.isEmpty()
                                    || "safety privacy".contains(search)
                    );

                    securityCard.setManaged(
                            search.isEmpty()
                                    || "safety privacy".contains(search)
                    );
                }
        );


        // =====================================================
        // HEADER BUTTON FUNCTIONALITY
        // =====================================================

        shopBtn.setOnAction(e ->
                showMessage(
                        "Shop",
                        "Shop section opened."
                )
        );


        ordersBtn.setOnAction(e ->
                showMessage(
                        "Orders",
                        "Your orders will appear here."
                )
        );


        helpBtn.setOnAction(e ->
                showMessage(
                        "Help",
                        "You are already on the Help Center."
                )
        );


        accountBtn.setOnAction(e ->
                showMessage(
                        "Account",
                        "Account settings opened."
                )
        );


        // =====================================================
        // LIVE CHAT FUNCTIONALITY
        // =====================================================

        liveChat.setOnAction(e ->
                showMessage(
                        "Live Chat",
                        "Connecting you with our support team..."
                )
        );


        // =====================================================
        // EMAIL FUNCTIONALITY
        // =====================================================

    email.setOnAction(e -> {

    try {

        String emailAddress =
                "sayalirepale2006@gmail.com";

        String subject =
                "EliteMarket Support";

        String body =
                "Hello EliteMarket Support,\n\n"
                        + "I need help regarding my order.";


        // Encode subject and body properly
        String encodedSubject =
                URLEncoder.encode(
                        subject,
                        StandardCharsets.UTF_8
                );

        String encodedBody =
                URLEncoder.encode(
                        body,
                        StandardCharsets.UTF_8
                );


        String url =
                "https://mail.google.com/mail/?view=cm&fs=1"
                        + "&to=" + emailAddress
                        + "&su=" + encodedSubject
                        + "&body=" + encodedBody;


        Desktop.getDesktop().browse(
                URI.create(url)
        );


    } catch (Exception ex) {

        ex.printStackTrace();

        showMessage(
                "Error",
                "Unable to open Gmail."
        );
    }
});
               
             

        // =====================================================
        // CALL SUPPORT FUNCTIONALITY
        // =====================================================

        call.setOnAction(e ->
                showMessage(
                        "Call Support",
                        "Call us at +91 7709701201"
                )
        );


        // =====================================================
        // BACK BUTTON FUNCTIONALITY
        // =====================================================

      

        // =====================================================
        // ROOT CENTER
        // =====================================================

        root.setCenter(
                contentBox
        );
        root.setStyle("-fx-background-color: #eee5df;");


        // =====================================================
        // SCENE
        // =====================================================

        Scene scene = new Scene(
                root,
                1530,
                850
        );
        HelpScene=scene;



        return HelpScene;
    }

    


    // =========================================================
    // CREATE NAV BUTTON
    // =========================================================

    private Button createNavButton(
            String text) {

        Button button =
                new Button(text);


        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 8 3;"
        );


        return button;
    }


    // =========================================================
    // CREATE CATEGORY CARD
    // =========================================================

    private VBox createCategoryCard(
            String icon,
            String title,
            String description,
            String popupTitle) {


        // ---------------- ICON ----------------

        Label iconLabel =
                new Label(icon);


        iconLabel.setMinWidth(28);

        iconLabel.setMinHeight(28);

        iconLabel.setAlignment(
                Pos.CENTER
        );


        iconLabel.setStyle(
                "-fx-background-color: #fff0e7;" +
                "-fx-background-radius: 5;" +
                "-fx-text-fill: #a93b0b;" +
                "-fx-font-size: 14px;"
        );


        // ---------------- TITLE ----------------

        Label titleLabel =
                new Label(title);


        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );


        // ---------------- DESCRIPTION ----------------

        Label descriptionLabel =
                new Label(description);


        descriptionLabel.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #777777;"
        );


        // ---------------- TEXT BOX ----------------

        VBox textBox =
                new VBox(4);


        textBox.getChildren().addAll(
                titleLabel,
                descriptionLabel
        );


        // ---------------- CARD CONTENT ----------------

        HBox cardContent =
                new HBox(10);


        cardContent.setAlignment(
                Pos.TOP_LEFT
        );


        cardContent.getChildren().addAll(
                iconLabel,
                textBox
        );


        // ---------------- CARD ----------------

        VBox card =
                new VBox();


        card.setPrefWidth(245);

        card.setPrefHeight(65);

        card.setPadding(
                new Insets(12)
        );


        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-color: #e5e2e5;" +
                "-fx-border-radius: 7;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 7, 0, 0, 2);"
        );


        card.getChildren().add(
                cardContent
        );


        // =====================================================
        // CARD CLICK
        // =====================================================

        card.setOnMouseClicked(e ->
                showMessage(
                        popupTitle,
                        "You selected "
                                + popupTitle
                                + "."
                )
        );


        // =====================================================
        // CARD HOVER
        // =====================================================

        card.setOnMouseEntered(e ->
                card.setStyle(
                        "-fx-background-color: #fffaf7;" +
                        "-fx-background-radius: 7;" +
                        "-fx-border-color: #a93b0b;" +
                        "-fx-border-radius: 7;" +
                        "-fx-cursor: hand;"
                )
        );


        card.setOnMouseExited(e ->
                card.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-background-radius: 7;" +
                        "-fx-border-color: #e5e2e5;" +
                        "-fx-border-radius: 7;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 7, 0, 0, 2);"
                )
        );


        return card;
    }


    // =========================================================
    // CREATE FAQ
    // =========================================================

    private VBox createFAQ(
            String question,
            String answer) {


        // ---------------- QUESTION ----------------

        Label questionLabel =
                new Label(
                        question + "                                      ˅"
                );


        questionLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 13 0;"
        );


        // ---------------- ANSWER ----------------

        Label answerLabel =
                new Label(answer);


        answerLabel.setWrapText(true);


        answerLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;" +
                "-fx-padding: 0 0 12 0;"
        );


        answerLabel.setVisible(false);

        answerLabel.setManaged(false);


        // ---------------- FAQ BOX ----------------

        VBox faq =
                new VBox();


        faq.getChildren().addAll(
                questionLabel,
                answerLabel
        );


        // =====================================================
        // FAQ CLICK FUNCTIONALITY
        // =====================================================

        questionLabel.setOnMouseClicked(e -> {

            boolean visible =
                    answerLabel.isVisible();


            answerLabel.setVisible(
                    !visible
            );


            answerLabel.setManaged(
                    !visible
            );
        });


        return faq;
    }


    // =========================================================
    // SHOW MESSAGE
    // =========================================================

    private void showMessage(
            String title,
            String message) {


        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );


        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
    public void backtoDashboard() {
    Seting seting = new Seting(userId);
    Homepage.HomepageStage.setScene(
        HelpScene
        
    );
}

}
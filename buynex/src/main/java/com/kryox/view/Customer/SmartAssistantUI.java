package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SmartAssistantUI {

    private Scene SmartAssistantUi;

    // This VBox is the live chat feed.
    private VBox chatVBox;

    // TextArea is used instead of TextField so API/user messages can be longer.
    private TextArea inputField;

    private ScrollPane chatScrollPane;

    public Scene getSmartAssisstantui() {

        // =========================================================
        // ROOT
        // =========================================================
        HBox root = new HBox();
        root.setSpacing(0);
        root.setAlignment(Pos.TOP_LEFT);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        root.setStyle("-fx-background-color: #FFFFFF;");

        // =========================================================
        // 2. CENTER AI CHAT
        // =========================================================
        VBox centerVBox = new VBox();
        centerVBox.setPrefWidth(700);
        centerVBox.setMinWidth(560);
        centerVBox.setMaxWidth(Double.MAX_VALUE);
        centerVBox.setMaxHeight(Double.MAX_VALUE);
        HBox.setHgrow(centerVBox, Priority.ALWAYS);
        centerVBox.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #FFF9F5, #F4ECE7);"
        );

        // =========================================================
        // AI HEADER - STYLISH + BACK BUTTON
        // =========================================================
        HBox headerHBox = new HBox();
        headerHBox.setPrefHeight(76);
        headerHBox.setMinHeight(76);
        headerHBox.setAlignment(Pos.CENTER_LEFT);
        headerHBox.setPadding(new Insets(12, 20, 12, 18));
        headerHBox.setSpacing(14);
        headerHBox.setStyle(
                "-fx-background-color: #ebccb7;" +
                "-fx-border-color: #E8DDD6;" +
                "-fx-border-width: 0 0 1 0;"
        );

        Button backButton = new Button("←  Back");
        backButton.setPrefHeight(38);
        backButton.setMinWidth(82);
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        backButton.setTextFill(Color.web("#5C3B2E"));
        backButton.setStyle(
                "-fx-background-color: #FFF0E7;" +
                "-fx-background-radius: 19;" +
                "-fx-border-color: #F1CDBB;" +
                "-fx-border-radius: 19;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnMouseEntered(event ->
                backButton.setStyle(
                        "-fx-background-color: #FF7109;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 19;" +
                        "-fx-border-color: #FF7109;" +
                        "-fx-border-radius: 19;" +
                        "-fx-cursor: hand;"
                )
        );

        backButton.setOnMouseExited(event ->
                backButton.setStyle(
                        "-fx-background-color: #FFF0E7;" +
                        "-fx-text-fill: #5C3B2E;" +
                        "-fx-background-radius: 19;" +
                        "-fx-border-color: #F1CDBB;" +
                        "-fx-border-radius: 19;" +
                        "-fx-cursor: hand;"
                )
        );

        // Change this to your own previous-page navigation if needed.
        backButton.setOnAction(event -> {
            if (backButton.getScene() != null &&
                    backButton.getScene().getWindow() != null) {
                backButton.getScene().getWindow().hide();
            }
        });

        VBox aiCircle = new VBox();
        aiCircle.setPrefSize(42, 42);
        aiCircle.setMinSize(42, 42);
        aiCircle.setMaxSize(42, 42);
        aiCircle.setAlignment(Pos.CENTER);
        aiCircle.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 30;" +
                "-fx-effect: dropshadow(gaussian, rgba(166,63,8,0.20), 8, 0, 0, 2);"
        );

        Text aiIcon = new Text("✦");
        aiIcon.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        aiIcon.setFill(Color.WHITE);
        aiCircle.getChildren().add(aiIcon);

        Text aiTitle = new Text("BuyNeX Smart Assistant");
        aiTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        aiTitle.setFill(Color.web("#2E2420"));

        Text aiSubtitle = new Text("Smart local shopping • AI powered");
        aiSubtitle.setFont(Font.font("Arial", 9));
        aiSubtitle.setFill(Color.web("#806B61"));

        Text onlineDot = new Text("●");
        onlineDot.setFont(Font.font("Arial", 8));
        onlineDot.setFill(Color.web("#3E9B55"));

        Text onlineText = new Text(" Online");
        onlineText.setFont(Font.font("Arial", 9));
        onlineText.setFill(Color.web("#3E7D4B"));

        HBox onlineHBox = new HBox(2, onlineDot, onlineText);
        onlineHBox.setAlignment(Pos.CENTER_LEFT);

        VBox titleVBox = new VBox(3, aiTitle, aiSubtitle, onlineHBox);

        Region headerSpace = new Region();
        HBox.setHgrow(headerSpace, Priority.ALWAYS);

        Text menuIcon = new Text("⋮");
        menuIcon.setFont(Font.font("Arial", 22));
        menuIcon.setFill(Color.web("#6C5A52"));

        headerHBox.getChildren().addAll(
                backButton,
                aiCircle,
                titleVBox,
                headerSpace,
                menuIcon
        );

        // =========================================================
        // LIVE CHAT FEED
        // =========================================================
        chatVBox = new VBox(16);
        chatVBox.setPadding(new Insets(22, 28, 18, 28));
        chatVBox.setFillWidth(true);

        // Initial message only. Static demo conversation is removed.
        addAiMessage(
                "Hello! I'm your BuyNeX AI. How can I help you source, " +
                "compare, or track your local purchases today?"
        );

        chatScrollPane = new ScrollPane(chatVBox);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        chatScrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;" +
                "-fx-border-color: transparent;"
        );
        VBox.setVgrow(chatScrollPane, Priority.ALWAYS);

        // =========================================================
        // BOTTOM INPUT AREA
        // =========================================================
        HBox inputOuterHBox = new HBox();
        inputOuterHBox.setPrefHeight(78);
        inputOuterHBox.setAlignment(Pos.CENTER);
        inputOuterHBox.setPadding(new Insets(12, 24, 14, 24));
        inputOuterHBox.setStyle(
                "-fx-border-color: #E7E2E8;" +
                "-fx-border-width: 1 0 0 0;"
        );

        HBox inputHBox = new HBox();
        inputHBox.setPrefHeight(50);
        inputHBox.setAlignment(Pos.CENTER_LEFT);
        inputHBox.setSpacing(8);
        inputHBox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(inputHBox, Priority.ALWAYS);
        inputHBox.setPadding(new Insets(4, 8, 4, 10));
        inputHBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 25;" +
                "-fx-border-color: #E3D5CD;" +
                "-fx-border-radius: 25;" +
                "-fx-effect: dropshadow(gaussian, rgba(80,45,25,0.08), 12, 0, 0, 2);"
        );

        Text attachIcon = new Text("⌕");
        attachIcon.setFont(Font.font("Arial", 18));
        attachIcon.setFill(Color.web("#5B5552"));

        // =========================================================
        // TEXTAREA - replaces TextField
        // =========================================================
        inputField = new TextArea();
        inputField.setPromptText("Ask BuyNeX AI...");
        inputField.setFont(Font.font("Arial", 11));
        inputField.setWrapText(true);
        inputField.setPrefRowCount(1);
        inputField.setPrefHeight(38);
        inputField.setMinHeight(32);
        inputField.setMaxHeight(55);
        inputField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-background-insets: 0;" +
                "-fx-padding: 8 2 8 2;"
        );
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Text micIcon = new Text("♩");
        micIcon.setFont(Font.font("Arial", 17));
        micIcon.setFill(Color.web("#5B5552"));

        Button sendButton = new Button("➤");
        sendButton.setPrefSize(31, 31);
        sendButton.setMinSize(31, 31);
        sendButton.setMaxSize(31, 31);
        sendButton.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        sendButton.setTextFill(Color.WHITE);
        sendButton.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-background-radius: 30;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );

        // =========================================================
        // SEND BUTTON
        // =========================================================
        sendButton.setOnAction(event -> sendMessage());

        // Enter = send.
        // Shift + Enter = new line.
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER
                    && !event.isShiftDown()) {

                event.consume();
                sendMessage();
            }
        });

        inputHBox.getChildren().addAll(
                attachIcon,
                inputField,
                micIcon,
                sendButton
        );

        inputOuterHBox.getChildren().add(inputHBox);

        centerVBox.getChildren().addAll(
                headerHBox,
                chatScrollPane,
                inputOuterHBox
        );

        // =========================================================
        // 3. RIGHT AI CAPABILITIES
        // =========================================================
        VBox rightVBox = new VBox();
        rightVBox.setPrefWidth(285);
        rightVBox.setMinWidth(285);
        rightVBox.setMaxWidth(285);
        rightVBox.setMaxHeight(Double.MAX_VALUE);
        rightVBox.setPadding(new Insets(22, 16, 18, 16));
        rightVBox.setSpacing(10);
        rightVBox.setStyle(
                "-fx-background-color: #ebccb7;" +
                "-fx-border-color: #E8DDD6;" +
                "-fx-border-width: 0 0 0 1;"
        );

        Text capabilitiesTitle = new Text("Explore BuyNeX AI");
        capabilitiesTitle.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        capabilitiesTitle.setFill(Color.web("#30241F"));

        VBox sourceCard = capabilityCard(
                "▢",
                "Source Products",
                "\"Find the best price for\navocados nearby.\""
        );

        VBox trackCard = capabilityCard(
                "▣",
                "Track Orders",
                "\"Track my current order\nfrom Electronics Hub.\""
        );

        VBox compareCard = capabilityCard(
                "↔",
                "Compare Shops",
                "\"Compare electronics\nshops near me.\""
        );

        VBox trendsCard = capabilityCard(
                "▤",
                "Local Trends",
                "\"What are the top trending\nitems in my neighborhood\ntoday?\""
        );

        Region rightSpace = new Region();
        VBox.setVgrow(rightSpace, Priority.ALWAYS);

        VBox infoBox = new VBox();
        infoBox.setPrefHeight(120);
        infoBox.setPadding(new Insets(10));
        infoBox.setSpacing(6);
        infoBox.setStyle(
                "-fx-background-color: #FFF7F3;" +
                "-fx-border-color: #F0D5C8;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );

        HBox infoTitleHBox = new HBox(7);
        infoTitleHBox.setAlignment(Pos.TOP_LEFT);

        Text infoIcon = new Text("ⓘ");
        infoIcon.setFont(Font.font("Arial", 15));
        infoIcon.setFill(Color.web("#A94005"));

        Text infoTitle = new Text("The BuyNeX Smart\nAssistant");
        infoTitle.setFont(Font.font("Arial", 10));
        infoTitle.setFill(Color.web("#594C48"));

        infoTitleHBox.getChildren().addAll(infoIcon, infoTitle);

        Text infoText = new Text(
                "uses real-time local data to\n" +
                "provide the most accurate\n" +
                "recommendations."
        );
        infoText.setFont(Font.font("Arial", 9));
        infoText.setFill(Color.web("#594C48"));

        infoBox.getChildren().addAll(infoTitleHBox, infoText);

        rightVBox.getChildren().addAll(
                capabilitiesTitle,
                sourceCard,
                trackCard,
                compareCard,
                trendsCard,
                rightSpace,
                infoBox
        );

        // =========================================================
        // ROOT
        // =========================================================
        root.getChildren().addAll(
                centerVBox,
                rightVBox
        );

        SmartAssistantUi = new Scene(root, 1250, 760);

        return SmartAssistantUi;
    }

    // =============================================================
    // SEND MESSAGE
    // =============================================================
    private void sendMessage() {

        String userText = inputField.getText();

        if (userText == null || userText.trim().isEmpty()) {
            return;
        }

        userText = userText.trim();

        // Add user's typed message to the live feed.
        addUserMessage(userText);

        // Clear input after sending.
        inputField.clear();

        /*
         * ==========================================================
         * FUTURE GROQ / API INTEGRATION
         * ==========================================================
         *
         * Later, API response yaha se milega:
         *
         * String apiResponse = callGroqAPI(userText);
         * addAiMessage(apiResponse);
         *
         * Abhi testing ke liye echo response show ho raha hai.
         */
        String testResponse =
                "I found some products related to your request:\n\"" +
                userText + "\"";

        addAiMessage(testResponse);

        // =========================================================
        // TEST PRODUCT RESULTS
        // =========================================================
        // Later these products can come directly from your API/Groq
        // response. For now, they are sample dynamic result cards.
        addProductResult(
                "Artisan Sourdough",
                "Freshly baked • 500g",
                "$5.99",
                "/assets/images/img1.png"
        );

        addProductResult(
                "Organic Avocado",
                "Fresh produce • 4 pack",
                "$6.99",
                "/assets/images/avocado.png"
        );

        addProductResult(
                "Wireless Headphones",
                "Bluetooth • Local store",
                "$29.99",
                "/assets/images/headphone.png"
        );
    }

    // =============================================================
    // ADD USER MESSAGE TO FEED
    // =============================================================
    private void addUserMessage(String message) {

        HBox userMessageHBox = new HBox();
        userMessageHBox.setAlignment(Pos.TOP_RIGHT);
        userMessageHBox.setMaxWidth(Double.MAX_VALUE);

        Text userMessage = new Text(message);
        userMessage.setFont(Font.font("Arial", 11));
        userMessage.setFill(Color.WHITE);
        userMessage.setWrappingWidth(330);

        VBox userMessageBox = new VBox();
        userMessageBox.setPadding(new Insets(9, 11, 9, 11));
        userMessageBox.setMaxWidth(360);
        userMessageBox.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-background-radius: 9;"
        );
        userMessageBox.getChildren().add(userMessage);

        userMessageHBox.getChildren().add(userMessageBox);

        chatVBox.getChildren().add(userMessageHBox);
        scrollToBottom();
    }

    // =============================================================
    // ADD AI MESSAGE TO FEED
    // =============================================================
    private void addAiMessage(String message) {

        HBox aiMessageHBox = new HBox();
        aiMessageHBox.setAlignment(Pos.TOP_LEFT);
        aiMessageHBox.setSpacing(9);

        VBox smallAiCircle = new VBox();
        smallAiCircle.setPrefSize(23, 23);
        smallAiCircle.setMinSize(23, 23);
        smallAiCircle.setMaxSize(23, 23);
        smallAiCircle.setAlignment(Pos.CENTER);
        smallAiCircle.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 20;"
        );

        Text smallAiIcon = new Text("♙");
        smallAiIcon.setFont(Font.font("Arial", 12));
        smallAiCircle.getChildren().add(smallAiIcon);

        Text aiMessage = new Text(message);
        aiMessage.setFont(Font.font("Arial", 11));
        aiMessage.setFill(Color.web("#3E3836"));
        aiMessage.setWrappingWidth(390);

        VBox aiMessageBox = new VBox();
        aiMessageBox.setPadding(new Insets(10, 11, 10, 11));
        aiMessageBox.setMaxWidth(410);
        aiMessageBox.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 8;"
        );
        aiMessageBox.getChildren().add(aiMessage);

        aiMessageHBox.getChildren().addAll(
                smallAiCircle,
                aiMessageBox
        );

        chatVBox.getChildren().add(aiMessageHBox);
        scrollToBottom();
    }

    // =============================================================
    // ADD PRODUCT RESULT CARD TO CHAT
    // =============================================================
    private void addProductResult(
            String productName,
            String productDescription,
            String price,
            String imagePath) {

        HBox aiRow = new HBox();
        aiRow.setAlignment(Pos.TOP_LEFT);
        aiRow.setSpacing(9);
        aiRow.setMaxWidth(Double.MAX_VALUE);

        // AI icon
        VBox smallAiCircle = new VBox();
        smallAiCircle.setPrefSize(23, 23);
        smallAiCircle.setMinSize(23, 23);
        smallAiCircle.setMaxSize(23, 23);
        smallAiCircle.setAlignment(Pos.CENTER);
        smallAiCircle.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 20;"
        );

        Text smallAiIcon = new Text("♙");
        smallAiIcon.setFont(Font.font("Arial", 12));
        smallAiCircle.getChildren().add(smallAiIcon);

        // =========================================================
        // PRODUCT CARD
        // =========================================================
        VBox productCard = new VBox();
        productCard.setSpacing(8);
        productCard.setPadding(new Insets(9));
        productCard.setMaxWidth(410);
        productCard.setPrefWidth(390);
        productCard.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 10;"
        );

        HBox productRow = new HBox();
        productRow.setSpacing(10);
        productRow.setAlignment(Pos.CENTER_LEFT);

        // =========================================================
        // PRODUCT IMAGE
        // =========================================================
        VBox imageBox = new VBox();
        imageBox.setPrefSize(82, 76);
        imageBox.setMinSize(82, 76);
        imageBox.setMaxSize(82, 76);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setStyle(
                "-fx-background-color: #FFF8F3;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 8;"
        );

        try {
            java.net.URL imageUrl = getClass().getResource(imagePath);

            if (imageUrl != null) {
                Image image = new Image(
                        imageUrl.toExternalForm(),
                        72,
                        66,
                        true,
                        true
                );

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(72);
                imageView.setFitHeight(66);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);

                imageBox.getChildren().add(imageView);
            } else {
                Text noImage = new Text("No Image");
                noImage.setFont(Font.font("Arial", 8));
                noImage.setFill(Color.web("#999999"));
                imageBox.getChildren().add(noImage);
            }

        } catch (Exception ex) {
            Text noImage = new Text("No Image");
            noImage.setFont(Font.font("Arial", 8));
            noImage.setFill(Color.web("#999999"));
            imageBox.getChildren().add(noImage);
        }

        // =========================================================
        // PRODUCT DETAILS
        // =========================================================
        VBox detailsBox = new VBox();
        detailsBox.setSpacing(4);
        detailsBox.setAlignment(Pos.CENTER_LEFT);

        Text name = new Text(productName);
        name.setFont(
                Font.font("Arial", FontWeight.BOLD, 12)
        );
        name.setFill(Color.web("#302A28"));
        name.setWrappingWidth(180);

        Text description = new Text(productDescription);
        description.setFont(Font.font("Arial", 9));
        description.setFill(Color.web("#625A56"));
        description.setWrappingWidth(180);

        Text priceText = new Text(price);
        priceText.setFont(
                Font.font("Arial", FontWeight.BOLD, 13)
        );
        priceText.setFill(Color.web("#A94005"));

        detailsBox.getChildren().addAll(
                name,
                description,
                priceText
        );

        Region productSpacer = new Region();
        HBox.setHgrow(productSpacer, Priority.ALWAYS);

        // =========================================================
        // ADD TO CART BUTTON
        // =========================================================
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setPrefWidth(86);
        addToCartButton.setPrefHeight(31);
        addToCartButton.setMinWidth(86);
        addToCartButton.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-text-fill: white;" +
                "-fx-font-family: 'Arial';" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        addToCartButton.setOnMouseEntered(event ->
                addToCartButton.setStyle(
                        "-fx-background-color: #D95600;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 9px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
                )
        );

        addToCartButton.setOnMouseExited(event -> {
            if (!addToCartButton.getText().equals("Added ✓")) {
                addToCartButton.setStyle(
                        "-fx-background-color: #FF7109;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-font-size: 9px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;"
                );
            }
        });

        addToCartButton.setOnAction(event -> {
            addToCartButton.setText("Added ✓");
            addToCartButton.setStyle(
                    "-fx-background-color: #3E8E41;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-family: 'Arial';" +
                    "-fx-font-size: 9px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;"
            );
        });

        productRow.getChildren().addAll(
                imageBox,
                detailsBox,
                productSpacer,
                addToCartButton
        );

        productCard.getChildren().add(productRow);

        aiRow.getChildren().addAll(
                smallAiCircle,
                productCard
        );

        chatVBox.getChildren().add(aiRow);
        scrollToBottom();
    }

    // =============================================================
    // AUTO SCROLL TO LATEST MESSAGE
    // =============================================================
    private void scrollToBottom() {

        if (chatScrollPane == null) {
            return;
        }

        javafx.application.Platform.runLater(() ->
                chatScrollPane.setVvalue(1.0)
        );
    }

    // =============================================================
    // RIGHT CAPABILITY CARD
    // =============================================================
    private VBox capabilityCard(
            String iconText,
            String titleText,
            String descriptionText) {

        VBox card = new VBox();
        card.setPrefHeight(85);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setPadding(new Insets(10));
        card.setSpacing(5);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #EEE9EF;" +
                "-fx-border-radius: 8;"
        );

        HBox titleHBox = new HBox(7);
        titleHBox.setAlignment(Pos.CENTER_LEFT);

        Text icon = new Text(iconText);
        icon.setFont(Font.font("Arial", 14));
        icon.setFill(Color.web("#A94005"));

        Text title = new Text(titleText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        title.setFill(Color.web("#A94005"));

        titleHBox.getChildren().addAll(icon, title);

        Text description = new Text(descriptionText);
        description.setFont(Font.font("Arial", 9));
        description.setFill(Color.web("#594C48"));

        card.getChildren().addAll(titleHBox, description);

        return card;
    }
}
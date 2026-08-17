package com.kryox.view.Customer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SmartAssistantUI extends Application {

    @Override
    public void start(Stage stage) {

        // =========================================================
        // ROOT HBOX
        // 1. LEFT SIDEBAR
        // 2. CENTER AI CHAT
        // 3. RIGHT AI CAPABILITIES
        // =========================================================

        HBox root = new HBox();

        root.setSpacing(0);
        root.setAlignment(Pos.TOP_LEFT);
        root.setMaxSize(
                Double.MAX_VALUE,
                Double.MAX_VALUE
        );
        root.setMaxWidth(Double.MAX_VALUE);
        root.setMaxHeight(Double.MAX_VALUE);
        

        root.setStyle(
                "-fx-background-color: #FFFFFF;"
        );


        // =========================================================
        // 1. LEFT SIDEBAR
        // =========================================================

        VBox leftVBox = new VBox();

        leftVBox.setPrefWidth(250);
        leftVBox.setMinWidth(250);
        leftVBox.setMaxWidth(250);

        leftVBox.setMaxHeight(Double.MAX_VALUE);

        leftVBox.setPadding(
                new Insets(48, 13, 25, 13)
        );

        leftVBox.setSpacing(10);

        leftVBox.setStyle(
        "-fx-background-color: #EBC9B2;" +
        "-fx-border-color: #E4DEE7;" +
        "-fx-border-width: 0 1 0 0;"
);

        // =========================================================
        // BUY NEX LOGO
        // =========================================================

        Text buyNex = new Text("BuyNeX");

        buyNex.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        buyNex.setFill(
                Color.web("#A63F08")
        );


        Text marketplace = new Text(
                "Hyperlocal Marketplace"
        );

        marketplace.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        9
                )
        );

        marketplace.setFill(
                Color.web("#332A27")
        );


        VBox logoVBox = new VBox();

        logoVBox.setSpacing(2);

        logoVBox.getChildren().addAll(
                buyNex,
                marketplace
        );


        // =========================================================
        // SPACE
        // =========================================================

        Region sideSpace = new Region();

        sideSpace.setPrefHeight(42);


        // =========================================================
        // DASHBOARD
        // =========================================================

        HBox dashboardHBox = new HBox();

        dashboardHBox.setPrefHeight(36);

        dashboardHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        dashboardHBox.setSpacing(13);

        dashboardHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text dashboardIcon = new Text("▦");

        dashboardIcon.setFont(
                Font.font("Arial", 17)
        );

        dashboardIcon.setFill(
                Color.web("#443A36")
        );


        Text dashboardText = new Text(
                "Dashboard"
        );

        dashboardText.setFont(
                Font.font("Arial", 11)
        );

        dashboardText.setFill(
                Color.web("#443A36")
        );


        dashboardHBox.getChildren().addAll(
                dashboardIcon,
                dashboardText
        );


        // =========================================================
        // CATEGORIES
        // =========================================================

        HBox categoriesHBox = new HBox();

        categoriesHBox.setPrefHeight(36);

        categoriesHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        categoriesHBox.setSpacing(13);

        categoriesHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text categoriesIcon = new Text("△");

        categoriesIcon.setFont(
                Font.font("Arial", 16)
        );

        categoriesIcon.setFill(
                Color.web("#443A36")
        );


        Text categoriesText = new Text(
                "Categories"
        );

        categoriesText.setFont(
                Font.font("Arial", 11)
        );

        categoriesText.setFill(
                Color.web("#443A36")
        );


        categoriesHBox.getChildren().addAll(
                categoriesIcon,
                categoriesText
        );


        // =========================================================
        // DEALS
        // =========================================================

        HBox dealsHBox = new HBox();

        dealsHBox.setPrefHeight(36);

        dealsHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        dealsHBox.setSpacing(13);

        dealsHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text dealsIcon = new Text("◇");

        dealsIcon.setFont(
                Font.font("Arial", 18)
        );

        dealsIcon.setFill(
                Color.web("#443A36")
        );


        Text dealsText = new Text(
                "Deals"
        );

        dealsText.setFont(
                Font.font("Arial", 11)
        );

        dealsText.setFill(
                Color.web("#443A36")
        );


        dealsHBox.getChildren().addAll(
                dealsIcon,
                dealsText
        );


        // =========================================================
        // MY ORDERS - ACTIVE
        // =========================================================

        HBox ordersHBox = new HBox();

        ordersHBox.setPrefHeight(34);

        ordersHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        ordersHBox.setSpacing(13);

        ordersHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );

        ordersHBox.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 7;"
        );


        Text ordersIcon = new Text("♧");

        ordersIcon.setFont(
                Font.font("Arial", 18)
        );

        ordersIcon.setFill(
                Color.web("#332A27")
        );


        Text ordersText = new Text(
                "My Orders"
        );

        ordersText.setFont(
                Font.font("Arial", 11)
        );

        ordersText.setFill(
                Color.web("#332A27")
        );


        ordersHBox.getChildren().addAll(
                ordersIcon,
                ordersText
        );


        // =========================================================
        // ANALYTICS
        // =========================================================

        HBox analyticsHBox = new HBox();

        analyticsHBox.setPrefHeight(36);

        analyticsHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        analyticsHBox.setSpacing(13);

        analyticsHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text analyticsIcon = new Text("▣");

        analyticsIcon.setFont(
                Font.font("Arial", 16)
        );

        analyticsIcon.setFill(
                Color.web("#443A36")
        );


        Text analyticsText = new Text(
                "Analytics"
        );

        analyticsText.setFont(
                Font.font("Arial", 11)
        );

        analyticsText.setFill(
                Color.web("#443A36")
        );


        analyticsHBox.getChildren().addAll(
                analyticsIcon,
                analyticsText
        );


        // =========================================================
        // ADD ALL SIDEBAR ITEMS
        // =========================================================

        leftVBox.getChildren().addAll(
                logoVBox,
                sideSpace,
                dashboardHBox,
                categoriesHBox,
                dealsHBox,
                ordersHBox,
                analyticsHBox
        );


        // =========================================================
        // 2. CENTER AI CHAT
        // =========================================================

        VBox centerVBox = new VBox();

        centerVBox.setPrefWidth(450);
        centerVBox.setMinWidth(450);
        centerVBox.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(
                centerVBox,
                Priority.ALWAYS
        );
        centerVBox.setMaxHeight(
                Double.MAX_VALUE
        );

        HBox.setHgrow(
                centerVBox,
                Priority.ALWAYS
        );

        centerVBox.setStyle(
        "-fx-background-color: #EEE5DF;"
        );


        // =========================================================
        // AI HEADER
        // =========================================================

        HBox headerHBox = new HBox();

        headerHBox.setPrefHeight(68);

        headerHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        headerHBox.setPadding(
                new Insets(0, 18, 0, 18)
        );

        headerHBox.setSpacing(12);

        headerHBox.setStyle(
                "-fx-border-color: #E7E2E8;" +
                "-fx-border-width: 0 0 1 0;"
        );


        // AI CIRCLE

        VBox aiCircle = new VBox();

        aiCircle.setPrefSize(30, 30);
        aiCircle.setMinSize(30, 30);
        aiCircle.setMaxSize(30, 30);

        aiCircle.setAlignment(
                Pos.CENTER
        );

        aiCircle.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 30;"
        );


        Text aiIcon = new Text("♙");

        aiIcon.setFont(
                Font.font("Arial", 16)
        );

        aiIcon.setFill(
                Color.web("#332A27")
        );


        aiCircle.getChildren().add(
                aiIcon
        );


        // AI TITLE

        VBox titleVBox = new VBox();

        titleVBox.setSpacing(1);


        Text aiTitle = new Text(
                "BuyNeX Smart Assistant"
        );

        aiTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        17
                )
        );

        aiTitle.setFill(
                Color.web("#211D1B")
        );


        HBox onlineHBox = new HBox();

        onlineHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        onlineHBox.setSpacing(4);


        Text onlineDot = new Text("●");

        onlineDot.setFont(
                Font.font("Arial", 8)
        );

        onlineDot.setFill(
                Color.web("#A63F08")
        );


        Text onlineText = new Text(
                "Online"
        );

        onlineText.setFont(
                Font.font("Arial", 8)
        );

        onlineText.setFill(
                Color.web("#A63F08")
        );


        onlineHBox.getChildren().addAll(
                onlineDot,
                onlineText
        );


        titleVBox.getChildren().addAll(
                aiTitle,
                onlineHBox
        );


        Region headerSpace = new Region();

        HBox.setHgrow(
                headerSpace,
                Priority.ALWAYS
        );


        Text menuIcon = new Text("⋮");

        menuIcon.setFont(
                Font.font("Arial", 22)
        );

        menuIcon.setFill(
                Color.web("#514B48")
        );


        headerHBox.getChildren().addAll(
                aiCircle,
                titleVBox,
                headerSpace,
                menuIcon
        );


        // =========================================================
        // CHAT AREA
        // =========================================================

        VBox chatVBox = new VBox();

        chatVBox.setSpacing(16);

        chatVBox.setPadding(
                new Insets(18, 18, 15, 18)
        );
        chatVBox.setFillWidth(true);

        VBox.setVgrow(
                chatVBox,
                Priority.ALWAYS
        );


        // =========================================================
        // AI MESSAGE 1
        // =========================================================

        HBox aiMessage1HBox = new HBox();

        aiMessage1HBox.setAlignment(
                Pos.TOP_LEFT
        );

        aiMessage1HBox.setSpacing(9);


        VBox smallAiCircle1 = new VBox();

        smallAiCircle1.setPrefSize(23, 23);
        smallAiCircle1.setMinSize(23, 23);
        smallAiCircle1.setMaxSize(23, 23);

        smallAiCircle1.setAlignment(
                Pos.CENTER
        );

        smallAiCircle1.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 20;"
        );


        Text smallAiIcon1 = new Text("♙");

        smallAiIcon1.setFont(
                Font.font("Arial", 12)
        );


        smallAiCircle1.getChildren().add(
                smallAiIcon1
        );


        Text message1 = new Text(
                "Hello! I'm your BuyNeX AI. How can I help you source, compare, or\n" +
                "track your local purchases today?"
        );

        message1.setFont(
                Font.font("Arial", 11)
        );

        message1.setFill(
                Color.web("#3E3836")
        );


        VBox message1Box = new VBox();

        message1Box.setPadding(
                new Insets(10, 11, 10, 11)
        );

        message1Box.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 8;"
        );

        message1Box.getChildren().add(
                message1
        );
        message1Box.setMaxWidth(395);

        aiMessage1HBox.getChildren().addAll(
                smallAiCircle1,
                message1Box
        );


        // =========================================================
        // USER MESSAGE
        // =========================================================

        HBox userMessageHBox = new HBox();

        userMessageHBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        userMessageHBox.setMaxWidth(
        Double.MAX_VALUE
);


        Text userMessage = new Text(
                "Find the best price for avocados near me."
        );

        userMessage.setFont(
                Font.font("Arial", 11)
        );

        userMessage.setFill(
                Color.WHITE
        );


        VBox userMessageBox = new VBox();

        userMessageBox.setPadding(
                new Insets(9, 11, 9, 11)
        );

        userMessageBox.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-background-radius: 9;"
        );

        userMessageBox.getChildren().add(
                userMessage
        );


        userMessageHBox.getChildren().add(
                userMessageBox
        );


        // =========================================================
        // AI MESSAGE 2
        // =========================================================

        HBox aiMessage2HBox = new HBox();

        aiMessage2HBox.setAlignment(
                Pos.TOP_LEFT
        );

        aiMessage2HBox.setSpacing(9);


        VBox smallAiCircle2 = new VBox();

        smallAiCircle2.setPrefSize(23, 23);
        smallAiCircle2.setMinSize(23, 23);
        smallAiCircle2.setMaxSize(23, 23);

        smallAiCircle2.setAlignment(
                Pos.CENTER
        );

        smallAiCircle2.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 20;"
        );


        Text smallAiIcon2 = new Text("♙");

        smallAiIcon2.setFont(
                Font.font("Arial", 12)
        );


        smallAiCircle2.getChildren().add(
                smallAiIcon2
        );


        VBox resultBox = new VBox();

        resultBox.setSpacing(7);

        resultBox.setPadding(
                new Insets(10)
        );
        resultBox.setMaxWidth(285);

        resultBox.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 8;"
        );


        Text resultText = new Text(
                "I found 3 great options for avocados in your area:"
        );

        resultText.setFont(
                Font.font("Arial", 11)
        );

        resultText.setFill(
                Color.web("#3E3836")
        );


        // =========================================================
        // SHOP RESULT 1
        // =========================================================

        HBox shop1 = new HBox();

        shop1.setPrefHeight(39);

        shop1.setAlignment(
                Pos.CENTER_LEFT
        );

        shop1.setPadding(
                new Insets(5, 8, 5, 8)
        );

        shop1.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 5;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 5;"
        );


        VBox shop1Info = new VBox();

        shop1Info.setSpacing(2);


        Text shop1Name = new Text(
                "Fresh Market Co."
        );

        shop1Name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );


        Text shop1Distance = new Text(
                "0.5 miles away"
        );

        shop1Distance.setFont(
                Font.font("Arial", 9)
        );

        shop1Distance.setFill(
                Color.web("#625A56")
        );


        shop1Info.getChildren().addAll(
                shop1Name,
                shop1Distance
        );


        Region shop1Space = new Region();

        HBox.setHgrow(
                shop1Space,
                Priority.ALWAYS
        );


        Text shop1Price = new Text(
                "$1.20/ea"
        );

        shop1Price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        shop1Price.setFill(
                Color.web("#A94005")
        );


        shop1.getChildren().addAll(
                shop1Info,
                shop1Space,
                shop1Price
        );


        // =========================================================
        // SHOP RESULT 2
        // =========================================================

        HBox shop2 = new HBox();

        shop2.setPrefHeight(39);

        shop2.setAlignment(
                Pos.CENTER_LEFT
        );

        shop2.setPadding(
                new Insets(5, 8, 5, 8)
        );

        shop2.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 5;" +
                "-fx-border-color: #E2DDE4;" +
                "-fx-border-radius: 5;"
        );


        VBox shop2Info = new VBox();

        shop2Info.setSpacing(2);


        Text shop2Name = new Text(
                "Local Grocer"
        );

        shop2Name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );


        Text shop2Distance = new Text(
                "1.2 miles away"
        );

        shop2Distance.setFont(
                Font.font("Arial", 9)
        );

        shop2Distance.setFill(
                Color.web("#625A56")
        );


        shop2Info.getChildren().addAll(
                shop2Name,
                shop2Distance
        );


        Region shop2Space = new Region();

        HBox.setHgrow(
                shop2Space,
                Priority.ALWAYS
        );


        Text shop2Price = new Text(
                "$1.45/ea"
        );

        shop2Price.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        shop2Price.setFill(
                Color.web("#A94005")
        );


        shop2.getChildren().addAll(
                shop2Info,
                shop2Space,
                shop2Price
        );


        resultBox.getChildren().addAll(
                resultText,
                shop1,
                shop2
        );


        aiMessage2HBox.getChildren().addAll(
                smallAiCircle2,
                resultBox
        );


        chatVBox.getChildren().addAll(
                aiMessage1HBox,
                userMessageHBox,
                aiMessage2HBox
        );


        // =========================================================
        // BOTTOM INPUT AREA
        // =========================================================

        HBox inputOuterHBox = new HBox();

        inputOuterHBox.setPrefHeight(72);

        inputOuterHBox.setAlignment(
                Pos.CENTER
        );
        inputOuterHBox.setMaxWidth(
        Double.MAX_VALUE
        );

        inputOuterHBox.setFillHeight(true);
        

        inputOuterHBox.setPadding(
                new Insets(10, 18, 12, 18)
        );

        inputOuterHBox.setStyle(
                "-fx-border-color: #E7E2E8;" +
                "-fx-border-width: 1 0 0 0;"
        );


        HBox inputHBox = new HBox();

        inputHBox.setPrefHeight(40);

        inputHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        inputHBox.setSpacing(8);
        inputHBox.setMaxWidth(
        Double.MAX_VALUE
        );

        HBox.setHgrow(
                inputHBox,
                Priority.ALWAYS
        );

        inputHBox.setPadding(
                new Insets(0, 8, 0, 10)
        );

        inputHBox.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 22;" +
                "-fx-border-color: #DDD7DF;" +
                "-fx-border-radius: 22;"
        );


        Text attachIcon = new Text(
                "⌕"
        );

        attachIcon.setFont(
                Font.font("Arial", 18)
        );

        attachIcon.setFill(
                Color.web("#5B5552")
        );


        TextField inputField = new TextField();

        inputField.setPromptText(
                "Ask BuyNeX AI..."
        );

        inputField.setFont(
                Font.font("Arial", 11)
        );

        inputField.setPrefHeight(34);

        inputField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;"
        );

        HBox.setHgrow(
                inputField,
                Priority.ALWAYS
        );


        Text micIcon = new Text(
                "♩"
        );

        micIcon.setFont(
                Font.font("Arial", 17)
        );

        micIcon.setFill(
                Color.web("#5B5552")
        );


        Button sendButton = new Button(
                "➤"
        );

        sendButton.setPrefSize(
                29,
                29
        );

        sendButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        sendButton.setTextFill(
                Color.WHITE
        );

        sendButton.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-background-radius: 30;" +
                "-fx-padding: 0;"
        );


        inputHBox.getChildren().addAll(
                attachIcon,
                inputField,
                micIcon,
                sendButton
        );


        inputOuterHBox.getChildren().add(
                inputHBox
        );


        centerVBox.getChildren().addAll(
                headerHBox,
                chatVBox,
                inputOuterHBox
        );


        // =========================================================
        // 3. RIGHT AI CAPABILITIES
        // =========================================================

        VBox rightVBox = new VBox();

        rightVBox.setPrefWidth(270);
        rightVBox.setMinWidth(270);
        rightVBox.setMaxWidth(270);

        rightVBox.setMaxHeight(
                Double.MAX_VALUE
        );

        rightVBox.setPadding(
        new Insets(18, 12, 15, 12)
);

        rightVBox.setSpacing(10);

        rightVBox.setStyle(
        "-fx-background-color: #EBC9B2;" +
        "-fx-border-color: #E4DEE7;" +
        "-fx-border-width: 0 0 0 1;"
        );


        // =========================================================
        // AI CAPABILITIES TITLE
        // =========================================================

        Text capabilitiesTitle = new Text(
                "AI Capabilities"
        );

        capabilitiesTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        17
                )
        );

        capabilitiesTitle.setFill(
                Color.web("#282321")
        );


        // =========================================================
        // SOURCE PRODUCTS CARD
        // =========================================================

        VBox sourceCard = new VBox();

        sourceCard.setPrefHeight(85);
        sourceCard.setMaxWidth(Double.MAX_VALUE);

        sourceCard.setPadding(
                new Insets(10)
        );

        sourceCard.setSpacing(5);

        sourceCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #EEE9EF;" +
                "-fx-border-radius: 8;"
        );


        HBox sourceTitleHBox = new HBox();

        sourceTitleHBox.setSpacing(7);

        sourceTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text sourceIcon = new Text("▢");

        sourceIcon.setFont(
                Font.font("Arial", 14)
        );

        sourceIcon.setFill(
                Color.web("#A94005")
        );


        Text sourceTitle = new Text(
                "Source Products"
        );

        sourceTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        sourceTitle.setFill(
                Color.web("#A94005")
        );


        sourceTitleHBox.getChildren().addAll(
                sourceIcon,
                sourceTitle
        );


        Text sourceText = new Text(
                "\"Find the best price for\n" +
                "avocados nearby.\""
        );

        sourceText.setFont(
                Font.font("Arial", 9)
        );

        sourceText.setFill(
                Color.web("#594C48")
        );


        sourceCard.getChildren().addAll(
                sourceTitleHBox,
                sourceText
        );


        // =========================================================
        // TRACK ORDERS CARD
        // =========================================================

        VBox trackCard = new VBox();

        trackCard.setPrefHeight(85);
        trackCard.setMaxWidth(Double.MAX_VALUE);

        trackCard.setPadding(
                new Insets(10)
        );

        trackCard.setSpacing(5);

        trackCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #EEE9EF;" +
                "-fx-border-radius: 8;"
        );


        HBox trackTitleHBox = new HBox();

        trackTitleHBox.setSpacing(7);

        trackTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text trackIcon = new Text("▣");

        trackIcon.setFont(
                Font.font("Arial", 14)
        );

        trackIcon.setFill(
                Color.web("#A94005")
        );


        Text trackTitle = new Text(
                "Track Orders"
        );

        trackTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        trackTitle.setFill(
                Color.web("#A94005")
        );


        trackTitleHBox.getChildren().addAll(
                trackIcon,
                trackTitle
        );


        Text trackText = new Text(
                "\"Track my current order\n" +
                "from Electronics Hub.\""
        );

        trackText.setFont(
                Font.font("Arial", 9)
        );

        trackText.setFill(
                Color.web("#594C48")
        );


        trackCard.getChildren().addAll(
                trackTitleHBox,
                trackText
        );


        // =========================================================
        // COMPARE SHOPS CARD
        // =========================================================

        VBox compareCard = new VBox();

        compareCard.setPrefHeight(85);
        compareCard.setMaxWidth(Double.MAX_VALUE);

        compareCard.setPadding(
                new Insets(10)
        );

        compareCard.setSpacing(5);

        compareCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #EEE9EF;" +
                "-fx-border-radius: 8;"
        );


        HBox compareTitleHBox = new HBox();

        compareTitleHBox.setSpacing(7);

        compareTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text compareIcon = new Text("↔");

        compareIcon.setFont(
                Font.font("Arial", 15)
        );

        compareIcon.setFill(
                Color.web("#A94005")
        );


        Text compareTitle = new Text(
                "Compare Shops"
        );

        compareTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        compareTitle.setFill(
                Color.web("#A94005")
        );


        compareTitleHBox.getChildren().addAll(
                compareIcon,
                compareTitle
        );


        Text compareText = new Text(
                "\"Compare electronics\n" +
                "shops near me.\""
        );

        compareText.setFont(
                Font.font("Arial", 9)
        );

        compareText.setFill(
                Color.web("#594C48")
        );


        compareCard.getChildren().addAll(
                compareTitleHBox,
                compareText
        );


        // =========================================================
        // LOCAL TRENDS CARD
        // =========================================================

        VBox trendsCard = new VBox();

        trendsCard.setPrefHeight(85);
        trendsCard.setMaxWidth(Double.MAX_VALUE);

        trendsCard.setPadding(
                new Insets(10)
        );

        trendsCard.setSpacing(5);

        trendsCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #EEE9EF;" +
                "-fx-border-radius: 8;"
        );


        HBox trendsTitleHBox = new HBox();

        trendsTitleHBox.setSpacing(7);

        trendsTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text trendsIcon = new Text("▤");

        trendsIcon.setFont(
                Font.font("Arial", 14)
        );

        trendsIcon.setFill(
                Color.web("#A94005")
        );


        Text trendsTitle = new Text(
                "Local Trends"
        );

        trendsTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        trendsTitle.setFill(
                Color.web("#A94005")
        );


        trendsTitleHBox.getChildren().addAll(
                trendsIcon,
                trendsTitle
        );


        Text trendsText = new Text(
                "\"What are the top trending\n" +
                "items in my neighborhood\n" +
                "today?\""
        );

        trendsText.setFont(
                Font.font("Arial", 9)
        );

        trendsText.setFill(
                Color.web("#594C48")
        );


        trendsCard.getChildren().addAll(
                trendsTitleHBox,
                trendsText
        );


        // =========================================================
        // SPACE BEFORE INFO BOX
        // =========================================================

        Region rightSpace = new Region();

        VBox.setVgrow(
                rightSpace,
                Priority.ALWAYS
        );


        // =========================================================
        // AI INFO BOX
        // =========================================================

        VBox infoBox = new VBox();

        infoBox.setPrefHeight(120);

        infoBox.setPadding(
                new Insets(10)
        );

        infoBox.setSpacing(6);

        infoBox.setStyle(
                "-fx-background-color: #FFF7F3;" +
                "-fx-border-color: #F0D5C8;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );


        HBox infoTitleHBox = new HBox();

        infoTitleHBox.setSpacing(7);

        infoTitleHBox.setAlignment(
                Pos.TOP_LEFT
        );


        Text infoIcon = new Text(
                "ⓘ"
        );

        infoIcon.setFont(
                Font.font("Arial", 15)
        );

        infoIcon.setFill(
                Color.web("#A94005")
        );


        Text infoTitle = new Text(
                "The BuyNeX Smart\n" +
                "Assistant"
        );

        infoTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        10
                )
        );

        infoTitle.setFill(
                Color.web("#594C48")
        );


        infoTitleHBox.getChildren().addAll(
                infoIcon,
                infoTitle
        );


        Text infoText = new Text(
                "uses real-time local data to\n" +
                "provide the most accurate\n" +
                "recommendations."
        );

        infoText.setFont(
                Font.font("Arial", 9)
        );

        infoText.setFill(
                Color.web("#594C48")
        );


        infoBox.getChildren().addAll(
                infoTitleHBox,
                infoText
        );


        // =========================================================
        // ADD RIGHT SIDE COMPONENTS
        // =========================================================

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
        // ADD 3 VBOX INTO ROOT
        // =========================================================

        root.getChildren().addAll(
                leftVBox,
                centerVBox,
                rightVBox
        );


        // =========================================================
        // SCENE
        // =========================================================

        Scene scene = new Scene(root);

        stage.setTitle(
                "BuyNeX - Smart Assistant"
        );

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }

}
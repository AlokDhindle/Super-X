package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

public class ShopkeeperProfile {

    public static Scene profileScene() {

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
                "-fx-background-color: transparent;" +
                "-fx-background-radius: 9px;");

        Button profileIcon = new Button("👤 Profile");
        profileIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-fill: #555555;" +
                "-fx-cursor: hand;" +
                "-fx-background-color: #FF6900;" +
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
        // HEADER ICONS BUTTON ACTIONS
        // ============================================================

        notificationIcon.setOnMouseClicked(e -> {
            System.out.println("Notification Icon clicked");
            Main.primaryStage.setScene(
                    ShopkeeperNotification.notificationScene());
        });

        botIcon.setOnMouseClicked(e -> {
            System.out.println("ChatBot Icon clicked");
            Main.primaryStage.setScene(
                    ShopkeeperChatbot.chatbotScene());
        });

        profileIcon.setOnMouseClicked(e -> {
            System.out.println("Profile Icon clicked");
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
        // CENTER - SHOPKEEPER PROFILE PAGE
        // ================================================================

        VBox centerMain = new VBox();
        centerMain.setSpacing(14);
        centerMain.setPadding(
                new Insets(20, 24, 18, 24));

        centerMain.setStyle(
                "-fx-background-color: #F8F7FC;");

        // ============================================================
        // PROFILE PAGE HEADING
        // ============================================================

        HBox headingBox = new HBox();
        headingBox.setAlignment(Pos.CENTER_LEFT);

        VBox headingTextBox = new VBox();
        headingTextBox.setSpacing(3);

        Text profileTitle = new Text("Shopkeeper Profile");

        profileTitle.setStyle(
                "-fx-font-size: 27px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text profileSubtitle = new Text(
                "Manage your account settings, store details, and security.");

        profileSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #694B3D;");

        headingTextBox.getChildren().addAll(
                profileTitle,
                profileSubtitle);

        Button saveButton = new Button("▣  Save Changes");

        saveButton.setPrefWidth(155);
        saveButton.setPrefHeight(42);

        saveButton.setStyle(
                "-fx-background-color: #B84200;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 22px;" +
                "-fx-cursor: hand;");

        saveButton.setOnMouseEntered(e -> {
            saveButton.setStyle(
                    "-fx-background-color: #963500;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 22px;" +
                    "-fx-cursor: hand;");
        });

        saveButton.setOnMouseExited(e -> {
            saveButton.setStyle(
                    "-fx-background-color: #B84200;" +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 22px;" +
                    "-fx-cursor: hand;");
        });

        saveButton.setOnAction(e -> {
            System.out.println("Profile changes saved successfully.");
        });

        HBox.setHgrow(headingTextBox, Priority.ALWAYS);

        headingBox.getChildren().addAll(
                headingTextBox,
                saveButton);

        // ============================================================
        // MAIN CONTENT AREA
        // ============================================================

        HBox contentArea = new HBox();
        contentArea.setSpacing(18);
        HBox.setHgrow(contentArea, Priority.ALWAYS);

        // ============================================================
        // LEFT PROFILE + SECURITY COLUMN
        // ============================================================

        VBox leftColumn = new VBox();
        leftColumn.setSpacing(14);
        leftColumn.setPrefWidth(665);
        leftColumn.setMaxWidth(665);

        // ============================================================
        // MERCHANT PROFILE CARD
        // ============================================================

        HBox merchantCard = new HBox();
        merchantCard.setSpacing(28);
        merchantCard.setAlignment(Pos.CENTER_LEFT);

        merchantCard.setPadding(
                new Insets(18, 22, 18, 22));

        merchantCard.setPrefHeight(145);

        merchantCard.setStyle(
                "-fx-background-color: #F2F0F5;" +
                "-fx-background-radius: 12px;");

        // Temporary profile image
        StackPane avatarPane = new StackPane();

        Circle avatarCircle = new Circle(
                53,
                Color.web("#E8D9CB"));

        Text avatarText = new Text("👩");

        avatarText.setStyle(
                "-fx-font-size: 42px;");

        avatarPane.getChildren().addAll(
                avatarCircle,
                avatarText);

        VBox merchantInfo = new VBox();
        merchantInfo.setSpacing(3);

        HBox merchantNameRow = new HBox();
        merchantNameRow.setSpacing(12);
        merchantNameRow.setAlignment(Pos.CENTER_LEFT);

        Text merchantName = new Text(
                "BuyNeX Premium\nMerchant");

        merchantName.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text verifiedText = new Text("⚙ Verified");

        verifiedText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #B84200;" +
                "-fx-background-color: #EEDFD7;" +
                "-fx-background-radius: 15px;" +
                "-fx-padding: 5px 10px;");

        merchantNameRow.getChildren().addAll(
                merchantName,
                verifiedText);

        Text branchText = new Text(
                "Downtown Metro Branch");

        branchText.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-fill: #694B3D;");

        merchantInfo.getChildren().addAll(
                merchantNameRow,
                branchText);

        VBox.setVgrow(merchantInfo, Priority.ALWAYS);

        merchantCard.getChildren().addAll(
                avatarPane,
                merchantInfo);

        // ============================================================
        // STORE DETAILS
        // ============================================================

        HBox storeDetails = new HBox();
        storeDetails.setSpacing(12);

        VBox storeIdCard = new VBox();
        storeIdCard.setSpacing(4);
        storeIdCard.setPadding(
                new Insets(11, 15, 11, 15));

        storeIdCard.setPrefHeight(62);

        storeIdCard.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 9px;" +
                "-fx-border-color: #E8E2EA;" +
                "-fx-border-radius: 9px;");

        Text storeIcon = new Text("▣");

        storeIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #B84200;");

        Text storeIdLabel = new Text("STORE ID");

        storeIdLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        Text storeIdValue = new Text("BNX - 9942 - A");

        storeIdValue.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #292929;");

        HBox storeIdContent = new HBox();
        storeIdContent.setSpacing(10);
        storeIdContent.setAlignment(Pos.CENTER_LEFT);

        VBox storeIdText = new VBox(
                1,
                storeIdLabel,
                storeIdValue);

        storeIdContent.getChildren().addAll(
                storeIcon,
                storeIdText);

        storeIdCard.getChildren().add(
                storeIdContent);

        HBox.setHgrow(storeIdCard, Priority.ALWAYS);

        VBox categoryCard = new VBox();
        categoryCard.setSpacing(4);
        categoryCard.setPadding(
                new Insets(11, 15, 11, 15));

        categoryCard.setPrefHeight(62);

        categoryCard.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 9px;" +
                "-fx-border-color: #E8E2EA;" +
                "-fx-border-radius: 9px;");

        Text categoryIcon = new Text("♢");

        categoryIcon.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-fill: #666666;");

        Text categoryLabel = new Text("CATEGORY");

        categoryLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        Text categoryValue = new Text(
                "Electronics & Tech");

        categoryValue.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #292929;");

        HBox categoryContent = new HBox();
        categoryContent.setSpacing(10);
        categoryContent.setAlignment(Pos.CENTER_LEFT);

        VBox categoryText = new VBox(
                1,
                categoryLabel,
                categoryValue);

        categoryContent.getChildren().addAll(
                categoryIcon,
                categoryText);

        categoryCard.getChildren().add(
                categoryContent);

        HBox.setHgrow(categoryCard, Priority.ALWAYS);

        storeDetails.getChildren().addAll(
                storeIdCard,
                categoryCard);

        // ============================================================
        // SECURITY SETTINGS CARD
        // ============================================================

        VBox securityCard = new VBox();
        securityCard.setSpacing(13);

        securityCard.setPadding(
                new Insets(18, 22, 18, 22));

        securityCard.setStyle(
                "-fx-background-color: #F2F0F5;" +
                "-fx-background-radius: 12px;");

        HBox securityHeading = new HBox();
        securityHeading.setSpacing(10);
        securityHeading.setAlignment(Pos.CENTER_LEFT);

        Text shieldIcon = new Text("⬟");

        shieldIcon.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-fill: #B84200;");

        Text securityTitle = new Text(
                "Security Settings");

        securityTitle.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        securityHeading.getChildren().addAll(
                shieldIcon,
                securityTitle);

        // ============================================================
        // PASSWORD ROW
        // ============================================================

        HBox passwordRow = new HBox();

        passwordRow.setAlignment(Pos.CENTER_LEFT);
        passwordRow.setPadding(
                new Insets(11, 14, 11, 14));

        passwordRow.setPrefHeight(65);

        passwordRow.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 9px;");

        Circle passwordCircle = new Circle(
                20,
                Color.web("#EFEAF1"));

        Text passwordIcon = new Text("***");

        passwordIcon.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        StackPane passwordIconPane = new StackPane(
                passwordCircle,
                passwordIcon);

        VBox passwordTextBox = new VBox();
        passwordTextBox.setSpacing(2);

        Text passwordTitle = new Text("Password");

        passwordTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #171717;");

        Text passwordSubTitle = new Text(
                "Last changed 45 days ago");

        passwordSubTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #694B3D;");

        passwordTextBox.getChildren().addAll(
                passwordTitle,
                passwordSubTitle);

        Button changePasswordButton =
                new Button("Change Password");

        changePasswordButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #B84200;" +
                "-fx-font-size: 12px;" +
                "-fx-cursor: hand;");

        changePasswordButton.setOnAction(e -> {
            System.out.println("Change Password clicked");
            Main.primaryStage.setScene(ShopkeeperLoginSendOtp.sendOtpScene());
        });

        HBox.setHgrow(passwordTextBox, Priority.ALWAYS);

        passwordRow.getChildren().addAll(
                passwordIconPane,
                passwordTextBox,
                changePasswordButton);

        // ============================================================
        // TWO FACTOR AUTHENTICATION
        // ============================================================

        HBox twoFactorRow = new HBox();

        twoFactorRow.setAlignment(Pos.CENTER_LEFT);
        twoFactorRow.setPadding(
                new Insets(11, 14, 11, 14));

        twoFactorRow.setPrefHeight(65);

        twoFactorRow.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 9px;");

        Circle twoFactorCircle = new Circle(
                20,
                Color.web("#F1E1DA"));

        Text twoFactorIcon = new Text("▣");

        twoFactorIcon.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-fill: #B84200;");

        StackPane twoFactorIconPane =
                new StackPane(
                        twoFactorCircle,
                        twoFactorIcon);

        VBox twoFactorTextBox = new VBox();
        twoFactorTextBox.setSpacing(2);

        Text twoFactorTitle = new Text(
                "Two-Factor Authentication");

        twoFactorTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #171717;");

        Text twoFactorSubTitle = new Text(
                "Adds an extra layer of security to your account");

        twoFactorSubTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #694B3D;");

        twoFactorTextBox.getChildren().addAll(
                twoFactorTitle,
                twoFactorSubTitle);

        ToggleButton twoFactorToggle =
                new ToggleButton("ON");

        twoFactorToggle.setSelected(true);

        twoFactorToggle.setPrefWidth(47);
        twoFactorToggle.setPrefHeight(26);

        twoFactorToggle.setStyle(
                "-fx-background-color: #B84200;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 20px;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;");

        twoFactorToggle.setOnAction(e -> {

            if (twoFactorToggle.isSelected()) {

                twoFactorToggle.setText("ON");

                twoFactorToggle.setStyle(
                        "-fx-background-color: #B84200;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-font-size: 10px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;");

            } else {

                twoFactorToggle.setText("OFF");

                twoFactorToggle.setStyle(
                        "-fx-background-color: #BDB7B3;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 20px;" +
                        "-fx-font-size: 9px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;");
            }
        });

        HBox.setHgrow(twoFactorTextBox, Priority.ALWAYS);

        twoFactorRow.getChildren().addAll(
                twoFactorIconPane,
                twoFactorTextBox,
                twoFactorToggle);

        // ============================================================
        // DELETE ACCOUNT
        // ============================================================

        HBox deleteRow = new HBox();

        deleteRow.setAlignment(Pos.CENTER_LEFT);
        deleteRow.setPadding(
                new Insets(11, 14, 11, 14));

        deleteRow.setPrefHeight(70);

        deleteRow.setStyle(
                "-fx-background-color: #FAF0F1;" +
                "-fx-background-radius: 9px;" +
                "-fx-border-color: #E8B7B8;" +
                "-fx-border-radius: 9px;");

        Circle deleteCircle = new Circle(
                20,
                Color.web("#F8DADC"));

        Text deleteIcon = new Text("▣");

        deleteIcon.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-fill: #D31313;");

        StackPane deleteIconPane =
                new StackPane(
                        deleteCircle,
                        deleteIcon);

        VBox deleteTextBox = new VBox();
        deleteTextBox.setSpacing(2);

        Text deleteTitle = new Text(
                "Delete Account");

        deleteTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #171717;");

        Text deleteSubTitle = new Text(
                "Permanently remove your store and all data");

        deleteSubTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #694B3D;");

        deleteTextBox.getChildren().addAll(
                deleteTitle,
                deleteSubTitle);

        Button deleteButton =
                new Button("Delete\nForever");

        deleteButton.setPrefWidth(120);
        deleteButton.setPrefHeight(47);

        deleteButton.setStyle(
                "-fx-background-color: #CE1515;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 25px;" +
                "-fx-cursor: hand;");

        deleteButton.setOnAction(e -> {
            System.out.println(
                    "Delete Account clicked");
        });

        HBox.setHgrow(deleteTextBox, Priority.ALWAYS);

        deleteRow.getChildren().addAll(
                deleteIconPane,
                deleteTextBox,
                deleteButton);

        securityCard.getChildren().addAll(
                securityHeading,
                passwordRow,
                twoFactorRow,
                deleteRow);

        leftColumn.getChildren().addAll(
                merchantCard,
                storeDetails,
                securityCard);

        // ============================================================
        // RIGHT COLUMN
        // ============================================================

        VBox rightColumn = new VBox();

        rightColumn.setSpacing(14);
        rightColumn.setPrefWidth(285);
        rightColumn.setMaxWidth(285);

        // ============================================================
        // ACCOUNT VERIFIED CARD
        // ============================================================

        HBox verifiedCard = new HBox();

        verifiedCard.setSpacing(13);
        verifiedCard.setAlignment(Pos.CENTER_LEFT);

        verifiedCard.setPadding(
                new Insets(16, 15, 16, 15));

        verifiedCard.setPrefHeight(100);

        verifiedCard.setStyle(
                "-fx-background-color: #F2F0F5;" +
                "-fx-background-radius: 11px;" +
                "-fx-border-color: #2D982E;" +
                "-fx-border-width: 0 0 0 4px;" +
                "-fx-border-radius: 11px;");

        Circle verifiedCircle = new Circle(
                18,
                Color.web("#DDEADB"));

        Text verifiedIcon = new Text("✓");

        verifiedIcon.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #198A24;");

        StackPane verifiedIconPane =
                new StackPane(
                        verifiedCircle,
                        verifiedIcon);

        VBox verifiedTextBox = new VBox();
        verifiedTextBox.setSpacing(4);

        Text verifiedTitle = new Text(
                "Account Verified");

        verifiedTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text verifiedDescription = new Text(
                "Business Identity Confirmed.\n" +
                "Your store is fully visible to\n" +
                "customers.");

        verifiedDescription.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #694B3D;");

        verifiedTextBox.getChildren().addAll(
                verifiedTitle,
                verifiedDescription);

        verifiedCard.getChildren().addAll(
                verifiedIconPane,
                verifiedTextBox);

        // ============================================================
        // CURRENT PLAN CARD
        // ============================================================

        VBox planCard = new VBox();

        planCard.setSpacing(8);

        planCard.setPadding(
                new Insets(16, 18, 16, 18));

        planCard.setPrefHeight(190);

        planCard.setStyle(
                "-fx-background-color: #F5EEEC;" +
                "-fx-background-radius: 11px;");

        HBox planHeading = new HBox();

        planHeading.setAlignment(
                Pos.CENTER_LEFT);

        Text currentPlan = new Text(
                "CURRENT PLAN");

        currentPlan.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        Text planIcon = new Text("♙");

        planIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #B84200;");

        HBox.setHgrow(currentPlan, Priority.ALWAYS);

        planHeading.getChildren().addAll(
                currentPlan,
                planIcon);

        Text proText = new Text("Pro");

        proText.setStyle(
                "-fx-font-size: 38px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text subscriptionText = new Text(
                "Merchant Pro Subscription");

        subscriptionText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #694B3D;");

        HBox renewalBox = new HBox();

        renewalBox.setAlignment(
                Pos.CENTER_LEFT);

        renewalBox.setPadding(
                new Insets(9, 11, 9, 11));

        renewalBox.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-background-radius: 8px;");

        VBox renewal = new VBox(2);

        Text renewalLabel = new Text("Renews On");

        renewalLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        Text renewalDate = new Text(
                "Nov 15, 2024");

        renewalDate.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #171717;");

        renewal.getChildren().addAll(
                renewalLabel,
                renewalDate);

        VBox amount = new VBox(2);

        amount.setAlignment(
                Pos.CENTER_RIGHT);

        Text amountLabel = new Text("Amount");

        amountLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        Text amountValue = new Text("$49/mo");

        amountValue.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #171717;");

        amount.getChildren().addAll(
                amountLabel,
                amountValue);

        HBox.setHgrow(renewal, Priority.ALWAYS);

        renewalBox.getChildren().addAll(
                renewal,
                amount);

        Button managePlanButton =
                new Button("Manage Plan");

        managePlanButton.setMaxWidth(
                Double.MAX_VALUE);

        managePlanButton.setPrefHeight(38);

        managePlanButton.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-text-fill: #B84200;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;");

        managePlanButton.setOnAction(e -> {
            System.out.println(
                    "Manage Plan clicked");
        });

        planCard.getChildren().addAll(
                planHeading,
                proText,
                subscriptionText,
                renewalBox,
                managePlanButton);

        // ============================================================
        // MEMBER SINCE + ACCOUNT HEALTH
        // ============================================================

        HBox smallCards = new HBox();
        smallCards.setSpacing(10);

        VBox memberCard = new VBox();

        memberCard.setAlignment(
                Pos.CENTER);

        memberCard.setSpacing(4);

        memberCard.setPadding(
                new Insets(10));

        memberCard.setPrefHeight(115);

        memberCard.setStyle(
                "-fx-background-color: #F2F0F5;" +
                "-fx-background-radius: 10px;");

        Text memberIcon = new Text("▣");

        memberIcon.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-fill: #555555;");

        Text memberLabel = new Text(
                "MEMBER\nSINCE");

        memberLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;" +
                "-fx-text-alignment: center;");

        Text memberDate = new Text(
                "Oct '23");

        memberDate.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        memberCard.getChildren().addAll(
                memberIcon,
                memberLabel,
                memberDate);

        VBox healthCard = new VBox();

        healthCard.setAlignment(
                Pos.CENTER);

        healthCard.setSpacing(4);

        healthCard.setPadding(
                new Insets(10));

        healthCard.setPrefHeight(115);

        healthCard.setStyle(
                "-fx-background-color: #F2F0F5;" +
                "-fx-background-radius: 10px;");

        Text healthIcon = new Text("♙");

        healthIcon.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-fill: #555555;");

        Text healthLabel = new Text(
                "ACCOUNT\nHEALTH");

        healthLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;" +
                "-fx-text-alignment: center;");

        Text healthValue = new Text(
                "98%");

        healthValue.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;");

        Text ratingText = new Text(
                "Positive Rating");

        ratingText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #694B3D;");

        healthCard.getChildren().addAll(
                healthIcon,
                healthLabel,
                healthValue,
                ratingText);

        HBox.setHgrow(memberCard, Priority.ALWAYS);
        HBox.setHgrow(healthCard, Priority.ALWAYS);

        smallCards.getChildren().addAll(
                memberCard,
                healthCard);

        // ============================================================
        // SECURE LOGOUT BUTTON
        // ============================================================

        Button secureLogoutButton =
                new Button("⇥    Secure Log Out");

        secureLogoutButton.setMaxWidth(
                Double.MAX_VALUE);

        secureLogoutButton.setPrefHeight(52);

        secureLogoutButton.setStyle(
                "-fx-background-color: #FCFAFD;" +
                "-fx-text-fill: #C41414;" +
                "-fx-font-size: 13px;" +
                "-fx-border-color: #E6B8B9;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-cursor: hand;");

        secureLogoutButton.setOnAction(e -> {
            System.out.println(
                    "Secure Log Out clicked");
                    Main.primaryStage.setScene(ShopkeeperLogin.loginscene());
        });

        rightColumn.getChildren().addAll(
                verifiedCard,
                planCard,
                smallCards,
                secureLogoutButton);

        // ============================================================
        // ADD COLUMNS
        // ============================================================

        contentArea.getChildren().addAll(
                leftColumn,
                rightColumn);

        // ============================================================
        // CENTER MAIN CONTENT
        // ============================================================

        centerMain.getChildren().addAll(
                headingBox,
                contentArea);

        VBox.setVgrow(contentArea, Priority.ALWAYS);

        // ============================================================
        // SCROLL PANE
        // ============================================================

        ScrollPane centerScroll = new ScrollPane(
                centerMain);

        centerScroll.setFitToWidth(true);
        centerScroll.setFitToHeight(true);

        centerScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER);

        centerScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED);

        centerScroll.setStyle(
                "-fx-background-color: #F8F7FC;" +
                "-fx-border-color: transparent;");

        borderPane.setCenter(centerScroll);

        // ================================================================
        // FOOTER
        // ================================================================

        VBox footerBox = Constants.footer();

        borderPane.setBottom(footerBox);

        // ============================================================
        // SCENE
        // ============================================================

        Scene profileScene = new Scene(
                borderPane,
                1280,
                650);

        profileScene.setFill(
                Color.web("#F8F7FC"));

        return profileScene;
    }
}
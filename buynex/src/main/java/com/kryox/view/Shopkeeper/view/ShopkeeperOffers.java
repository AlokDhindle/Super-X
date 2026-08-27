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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShopkeeperOffers {

        public static Scene offersScene() {

                // ============================================================
                // BORDER PANE
                // ============================================================

                BorderPane borderPane = new BorderPane();

                // ================================================================
                // MAIN BORDER PANE
                // ================================================================

                HBox headerMainBox = ViewConstants.header();
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

                HBox profileBox = ViewConstants.letfProfileBox();
                profileBox.setAlignment(Pos.CENTER_LEFT);
                profileBox.setPadding(new Insets(30, 20, 30, 20));

                // ============================================================
                // DASHBOARD BUTTON
                // ============================================================

                Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", false);

                // ============================================================
                // ORDERS BUTTON
                // ============================================================

                Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", false);

                // ============================================================
                // INVENTORY BUTTON
                // ============================================================

                Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);

                // ============================================================
                // OFFERS BUTTON
                // ============================================================

                Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", true);

                // ============================================================
                // ANALYTICS BUTTON
                // ============================================================

                Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);

                // ============================================================
                // SETTINGS BUTTON
                // ============================================================

                Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", false);

                // ============================================================
                // SUPPORT BUTTON
                // ============================================================

                Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

                VBox sidebarMenu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                sidebarMenu.setPadding(new Insets(0, 8, 0, 8));

                // ============================================================
                // LOGOUT BUTTON
                // ============================================================

                VBox logoutBox = ViewConstants.logoutBox();

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
                        Main.primaryStage.setScene(ShopkeeperDashboard.dashboardScene());

                });

                ordersButton.setOnAction(e -> {

                        System.out.println("Orders clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderReady.ordersScene());

                });

                inventoryButton.setOnAction(e -> {

                        System.out.println("Inventory clicked");
                        Main.primaryStage.setScene(ShopkeeperInventory.inventoryScene());

                });

                offersButton.setOnAction(e -> {

                        System.out.println("Offers clicked");
                });

                analyticsButton.setOnAction(e -> {

                        System.out.println("Analytics clicked");
                        Main.primaryStage.setScene(ShopkeeperAnalytics.analyticsScene());

                });

                settingsButton.setOnAction(e -> {

                        System.out.println("Settings clicked");
                        Main.primaryStage.setScene(ShopkeeperSettings.settingsScene());

                });

                supportButton.setOnAction(e -> {

                        System.out.println("Support clicked");
                        Main.primaryStage.setScene(ShopkeeperSupport.supportScene());

                });

                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();
                // SET BOTTOM
                borderPane.setBottom(footerBox);

                // ============================================================
                // MAIN TITLE
                // ============================================================

                Text offersTitle = new Text(
                                "Offers & Promotions");

                offersTitle.setStyle(
                                "-fx-font-size: 31px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #171717;");

                Text offersSubtitle = new Text(
                                "Manage active deals and discover new opportunities.");

                offersSubtitle.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #666666;");

                VBox titleBox = new VBox(
                                5,
                                offersTitle,
                                offersSubtitle);

                // ============================================================
                // CREATE NEW OFFER BUTTON
                // ============================================================

                Button createOfferButton = new Button(
                                "⊕  Create New Offer");

                createOfferButton.setPrefWidth(211);

                createOfferButton.setPrefHeight(40);

                createOfferButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9C72);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-cursor: hand;");

                HBox topTitleBox = new HBox(
                                titleBox,
                                createOfferButton);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                topTitleBox.setAlignment(
                                Pos.CENTER_LEFT);

                // ============================================================
                // AI RECOMMENDATION CARD
                // ============================================================

                Circle aiCircle = new Circle(27);

                aiCircle.setFill(
                                Color.web("#FFE4D5"));

                Text aiIcon = new Text("✦");

                aiIcon.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-fill: #FF6900;" +
                                                "-fx-font-weight: bold;");

                StackPane aiIconBox = new StackPane(
                                aiCircle,
                                aiIcon);

                Text aiTitle = new Text(
                                "AI Recommendation");

                aiTitle.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #181818;");

                Text aiDescription = new Text(
                                "Suggesting 15% off on Groceries for Sunday morning rush. High\n" +
                                                "probability of increasing basket size based on local search trends.");

                aiDescription.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: #666666;");

                Button applyDealButton = new Button(
                                "Apply Deal");

                applyDealButton.setPrefWidth(116);

                applyDealButton.setPrefHeight(40);

                applyDealButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-text-fill: #222222;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20px;" +
                                                "-fx-cursor: hand;");

                Button dismissButton = new Button(
                                "Dismiss");

                dismissButton.setPrefWidth(94);

                dismissButton.setPrefHeight(40);

                dismissButton.setStyle(
                                "-fx-background-color: #E5E3E6;" +
                                                "-fx-text-fill: #444444;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 20px;" +
                                                "-fx-cursor: hand;");

                HBox aiButtons = new HBox(
                                12,
                                applyDealButton,
                                dismissButton);

                VBox aiTextBox = new VBox(
                                7,
                                aiTitle,
                                aiDescription,
                                aiButtons);

                HBox aiRecommendation = new HBox(
                                18,
                                aiIconBox,
                                aiTextBox);

                aiRecommendation.setAlignment(
                                Pos.TOP_LEFT);

                aiRecommendation.setPadding(
                                new Insets(24));

                aiRecommendation.setPrefHeight(175);

                aiRecommendation.setStyle(
                                "-fx-background-color: #FFF9F7;" +
                                                "-fx-border-color: #FF6900;" +
                                                "-fx-border-width: 2px 0 0 0;" +
                                                "-fx-border-radius: 18px;" +
                                                "-fx-background-radius: 18px;");

                DropShadow aiShadow = new DropShadow();

                aiShadow.setRadius(10);

                aiShadow.setSpread(0.02);

                aiShadow.setColor(
                                Color.rgb(80, 60, 50, 0.08));

                aiRecommendation.setEffect(
                                aiShadow);

                // ============================================================
                // ACTIVE OFFERS TITLE
                // ============================================================

                Text activeOffersTitle = new Text(
                                "Active Offers");

                activeOffersTitle.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #181818;");

                // ============================================================
                // OFFER CARD 1
                // ============================================================

                StackPane flashIconBox = new StackPane();

                flashIconBox.setPrefWidth(69);

                flashIconBox.setPrefHeight(69);

                flashIconBox.setStyle(
                                "-fx-background-color: #E5E4E8;" +
                                                "-fx-background-radius: 9px;");

                Text flashIcon = new Text("ϟ");

                flashIcon.setStyle(
                                "-fx-font-size: 34px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #FF6900;");

                flashIconBox.getChildren().add(
                                flashIcon);

                Text flashSaleTitle = new Text(
                                "10% Flash Sale");

                flashSaleTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                Text storewideTag = new Text(
                                "STOREWIDE");

                storewideTag.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #444444;" +
                                                "-fx-background-color: #E8E6EA;" +
                                                "-fx-background-radius: 10px;");

                HBox flashTitleRow = new HBox(
                                10,
                                flashSaleTitle,
                                storewideTag);

                flashTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                Text flashDescription = new Text(
                                "Ends in 4 hours • 124 Claims");

                flashDescription.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #666666;");

                VBox flashTextBox = new VBox(
                                7,
                                flashTitleRow,
                                flashDescription);

                VBox.setVgrow(
                                flashTextBox,
                                Priority.ALWAYS);

                Text flashStatusText = new Text(
                                "Status");

                flashStatusText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");

                ToggleButton flashToggle = new ToggleButton();

                flashToggle.setSelected(true);

                flashToggle.setPrefWidth(48);

                flashToggle.setPrefHeight(27);

                flashToggle.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 20px;" +
                                                "-fx-cursor: hand;");

                Text flashViewStats = new Text(
                                "View Stats");

                flashViewStats.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #FF6900;" +
                                                "-fx-font-weight: bold;");

                VBox flashStatusBox = new VBox(
                                7,
                                new HBox(
                                                8,
                                                flashStatusText,
                                                flashToggle),
                                flashViewStats);

                flashStatusBox.setAlignment(
                                Pos.CENTER_RIGHT);

                HBox flashOfferCard = new HBox(
                                20,
                                flashIconBox,
                                flashTextBox,
                                flashStatusBox);

                HBox.setHgrow(
                                flashTextBox,
                                Priority.ALWAYS);

                flashOfferCard.setAlignment(
                                Pos.CENTER_LEFT);

                flashOfferCard.setPadding(
                                new Insets(20));

                flashOfferCard.setPrefHeight(112);

                flashOfferCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0DDE2;" +
                                                "-fx-border-radius: 18px;" +
                                                "-fx-background-radius: 18px;");

                // ============================================================
                // OFFER CARD 2
                // ============================================================

                StackPane avocadoImageBox = new StackPane();

                avocadoImageBox.setPrefWidth(69);

                avocadoImageBox.setPrefHeight(69);

                avocadoImageBox.setStyle(
                                "-fx-background-color: #E7F0E5;" +
                                                "-fx-background-radius: 9px;");

                Text avocadoIcon = new Text(
                                "🥑");

                avocadoIcon.setStyle(
                                "-fx-font-size: 38px;");

                avocadoImageBox.getChildren().add(
                                avocadoIcon);

                Text avocadoTitle = new Text(
                                "Organic Avocados BOGO");

                avocadoTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                Text produceTag = new Text(
                                "PRODUCE");

                produceTag.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #444444;" +
                                                "-fx-background-color: #E8E6EA;" +
                                                "-fx-background-radius: 10px;");

                HBox avocadoTitleRow = new HBox(
                                10,
                                avocadoTitle,
                                produceTag);

                avocadoTitleRow.setAlignment(
                                Pos.CENTER_LEFT);

                Text avocadoDescription = new Text(
                                "Valid until Friday • 89 Claims");

                avocadoDescription.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #666666;");

                VBox avocadoTextBox = new VBox(
                                7,
                                avocadoTitleRow,
                                avocadoDescription);

                HBox.setHgrow(
                                avocadoTextBox,
                                Priority.ALWAYS);

                Text avocadoStatusText = new Text(
                                "Status");

                avocadoStatusText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");

                ToggleButton avocadoToggle = new ToggleButton();

                avocadoToggle.setSelected(true);

                avocadoToggle.setPrefWidth(48);

                avocadoToggle.setPrefHeight(27);

                avocadoToggle.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 20px;" +
                                                "-fx-cursor: hand;");

                Text avocadoViewStats = new Text(
                                "View Stats");

                avocadoViewStats.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #FF6900;" +
                                                "-fx-font-weight: bold;");

                VBox avocadoStatusBox = new VBox(
                                7,
                                new HBox(
                                                8,
                                                avocadoStatusText,
                                                avocadoToggle),
                                avocadoViewStats);

                avocadoStatusBox.setAlignment(
                                Pos.CENTER_RIGHT);

                HBox avocadoOfferCard = new HBox(
                                20,
                                avocadoImageBox,
                                avocadoTextBox,
                                avocadoStatusBox);

                avocadoOfferCard.setAlignment(
                                Pos.CENTER_LEFT);

                avocadoOfferCard.setPadding(
                                new Insets(20));

                avocadoOfferCard.setPrefHeight(112);

                avocadoOfferCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0DDE2;" +
                                                "-fx-border-radius: 18px;" +
                                                "-fx-background-radius: 18px;");

                // ============================================================
                // ACTIVE OFFERS CONTENT
                // ============================================================

                VBox activeOffersBox = new VBox(
                                14,
                                activeOffersTitle,
                                flashOfferCard,
                                avocadoOfferCard);

                // ============================================================
                // LEFT MAIN CONTENT
                // ============================================================

                VBox leftMainContent = new VBox(
                                22,
                                topTitleBox,
                                aiRecommendation,
                                activeOffersBox);

                leftMainContent.setPrefWidth(
                                680);

                // ============================================================
                // PROMOTIONS INSIGHT CARD
                // ============================================================

                Text promotionInsightTitle = new Text(
                                "Promotions Insight");

                promotionInsightTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #181818;");

                Text roiLabel = new Text(
                                "Est. ROI this week");

                roiLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #666666;");

                Text roiValue = new Text(
                                "+24%");

                roiValue.setStyle(
                                "-fx-font-size: 31px;" +
                                                "-fx-fill: #F4770A;");

                Rectangle roiProgressBackground = new Rectangle(
                                290,
                                8);

                roiProgressBackground.setArcWidth(8);

                roiProgressBackground.setArcHeight(8);

                roiProgressBackground.setFill(
                                Color.web("#E7E5E9"));

                Rectangle roiProgress = new Rectangle(
                                220,
                                8);

                roiProgress.setArcWidth(8);

                roiProgress.setArcHeight(8);

                roiProgress.setFill(
                                Color.web("#FF6900"));

                StackPane roiProgressBox = new StackPane(
                                roiProgressBackground,
                                roiProgress);

                roiProgressBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text engagementText = new Text(
                                "Customer Engagement");

                engagementText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");

                Rectangle bar1 = new Rectangle(51, 40);

                Rectangle bar2 = new Rectangle(51, 62);

                Rectangle bar3 = new Rectangle(51, 92);

                Rectangle bar4 = new Rectangle(51, 51);

                Rectangle bar5 = new Rectangle(51, 77);

                bar1.setFill(Color.web("#DDDBE0"));
                bar2.setFill(Color.web("#DDDBE0"));
                bar3.setFill(Color.web("#FF9C5C"));
                bar4.setFill(Color.web("#DDDBE0"));
                bar5.setFill(Color.web("#DDDBE0"));

                HBox engagementChart = new HBox(
                                9,
                                bar1,
                                bar2,
                                bar3,
                                bar4,
                                bar5);

                engagementChart.setAlignment(
                                Pos.BOTTOM_CENTER);

                VBox chartBox = new VBox(
                                8,
                                engagementChart);

                chartBox.setPrefHeight(100);

                chartBox.setAlignment(
                                Pos.BOTTOM_CENTER);

                Text chartCaption = new Text(
                                "Peak claims usually occur on Wednesdays");

                chartCaption.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #666666;");

                VBox promotionInsightCard = new VBox(
                                18,
                                promotionInsightTitle,
                                roiLabel,
                                roiValue,
                                roiProgressBox,
                                engagementText,
                                chartBox,
                                chartCaption);

                promotionInsightCard.setPadding(
                                new Insets(25));

                promotionInsightCard.setPrefWidth(
                                315);

                promotionInsightCard.setPrefHeight(
                                385);

                promotionInsightCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0DDE2;" +
                                                "-fx-border-radius: 20px;" +
                                                "-fx-background-radius: 20px;");

                // ============================================================
                // LOYALTY PROGRAM CARD
                // ============================================================

                Text loyaltyTitle = new Text(
                                "Loyalty Program");

                loyaltyTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #181818;");

                Text loyaltyMembers = new Text(
                                "450 members active.");

                loyaltyMembers.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #666666;");

                Button manageRewardsButton = new Button(
                                "Manage Rewards");

                manageRewardsButton.setPrefWidth(
                                270);

                manageRewardsButton.setPrefHeight(
                                42);

                manageRewardsButton.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-text-fill: #222222;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: #80695D;" +
                                                "-fx-border-width: 2px;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-cursor: hand;");

                VBox loyaltyCard = new VBox(
                                15,
                                loyaltyTitle,
                                loyaltyMembers,
                                manageRewardsButton);

                loyaltyCard.setPadding(
                                new Insets(25));

                loyaltyCard.setPrefWidth(
                                315);

                loyaltyCard.setPrefHeight(
                                170);

                loyaltyCard.setAlignment(
                                Pos.TOP_LEFT);

                loyaltyCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-border-color: #E0DDE2;" +
                                                "-fx-border-radius: 20px;" +
                                                "-fx-background-radius: 20px;");

                // ============================================================
                // RIGHT CONTENT
                // ============================================================

                VBox rightContent = new VBox(
                                22,
                                promotionInsightCard,
                                loyaltyCard);

                rightContent.setPrefWidth(
                                315);

                // ============================================================
                // MAIN CONTENT
                // ============================================================

                HBox mainContent = new HBox(
                                25,
                                leftMainContent,
                                rightContent);

                mainContent.setAlignment(
                                Pos.TOP_CENTER);

                mainContent.setPadding(
                                new Insets(25));

                // ============================================================
                // SCROLL PANE
                // ============================================================

                ScrollPane scrollPane = new ScrollPane(
                                mainContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: #F8F7FC;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                scrollPane);

                // ============================================================
                // BUTTON ACTIONS
                // ============================================================

                createOfferButton.setOnAction(e -> {

                        System.out.println(
                                        "Create New Offer clicked");
                        Main.primaryStage.setScene(ShopkeeperOffersCreateNew.createNewOfferScene());
                });

                applyDealButton.setOnAction(e -> {

                        System.out.println(
                                        "Apply Deal clicked");
                });

                dismissButton.setOnAction(e -> {

                        aiRecommendation.setVisible(false);

                        aiRecommendation.setManaged(false);
                });

                flashViewStats.setOnMouseClicked(e -> {

                        System.out.println(
                                        "Flash Sale statistics clicked");
                });

                avocadoViewStats.setOnMouseClicked(e -> {

                        System.out.println(
                                        "Avocado offer statistics clicked");
                });

                manageRewardsButton.setOnAction(e -> {

                        System.out.println(
                                        "Manage Rewards clicked");
                });

                // ============================================================
                // SCENE
                // ============================================================

                Scene offersScene = new Scene(
                                borderPane,
                                1280,
                                650);

                offersScene.setFill(
                                Color.web("#F8F7FC"));

                return offersScene;
        }
}
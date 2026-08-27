package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperOffersCreateNew {

        public static Scene createNewOfferScene() {

                // ================================================================
                // BORDER PANE
                // ================================================================

                BorderPane borderPane = new BorderPane();

                // ================================================================
                // HEADER
                // SAME HEADER AS SHOPKEEPER OFFERS
                // ================================================================

                HBox headerMainBox = ViewConstants.header();

                borderPane.setTop(headerMainBox);

                // ================================================================
                // LEFT SIDEBAR
                // SAME AS SHOPKEEPER OFFERS
                // ================================================================

                VBox sidebar = new VBox();

                sidebar.setMinWidth(260);
                sidebar.setMaxWidth(260);

                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                // ================================================================
                // PROFILE
                // ================================================================

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(30, 20, 30, 20));

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

                // ================================================================
                // SIDEBAR MENU
                // ================================================================

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

                VBox.setVgrow(
                                sidebarMenu,
                                Priority.ALWAYS);

                // ================================================================
                // LOGOUT
                // ================================================================

                VBox logoutBox = ViewConstants.logoutBox();

                sidebar.getChildren().addAll(
                                profileBox,
                                sidebarMenu,
                                logoutBox);

                borderPane.setLeft(sidebar);

                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ================================================================
                // ================= CENTER =================
                // ================================================================

                VBox centerContent = new VBox();

                centerContent.setPadding(
                                new Insets(18, 28, 20, 28));

                centerContent.setSpacing(12);

                centerContent.setStyle(
                                "-fx-background-color: #F8F7FC;");

                // ================================================================
                // PAGE TITLE
                // ================================================================

                Text pageTitle = new Text("Create New Offer");

                pageTitle.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                Text pageSubtitle = new Text(
                                "Design a new promotional campaign to drive sales and reward customers.");

                pageSubtitle.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-fill: #6B5C55;");

                VBox titleBox = new VBox(
                                3,
                                pageTitle,
                                pageSubtitle);

                // ================================================================
                // BASIC INFORMATION CARD
                // ================================================================

                VBox basicCard = new VBox();

                basicCard.setPadding(
                                new Insets(22));

                basicCard.setSpacing(15);

                basicCard.setPrefWidth(570);
                basicCard.setMinWidth(570);
                basicCard.setMaxWidth(570);

                basicCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;");

                // ================================================================
                // BASIC INFORMATION HEADING
                // ================================================================

                Text infoIcon = new Text("ⓘ");

                infoIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #A94408;");

                Text infoTitle = new Text("Basic Information");

                infoTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                HBox infoHeading = new HBox(
                                10,
                                infoIcon,
                                infoTitle);

                infoHeading.setAlignment(
                                Pos.CENTER_LEFT);

                // ================================================================
                // OFFER NAME
                // ================================================================

                Text offerNameLabel = new Text("Offer Name");

                offerNameLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #493B35;");

                TextField offerNameField = new TextField();

                offerNameField.setPromptText(
                                "e.g. Summer Mega Sale");

                offerNameField.setPrefHeight(38);

                offerNameField.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox offerNameBox = new VBox(
                                5,
                                offerNameLabel,
                                offerNameField);

                // ================================================================
                // PROMOTION CODE
                // ================================================================

                Text promoLabel = new Text("Promotion Code");

                promoLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #493B35;");

                TextField promoField = new TextField();

                promoField.setPromptText(
                                "E.G.");

                promoField.setPrefHeight(38);

                promoField.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                Button autoGenerate = new Button("Auto-generate");

                autoGenerate.setPrefHeight(30);

                autoGenerate.setStyle(
                                "-fx-background-color: #E8DAD4;" +
                                                "-fx-text-fill: #A94408;" +
                                                "-fx-font-size: 10px;" +
                                                "-fx-background-radius: 5px;" +
                                                "-fx-cursor: hand;");

                HBox promoRow = new HBox(
                                5,
                                promoField,
                                autoGenerate);

                promoRow.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                promoField,
                                Priority.ALWAYS);

                VBox promoBox = new VBox(
                                5,
                                promoLabel,
                                promoRow);

                // ================================================================
                // DESCRIPTION
                // ================================================================

                Text descriptionLabel = new Text("Description (Optional)");

                descriptionLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #493B35;");

                TextField descriptionField = new TextField();

                descriptionField.setPromptText(
                                "Short description for internal use");

                descriptionField.setPrefHeight(38);

                descriptionField.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                VBox descriptionBox = new VBox(
                                5,
                                descriptionLabel,
                                descriptionField);

                HBox codeRow = new HBox(
                                18,
                                promoBox,
                                descriptionBox);

                HBox.setHgrow(
                                promoBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                descriptionBox,
                                Priority.ALWAYS);

                // ================================================================
                // OFFER TYPE & VALUE
                // ================================================================

                Text typeIcon = new Text("◇");

                typeIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #A94408;");

                Text typeTitle = new Text("Offer Type & Value");

                typeTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                HBox typeHeading = new HBox(
                                10,
                                typeIcon,
                                typeTitle);

                typeHeading.setAlignment(
                                Pos.CENTER_LEFT);

                ToggleGroup typeGroup = new ToggleGroup();

                RadioButton percentage = new RadioButton();

                percentage.setToggleGroup(
                                typeGroup);

                percentage.setSelected(true);

                RadioButton flatAmount = new RadioButton();

                flatAmount.setToggleGroup(
                                typeGroup);

                // ================================================================
                // PERCENTAGE CARD
                // ================================================================

                Text percentageTitle = new Text("Percentage Discount");

                percentageTitle.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252020;");

                Text percentageDesc = new Text(
                                "Discount based on\norder %");

                percentageDesc.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #5E504A;");

                VBox percentageText = new VBox(
                                3,
                                percentageTitle,
                                percentageDesc);

                HBox percentageContent = new HBox(
                                10,
                                percentage,
                                percentageText);

                percentageContent.setAlignment(
                                Pos.TOP_LEFT);

                StackPane percentageCard = new StackPane(
                                percentageContent);

                percentageCard.setPrefWidth(215);
                percentageCard.setPrefHeight(82);

                percentageCard.setPadding(
                                new Insets(12));

                percentageCard.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 9px;");

                // ================================================================
                // FLAT CARD
                // ================================================================

                Text flatTitle = new Text("Flat Amount Off");

                flatTitle.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252020;");

                Text flatDesc = new Text(
                                "Fixed monetary\ndiscount");

                flatDesc.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #5E504A;");

                VBox flatText = new VBox(
                                3,
                                flatTitle,
                                flatDesc);

                HBox flatContent = new HBox(
                                10,
                                flatAmount,
                                flatText);

                flatContent.setAlignment(
                                Pos.TOP_LEFT);

                StackPane flatCard = new StackPane(
                                flatContent);

                flatCard.setPrefWidth(215);
                flatCard.setPrefHeight(82);

                flatCard.setPadding(
                                new Insets(12));

                flatCard.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 9px;");

                HBox typeCards = new HBox(
                                18,
                                percentageCard,
                                flatCard);

                // ================================================================
                // DISCOUNT VALUE
                // ================================================================

                Text discountLabel = new Text("Discount Value");

                discountLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #493B35;");

                TextField discountField = new TextField("20");

                discountField.setPrefHeight(38);

                discountField.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                Text discountIcon = new Text("%");

                discountIcon.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #493B35;");

                HBox discountRow = new HBox(
                                10,
                                discountIcon,
                                discountField);

                discountRow.setAlignment(
                                Pos.CENTER_LEFT);

                discountRow.setPrefWidth(225);

                VBox discountBox = new VBox(
                                5,
                                discountLabel,
                                discountRow);

                // ================================================================
                // APPLICABILITY
                // ================================================================

                Text applicabilityIcon = new Text("⌂");

                applicabilityIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #A94408;");

                Text applicabilityTitle = new Text("Applicability");

                applicabilityTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                HBox applicabilityHeading = new HBox(
                                10,
                                applicabilityIcon,
                                applicabilityTitle);

                applicabilityHeading.setAlignment(
                                Pos.CENTER_LEFT);

                Text searchLabel = new Text(
                                "Search Products or Categories");

                searchLabel.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #493B35;");

                TextField searchField = new TextField();

                searchField.setPromptText(
                                "Start typing to search...");

                searchField.setPrefHeight(38);

                searchField.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 12px;");

                Button allProducts = new Button("All Products");

                allProducts.setPrefHeight(27);

                allProducts.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 15px;" +
                                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: #493B35;");

                HBox searchRow = new HBox(
                                7,
                                searchField,
                                allProducts);

                searchRow.setAlignment(
                                Pos.CENTER_LEFT);

                HBox.setHgrow(
                                searchField,
                                Priority.ALWAYS);

                Text tag1 = new Text("Electronics  ×");

                tag1.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #333333;" +
                                                "-fx-background-color: #E7E5EB;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-padding: 5px 9px;");

                Text tag2 = new Text("Smartphones  ×");

                tag2.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #333333;" +
                                                "-fx-background-color: #E7E5EB;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-padding: 5px 9px;");

                HBox tags = new HBox(
                                9,
                                tag1,
                                tag2);

                // ================================================================
                // VALIDITY
                // ================================================================

                Text validityIcon = new Text("▣");

                validityIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #A94408;");

                Text validityTitle = new Text("Validity");

                validityTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                HBox validityHeading = new HBox(
                                10,
                                validityIcon,
                                validityTitle);

                validityHeading.setAlignment(
                                Pos.CENTER_LEFT);

                DatePicker startDate = new DatePicker();

                startDate.setPromptText(
                                "mm/dd/yyyy");

                startDate.setPrefHeight(38);

                startDate.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 10px;");

                DatePicker endDate = new DatePicker();

                endDate.setPromptText(
                                "mm/dd/yyyy");

                endDate.setPrefHeight(38);

                endDate.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 10px;");

                VBox dateFields = new VBox(
                                8,
                                startDate,
                                endDate);

                VBox validityBox = new VBox(
                                10,
                                validityHeading,
                                dateFields);

                // ================================================================
                // USAGE LIMITS
                // ================================================================

                Text usageIcon = new Text("♙");

                usageIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-fill: #A94408;");

                Text usageTitle = new Text("Usage Limits");

                usageTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                HBox usageHeading = new HBox(
                                10,
                                usageIcon,
                                usageTitle);

                usageHeading.setAlignment(
                                Pos.CENTER_LEFT);

                TextField maxUses = new TextField();

                maxUses.setPromptText(
                                "Max uses per customer");

                maxUses.setPrefHeight(38);

                maxUses.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 10px;");

                TextField totalLimit = new TextField();

                totalLimit.setPromptText(
                                "Total usage limit");

                totalLimit.setPrefHeight(38);

                totalLimit.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-border-color: transparent;" +
                                                "-fx-font-size: 10px;");

                VBox usageFields = new VBox(
                                8,
                                maxUses,
                                totalLimit);

                VBox usageBox = new VBox(
                                10,
                                usageHeading,
                                usageFields);

                HBox validityUsageRow = new HBox(
                                25,
                                validityBox,
                                usageBox);

                HBox.setHgrow(
                                validityBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                usageBox,
                                Priority.ALWAYS);

                // ================================================================
                // ADD FORM CONTENT
                // ================================================================

                basicCard.getChildren().addAll(
                                infoHeading,
                                offerNameBox,
                                codeRow,
                                typeHeading,
                                typeCards,
                                discountBox,
                                applicabilityHeading,
                                searchLabel,
                                searchRow,
                                tags,
                                validityUsageRow);

                // ================================================================
                // LIVE PREVIEW
                // ================================================================

                VBox previewCard = new VBox();

                previewCard.setPrefWidth(270);
                previewCard.setMinWidth(270);
                previewCard.setMaxWidth(270);

                previewCard.setPrefHeight(570);

                previewCard.setPadding(
                                new Insets(25));

                previewCard.setSpacing(18);

                previewCard.setStyle(
                                "-fx-background-color: #B34F0D;" +
                                                "-fx-background-radius: 12px;");

                Text livePreview = new Text("LIVE PREVIEW");

                livePreview.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;" +
                                                "-fx-letter-spacing: 1px;");

                Text eye = new Text("◉");

                eye.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-fill: white;");

                Region previewSpace = new Region();

                HBox.setHgrow(
                                previewSpace,
                                Priority.ALWAYS);

                HBox previewHeader = new HBox(
                                livePreview,
                                previewSpace,
                                eye);

                previewHeader.setAlignment(
                                Pos.CENTER_LEFT);

                // ================================================================
                // PREVIEW DISCOUNT
                // ================================================================

                Text previewDiscount = new Text("20% OFF");

                previewDiscount.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #A94408;");

                StackPane discountPreview = new StackPane(
                                previewDiscount);

                discountPreview.setPrefWidth(145);
                discountPreview.setPrefHeight(38);

                discountPreview.setMaxWidth(145);

                discountPreview.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 6px;");

                // ================================================================
                // PREVIEW OFFER NAME
                // ================================================================

                Text previewName = new Text(
                                "Summer\nMega Sale");

                previewName.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;");

                // ================================================================
                // PREVIEW CODE
                // ================================================================

                Text checkoutText = new Text(
                                "USE CODE AT CHECKOUT");

                checkoutText.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;");

                Text previewCode = new Text("SUMMER20");

                previewCode.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;");

                StackPane codePreview = new StackPane(
                                previewCode);

                codePreview.setPrefHeight(48);

                codePreview.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.12);" +
                                                "-fx-border-color: rgba(255,255,255,0.65);" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-style: dashed;" +
                                                "-fx-border-radius: 6px;" +
                                                "-fx-background-radius: 6px;");

                VBox checkoutBox = new VBox(
                                7,
                                checkoutText,
                                codePreview);

                // ================================================================
                // PREVIEW IMAGE
                // ================================================================

                StackPane previewImage = new StackPane();

                previewImage.setPrefWidth(220);
                previewImage.setPrefHeight(125);

                previewImage.setStyle(
                                "-fx-background-color: rgba(255,255,255,0.88);" +
                                                "-fx-background-radius: 8px;");

                Text imageIcon = new Text("▧");

                imageIcon.setStyle(
                                "-fx-font-size: 45px;" +
                                                "-fx-fill: #E6D8D0;");

                previewImage.getChildren().add(
                                imageIcon);

                previewCard.getChildren().addAll(
                                previewHeader,
                                discountPreview,
                                previewName,
                                checkoutBox,
                                previewImage);

                // ================================================================
                // MAIN FORM ROW
                // ================================================================

                HBox mainRow = new HBox(
                                20,
                                basicCard,
                                previewCard);

                mainRow.setAlignment(
                                Pos.TOP_CENTER);

                // ================================================================
                // BOTTOM BUTTONS
                // ================================================================

                Region buttonSpacer = new Region();

                HBox.setHgrow(
                                buttonSpacer,
                                Priority.ALWAYS);

                Button discardButton = new Button("Discard");

                discardButton.setPrefWidth(85);
                discardButton.setPrefHeight(38);

                discardButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #A94408;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-cursor: hand;");

                Button createButton = new Button("Create Offer");

                createButton.setPrefWidth(145);
                createButton.setPrefHeight(38);

                createButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-text-fill: #222222;" +
                                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 7px;" +
                                                "-fx-cursor: hand;");

                HBox bottomButtons = new HBox(
                                20,
                                buttonSpacer,
                                discardButton,
                                createButton);

                bottomButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                // ================================================================
                // CENTER CONTENT
                // ================================================================

                centerContent.getChildren().addAll(
                                titleBox,
                                mainRow,
                                bottomButtons);

                // ================================================================
                // SCROLL PANE
                // ================================================================

                ScrollPane centerScroll = new ScrollPane(
                                centerContent);

                centerScroll.setFitToWidth(true);

                centerScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                centerScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                centerScroll.setStyle(
                                "-fx-background-color: #F8F7FC;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                centerScroll);

                // ================================================================
                // NAVIGATION
                // ================================================================

                dashboardButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperDashboard.dashboardScene());

                });

                ordersButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperOrderReady.ordersScene());

                });

                inventoryButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperInventory.inventoryScene());

                });

                offersButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperOffers.offersScene());

                });

                analyticsButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperAnalytics.analyticsScene());

                });

                settingsButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperSettings.settingsScene());

                });

                supportButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperSupport.supportScene());

                });

                // ================================================================
                // AUTO GENERATE
                // ================================================================

                autoGenerate.setOnAction(e -> {

                        promoField.setText(
                                        "SUMMER20");

                });

                // ================================================================
                // LIVE PREVIEW - OFFER NAME
                // ================================================================

                offerNameField.textProperty().addListener(
                                (observable, oldValue, newValue) -> {

                                        if (newValue == null ||
                                                        newValue.trim().isEmpty()) {

                                                previewName.setText(
                                                                "Summer\nMega Sale");

                                        } else {

                                                String value = newValue.trim();

                                                if (value.length() > 18) {

                                                        int space = value.lastIndexOf(
                                                                        " ",
                                                                        18);

                                                        if (space > 0) {

                                                                previewName.setText(
                                                                                value.substring(0, space)
                                                                                                + "\n" +
                                                                                                value.substring(space
                                                                                                                + 1));

                                                        } else {

                                                                previewName.setText(
                                                                                value);

                                                        }

                                                } else {

                                                        previewName.setText(
                                                                        value);

                                                }
                                        }
                                });

                // ================================================================
                // LIVE PREVIEW - PROMO CODE
                // ================================================================

                promoField.textProperty().addListener(
                                (observable, oldValue, newValue) -> {

                                        if (newValue == null ||
                                                        newValue.trim().isEmpty()) {

                                                previewCode.setText(
                                                                "SUMMER20");

                                        } else {

                                                previewCode.setText(
                                                                newValue.toUpperCase());

                                        }
                                });

                // ================================================================
                // LIVE PREVIEW - DISCOUNT
                // ================================================================

                discountField.textProperty().addListener(
                                (observable, oldValue, newValue) -> {

                                        String value = newValue == null ||
                                                        newValue.trim().isEmpty()
                                                                        ? "20"
                                                                        : newValue.trim();

                                        if (percentage.isSelected()) {

                                                previewDiscount.setText(
                                                                value + "% OFF");

                                        } else {

                                                previewDiscount.setText(
                                                                "₹" + value + " OFF");

                                        }
                                });

                // ================================================================
                // PERCENTAGE
                // ================================================================

                percentage.setOnAction(e -> {

                        discountIcon.setText("%");

                        String value = discountField.getText();

                        if (value == null ||
                                        value.trim().isEmpty()) {

                                value = "20";

                        }

                        previewDiscount.setText(
                                        value + "% OFF");

                });

                // ================================================================
                // FLAT AMOUNT
                // ================================================================

                flatAmount.setOnAction(e -> {

                        discountIcon.setText("₹");

                        String value = discountField.getText();

                        if (value == null ||
                                        value.trim().isEmpty()) {

                                value = "20";

                        }

                        previewDiscount.setText(
                                        "₹" + value + " OFF");

                });

                // ================================================================
                // DISCARD
                // ================================================================

                discardButton.setOnAction(e -> {

                        Main.primaryStage.setScene(
                                        ShopkeeperOffers.offersScene());

                });

                // ================================================================
                // CREATE OFFER
                // ================================================================

                createButton.setOnAction(e -> {

                        System.out.println(
                                        "Create Offer clicked");

                        System.out.println(
                                        "Offer Name: " +
                                                        offerNameField.getText());

                        System.out.println(
                                        "Promotion Code: " +
                                                        promoField.getText());

                        System.out.println(
                                        "Description: " +
                                                        descriptionField.getText());

                        System.out.println(
                                        "Discount Value: " +
                                                        discountField.getText());

                        System.out.println(
                                        "Start Date: " +
                                                        startDate.getValue());

                        System.out.println(
                                        "End Date: " +
                                                        endDate.getValue());

                        System.out.println(
                                        "Max Uses: " +
                                                        maxUses.getText());

                        System.out.println(
                                        "Total Limit: " +
                                                        totalLimit.getText());

                });

                // ================================================================
                // SCENE
                // ================================================================

                Scene scene = new Scene(
                                borderPane,
                                1280,
                                650);

                scene.setFill(
                                Color.web("#F8F7FC"));

                return scene;
        }
}
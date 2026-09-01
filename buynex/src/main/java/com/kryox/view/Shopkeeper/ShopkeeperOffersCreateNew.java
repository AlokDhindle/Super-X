package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.view.Customer.Homepage;

import java.util.UUID;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        BorderPane borderPane = new BorderPane();


        // ============================================================
        // HEADER
        // ============================================================

        HBox headerMainBox = ViewConstants.header();

<<<<<<< HEAD
=======
    // Header background
    headerMainBox.setStyle(
            "-fx-background-color: #EBCCB7;"
    );

>>>>>>> Sayali
        borderPane.setTop(headerMainBox);


        // ============================================================
        // SIDEBAR
        // ============================================================

        VBox sidebar = createSidebar();


        borderPane.setLeft(sidebar);


        // ============================================================
        // FOOTER
        // ============================================================

        borderPane.setBottom(
                ViewConstants.footer()
        );


        // ============================================================
        // CENTER CONTENT
        // ============================================================

        VBox centerContent = new VBox();

        centerContent.setPadding(
                new Insets(18, 28, 20, 28)
        );

        centerContent.setSpacing(12);

        centerContent.setStyle(
<<<<<<< HEAD
                "-fx-background-color: #F8F7FC;"
=======
                "-fx-background-color: #EEE5DF;"
>>>>>>> Sayali
        );


        // ============================================================
        // PAGE TITLE
        // ============================================================

        Text pageTitle =
                new Text("Create New Offer");

        pageTitle.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        Text pageSubtitle =
                new Text(
                        "Create a promotional offer applicable to all products."
                );

        pageSubtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #6B5C55;"
        );


        VBox titleBox = new VBox(
                3,
                pageTitle,
                pageSubtitle
        );


        // ============================================================
        // BASIC INFORMATION CARD
        // ============================================================

        VBox basicCard = new VBox();

        basicCard.setPadding(
                new Insets(22)
        );

        basicCard.setSpacing(15);

        basicCard.setPrefWidth(570);
        basicCard.setMinWidth(570);
        basicCard.setMaxWidth(570);

        basicCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;"
        );


        Text infoIcon =
                new Text("ⓘ");

        infoIcon.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-fill: #A94408;"
        );


        Text infoTitle =
                new Text("Basic Information");

        infoTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        HBox infoHeading = new HBox(
                10,
                infoIcon,
                infoTitle
        );


        // ============================================================
        // OFFER NAME
        // ============================================================

        Text offerNameLabel =
                new Text("Offer Name");

        offerNameLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #493B35;"
        );


        TextField offerNameField =
                new TextField();

        offerNameField.setPromptText(
                "e.g. Summer Mega Sale"
        );

        offerNameField.setPrefHeight(38);

        offerNameField.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 12px;"
        );


        VBox offerNameBox = new VBox(
                5,
                offerNameLabel,
                offerNameField
        );


        // ============================================================
        // PROMOTION CODE
        // ============================================================

        Text promoLabel =
                new Text("Promotion Code");

        promoLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #493B35;"
        );


        TextField promoField =
                new TextField();

        promoField.setPromptText(
                "E.G. SUMMER20"
        );

        promoField.setPrefHeight(38);

        promoField.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 12px;"
        );


        Button autoGenerate =
                new Button("Auto-generate");

        autoGenerate.setPrefHeight(30);

        autoGenerate.setStyle(
                "-fx-background-color: #E8DAD4;" +
                "-fx-text-fill: #A94408;" +
                "-fx-font-size: 10px;" +
                "-fx-background-radius: 5px;" +
                "-fx-cursor: hand;"
        );


        HBox promoRow = new HBox(
                5,
                promoField,
                autoGenerate
        );

        HBox.setHgrow(
                promoField,
                Priority.ALWAYS
        );


        VBox promoBox = new VBox(
                5,
                promoLabel,
                promoRow
        );


        // ============================================================
        // DESCRIPTION
        // ============================================================

        Text descriptionLabel =
                new Text("Description (Optional)");

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #493B35;"
        );


        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Short description for internal use"
        );

        descriptionField.setPrefHeight(38);

        descriptionField.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 12px;"
        );


        VBox descriptionBox = new VBox(
                5,
                descriptionLabel,
                descriptionField
        );


        HBox codeRow = new HBox(
                18,
                promoBox,
                descriptionBox
        );

        HBox.setHgrow(
                promoBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                descriptionBox,
                Priority.ALWAYS
        );


        // ============================================================
        // OFFER TYPE & VALUE
        // ============================================================

        Text typeIcon =
                new Text("◇");

        typeIcon.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-fill: #A94408;"
        );


        Text typeTitle =
                new Text("Offer Type & Value");

        typeTitle.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        HBox typeHeading = new HBox(
                10,
                typeIcon,
                typeTitle
        );


        ToggleGroup typeGroup =
                new ToggleGroup();


        RadioButton percentage =
                new RadioButton();

        percentage.setToggleGroup(
                typeGroup
        );

        percentage.setSelected(
                true
        );


        RadioButton flatAmount =
                new RadioButton();

        flatAmount.setToggleGroup(
                typeGroup
        );


        Text percentageTitle =
                new Text("Percentage Discount");

        percentageTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );


        Text percentageDesc =
                new Text(
                        "Discount based on\norder %"
                );

        percentageDesc.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5E504A;"
        );


        VBox percentageText =
                new VBox(
                        3,
                        percentageTitle,
                        percentageDesc
                );


        HBox percentageContent =
                new HBox(
                        10,
                        percentage,
                        percentageText
                );


        StackPane percentageCard =
                new StackPane(
                        percentageContent
                );

        percentageCard.setPrefSize(
                215,
                82
        );

        percentageCard.setPadding(
                new Insets(12)
        );

        percentageCard.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 9px;"
        );


        Text flatTitle =
                new Text("Flat Amount Off");

        flatTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );


        Text flatDesc =
                new Text(
                        "Fixed monetary\ndiscount"
                );

        flatDesc.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-fill: #5E504A;"
        );


        VBox flatText =
                new VBox(
                        3,
                        flatTitle,
                        flatDesc
                );


        HBox flatContent =
                new HBox(
                        10,
                        flatAmount,
                        flatText
                );


        StackPane flatCard =
                new StackPane(
                        flatContent
                );

        flatCard.setPrefSize(
                215,
                82
        );

        flatCard.setPadding(
                new Insets(12)
        );

        flatCard.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 9px;"
        );


        HBox typeCards =
                new HBox(
                        18,
                        percentageCard,
                        flatCard
                );


        // ============================================================
        // DISCOUNT VALUE
        // ============================================================

        Text discountLabel =
                new Text("Discount Value");

        discountLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-fill: #493B35;"
        );


        TextField discountField =
                new TextField("20");

        discountField.setPrefHeight(38);

        discountField.setStyle(
                "-fx-background-color: #F0EEF4;" +
                "-fx-background-radius: 7px;" +
                "-fx-border-color: transparent;"
        );


        Text discountIcon =
                new Text("%");

        discountIcon.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #493B35;"
        );


        HBox discountRow =
                new HBox(
                        10,
                        discountIcon,
                        discountField
                );


        VBox discountBox =
                new VBox(
                        5,
                        discountLabel,
                        discountRow
                );


        // ============================================================
        // VALIDITY
        // ============================================================

        Text validityTitle =
                new Text("Validity");

        validityTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        DatePicker startDate =
                new DatePicker();

        startDate.setPromptText(
                "Start Date"
        );

        startDate.setPrefHeight(38);


        DatePicker endDate =
                new DatePicker();

        endDate.setPromptText(
                "End Date"
        );

        endDate.setPrefHeight(38);


        VBox dateFields =
                new VBox(
                        8,
                        startDate,
                        endDate
                );


        VBox validityBox =
                new VBox(
                        10,
                        validityTitle,
                        dateFields
                );


        // ============================================================
        // USAGE LIMITS
        // ============================================================

        Text usageTitle =
                new Text("Usage Limits");

        usageTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        TextField maxUses =
                new TextField();

        maxUses.setPromptText(
                "Max uses per customer"
        );

        maxUses.setPrefHeight(38);


        TextField totalLimit =
                new TextField();

        totalLimit.setPromptText(
                "Total usage limit"
        );

        totalLimit.setPrefHeight(38);


        VBox usageFields =
                new VBox(
                        8,
                        maxUses,
                        totalLimit
                );


        VBox usageBox =
                new VBox(
                        10,
                        usageTitle,
                        usageFields
                );


        HBox validityUsageRow =
                new HBox(
                        25,
                        validityBox,
                        usageBox
                );


        HBox.setHgrow(
                validityBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                usageBox,
                Priority.ALWAYS
        );


        // ============================================================
        // ADD ALL FORM ELEMENTS
        // ============================================================

        basicCard.getChildren().addAll(
                infoHeading,
                offerNameBox,
                codeRow,
                typeHeading,
                typeCards,
                discountBox,
                validityUsageRow
        );


        // ============================================================
        // LIVE PREVIEW CARD
        // ============================================================

        VBox previewCard =
                new VBox();

        previewCard.setPrefWidth(270);

        previewCard.setPadding(
                new Insets(25)
        );

        previewCard.setSpacing(18);

        previewCard.setStyle(
                "-fx-background-color: #B34F0D;" +
                "-fx-background-radius: 12px;"
        );


        Text livePreview =
                new Text("LIVE PREVIEW");

        livePreview.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;"
        );


        Text previewDiscount =
                new Text("20% OFF");

        previewDiscount.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A94408;"
        );


        StackPane discountPreview =
                new StackPane(
                        previewDiscount
                );

        discountPreview.setPrefSize(
                145,
                38
        );

        discountPreview.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 6px;"
        );


        Text previewName =
                new Text(
                        "Summer\nMega Sale"
                );

        previewName.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;"
        );


        Text checkoutText =
                new Text(
                        "USE CODE AT CHECKOUT"
                );

        checkoutText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;"
        );


        Text previewCode =
                new Text("SUMMER20");

        previewCode.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;"
        );


        StackPane codePreview =
                new StackPane(
                        previewCode
                );

        codePreview.setPrefHeight(48);

        codePreview.setStyle(
                "-fx-background-color: rgba(255,255,255,0.12);" +
                "-fx-border-color: rgba(255,255,255,0.65);" +
                "-fx-border-radius: 6px;" +
                "-fx-background-radius: 6px;"
        );


        VBox checkoutBox =
                new VBox(
                        7,
                        checkoutText,
                        codePreview
                );


        previewCard.getChildren().addAll(
                livePreview,
                discountPreview,
                previewName,
                checkoutBox
        );


        // ============================================================
        // MAIN ROW
        // ============================================================

        HBox mainRow =
                new HBox(
                        20,
                        basicCard,
                        previewCard
                );

        mainRow.setAlignment(
                Pos.TOP_CENTER
        );


        // ============================================================
        // BOTTOM BUTTONS
        // ============================================================

        Region buttonSpacer =
                new Region();

        HBox.setHgrow(
                buttonSpacer,
                Priority.ALWAYS
        );


        Button discardButton =
                new Button("Discard");

        discardButton.setPrefSize(
                85,
                38
        );


        Button createButton =
                new Button("Create Offer");

        createButton.setPrefSize(
                145,
                38
        );

        createButton.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );


        HBox bottomButtons =
                new HBox(
                        20,
                        buttonSpacer,
                        discardButton,
                        createButton
                );


        // ============================================================
        // CENTER CONTENT
        // ============================================================

        centerContent.getChildren().addAll(
                titleBox,
                mainRow,
                bottomButtons
        );


        ScrollPane centerScroll =
                new ScrollPane(
                        centerContent
                );

        centerScroll.setFitToWidth(true);

        centerScroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        centerScroll.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        centerScroll.setStyle(
<<<<<<< HEAD
                "-fx-background: #F8F7FC;" +
                "-fx-background-color: #F8F7FC;"
=======
                "-fx-background: #EEE5DF;" +
                "-fx-background-color: #EEE5DF;"
>>>>>>> Sayali
        );


        borderPane.setCenter(
                centerScroll
        );


     
        // ============================================================
        // AUTO GENERATE PROMO CODE
        // ============================================================

        autoGenerate.setOnAction(e -> {

            String generatedCode =
                    "OFFER"
                            + UUID.randomUUID()
                            .toString()
                            .substring(0, 6)
                            .toUpperCase();

            promoField.setText(
                    generatedCode
            );
        });


        // ============================================================
        // LIVE PREVIEW - OFFER NAME
        // ============================================================

        offerNameField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue == null
                            || newValue.trim().isEmpty()) {

                        previewName.setText(
                                "Summer Mega Sale"
                        );

                    } else {

                        previewName.setText(
                                newValue.trim()
                        );
                    }
                }
        );


        // ============================================================
        // LIVE PREVIEW - PROMO CODE
        // ============================================================

        promoField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue == null
                            || newValue.trim().isEmpty()) {

                        previewCode.setText(
                                "SUMMER20"
                        );

                    } else {

                        previewCode.setText(
                                newValue
                                        .trim()
                                        .toUpperCase()
                        );
                    }
                }
        );


        // ============================================================
        // LIVE PREVIEW - DISCOUNT
        // ============================================================

        discountField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    updateDiscountPreview(
                            previewDiscount,
                            discountField,
                            percentage
                    );
                }
        );


        percentage.setOnAction(e -> {

            discountIcon.setText("%");

            updateDiscountPreview(
                    previewDiscount,
                    discountField,
                    percentage
            );
        });


        flatAmount.setOnAction(e -> {

            discountIcon.setText("₹");

            updateDiscountPreview(
                    previewDiscount,
                    discountField,
                    percentage
            );
        });


        // ============================================================
        // DISCARD BUTTON
        // ============================================================

        discardButton.setOnAction(e ->
                Homepage.HomepageStage.setScene(
                        ShopkeeperOffers.offersScene()
                )
        );


        // ============================================================
        // CREATE OFFER
        // ============================================================

        createButton.setOnAction(e -> {

            try {

                String offerName =
                        offerNameField
                                .getText()
                                .trim();


                String promoCode =
                        promoField
                                .getText()
                                .trim()
                                .toUpperCase();


                String description =
                        descriptionField
                                .getText()
                                .trim();


                // ----------------------------------------------------
                // VALIDATION
                // ----------------------------------------------------

                if (offerName.isEmpty()) {

                    showError(
                            "Offer name is required."
                    );

                    return;
                }


                if (promoCode.isEmpty()) {

                    showError(
                            "Promotion code is required."
                    );

                    return;
                }


                if (discountField
                        .getText()
                        .trim()
                        .isEmpty()) {

                    showError(
                            "Discount value is required."
                    );

                    return;
                }


                if (startDate.getValue() == null) {

                    showError(
                            "Please select a start date."
                    );

                    return;
                }


                if (endDate.getValue() == null) {

                    showError(
                            "Please select an end date."
                    );

                    return;
                }


                if (endDate
                        .getValue()
                        .isBefore(
                                startDate.getValue()
                        )) {

                    showError(
                            "End date cannot be before start date."
                    );

                    return;
                }


                // ----------------------------------------------------
                // DISCOUNT VALUE
                // ----------------------------------------------------

                double discountValue =
                        Double.parseDouble(
                                discountField
                                        .getText()
                                        .trim()
                        );


                if (discountValue <= 0) {

                    showError(
                            "Discount value must be greater than zero."
                    );

                    return;
                }


                String discountType;


                if (percentage.isSelected()) {

                    discountType =
                            "PERCENTAGE";


                    if (discountValue > 100) {

                        showError(
                                "Percentage discount cannot exceed 100%."
                        );

                        return;
                    }

                } else {

                    discountType =
                            "FLAT_AMOUNT";
                }


                // ----------------------------------------------------
                // USAGE LIMITS
                // ----------------------------------------------------

                int maxUsesPerCustomer = 0;

                int totalUsageLimit = 0;


                if (!maxUses
                        .getText()
                        .trim()
                        .isEmpty()) {

                    maxUsesPerCustomer =
                            Integer.parseInt(
                                    maxUses
                                            .getText()
                                            .trim()
                            );


                    if (maxUsesPerCustomer < 0) {

                        showError(
                                "Max uses cannot be negative."
                        );

                        return;
                    }
                }


                if (!totalLimit
                        .getText()
                        .trim()
                        .isEmpty()) {

                    totalUsageLimit =
                            Integer.parseInt(
                                    totalLimit
                                            .getText()
                                            .trim()
                            );


                    if (totalUsageLimit < 0) {

                        showError(
                                "Total usage limit cannot be negative."
                        );

                        return;
                    }
                }


                // ----------------------------------------------------
                // CREATE OFFER MODEL
                // ----------------------------------------------------

                OfferModel offerModel =
                        new OfferModel(

                                null,

                                offerName,

                                promoCode,

                                description,

                                discountType,

                                discountValue,

                                startDate
                                        .getValue()
                                        .toString(),

                                endDate
                                        .getValue()
                                        .toString(),

                                maxUsesPerCustomer,

                                totalUsageLimit,

                                "ACTIVE"
                        );


                // ----------------------------------------------------
                // SAVE OFFER
                // ----------------------------------------------------

                OfferController.addNewOffer(
                        offerModel
                );


                // ----------------------------------------------------
                // SUCCESS
                // ----------------------------------------------------

                showSuccess(
                        "Offer created successfully."
                );


                // ----------------------------------------------------
                // RETURN TO OFFERS PAGE
                // ----------------------------------------------------

                Homepage.HomepageStage.setScene(
                        ShopkeeperOffers
                                .offersScene()
                );


            } catch (NumberFormatException exception) {

                showError(
                        "Please enter valid numeric values."
                );

            } catch (Exception exception) {

                exception.printStackTrace();

                showError(
                        "Unable to create offer."
                );
            }
        });


        // ============================================================
        // RETURN SCENE
        // ============================================================

        Scene scene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        scene.setFill(
<<<<<<< HEAD
                Color.web("#F8F7FC")
=======
                Color.web("#EEE5DF")
>>>>>>> Sayali
        );


        return scene;
    }


    // ================================================================
    // UPDATE DISCOUNT PREVIEW
    // ================================================================

    private static void updateDiscountPreview(
            Text previewDiscount,
            TextField discountField,
            RadioButton percentage) {

        String value =
                discountField
                        .getText()
                        .trim();


        if (value.isEmpty()) {

            value = "0";
        }


        if (percentage.isSelected()) {

            previewDiscount.setText(
                    value + "% OFF"
            );

        } else {

            previewDiscount.setText(
                    "₹" + value + " OFF"
            );
        }
    }


    // ================================================================
    // ERROR ALERT
    // ================================================================

    private static void showError(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle(
                "Error"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }


    // ================================================================
    // SUCCESS ALERT
    // ================================================================

    private static void showSuccess(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Success"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

       private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
<<<<<<< HEAD
                                "-fx-background-color: #F5F4F9;" +
=======
                                "-fx-background-color: #EBCCB7;" +
>>>>>>> Sayali
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                true);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                false);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                false);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));
                offersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));


                analyticsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperAnalytics
                                                                .analyticsScene()));

                settingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }
}

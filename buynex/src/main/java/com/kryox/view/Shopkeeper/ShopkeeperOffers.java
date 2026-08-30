package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.view.Customer.Homepage;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperOffers {

    public static Scene offersScene() {

        // ============================================================
        // BORDER PANE
        // ============================================================

        BorderPane borderPane = new BorderPane();


        // ============================================================
        // HEADER
        // ============================================================

        HBox headerMainBox =
                ViewConstants.header();

        borderPane.setTop(headerMainBox);


        // ============================================================
        // SIDEBAR
        // ============================================================

        VBox sidebar = createSidebar();

        borderPane.setLeft(sidebar);

        // ============================================================
        // FOOTER
        // ============================================================

        VBox footerBox =
                ViewConstants.footer();

        borderPane.setBottom(
                footerBox
        );


        // ============================================================
        // PAGE TITLE
        // ============================================================

        Text offersTitle =
                new Text(
                        "Offers & Promotions"
                );

        offersTitle.setStyle(
                "-fx-font-size: 31px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #171717;"
        );


        Text offersSubtitle =
                new Text(
                        "Manage your active offers and promotions."
                );

        offersSubtitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-family: 'Arial';" +
                "-fx-fill: #666666;"
        );


        VBox titleBox =
                new VBox(
                        5,
                        offersTitle,
                        offersSubtitle
                );


        // ============================================================
        // CREATE OFFER BUTTON
        // ============================================================

        Button createOfferButton =
                new Button(
                        "⊕  Create New Offer"
                );

        createOfferButton.setPrefWidth(211);
        createOfferButton.setPrefHeight(40);

        createOfferButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9C72);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-cursor: hand;"
        );


        HBox topTitleBox =
                new HBox(
                        titleBox,
                        createOfferButton
                );


        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        topTitleBox.setAlignment(
                Pos.CENTER_LEFT
        );


        // ============================================================
        // ACTIVE OFFERS TITLE
        // ============================================================

        Text activeOffersTitle =
                new Text(
                        "Active Offers"
                );

        activeOffersTitle.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #181818;"
        );


        VBox activeOffersBox =
                new VBox(
                        14
                );


        activeOffersBox.getChildren().add(
                activeOffersTitle
        );


        // ============================================================
        // FETCH OFFERS DYNAMICALLY
        // ============================================================

        ArrayList<OfferModel> offers =
                OfferController.getOffers();


        boolean hasValidOffers = false;


        if (offers != null) {

            for (OfferModel offer : offers) {

                if (isOfferValid(offer)) {

                    activeOffersBox
                            .getChildren()
                            .add(
                                    createOfferCard(
                                            offer
                                    )
                            );

                    hasValidOffers = true;
                }
            }
        }


        // ============================================================
        // NO VALID OFFERS
        // ============================================================

        if (!hasValidOffers) {

            Text noOffers =
                    new Text(
                            "No active offers available."
                    );

            noOffers.setStyle(
                    "-fx-font-size: 15px;" +
                    "-fx-fill: #777777;"
            );


            VBox emptyOfferBox =
                    new VBox(
                            noOffers
                    );


            emptyOfferBox.setAlignment(
                    Pos.CENTER
            );


            emptyOfferBox.setPadding(
                    new Insets(
                            30
                    )
            );


            emptyOfferBox.setPrefHeight(
                    100
            );


            emptyOfferBox.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-border-color: #E0DDE2;" +
                    "-fx-border-radius: 18px;" +
                    "-fx-background-radius: 18px;"
            );


            activeOffersBox
                    .getChildren()
                    .add(
                            emptyOfferBox
                    );
        }


        // ============================================================
        // MAIN CONTENT
        // ============================================================

        VBox mainContent =
                new VBox(
                        25,
                        topTitleBox,
                        activeOffersBox
                );


        mainContent.setPadding(
                new Insets(
                        25
                )
        );


        mainContent.setAlignment(
                Pos.TOP_CENTER
        );


        mainContent.setMaxWidth(
                Double.MAX_VALUE
        );


        // ============================================================
        // SCROLL PANE
        // ============================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        mainContent
                );


        scrollPane.setFitToWidth(
                true
        );


        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );


        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );


        scrollPane.setStyle(
                "-fx-background-color: #F8F7FC;" +
                "-fx-border-color: transparent;"
        );


        borderPane.setCenter(
                scrollPane
        );


        // ============================================================
        // CREATE OFFER BUTTON ACTION
        // ============================================================

        createOfferButton.setOnAction(
                e -> Homepage.HomepageStage.setScene(
                        ShopkeeperOffersCreateNew
                                .createNewOfferScene()
                )
        );


        // ============================================================
        // RETURN SCENE
        // ============================================================

        Scene offersScene =
                new Scene(
                        borderPane,
                        ViewConstants.STAGE_WIDTH,
                        ViewConstants.STAGE_HEIGHT
                );


        offersScene.setFill(
                Color.web(
                        "#F8F7FC"
                )
        );


        return offersScene;
    }


    // ================================================================
    // CHECK WHETHER OFFER IS VALID
    // ================================================================

    private static boolean isOfferValid(
            OfferModel offer) {

        try {

            // Offer must exist
            if (offer == null) {
                return false;
            }


            // End date must exist
            if (offer.getEndDate() == null
                    || offer.getEndDate().isEmpty()) {

                return false;
            }


            LocalDate endDate =
                    LocalDate.parse(
                            offer.getEndDate()
                    );


            LocalDate today =
                    LocalDate.now();


            /*
             * If today is after endDate,
             * the offer has expired.
             *
             * Example:
             *
             * Today:    2026-08-29
             * End Date: 2026-08-28
             *
             * Result: false
             */

            if (today.isAfter(endDate)) {

                return false;
            }


            // Optional:
            // Do not show manually inactive offers
            if (offer.getStatus() != null
                    && offer.getStatus()
                            .equalsIgnoreCase(
                                    "INACTIVE"
                            )) {

                return false;
            }


            return true;

        } catch (Exception e) {

            // Invalid date should not break the page
            return false;
        }
    }


    // ================================================================
    // CREATE DYNAMIC OFFER CARD
    // ================================================================

    private static HBox createOfferCard(
            OfferModel offer) {


        // ============================================================
        // ICON
        // ============================================================

        StackPane iconBox =
                new StackPane();


        iconBox.setPrefWidth(
                69
        );


        iconBox.setPrefHeight(
                69
        );


        iconBox.setStyle(
                "-fx-background-color: #E5E4E8;" +
                "-fx-background-radius: 9px;"
        );


        Text icon =
                new Text(
                        "ϟ"
                );


        icon.setStyle(
                "-fx-font-size: 34px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #FF6900;"
        );


        iconBox.getChildren().add(
                icon
        );


        // ============================================================
        // OFFER NAME
        // ============================================================

        Text offerName =
                new Text(
                        safe(
                                offer.getOfferName()
                        )
                );


        offerName.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #171717;"
        );


        // ============================================================
        // DISCOUNT TEXT
        // ============================================================

        String discountText;


        if ("PERCENTAGE".equalsIgnoreCase(
                offer.getDiscountType()
        )) {

            discountText =
                    String.format(
                            "%.0f%% OFF",
                            offer.getDiscountValue()
                    );

        } else {

            discountText =
                    "₹"
                            + String.format(
                                    "%.2f",
                                    offer.getDiscountValue()
                            )
                            + " OFF";
        }


        Text discountTag =
                new Text(
                        discountText
                );


        discountTag.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #A94408;"
        );


        HBox titleRow =
                new HBox(
                        10,
                        offerName,
                        discountTag
                );


        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );


        // ============================================================
        // DESCRIPTION
        // ============================================================

        Text description =
                new Text(
                        safe(
                                offer.getDescription()
                        )
                );


        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );


        // ============================================================
        // PROMO CODE
        // ============================================================

        Text promoCode =
                new Text(
                        "Code: "
                                + safe(
                                        offer.getPromoCode()
                                )
                );


        promoCode.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-fill: #666666;"
        );


        // ============================================================
        // END DATE
        // ============================================================

        Text endDate =
                new Text(
                        "Valid until "
                                + safe(
                                        offer.getEndDate()
                                )
                );


        endDate.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-fill: #777777;"
        );


        // ============================================================
        // OFFER DETAILS
        // ============================================================

        VBox offerTextBox =
                new VBox(
                        6,
                        titleRow,
                        description,
                        promoCode,
                        endDate
                );


        HBox.setHgrow(
                offerTextBox,
                Priority.ALWAYS
        );


        // ============================================================
        // COMPLETE OFFER CARD
        // STATUS BUTTON REMOVED
        // ============================================================

        HBox offerCard =
                new HBox(
                        20,
                        iconBox,
                        offerTextBox
                );


        offerCard.setAlignment(
                Pos.CENTER_LEFT
        );


        offerCard.setPadding(
                new Insets(
                        20
                )
        );


        offerCard.setPrefHeight(
                140
        );


        offerCard.setMaxWidth(
                Double.MAX_VALUE
        );


        offerCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-color: #E0DDE2;" +
                "-fx-border-radius: 18px;" +
                "-fx-background-radius: 18px;"
        );


        return offerCard;
    }


    // ================================================================
    // NULL SAFE STRING
    // ================================================================

    private static String safe(
            String value) {

        return value == null
                ? ""
                : value;
    }

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
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

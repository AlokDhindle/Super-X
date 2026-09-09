package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Shopkeeper.OfferModel;
import com.kryox.view.Customer.Homepage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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

        // Border Pane

        BorderPane borderPane = new BorderPane();


        // Header

        HBox headerMainBox =
                ViewConstants.header();

        // Header background
        headerMainBox.setStyle(
                "-fx-background-color: #EBCCB7;"
        );

        borderPane.setTop(headerMainBox);


        // Sidebar

        VBox sidebar = createSidebar();

        borderPane.setLeft(sidebar);

        // Footer

        VBox footerBox =
                ViewConstants.footer();

        borderPane.setBottom(
                footerBox
        );


        // Page Title

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


        // Create Campaign Button
        Button createCampaignButton =
                new Button(
                        "📢  Request Campaign"
                );

        createCampaignButton.setPrefWidth(190);
        createCampaignButton.setPrefHeight(40);

        createCampaignButton.setStyle(
                "-fx-background-color: #B84300;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-cursor: hand;"
        );

        createCampaignButton.setOnAction(
                e -> Homepage.HomepageStage.setScene(
                        new com.kryox.view.Admin.CreateCampaignPage(true)
                                .getCampaignScene()
                )
        );

        // Create Offer Button

        Button createOfferButton =
                new Button(
                        "⊕  Create New Offer"
                );

        createOfferButton.setPrefWidth(195);
        createOfferButton.setPrefHeight(40);

        createOfferButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9C72);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-cursor: hand;"
        );

        HBox topButtonsBox = new HBox(12, createCampaignButton, createOfferButton);
        topButtonsBox.setAlignment(Pos.CENTER_RIGHT);

        HBox topTitleBox =
                new HBox(
                        titleBox,
                        topButtonsBox
                );


        HBox.setHgrow(
                titleBox,
                Priority.ALWAYS
        );


        topTitleBox.setAlignment(
                Pos.CENTER_LEFT
        );


        // Campaign Requests Section (Shopkeeper Side)
        VBox campaignRequestsBox = createShopkeeperCampaignRequestsBox();


        // Active Offers Title

        Text activeOffersTitle =
                new Text(
                        "Active Store Offers"
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


        // Fetch Offers Dynamically

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


        // No Valid Offers

        if (!hasValidOffers) {

            Text noOffers =
                    new Text(
                            "No active store offers available."
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


        // Main Content

        VBox mainContent =
                new VBox(
                        25,
                        topTitleBox,
                        campaignRequestsBox,
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


        // Scroll Pane

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
                "-fx-background-color: #EEE5DF;" +
                "-fx-border-color: transparent;"
        );


        borderPane.setCenter(
                scrollPane
        );


        // Create Offer Button Action

        createOfferButton.setOnAction(
                e -> Homepage.HomepageStage.setScene(
                        ShopkeeperOffersCreateNew
                                .createNewOfferScene()
                )
        );


        // Return Scene

        Scene offersScene =
                new Scene(
                        borderPane,
                        1550,
                        850
                );


        offersScene.setFill(
                Color.web(
                        "#EEE5DF"
                )
        );


        return offersScene;
    }


    // Check Whether Offer is Valid

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


    // Create Dynamic Offer Card

    private static HBox createOfferCard(
            OfferModel offer) {


        // Icon

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


        // Offer Name

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


        // Discount Text

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


        // Description

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


        // Promo Code

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


        // End Date

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


        // Offer Details

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

        // Delete Offer Button
        Button deleteOfferBtn = new Button("🗑  Delete Offer");
        deleteOfferBtn.setPrefHeight(38);
        deleteOfferBtn.setMinWidth(135);
        deleteOfferBtn.setStyle(
                "-fx-background-color: #FFF5F5;" +
                "-fx-text-fill: #D32F2F;" +
                "-fx-border-color: #FFCDD2;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
        deleteOfferBtn.setOnMouseEntered(e -> {
            deleteOfferBtn.setStyle(
                    "-fx-background-color: #D32F2F;" +
                    "-fx-text-fill: white;" +
                    "-fx-border-color: #D32F2F;" +
                    "-fx-border-radius: 9px;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );
        });
        deleteOfferBtn.setOnMouseExited(e -> {
            deleteOfferBtn.setStyle(
                    "-fx-background-color: #FFF5F5;" +
                    "-fx-text-fill: #D32F2F;" +
                    "-fx-border-color: #FFCDD2;" +
                    "-fx-border-radius: 9px;" +
                    "-fx-background-radius: 9px;" +
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;"
            );
        });
        deleteOfferBtn.setOnAction(e -> {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Delete Offer");
            confirmAlert.setHeaderText("Delete Offer: " + safe(offer.getOfferName()));
            confirmAlert.setContentText("Are you sure you want to permanently delete this offer?");
            Optional<ButtonType> res = confirmAlert.showAndWait();
            if (res.isPresent() && res.get() == ButtonType.OK) {
                OfferController.deleteOffer(offer.getOfferId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Offer Deleted");
                alert.setHeaderText(null);
                alert.setContentText("The offer has been deleted successfully.");
                alert.showAndWait();
                Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene());
            }
        });

        // Complete Offer Card with Delete Button
        HBox offerCard =
                new HBox(
                        20,
                        iconBox,
                        offerTextBox,
                        deleteOfferBtn
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


    // Null Safe String

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
                                "-fx-background-color: #EBCCB7;" +
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

                Button bookingsButton = ViewConstants.createDashboardButton(
                                "📅",
                                "Bookings",
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
                                bookingsButton,
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

                bookingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperBookedProducts
                                                                .bookedProductsScene()));

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

        private static VBox createShopkeeperCampaignRequestsBox() {
                VBox box = new VBox(14);

                Text title = new Text("My Campaign Requests & Admin Approvals");
                title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-fill: #181818;");

                Text subtitle = new Text("Track your campaign review status. Only APPROVED campaigns go live on the Customer Portal.");
                subtitle.setStyle("-fx-font-size: 13px; -fx-fill: #777777;");

                VBox titleContainer = new VBox(4, title, subtitle);
                box.getChildren().add(titleContainer);

                String currentShopId = null;
                if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getShopkeeperUid() != null) {
                        currentShopId = ViewConstants.shopkeeperModel.getShopkeeperUid();
                }

                java.util.List<com.kryox.model.CampaignModel> myCampaigns =
                                com.kryox.controller.CampaignController.getCampaignsForShopkeeper(currentShopId);

                if (myCampaigns == null || myCampaigns.isEmpty()) {
                        VBox emptyBox = new VBox();
                        emptyBox.setAlignment(Pos.CENTER);
                        emptyBox.setPadding(new Insets(24));
                        emptyBox.setStyle("-fx-background-color: white; -fx-background-radius: 14px; -fx-border-color: #E0DDE2; -fx-border-radius: 14px;");

                        Text emptyText = new Text("No campaign requests submitted yet. Click 'Request Campaign' above to launch your first promotion!");
                        emptyText.setStyle("-fx-font-size: 14px; -fx-fill: #777777;");
                        emptyBox.getChildren().add(emptyText);
                        box.getChildren().add(emptyBox);
                        return box;
                }

                for (com.kryox.model.CampaignModel camp : myCampaigns) {
                        HBox card = new HBox(16);
                        card.setAlignment(Pos.CENTER_LEFT);
                        card.setPadding(new Insets(18, 22, 18, 22));
                        card.setStyle("-fx-background-color: white; -fx-background-radius: 14px; -fx-border-color: #E2D9D2; -fx-border-radius: 14px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 8, 0, 0, 2);");

                        // Icon box
                        StackPane iconBox = new StackPane();
                        iconBox.setPrefSize(60, 60);
                        iconBox.setMinSize(60, 60);
                        iconBox.setStyle("-fx-background-color: #FFF0E7; -fx-background-radius: 10px;");
                        Text icon = new Text("📢");
                        icon.setStyle("-fx-font-size: 26px;");
                        iconBox.getChildren().add(icon);

                        // Info
                        VBox infoBox = new VBox(5);
                        HBox.setHgrow(infoBox, Priority.ALWAYS);

                        HBox titleRow = new HBox(10);
                        titleRow.setAlignment(Pos.CENTER_LEFT);

                        Text campTitle = new Text(camp.getTitle() != null ? camp.getTitle() : "Campaign");
                        campTitle.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-fill: #222222;");

                        String discText = (int) camp.getDiscount() + "% OFF";
                        if (camp.getDiscountType() != null && camp.getDiscountType().contains("FLAT")) {
                                discText = "₹" + (int) camp.getDiscount() + " OFF";
                        }
                        Label discBadge = new Label(discText);
                        discBadge.setStyle("-fx-background-color: #FF6900; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 11px; -fx-padding: 3 8 3 8; -fx-background-radius: 8px;");

                        titleRow.getChildren().addAll(campTitle, discBadge);

                        Text desc = new Text(camp.getDescription() != null ? camp.getDescription() : "");
                        desc.setStyle("-fx-font-size: 13px; -fx-fill: #666666;");

                        String dateStr = "Validity: " + (camp.getStartDate() != null ? camp.getStartDate() : "--")
                                        + " to " + (camp.getEndDate() != null ? camp.getEndDate() : "--")
                                        + " | Category: " + (camp.getApplicableCategories() != null ? camp.getApplicableCategories() : "All");
                        Text dates = new Text(dateStr);
                        dates.setStyle("-fx-font-size: 11px; -fx-fill: #999999;");

                        infoBox.getChildren().addAll(titleRow, desc, dates);

                        // If rejected, show reason
                        if ("REJECTED".equalsIgnoreCase(camp.getStatus()) && camp.getRejectionReason() != null) {
                                Text reasonText = new Text("Rejection reason: " + camp.getRejectionReason());
                                reasonText.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-fill: #C62828;");
                                infoBox.getChildren().add(reasonText);
                        }

                        // Status Badge Box
                        VBox statusBox = new VBox(6);
                        statusBox.setAlignment(Pos.CENTER_RIGHT);

                        Label statusLabel = new Label();
                        statusLabel.setPadding(new Insets(6, 14, 6, 14));
                        statusLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 12px;");

                        if ("APPROVED".equalsIgnoreCase(camp.getStatus())) {
                                statusLabel.setText("✓ APPROVED & LIVE");
                                statusLabel.setStyle("-fx-background-color: #E8F5E9; -fx-text-fill: #2E7D32; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 12px;");
                        } else if ("REJECTED".equalsIgnoreCase(camp.getStatus())) {
                                statusLabel.setText("✕ REJECTED");
                                statusLabel.setStyle("-fx-background-color: #FFEBEE; -fx-text-fill: #C62828; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 12px;");
                        } else {
                                statusLabel.setText("⏳ PENDING ADMIN REVIEW");
                                statusLabel.setStyle("-fx-background-color: #FFF8E1; -fx-text-fill: #F57F17; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-radius: 12px;");
                        }

                        Text reqDate = new Text("Requested: " + (camp.getCreatedAt() != null ? camp.getCreatedAt().substring(0, Math.min(10, camp.getCreatedAt().length())) : "Today"));
                        reqDate.setStyle("-fx-font-size: 10px; -fx-fill: #888888;");

                        Button deleteCampBtn = new Button("🗑 Delete");
                        deleteCampBtn.setStyle(
                                "-fx-background-color: #FFF5F5; -fx-text-fill: #D32F2F; -fx-border-color: #FFCDD2; -fx-border-radius: 6px; -fx-background-radius: 6px; -fx-font-size: 11px; -fx-font-weight: bold; -fx-cursor: hand;"
                        );
                        deleteCampBtn.setOnAction(e -> {
                                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                confirmAlert.setTitle("Delete Campaign");
                                confirmAlert.setHeaderText("Delete Campaign: " + camp.getTitle());
                                confirmAlert.setContentText("Are you sure you want to permanently delete this campaign request?");
                                Optional<ButtonType> res = confirmAlert.showAndWait();
                                if (res.isPresent() && res.get() == ButtonType.OK) {
                                        com.kryox.controller.CampaignController.deleteCampaign(camp.getCampaignId());
                                        Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene());
                                }
                        });

                        statusBox.getChildren().addAll(statusLabel, reqDate, deleteCampBtn);
                        card.getChildren().addAll(iconBox, infoBox, statusBox);
                        box.getChildren().add(card);
                }

                return box;
        }
}
package com.kryox.view.Customer;

import java.net.URL;
import java.util.List;

import com.kryox.controller.CampaignController;
import com.kryox.model.CampaignModel;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerCampaignSection {

    private static Image loadImage(String path) {
        try {
            if (path != null && (path.startsWith("file:") || path.startsWith("http:") || path.startsWith("https:"))) {
                return new Image(path, true);
            }
            if (path != null && !path.isBlank()) {
                URL url = CustomerCampaignSection.class.getResource(path);
                if (url != null) {
                    return new Image(url.toExternalForm());
                }
            }
        } catch (Exception e) {}

        try {
            URL fallback = CustomerCampaignSection.class.getResource("/assets/images/image.png");
            if (fallback != null) {
                return new Image(fallback.toExternalForm());
            }
        } catch (Exception ignored) {}

        return null;
    }

    public static VBox createSpecialCampaignsSection(String userId, Runnable backCallback) {
        VBox section = new VBox(14);
        section.setPadding(new Insets(10, 0, 10, 0));

        // Header Row: 🔥 SPECIAL CAMPAIGNS + Dynamic Active Counter
        HBox headerRow = new HBox(12);
        headerRow.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("🔥 SPECIAL CAMPAIGNS");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: 900; -fx-text-fill: #1E1E24;");

        // Fetch ONLY approved and currently valid active campaigns
        List<CampaignModel> activeCampaigns = CampaignController.getApprovedActiveCampaigns();
        int activeCount = activeCampaigns.size();

        Label countBadge = new Label("🔥 " + activeCount + (activeCount == 1 ? " Active Campaign" : " Active Campaigns"));
        countBadge.setStyle(
                "-fx-background-color: #FFF0E6;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 4 10 4 10;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #FFBD95;" +
                "-fx-border-radius: 12px;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label subNotice = new Label("Admin-verified marketplace promotions • Tap to explore products");
        subNotice.setStyle("-fx-font-size: 11px; -fx-text-fill: #777777; -fx-font-weight: bold;");

        headerRow.getChildren().addAll(title, countBadge, spacer, subNotice);
        section.getChildren().add(headerRow);

        if (activeCampaigns.isEmpty()) {
            VBox emptyCard = new VBox(6);
            emptyCard.setAlignment(Pos.CENTER);
            emptyCard.setPadding(new Insets(24));
            emptyCard.setStyle("-fx-background-color: white; -fx-background-radius: 14px; -fx-border-color: #E6DFD9; -fx-border-radius: 14px;");

            Label emptyLabel = new Label("No special campaigns active right now. Check back soon for festival sales!");
            emptyLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #777777;");
            emptyCard.getChildren().add(emptyLabel);
            section.getChildren().add(emptyCard);
            return section;
        }

        // Horizontal scroll container of dynamic approved campaign cards
        HBox cardsContainer = new HBox(18);
        cardsContainer.setAlignment(Pos.CENTER_LEFT);
        cardsContainer.setPadding(new Insets(4, 4, 12, 4));

        for (CampaignModel camp : activeCampaigns) {
            cardsContainer.getChildren().add(createCampaignCard(camp, userId, backCallback));
        }

        ScrollPane scroll = new ScrollPane(cardsContainer);
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: transparent; -fx-background: transparent; -fx-border-color: transparent;");

        section.getChildren().add(scroll);
        return section;
    }

    private static VBox createCampaignCard(CampaignModel c, String userId, Runnable backCallback) {
        VBox card = new VBox(10);
        card.setPrefWidth(310);
        card.setMinWidth(310);
        card.setMaxWidth(310);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: #FFCBA8;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 16px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 10, 0, 0, 3);"
        );

        // Banner Image
        ImageView bannerView = new ImageView(loadImage(c.getImageUrl()));
        bannerView.setFitWidth(278);
        bannerView.setFitHeight(125);
        bannerView.setPreserveRatio(true);

        StackPane bannerWrap = new StackPane(bannerView);
        bannerWrap.setPrefSize(278, 125);
        bannerWrap.setStyle(
                "-fx-background-color: linear-gradient(to right, #FFF3EB, #FFE6D6);" +
                "-fx-background-radius: 12px;"
        );

        // Discount overlay badge
        String discText = (int) c.getDiscount() + "% OFF";
        if (c.getDiscountType() != null && c.getDiscountType().contains("FLAT")) {
            discText = "₹" + (int) c.getDiscount() + " OFF";
        }
        Label discBadge = new Label("🎉 " + discText);
        discBadge.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: 900;" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 4 9 4 9;" +
                "-fx-background-radius: 8px;"
        );
        StackPane.setAlignment(discBadge, Pos.TOP_RIGHT);
        StackPane.setMargin(discBadge, new Insets(8));
        bannerWrap.getChildren().add(discBadge);

        // Campaign Name
        Label title = new Label("🎉 " + (c.getTitle() != null ? c.getTitle() : "Special Sale"));
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #1E1E24;");
        title.setWrapText(true);

        // Store Name
        Label storeName = new Label("🏪 " + (c.getStoreName() != null ? c.getStoreName() : "BuyNex Partner Store"));
        storeName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #A83E00;");

        // Validity
        Label validity = new Label("Valid: " + (c.getStartDate() != null ? c.getStartDate() : "--") + " to " + (c.getEndDate() != null ? c.getEndDate() : "--"));
        validity.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        // Description snippet
        String descSnippet = c.getDescription() != null ? c.getDescription() : "Special campaign offer on selected products.";
        if (descSnippet.length() > 70) {
            descSnippet = descSnippet.substring(0, 67) + "...";
        }
        Label desc = new Label(descSnippet);
        desc.setStyle("-fx-font-size: 11px; -fx-text-fill: #777777;");
        desc.setWrapText(true);

        // VIEW CAMPAIGN Button
        Button viewBtn = new Button("VIEW CAMPAIGN  →");
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        viewBtn.setPrefHeight(36);
        viewBtn.setStyle(
                "-fx-background-color: #B84300;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        viewBtn.setOnAction(e -> {
            CustomerCampaignDetails details = new CustomerCampaignDetails(userId, c, backCallback);
            Homepage.HomepageStage.setScene(details.getScene());
        });

        card.getChildren().addAll(bannerWrap, title, storeName, validity, desc, viewBtn);
        return card;
    }
}

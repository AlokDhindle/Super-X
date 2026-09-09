package com.kryox.view.Admin;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import com.kryox.controller.CampaignController;
import com.kryox.model.CampaignModel;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Shopkeeper.ShopkeeperOffers;
import com.kryox.view.Shopkeeper.ViewConstants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class CreateCampaignPage {

    private final boolean isShopkeeperMode;
    private File selectedImage;
    private String customImageLocalPath = "/assets/images/image.png";

    public CreateCampaignPage() {
        this(false);
    }

    public CreateCampaignPage(boolean isShopkeeperMode) {
        this.isShopkeeperMode = isShopkeeperMode;
    }

    public Scene getCampaignScene() {

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #eee5df;");

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(22, 40, 22, 40));
        topBar.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#E8DFE5;" +
                "-fx-border-width:0 0 1 0;"
        );

        Label backButton = new Label("←");
        backButton.setAlignment(Pos.CENTER);
        backButton.setPrefSize(46, 46);
        backButton.setStyle(
                "-fx-font-size:29px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-color:#F3E3D3;" +
                "-fx-text-fill:#A83E00;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#A83E00;" + 
                "-fx-border-radius:8;" +
                "-fx-cursor:hand;"
        );

        VBox headingBox = new VBox(4);
        headingBox.setPadding(new Insets(0, 0, 0, 18));

        Label heading = new Label(isShopkeeperMode ? "Submit Campaign Request" : "Create Global Campaign");
        heading.setStyle(
                "-fx-font-size:31px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#242124;"
        );

        Label subHeading = new Label(
                isShopkeeperMode
                        ? "Create a campaign request for BuyNex Admin review and customer showcase."
                        : "Create an offer that will be available across the marketplace."
        );
        subHeading.setStyle(
                "-fx-font-size:16px;" +
                "-fx-text-fill:#777277;"
        );

        headingBox.getChildren().addAll(heading, subHeading);

        Region topSpace = new Region();
        HBox.setHgrow(topSpace, Priority.ALWAYS);

        Label draftLabel = new Label(isShopkeeperMode ? "Shopkeeper Request" : "Admin Campaign");
        draftLabel.setPadding(new Insets(8, 16, 8, 16));
        draftLabel.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-background-radius:18;" +
                "-fx-text-fill:#C64A00;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;"
        );

        topBar.getChildren().addAll(
                backButton,
                headingBox,
                topSpace,
                draftLabel
        );

        HBox content = new HBox(25);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30, 60, 40, 60));

        VBox formCard = new VBox(18);
        formCard.setPrefWidth(780);
        formCard.setPadding(new Insets(30));
        formCard.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#E8C7B3;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:1;"
        );

        Label campaignDetails = new Label("Campaign Details");
        campaignDetails.setStyle(
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        // Row 1: Campaign Name & Campaign Title
        HBox nameRow = new HBox(20);
        VBox nameBox = new VBox(6);
        HBox.setHgrow(nameBox, Priority.ALWAYS);
        Label nameLabel = new Label("Campaign Name *");
        nameLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextField campaignName = new TextField();
        campaignName.setPromptText("e.g. Diwali Mega Sale");
        campaignName.setPrefHeight(46);
        campaignName.setStyle("-fx-background-color:#FAF8FA;-fx-border-color:#D8D1D7;-fx-border-radius:7;-fx-font-size:14px;");
        nameBox.getChildren().addAll(nameLabel, campaignName);

        VBox titleBox = new VBox(6);
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        Label titleLabel = new Label("Campaign Title / Headline *");
        titleLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextField campaignTitleField = new TextField();
        campaignTitleField.setPromptText("e.g. Flat 20% OFF on All Essentials");
        campaignTitleField.setPrefHeight(46);
        campaignTitleField.setStyle("-fx-background-color:#FAF8FA;-fx-border-color:#D8D1D7;-fx-border-radius:7;-fx-font-size:14px;");
        titleBox.getChildren().addAll(titleLabel, campaignTitleField);

        nameRow.getChildren().addAll(nameBox, titleBox);

        // Description
        Label descriptionLabel = new Label("Campaign Description *");
        descriptionLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        TextArea description = new TextArea();
        description.setPromptText("Enter campaign description and customer benefits...");
        description.setPrefRowCount(3);
        description.setWrapText(true);
        description.setStyle("-fx-background-color:#FAF8FA;-fx-border-color:#D8D1D7;-fx-border-radius:7;-fx-font-size:14px;");

        // Row 2: Discount Type & Discount Value
        HBox discountRow = new HBox(20);

        VBox discountTypeBox = new VBox(6);
        HBox.setHgrow(discountTypeBox, Priority.ALWAYS);
        Label discountTypeLabel = new Label("Discount Type *");
        discountTypeLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        ComboBox<String> discountType = new ComboBox<>();
        discountType.getItems().addAll(
                "Percentage Discount (%)",
                "Flat Amount Discount (₹)",
                "Free Delivery"
        );
        discountType.setValue("Percentage Discount (%)");
        discountType.setPrefHeight(46);
        discountType.setMaxWidth(Double.MAX_VALUE);
        discountType.setStyle("-fx-font-size:14px;");
        discountTypeBox.getChildren().addAll(discountTypeLabel, discountType);

        VBox discountValueBox = new VBox(6);
        HBox.setHgrow(discountValueBox, Priority.ALWAYS);
        Label discountValueLabel = new Label("Discount Value *");
        discountValueLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextField discountValue = new TextField("20");
        discountValue.setPromptText("Example: 20");
        discountValue.setPrefHeight(46);
        discountValue.setStyle("-fx-font-size:14px;");
        discountValueBox.getChildren().addAll(discountValueLabel, discountValue);

        discountRow.getChildren().addAll(discountTypeBox, discountValueBox);

        // Row 3: Category, Min Order & Max Discount
        HBox categoryRow = new HBox(20);

        VBox categoryBox = new VBox(6);
        HBox.setHgrow(categoryBox, Priority.ALWAYS);
        Label categoryLabel = new Label("Applicable Category *");
        categoryLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll(
                "All Categories",
                "Groceries",
                "Electronics",
                "Fashion",
                "Home Goods",
                "Beauty",
                "Health & Pharmacy",
                "Pet Supplies"
        );
        category.setValue("All Categories");
        category.setPrefHeight(46);
        category.setMaxWidth(Double.MAX_VALUE);
        category.setStyle("-fx-font-size:14px;");
        categoryBox.getChildren().addAll(categoryLabel, category);

        VBox minimumOrderBox = new VBox(6);
        HBox.setHgrow(minimumOrderBox, Priority.ALWAYS);
        Label minimumOrderLabel = new Label("Min Purchase (₹)");
        minimumOrderLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextField minimumOrder = new TextField("199");
        minimumOrder.setPromptText("₹ Example: 199");
        minimumOrder.setPrefHeight(46);
        minimumOrder.setStyle("-fx-font-size:14px;");
        minimumOrderBox.getChildren().addAll(minimumOrderLabel, minimumOrder);

        VBox maxDiscountBox = new VBox(6);
        HBox.setHgrow(maxDiscountBox, Priority.ALWAYS);
        Label maxDiscountLabel = new Label("Max Discount (₹)");
        maxDiscountLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextField maxDiscount = new TextField("500");
        maxDiscount.setPromptText("₹ Example: 500");
        maxDiscount.setPrefHeight(46);
        maxDiscount.setStyle("-fx-font-size:14px;");
        maxDiscountBox.getChildren().addAll(maxDiscountLabel, maxDiscount);

        categoryRow.getChildren().addAll(categoryBox, minimumOrderBox, maxDiscountBox);

        // Row 4: Dates
        HBox dateRow = new HBox(20);

        VBox startDateBox = new VBox(6);
        HBox.setHgrow(startDateBox, Priority.ALWAYS);
        Label startDateLabel = new Label("Start Date *");
        startDateLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        DatePicker startDate = new DatePicker(LocalDate.now());
        startDate.setPrefHeight(46);
        startDate.setMaxWidth(Double.MAX_VALUE);
        startDate.setStyle("-fx-font-size:14px;");
        startDateBox.getChildren().addAll(startDateLabel, startDate);

        VBox endDateBox = new VBox(6);
        HBox.setHgrow(endDateBox, Priority.ALWAYS);
        Label endDateLabel = new Label("End Date *");
        endDateLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        DatePicker endDate = new DatePicker(LocalDate.now().plusDays(10));
        endDate.setPrefHeight(46);
        endDate.setMaxWidth(Double.MAX_VALUE);
        endDate.setStyle("-fx-font-size:14px;");
        endDateBox.getChildren().addAll(endDateLabel, endDate);

        dateRow.getChildren().addAll(startDateBox, endDateBox);

        // Terms and Conditions
        Label termsLabel = new Label("Campaign Terms and Conditions");
        termsLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");
        TextArea termsField = new TextArea("1. Valid on participating shopkeeper products.\n2. Cannot be combined with other promotional codes.\n3. Applicable while inventory lasts.");
        termsField.setPrefRowCount(3);
        termsField.setWrapText(true);
        termsField.setStyle("-fx-background-color:#FAF8FA;-fx-border-color:#D8D1D7;-fx-border-radius:7;-fx-font-size:13px;");

        // Banner Image Upload
        Label imageLabel = new Label("Campaign Banner / Image");
        imageLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        Label selectedFileLabel = new Label("PNG or JPG image. Default banner used if none selected.");
        selectedFileLabel.setStyle("-fx-text-fill:#777277;-fx-font-size:13px;");

        Button chooseImageButton = new Button("Choose Banner Image");
        chooseImageButton.setPrefHeight(42);
        chooseImageButton.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-text-fill:#C64A00;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-font-size:13px;" +
                "-fx-cursor:hand;"
        );

        HBox imageUploadBox = new HBox(18);
        imageUploadBox.setAlignment(Pos.CENTER_LEFT);
        imageUploadBox.setPadding(new Insets(12, 16, 12, 16));
        imageUploadBox.setStyle(
                "-fx-background-color:#FAF8FA;" +
                "-fx-border-color:#D8D1D7;" +
                "-fx-border-style:dashed;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );
        imageUploadBox.getChildren().addAll(chooseImageButton, selectedFileLabel);

        chooseImageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Campaign Banner");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            selectedImage = fileChooser.showOpenDialog(Homepage.HomepageStage);
            if (selectedImage != null) {
                selectedFileLabel.setText("Selected: " + selectedImage.getName());
                selectedFileLabel.setStyle("-fx-text-fill:#2E7D32;-fx-font-weight:bold;");
                customImageLocalPath = selectedImage.toURI().toString();
            }
        });

        // Submit & Cancel Buttons
        Region buttonSpace = new Region();
        HBox.setHgrow(buttonSpace, Priority.ALWAYS);

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(120, 46);
        cancelButton.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#C84B00;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-text-fill:#C84B00;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;" +
                "-fx-cursor:hand;"
        );

        Button createButton = new Button(isShopkeeperMode ? "Submit Campaign Request" : "Create Campaign");
        createButton.setPrefSize(230, 46);
        createButton.setStyle(
                "-fx-background-color:#C84B00;" +
                "-fx-background-radius:7;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:14px;" +
                "-fx-cursor:hand;"
        );

        HBox formButtons = new HBox(15);
        formButtons.setAlignment(Pos.CENTER_RIGHT);
        formButtons.getChildren().addAll(buttonSpace, cancelButton, createButton);

        formCard.getChildren().addAll(
                campaignDetails,
                nameRow,
                descriptionLabel,
                description,
                discountRow,
                categoryRow,
                dateRow,
                termsLabel,
                termsField,
                imageLabel,
                imageUploadBox,
                formButtons
        );

        // Right Preview Card
        VBox previewCard = new VBox(16);
        previewCard.setPrefWidth(330);
        previewCard.setPadding(new Insets(25));
        previewCard.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#C84B00;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:2;"
        );

        Label previewTitle = new Label("Campaign Preview");
        previewTitle.setStyle("-fx-font-size:23px;-fx-font-weight:bold;");

        Label previewStatus = new Label(isShopkeeperMode ? "PENDING REVIEW" : "DRAFT");
        previewStatus.setPadding(new Insets(6, 12, 6, 12));
        previewStatus.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-background-radius:15;" +
                "-fx-text-fill:#C84B00;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;"
        );

        Label previewName = new Label("Campaign name");
        previewName.setWrapText(true);
        previewName.setStyle("-fx-font-size:20px;-fx-font-weight:bold;-fx-text-fill:#1E1E24;");

        Label previewHeadline = new Label("Campaign headline...");
        previewHeadline.setWrapText(true);
        previewHeadline.setStyle("-fx-font-size:13px;-fx-font-weight:bold;-fx-text-fill:#B84300;");

        Label previewDescription = new Label("Campaign description will appear here.");
        previewDescription.setWrapText(true);
        previewDescription.setStyle("-fx-text-fill:#777277;-fx-font-size:13px;");

        Label previewDiscount = new Label("Discount: 20% OFF");
        previewDiscount.setStyle("-fx-font-size:16px;-fx-font-weight:bold;-fx-text-fill:#C84B00;");

        Label previewCategory = new Label("Category: All Categories");
        previewCategory.setStyle("-fx-font-size:13px;");

        Label previewDates = new Label("Campaign dates: " + startDate.getValue() + " to " + endDate.getValue());
        previewDates.setStyle("-fx-font-size:13px;");
        previewDates.setWrapText(true);

        Label previewMinMax = new Label("Min: ₹199 | Max Discount: ₹500");
        previewMinMax.setStyle("-fx-font-size:12px;-fx-text-fill:#666666;");

        previewCard.getChildren().addAll(
                previewTitle,
                previewStatus,
                previewName,
                previewHeadline,
                previewDescription,
                previewDiscount,
                previewCategory,
                previewDates,
                previewMinMax
        );

        // Dynamic Listeners for preview
        campaignName.textProperty().addListener((obs, o, n) -> {
            previewName.setText((n == null || n.trim().isEmpty()) ? "Campaign name" : n.trim());
        });

        campaignTitleField.textProperty().addListener((obs, o, n) -> {
            previewHeadline.setText((n == null || n.trim().isEmpty()) ? "Campaign headline..." : n.trim());
        });

        description.textProperty().addListener((obs, o, n) -> {
            previewDescription.setText((n == null || n.trim().isEmpty()) ? "Campaign description will appear here." : n.trim());
        });

        discountValue.textProperty().addListener((obs, o, n) -> {
            String typeStr = discountType.getValue() != null && discountType.getValue().contains("Flat") ? "₹" : "%";
            previewDiscount.setText("Discount: " + (n.trim().isEmpty() ? "--" : (n.trim() + typeStr + " OFF")));
        });

        category.setOnAction(e -> previewCategory.setText("Category: " + category.getValue()));
        startDate.setOnAction(e -> previewDates.setText("Campaign dates: " + startDate.getValue() + " to " + endDate.getValue()));
        endDate.setOnAction(e -> previewDates.setText("Campaign dates: " + startDate.getValue() + " to " + endDate.getValue()));

        minimumOrder.textProperty().addListener((obs, o, n) -> {
            previewMinMax.setText("Min: ₹" + minimumOrder.getText() + " | Max Discount: ₹" + maxDiscount.getText());
        });
        maxDiscount.textProperty().addListener((obs, o, n) -> {
            previewMinMax.setText("Min: ₹" + minimumOrder.getText() + " | Max Discount: ₹" + maxDiscount.getText());
        });

        // Submit Action
        createButton.setOnAction(e -> {
            String cName = campaignName.getText().trim();
            String cTitle = campaignTitleField.getText().trim();
            String desc = description.getText().trim();
            String dValStr = discountValue.getText().trim();

            if (cName.isEmpty() || desc.isEmpty() || dValStr.isEmpty()
                    || startDate.getValue() == null || endDate.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Complete all required fields");
                alert.setContentText("Please fill all fields marked with *.");
                alert.showAndWait();
                return;
            }

            if (endDate.getValue().isBefore(startDate.getValue())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("End date is not valid");
                alert.setContentText("End date must be after or equal to the start date.");
                alert.showAndWait();
                return;
            }

            double disc = 10.0;
            try {
                disc = Double.parseDouble(dValStr);
            } catch (Exception ex) {
                disc = 10.0;
            }

            double minOrd = 0.0;
            try {
                minOrd = Double.parseDouble(minimumOrder.getText().trim());
            } catch (Exception ex) {}

            double maxDisc = 0.0;
            try {
                maxDisc = Double.parseDouble(maxDiscount.getText().trim());
            } catch (Exception ex) {}

            String dType = (discountType.getValue() != null && discountType.getValue().contains("Flat"))
                    ? "FLAT_AMOUNT" : "PERCENTAGE";

            // Resolve Shopkeeper Information
            String shopId = "SHOP_" + System.currentTimeMillis();
            String sName = "BuyNex Merchant";
            String stName = "BuyNex Partner Store";

            if (ViewConstants.shopkeeperModel != null) {
                if (ViewConstants.shopkeeperModel.getShopkeeperUid() != null) {
                    shopId = ViewConstants.shopkeeperModel.getShopkeeperUid();
                }
                if (ViewConstants.shopkeeperModel.getOwnerNameValue() != null) {
                    sName = ViewConstants.shopkeeperModel.getOwnerNameValue();
                }
                if (ViewConstants.shopkeeperModel.getShopNameValue() != null) {
                    stName = ViewConstants.shopkeeperModel.getShopNameValue();
                }
            }

            // Build Campaign Model
            CampaignModel campaignModel = new CampaignModel();
            campaignModel.setTitle(cName);
            campaignModel.setCampaignTitle(cTitle.isEmpty() ? cName : cTitle);
            campaignModel.setDescription(desc);
            campaignModel.setImageUrl(customImageLocalPath);
            campaignModel.setStartDate(startDate.getValue().toString());
            campaignModel.setEndDate(endDate.getValue().toString());
            campaignModel.setDiscount(disc);
            campaignModel.setDiscountType(dType);
            campaignModel.setApplicableCategories(category.getValue() != null ? category.getValue() : "All Categories");
            campaignModel.setMinimumOrderAmount(minOrd);
            campaignModel.setMaximumDiscount(maxDisc);
            campaignModel.setTermsAndConditions(termsField.getText());
            campaignModel.setShopkeeperId(shopId);
            campaignModel.setShopkeeperName(sName);
            campaignModel.setStoreName(stName);
            campaignModel.setApplicableProducts(new ArrayList<>());

            if (isShopkeeperMode) {
                // DO NOT publish directly. Always PENDING.
                campaignModel.setStatus("PENDING");
                campaignModel.setRequestType("CAMPAIGN_REQUEST");

                CampaignController.submitCampaignRequest(campaignModel);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Campaign Request Submitted");
                successAlert.setHeaderText("Request Sent for Admin Approval");
                successAlert.setContentText(
                        "Your campaign '" + cName + "' was successfully submitted to BuyNex Admin.\n"
                        + "Status: PENDING\n\n"
                        + "Once approved by Admin, it will automatically go live on the Customer Portal."
                );
                successAlert.showAndWait();

                // Navigate back to Shopkeeper Offers
                Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene());

            } else {
                // Admin creates campaign directly
                campaignModel.setStatus("APPROVED");
                campaignModel.setApprovedBy("Admin");
                campaignModel.setApprovedAt(LocalDate.now().toString());

                CampaignController.submitCampaignRequest(campaignModel);
                CampaignController.approveCampaign(campaignModel.getCampaignId(), "Admin");

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Campaign Created");
                successAlert.setHeaderText("Campaign Created Successfully");
                successAlert.setContentText("'" + cName + "' is now live for all customers on BuyNex!");
                successAlert.showAndWait();

                OfferPage offerPage = new OfferPage();
                Homepage.HomepageStage.setScene(offerPage.getUserScene());
            }
        });

        Runnable navigateBack = () -> {
            if (isShopkeeperMode) {
                Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene());
            } else {
                OfferPage offerPage = new OfferPage();
                Homepage.HomepageStage.setScene(offerPage.getUserScene());
            }
        };

        backButton.setOnMouseClicked(e -> navigateBack.run());
        cancelButton.setOnAction(e -> navigateBack.run());

        content.getChildren().addAll(formCard, previewCard);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background:#F9F7FB;" +
                "-fx-background-color:#F9F7FB;" +
                "-fx-border-color:transparent;"
        );

        root.getChildren().addAll(topBar, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        root.setStyle("-fx-background-color: #eee5df;");

        return new Scene(root, 1550, 850);
    }
}
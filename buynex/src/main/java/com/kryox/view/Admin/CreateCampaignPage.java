package com.kryox.view.Admin;

import java.io.File;

import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class CreateCampaignPage {

    File selectedImage;

    public Scene getCampaignScene() {

        VBox root = new VBox();
        root.setStyle("-fx-background-color:#F9F7FB;");

        // =====================================================
        // TOP BAR
        // =====================================================

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

        Label heading = new Label("Create Global Campaign");
        heading.setStyle(
                "-fx-font-size:31px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#242124;"
        );

        Label subHeading = new Label(
                "Create an offer that will be available across the marketplace."
        );
        subHeading.setStyle(
                "-fx-font-size:18px;" +
                "-fx-text-fill:#777277;"
        );

        headingBox.getChildren().addAll(heading, subHeading);

        Region topSpace = new Region();
        HBox.setHgrow(topSpace, Priority.ALWAYS);

        Label draftLabel = new Label("New Campaign");
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

        // =====================================================
        // FORM CONTAINER
        // =====================================================

        HBox content = new HBox(25);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30, 60, 40, 60));

        // =====================================================
        // LEFT FORM
        // =====================================================

        VBox formCard = new VBox(20);
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

        Label nameLabel = new Label("Campaign Name *");
        nameLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        TextField campaignName = new TextField();
        campaignName.setPromptText("Example: Summer Tech Fest");
        campaignName.setPrefHeight(50);
        campaignName.setStyle(
                "-fx-background-color:#FAF8FA;" +
                "-fx-border-color:#D8D1D7;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-padding:0 14;" +
                "-fx-font-size:14px;"
        );

        Label descriptionLabel = new Label("Description *");
        descriptionLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        TextArea description = new TextArea();
        description.setPromptText("Enter campaign description...");
        description.setPrefRowCount(3);
        description.setWrapText(true);
        description.setStyle(
                "-fx-background-color:#FAF8FA;" +
                "-fx-border-color:#D8D1D7;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-font-size:14px;"
        );

        // =====================================================
        // DISCOUNT ROW
        // =====================================================

        HBox discountRow = new HBox(20);

        VBox discountTypeBox = new VBox(8);
        HBox.setHgrow(discountTypeBox, Priority.ALWAYS);

        Label discountTypeLabel = new Label("Discount Type *");
        discountTypeLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        ComboBox<String> discountType = new ComboBox<>();
        discountType.getItems().addAll(
                "Percentage Discount",
                "Flat Amount Discount",
                "Free Delivery"
        );
        discountType.setPromptText("Select discount type");
        discountType.setPrefHeight(50);
        discountType.setMaxWidth(Double.MAX_VALUE);
        discountType.setStyle("-fx-font-size:14px;");

        discountTypeBox.getChildren().addAll(
                discountTypeLabel,
                discountType
        );

        VBox discountValueBox = new VBox(8);
        HBox.setHgrow(discountValueBox, Priority.ALWAYS);

        Label discountValueLabel = new Label("Discount Value *");
        discountValueLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        TextField discountValue = new TextField();
        discountValue.setPromptText("Example: 15");
        discountValue.setPrefHeight(50);
        discountValue.setStyle("-fx-font-size:14px;");

        discountValueBox.getChildren().addAll(
                discountValueLabel,
                discountValue
        );

        discountRow.getChildren().addAll(
                discountTypeBox,
                discountValueBox
        );

        // =====================================================
        // CATEGORY AND MINIMUM ORDER
        // =====================================================

        HBox categoryRow = new HBox(20);

        VBox categoryBox = new VBox(8);
        HBox.setHgrow(categoryBox, Priority.ALWAYS);

        Label categoryLabel = new Label("Category *");
        categoryLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        ComboBox<String> category = new ComboBox<>();
        category.getItems().addAll(
                "All Categories",
                "Electronics",
                "Groceries",
                "Fashion",
                "Home Goods",
                "Beauty",
                "Pet Supplies"
        );
        category.setPromptText("Select category");
        category.setPrefHeight(50);
        category.setMaxWidth(Double.MAX_VALUE);
        category.setStyle("-fx-font-size:14px;");

        categoryBox.getChildren().addAll(categoryLabel, category);

        VBox minimumOrderBox = new VBox(8);
        HBox.setHgrow(minimumOrderBox, Priority.ALWAYS);

        Label minimumOrderLabel = new Label("Minimum Order Amount");
        minimumOrderLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        TextField minimumOrder = new TextField();
        minimumOrder.setPromptText("₹ Example: 500");
        minimumOrder.setPrefHeight(50);
        minimumOrder.setStyle("-fx-font-size:14px;");

        minimumOrderBox.getChildren().addAll(
                minimumOrderLabel,
                minimumOrder
        );

        categoryRow.getChildren().addAll(
                categoryBox,
                minimumOrderBox
        );

        // =====================================================
        // DATE ROW
        // =====================================================

        HBox dateRow = new HBox(20);

        VBox startDateBox = new VBox(8);
        HBox.setHgrow(startDateBox, Priority.ALWAYS);

        Label startDateLabel = new Label("Start Date *");
        startDateLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        DatePicker startDate = new DatePicker();
        startDate.setPrefHeight(50);
        startDate.setMaxWidth(Double.MAX_VALUE);
        startDate.setStyle("-fx-font-size:14px;");

        startDateBox.getChildren().addAll(
                startDateLabel,
                startDate
        );

        VBox endDateBox = new VBox(8);
        HBox.setHgrow(endDateBox, Priority.ALWAYS);

        Label endDateLabel = new Label("End Date *");
        endDateLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        DatePicker endDate = new DatePicker();
        endDate.setPrefHeight(50);
        endDate.setMaxWidth(Double.MAX_VALUE);
        endDate.setStyle("-fx-font-size:14px;");

        endDateBox.getChildren().addAll(
                endDateLabel,
                endDate
        );

        dateRow.getChildren().addAll(startDateBox, endDateBox);

        // =====================================================
        // APPLY TO SHOPS
        // =====================================================

        Label applyLabel = new Label("Apply Campaign To *");
        applyLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        ToggleGroup shopGroup = new ToggleGroup();

        RadioButton allShops = new RadioButton(
                "All verified shops in the marketplace"
        );
        allShops.setToggleGroup(shopGroup);
        allShops.setSelected(true);
        allShops.setStyle("-fx-font-size:14px;");

        RadioButton selectedShops = new RadioButton(
                "Only selected shops"
        );
        selectedShops.setToggleGroup(shopGroup);
        selectedShops.setStyle("-fx-font-size:14px;");

        VBox applyBox = new VBox(13);
        applyBox.setPadding(new Insets(16));
        applyBox.setStyle(
                "-fx-background-color:#FAF8FA;" +
                "-fx-background-radius:8;" +
                "-fx-border-color:#E1DBE0;" +
                "-fx-border-radius:8;"
        );
        applyBox.getChildren().addAll(allShops, selectedShops);

        // =====================================================
        // IMAGE UPLOAD
        // =====================================================

        Label imageLabel = new Label("Campaign Banner");
        imageLabel.setStyle("-fx-font-weight:bold;-fx-font-size:14px;");

        Label selectedFileLabel = new Label(
                "PNG or JPG image. Maximum size 5 MB."
        );
        selectedFileLabel.setStyle("-fx-text-fill:#777277;-fx-font-size:13px;");

        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setPrefHeight(46);
        chooseImageButton.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-text-fill:#C64A00;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7;" +
                "-fx-font-size:14px;" +
                "-fx-cursor:hand;"
        );

        HBox imageUploadBox = new HBox(18);
        imageUploadBox.setAlignment(Pos.CENTER_LEFT);
        imageUploadBox.setPadding(new Insets(16));
        imageUploadBox.setStyle(
                "-fx-background-color:#FAF8FA;" +
                "-fx-border-color:#D8D1D7;" +
                "-fx-border-style:dashed;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );

        imageUploadBox.getChildren().addAll(
                chooseImageButton,
                selectedFileLabel
        );

        chooseImageButton.setOnAction(e -> {

            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Select Campaign Banner");

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Image Files",
                            "*.png",
                            "*.jpg",
                            "*.jpeg"
                    )
            );

            selectedImage = fileChooser.showOpenDialog(
                    Homepage.HomepageStage
            );

            if (selectedImage != null) {
                selectedFileLabel.setText(selectedImage.getName());
                selectedFileLabel.setStyle(
                        "-fx-text-fill:#2E7D32;" +
                        "-fx-font-weight:bold;"
                );
            }
        });

        // =====================================================
        // FORM BUTTONS
        // =====================================================

        Region buttonSpace = new Region();
        HBox.setHgrow(buttonSpace, Priority.ALWAYS);

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(130, 50);
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

        Button createButton = new Button("Create Campaign");
        createButton.setPrefSize(185, 50);
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
        formButtons.getChildren().addAll(
                buttonSpace,
                cancelButton,
                createButton
        );

        formCard.getChildren().addAll(
                campaignDetails,
                nameLabel,
                campaignName,
                descriptionLabel,
                description,
                discountRow,
                categoryRow,
                dateRow,
                applyLabel,
                applyBox,
                imageLabel,
                imageUploadBox,
                formButtons
        );

        // =====================================================
        // RIGHT PREVIEW
        // =====================================================

        VBox previewCard = new VBox(16);
        previewCard.setPrefWidth(320);
        previewCard.setPadding(new Insets(25));
        previewCard.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:16;" +
                "-fx-border-color:#C84B00;" +
                "-fx-border-radius:16;" +
                "-fx-border-width:2;"
        );

        Label previewTitle = new Label("Campaign Preview");
        previewTitle.setStyle(
                "-fx-font-size:23px;" +
                "-fx-font-weight:bold;"
        );

        Label previewStatus = new Label("DRAFT");
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
        previewName.setStyle(
                "-fx-font-size:21px;" +
                "-fx-font-weight:bold;"
        );

        Label previewDescription = new Label(
                "Campaign description will appear here."
        );
        previewDescription.setWrapText(true);
        previewDescription.setStyle("-fx-text-fill:#777277;-fx-font-size:14px;");

        Label previewDiscount = new Label("Discount: --");
        previewDiscount.setStyle(
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#C84B00;"
        );

        Label previewCategory = new Label("Category: --");
        previewCategory.setStyle("-fx-font-size:14px;");
        Label previewDates = new Label("Campaign dates: --");
        previewDates.setStyle("-fx-font-size:14px;");
        previewDates.setWrapText(true);

        previewCard.getChildren().addAll(
                previewTitle,
                previewStatus,
                previewName,
                previewDescription,
                previewDiscount,
                previewCategory,
                previewDates
        );

        // =====================================================
        // LIVE PREVIEW
        // =====================================================

        campaignName.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue.trim().isEmpty()) {
                        previewName.setText("Campaign name");
                    } else {
                        previewName.setText(newValue);
                    }
                }
        );

        description.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue.trim().isEmpty()) {
                        previewDescription.setText(
                                "Campaign description will appear here."
                        );
                    } else {
                        previewDescription.setText(newValue);
                    }
                }
        );

        discountValue.textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue.trim().isEmpty()) {
                        previewDiscount.setText("Discount: --");
                    } else {
                        previewDiscount.setText(
                                "Discount: " + newValue
                        );
                    }
                }
        );

        category.setOnAction(e -> {
            previewCategory.setText(
                    "Category: " + category.getValue()
            );
        });

        // =====================================================
        // CREATE CAMPAIGN VALIDATION
        // =====================================================

        createButton.setOnAction(e -> {

            if (campaignName.getText().trim().isEmpty()
                    || description.getText().trim().isEmpty()
                    || discountType.getValue() == null
                    || discountValue.getText().trim().isEmpty()
                    || category.getValue() == null
                    || startDate.getValue() == null
                    || endDate.getValue() == null) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Complete all required fields");
                alert.setContentText(
                        "Please fill all fields marked with *."
                );
                alert.showAndWait();

                return;
            }

            if (endDate.getValue().isBefore(startDate.getValue())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Date");
                alert.setHeaderText("End date is not valid");
                alert.setContentText(
                        "End date must be after the start date."
                );
                alert.showAndWait();

                return;
            }

            Alert successAlert = new Alert(
                    Alert.AlertType.INFORMATION
            );

            successAlert.setTitle("Campaign Created");
            successAlert.setHeaderText(
                    "Campaign created successfully"
            );
            successAlert.setContentText(
                    campaignName.getText()
                            + " is now available in campaigns."
            );

            successAlert.showAndWait();

            
        });

        // =====================================================
        // BACK AND CANCEL
        // =====================================================

        backButton.setOnMouseClicked(e -> {

            OfferPage offerPage = new OfferPage();

            Homepage.HomepageStage.setScene(
                    offerPage.getUserScene()
            );
        });

        cancelButton.setOnAction(e -> {

            OfferPage offerPage = new OfferPage();

            Homepage.HomepageStage.setScene(
                    offerPage.getUserScene()
            );
        });

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

        return new Scene(root, 1550, 850);
    }
}
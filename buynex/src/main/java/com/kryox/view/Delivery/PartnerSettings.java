package com.kryox.view.Delivery;

import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;

import java.awt.Desktop;
import java.net.URI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;

public class PartnerSettings {

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String SIDEBAR_BG = "#ffffff";

    // =========================================================================
    // DYNAMIC DATA MODEL FOR PARTNER SETTINGS
    // =========================================================================
    public static class SettingsData {
        public String partnerName;
        public String partnerTier;
        public boolean isShiftActive = true;

        // Personal Details
        public String fullName;
        public String email;
        public String phone;

        // Vehicle & Assets
        public String vehicleType;
        public String vehicleIdNumber;
        public String insuranceStatus;

        // Compliance & Verification Documents
        public String licenseStatus;
        public String governmentIdStatus;
        public String rcBookStatus;
        public boolean isOverallApproved;

        // Document URLs
        public String licenseDocUrl;
        public String governmentIdDocUrl;
        public String rcBookDocUrl;

        // Rating & Performance
        public double ratingScore;
        public String ratingQuote;
        public int totalSuccessfulDeliveries;
        public double completionRate;

        // App & Alert Preferences
        public boolean orderSoundAlerts = true;
        public boolean surgeNotification = true;
        public boolean voiceNavigation = true;

        // Payout Method
        public String bankName;
        public String maskedAccountNumber;
        public String accountHolderName;

        public SettingsData() {
            this.partnerName           = PartnerConstants.FULL_NAME;
            this.fullName              = PartnerConstants.FULL_NAME;
            this.email                 = PartnerConstants.EMAIL;
            this.phone                 = PartnerConstants.PHONE;
            this.vehicleType           = PartnerConstants.VEHICLE_TYPE;
            this.vehicleIdNumber       = PartnerConstants.VEHICLE_NUMBER;
            this.partnerTier           = PartnerConstants.PARTNER_TIER;

            this.bankName              = PartnerConstants.BANK_NAME;
            this.maskedAccountNumber   = PartnerConstants.MASKED_ACCOUNT;
            this.accountHolderName     = PartnerConstants.FULL_NAME;

            this.insuranceStatus       = PartnerConstants.INSURANCE_STATUS;
            this.licenseStatus         = PartnerConstants.LICENSE_STATUS;
            this.governmentIdStatus    = PartnerConstants.GOVERNMENT_ID_STATUS;
            this.rcBookStatus          = PartnerConstants.RC_BOOK_STATUS;

            this.licenseDocUrl         = PartnerConstants.LICENSE_DOC_URL;
            this.governmentIdDocUrl    = PartnerConstants.ID_CARD_URL;
            this.rcBookDocUrl          = PartnerConstants.RC_BOOK_URL;

            this.ratingScore           = PartnerConstants.RATING_SCORE;
            this.ratingQuote           = PartnerConstants.RATING_QUOTE;
            this.totalSuccessfulDeliveries = PartnerConstants.TOTAL_DELIVERIES;
            this.completionRate        = PartnerConstants.COMPLETION_RATE;

            this.isOverallApproved     = PartnerConstants.IS_ADMIN_APPROVED || 
                    (isStatusApproved(licenseStatus) 
                     && isStatusApproved(governmentIdStatus) 
                     && isStatusApproved(rcBookStatus));
        }

        private static boolean isStatusApproved(String stat) {
            return stat != null && (stat.equalsIgnoreCase("Approved") || stat.equalsIgnoreCase("Verified") || stat.equalsIgnoreCase("Active"));
        }
    }

    public static Scene partnerSettingsScene() {
        return partnerSettingsScene(new SettingsData());
    }

    public static Scene partnerSettingsScene(SettingsData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Search Header
        root.setTop(createTopHeader(data));

        // 2. Left Navigation Sidebar
        root.setLeft(createSidebar(data));

        // 3. Main Settings Content
        VBox profileContent = createProfileContent(data);
        ScrollPane scrollPane = new ScrollPane(profileContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: " + BG_COLOR + ";" +
                "-fx-border-color: transparent;"
        );

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static BorderPane createTopHeader(SettingsData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 35 0 30;"
        );

        HBox searchContainer = new HBox(8);
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setMaxWidth(380);
        searchContainer.setPrefHeight(36);
        searchContainer.setPadding(new Insets(0, 12, 0, 12));
        searchContainer.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 20;" +
                "-fx-background-radius: 20;"
        );

        Label searchIcon = new Label("🔍");
        searchIcon.setStyle("-fx-font-size: 11px; -fx-text-fill: #9ca3af;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search settings, vehicle, profile...");
        searchField.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-font-size: 12px;" +
                "-fx-prompt-text-fill: #9ca3af;"
        );
        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchContainer.getChildren().addAll(searchIcon, searchField);
        topBar.setCenter(searchContainer);

        HBox rightIcons = new HBox(16);
        rightIcons.setAlignment(Pos.CENTER_RIGHT);

        Label notifIcon = new Label("🔔");
        notifIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b5563; -fx-cursor: hand;");
        notifIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNotifications.partnerNotificationsScene("SETTINGS"));
            }
        });

        Label chatIcon = new Label("💬");
        chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #4b5563; -fx-cursor: hand;");
        chatIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerChatSupport.partnerChatSupportScene("SETTINGS"));
            }
        });

        StackPane userAvatarPane = createAvatarNode(16);
        userAvatarPane.setStyle("-fx-cursor: hand;");

        ContextMenu userMenu = new ContextMenu();
        userMenu.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-radius: 8;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        );

        MenuItem itemProfile = new MenuItem("👤   View Profile & Settings");
        itemProfile.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemProfile.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerProfile.partnerProfileScene(data));
            }
        });

        MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
        itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        MenuItem itemLogout = new MenuItem("↪   Logout");
        itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemLogout.setOnAction(e -> {
            DeliveryPartnerDAO.stopListening();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

        userAvatarPane.setOnMouseClicked(e -> {
            if (!userMenu.isShowing()) {
                userMenu.show(userAvatarPane, Side.BOTTOM, -120, 8);
            } else {
                userMenu.hide();
            }
        });

        rightIcons.getChildren().addAll(notifIcon, chatIcon, userAvatarPane);
        topBar.setRight(rightIcons);

        return topBar;
    }

    private static VBox createSidebar(SettingsData data) {
        VBox sidebar = new VBox(12);
        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.setPadding(new Insets(20, 16, 25, 16));
        sidebar.setStyle(
                "-fx-background-color: " + SIDEBAR_BG + ";" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 1 0 0;"
        );

        Text logo = new Text("BuyNeX");
        logo.setStyle("-fx-font-size: 26px; -fx-fill: " + ORANGE_GRADIENT + "; -fx-font-weight: bold;");
        VBox logoBox = new VBox(logo);
        logoBox.setPadding(new Insets(0, 0, 15, 8));

        Button btnDashboard = createSidebarNavButton("▤   Dashboard", false);
        btnDashboard.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
            }
        });

        Button btnDeliveries = createSidebarNavButton("📦   My Deliveries", false);
        btnDeliveries.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
            }
        });

        Button btnNavigation = createSidebarNavButton("🧭   Navigation", false);
        btnNavigation.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNavigation.partnerNavigationScene());
            }
        });

        Button btnEarnings = createSidebarNavButton("💵   Earnings", false);
        btnEarnings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
            }
        });

        Button btnAvailability = createSidebarNavButton("⏱   Availability", false);
        btnAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        Button btnSettings = createSidebarNavButton("⚙   Settings", true);
        btnSettings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerSettingsScene(data));
            }
        });

        VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability, btnSettings);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox profileCard = new VBox(4);
        profileCard.setPadding(new Insets(10, 12, 10, 12));
        profileCard.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-background-radius: 10;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 10;" +
                "-fx-cursor: hand;"
        );

        HBox userBox = new HBox(8);
        userBox.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatarNode(14);

        VBox userDetails = new VBox(1);
        Label userName = new Label(data.partnerName);
        userName.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label userTier = new Label(data.partnerTier);
        userTier.setStyle("-fx-font-size: 9px; -fx-text-fill: #6b7280;");
        userDetails.getChildren().addAll(userName, userTier);
        userBox.getChildren().addAll(avatar, userDetails);
        profileCard.getChildren().add(userBox);

        profileCard.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerProfile.partnerProfileScene(data));
            }
        });

        Button btnLogout = new Button("↪   Logout");
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setAlignment(Pos.CENTER_LEFT);
        btnLogout.setPrefHeight(34);
        btnLogout.setStyle("-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
        btnLogout.setOnAction(e -> {
            DeliveryPartnerDAO.stopListening();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        VBox bottomNav = new VBox(6, profileCard, btnLogout);
        sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
        return sidebar;
    }

    private static VBox createProfileContent(SettingsData data) {
        VBox main = new VBox(22);
        main.setPadding(new Insets(26, 35, 60, 35));
        main.setFillWidth(true);

        VBox titleBox = new VBox(3);
        Text title = new Text("Partner Settings & Preferences");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-fill: #111827;");
        Text subTitle = new Text("Manage your active identity, vehicle compliance, notifications, and direct payouts.");
        subTitle.setStyle("-fx-font-size: 12px; -fx-fill: #6b7280;");
        titleBox.getChildren().addAll(title, subTitle);

        HBox columns = new HBox(22);
        columns.setFillHeight(true);

        VBox leftCol = new VBox(18);
        leftCol.getChildren().addAll(
                createShiftAvailabilityCard(data),
                createPersonalAndVehicleCard(data),
                createComplianceCard(data),
                createAppPreferencesCard(data)
        );
        HBox.setHgrow(leftCol, Priority.ALWAYS);

        VBox rightCol = new VBox(18);
        rightCol.setPrefWidth(310);
        rightCol.setMinWidth(310);
        rightCol.setMaxWidth(310);
        rightCol.getChildren().addAll(
                createRatingCard(data),
                createPayoutMethodCard(data),
                createHelpSupportCard()
        );
        HBox.setHgrow(rightCol, Priority.NEVER);

        columns.getChildren().addAll(leftCol, rightCol);
        main.getChildren().addAll(titleBox, columns);
        return main;
    }

    private static VBox createShiftAvailabilityCard(SettingsData data) {
        VBox card = createCard();
        BorderPane row = new BorderPane();

        VBox textPart = new VBox(3);
        HBox badgeBox = new HBox(5);
        badgeBox.setAlignment(Pos.CENTER_LEFT);
        Circle greenDot = new Circle(4, data.isShiftActive ? Color.web("#22c55e") : Color.web("#9ca3af"));
        Label badge = new Label(data.isShiftActive ? "Active & Accepting Orders" : "Shift Offline");
        badge.setStyle("-fx-font-size: 10px; -fx-text-fill: " + (data.isShiftActive ? "#15803d;" : "#9ca3af;") + " -fx-font-weight: bold;");
        badgeBox.getChildren().addAll(greenDot, badge);

        Label cardTitle = new Label("Shift Availability Status");
        cardTitle.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label cardSub = new Label("Toggle to receive instant delivery orders and dispatch requests.");
        cardSub.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        textPart.getChildren().addAll(badgeBox, cardTitle, cardSub);
        row.setLeft(textPart);

        HBox toggleBox = new HBox(10);
        toggleBox.setAlignment(Pos.CENTER_RIGHT);
        Label onlineLbl = new Label("Duty\nStatus");
        onlineLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280; -fx-text-alignment: right;");

        StackPane switchTrack = new StackPane();
        Rectangle track = new Rectangle(42, 22);
        track.setArcWidth(22);
        track.setArcHeight(22);
        track.setFill(data.isShiftActive ? Color.web(ORANGE_PRIMARY) : Color.web("#d1d5db"));

        Circle thumb = new Circle(8, Color.WHITE);
        StackPane.setAlignment(thumb, data.isShiftActive ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        StackPane.setMargin(thumb, new Insets(0, 3, 0, 3));
        switchTrack.getChildren().addAll(track, thumb);
        switchTrack.setStyle("-fx-cursor: hand;");

        switchTrack.setOnMouseClicked(e -> {
            data.isShiftActive = !data.isShiftActive;
            track.setFill(data.isShiftActive ? Color.web(ORANGE_PRIMARY) : Color.web("#d1d5db"));
            StackPane.setAlignment(thumb, data.isShiftActive ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
            greenDot.setFill(data.isShiftActive ? Color.web("#22c55e") : Color.web("#9ca3af"));
            badge.setText(data.isShiftActive ? "Active & Accepting Orders" : "Shift Offline");
            badge.setStyle("-fx-font-size: 10px; -fx-text-fill: " + (data.isShiftActive ? "#15803d;" : "#9ca3af;") + " -fx-font-weight: bold;");
        });

        toggleBox.getChildren().addAll(onlineLbl, switchTrack);
        row.setRight(toggleBox);

        card.getChildren().add(row);
        return card;
    }

    private static VBox createPersonalAndVehicleCard(SettingsData data) {
        VBox card = createCard();

        HBox split = new HBox(24);
        split.setFillHeight(true);

        VBox personal = new VBox(8);
        BorderPane personalHeader = new BorderPane();
        Label pTitle = new Label("Personal Information");
        pTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label editLbl = new Label("Edit Profile");
        editLbl.setStyle("-fx-font-size: 11px; -fx-text-fill: " + ORANGE_PRIMARY + "; -fx-font-weight: bold; -fx-cursor: hand;");
        editLbl.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerProfile.partnerProfileScene(data));
            }
        });
        personalHeader.setLeft(pTitle);
        personalHeader.setRight(editLbl);

        VBox pFields = new VBox(6);
        pFields.getChildren().addAll(
                createDetailRow("Full Name", data.fullName),
                createDetailRow("Email Address", data.email),
                createDetailRow("Contact Phone", data.phone)
        );
        personal.getChildren().addAll(personalHeader, pFields);
        HBox.setHgrow(personal, Priority.ALWAYS);

        Region sep = new Region();
        sep.setPrefWidth(1);
        sep.setStyle("-fx-background-color: #f0edf2;");

        VBox vehicle = new VBox(8);
        BorderPane vehicleHeader = new BorderPane();
        Label vTitle = new Label("Vehicle & Equipment");
        vTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label vIcon = new Label("🛵");
        vIcon.setStyle("-fx-font-size: 13px;");
        vehicleHeader.setLeft(vTitle);
        vehicleHeader.setRight(vIcon);

        VBox vFields = new VBox(6);
        vFields.getChildren().addAll(
                createDetailRow("Vehicle Model", data.vehicleType),
                createDetailRow("Registration No.", data.vehicleIdNumber),
                createDetailRowWithBadge("Insurance Coverage", data.insuranceStatus)
        );
        vehicle.getChildren().addAll(vehicleHeader, vFields);
        HBox.setHgrow(vehicle, Priority.ALWAYS);

        split.getChildren().addAll(personal, sep, vehicle);
        card.getChildren().add(split);
        return card;
    }

    private static VBox createComplianceCard(SettingsData data) {
        VBox card = createCard();

        BorderPane header = new BorderPane();
        Label title = new Label("Compliance & Verified Documents");
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label statusBadge = new Label(data.isOverallApproved ? "✓ Approved" : "⏳ Pending Approval");
        if (data.isOverallApproved) {
            statusBadge.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #15803d; -fx-background-color: #dcfce7; -fx-background-radius: 6; -fx-padding: 2 6 2 6;");
        } else {
            statusBadge.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #b45309; -fx-background-color: #fef3c7; -fx-background-radius: 6; -fx-padding: 2 6 2 6;");
        }

        header.setLeft(title);
        header.setRight(statusBadge);

        HBox docChips = new HBox(12);
        docChips.getChildren().addAll(
                createDocChip("Driving License", data.licenseStatus, data.licenseDocUrl),
                createDocChip("Government ID Proof", data.governmentIdStatus, data.governmentIdDocUrl),
                createDocChip("Vehicle RC Book", data.rcBookStatus, data.rcBookDocUrl)
        );

        card.getChildren().addAll(header, docChips);
        return card;
    }

    private static HBox createDocChip(String docName, String status, String docUrl) {
        HBox chip = new HBox(8);
        chip.setAlignment(Pos.CENTER_LEFT);
        chip.setPadding(new Insets(10, 12, 10, 12));
        chip.setStyle(
                "-fx-background-color: #f9fafb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;"
        );
        HBox.setHgrow(chip, Priority.ALWAYS);

        Label icon = new Label("📄");
        icon.setStyle("-fx-font-size: 16px;");

        VBox info = new VBox(2);
        Label name = new Label(docName);
        name.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #1f2937;");

        boolean approved = status != null && (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Verified") || status.equalsIgnoreCase("Active"));

        Label stat = new Label(approved ? "✓ Approved" : "⏳ " + (status != null && !status.isEmpty() ? status : "Pending Approval"));
        stat.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: " + (approved ? "#10b981;" : "#d97706;"));

        Button btnView = new Button("👁 View Document");
        btnView.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + ORANGE_PRIMARY + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 2 0 0 0;" +
                "-fx-cursor: hand;"
        );
        btnView.setOnMouseEntered(e -> btnView.setStyle("-fx-background-color: transparent; -fx-text-fill: #B84208; -fx-font-size: 10px; -fx-font-weight: bold; -fx-underline: true; -fx-padding: 2 0 0 0; -fx-cursor: hand;"));
        btnView.setOnMouseExited(e -> btnView.setStyle("-fx-background-color: transparent; -fx-text-fill: " + ORANGE_PRIMARY + "; -fx-font-size: 10px; -fx-font-weight: bold; -fx-underline: false; -fx-padding: 2 0 0 0; -fx-cursor: hand;"));

        btnView.setOnAction(e -> openDocumentViewer(docName, docUrl));

        info.getChildren().addAll(name, stat, btnView);
        chip.getChildren().addAll(icon, info);
        return chip;
    }

    private static void openDocumentViewer(String docTitle, String url) {
        if (url == null || url.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Document Not Available");
            alert.setHeaderText(null);
            alert.setContentText("No document upload found for: " + docTitle);
            if (Homepage.HomepageStage != null) {
                alert.initOwner(Homepage.HomepageStage);
            }
            alert.showAndWait();
            return;
        }

        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle(docTitle + " - Preview");
        dialog.initModality(Modality.APPLICATION_MODAL);
        if (Homepage.HomepageStage != null) {
            dialog.initOwner(Homepage.HomepageStage);
        }

        VBox content = new VBox(12);
        content.setPadding(new Insets(16));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: #ffffff;");

        Label titleLbl = new Label(docTitle);
        titleLbl.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(480, 360);
        imageContainer.setMaxSize(480, 360);
        imageContainer.setStyle("-fx-background-color: #f3f4f6; -fx-border-color: #e5e7eb; -fx-border-radius: 8; -fx-background-radius: 8;");

        ProgressIndicator loader = new ProgressIndicator();
        imageContainer.getChildren().add(loader);

        try {
            Image docImage = new Image(url, true);
            ImageView imageView = new ImageView(docImage);
            imageView.setFitWidth(460);
            imageView.setFitHeight(340);
            imageView.setPreserveRatio(true);

            docImage.progressProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.doubleValue() >= 1.0) {
                    imageContainer.getChildren().remove(loader);
                    if (!docImage.isError()) {
                        imageContainer.getChildren().add(imageView);
                    } else {
                        Label err = new Label("Could not load image preview. Click below to open in browser.");
                        err.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 11px;");
                        imageContainer.getChildren().add(err);
                    }
                }
            });
        } catch (Exception ex) {
            imageContainer.getChildren().remove(loader);
            Label err = new Label("Could not load document preview.");
            err.setStyle("-fx-text-fill: #ef4444; -fx-font-size: 11px;");
            imageContainer.getChildren().add(err);
        }

        Button btnBrowser = new Button("🌐 Open in Browser");
        btnBrowser.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-text-fill: #374151;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
        btnBrowser.setOnAction(e -> {
            try {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI(url));
                }
            } catch (Exception ignored) {}
        });

        content.getChildren().addAll(titleLbl, imageContainer, btnBrowser);
        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        dialog.showAndWait();
    }

    private static VBox createAppPreferencesCard(SettingsData data) {
        VBox card = createCard();

        Label title = new Label("App Alerts & Order Preferences");
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        VBox options = new VBox(10);
        options.setPadding(new Insets(4, 0, 0, 0));

        CheckBox cb1 = new CheckBox("High-volume audio alerts for incoming orders");
        cb1.setSelected(data.orderSoundAlerts);
        cb1.setStyle("-fx-font-size: 11px; -fx-text-fill: #374151;");
        cb1.setOnAction(e -> data.orderSoundAlerts = cb1.isSelected());

        CheckBox cb2 = new CheckBox("Real-time surge pricing & bonus area alerts");
        cb2.setSelected(data.surgeNotification);
        cb2.setStyle("-fx-font-size: 11px; -fx-text-fill: #374151;");
        cb2.setOnAction(e -> data.surgeNotification = cb2.isSelected());

        CheckBox cb3 = new CheckBox("Voice-guided turn-by-turn live navigation prompts");
        cb3.setSelected(data.voiceNavigation);
        cb3.setStyle("-fx-font-size: 11px; -fx-text-fill: #374151;");
        cb3.setOnAction(e -> data.voiceNavigation = cb3.isSelected());

        options.getChildren().addAll(cb1, cb2, cb3);
        card.getChildren().addAll(title, options);
        return card;
    }

    private static VBox createRatingCard(SettingsData data) {
        VBox card = createCard();
        card.setAlignment(Pos.TOP_CENTER);

        StackPane badgeCircle = new StackPane();
        Circle circle = new Circle(32);
        circle.setFill(Color.web("#fff7ed"));
        circle.setStroke(Color.web("#fdba74"));
        circle.setStrokeWidth(1.5);

        VBox badgeText = new VBox();
        badgeText.setAlignment(Pos.CENTER);
        Label score = new Label(String.format("%.1f", data.ratingScore));
        score.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #9a3412;");
        Label rat = new Label("Rating");
        rat.setStyle("-fx-font-size: 8px; -fx-text-fill: #c2410c;");
        badgeText.getChildren().addAll(score, rat);

        badgeCircle.getChildren().addAll(circle, badgeText);

        Label stars = new Label("★★★★★");
        stars.setStyle("-fx-font-size: 13px; -fx-text-fill: " + ORANGE_PRIMARY + ";");

        Label quote = new Label(data.ratingQuote);
        quote.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280; -fx-text-alignment: center; -fx-font-style: italic;");

        HBox stats = new HBox(12);
        stats.setAlignment(Pos.CENTER);
        stats.setPadding(new Insets(10, 0, 0, 0));
        stats.setStyle("-fx-border-color: #f0edf2; -fx-border-width: 1 0 0 0;");

        VBox s1 = createMetricItem(String.format("%,d", data.totalSuccessfulDeliveries), "Total Deliveries");
        VBox s2 = createMetricItem(String.format("%.1f%%", data.completionRate), "Completion Rate");
        stats.getChildren().addAll(s1, s2);
        HBox.setHgrow(s1, Priority.ALWAYS);
        HBox.setHgrow(s2, Priority.ALWAYS);

        card.getChildren().addAll(badgeCircle, stars, quote, stats);
        return card;
    }

    private static VBox createPayoutMethodCard(SettingsData data) {
        VBox card = createCard();

        BorderPane titleRow = new BorderPane();
        Label title = new Label("Payout Destination");
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label cardIcon = new Label("💳");
        cardIcon.setStyle("-fx-font-size: 12px;");
        titleRow.setLeft(title);
        titleRow.setRight(cardIcon);

        VBox debitCard = new VBox(10);
        debitCard.setPadding(new Insets(14));
        debitCard.setStyle(
                "-fx-background-color: #1e293b;" +
                "-fx-background-radius: 10;"
        );

        Label bankName = new Label(data.bankName);
        bankName.setStyle("-fx-font-size: 10px; -fx-text-fill: #94a3b8;");

        Label accNumber = new Label("ACCOUNT NUMBER\n" + data.maskedAccountNumber);
        accNumber.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label holder = new Label("HOLDER\n" + data.accountHolderName);
        holder.setStyle("-fx-font-size: 9px; -fx-text-fill: #cbd5e1;");

        debitCard.getChildren().addAll(bankName, accNumber, holder);

        Button btnChange = new Button("Change Account");
        btnChange.setMaxWidth(Double.MAX_VALUE);
        btnChange.setPrefHeight(32);
        btnChange.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-text-fill: #374151;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        btnChange.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(ChangePayout.changePayoutScene(data));
            }
        });

        card.getChildren().addAll(titleRow, debitCard, btnChange);
        return card;
    }

    private static VBox createHelpSupportCard() {
        VBox card = createCard();

        Label title = new Label("Help & Support");
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        VBox accordion = new VBox(6);
        accordion.getChildren().addAll(
                createSupportRow("How do I update my vehicle?"),
                createSupportRow("Weekly payout schedule"),
                createSupportRow("Reporting a delivery issue")
        );

        Button btnContact = new Button("🎧 Contact Support");
        btnContact.setMaxWidth(Double.MAX_VALUE);
        btnContact.setPrefHeight(34);
        btnContact.setStyle(
                "-fx-background-color: #fff7ed;" +
                "-fx-border-color: #fed7aa;" +
                "-fx-border-radius: 7;" +
                "-fx-background-radius: 7;" +
                "-fx-text-fill: #c2410c;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;" +
                "-fx-cursor: hand;"
        );
        btnContact.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerChatSupport.partnerChatSupportScene("SETTINGS"));
            }
        });

        card.getChildren().addAll(title, accordion, btnContact);
        return card;
    }

    private static BorderPane createSupportRow(String text) {
        BorderPane row = new BorderPane();
        row.setPadding(new Insets(4, 0, 4, 0));
        Label q = new Label(text);
        q.setStyle("-fx-font-size: 11px; -fx-text-fill: #4b5563;");
        Label chevron = new Label("⌄");
        chevron.setStyle("-fx-font-size: 12px; -fx-text-fill: #9ca3af;");
        row.setLeft(q);
        row.setRight(chevron);
        return row;
    }

    private static StackPane createAvatarNode(double radius) {
        StackPane avatarPane = new StackPane();
        avatarPane.setPrefSize(radius * 2, radius * 2);
        avatarPane.setMaxSize(radius * 2, radius * 2);

        Circle bg = new Circle(radius, Color.web("#fed7aa"));

        if (PartnerConstants.PROFILE_PHOTO_URL != null && !PartnerConstants.PROFILE_PHOTO_URL.trim().isEmpty()) {
            try {
                ImageView imgView = new ImageView(new Image(PartnerConstants.PROFILE_PHOTO_URL, true));
                imgView.setFitWidth(radius * 2);
                imgView.setFitHeight(radius * 2);
                imgView.setPreserveRatio(false);

                Circle clip = new Circle(radius, radius, radius);
                imgView.setClip(clip);

                avatarPane.getChildren().addAll(bg, imgView);
                return avatarPane;
            } catch (Exception ignored) {}
        }

        String initials = "P";
        if (PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.trim().isEmpty()) {
            String[] parts = PartnerConstants.FULL_NAME.trim().split("\\s+");
            if (parts.length >= 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                initials = ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
            } else if (!parts[0].isEmpty()) {
                initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
            }
        }

        Label initialLabel = new Label(initials);
        initialLabel.setStyle("-fx-font-size: " + (radius * 0.7) + "px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        avatarPane.getChildren().addAll(bg, initialLabel);

        return avatarPane;
    }

    private static VBox createCard() {
        VBox card = new VBox(12);
        card.setPadding(new Insets(16));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );
        return card;
    }

    private static VBox createDetailRow(String label, String value) {
        VBox box = new VBox(1);
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 9px; -fx-text-fill: #9ca3af;");
        Label v = new Label(value != null && !value.isEmpty() ? value : "—");
        v.setStyle("-fx-font-size: 11px; -fx-text-fill: #1f2937; -fx-font-weight: bold;");
        box.getChildren().addAll(l, v);
        return box;
    }

    private static VBox createDetailRowWithBadge(String label, String status) {
        VBox box = new VBox(1);
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 9px; -fx-text-fill: #9ca3af;");

        boolean approved = status != null && (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Verified") || status.equalsIgnoreCase("Active"));

        HBox statusBox = new HBox(4);
        statusBox.setAlignment(Pos.CENTER_LEFT);
        Circle dot = new Circle(3, approved ? Color.web("#22c55e") : Color.web("#d97706"));
        Label v = new Label(approved ? "Approved" : (status != null && !status.isEmpty() ? status : "Pending Approval"));
        v.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: " + (approved ? "#22c55e;" : "#d97706;"));
        statusBox.getChildren().addAll(dot, v);

        box.getChildren().addAll(l, statusBox);
        return box;
    }

    private static VBox createMetricItem(String val, String lbl) {
        VBox box = new VBox(2);
        box.setAlignment(Pos.CENTER);
        Label v = new Label(val);
        v.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label l = new Label(lbl);
        l.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        box.getChildren().addAll(v, l);
        return box;
    }

    private static Button createSidebarNavButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPrefHeight(38);

        if (active) {
            btn.setStyle(
                    "-fx-background-color: " + ORANGE_PRIMARY + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;" +
                    "-fx-background-radius: 8;" +
                    "-fx-padding: 0 14 0 14;" +
                    "-fx-cursor: hand;"
            );
        } else {
            btn.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #6b7280;" +
                    "-fx-font-size: 12px;" +
                    "-fx-padding: 0 14 0 14;" +
                    "-fx-cursor: hand;"
            );
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #fcece3; -fx-text-fill: #a94717; -fx-font-size: 12px; -fx-background-radius: 8; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: #6b7280; -fx-font-size: 12px; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
        }
        return btn;
    }
}
package com.kryox.view.Admin;

import com.kryox.dao.Customer.UserDao;
import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SettingsPage {

    private VBox centerBox;

    private Button identityButton;
    private Button commissionButton;
    private Button securityButton;
    private Button apiButton;
    private Button payoutButton;

    // Default Commission & Financial Fields
    private TextField groceryRateField;
    private TextField electronicsRateField;
    private TextField fashionRateField;
    private TextField payoutThresholdField;
    private TextField platformFeeField;

    private VBox commissionCard;
    private VBox salesCommissionCard;
    private VBox financialCard;

    public Scene getUserScene() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #eee5df;");

        VBox left = new VBox(25);
        left.setPrefWidth(210);
        left.setPadding(new Insets(30, 15, 20, 15));
        left.setStyle("-fx-background-color: #ebccb7");

        Text logo = new Text("Admin Panel");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logo.setFill(Color.web("#A83E00"));

        Text subLogo = new Text("Marketplace Controller");
        subLogo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        subLogo.setFill(Color.web("#999999"));

        VBox logoBox = new VBox(4);
        logoBox.getChildren().addAll(logo, subLogo);

        VBox menu = new VBox(4);

        Image dashboardImage = new Image(getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());
        ImageView dashboardIcon = new ImageView(dashboardImage);
        Text dashboardText = new Text("Dashboard");
        dashboardIcon.setFitWidth(21);
        dashboardIcon.setFitHeight(21);
        dashboardIcon.setPreserveRatio(true);
        dashboardText.setFont(Font.font("Arial", 14));

        HBox dashboard = new HBox(10);
        dashboard.setAlignment(Pos.CENTER_LEFT);
        dashboard.setPadding(new Insets(10, 12, 10, 12));
        dashboard.setStyle("-fx-background-color:transparent;");
        dashboard.getChildren().addAll(dashboardIcon, dashboardText);

        Image usersImage = new Image(getClass().getResource("/assets/images/admin/admin_logo.png").toExternalForm());
        ImageView usersIcon = new ImageView(usersImage);
        Text usersText = new Text("Users");
        usersIcon.setFitWidth(21);
        usersIcon.setFitHeight(21);
        usersIcon.setPreserveRatio(true);
        usersText.setFont(Font.font("Arial", 14));
        HBox users = new HBox(10);
        users.setAlignment(Pos.CENTER_LEFT);
        users.setPadding(new Insets(10, 12, 10, 12));
        users.setStyle("-fx-background-color:transparent;");
        users.getChildren().addAll(usersIcon, usersText);

        Image shopsImage = new Image(getClass().getResource("/assets/images/admin/shop.png").toExternalForm());
        ImageView shopsIcon = new ImageView(shopsImage);
        Text shopsText = new Text("Shops");
        shopsIcon.setFitWidth(21);
        shopsIcon.setFitHeight(21);
        shopsIcon.setPreserveRatio(true);
        shopsText.setFont(Font.font("Arial", 14));
        HBox shops = new HBox(10);
        shops.setAlignment(Pos.CENTER_LEFT);
        shops.setPadding(new Insets(10, 12, 10, 12));
        shops.setStyle("-fx-background-color:transparent;");
        shops.getChildren().addAll(shopsIcon, shopsText);

        Image offersImage = new Image(getClass().getResource("/assets/images/admin/tag.png").toExternalForm());
        ImageView offersIcon = new ImageView(offersImage);
        Text offersText = new Text("Offers");
        offersIcon.setFitWidth(21);
        offersIcon.setFitHeight(21);
        offersIcon.setPreserveRatio(true);
        offersText.setFont(Font.font("Arial", 14));
        HBox offers = new HBox(10);
        offers.setAlignment(Pos.CENTER_LEFT);
        offers.setPadding(new Insets(10, 12, 10, 12));
        offers.setStyle("-fx-background-color:transparent;");
        offers.getChildren().addAll(offersIcon, offersText);

        Image analyticsImage = new Image(getClass().getResource("/assets/images/admin/stats.png").toExternalForm());
        ImageView analyticsIcon = new ImageView(analyticsImage);
        Text analyticsText = new Text("Analytics");
        analyticsIcon.setFitWidth(21);
        analyticsIcon.setFitHeight(21);
        analyticsIcon.setPreserveRatio(true);
        analyticsText.setFont(Font.font("Arial", 14));
        HBox analytics = new HBox(10);
        analytics.setAlignment(Pos.CENTER_LEFT);
        analytics.setPadding(new Insets(10, 12, 10, 12));
        analytics.setStyle("-fx-background-color:transparent;");
        analytics.getChildren().addAll(analyticsIcon, analyticsText);

        Image settingsImage = new Image(getClass().getResource("/assets/images/admin/setting.png").toExternalForm());
        ImageView settingsIcon = new ImageView(settingsImage);
        Text settingsText = new Text("Settings");
        settingsIcon.setFitWidth(21);
        settingsIcon.setFitHeight(21);
        settingsIcon.setPreserveRatio(true);
        settingsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        settingsText.setFill(Color.WHITE);
        HBox settings = new HBox(10);
        settings.setAlignment(Pos.CENTER_LEFT);
        settings.setPadding(new Insets(10, 12, 10, 12));
        settings.setStyle("-fx-background-color:#FF6500; -fx-background-radius:10;");
        settings.getChildren().addAll(settingsIcon, settingsText);

        menu.getChildren().addAll(dashboard, users, shops, offers, analytics, settings);

        VBox bottomMenu = new VBox(4);
        Image supportImage = new Image(getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
        ImageView supportIcon = new ImageView(supportImage);
        Text supportText = new Text("Support");
        supportIcon.setFitWidth(21);
        supportIcon.setFitHeight(21);
        supportIcon.setPreserveRatio(true);
        supportText.setFont(Font.font("Arial", 14));
        HBox support = new HBox(10);
        support.setAlignment(Pos.CENTER_LEFT);
        support.setPadding(new Insets(10, 12, 10, 12));
        support.setStyle("-fx-background-color:transparent;");
        support.getChildren().addAll(supportIcon, supportText);
        bottomMenu.getChildren().add(support);

        dashboard.setOnMouseEntered(e -> {
            dashboard.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            dashboardText.setFill(Color.WHITE);
            dashboardText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition dashboardAnimation = new ScaleTransition(Duration.millis(120), dashboard);
            dashboardAnimation.setToX(1.03);
            dashboardAnimation.setToY(1.03);
            dashboardAnimation.play();
        });

        dashboard.setOnMouseExited(e -> {
            dashboard.setStyle("-fx-background-color:transparent;");
            dashboardText.setFill(Color.web("#333333"));
            dashboardText.setFont(Font.font("Arial", 14));
            ScaleTransition dashboardAnimation = new ScaleTransition(Duration.millis(120), dashboard);
            dashboardAnimation.setToX(1);
            dashboardAnimation.setToY(1);
            dashboardAnimation.play();
        });

        users.setOnMouseEntered(e -> {
            users.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            usersText.setFill(Color.WHITE);
            usersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition usersAnimation = new ScaleTransition(Duration.millis(120), users);
            usersAnimation.setToX(1.03);
            usersAnimation.setToY(1.03);
            usersAnimation.play();
        });

        users.setOnMouseExited(e -> {
            users.setStyle("-fx-background-color:transparent;");
            usersText.setFill(Color.web("#333333"));
            usersText.setFont(Font.font("Arial", 14));
            ScaleTransition usersAnimation = new ScaleTransition(Duration.millis(120), users);
            usersAnimation.setToX(1);
            usersAnimation.setToY(1);
            usersAnimation.play();
        });

        shops.setOnMouseEntered(e -> {
            shops.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            shopsText.setFill(Color.WHITE);
            shopsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition shopsAnimation = new ScaleTransition(Duration.millis(120), shops);
            shopsAnimation.setToX(1.03);
            shopsAnimation.setToY(1.03);
            shopsAnimation.play();
        });

        shops.setOnMouseExited(e -> {
            shops.setStyle("-fx-background-color:transparent;");
            shopsText.setFill(Color.web("#333333"));
            shopsText.setFont(Font.font("Arial", 14));
            ScaleTransition shopsAnimation = new ScaleTransition(Duration.millis(120), shops);
            shopsAnimation.setToX(1);
            shopsAnimation.setToY(1);
            shopsAnimation.play();
        });

        offers.setOnMouseEntered(e -> {
            offers.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            offersText.setFill(Color.WHITE);
            offersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition offersAnimation = new ScaleTransition(Duration.millis(120), offers);
            offersAnimation.setToX(1.03);
            offersAnimation.setToY(1.03);
            offersAnimation.play();
        });

        offers.setOnMouseExited(e -> {
            offers.setStyle("-fx-background-color:transparent;");
            offersText.setFill(Color.web("#333333"));
            offersText.setFont(Font.font("Arial", 14));
            ScaleTransition offersAnimation = new ScaleTransition(Duration.millis(120), offers);
            offersAnimation.setToX(1);
            offersAnimation.setToY(1);
            offersAnimation.play();
        });

        analytics.setOnMouseEntered(e -> {
            analytics.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            analyticsText.setFill(Color.WHITE);
            analyticsText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition analyticsAnimation = new ScaleTransition(Duration.millis(120), analytics);
            analyticsAnimation.setToX(1.03);
            analyticsAnimation.setToY(1.03);
            analyticsAnimation.play();
        });

        analytics.setOnMouseExited(e -> {
            analytics.setStyle("-fx-background-color:transparent;");
            analyticsText.setFill(Color.web("#333333"));
            analyticsText.setFont(Font.font("Arial", 14));
            ScaleTransition analyticsAnimation = new ScaleTransition(Duration.millis(120), analytics);
            analyticsAnimation.setToX(1);
            analyticsAnimation.setToY(1);
            analyticsAnimation.play();
        });

        support.setOnMouseEntered(e -> {
            support.setStyle("-fx-background-color:#D94F00; -fx-background-radius:10;");
            supportText.setFill(Color.WHITE);
            supportText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            ScaleTransition supportAnimation = new ScaleTransition(Duration.millis(120), support);
            supportAnimation.setToX(1.03);
            supportAnimation.setToY(1.03);
            supportAnimation.play();
        });

        support.setOnMouseExited(e -> {
            support.setStyle("-fx-background-color:transparent;");
            supportText.setFill(Color.web("#333333"));
            supportText.setFont(Font.font("Arial", 14));
            ScaleTransition supportAnimation = new ScaleTransition(Duration.millis(120), support);
            supportAnimation.setToX(1);
            supportAnimation.setToY(1);
            supportAnimation.play();
        });

        dashboard.setOnMouseClicked(e -> {
            AdminDashboardPage dashboardPage = new AdminDashboardPage();
            Homepage.HomepageStage.setScene(dashboardPage.getUserScene());
        });

        users.setOnMouseClicked(e -> {
            UserManagementPage userPage = new UserManagementPage();
            Homepage.HomepageStage.setScene(userPage.getUserScene());
        });

        shops.setOnMouseClicked(e -> {
            ShopVerificationPage shopPage = new ShopVerificationPage();
            Homepage.HomepageStage.setScene(shopPage.getUserScene());
        });

        offers.setOnMouseClicked(e -> {
            OfferPage offerPage = new OfferPage();
            Homepage.HomepageStage.setScene(offerPage.getUserScene());
        });

        analytics.setOnMouseClicked(e -> {
            AdminAnalyticsPage analyticsPage = new AdminAnalyticsPage();
            Homepage.HomepageStage.setScene(analyticsPage.getUserScene());
        });

        settings.setOnMouseClicked(e -> {
            SettingsPage settingsPage = new SettingsPage();
            Homepage.HomepageStage.setScene(settingsPage.getUserScene());
        });

        support.setOnMouseClicked(e -> {
            SupportPage supports = new SupportPage();
            Homepage.HomepageStage.setScene(supports.getUserScene());
        });

        Region leftGrow = new Region();
        VBox.setVgrow(leftGrow, Priority.ALWAYS);

        AdminProfileCard adminProfileCard = new AdminProfileCard();
        HBox profile = adminProfileCard.getProfileCard();
        Separator leftSeparator = new Separator();

        left.getChildren().addAll(
                logoBox,
                menu,
                leftSeparator,
                bottomMenu,
                leftGrow,
                profile
        );
        root.setLeft(left);

        VBox rightBox = new VBox(20);
        rightBox.setPadding(new Insets(25));
        rightBox.setStyle("-fx-background-color: #eee5df;");

        HBox top = new HBox(20);
        top.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText("Search orders, shops, or users...");
        search.setPrefSize(360, 42);
        search.setStyle("-fx-background-color:#F2F0F5; -fx-background-radius:20; -fx-font-size:14px; -fx-padding: 0 15 0 15;");
        search.setOnAction(e -> {
            String q = search.getText() != null ? search.getText().trim() : "";
            UserManagementPage ump = new UserManagementPage(q);
            Homepage.HomepageStage.setScene(ump.getUserScene());
        });

        Region topGrow = new Region();
        HBox.setHgrow(topGrow, Priority.ALWAYS);

        Image messageImage = new Image(getClass().getResource("/assets/images/admin/message.png").toExternalForm());
        ImageView message = new ImageView(messageImage);
        message.setFitWidth(22);
        message.setFitHeight(22);
        message.setPreserveRatio(true);
        message.setStyle("-fx-cursor:hand;");
        message.setOnMouseClicked(e -> {
            SmartAssistantUI chatPage = new SmartAssistantUI();
            Homepage.HomepageStage.setScene(chatPage.getUserScene());
        });

        Image bellImage = new Image(getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
        ImageView bell = new ImageView(bellImage);
        bell.setFitWidth(22);
        bell.setFitHeight(22);
        bell.setPreserveRatio(true);

        Text admin = new Text("Admin Portal");
        admin.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        HBox topRight = new HBox(20, message, bell, admin);
        topRight.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().addAll(search, topGrow, topRight);

        VBox content = new VBox(20);
        content.setPadding(new Insets(5, 5, 25, 5));

        Text title = new Text("Platform Configuration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        Text subtitle = new Text(
                "Manage core system parameters, financial structures (in ₹), and security protocols for the BuyNeX ecosystem.");
        subtitle.setFont(Font.font("Arial", 16));
        subtitle.setFill(Color.web("#666666"));

        VBox heading = new VBox(6, title, subtitle);

        HBox settingBoxes = new HBox(18);
        settingBoxes.setAlignment(Pos.TOP_LEFT);

        VBox settingMenu = new VBox(14);
        settingMenu.setPrefWidth(220);
        settingMenu.setPadding(new Insets(20));
        settingMenu.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");

        identityButton = new Button("▣  Platform Identity");
        commissionButton = new Button("▣  Commission & Payouts  ›");
        securityButton = new Button("⬟  Security");
        apiButton = new Button("◆  API Integrations");
        payoutButton = new Button("⟳  Payout Cycles");

        identityButton.setMaxWidth(Double.MAX_VALUE);
        commissionButton.setMaxWidth(Double.MAX_VALUE);
        securityButton.setMaxWidth(Double.MAX_VALUE);
        apiButton.setMaxWidth(Double.MAX_VALUE);
        payoutButton.setMaxWidth(Double.MAX_VALUE);

        identityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        commissionButton.setStyle("-fx-background-color:#F3F1F7; -fx-text-fill:#9B3100; -fx-font-weight:bold; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-cursor:hand;");
        securityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        apiButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        payoutButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");

        settingMenu.getChildren().addAll(
                identityButton,
                commissionButton,
                securityButton,
                apiButton,
                payoutButton);

        centerBox = new VBox(18);
        HBox.setHgrow(centerBox, Priority.ALWAYS);

        // Build default Commission & Payouts view
        buildCommissionAndPayoutsCards();
        showCommissionView();

        // Sub-tab button event handlers
        identityButton.setOnAction(e -> showIdentityView());
        commissionButton.setOnAction(e -> showCommissionView());
        securityButton.setOnAction(e -> showSecurityView());
        apiButton.setOnAction(e -> showApiView());
        payoutButton.setOnAction(e -> showPayoutView());

        VBox statusBox = new VBox(18);
        statusBox.setPrefWidth(230);

        VBox systemCard = new VBox(14);
        systemCard.setPrefWidth(230);
        systemCard.setPadding(new Insets(20));
        systemCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text systemTitle = new Text("●  System Status");
        systemTitle.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        systemTitle.setFill(Color.web("#9B3100"));

        Text uptimeText = new Text("Server Uptime");
        uptimeText.setFont(Font.font("Arial", 14));
        Text uptimeValue = new Text("99.99%");
        uptimeValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region uptimeGrow = new Region();
        HBox.setHgrow(uptimeGrow, Priority.ALWAYS);
        HBox uptimeBox = new HBox(uptimeText, uptimeGrow, uptimeValue);

        Text sessionText = new Text("Active Sessions");
        sessionText.setFont(Font.font("Arial", 14));
        Text sessionValue = new Text("Loading...");
        sessionValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region sessionGrow = new Region();
        HBox.setHgrow(sessionGrow, Priority.ALWAYS);
        HBox sessionBox = new HBox(sessionText, sessionGrow, sessionValue);

        Text latencyText = new Text("API Latency");
        latencyText.setFont(Font.font("Arial", 14));
        Text latencyValue = new Text("28ms");
        latencyValue.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Region latencyGrow = new Region();
        HBox.setHgrow(latencyGrow, Priority.ALWAYS);
        HBox latencyBox = new HBox(latencyText, latencyGrow, latencyValue);

        Separator statusSeparator1 = new Separator();
        Separator statusSeparator2 = new Separator();

        systemCard.getChildren().addAll(
                systemTitle,
                uptimeBox,
                statusSeparator1,
                sessionBox,
                statusSeparator2,
                latencyBox);

        VBox auditCard = new VBox(14);
        auditCard.setPrefWidth(230);
        auditCard.setPadding(new Insets(20));
        auditCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");
        Text auditTitle = new Text("Security Audit");
        auditTitle.setFont(Font.font("Arial", FontWeight.BOLD, 23));
        Text score = new Text("98 / 100");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 40));

        Button report = new Button("View Full Report");
        report.setMaxWidth(Double.MAX_VALUE);
        report.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-cursor:hand;");
        report.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("BuyNeX Security Audit Report");
            alert.setHeaderText("Platform Security & Encryption Compliance (98/100)");
            alert.setContentText("1. MFA Enforcement: 100% active for all Admin roles.\n"
                    + "2. Data Encryption: AES-256-GCM Active for all user data.\n"
                    + "3. Payment Gateway (Razorpay/UPI): Verified & Compliant.\n"
                    + "4. Firestore Security Rules: Strict role-based read/write access.\n"
                    + "5. API Keys: 1 pending key review (Twilio SMS backup).");
            alert.showAndWait();
        });

        Text mfaText = new Text("⊙  MFA enforced for Admins");
        mfaText.setFont(Font.font("Arial", 13));
        Text encryptionText = new Text("⊙  Data encryption active");
        encryptionText.setFont(Font.font("Arial", 13));
        Text apiPendingText = new Text("ⓘ  1 API key pending");
        apiPendingText.setFont(Font.font("Arial", 13));

        auditCard.getChildren().addAll(
                auditTitle,
                score,
                mfaText,
                encryptionText,
                apiPendingText,
                report);

        statusBox.getChildren().addAll(systemCard, auditCard);
        settingBoxes.getChildren().addAll(settingMenu, centerBox, statusBox);

        Button discard = new Button("Discard Changes");
        discard.setStyle("-fx-font-size:14px; -fx-font-weight:bold; -fx-cursor:hand;");
        discard.setPrefSize(190, 50);

        Button save = new Button("Save Configuration");
        save.setPrefSize(215, 50);
        save.setStyle("-fx-background-color:#D94F00; -fx-text-fill:white; -fx-font-weight:bold; -fx-font-size:14px; -fx-cursor:hand;");

        save.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Settings Saved");
            alert.setHeaderText("Platform Configuration Updated Successfully");
            alert.setContentText("All financial parameters (in ₹), category commission rates, and security rules have been saved and applied to the live BuyNeX ecosystem.");
            alert.showAndWait();
        });

        discard.setOnAction(e -> {
            groceryRateField.setText("2.5  %");
            electronicsRateField.setText("4.0  %");
            fashionRateField.setText("5.5  %");
            payoutThresholdField.setText("₹  500.00");
            platformFeeField.setText("₹  25.00");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Changes Discarded");
            alert.setHeaderText("Settings Reset to Default");
            alert.setContentText("All field values have been restored to default system configuration.");
            alert.showAndWait();
        });

        HBox actions = new HBox(15, discard, save);
        actions.setAlignment(Pos.CENTER);

        content.getChildren().addAll(heading, settingBoxes, actions);

        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background:#F9F7FB; -fx-border-color:transparent;");
        VBox.setVgrow(scroll, Priority.ALWAYS);

        rightBox.getChildren().addAll(top, scroll);
        root.setCenter(rightBox);

        // Dynamic System Status fetch from UserDao
        Thread statusThread = new Thread(() -> {
            UserDao userDao = new UserDao();
            int totalCustomers = userDao.getUserCountByRole("Customer");
            int totalShopkeepers = userDao.getUserCountByRole("Shopkeeper");
            int activeTotal = totalCustomers + totalShopkeepers;
            final int displaySessions = Math.max(12, activeTotal);

            Platform.runLater(() -> {
                sessionValue.setText(String.valueOf(displaySessions));
            });
        });
        statusThread.setDaemon(true);
        statusThread.start();

        return new Scene(root, 1550, 850);
    }

    private void resetSubMenuStyles() {
        identityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        commissionButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        securityButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        apiButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
        payoutButton.setStyle("-fx-background-color:transparent; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-font-size:14px; -fx-cursor:hand;");
    }

    private void setActiveSubMenu(Button btn) {
        resetSubMenuStyles();
        btn.setStyle("-fx-background-color:#F3F1F7; -fx-text-fill:#9B3100; -fx-font-weight:bold; -fx-alignment:CENTER-LEFT; -fx-padding:13; -fx-cursor:hand;");
    }

    private void buildCommissionAndPayoutsCards() {
        commissionCard = new VBox(14);
        commissionCard.setPrefWidth(560);
        commissionCard.setPadding(new Insets(20));
        commissionCard.setStyle("-fx-background-color:white; -fx-background-radius:15;"
                + "-fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text commissionTitle = new Text("Category Commission Rates");
        commissionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        Text commissionSub = new Text("Set the default percentage taken per transaction by category.");
        commissionSub.setFont(Font.font("Arial", 14));
        commissionSub.setFill(Color.web("#666666"));

        Text groceryIcon = new Text("◉");
        groceryIcon.setFont(Font.font("Arial", 18));
        Text groceryText = new Text("Grocery & Daily Essentials");
        groceryText.setFont(Font.font("Arial", 14));
        groceryRateField = new TextField("2.5  %");
        Region groceryGrow = new Region();
        HBox.setHgrow(groceryGrow, Priority.ALWAYS);
        groceryRateField.setPrefWidth(110);
        groceryRateField.setAlignment(Pos.CENTER_RIGHT);
        groceryRateField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        HBox groceryBox = new HBox(12, groceryIcon, groceryText, groceryGrow, groceryRateField);
        groceryBox.setAlignment(Pos.CENTER_LEFT);
        groceryBox.setPadding(new Insets(12));
        groceryBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10; -fx-border-color:#E5CDC1; -fx-border-radius:10;");

        Text electronicsIcon = new Text("▣");
        electronicsIcon.setFont(Font.font("Arial", 18));
        Text electronicsText = new Text("Electronics & Gadgets");
        electronicsText.setFont(Font.font("Arial", 14));
        electronicsRateField = new TextField("4.0  %");
        Region electronicsGrow = new Region();
        HBox.setHgrow(electronicsGrow, Priority.ALWAYS);
        electronicsRateField.setPrefWidth(110);
        electronicsRateField.setAlignment(Pos.CENTER_RIGHT);
        electronicsRateField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        HBox electronicsBox = new HBox(12, electronicsIcon, electronicsText, electronicsGrow, electronicsRateField);
        electronicsBox.setAlignment(Pos.CENTER_LEFT);
        electronicsBox.setPadding(new Insets(12));
        electronicsBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10; -fx-border-color:#E5CDC1; -fx-border-radius:10;");

        Text fashionIcon = new Text("♧");
        fashionIcon.setFont(Font.font("Arial", 18));
        Text fashionText = new Text("Fashion & Apparel");
        fashionText.setFont(Font.font("Arial", 14));
        fashionRateField = new TextField("5.5  %");
        Region fashionGrow = new Region();
        HBox.setHgrow(fashionGrow, Priority.ALWAYS);
        fashionRateField.setPrefWidth(110);
        fashionRateField.setAlignment(Pos.CENTER_RIGHT);
        fashionRateField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        HBox fashionBox = new HBox(12, fashionIcon, fashionText, fashionGrow, fashionRateField);
        fashionBox.setAlignment(Pos.CENTER_LEFT);
        fashionBox.setPadding(new Insets(12));
        fashionBox.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10; -fx-border-color:#E5CDC1; -fx-border-radius:10;");

        commissionCard.getChildren().addAll(
                commissionTitle,
                commissionSub,
                groceryBox,
                electronicsBox,
                fashionBox);

        // Sales Based Commission
        salesCommissionCard = new VBox(14);
        salesCommissionCard.setPrefWidth(560);
        salesCommissionCard.setPadding(new Insets(20));
        salesCommissionCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        HBox salesCommissionHeading = new HBox(8);
        salesCommissionHeading.setAlignment(Pos.CENTER_LEFT);

        Text salesCommissionTitle = new Text("Sales Based Commission (Shopkeeper)");
        salesCommissionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        Text salesInfo = new Text("ⓘ");
        salesInfo.setFont(Font.font("Arial", 15));
        salesInfo.setFill(Color.web("#777777"));

        salesCommissionHeading.getChildren().addAll(salesCommissionTitle, salesInfo);

        Text salesCommissionSub = new Text("Commission changes based on total monthly product sales in Rupee (₹).");
        salesCommissionSub.setFont(Font.font("Arial", 13));
        salesCommissionSub.setFill(Color.web("#666666"));

        Text rangeHeader = new Text("Monthly Sales Range (₹)");
        Text rateHeader = new Text("Commission (%)");
        Text descriptionHeader = new Text("Description");
        Text actionHeader = new Text("Action");

        rangeHeader.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        rateHeader.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        descriptionHeader.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        actionHeader.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        rangeHeader.setWrappingWidth(150);
        rateHeader.setWrappingWidth(100);
        descriptionHeader.setWrappingWidth(210);
        actionHeader.setWrappingWidth(45);

        HBox salesHeader = new HBox(rangeHeader, rateHeader, descriptionHeader, actionHeader);
        salesHeader.setAlignment(Pos.CENTER_LEFT);
        salesHeader.setPadding(new Insets(8, 8, 8, 8));

        VBox salesTierRows = new VBox(4);

        TextField range1 = new TextField("₹0 - ₹10,000");
        TextField rate1 = new TextField("2.0 %");
        Text description1 = new Text("Low sales - Standard commission");
        Button delete1 = new Button("✕");

        range1.setPrefWidth(145);
        rate1.setPrefWidth(90);
        rate1.setAlignment(Pos.CENTER);
        description1.setWrappingWidth(210);
        range1.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        rate1.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        delete1.setStyle("-fx-background-color:transparent;-fx-text-fill:#E53935;-fx-font-weight:bold;-fx-cursor:hand;");

        HBox salesRow1 = new HBox(10, range1, rate1, description1, delete1);
        salesRow1.setAlignment(Pos.CENTER_LEFT);
        salesRow1.setPadding(new Insets(7, 8, 7, 8));
        delete1.setOnAction(e -> salesTierRows.getChildren().remove(salesRow1));

        TextField range2 = new TextField("₹10,001 - ₹50,000");
        TextField rate2 = new TextField("3.0 %");
        Text description2 = new Text("Medium sales - Moderate commission");
        Button delete2 = new Button("✕");

        range2.setPrefWidth(145);
        rate2.setPrefWidth(90);
        rate2.setAlignment(Pos.CENTER);
        description2.setWrappingWidth(210);
        range2.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        rate2.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        delete2.setStyle("-fx-background-color:transparent;-fx-text-fill:#E53935;-fx-font-weight:bold;-fx-cursor:hand;");

        HBox salesRow2 = new HBox(10, range2, rate2, description2, delete2);
        salesRow2.setAlignment(Pos.CENTER_LEFT);
        salesRow2.setPadding(new Insets(7, 8, 7, 8));
        delete2.setOnAction(e -> salesTierRows.getChildren().remove(salesRow2));

        TextField range3 = new TextField("₹50,001 - ₹1,00,000");
        TextField rate3 = new TextField("4.5 %");
        Text description3 = new Text("High sales - Higher commission");
        Button delete3 = new Button("✕");

        range3.setPrefWidth(145);
        rate3.setPrefWidth(90);
        rate3.setAlignment(Pos.CENTER);
        description3.setWrappingWidth(210);
        range3.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        rate3.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        delete3.setStyle("-fx-background-color:transparent;-fx-text-fill:#E53935;-fx-font-weight:bold;-fx-cursor:hand;");

        HBox salesRow3 = new HBox(10, range3, rate3, description3, delete3);
        salesRow3.setAlignment(Pos.CENTER_LEFT);
        salesRow3.setPadding(new Insets(7, 8, 7, 8));
        delete3.setOnAction(e -> salesTierRows.getChildren().remove(salesRow3));

        TextField range4 = new TextField("₹1,00,001+");
        TextField rate4 = new TextField("6.0 %");
        Text description4 = new Text("Very high sales - Maximum commission");
        Button delete4 = new Button("✕");

        range4.setPrefWidth(145);
        rate4.setPrefWidth(90);
        rate4.setAlignment(Pos.CENTER);
        description4.setWrappingWidth(210);
        range4.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        rate4.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
        delete4.setStyle("-fx-background-color:transparent;-fx-text-fill:#E53935;-fx-font-weight:bold;-fx-cursor:hand;");

        HBox salesRow4 = new HBox(10, range4, rate4, description4, delete4);
        salesRow4.setAlignment(Pos.CENTER_LEFT);
        salesRow4.setPadding(new Insets(7, 8, 7, 8));
        delete4.setOnAction(e -> salesTierRows.getChildren().remove(salesRow4));

        salesTierRows.getChildren().addAll(salesRow1, salesRow2, salesRow3, salesRow4);

        Button addSalesTier = new Button("＋ Add Sales Tier");
        addSalesTier.setStyle(
                "-fx-background-color:white; -fx-text-fill:#9B3100; -fx-border-color:#E5CDC1; -fx-border-radius:7; -fx-background-radius:7; -fx-font-weight:bold; -fx-cursor:hand;");

        addSalesTier.setOnAction(e -> {
            TextField newRange = new TextField("₹ Range");
            TextField newRate = new TextField("0.0 %");
            Text newDescription = new Text("New sales tier");
            Button newDelete = new Button("✕");

            newRange.setPrefWidth(145);
            newRate.setPrefWidth(90);
            newRate.setAlignment(Pos.CENTER);
            newDescription.setWrappingWidth(210);
            newRange.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
            newRate.setStyle("-fx-background-color:white;-fx-border-color:#E5CDC1;-fx-border-radius:7;-fx-background-radius:7;");
            newDelete.setStyle("-fx-background-color:transparent;-fx-text-fill:#E53935;-fx-font-weight:bold;-fx-cursor:hand;");

            HBox newRow = new HBox(10, newRange, newRate, newDescription, newDelete);
            newRow.setAlignment(Pos.CENTER_LEFT);
            newRow.setPadding(new Insets(7, 8, 7, 8));
            newDelete.setOnAction(event -> salesTierRows.getChildren().remove(newRow));
            salesTierRows.getChildren().add(newRow);
        });

        salesCommissionCard.getChildren().addAll(
                salesCommissionHeading,
                salesCommissionSub,
                salesHeader,
                new Separator(),
                salesTierRows,
                addSalesTier);

        // Financial Thresholds
        financialCard = new VBox(14);
        financialCard.setPrefWidth(560);
        financialCard.setPadding(new Insets(20));
        financialCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text financialTitle = new Text("Financial Thresholds");
        financialTitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));

        payoutThresholdField = new TextField("₹  500.00");
        platformFeeField = new TextField("₹  25.00");
        payoutThresholdField.setPrefHeight(50);
        platformFeeField.setPrefHeight(50);
        payoutThresholdField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8; -fx-font-size:14px;");
        platformFeeField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8; -fx-font-size:14px;");

        Text payoutLabel = new Text("Minimum Payout Threshold (₹)");
        payoutLabel.setFont(Font.font("Arial", 14));
        VBox payoutBox = new VBox(7, payoutLabel, payoutThresholdField);

        Text feeLabel = new Text("Standard Platform Fee (Flat ₹)");
        feeLabel.setFont(Font.font("Arial", 14));
        VBox feeBox = new VBox(7, feeLabel, platformFeeField);

        HBox.setHgrow(payoutBox, Priority.ALWAYS);
        HBox.setHgrow(feeBox, Priority.ALWAYS);
        HBox fields = new HBox(18, payoutBox, feeBox);
        financialCard.getChildren().addAll(financialTitle, fields);
    }

    private void showCommissionView() {
        setActiveSubMenu(commissionButton);
        centerBox.getChildren().setAll(commissionCard, salesCommissionCard, financialCard);
    }

    private void showIdentityView() {
        setActiveSubMenu(identityButton);
        VBox identityCard = new VBox(16);
        identityCard.setPrefWidth(560);
        identityCard.setPadding(new Insets(20));
        identityCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text title = new Text("Platform Identity");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField nameField = new TextField("BuyNeX Super-X Marketplace");
        nameField.setPrefHeight(45);
        nameField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        TextField emailField = new TextField("admin@buynex.com");
        emailField.setPrefHeight(45);
        emailField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        TextField currencyField = new TextField("INR (₹) - Indian Rupee");
        currencyField.setPrefHeight(45);
        currencyField.setEditable(false);
        currencyField.setStyle("-fx-background-color:#F5F3F7; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        TextField timezoneField = new TextField("IST (Asia/Kolkata - UTC+05:30)");
        timezoneField.setPrefHeight(45);
        timezoneField.setEditable(false);
        timezoneField.setStyle("-fx-background-color:#F5F3F7; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        identityCard.getChildren().addAll(
                title,
                new VBox(6, new Text("Marketplace Application Name"), nameField),
                new VBox(6, new Text("Official Support Email"), emailField),
                new VBox(6, new Text("Operating Currency"), currencyField),
                new VBox(6, new Text("System Timezone"), timezoneField)
        );

        centerBox.getChildren().setAll(identityCard);
    }

    private void showSecurityView() {
        setActiveSubMenu(securityButton);
        VBox securityCard = new VBox(16);
        securityCard.setPrefWidth(560);
        securityCard.setPadding(new Insets(20));
        securityCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text title = new Text("Security Configuration");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        CheckBox mfaBox = new CheckBox("Enforce Multi-Factor Authentication (MFA / OTP) for Admins & Merchants");
        mfaBox.setSelected(true);
        mfaBox.setFont(Font.font("Arial", 14));

        CheckBox sessionLockBox = new CheckBox("Automatic Session Lockout after 30 minutes of inactivity");
        sessionLockBox.setSelected(true);
        sessionLockBox.setFont(Font.font("Arial", 14));

        CheckBox ipWhitelistBox = new CheckBox("Restrict Admin Login to Whitelisted IP Ranges");
        ipWhitelistBox.setSelected(false);
        ipWhitelistBox.setFont(Font.font("Arial", 14));

        TextField maxAttempts = new TextField("5 Failed Attempts");
        maxAttempts.setPrefHeight(42);
        maxAttempts.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        securityCard.getChildren().addAll(
                title,
                mfaBox,
                sessionLockBox,
                ipWhitelistBox,
                new VBox(6, new Text("Max Allowed Password Retries"), maxAttempts)
        );

        centerBox.getChildren().setAll(securityCard);
    }

    private void showApiView() {
        setActiveSubMenu(apiButton);
        VBox apiCard = new VBox(16);
        apiCard.setPrefWidth(560);
        apiCard.setPadding(new Insets(20));
        apiCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text title = new Text("API Integrations & Webhooks");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        HBox p1 = createApiStatusRow("Payment Gateway (Razorpay / UPI)", "CONNECTED (Production Live)", "#1FA64B");
        HBox p2 = createApiStatusRow("Database & Auth (Google Firebase)", "SYNCHRONIZED", "#1FA64B");
        HBox p3 = createApiStatusRow("SMS Gateway (Twilio OTP Service)", "CONNECTED (1 Key Pending)", "#E08A00");
        HBox p4 = createApiStatusRow("Maps & Geocoding (Google Maps API)", "ACTIVE", "#1FA64B");

        apiCard.getChildren().addAll(
                title,
                p1,
                p2,
                p3,
                p4
        );

        centerBox.getChildren().setAll(apiCard);
    }

    private void showPayoutView() {
        setActiveSubMenu(payoutButton);
        VBox payoutCard = new VBox(16);
        payoutCard.setPrefWidth(560);
        payoutCard.setPadding(new Insets(20));
        payoutCard.setStyle("-fx-background-color:white; -fx-background-radius:15; -fx-border-color:#EEE5E0; -fx-border-radius:15;");

        Text title = new Text("Vendor Payout Cycles");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField cycleField = new TextField("Weekly (Every Monday at 06:00 AM IST)");
        cycleField.setPrefHeight(45);
        cycleField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        TextField minLimitField = new TextField("₹  500.00");
        minLimitField.setPrefHeight(45);
        minLimitField.setStyle("-fx-background-color:white; -fx-border-color:#E5CDC1; -fx-border-radius:8; -fx-background-radius:8;");

        CheckBox autoPayout = new CheckBox("Enable Automated Bank Transfer via Razorpay Payouts API");
        autoPayout.setSelected(true);
        autoPayout.setFont(Font.font("Arial", 14));

        payoutCard.getChildren().addAll(
                title,
                new VBox(6, new Text("Default Vendor Payout Frequency"), cycleField),
                new VBox(6, new Text("Minimum Payout Limit (₹)"), minLimitField),
                autoPayout
        );

        centerBox.getChildren().setAll(payoutCard);
    }

    private HBox createApiStatusRow(String name, String status, String statusColorHex) {
        Text t = new Text(name);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        Text st = new Text(status);
        st.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        st.setFill(Color.web(statusColorHex));

        Region grow = new Region();
        HBox.setHgrow(grow, Priority.ALWAYS);

        HBox row = new HBox(12, t, grow, st);
        row.setPadding(new Insets(12));
        row.setAlignment(Pos.CENTER_LEFT);
        row.setStyle("-fx-background-color:#FAF9FC; -fx-background-radius:10; -fx-border-color:#E5CDC1; -fx-border-radius:10;");
        return row;
    }

}
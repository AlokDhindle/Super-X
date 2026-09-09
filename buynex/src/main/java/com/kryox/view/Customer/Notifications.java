package com.kryox.view.Customer;

import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Shopkeeper.OfferModel;

import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Notifications extends Application {

    public String userId;

    public Notifications() {
    }

    public Notifications(String userId) {
        this.userId = userId;
    }

    public Scene getNotificationscene(Runnable callback) {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #EEE5DF;");

        VBox sidebar = CustomerSidebar.createSidebar(userId, "Deals");
        borderPane.setLeft(sidebar);

        HBox topNav = CustomerSidebar.createTopNav(userId, "NOTIFICATIONS", null, () -> CustomerNavigation.navigateToNotifications(userId));
        borderPane.setTop(topNav);

        // Header bar inside content area
        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(10, 0, 10, 0));

        Button backBtn = new Button("← Back to Dashboard");
        backBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        backBtn.setOnAction(e -> {
            if (callback != null) {
                callback.run();
            } else {
                CustomerNavigation.navigateToDashboard(userId);
            }
        });

        Label title = new Label("Notifications & Shopkeeper Offers");
        title.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #9C3700;");

        header.getChildren().addAll(backBtn, title);

        VBox centerBox = new VBox(18);
        centerBox.setPadding(new Insets(20, 30, 30, 30));
        centerBox.setAlignment(Pos.TOP_LEFT);
        centerBox.getChildren().add(header);

        Label subHeading = new Label("🏷️ Active Shopkeeper Offers & Latest Alerts");
        subHeading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        VBox notifList = new VBox(14);
        notifList.setMaxWidth(1000);

        // Standard notification cards
        notifList.getChildren().addAll(
            createNotificationCard("📦 Order Status Update", "Your order has been confirmed by shopkeeper.", "Just now"),
            createNotificationCard("🚚 Delivery Partner Assigned", "Rider is heading to pickup your items.", "1 hour ago")
        );

        Label loadingLabel = new Label("⏳ Loading Shopkeeper Offers...");
        loadingLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #FF6900; -fx-font-weight: bold;");
        notifList.getChildren().add(loadingLabel);

        // Fetch Shopkeeper Offers dynamically from database
        new Thread(() -> {
            List<OfferModel> shopkeeperOffers = null;
            try {
                shopkeeperOffers = OfferController.getAllOffersForAdmin();
            } catch (Exception ex) {
                System.err.println("Error fetching shopkeeper offers: " + ex.getMessage());
            }

            final List<OfferModel> finalOffers = shopkeeperOffers;

            Platform.runLater(() -> {
                notifList.getChildren().remove(loadingLabel);

                if (finalOffers != null && !finalOffers.isEmpty()) {
                    for (OfferModel offer : finalOffers) {
                        if (offer == null) continue;

                        String offerTitle = "🎉 " + (offer.getOfferName() == null ? "Shopkeeper Special Offer" : offer.getOfferName());
                        if (offer.getPromoCode() != null && !offer.getPromoCode().isBlank()) {
                            offerTitle += " (Code: " + offer.getPromoCode() + ")";
                        }

                        String discInfo = "";
                        if (offer.getDiscountValue() > 0) {
                            discInfo = "Get " + String.format("%.0f", offer.getDiscountValue()) +
                                    (offer.getDiscountType() != null && offer.getDiscountType().contains("Percent") ? "%" : "₹") + " OFF! ";
                        }

                        String desc = discInfo + (offer.getDescription() == null ? "Exclusive shopkeeper promotion." : offer.getDescription());
                        String categoryTime = "Category: " + (offer.getCategory() == null ? "General" : offer.getCategory()) +
                                (offer.getEndDate() != null ? " | Valid till: " + offer.getEndDate() : "");

                        VBox offerCard = createShopkeeperOfferCard(offerTitle, desc, categoryTime, offer.getPromoCode());
                        notifList.getChildren().add(0, offerCard);
                    }
                } else {
                    VBox fallback1 = createShopkeeperOfferCard("🎉 Special Grocery Deal (Code: GROCERY20)", "Get 20% OFF on all fresh fruits & organic groceries above ₹30!", "Category: Grocery | Valid till Today", "GROCERY20");
                    VBox fallback2 = createShopkeeperOfferCard("⚡ Super Tech Discount (Code: TECH50)", "Flat ₹50 OFF on premium wireless headphones & smart gear!", "Category: Electronics | Valid till Weekend", "TECH50");
                    notifList.getChildren().addAll(0, List.of(fallback1, fallback2));
                }
            });
        }).start();

        centerBox.getChildren().addAll(subHeading, notifList);

        ScrollPane scrollPane = new ScrollPane(centerBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        borderPane.setCenter(scrollPane);

        return new Scene(borderPane, 1530, 850);
    }

    private VBox createShopkeeperOfferCard(String titleText, String descText, String timeText, String promoCode) {
        VBox card = new VBox(8);
        card.setPadding(new Insets(16, 22, 16, 22));
        card.setStyle(
                "-fx-background-color: #FFF8F3;" +
                "-fx-background-radius: 14;" +
                "-fx-border-color: #FFB889;" +
                "-fx-border-radius: 14;" +
                "-fx-border-width: 1.5;" +
                "-fx-effect: dropshadow(gaussian, rgba(255, 105, 0, 0.12), 10, 0, 0, 3);"
        );

        Label badge = new Label("OFFER FROM SHOPKEEPER");
        badge.setStyle("-fx-background-color: #FF6900; -fx-text-fill: white; -fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 3 8; -fx-background-radius: 6;");

        Label t = new Label(titleText);
        t.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #D94F0B;");

        Label d = new Label(descText);
        d.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 12px; -fx-text-fill: #444444;");

        Label time = new Label("⏰ " + timeText);
        time.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 10px; -fx-text-fill: #888888;");

        Button shopNowBtn = new Button("Shop Offer Deals →");
        shopNowBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 5 12;" +
                "-fx-cursor: hand;"
        );
        shopNowBtn.setOnAction(e -> CustomerNavigation.navigateToDeals(userId));

        Region sp = new Region();
        HBox.setHgrow(sp, Priority.ALWAYS);

        HBox actionRow = new HBox(10, time, sp, shopNowBtn);
        actionRow.setAlignment(Pos.CENTER_LEFT);

        card.getChildren().addAll(badge, t, d, actionRow);
        return card;
    }

    private VBox createNotificationCard(String titleText, String descText, String timeText) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(15, 20, 15, 20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-border-color: #E0D5CE; -fx-border-radius: 12;");

        Label t = new Label(titleText);
        t.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label d = new Label(descText);
        d.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 12px; -fx-text-fill: #666666;");

        Label time = new Label(timeText);
        time.setStyle("-fx-font-family: 'Montserrat'; -fx-font-size: 10px; -fx-text-fill: #999999;");

        card.getChildren().addAll(t, d, time);
        return card;
    }

    @Override
    public void start(Stage myStage) throws Exception {


        Text logo = new Text("BuyNeX");
        logo.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#9C3700;"
        );

        HBox logoBox = new HBox(logo);
        logoBox.setAlignment(Pos.CENTER_LEFT);


        // Profile

        Circle profileCircle = new Circle(11);
        profileCircle.setFill(Color.LIGHTGRAY);

        Label profileName = new Label("Alex Rivera");
        profileName.setStyle(
                "-fx-font-size:8px;" +
                "-fx-font-weight:bold;"
        );

        Label profileType = new Label("Premium Member");
        profileType.setStyle(
                "-fx-font-size:6px;" +
                "-fx-text-fill:#777777;"
        );

        VBox profileText = new VBox(1);
        profileText.getChildren().addAll(
                profileName,
                profileType
        );

        HBox profileBox = new HBox(8);
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(
                new Insets(20, 5, 15, 5)
        );

        profileBox.getChildren().addAll(
                profileCircle,
                profileText
        );


        // Main Menu

        Label profileMenu =
                new Label("♙    Profile");

        Label privacyMenu =
                new Label("♙    Privacy");

        Label notificationMenu =
                new Label("♧    Notifications");

        Label addressMenu =
                new Label("⌖    Address");

        Label paymentMenu =
                new Label("▣    Payment");

        Label ordersMenu =
                new Label("▣    Orders");

        Label deliveryMenu =
                new Label("▣    Delivery");


        Label[] menuLabels = {
                profileMenu,
                privacyMenu,
                notificationMenu,
                addressMenu,
                paymentMenu,
                ordersMenu,
                deliveryMenu
        };


        VBox menuBox = new VBox(3);

        for (Label menu : menuLabels) {

            menu.setPrefWidth(140);
            menu.setPrefHeight(27);

            menu.setPadding(
                    new Insets(0, 5, 0, 10)
            );

            menu.setStyle(
                    "-fx-font-size:7px;" +
                    "-fx-text-fill:#444444;"
            );

            menuBox.getChildren().add(menu);
        }


        // Active Notification

        notificationMenu.setStyle(
                "-fx-background-color:#FFF0E7;" +
                "-fx-text-fill:#9E3508;" +
                "-fx-font-size:7px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:5;"
        );


        Separator separator1 = new Separator();


        // More Menu

        Label language =
                new Label("◉    Language");

        Label appearance =
                new Label("◉    Appearance");

        Label accessibility =
                new Label("⚝    Accessibility");


        Label[] moreLabels = {
                language,
                appearance,
                accessibility
        };


        VBox moreBox = new VBox(3);

        for (Label menu : moreLabels) {

            menu.setPrefWidth(140);
            menu.setPrefHeight(27);

            menu.setPadding(
                    new Insets(0, 5, 0, 10)
            );

            menu.setStyle(
                    "-fx-font-size:7px;" +
                    "-fx-text-fill:#444444;"
            );

            moreBox.getChildren().add(menu);
        }


        Separator separator2 = new Separator();


        // Bottom Menu

        Label security =
                new Label("♢    Login & Security");

        Label help =
                new Label("?    Help");

        Label terms =
                new Label("▤    Terms");


        Label[] bottomLabels = {
                security,
                help,
                terms
        };


        VBox bottomMenu = new VBox(3);

        for (Label menu : bottomLabels) {

            menu.setPrefWidth(140);
            menu.setPrefHeight(27);

            menu.setPadding(
                    new Insets(0, 5, 0, 10)
            );

            menu.setStyle(
                    "-fx-font-size:7px;" +
                    "-fx-text-fill:#444444;"
            );

            bottomMenu.getChildren().add(menu);
        }


        // Upgrade Button

        Button upgrade = new Button("Upgrade to Pro");

        upgrade.setPrefWidth(115);
        upgrade.setPrefHeight(28);

        upgrade.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#A83A08;" +
                "-fx-border-radius:5;" +
                "-fx-background-radius:5;" +
                "-fx-text-fill:#A83A08;" +
                "-fx-font-size:8px;"
        );


        HBox upgradeBox = new HBox(upgrade);

        upgradeBox.setAlignment(Pos.CENTER);


        // LEFT VBOX

        VBox vbLeft = new VBox(8);

        vbLeft.setPrefWidth(165);
        vbLeft.setMinWidth(165);
        vbLeft.setMaxWidth(165);

        vbLeft.setPadding(
                new Insets(15, 12, 15, 12)
        );

        vbLeft.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:" +
                "transparent #DDCFCF transparent transparent;"
        );


        vbLeft.getChildren().addAll(
                logoBox,
                profileBox,
                menuBox,
                separator1,
                moreBox,
                separator2,
                bottomMenu,
                upgradeBox
        );



        Text notificationTitle =
                new Text("Notifications");

        notificationTitle.setStyle(
                "-fx-font-size:25px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#111111;"
        );


        Text notificationDesc =
                new Text(
                        "Manage how you receive updates and alerts."
                );

        notificationDesc.setStyle(
                "-fx-font-size:8px;" +
                "-fx-fill:#666666;"
        );


        // Notification Main Card

        VBox notificationBox =
                new VBox(0);

        notificationBox.setPrefWidth(290);
        notificationBox.setMinWidth(290);
        notificationBox.setMaxWidth(290);

        notificationBox.setPadding(
                new Insets(18, 20, 18, 20)
        );

        notificationBox.setStyle(
                "-fx-background-color:#FCF9FD;" +
                "-fx-background-radius:10;" +
                "-fx-effect:dropshadow(" +
                "gaussian," +
                "rgba(0,0,0,0.12)," +
                "12,0,0,2,3);"
        );


        // Enable All

        Label enableTitle =
                new Label("Enable All Notifications");

        enableTitle.setStyle(
                "-fx-font-size:7px;" +
                "-fx-font-weight:bold;"
        );


        Label enableDesc =
                new Label(
                        "Turn on all alerts with one click."
                );

        enableDesc.setStyle(
                "-fx-font-size:6px;" +
                "-fx-text-fill:#666666;"
        );


        VBox enableText =
                new VBox(2);

        enableText.getChildren().addAll(
                enableTitle,
                enableDesc
        );


        Region enableSpacer =
                new Region();

        HBox.setHgrow(
                enableSpacer,
                javafx.scene.layout.Priority.ALWAYS
        );


        // Orange toggle

        Button enableToggle =
                createToggle(true);


        HBox enableRow =
                new HBox(8);

        enableRow.setPrefHeight(38);
        enableRow.setAlignment(
                Pos.CENTER_LEFT
        );

        enableRow.setPadding(
                new Insets(7)
        );

        enableRow.setStyle(
                "-fx-background-color:#F7F2F8;" +
                "-fx-background-radius:5;"
        );


        enableRow.getChildren().addAll(
                enableText,
                enableSpacer,
                enableToggle
        );


        notificationBox.getChildren().add(
                enableRow
        );


        // Notification rows

        addNotification(
                notificationBox,
                "Order Updates",
                "Alerts about your active orders.",
                true
        );

        addNotification(
                notificationBox,
                "Delivery Updates",
                "Real-time tracking notifications.",
                true
        );

        addNotification(
                notificationBox,
                "Flash Deals",
                "Instant alerts for limited-time offers.",
                false
        );

        addNotification(
                notificationBox,
                "New Offers",
                "Personalised marketplace recommendations.",
                true
        );

        addNotification(
                notificationBox,
                "Price Drop Alerts",
                "Alerts for items in your wishlist.",
                false
        );

        addNotification(
                notificationBox,
                "Wishlist Alerts",
                "Updates on restocked favorites.",
                true
        );

        addNotification(
                notificationBox,
                "Promotional Notifications",
                "News and seasonal campaign updates.",
                false
        );

        addNotification(
                notificationBox,
                "Email Notifications",
                "Summary and transactional emails.",
                true
        );

        addNotification(
                notificationBox,
                "SMS Notifications",
                "Direct text message alerts for critical updates.",
                false
        );


        // Save Button

        Button saveBtn =
                new Button("Save Preferences");

        saveBtn.setPrefWidth(95);
        saveBtn.setPrefHeight(25);

        saveBtn.setStyle(
                "-fx-background-color:#FF6B00;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:7px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:5;"
        );


        HBox saveBox =
                new HBox(saveBtn);

        saveBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        saveBox.setPadding(
                new Insets(10, 0, 0, 0)
        );


        notificationBox.getChildren().add(
                saveBox
        );


        // CENTER VBOX

        VBox vbCenter =
                new VBox(10);

        vbCenter.setPrefWidth(335);
        vbCenter.setMinWidth(335);
        vbCenter.setMaxWidth(335);

        vbCenter.setPadding(
                new Insets(40, 22, 20, 22)
        );


        vbCenter.getChildren().addAll(
                notificationTitle,
                notificationDesc,
                notificationBox
        );



        Circle verifyCircle =
                new Circle(16);

        verifyCircle.setFill(Color.WHITE);

        verifyCircle.setStroke(
                Color.web("#FF6B00")
        );


        Text verifySymbol =
                new Text("⚙");

        verifySymbol.setStyle(
                "-fx-font-size:13px;" +
                "-fx-fill:#A83A08;"
        );


        StackPane verifyIcon =
                new StackPane();

        verifyIcon.getChildren().addAll(
                verifyCircle,
                verifySymbol
        );


        Text verifiedTitle =
                new Text(
                        "Account\nVerified"
                );

        verifiedTitle.setStyle(
                "-fx-font-size:15px;" +
                "-fx-font-weight:bold;" +
                "-fx-fill:#111111;"
        );


        Label verifiedDesc =
                new Label(
                        "Your identity has\n" +
                        "been confirmed. You\n" +
                        "have full access to\n" +
                        "marketplace\n" +
                        "features."
                );

        verifiedDesc.setAlignment(
                Pos.CENTER
        );

        verifiedDesc.setStyle(
                "-fx-font-size:8px;" +
                "-fx-text-fill:#666666;"
        );


        Hyperlink verification =
                new Hyperlink(
                        "View Verification\nDetails"
                );

        verification.setBorder(
                Border.EMPTY
        );

        verification.setStyle(
                "-fx-font-size:8px;" +
                "-fx-text-fill:#A83A08;"
        );


        VBox vbRight =
                new VBox(15);

        vbRight.setPrefWidth(165);
        vbRight.setMinWidth(165);
        vbRight.setMaxWidth(165);

        vbRight.setAlignment(
                Pos.CENTER
        );

        vbRight.setStyle(
                "-fx-background-color:#F5F2F8;" +
                "-fx-border-color:" +
                "transparent transparent transparent #DDCFCF;"
        );


        vbRight.getChildren().addAll(
                verifyIcon,
                verifiedTitle,
                verifiedDesc,
                verification
        );



        HBox center =
                new HBox(
                        0,
                        vbLeft,
                        vbCenter,
                        vbRight
                );

        center.setAlignment(
                Pos.TOP_CENTER
        );



        BorderPane bp =
                new BorderPane();

        bp.setCenter(center);

        bp.setStyle(
                "-fx-background-color:#F7F5F9;"
        );



        Scene scene =
                new Scene(
                        bp,
                        1550,
                        850
                );


        myStage.setScene(scene);

        // IMPORTANT:
        // setMaximized(true) removed

        myStage.setWidth(700);
        myStage.setHeight(720);

        myStage.setResizable(false);

        myStage.show();
    }



    public void addNotification(
            VBox parent,
            String title,
            String description,
            boolean selected
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size:7px;" +
                "-fx-font-weight:bold;"
        );


        Label descLabel =
                new Label(description);

        descLabel.setStyle(
                "-fx-font-size:6px;" +
                "-fx-text-fill:#666666;"
        );


        VBox textBox =
                new VBox(2);

        textBox.getChildren().addAll(
                titleLabel,
                descLabel
        );


        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                javafx.scene.layout.Priority.ALWAYS
        );


        // Screenshot style toggle

        Button toggle =
                createToggle(selected);


        HBox row =
                new HBox(8);

        row.setPrefHeight(35);
        row.setAlignment(
                Pos.CENTER_LEFT
        );


        row.getChildren().addAll(
                textBox,
                spacer,
                toggle
        );


        Separator separator =
                new Separator();


        parent.getChildren().addAll(
                row,
                separator
        );
    }



    public Button createToggle(
            boolean selected
    ) {

        Button toggle =
                new Button();

        toggle.setPrefWidth(25);
        toggle.setMinWidth(25);
        toggle.setMaxWidth(25);

        toggle.setPrefHeight(13);
        toggle.setMinHeight(13);
        toggle.setMaxHeight(13);

        toggle.setText(
                selected ? "●" : ""
        );

        toggle.setStyle(
                selected
                ?
                "-fx-background-color:#FF6B00;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:8px;" +
                "-fx-padding:0;" +
                "-fx-background-radius:10;"
                :
                "-fx-background-color:#DDDDDD;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:8px;" +
                "-fx-padding:0;" +
                "-fx-background-radius:10;"
        );


        toggle.setOnAction(e -> {

            if (toggle.getText().equals("●")) {

                toggle.setText("");

                toggle.setStyle(
                        "-fx-background-color:#DDDDDD;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:8px;" +
                        "-fx-padding:0;" +
                        "-fx-background-radius:10;"
                );

            } else {

                toggle.setText("●");

                toggle.setStyle(
                        "-fx-background-color:#FF6B00;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:8px;" +
                        "-fx-padding:0;" +
                        "-fx-background-radius:10;"
                );
            }
        });


        return toggle;
    }
}
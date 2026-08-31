package com.kryox.view.Admin;

import java.awt.Desktop;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kryox.dao.Delivery.DeliveryPartnerDAO;

import com.kryox.view.Customer.Homepage;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DeliveryVerificationPage {

    public Scene getUserScene() {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:#FAF8FC;");

        VBox left = new VBox();
        left.setPrefWidth(210);
        left.setSpacing(28);
        left.setPadding(new Insets(30, 15, 20, 15));
        left.setStyle("-fx-background-color:#ebccb7;");

        Text logo = new Text("Admin Panel");
        logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logo.setFill(Color.web("#A83E00"));

        Text controller = new Text("Marketplace Controller");
        controller.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        controller.setFill(Color.web("#999999"));

        VBox logoBox = new VBox(4, logo, controller);

        VBox menu = new VBox();
        menu.setSpacing(4);

        HBox dashboard = new HBox();
        dashboard.setSpacing(10);
        dashboard.setAlignment(Pos.CENTER_LEFT);
        dashboard.setPadding(new Insets(10, 12, 10, 12));
        dashboard.setPrefWidth(180);
        dashboard.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image dashboardImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/dashboard.png"
                ).toExternalForm()
        );

        ImageView dashboardIcon = new ImageView(dashboardImage);
        dashboardIcon.setFitWidth(20);
        dashboardIcon.setFitHeight(20);
        dashboardIcon.setPreserveRatio(true);

        Text dashboardText = new Text("Dashboard");
        dashboardText.setFill(Color.web("#333333"));
        dashboardText.setFont(Font.font("Arial", 14));

        dashboard.getChildren().addAll(dashboardIcon, dashboardText);

        dashboard.setOnMouseEntered(e -> {
            setHoverStyle(dashboard, dashboardText);
        });

        dashboard.setOnMouseExited(e -> {
            setNormalStyle(dashboard, dashboardText);
        });

        dashboard.setOnMouseClicked(e -> {
            AdminDashboardPage page = new AdminDashboardPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        HBox users = new HBox();
        users.setSpacing(10);
        users.setAlignment(Pos.CENTER_LEFT);
        users.setPadding(new Insets(10, 12, 10, 12));
        users.setPrefWidth(180);
        users.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image usersImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/admin_logo.png"
                ).toExternalForm()
        );

        ImageView usersIcon = new ImageView(usersImage);
        usersIcon.setFitWidth(20);
        usersIcon.setFitHeight(20);
        usersIcon.setPreserveRatio(true);

        Text usersText = new Text("Users");
        usersText.setFill(Color.web("#333333"));
        usersText.setFont(Font.font("Arial", 14));

        users.getChildren().addAll(usersIcon, usersText);

        users.setOnMouseEntered(e -> {
            setHoverStyle(users, usersText);
        });

        users.setOnMouseExited(e -> {
            setNormalStyle(users, usersText);
        });

        users.setOnMouseClicked(e -> {
            UserManagementPage page = new UserManagementPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        HBox shops = new HBox();
        shops.setSpacing(10);
        shops.setAlignment(Pos.CENTER_LEFT);
        shops.setPadding(new Insets(10, 12, 10, 12));
        shops.setPrefWidth(180);
        shops.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image shopsImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/shop.png"
                ).toExternalForm()
        );

        ImageView shopsIcon = new ImageView(shopsImage);
        shopsIcon.setFitWidth(20);
        shopsIcon.setFitHeight(20);
        shopsIcon.setPreserveRatio(true);

        Text shopsText = new Text("Shops");
        shopsText.setFill(Color.web("#333333"));
        shopsText.setFont(Font.font("Arial", 14));

        shops.getChildren().addAll(shopsIcon, shopsText);

        shops.setOnMouseEntered(e -> {
            setHoverStyle(shops, shopsText);
        });

        shops.setOnMouseExited(e -> {
            setNormalStyle(shops, shopsText);
        });

        shops.setOnMouseClicked(e -> {
            ShopVerificationPage page = new ShopVerificationPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        HBox delivery = new HBox();
        delivery.setSpacing(10);
        delivery.setAlignment(Pos.CENTER_LEFT);
        delivery.setPadding(new Insets(10, 12, 10, 12));
        delivery.setPrefWidth(180);
        delivery.setStyle(
                "-fx-background-color:#FF6500;" +
                "-fx-background-radius:10;");

        Text deliveryIcon = new Text("🚚");
        deliveryIcon.setFont(Font.font("Arial", 18));

        Text deliveryText = new Text("Delivery");
        deliveryText.setFill(Color.WHITE);
        deliveryText.setFont(
                Font.font("Arial", FontWeight.BOLD, 14));

        delivery.getChildren().addAll(deliveryIcon, deliveryText);

        delivery.setOnMouseEntered(e -> {
            delivery.setStyle(
                    "-fx-background-color:#D94F00;" +
                    "-fx-background-radius:10;");

            ScaleTransition st = new ScaleTransition(
                    Duration.millis(120),
                    delivery);

            st.setToX(1.03);
            st.setToY(1.03);
            st.play();
        });

        delivery.setOnMouseExited(e -> {
            delivery.setStyle(
                    "-fx-background-color:#FF6500;" +
                    "-fx-background-radius:10;");

            deliveryText.setFill(Color.WHITE);

            ScaleTransition st = new ScaleTransition(
                    Duration.millis(120),
                    delivery);

            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        HBox offers = new HBox();
        offers.setSpacing(10);
        offers.setAlignment(Pos.CENTER_LEFT);
        offers.setPadding(new Insets(10, 12, 10, 12));
        offers.setPrefWidth(180);
        offers.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image offersImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/tag.png"
                ).toExternalForm()
        );

        ImageView offersIcon = new ImageView(offersImage);
        offersIcon.setFitWidth(20);
        offersIcon.setFitHeight(20);
        offersIcon.setPreserveRatio(true);

        Text offersText = new Text("Offers");
        offersText.setFill(Color.web("#333333"));
        offersText.setFont(Font.font("Arial", 14));

        offers.getChildren().addAll(offersIcon, offersText);

        offers.setOnMouseEntered(e -> {
            setHoverStyle(offers, offersText);
        });

        offers.setOnMouseExited(e -> {
            setNormalStyle(offers, offersText);
        });

        offers.setOnMouseClicked(e -> {
            OfferPage page = new OfferPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        HBox analytics = new HBox();
        analytics.setSpacing(10);
        analytics.setAlignment(Pos.CENTER_LEFT);
        analytics.setPadding(new Insets(10, 12, 10, 12));
        analytics.setPrefWidth(180);
        analytics.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image analyticsImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/stats.png"
                ).toExternalForm()
        );

        ImageView analyticsIcon = new ImageView(analyticsImage);
        analyticsIcon.setFitWidth(20);
        analyticsIcon.setFitHeight(20);
        analyticsIcon.setPreserveRatio(true);

        Text analyticsText = new Text("Analytics");
        analyticsText.setFill(Color.web("#333333"));
        analyticsText.setFont(Font.font("Arial", 14));

        analytics.getChildren().addAll(analyticsIcon, analyticsText);

        analytics.setOnMouseEntered(e -> {
            setHoverStyle(analytics, analyticsText);
        });

        analytics.setOnMouseExited(e -> {
            setNormalStyle(analytics, analyticsText);
        });

        analytics.setOnMouseClicked(e -> {
            AdminAnalyticsPage page = new AdminAnalyticsPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        menu.getChildren().addAll(
                dashboard,
                users,
                shops,
                delivery,
                offers,
                analytics);

        VBox bottomMenu = new VBox();
        bottomMenu.setSpacing(4);

        HBox settings = new HBox();
        settings.setSpacing(10);
        settings.setAlignment(Pos.CENTER_LEFT);
        settings.setPadding(new Insets(10, 12, 10, 12));
        settings.setPrefWidth(180);
        settings.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image settingsImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/setting.png"
                ).toExternalForm()
        );

        ImageView settingsIcon = new ImageView(settingsImage);
        settingsIcon.setFitWidth(20);
        settingsIcon.setFitHeight(20);
        settingsIcon.setPreserveRatio(true);

        Text settingsText = new Text("Settings");
        settingsText.setFill(Color.web("#333333"));
        settingsText.setFont(Font.font("Arial", 14));

        settings.getChildren().addAll(settingsIcon, settingsText);

        settings.setOnMouseEntered(e -> {
            setHoverStyle(settings, settingsText);
        });

        settings.setOnMouseExited(e -> {
            setNormalStyle(settings, settingsText);
        });

        settings.setOnMouseClicked(e -> {
            SettingsPage page = new SettingsPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        HBox support = new HBox();
        support.setSpacing(10);
        support.setAlignment(Pos.CENTER_LEFT);
        support.setPadding(new Insets(10, 12, 10, 12));
        support.setPrefWidth(180);
        support.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        Image supportImage = new Image(
                getClass().getResource(
                        "/assets/images/admin/service-call.png"
                ).toExternalForm()
        );

        ImageView supportIcon = new ImageView(supportImage);
        supportIcon.setFitWidth(20);
        supportIcon.setFitHeight(20);
        supportIcon.setPreserveRatio(true);

        Text supportText = new Text("Support");
        supportText.setFill(Color.web("#333333"));
        supportText.setFont(Font.font("Arial", 14));

        support.getChildren().addAll(supportIcon, supportText);

        support.setOnMouseEntered(e -> {
            setHoverStyle(support, supportText);
        });

        support.setOnMouseExited(e -> {
            setNormalStyle(support, supportText);
        });

        support.setOnMouseClicked(e -> {
            SupportPage page = new SupportPage();
            Homepage.HomepageStage.setScene(page.getUserScene());
        });

        bottomMenu.getChildren().addAll(
                settings,
                support);

        AdminProfileCard adminProfileCard =
                new AdminProfileCard();

        HBox profile =
                adminProfileCard.getProfileCard();

        Region leftGrow = new Region();

        VBox.setVgrow(
                leftGrow,
                Priority.ALWAYS);

        left.getChildren().addAll(
                logoBox,
                menu,
                new Separator(),
                bottomMenu,
                leftGrow,
                profile);

        root.setLeft(left);

        VBox rightBox = new VBox();
        rightBox.setSpacing(20);
        rightBox.setPadding(new Insets(30));
        rightBox.setStyle("-fx-background-color:#eee5df;");

        HBox pageHeader = new HBox();
        pageHeader.setAlignment(Pos.CENTER_LEFT);

        VBox headingText = new VBox(4);

        Text title = new Text("Delivery Verification");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.web("#B84300"));

        Text subtitle = new Text(
                "Review delivery partner identity, license and vehicle documents.");

        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setFill(Color.web("#777777"));

        headingText.getChildren().addAll(
                title,
                subtitle);

        Region headerSpace = new Region();
        HBox.setHgrow(headerSpace, Priority.ALWAYS);

        VBox pending = createCountBox(
                "PENDING",
                "0",
                false);

        Text pendingValue =
                (Text) pending.getChildren().get(1);

        VBox today = createCountBox(
                "TODAY",
                "0",
                true);

        Text todayValue =
                (Text) today.getChildren().get(1);

        pageHeader.getChildren().addAll(
                headingText,
                headerSpace,
                pending,
                today);

        HBox approvalHeader = new HBox();
        approvalHeader.setAlignment(Pos.CENTER_LEFT);

        Text approvalTitle = new Text(
                "▣  Pending Delivery Approvals");

        approvalTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18));

        Region approvalSpace = new Region();
        HBox.setHgrow(approvalSpace, Priority.ALWAYS);

        Text filter = new Text(
                "Filter By Vehicle ≡");

        filter.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12));

        filter.setFill(
                Color.web("#B84300"));

        approvalHeader.getChildren().addAll(
                approvalTitle,
                approvalSpace,
                filter);

        HBox mainContent = new HBox(15);
        mainContent.setAlignment(Pos.TOP_LEFT);

        VBox deliveryCards = new VBox(14);
        deliveryCards.setPrefWidth(520);

        Text loadingText =
                new Text(
                        "Loading pending delivery partners..."
                );

        loadingText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        loadingText.setFill(
                Color.GRAY
        );

        deliveryCards
                .getChildren()
                .add(
                        loadingText
                );

        VBox reviewPanel = new VBox(12);
        reviewPanel.setPrefWidth(560);
        reviewPanel.setMinWidth(500);
        reviewPanel.setPrefHeight(520);
        reviewPanel.setAlignment(Pos.CENTER);
        reviewPanel.setStyle(
                "-fx-background-color:#FAF9FC;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#E8DCD5;" +
                "-fx-border-radius:15;");

        Text check = new Text("✓");
        check.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        42));

        check.setFill(
                Color.web("#E5D3CB"));

        Text reviewText = new Text(
                "Select a\ndelivery partner to\nbegin the review\nprocess.");

        reviewText.setFont(
                Font.font(
                        "Arial",
                        14));

        reviewText.setFill(
                Color.web("#999999"));

        reviewText.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER);

        reviewPanel.getChildren().addAll(
                check,
                reviewText);

        HBox.setHgrow(
                reviewPanel,
                Priority.ALWAYS);

        DeliveryPartnerDAO deliveryPartnerDAO =
                new DeliveryPartnerDAO();

        Thread pendingDeliveryThread =
                new Thread(() -> {

                    List<QueryDocumentSnapshot> pendingPartners =
                            deliveryPartnerDAO
                                    .getPendingPartners();

                    int todayCount = 0;

                    LocalDate todayDate =
                            LocalDate.now();

                    for (QueryDocumentSnapshot document :
                            pendingPartners) {

                        Long createdAt =
                                document.getLong(
                                        "createdAt"
                                );

                        if (createdAt != null) {

                            LocalDate createdDate =
                                    Instant.ofEpochMilli(
                                            createdAt
                                    )
                                            .atZone(
                                                    ZoneId.systemDefault()
                                            )
                                            .toLocalDate();

                            if (todayDate.equals(
                                    createdDate
                            )) {

                                todayCount++;
                            }
                        }
                    }

                    int finalTodayCount =
                            todayCount;

                    Platform.runLater(() -> {

                        deliveryCards
                                .getChildren()
                                .clear();

                        pendingValue.setText(
                                String.valueOf(
                                        pendingPartners.size()
                                )
                        );

                        todayValue.setText(
                                String.valueOf(
                                        finalTodayCount
                                )
                        );

                        if (pendingPartners.isEmpty()) {

                            Text noPending =
                                    new Text(
                                            "No pending delivery partner verifications."
                                    );

                            noPending.setFont(
                                    Font.font(
                                            "Arial",
                                            FontWeight.BOLD,
                                            14
                                    )
                            );

                            noPending.setFill(
                                    Color.GRAY
                            );

                            deliveryCards
                                    .getChildren()
                                    .add(
                                            noPending
                                    );

                            return;
                        }

                        for (QueryDocumentSnapshot document :
                                pendingPartners) {

                            VBox card =
                                    createPendingDeliveryCard(
                                            document,
                                            reviewPanel,
                                            deliveryCards
                                    );

                            deliveryCards
                                    .getChildren()
                                    .add(
                                            card
                                    );
                        }
                    });
                });

        pendingDeliveryThread
                .setDaemon(true);

        pendingDeliveryThread
                .start();

        mainContent.getChildren().addAll(
                deliveryCards,
                reviewPanel);

        rightBox.getChildren().addAll(
                pageHeader,
                approvalHeader,
                mainContent);

        root.setCenter(rightBox);

        Scene scene = new Scene(
                root,
                1550,
                850);

        return scene;
    }

    private VBox createCountBox(
            String label,
            String value,
            boolean orange) {

        VBox box = new VBox(2);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(8, 15, 8, 15));
        box.setStyle(
                "-fx-background-color:#EBE9EF;" +
                "-fx-background-radius:8;");

        Text title = new Text(label);
        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10));

        title.setFill(Color.GRAY);

        Text number = new Text(value);
        number.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        20));

        if (orange) {
            number.setFill(
                    Color.web("#B84300"));
        }

        box.getChildren().addAll(
                title,
                number);

        return box;
    }

    private VBox createDeliveryCard(
            String name,
            String details,
            String vehicleType,
            String doc1,
            String doc2,
            boolean warning) {

        VBox card = new VBox(12);
        card.setPadding(new Insets(14));
        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:12;");

        HBox top = new HBox(10);
        top.setAlignment(Pos.CENTER_LEFT);

        Circle avatarCircle = new Circle(
                22,
                Color.web("#FFE7D8"));

        Text firstLetter = new Text(
                name.substring(0, 1)
                        .toUpperCase());

        firstLetter.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16));

        firstLetter.setFill(
                Color.web("#B84300"));

        javafx.scene.layout.StackPane avatar =
                new javafx.scene.layout.StackPane(
                        avatarCircle,
                        firstLetter);

        VBox info = new VBox(3);

        Text nameText = new Text(name);
        nameText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17));

        Text detailText = new Text(details);
        detailText.setFont(
                Font.font(
                        "Arial",
                        11));

        detailText.setFill(Color.GRAY);

        info.getChildren().addAll(
                nameText,
                detailText);

        Region topSpace = new Region();
        HBox.setHgrow(
                topSpace,
                Priority.ALWAYS);

        Text vehicle = new Text(vehicleType);
        vehicle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9));

        vehicle.setFill(Color.GRAY);

        top.getChildren().addAll(
                avatar,
                info,
                topSpace,
                vehicle);

        HBox bottom = new HBox(12);
        bottom.setAlignment(Pos.CENTER_LEFT);

        Text firstDoc = new Text(doc1);
        firstDoc.setFont(
                Font.font(
                        "Arial",
                        11));

        Text secondDoc = new Text(doc2);
        secondDoc.setFont(
                Font.font(
                        "Arial",
                        11));

        if (warning) {
            secondDoc.setFill(
                    Color.web("#C0392B"));
        }

        Region bottomSpace = new Region();
        HBox.setHgrow(
                bottomSpace,
                Priority.ALWAYS);

        Button view = new Button(
                "View Documents");

        view.setPrefHeight(36);
        view.setStyle(
                "-fx-background-color:#FF6500;" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:7;" +
                "-fx-font-size:12px;");

        bottom.getChildren().addAll(
                firstDoc,
                secondDoc,
                bottomSpace,
                view);

        card.getChildren().addAll(
                top,
                bottom);

        return card;
    }

    private VBox createPendingDeliveryCard(
            QueryDocumentSnapshot document,
            VBox reviewPanel,
            VBox deliveryCards
    ) {

        String uid =
                document.getId();

        String fullName =
                getString(
                        document,
                        "fullName"
                );

        if (fullName == null ||
                fullName.isBlank()) {

            fullName =
                    "Delivery Partner";
        }

        String mobile =
                getString(
                        document,
                        "mobile"
                );

        String address =
                getString(
                        document,
                        "address"
                );

        String vehicleType =
                getString(
                        document,
                        "vehicleType"
                );

        String vehicleNumber =
                getString(
                        document,
                        "vehicleNumber"
                );

        Map<String, Object> adminVerification =
                getAdminVerification(
                        document
                );

        String idProofUrl =
                getMapString(
                        adminVerification,
                        "idCardUrl"
                );

        String drivingLicenseUrl =
                getMapString(
                        adminVerification,
                        "licenseDocUrl"
                );

        String rcUrl =
                getMapString(
                        adminVerification,
                        "rcBookUrl"
                );

        if (idProofUrl == null ||
                idProofUrl.isBlank()) {

            idProofUrl =
                    getString(
                            document,
                            "idCardPath"
                    );
        }

        if (drivingLicenseUrl == null ||
                drivingLicenseUrl.isBlank()) {

            drivingLicenseUrl =
                    getString(
                            document,
                            "licenseDocPath"
                    );
        }

        if (rcUrl == null ||
                rcUrl.isBlank()) {

            rcUrl =
                    getString(
                            document,
                            "rcBookPath"
                    );
        }

        String details = "";

        if (address != null &&
                !address.isBlank()) {

            details += address;
        }

        if (mobile != null &&
                !mobile.isBlank()) {

            if (!details.isBlank()) {
                details += " • ";
            }

            details += mobile;
        }

        if (details.isBlank()) {
            details = "Pending verification";
        }

        String safeVehicleType =
                vehicleType == null ||
                        vehicleType.isBlank()
                        ? "N/A"
                        : vehicleType;

        String finalFullName =
                fullName;

        String finalDetails =
                details;

        String finalVehicleNumber =
                vehicleNumber == null
                        ? ""
                        : vehicleNumber;

        String finalIdProofUrl =
                idProofUrl;

        String finalDrivingLicenseUrl =
                drivingLicenseUrl;

        String finalRcUrl =
                rcUrl;

        VBox card =
                createDeliveryCard(
                        finalFullName,
                        finalDetails,
                        safeVehicleType,
                        "▧ ID Proof",
                        "▧ Driving License",
                        false
                );

        Button view =
                (Button) ((HBox) card
                        .getChildren()
                        .get(1))
                        .getChildren()
                        .get(
                                ((HBox) card
                                        .getChildren()
                                        .get(1))
                                        .getChildren()
                                        .size() - 1
                        );

        view.setOnAction(e -> {

            for (javafx.scene.Node node :
                    deliveryCards.getChildren()) {

                if (node instanceof VBox) {

                    ((VBox) node).setStyle(
                            "-fx-background-color:white;" +
                            "-fx-background-radius:12;"
                    );
                }
            }

            card.setStyle(
                    "-fx-background-color:white;" +
                    "-fx-background-radius:12;" +
                    "-fx-border-color:#FF6500;" +
                    "-fx-border-width:2;" +
                    "-fx-border-radius:12;"
            );

            showDeliveryReview(
                    reviewPanel,
                    finalFullName,
                    finalDetails,
                    safeVehicleType,
                    finalVehicleNumber,
                    finalIdProofUrl,
                    finalDrivingLicenseUrl,
                    finalRcUrl,
                    uid
            );
        });

        return card;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getAdminVerification(
            QueryDocumentSnapshot document
    ) {

        try {

            Object value =
                    document.get(
                            "adminVerification"
                    );

            if (value instanceof Map) {

                return (Map<String, Object>) value;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private String getString(
            QueryDocumentSnapshot document,
            String field
    ) {

        try {

            String value =
                    document.getString(
                            field
                    );

            return value == null
                    ? ""
                    : value;

        } catch (Exception e) {

            return "";
        }
    }

    private String getMapString(
            Map<String, Object> map,
            String key
    ) {

        if (map == null) {
            return "";
        }

        Object value =
                map.get(key);

        return value == null
                ? ""
                : String.valueOf(value);
    }


    public void showDeliveryReview(
            VBox reviewPanel,
            String partnerName,
            String locationAndMobile,
            String vehicleType,
            String vehicleNumber,
            String idProofUrl,
            String drivingLicenseUrl,
            String rcUrl,
            String deliveryPartnerUid) {

        reviewPanel.getChildren().clear();
        reviewPanel.setAlignment(Pos.TOP_LEFT);
        reviewPanel.setSpacing(14);
        reviewPanel.setPadding(new Insets(25));
        reviewPanel.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:15;" +
                "-fx-border-color:#E8DCD5;" +
                "-fx-border-radius:15;");

        Text panelTitle = new Text(
                "Delivery Partner Review");

        panelTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        24));

        Circle avatarCircle = new Circle(
                26,
                Color.web("#FFE7D8"));

        Text avatarText = new Text(
                partnerName.substring(0, 1)
                        .toUpperCase());

        avatarText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19));

        avatarText.setFill(
                Color.web("#B84300"));

        javafx.scene.layout.StackPane avatar =
                new javafx.scene.layout.StackPane(
                        avatarCircle,
                        avatarText);

        Text nameText = new Text(partnerName);
        nameText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19));

        Text infoText = new Text(
                locationAndMobile);

        infoText.setFont(
                Font.font(
                        "Arial",
                        12));

        infoText.setFill(Color.GRAY);

        VBox partnerInfo = new VBox(
                4,
                nameText,
                infoText);

        Region partnerSpace = new Region();
        HBox.setHgrow(
                partnerSpace,
                Priority.ALWAYS);

        Label vehicleLabel = new Label(
                vehicleType);

        vehicleLabel.setStyle(
                "-fx-background-color:#F7E5D7;" +
                "-fx-padding:5 12 5 12;" +
                "-fx-background-radius:8;" +
                "-fx-font-size:11px;");

        HBox partnerHeader = new HBox(
                12,
                avatar,
                partnerInfo,
                partnerSpace,
                vehicleLabel);

        partnerHeader.setAlignment(
                Pos.CENTER_LEFT);

        Label vehicleNumberLabel =
                new Label(
                        "Vehicle No: " +
                                vehicleNumber);

        vehicleNumberLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:#555555;");

        HBox idRow = createDocumentRow(
                "ID Proof",
                idProofUrl);

        HBox licenseRow = createDocumentRow(
                "Driving License",
                drivingLicenseUrl);

        HBox rcRow = createDocumentRow(
                "Vehicle RC",
                rcUrl);

        Label noteLabel = new Label(
                "Review Note (optional)");

        noteLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;");

        TextArea reviewNote =
                new TextArea();

        reviewNote.setPromptText(
                "Add a note for the delivery partner...");

        reviewNote.setPrefRowCount(3);
        reviewNote.setWrapText(true);
        reviewNote.setStyle(
                "-fx-font-size:13px;");

        Button reject = new Button(
                "Reject");

        reject.setPrefSize(
                130,
                44);

        reject.setStyle(
                "-fx-background-color:white;" +
                "-fx-text-fill:#E53935;" +
                "-fx-border-color:#E53935;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;");

        Button approve = new Button(
                "Approve Partner");

        approve.setPrefSize(
                160,
                44);

        approve.setStyle(
                "-fx-background-color:#FF6500;" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:7;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;");

        Region actionSpace = new Region();
        HBox.setHgrow(
                actionSpace,
                Priority.ALWAYS);

        HBox actions = new HBox(
                reject,
                actionSpace,
                approve);

        actions.setAlignment(
                Pos.CENTER_LEFT);

        reject.setOnAction(e -> {

            if (reviewNote
                    .getText()
                    .isBlank()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING);

                alert.setHeaderText(null);
                alert.setContentText(
                        "Reject करण्याआधी reason लिहा.");

                alert.showAndWait();
                return;
            }

            DeliveryPartnerDAO dao =
                    new DeliveryPartnerDAO();

            boolean rejected =
                    dao.rejectPartner(
                            deliveryPartnerUid,
                            reviewNote
                                    .getText()
                                    .trim()
                    );

            if (rejected) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);
                alert.setContentText(
                        partnerName +
                                " verification rejected.");

                alert.showAndWait();

                DeliveryVerificationPage page =
                        new DeliveryVerificationPage();

                Homepage.HomepageStage.setScene(
                        page.getUserScene()
                );

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setHeaderText(null);
                alert.setContentText(
                        "Delivery partner rejection failed.");

                alert.showAndWait();
            }
        });

        approve.setOnAction(e -> {

            DeliveryPartnerDAO dao =
                    new DeliveryPartnerDAO();

            boolean approved =
                    dao.approvePartner(
                            deliveryPartnerUid
                    );

            if (approved) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setHeaderText(null);
                alert.setContentText(
                        partnerName +
                                " approved successfully.");

                alert.showAndWait();

                DeliveryVerificationPage page =
                        new DeliveryVerificationPage();

                Homepage.HomepageStage.setScene(
                        page.getUserScene()
                );

            } else {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR);

                alert.setHeaderText(null);
                alert.setContentText(
                        "Delivery partner approval failed.");

                alert.showAndWait();
            }
        });

        reviewPanel.getChildren().addAll(
                panelTitle,
                partnerHeader,
                vehicleNumberLabel,
                idRow,
                licenseRow,
                rcRow,
                noteLabel,
                reviewNote,
                actions);
    }

    private HBox createDocumentRow(
            String documentName,
            String documentUrl) {

        Text icon = new Text("▣");
        icon.setFont(
                Font.font(
                        "Arial",
                        24));

        Text name = new Text(
                documentName);

        name.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        Region space = new Region();
        HBox.setHgrow(
                space,
                Priority.ALWAYS);

        boolean available =
                documentUrl != null &&
                !documentUrl.isBlank();

        Label status = new Label(
                available
                        ? "Submitted"
                        : "Missing");

        if (available) {
            status.setStyle(
                    "-fx-background-color:#DFF2DA;" +
                    "-fx-text-fill:#26733C;" +
                    "-fx-padding:4 10 4 10;" +
                    "-fx-background-radius:7;" +
                    "-fx-font-size:12px;");
        } else {
            status.setStyle(
                    "-fx-background-color:#FFE1E1;" +
                    "-fx-text-fill:#B22222;" +
                    "-fx-padding:4 10 4 10;" +
                    "-fx-background-radius:7;" +
                    "-fx-font-size:12px;");
        }

        Button view = new Button(
                "View");

        view.setPrefSize(
                100,
                36);

        view.setDisable(!available);

        view.setStyle(
                "-fx-background-color:white;" +
                "-fx-text-fill:#FF6500;" +
                "-fx-border-color:#FF6500;" +
                "-fx-border-radius:7;" +
                "-fx-background-radius:7;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;");

        view.setOnAction(e -> {
            openDocument(documentUrl);
        });

        HBox row = new HBox(
                15,
                icon,
                name,
                space,
                status,
                view);

        row.setAlignment(
                Pos.CENTER_LEFT);

        row.setPadding(
                new Insets(
                        10,
                        0,
                        10,
                        0));

        row.setStyle(
                "-fx-border-color:#EEE9F0;" +
                "-fx-border-width:0 0 1 0;");

        return row;
    }

    private void setHoverStyle(
            HBox item,
            Text text) {

        item.setStyle(
                "-fx-background-color:#D94F00;" +
                "-fx-background-radius:10;");

        text.setFill(Color.WHITE);
        text.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14));

        ScaleTransition st =
                new ScaleTransition(
                        Duration.millis(120),
                        item);

        st.setToX(1.03);
        st.setToY(1.03);
        st.play();
    }

    private void setNormalStyle(
            HBox item,
            Text text) {

        item.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-background-radius:10;");

        text.setFill(
                Color.web("#333333"));

        text.setFont(
                Font.font(
                        "Arial",
                        14));

        ScaleTransition st =
                new ScaleTransition(
                        Duration.millis(120),
                        item);

        st.setToX(1);
        st.setToY(1);
        st.play();
    }

    public void openDocument(
            String documentUrl) {

        if (documentUrl == null ||
                documentUrl.isBlank() ||
                documentUrl.contains(
                        "CLOUDINARY_URL")) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.WARNING);

            alert.setHeaderText(null);
            alert.setContentText(
                    "Document URL उपलब्ध नाही.");

            alert.showAndWait();
            return;
        }

        try {
            Desktop.getDesktop()
                    .browse(
                            new URI(
                                    documentUrl));
        } catch (Exception e) {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR);

            alert.setHeaderText(null);
            alert.setContentText(
                    "Document open करता आली नाही.");

            alert.showAndWait();
            e.printStackTrace();
        }
    }
}

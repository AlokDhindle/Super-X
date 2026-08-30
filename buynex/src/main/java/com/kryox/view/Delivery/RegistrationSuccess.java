package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.InputStream;

public class RegistrationSuccess {

    private static final String DEFAULT_IMAGE_PATH = "src/main/resources/assets/requirements/delivery2.jpeg";

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    public static void show(Scene scene) {
        show(scene, DEFAULT_IMAGE_PATH);
    }

    public static void show(Scene scene, String customImagePath) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        // 1. Top Bar
        root.setTop(createTopHeader(scene));

        // 2. Main Content Container
        HBox mainContainer = new HBox(60);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(50, 70, 70, 70));

        VBox leftColumn = createLeftColumn(customImagePath);
        VBox rightColumn = createRightColumn(scene);

        mainContainer.getChildren().addAll(leftColumn, rightColumn);
        root.setCenter(mainContainer);

        // Smooth Scene Transition
        if (scene != null) {
            scene.setRoot(root);
        }
    }

    // =========================================================================
    // TOP HEADER
    // =========================================================================
    private static BorderPane createTopHeader(Scene scene) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 40 0 40;"
        );

        Text logo = new Text("BuyNeX");
        logo.setStyle("-fx-font-size: 28px; -fx-fill: " + ORANGE_GRADIENT + "; -fx-font-weight: bold;");
        topBar.setLeft(new HBox(logo));
        ((HBox) topBar.getLeft()).setAlignment(Pos.CENTER_LEFT);

        HBox rightNav = new HBox(16);
        rightNav.setAlignment(Pos.CENTER_RIGHT);

        // Runnable Actions
        Runnable openSupportTask = new Runnable() {
            @Override
            public void run() {
                DeliverySupport.show(scene, "SUCCESS");
            }
        };

        Runnable openLoginTask = new Runnable() {
            @Override
            public void run() {
                Deliverylogin.show(scene);
            }
        };

        Text support = new Text("ⓘ Support");
        support.setStyle("-fx-font-size: 14px; -fx-fill: #4b5563; -fx-font-weight: bold; -fx-cursor: hand;");
        support.setOnMouseClicked(e -> openSupportTask.run());

        Button partnerLogin = new Button("Partner Login");
        partnerLogin.setPrefHeight(36);
        partnerLogin.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: #374151;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0 18 0 18;" +
                "-fx-cursor: hand;"
        );
        partnerLogin.setOnAction(e -> openLoginTask.run());

        rightNav.getChildren().addAll(support, partnerLogin);
        topBar.setRight(rightNav);

        return topBar;
    }

    // =========================================================================
    // LEFT COLUMN (IMAGE CONTAINER + BADGES)
    // =========================================================================
    private static VBox createLeftColumn(String path) {
        VBox col = new VBox(20);
        col.setPrefWidth(420);
        col.setMaxWidth(420);
        col.setAlignment(Pos.CENTER);

        StackPane imageCard = new StackPane();
        imageCard.setPrefSize(420, 260);
        imageCard.setMaxSize(420, 260);
        imageCard.setStyle("-fx-background-color: transparent;");

        Image img = createResolvedImage(path);

        ImageView imgView = new ImageView();
        imgView.setFitWidth(420);
        imgView.setFitHeight(260);
        imgView.setPreserveRatio(false);
        imgView.setSmooth(true);

        if (img != null && !img.isError()) {
            imgView.setImage(img);
        }

        Rectangle clip = new Rectangle(420, 260);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imgView.setClip(clip);

        imageCard.getChildren().add(imgView);

        HBox midRow = new HBox(16);
        VBox reviewCard = createSmallBadgeCard("⏱", "24-48h", "REVIEW TIME");
        VBox supportCard = createSmallBadgeCard("🎧", "Live", "SUPPORT");

        midRow.getChildren().addAll(reviewCard, supportCard);
        HBox.setHgrow(reviewCard, Priority.ALWAYS);
        HBox.setHgrow(supportCard, Priority.ALWAYS);

        col.getChildren().addAll(imageCard, midRow);
        return col;
    }

    private static Image createResolvedImage(String rawPath) {
        if (rawPath == null || rawPath.trim().isEmpty()) {
            return null;
        }

        String normalizedPath = rawPath.replace("\\", "/");
        File file = new File(normalizedPath);
        if (file.exists() && file.isFile()) {
            return new Image(file.toURI().toString(), 420, 260, false, true);
        }

        String resourcePath = normalizedPath.startsWith("/") ? normalizedPath : "/" + normalizedPath;
        InputStream stream = RegistrationSuccess.class.getResourceAsStream(resourcePath);
        if (stream != null) {
            return new Image(stream, 420, 260, false, true);
        }

        try {
            return new Image(normalizedPath, 420, 260, false, true);
        } catch (Exception ignored) {
            return null;
        }
    }

    private static VBox createSmallBadgeCard(String icon, String title, String subtitle) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(18));
        card.setAlignment(Pos.CENTER);
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 14;" +
                "-fx-border-radius: 14;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.04), 8, 0, 0, 2);"
        );

        Label iconLbl = new Label(icon);
        iconLbl.setStyle("-fx-font-size: 18px; -fx-text-fill: " + ORANGE_PRIMARY + ";");

        Label titleLbl = new Label(title);
        titleLbl.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label subLbl = new Label(subtitle);
        subLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af; -fx-font-weight: bold;");

        card.getChildren().addAll(iconLbl, titleLbl, subLbl);
        return card;
    }

    // =========================================================================
    // RIGHT COLUMN (ACTIONS & REDIRECTS)
    // =========================================================================
    private static VBox createRightColumn(Scene scene) {
        VBox col = new VBox(20);
        col.setPrefWidth(520);
        col.setMaxWidth(520);
        col.setAlignment(Pos.CENTER_LEFT);

        StackPane checkCircle = new StackPane();
        Circle outer = new Circle(28, Color.web("#dcfce7"));
        Label checkIcon = new Label("✓");
        checkIcon.setStyle("-fx-font-size: 22px; -fx-text-fill: #16a34a; -fx-font-weight: bold;");
        checkCircle.getChildren().addAll(outer, checkIcon);
        checkCircle.setAlignment(Pos.CENTER_LEFT);

        Text heading = new Text("Congratulations!");
        heading.setStyle("-fx-font-size: 34px; -fx-font-weight: bold; -fx-fill: #111827;");

        Text subHeading = new Text("Your application has been submitted successfully.");
        subHeading.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #374151;");
        subHeading.setWrappingWidth(500);

        Text description = new Text(
                "We've received your details and verification documents. Our onboarding and " +
                "compliance team is currently reviewing your profile.\n\n" +
                "This verification process typically takes between 24 to 48 hours. You will receive " +
                "full access to accept orders once approved."
        );
        description.setStyle("-fx-font-size: 14px; -fx-fill: #6b7280; -fx-line-spacing: 4px;");
        description.setWrappingWidth(500);

        HBox btnRow = new HBox(16);
        btnRow.setAlignment(Pos.CENTER_LEFT);
        btnRow.setPadding(new Insets(10, 0, 10, 0));

        Runnable navigateToLoginTask = new Runnable() {
            @Override
            public void run() {
                Deliverylogin.show(scene);
            }
        };

        Button btnGoToLogin = new Button("Go to Login  →");
        btnGoToLogin.setPrefHeight(46);
        btnGoToLogin.setStyle(
                "-fx-background-color: " + ORANGE_GRADIENT + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 0 24 0 24;" +
                "-fx-cursor: hand;"
        );
        btnGoToLogin.setOnAction(e -> navigateToLoginTask.run());

        btnRow.getChildren().add(btnGoToLogin);

        HBox notifyTip = new HBox(10);
        notifyTip.setAlignment(Pos.CENTER_LEFT);
        Label infoIcon = new Label("ⓘ");
        infoIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #f97316;");
        Label notifyText = new Label("We will notify you via email and SMS once your verification is complete.");
        notifyText.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");
        notifyTip.getChildren().addAll(infoIcon, notifyText);

        col.getChildren().addAll(
                checkCircle,
                heading,
                subHeading,
                description,
                btnRow,
                notifyTip
        );

        return col;
    }
}
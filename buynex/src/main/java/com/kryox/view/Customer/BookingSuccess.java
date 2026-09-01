package com.kryox.view.Customer;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.UUID;

public class BookingSuccess {

        private Scene Bookingscene;

        public Scene getBookingscene() {
                BorderPane root = new BorderPane();

                root.setStyle("-fx-background-color: " + BG + ";");

                root.setTop(createHeader());

                StackPane centerPane = new StackPane();

                centerPane.setAlignment(Pos.CENTER);

                centerPane.setPadding(new Insets(35));

                VBox successCard = createSuccessCard();

                centerPane.getChildren().add(successCard);
                root.setCenter(centerPane);

                Bookingscene = new Scene(root, 1550, 850);

                playAnimation(successCard);

                return Bookingscene;
        }

        private String productName = "Artisanal Sourdough Loaf";

        private String shopName = "BakeHouse Local";

        private String productImage = "/assects/images/products/bread.png";

        private String bookingId = "BK-" +
                        UUID.randomUUID()
                                        .toString()
                                        .substring(0, 4)
                                        .toUpperCase();

        private static final String ORANGE = "#FF6900";
        private static final String DARK_ORANGE = "#B94D00";
        private static final String BG = "#F7F6FA";
        private static final String TEXT = "#181818";
        private static final String MUTED = "#766C68";

        private HBox createHeader() {

                HBox header = new HBox();

                header.setPrefHeight(50);

                header.setPadding(
                                new Insets(
                                                0,
                                                38,
                                                0,
                                                38));

                header.setAlignment(
                                Pos.CENTER_LEFT);

                header.setStyle(
                                "-fx-background-color: #ebccb7" +
                                                "-fx-border-color: #E8E3E8;" +
                                                "-fx-border-width: 0 0 1 0;");

                // =====================================================
                // LOGO
                // =====================================================

                Label logo = new Label("BuyNeX");

                logo.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: 900;" +
                                                "-fx-text-fill: " + DARK_ORANGE + ";");

                // =====================================================
                // SPACER
                // =====================================================

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                // =====================================================
                // NOTIFICATION
                // =====================================================

                Button notification = new Button("♧");

                // =====================================================
                // CART
                // =====================================================

                Button cart = new Button("🛒");

                // =====================================================
                // PROFILE
                // =====================================================

                Button profile = new Button("●");

                String headerButtonStyle = "-fx-background-color: transparent;" +
                                "-fx-border-width: 0;" +
                                "-fx-text-fill: #555555;" +
                                "-fx-font-size: 13px;" +
                                "-fx-padding: 5;" +
                                "-fx-cursor: hand;";

                notification.setStyle(
                                headerButtonStyle);

                cart.setStyle(
                                headerButtonStyle);

                profile.setStyle(
                                headerButtonStyle);

                HBox actions = new HBox(
                                14,
                                notification,
                                cart,
                                profile);

                actions.setAlignment(
                                Pos.CENTER_RIGHT);

                header.getChildren().addAll(
                                logo,
                                spacer,
                                actions);

                return header;
        }

        // =========================================================
        // SUCCESS CARD
        // =========================================================

        private VBox createSuccessCard() {

                VBox card = new VBox(16);

                // IMPORTANT:
                // Bigger than previous 320px card

                card.setPrefWidth(390);
                card.setMaxWidth(390);

                card.setMinHeight(0);

                card.setPadding(
                                new Insets(
                                                24,
                                                26,
                                                22,
                                                26));

                card.setAlignment(
                                Pos.TOP_CENTER);

                card.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10;");

                // =====================================================
                // SHADOW
                // =====================================================

                javafx.scene.effect.DropShadow shadow = new javafx.scene.effect.DropShadow();

                shadow.setRadius(28);

                shadow.setOffsetY(10);

                shadow.setColor(
                                Color.rgb(
                                                0,
                                                0,
                                                0,
                                                0.12));

                card.setEffect(shadow);

                // =====================================================
                // SUCCESS ICON
                // =====================================================

                StackPane successIcon = createSuccessIcon();

                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label(
                                "Product Booked Successfully!");

                title.setStyle(
                                "-fx-font-family: 'Montserrat';" +
                                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: " + TEXT + ";");

                // =====================================================
                // SUBTITLE
                // =====================================================

                Label subtitle = new Label(
                                "Your reservation for order #" +
                                                bookingId +
                                                " has been confirmed.");

                subtitle.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: " + MUTED + ";");

                subtitle.setWrapText(true);

                subtitle.setAlignment(
                                Pos.CENTER);

                // =====================================================
                // PRODUCT CARD
                // =====================================================

                VBox productCard = createProductCard();

                // =====================================================
                // NEXT STEPS
                // =====================================================

                VBox nextSteps = createNextSteps();

                // =====================================================
                // BUTTONS
                // =====================================================

                Button bookingsButton = createPrimaryButton(
                                "View My Bookings");

                Button backButton = createSecondaryButton(
                                "Back to Home");

                // =====================================================
                // BUTTON ACTION
                // =====================================================

                bookingsButton.setOnAction(
                                e -> showBookingAlert());

                backButton.setOnAction(
                                e -> {

                                        Dashbord ds = new Dashbord(null);
                                        Homepage.HomepageStage.setScene(ds.getDashbordScene());
                                });

                // =====================================================
                // ADD TO CARD
                // =====================================================

                card.getChildren().addAll(
                                successIcon,
                                title,
                                subtitle,
                                productCard,
                                nextSteps,
                                bookingsButton,
                                backButton);

                return card;
        }

        // =========================================================
        // SUCCESS ICON
        // =========================================================

        private StackPane createSuccessIcon() {

                Circle outer = new Circle(
                                31,
                                Color.web("#FFF0E5"));

                Circle inner = new Circle(
                                20,
                                Color.web(ORANGE));

                Label check = new Label("✓");

                check.setStyle(
                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;");

                StackPane icon = new StackPane(
                                outer,
                                inner,
                                check);

                icon.setPrefSize(
                                62,
                                62);

                return icon;
        }

        // =========================================================
        // PRODUCT CARD
        // =========================================================

        private VBox createProductCard() {

                VBox box = new VBox(12);

                box.setPadding(
                                new Insets(14));

                box.setMaxWidth(
                                Double.MAX_VALUE);

                box.setStyle(
                                "-fx-background-color: #eee5df" +
                                                "-fx-background-radius: 7;" +
                                                "-fx-border-color: #ECE6ED;" +
                                                "-fx-border-radius: 7;");

                // =====================================================
                // PRODUCT ROW
                // =====================================================

                HBox productRow = new HBox(13);

                productRow.setAlignment(
                                Pos.CENTER_LEFT);

                // =====================================================
                // IMAGE
                // =====================================================

                StackPane imageBox = new StackPane();

                imageBox.setPrefSize(
                                65,
                                58);

                imageBox.setMinSize(
                                65,
                                58);

                imageBox.setMaxSize(
                                65,
                                58);

                imageBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 5;");

                try {

                        var imageUrl = getClass().getResource(productImage);

                        Image image = imageUrl == null
                                        ? null
                                        : new Image(
                                                        imageUrl.toExternalForm());

                        if (image != null && !image.isError()) {

                                ImageView imageView = new ImageView(image);

                                imageView.setFitWidth(58);

                                imageView.setFitHeight(52);

                                imageView.setPreserveRatio(
                                                true);

                                imageBox.getChildren().add(
                                                imageView);
                        }

                } catch (Exception e) {

                        Label noImage = new Label("IMAGE");

                        noImage.setStyle(
                                        "-fx-font-size: 8px;" +
                                                        "-fx-text-fill: #999999;");

                        imageBox.getChildren().add(
                                        noImage);
                }

                // =====================================================
                // PRODUCT INFO
                // =====================================================

                VBox info = new VBox(5);

                Label product = new Label(
                                productName);

                product.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #222222;");

                Label shop = new Label(
                                "▣  " + shopName);

                shop.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-text-fill: #6E625D;");

                info.getChildren().addAll(
                                product,
                                shop);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                productRow.getChildren().addAll(
                                imageBox,
                                info,
                                spacer);

                // =====================================================
                // STATUS ROW
                // =====================================================

                HBox statusRow = new HBox();

                statusRow.setAlignment(
                                Pos.CENTER_LEFT);

                Label status = new Label("Status");

                status.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-text-fill: #777777;");

                Region statusSpacer = new Region();

                HBox.setHgrow(
                                statusSpacer,
                                Priority.ALWAYS);

                Label valid = new Label(
                                "Valid for 24 hours");

                valid.setPadding(
                                new Insets(
                                                4,
                                                10,
                                                4,
                                                10));

                valid.setStyle(
                                "-fx-background-color: #FFE4D5;" +
                                                "-fx-background-radius: 12;" +
                                                "-fx-text-fill: " + ORANGE + ";" +
                                                "-fx-font-size: 8px;" +
                                                "-fx-font-weight: bold;");

                statusRow.getChildren().addAll(
                                status,
                                statusSpacer,
                                valid);

                box.getChildren().addAll(
                                productRow,
                                statusRow);

                return box;
        }

        // =========================================================
        // NEXT STEPS
        // =========================================================

        private VBox createNextSteps() {

                VBox box = new VBox(7);

                box.setMaxWidth(
                                Double.MAX_VALUE);

                Label title = new Label(
                                "⚡  Next Steps");

                title.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-text-fill: #222222;");

                Label text = new Label(
                                "Visit the shop within the next 24 hours and show your booking\n" +
                                                "ID #" +
                                                bookingId +
                                                " at the counter to complete your purchase and\n" +
                                                "pick up your item.");

                text.setWrapText(true);

                text.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-line-spacing: 3;" +
                                                "-fx-text-fill: " + MUTED + ";");

                box.getChildren().addAll(
                                title,
                                text);

                return box;
        }

        // =========================================================
        // PRIMARY BUTTON
        // =========================================================

        private Button createPrimaryButton(
                        String text) {

                Button button = new Button(text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(42);

                button.setStyle(
                                "-fx-background-color: " +
                                                ORANGE + ";" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                button.setOnMouseEntered(
                                e -> button.setStyle(
                                                "-fx-background-color: " +
                                                                DARK_ORANGE + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 6;" +
                                                                "-fx-cursor: hand;"));

                button.setOnMouseExited(
                                e -> button.setStyle(
                                                "-fx-background-color: " +
                                                                ORANGE + ";" +
                                                                "-fx-text-fill: white;" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-background-radius: 6;" +
                                                                "-fx-cursor: hand;"));

                return button;
        }

        // =========================================================
        // SECONDARY BUTTON
        // =========================================================

        private Button createSecondaryButton(
                        String text) {

                Button button = new Button(text);

                button.setMaxWidth(
                                Double.MAX_VALUE);

                button.setPrefHeight(40);

                button.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-text-fill: " +
                                                DARK_ORANGE + ";" +
                                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-border-color: " +
                                                DARK_ORANGE + ";" +
                                                "-fx-border-width: 1;" +
                                                "-fx-border-radius: 6;" +
                                                "-fx-background-radius: 6;" +
                                                "-fx-cursor: hand;");

                button.setOnMouseEntered(
                                e -> button.setStyle(
                                                "-fx-background-color: #FFF3EC;" +
                                                                "-fx-text-fill: " +
                                                                DARK_ORANGE + ";" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-border-color: " +
                                                                DARK_ORANGE + ";" +
                                                                "-fx-border-width: 1;" +
                                                                "-fx-border-radius: 6;" +
                                                                "-fx-background-radius: 6;" +
                                                                "-fx-cursor: hand;"));

                button.setOnMouseExited(
                                e -> button.setStyle(
                                                "-fx-background-color: white;" +
                                                                "-fx-text-fill: " +
                                                                DARK_ORANGE + ";" +
                                                                "-fx-font-size: 11px;" +
                                                                "-fx-font-weight: bold;" +
                                                                "-fx-border-color: " +
                                                                DARK_ORANGE + ";" +
                                                                "-fx-border-width: 1;" +
                                                                "-fx-border-radius: 6;" +
                                                                "-fx-background-radius: 6;" +
                                                                "-fx-cursor: hand;"));

                return button;
        }

        // =========================================================
        // ANIMATION
        // =========================================================

        private void playAnimation(
                        VBox card) {

                // =====================================================
                // INITIAL POSITION
                // =====================================================

                card.setOpacity(0);

                card.setTranslateY(45);

                card.setScaleX(0.92);

                card.setScaleY(0.92);

                // =====================================================
                // FADE
                // =====================================================

                FadeTransition fade = new FadeTransition(
                                Duration.millis(700),
                                card);

                fade.setFromValue(0);

                fade.setToValue(1);

                // =====================================================
                // SLIDE
                // =====================================================

                TranslateTransition slide = new TranslateTransition(
                                Duration.millis(700),
                                card);

                slide.setFromY(45);

                slide.setToY(0);

                // =====================================================
                // SCALE
                // =====================================================

                ScaleTransition scale = new ScaleTransition(
                                Duration.millis(700),
                                card);

                scale.setFromX(0.92);

                scale.setFromY(0.92);

                scale.setToX(1);

                scale.setToY(1);

                // =====================================================
                // COMBINE
                // =====================================================

                ParallelTransition animation = new ParallelTransition(
                                fade,
                                slide,
                                scale);

                animation.play();

                // =====================================================
                // SUCCESS ICON ANIMATION
                // =====================================================

                if (!card.getChildren().isEmpty()) {

                        StackPane icon = (StackPane) card.getChildren()
                                        .get(0);

                        icon.setScaleX(0.4);

                        icon.setScaleY(0.4);

                        ScaleTransition iconAnimation = new ScaleTransition(
                                        Duration.millis(500),
                                        icon);

                        iconAnimation.setDelay(
                                        Duration.millis(450));

                        iconAnimation.setFromX(0.4);

                        iconAnimation.setFromY(0.4);

                        iconAnimation.setToX(1);

                        iconAnimation.setToY(1);

                        iconAnimation.play();
                }
        }

        // =========================================================
        // BOOKING ALERT
        // =========================================================

        private void showBookingAlert() {

                Alert alert = new Alert(
                                Alert.AlertType.INFORMATION);

                alert.setTitle(
                                "My Bookings");

                alert.setHeaderText(
                                "Booking Confirmed");

                alert.setContentText(
                                "Booking ID : #" +
                                                bookingId +
                                                "\n\n" +
                                                "Product : " +
                                                productName +
                                                "\n" +
                                                "Shop : " +
                                                shopName +
                                                "\n\n" +
                                                "Status : Confirmed");

                alert.showAndWait();
        }

}
package com.kryox.view.Customer;

import com.kryox.controller.Customer.PaymentController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import java.util.List;
import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.model.Customer.Productcart;

public class ShoppingCartUI {

        private VBox products;

        private Scene addcartScene;
        private String userId;

        public ShoppingCartUI(String userId) {
                this.userId = userId;
        }

        public Scene getaddcartScene(){
                 // =====================================================
        // SHADOWS
        // =====================================================

        DropShadow shadow =
                new DropShadow();

        shadow.setRadius(18);
        shadow.setSpread(0.05);
        shadow.setOffsetX(5);
        shadow.setOffsetY(0);
        shadow.setColor(
                Color.rgb(0, 0, 0, 0.14)
        );

        DropShadow cardShadow =
                new DropShadow();

        cardShadow.setRadius(14);
        cardShadow.setOffsetY(5);
        cardShadow.setSpread(0.02);
        cardShadow.setColor(
                Color.rgb(0, 0, 0, 0.10)
        );

        // =====================================================
        // LOGO
        // =====================================================

        Label name =
                new Label("BuyNeX");

        name.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: 900;" +
                "-fx-text-fill: #E87500;"
        );

        // =====================================================
        // PREMIUM SHOPPER
        // =====================================================

        HBox premiumBox =
                new HBox(9);

        premiumBox.setPrefSize(
                205,
                58
        );

        premiumBox.setMinSize(
                205,
                58
        );

        premiumBox.setMaxSize(
                205,
                58
        );

        premiumBox.setAlignment(
                Pos.CENTER_LEFT
        );

        premiumBox.setPadding(
                new Insets(8, 13, 8, 13)
        );

        premiumBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #E9E2EA;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 15;"
        );

        premiumBox.setEffect(cardShadow);

        VBox textBox =
                new VBox(3);

        Label premiumTitle =
                new Label("Premium Shopper");

        premiumTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label premiumSubtitle =
                new Label("● AI Assistant Active");

        premiumSubtitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #FF6900;"
        );

        textBox.getChildren().addAll(
                premiumTitle,
                premiumSubtitle
        );

        premiumBox.getChildren().add(
                textBox
        );

        // =====================================================
        // SIDEBAR BUTTONS
        // =====================================================

        Button dashboard =
                new Button("Dashboard");

        dashboard.setOnAction(event->{
                Dashbord ds=new Dashbord(null);
                Homepage.HomepageStage.setScene(ds.getDashbordScene());
        });

        Button nearby =
                new Button("Nearby Shops");
                neaby_shope ns=new neaby_shope(userId);
                Homepage.HomepageStage.setScene(ns.getNearby_shopes(null));

        Button deals =
                new Button("Deals");
        deals.setOnAction(event->{
                DealsDB db=new DealsDB(userId);
                Homepage.HomepageStage.setScene(db.getDealScene(null));
        });

        Button orders =
                new Button("My Orders");
        orders.setOnAction(event->{
                My_orderAllorder my=new My_orderAllorder(userId);
                Homepage.HomepageStage.setScene(my.getAllorderScene());
        });

        Button analytics =
                new Button("Analytics");

        Button settings =
                new Button("Settings");

        Button help =
                new Button("Help & Support");

        Button[] sidebarButtons = {
                dashboard,
                nearby,
                deals,
                orders,
                analytics
        };

        for (Button button : sidebarButtons) {

            button.setPrefWidth(205);
            button.setPrefHeight(42);

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: #333333;" +
                    "-fx-font-size: 12px;" +
                    "-fx-font-family: 'Montserrat';" +
                    "-fx-font-weight: 500;" +
                    "-fx-background-radius: 12;" +
                    "-fx-alignment: CENTER_LEFT;" +
                    "-fx-padding: 0 0 0 20;" +
                    "-fx-cursor: hand;"
            );

            button.setOnMouseEntered(e ->
                    button.setStyle(
                            "-fx-background-color: #FF6900;" +
                            "-fx-text-fill: white;" +
                            "-fx-font-size: 12px;" +
                            "-fx-font-family: 'Montserrat';" +
                            "-fx-font-weight: bold;" +
                            "-fx-background-radius: 12;" +
                            "-fx-alignment: CENTER_LEFT;" +
                            "-fx-padding: 0 0 0 20;" +
                            "-fx-cursor: hand;"
                    )
            );

            button.setOnMouseExited(e ->
                    button.setStyle(
                            "-fx-background-color: transparent;" +
                            "-fx-text-fill: #333333;" +
                            "-fx-font-size: 12px;" +
                            "-fx-font-family: 'Montserrat';" +
                            "-fx-font-weight: 500;" +
                            "-fx-background-radius: 12;" +
                            "-fx-alignment: CENTER_LEFT;" +
                            "-fx-padding: 0 0 0 20;" +
                            "-fx-cursor: hand;"
                    )
            );
        }

        settings.setPrefHeight(34);
        help.setPrefHeight(34);

        // =====================================================
        // UPGRADE CARD
        // =====================================================

        VBox upgradeCard =
                new VBox(7);

        upgradeCard.setPrefWidth(205);
        upgradeCard.setPrefHeight(112);
        upgradeCard.setPadding(
                new Insets(15)
        );

        upgradeCard.setAlignment(
                Pos.CENTER_LEFT
        );

        LinearGradient upgradeGradient =
                new LinearGradient(
                        0,
                        0,
                        1,
                        1,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.web("#25262A")
                        ),
                        new Stop(
                                1,
                                Color.web("#45474D")
                        )
                );

        upgradeCard.setBackground(
                new Background(
                        new BackgroundFill(
                                upgradeGradient,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        Label upgradeTitle =
                new Label("Unlock Gold");

        upgradeTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label upgradeText =
                new Label(
                        "Smarter deals & exclusive rewards"
                );

        upgradeText.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #BEBFC3;"
        );

        Button upgradeGold =
                new Button("Upgrade to Gold");

        upgradeGold.setPrefWidth(175);
        upgradeGold.setPrefHeight(30);

        upgradeGold.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10;" +
                "-fx-cursor: hand;"
        );

        upgradeCard.getChildren().addAll(
                upgradeTitle,
                upgradeText,
                upgradeGold
        );

        // =====================================================
        // LEFT BOX
        // =====================================================

        VBox leftBox =
                new VBox(14);

        leftBox.setPrefWidth(245);
        leftBox.setMinWidth(245);
        leftBox.setMaxWidth(245);

        leftBox.setPrefHeight(800);

        leftBox.setAlignment(
                Pos.TOP_CENTER
        );

        leftBox.setPadding(
                new Insets(27, 20, 20, 20)
        );

        leftBox.setStyle(
                "-fx-background-color: #ebccb7"
        );

        leftBox.setEffect(shadow);

        Region sidebarSpacer =
                new Region();

        VBox.setVgrow(
                sidebarSpacer,
                Priority.ALWAYS
        );

        leftBox.getChildren().addAll(
                name,
                premiumBox,
                dashboard,
                nearby,
                deals,
                orders,
                analytics,
                sidebarSpacer,
                upgradeCard,
                settings,
                help
        );

        // =====================================================
        // TOP NAVIGATION
        // =====================================================

        Button t1 =
                new Button("Offers");

        Button t2 =
                new Button("Shops");

        Button t3 =
                new Button("Support");

        String topButtonStyle =
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 8 6 8;" +
                "-fx-border-width: 0;" +
                "-fx-cursor: hand;";

        t1.setStyle(topButtonStyle);
        t2.setStyle(topButtonStyle);
        t3.setStyle(topButtonStyle);

        HBox topLinks =
                new HBox(
                        6,
                        t1,
                        t2,
                        t3
                );

        topLinks.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // SEARCH
        // =====================================================

        TextField searchBox =
                new TextField();

        searchBox.setPromptText(
                "Search products, shops or deals with AI..."
        );

        searchBox.setPrefWidth(310);
        searchBox.setPrefHeight(39);

        searchBox.setStyle(
                "-fx-background-color: #F8F7FA;" +
                "-fx-background-radius: 20;" +
                "-fx-border-color: #E5E1E8;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 1;" +
                "-fx-padding: 0 16 0 16;" +
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #444444;" +
                "-fx-prompt-text-fill: #999999;"
        );

        // =====================================================
        // LOCATION
        // =====================================================

        Label locationIcon =
                new Label("📍");

        Label locationText =
                new Label("Downtown Manhattan⌄");

        locationText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #555555;"
        );

        HBox locationBox =
                new HBox(
                        4,
                        locationIcon,
                        locationText
                );

        locationBox.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        Button notification =
                new Button("🔔");

        Button cart =
                new Button("🛒");

        Button profile =
                new Button("●");

        String actionStyle =
                "-fx-background-color: white;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: #E7E2E9;" +
                "-fx-border-radius: 11;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;";

        notification.setPrefSize(37, 37);
        cart.setPrefSize(37, 37);
        profile.setPrefSize(37, 37);

        notification.setStyle(actionStyle);
        cart.setStyle(actionStyle);
        profile.setStyle(actionStyle);

        HBox actionBox =
                new HBox(
                        7,
                        notification,
                        cart,
                        profile
                );

        actionBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        // =====================================================
        // NAVIGATION SPACERS
        // =====================================================

        Region navSpacer1 =
                new Region();

        Region navSpacer2 =
                new Region();

        HBox.setHgrow(
                navSpacer1,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                navSpacer2,
                Priority.ALWAYS
        );

        // =====================================================
        // NAV BOX
        // =====================================================

        HBox navBox =
                new HBox(
                        12,
                        topLinks,
                        navSpacer1,
                        searchBox,
                        navSpacer2,
                        locationBox,
                        actionBox
                );

        navBox.setPrefHeight(68);

        navBox.setPadding(
                new Insets(
                        12,
                        24,
                        12,
                        24
                )
        );

        navBox.setAlignment(
                Pos.CENTER_LEFT
        );

        navBox.setStyle(
                "-fx-background-color: #ebccb7;" +
                "-fx-border-width: 0 0 1 0;"
        );

        // =====================================================
        // CART PAGE
        // =====================================================

        VBox cartPage =
                createCartPage();

        ScrollPane scrollPane =
                new ScrollPane(cartPage);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        // =====================================================
        // RIGHT CONTENT
        // =====================================================

        VBox rightBox =
                new VBox(
                        0,
                        navBox,
                        scrollPane
                );

        rightBox.setStyle(
                "-fx-background-color: #F8F6FA;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        // =====================================================
        // ORANGE GLOW
        // =====================================================

        RadialGradient orangeGlow =
                new RadialGradient(
                        0,
                        0,
                        0.84,
                        0.16,
                        0.42,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0.0,
                                Color.web("#FF9148", 0.18)
                        ),
                        new Stop(
                                0.40,
                                Color.web("#FFD1B4", 0.08)
                        ),
                        new Stop(
                                1.0,
                                Color.TRANSPARENT
                        )
                );

        rightBox.setBackground(
                new Background(
                        new BackgroundFill(
                                orangeGlow,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // BORDER PANE
        // =====================================================

        BorderPane mainBox =
                new BorderPane();

        mainBox.setLeft(
                leftBox
        );

        // CART PAGE IS IN CENTER
        mainBox.setCenter(
                rightBox
        );

        mainBox.setStyle(
                "-fx-background-color: #F8F6FA;"
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        mainBox,
                        1530,
                        850
                );
                addcartScene=scene;

                return addcartScene;
        }

    // =========================================================
    // PRODUCT IMAGE HELPER
    // =========================================================

    private StackPane createProductImage(String imagePath) {

        StackPane imageBox = new StackPane();

        imageBox.setPrefSize(80, 70);
        imageBox.setMinSize(80, 70);
        imageBox.setMaxSize(80, 70);

        imageBox.setStyle(
                "-fx-background-color: #F3F3F3;" +
                "-fx-background-radius: 6;"
        );

        try {

            Image image = new Image(
                    getClass().getResourceAsStream(imagePath)
            );

            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(70);
            imageView.setFitHeight(65);
            imageView.setPreserveRatio(true);

            imageBox.getChildren().add(imageView);

        } catch (Exception e) {

            Label noImage = new Label("Product");
            noImage.setStyle(
                    "-fx-font-size: 10px;" +
                    "-fx-text-fill: #999999;"
            );

            imageBox.getChildren().add(noImage);
        }

        return imageBox;
    }

    // =========================================================
    // QUANTITY BUTTON
    // =========================================================

    private HBox createQuantityBox(
            String quantity,
            double unitPrice,
            Label priceLabel,
            Label eachLabel,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel,
            int[] itemCount,
            double[] subtotal,
            VBox card,
            VBox products
    ) {

        Button minus = new Button("−");
        Button plus = new Button("+");

        Label qty = new Label(quantity);

        String buttonStyle =
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 0 7 0 7;" +
                "-fx-cursor: hand;";

        minus.setStyle(buttonStyle);
        plus.setStyle(buttonStyle);

        qty.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        HBox quantityBox = new HBox(8, minus, qty, plus);

        quantityBox.setAlignment(Pos.CENTER);
        quantityBox.setPrefHeight(32);
        quantityBox.setPrefWidth(82);

        quantityBox.setStyle(
                "-fx-background-color: #F1EDF3;" +
                "-fx-background-radius: 18;"
        );

        minus.setOnAction(e -> {

            int value = Integer.parseInt(qty.getText());

            // =====================================================
            // IF QUANTITY IS 1, REMOVE THE COMPLETE PRODUCT
            // =====================================================

            if (value == 1) {

                card.setVisible(false);
                card.setManaged(false);

                subtotal[0] -= unitPrice;

                if (subtotal[0] < 0) {
                    subtotal[0] = 0;
                }

                itemCount[0]--;

                if (itemCount[0] < 0) {
                    itemCount[0] = 0;
                }

                subtotalLabel.setText(
                        String.format(
                                "Subtotal (%d items)",
                                itemCount[0]
                        )
                );

                updateSummaryAmounts(
                        subtotal,
                        subtotalAmountLabel,
                        totalAmountLabel
                );

                // Show empty-cart message if last product was removed.
                if (itemCount[0] == 0) {

                    Label emptyLabel =
                            new Label("Your cart is empty.");

                    emptyLabel.setStyle(
                            "-fx-font-size: 14px;" +
                            "-fx-text-fill: #777777;" +
                            "-fx-font-weight: bold;"
                    );

                    products.getChildren().clear();
                    products.getChildren().add(emptyLabel);
                }

                return;
            }

            // =====================================================
            // IF QUANTITY IS GREATER THAN 1, DECREASE IT
            // =====================================================

            value--;

            qty.setText(String.valueOf(value));

            priceLabel.setText(
                    String.format(
                            "₹%.2f",
                            unitPrice * value
                    )
            );

            eachLabel.setText(
                    String.format(
                            "₹%.2f / ea",
                            unitPrice
                    )
            );

            subtotal[0] -= unitPrice;

            if (subtotal[0] < 0) {
                subtotal[0] = 0;
            }

            itemCount[0]--;

            if (itemCount[0] < 0) {
                itemCount[0] = 0;
            }

            subtotalLabel.setText(
                    String.format(
                            "Subtotal (%d items)",
                            itemCount[0]
                    )
            );

            updateSummaryAmounts(
                    subtotal,
                    subtotalAmountLabel,
                    totalAmountLabel
            );
        });

        plus.setOnAction(e -> {

            int value = Integer.parseInt(qty.getText());

            value++;

            qty.setText(String.valueOf(value));
            priceLabel.setText(String.format("₹%.2f", unitPrice * value));
            eachLabel.setText(String.format("₹%.2f / ea", unitPrice));

            subtotal[0] += unitPrice;
            itemCount[0]++;

            subtotalLabel.setText(
                    String.format("Subtotal (%d items)", itemCount[0])
            );

            updateSummaryAmounts(
                    subtotal,
                    subtotalAmountLabel,
                    totalAmountLabel
            );
        });

        return quantityBox;
    }

    // =========================================================
    // CART PRODUCT CARD
    // =========================================================

    private VBox createCartProduct(
            String productName,
            String shopName,
            double unitPrice,
            String oldPrice,
            String quantity,
            String imagePath,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel,
            int[] itemCount,
            double[] subtotal,
            VBox products
    ) {

        VBox card = new VBox(8);

        card.setPadding(new Insets(18));

        card.setPrefWidth(700);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 7;" +
                "-fx-border-color: #E8E3E8;" +
                "-fx-border-radius: 7;"
        );

        DropShadow cardShadow = new DropShadow();

        cardShadow.setRadius(10);
        cardShadow.setOffsetY(3);
        cardShadow.setColor(Color.rgb(0, 0, 0, 0.07));

        card.setEffect(cardShadow);

        // -----------------------------------------------------
        // PRODUCT TOP
        // -----------------------------------------------------

        HBox productTop = new HBox(17);

        productTop.setAlignment(Pos.CENTER_LEFT);

        StackPane productImage =
                createProductImage(imagePath);

        VBox productInfo = new VBox(4);

        Label productTitle =
                new Label(productName);

        productTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label shop =
                new Label("From " + shopName);

        shop.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #B44E00;" +
                "-fx-font-weight: bold;"
        );

        productInfo.getChildren().addAll(
                productTitle,
                shop
        );

        Region productSpacer = new Region();

        HBox.setHgrow(
                productSpacer,
                Priority.ALWAYS
        );

        Button deleteButton =
                new Button("▣");

        deleteButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #A74716;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 2;" +
                "-fx-cursor: hand;"
        );

        deleteButton.setOnAction(e -> {

            int currentQuantity =
                    Integer.parseInt(quantity);

            card.setVisible(false);
            card.setManaged(false);

            subtotal[0] -= unitPrice * currentQuantity;

            if (subtotal[0] < 0) {
                subtotal[0] = 0;
            }

            itemCount[0] -= currentQuantity;

            if (itemCount[0] < 0) {
                itemCount[0] = 0;
            }

            subtotalLabel.setText(
                    String.format(
                            "Subtotal (%d items)",
                            itemCount[0]
                    )
            );

            updateSummaryAmounts(
                    subtotal,
                    subtotalAmountLabel,
                    totalAmountLabel
            );

            if (itemCount[0] == 0) {

                Label emptyLabel =
                        new Label("Your cart is empty.");

                emptyLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #777777;" +
                        "-fx-font-weight: bold;"
                );

                products.getChildren().clear();
                products.getChildren().add(emptyLabel);
            }
        });

        productTop.getChildren().addAll(
                productImage,
                productInfo,
                productSpacer,
                deleteButton
        );

        // -----------------------------------------------------
        // BOTTOM ROW
        // -----------------------------------------------------

        HBox bottomRow = new HBox(15);

        bottomRow.setAlignment(Pos.CENTER_LEFT);

        Label priceLabel =
                new Label(String.format("₹%.2f", unitPrice));

        priceLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #151515;"
        );

        Label each =
                new Label(String.format("₹%.2f / ea", unitPrice));

        each.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        HBox quantity1 =
                createQuantityBox(
                        quantity,
                        unitPrice,
                        priceLabel,
                        each,
                        subtotalLabel,
                        subtotalAmountLabel,
                        totalAmountLabel,
                        itemCount,
                        subtotal,
                        card,
                        products
                );

        Region bottomSpacer =
                new Region();

        HBox.setHgrow(
                bottomSpacer,
                Priority.ALWAYS
        );

        VBox priceBox = new VBox(1);

        priceBox.setAlignment(Pos.CENTER_RIGHT);

        priceBox.getChildren().addAll(
                priceLabel,
                each
        );

        bottomRow.getChildren().addAll(
                quantity1,
                bottomSpacer,
                priceBox
        );

        Separator separator =
                new Separator();

        separator.setStyle(
                "-fx-background-color: #ECE8EC;"
        );

        // -----------------------------------------------------
        // ACTION BUTTONS
        // -----------------------------------------------------

        HBox actions = new HBox(9);

        actions.setAlignment(Pos.CENTER_LEFT);

        Button buyNow =
                new Button("Buy Now");

        buyNow.setStyle(
                "-fx-background-color: #B94D00;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );

        Button book =
                new Button("Book Product");

        book.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );
        book.setOnAction(event->{
                BookingSuccess bs=new BookingSuccess();
                Homepage.HomepageStage.setScene(bs.getBookingscene());
        });

        Button visit =
                new Button("Visit Shop");

        visit.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #333333;" +
                "-fx-font-size: 12px;" +
                "-fx-border-color: #C7A99A;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );

        actions.getChildren().addAll(
                buyNow,
                book,
                visit
        );

        card.getChildren().addAll(
                productTop,
                bottomRow,
                separator,
                actions
        );

        return card;
    }

    private void updateSummaryAmounts(
            double[] subtotal,
            Label subtotalAmountLabel,
            Label totalAmountLabel
    ) {

        double subtotalAmount = subtotal[0];

        double platformFee = 10.0;
        double tax = subtotalAmount * 0.05;
        double deliveryFee = subtotalAmount >= 500 ? 0 : 30.0;

        double total =
                subtotalAmount
                + platformFee
                + tax
                + deliveryFee;

        subtotalAmountLabel.setText(
                String.format("₹%.2f", subtotalAmount)
        );

        totalAmountLabel.setText(
                String.format("₹%.2f", total)
        );
    }

    // =========================================================
    // ORDER SUMMARY
    // =========================================================

    private VBox createOrderSummary(
            double subtotalAmount,
            int itemCount,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel
    ) {

        double platformFee = 10.0;
        double tax = subtotalAmount * 0.05;
        double deliveryFee = subtotalAmount >= 500 ? 0 : 30.0;
        double totalAmount =
                subtotalAmount + platformFee + tax + deliveryFee;

        VBox summary =
                new VBox(13);

        summary.setPadding(
                new Insets(22, 18, 18, 18)
        );

        summary.setPrefWidth(300);
        summary.setMinWidth(300);

        summary.setStyle(
                "-fx-background-color: #eee5df;" +
                "-fx-background-radius: 7;" +
                "-fx-border-color: #E8E3E8;" +
                "-fx-border-radius: 7;"
        );

        DropShadow shadow =
                new DropShadow();

        shadow.setRadius(10);
        shadow.setOffsetY(3);
        shadow.setColor(
                Color.rgb(0, 0, 0, 0.06)
        );

        summary.setEffect(shadow);

        // -----------------------------------------------------
        // TITLE
        // -----------------------------------------------------

        Label title =
                new Label("Order Summary");

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        // -----------------------------------------------------
        // SUBTOTAL
        // -----------------------------------------------------

        subtotalLabel.setText(
                String.format("Subtotal (%d items)", itemCount)
        );

        HBox subtotal =
                summaryRowWithLabel(
                        subtotalLabel,
                        subtotalAmountLabel
                );

        HBox platform =
                summaryRow(
                        "Platform Fee",
                        String.format("₹%.2f", platformFee)
                );

        HBox tax1 =
                summaryRow(
                        "Estimated Tax",
                        String.format("₹%.2f", tax)
                );

        HBox delivery =
                summaryRow(
                        "Delivery Fee",
                        deliveryFee == 0
                                ? "FREE"
                                : String.format("₹%.2f", deliveryFee)
                );

        // -----------------------------------------------------
        // FREE DELIVERY
        // -----------------------------------------------------

        Label progressText =
                new Label("Progress to Free Delivery");

        progressText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        HBox freeText =
                new HBox();

        Label more =
                new Label(
                        subtotalAmount >= 500
                                ? "Free Delivery Unlocked"
                                : String.format(
                                        "₹%.2f more",
                                        500 - subtotalAmount
                                )
                );

        more.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #B44D00;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        freeText.getChildren().addAll(
                spacer,
                more
        );

        Region progress =
                new Region();

        progress.setPrefHeight(6);

        progress.setStyle(
                "-fx-background-color: #B94D00;" +
                "-fx-background-radius: 10;"
        );

        // -----------------------------------------------------
        // TOTAL
        // -----------------------------------------------------

        Separator separator =
                new Separator();

        HBox totalRow =
                new HBox();

        totalRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label total =
                new Label("Total");

        total.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Region totalSpacer =
                new Region();

        HBox.setHgrow(
                totalSpacer,
                Priority.ALWAYS
        );

        Label totalValue =
                new Label(String.format("₹%.2f", totalAmount));

        totalValue.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        totalRow.getChildren().addAll(
                total,
                totalSpacer,
                totalValue
        );

        // -----------------------------------------------------
        // CHECKOUT
        // -----------------------------------------------------

        Button checkout =
                new Button("Proceed to Checkout  →");

        checkout.setMaxWidth(
                Double.MAX_VALUE
        );

        checkout.setPrefHeight(39);

        checkout.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #FFA276);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        checkout.setOnAction(event -> {

                PaymentController paymentController =
                        new PaymentController();

                paymentController.startPayment(
                        totalAmount,
                        () -> handlePaymentSuccess()
                );
        });

        // -----------------------------------------------------
        // AI TIP
        // -----------------------------------------------------

        VBox aiTip =
                new VBox(5);

        aiTip.setPadding(
                new Insets(12)
        );

        aiTip.setStyle(
                "-fx-background-color: #FFF5F0;" +
                "-fx-border-color: #F1D8CA;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        Label aiTitle =
                new Label("💡  AI Delivery Tip");

        aiTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label aiText =
                new Label(
                        "Add more to your cart to unlock Free\n" +
                        "Delivery! Try adding the suggested Farm\n" +
                        "Fresh Milk." 
                );

        aiText.setWrapText(true);

        aiText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        aiTip.getChildren().addAll(
                aiTitle,
                aiText
        );

        summary.getChildren().addAll(
                title,
                subtotal,
                platform,
                tax1,
                delivery,
                progressText,
                freeText,
                progress,
                separator,
                totalRow,
                checkout,
                aiTip
        );

        return summary;
    }

    // =========================================================
    // SUMMARY ROW
    // =========================================================

    private HBox summaryRow(
            String left,
            String right
    ) {

        HBox row =
                new HBox();

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        Label leftLabel =
                new Label(left);

        leftLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label rightLabel =
                new Label(right);

        rightLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        row.getChildren().addAll(
                leftLabel,
                spacer,
                rightLabel
        );

        return row;
    }

    private HBox summaryRowWithLabel(
            Label leftLabel,
            Label rightLabel
    ) {

        HBox row = new HBox();

        row.setAlignment(Pos.CENTER_LEFT);

        leftLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        rightLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        row.getChildren().addAll(
                leftLabel,
                spacer,
                rightLabel
        );

        return row;
    }

    // =========================================================
    // FREQUENTLY BOUGHT TOGETHER
    // =========================================================

    private HBox createSuggestion(
            String name,
            String price,
            String imagePath
    ) {

        HBox box =
                new HBox(8);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        box.setPadding(
                new Insets(10)
        );

        box.setPrefSize(
                195,
                74
        );

        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );

        StackPane image =
                createProductImage(imagePath);

        image.setPrefSize(48, 48);
        image.setMinSize(48, 48);
        image.setMaxSize(48, 48);

        VBox info =
                new VBox(2);

        Label product =
                new Label(name);

        product.setMaxWidth(95);

        product.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333333;"
        );

        Label productPrice =
                new Label(price);

        productPrice.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        info.getChildren().addAll(
                product,
                productPrice
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button add =
                new Button("+");

        add.setPrefSize(
                27,
                27
        );

        add.setStyle(
                "-fx-background-color: #F4F0F4;" +
                "-fx-text-fill: #B94D00;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 50;" +
                "-fx-cursor: hand;"
        );

        box.getChildren().addAll(
                image,
                info,
                spacer,
                add
        );

        return box;
    }

    // =========================================================
    // SHOPPING CART PAGE
    // =========================================================

    private VBox createCartPage() {

        VBox page =
                new VBox(16);

        page.setPadding(
                new Insets(25, 30, 25, 30)
        );

        page.setStyle(
                "-fx-background-color: #F8F6FA;"
        );

        // -----------------------------------------------------
        // HEADER
        // -----------------------------------------------------

        VBox heading =
                new VBox(4);

        Label title =
                new Label("Your Shopping Cart");

        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #151515;"
        );

        Label subtitle =
                new Label("3 items from local sellers");

        subtitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #A44D20;"
        );

        heading.getChildren().addAll(
                title,
                subtitle
        );

        // -----------------------------------------------------
        // CART + SUMMARY
        // -----------------------------------------------------

        products =
                new VBox(12);

        products.setPrefWidth(700);

        // Dynamic cart totals
        double[] subtotal = {0.0};
        int[] itemCount = {0};

        Label subtotalLabel =
                new Label("Subtotal (0 items)");

        Label subtotalAmountLabel =
                new Label("₹0.00");

        Label totalAmountLabel =
                new Label("₹0.00");

        // -----------------------------------------------------
        // FETCH CART PRODUCTS FROM FIRESTORE
        // -----------------------------------------------------

        if (userId == null || userId.isBlank()) {

                Label errorLabel =
                        new Label("User not logged in.");

                errorLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #B44D00;" +
                        "-fx-font-weight: bold;"
                );

                products.getChildren().add(errorLabel);

        } else {

                CARTcontroller cartController =
                        new CARTcontroller();

                List<Productcart> cartList =
                        cartController.getCart(userId);

                if (cartList.isEmpty()) {

                        Label emptyLabel =
                                new Label("Your cart is empty.");

                        emptyLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-text-fill: #777777;" +
                                "-fx-font-weight: bold;"
                        );

                        products.getChildren().add(emptyLabel);

                } else {

                        for (Productcart product : cartList) {

                                String productName =
                                        product.getName();

                                String shopName =
                                        product.getName1();

                                double unitPrice =
                                        product.getPrice();

                                subtotal[0] += unitPrice;
                                itemCount[0]++;

                                VBox productCard =
                                        createCartProduct(
                                                productName,
                                                shopName,
                                                unitPrice,
                                                "",
                                                "1",
                                                "/assects/images/products/avocado.png",
                                                subtotalLabel,
                                                subtotalAmountLabel,
                                                totalAmountLabel,
                                                itemCount,
                                                subtotal,
                                                products
                                        );

                                products.getChildren().add(productCard);
                        }
                }
        }

        VBox summary =
                createOrderSummary(
                        subtotal[0],
                        itemCount[0],
                        subtotalLabel,
                        subtotalAmountLabel,
                        totalAmountLabel
                );

        HBox cartContent =
                new HBox(18);

        cartContent.setAlignment(
                Pos.TOP_LEFT
        );

        cartContent.getChildren().addAll(
                products,
                summary
        );

        // -----------------------------------------------------
        // FREQUENTLY BOUGHT
        // -----------------------------------------------------

        VBox frequently =
                new VBox(10);

        Label frequentlyTitle =
                new Label("✦  Frequently Bought Together");

        frequentlyTitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        HBox suggestions =
                new HBox(12);

        suggestions.getChildren().addAll(

                createSuggestion(
                        "Farm Fresh...",
                        "$4.20",
                        "/assects/images/products/milk.png"
                ),

                createSuggestion(
                        "Artisanal...",
                        "$6.50",
                        "/assects/images/products/bread.png"
                )
        );

        frequently.getChildren().addAll(
                frequentlyTitle,
                suggestions
        );

        page.getChildren().addAll(
                heading,
                cartContent,
                frequently
        );

        VBox.setVgrow(
                cartContent,
                Priority.ALWAYS
        );

        return page;
    }

    




    // =========================================================
    // CLEAR CART
    // =========================================================

    private void clearCart() {

        if (products != null) {
            products.getChildren().clear();
        }

        System.out.println("Cart cleared successfully.");
    }

    // =========================================================
    // PAYMENT SUCCESS
    // =========================================================

    private void handlePaymentSuccess() {

        System.out.println("================================");
        System.out.println("ORDER PROCESSING");
        System.out.println("================================");

        // 1. Order Firestore mein save karna
        // 2. Cart clear karna
        // 3. My Orders open karna

        clearCart();

        My_orderAllorder myOrders =
                new My_orderAllorder(userId);

        Homepage.HomepageStage.setScene(
                myOrders.getAllorderScene()
        );
    }
}
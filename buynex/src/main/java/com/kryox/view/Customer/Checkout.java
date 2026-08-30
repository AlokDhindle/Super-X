package com.kryox.view.Customer;

import javafx.application.Application;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.UUID;

public class Checkout extends Application {

    // =========================================================
    // PRODUCT MODEL
    // =========================================================

    public static class CartProduct {

        private final StringProperty name =
                new SimpleStringProperty();

        private final StringProperty image =
                new SimpleStringProperty();

        private final IntegerProperty quantity =
                new SimpleIntegerProperty();

        private final DoubleProperty price =
                new SimpleDoubleProperty();

        public CartProduct(
                String name,
                String image,
                int quantity,
                double price) {

            this.name.set(name);
            this.image.set(image);
            this.quantity.set(quantity);
            this.price.set(price);
        }

        public String getName() {
            return name.get();
        }

        public String getImage() {
            return image.get();
        }

        public int getQuantity() {
            return quantity.get();
        }

        public void setQuantity(int quantity) {
            this.quantity.set(quantity);
        }

        public double getPrice() {
            return price.get();
        }

        public DoubleProperty priceProperty() {
            return price;
        }

        public IntegerProperty quantityProperty() {
            return quantity;
        }
    }

    // =========================================================
    // DYNAMIC CART DATA
    // =========================================================

    private final ObservableList<CartProduct> cartProducts =
            FXCollections.observableArrayList(

                    new CartProduct(
                            "Artisanal Sourdough Loaf",
                            "/assects/images/products/bread.png",
                            1,
                            8.50
                    ),

                    new CartProduct(
                            "Organic Avocados",
                            "/assects/images/products/avocado.png",
                            3,
                            2.00
                    )
            );

    // =========================================================
    // DYNAMIC ADDRESS
    // =========================================================

    private String customerName =
            "Alex Rivera";

    private String addressLine =
            "123 Innovation Drive, Apt 4B";

    private String city =
            "Tech District, San Francisco, CA 94105";

    private String phone =
            "+1 (555) 123-4567";

    // =========================================================
    // DELIVERY
    // =========================================================

    private double deliveryFee = 0.00;

    private final double TAX_RATE = 0.085;

    private Label subtotalValue;
    private Label deliveryValue;
    private Label taxValue;
    private Label totalValue;

    // =========================================================
    // PAGE NAVIGATION
    // =========================================================

    private Scene checkoutScene;
    private Stage currentStage;

    // =========================================================
    // COLORS
    // =========================================================

    private final String ORANGE = "#B84C00";
    private final String LIGHT_ORANGE = "#FFF1E8";
    private final String BORDER = "#E7DDD8";
    private final String BACKGROUND = "#F7F5F8";

    // =========================================================
    // START
    // =========================================================

    @Override
    public void start(Stage stage) {

        currentStage = stage;

        // =====================================================
        // MAIN BORDER PANE
        // =====================================================

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: " + BACKGROUND + ";"
        );

        // =====================================================
        // TOP HEADER
        // =====================================================

        HBox header =
                createHeader();

        root.setTop(header);

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        HBox content =
                new HBox(20);

        content.setPadding(
                new Insets(
                        28,
                        55,
                        35,
                        55
                )
        );

        // =====================================================
        // LEFT SIDE
        // =====================================================

        VBox leftSide =
                new VBox(18);

        leftSide.setPrefWidth(545);

        // CHECKOUT TITLE
        VBox titleBox =
                createCheckoutTitle();

        // SHIPPING
        VBox shippingBox =
                createShippingAddress();

        // DELIVERY
        VBox deliveryBox =
                createDeliveryMethod();

        // PAYMENT
        VBox paymentBox =
                createPaymentMethod();

        leftSide.getChildren().addAll(
                titleBox,
                shippingBox,
                deliveryBox,
                paymentBox
        );

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        VBox rightSide =
                createOrderSummary();

        content.getChildren().addAll(
                leftSide,
                rightSide
        );

        root.setCenter(content);

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scroll =
                new ScrollPane(content);

        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scroll.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-background: transparent;"
        );

        root.setCenter(scroll);

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        root,
                        1550,
                        850
                );

        stage.setTitle(
                "BuyNeX - Secure Checkout"
        );

        checkoutScene = scene;
        stage.setScene(scene);

        stage.setMinWidth(850);
        stage.setMinHeight(700);

        stage.show();

        updateTotals();
    }

    // =========================================================
    // HEADER
    // =========================================================

    private HBox createHeader() {

        HBox header =
                new HBox();

        header.setPrefHeight(50);

        header.setPadding(
                new Insets(
                        0,
                        55,
                        0,
                        55
                )
        );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setStyle(
                "-fx-background-color: white;"
        );

        Label logo =
                new Label("BuyNeX");

        logo.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label lock =
                new Label("🔒");

        Label secure =
                new Label("Secure Checkout");

        secure.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #666666;" +
                "-fx-font-weight: bold;"
        );

        HBox secureBox =
                new HBox(
                        7,
                        lock,
                        secure
                );

        secureBox.setAlignment(
                Pos.CENTER
        );

        header.getChildren().addAll(
                logo,
                spacer,
                secureBox
        );

        return header;
    }

    // =========================================================
    // CHECKOUT TITLE
    // =========================================================

    private VBox createCheckoutTitle() {

        VBox box =
                new VBox(4);

        Label title =
                new Label("Checkout");

        title.setStyle(
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        String orderId =
                "BNX-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 4)
                        .toUpperCase();

        Label order =
                new Label(
                        "Order ID: " + orderId
                );

        order.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: #765B4E;"
        );

        box.getChildren().addAll(
                title,
                order
        );

        return box;
    }

    // =========================================================
    // SHIPPING ADDRESS
    // =========================================================

    private VBox createShippingAddress() {

        VBox card =
                createCard();

        HBox heading =
                new HBox(9);

        Label icon =
                new Label("⌖");

        icon.setStyle(
                "-fx-text-fill: " + ORANGE + ";" +
                "-fx-font-size: 20px;"
        );

        Label title =
                new Label(
                        "Shipping Address"
                );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button change =
                new Button("Change");

        change.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: " + ORANGE + ";" +
                "-fx-font-size: 10px;" +
                "-fx-cursor: hand;"
        );

        heading.getChildren().addAll(
                icon,
                title,
                spacer,
                change
        );

        // =====================================================
        // ADDRESS BOX
        // =====================================================

        HBox addressBox =
                new HBox(12);

        addressBox.setPadding(
                new Insets(10)
        );

        addressBox.setStyle(
                "-fx-background-color: #F6F3F7;" +
                "-fx-background-radius: 6;" +
                "-fx-border-color: #EDE3E0;" +
                "-fx-border-radius: 6;"
        );

        Circle homeCircle =
                new Circle(
                        18,
                        Color.web("#F8E5D8")
                );

        Label home =
                new Label("⌂");

        home.setStyle(
                "-fx-text-fill: " + ORANGE + ";" +
                "-fx-font-size: 16px;"
        );

        StackPane homeBox =
                new StackPane(
                        homeCircle,
                        home
                );

        VBox addressText =
                new VBox(3);

        Label customer =
                new Label(
                        customerName
                );

        customer.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label address =
                new Label(
                        addressLine
                );

        Label cityLabel =
                new Label(
                        city
                );

        Label phoneLabel =
                new Label(
                        phone
                );

        address.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        cityLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        phoneLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        addressText.getChildren().addAll(
                customer,
                address,
                cityLabel,
                phoneLabel
        );

        addressBox.getChildren().addAll(
                homeBox,
                addressText
        );

        card.getChildren().addAll(
                heading,
                addressBox
        );

        return card;
    }

    // =========================================================
    // DELIVERY METHOD
    // =========================================================

    private VBox createDeliveryMethod() {

        VBox card =
                createCard();

        Label title =
                new Label(
                        "🚚  Delivery Method"
                );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        ToggleGroup deliveryGroup =
                new ToggleGroup();

        // =====================================================
        // EXPRESS
        // =====================================================

        RadioButton express =
                new RadioButton();

        express.setToggleGroup(
                deliveryGroup
        );

        VBox expressText =
                new VBox(3);

        Label expressTitle =
                new Label(
                        "Express 5 Min"
                );

        Label expressSub =
                new Label(
                        "Instant Delivery"
                );

        expressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        expressSub.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;"
        );

        expressText.getChildren().addAll(
                expressTitle,
                expressSub
        );

        Label expressPrice =
                new Label("+$6.99");

        expressPrice.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox expressBox =
                new HBox(8);

        expressBox.setAlignment(
                Pos.CENTER_LEFT
        );

        expressBox.setPadding(
                new Insets(8)
        );

        Region expressSpacer =
                new Region();

        HBox.setHgrow(
                expressSpacer,
                Priority.ALWAYS
        );

        expressBox.getChildren().addAll(
                express,
                expressText,
                expressSpacer,
                expressPrice
        );

        expressBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        // =====================================================
        // HYPERLOCAL
        // =====================================================

        RadioButton hyperlocal =
                new RadioButton();

        hyperlocal.setToggleGroup(
                deliveryGroup
        );

        VBox hyperText =
                new VBox(3);

        Label hyperTitle =
                new Label(
                        "Hyperlocal 10-15 Min"
                );

        Label hyperSub =
                new Label(
                        "Local Courier"
                );

        hyperTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        hyperSub.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;"
        );

        hyperText.getChildren().addAll(
                hyperTitle,
                hyperSub
        );

        Label hyperPrice =
                new Label("+$4.99");

        hyperPrice.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox hyperBox =
                new HBox(8);

        hyperBox.setAlignment(
                Pos.CENTER_LEFT
        );

        hyperBox.setPadding(
                new Insets(8)
        );

        Region hyperSpacer =
                new Region();

        HBox.setHgrow(
                hyperSpacer,
                Priority.ALWAYS
        );

        hyperBox.getChildren().addAll(
                hyperlocal,
                hyperText,
                hyperSpacer,
                hyperPrice
        );

        hyperBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        // =====================================================
        // STANDARD
        // =====================================================

        RadioButton standard =
                new RadioButton();

        standard.setToggleGroup(
                deliveryGroup
        );

        standard.setSelected(true);

        VBox standardText =
                new VBox(3);

        Label standardTitle =
                new Label(
                        "Standard 1-2 Days"
                );

        Label standardSub =
                new Label(
                        "Eco-friendly Shipping"
                );

        standardTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        standardSub.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #777777;"
        );

        standardText.getChildren().addAll(
                standardTitle,
                standardSub
        );

        Region standardSpacer =
                new Region();

        HBox.setHgrow(
                standardSpacer,
                Priority.ALWAYS
        );

        Label free =
                new Label("Free");

        free.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox standardBox =
                new HBox(8);

        standardBox.setAlignment(
                Pos.CENTER_LEFT
        );

        standardBox.setPadding(
                new Insets(8)
        );

        standardBox.getChildren().addAll(
                standard,
                standardText,
                standardSpacer,
                free
        );

        standardBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + ORANGE + ";" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        // =====================================================
        // DELIVERY EVENT
        // =====================================================

        express.setOnAction(e -> {

            deliveryFee = 6.99;

            updateTotals();

            updateDeliverySelection(
                    expressBox,
                    hyperBox,
                    standardBox
            );
        });

        hyperlocal.setOnAction(e -> {

            deliveryFee = 4.99;

            updateTotals();

            updateDeliverySelection(
                    hyperBox,
                    expressBox,
                    standardBox
            );
        });

        standard.setOnAction(e -> {

            deliveryFee = 0;

            updateTotals();

            updateDeliverySelection(
                    standardBox,
                    expressBox,
                    hyperBox
            );
        });

        HBox row1 =
                new HBox(
                        8,
                        expressBox,
                        hyperBox
                );

        HBox.setHgrow(
                expressBox,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                hyperBox,
                Priority.ALWAYS
        );

        card.getChildren().addAll(
                title,
                row1,
                standardBox
        );

        return card;
    }

    // =========================================================
    // DELIVERY SELECTION STYLE
    // =========================================================

    private void updateDeliverySelection(
            HBox selected,
            HBox other1,
            HBox other2) {

        selected.setStyle(
                "-fx-background-color: #FFF8F3;" +
                "-fx-border-color: " + ORANGE + ";" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        other1.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        other2.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );
    }

    // =========================================================
    // PAYMENT METHOD
    // =========================================================

    private VBox createPaymentMethod() {

        VBox card =
                createCard();

        Label title =
                new Label(
                        "▣  Payment Method"
                );

        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        ToggleGroup paymentGroup =
                new ToggleGroup();

        RadioButton cardPayment =
                new RadioButton(
                        "Credit / Debit Card"
                );

        cardPayment.setSelected(true);
        cardPayment.setToggleGroup(
                paymentGroup
        );

        cardPayment.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #222222;"
        );

        // =====================================================
        // CARD NUMBER
        // =====================================================

        TextField cardNumber =
                new TextField();

        cardNumber.setPromptText(
                "Card Number"
        );

        cardNumber.setPrefHeight(32);

        styleTextField(cardNumber);

        // =====================================================
        // EXPIRY + CVC
        // =====================================================

        TextField expiry =
                new TextField();

        expiry.setPromptText(
                "MM/YY"
        );

        styleTextField(expiry);

        TextField cvc =
                new TextField();

        cvc.setPromptText(
                "CVC"
        );

        styleTextField(cvc);

        HBox cardDetails =
                new HBox(8);

        HBox.setHgrow(
                expiry,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                cvc,
                Priority.ALWAYS
        );

        cardDetails.getChildren().addAll(
                expiry,
                cvc
        );

        // =====================================================
        // CARD FORM
        // =====================================================

        VBox cardForm =
                new VBox(9);

        cardForm.setPadding(
                new Insets(10)
        );

        cardForm.setStyle(
                "-fx-background-color: #FCFAFC;" +
                "-fx-border-color: " + ORANGE + ";" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        cardForm.getChildren().addAll(
                cardPayment,
                cardNumber,
                cardDetails
        );

        // =====================================================
        // OTHER PAYMENT
        // =====================================================

        RadioButton upi =
                new RadioButton(
                        "UPI Payment"
                );

        upi.setToggleGroup(
                paymentGroup
        );

        upi.setStyle(
                "-fx-font-size: 10px;"
        );

        RadioButton cash =
                new RadioButton(
                        "Cash on Delivery"
                );

        cash.setToggleGroup(
                paymentGroup
        );

        cash.setStyle(
                "-fx-font-size: 10px;"
        );

        card.getChildren().addAll(
                title,
                cardForm,
                upi,
                cash
        );

        return card;
    }

    // =========================================================
    // ORDER SUMMARY
    // =========================================================

    private VBox createOrderSummary() {

        VBox summary =
                new VBox(12);

        summary.setPrefWidth(265);

        summary.setPadding(
                new Insets(18)
        );

        summary.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E57828;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-border-width: 1;"
        );

        Label title =
                new Label(
                        "Order Summary"
                );

        title.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;"
        );

        VBox productList =
                new VBox(9);

        for (CartProduct product :
                cartProducts) {

            productList.getChildren().add(
                    createSummaryProduct(
                            product
                    )
            );
        }

        Separator separator =
                new Separator();

        subtotalValue =
                new Label("$0.00");

        deliveryValue =
                new Label("$0.00");

        taxValue =
                new Label("$0.00");

        totalValue =
                new Label("$0.00");

        HBox subtotal =
                summaryRow(
                        "Subtotal",
                        subtotalValue
                );

        HBox delivery =
                summaryRow(
                        "Delivery Fee",
                        deliveryValue
                );

        HBox taxes =
                summaryRow(
                        "Taxes",
                        taxValue
                );

        Separator totalSeparator =
                new Separator();

        HBox total =
                new HBox();

        total.setAlignment(
                Pos.CENTER_LEFT
        );

        Label totalText =
                new Label("Total");

        totalText.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Region totalSpacer =
                new Region();

        HBox.setHgrow(
                totalSpacer,
                Priority.ALWAYS
        );

        totalValue.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + ORANGE + ";"
        );

        total.getChildren().addAll(
                totalText,
                totalSpacer,
                totalValue
        );

        // =====================================================
        // PLACE ORDER BUTTON
        // =====================================================

        Button placeOrder =
                new Button(
                        "🔒   Place Order"
                );

        placeOrder.setMaxWidth(
                Double.MAX_VALUE
        );

        placeOrder.setPrefHeight(38);

        placeOrder.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF6900, #B94D00);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-cursor: hand;"
        );

        placeOrder.setOnAction(e ->
                showOrderConfirmation()
        );

        Label security =
                new Label(
                        "♢ SSL Secure   •   💳 PCI-DSS"
                );

        security.setMaxWidth(
                Double.MAX_VALUE
        );

        security.setAlignment(
                Pos.CENTER
        );

        security.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #666666;"
        );

        summary.getChildren().addAll(
                title,
                productList,
                separator,
                subtotal,
                delivery,
                taxes,
                totalSeparator,
                total,
                placeOrder,
                security
        );

        return summary;
    }

    // =========================================================
    // SUMMARY PRODUCT
    // =========================================================

    private HBox createSummaryProduct(
            CartProduct product) {

        HBox box =
                new HBox(8);

        box.setAlignment(
                Pos.CENTER_LEFT
        );

        StackPane imageBox =
                new StackPane();

        imageBox.setPrefSize(
                46,
                46
        );

        imageBox.setStyle(
                "-fx-background-color: #F4F2F4;" +
                "-fx-background-radius: 5;"
        );

        try {

            Image image =
                    new Image(
                            getClass()
                                    .getResourceAsStream(
                                            product.getImage()
                                    )
                    );

            ImageView imageView =
                    new ImageView(image);

            imageView.setFitWidth(42);
            imageView.setFitHeight(42);
            imageView.setPreserveRatio(true);

            imageBox.getChildren().add(
                    imageView
            );

        } catch (Exception e) {

            imageBox.getChildren().add(
                    new Label("IMG")
            );
        }

        VBox info =
                new VBox(2);

        Label name =
                new Label(
                        product.getName()
                );

        name.setMaxWidth(120);

        name.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;"
        );

        Label qty =
                new Label(
                        "Qty: " +
                        product.getQuantity()
                );

        qty.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #777777;"
        );

        info.getChildren().addAll(
                name,
                qty
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label price =
                new Label(
                        String.format(
                                "$%.2f",
                                product.getPrice()
                                        * product.getQuantity()
                        )
                );

        price.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        box.getChildren().addAll(
                imageBox,
                info,
                spacer,
                price
        );

        return box;
    }

    // =========================================================
    // SUMMARY ROW
    // =========================================================

    private HBox summaryRow(
            String title,
            Label value) {

        HBox row =
                new HBox();

        Label label =
                new Label(title);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #555555;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        value.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        row.getChildren().addAll(
                label,
                spacer,
                value
        );

        return row;
    }

    // =========================================================
    // CARD STYLE
    // =========================================================

    private VBox createCard() {

        VBox card =
                new VBox(12);

        card.setPadding(
                new Insets(14)
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #E9E1DD;" +
                "-fx-border-radius: 8;"
        );

        return card;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            TextField field) {

        field.setPrefHeight(32);

        field.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #E5D9D3;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-padding: 0 10;" +
                "-fx-font-size: 10px;"
        );
    }

    // =========================================================
    // UPDATE TOTALS
    // =========================================================

    private void updateTotals() {

        double subtotal = 0;

        for (CartProduct product :
                cartProducts) {

            subtotal +=
                    product.getPrice()
                            * product.getQuantity();
        }

        double tax =
                subtotal * TAX_RATE;

        double total =
                subtotal
                        + deliveryFee
                        + tax;

        if (subtotalValue != null) {

            subtotalValue.setText(
                    String.format(
                            "$%.2f",
                            subtotal
                    )
            );
        }

        if (deliveryValue != null) {

            deliveryValue.setText(
                    String.format(
                            "$%.2f",
                            deliveryFee
                    )
            );
        }

        if (taxValue != null) {

            taxValue.setText(
                    String.format(
                            "$%.2f",
                            tax
                    )
            );
        }

        if (totalValue != null) {

            totalValue.setText(
                    String.format(
                            "$%.2f",
                            total
                    )
            );
        }
    }

    // =========================================================
    // PLACE ORDER
    // =========================================================

    private void showOrderConfirmation() {

        // Open the proper booking-success page instead of a small Alert.
        showBookingSuccessPage();
    }

    // =========================================================
    // BOOKING SUCCESS PAGE
    // =========================================================

    private void showBookingSuccessPage() {

        String bookingId =
                "BK-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 4)
                        .toUpperCase();

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color: #F7F5F8;"
        );

        // =====================================================
        // SIMPLE BUY-NEX HEADER
        // =====================================================

        HBox header = new HBox();

        header.setPrefHeight(50);
        header.setPadding(new Insets(0, 38, 0, 38));
        header.setAlignment(Pos.CENTER_LEFT);

        header.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #ECE7EC;" +
                "-fx-border-width: 0 0 1 0;"
        );

        Label logo = new Label("BuyNeX");

        logo.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #B84C00;"
        );

        Region headerSpacer = new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        Label secure = new Label("🔒  Secure Checkout");

        secure.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: #77716E;"
        );

        header.getChildren().addAll(
                logo,
                headerSpacer,
                secure
        );

        root.setTop(header);

        // =====================================================
        // MAIN SUCCESS CARD
        // =====================================================

        VBox card = new VBox(12);

        card.setPrefWidth(390);
        card.setMaxWidth(390);

        card.setPadding(
                new Insets(25, 26, 24, 26)
        );

        card.setAlignment(
                Pos.TOP_CENTER
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;"
        );

        javafx.scene.effect.DropShadow shadow =
                new javafx.scene.effect.DropShadow();

        shadow.setRadius(18);
        shadow.setOffsetY(7);
        shadow.setColor(
                Color.rgb(0, 0, 0, 0.12)
        );

        card.setEffect(shadow);

        // =====================================================
        // SUCCESS ICON
        // =====================================================

        StackPane successIcon =
                new StackPane();

        successIcon.setPrefSize(52, 52);
        successIcon.setMaxSize(52, 52);

        Circle iconBackground =
                new Circle(
                        26,
                        Color.web("#FFF0E5")
                );

        Circle iconCircle =
                new Circle(
                        16,
                        Color.web("#FF6900")
                );

        Label check =
                new Label("✓");

        check.setStyle(
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;"
        );

        successIcon.getChildren().addAll(
                iconBackground,
                iconCircle,
                check
        );

        // =====================================================
        // TITLE
        // =====================================================

        Label title =
                new Label(
                        "Product Booked Successfully!"
                );

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        Label subtitle =
                new Label(
                        "Your reservation for order #" +
                        bookingId +
                        " has been confirmed."
                );

        subtitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #786F6B;"
        );

        // =====================================================
        // BOOKED PRODUCT
        // =====================================================

        CartProduct bookedProduct =
                cartProducts.isEmpty()
                        ? null
                        : cartProducts.get(0);

        String bookedName =
                bookedProduct == null
                        ? "Your Product"
                        : bookedProduct.getName();

        String bookedImage =
                bookedProduct == null
                        ? ""
                        : bookedProduct.getImage();

        HBox productRow =
                new HBox(10);

        productRow.setPrefHeight(72);
        productRow.setAlignment(
                Pos.CENTER_LEFT
        );

        productRow.setPadding(
                new Insets(9)
        );

        productRow.setStyle(
                "-fx-background-color: #F3F0F5;" +
                "-fx-background-radius: 6;"
        );

        StackPane productImage =
                new StackPane();

        productImage.setPrefSize(55, 52);
        productImage.setMinSize(55, 52);
        productImage.setMaxSize(55, 52);

        productImage.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 4;"
        );

        try {

            if (!bookedImage.isEmpty()) {

                Image image =
                        new Image(
                                getClass()
                                        .getResourceAsStream(
                                                bookedImage
                                        )
                        );

                if (!image.isError()) {

                    ImageView imageView =
                            new ImageView(image);

                    imageView.setFitWidth(50);
                    imageView.setFitHeight(48);
                    imageView.setPreserveRatio(true);

                    productImage.getChildren().add(
                            imageView
                    );
                }
            }

        } catch (Exception ignored) {
        }

        VBox productInfo =
                new VBox(3);

        productInfo.setAlignment(
                Pos.CENTER_LEFT
        );

        Label productName =
                new Label(bookedName);

        productName.setMaxWidth(245);

        productName.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label shop =
                new Label(
                        "▣  BakeHouse Local"
                );

        shop.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #77716E;"
        );

        productInfo.getChildren().addAll(
                productName,
                shop
        );

        productRow.getChildren().addAll(
                productImage,
                productInfo
        );

        // =====================================================
        // STATUS ROW
        // =====================================================

        HBox statusRow =
                new HBox();

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label status =
                new Label("Status");

        status.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #77716E;"
        );

        Region statusSpacer =
                new Region();

        HBox.setHgrow(
                statusSpacer,
                Priority.ALWAYS
        );

        Label valid =
                new Label(
                        "Valid for 24 hours"
                );

        valid.setPadding(
                new Insets(3, 8, 3, 8)
        );

        valid.setStyle(
                "-fx-background-color: #FFE4D6;" +
                "-fx-background-radius: 10;" +
                "-fx-text-fill: #FF6900;" +
                "-fx-font-size: 7px;" +
                "-fx-font-weight: bold;"
        );

        statusRow.getChildren().addAll(
                status,
                statusSpacer,
                valid
        );

        VBox productBox =
                new VBox(7);

        productBox.setPadding(
                new Insets(9)
        );

        productBox.setStyle(
                "-fx-background-color: #F3F0F5;" +
                "-fx-background-radius: 6;"
        );

        productBox.getChildren().addAll(
                productRow,
                statusRow
        );

        // =====================================================
        // NEXT STEPS
        // =====================================================

        VBox nextSteps =
                new VBox(5);

        nextSteps.setPadding(
                new Insets(2, 0, 0, 0)
        );

        Label nextTitle =
                new Label("⚡  Next Steps");

        nextTitle.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label nextText =
                new Label(
                        "Visit the shop within the next 24 hours and show your booking\n" +
                        "ID #" + bookingId +
                        " at the counter to complete your purchase and\n" +
                        "pick up your item."
                );

        nextText.setWrapText(true);

        nextText.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #756B67;" +
                "-fx-line-spacing: 2px;"
        );

        nextSteps.getChildren().addAll(
                nextTitle,
                nextText
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button bookingsButton =
                new Button("View My Bookings");

        bookingsButton.setMaxWidth(
                Double.MAX_VALUE
        );

        bookingsButton.setPrefHeight(34);

        bookingsButton.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );

        bookingsButton.setOnAction(e -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setTitle("My Bookings");
            alert.setHeaderText(
                    "Booking Confirmed"
            );

            alert.setContentText(
                    "Booking ID: #" +
                    bookingId +
                    "\n\n" +
                    bookedName +
                    "\n" +
                    "BakeHouse Local"
            );

            alert.showAndWait();
        });

        Button backButton =
                new Button("Back to Home");

        backButton.setMaxWidth(
                Double.MAX_VALUE
        );

        backButton.setPrefHeight(32);

        backButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #B84C00;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #B84C00;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-cursor: hand;"
        );

        backButton.setOnAction(e -> {

            if (checkoutScene != null) {

                currentStage.setScene(
                        checkoutScene
                );

                currentStage.setTitle(
                        "BuyNeX - Secure Checkout"
                );
            }
        });

        card.getChildren().addAll(
                successIcon,
                title,
                subtitle,
                productBox,
                nextSteps,
                bookingsButton,
                backButton
        );

        // =====================================================
        // CENTER THE CARD
        // =====================================================

        StackPane center =
                new StackPane(card);

        center.setAlignment(
                Pos.CENTER
        );

        center.setPadding(
                new Insets(25)
        );

        root.setCenter(center);

        // =====================================================
        // SUCCESS SCENE
        // =====================================================

        Scene successScene =
                new Scene(
                        root,
                        940,
                        750
                );

        currentStage.setScene(
                successScene
        );

        // =====================================================
        // SMOOTH ENTRY ANIMATION
        // =====================================================

        card.setOpacity(0);
        card.setTranslateY(15);
        card.setScaleX(0.98);
        card.setScaleY(0.98);

        FadeTransition fade =
                new FadeTransition(
                        Duration.millis(450),
                        card
                );

        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition slide =
                new TranslateTransition(
                        Duration.millis(450),
                        card
                );

        slide.setFromY(15);
        slide.setToY(0);

        ScaleTransition scale =
                new ScaleTransition(
                        Duration.millis(450),
                        card
                );

        scale.setFromX(0.98);
        scale.setFromY(0.98);
        scale.setToX(1);
        scale.setToY(1);

        new ParallelTransition(
                fade,
                slide,
                scale
        ).play();

        ScaleTransition iconAnimation =
                new ScaleTransition(
                        Duration.millis(350),
                        successIcon
                );

        iconAnimation.setDelay(
                Duration.millis(180)
        );

        iconAnimation.setFromX(0.65);
        iconAnimation.setFromY(0.65);
        iconAnimation.setToX(1);
        iconAnimation.setToY(1);

        successIcon.setScaleX(0.65);
        successIcon.setScaleY(0.65);

        iconAnimation.play();
    }


    // =========================================================
    // MAIN
    // =========================================================

   
    
}
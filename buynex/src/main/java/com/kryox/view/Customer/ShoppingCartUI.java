package com.kryox.view.Customer;

import com.kryox.controller.Customer.PaymentController;
import com.kryox.config.Firebaseconfig;

import com.google.cloud.firestore.Firestore;
import com.kryox.model.Shopkeeper.OfferModel;
import javafx.scene.layout.FlowPane;

import javafx.application.Platform;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

import com.kryox.controller.Customer.CARTcontroller;
import com.kryox.model.Customer.Productcart;

public class ShoppingCartUI {

    private VBox products;
    private Scene addcartScene;
    private String userId;

    private static final double FREE_DELIVERY_THRESHOLD = 99.0;
    private Label freeDeliveryMoreLabel;
    private Label deliveryFeeValueLabel;
    private Region freeDeliveryProgress;
    private Label visibleTotalValueLabel;

    private Label discountRowAmountLabel;
    private Label discountRowTitleLabel;
    private HBox discountRow;
    private double currentDiscountAmount = 0.0;
    private double[] currentSubtotalRef;
    private Label currentSubtotalAmountLabel;
    private Label currentTotalAmountLabel;
    private Label promoMessageLabel;
    private TextField promoInputField;

    private final List<Productcart> activeCartProducts = new ArrayList<>();

    public ShoppingCartUI(String userId) {
        this.userId = userId;
    }

    public Scene getaddcartScene() {
        VBox leftBox = CustomerSidebar.createSidebar(userId, "Cart");
        HBox navBox = CustomerSidebar.createTopNav(userId, "CART", null, () -> CustomerNavigation.navigateToCart(userId));

        VBox cartPage = createCartPage();

        ScrollPane scrollPane = new ScrollPane(cartPage);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        VBox rightBox = new VBox(0, navBox, scrollPane);
        rightBox.setStyle("-fx-background-color: #F8F6FA;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        RadialGradient orangeGlow = new RadialGradient(
                0, 0, 0.84, 0.16, 0.42, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.web("#FF9148", 0.18)),
                new Stop(0.40, Color.web("#FFD1B4", 0.08)),
                new Stop(1.0, Color.TRANSPARENT)
        );

        rightBox.setBackground(new Background(new BackgroundFill(orangeGlow, CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane mainBox = new BorderPane();
        mainBox.setLeft(leftBox);
        mainBox.setCenter(rightBox);
        mainBox.setStyle("-fx-background-color: #F8F6FA;");

        Scene scene = new Scene(mainBox, 1530, 850);
        addcartScene = scene;

        return addcartScene;
    }

    private void clearCart11() {
        if (products != null) {
            products.getChildren().clear();
        }
        System.out.println("Cart cleared successfully.");
    }

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
            Image image;
            if (imagePath != null && (imagePath.startsWith("http://") || imagePath.startsWith("https://"))) {
                image = new Image(imagePath, true);
            } else {
                java.io.InputStream is = getClass().getResourceAsStream(imagePath);
                if (is != null) {
                    image = new Image(is);
                } else {
                    image = new Image(imagePath);
                }
            }

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

    private HBox createQuantityBox(
            Productcart product,
            Label priceLabel,
            Label eachLabel,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel,
            int[] itemCount,
            double[] subtotal,
            VBox card,
            VBox products) {

        Button minus = new Button("−");
        Button plus = new Button("+");

        int initialQty = product.getQuantity() > 0 ? product.getQuantity() : 1;
        product.setQuantity(initialQty);
        Label qty = new Label(String.valueOf(initialQty));

        String buttonStyle = "-fx-background-color: transparent;" +
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

            if (value <= 1) {
                card.setVisible(false);
                card.setManaged(false);

                subtotal[0] -= product.getPrice();
                if (subtotal[0] < 0) {
                    subtotal[0] = 0;
                }

                itemCount[0]--;
                if (itemCount[0] < 0) {
                    itemCount[0] = 0;
                }

                activeCartProducts.remove(product);

                subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount[0]));
                updateSummaryAmounts(subtotal, subtotalAmountLabel, totalAmountLabel, deliveryFeeValueLabel);

                new Thread(() -> new CARTcontroller().deleteFromCart(userId, product.getName())).start();

                if (itemCount[0] == 0) {
                    Label emptyLabel = new Label("Your cart is empty.");
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

            value--;
            qty.setText(String.valueOf(value));
            product.setQuantity(value);

            priceLabel.setText(String.format("₹%.2f", product.getPrice() * value));
            eachLabel.setText(String.format("₹%.2f / ea", product.getPrice()));

            subtotal[0] -= product.getPrice();
            if (subtotal[0] < 0) {
                subtotal[0] = 0;
            }

            itemCount[0]--;
            if (itemCount[0] < 0) {
                itemCount[0] = 0;
            }

            subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount[0]));
            updateSummaryAmounts(subtotal, subtotalAmountLabel, totalAmountLabel, deliveryFeeValueLabel);

            final int updatedVal = value;
            new Thread(() -> new CARTcontroller().updateQuantity(userId, product.getName(), updatedVal)).start();
        });

        plus.setOnAction(e -> {
            int value = Integer.parseInt(qty.getText());
            value++;

            qty.setText(String.valueOf(value));
            product.setQuantity(value);
            priceLabel.setText(String.format("₹%.2f", product.getPrice() * value));
            eachLabel.setText(String.format("₹%.2f / ea", product.getPrice()));

            subtotal[0] += product.getPrice();
            itemCount[0]++;

            subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount[0]));
            updateSummaryAmounts(subtotal, subtotalAmountLabel, totalAmountLabel, deliveryFeeValueLabel);

            final int updatedVal = value;
            new Thread(() -> new CARTcontroller().updateQuantity(userId, product.getName(), updatedVal)).start();
        });

        return quantityBox;
    }

    private VBox createCartProduct(
            Productcart product,
            String oldPrice,
            String imagePath,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel,
            int[] itemCount,
            double[] subtotal,
            VBox products) {

        String productName = product.getName();
        String shopName = product.getName1() != null && !product.getName1().isBlank() ? product.getName1() : "BuyNex Store";
        double unitPrice = product.getPrice();
        int initialQuantity = product.getQuantity() > 0 ? product.getQuantity() : 1;
        product.setQuantity(initialQuantity);

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

        HBox productTop = new HBox(17);
        productTop.setAlignment(Pos.CENTER_LEFT);

        StackPane productImage = createProductImage(imagePath);

        VBox productInfo = new VBox(4);
        Label productTitle = new Label(productName);
        productTitle.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label shop = new Label();
        shop.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #B44E00;" +
                "-fx-font-weight: bold;"
        );
        if (product.getName1() != null && !product.getName1().isBlank() && !product.getName1().equalsIgnoreCase("BuyNex Store")) {
            shop.setText("From " + product.getName1());
        } else {
            CustomerShopResolver.bindShopName(shop, product.getShopkeeperUid(), "From ");
        }

        productInfo.getChildren().addAll(productTitle, shop);

        Region productSpacer = new Region();
        HBox.setHgrow(productSpacer, Priority.ALWAYS);

        Button deleteButton = new Button("▣");
        deleteButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #A74716;" +
                "-fx-font-size: 15px;" +
                "-fx-padding: 2;" +
                "-fx-cursor: hand;"
        );

        deleteButton.setOnAction(e -> {
            int currentQuantity = product.getQuantity() > 0 ? product.getQuantity() : 1;

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

            activeCartProducts.remove(product);

            subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount[0]));
            updateSummaryAmounts(subtotal, subtotalAmountLabel, totalAmountLabel, deliveryFeeValueLabel);

            new Thread(() -> new CARTcontroller().deleteFromCart(userId, product.getName())).start();

            if (itemCount[0] == 0) {
                Label emptyLabel = new Label("Your cart is empty.");
                emptyLabel.setStyle(
                        "-fx-font-size: 14px;" +
                        "-fx-text-fill: #777777;" +
                        "-fx-font-weight: bold;"
                );
                products.getChildren().clear();
                products.getChildren().add(emptyLabel);
            }
        });

        productTop.getChildren().addAll(productImage, productInfo, productSpacer, deleteButton);

        HBox bottomRow = new HBox(15);
        bottomRow.setAlignment(Pos.CENTER_LEFT);

        Label priceLabel = new Label(String.format("₹%.2f", unitPrice * initialQuantity));
        priceLabel.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #151515;"
        );

        Label each = new Label(String.format("₹%.2f / ea", unitPrice));
        each.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        HBox quantity1 = createQuantityBox(
                product,
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

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        VBox priceBox = new VBox(1);
        priceBox.setAlignment(Pos.CENTER_RIGHT);
        priceBox.getChildren().addAll(priceLabel, each);

        bottomRow.getChildren().addAll(quantity1, bottomSpacer, priceBox);

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: #ECE8EC;");

        HBox actions = new HBox(9);
        actions.setAlignment(Pos.CENTER_LEFT);

        Button buyNow = new Button("Buy Now");
        buyNow.setStyle(
                "-fx-background-color: #B94D00;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );

        buyNow.setOnAction(event -> {
            final int currentQuantity = product.getQuantity() > 0 ? product.getQuantity() : 1;
            final double bSubtotal = unitPrice * currentQuantity;
            final double bPlatformFee = 10.0;
            final double bTax = bSubtotal * 0.05;
            final double bDeliveryFee = bSubtotal >= FREE_DELIVERY_THRESHOLD ? 0.0 : 30.0;

            double bDiscount = 0.0;
            String bPromo = "";
            if (CartOfferManager.getAppliedOffer() != null) {
                bDiscount = CartOfferManager.calculateDiscount(CartOfferManager.getAppliedOffer(), bSubtotal, List.of(product));
                bPromo = CartOfferManager.getAppliedOffer().getPromoCode();
            }

            final double bTotal = Math.max(0, bSubtotal - bDiscount) + bPlatformFee + bTax + bDeliveryFee;
            final double finalBDiscount = bDiscount;
            final String finalBPromo = bPromo;

            PaymentController paymentController = new PaymentController();
            paymentController.startPayment(bTotal, () -> {
                handleSingleProductPaymentSuccess(product, currentQuantity, bTotal, bSubtotal, bPlatformFee, bTax, bDeliveryFee, finalBDiscount, finalBPromo);
            });
        });

        Button book = new Button("Book Product");
        book.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-text-fill: #222222;" +
                "-fx-font-size: 12px;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 7 14 7 14;" +
                "-fx-cursor: hand;"
        );
        book.setOnAction(event -> {
            final int currentQuantity = product.getQuantity() > 0 ? product.getQuantity() : 1;
            final double currentTotal = unitPrice * currentQuantity;
            PaymentController paymentController = new PaymentController();
            paymentController.startPayment(currentTotal, () -> {
                javafx.application.Platform.runLater(() -> {
                    System.out.println("Booking Success for " + productName);
                    try {
                        java.util.Map<String, Object> bookingData = new java.util.HashMap<>();
                        bookingData.put("productName", productName);
                        bookingData.put("quantity", currentQuantity);
                        bookingData.put("totalAmount", currentTotal);
                        bookingData.put("customerId", userId);
                        bookingData.put("status", "BOOKED");
                        bookingData.put("date", java.time.LocalDate.now().toString());

                        com.google.cloud.firestore.Firestore db = com.kryox.config.Firebaseconfig.gFirestore();
                        db.collection("Bookings").add(bookingData);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    BookingSuccess bs = new BookingSuccess();
                    Homepage.HomepageStage.setScene(bs.getBookingscene());
                });
            });
        });

        Button visit = new Button("Visit Shop");
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
        visit.setOnAction(event -> {
            neaby_shope ns = new neaby_shope(userId);
            Homepage.HomepageStage.setScene(ns.getNearby_shopes(() -> {
                Dashbord ds = new Dashbord(userId);
                Homepage.HomepageStage.setScene(ds.getDashbordScene());
            }));
        });

        actions.getChildren().addAll(buyNow, book, visit);

        card.getChildren().addAll(productTop, bottomRow, separator, actions);
        return card;
    }

    private void updateSummaryAmounts(
            double[] subtotal,
            Label subtotalAmountLabel,
            Label totalAmountLabel,
            Label deliveryFeeLabel) {

        double subtotalAmount = subtotal != null && subtotal.length > 0 ? subtotal[0] : 0.0;
        double platformFee = 10.0;
        double tax = subtotalAmount * 0.05;
        double deliveryFee = subtotalAmount >= FREE_DELIVERY_THRESHOLD ? 0 : 30.0;

        if (CartOfferManager.getAppliedOffer() != null && subtotalAmount > 0) {
            currentDiscountAmount = CartOfferManager.calculateDiscount(CartOfferManager.getAppliedOffer(), subtotalAmount, activeCartProducts);
        } else {
            currentDiscountAmount = 0.0;
        }

        if (discountRow != null && discountRowAmountLabel != null) {
            if (currentDiscountAmount > 0) {
                discountRow.setVisible(true);
                discountRow.setManaged(true);
                discountRowAmountLabel.setText(String.format("-₹%.2f", currentDiscountAmount));
            } else {
                discountRow.setVisible(false);
                discountRow.setManaged(false);
            }
        }

        double total = Math.max(0, subtotalAmount - currentDiscountAmount) + platformFee + tax + deliveryFee;

        if (subtotalAmountLabel != null) {
            subtotalAmountLabel.setText(String.format("₹%.2f", subtotalAmount));
        }

        Label totalLabelToUpdate = visibleTotalValueLabel != null ? visibleTotalValueLabel : totalAmountLabel;
        if (totalLabelToUpdate != null) {
            totalLabelToUpdate.setText(String.format("₹%.2f", total));
            totalLabelToUpdate.applyCss();
            totalLabelToUpdate.layout();
        }

        if (deliveryFeeLabel != null) {
            deliveryFeeLabel.setText(deliveryFee == 0 ? "FREE" : String.format("₹%.2f", deliveryFee));
        }

        if (freeDeliveryMoreLabel != null) {
            if (subtotalAmount >= FREE_DELIVERY_THRESHOLD) {
                freeDeliveryMoreLabel.setText("Free Delivery Unlocked");
            } else {
                double remaining = FREE_DELIVERY_THRESHOLD - subtotalAmount;
                freeDeliveryMoreLabel.setText(String.format("₹%.2f more", remaining));
            }
        }

        if (freeDeliveryProgress != null) {
            double progressRatio = Math.min(subtotalAmount / FREE_DELIVERY_THRESHOLD, 1.0);
            double progressWidth = 264.0 * progressRatio;
            freeDeliveryProgress.setPrefWidth(progressWidth);
            freeDeliveryProgress.setMaxWidth(progressWidth);
        }
    }

    private VBox createOrderSummary(
            double[] subtotal,
            int itemCount,
            Label subtotalLabel,
            Label subtotalAmountLabel,
            Label totalAmountLabel) {

        this.currentSubtotalRef = subtotal;
        this.currentSubtotalAmountLabel = subtotalAmountLabel;
        this.currentTotalAmountLabel = totalAmountLabel;

        double subtotalAmount = subtotal != null && subtotal.length > 0 ? subtotal[0] : 0.0;
        double platformFee = 10.0;
        double tax = subtotalAmount * 0.05;
        double deliveryFee = subtotalAmount >= FREE_DELIVERY_THRESHOLD ? 0 : 30.0;

        if (CartOfferManager.getAppliedOffer() != null && subtotalAmount > 0) {
            currentDiscountAmount = CartOfferManager.calculateDiscount(CartOfferManager.getAppliedOffer(), subtotalAmount, activeCartProducts);
        } else {
            currentDiscountAmount = 0.0;
        }

        double totalAmount = Math.max(0, subtotalAmount - currentDiscountAmount) + platformFee + tax + deliveryFee;

        VBox summary = new VBox(12);
        summary.setPadding(new Insets(20, 18, 18, 18));
        summary.setPrefWidth(310);
        summary.setMinWidth(310);
        summary.setStyle(
                "-fx-background-color: #eee5df;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #E8E3E8;" +
                "-fx-border-radius: 12;"
        );

        DropShadow shadow = new DropShadow();
        shadow.setRadius(10);
        shadow.setOffsetY(3);
        shadow.setColor(Color.rgb(0, 0, 0, 0.06));
        summary.setEffect(shadow);

        Label title = new Label("Order Summary");
        title.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount));
        subtotalAmountLabel.setText(String.format("₹%.2f", subtotalAmount));

        HBox subtotalRow = summaryRowWithLabel(subtotalLabel, subtotalAmountLabel);
        HBox platform = summaryRow("Platform Fee", String.format("₹%.2f", platformFee));
        HBox tax1 = summaryRow("Estimated Tax", String.format("₹%.2f", tax));

        deliveryFeeValueLabel = new Label(deliveryFee == 0 ? "FREE" : String.format("₹%.2f", deliveryFee));
        HBox delivery = summaryRowWithLabel(new Label("Delivery Fee"), deliveryFeeValueLabel);

        // DISCOUNT ROW
        discountRowTitleLabel = new Label(CartOfferManager.getAppliedOffer() != null ? "Offer (" + CartOfferManager.getAppliedOffer().getPromoCode() + ")" : "Discount Offer");
        discountRowTitleLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #2E7D32; -fx-font-weight: bold;");

        discountRowAmountLabel = new Label(String.format("-₹%.2f", currentDiscountAmount));
        discountRowAmountLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #2E7D32; -fx-font-weight: bold;");

        Button removeDiscountBtn = new Button("✕");
        removeDiscountBtn.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #999999;" +
                "-fx-font-size: 9px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0 4 0 4;"
        );

        Region discSpacer = new Region();
        HBox.setHgrow(discSpacer, Priority.ALWAYS);
        discountRow = new HBox(4, discountRowTitleLabel, discSpacer, discountRowAmountLabel, removeDiscountBtn);
        discountRow.setAlignment(Pos.CENTER_LEFT);
        boolean hasActiveDiscount = currentDiscountAmount > 0;
        discountRow.setVisible(hasActiveDiscount);
        discountRow.setManaged(hasActiveDiscount);

        // OFFERS & PROMO CODE INPUT BOX
        VBox promoCard = new VBox(7);
        promoCard.setPadding(new Insets(10, 10, 10, 10));
        promoCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #E6E0D8;" +
                "-fx-border-radius: 8;"
        );

        HBox promoHeader = new HBox(5);
        promoHeader.setAlignment(Pos.CENTER_LEFT);
        Label promoBadge = new Label("🏷️ Have a Promo Code?");
        promoBadge.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        promoHeader.getChildren().add(promoBadge);

        TextField promoInput = new TextField();
        promoInputField = promoInput;
        promoInput.setPromptText("Enter code (e.g. GROCERY20)");
        promoInput.setStyle(
                "-fx-background-color: #F8F6F9;" +
                "-fx-background-radius: 6;" +
                "-fx-border-color: #D8D2DC;" +
                "-fx-border-radius: 6;" +
                "-fx-font-size: 10px;" +
                "-fx-padding: 5 8 5 8;"
        );
        HBox.setHgrow(promoInput, Priority.ALWAYS);

        Button applyPromoBtn = new Button("Apply");
        applyPromoBtn.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 6;" +
                "-fx-padding: 5 12 5 12;" +
                "-fx-cursor: hand;"
        );

        HBox promoInputRow = new HBox(6, promoInput, applyPromoBtn);
        promoInputRow.setAlignment(Pos.CENTER_LEFT);

        promoMessageLabel = new Label("");
        promoMessageLabel.setStyle("-fx-font-size: 9px; -fx-font-weight: bold;");
        promoMessageLabel.setWrapText(true);

        // Quick offers clickable chips
        Label availableLabel = new Label("Tap to apply offer:");
        availableLabel.setStyle("-fx-font-size: 8.5px; -fx-text-fill: #777777; -fx-font-weight: bold;");

        FlowPane quickOffersPane = new FlowPane();
        quickOffersPane.setHgap(5);
        quickOffersPane.setVgap(4);

        List<OfferModel> availList = CartOfferManager.getAvailableOffers();
        for (OfferModel om : availList) {
            if (om == null || om.getPromoCode() == null) continue;
            String code = om.getPromoCode();
            String discStr = (om.getDiscountType() != null && om.getDiscountType().toLowerCase().contains("percent"))
                    ? String.format("%.0f%%", om.getDiscountValue())
                    : String.format("₹%.0f", om.getDiscountValue());

            Button chip = new Button(code + " (" + discStr + ")");
            chip.setStyle(
                    "-fx-background-color: #FFF2E8;" +
                    "-fx-text-fill: #FF6900;" +
                    "-fx-font-size: 8px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-color: #FFC5A3;" +
                    "-fx-border-radius: 8;" +
                    "-fx-padding: 2 5 2 5;" +
                    "-fx-cursor: hand;"
            );
            chip.setOnAction(e -> {
                promoInput.setText(code);
                applyPromoBtn.fire();
            });
            quickOffersPane.getChildren().add(chip);
        }

        promoCard.getChildren().addAll(promoHeader, promoInputRow, promoMessageLabel, availableLabel, quickOffersPane);

        applyPromoBtn.setOnAction(e -> {
            String code = promoInput.getText();
            double curSub = (currentSubtotalRef != null && currentSubtotalRef.length > 0) ? currentSubtotalRef[0] : 0.0;
            CartOfferManager.OfferResult res = CartOfferManager.applyOffer(code, curSub, activeCartProducts);
            if (res.success) {
                currentDiscountAmount = res.discountAmount;
                promoMessageLabel.setText("✓ " + res.message);
                promoMessageLabel.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;");
                discountRowTitleLabel.setText("Discount (" + res.offer.getPromoCode() + ")");
                discountRowAmountLabel.setText(String.format("-₹%.2f", currentDiscountAmount));
                discountRow.setVisible(true);
                discountRow.setManaged(true);
                updateSummaryAmounts(currentSubtotalRef, currentSubtotalAmountLabel, currentTotalAmountLabel, deliveryFeeValueLabel);
            } else {
                promoMessageLabel.setText("⚠ " + res.message);
                promoMessageLabel.setStyle("-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #D32F2F;");
            }
        });

        removeDiscountBtn.setOnAction(e -> {
            CartOfferManager.clearAppliedOffer();
            CartOfferManager.clearPreappliedCode();
            currentDiscountAmount = 0.0;
            discountRow.setVisible(false);
            discountRow.setManaged(false);
            promoInput.setText("");
            promoMessageLabel.setText("");
            updateSummaryAmounts(currentSubtotalRef, currentSubtotalAmountLabel, currentTotalAmountLabel, deliveryFeeValueLabel);
        });

        // Check if offer was pre-applied from Deals
        String preapplied = CartOfferManager.getPreappliedCode();
        if (preapplied != null && !preapplied.isBlank()) {
            promoInput.setText(preapplied);
            Platform.runLater(applyPromoBtn::fire);
        } else if (CartOfferManager.getAppliedOffer() != null) {
            promoInput.setText(CartOfferManager.getAppliedOffer().getPromoCode());
            Platform.runLater(applyPromoBtn::fire);
        }

        Label progressText = new Label("Progress to Free Delivery");
        progressText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        HBox freeText = new HBox();
        freeDeliveryMoreLabel = new Label(
                subtotalAmount >= FREE_DELIVERY_THRESHOLD
                        ? "Free Delivery Unlocked"
                        : String.format("₹%.2f more", FREE_DELIVERY_THRESHOLD - subtotalAmount)
        );

        Label more = freeDeliveryMoreLabel;
        more.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #B44D00;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        freeText.getChildren().addAll(spacer, more);

        freeDeliveryProgress = new Region();
        freeDeliveryProgress.setPrefHeight(6);

        double initialProgressRatio = Math.min(subtotalAmount / FREE_DELIVERY_THRESHOLD, 1.0);
        double initialProgressWidth = 264.0 * initialProgressRatio;
        freeDeliveryProgress.setPrefWidth(initialProgressWidth);
        freeDeliveryProgress.setMaxWidth(initialProgressWidth);
        freeDeliveryProgress.setStyle(
                "-fx-background-color: #B94D00;" +
                "-fx-background-radius: 10;"
        );

        Region progress = freeDeliveryProgress;

        Separator separator = new Separator();

        HBox totalRow = new HBox();
        totalRow.setAlignment(Pos.CENTER_LEFT);

        Label total = new Label("Total");
        total.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Region totalSpacer = new Region();
        HBox.setHgrow(totalSpacer, Priority.ALWAYS);

        Label totalValue = new Label(String.format("₹%.2f", totalAmount));
        visibleTotalValueLabel = totalValue;
        totalValue.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #171717;"
        );

        totalRow.getChildren().addAll(total, totalSpacer, totalValue);

        Button checkout = new Button("Proceed to Checkout  →");
        checkout.setMaxWidth(Double.MAX_VALUE);
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
            if (activeCartProducts.isEmpty()) {
                System.out.println("Cart is empty.");
                return;
            }

            double currentSubtotal = 0.0;
            try {
                String text = subtotalAmountLabel.getText().replace("₹", "").trim();
                if (!text.isEmpty()) {
                    currentSubtotal = Double.parseDouble(text);
                }
            } catch (Exception ex) {
                currentSubtotal = 0.0;
            }

            if (currentSubtotal <= 0) {
                for (Productcart p : activeCartProducts) {
                    int q = p.getQuantity() > 0 ? p.getQuantity() : 1;
                    currentSubtotal += p.getPrice() * q;
                }
            }

            if (currentSubtotal <= 0) {
                System.out.println("Cart subtotal is 0.");
                return;
            }

            double currentPlatformFee = 10.0;
            double currentTax = currentSubtotal * 0.05;
            double currentDeliveryFee = currentSubtotal >= FREE_DELIVERY_THRESHOLD ? 0.0 : 30.0;
            double currentDiscount = currentDiscountAmount;

            double currentTotal = Math.max(0, currentSubtotal - currentDiscount) + currentPlatformFee + currentTax + currentDeliveryFee;

            final double finalTotal = currentTotal;
            final double finalSubtotal = currentSubtotal;
            final double finalPlatformFee = currentPlatformFee;
            final double finalTax = currentTax;
            final double finalDeliveryFee = currentDeliveryFee;
            final double finalDiscount = currentDiscount;
            final String finalPromoCode = (CartOfferManager.getAppliedOffer() != null) ? CartOfferManager.getAppliedOffer().getPromoCode() : "";
            final List<Productcart> purchasedItems = new ArrayList<>(activeCartProducts);

            PaymentController paymentController = new PaymentController();
            paymentController.startPayment(
                    finalTotal,
                    () -> handlePaymentSuccess(finalTotal, finalSubtotal, finalPlatformFee, finalTax, finalDeliveryFee, finalDiscount, finalPromoCode, purchasedItems)
            );
        });

        VBox aiTip = new VBox(5);
        aiTip.setPadding(new Insets(12));
        aiTip.setStyle(
                "-fx-background-color: #FFF5F0;" +
                "-fx-border-color: #F1D8CA;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );

        Label aiTitle = new Label("💡  AI Delivery Tip");
        aiTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );

        Label aiText = new Label(
                "Add more to your cart to unlock Free\n" +
                "Delivery at ₹99! Try adding the suggested Farm\n" +
                "Fresh Milk."
        );
        aiText.setWrapText(true);
        aiText.setStyle(
                "-fx-font-size: 9px;" +
                "-fx-text-fill: #555555;"
        );

        aiTip.getChildren().addAll(aiTitle, aiText);

        summary.getChildren().addAll(
                title,
                subtotalRow,
                platform,
                tax1,
                delivery,
                discountRow,
                promoCard,
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

    private HBox summaryRow(String left, String right) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        Label leftLabel = new Label(left);
        leftLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #555555;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label rightLabel = new Label(right);
        rightLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        row.getChildren().addAll(leftLabel, spacer, rightLabel);
        return row;
    }

    private HBox summaryRowWithLabel(Label leftLabel, Label rightLabel) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        leftLabel.setStyle("-fx-font-size: 10px; -fx-text-fill: #555555;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        rightLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        row.getChildren().addAll(leftLabel, spacer, rightLabel);
        return row;
    }

    private HBox createSuggestion(String name, String price, String imagePath) {
        HBox box = new HBox(8);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(10));
        box.setPrefSize(195, 74);
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #FF6900;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );

        StackPane image = createProductImage(imagePath);
        image.setPrefSize(48, 48);
        image.setMinSize(48, 48);
        image.setMaxSize(48, 48);

        VBox info = new VBox(2);
        Label product = new Label(name);
        product.setMaxWidth(95);
        product.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Label productPrice = new Label(price);
        productPrice.setStyle("-fx-font-size: 9px; -fx-text-fill: #555555;");

        info.getChildren().addAll(product, productPrice);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button add = new Button("+");
        add.setPrefSize(27, 27);
        add.setStyle(
                "-fx-background-color: #F4F0F4;" +
                "-fx-text-fill: #B94D00;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 50;" +
                "-fx-cursor: hand;"
        );

        box.getChildren().addAll(image, info, spacer, add);
        return box;
    }

    private VBox createCartPage() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(25, 30, 25, 30));
        page.setStyle("-fx-background-color: #F8F6FA;");

        VBox heading = new VBox(4);
        Label title = new Label("Your Shopping Cart");
        title.setStyle(
                "-fx-font-family: 'Montserrat';" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #151515;"
        );

        Label subtitle = new Label("0 items from local sellers");
        subtitle.setStyle("-fx-font-size: 11px; -fx-text-fill: #A44D20;");

        heading.getChildren().addAll(title, subtitle);

        products = new VBox(12);
        products.setPrefWidth(700);

        double[] subtotal = { 0.0 };
        int[] itemCount = { 0 };

        Label subtotalLabel = new Label("Subtotal (0 items)");
        Label subtotalAmountLabel = new Label("₹0.00");
        Label totalAmountLabel = new Label("₹0.00");

        if (userId == null || userId.isBlank()) {
            Label errorLabel = new Label("User not logged in.");
            errorLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #B44D00; -fx-font-weight: bold;");
            products.getChildren().add(errorLabel);
        } else {
            CARTcontroller cartController = new CARTcontroller();
            List<Productcart> cartList = cartController.getCart(userId);

            activeCartProducts.clear();

            if (cartList.isEmpty()) {
                Label emptyLabel = new Label("Your cart is empty.");
                emptyLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #777777; -fx-font-weight: bold;");
                products.getChildren().add(emptyLabel);
            } else {
                for (Productcart product : cartList) {
                    if (product == null) continue;

                    int cartQuantity = product.getQuantity() > 0 ? product.getQuantity() : 1;
                    product.setQuantity(cartQuantity);
                    activeCartProducts.add(product);

                    subtotal[0] += product.getPrice() * cartQuantity;
                    itemCount[0] += cartQuantity;

                    VBox productCard = createCartProduct(
                            product,
                            "",
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

        subtitle.setText(itemCount[0] + " items from local sellers");
        subtotalLabel.setText(String.format("Subtotal (%d items)", itemCount[0]));
        subtotalAmountLabel.setText(String.format("₹%.2f", subtotal[0]));

        VBox summary = createOrderSummary(
                subtotal,
                itemCount[0],
                subtotalLabel,
                subtotalAmountLabel,
                totalAmountLabel
        );

        HBox cartContent = new HBox(18);
        cartContent.setAlignment(Pos.TOP_LEFT);
        cartContent.getChildren().addAll(products, summary);

        VBox frequently = new VBox(10);
        Label frequentlyTitle = new Label("✦  Frequently Bought Together");
        frequentlyTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #222222;");

        HBox suggestions = new HBox(12);
        suggestions.getChildren().addAll(
                createSuggestion("Farm Fresh...", "₹4.20", "/assects/images/products/milk.png"),
                createSuggestion("Artisanal...", "₹6.50", "/assects/images/products/bread.png")
        );

        frequently.getChildren().addAll(frequentlyTitle, suggestions);

        page.getChildren().addAll(heading, cartContent, frequently);
        VBox.setVgrow(cartContent, Priority.ALWAYS);

        return page;
    }

    private void handlePaymentSuccess(
            double paidTotalAmount,
            double paidSubtotal,
            double paidPlatformFee,
            double paidTax,
            double paidDeliveryFee,
            double paidDiscount,
            String paidPromoCode,
            List<Productcart> purchasedItems) {

        System.out.println("PAYMENT SUCCESSFUL - CREATING ORDER");
        System.out.println("Paid Total: ₹" + paidTotalAmount + " (Discount: ₹" + paidDiscount + ", Promo: " + paidPromoCode + ")");

        Platform.runLater(() -> {
            try {
                if (userId == null || userId.trim().isEmpty()) {
                    System.out.println("ERROR: User ID is missing.");
                    return;
                }

                if (purchasedItems == null || purchasedItems.isEmpty()) {
                    System.out.println("ERROR: Cart is empty. Order was not created.");
                    return;
                }

                Firestore db = Firebaseconfig.gFirestore();
                String today = LocalDate.now().toString();

                List<Map<String, Object>> orderProducts = new ArrayList<>();
                String primaryShopkeeperUid = null;
                String primaryShopName = null;

                for (Productcart cartProduct : purchasedItems) {
                    if (cartProduct == null) continue;

                    String productName = cartProduct.getName();
                    if (productName == null || productName.trim().isEmpty()) continue;

                    int quantity = cartProduct.getQuantity() > 0 ? cartProduct.getQuantity() : 1;
                    double unitPrice = cartProduct.getPrice();
                    double itemTotal = unitPrice * quantity;

                    Map<String, Object> item = new HashMap<>();
                    String pId = (cartProduct.getProductId() != null && !cartProduct.getProductId().isBlank())
                            ? cartProduct.getProductId()
                            : productName;
                    item.put("productName", productName);
                    item.put("productId", pId);
                    item.put("quantity", quantity);
                    item.put("price", unitPrice);
                    item.put("unitPrice", unitPrice);
                    item.put("totalPrice", itemTotal);
                    item.put("shopName", cartProduct.getName1() != null ? cartProduct.getName1() : "BuyNex Store");
                    orderProducts.add(item);

                    String shopUid = cartProduct.getShopkeeperUid();
                    if (shopUid != null && !shopUid.isBlank() && !"default_shopkeeper".equalsIgnoreCase(shopUid) && !shopUid.equalsIgnoreCase(userId)) {
                        if (primaryShopkeeperUid == null) {
                            primaryShopkeeperUid = shopUid.trim();
                        }
                    }
                    if (primaryShopName == null && cartProduct.getName1() != null && !cartProduct.getName1().isBlank()) {
                        primaryShopName = cartProduct.getName1();
                    }
                }

                if (primaryShopkeeperUid == null) {
                    primaryShopkeeperUid = "default_shopkeeper";
                }
                if (primaryShopName == null) {
                    primaryShopName = "BuyNex Store";
                }

                var orderReference = db.collection("Orders").document();

                Map<String, Object> order = new HashMap<>();
                order.put("orderId", orderReference.getId());
                order.put("customerId", userId);
                order.put("customerName", userId);
                order.put("shopkeeperUid", primaryShopkeeperUid);
                order.put("shopName", primaryShopName);
                order.put("orderDate", today);
                order.put("orderStatus", "NEW");
                order.put("products", orderProducts);
                order.put("totalAmount", paidTotalAmount);
                order.put("subtotal", paidSubtotal);
                order.put("platformFee", paidPlatformFee);
                order.put("tax", paidTax);
                order.put("deliveryFee", paidDeliveryFee);
                order.put("discount", paidDiscount);
                order.put("promoCode", paidPromoCode != null ? paidPromoCode : "");
                order.put("paymentStatus", "PAID");

                orderReference.set(order).get();

                System.out.println("NEW ORDER CREATED SUCCESSFULLY");
                System.out.println("Order ID: " + orderReference.getId());

                CartOfferManager.clearAppliedOffer();
                CartOfferManager.clearPreappliedCode();
                currentDiscountAmount = 0.0;

                clearCart();
                new Thread(() -> new CARTcontroller().clearCart(userId)).start();

                My_orderAllorder myOrders = new My_orderAllorder(userId);
                Homepage.HomepageStage.setScene(myOrders.getAllorderScene());
                System.out.println("ORDER FLOW COMPLETED SUCCESSFULLY.");
            } catch (Exception e) {
                System.out.println("ERROR CREATING ORDER AFTER PAYMENT");
                e.printStackTrace();
            }
        });
    }

    private void handleSingleProductPaymentSuccess(
            Productcart product,
            int quantity,
            double paidTotalAmount,
            double paidSubtotal,
            double paidPlatformFee,
            double paidTax,
            double paidDeliveryFee,
            double paidDiscount,
            String paidPromoCode) {

        System.out.println("BUY NOW PAYMENT SUCCESSFUL");
        System.out.println("Paid Total: ₹" + paidTotalAmount + " (Discount: ₹" + paidDiscount + ")");

        Platform.runLater(() -> {
            try {
                if (userId == null || userId.trim().isEmpty()) {
                    System.out.println("ERROR: User ID is missing.");
                    return;
                }

                Firestore db = Firebaseconfig.gFirestore();
                String today = LocalDate.now().toString();

                List<Map<String, Object>> orderProducts = new ArrayList<>();
                double unitPrice = product.getPrice();
                double itemTotal = unitPrice * quantity;

                Map<String, Object> item = new HashMap<>();
                String pId = (product.getProductId() != null && !product.getProductId().isBlank())
                        ? product.getProductId()
                        : product.getName();
                item.put("productName", product.getName());
                item.put("productId", pId);
                item.put("quantity", quantity);
                item.put("price", unitPrice);
                item.put("unitPrice", unitPrice);
                item.put("totalPrice", itemTotal);
                item.put("shopName", product.getName1() != null && !product.getName1().isBlank() ? product.getName1() : "BuyNex Store");
                orderProducts.add(item);

                String shopUid = product.getShopkeeperUid();
                if (shopUid == null || shopUid.isBlank() || "default_shopkeeper".equalsIgnoreCase(shopUid) || shopUid.equalsIgnoreCase(userId)) {
                    shopUid = "default_shopkeeper";
                }

                var orderReference = db.collection("Orders").document();

                Map<String, Object> order = new HashMap<>();
                order.put("orderId", orderReference.getId());
                order.put("customerId", userId);
                order.put("customerName", userId);
                order.put("shopkeeperUid", shopUid);
                order.put("shopName", product.getName1() != null && !product.getName1().isBlank() ? product.getName1() : "BuyNex Store");
                order.put("orderDate", today);
                order.put("orderStatus", "NEW");
                order.put("products", orderProducts);
                order.put("totalAmount", paidTotalAmount);
                order.put("subtotal", paidSubtotal);
                order.put("platformFee", paidPlatformFee);
                order.put("tax", paidTax);
                order.put("deliveryFee", paidDeliveryFee);
                order.put("discount", paidDiscount);
                order.put("promoCode", paidPromoCode != null ? paidPromoCode : "");
                order.put("paymentStatus", "PAID");

                orderReference.set(order).get();

                System.out.println("BUY NOW ORDER CREATED SUCCESSFULLY");
                System.out.println("Order ID: " + orderReference.getId());

                CartOfferManager.clearAppliedOffer();
                CartOfferManager.clearPreappliedCode();
                currentDiscountAmount = 0.0;

                activeCartProducts.remove(product);
                new Thread(() -> new CARTcontroller().deleteFromCart(userId, product.getName())).start();

                My_orderAllorder myOrders = new My_orderAllorder(userId);
                Homepage.HomepageStage.setScene(myOrders.getAllorderScene());
            } catch (Exception e) {
                System.out.println("ERROR CREATING BUY NOW ORDER");
                e.printStackTrace();
            }
        });
    }

    private void clearCart() {
        activeCartProducts.clear();

        if (products != null) {
            products.getChildren().clear();
            Label emptyLabel = new Label("Your cart is empty.");
            emptyLabel.setStyle(
                    "-fx-font-size: 14px;" +
                    "-fx-text-fill: #777777;" +
                    "-fx-font-weight: bold;"
            );
            products.getChildren().add(emptyLabel);
        }

        if (visibleTotalValueLabel != null) {
            visibleTotalValueLabel.setText("₹0.00");
        }
        System.out.println("Cart cleared successfully.");
    }

    public void backTodashboard() {
        Homepage.HomepageStage.setScene(addcartScene);
    }
}

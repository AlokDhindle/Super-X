package com.kryox.view.Customer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShoppingCartUI extends Application {

    @Override
    public void start(Stage stage) {

        // =========================================================
        // ROOT VBOX
        // =========================================================
        VBox root = new VBox();

        root.setAlignment(Pos.TOP_LEFT);

        root.setFillWidth(true);

        root.setStyle(
        "-fx-background-color: #EEE5DF;"
);

        // =========================================================
        // MAIN HBOX
        // 3 VBOX
        // 1. LEFT SIDEBAR
        // 2. CENTER CART
        // 3. RIGHT SUMMARY
        // =========================================================
        HBox mainHBox = new HBox();

        mainHBox.setSpacing(0);

        mainHBox.setAlignment(Pos.TOP_LEFT);

        mainHBox.setMaxSize(
                Double.MAX_VALUE,
                Double.MAX_VALUE
        );


        // =========================================================
        // 1. LEFT SIDEBAR VBOX
        // =========================================================

        VBox leftVBox = new VBox();

        leftVBox.setPrefWidth(210);
        leftVBox.setMinWidth(210);
        leftVBox.setMaxWidth(210);

        leftVBox.setMaxHeight(Double.MAX_VALUE);

        leftVBox.setPadding(
                new Insets(45, 18, 20, 18)
        );

        leftVBox.setSpacing(10);

       leftVBox.setStyle(
        "-fx-background-color: #EBCBB7;" +
        "-fx-border-color: #D8B29C;" +
        "-fx-border-width: 0 1 0 0;"
);


        // =========================================================
        // BUY NEX LOGO
        // =========================================================

        Text buyNex = new Text("BuyNeX");

        buyNex.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        buyNex.setFill(
                Color.web("#AD4508")
        );


        Text marketplace = new Text(
                "Hyperlocal Marketplace"
        );

        marketplace.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        9
                )
        );

        marketplace.setFill(
                Color.web("#332A27")
        );


        VBox logoVBox = new VBox();

        logoVBox.setSpacing(2);

        logoVBox.getChildren().addAll(
                buyNex,
                marketplace
        );


        // =========================================================
        // SPACE
        // =========================================================

        Region sideSpace = new Region();

        sideSpace.setPrefHeight(38);


        // =========================================================
        // DASHBOARD
        // =========================================================

        HBox dashboardHBox = new HBox();

        dashboardHBox.setPrefHeight(36);

        dashboardHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        dashboardHBox.setSpacing(13);

        dashboardHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text dashboardIcon = new Text("▦");

        dashboardIcon.setFont(
                Font.font("Arial", 18)
        );

        dashboardIcon.setFill(
                Color.web("#443A36")
        );


        Text dashboardText = new Text(
                "Dashboard"
        );

        dashboardText.setFont(
                Font.font("Arial", 11)
        );

        dashboardText.setFill(
                Color.web("#443A36")
        );


        dashboardHBox.getChildren().addAll(
                dashboardIcon,
                dashboardText
        );


        // =========================================================
        // CATEGORIES
        // =========================================================

        HBox categoriesHBox = new HBox();

        categoriesHBox.setPrefHeight(36);

        categoriesHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        categoriesHBox.setSpacing(13);

        categoriesHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text categoriesIcon = new Text("△");

        categoriesIcon.setFont(
                Font.font("Arial", 17)
        );

        categoriesIcon.setFill(
                Color.web("#443A36")
        );


        Text categoriesText = new Text(
                "Categories"
        );

        categoriesText.setFont(
                Font.font("Arial", 11)
        );

        categoriesText.setFill(
                Color.web("#443A36")
        );


        categoriesHBox.getChildren().addAll(
                categoriesIcon,
                categoriesText
        );


        // =========================================================
        // DEALS
        // =========================================================

        HBox dealsHBox = new HBox();

        dealsHBox.setPrefHeight(36);

        dealsHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        dealsHBox.setSpacing(13);

        dealsHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text dealsIcon = new Text("◇");

        dealsIcon.setFont(
                Font.font("Arial", 19)
        );

        dealsIcon.setFill(
                Color.web("#443A36")
        );


        Text dealsText = new Text("Deals");

        dealsText.setFont(
                Font.font("Arial", 11)
        );

        dealsText.setFill(
                Color.web("#443A36")
        );


        dealsHBox.getChildren().addAll(
                dealsIcon,
                dealsText
        );


        // =========================================================
        // MY ORDERS
        // =========================================================

        HBox ordersHBox = new HBox();

        ordersHBox.setPrefHeight(34);

        ordersHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        ordersHBox.setSpacing(13);

        ordersHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );

        ordersHBox.setStyle(
                "-fx-background-color: #FF7109;" +
                "-fx-background-radius: 7;"
        );


        Text ordersIcon = new Text("♧");

        ordersIcon.setFont(
                Font.font("Arial", 18)
        );

        ordersIcon.setFill(
                Color.web("#332A27")
        );


        Text ordersText = new Text(
                "My Orders"
        );

        ordersText.setFont(
                Font.font("Arial", 11)
        );

        ordersText.setFill(
                Color.web("#332A27")
        );


        ordersHBox.getChildren().addAll(
                ordersIcon,
                ordersText
        );


        // =========================================================
        // ANALYTICS
        // =========================================================

        HBox analyticsHBox = new HBox();

        analyticsHBox.setPrefHeight(36);

        analyticsHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        analyticsHBox.setSpacing(13);

        analyticsHBox.setPadding(
                new Insets(0, 10, 0, 10)
        );


        Text analyticsIcon = new Text("▣");

        analyticsIcon.setFont(
                Font.font("Arial", 16)
        );

        analyticsIcon.setFill(
                Color.web("#443A36")
        );


        Text analyticsText = new Text(
                "Analytics"
        );

        analyticsText.setFont(
                Font.font("Arial", 11)
        );

        analyticsText.setFill(
                Color.web("#443A36")
        );


        analyticsHBox.getChildren().addAll(
                analyticsIcon,
                analyticsText
        );


        // ADD ALL SIDEBAR ITEMS

        leftVBox.getChildren().addAll(
                logoVBox,
                sideSpace,
                dashboardHBox,
                categoriesHBox,
                dealsHBox,
                ordersHBox,
                analyticsHBox
        );


        // =========================================================
        // 2. CENTER VBOX
        // =========================================================

        VBox centerVBox = new VBox();

        centerVBox.setPrefWidth(530);
        centerVBox.setMinWidth(530);

        centerVBox.setMaxWidth(Double.MAX_VALUE);
        centerVBox.setMaxHeight(Double.MAX_VALUE);
        


        HBox.setHgrow(
                centerVBox,
                Priority.ALWAYS
        );

        centerVBox.setPadding(
    new Insets(50, 20, 25, 50)
);

        centerVBox.setSpacing(0);


        // =========================================================
        // PAGE TITLE
        // =========================================================

        Text cartTitle = new Text(
                "Your Shopping Cart"
        );

        cartTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );

        cartTitle.setFill(
                Color.web("#171315")
        );


        Text cartSubtitle = new Text(
                "3 items from local sellers"
        );

        cartSubtitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        12
                )
        );

        cartSubtitle.setFill(
                Color.web("#594C48")
        );


        VBox titleVBox = new VBox();

        titleVBox.setSpacing(7);

        titleVBox.getChildren().addAll(
                cartTitle,
                cartSubtitle
        );


        // =========================================================
        // IMAGE 1
        // ARTISANAL SOURDOUGH
        // =========================================================

        Image breadImage = new Image(
                "assets//images//artisanal_sourdough.png"
        );

        ImageView breadImageView = new ImageView(
                breadImage
        );

        breadImageView.setFitWidth(66);

        breadImageView.setFitHeight(66);

        breadImageView.setPreserveRatio(false);

        breadImageView.setSmooth(true);


        // =========================================================
        // PRODUCT CARD 1
        // =========================================================

        VBox productCard1 = new VBox();

        productCard1.setMaxWidth(Double.MAX_VALUE);
        productCard1.setPrefWidth(430);

        HBox.setHgrow(productCard1, Priority.ALWAYS);
        productCard1.setPrefHeight(165);

        productCard1.setPadding(
                new Insets(17)
        );

        productCard1.setSpacing(6);

        productCard1.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 6;" +
                "-fx-border-color: #E6E0DC;" +
                "-fx-border-radius: 6;"
        );


        HBox productTop1 = new HBox();

        productTop1.setSpacing(15);


        // IMAGE BOX

        VBox imageBox1 = new VBox();

        imageBox1.setPrefSize(66, 66);

        imageBox1.setMinSize(66, 66);

        imageBox1.setMaxSize(66, 66);

        imageBox1.setAlignment(
                Pos.CENTER
        );

        imageBox1.setStyle(
                "-fx-background-color: #E8E8E4;" +
                "-fx-background-radius: 4;"
        );


        imageBox1.getChildren().add(
                breadImageView
        );


        // PRODUCT INFORMATION

        VBox productInfo1 = new VBox();

        productInfo1.setSpacing(3);


        Text productName1 = new Text(
                "Artisanal Sourdough Loaf"
        );

        productName1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );


        Text seller1 = new Text(
                "From BakeHouse Local"
        );

        seller1.setFont(
                Font.font("Arial", 9)
        );

        seller1.setFill(
                Color.web("#A64008")
        );


        // QUANTITY

        HBox quantityHBox1 = new HBox();

        quantityHBox1.setPrefSize(78, 28);

        quantityHBox1.setAlignment(
                Pos.CENTER
        );

        quantityHBox1.setSpacing(14);

        quantityHBox1.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 18;"
        );


        Text minus1 = new Text("−");

        minus1.setFont(
                Font.font("Arial", 14)
        );


        Text quantity1 = new Text("2");

        quantity1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        Text plus1 = new Text("+");

        plus1.setFont(
                Font.font("Arial", 14)
        );


        quantityHBox1.getChildren().addAll(
                minus1,
                quantity1,
                plus1
        );


        productInfo1.getChildren().addAll(
                productName1,
                seller1,
                quantityHBox1
        );


        Region cardSpace1 = new Region();

        HBox.setHgrow(
                cardSpace1,
                Priority.ALWAYS
        );


        // PRICE

        VBox priceVBox1 = new VBox();

        priceVBox1.setAlignment(
                Pos.CENTER_RIGHT
        );

        priceVBox1.setSpacing(1);


        Text delete1 = new Text("♜");

        delete1.setFont(
                Font.font("Arial", 14)
        );

        delete1.setFill(
                Color.web("#594C48")
        );


        Text price1 = new Text(
                "$12.00"
        );

        price1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );


        Text each1 = new Text(
                "$6.00 / ea"
        );

        each1.setFont(
                Font.font("Arial", 9)
        );

        each1.setFill(
                Color.web("#594C48")
        );


        priceVBox1.getChildren().addAll(
                delete1,
                price1,
                each1
        );


        productTop1.getChildren().addAll(
                imageBox1,
                productInfo1,
                cardSpace1,
                priceVBox1
        );


        // =========================================================
        // BUTTONS CARD 1
        // =========================================================

        HBox buttonHBox1 = new HBox();

        buttonHBox1.setSpacing(8);

        buttonHBox1.setAlignment(
                Pos.CENTER_RIGHT
        );


        Button buyButton1 = new Button(
                "Buy Now"
        );

        buyButton1.setPrefSize(70, 28);

        buyButton1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        buyButton1.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;"
        );


        Button bookButton1 = new Button(
                "Book Product"
        );

        bookButton1.setPrefSize(96, 28);

        bookButton1.setFont(
                Font.font("Arial", 10)
        );

        bookButton1.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-text-fill: #302A28;" +
                "-fx-background-radius: 5;"
        );


        Button visitButton1 = new Button(
                "Visit Shop"
        );

        visitButton1.setPrefSize(77, 28);

        visitButton1.setFont(
                Font.font("Arial", 10)
        );

        visitButton1.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #9E8D84;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );


        buttonHBox1.getChildren().addAll(
                buyButton1,
                bookButton1,
                visitButton1
        );


        productCard1.getChildren().addAll(
                productTop1,
                buttonHBox1
        );


        // =========================================================
        // IMAGE 2
        // AVOCADO
        // =========================================================

        Image avocadoImage = new Image(
                "assets//images//avocado.png"
        );

        ImageView avocadoImageView = new ImageView(
                avocadoImage
        );

        avocadoImageView.setFitWidth(66);

        avocadoImageView.setFitHeight(66);

        avocadoImageView.setPreserveRatio(false);

        avocadoImageView.setSmooth(true);


        // =========================================================
        // PRODUCT CARD 2
        // =========================================================

        VBox productCard2 = new VBox();

        productCard2.setMaxWidth(Double.MAX_VALUE);
        productCard2.setPrefWidth(430);

        HBox.setHgrow(productCard2, Priority.ALWAYS);

        productCard2.setPrefHeight(159);

        productCard2.setPadding(
                new Insets(17)
        );

        productCard2.setSpacing(6);

        productCard2.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 6;" +
                "-fx-border-color: #E6E0DC;" +
                "-fx-border-radius: 6;"
        );


        HBox productTop2 = new HBox();

        productTop2.setSpacing(15);


        VBox imageBox2 = new VBox();

        imageBox2.setPrefSize(66, 66);

        imageBox2.setMinSize(66, 66);

        imageBox2.setMaxSize(66, 66);

        imageBox2.setAlignment(
                Pos.CENTER
        );

        imageBox2.setStyle(
                "-fx-background-color: #E5E8E4;" +
                "-fx-background-radius: 4;"
        );


        imageBox2.getChildren().add(
                avocadoImageView
        );


        VBox productInfo2 = new VBox();

        productInfo2.setSpacing(3);


        Text productName2 = new Text(
                "Organic Hass Avocados (Pack of 3)"
        );

        productName2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );


        Text seller2 = new Text(
                "From Green Grocer"
        );

        seller2.setFont(
                Font.font("Arial", 9)
        );

        seller2.setFill(
                Color.web("#3E9C54")
        );


        HBox quantityHBox2 = new HBox();

        quantityHBox2.setPrefSize(78, 28);

        quantityHBox2.setAlignment(
                Pos.CENTER
        );

        quantityHBox2.setSpacing(14);

        quantityHBox2.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 18;"
        );


        Text minus2 = new Text("−");

        minus2.setFont(
                Font.font("Arial", 14)
        );


        Text quantity2 = new Text("1");

        quantity2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );


        Text plus2 = new Text("+");

        plus2.setFont(
                Font.font("Arial", 14)
        );


        quantityHBox2.getChildren().addAll(
                minus2,
                quantity2,
                plus2
        );


        productInfo2.getChildren().addAll(
                productName2,
                seller2,
                quantityHBox2
        );


        Region cardSpace2 = new Region();

        HBox.setHgrow(
                cardSpace2,
                Priority.ALWAYS
        );


        VBox priceVBox2 = new VBox();

        priceVBox2.setAlignment(
                Pos.CENTER_RIGHT
        );

        priceVBox2.setSpacing(5);


        Text delete2 = new Text("♜");

        delete2.setFont(
                Font.font("Arial", 14)
        );

        delete2.setFill(
                Color.web("#594C48")
        );


        Text price2 = new Text(
                "$5.50"
        );

        price2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        17
                )
        );


        priceVBox2.getChildren().addAll(
                delete2,
                price2
        );


        productTop2.getChildren().addAll(
                imageBox2,
                productInfo2,
                cardSpace2,
                priceVBox2
        );


        // =========================================================
        // BUTTONS CARD 2
        // =========================================================

        HBox buttonHBox2 = new HBox();

        buttonHBox2.setSpacing(8);

        buttonHBox2.setAlignment(
                Pos.CENTER_RIGHT
        );


        Button buyButton2 = new Button(
                "Buy Now"
        );

        buyButton2.setPrefSize(70, 28);

        buyButton2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        buyButton2.setStyle(
                "-fx-background-color: #A94005;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 5;"
        );


        Button bookButton2 = new Button(
                "Book Product"
        );

        bookButton2.setPrefSize(96, 28);

        bookButton2.setFont(
                Font.font("Arial", 10)
        );

        bookButton2.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-text-fill: #302A28;" +
                "-fx-background-radius: 5;"
        );


        Button visitButton2 = new Button(
                "Visit Shop"
        );

        visitButton2.setPrefSize(77, 28);

        visitButton2.setFont(
                Font.font("Arial", 10)
        );

        visitButton2.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #9E8D84;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );


        buttonHBox2.getChildren().addAll(
                buyButton2,
                bookButton2,
                visitButton2
        );


        productCard2.getChildren().addAll(
                productTop2,
                buttonHBox2
        );


        // =========================================================
        // FREQUENTLY BOUGHT TOGETHER
        // =========================================================

        VBox frequentlyVBox = new VBox();

        frequentlyVBox.setSpacing(15);

        frequentlyVBox.setPadding(
                new Insets(55, 0, 0, 0)
        );


        // TITLE

        HBox frequentlyTitleHBox = new HBox();

        frequentlyTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );

        frequentlyTitleHBox.setSpacing(7);


        Text sparkle = new Text("✣");

        sparkle.setFont(
                Font.font("Arial", 17)
        );

        sparkle.setFill(
                Color.web("#A94005")
        );


        Text frequentlyTitle = new Text(
                "Frequently Bought Together"
        );

        frequentlyTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        frequentlyTitle.setFill(
                Color.web("#201B19")
        );


        frequentlyTitleHBox.getChildren().addAll(
                sparkle,
                frequentlyTitle
        );


        // =========================================================
        // IMAGE 3
        // FARM FRESH
        // =========================================================

        Image farmFreshImage = new Image(
                "assets//images//image1.png"
        );

        ImageView farmFreshImageView = new ImageView(
                farmFreshImage
        );

        farmFreshImageView.setFitWidth(45);

        farmFreshImageView.setFitHeight(45);

        farmFreshImageView.setPreserveRatio(false);

        farmFreshImageView.setSmooth(true);


        // =========================================================
        // IMAGE 4
        // ARTISANAL SMALL
        // =========================================================

        Image artisanalSmallImage = new Image(
                "assets//images//image2.png"
        );

        ImageView artisanalSmallImageView =
                new ImageView(
                        artisanalSmallImage
                );

        artisanalSmallImageView.setFitWidth(45);

        artisanalSmallImageView.setFitHeight(45);

        artisanalSmallImageView.setPreserveRatio(false);

        artisanalSmallImageView.setSmooth(true);


        // =========================================================
        // FREQUENTLY BOUGHT PRODUCTS HBOX
        // =========================================================

        HBox frequentlyProductsHBox = new HBox();

        frequentlyProductsHBox.setSpacing(12);


        // =========================================================
        // FREQUENTLY CARD 1
        // =========================================================

        VBox frequentlyCard1 = new VBox();

        frequentlyCard1.setPrefSize(220, 75);

        frequentlyCard1.setPadding(
                new Insets(10)
        );

        frequentlyCard1.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #FF720A;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );


        HBox frequentlyInner1 = new HBox();

        frequentlyInner1.setAlignment(
                Pos.CENTER_LEFT
        );

        frequentlyInner1.setSpacing(9);


        VBox smallImage1 = new VBox();

        smallImage1.setPrefSize(45, 45);

        smallImage1.setMinSize(45, 45);

        smallImage1.setMaxSize(45, 45);

        smallImage1.setAlignment(
                Pos.CENTER
        );

        smallImage1.setStyle(
                "-fx-background-color: #E7E9E5;" +
                "-fx-background-radius: 3;"
        );


        smallImage1.getChildren().add(
                farmFreshImageView
        );


        VBox smallInfo1 = new VBox();

        smallInfo1.setSpacing(3);


        Text smallName1 = new Text(
                "Farm Fresh..."
        );

        smallName1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );


        Text smallPrice1 = new Text(
                "$4.20"
        );

        smallPrice1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );


        smallInfo1.getChildren().addAll(
                smallName1,
                smallPrice1
        );


        Region smallSpace1 = new Region();

        HBox.setHgrow(
                smallSpace1,
                Priority.ALWAYS
        );


        VBox addCircle1 = new VBox();

        addCircle1.setPrefSize(23, 23);

        addCircle1.setMinSize(23, 23);

        addCircle1.setMaxSize(23, 23);

        addCircle1.setAlignment(
                Pos.CENTER
        );

        addCircle1.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 20;"
        );


        Text add1 = new Text("+");

        add1.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        add1.setFill(
                Color.web("#A94005")
        );


        addCircle1.getChildren().add(
                add1
        );


        frequentlyInner1.getChildren().addAll(
                smallImage1,
                smallInfo1,
                smallSpace1,
                addCircle1
        );


        frequentlyCard1.getChildren().add(
                frequentlyInner1
        );


        // =========================================================
        // FREQUENTLY CARD 2
        // =========================================================

        VBox frequentlyCard2 = new VBox();

        frequentlyCard2.setPrefSize(220, 75);

        frequentlyCard2.setPadding(
                new Insets(10)
        );

        frequentlyCard2.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #FF720A;" +
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;"
        );


        HBox frequentlyInner2 = new HBox();

        frequentlyInner2.setAlignment(
                Pos.CENTER_LEFT
        );

        frequentlyInner2.setSpacing(9);


        VBox smallImage2 = new VBox();

        smallImage2.setPrefSize(45, 45);

        smallImage2.setMinSize(45, 45);

        smallImage2.setMaxSize(45, 45);

        smallImage2.setAlignment(
                Pos.CENTER
        );

        smallImage2.setStyle(
                "-fx-background-color: #E7E5DF;" +
                "-fx-background-radius: 3;"
        );


        smallImage2.getChildren().add(
                artisanalSmallImageView
        );


        VBox smallInfo2 = new VBox();

        smallInfo2.setSpacing(3);


        Text smallName2 = new Text(
                "Artisanal..."
        );

        smallName2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );


        Text smallPrice2 = new Text(
                "$6.50"
        );

        smallPrice2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );


        smallInfo2.getChildren().addAll(
                smallName2,
                smallPrice2
        );


        Region smallSpace2 = new Region();

        HBox.setHgrow(
                smallSpace2,
                Priority.ALWAYS
        );


        VBox addCircle2 = new VBox();

        addCircle2.setPrefSize(23, 23);

        addCircle2.setMinSize(23, 23);

        addCircle2.setMaxSize(23, 23);

        addCircle2.setAlignment(
                Pos.CENTER
        );

        addCircle2.setStyle(
                "-fx-background-color: #F0EDF2;" +
                "-fx-background-radius: 20;"
        );


        Text add2 = new Text("+");

        add2.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        add2.setFill(
                Color.web("#A94005")
        );


        addCircle2.getChildren().add(
                add2
        );


        frequentlyInner2.getChildren().addAll(
                smallImage2,
                smallInfo2,
                smallSpace2,
                addCircle2
        );


        frequentlyCard2.getChildren().add(
                frequentlyInner2
        );


        // ADD BOTH FREQUENTLY CARDS

        frequentlyProductsHBox.getChildren().addAll(
                frequentlyCard1,
                frequentlyCard2
        );


        frequentlyVBox.getChildren().addAll(
                frequentlyTitleHBox,
                frequentlyProductsHBox
        );


        // =========================================================
        // CENTER VBOX ADD
        // =========================================================

        Region centerGap = new Region();

        centerGap.setPrefHeight(28);


        centerVBox.getChildren().addAll(
                titleVBox,
                centerGap,
                productCard1,
                productCard2,
                frequentlyVBox
        );


        // =========================================================
        // 3. RIGHT ORDER SUMMARY VBOX
        // =========================================================

        VBox rightVBox = new VBox();

        rightVBox.setPrefWidth(310);
        rightVBox.setMinWidth(310);
        rightVBox.setMaxWidth(310);

        rightVBox.setMaxHeight(Double.MAX_VALUE);

        rightVBox.setPadding(
                new Insets(17)
        );

        rightVBox.setSpacing(14);

        rightVBox.setStyle(
        "-fx-background-color: #EBCBB7;" +
        "-fx-background-radius: 6;" +
        "-fx-border-color: #D8B29C;" +
        "-fx-border-radius: 6;"
);

        // =========================================================
        // SUMMARY TITLE
        // =========================================================

        Text summaryTitle = new Text(
                "Order Summary"
        );

        summaryTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );

        summaryTitle.setFill(
                Color.web("#1D1816")
        );


        // =========================================================
        // SUBTOTAL
        // =========================================================

        HBox subtotalHBox = new HBox();

        subtotalHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text subtotalText = new Text(
                "Subtotal (3 items)"
        );

        subtotalText.setFont(
                Font.font("Arial", 11)
        );

        subtotalText.setFill(
                Color.web("#594C48")
        );


        Region subtotalSpace = new Region();

        HBox.setHgrow(
                subtotalSpace,
                Priority.ALWAYS
        );


        Text subtotalValue = new Text(
                "$17.50"
        );

        subtotalValue.setFont(
                Font.font("Arial", 11)
        );


        subtotalHBox.getChildren().addAll(
                subtotalText,
                subtotalSpace,
                subtotalValue
        );


        // =========================================================
        // PLATFORM FEE
        // =========================================================

        HBox platformHBox = new HBox();


        Text platformText = new Text(
                "Platform Fee"
        );

        platformText.setFont(
                Font.font("Arial", 11)
        );

        platformText.setFill(
                Color.web("#594C48")
        );


        Region platformSpace = new Region();

        HBox.setHgrow(
                platformSpace,
                Priority.ALWAYS
        );


        Text platformValue = new Text(
                "$1.00"
        );

        platformValue.setFont(
                Font.font("Arial", 11)
        );


        platformHBox.getChildren().addAll(
                platformText,
                platformSpace,
                platformValue
        );


        // =========================================================
        // TAX
        // =========================================================

        HBox taxHBox = new HBox();


        Text taxText = new Text(
                "Estimated Tax"
        );

        taxText.setFont(
                Font.font("Arial", 11)
        );

        taxText.setFill(
                Color.web("#594C48")
        );


        Region taxSpace = new Region();

        HBox.setHgrow(
                taxSpace,
                Priority.ALWAYS
        );


        Text taxValue = new Text(
                "$1.40"
        );

        taxValue.setFont(
                Font.font("Arial", 11)
        );


        taxHBox.getChildren().addAll(
                taxText,
                taxSpace,
                taxValue
        );


        // =========================================================
        // DELIVERY FEE
        // =========================================================

        HBox deliveryHBox = new HBox();


        Text deliveryText = new Text(
                "Delivery Fee"
        );

        deliveryText.setFont(
                Font.font("Arial", 11)
        );

        deliveryText.setFill(
                Color.web("#594C48")
        );


        Region deliverySpace = new Region();

        HBox.setHgrow(
                deliverySpace,
                Priority.ALWAYS
        );


        Text deliveryValue = new Text(
                "$3.50"
        );

        deliveryValue.setFont(
                Font.font("Arial", 11)
        );


        deliveryHBox.getChildren().addAll(
                deliveryText,
                deliverySpace,
                deliveryValue
        );


        // =========================================================
        // FREE DELIVERY PROGRESS
        // =========================================================

        VBox progressVBox = new VBox();

        progressVBox.setSpacing(5);


        HBox progressTextHBox = new HBox();


        Text progressText = new Text(
                "Progress to Free Delivery"
        );

        progressText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );


        Region progressSpace = new Region();

        HBox.setHgrow(
                progressSpace,
                Priority.ALWAYS
        );


        Text progressValue = new Text(
                "$7.50 more"
        );

        progressValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        9
                )
        );

        progressValue.setFill(
                Color.web("#A94005")
        );


        progressTextHBox.getChildren().addAll(
                progressText,
                progressSpace,
                progressValue
        );


        Rectangle progressBackground =
                new Rectangle(230, 5);

        progressBackground.setArcWidth(8);

        progressBackground.setArcHeight(8);

        progressBackground.setFill(
                Color.web("#EEE8E5")
        );


        Rectangle progress =
                new Rectangle(163, 5);

        progress.setArcWidth(8);

        progress.setArcHeight(8);

        progress.setFill(
                Color.web("#A94005")
        );


        HBox progressBarHBox = new HBox();

        progressBarHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        progressBarHBox.getChildren().add(
                progressBackground
        );

        progressBarHBox.getChildren().add(
                progress
        );


        progressVBox.getChildren().addAll(
                progressTextHBox,
                progressBarHBox
        );


        // =========================================================
        // TOTAL
        // =========================================================

        HBox totalHBox = new HBox();

        totalHBox.setPadding(
                new Insets(12, 0, 8, 0)
        );


        Text totalText = new Text(
                "Total"
        );

        totalText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );


        Region totalSpace = new Region();

        HBox.setHgrow(
                totalSpace,
                Priority.ALWAYS
        );


        Text totalValue = new Text(
                "$23.40"
        );

        totalValue.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        21
                )
        );


        totalHBox.getChildren().addAll(
                totalText,
                totalSpace,
                totalValue
        );


        // =========================================================
        // CHECKOUT BUTTON
        // =========================================================

        Button checkoutButton = new Button(
                "Proceed to Checkout  →"
        );

        checkoutButton.setPrefSize(
                230,
                36
        );

        checkoutButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        11
                )
        );

        checkoutButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF7109, #FFB08D);" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 6;"
        );


        // =========================================================
        // DELIVERY TIP
        // =========================================================

        VBox deliveryTipVBox = new VBox();

        deliveryTipVBox.setSpacing(6);

        deliveryTipVBox.setPadding(
                new Insets(10)
        );

        deliveryTipVBox.setStyle(
                "-fx-background-color: #FFF5F0;" +
                "-fx-border-color: #E9D6CB;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;"
        );


        HBox tipTitleHBox = new HBox();

        tipTitleHBox.setSpacing(7);

        tipTitleHBox.setAlignment(
                Pos.CENTER_LEFT
        );


        Text tipIcon = new Text("♧");

        tipIcon.setFont(
                Font.font("Arial", 15)
        );

        tipIcon.setFill(
                Color.web("#A94005")
        );


        Text tipTitle = new Text(
                "AI Delivery Tip"
        );

        tipTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );


        tipTitleHBox.getChildren().addAll(
                tipIcon,
                tipTitle
        );


        Text tipText = new Text(
                "Add $7.50 more to your cart to unlock Free\n" +
                "Delivery. Try adding the suggested Farm\n" +
                "Fresh Milk."
        );

        tipText.setFont(
                Font.font("Arial", 8)
        );

        tipText.setFill(
                Color.web("#594C48")
        );


        deliveryTipVBox.getChildren().addAll(
                tipTitleHBox,
                tipText
        );


        // =========================================================
        // ADD RIGHT SIDE COMPONENTS
        // =========================================================

        rightVBox.getChildren().addAll(
                summaryTitle,
                subtotalHBox,
                platformHBox,
                taxHBox,
                deliveryHBox,
                progressVBox,
                totalHBox,
                checkoutButton,
                deliveryTipVBox
        );


        // =========================================================
        // ADD 3 VBOX INTO MAIN HBOX
        // =========================================================

        mainHBox.getChildren().addAll(
                leftVBox,
                centerVBox,
                rightVBox
        );
        
        

        // =========================================================
        // ROOT
        // =========================================================

        root.getChildren().add(
                mainHBox
        );
        VBox.setVgrow(
        mainHBox,
        Priority.ALWAYS
);

        // =========================================================
        // SCENE
        // =========================================================

        Scene scene = new Scene(root);

        stage.setTitle(
                "BuyNeX - Shopping Cart"
        );

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
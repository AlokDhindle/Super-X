package com.kryox.view.Shopkeeper;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Text;
public class MainPage {

    private static Scene mainPageScene;

    public static Scene mainPageScene() {

        // =========================================================
        // MAIN BORDERPANE
        // =========================================================

        BorderPane borderPane = new BorderPane();

        borderPane.setStyle(
                "-fx-background-color: #FAF8FC;"
        );


        // =========================================================
        // HEADER
        // =========================================================

        HBox header = createHeader();

        borderPane.setTop(header);


        // =========================================================
        // CENTER CONTENT
        // =========================================================

        VBox centerContent = new VBox();

        centerContent.setAlignment(
                Pos.TOP_CENTER
        );

        centerContent.setSpacing(0);

        centerContent.setPadding(
                new Insets(
                        20,
                        50,
                        8,
                        50
                )
        );


        // =========================================================
        // WELCOME TITLE
        // =========================================================

        HBox welcomeTitle = new HBox();

        welcomeTitle.setAlignment(
                Pos.CENTER
        );


        Label welcomeText =
                new Label("Welcome back to ");

        welcomeText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        welcomeText.setTextFill(
                Color.web("#161616")
        );


        Label buyNexText =
                new Label("BuyNeX");

        buyNexText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        28
                )
        );

        buyNexText.setTextFill(
                Color.web("#C65318")
        );


        welcomeTitle.getChildren().addAll(
                welcomeText,
                buyNexText
        );


        // =========================================================
        // DESCRIPTION
        // =========================================================

        Label description =
                new Label(
                        "Our hyperlocal ecosystem connects intelligence with speed. Please select\n" +
                        "your specific portal to continue your journey."
                );

        description.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        description.setTextFill(
                Color.web("#65483E")
        );

        description.setTextAlignment(
                TextAlignment.CENTER
        );

        description.setAlignment(
                Pos.CENTER
        );

        description.setPadding(
                new Insets(
                        3,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // ROLE GRID
        // =========================================================

        GridPane roleGrid =
                new GridPane();

        roleGrid.setAlignment(
                Pos.CENTER
        );

        roleGrid.setHgap(20);

        roleGrid.setVgap(18);


        // =========================================================
        // SHOPKEEPER CARD
        // =========================================================

        VBox shopkeeperCard =
                createRoleCard(
                        "🏪",
                        "Shopkeeper",
                        "Manage your inventory, process local orders, and\n" +
                        "grow your retail business with AI insights.",
                        "#FFD8C8",
                        "#A9460D"
                );


        // =========================================================
        // CUSTOMER CARD
        // =========================================================

        VBox customerCard =
                createRoleCard(
                        "🛍",
                        "Customer",
                        "Explore nearby shops, get lightning-fast delivery,\n" +
                        "and enjoy a premium hyperlocal experience.",
                        "#E5E3E3",
                        "#626262"
                );


        // =========================================================
        // ADMIN CARD
        // =========================================================

        VBox adminCard =
                createRoleCard(
                        "🛡",
                        "Admin",
                        "Monitor system health, manage platform users, and\n" +
                        "oversee the entire BuyNeX operations grid.",
                        "#E8E8E8",
                        "#656565"
                );


        // =========================================================
        // DELIVERY CARD
        // =========================================================

        VBox deliveryCard =
                createRoleCard(
                        "🛵",
                        "Delivery",
                        "Navigate efficient routes, complete deliveries fast,\n" +
                        "and earn with our high-performance logistics tool.",
                        "#FFE9C9",
                        "#F36A13"
                );


        // =========================================================
        // ADD CARDS TO GRID
        // =========================================================

        roleGrid.add(
                shopkeeperCard,
                0,
                0
        );

        roleGrid.add(
                customerCard,
                1,
                0
        );

        roleGrid.add(
                adminCard,
                0,
                1
        );

        roleGrid.add(
                deliveryCard,
                1,
                1
        );


        // Space between description and cards
        VBox.setMargin(
                roleGrid,
                new Insets(
                        20,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // FEATURE STRIP
        // =========================================================

        HBox featureStrip =
                createFeatureStrip();

        VBox.setMargin(
                featureStrip,
                new Insets(
                        24,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // ADD CENTER CONTENT
        // =========================================================

        centerContent.getChildren().addAll(
                welcomeTitle,
                description,
                roleGrid,
                featureStrip
        );


        // =========================================================
        // CENTER BACKGROUND
        // =========================================================

        StackPane centerWrapper =
                new StackPane();

        centerWrapper.setStyle(
                "-fx-background-color: #FAF8FC;"
        );


        // =========================================================
        // RIGHT PEACH GLOW
        // =========================================================

        Circle peachGlow =
                new Circle(210);

        peachGlow.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.78,
                        0.30,
                        0.30,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.rgb(
                                        245,
                                        196,
                                        172,
                                        0.28
                                )
                        ),
                        new Stop(
                                1,
                                Color.rgb(
                                        245,
                                        196,
                                        172,
                                        0.0
                                )
                        )
                )
        );

        StackPane.setAlignment(
                peachGlow,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                peachGlow,
                new Insets(
                        -30,
                        -80,
                        0,
                        0
                )
        );


        // =========================================================
        // BOTTOM LEFT GLOW
        // =========================================================

        Circle bottomGlow =
                new Circle(170);

        bottomGlow.setFill(
                new RadialGradient(
                        0,
                        0,
                        0.5,
                        0.5,
                        0.5,
                        true,
                        CycleMethod.NO_CYCLE,
                        new Stop(
                                0,
                                Color.rgb(
                                        255,
                                        216,
                                        196,
                                        0.18
                                )
                        ),
                        new Stop(
                                1,
                                Color.rgb(
                                        255,
                                        216,
                                        196,
                                        0.0
                                )
                        )
                )
        );

        StackPane.setAlignment(
                bottomGlow,
                Pos.BOTTOM_LEFT
        );

        StackPane.setMargin(
                bottomGlow,
                new Insets(
                        0,
                        0,
                        -70,
                        -70
                )
        );


        centerWrapper.getChildren().addAll(
                peachGlow,
                bottomGlow,
                centerContent
        );


        borderPane.setCenter(
                centerWrapper
        );


        // =========================================================
        // FOOTER
        // =========================================================

        VBox footer =
                createFooter();

        borderPane.setBottom(
                footer
        );


        // =========================================================
        // SCENE
        // =========================================================

        mainPageScene =
                new Scene(
                        borderPane,
                        1280,
                        650
                );

        mainPageScene.setFill(
                Color.web("#FAF8FC")
        );

        return mainPageScene;
    }


    // =============================================================
    // HEADER
    // =============================================================

    private static HBox createHeader() {

        HBox header =
                new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setPrefHeight(62);

        header.setMinHeight(62);

        header.setMaxHeight(62);

        header.setPadding(
                new Insets(
                        0,
                        40,
                        0,
                        48
                )
        );

        header.setStyle(
                "-fx-background-color: rgba(250,248,252,0.97);" +
                "-fx-border-color: #E9E2E1;" +
                "-fx-border-width: 0 0 1 0;"
        );


        // =========================================================
        // LOGO
        // =========================================================

        Label buy =
                new Label("Buy");

        buy.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        buy.setTextFill(
                Color.web("#B9470A")
        );


        Label nex =
                new Label("NeX");

        nex.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        34
                )
        );

        nex.setTextFill(
                Color.web("#F06A0B")
        );


        HBox logo =
                new HBox();

        logo.setAlignment(
                Pos.CENTER_LEFT
        );

        logo.getChildren().addAll(
                buy,
                nex
        );


        // =========================================================
        // SPACER
        // =========================================================

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );


        // =========================================================
        // HELP TEXT
        // =========================================================

        Label help =
                new Label("Need Help?");

        help.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        help.setTextFill(
                Color.web("#513D36")
        );


        // =========================================================
        // HELP BUTTON
        // =========================================================

        Button helpButton =
                new Button("?");

        helpButton.setPrefSize(
                34,
                34
        );

        helpButton.setMinSize(
                34,
                34
        );

        helpButton.setMaxSize(
                34,
                34
        );

        helpButton.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        helpButton.setTextFill(
                Color.web("#5E5E5E")
        );

        helpButton.setStyle(
                "-fx-background-color: #E5E3E3;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-cursor: hand;"
        );


        helpButton.setOnMouseEntered(e ->
                helpButton.setStyle(
                        "-fx-background-color: #D8D5D5;" +
                        "-fx-background-radius: 50%;" +
                        "-fx-border-radius: 50%;" +
                        "-fx-cursor: hand;"
                )
        );


        helpButton.setOnMouseExited(e ->
                helpButton.setStyle(
                        "-fx-background-color: #E5E3E3;" +
                        "-fx-background-radius: 50%;" +
                        "-fx-border-radius: 50%;" +
                        "-fx-cursor: hand;"
                )
        );


        HBox.setMargin(
                helpButton,
                new Insets(
                        0,
                        0,
                        0,
                        14
                )
        );


        header.getChildren().addAll(
                logo,
                spacer,
                help,
                helpButton
        );


        return header;
    }


    // =============================================================
    // ROLE CARD
    // =============================================================

    private static VBox createRoleCard(
            String iconText,
            String title,
            String description,
            String iconBackground,
            String iconColor) {


        // =========================================================
        // CARD
        // =========================================================

        VBox card =
                new VBox();

        card.setAlignment(
                Pos.TOP_CENTER
        );

        card.setPrefWidth(400);

        card.setPrefHeight(155);

        card.setMinWidth(400);

        card.setMinHeight(155);

        card.setMaxWidth(400);

        card.setMaxHeight(155);

        card.setPadding(
                new Insets(
                        17,
                        20,
                        12,
                        20
                )
        );


        // =========================================================
        // NORMAL STYLE
        // =========================================================

        String normalStyle =
                "-fx-background-color: rgba(255,255,255,0.96);" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: #EEE4E0;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 17;";


        // =========================================================
        // HOVER STYLE
        // =========================================================

        String hoverStyle =
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 17;" +
                "-fx-border-color: #E8B18F;" +
                "-fx-border-width: 1.3;" +
                "-fx-border-radius: 17;";


        card.setStyle(
                normalStyle
        );


        // =========================================================
        // NORMAL SHADOW
        // =========================================================

        DropShadow normalShadow =
                new DropShadow();

        normalShadow.setRadius(7);

        normalShadow.setSpread(0.01);

        normalShadow.setOffsetY(2);

        normalShadow.setColor(
                Color.rgb(
                        70,
                        50,
                        40,
                        0.07
                )
        );


        // =========================================================
        // HOVER SHADOW
        // =========================================================

        DropShadow hoverShadow =
                new DropShadow();

        hoverShadow.setRadius(14);

        hoverShadow.setSpread(0.02);

        hoverShadow.setOffsetY(5);

        hoverShadow.setColor(
                Color.rgb(
                        180,
                        85,
                        40,
                        0.15
                )
        );


        card.setEffect(
                normalShadow
        );


        // =========================================================
        // ICON BOX
        // =========================================================

        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                52,
                52
        );

        iconBox.setMinSize(
                52,
                52
        );

        iconBox.setMaxSize(
                52,
                52
        );

        iconBox.setStyle(
                "-fx-background-color: " +
                iconBackground +
                ";" +
                "-fx-background-radius: 13;"
        );


        Label icon =
                new Label(iconText);

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.NORMAL,
                        23
                )
        );

        icon.setTextFill(
                Color.web(iconColor)
        );


        iconBox.getChildren().add(
                icon
        );


        // =========================================================
        // TITLE
        // =========================================================

        Label titleLabel =
                new Label(title);

        titleLabel.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        titleLabel.setTextFill(
                Color.web("#111111")
        );

        titleLabel.setPadding(
                new Insets(
                        8,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // DESCRIPTION
        // =========================================================

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setFont(
                Font.font(
                        "Arial",
                        11.5
                )
        );

        descriptionLabel.setTextFill(
                Color.web("#65483E")
        );

        descriptionLabel.setTextAlignment(
                TextAlignment.CENTER
        );

        descriptionLabel.setAlignment(
                Pos.CENTER
        );

        descriptionLabel.setWrapText(true);


        // =========================================================
        // ARROW BUTTON
        // =========================================================

        Button arrowButton = new Button("GO");

        arrowButton.setPrefSize(
                32,
                32
        );

        // arrowButton.setMinSize(
        //         32,
        //         32
        // );

        // arrowButton.setMaxSize(
        //         32,
        //         32
        // );

        arrowButton.setFont(
                Font.font(
                        "Arial"
                        
                )
        );

        arrowButton.setTextFill(
                Color.web("#222222")
        );


        // =========================================================
        // ARROW STYLES
        // =========================================================

        String arrowNormalStyle =
                "-fx-background-color: white;" +
                "-fx-border-color: #E8BFAE;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-cursor: hand;";


        String arrowHoverStyle =
                "-fx-background-color: #C65318;" +
                "-fx-border-color: #C65318;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 50%;" +
                "-fx-border-radius: 50%;" +
                "-fx-cursor: hand;";


        arrowButton.setStyle(
                arrowNormalStyle
        );


        VBox.setMargin(
                arrowButton,
                new Insets(
                        8,
                        0,
                        0,
                        0
                )
        );


        // =========================================================
        // ARROW ACTION
        // =========================================================

        arrowButton.setOnAction(e -> {

            if (title.equals("Shopkeeper")) {

                // Main.primaryStage.setScene(
                //         ShopkeeperLogin.loginscene()
                // );
            }
        });


        // =========================================================
        // WHOLE CARD HOVER
        // =========================================================

        card.setOnMouseEntered(e -> {

            card.setTranslateY(-4);

            card.setEffect(
                    hoverShadow
            );

            card.setStyle(
                    hoverStyle
            );

            arrowButton.setStyle(
                    arrowHoverStyle
            );

            arrowButton.setTextFill(
                    Color.WHITE
            );

            iconBox.setScaleX(
                    1.04
            );

            iconBox.setScaleY(
                    1.04
            );
        });


        // =========================================================
        // WHOLE CARD EXIT
        // =========================================================

        card.setOnMouseExited(e -> {

            card.setTranslateY(0);

            card.setEffect(
                    normalShadow
            );

            card.setStyle(
                    normalStyle
            );

            arrowButton.setStyle(
                    arrowNormalStyle
            );

            arrowButton.setTextFill(
                    Color.web("#222222")
            );

            iconBox.setScaleX(
                    1.0
            );

            iconBox.setScaleY(
                    1.0
            );
        });


        // =========================================================
        // ARROW HOVER
        // =========================================================

        arrowButton.setOnMouseEntered(e -> {

            arrowButton.setStyle(
                    arrowHoverStyle
            );

            arrowButton.setTextFill(
                    Color.WHITE
            );
        });


        arrowButton.setOnMouseExited(e -> {

            if (card.isHover()) {

                arrowButton.setStyle(
                        arrowHoverStyle
                );

                arrowButton.setTextFill(
                        Color.WHITE
                );

            } else {

                arrowButton.setStyle(
                        arrowNormalStyle
                );

                arrowButton.setTextFill(
                        Color.web("#222222")
                );
            }
        });


        // =========================================================
        // ADD CARD CONTENT
        // =========================================================

        card.getChildren().addAll(
                iconBox,
                titleLabel,
                descriptionLabel,
                arrowButton
        );


        return card;
    }


    // =============================================================
    // FEATURE STRIP
    // =============================================================

    private static HBox createFeatureStrip() {

        HBox featureStrip =
                new HBox();

        featureStrip.setAlignment(
                Pos.CENTER
        );

        featureStrip.setSpacing(
                30
        );


        HBox featureOne =
                createFeature(
                        "⚙",
                        "Secure Login"
                );


        HBox featureTwo =
                createFeature(
                        "ϟ",
                        "AI-Powered"
                );


        HBox featureThree =
                createFeature(
                        "◷",
                        "24/7 Support"
                );


        featureStrip.getChildren().addAll(
                featureOne,
                featureTwo,
                featureThree
        );


        return featureStrip;
    }


    // =============================================================
    // FEATURE
    // =============================================================

    private static HBox createFeature(
            String iconText,
            String text) {

        HBox feature =
                new HBox(7);

        feature.setAlignment(
                Pos.CENTER
        );


        Label icon =
                new Label(iconText);

        icon.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        icon.setTextFill(
                Color.web("#8C766C")
        );


        Label label =
                new Label(text);

        label.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        label.setTextFill(
                Color.web("#8C766C")
        );


        feature.getChildren().addAll(
                icon,
                label
        );


        return feature;
    }


    // =============================================================
    // FOOTER
    // =============================================================

    private static VBox createFooter() {

        VBox footer =
                new VBox();

        footer.setAlignment(
                Pos.CENTER
        );

        footer.setSpacing(
                5
        );

        footer.setPadding(
                new Insets(
                        8,
                        0,
                        8,
                        0
                )
        );

        footer.setStyle(
                "-fx-background-color: rgba(255,255,255,0.60);" +
                "-fx-border-color: #E8E1DF;" +
                "-fx-border-width: 1 0 0 0;"
        );


        Label copyright =
                new Label(
                        "© 2024 BuyNeX Hyperlocal Ecosystem. All rights reserved."
                );

        copyright.setFont(
                Font.font(
                        "Arial",
                        10
                )
        );

        copyright.setTextFill(
                Color.web("#85756E")
        );


        HBox footerLinks =
                new HBox(
                        25
                );

        footerLinks.setAlignment(
                Pos.CENTER
        );


        Button privacy =
                createFooterButton(
                        "PRIVACY POLICY"
                );


        Button terms =
                createFooterButton(
                        "TERMS OF SERVICE"
                );


        Button support =
                createFooterButton(
                        "SUPPORT"
                );


        footerLinks.getChildren().addAll(
                privacy,
                terms,
                support
        );


        footer.getChildren().addAll(
                copyright,
                footerLinks
        );


        return footer;
    }


    // =============================================================
    // FOOTER BUTTON
    // =============================================================

    private static Button createFooterButton(
            String text) {

        Button button =
                new Button(text);

        button.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        10
                )
        );

        button.setTextFill(
                Color.web("#554740")
        );

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-padding: 0;" +
                "-fx-cursor: hand;"
        );


        button.setOnMouseEntered(e ->
                button.setTextFill(
                        Color.web("#C65318")
                )
        );


        button.setOnMouseExited(e ->
                button.setTextFill(
                        Color.web("#554740")
                )
        );


        return button;
    }
}
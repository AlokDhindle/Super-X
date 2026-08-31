package com.kryox.view.Delivery;


import com.kryox.view.Admin.AdminLoginPage;
import com.kryox.view.Shopkeeper.ShopkeeperLogin;
import com.kryox.view.Delivery.Deliverylogin;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Homepage extends Application {

    public static Stage HomepageStage;
    private Scene homeScene;

    // =========================================================
    // INTRO VIDEO PATH
    // =========================================================

    private static final String VIDEO_PATH =
            "C:\\BuyNex\\Super-X\\buynex\\src\\main\\resources\\assets\\vedio\\Videio1.mp4";

    @Override
    public void start(Stage stage) throws Exception {

        HomepageStage = stage;

        showIntroVideo(stage);
    }

    // =========================================================
    // INTRO VIDEO
    // =========================================================

    private void showIntroVideo(Stage stage) {

        try {

            Media media = new Media(
                    new java.io.File(VIDEO_PATH)
                            .toURI()
                            .toString()
            );

            MediaPlayer mediaPlayer = new MediaPlayer(media);

            MediaView mediaView = new MediaView(mediaPlayer);

            mediaView.setPreserveRatio(true);

            javafx.scene.layout.StackPane videoPane =
                    new javafx.scene.layout.StackPane(mediaView);

            videoPane.setStyle(
                    "-fx-background-color: black;"
            );

            Scene videoScene =
                    new Scene(videoPane, 1700, 900);

            stage.setTitle("BuyNeX");

            stage.setScene(videoScene);

            stage.setMaximized(true);

            stage.show();

            mediaView.fitWidthProperty().bind(
                    videoPane.widthProperty()
            );

            mediaView.fitHeightProperty().bind(
                    videoPane.heightProperty()
            );

            // When video finishes
            mediaPlayer.setOnEndOfMedia(() -> {

                mediaPlayer.stop();

                mediaPlayer.dispose();

                showHomepage(stage);
            });

            // Video error
            mediaPlayer.setOnError(() -> {

                System.out.println(
                        "Video Error: " +
                                mediaPlayer.getError()
                );

                mediaPlayer.dispose();

                showHomepage(stage);
            });

            mediaPlayer.play();

        } catch (Exception e) {

            System.out.println(
                    "Unable to load intro video: " +
                            e.getMessage()
            );

            showHomepage(stage);
        }
    }

    // =========================================================
    // HOMEPAGE
    // =========================================================

    private void showHomepage(Stage stage) {

        // =====================================================
        // COMMON SHADOW
        // =====================================================

        DropShadow shadow = new DropShadow();

        shadow.setRadius(18);
        shadow.setSpread(0.08);
        shadow.setOffsetX(0);
        shadow.setOffsetY(5);

        shadow.setColor(
                Color.rgb(0, 0, 0, 0.12)
        );

        // =====================================================
        // HEADER
        // =====================================================

        Text logoText = new Text("BuyNeX");

        logoText.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        34
                )
        );

        logoText.setFill(
                Color.web("#c56b2a")
        );

        Text helpText = new Text("Need help ?");

        helpText.setFont(
                Font.font(
                        "System",
                        FontWeight.NORMAL,
                        12
                )
        );

        Image logoImage =
                new Image(
                        "assets\\images\\mark.png"
                );

        ImageView logoImageView =
                new ImageView(logoImage);

        logoImageView.setFitHeight(30);
        logoImageView.setFitWidth(30);

        HBox header = new HBox();

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setSpacing(15);

        header.setPadding(
                new Insets(
                        10,
                        25,
                        10,
                        25
                )
        );

        Region headerSpacer = new Region();

        HBox.setHgrow(
                headerSpacer,
                Priority.ALWAYS
        );

        header.getChildren().addAll(
                logoText,
                headerSpacer,
                helpText,
                logoImageView
        );

        header.setStyle(
                "-fx-background-color: #eee5df;"
        );

        header.setEffect(shadow);

        // =====================================================
        // WELCOME TEXT
        // =====================================================

        Text welcomeText =
                new Text("Welcome back to ");

        welcomeText.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        34
                )
        );

        welcomeText.setFill(
                Color.web("#171717")
        );

        Text buyNexText =
                new Text("BuyNeX");

        buyNexText.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        34
                )
        );

        buyNexText.setFill(
                Color.web("#D86B1F")
        );

        ScaleTransition welcomeAnimation =
                new ScaleTransition(
                        javafx.util.Duration.seconds(1.5),
                        buyNexText
                );

        welcomeAnimation.setFromX(1);
        welcomeAnimation.setFromY(1);

        welcomeAnimation.setToX(1.1);
        welcomeAnimation.setToY(1.1);

        welcomeAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        welcomeAnimation.setAutoReverse(true);

        welcomeAnimation.play();

        HBox welcomeBox =
                new HBox(
                        8,
                        welcomeText,
                        buyNexText
                );

        welcomeBox.setAlignment(
                Pos.CENTER
        );

        Text description =
                new Text(
                        "Our hyperlocal ecosystem connects intelligence with speed.\n" +
                        "Please select your specific portal to continue your journey."
                );

        description.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.NORMAL,
                        15
                )
        );

        description.setFill(
                Color.web("#674A40")
        );

        description.setTextAlignment(
                TextAlignment.CENTER
        );

        VBox introBox =
                new VBox(
                        10,
                        welcomeBox,
                        description
                );

        introBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // CARD 1 - SHOPKEEPER
        // =====================================================

        Image shopImage =
                new Image(
                        "assets/images/shop.png"
                );

        ImageView shopImageView =
                new ImageView(shopImage);

        shopImageView.setFitWidth(35);
        shopImageView.setFitHeight(35);

        HBox shopIconBox =
                new HBox(shopImageView);

        shopIconBox.setPrefSize(64, 64);
        shopIconBox.setMinSize(64, 64);
        shopIconBox.setMaxSize(64, 64);

        shopIconBox.setAlignment(
                Pos.CENTER
        );

        shopIconBox.setStyle(
                "-fx-background-color: #FFD9C9;" +
                "-fx-background-radius: 15;"
        );

        ScaleTransition shopAnimation =
                new ScaleTransition(
                        javafx.util.Duration.seconds(1.5),
                        shopImageView
                );

        shopAnimation.setFromX(1.2);
        shopAnimation.setFromY(1.2);

        shopAnimation.setToX(1.1);
        shopAnimation.setToY(1.1);

        shopAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        shopAnimation.setAutoReverse(true);

        shopAnimation.play();

        Text shopTitle =
                new Text("Shopkeeper");

        shopTitle.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        26
                )
        );

        shopTitle.setFill(
                Color.web("#171717")
        );

        Text shopDescription =
                new Text(
                        "Manage your inventory, process local orders,\n" +
                        "and grow your retail business with AI insights."
                );

        shopDescription.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.NORMAL,
                        14
                )
        );

        shopDescription.setFill(
                Color.web("#674A40")
        );

        shopDescription.setTextAlignment(
                TextAlignment.CENTER
        );

        Image arrowImage1 =
                new Image(
                        "assets\\arrow.png"
                );

        ImageView arrowView1 =
                new ImageView(arrowImage1);

        arrowView1.setFitWidth(30);
        arrowView1.setFitHeight(30);

        Button b1 =
                new Button();

        b1.setGraphic(arrowView1);

        b1.setPrefSize(60, 50);
        b1.setMinSize(60, 50);
        b1.setMaxSize(60, 50);

        b1.setStyle(
                "-fx-background-color: #e6690f;" +
                "-fx-background-radius: 15;"
        );
        b1.setOnAction(event->{
                ShopkeeperLogin sl=new ShopkeeperLogin();
                Homepage.HomepageStage.setScene(sl.loginscene());
        });

        Region shopSpacer =
                new Region();

        VBox.setVgrow(
                shopSpacer,
                Priority.ALWAYS
        );

        VBox shopCard =
                new VBox(
                        8,
                        shopIconBox,
                        shopTitle,
                        shopDescription,
                        shopSpacer,
                        b1
                );

        shopCard.setAlignment(
                Pos.CENTER
        );

        shopCard.setPrefSize(320, 280);
        shopCard.setMinSize(320, 280);
        shopCard.setMaxSize(320, 280);

        shopCard.setPadding(
                new Insets(15)
        );

        shopCard.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: #ffffff;"
        );

        // =====================================================
        // CARD 2 - CUSTOMER
        // =====================================================

        Image customerImage =
                new Image(
                        "assets/images/shopper.png"
                );

        ImageView customerImageView =
                new ImageView(customerImage);

        customerImageView.setFitWidth(35);
        customerImageView.setFitHeight(35);

        customerImageView.setPreserveRatio(true);

        HBox customerIconBox =
                new HBox(customerImageView);

        customerIconBox.setPrefSize(64, 64);
        customerIconBox.setMinSize(64, 64);
        customerIconBox.setMaxSize(64, 64);

        customerIconBox.setAlignment(
                Pos.CENTER
        );

        customerIconBox.setStyle(
                "-fx-background-color: #E8E8E8;" +
                "-fx-background-radius: 15;"
        );

        ScaleTransition customerAnimation =
                new ScaleTransition(
                        javafx.util.Duration.seconds(1.5),
                        customerImageView
                );

        customerAnimation.setFromX(1.2);
        customerAnimation.setFromY(1.2);

        customerAnimation.setToX(1.1);
        customerAnimation.setToY(1.1);

        customerAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        customerAnimation.setAutoReverse(true);

        customerAnimation.play();

        Text customerTitle =
                new Text("Customer");

        customerTitle.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        28
                )
        );

        customerTitle.setFill(
                Color.web("#171717")
        );

        Text customerDescription =
                new Text(
                        "Explore nearby shops, get lightning-fast delivery,\n" +
                        "and enjoy a premium hyperlocal experience."
                );

        customerDescription.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.NORMAL,
                        14
                )
        );

        customerDescription.setFill(
                Color.web("#674A40")
        );

        customerDescription.setTextAlignment(
                TextAlignment.CENTER
        );

        Image arrowImage2 =
                new Image(
                        "assets\\arrow.png"
                );

        ImageView arrowView2 =
                new ImageView(arrowImage2);

        arrowView2.setFitWidth(30);
        arrowView2.setFitHeight(30);

        Button b2 =
                new Button();

        b2.setGraphic(arrowView2);

        b2.setPrefSize(60, 50);
        b2.setMinSize(60, 50);
        b2.setMaxSize(60, 50);

        b2.setStyle(
                "-fx-background-color: #e6690f;" +
                "-fx-background-radius: 15;"
        );

        b2.setOnAction(event -> {

            CustomerLogin cl =
                    new CustomerLogin();

            Homepage.HomepageStage.setScene(
                    cl.getLoginScene()
            );
        });

        Region customerSpacer =
                new Region();

        VBox.setVgrow(
                customerSpacer,
                Priority.ALWAYS
        );

        VBox customerCard =
                new VBox(
                        8,
                        customerIconBox,
                        customerTitle,
                        customerDescription,
                        customerSpacer,
                        b2
                );

        customerCard.setAlignment(
                Pos.CENTER
        );

        customerCard.setPrefSize(320, 280);
        customerCard.setMinSize(320, 280);
        customerCard.setMaxSize(320, 280);

        customerCard.setPadding(
                new Insets(15)
        );

        customerCard.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: #ffffff;"
        );

        // =====================================================
        // CARD 3 - ADMIN
        // =====================================================

        Image adminImage =
                new Image(
                        "assets/images/protection.png"
                );

        ImageView adminImageView =
                new ImageView(adminImage);

        adminImageView.setFitWidth(35);
        adminImageView.setFitHeight(35);

        HBox adminIconBox =
                new HBox(adminImageView);

        adminIconBox.setPrefSize(64, 64);
        adminIconBox.setMinSize(64, 64);
        adminIconBox.setMaxSize(64, 64);

        adminIconBox.setAlignment(
                Pos.CENTER
        );

        adminIconBox.setStyle(
                "-fx-background-color: #E8E8E8;" +
                "-fx-background-radius: 15;"
        );

        ScaleTransition adminAnimation =
                new ScaleTransition(
                        javafx.util.Duration.seconds(1.5),
                        adminImageView
                );

        adminAnimation.setFromX(1.0);
        adminAnimation.setFromY(1.0);

        adminAnimation.setToX(1.08);
        adminAnimation.setToY(1.08);

        adminAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        adminAnimation.setAutoReverse(true);

        adminAnimation.play();

        Text adminTitle =
                new Text("Admin");

        adminTitle.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        26
                )
        );

        adminTitle.setFill(
                Color.web("#171717")
        );

        Text adminDescription =
                new Text(
                        "Monitor system health, manage platform users,\n" +
                        "and oversee the entire BuyNeX operations grid."
                );

        adminDescription.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.NORMAL,
                        13
                )
        );

        adminDescription.setFill(
                Color.web("#674A40")
        );

        adminDescription.setTextAlignment(
                TextAlignment.CENTER
        );

        Image arrowImage3 =
                new Image(
                        "assets\\arrow.png"
                );

        ImageView arrowView3 =
                new ImageView(arrowImage3);

        arrowView3.setFitWidth(30);
        arrowView3.setFitHeight(30);

        Button b3 =
                new Button();

        b3.setGraphic(arrowView3);

        b3.setPrefSize(60, 50);
        b3.setMinSize(60, 50);
        b3.setMaxSize(60, 50);

        b3.setStyle(
                "-fx-background-color: #e6690f;" +
                "-fx-background-radius: 15;"
        );
        b3.setOnAction(event->{
                AdminLoginPage adl=new AdminLoginPage();
                Homepage.HomepageStage.setScene(adl.getLoginScene());
        });

        Region adminSpacer =
                new Region();

        VBox.setVgrow(
                adminSpacer,
                Priority.ALWAYS
        );

        VBox adminCard =
                new VBox(
                        8,
                        adminIconBox,
                        adminTitle,
                        adminDescription,
                        adminSpacer,
                        b3
                );

        adminCard.setAlignment(
                Pos.CENTER
        );

        adminCard.setPrefSize(320, 280);
        adminCard.setMinSize(320, 280);
        adminCard.setMaxSize(320, 280);

        adminCard.setPadding(
                new Insets(15)
        );

        adminCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: #EEEEEE;"
        );

        // =====================================================
        // CARD 4 - DELIVERY
        // =====================================================

        Image deliveryImage =
                new Image(
                        "assets/images/man.png"
                );

        ImageView deliveryImageView =
                new ImageView(deliveryImage);

        deliveryImageView.setFitWidth(35);
        deliveryImageView.setFitHeight(35);

        HBox deliveryIconBox =
                new HBox(deliveryImageView);

        deliveryIconBox.setPrefSize(64, 64);
        deliveryIconBox.setMinSize(64, 64);
        deliveryIconBox.setMaxSize(64, 64);

        deliveryIconBox.setAlignment(
                Pos.CENTER
        );

        deliveryIconBox.setStyle(
                "-fx-background-color: #FFD9C9;" +
                "-fx-background-radius: 15;"
        );

        ScaleTransition deliveryAnimation =
                new ScaleTransition(
                        javafx.util.Duration.seconds(1.5),
                        deliveryImageView
                );

        deliveryAnimation.setFromX(1.2);
        deliveryAnimation.setFromY(1.2);

        deliveryAnimation.setToX(1.1);
        deliveryAnimation.setToY(1.1);

        deliveryAnimation.setCycleCount(
                Animation.INDEFINITE
        );

        deliveryAnimation.setAutoReverse(true);

        deliveryAnimation.play();

        Text deliveryTitle =
                new Text("Delivery");

        deliveryTitle.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.BOLD,
                        26
                )
        );

        deliveryTitle.setFill(
                Color.web("#171717")
        );

        Text deliveryDescription =
                new Text(
                        "Monitor system health, manage platform users,\n" +
                        "and oversee the entire BuyNeX operations grid."
                );

        deliveryDescription.setFont(
                Font.font(
                        "Poppins",
                        FontWeight.NORMAL,
                        13
                )
        );

        deliveryDescription.setFill(
                Color.web("#674A40")
        );

        deliveryDescription.setTextAlignment(
                TextAlignment.CENTER
        );

        Image arrowImage4 =
                new Image(
                        "assets\\arrow.png"
                );

        ImageView arrowView4 =
                new ImageView(arrowImage4);

        arrowView4.setFitWidth(30);
        arrowView4.setFitHeight(30);

        Button b4 =
                new Button();

        b4.setGraphic(arrowView4);

        b4.setPrefSize(60, 50);
        b4.setMinSize(60, 50);
        b4.setMaxSize(60, 50);

        b4.setStyle(
                "-fx-background-color: #e6690f;" +
                "-fx-background-radius: 15;"
        );
        b4.setOnAction(event->{

                Deliverylogin dl = new Deliverylogin();
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());

        });

        Region deliverySpacer =
                new Region();

        VBox.setVgrow(
                deliverySpacer,
                Priority.ALWAYS
        );

        VBox deliveryCard =
                new VBox(
                        8,
                        deliveryIconBox,
                        deliveryTitle,
                        deliveryDescription,
                        deliverySpacer,
                        b4
                );

        deliveryCard.setAlignment(
                Pos.CENTER
        );

        deliveryCard.setPrefSize(320, 280);
        deliveryCard.setMinSize(320, 280);
        deliveryCard.setMaxSize(320, 280);

        deliveryCard.setPadding(
                new Insets(15)
        );

        deliveryCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 18;" +
                "-fx-border-radius: 18;" +
                "-fx-border-color: #FFFFFF;"
        );

        // =====================================================
        // CARD HOVER EFFECT
        // =====================================================

        DropShadow orangeGlow =
                new DropShadow();

        orangeGlow.setColor(
                Color.web("#f0872c")
        );

        orangeGlow.setRadius(25);

        orangeGlow.setSpread(0.4);

        setHoverEffect(
                shopCard,
                orangeGlow
        );

        setHoverEffect(
                customerCard,
                orangeGlow
        );

        setHoverEffect(
                adminCard,
                orangeGlow
        );

        setHoverEffect(
                deliveryCard,
                orangeGlow
        );

        // =====================================================
        // FIRST ROW
        // =====================================================

        HBox firstRow =
                new HBox(
                        20,
                        shopCard,
                        customerCard
                );

        firstRow.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // SECOND ROW
        // =====================================================

        HBox secondRow =
                new HBox(
                        20,
                        adminCard,
                        deliveryCard
                );

        secondRow.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // ALL CARDS
        // =====================================================

        VBox cardsBox =
                new VBox(
                        20,
                        firstRow,
                        secondRow
                );

        cardsBox.setAlignment(
                Pos.CENTER
        );

        // =====================================================
        // MAIN LAYOUT
        // =====================================================

        VBox mainLayout =
                new VBox(
                        15,
                        header,
                        introBox,
                        cardsBox
                );

        mainLayout.setAlignment(
                Pos.TOP_CENTER
        );

        mainLayout.setPadding(
                new Insets(
                        0,
                        20,
                        20,
                        20
                )
        );

        // =====================================================
        // BACKGROUND GLOW
        // =====================================================

        RadialGradient rightTopGlow =
                new RadialGradient(
                        0,
                        0,
                        0.80,
                        0.03,
                        0.65,
                        true,
                        CycleMethod.NO_CYCLE,

                        new Stop(
                                0.0,
                                Color.web(
                                        "#D86B1F",
                                        0.30
                                )
                        ),

                        new Stop(
                                0.35,
                                Color.web(
                                        "#EAD7D0",
                                        0.12
                                )
                        ),

                        new Stop(
                                0.70,
                                Color.web(
                                        "#FAF8FE",
                                        0.03
                                )
                        ),

                        new Stop(
                                1.0,
                                Color.web(
                                        "#FAF8FE",
                                        0.0
                                )
                        )
                );

        RadialGradient leftBottomGlow =
                new RadialGradient(
                        0,
                        0,
                        0.08,
                        0.90,
                        0.40,
                        true,
                        CycleMethod.NO_CYCLE,

                        new Stop(
                                0.0,
                                Color.web(
                                        "#D86B1F",
                                        0.14
                                )
                        ),

                        new Stop(
                                0.35,
                                Color.web(
                                        "#EAD7D0",
                                        0.10
                                )
                        ),

                        new Stop(
                                0.70,
                                Color.web(
                                        "#FAF8FE",
                                        0.03
                                )
                        ),

                        new Stop(
                                1.0,
                                Color.web(
                                        "#FAF8FE",
                                        0.0
                                )
                        )
                );

        mainLayout.setBackground(
                new Background(
                        new BackgroundFill(
                                rightTopGlow,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        ),
                        new BackgroundFill(
                                leftBottomGlow,
                                CornerRadii.EMPTY,
                                Insets.EMPTY
                        )
                )
        );

        // =====================================================
        // SCENE
        // =====================================================

        Scene scene =
                new Scene(
                        mainLayout,
                        1550,
                        850
                );

        stage.setTitle(
                "Home page"
        );

        stage.setScene(scene);

        stage.setMaximized(true);

        stage.show();
    }

    // =========================================================
    // HOVER EFFECT METHOD
    // =========================================================

    private void setHoverEffect(
            VBox card,
            DropShadow glow
    ) {

        card.setEffect(
                new DropShadow(
                        18,
                        0,
                        5,
                        Color.rgb(
                                0,
                                0,
                                0,
                                0.12
                        )
                )
        );

        card.setOnMouseEntered(event -> {

            card.setEffect(glow);

            card.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-color: #ffffff;" +
                    "-fx-border-width: 1;"
            );
        });

        card.setOnMouseExited(event -> {

            card.setEffect(
                    new DropShadow(
                            18,
                            0,
                            5,
                            Color.rgb(
                                    0,
                                    0,
                                    0,
                                    0.12
                            )
                    )
            );

            card.setStyle(
                    "-fx-background-color: #FFFFFF;" +
                    "-fx-background-radius: 18;" +
                    "-fx-border-radius: 18;" +
                    "-fx-border-color: #ffffff;"
            );
        });
    }
}

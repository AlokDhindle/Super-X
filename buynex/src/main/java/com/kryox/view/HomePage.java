package com.kryox.view;

import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomePage extends Application {
        public Stage HomepageStage;
        private Scene homeScene;

        @Override
        public void start(Stage stage) throws Exception {
                HomepageStage = stage;
                DropShadow shadowhb4 = new DropShadow();

                shadowhb4.setRadius(18);
                shadowhb4.setSpread(0.08);
                shadowhb4.setOffsetX(0);
                shadowhb4.setOffsetY(5);
                shadowhb4.setColor(Color.rgb(0, 0, 0, 0.12));

                Text t1 = new Text("Welcome back to ");
                t1.setFont(Font.font("Poppins", FontWeight.BOLD, 34));
                t1.setFill(Color.web("#171717"));
                t1.setLayoutX(500);

                Text t2 = new Text("BuyNeX");
                t2.setStyle("""
                                    -fx-font-family: 'Poppins';
                                    -fx-font-size: 34px;
                                    -fx-font-weight: bold;
                                    -fx-fill: #D86B1F;;
                                """);
                t2.setLayoutX(500);
                ScaleTransition st5 = new ScaleTransition(Duration.seconds(1.5), t2);
                st5.setFromX(1);
                st5.setFromY(1);
                st5.setToX(1.1);
                st5.setToY(1.1);
                st5.setCycleCount(ScaleTransition.INDEFINITE);
                st5.setAutoReverse(true);
                st5.play();

                Text t3 = new Text(
                                "Our hyperlocal ecosystem connects intelligence with speed. Please select \n                      your specific portal to continue your journey.");

                t3.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-font-family: 'Poppins';" +
                                                "-fx-fill: #674A40;" +
                                                "-fx-font-weight: 400;");
                Text t4 = new Text("BuyNeX");
                t4.setStyle("""
                                    -fx-font-family: 'Poppins';
                                    -fx-font-size: 34px;
                                    -fx-font-weight: bold;
                                    -fx-fill: #c56b2a;
                                """);
                t4.setTranslateX(100);
                t4.setTranslateY(2);
                // block1
                Button b1 = new Button("->");
                b1.setTranslateX(-360);
                b1.setTranslateY(165);
                b1.setMaxSize(50, 20);

                Image i1 = new Image("assects/images/shop.png");
                ImageView iv1 = new ImageView(i1);

                iv1.setFitWidth(35);
                iv1.setFitHeight(35);

                HBox hbiv1 = new HBox(iv1);
                hbiv1.setPrefSize(50, 50);
                hbiv1.setMinSize(50, 50);
                hbiv1.setMaxSize(50, 50);

                hbiv1.setAlignment(Pos.CENTER);

                hbiv1.setStyle(
                                "-fx-background-color: #FFD9C9;" +
                                                "-fx-background-radius: 12;");

                hbiv1.setTranslateY(15);

                ScaleTransition st4 = new ScaleTransition(Duration.seconds(1.5), iv1);
                st4.setFromX(1.2);
                st4.setFromY(1.2);
                st4.setToX(1.1);
                st4.setToY(1.1);
                st4.setCycleCount(ScaleTransition.INDEFINITE);
                st4.setAutoReverse(true);
                st4.play();

                Text thb3 = new Text("Shopkeeper");
                thb3.setFont(Font.font("Poppins", FontWeight.BOLD, 26));
                thb3.setFill(Color.web("#171717"));
                thb3.setTranslateX(95);
                thb3.setTranslateY(67);

                Text t1hb3 = new Text(
                                "Manage your inventory, process local orders, and \n     grow your retail business with AI insights.");
                t1hb3.setStyle("""
                                    -fx-font-family: 'Poppins';
                                    -fx-font-size: 14px;
                                    -fx-font-weight: normal;
                                    -fx-fill: #674A40;
                                """);
                t1hb3.setTranslateX(-190);
                t1hb3.setTranslateY(110);
                HBox hb3 = new HBox(thb3, hbiv1, t1hb3, b1);
                hb3.setPrefSize(320, 220);
                hb3.setMinSize(320, 220);
                hb3.setMaxSize(320, 220);

                hb3.setStyle(
                                "-fx-pref-width: 320px;" +
                                                "-fx-pref-height: 220px;" +
                                                "-fx-background-color: #ffffff;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-border-color: #ffffff;");

                DropShadow glow = new DropShadow();
                glow.setColor(Color.web("#edb07a"));
                glow.setRadius(25);
                glow.setSpread(0.4);

                hb3.setOnMouseEntered(e -> {
                        hb3.setEffect(glow);

                        hb3.setStyle(
                                        "-fx-pref-width: 440px;" +
                                                        "-fx-pref-height: 300px;" +
                                                        "-fx-background-color: #FFFFFF;" +
                                                        "-fx-background-radius: 18;" +
                                                        "-fx-border-radius: 18;" +
                                                        "-fx-border-color: #ffffff;" +
                                                        "-fx-border-width: 1;");
                });

                hb3.setOnMouseExited(e -> {
                        hb3.setEffect(null);
                        hb3.setEffect(shadowhb4);
                });

                // block2
                Button b2 = new Button("Shopkeeper");
                b2.setTranslateX(-330);
                b2.setTranslateY(165);
                b2.setMaxSize(50, 20);

                Image i2 = new Image("assects/images/shopper.png");
                ImageView iv2 = new ImageView(i2);

                iv2.setFitWidth(35);
                iv2.setFitHeight(35);
                iv2.setPreserveRatio(true);

                HBox hbiv2 = new HBox(iv2);

                hbiv2.setPrefSize(50, 50);
                hbiv2.setMinSize(50, 50);
                hbiv2.setMaxSize(50, 50);

                hbiv2.setAlignment(Pos.CENTER);
                hbiv2.setTranslateX(140);
                hbiv2.setTranslateY(15);

                hbiv2.setStyle(
                                "-fx-background-color: #E8E8E8;" +
                                                "-fx-background-radius: 12;");
                ScaleTransition st3 = new ScaleTransition(Duration.seconds(1.5), iv2);
                st3.setFromX(1.2);
                st3.setFromY(1.2);
                st3.setToX(1.1);
                st3.setToY(1.1);
                st3.setCycleCount(ScaleTransition.INDEFINITE);
                st3.setAutoReverse(true);
                st3.play();

                Text thb4 = new Text("Customer");
                thb4.setFont(Font.font("Poppins", FontWeight.BOLD, 23));
                thb4.setFill(Color.web("#171717"));
                thb4.setTranslateX(60);
                thb4.setTranslateY(67);

                Text t1hb4 = new Text(
                                "Explore nearby shops, get lightning-fast delivery, \n     and enjoy a premium hyperlocal experience.");
                t1hb4.setStyle("""
                                    -fx-font-family: 'Poppins';
                                    -fx-font-size: 14px;
                                    -fx-font-weight: normal;
                                    -fx-fill: #674A40;;
                                """);
                t1hb4.setTranslateX(-140);
                t1hb4.setTranslateY(110);

                HBox hb4 = new HBox(hbiv2, thb4, t1hb4, b2);
                hb4.setPrefSize(320, 220);
                hb4.setMinSize(320, 220);
                hb4.setMaxSize(320, 220);

                hb4.setStyle(
                                "-fx-pref-width: 320px;" +
                                                "-fx-pref-height: 220px;" +
                                                "-fx-background-color: #ffffff;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-border-color: #ffffff;");
                DropShadow glowhb = new DropShadow();
                glowhb.setColor(Color.web("#edb07a"));
                glowhb.setRadius(25);
                glowhb.setSpread(0.4);

                hb4.setOnMouseEntered(e -> {
                        hb4.setEffect(glow);
                        hb4.setStyle(
                                        "-fx-pref-width: 440px;" +
                                                        "-fx-pref-height: 300px;" +
                                                        "-fx-background-color: #FFFFFF;" +
                                                        "-fx-background-radius: 18;" +
                                                        "-fx-border-radius: 18;" +
                                                        "-fx-border-color: #ffffff;" +
                                                        "-fx-border-width: 1;");
                });

                hb4.setOnMouseExited(e -> {
                        hb4.setEffect(null);
                        hb4.setEffect(shadowhb4);
                });

                HBox hb5 = new HBox(20, hb3, hb4);

                hb5.setStyle(
                                "-fx-background-color: transparent;");
                hb5.setTranslateX(450);
                hb5.setTranslateY(160);

                // block 3

                Button b3 = new Button("->");
                b3.setTranslateX(-290);
                b3.setTranslateY(165);
                b3.setMaxSize(50, 20);

                Image i3 = new Image("assects/images/protection.png");
                ImageView iv3 = new ImageView(i3);

                iv3.setFitWidth(35);
                iv3.setFitHeight(35);

                HBox hbiv3 = new HBox(iv3);
                hbiv3.setPrefSize(50, 50);
                hbiv3.setMinSize(50, 50);
                hbiv3.setMaxSize(50, 50);

                hbiv3.setAlignment(Pos.CENTER);

                hbiv3.setStyle(
                                "-fx-background-color: #E8E8E8;" +
                                                "-fx-background-radius: 12;");

                hbiv3.setTranslateX(140);
                hbiv3.setTranslateY(15);

                ScaleTransition st2 = new ScaleTransition(Duration.seconds(1.5), iv3);

                st2.setFromX(1.0);
                st2.setFromY(1.0);

                st2.setToX(1.08);
                st2.setToY(1.08);

                st2.setCycleCount(Animation.INDEFINITE);
                st2.setAutoReverse(true);

                st2.play();

                Text thb5 = new Text("Admin");

                thb5.setFont(
                                Font.font(
                                                "Poppins",
                                                FontWeight.BOLD,
                                                20));

                thb5.setFill(Color.web("#171717"));

                thb5.setTranslateX(79);
                thb5.setTranslateY(80);

                Text t1hb5 = new Text(
                                "Monitor system health, manage platform users,\n" +
                                                "and oversee the entire BuyNeX operations grid.");

                t1hb5.setFont(
                                Font.font(
                                                "Poppins",
                                                FontWeight.NORMAL,
                                                12));

                t1hb5.setFill(Color.web("#674A40"));

                t1hb5.setTranslateX(-105);
                t1hb5.setTranslateY(110);

                HBox hb6 = new HBox(
                                hbiv3,
                                thb5,
                                t1hb5,
                                b3);

                hb6.setPrefSize(320, 220);
                hb6.setMinSize(320, 220);
                hb6.setMaxSize(320, 220);

                hb6.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-border-color: #EEEEEE;" +
                                                "-fx-border-width: 1;");
                ;

                DropShadow glowhb6 = new DropShadow();
                glowhb6.setColor(Color.web("#edb07a"));
                glowhb6.setRadius(25);
                glowhb6.setSpread(0.4);

                hb6.setOnMouseEntered(e -> {
                        hb6.setEffect(glowhb6);
                        hb6.setStyle("""
                                        -fx-background-color: #ffffff;
                                        -fx-background-radius: 15;
                                        -fx-background-color: #ffffff;
                                        -fx-border-width: 2;
                                        -fx-border-radius: 15;
                                        """);
                });

                hb6.setOnMouseExited(e -> {
                        hb6.setEffect(null);
                        hb6.setEffect(shadowhb4);
                });

                Button b4 = new Button("->");
                b4.setTranslateX(-290);
                b4.setTranslateY(165);
                b4.setMaxSize(50, 20);

                Image i4 = new Image("assects/images/man.png");
                ImageView iv4 = new ImageView(i4);

                iv4.setFitWidth(35);
                iv4.setFitHeight(35);

                HBox hbiv4 = new HBox(iv4);
                hbiv4.setPrefSize(50, 50);
                hbiv4.setMinSize(50, 50);
                hbiv4.setMaxSize(50, 50);

                hbiv4.setAlignment(Pos.CENTER);

                hbiv4.setStyle(
                                "-fx-background-color: #FFD9C9;" +
                                                "-fx-background-radius: 12;");

                hbiv4.setTranslateX(140);
                hbiv4.setTranslateY(15);

                ScaleTransition st1 = new ScaleTransition(Duration.seconds(1.5), iv3);
                st1.setFromX(1.2);
                st1.setFromY(1.2);
                st1.setToX(1.1);
                st1.setToY(1.1);
                st1.setCycleCount(ScaleTransition.INDEFINITE);
                st1.setAutoReverse(true);
                st1.play();

                Text thb6 = new Text("Delivery");
                thb6.setFont(Font.font("Poppins", FontWeight.BOLD, 26));
                thb6.setFill(Color.web("#171717"));
                thb6.setTranslateX(55);
                thb6.setTranslateY(67);
                Text t1hb6 = new Text(
                                "Monitor system health, manage platform users \n and oversee the entire BuyNeX operations grid.");
                t1hb5.setStyle("""
                                    -fx-font-family: 'Poppins';
                                    -fx-font-size: 14px;
                                    -fx-font-weight: normal;
                                    -fx-fill: #674A40;
                                """);
                t1hb6.setTranslateX(-120);
                t1hb6.setTranslateY(110);

                HBox hb7 = new HBox(hbiv4, thb6, t1hb6, b4);
                hb7.setPrefSize(320, 220);
                hb7.setMinSize(320, 220);
                hb7.setMaxSize(320, 220);
                hb7.setEffect(shadowhb4);

                hb7.setStyle(
                                "-fx-pref-width: 440px;" +
                                                "-fx-pref-height: 300px;" +
                                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-background-radius: 18;" +
                                                "-fx-border-radius: 18;" +
                                                "-fx-border-color: #ffffff;" +
                                                "-fx-border-width: 1;");

                DropShadow glowhb7 = new DropShadow();
                glowhb7.setColor(Color.web("#edb07a"));
                glowhb7.setRadius(25);
                glowhb7.setSpread(0.4);

                hb7.setOnMouseEntered(e -> {
                        hb7.setEffect(glowhb6);
                        hb7.setStyle("""
                                        -fx-background-color: #ffffff;
                                        -fx-background-radius: 15;
                                        -fx-background-color: #ffffff;
                                        -fx-border-width: 2;
                                        -fx-border-radius: 15;
                                        """);
                });

                hb7.setOnMouseExited(e -> {
                        hb7.setEffect(null);
                        hb7.setEffect(shadowhb4);
                });
                HBox hb8 = new HBox(20, hb6, hb7);

                RadialGradient orangeGlow1 = new RadialGradient(
                                0,
                                0,
                                0.82, // X position → right
                                0.18, // Y position → top
                                0.45, // glow size
                                true,
                                CycleMethod.NO_CYCLE,

                                new Stop(
                                                0.0,
                                                Color.web("#D86B1F", 0.20)),

                                new Stop(
                                                0.35,
                                                Color.web("#EAD7D0", 0.15)),

                                new Stop(
                                                0.70,
                                                Color.web("#FAF8FE", 0.05)),

                                new Stop(
                                                1.0,
                                                Color.web("#FAF8FE", 0.0)));

                hb8.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                orangeGlow1,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));
                hb8.setTranslateX(450);
                hb8.setTranslateY(190);

                Image im = new Image("assects\\images\\Gemini_Generated_Image_5vc4955vc4955vc4-removebg-preview.png");
                ImageView ivi = new ImageView(im);

                ivi.setFitWidth(80);
                ivi.setFitHeight(50);
                ivi.setTranslateX(-120);

                ScaleTransition st = new ScaleTransition(Duration.seconds(1.5), ivi);
                st.setFromX(1.2);
                st.setFromY(1.2);
                st.setToX(1.1);
                st.setToY(1.1);
                st.setCycleCount(ScaleTransition.INDEFINITE);
                st.setAutoReverse(true);
                st.play();

                hb3.setEffect(shadowhb4);
                hb4.setEffect(shadowhb4);
                hb6.setEffect(shadowhb4);
                hb7.setEffect(shadowhb4);

                Text t5 = new Text("Need help ?");
                t5.setFont(Font.font("System", FontWeight.NORMAL, 12));
                t5.setTranslateX(1240);
                t5.setTranslateY(15);

                Image img = new Image("assects\\images\\mark.png");
                ImageView imgv = new ImageView(img);
                imgv.setFitHeight(30);
                imgv.setFitWidth(30);
                imgv.setTranslateX(1250);
                imgv.setTranslateY(10);

                HBox hb1 = new HBox(t4, t5, imgv);
                hb1.setStyle(
                                "-fx-background-color: #eee5df;");

                hb1.setEffect(shadowhb4);

                HBox hb = new HBox(t1, t2);
                hb.setStyle("\"-fx-background-color: transparent;");
                hb.setTranslateX(580);
                hb.setTranslateY(80);

                VBox vb = new VBox(hb, t3);
                vb.setStyle("\"-fx-background-color: transparent;\"");

                t3.setTranslateX(550);
                t3.setTranslateY(100);

                VBox vb1 = new VBox(hb1, vb, hb5, hb8);

                RadialGradient rightTopGlow = new RadialGradient(
                                0,
                                0,
                                0.80, // RIGHT
                                0.03, // TOP
                                0.65, // SIZE
                                true,
                                CycleMethod.NO_CYCLE,

                                new Stop(
                                                0.0,
                                                Color.web("#D86B1F", 0.30)),

                                new Stop(
                                                0.35,
                                                Color.web("#EAD7D0", 0.12)),

                                new Stop(
                                                0.70,
                                                Color.web("#FAF8FE", 0.03)),

                                new Stop(
                                                1.0,
                                                Color.web("#FAF8FE", 0.0)));

                RadialGradient leftBottomGlow = new RadialGradient(
                                0,
                                0,
                                0.08, // LEFT
                                0.90, // BOTTOM
                                0.40, // SIZE
                                true,
                                CycleMethod.NO_CYCLE,

                                new Stop(
                                                0.0,
                                                Color.web("#D86B1F", 0.14)),

                                new Stop(
                                                0.35,
                                                Color.web("#EAD7D0", 0.10)),

                                new Stop(
                                                0.70,
                                                Color.web("#FAF8FE", 0.03)),

                                new Stop(
                                                1.0,
                                                Color.web("#FAF8FE", 0.0)));

                vb1.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                rightTopGlow,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY),
                                                new BackgroundFill(
                                                                leftBottomGlow,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));
                vb1.setStyle("\"-fx-background-color: #eee5df;");

                Scene sc = new Scene(vb1, 1000, 800);

                stage.setTitle("Home page");
                stage.setScene(sc);
                stage.show();

        }

}

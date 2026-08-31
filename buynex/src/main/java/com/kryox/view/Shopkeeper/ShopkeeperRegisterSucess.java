package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperRegisterSucess {

        private static Scene registrationSuccessScene;

        public static Scene registeredScene() {

                // MAIN BORDER PANE

                // -------------------------------------------------------------------------------------
                BorderPane borderPane = new BorderPane();
                borderPane.setStyle("-fx-background-color: #F8FBF8;");

                HBox headerBox = ViewConstants.loginHeader();
                borderPane.setTop(headerBox);

                // TICK MARK IMAGE

                Image image = new Image("assets\\images\\sucessTickMark.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(180);
                imageView.preserveRatioProperty().set(true);

                // CONGRATULATIONS TEXT

                Text congratulationsText = new Text("Congratulations!");
                congratulationsText.setStyle(
                                "-fx-font-size: 28px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #202020;");

                Text submittedText = new Text(
                                "Your registration has been submitted.\nVerification usually takes 24 hours.");
                submittedText.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
                submittedText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #555555;");

                // WHAT'S NEXT BOX

                Text infoIcon = new Text("ⓘ");
                infoIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #C75A25;");

                Text whatsNextTitle = new Text("WHAT'S NEXT?");
                whatsNextTitle.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #333333;");

                Text whatsNextText = new Text(
                                "Our team will review your shop details.\n" +
                                                "You will receive an email confirmation\n" +
                                                "once your store is live on the BuyNeX\n" +
                                                "network.");
                whatsNextText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #555555;");

                VBox whatsNextTextBox = new VBox(5, whatsNextTitle, whatsNextText);
                whatsNextTextBox.setAlignment(Pos.CENTER_LEFT);

                HBox whatsNextContent = new HBox(14, infoIcon, whatsNextTextBox);
                whatsNextContent.setAlignment(Pos.CENTER_LEFT);
                whatsNextContent.setPadding(new Insets(15, 18, 15, 18));

                VBox whatsNextBox = new VBox(whatsNextContent);
                whatsNextBox.setPrefWidth(345);
                whatsNextBox.setPrefHeight(120);
                whatsNextBox.setAlignment(Pos.CENTER_LEFT);

                whatsNextBox.setStyle(
                                "-fx-background-color: #FBF3F7;" +
                                                "-fx-border-color: #F0E2E8;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 13px;" +
                                                "-fx-background-radius: 13px;");

                // GO TO LOGIN BUTTON

                Button loginButton = new Button("Go to Login   →");
                loginButton.setPrefWidth(345);
                loginButton.setPrefHeight(47);
                loginButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #A94308, #FF6900);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-background-radius: 25px;" +
                                                "-fx-cursor: hand;");
                loginButton.setOnAction(e -> {
                        System.out.println("go to login button clicked");
                        Homepage.HomepageStage.setScene(ShopkeeperLogin.loginscene());
                });
                // SUCCESS CARD

                VBox successCard = new VBox(
                                20,
                                imageView,
                                congratulationsText,
                                submittedText,
                                whatsNextBox,
                                loginButton);
                // successCard.setTranslateX(240);

                successCard.setAlignment(Pos.TOP_CENTER);
                successCard.setPadding(new Insets(0, 30, 25, 30));
                successCard.setMaxWidth(425);
                // successCard.setPrefHeight(610);
                successCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                                "-fx-background-radius: 25px;");

                // Card shadow
                DropShadow cardShadow = new DropShadow();
                cardShadow.setRadius(25);
                cardShadow.setSpread(0.02);
                cardShadow.setColor(Color.rgb(70, 50, 40, 0.10));
                successCard.setEffect(cardShadow);

                VBox centerContent = new VBox(successCard);
                centerContent.setAlignment(Pos.TOP_CENTER);
                centerContent.setPadding(new Insets(5, 0, 5, 0));
                centerContent.setStyle("-fx-background-color: #F8FBF8;");
                centerContent.setPrefWidth(1280);

                // CENTER CONTENT

                centerContent.setStyle("-fx-background-color: #F8FBF8;");
                // SET CENTER
                borderPane.setCenter(centerContent);

                // FOOTER BOX
                VBox footerBox = ViewConstants.loginFooter();

                // SET BOTTOM
                borderPane.setBottom(footerBox);

                // SCENE

                registrationSuccessScene = new Scene(
                                borderPane,
                                1550,
                                850);

                registrationSuccessScene.setFill(
                                Color.web("#F8FBF8"));

                return registrationSuccessScene;
        }
}

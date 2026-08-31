
package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.dao.Shopkeeper.ShopkeeperDAO;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperDeleteAccountConfirmation {

    private static Scene deleteAccountScene;

    public static Scene deleteAccountScene() {

        // MAIN BORDER PANE
        // ------------------------------------------------------------------

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #F8FBF8;");

        HBox headerBox = ViewConstants.loginHeader();
        borderPane.setTop(headerBox);

        // DELETE ICON

        Text deleteIcon = new Text("⚠");

        deleteIcon.setStyle(
                "-fx-font-size: 80px;" +
                        "-fx-fill: #D9534F;");

        // HEADING

        Text titleText = new Text("Delete Your Account?");

        titleText.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #202020;");

        // DESCRIPTION

        Text descriptionText = new Text(
                "Are you sure you want to permanently delete your account?\n" +
                        "This action cannot be undone.");

        descriptionText.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER);

        descriptionText.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #555555;");

        // WARNING BOX

        Text warningIcon = new Text("⚠");

        warningIcon.setStyle(
                "-fx-font-size: 22px;" +
                        "-fx-fill: #D9534F;");

        Text warningTitle = new Text("IMPORTANT");

        warningTitle.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #8B1E1E;");

        Text warningText = new Text(
                "Deleting your account will permanently remove\n" +
                        "your shop information, products, and account data.\n" +
                        "You will not be able to recover this information.");

        warningText.setStyle(
                "-fx-font-size: 12px;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-fill: #555555;");

        VBox warningTextBox = new VBox(
                5,
                warningTitle,
                warningText);

        warningTextBox.setAlignment(Pos.CENTER_LEFT);

        HBox warningContent = new HBox(
                14,
                warningIcon,
                warningTextBox);

        warningContent.setAlignment(Pos.CENTER_LEFT);

        warningContent.setPadding(
                new Insets(15, 18, 15, 18));

        VBox warningBox = new VBox(warningContent);

        warningBox.setPrefWidth(345);
        warningBox.setPrefHeight(125);

        warningBox.setAlignment(Pos.CENTER_LEFT);

        warningBox.setStyle(
                "-fx-background-color: #FFF3F3;" +
                        "-fx-border-color: #F3CACA;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 13px;" +
                        "-fx-background-radius: 13px;");

        // DELETE ACCOUNT BUTTON

        Button deleteButton = new Button("Delete Account");

        deleteButton.setPrefWidth(345);
        deleteButton.setPrefHeight(47);

        deleteButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #B71C1C, #E53935);" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-background-radius: 25px;" +
                        "-fx-cursor: hand;");

        deleteButton.setOnAction(e -> {

            System.out.println("Delete account button clicked");

            ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
    
                    shopkeeperDAO.deleteAccount();
                    ShopkeeperLogController.resetRegistration();
                    System.out.println("Account deleted");
                    Homepage.HomepageStage.setScene(            
                            ShopkeeperLogin.loginscene());  
        });

        // BACK BUTTON

        Button backButton = new Button("← Back");

        backButton.setPrefWidth(345);
        backButton.setPrefHeight(47);

        backButton.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #777777;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-radius: 25px;" +
                        "-fx-background-radius: 25px;" +
                        "-fx-text-fill: #555555;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-family: 'Arial';" +
                        "-fx-cursor: hand;");

        backButton.setOnAction(e -> {

            System.out.println("Back button clicked");

            Homepage.HomepageStage.setScene(
                    ShopkeeperSettings.settingsScene());

        });

        // SUCCESS / CONFIRMATION CARD

        VBox confirmationCard = new VBox(
                18,
                deleteIcon,
                titleText,
                descriptionText,
                warningBox,
                deleteButton,
                backButton);

        confirmationCard.setAlignment(Pos.TOP_CENTER);

        confirmationCard.setPadding(
                new Insets(25, 30, 25, 30));

        confirmationCard.setMaxWidth(425);

        confirmationCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                        "-fx-background-radius: 25px;");

        // CARD SHADOW

        DropShadow cardShadow = new DropShadow();

        cardShadow.setRadius(25);
        cardShadow.setSpread(0.02);

        cardShadow.setColor(
                Color.rgb(70, 50, 40, 0.10));

        confirmationCard.setEffect(cardShadow);

        // CENTER CONTENT

        VBox centerContent = new VBox(confirmationCard);

        centerContent.setAlignment(Pos.TOP_CENTER);

        centerContent.setPadding(
                new Insets(10, 0, 10, 0));

        centerContent.setStyle(
                "-fx-background-color: #F8FBF8;");

        centerContent.setPrefWidth(1280);

        // SET CENTER

        borderPane.setCenter(centerContent);

        // FOOTER

        VBox footerBox = ViewConstants.loginFooter();

        borderPane.setBottom(footerBox);

        // SCENE

        deleteAccountScene = new Scene(
                borderPane,
                1280,
                650);

        deleteAccountScene.setFill(
                Color.web("#F8FBF8"));

        return deleteAccountScene;
    }

}

package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Constants {


    public static VBox loginFooter(){

                // FOOTER

                Text copyrightText = new Text("© 2024 BuyNeX Hyperlocal Ecosystem. All rights reserved.");
                copyrightText.setStyle("-fx-font-size: 10px;-fx-fill: #777777;");

                Text privacyText = new Text("PRIVACY POLICY");
                Text termsText = new Text("TERMS OF SERVICE");

                HBox footerLinks = new HBox(25, privacyText, termsText);
                footerLinks.setAlignment(Pos.CENTER);

                VBox footerBox = new VBox(12, copyrightText, footerLinks);
                footerBox.setAlignment(Pos.CENTER);
                footerBox.setPrefHeight(60);
                footerBox.setStyle(
                                "-fx-background-color: #FFFDF9;-fx-border-color: #F0E6E0;-fx-border-width: 1px 0 0 0;");

                return footerBox;
        }

    public static HBox loginHeader(){
                // ============================================================
                // HEADER
                // ============================================================

                // HEADER

                Text headerBuyNex = new Text("BuyNeX");
                headerBuyNex.setStyle("-fx-font-size: 36px;-fx-font-weight: bold;-fx-font-family: 'Arial';");

                LinearGradient orangeGradient = new LinearGradient(
                                0, 0,
                                1, 0,
                                true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#A62B0A")),
                                new Stop(0.45, Color.web("#D94D0A")),
                                new Stop(1, Color.web("#F4770A")));
                headerBuyNex.setFill(orangeGradient);

                HBox headerLeftBox = new HBox(headerBuyNex);
                headerLeftBox.setAlignment(Pos.CENTER_LEFT);
                headerLeftBox.setPadding(new Insets(0, 0, 0, 30));

                Button needHelpButton = new Button("Need help?");

                needHelpButton.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #714B43;-fx-font-size: 13px;-fx-font-family: 'Arial';-fx-cursor: hand;");

                Button supportButton = new Button("ⓘ  Support");

                supportButton.setStyle(
                                "-fx-background-color: transparent;-fx-text-fill: #A83B16;-fx-font-size: 13px;-fx-font-weight: bold;-fx-font-family: 'Arial';-fx-cursor: hand;");

                HBox headerRightBox = new HBox(12, needHelpButton, supportButton);

                headerRightBox.setAlignment(Pos.CENTER_RIGHT);

                headerRightBox.setPadding(new Insets(0, 29, 0, 0));

                HBox headerMainBox = new HBox(headerLeftBox, headerRightBox);
                HBox.setHgrow(headerLeftBox, javafx.scene.layout.Priority.ALWAYS);
                HBox.setHgrow(headerRightBox, javafx.scene.layout.Priority.ALWAYS);

                headerMainBox.setAlignment(Pos.CENTER);

                headerMainBox.setPrefHeight(60);

                headerMainBox.setStyle(
                                "-fx-background-color: #F8FBF8;-fx-border-color: #F1E8E1;-fx-border-width: 0 0 1px 0;");

                return headerMainBox;
        }



    public static HBox header(){
                // ============================================================
                // HEADER
                // ============================================================

                Text headerBuyNex = new Text("BuyNeX");
                headerBuyNex.setStyle(
                                "-fx-font-size: 36px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';");

                LinearGradient orangeGradient = new LinearGradient(
                                0, 0,
                                1, 0,
                                true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#A62B0A")),
                                new Stop(0.45, Color.web("#D94D0A")),
                                new Stop(1, Color.web("#F4770A")));
                headerBuyNex.setFill(orangeGradient);

                HBox headerLeftBox = new HBox(headerBuyNex);
                headerLeftBox.setAlignment(Pos.CENTER_LEFT);
                headerLeftBox.setPadding(new Insets(0, 0, 0, 25));

                // ============================================================
                // HEADER ICONS
                // ============================================================

                Button notificationIcon = new Button("🔔 Notifications");
                notificationIcon.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: #555555;-fx-cursor: hand;-fx-background-color: transparent;");

                Button botIcon = new Button("🤖 ChatBot");
                botIcon.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: #555555;-fx-cursor: hand;-fx-background-color: transparent;");

                Button profileIcon = new Button("👤 Profile");
                profileIcon.setStyle(
                                "-fx-font-size: 15px;" +
                                                "-fx-fill: #555555;-fx-cursor: hand;-fx-background-color: transparent;");

                HBox headerIcons = new HBox(
                                25,
                                notificationIcon,
                                botIcon,
                                profileIcon);

                headerIcons.setAlignment(Pos.CENTER_RIGHT);

                headerIcons.setPadding(
                                new Insets(0, 25, 0, 0));

                HBox headerMainBox = new HBox(
                                headerLeftBox,
                                headerIcons);

                HBox.setHgrow(headerLeftBox, Priority.ALWAYS);
                HBox.setHgrow(headerIcons, Priority.ALWAYS);

                headerMainBox.setAlignment(Pos.CENTER);
                headerMainBox.setPrefHeight(70);
                headerMainBox.setStyle(
                                "-fx-background-color: #F8FBF8;-fx-border-color: #F1E8E1;-fx-border-width: 0 0 1px 0;");

                // ============================================================
                // HEADER ICONS BUTTON ACTIONS
                // ============================================================

                notificationIcon.setOnMouseClicked(e -> {
                        System.out.println("Notification Icon clicked");
                        Main.primaryStage.setScene(ShopkeeperNotification.notificationScene());
                });

                botIcon.setOnMouseClicked(e -> {
                        System.out.println("ChatBot Icon clicked");
                        Main.primaryStage.setScene(ShopkeeperChatbot.chatbotScene());
                });

                profileIcon.setOnMouseClicked(e -> {
                        System.out.println("Profile Icon clicked");
                        Main.primaryStage.setScene(ShopkeeperProfile.profileScene());
                });

                return headerMainBox;
    }


    public static HBox letfProfileBox(){

        
                Circle profileCircle = new Circle(21);
                profileCircle.setFill(Color.web("#FF6A00"));


                Text profileInitials = new Text("SK");


                profileInitials.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #6B2E0B;");

                StackPane profileIconBox = new StackPane(
                                profileCircle,
                                profileInitials);

                Text shopkeeperName = new Text("BuyNeX Shopkeeper");
                shopkeeperName.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #963A12;" +
                                                "-fx-font-family: 'Arial';");

                Text merchantText = new Text("Premium Merchant");
                merchantText.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #555555;" +
                                                "-fx-font-family: 'Arial';");

                VBox profileTextBox = new VBox(
                                2,
                                shopkeeperName,
                                merchantText);

                HBox profileBox = new HBox(
                                12,
                                profileIconBox,
                                profileTextBox);

                return profileBox;
    
                }




    public static VBox logoutBox(){
                Button logoutButton = new Button("Account Logout");
                logoutButton.setPrefWidth(230);
                logoutButton.setPrefHeight(42);
                logoutButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                                                "-fx-text-fill: white;" +
                                                "-fx-font-size: 14px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-cursor: hand;");
                logoutButton.setOnAction(e -> {
                        System.out.println("Add Product clicked");
                });
                VBox logoutBox = new VBox(logoutButton);
                logoutBox.setAlignment(Pos.CENTER);
                logoutBox.setPadding(new Insets(10, 15, 25, 15));


                return logoutBox;
    }


    public static VBox footer(){

                // FOOTER

                Text copyrightText = new Text("© 2026 BuyNeX Hyperlocal Ecosystem. All rights reserved.");
                copyrightText.setStyle("-fx-font-size: 10px;-fx-fill: #777777;");

                Text privacyText = new Text("PRIVACY POLICY");
                Text termsText = new Text("TERMS OF SERVICE");
                HBox footerLinks = new HBox(25, privacyText, termsText);
                footerLinks.setAlignment(Pos.CENTER);

                VBox footerBox = new VBox(12, copyrightText, footerLinks);
                footerBox.setAlignment(Pos.CENTER);

                footerBox.setPrefHeight(60);

                footerBox.setStyle(
                                "-fx-background-color: #FFFDF9;-fx-border-color: #F0E6E0;-fx-border-width: 1px 0 0 0;");
                return footerBox;
    }    


    // ================================================================
    // ORDER STATUS BUTTONS
    // ================================================================

   public static void setSelectedStatusButton(
            Button selectedButton) {

        if (selectedButton.getParent()
                instanceof HBox) {

            HBox parent =
                    (HBox) selectedButton.getParent();

            for (javafx.scene.Node node :
                    parent.getChildren()) {

                if (node instanceof Button) {

                    Button button =
                            (Button) node;

                    button.setStyle(
                            "-fx-background-color: transparent;" +
                            "-fx-text-fill: #604D43;" +
                            "-fx-font-size: 15px;" +
                            "-fx-font-weight: normal;" +
                            "-fx-border-color: transparent;" +
                            "-fx-border-width: 0 0 3px 0;" +
                            "-fx-background-radius: 0;" +
                            "-fx-cursor: hand;");
                }
            }
        }

        selectedButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #B94F00;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: normal;" +
                "-fx-border-color: #B94F00;" +
                "-fx-border-width: 0 0 3px 0;" +
                "-fx-background-radius: 0;" +
                "-fx-cursor: hand;");
    }


    // ================================================================
    // ORDER STATUS BUTTONS 
    // ================================================================

    public static Button createStatusButton(
            String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(70);

        button.setMaxHeight(
                Double.MAX_VALUE);

        button.setMaxWidth(
                Double.MAX_VALUE);

        button.setAlignment(
                Pos.CENTER);

        button.setWrapText(true);

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #604D43;" +
                "-fx-font-size: 15px;" +
                "-fx-font-weight: normal;" +
                "-fx-border-color: transparent;" +
                "-fx-border-width: 0 0 3px 0;" +
                "-fx-background-radius: 0;" +
                "-fx-cursor: hand;");

        return button;
    }

}

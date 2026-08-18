
package com.kryox.view;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShopkeeperSupport {

    public static Scene supportScene() {

        // ============================================================
        // BORDER PANE
        // ============================================================


        BorderPane borderPane = new BorderPane();


        // ================================================================
        // MAIN BORDER PANE
        // ================================================================

                HBox headerMainBox = Constants.header();
                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = new VBox();
                sidebar.setMinWidth(260);
                sidebar.setMaxWidth(260);
                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                // ============================================================
                // SHOPKEEPER PROFILE
                // ============================================================

            HBox profileBox = Constants.letfProfileBox();
            profileBox.setAlignment(Pos.CENTER_LEFT);
            profileBox.setPadding(new Insets(30, 20, 30, 20));

                // ============================================================
                // DASHBOARD BUTTON
                // ============================================================

                Text dashboardIcon = new Text("▦");
                dashboardIcon.setStyle(
                                "-fx-font-size: 29px;" +
                                                "-fx-fill: #333333;");
                Text dashboardText = new Text("Dashboard");
                dashboardText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #333333;");
                HBox dashboardContent = new HBox(
                                16,
                                dashboardIcon,
                                dashboardText);
                dashboardContent.setAlignment(Pos.CENTER_LEFT);
                Button dashboardButton = new Button();
                dashboardButton.setGraphic(dashboardContent);
                dashboardButton.setPrefWidth(244);
                dashboardButton.setPrefHeight(51);
                dashboardButton.setAlignment(Pos.CENTER_LEFT);
                dashboardButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");

                dashboardButton.setOnMouseEntered(e -> {
                        dashboardButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                dashboardButton.setOnMouseExited(e -> {
                        dashboardButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

                // ============================================================
                // ORDERS BUTTON
                // ============================================================

                Text ordersIcon = new Text("🛒");
                ordersIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-fill: #555555;");
                Text ordersText = new Text("Orders");
                ordersText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox ordersContent = new HBox(
                                13,
                                ordersIcon,
                                ordersText);
                ordersContent.setAlignment(Pos.CENTER_LEFT);
                Button ordersButton = new Button();
                ordersButton.setGraphic(ordersContent);
                ordersButton.setPrefWidth(244);
                ordersButton.setPrefHeight(51);
                ordersButton.setAlignment(Pos.CENTER_LEFT);
                ordersButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");

                ordersButton.setOnMouseEntered(e -> {
                        ordersButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                ordersButton.setOnMouseExited(e -> {
                        ordersButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

                // ============================================================
                // INVENTORY BUTTON
                // ============================================================

                Text inventoryIcon = new Text("📋");
                inventoryIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #555555;");
                Text inventoryText = new Text("Inventory");
                inventoryText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox inventoryContent = new HBox(
                                15,
                                inventoryIcon,
                                inventoryText);
                inventoryContent.setAlignment(Pos.CENTER_LEFT);
                Button inventoryButton = new Button();
                inventoryButton.setGraphic(inventoryContent);
                inventoryButton.setPrefWidth(244);
                inventoryButton.setPrefHeight(51);
                inventoryButton.setAlignment(Pos.CENTER_LEFT);
                inventoryButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");

                inventoryButton.setOnMouseEntered(e -> {
                        inventoryButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                inventoryButton.setOnMouseExited(e -> {
                        inventoryButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });


                // ============================================================
                // OFFERS BUTTON
                // ============================================================

                Text offersIcon = new Text("🎁");
                offersIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #555555;");
                Text offersText = new Text("Offers");
                offersText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox offersContent = new HBox(
                                14,
                                offersIcon,
                                offersText);
                offersContent.setAlignment(Pos.CENTER_LEFT);
                Button offersButton = new Button();
                offersButton.setGraphic(offersContent);
                offersButton.setPrefWidth(244);
                offersButton.setPrefHeight(51);
                offersButton.setAlignment(Pos.CENTER_LEFT);
                offersButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");
                offersButton.setOnMouseEntered(e -> {
                        offersButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                offersButton.setOnMouseExited(e -> {
                        offersButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

                // ============================================================
                // ANALYTICS BUTTON
                // ============================================================

                Text analyticsIcon = new Text("📊");
                analyticsIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #555555;");
                Text analyticsText = new Text("Analytics");
                analyticsText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox analyticsContent = new HBox(
                                15,
                                analyticsIcon,
                                analyticsText);
                analyticsContent.setAlignment(Pos.CENTER_LEFT);
                Button analyticsButton = new Button();
                analyticsButton.setGraphic(analyticsContent);
                analyticsButton.setPrefWidth(244);
                analyticsButton.setPrefHeight(51);
                analyticsButton.setAlignment(Pos.CENTER_LEFT);
                analyticsButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");
                analyticsButton.setOnMouseEntered(e -> {
                        analyticsButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                analyticsButton.setOnMouseExited(e -> {
                        analyticsButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });



                // ============================================================
                // SETTINGS BUTTON
                // ============================================================

                Text settingsIcon = new Text("⚙");
                settingsIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #555555;");
                Text settingsText = new Text("Settings");
                settingsText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox settingsContent = new HBox(
                                15,
                                settingsIcon,
                                settingsText);
                settingsContent.setAlignment(Pos.CENTER_LEFT);
                Button settingsButton = new Button();
                settingsButton.setGraphic(settingsContent);
                settingsButton.setPrefWidth(244);
                settingsButton.setPrefHeight(51);
                settingsButton.setAlignment(Pos.CENTER_LEFT);
                settingsButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");
                settingsButton.setOnMouseEntered(e -> {
                        settingsButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                settingsButton.setOnMouseExited(e -> {
                        settingsButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

                // ============================================================
                // SUPPORT BUTTON
                // ============================================================

                Text supportIcon = new Text("?");
                supportIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-fill: #555555;");
                Text supportText = new Text("Support");
                supportText.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #555555;");
                HBox supportContent = new HBox(
                                20,
                                supportIcon,
                                supportText);
                supportContent.setAlignment(Pos.CENTER_LEFT);
                Button supportButton = new Button();
                supportButton.setGraphic(supportContent);
                supportButton.setPrefWidth(244);
                supportButton.setPrefHeight(51);
                supportButton.setAlignment(Pos.CENTER_LEFT);
                supportButton.setStyle(
                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");

                VBox sidebarMenu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                sidebarMenu.setPadding(new Insets(0, 8, 0, 8));

                // ============================================================
                // LOGOUT BUTTON
                // ============================================================


                VBox logoutBox = Constants.logoutBox();

                VBox.setVgrow(
                                sidebarMenu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                sidebarMenu,
                                logoutBox);
                borderPane.setLeft(sidebar);

                // ============================================================
                // BUTTON ACTIONS
                // ============================================================

                dashboardButton.setOnAction(e -> {

                        System.out.println("Dashboard clicked");
                        Main.primaryStage.setScene(ShopkeeperDashboard.dashboardScene());

                });

                ordersButton.setOnAction(e -> {

                        System.out.println("Orders clicked");
                        Main.primaryStage.setScene(ShopkeeperOrderReady.ordersScene());


                });

                inventoryButton.setOnAction(e -> {

                        System.out.println("Inventory clicked");
                        Main.primaryStage.setScene(ShopkeeperInventory.inventoryScene());

                });

                offersButton.setOnAction(e -> {

                        System.out.println("Offers clicked");
                        Main.primaryStage.setScene(ShopkeeperOffers.offersScene());
                });

                analyticsButton.setOnAction(e -> {

                        System.out.println("Analytics clicked");
                        Main.primaryStage.setScene(ShopkeeperAnalytics.analyticsScene());

                });

                settingsButton.setOnAction(e -> {

                        System.out.println("Settings clicked");
                        Main.primaryStage.setScene(ShopkeeperSettings.settingsScene());

                });

                supportButton.setOnAction(e -> {

                        System.out.println("Support clicked");

                });





        // ================================================================
        // FOOTER
        // ================================================================

                VBox footerBox = Constants.footer();
                // SET BOTTOM
                borderPane.setBottom(footerBox);






        // ============================================================
        // SCENE
        // ============================================================

        Scene supportScene = new Scene(
                borderPane,
                1280,
                650
        );

        supportScene.setFill(
                Color.web("#F8F7FC")
        );

        return supportScene;
    }
}
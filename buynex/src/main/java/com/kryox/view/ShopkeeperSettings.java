




package com.kryox.view;

import com.kryox.Main;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ShopkeeperSettings {

        public static Scene settingsScene() {

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
                                "-fx-background-color: #FF6900;" +
                                                "-fx-background-radius: 9px;" +
                                                "-fx-cursor: hand;");


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
                                "-fx-background-color: transparent;" +
                                                "-fx-cursor: hand;");
                supportButton.setOnMouseEntered(e -> {
                        supportButton.setStyle(
                                        "-fx-background-color: #FF6900;" +
                                                        "-fx-background-radius: 9px;" +
                                                        "-fx-cursor: hand;");
                });

                supportButton.setOnMouseExited(e -> {
                        supportButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-cursor: hand;");
                });

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

                });

                supportButton.setOnAction(e -> {

                        System.out.println("Support clicked");
                        Main.primaryStage.setScene(ShopkeeperSupport.supportScene());

                });





        // ================================================================
        // FOOTER
        // ================================================================

                VBox footerBox = Constants.footer();
                // SET BOTTOM
                borderPane.setBottom(footerBox);























                // ============================================================
                // ONLY CENTER CONTENT CHANGES FROM DASHBOARD
                // ============================================================

                Text settingsTitle =
                                new Text("Settings Dashboard");

                settingsTitle.setStyle(
                                "-fx-font-size: 31px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-font-family: 'Arial';" +
                                "-fx-fill: #f10b0b;");

                // ============================================================
                // SETTINGS LEFT SIDE NAVIGATION BOX
                // ============================================================
//---------------------------------------------------------------------------------
                
                //STORE PROFILE BUTTON

//---------------------------------------------------------------------------------

                Text storeProfileIcon =
                                new Text("▤");

                storeProfileIcon.setStyle(
                                "-fx-font-size: 22px;" +
                                "-fx-fill: #963A12;");

                Text storeProfileText =
                                new Text("Store Profile");

                storeProfileText.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-fill: #963A12;");

                HBox storeProfileContent =
                                new HBox(
                                                15,
                                                storeProfileIcon,
                                                storeProfileText);

                storeProfileContent.setAlignment(
                                Pos.CENTER_LEFT);

                Button storeProfileButton =
                                new Button();

                storeProfileButton.setGraphic(
                                storeProfileContent);

                storeProfileButton.setPrefWidth(220);
                storeProfileButton.setPrefHeight(52);

                storeProfileButton.setAlignment(
                                Pos.CENTER_LEFT);

                storeProfileButton.setStyle(
                                "-fx-background-color: #F5F2F5;" +
                                "-fx-border-color: #D8C9C2;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;");



//---------------------------------------------------------------------------------

                //PAYOUT BUTTON

////---------------------------------------------------------------------------------


                Text payoutIcon =
                                new Text("₹");

                payoutIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                "-fx-fill: #555555;");

                Text payoutText =
                                new Text("Payout Settings");

                payoutText.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #555555;");

                HBox payoutContent =
                                new HBox(
                                                15,
                                                payoutIcon,
                                                payoutText);

                payoutContent.setAlignment(
                                Pos.CENTER_LEFT);

                Button payoutButton =
                                new Button();

                payoutButton.setGraphic(
                                payoutContent);

                payoutButton.setPrefWidth(220);
                payoutButton.setPrefHeight(52);

                payoutButton.setAlignment(
                                Pos.CENTER_LEFT);

                payoutButton.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-cursor: hand;");

//---------------------------------------------------------------------------------

                //NOTIFICATION BUTTON

//---------------------------------------------------------------------------------

                Text notificationSettingsIcon =
                                new Text("🔔");

                notificationSettingsIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                "-fx-fill: #555555;");

                Text notificationSettingsText =
                                new Text(
                                                "Notification\nPreferences");

                notificationSettingsText.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #555555;");

                HBox notificationSettingsContent =
                                new HBox(
                                                15,
                                                notificationSettingsIcon,
                                                notificationSettingsText);

                notificationSettingsContent.setAlignment(
                                Pos.CENTER_LEFT);

                Button notificationSettingsButton =
                                new Button();

                notificationSettingsButton.setGraphic(
                                notificationSettingsContent);

                notificationSettingsButton.setPrefWidth(220);
                notificationSettingsButton.setPrefHeight(62);

                notificationSettingsButton.setAlignment(
                                Pos.CENTER_LEFT);

                notificationSettingsButton.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-cursor: hand;");

//---------------------------------------------------------------------------------

                //SETTINGS NAVIGATION BOX

//---------------------------------------------------------------------------------

                VBox settingsNavigation =
                                new VBox(
                                                5,
                                                storeProfileButton,
                                                payoutButton,
                                                notificationSettingsButton);

                settingsNavigation.setPadding(
                                new Insets(15));

                settingsNavigation.setPrefWidth(245);
                settingsNavigation.setMaxHeight(300);

                settingsNavigation.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                "-fx-border-color: #E0D7D2;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 17px;" +
                                "-fx-background-radius: 17px;");

                // ============================================================
                // STORE PROFILE CARD
                // ============================================================

                Text storeProfileTitle =
                                new Text("Store Profile");

                storeProfileTitle.setStyle(
                                "-fx-font-size: 27px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-fill: #151515;");

                Text storeProfileDescription =
                                new Text(
                                                "Manage how your store appears to customers on\n" +
                                                "BuyNeX.");

                storeProfileDescription.setStyle(
                                "-fx-font-size: 15px;" +
                                "-fx-fill: #666666;");

                VBox storeProfileHeading =
                                new VBox(
                                                5,
                                                storeProfileTitle,
                                                storeProfileDescription);

                // ============================================================
                // STORE LOGO
                // ============================================================

                Circle logoCircle =
                                new Circle(50);

                logoCircle.setFill(
                                Color.web("#FBFBFB"));

                logoCircle.setStroke(
                                Color.web("#E1DFE1"));

                logoCircle.setStrokeWidth(2);




                Text logoTitle =
                                new Text("Store Logo");

                logoTitle.setStyle(
                                "-fx-font-size: 15px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-fill: #222222;");

                Text logoDescription =
                                new Text(
                                                "Recommended size: 500×500px (JPG or PNG).");

                logoDescription.setStyle(
                                "-fx-font-size: 12px;" +
                                "-fx-fill: #666666;");




//--------------------------------------------------------------------------------
                //CHANGE LOGO BUTTON
//--------------------------------------------------------------------------------

                Button changeLogoButton =
                                new Button("Change Logo");

                changeLogoButton.setPrefWidth(135);
                changeLogoButton.setPrefHeight(40);

                changeLogoButton.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                "-fx-border-color: #333333;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 13px;" +
                                "-fx-cursor: hand;");

//--------------------------------------------------------------------------------
                //REMOVE LOGO BUTTON
//--------------------------------------------------------------------------------

                Button removeLogoButton =
                                new Button("Remove");

                removeLogoButton.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-text-fill: #A91D1D;" +
                                "-fx-font-size: 14px;" +
                                "-fx-cursor: hand;");

                HBox logoButtons =
                                new HBox(
                                                12,
                                                changeLogoButton,
                                                removeLogoButton);

                logoButtons.setAlignment(
                                Pos.CENTER_LEFT);

//------------------------------------------------------------------------------

                VBox logoInformation =
                                new VBox(
                                                5,
                                                logoTitle,
                                                logoDescription,
                                                logoButtons);

                logoInformation.setAlignment(
                                Pos.CENTER_LEFT);

                HBox logoSection =
                                new HBox(
                                                25,
                                                logoCircle,
                                                logoInformation);

                logoSection.setAlignment(
                                Pos.CENTER_LEFT);



                // ============================================================
                // STORE NAME
                // ============================================================

                Text storeNameLabel =
                                new Text("Store Name");

                storeNameLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #222222;");

                TextField storeNameField =
                                new TextField(
                                                "Buynex Store Name");

                storeNameField.setPrefHeight(50);

                storeNameField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 0 14px;");

                VBox storeNameBox =
                                new VBox(
                                                7,
                                                storeNameLabel,
                                                storeNameField);

                // ============================================================
                // CATEGORY
                // ============================================================

                Text categoryLabel =
                                new Text("Category");

                categoryLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #222222;");

                ComboBox<String> categoryComboBox =
                                new ComboBox<>();

                categoryComboBox.getItems().addAll(
                                "Grocery & Essentials",
                                "Fresh Produce",
                                "Bakery",
                                "Beverages",
                                "Household",
                                "Fashion"
                        );

                categoryComboBox.setValue(
                                "Grocery & Essentials");

                categoryComboBox.setPrefHeight(50);

                categoryComboBox.setMaxWidth(
                                Double.MAX_VALUE);

                categoryComboBox.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;");

                VBox categoryBox =
                                new VBox(
                                                7,
                                                categoryLabel,
                                                categoryComboBox);

                HBox.setHgrow(
                                storeNameBox,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                categoryBox,
                                Priority.ALWAYS);

                HBox storeBasicDetails =
                                new HBox(
                                                20,
                                                storeNameBox,
                                                categoryBox);

                // ============================================================
                // DESCRIPTION
                // ============================================================

                Text descriptionLabel =
                                new Text("Store Description");

                descriptionLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #222222;");

                TextArea descriptionArea =
                                new TextArea(
                                                "Premium organic vegetables, fruits, and daily " +
                                                "essentials delivered fresh to your neighborhood.");

                descriptionArea.setPrefHeight(105);

                descriptionArea.setWrapText(true);

                descriptionArea.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 12px;");

                VBox descriptionBox =
                                new VBox(
                                                7,
                                                descriptionLabel,
                                                descriptionArea);

                // ============================================================
                // BUSINESS ADDRESS
                // ============================================================

                Text addressLabel =
                                new Text("Business Address");

                addressLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #222222;");

                TextField addressField =
                                new TextField(
                                                "123 Market Street, Suite 4B");

                addressField.setPrefHeight(50);

                addressField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 0 14px;");

                VBox addressBox =
                                new VBox(
                                                7,
                                                addressLabel,
                                                addressField);

                // ============================================================
                // CITY + ZIP
                // ============================================================

                TextField cityField =
                                new TextField(
                                                "San Francisco");

                cityField.setPrefHeight(50);

                cityField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 0 14px;");

                TextField pinCodeField =
                                new TextField(
                                                "CA 94105");

                pinCodeField.setPrefHeight(50);

                pinCodeField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 0 14px;");

                HBox cityZipBox =
                                new HBox(
                                                20,
                                                cityField,
                                                pinCodeField);

                HBox.setHgrow(
                                cityField,
                                Priority.ALWAYS);

                HBox.setHgrow(
                                pinCodeField,
                                Priority.ALWAYS);

                // ============================================================
                // PHONE
                // ============================================================

                Text phoneLabel =
                                new Text("Contact Phone");

                phoneLabel.setStyle(
                                "-fx-font-size: 14px;" +
                                "-fx-fill: #222222;");

                TextField phoneField =
                                new TextField(
                                                "+91 987329022");

                phoneField.setPrefHeight(50);

                phoneField.setStyle(
                                "-fx-background-color: #FAF7FB;" +
                                "-fx-border-color: #D7C8C0;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-font-size: 15px;" +
                                "-fx-padding: 0 14px;");

                VBox phoneBox =
                                new VBox(
                                                7,
                                                phoneLabel,
                                                phoneField);

                // ============================================================
                // SAVE BUTTONS
                // ============================================================

                Button discardButton =
                                new Button("Discard");

                discardButton.setPrefWidth(95);
                discardButton.setPrefHeight(42);

                discardButton.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-text-fill: #963A12;" +
                                "-fx-font-size: 14px;" +
                                "-fx-cursor: hand;");

                Button saveChangesButton =
                                new Button("Save Changes");

                saveChangesButton.setPrefWidth(155);
                saveChangesButton.setPrefHeight(42);

                saveChangesButton.setStyle(
                                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                                "-fx-text-fill: white;" +
                                "-fx-font-size: 14px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-background-radius: 8px;" +
                                "-fx-cursor: hand;");

                HBox saveButtons =
                                new HBox(
                                                10,
                                                discardButton,
                                                saveChangesButton);

                saveButtons.setAlignment(
                                Pos.CENTER_RIGHT);

                // ============================================================
                // STORE PROFILE CARD
                // ============================================================

                VBox storeProfileCard =
                                new VBox(
                                                22,
                                                storeProfileHeading,
                                                logoSection,
                                                // separator,
                                                storeBasicDetails,
                                                descriptionBox,
                                                addressBox,
                                                cityZipBox,
                                                phoneBox,
                                                saveButtons);

                storeProfileCard.setPadding(
                                new Insets(30));

                storeProfileCard.setPrefWidth(500);

                storeProfileCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                "-fx-border-color: #DDCFC8;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 20px;" +
                                "-fx-background-radius: 20px;");

                DropShadow shadow =
                                new DropShadow();

                shadow.setRadius(10);

                shadow.setColor(
                                Color.rgb(80, 60, 50, 0.08));

                storeProfileCard.setEffect(
                                shadow);

                // ============================================================
                // ACCOUNT SECURITY
                // ============================================================

                Text securityTitle =
                                new Text("Account Security");

                securityTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-fill: #171717;");

                Text securityIcon =
                                new Text("🔒");

                securityIcon.setStyle(
                                "-fx-font-size: 22px;" +
                                "-fx-fill: #963A12;");

                HBox securityHeading =
                                new HBox(
                                                securityTitle,
                                                securityIcon);

                HBox.setHgrow(
                                securityTitle,
                                Priority.ALWAYS);

                Text twoFactorTitle =
                                new Text("Two-Factor Auth");

                twoFactorTitle.setStyle(
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-fill: #333333;");

                Text twoFactorStatus =
                                new Text("Enabled via SMS");

                twoFactorStatus.setStyle(
                                "-fx-font-size: 12px;" +
                                "-fx-fill: #555555;");

                VBox twoFactorText =
                                new VBox(
                                                2,
                                                twoFactorTitle,
                                                twoFactorStatus);

                ToggleButton twoFactorToggle =
                                new ToggleButton();

                twoFactorToggle.setSelected(true);

                twoFactorToggle.setPrefWidth(47);
                twoFactorToggle.setPrefHeight(27);

                twoFactorToggle.setStyle(
                                "-fx-background-color: #A94408;" +
                                "-fx-background-radius: 20px;" +
                                "-fx-cursor: hand;");

                HBox twoFactorBox =
                                new HBox(
                                                10,
                                                twoFactorText,
                                                twoFactorToggle);

                HBox.setHgrow(
                                twoFactorText,
                                Priority.ALWAYS);

                twoFactorBox.setAlignment(
                                Pos.CENTER);

                twoFactorBox.setPadding(
                                new Insets(10));

                twoFactorBox.setStyle(
                                "-fx-background-color: #F5F3F7;" +
                                "-fx-border-color: #E2DDE2;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;");

                Button changePasswordButton =
                                new Button("Change Password");

                changePasswordButton.setStyle(
                                "-fx-background-color: transparent;" +
                                "-fx-text-fill: #963A12;" +
                                "-fx-font-size: 13px;" +
                                "-fx-font-weight: bold;" +
                                "-fx-cursor: hand;");

                VBox securityCard =
                                new VBox(
                                                15,
                                                securityHeading,
                                                twoFactorBox,
                                                changePasswordButton);

                securityCard.setPadding(
                                new Insets(22));

                securityCard.setPrefWidth(245);

                securityCard.setStyle(
                                "-fx-background-color: #FFFFFF;" +
                                "-fx-border-color: #DDCFC8;" +
                                "-fx-border-radius: 20px;" +
                                "-fx-background-radius: 20px;");



                // ============================================================
                // LEFT CONTENT
                // ============================================================

                VBox leftContent =
                                new VBox(
                                                25,
                                                settingsNavigation,
                                                securityCard
                                        );

                leftContent.setPrefWidth(245);

                // ============================================================
                // MAIN CENTER CONTENT
                // ============================================================

                /*
                 * IMPORTANT:
                 *
                 * Do NOT call this variable settingsContent.
                 *
                 * settingsContent already belongs to the left sidebar.
                 */



                HBox settingsMainContent =
                                new HBox(
                                                25,
                                                leftContent,
                                                storeProfileCard
                                        );

                settingsMainContent.setAlignment(
                                Pos.TOP_CENTER);

                VBox centerContent =
                                new VBox(
                                                25,
                                                settingsTitle,
                                                settingsMainContent);

                centerContent.setAlignment(
                                Pos.TOP_CENTER);

                centerContent.setPadding(
                                new Insets(25));

                centerContent.setStyle(
                                "-fx-background-color: #F8F7FC;");

                ScrollPane scrollPane =
                                new ScrollPane(
                                                centerContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                scrollPane.setStyle(
                                "-fx-background-color: #F8F7FC;" +
                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                scrollPane);

                // ============================================================
                // BUTTON ACTIONS
                // ============================================================




                storeProfileButton.setOnAction(e -> {

                        System.out.println(
                                        "Store Profile selected");
                });



                payoutButton.setOnAction(e -> {

                        System.out.println(
                                        "Payout Settings selected");
                });

                notificationSettingsButton.setOnAction(e -> {

                        System.out.println(
                                        "Notification Preferences selected");
                });

                changeLogoButton.setOnAction(e -> {

                        System.out.println(
                                        "Change Logo clicked");
                });

                removeLogoButton.setOnAction(e -> {

                        System.out.println(
                                        "Remove Logo clicked");
                });

                saveChangesButton.setOnAction(e -> {

                        System.out.println(
                                        "Save Changes clicked");
                });

                discardButton.setOnAction(e -> {

                        storeNameField.setText(
                                        "Green Groceries Store");

                        categoryComboBox.setValue(
                                        "Grocery & Essentials");

                        descriptionArea.setText(
                                        "Premium organic vegetables, fruits, and daily " +
                                        "essentials delivered fresh to your neighborhood.");

                        addressField.setText(
                                        "123 Market Street, Suite 4B");

                        cityField.setText(
                                        "San Francisco");

                        pinCodeField.setText(
                                        "CA 94105");

                        phoneField.setText(
                                        "+1 (555) 123-4567");
                });

                twoFactorToggle.setOnAction(e -> {

                        if (twoFactorToggle.isSelected()) {

                                twoFactorStatus.setText(
                                                "Enabled via SMS");

                        } else {

                                twoFactorStatus.setText(
                                                "Disabled");
                        }
                });

                changePasswordButton.setOnAction(e -> {

                        System.out.println(
                                        "Change Password clicked");
                });








                // ============================================================
                // SCENE
                // ============================================================

                Scene settingsScene =
                                new Scene(
                                                borderPane,
                                                1280,
                                                650);

                settingsScene.setFill(
                                Color.web("#F8F7FC"));

                return settingsScene;
        }
}
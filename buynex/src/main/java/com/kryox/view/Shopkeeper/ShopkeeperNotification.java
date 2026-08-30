package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;

public class ShopkeeperNotification {

        public static Scene notificationScene() {


                BorderPane borderPane = new BorderPane();

                // ============================================================
                // HEADER
                // ============================================================
                HBox headerMainBox = ViewConstants.header();

                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = createSidebar();
                borderPane.setLeft(sidebar);
                // ============================================================
                // CENTER - NOTIFICATIONS PAGE
                // ============================================================

                VBox notificationPage = new VBox();

                notificationPage.setAlignment(Pos.TOP_CENTER);

                notificationPage.setPadding(
                                new Insets(55, 40, 30, 40));

                notificationPage.setSpacing(25);

                notificationPage.setStyle(
                                "-fx-background-color: white;");

                // ============================================================
                // NOTIFICATION TITLE
                // ============================================================

                Text notificationTitle = new Text("Notifications");

                notificationTitle.setStyle(
                                "-fx-font-size: 26px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #202124;");

                Text titleBell = new Text("♧");

                titleBell.setText("♧");

                titleBell.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-fill: #624638;");

                titleBell.setText("♧");

                HBox titleBox = new HBox();

                titleBox.setPrefWidth(570);

                titleBox.setAlignment(Pos.CENTER);

                titleBox.getChildren().add(notificationTitle);

                StackPane titleContainer = new StackPane();

                titleContainer.setPrefWidth(570);

                titleContainer.setPrefHeight(45);

                titleContainer.getChildren().add(titleBox);

                StackPane.setAlignment(
                                titleBox,
                                Pos.CENTER);

                Text bellRight = new Text("♧");

                bellRight.setText("♧");

                bellRight.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-fill: #624638;");

                titleContainer.getChildren().add(bellRight);

                StackPane.setAlignment(
                                bellRight,
                                Pos.CENTER_RIGHT);

                // ============================================================
                // NOTIFICATION LIST
                // ============================================================

                VBox notificationList = new VBox();

                notificationList.setSpacing(12);

                notificationList.setPadding(
                                new Insets(0));

                notificationList.setPrefWidth(570);

                notificationList.setMaxWidth(570);

                // ============================================================
                // NOTIFICATION 1
                // ============================================================

                HBox orderIconCircle = new HBox();

                orderIconCircle.setPrefWidth(44);
                orderIconCircle.setPrefHeight(44);

                orderIconCircle.setMinWidth(44);
                orderIconCircle.setMinHeight(44);

                orderIconCircle.setMaxWidth(44);
                orderIconCircle.setMaxHeight(44);

                orderIconCircle.setAlignment(Pos.CENTER);

                orderIconCircle.setStyle(
                                "-fx-background-color: #F1E6E0;" +
                                                "-fx-background-radius: 50px;");

                Text orderIcon = new Text("🛒");

                orderIcon.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-fill: #C85A0A;");

                orderIconCircle.getChildren().add(orderIcon);

                Text orderTitle = new Text("New Order #4592");

                orderTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #292929;");

                Text orderTime = new Text("2 minutes ago");

                orderTime.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #765F55;");

                VBox orderTextBox = new VBox(
                                6,
                                orderTitle,
                                orderTime);

                orderTextBox.setAlignment(Pos.CENTER_LEFT);

                HBox orderNotification = new HBox(
                                24,
                                orderIconCircle,
                                orderTextBox);

                orderNotification.setAlignment(
                                Pos.CENTER_LEFT);

                orderNotification.setPadding(
                                new Insets(20, 25, 20, 25));

                orderNotification.setPrefWidth(570);
                orderNotification.setPrefHeight(104);

                orderNotification.setStyle(
                                "-fx-background-color: #F5F4F8;" +
                                                "-fx-background-radius: 12px;");

                // ============================================================
                // NOTIFICATION 2
                // ============================================================

                HBox stockIconCircle = new HBox();

                stockIconCircle.setPrefWidth(44);
                stockIconCircle.setPrefHeight(44);

                stockIconCircle.setMinWidth(44);
                stockIconCircle.setMinHeight(44);

                stockIconCircle.setMaxWidth(44);
                stockIconCircle.setMaxHeight(44);

                stockIconCircle.setAlignment(Pos.CENTER);

                stockIconCircle.setStyle(
                                "-fx-background-color: #F7E4E6;" +
                                                "-fx-background-radius: 50px;");

                Text stockIcon = new Text("⚠");

                stockIcon.setStyle(
                                "-fx-font-size: 23px;" +
                                                "-fx-fill: #D92F2F;");

                stockIconCircle.getChildren().add(stockIcon);

                Text stockTitle = new Text(
                                "Low Stock Alert: Organic Milk");

                stockTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #292929;");

                Text stockTime = new Text("15 minutes ago");

                stockTime.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #765F55;");

                VBox stockTextBox = new VBox(
                                6,
                                stockTitle,
                                stockTime);

                stockTextBox.setAlignment(
                                Pos.CENTER_LEFT);

                HBox stockNotification = new HBox(
                                24,
                                stockIconCircle,
                                stockTextBox);

                stockNotification.setAlignment(
                                Pos.CENTER_LEFT);

                stockNotification.setPadding(
                                new Insets(20, 25, 20, 25));

                stockNotification.setPrefWidth(570);
                stockNotification.setPrefHeight(104);

                stockNotification.setStyle(
                                "-fx-background-color: #F5F4F8;" +
                                                "-fx-background-radius: 12px;");

                // ============================================================
                // NOTIFICATION 3
                // ============================================================

                HBox aiIconCircle = new HBox();

                aiIconCircle.setPrefWidth(44);
                aiIconCircle.setPrefHeight(44);

                aiIconCircle.setMinWidth(44);
                aiIconCircle.setMinHeight(44);

                aiIconCircle.setMaxWidth(44);
                aiIconCircle.setMaxHeight(44);

                aiIconCircle.setAlignment(Pos.CENTER);

                aiIconCircle.setStyle(
                                "-fx-background-color: #F1E6E0;" +
                                                "-fx-background-radius: 50px;");

                Text aiIcon = new Text("▣");

                aiIcon.setStyle(
                                "-fx-font-size: 23px;" +
                                                "-fx-fill: #C85A0A;");

                aiIconCircle.getChildren().add(aiIcon);

                Text aiTitle = new Text(
                                "AI Insight: Sales up 12%");

                aiTitle.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #292929;");

                Text aiTime = new Text("1 hour ago");

                aiTime.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-family: 'Arial';" +
                                                "-fx-fill: #765F55;");

                VBox aiTextBox = new VBox(
                                6,
                                aiTitle,
                                aiTime);

                aiTextBox.setAlignment(
                                Pos.CENTER_LEFT);

                HBox aiNotification = new HBox(
                                24,
                                aiIconCircle,
                                aiTextBox);

                aiNotification.setAlignment(
                                Pos.CENTER_LEFT);

                aiNotification.setPadding(
                                new Insets(20, 25, 20, 25));

                aiNotification.setPrefWidth(570);
                aiNotification.setPrefHeight(104);

                aiNotification.setStyle(
                                "-fx-background-color: #F5F4F8;" +
                                                "-fx-background-radius: 12px;");

                // ============================================================
                // ADD NOTIFICATIONS TO LIST
                // ============================================================

                notificationList.getChildren().addAll(
                                orderNotification,
                                stockNotification,
                                aiNotification);

                // ============================================================
                // SCROLL PANE
                // ============================================================

                ScrollPane notificationScrollPane = new ScrollPane(notificationList);

                notificationScrollPane.setPrefWidth(590);
                notificationScrollPane.setMaxWidth(590);

                notificationScrollPane.setPrefHeight(365);
                notificationScrollPane.setMaxHeight(365);

                notificationScrollPane.setFitToWidth(true);

                notificationScrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                notificationScrollPane.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                notificationScrollPane.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-background: transparent;" +
                                                "-fx-border-color: transparent;");

                // ============================================================
                // VIEW ALL ACTIVITY BUTTON
                // ============================================================

                Button viewAllButton = new Button("View All Activity");

                viewAllButton.setPrefWidth(150);
                viewAllButton.setPrefHeight(40);

                viewAllButton.setStyle(
                                "-fx-background-color: transparent;" +
                                                "-fx-text-fill: #B44A0A;" +
                                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-cursor: hand;");

                viewAllButton.setOnMouseEntered(e -> {

                        viewAllButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #8F3505;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-cursor: hand;");

                });

                viewAllButton.setOnMouseExited(e -> {

                        viewAllButton.setStyle(
                                        "-fx-background-color: transparent;" +
                                                        "-fx-text-fill: #B44A0A;" +
                                                        "-fx-font-size: 13px;" +
                                                        "-fx-font-weight: bold;" +
                                                        "-fx-cursor: hand;");

                });

                viewAllButton.setOnAction(e -> {

                        System.out.println(
                                        "View All Activity clicked");

                });

                // ============================================================
                // ADD CENTER COMPONENTS
                // ============================================================

                notificationPage.getChildren().addAll(
                                titleContainer,
                                notificationScrollPane,
                                viewAllButton);

                // ============================================================
                // SET CENTER
                // ============================================================

                borderPane.setCenter(notificationPage);

                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();

                // SET BOTTOM

                borderPane.setBottom(footerBox);

                // ============================================================
                // SCENE
                // ============================================================

                Scene notificationScene = new Scene(
                                borderPane,
                                ViewConstants.STAGE_WIDTH,
                                ViewConstants.STAGE_HEIGHT
                        );

                notificationScene.setFill(
                                Color.web("#F8F7FC"));

                return notificationScene;
        }

        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                false);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                false);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                false);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));

                offersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));

                analyticsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperAnalytics
                                                                .analyticsScene()));

                settingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }
}
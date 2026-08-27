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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ShopkeeperDashboard {

        public static Scene dashboardScene() {

                // ============================================================
                // BORDER PANE
                // ============================================================
                BorderPane borderPane = new BorderPane();
                borderPane.setStyle("-fx-background-color: #F8F7FC;");

                // ============================================================
                // HEADER
                // ============================================================
                HBox headerMainBox = ViewConstants.header();
                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================
                VBox sidebar = new VBox();
                sidebar.setMinWidth(260);
                sidebar.setMaxWidth(260);
                sidebar.setStyle(
                                "-fx-background-color: #F5F4F9;"
                                                + "-fx-border-color: #E3C7BA;"
                                                + "-fx-border-width: 0 1px 0 0;");

                // ============================================================
                // SHOPKEEPER PROFILE
                // ============================================================
                HBox profileBox = ViewConstants.letfProfileBox();
                profileBox.setAlignment(Pos.CENTER_LEFT);
                profileBox.setPadding(new Insets(30, 20, 30, 20));

                // ============================================================
                // DASHBOARD BUTTON
                // ============================================================
                Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", true);

                // ============================================================
                // ORDERS BUTTON
                // ============================================================
                Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", false);

                // ============================================================
                // INVENTORY BUTTON
                // ============================================================
                Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);

                // ============================================================
                // OFFERS BUTTON
                // ============================================================
                Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", false);

                // ============================================================
                // ANALYTICS BUTTON
                // ============================================================
                Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);

                // ============================================================
                // SETTINGS BUTTON
                // ============================================================
                Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", false);

                // ============================================================
                // SUPPORT BUTTON
                // ============================================================
                Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

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
                VBox logoutBox = ViewConstants.logoutBox();

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

                        System.out.println(
                                        "Dashboard clicked");

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
                        Main.primaryStage.setScene(ShopkeeperSupport.supportScene());

                });

                // ============================================================
                // DASHBOARD TITLE
                // ============================================================
                Text dashboardTitle = new Text("Dashboard Overview");
                dashboardTitle.setStyle(
                                "-fx-font-size: 31px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-font-family: 'Arial';"
                                                + "-fx-fill: #151515;");
                Text dashboardSubTitle = new Text(
                                "Welcome back. Here is your store's performance at a glance.");
                dashboardSubTitle.setStyle(
                                "-fx-font-size: 15px;"
                                                + "-fx-font-family: 'Arial';"
                                                + "-fx-fill: #666666;");
                VBox titleBox = new VBox(
                                5,
                                dashboardTitle,
                                dashboardSubTitle);

                // ============================================================
                // TODAY'S SALES CARD
                // ============================================================
                Text salesTitle = new Text("TODAY'S SALES");
                salesTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");
                Text salesIcon = new Text("💰");
                salesIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #A94A18;");
                Circle salesCircle = new Circle(17);
                salesCircle.setFill(Color.web("#FFE5D3"));
                StackPane salesIconBox = new StackPane(salesCircle, salesIcon);
                HBox salesTitleRow = new HBox(
                                10,
                                salesTitle,
                                salesIconBox);
                HBox.setHgrow(salesTitle, Priority.ALWAYS);
                salesTitleRow.setAlignment(Pos.CENTER);

                Text salesValue = new Text("₹");
                salesValue.setStyle(
                                "-fx-font-size: 27px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #171717;");
                Text salesBottom = new Text("↗ +% from yesterday");
                salesBottom.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #21884A;");
                VBox salesCard = new VBox(
                                14,
                                salesTitleRow,
                                salesValue,
                                salesBottom);

                salesCard.setPadding(new Insets(20));
                salesCard.setPrefWidth(235);
                salesCard.setPrefHeight(165);
                salesCard.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #DFDAD7;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 17px;"
                                                + "-fx-background-radius: 17px;");

                // ============================================================
                // ACTIVE ORDERS CARD
                // ============================================================
                Text activeOrdersTitle = new Text("ACTIVE ORDERS");
                activeOrdersTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");

                Circle activeOrdersCircle = new Circle(17);
                activeOrdersCircle.setFill(Color.web("#FFE5D3"));
                Circle activeOrdersIconCircle = new Circle(6);
                activeOrdersIconCircle.setFill(Color.web("#27c924"));
                StackPane activeOrdersIconBox = new StackPane(
                                activeOrdersCircle,
                                activeOrdersIconCircle);
                HBox activeOrdersTitleRow = new HBox(
                                10, activeOrdersTitle,
                                activeOrdersIconBox);
                HBox.setHgrow(activeOrdersTitle, Priority.ALWAYS);
                activeOrdersTitleRow.setAlignment(Pos.CENTER);
                Text activeOrdersValue = new Text("12");
                activeOrdersValue.setStyle(
                                "-fx-font-size: 27px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #171717;");
                Text activeOrdersBottom = new Text("4 ready for pickup");
                activeOrdersBottom.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #666666;");
                VBox activeOrdersCard = new VBox(
                                14,
                                activeOrdersTitleRow,
                                activeOrdersValue,
                                activeOrdersBottom);
                activeOrdersCard.setPadding(new Insets(20));
                activeOrdersCard.setPrefWidth(235);
                activeOrdersCard.setPrefHeight(165);
                activeOrdersCard.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #DFDAD7;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 17px;"
                                                + "-fx-background-radius: 17px;");

                // ============================================================
                // TOTAL REVENUE CARD
                // ============================================================
                Text revenueTitle = new Text("TOTAL REVENUE");
                revenueTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");
                Text revenueIcon = new Text("📈");
                revenueIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #A94A18;");

                Circle revenueCircle = new Circle(17);
                revenueCircle.setFill(Color.web("#FFE5D3"));
                StackPane revenueIconBox = new StackPane(
                                revenueCircle,
                                revenueIcon);
                HBox revenueTitleRow = new HBox(
                                10, revenueTitle,
                                revenueIconBox);
                HBox.setHgrow(revenueTitle, Priority.ALWAYS);
                revenueTitleRow.setAlignment(Pos.CENTER);
                Text revenueValue = new Text("$24.8k");
                revenueValue.setStyle(
                                "-fx-font-size: 27px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #171717;");
                Text revenueBottom = new Text("↗ +5% this month");
                revenueBottom.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #21884A;");
                VBox revenueCard = new VBox(
                                14,
                                revenueTitleRow,
                                revenueValue,
                                revenueBottom);
                revenueCard.setPadding(new Insets(20));
                revenueCard.setPrefWidth(235);
                revenueCard.setPrefHeight(165);
                revenueCard.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #DFDAD7;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 17px;"
                                                + "-fx-background-radius: 17px;");

                // ============================================================
                // SATISFACTION CARD
                // ============================================================
                Text satisfactionTitle = new Text("SATISFACTION");
                satisfactionTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");
                Text satisfactionIcon = new Text("☆");
                satisfactionIcon.setStyle(
                                "-fx-font-size: 18px;"
                                                + "-fx-fill: #A94A18;");
                Circle satisfactionCircle = new Circle(17);
                satisfactionCircle.setFill(Color.web("#FFE5D3"));
                StackPane satisfactionIconBox = new StackPane(
                                satisfactionCircle,
                                satisfactionIcon);
                HBox satisfactionTitleRow = new HBox(
                                10, satisfactionTitle,
                                satisfactionIconBox);
                HBox.setHgrow(satisfactionTitle, Priority.ALWAYS);
                satisfactionTitleRow.setAlignment(Pos.CENTER);
                Text satisfactionValue = new Text("4.8/5");
                satisfactionValue.setStyle(
                                "-fx-font-size: 27px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #171717;");
                Text satisfactionBottom = new Text("Based on 142 reviews");
                satisfactionBottom.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #666666;");
                VBox satisfactionCard = new VBox(
                                14,
                                satisfactionTitleRow,
                                satisfactionValue,
                                satisfactionBottom);
                satisfactionCard.setPadding(new Insets(20));
                satisfactionCard.setPrefWidth(235);
                satisfactionCard.setPrefHeight(165);
                satisfactionCard.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #DFDAD7;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 17px;"
                                                + "-fx-background-radius: 17px;");

                // ============================================================
                // STAT CARDS
                // ============================================================
                HBox statCards = new HBox(
                                17,
                                salesCard,
                                activeOrdersCard,
                                revenueCard,
                                satisfactionCard);
                statCards.setAlignment(Pos.CENTER);

                // ============================================================
                // URGENT ALERTS
                // ============================================================
                Text alertIcon = new Text("🚨");
                alertIcon.setStyle(
                                "-fx-font-size: 21px;"
                                                + "-fx-fill: #B51D1D;");
                Text urgentTitle = new Text("URGENT ALERTS");
                urgentTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #B51D1D;");
                HBox urgentTitleBox = new HBox(
                                10,
                                alertIcon,
                                urgentTitle);
                urgentTitleBox.setAlignment(Pos.CENTER_LEFT);

                // LOW STOCK
                Text lowStockIcon = new Text("⚠");
                lowStockIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #B51D1D;");

                Text lowStockTitle = new Text("Low Stock (3 items)");
                lowStockTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #222222;");
                HBox lowStockTitleBox = new HBox(
                                10,
                                lowStockIcon,
                                lowStockTitle);
                lowStockTitleBox.setAlignment(Pos.CENTER_LEFT);
                Text lowStockDescription = new Text("Items are running low.");
                lowStockDescription.setStyle(
                                "-fx-font-size: 12px;"
                                                + "-fx-fill: #666666;");
                VBox lowStockBox = new VBox(
                                6,
                                lowStockTitleBox,
                                lowStockDescription);
                lowStockBox.setPadding(new Insets(12));
                lowStockBox.setPrefWidth(275);
                lowStockBox.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #F0E6E2;"
                                                + "-fx-border-radius: 9px;"
                                                + "-fx-background-radius: 9px;");
                // EXPIRY WARNING
                Text expiryIcon = new Text("🚫");
                expiryIcon.setStyle(
                                "-fx-font-size: 17px;"
                                                + "-fx-fill: #B51D1D;");
                Text expiryTitle = new Text("Expiry Warning");
                expiryTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #222222;");
                HBox expiryTitleBox = new HBox(
                                10,
                                expiryIcon,
                                expiryTitle);
                expiryTitleBox.setAlignment(Pos.CENTER_LEFT);
                Text expiryDescription = new Text(
                                " Products expiring in 2 days.");
                expiryDescription.setStyle(
                                "-fx-font-size: 12px;"
                                                + "-fx-fill: #666666;");
                VBox expiryBox = new VBox(
                                6,
                                expiryTitleBox,
                                expiryDescription);
                expiryBox.setPadding(
                                new Insets(12));
                expiryBox.setPrefWidth(275);
                expiryBox.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #F0E6E2;"
                                                + "-fx-border-radius: 9px;"
                                                + "-fx-background-radius: 9px;");
                VBox alertsBox = new VBox(
                                12,
                                urgentTitleBox,
                                lowStockBox,
                                expiryBox);
                alertsBox.setPadding(new Insets(20));
                alertsBox.setPrefWidth(315);
                alertsBox.setPrefHeight(255);
                alertsBox.setStyle(
                                "-fx-background-color: #FFF3F3;"
                                                + "-fx-border-color: #E8BDBD;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 18px;"
                                                + "-fx-background-radius: 18px;");

                // ============================================================
                // GROWTH INSIGHT
                // ============================================================
                Text growthIcon = new Text("✦");
                growthIcon.setStyle(
                                "-fx-font-size: 24px;"
                                                + "-fx-fill: #C65417;");
                Text growthTitle = new Text("GROWTH INSIGHT");
                growthTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #C65417;");
                HBox growthTitleBox = new HBox(
                                10,
                                growthIcon,
                                growthTitle);
                growthTitleBox.setAlignment(Pos.CENTER_LEFT);
                Text trendingTitle = new Text("Trending Item Alert");
                trendingTitle.setStyle(
                                "-fx-font-size: 18px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #202020;");
                Text trendingText = new Text(
                                "Organic Avocados are surging in your\n"
                                                + "area based on current search trends.");
                trendingText.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #666666;");

                Text suggestionTitle = new Text("📌 Suggestion");

                suggestionTitle.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #A84A20;");

                Text suggestionText = new Text(
                                "Start a 10% Flash Sale to capture\n"
                                                + "demand.");

                suggestionText.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-fill: #333333;");

                VBox suggestionBox = new VBox(
                                7,
                                suggestionTitle,
                                suggestionText);

                suggestionBox.setPadding(
                                new Insets(10));

                suggestionBox.setStyle(
                                "-fx-background-color: #F7F4F7;"
                                                + "-fx-border-color: #E5DDE2;"
                                                + "-fx-border-radius: 8px;"
                                                + "-fx-background-radius: 8px;");

                Button flashSaleButton = new Button("Apply Flash Sale");

                flashSaleButton.setPrefWidth(260);

                flashSaleButton.setPrefHeight(43);

                flashSaleButton.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-text-fill: #222222;"
                                                + "-fx-font-size: 14px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-border-color: #222222;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 7px;"
                                                + "-fx-background-radius: 7px;"
                                                + "-fx-cursor: hand;");

                VBox growthBox = new VBox(
                                18,
                                growthTitleBox,
                                trendingTitle,
                                trendingText,
                                suggestionBox,
                                flashSaleButton);

                growthBox.setPadding(
                                new Insets(20));

                growthBox.setPrefWidth(315);

                growthBox.setPrefHeight(335);

                growthBox.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #E3DCD8;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 18px;"
                                                + "-fx-background-radius: 18px;");

                // ============================================================
                // LEFT CONTENT
                // ============================================================
                VBox leftContent = new VBox(
                                18,
                                alertsBox,
                                growthBox);

                leftContent.setPrefWidth(315);

                // ============================================================
                // PRODUCT INVENTORY TITLE
                // ============================================================
                Text inventoryTitle = new Text("Product Inventory");

                inventoryTitle.setStyle(
                                "-fx-font-size: 25px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #161616;");

                Text filterText = new Text("≡  Filter");

                filterText.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #A04B23;");

                HBox inventoryTitleBox = new HBox(
                                inventoryTitle,
                                filterText);

                HBox.setHgrow(
                                inventoryTitle,
                                Priority.ALWAYS);

                inventoryTitleBox.setAlignment(
                                Pos.CENTER);

                // ============================================================
                // TABLE HEADER
                // ============================================================
                Text productHeader = new Text("PRODUCT");

                productHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox productHeaderBox = new HBox(productHeader);

                productHeaderBox.setMinWidth(165);

                productHeaderBox.setPrefWidth(165);

                productHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text categoryHeader = new Text("CATEGORY");

                categoryHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox categoryHeaderBox = new HBox(categoryHeader);

                categoryHeaderBox.setMinWidth(130);

                categoryHeaderBox.setPrefWidth(130);

                categoryHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text skuHeader = new Text("SKU");

                skuHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox skuHeaderBox = new HBox(skuHeader);

                skuHeaderBox.setMinWidth(70);

                skuHeaderBox.setPrefWidth(70);

                skuHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text stockHeader = new Text("STOCK");

                stockHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox stockHeaderBox = new HBox(stockHeader);

                stockHeaderBox.setMinWidth(95);

                stockHeaderBox.setPrefWidth(95);

                stockHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text priceHeader = new Text("PRICE\n(₹)");

                priceHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox priceHeaderBox = new HBox(priceHeader);

                priceHeaderBox.setMinWidth(90);

                priceHeaderBox.setPrefWidth(90);

                priceHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text discountHeader = new Text("DISC");

                discountHeader.setStyle(
                                "-fx-font-size: 13px;"
                                                + "-fx-font-weight: bold;"
                                                + "-fx-fill: #555555;");

                HBox discountHeaderBox = new HBox(discountHeader);

                discountHeaderBox.setMinWidth(60);

                discountHeaderBox.setPrefWidth(60);

                discountHeaderBox.setAlignment(
                                Pos.CENTER_LEFT);

                HBox tableHeader = new HBox(
                                productHeaderBox,
                                categoryHeaderBox,
                                skuHeaderBox,
                                stockHeaderBox,
                                priceHeaderBox,
                                discountHeaderBox);

                tableHeader.setPadding(
                                new Insets(12, 10, 12, 15));

                tableHeader.setStyle(
                                "-fx-background-color: #F1F0F5;");

                // ============================================================
                // PRODUCT ROW 1
                // ============================================================
                Text avocadoIcon = new Text("▧");

                avocadoIcon.setStyle(
                                "-fx-font-size: 23px;"
                                                + "-fx-fill: #555555;");

                Text avocadoName = new Text(
                                "Organic\nAvocados");

                avocadoName.setStyle(
                                "-fx-font-size: 15px;"
                                                + "-fx-fill: #222222;");

                HBox avocadoProduct = new HBox(
                                12,
                                avocadoIcon,
                                avocadoName);

                avocadoProduct.setAlignment(
                                Pos.CENTER_LEFT);

                avocadoProduct.setMinWidth(165);

                avocadoProduct.setPrefWidth(165);

                Text avocadoCategory = new Text("Fresh Produce");

                avocadoCategory.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");

                HBox avocadoCategoryBox = new HBox(avocadoCategory);

                avocadoCategoryBox.setMinWidth(130);

                avocadoCategoryBox.setPrefWidth(130);

                avocadoCategoryBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text avocadoSKU = new Text(
                                "AVO-\n001");

                avocadoSKU.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #444444;");

                HBox avocadoSKUBox = new HBox(avocadoSKU);

                avocadoSKUBox.setMinWidth(70);

                avocadoSKUBox.setPrefWidth(70);

                avocadoSKUBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text avocadoStock = new Text("45 units");

                avocadoStock.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #444444;");

                HBox avocadoStockBox = new HBox(avocadoStock);

                avocadoStockBox.setMinWidth(95);

                avocadoStockBox.setPrefWidth(95);

                avocadoStockBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text avocadoPrice = new Text(
                                "₹180.00\n"
                                                + "₹200.00");

                avocadoPrice.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #333333;");

                HBox avocadoPriceBox = new HBox(avocadoPrice);

                avocadoPriceBox.setMinWidth(90);

                avocadoPriceBox.setPrefWidth(90);

                avocadoPriceBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text avocadoDiscount = new Text("10%");

                avocadoDiscount.setStyle(
                                "-fx-font-size: 12px;"
                                                + "-fx-fill: #168343;"
                                                + "-fx-background-color: #DFF5E5;"
                                                + "-fx-background-radius: 12px;");

                HBox avocadoDiscountBox = new HBox(avocadoDiscount);

                avocadoDiscountBox.setMinWidth(60);

                avocadoDiscountBox.setPrefWidth(60);

                avocadoDiscountBox.setAlignment(
                                Pos.CENTER_LEFT);

                HBox avocadoRow = new HBox(
                                avocadoProduct,
                                avocadoCategoryBox,
                                avocadoSKUBox,
                                avocadoStockBox,
                                avocadoPriceBox,
                                avocadoDiscountBox);

                avocadoRow.setAlignment(
                                Pos.CENTER_LEFT);

                avocadoRow.setPadding(
                                new Insets(15, 10, 15, 15));

                avocadoRow.setStyle(
                                "-fx-border-color: #E1DDDA;"
                                                + "-fx-border-width: 0 0 1px 0;");

                // ============================================================
                // PRODUCT ROW 2
                // ============================================================
                Text breadIcon = new Text("▧");

                breadIcon.setStyle(
                                "-fx-font-size: 23px;"
                                                + "-fx-fill: #555555;");

                Text breadName = new Text(
                                "Whole\nWheat\nBread");

                breadName.setStyle(
                                "-fx-font-size: 15px;"
                                                + "-fx-fill: #222222;");

                HBox breadProduct = new HBox(
                                12,
                                breadIcon,
                                breadName);

                breadProduct.setAlignment(
                                Pos.CENTER_LEFT);

                breadProduct.setMinWidth(165);

                breadProduct.setPrefWidth(165);

                Text breadCategory = new Text("Bakery");

                breadCategory.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #666666;");

                HBox breadCategoryBox = new HBox(breadCategory);

                breadCategoryBox.setMinWidth(130);

                breadCategoryBox.setPrefWidth(130);

                breadCategoryBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text breadSKU = new Text(
                                "BRD-\n042");

                breadSKU.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #444444;");

                HBox breadSKUBox = new HBox(breadSKU);

                breadSKUBox.setMinWidth(70);

                breadSKUBox.setPrefWidth(70);

                breadSKUBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text breadStock = new Text("3 units");

                breadStock.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #B51D1D;");

                HBox breadStockBox = new HBox(breadStock);

                breadStockBox.setMinWidth(95);

                breadStockBox.setPrefWidth(95);

                breadStockBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text breadPrice = new Text("₹45.00");

                breadPrice.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #333333;");

                HBox breadPriceBox = new HBox(breadPrice);

                breadPriceBox.setMinWidth(90);

                breadPriceBox.setPrefWidth(90);

                breadPriceBox.setAlignment(
                                Pos.CENTER_LEFT);

                Text breadDiscount = new Text("-");

                breadDiscount.setStyle(
                                "-fx-font-size: 14px;"
                                                + "-fx-fill: #777777;");

                HBox breadDiscountBox = new HBox(breadDiscount);

                breadDiscountBox.setMinWidth(60);

                breadDiscountBox.setPrefWidth(60);

                breadDiscountBox.setAlignment(
                                Pos.CENTER_LEFT);

                HBox breadRow = new HBox(
                                breadProduct,
                                breadCategoryBox,
                                breadSKUBox,
                                breadStockBox,
                                breadPriceBox,
                                breadDiscountBox);

                breadRow.setAlignment(
                                Pos.CENTER_LEFT);

                breadRow.setPadding(
                                new Insets(15, 10, 15, 15));

                breadRow.setStyle(
                                "-fx-border-color: #E1DDDA;"
                                                + "-fx-border-width: 0 0 1px 0;");

                // ============================================================
                // INVENTORY TABLE
                // ============================================================
                VBox inventoryTable = new VBox(
                                tableHeader,
                                avocadoRow,
                                breadRow);

                // ============================================================
                // INVENTORY CARD
                // ============================================================
                VBox inventoryCard = new VBox(
                                20,
                                inventoryTitleBox,
                                inventoryTable);

                inventoryCard.setPadding(
                                new Insets(25));

                inventoryCard.setPrefWidth(665);

                inventoryCard.setPrefHeight(610);

                inventoryCard.setStyle(
                                "-fx-background-color: #FFFFFF;"
                                                + "-fx-border-color: #E0DCD9;"
                                                + "-fx-border-width: 1px;"
                                                + "-fx-border-radius: 18px;"
                                                + "-fx-background-radius: 18px;");

                DropShadow inventoryShadow = new DropShadow();

                inventoryShadow.setRadius(10);

                inventoryShadow.setSpread(0.02);

                inventoryShadow.setColor(
                                Color.rgb(80, 60, 50, 0.08));

                inventoryCard.setEffect(
                                inventoryShadow);

                // ============================================================
                // LOWER CONTENT
                // ============================================================
                HBox lowerContent = new HBox(
                                25,
                                leftContent,
                                inventoryCard);

                lowerContent.setAlignment(
                                Pos.TOP_CENTER);

                // ============================================================
                // CENTER CONTENT
                // ============================================================
                VBox centerContent = new VBox(
                                25,
                                titleBox,
                                statCards,
                                lowerContent);

                centerContent.setAlignment(
                                Pos.TOP_CENTER);

                centerContent.setPadding(
                                new Insets(25));

                centerContent.setStyle(
                                "-fx-background-color: #F8F7FC;");

                flashSaleButton.setOnAction(e -> {

                        System.out.println(
                                        "Apply Flash Sale clicked");

                });

                // ============================================================
                // SCROLL PANE
                // ============================================================
                ScrollPane scrollPane = new ScrollPane(centerContent);

                scrollPane.setFitToWidth(true);

                scrollPane.setFitToHeight(false);

                scrollPane.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                scrollPane.setStyle(
                                "-fx-background-color: #F8F7FC;"
                                                + "-fx-border-color: transparent;");

                borderPane.setCenter(
                                scrollPane);

                // FOOTER
                VBox footerBox = ViewConstants.footer();
                // SET BOTTOM
                borderPane.setBottom(footerBox);

                // ============================================================
                // SCENE
                // ============================================================
                Scene dashboardScene = new Scene(
                                borderPane,
                                1280,
                                650);

                dashboardScene.setFill(
                                Color.web("#F8F7FC"));

                return dashboardScene;
        }
}

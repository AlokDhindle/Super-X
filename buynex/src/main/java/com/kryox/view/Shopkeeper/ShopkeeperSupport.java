package com.kryox.view.Shopkeeper;

import com.kryox.Main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ShopkeeperSupport {


        public static Scene supportScene() {


                BorderPane borderPane = new BorderPane();

                // ================================================================
                // HEADER
                // ================================================================

                HBox headerMainBox = ViewConstants.header();

                borderPane.setTop(headerMainBox);

                // ================================================================
                // LEFT SIDEBAR
                // ================================================================

                VBox sidebar = createSidebar();


                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ================================================================
                // CENTER CONTENT
                // ================================================================

                VBox centerContent = new VBox();

                centerContent.setPadding(
                                new Insets(
                                                25,
                                                35,
                                                35,
                                                35));

                centerContent.setSpacing(
                                20);

                centerContent.setStyle(
                                "-fx-background-color: #F8F7FC;");

                // ================================================================
                // PAGE TITLE
                // ================================================================

                Text supportTitle = new Text(
                                "Support Center");

                supportTitle.setStyle(
                                "-fx-font-size: 30px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                Text supportSubtitle = new Text(
                                "Guidance and information for managing your BuyNeX shop.");

                supportSubtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #666666;");

                VBox titleBox = new VBox(
                                5,
                                supportTitle,
                                supportSubtitle);

                // ================================================================
                // WELCOME INFORMATION
                // ================================================================

                VBox welcomeCard = new VBox();

                welcomeCard.setPadding(
                                new Insets(22));

                welcomeCard.setSpacing(
                                8);

                welcomeCard.setStyle(
                                "-fx-background-color: #FFF8F3;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-border-color: #F1CDB9;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 12px;");

                Text welcomeTitle = new Text(
                                "Welcome to BuyNeX Merchant Support");

                welcomeTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                Text welcomeText = new Text(
                                "BuyNeX provides shopkeepers with a unified platform " +
                                                "for managing products, inventory, orders, offers, " +
                                                "customers and business performance. This page provides " +
                                                "general information to help you understand the main " +
                                                "areas of your merchant workspace.");

                welcomeText.setWrappingWidth(
                                900);

                welcomeText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #665850;");

                welcomeCard.getChildren().addAll(
                                welcomeTitle,
                                welcomeText);

                // ================================================================
                // SHOP MANAGEMENT
                // ================================================================

                Text shopManagementTitle = createSectionTitle(
                                "Shop Management");

                VBox shopManagementCard = new VBox();

                shopManagementCard.setPadding(
                                new Insets(20));

                shopManagementCard.setSpacing(
                                12);

                shopManagementCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                shopManagementCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Shop Registration & Verification",
                                                "Shop registration establishes your merchant presence " +
                                                                "on BuyNeX. Verification helps maintain reliable shop "
                                                                +
                                                                "information across the marketplace."),

                                createInformationRow(
                                                "02",
                                                "Digital Shop Profile",
                                                "Your digital shop profile represents your business " +
                                                                "within BuyNeX. Keeping business information accurate "
                                                                +
                                                                "helps customers understand your store."),

                                createInformationRow(
                                                "03",
                                                "Business Profile Management",
                                                "Business profile information should be maintained " +
                                                                "carefully so that customers receive clear and consistent "
                                                                +
                                                                "information about your shop."));

                // ================================================================
                // PRODUCT & INVENTORY
                // ================================================================

                Text inventoryTitle = createSectionTitle(
                                "Products & Inventory");

                VBox inventoryCard = new VBox();

                inventoryCard.setPadding(
                                new Insets(20));

                inventoryCard.setSpacing(
                                12);

                inventoryCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                inventoryCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Product Management",
                                                "Products can be organized with information such as " +
                                                                "name, category, description, pricing and other catalog "
                                                                +
                                                                "details."),

                                createInformationRow(
                                                "02",
                                                "Inventory Management",
                                                "Inventory information helps you keep track of the " +
                                                                "products available in your shop and maintain an " +
                                                                "organized catalog."),

                                createInformationRow(
                                                "03",
                                                "Real-Time Stock Updates",
                                                "Keeping stock information current helps maintain " +
                                                                "accurate product availability and reduces problems " +
                                                                "caused by outdated stock information."),

                                createInformationRow(
                                                "04",
                                                "Product Images & Catalog",
                                                "Clear product images and complete catalog information " +
                                                                "help customers understand the products offered by " +
                                                                "your shop."),

                                createInformationRow(
                                                "05",
                                                "Expiry & Stock Awareness",
                                                "Inventory information can be used to identify products " +
                                                                "that require attention because of stock or expiry-related "
                                                                + "conditions."));

                // ================================================================
                // ORDERS
                // ================================================================

                Text ordersTitle = createSectionTitle(
                                "Orders & Reservations");

                VBox ordersCard = new VBox();

                ordersCard.setPadding(
                                new Insets(20));

                ordersCard.setSpacing(
                                12);

                ordersCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                ordersCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Order Management",
                                                "The Orders section provides the shopkeeper with " +
                                                                "information needed to manage incoming customer orders."),

                                createInformationRow(
                                                "02",
                                                "Order Status",
                                                "Keeping the order status updated provides clearer " +
                                                                "information about the progress of an order."),

                                createInformationRow(
                                                "03",
                                                "Reservations",
                                                "BuyNeX supports order and reservation management " +
                                                                "for shopkeepers."),

                                createInformationRow(
                                                "04",
                                                "Delivery Management",
                                                "Delivery-related information helps coordinate the " +
                                                                "fulfilment of customer purchases."),

                                createInformationRow(
                                                "05",
                                                "Order History",
                                                "Completed order information provides a useful record " +
                                                                "of previously fulfilled customer purchases."));

                // ================================================================
                // OFFERS
                // ================================================================

                Text offersTitle = createSectionTitle(
                                "Offers & Promotions");

                VBox offersCard = new VBox();

                offersCard.setPadding(
                                new Insets(20));

                offersCard.setSpacing(
                                12);

                offersCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                offersCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Offer Management",
                                                "Shopkeepers can manage promotional offers designed " +
                                                                "to attract customers and encourage purchases."),

                                createInformationRow(
                                                "02",
                                                "Offer Scheduling",
                                                "Promotions can be planned around specific periods " +
                                                                "so that offers are available when they are relevant."),

                                createInformationRow(
                                                "03",
                                                "Expiry Management",
                                                "Offer validity and expiry information helps ensure " +
                                                                "that promotions remain within their intended period."),

                                createInformationRow(
                                                "04",
                                                "Coupons & Promotion Codes",
                                                "BuyNeX supports coupon and promotional-code functionality " +
                                                                "for merchant promotions."),

                                createInformationRow(
                                                "05",
                                                "Digital Loyalty & Rewards",
                                                "Digital loyalty and reward capabilities can support " +
                                                                "long-term customer engagement."));

                // ================================================================
                // CUSTOMER MANAGEMENT
                // ================================================================

                Text customerTitle = createSectionTitle(
                                "Customers & Communication");

                VBox customerCard = new VBox();

                customerCard.setPadding(
                                new Insets(20));

                customerCard.setSpacing(
                                12);

                customerCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                customerCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Customer Chat & Inquiry",
                                                "Customer communication allows shopkeepers to respond " +
                                                                "to inquiries and maintain direct communication."),

                                createInformationRow(
                                                "02",
                                                "Customer Notifications",
                                                "Push notifications can be used to communicate relevant " +
                                                                "updates and information to customers."),

                                createInformationRow(
                                                "03",
                                                "Reviews & Ratings",
                                                "Customer reviews and ratings provide feedback about " +
                                                                "the shopping experience and shop performance."),

                                createInformationRow(
                                                "04",
                                                "Customer Reach",
                                                "Customer reach information helps shopkeepers understand " +
                                                                "how effectively their business is reaching customers."));

                // ================================================================
                // ANALYTICS
                // ================================================================

                Text analyticsTitle = createSectionTitle(
                                "Analytics & Business Performance");

                VBox analyticsCard = new VBox();

                analyticsCard.setPadding(
                                new Insets(20));

                analyticsCard.setSpacing(
                                12);

                analyticsCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                analyticsCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Sales Analytics",
                                                "Sales analytics provide information that can help " +
                                                                "shopkeepers understand their store activity."),

                                createInformationRow(
                                                "02",
                                                "Store Performance",
                                                "Performance reports provide a broader view of how " +
                                                                "your store is performing."),

                                createInformationRow(
                                                "03",
                                                "Revenue & Profit",
                                                "Revenue and profit analytics provide financial insights " +
                                                                "for understanding business performance."),

                                createInformationRow(
                                                "04",
                                                "Business Growth",
                                                "Business growth information can help shopkeepers " +
                                                                "identify areas where their store can improve."));

                // ================================================================
                // AI SUPPORT
                // ================================================================

                Text aiTitle = createSectionTitle(
                                "AI & Smart Assistance");

                VBox aiCard = new VBox();

                aiCard.setPadding(
                                new Insets(20));

                aiCard.setSpacing(
                                12);

                aiCard.setStyle(
                                "-fx-background-color: #FFF8F3;" +
                                                "-fx-background-radius: 12px;" +
                                                "-fx-border-color: #F1D2C0;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 12px;");

                aiCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "AI Shopkeeper Assistant",
                                                "The AI shopkeeper assistant is designed to provide " +
                                                                "intelligent assistance for shop-related activities."),

                                createInformationRow(
                                                "02",
                                                "AI Product Analysis",
                                                "AI-based product analysis can provide additional " +
                                                                "information to support product-related decisions."),

                                createInformationRow(
                                                "03",
                                                "AI Demand Forecasting",
                                                "Demand forecasting is intended to help shopkeepers " +
                                                                "understand possible future demand patterns."),

                                createInformationRow(
                                                "04",
                                                "AI Expiry Stock Alerts",
                                                "AI-assisted alerts can help draw attention to stock " +
                                                                "that may require action."),

                                createInformationRow(
                                                "05",
                                                "Smart Inventory Optimization",
                                                "Smart inventory capabilities are intended to assist " +
                                                                "with maintaining appropriate inventory levels."),

                                createInformationRow(
                                                "06",
                                                "Smart Pricing Suggestions",
                                                "AI-based pricing suggestions can provide additional " +
                                                                "information when considering product pricing."),

                                createInformationRow(
                                                "07",
                                                "Seasonal Demand Prediction",
                                                "Seasonal demand prediction can help merchants understand " +
                                                                "changes in expected product demand."));

                // ================================================================
                // BUSINESS LEARNING
                // ================================================================

                Text learningTitle = createSectionTitle(
                                "Business Learning & Growth");

                VBox learningCard = new VBox();

                learningCard.setPadding(
                                new Insets(20));

                learningCard.setSpacing(
                                12);

                learningCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                learningCard.getChildren().addAll(

                                createInformationRow(
                                                "01",
                                                "Business Learning Center",
                                                "The Business Learning Center is intended to provide " +
                                                                "resources that help local retailers understand and " +
                                                                "improve their business operations."),

                                createInformationRow(
                                                "02",
                                                "Performance Awareness",
                                                "Combining sales, customer and operational information " +
                                                                "can help merchants better understand their business."),

                                createInformationRow(
                                                "03",
                                                "Digital Retail Growth",
                                                "BuyNeX is designed as a digital growth platform for " +
                                                                "local retailers, bringing shop management and customer "
                                                                +
                                                                "reach together in one ecosystem."));

                // ================================================================
                // GENERAL GUIDANCE
                // ================================================================

                Text guidanceTitle = createSectionTitle(
                                "Good Practices for Shopkeepers");

                VBox guidanceCard = new VBox();

                guidanceCard.setPadding(
                                new Insets(22));

                guidanceCard.setSpacing(
                                12);

                guidanceCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 12px;");

                guidanceCard.getChildren().addAll(

                                createGuidance(
                                                "Keep product information accurate",
                                                "Maintain correct product names, categories, prices, " +
                                                                "images and descriptions."),

                                createGuidance(
                                                "Keep inventory information updated",
                                                "Update stock information regularly so product availability " +
                                                                "remains reliable."),

                                createGuidance(
                                                "Monitor orders regularly",
                                                "Review incoming orders and maintain accurate order status " +
                                                                "information throughout fulfilment."),

                                createGuidance(
                                                "Review promotions",
                                                "Keep promotional information, validity periods and " +
                                                                "discount details consistent."),

                                createGuidance(
                                                "Use analytics for decisions",
                                                "Review business performance information when planning " +
                                                                "inventory, pricing and promotional activities."),

                                createGuidance(
                                                "Review AI recommendations",
                                                "AI-generated insights are intended to support merchant " +
                                                                "decision-making and should be considered together with "
                                                                +
                                                                "your business knowledge."));

                // ================================================================
                // SUPPORT OVERVIEW
                // ================================================================

                VBox overviewCard = new VBox();

                overviewCard.setPadding(
                                new Insets(22));

                overviewCard.setSpacing(
                                8);

                overviewCard.setStyle(
                                "-fx-background-color: #B64F0D;" +
                                                "-fx-background-radius: 12px;");

                Text overviewTitle = new Text(
                                "BuyNeX Merchant Support");

                overviewTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: white;");

                Text overviewText = new Text(
                                "BuyNeX brings shop management, products, inventory, " +
                                                "orders, promotions, customers, analytics and intelligent " +
                                                "business assistance together for local retailers.");

                overviewText.setWrappingWidth(
                                900);

                overviewText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #FFF1E8;");

                Text overviewBottom = new Text(
                                "Use the sections of your merchant dashboard to manage " +
                                                "the corresponding part of your store.");

                overviewBottom.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: white;");

                overviewCard.getChildren().addAll(
                                overviewTitle,
                                overviewText,
                                overviewBottom);

                // ================================================================
                // CENTER CONTENT
                // ================================================================

                centerContent.getChildren().addAll(

                                titleBox,

                                welcomeCard,

                                shopManagementTitle,
                                shopManagementCard,

                                inventoryTitle,
                                inventoryCard,

                                ordersTitle,
                                ordersCard,

                                offersTitle,
                                offersCard,

                                customerTitle,
                                customerCard,

                                analyticsTitle,
                                analyticsCard,

                                aiTitle,
                                aiCard,

                                learningTitle,
                                learningCard,

                                guidanceTitle,
                                guidanceCard,

                                overviewCard);

                // ================================================================
                // CENTER SCROLL
                // ================================================================

                ScrollPane centerScroll = new ScrollPane(
                                centerContent);

                centerScroll.setFitToWidth(
                                true);

                centerScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                centerScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                centerScroll.setStyle(
                                "-fx-background-color: #F8F7FC;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                centerScroll);

                // ================================================================
                // SCENE
                // ================================================================

                Scene scene = new Scene(
                                borderPane,
                                1280,
                                650);

                scene.setFill(
                                Color.web("#F8F7FC"));

                return scene;
        }



        // ====================================================================
        // SECTION TITLE
        // ====================================================================

        private static Text createSectionTitle(
                        String title) {

                Text text = new Text(title);

                text.setStyle(
                                "-fx-font-size: 21px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #171717;");

                return text;
        }

        // ====================================================================
        // INFORMATION ROW
        // ====================================================================

        private static HBox createInformationRow(
                        String number,
                        String title,
                        String description) {

                Text numberText = new Text(number);

                numberText.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #A94408;" +
                                                "-fx-background-color: #FFF0E7;" +
                                                "-fx-background-radius: 20px;" +
                                                "-fx-padding: 7px 9px 7px 9px;");

                Text titleText = new Text(title);

                titleText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #292929;");

                Text descriptionText = new Text(description);

                descriptionText.setWrappingWidth(
                                780);

                descriptionText.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #71645E;");

                VBox textBox = new VBox(
                                4,
                                titleText,
                                descriptionText);

                Region spacer = new Region();

                HBox.setHgrow(
                                spacer,
                                Priority.ALWAYS);

                HBox row = new HBox(
                                15,
                                numberText,
                                textBox,
                                spacer);

                row.setAlignment(
                                Pos.TOP_LEFT);

                row.setPadding(
                                new Insets(
                                                8,
                                                5,
                                                10,
                                                5));

                row.setStyle(
                                "-fx-border-color: transparent transparent #EEEEEE transparent;" +
                                                "-fx-border-width: 0 0 1px 0;");

                return row;
        }

        // ====================================================================
        // GOOD PRACTICE ROW
        // ====================================================================

        private static HBox createGuidance(
                        String title,
                        String description) {

                Text icon = new Text("✓");

                icon.setStyle(
                                "-fx-font-size: 16px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #FF6900;");

                Text titleText = new Text(title);

                titleText.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #292929;");

                Text descriptionText = new Text(
                                " — " + description);

                descriptionText.setWrappingWidth(
                                760);

                descriptionText.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #71645E;");

                HBox row = new HBox(
                                9,
                                icon,
                                titleText,
                                descriptionText);

                row.setAlignment(
                                Pos.TOP_LEFT);

                return row;
        }

        // ====================================================================
        // SIDEBAR
        // ====================================================================

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
                                true);

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
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));

                offersButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));

                analyticsButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperAnalytics
                                                                .analyticsScene()));

                settingsButton.setOnAction(
                                event -> Main.primaryStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                return sidebar;
        }

}
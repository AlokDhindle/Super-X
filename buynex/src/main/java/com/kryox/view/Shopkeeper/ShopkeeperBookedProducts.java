package com.kryox.view.Shopkeeper;

import com.kryox.config.Firebaseconfig;
import com.kryox.view.Customer.Homepage;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class ShopkeeperBookedProducts {

    public static Scene bookedProductsScene() {

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #EEE5DF;");

        // Header
        HBox headerMainBox = ViewConstants.header();
        headerMainBox.setStyle("-fx-background-color: #EBCCB7;");
        borderPane.setTop(headerMainBox);

        // Sidebar
        VBox sidebar = createSidebar();
        borderPane.setLeft(sidebar);

        // Center Content
        VBox centerMain = new VBox();
        centerMain.setPadding(new Insets(30, 25, 20, 25));
        centerMain.setSpacing(15);
        centerMain.setStyle("-fx-background-color: #EEE5DF;");

        Text pageTitle = new Text("Booked Products");
        pageTitle.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-fill: #1E1E24;");

        Text pageSubtitle = new Text("Manage products booked by customers.");
        pageSubtitle.setStyle("-fx-font-size: 16px; -fx-fill: #604D43;");

        VBox titleBox = new VBox(5, pageTitle, pageSubtitle);
        HBox titleRow = new HBox(titleBox);
        titleRow.setAlignment(Pos.CENTER_LEFT);

        VBox bookingsList = new VBox(10);
        
        ScrollPane scrollPane = new ScrollPane(bookingsList);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #EEE5DF; -fx-border-color: transparent;");
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        centerMain.getChildren().addAll(titleRow, scrollPane);
        borderPane.setCenter(centerMain);

        // Fetch Data from Firestore
        fetchBookings(bookingsList);

        return new Scene(borderPane, 1550, 850);
    }

    private static void fetchBookings(VBox bookingsList) {
        new Thread(() -> {
            try {
                Firestore db = Firebaseconfig.gFirestore();
                QuerySnapshot querySnapshot = db.collection("Bookings").get().get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                
                Platform.runLater(() -> {
                    bookingsList.getChildren().clear();
                    if (documents.isEmpty()) {
                        Label noBookings = new Label("No booked products found.");
                        noBookings.setStyle("-fx-font-size: 16px; -fx-text-fill: #777777;");
                        bookingsList.getChildren().add(noBookings);
                    } else {
                        for (QueryDocumentSnapshot doc : documents) {
                            String productName = doc.getString("productName");
                            Long quantity = doc.getLong("quantity");
                            Double totalAmount = doc.getDouble("totalAmount");
                            String status = doc.getString("status");
                            String date = doc.getString("date");

                            bookingsList.getChildren().add(createBookingCard(productName, quantity, totalAmount, status, date));
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static HBox createBookingCard(String productName, Long quantity, Double totalAmount, String status, String date) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #E3C7BA;");
        card.setAlignment(Pos.CENTER_LEFT);

        VBox details = new VBox(5);
        Text name = new Text("Product: " + (productName != null ? productName : "N/A"));
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Text qty = new Text("Quantity: " + (quantity != null ? quantity : 1));
        Text amount = new Text(String.format("Total: ₹%.2f", totalAmount != null ? totalAmount : 0.0));
        Text d = new Text("Date: " + (date != null ? date : "N/A"));

        details.getChildren().addAll(name, qty, amount, d);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label stat = new Label(status != null ? status : "BOOKED");
        stat.setStyle("-fx-background-color: #EBCCB7; -fx-text-fill: #1E1E24; -fx-padding: 5 10; -fx-background-radius: 5;");

        card.getChildren().addAll(details, spacer, stat);
        return card;
    }

    private static VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setMinWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setMaxWidth(ViewConstants.SIDEBAR_WIDTH);
        sidebar.setStyle("-fx-background-color: #EBCCB7; -fx-border-color: #E3C7BA; -fx-border-width: 0 1px 0 0;");

        HBox profileBox = ViewConstants.letfProfileBox();
        profileBox.setAlignment(Pos.CENTER_LEFT);
        profileBox.setPadding(new Insets(30, 20, 30, 20));

        Button dashboardButton = ViewConstants.createDashboardButton("★", "Dashboard", false);
        Button ordersButton = ViewConstants.createDashboardButton("🛒", "Orders", false);
        Button bookingsButton = ViewConstants.createDashboardButton("📅", "Bookings", true);
        Button inventoryButton = ViewConstants.createDashboardButton("📋", "Inventory", false);
        Button offersButton = ViewConstants.createDashboardButton("🎁", "Offers", false);
        Button analyticsButton = ViewConstants.createDashboardButton("📊", "Analytics", false);
        Button settingsButton = ViewConstants.createDashboardButton("⚙", "Settings", false);
        Button supportButton = ViewConstants.createDashboardButton("?", "Support", false);

        VBox menu = new VBox(
                5,
                dashboardButton,
                ordersButton,
                bookingsButton,
                inventoryButton,
                offersButton,
                analyticsButton,
                settingsButton,
                supportButton
        );
        menu.setPadding(new Insets(0, 8, 0, 8));
        VBox.setVgrow(menu, Priority.ALWAYS);

        sidebar.getChildren().addAll(profileBox, menu);

        dashboardButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperDashboard.dashboardScene()));
        ordersButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperOrderNew.ordersScene()));
        bookingsButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperBookedProducts.bookedProductsScene()));
        inventoryButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperInventory.inventoryScene()));
        offersButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperOffers.offersScene()));
        analyticsButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperAnalytics.analyticsScene()));
        settingsButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperSettings.settingsScene()));
        supportButton.setOnAction(event -> Homepage.HomepageStage.setScene(ShopkeeperSupport.supportScene()));

        return sidebar;
    }
}

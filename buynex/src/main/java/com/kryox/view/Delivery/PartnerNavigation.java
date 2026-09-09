package com.kryox.view.Delivery;



import com.kryox.config.DelivrayFirebaseConfig;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Text;

import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PartnerNavigation {

    public static String GOOGLE_MAPS_API_KEY = "YOUR_API_KEY_HERE";

    private static final String ORANGE_PRIMARY = "#f46a06";
    private static final String ORANGE_GRADIENT = "linear-gradient(to right, #B84208, #F36A00)";
    private static final String SIDEBAR_BG = "#EBCCB7";
    private static final String BORDER_COLOR = "#f0edf2";
    private static final String BG_COLOR = "#EEE5DE";

    private static ListenerRegistration navOrderListener;

    public static class TripData {
        public String partnerName;
        public String partnerTier;
        public boolean isOnline = true;

        public String orderNumber = "Order #BX-9942";
        public String estArrivalMin = "14";
        public String distanceMiles = "3.2";
        public String turnInstruction = "Turn right onto Market St.";
        public String turnDistance = "500 ft";

        public String pickupName = "Artisan Bakery";
        public String pickupAddress = "FC Road, Deccan Gymkhana, Pune";
        public String pickupStatus = "Picked Up";
        public String pickupLat = "18.5204";
        public String pickupLng = "73.8567";

        public String dropoffName = "Sarah J.";
        public String dropoffAddress = "Kothrud, Pune, Maharashtra";
        public String dropoffEta = "Dropoff by 2:45 PM";
        public String dropoffPhone = "+919876543210";
        public String dropoffLat = "18.5074";
        public String dropoffLng = "73.8077";

        public String trafficCondition = "Light Traffic";
        public String trafficDescription = "Smooth route ahead.";
        public double orderEarnings = 145.50;

        public TripData() {
            this.partnerName = PartnerConstants.FULL_NAME;
            this.partnerTier = PartnerConstants.PARTNER_TIER;
        }

        public void loadDummyRoute() {
            this.orderNumber = "Order #BX-9942";
            this.estArrivalMin = "14";
            this.distanceMiles = "3.2";
            this.turnInstruction = "Turn right onto Market St.";
            this.turnDistance = "500 ft";

            this.pickupName = "Artisan Bakery";
            this.pickupAddress = "FC Road, Deccan Gymkhana, Pune";
            this.pickupStatus = "Picked Up";
            this.pickupLat = "18.5204";
            this.pickupLng = "73.8567";

            this.dropoffName = "Sarah J.";
            this.dropoffAddress = "Kothrud, Pune, Maharashtra";
            this.dropoffEta = "Dropoff by 2:45 PM";
            this.dropoffPhone = "+919876543210";
            this.dropoffLat = "18.5074";
            this.dropoffLng = "73.8077";

            this.trafficCondition = "Light Traffic";
            this.trafficDescription = "Smooth route ahead.";
            this.orderEarnings = 145.50;
        }

        public double getPickupLatVal() {
            try { return Double.parseDouble(pickupLat); } catch (Exception e) { return 18.5204; }
        }
        public double getPickupLngVal() {
            try { return Double.parseDouble(pickupLng); } catch (Exception e) { return 73.8567; }
        }
        public double getDropoffLatVal() {
            try { return Double.parseDouble(dropoffLat); } catch (Exception e) { return 18.5074; }
        }
        public double getDropoffLngVal() {
            try { return Double.parseDouble(dropoffLng); } catch (Exception e) { return 73.8077; }
        }
    }

    public static Scene partnerNavigationScene() {
        TripData data = new TripData();
        Scene scene = partnerNavigationScene(data);
        attachRealtimeNavListener(data);
        return scene;
    }

    public static Scene partnerNavigationScene(TripData tripData) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        root.setTop(createTopHeader(tripData));
        root.setLeft(createSidebar(tripData));
        root.setCenter(createMapArea(tripData));
        root.setRight(createTripDetailsPanel(tripData));

        Scene scene = new Scene(root, 1550, 850);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static void attachRealtimeNavListener(TripData data) {
        try {
            if (navOrderListener != null) {
                navOrderListener.remove();
            }

            Firestore db = DelivrayFirebaseConfig.getFireStore();
            navOrderListener = db.collection("Orders").addSnapshotListener((snapshots, error) -> {
                if (error != null) {
                    System.err.println("Navigation Listener Error: " + error.getMessage());
                    return;
                }

                Platform.runLater(() -> {
                    boolean foundLiveActive = false;

                    if (snapshots != null && !snapshots.isEmpty()) {
                        for (DocumentSnapshot doc : snapshots.getDocuments()) {
                            String status = doc.getString("orderStatus");
                            if (status == null) status = doc.getString("status");
                            if (status == null) status = "PLACED";

                            String partnerId = doc.getString("deliveryPartnerId");
                            boolean isForMe = partnerId == null || partnerId.isEmpty()
                                    || (PartnerConstants.UID != null && partnerId.equals(PartnerConstants.UID));

                            if (("ACCEPTED".equalsIgnoreCase(status) || "ASSIGNED".equalsIgnoreCase(status) || "PLACED".equalsIgnoreCase(status) || "OUT_FOR_DELIVERY".equalsIgnoreCase(status) || "REQUESTING_DELIVERY".equalsIgnoreCase(status)) && isForMe) {
                                String orderId = doc.getId();
                                data.orderNumber = "Order #" + (orderId.length() > 6 ? orderId.substring(0, 6).toUpperCase() : orderId);
                                data.pickupName = doc.getString("shopName") != null ? doc.getString("shopName") : "Local Store";
                                data.pickupAddress = doc.getString("shopAddress") != null ? doc.getString("shopAddress") : "FC Road, Pune";
                                data.dropoffName = doc.getString("customerName") != null ? doc.getString("customerName") : "Customer";
                                data.dropoffAddress = doc.getString("customerAddress") != null ? doc.getString("customerAddress") : "Kothrud, Pune";
                                data.distanceMiles = doc.getString("distance") != null ? doc.getString("distance") : (doc.getString("riderDistance") != null ? doc.getString("riderDistance") : "2.4 mi");
                                data.estArrivalMin = "12";
                                data.pickupStatus = "ACCEPTED".equalsIgnoreCase(status) ? "Accepted" : ("OUT_FOR_DELIVERY".equalsIgnoreCase(status) ? "Out for Delivery" : "Assigned");

                                if (doc.get("shopLat") != null) data.pickupLat = String.valueOf(doc.get("shopLat"));
                                if (doc.get("shopLng") != null) data.pickupLng = String.valueOf(doc.get("shopLng"));
                                if (doc.get("customerLat") != null) data.dropoffLat = String.valueOf(doc.get("customerLat"));
                                if (doc.get("customerLng") != null) data.dropoffLng = String.valueOf(doc.get("customerLng"));

                                if (doc.get("totalAmount") != null) {
                                    try {
                                        data.orderEarnings = Double.parseDouble(doc.get("totalAmount").toString());
                                    } catch (Exception ignored) {}
                                }

                                foundLiveActive = true;
                                break;
                            }
                        }
                    }

                    if (!foundLiveActive) {
                        data.loadDummyRoute();
                    }

                    if (Homepage.HomepageStage != null) {
                        Homepage.HomepageStage.setScene(partnerNavigationScene(data));
                    }
                });
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static BorderPane createTopHeader(TripData data) {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setStyle(
                "-fx-background-color: #EBCCB7;" +
                        "-fx-border-color: #f0edf2;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-padding: 0 35 0 30;");

        Text title = new Text("Map");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #a94717;");
        topBar.setLeft(new HBox(title));
        ((HBox) topBar.getLeft()).setAlignment(Pos.CENTER_LEFT);
        ((HBox) topBar.getLeft()).setStyle("-fx-background-color: #EBCCB7;");

        HBox rightControls = new HBox(16);
        rightControls.setAlignment(Pos.CENTER_RIGHT);

        HBox statusPill = new HBox(8);
        statusPill.setAlignment(Pos.CENTER);
        statusPill.setPadding(new Insets(4, 10, 4, 10));
        statusPill.setStyle(data.isOnline
                ? "-fx-background-color: #dcfce7; -fx-background-radius: 14;"
                : "-fx-background-color: #f1f3f7; -fx-background-radius: 14;");

        Label statusLbl = new Label(data.isOnline ? "ONLINE" : "OFFLINE");
        statusLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: "
                + (data.isOnline ? "#16a34a;" : "#7d8592;"));
        Circle toggleDot = new Circle(5, data.isOnline ? Color.web("#16a34a") : Color.web("#cbd0d8"));
        statusPill.getChildren().addAll(statusLbl, toggleDot);

        Label bellIcon = new Label("🔔");
        bellIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        bellIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerNotifications.partnerNotificationsScene("NAVIGATION"));
            }
        });

        Label chatIcon = new Label("💬");
        chatIcon.setStyle("-fx-font-size: 14px; -fx-text-fill: #555; -fx-cursor: hand;");
        chatIcon.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerChatSupport.partnerChatSupportScene("NAVIGATION"));
            }
        });

        StackPane userAvatarPane = createAvatarNode(15);
        userAvatarPane.setStyle("-fx-cursor: hand;");

        ContextMenu userMenu = new ContextMenu();
        userMenu.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        MenuItem itemProfile = new MenuItem("👤   View Profile & Settings");
        itemProfile.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemProfile.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
            }
        });

        MenuItem itemAvailability = new MenuItem("⏱   Manage Availability");
        itemAvailability.setStyle("-fx-font-size: 11px; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        MenuItem itemLogout = new MenuItem("↪   Logout");
        itemLogout.setStyle("-fx-font-size: 11px; -fx-text-fill: #e11d48; -fx-padding: 6 14 6 14; -fx-cursor: hand;");
        itemLogout.setOnAction(e -> {
            if (navOrderListener != null) navOrderListener.remove();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        userMenu.getItems().addAll(itemProfile, itemAvailability, itemLogout);

        userAvatarPane.setOnMouseClicked(e -> {
            if (!userMenu.isShowing()) {
                userMenu.show(userAvatarPane, javafx.geometry.Side.BOTTOM, -120, 8);
            } else {
                userMenu.hide();
            }
        });

        rightControls.getChildren().addAll(statusPill, bellIcon, chatIcon, userAvatarPane);
        topBar.setRight(rightControls);

        return topBar;
    }

    private static VBox createSidebar(TripData data) {
        VBox sidebar = new VBox(12);
        sidebar.setPrefWidth(220);
        sidebar.setMinWidth(220);
        sidebar.setMaxWidth(220);
        sidebar.setPadding(new Insets(20, 16, 25, 16));
        sidebar.setStyle(
                "-fx-background-color: " + SIDEBAR_BG + ";" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 0 1 0 0;");

        Text logo = new Text("BuyNeX");
        logo.setStyle("-fx-font-size: 26px; -fx-fill: " + ORANGE_GRADIENT + "; -fx-font-weight: bold;");
        VBox logoBox = new VBox(logo);
        logoBox.setPadding(new Insets(0, 0, 15, 8));

        Button btnDashboard = createNavButton("▤   Dashboard", false);
        btnDashboard.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDashboard.partnerDashboardScene());
            }
        });

        Button btnDeliveries = createNavButton("📦   My Deliveries", false);
        btnDeliveries.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerDeliveries.partnerDeliveriesScene());
            }
        });

        Button btnNavigation = createNavButton("🧭   Navigation", true);
        btnNavigation.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerNavigationScene(data));
            }
        });

        Button btnEarnings = createNavButton("💵   Earnings", false);
        btnEarnings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerEarnings.partnerEarningsScene());
            }
        });

        Button btnAvailability = createNavButton("⏱   Availability", false);
        btnAvailability.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        VBox navList = new VBox(6, btnDashboard, btnDeliveries, btnNavigation, btnEarnings, btnAvailability);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox profileCard = new VBox(4);
        profileCard.setPadding(new Insets(10, 12, 10, 12));
        profileCard.setStyle(
                "-fx-background-color: #f8f8fb;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-border-radius: 10;" +
                        "-fx-cursor: hand;"
        );

        HBox userBox = new HBox(8);
        userBox.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = createAvatarNode(14);

        VBox userDetails = new VBox(1);
        Label userName = new Label(PartnerConstants.FULL_NAME);
        userName.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label userRole = new Label(PartnerConstants.PARTNER_TIER);
        userRole.setStyle("-fx-font-size: 9px; -fx-text-fill: #6b7280;");
        userDetails.getChildren().addAll(userName, userRole);

        userBox.getChildren().addAll(avatar, userDetails);
        profileCard.getChildren().add(userBox);
        profileCard.setOnMouseClicked(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerProfile.partnerProfileScene());
            }
        });

        Button btnSettings = createNavButton("⚙   Settings", false);
        btnSettings.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerSettings.partnerSettingsScene());
            }
        });

        Button btnLogout = new Button("↪   Logout");
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setAlignment(Pos.CENTER_LEFT);
        btnLogout.setPrefHeight(34);
        btnLogout.setStyle(
                "-fx-font-size: 12px; -fx-text-fill: #e11d48; -fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 14 0 14;");
        btnLogout.setOnAction(e -> {
            if (navOrderListener != null) navOrderListener.remove();
            PartnerConstants.clear();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(Deliverylogin.deliveryLoginScene());
            }
        });

        Button btnGoOnline = new Button(data.isOnline ? "Go Offline" : "Go Online");
        btnGoOnline.setMaxWidth(Double.MAX_VALUE);
        btnGoOnline.setPrefHeight(38);
        btnGoOnline.setStyle(
                "-fx-background-color: " + (data.isOnline ? "#93380b;" : "#15803d;") +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 12px;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;");
        btnGoOnline.setOnAction(e -> {
            data.isOnline = !data.isOnline;
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(partnerNavigationScene(data));
            }
        });

        VBox bottomNav = new VBox(6, profileCard, btnSettings, btnLogout, btnGoOnline);
        sidebar.getChildren().addAll(logoBox, navList, spacer, bottomNav);
        return sidebar;
    }

    private static StackPane createMapArea(TripData tripData) {
        StackPane mapStack = new StackPane();
        mapStack.setStyle("-fx-background-color: #dce3e8;");

        PartnerOsmMapView osmMapView = new PartnerOsmMapView(tripData);
        osmMapView.prefWidthProperty().bind(mapStack.widthProperty());
        osmMapView.prefHeightProperty().bind(mapStack.heightProperty());
        mapStack.getChildren().add(osmMapView);

        HBox topHud = new HBox(14);
        topHud.setAlignment(Pos.CENTER_LEFT);
        topHud.setPadding(new Insets(12, 18, 12, 16));
        topHud.setPrefWidth(320);
        topHud.setMaxWidth(320);
        topHud.setPrefHeight(62);
        topHud.setMinHeight(62);
        topHud.setMaxHeight(62);
        topHud.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.95);" +
                        "-fx-background-radius: 14;" +
                        "-fx-border-radius: 14;" +
                        "-fx-border-color: rgba(255, 255, 255, 0.8);" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 12, 0, 0, 3);");

        StackPane turnIconBox = new StackPane();
        Rectangle turnBg = new Rectangle(38, 38);
        turnBg.setArcWidth(10);
        turnBg.setArcHeight(10);
        turnBg.setFill(Color.web(ORANGE_PRIMARY));

        Label turnArrow = new Label("↱");
        turnArrow.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        turnIconBox.getChildren().addAll(turnBg, turnArrow);

        VBox turnText = new VBox(2);
        turnText.setAlignment(Pos.CENTER_LEFT);
        Label turnDist = new Label(tripData.turnDistance);
        turnDist.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label turnInst = new Label(tripData.turnInstruction);
        turnInst.setStyle("-fx-font-size: 12px; -fx-text-fill: #4b5563;");
        turnText.getChildren().addAll(turnDist, turnInst);

        topHud.getChildren().addAll(turnIconBox, turnText);
        StackPane.setAlignment(topHud, Pos.TOP_LEFT);
        StackPane.setMargin(topHud, new Insets(24, 0, 0, 24));
        mapStack.getChildren().add(topHud);

        HBox bottomControls = new HBox(14);
        bottomControls.setAlignment(Pos.CENTER);
        bottomControls.setMaxHeight(Region.USE_PREF_SIZE);

        Button btnStartNav = new Button("▲   Start Navigation");
        btnStartNav.setPrefHeight(44);
        btnStartNav.setPrefWidth(200);
        btnStartNav.setStyle(
                "-fx-background-color: " + ORANGE_PRIMARY + ";" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 24;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(244,106,6,0.35), 12, 0, 0, 4);");
        btnStartNav.setOnAction(e -> launchExternalNavigation(tripData));

        Button btnContact = new Button("📞   Contact");
        btnContact.setPrefHeight(44);
        btnContact.setPrefWidth(130);
        btnContact.setStyle(
                "-fx-background-color: white;" +
                        "-fx-text-fill: #111827;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 24;" +
                        "-fx-border-color: #e5e7eb;" +
                        "-fx-border-radius: 24;" +
                        "-fx-cursor: hand;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 2);");
        btnContact.setOnAction(e -> launchDialer(tripData.dropoffPhone));

        bottomControls.getChildren().addAll(btnStartNav, btnContact);
        StackPane.setAlignment(bottomControls, Pos.BOTTOM_CENTER);
        StackPane.setMargin(bottomControls, new Insets(0, 0, 28, 0));
        mapStack.getChildren().add(bottomControls);

        return mapStack;
    }

    private static class PartnerOsmMapView extends StackPane {
        private static final int TILE_SIZE = 256;
        private static final int MAX_ZOOM = 18;
        private static final int MIN_ZOOM = 3;

        private final Pane tilePane = new Pane();
        private final Pane routePane = new Pane();
        private final Pane markerPane = new Pane();

        private final HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        private final Map<String, Image> imageCache = new ConcurrentHashMap<>();

        private double centerLatitude = 18.5139;
        private double centerLongitude = 73.8322;
        private int zoom = 13;

        private List<double[]> routePoints = new ArrayList<>();
        private double pickupLat = 18.5204;
        private double pickupLng = 73.8567;
        private String pickupName = "Artisan Bakery";
        private String pickupAddress = "FC Road, Pune";

        private double dropoffLat = 18.5074;
        private double dropoffLng = 73.8077;
        private String dropoffName = "Sarah J.";
        private String dropoffAddress = "Kothrud, Pune";

        private double mousePressedX;
        private double mousePressedY;
        private double pressedCenterX;
        private double pressedCenterY;
        private long renderNumber = 0;

        public PartnerOsmMapView(TripData tripData) {
            setStyle("-fx-background-color: #E8ECEF;");

            tilePane.setMouseTransparent(true);
            routePane.setMouseTransparent(true);
            markerPane.setMouseTransparent(false);

            getChildren().addAll(tilePane, routePane, markerPane);

            Rectangle clip = new Rectangle();
            clip.widthProperty().bind(widthProperty());
            clip.heightProperty().bind(heightProperty());
            setClip(clip);

            createControls();
            createAttribution();

            setOnMousePressed(this::handleMousePressed);
            setOnMouseDragged(this::handleMouseDragged);
            setOnMouseReleased(e -> scheduleRender());

            setOnScroll(e -> {
                if (e.getDeltaY() > 0) {
                    zoomIn();
                } else if (e.getDeltaY() < 0) {
                    zoomOut();
                }
                e.consume();
            });

            widthProperty().addListener((obs, oldVal, newVal) -> scheduleRender());
            heightProperty().addListener((obs, oldVal, newVal) -> scheduleRender());

            this.pickupLat = tripData.getPickupLatVal();
            this.pickupLng = tripData.getPickupLngVal();
            this.pickupName = tripData.pickupName != null ? tripData.pickupName : "Store";
            this.pickupAddress = tripData.pickupAddress != null ? tripData.pickupAddress : "FC Road, Pune";

            this.dropoffLat = tripData.getDropoffLatVal();
            this.dropoffLng = tripData.getDropoffLngVal();
            this.dropoffName = tripData.dropoffName != null ? tripData.dropoffName : "Customer";
            this.dropoffAddress = tripData.dropoffAddress != null ? tripData.dropoffAddress : "Kothrud, Pune";

            this.centerLatitude = (this.pickupLat + this.dropoffLat) / 2.0;
            this.centerLongitude = (this.pickupLng + this.dropoffLng) / 2.0;

            Platform.runLater(this::fetchAndDrawRoute);
        }

        private void createControls() {
            VBox controlBox = new VBox(6);
            controlBox.setAlignment(Pos.CENTER);
            controlBox.setPadding(new Insets(10));

            Button btnZoomIn = new Button("+");
            Button btnZoomOut = new Button("−");
            Button btnRecenter = new Button("🎯");

            String buttonStyle =
                    "-fx-background-color: white;" +
                    "-fx-text-fill: #1f2937;" +
                    "-fx-font-size: 16px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 8;" +
                    "-fx-border-radius: 8;" +
                    "-fx-border-color: #e5e7eb;" +
                    "-fx-border-width: 1;" +
                    "-fx-pref-width: 36px;" +
                    "-fx-pref-height: 36px;" +
                    "-fx-cursor: hand;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 6, 0, 0, 2);";

            btnZoomIn.setStyle(buttonStyle);
            btnZoomOut.setStyle(buttonStyle);
            btnRecenter.setStyle(buttonStyle);

            btnZoomIn.setOnAction(e -> zoomIn());
            btnZoomOut.setOnAction(e -> zoomOut());
            btnRecenter.setOnAction(e -> recenterRoute());

            controlBox.getChildren().addAll(btnZoomIn, btnZoomOut, btnRecenter);
            StackPane.setAlignment(controlBox, Pos.TOP_RIGHT);
            StackPane.setMargin(controlBox, new Insets(20, 20, 0, 0));
            getChildren().add(controlBox);
        }

        private void createAttribution() {
            Label attribution = new Label("© OpenStreetMap contributors");
            attribution.setStyle(
                    "-fx-background-color: rgba(255,255,255,0.92);" +
                    "-fx-text-fill: #4b5563;" +
                    "-fx-font-size: 9px;" +
                    "-fx-padding: 3 8 3 8;" +
                    "-fx-background-radius: 4;" +
                    "-fx-border-color: rgba(0,0,0,0.06);" +
                    "-fx-border-radius: 4;");
            StackPane.setAlignment(attribution, Pos.BOTTOM_RIGHT);
            StackPane.setMargin(attribution, new Insets(0, 16, 12, 0));
            getChildren().add(attribution);
        }

        private void recenterRoute() {
            if (routePoints != null && !routePoints.isEmpty()) {
                applyRoute(routePoints);
            } else {
                fetchAndDrawRoute();
            }
        }

        private void handleMousePressed(MouseEvent event) {
            mousePressedX = event.getX();
            mousePressedY = event.getY();
            pressedCenterX = longitudeToWorldX(centerLongitude, zoom);
            pressedCenterY = latitudeToWorldY(centerLatitude, zoom);
        }

        private void handleMouseDragged(MouseEvent event) {
            double deltaX = mousePressedX - event.getX();
            double deltaY = mousePressedY - event.getY();

            double worldX = pressedCenterX + deltaX;
            double worldY = pressedCenterY + deltaY;

            centerLongitude = worldXToLongitude(worldX, zoom);
            centerLatitude = worldYToLatitude(worldY, zoom);
            centerLatitude = Math.max(-85.05112878, Math.min(85.05112878, centerLatitude));

            renderMap();
        }

        private void zoomIn() {
            if (zoom >= MAX_ZOOM) return;
            zoom++;
            renderMap();
        }

        private void zoomOut() {
            if (zoom <= MIN_ZOOM) return;
            zoom--;
            renderMap();
        }

        private void scheduleRender() {
            Platform.runLater(this::renderMap);
        }

        private void fetchAndDrawRoute() {
            String osrmUrl = "https://router.project-osrm.org/route/v1/driving/" +
                    pickupLng + "," + pickupLat + ";" +
                    dropoffLng + "," + dropoffLat +
                    "?overview=full&geometries=geojson";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(osrmUrl))
                    .header("User-Agent", "BuyNeX-JavaFX/1.0")
                    .GET()
                    .build();

            httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        if (response.statusCode() != 200) {
                            throw new RuntimeException("OSRM HTTP " + response.statusCode());
                        }
                        return parseRouteCoordinates(response.body());
                    })
                    .thenAccept(points -> {
                        Platform.runLater(() -> {
                            List<double[]> finalPoints = (points != null && points.size() >= 2)
                                    ? points
                                    : straightLine(pickupLat, pickupLng, dropoffLat, dropoffLng);
                            applyRoute(finalPoints);
                        });
                    })
                    .exceptionally(err -> {
                        System.err.println("Route fetch failed, fallback to straight line: " + err.getMessage());
                        Platform.runLater(() -> applyRoute(straightLine(pickupLat, pickupLng, dropoffLat, dropoffLng)));
                        return null;
                    });
        }

        private void applyRoute(List<double[]> points) {
            this.routePoints = points;
            double minLat = Math.min(pickupLat, dropoffLat);
            double maxLat = Math.max(pickupLat, dropoffLat);
            double minLon = Math.min(pickupLng, dropoffLng);
            double maxLon = Math.max(pickupLng, dropoffLng);

            for (double[] pt : points) {
                minLat = Math.min(minLat, pt[0]);
                maxLat = Math.max(maxLat, pt[0]);
                minLon = Math.min(minLon, pt[1]);
                maxLon = Math.max(maxLon, pt[1]);
            }

            fitToBounds(minLat, maxLat, minLon, maxLon);
            renderMap();
        }

        private void fitToBounds(double minLat, double maxLat, double minLon, double maxLon) {
            double viewW = Math.max(getWidth(), 300);
            double viewH = Math.max(getHeight(), 300);

            int bestZoom = MIN_ZOOM;
            for (int candidateZoom = MAX_ZOOM; candidateZoom >= MIN_ZOOM; candidateZoom--) {
                double x1 = longitudeToWorldX(minLon, candidateZoom);
                double x2 = longitudeToWorldX(maxLon, candidateZoom);
                double y1 = latitudeToWorldY(minLat, candidateZoom);
                double y2 = latitudeToWorldY(maxLat, candidateZoom);

                double spanX = Math.abs(x2 - x1);
                double spanY = Math.abs(y2 - y1);

                if (spanX <= viewW * 0.70 && spanY <= viewH * 0.70) {
                    bestZoom = candidateZoom;
                    break;
                }
            }

            this.centerLatitude = (minLat + maxLat) / 2.0;
            this.centerLongitude = (minLon + maxLon) / 2.0;
            this.zoom = bestZoom;
        }

        private void renderMap() {
            if (getWidth() < 10 || getHeight() < 10) {
                return;
            }

            final long currentRender = ++renderNumber;

            double centerWorldX = longitudeToWorldX(centerLongitude, zoom);
            double centerWorldY = latitudeToWorldY(centerLatitude, zoom);

            double leftWorld = centerWorldX - getWidth() / 2.0;
            double topWorld = centerWorldY - getHeight() / 2.0;

            tilePane.getChildren().clear();
            routePane.getChildren().clear();
            markerPane.getChildren().clear();

            int firstTileX = (int) Math.floor(leftWorld / TILE_SIZE) - 1;
            int lastTileX = (int) Math.floor((leftWorld + getWidth()) / TILE_SIZE) + 1;
            int firstTileY = (int) Math.floor(topWorld / TILE_SIZE) - 1;
            int lastTileY = (int) Math.floor((topWorld + getHeight()) / TILE_SIZE) + 1;

            int maxTile = (1 << zoom) - 1;

            for (int tileX = firstTileX; tileX <= lastTileX; tileX++) {
                int wrappedX = ((tileX % (maxTile + 1)) + (maxTile + 1)) % (maxTile + 1);

                for (int tileY = firstTileY; tileY <= lastTileY; tileY++) {
                    if (tileY < 0 || tileY > maxTile) {
                        continue;
                    }

                    double imgX = tileX * TILE_SIZE - leftWorld;
                    double imgY = tileY * TILE_SIZE - topWorld;

                    loadTile(zoom, wrappedX, tileY, imgX, imgY, currentRender);
                }
            }

            drawRoute(leftWorld, topWorld);
            drawMarkers(leftWorld, topWorld);
        }

        private void loadTile(int tileZoom, int tileX, int tileY, double imgX, double imgY, long currentRender) {
            String key = tileZoom + "/" + tileX + "/" + tileY;
            Image cached = imageCache.get(key);

            if (cached != null && !cached.isError()) {
                addTileImage(cached, imgX, imgY);
                return;
            }

            String tileUrl = "https://tile.openstreetmap.org/" + tileZoom + "/" + tileX + "/" + tileY + ".png";
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(tileUrl))
                    .header("User-Agent", "BuyNeX-JavaFX/1.0")
                    .GET()
                    .build();

            httpClient.sendAsync(req, HttpResponse.BodyHandlers.ofByteArray())
                    .thenApply(resp -> {
                        if (resp.statusCode() != 200) {
                            throw new RuntimeException("OSM tile HTTP " + resp.statusCode());
                        }
                        return new Image(new ByteArrayInputStream(resp.body()));
                    })
                    .thenAccept(img -> {
                        imageCache.put(key, img);
                        Platform.runLater(() -> {
                            if (currentRender != renderNumber) return;
                            addTileImage(img, imgX, imgY);
                        });
                    })
                    .exceptionally(err -> null);
        }

        private void addTileImage(Image image, double x, double y) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(TILE_SIZE);
            imageView.setFitHeight(TILE_SIZE);
            imageView.setPreserveRatio(false);
            imageView.setSmooth(true);
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
            tilePane.getChildren().add(imageView);
        }

        private void drawRoute(double leftWorld, double topWorld) {
            if (routePoints == null || routePoints.size() < 2) {
                return;
            }

            Polyline outline = new Polyline();
            Polyline line = new Polyline();

            for (double[] pt : routePoints) {
                double worldX = longitudeToWorldX(pt[1], zoom) - leftWorld;
                double worldY = latitudeToWorldY(pt[0], zoom) - topWorld;
                outline.getPoints().addAll(worldX, worldY);
                line.getPoints().addAll(worldX, worldY);
            }

            outline.setStroke(Color.web("#9a3412", 0.45));
            outline.setStrokeWidth(9);
            outline.setStrokeLineCap(StrokeLineCap.ROUND);
            outline.setStrokeLineJoin(StrokeLineJoin.ROUND);

            line.setStroke(Color.web("#f46a06"));
            line.setStrokeWidth(5);
            line.setStrokeLineCap(StrokeLineCap.ROUND);
            line.setStrokeLineJoin(StrokeLineJoin.ROUND);

            routePane.getChildren().addAll(outline, line);
        }

        private void drawMarkers(double leftWorld, double topWorld) {
            addLocationPin(pickupLat, pickupLng, leftWorld, topWorld,
                    "#93380b", "🏪", "PICKUP", pickupName, pickupAddress);

            addLocationPin(dropoffLat, dropoffLng, leftWorld, topWorld,
                    "#ea580c", "📍", "DROPOFF", dropoffName, dropoffAddress);
        }

        private void addLocationPin(double lat, double lon, double leftWorld, double topWorld,
                                    String pinColor, String icon, String tag, String name, String addr) {
            double worldX = longitudeToWorldX(lon, zoom) - leftWorld;
            double worldY = latitudeToWorldY(lat, zoom) - topWorld;

            VBox pinContainer = new VBox(2);
            pinContainer.setAlignment(Pos.CENTER);

            StackPane pinHead = new StackPane();
            Circle circle = new Circle(14, Color.web(pinColor));
            circle.setStroke(Color.WHITE);
            circle.setStrokeWidth(2.5);

            Label iconLabel = new Label(icon);
            iconLabel.setStyle("-fx-text-fill: white; -fx-font-size: 11px;");
            pinHead.getChildren().addAll(circle, iconLabel);

            HBox badge = new HBox(4);
            badge.setAlignment(Pos.CENTER);
            badge.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-padding: 3 8 3 8;" +
                    "-fx-background-radius: 12;" +
                    "-fx-border-color: " + pinColor + ";" +
                    "-fx-border-width: 1.5;" +
                    "-fx-border-radius: 12;" +
                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.18), 8, 0, 0, 2);");

            Label tagLbl = new Label(tag);
            tagLbl.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-text-fill: " + pinColor + ";");
            String displayStr = (name != null && name.length() > 18) ? name.substring(0, 16) + "…" : (name != null ? name : "");
            Label nameLbl = new Label(displayStr);
            nameLbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #111827;");
            badge.getChildren().addAll(tagLbl, nameLbl);

            Tooltip tip = new Tooltip(tag + ": " + name + "\n" + addr);
            Tooltip.install(pinContainer, tip);

            pinContainer.getChildren().addAll(pinHead, badge);

            pinContainer.setLayoutX(worldX - 60);
            pinContainer.setLayoutY(worldY - 14);
            pinContainer.setPrefWidth(120);

            markerPane.getChildren().add(pinContainer);
        }

        private static double longitudeToWorldX(double longitude, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            return (longitude + 180.0) / 360.0 * mapSize;
        }

        private static double latitudeToWorldY(double latitude, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            double latitudeRadians = Math.toRadians(latitude);
            double y = (1.0 - Math.log(Math.tan(latitudeRadians) + (1.0 / Math.cos(latitudeRadians))) / Math.PI) / 2.0;
            return y * mapSize;
        }

        private static double worldXToLongitude(double worldX, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            return worldX / mapSize * 360.0 - 180.0;
        }

        private static double worldYToLatitude(double worldY, int zoomLevel) {
            double mapSize = TILE_SIZE * Math.pow(2, zoomLevel);
            double y = 1.0 - (2.0 * worldY / mapSize);
            return Math.toDegrees(Math.atan(Math.sinh(y * Math.PI)));
        }

        private static List<double[]> parseRouteCoordinates(String json) {
            List<double[]> points = new ArrayList<>();
            try {
                int geometryIndex = json.indexOf("\"geometry\"");
                int coordinatesIndex = json.indexOf("\"coordinates\"", Math.max(geometryIndex, 0));
                if (coordinatesIndex < 0) return points;

                int start = json.indexOf("[[", coordinatesIndex);
                int end = json.indexOf("]]", start);
                if (start < 0 || end < 0) return points;

                String coordsBlock = json.substring(start + 2, end);
                String[] pairs = coordsBlock.split("\\],\\s*\\[");
                for (String pair : pairs) {
                    String[] parts = pair.split(",");
                    if (parts.length < 2) continue;
                    double lon = Double.parseDouble(parts[0].trim());
                    double lat = Double.parseDouble(parts[1].trim());
                    points.add(new double[]{lat, lon});
                }
            } catch (Exception ex) {
                System.err.println("OSRM parse error: " + ex.getMessage());
                points.clear();
            }
            return points;
        }

        private static List<double[]> straightLine(double sLat, double sLon, double eLat, double eLon) {
            List<double[]> list = new ArrayList<>();
            list.add(new double[]{sLat, sLon});
            list.add(new double[]{eLat, eLon});
            return list;
        }
    }

    private static void launchExternalNavigation(TripData data) {
        try {
            String origin = URLEncoder.encode(
                    data.pickupAddress != null ? data.pickupAddress : data.pickupLat + "," + data.pickupLng,
                    StandardCharsets.UTF_8);
            String destination = URLEncoder.encode(
                    data.dropoffAddress != null ? data.dropoffAddress : data.dropoffLat + "," + data.dropoffLng,
                    StandardCharsets.UTF_8);

            String navUrl = "https://www.google.com/maps/dir/?api=1"
                    + "&origin=" + origin
                    + "&destination=" + destination
                    + "&travelmode=driving";

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(navUrl));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void launchDialer(String phone) {
        try {
            if (phone != null && !phone.isEmpty()) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(new URI("tel:" + phone));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static VBox createTripDetailsPanel(TripData trip) {
        VBox panel = new VBox(20);
        panel.setPrefWidth(330);
        panel.setMinWidth(330);
        panel.setMaxWidth(330);
        panel.setPadding(new Insets(26, 22, 26, 22));
        panel.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: " + BORDER_COLOR + ";" +
                        "-fx-border-width: 0 0 0 1;");

        BorderPane titleRow = new BorderPane();
        VBox titleBox = new VBox(2);
        Text title = new Text("Trip Details");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-fill: #111827;");
        Label orderIdLbl = new Label(trip.orderNumber);
        orderIdLbl.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(title, orderIdLbl);
        titleRow.setLeft(titleBox);

        Label earnLbl = new Label("₹" + String.format("%.2f", trip.orderEarnings));
        earnLbl.setStyle(
                "-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #15803d; -fx-background-color: #dcfce7; -fx-background-radius: 6; -fx-padding: 4 8 4 8;");
        titleRow.setRight(earnLbl);
        BorderPane.setAlignment(earnLbl, Pos.CENTER_RIGHT);

        VBox etaCard = new VBox();
        etaCard.setPadding(new Insets(16, 18, 16, 18));
        etaCard.setStyle(
                "-fx-background-color: #fafafc;" +
                        "-fx-border-color: " + ORANGE_PRIMARY + " #e5e7eb #e5e7eb #e5e7eb;" +
                        "-fx-border-width: 3 1 1 1;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;");

        BorderPane etaRow = new BorderPane();
        VBox leftEta = new VBox(0);
        Label etaTitle = new Label("EST. ARRIVAL");
        etaTitle.setStyle("-fx-font-size: 9px; -fx-text-fill: #6b7280; -fx-font-weight: bold;");

        HBox etaValBox = new HBox(4);
        etaValBox.setAlignment(Pos.BASELINE_LEFT);
        Label etaVal = new Label(trip.estArrivalMin);
        etaVal.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label etaUnit = new Label("min");
        etaUnit.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        etaValBox.getChildren().addAll(etaVal, etaUnit);
        leftEta.getChildren().addAll(etaTitle, etaValBox);

        VBox rightDist = new VBox(0);
        rightDist.setAlignment(Pos.TOP_RIGHT);
        Label distTitle = new Label("DISTANCE");
        distTitle.setStyle("-fx-font-size: 9px; -fx-text-fill: #6b7280; -fx-font-weight: bold;");
        Label distVal = new Label(trip.distanceMiles);
        distVal.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        rightDist.getChildren().addAll(distTitle, distVal);

        etaRow.setLeft(leftEta);
        etaRow.setRight(rightDist);
        etaCard.getChildren().add(etaRow);

        HBox pickupRow = new HBox(12);
        pickupRow.setAlignment(Pos.TOP_LEFT);

        VBox pickupIconCol = new VBox();
        pickupIconCol.setAlignment(Pos.CENTER);
        Circle pickupCircle = new Circle(14, Color.web("#93380b"));
        Label storeIcon = new Label("🏪");
        storeIcon.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");
        StackPane pickupPin = new StackPane(pickupCircle, storeIcon);

        Line connector = new Line(0, 0, 0, 42);
        connector.setStroke(Color.web("#e5e7eb"));
        connector.setStrokeWidth(2);
        pickupIconCol.getChildren().addAll(pickupPin, connector);

        VBox pickupInfo = new VBox(2);
        Label pickupName = new Label(trip.pickupName);
        pickupName.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label pickupAddr = new Label(trip.pickupAddress);
        pickupAddr.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        Label pickupBadge = new Label("✓ " + trip.pickupStatus);
        pickupBadge.setStyle(
                "-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #92400e; -fx-background-color: #fef3c7; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        pickupInfo.getChildren().addAll(pickupName, pickupAddr, pickupBadge);
        pickupRow.getChildren().addAll(pickupIconCol, pickupInfo);

        HBox dropoffRow = new HBox(12);
        dropoffRow.setAlignment(Pos.TOP_LEFT);

        Circle dropoffCircle = new Circle(14, Color.web(ORANGE_PRIMARY));
        Label userIcon = new Label("👤");
        userIcon.setStyle("-fx-font-size: 10px; -fx-text-fill: white;");
        StackPane dropoffPin = new StackPane(dropoffCircle, userIcon);

        VBox dropoffInfo = new VBox(2);
        Label dropoffName = new Label(trip.dropoffName);
        dropoffName.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label dropoffAddr = new Label(trip.dropoffAddress);
        dropoffAddr.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");

        Label dropoffBadge = new Label("⏱ " + trip.dropoffEta);
        dropoffBadge.setStyle(
                "-fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: #c2410c; -fx-background-color: #ffedd5; -fx-background-radius: 4; -fx-padding: 2 6 2 6;");
        dropoffInfo.getChildren().addAll(dropoffName, dropoffAddr, dropoffBadge);
        dropoffRow.getChildren().addAll(dropoffPin, dropoffInfo);

        HBox trafficCard = new HBox(12);
        trafficCard.setPadding(new Insets(12, 14, 12, 14));
        trafficCard.setAlignment(Pos.CENTER_LEFT);
        trafficCard.setStyle(
                "-fx-background-color: #f3f4f6;" +
                        "-fx-background-radius: 10;");

        Label trafficIcon = new Label("🚦");
        trafficIcon.setStyle("-fx-font-size: 16px;");

        VBox trafficMeta = new VBox(2);
        Label trafficCond = new Label(trip.trafficCondition);
        trafficCond.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        Label trafficDesc = new Label(trip.trafficDescription);
        trafficDesc.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        trafficMeta.getChildren().addAll(trafficCond, trafficDesc);

        trafficCard.getChildren().addAll(trafficIcon, trafficMeta);

        panel.getChildren().addAll(titleRow, etaCard, pickupRow, dropoffRow, trafficCard);
        return panel;
    }

    private static StackPane createAvatarNode(double radius) {
        StackPane avatarPane = new StackPane();
        avatarPane.setPrefSize(radius * 2, radius * 2);
        avatarPane.setMaxSize(radius * 2, radius * 2);

        Circle bg = new Circle(radius, Color.web("#fed7aa"));

        if (PartnerConstants.PROFILE_PHOTO_URL != null && !PartnerConstants.PROFILE_PHOTO_URL.trim().isEmpty()) {
            try {
                ImageView imgView = new ImageView(new Image(PartnerConstants.PROFILE_PHOTO_URL, true));
                imgView.setFitWidth(radius * 2);
                imgView.setFitHeight(radius * 2);
                imgView.setPreserveRatio(false);

                Circle clip = new Circle(radius, radius, radius);
                imgView.setClip(clip);

                avatarPane.getChildren().addAll(bg, imgView);
                return avatarPane;
            } catch (Exception ignored) {}
        }

        String initials = "P";
        if (PartnerConstants.FULL_NAME != null && !PartnerConstants.FULL_NAME.trim().isEmpty()) {
            String[] parts = PartnerConstants.FULL_NAME.trim().split("\\s+");
            if (parts.length >= 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                initials = ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
            } else if (!parts[0].isEmpty()) {
                initials = parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
            }
        }

        Label initialLabel = new Label(initials);
        initialLabel.setStyle("-fx-font-size: " + (radius * 0.7) + "px; -fx-font-weight: bold; -fx-text-fill: #b45309;");
        avatarPane.getChildren().addAll(bg, initialLabel);

        return avatarPane;
    }

    private static Button createNavButton(String text, boolean active) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setPrefHeight(38);

        if (active) {
            btn.setStyle(
                    "-fx-background-color: " + ORANGE_PRIMARY + ";" +
                            "-fx-text-fill: white;" +
                            "-fx-font-weight: bold;" +
                            "-fx-font-size: 12px;" +
                            "-fx-background-radius: 8;" +
                            "-fx-padding: 0 14 0 14;" +
                            "-fx-cursor: hand;");
        } else {
            btn.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-text-fill: #6b7280;" +
                            "-fx-font-size: 12px;" +
                            "-fx-padding: 0 14 0 14;" +
                            "-fx-cursor: hand;");
            btn.setOnMouseEntered(e -> btn.setStyle(
                    "-fx-background-color: #fcece3; -fx-text-fill: #a94717; -fx-font-size: 12px; -fx-background-radius: 8; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
            btn.setOnMouseExited(e -> btn.setStyle(
                    "-fx-background-color: transparent; -fx-text-fill: #6b7280; -fx-font-size: 12px; -fx-padding: 0 14 0 14; -fx-cursor: hand;"));
        }
        return btn;
    }
}

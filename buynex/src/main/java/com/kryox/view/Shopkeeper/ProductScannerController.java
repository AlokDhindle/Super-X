package com.kryox.view.Shopkeeper;

import com.google.cloud.firestore.DocumentSnapshot;
import com.kryox.dao.Shopkeeper.OnlineBarcodeLookupService;
import com.kryox.dao.Shopkeeper.ProductScannerDAO;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamLockException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.view.Customer.Homepage;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * BuyNeX Product QR / Barcode Scanner
 *
 * Add Product -> Scan Product -> Camera -> QR/Barcode -> Firebase -> Details
 */
public class ProductScannerController {

    /* ------------------------------------------------------------
       CAMERA STATE
       One physical camera is shared inside this JVM.
       ------------------------------------------------------------ */
    private static Webcam webcam;
    private static ExecutorService executor;
    private static final Object CAMERA_LOCK = new Object();
    private static final AtomicBoolean CAMERA_OPENING = new AtomicBoolean(false);
    private static ProductScannerController activeScanner;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                if (webcam != null) {
                    if (webcam.getLock() != null) {
                        try {
                            webcam.getLock().unlock();
                        } catch (Exception ignored) {}
                    }
                    if (webcam.isOpen()) {
                        webcam.close();
                    }
                }
                cleanStaleLockFiles();
            } catch (Exception ignored) {}
        }, "BuyNeX-Webcam-ShutdownHook"));
    }

    private volatile boolean scanning = false;
    private volatile boolean closed = false;

    /* UI */
    private ImageView cameraView;
    private Label scanStatus;
    private VBox productDetails;

    /* Firebase */
    private final ProductScannerDAO dao = new ProductScannerDAO();

    public Scene openScanner() {

        /*
         * IMPORTANT:
         * If an older scanner controller is still active, stop its scanner
         * and close the camera before this controller starts.
         *
         * This prevents:
         * WebcamLockException:
         * Webcam Integrated Camera 0 has already been locked
         */
        cleanStaleLockFiles();
        synchronized (CAMERA_LOCK) {
            if (activeScanner != null && activeScanner != this) {
                activeScanner.shutdownCameraInternal();
            }
            activeScanner = this;
            closed = false;
        }

        stopScanningThread();

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f3e8e0;");

        HBox header = new HBox();
        header.setPadding(new Insets(18, 28, 18, 28));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #EFCDB7;");

        Label title = new Label("BuyNeX");
        title.setStyle(
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C7460B;"
        );

        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);

        Label pageTitle = new Label("PRODUCT SCANNER");
        pageTitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #403936;"
        );

        header.getChildren().addAll(title, headerSpacer, pageTitle);
        root.setTop(header);

        HBox content = new HBox(25);
        content.setPadding(new Insets(25, 30, 30, 30));
        content.setAlignment(Pos.TOP_CENTER);

        /* LEFT: CAMERA */
        VBox scannerCard = new VBox(16);
        scannerCard.setPrefWidth(680);
        scannerCard.setPadding(new Insets(25));
        scannerCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;"
        );

        Label scannerTitle = new Label("Scan Product");
        scannerTitle.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292525;"
        );

        Label instruction = new Label(
                "Place the QR code or barcode inside the frame."
        );
        instruction.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #77716E;"
        );

        StackPane cameraContainer = new StackPane();
        cameraContainer.setPrefSize(620, 430);
        cameraContainer.setStyle(
                "-fx-background-color: #181818;" +
                "-fx-background-radius: 12;"
        );

        cameraView = new ImageView();
        cameraView.setFitWidth(620);
        cameraView.setFitHeight(430);
        cameraView.setPreserveRatio(true);

        Rectangle scannerFrame = new Rectangle(380, 230);
        scannerFrame.setFill(Color.TRANSPARENT);
        scannerFrame.setStroke(Color.ORANGE);
        scannerFrame.setStrokeWidth(3);
        scannerFrame.setArcWidth(15);
        scannerFrame.setArcHeight(15);

        cameraContainer.getChildren().addAll(cameraView, scannerFrame);

        scanStatus = new Label("Starting camera...");
        scanStatus.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #777;"
        );

        HBox buttonBox = new HBox(12);
        buttonBox.setAlignment(Pos.CENTER);

        Button scanAgain = new Button("🔄  Scan Again");
        scanAgain.setPrefWidth(180);
        scanAgain.setPrefHeight(43);
        scanAgain.setStyle(
                "-fx-background-color: #FF6900;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        scanAgain.setOnAction(e -> restartScanner());

        Button closeButton = new Button("Close");
        closeButton.setPrefWidth(130);
        closeButton.setPrefHeight(43);
        closeButton.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #C7460B;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #C7460B;" +
                "-fx-border-width: 1.5;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        closeButton.setOnAction(e -> {
            stopCamera();
            goBackToAddProduct();
        });

        buttonBox.getChildren().addAll(scanAgain, closeButton);

        HBox manualSearchBox = new HBox(10);
        manualSearchBox.setAlignment(Pos.CENTER);

        TextField manualField = new TextField();
        manualField.setPromptText("Type or scan barcode / SKU / product ID...");
        manualField.setPrefWidth(350);
        manualField.setPrefHeight(40);
        manualField.setStyle(
                "-fx-background-color: #FAF8F6;" +
                "-fx-background-radius: 8;" +
                "-fx-border-color: #D6C2B4;" +
                "-fx-border-radius: 8;" +
                "-fx-font-size: 13px;" +
                "-fx-padding: 0 12px;"
        );

        Button manualButton = new Button("🔍  Search");
        manualButton.setPrefWidth(120);
        manualButton.setPrefHeight(40);
        manualButton.setStyle(
                "-fx-background-color: #C7460B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );

        manualButton.setOnAction(e -> {
            String code = manualField.getText() != null ? manualField.getText().trim() : "";
            if (!code.isEmpty()) {
                scanning = false;
                updateStatus("✓ Checking code: " + code);
                loadProduct(code);
            }
        });
        manualField.setOnAction(e -> manualButton.fire());

        manualSearchBox.getChildren().addAll(manualField, manualButton);

        scannerCard.getChildren().addAll(
                scannerTitle,
                instruction,
                cameraContainer,
                scanStatus,
                manualSearchBox,
                buttonBox
        );

        /* RIGHT: PRODUCT DETAILS */
        VBox detailsCard = new VBox(16);
        detailsCard.setPrefWidth(450);
        detailsCard.setPadding(new Insets(25));
        detailsCard.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 15;"
        );

        Label detailsTitle = new Label("Product Details");
        detailsTitle.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292525;"
        );

        Label detailsSub = new Label(
                "Scanned product information will appear here."
        );
        detailsSub.setWrapText(true);
        detailsSub.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #77716E;"
        );

        productDetails = new VBox(12);

        VBox emptyBox = new VBox(10);
        emptyBox.setAlignment(Pos.CENTER);
        emptyBox.setPrefHeight(400);

        Label qrIcon = new Label("▦");
        qrIcon.setStyle(
                "-fx-font-size: 65px;" +
                "-fx-text-fill: #C7460B;"
        );

        Label emptyText = new Label("Scan a product to view details");
        emptyText.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #888;"
        );

        emptyBox.getChildren().addAll(qrIcon, emptyText);
        productDetails.getChildren().add(emptyBox);

        detailsCard.getChildren().addAll(
                detailsTitle,
                detailsSub,
                productDetails
        );

        content.getChildren().addAll(scannerCard, detailsCard);
        root.setCenter(content);

        Scene scene = new Scene(root, 1550, 850);
        scene.setFill(Color.web("#f3e8e0"));

        Platform.runLater(this::startCamera);

        return scene;
    }

    /* ------------------------------------------------------------
       START CAMERA
       ------------------------------------------------------------ */
    private void startCamera() {

        if (closed) {
            return;
        }

        synchronized (CAMERA_LOCK) {

            if (activeScanner != this || closed) {
                return;
            }

            if (webcam != null && webcam.isOpen()) {
                scanning = true;
                updateStatus("🟢 Camera ready - Scan QR / Barcode");
                startScanThread();
                return;
            }

            if (!CAMERA_OPENING.compareAndSet(false, true)) {
                return;
            }

            try {
                System.out.println("Opening BuyNeX camera...");
                cleanStaleLockFiles();

                webcam = Webcam.getDefault();

                if (webcam == null) {
                    updateStatus("❌ Camera not found");
                    return;
                }

                // Force unlock and disable the stale lock file mechanism
                try {
                    if (webcam.getLock() != null) {
                        if (webcam.getLock().isLocked()) {
                            webcam.getLock().unlock();
                        }
                        webcam.getLock().disable();
                    }
                } catch (Exception lockEx) {
                    System.err.println("Warning disabling webcam lock: " + lockEx.getMessage());
                }

                webcam.setViewSize(new Dimension(640, 480));

                /*
                 * Do not call open() twice.
                 */
                if (!webcam.isOpen()) {
                    webcam.open();
                }

                System.out.println("BuyNeX camera opened.");

                scanning = true;
                updateStatus("🟢 Camera ready - Scan QR / Barcode");
                startScanThread();

            } catch (WebcamLockException e) {
                System.err.println("CAMERA LOCK ERROR - Attempting force unlock and retry");
                cleanStaleLockFiles();
                try {
                    if (webcam != null && webcam.getLock() != null) {
                        webcam.getLock().unlock();
                        webcam.getLock().disable();
                        if (!webcam.isOpen()) {
                            webcam.open();
                        }
                        scanning = true;
                        updateStatus("🟢 Camera ready - Scan QR / Barcode");
                        startScanThread();
                        return;
                    }
                } catch (Exception retryEx) {
                    retryEx.printStackTrace();
                }
                updateStatus("❌ Camera was busy. Click 'Scan Again' to restart.");
                closeWebcamOnly();

            } catch (Exception e) {
                e.printStackTrace();
                updateStatus("❌ Unable to start camera");
                closeWebcamOnly();

            } finally {
                CAMERA_OPENING.set(false);
            }
        }
    }

    /* ------------------------------------------------------------
       SCAN THREAD
       ------------------------------------------------------------ */
    private synchronized void startScanThread() {

        if (closed) {
            return;
        }

        if (executor != null &&
                !executor.isShutdown() &&
                !executor.isTerminated()) {
            return;
        }

        scanning = true;
        executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r, "BuyNeX-QR-Scanner");
            t.setDaemon(true);
            return t;
        });

        executor.submit(this::scanLoop);
    }

    private void scanLoop() {

        MultiFormatReader reader = new MultiFormatReader();
        Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hints.put(DecodeHintType.POSSIBLE_FORMATS, Arrays.asList(
                BarcodeFormat.EAN_13,
                BarcodeFormat.EAN_8,
                BarcodeFormat.CODE_128,
                BarcodeFormat.CODE_39,
                BarcodeFormat.ITF,
                BarcodeFormat.QR_CODE,
                BarcodeFormat.DATA_MATRIX
        ));
        reader.setHints(hints);

        while (scanning && !closed) {

            try {

                Webcam currentCamera = webcam;

                if (currentCamera == null || !currentCamera.isOpen()) {
                    break;
                }

                BufferedImage frame = currentCamera.getImage();

                if (frame == null) {
                    Thread.sleep(50);
                    continue;
                }

                Image fxImage = SwingFXUtils.toFXImage(frame, null);

                Platform.runLater(() -> {
                    if (!closed && cameraView != null) {
                        cameraView.setImage(fxImage);
                    }
                });

                LuminanceSource source =
                        new BufferedImageLuminanceSource(frame);

                Result result = null;

                // Strategy 1: HybridBinarizer on full frame
                try {
                    result = reader.decodeWithState(new BinaryBitmap(new HybridBinarizer(source)));
                } catch (NotFoundException ignored) {
                }

                // Strategy 2: GlobalHistogramBinarizer on full frame (reliable for 1D retail barcodes on webcams)
                if (result == null) {
                    try {
                        result = reader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(source)));
                    } catch (NotFoundException ignored) {
                    }
                }

                // Strategy 3: Center cropped region (user aims at center box)
                if (result == null) {
                    try {
                        int w = frame.getWidth();
                        int h = frame.getHeight();
                        int cropW = (int) (w * 0.70);
                        int cropH = (int) (h * 0.55);
                        int cropX = (w - cropW) / 2;
                        int cropY = (h - cropH) / 2;

                        LuminanceSource cropped = source.crop(cropX, cropY, cropW, cropH);

                        try {
                            result = reader.decodeWithState(new BinaryBitmap(new HybridBinarizer(cropped)));
                        } catch (NotFoundException e) {
                            result = reader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(cropped)));
                        }
                    } catch (Exception ignored) {
                    }
                }

                reader.reset();

                if (result != null) {

                    String scannedCode = result.getText();

                    if (scannedCode != null &&
                            !scannedCode.trim().isEmpty()) {

                        System.out.println(
                                "======================================"
                        );
                        System.out.println(
                                "SCANNED CODE: " + scannedCode + " (Format: " + result.getBarcodeFormat() + ")"
                        );
                        System.out.println(
                                "======================================"
                        );

                        scanning = false;

                        Platform.runLater(() -> {
                            updateStatus(
                                    "✓ Code detected: " + scannedCode
                            );
                            loadProduct(scannedCode);
                        });

                        break;
                    }
                }

                Thread.sleep(60);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;

            } catch (Exception e) {
                if (scanning && !closed) {
                    System.err.println(
                            "Scanner frame error: " + e.getMessage()
                    );
                }
            }
        }
    }

    /* ------------------------------------------------------------
       PRODUCT SEARCH (FIRESTORE + ONLINE CATALOG)
       ------------------------------------------------------------ */
    private void loadProduct(String scannedCode) {

        updateStatus("🔍 Searching store inventory & global catalog...");

        Thread searchThread = new Thread(() -> {

            try {

                // 1. Check local Firestore store inventory
                DocumentSnapshot doc = dao.findProduct(scannedCode);

                if (doc != null && doc.exists()) {
                    Platform.runLater(() -> {
                        showProduct(doc);
                        updateStatus("✓ Found in Store Inventory");
                    });
                    return;
                }

                // 2. Not in inventory -> Query online catalog (Open Food Facts + Indian GS1)
                Platform.runLater(() -> updateStatus("🌐 Searching online barcode catalog..."));
                ProductModel onlineProduct = OnlineBarcodeLookupService.lookup(scannedCode);

                Platform.runLater(() -> {
                    if (onlineProduct != null) {
                        showOnlineProduct(onlineProduct);
                        updateStatus("✓ Identified: " + onlineProduct.getProductName());
                    } else {
                        showProductNotFound(scannedCode);
                        updateStatus("❌ Product not found");
                    }
                });

            } catch (Exception e) {

                e.printStackTrace();

                Platform.runLater(() -> {
                    updateStatus("❌ Search error");
                    showError(e.getMessage());
                });
            }

        }, "BuyNeX-Product-Search");

        searchThread.setDaemon(true);
        searchThread.start();
    }

    /* ------------------------------------------------------------
       ONLINE CATALOG PRODUCT CARD
       ------------------------------------------------------------ */
    private void showOnlineProduct(ProductModel model) {

        if (productDetails == null) {
            return;
        }

        productDetails.getChildren().clear();
        productDetails.setAlignment(Pos.TOP_LEFT);

        Label badge = new Label("✦  ONLINE BARCODE RECOGNITION");
        badge.setStyle(
                "-fx-background-color: #E8F5E9;" +
                "-fx-text-fill: #2E7D32;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 11px;" +
                "-fx-padding: 4 10 4 10;" +
                "-fx-background-radius: 12;"
        );

        Label productNameLabel = new Label(model.getProductName());
        productNameLabel.setWrapText(true);
        productNameLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292525;"
        );

        // Product image preview from online catalog if available
        ImageView onlineImgView = null;
        if (model.getImageUrl() != null && !model.getImageUrl().trim().isEmpty()) {
            try {
                Image img = new Image(model.getImageUrl(), 200, 130, true, true, true);
                onlineImgView = new ImageView(img);
                onlineImgView.setFitWidth(200);
                onlineImgView.setFitHeight(130);
                onlineImgView.setPreserveRatio(true);
                onlineImgView.setStyle(
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 6, 0, 0, 2);"
                );
            } catch (Exception ignored) {}
        }

        Button autofillButton = new Button("  Autofill to Add Product");
        autofillButton.setPrefWidth(Double.MAX_VALUE);
        autofillButton.setPrefHeight(44);
        autofillButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        autofillButton.setOnAction(e -> {
            stopCamera();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(ShopkeeperInventoryAddProduct.addProductScene(model));
            }
        });

        productDetails.getChildren().addAll(badge, productNameLabel);

        if (onlineImgView != null) {
            productDetails.getChildren().add(onlineImgView);
        }

        String autoSku = model.getSku();
        if (autoSku == null || autoSku.isEmpty()) {
            String b = model.getBrand() != null ? model.getBrand().toUpperCase().replaceAll("[^A-Z]", "") : "PROD";
            String code = model.getBarcode() != null ? model.getBarcode() : "0000";
            autoSku = b + "-" + code.substring(Math.max(0, code.length() - 4));
            model.setSku(autoSku);
        }

        productDetails.getChildren().addAll(
                autofillButton,
                createInfoRow("Brand", model.getBrand()),
                createInfoRow("Category", model.getCategory()),
                createInfoRow("Barcode", model.getBarcode()),
                createInfoRow("Generated SKU", autoSku),
                createInfoRow("Unit", model.getUnit() != null ? model.getUnit() : "Piece")
        );

        if (model.getDescriptionValue() != null && !model.getDescriptionValue().trim().isEmpty()) {
            Label descTitle = new Label("Description");
            descTitle.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #333;");
            Label descLabel = new Label(model.getDescriptionValue());
            descLabel.setWrapText(true);
            descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666;");
            productDetails.getChildren().addAll(descTitle, descLabel);
        }
    }

    /* ------------------------------------------------------------
       PRODUCT DETAILS
       ------------------------------------------------------------ */
    private void showProduct(DocumentSnapshot doc) {

        if (productDetails == null) {
            return;
        }

        productDetails.getChildren().clear();
        productDetails.setAlignment(Pos.TOP_LEFT);

        ProductModel model = null;
        try {
            model = doc.toObject(ProductModel.class);
        } catch (Exception ignored) {}

        String name = (model != null && model.getProductName() != null)
                ? model.getProductName()
                : getString(doc, "productName", getString(doc, "name", "Unknown Product"));

        String brand = (model != null && model.getBrand() != null)
                ? model.getBrand()
                : getString(doc, "brand", "-");

        String category = (model != null && model.getCategory() != null)
                ? model.getCategory()
                : getString(doc, "category", "-");

        String barcode = (model != null && model.getBarcode() != null)
                ? model.getBarcode()
                : getString(doc, "barcode", doc.getId());

        String sku = (model != null && model.getSku() != null)
                ? model.getSku()
                : getString(doc, "sku", "-");

        String status = (model != null && model.getStatus() != null)
                ? model.getStatus()
                : getString(doc, "status", "ACTIVE");

        String description = (model != null && model.getDescriptionValue() != null)
                ? model.getDescriptionValue()
                : getString(doc, "descriptionValue", getString(doc, "description", "No description available."));

        Double sellingPrice = (model != null && model.getSellingPrice() != null)
                ? model.getSellingPrice()
                : getDouble(doc, "sellingPrice", 0.0);

        Double mrp = (model != null && model.getMrp() != null)
                ? model.getMrp()
                : getDouble(doc, "mrp", getDouble(doc, "originalPrice", 0.0));

        Double costPrice = (model != null && model.getCostPrice() != null)
                ? model.getCostPrice()
                : getDouble(doc, "costPrice", 0.0);

        int stock = (model != null && model.getStockQuantity() > 0)
                ? model.getStockQuantity()
                : getLong(doc, "stockQuantity", getLong(doc, "stock", 0L)).intValue();

        String imageUrl = (model != null && model.getImageUrl() != null)
                ? model.getImageUrl()
                : getString(doc, "imageUrl", null);

        final ProductModel productForPrefill;
        if (model != null) {
            productForPrefill = model;
            if (productForPrefill.getProductName() == null) productForPrefill.setProductName(name);
            if (productForPrefill.getBarcode() == null) productForPrefill.setBarcode(barcode);
            if (productForPrefill.getBrand() == null) productForPrefill.setBrand(brand);
            if (productForPrefill.getCategory() == null) productForPrefill.setCategory(category);
            if (productForPrefill.getSku() == null) productForPrefill.setSku(sku);
            if (productForPrefill.getDescriptionValue() == null) productForPrefill.setDescriptionValue(description);
            if (productForPrefill.getMrp() == null) productForPrefill.setMrp(mrp);
            if (productForPrefill.getSellingPrice() == null) productForPrefill.setSellingPrice(sellingPrice);
            if (productForPrefill.getCostPrice() == null) productForPrefill.setCostPrice(costPrice);
            if (productForPrefill.getStockQuantity() == 0) productForPrefill.setStockQuantity(stock);
            if (productForPrefill.getStatus() == null) productForPrefill.setStatus(status);
        } else {
            productForPrefill = new ProductModel();
            productForPrefill.setProductName(name);
            productForPrefill.setBrand(brand);
            productForPrefill.setCategory(category);
            productForPrefill.setBarcode(barcode);
            productForPrefill.setSku(sku);
            productForPrefill.setSellingPrice(sellingPrice);
            productForPrefill.setMrp(mrp);
            productForPrefill.setCostPrice(costPrice);
            productForPrefill.setStockQuantity(stock);
            productForPrefill.setStatus(status);
            productForPrefill.setDescriptionValue(description);
            productForPrefill.setImageUrl(imageUrl);
        }

        Label productNameLabel = new Label(name);
        productNameLabel.setWrapText(true);
        productNameLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292525;"
        );

        Label price = new Label(
                "₹ " + String.format("%.2f", sellingPrice)
        );
        price.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #C7460B;"
        );

        Button autofillButton = new Button("✨  Autofill to Add Product");
        autofillButton.setPrefWidth(Double.MAX_VALUE);
        autofillButton.setPrefHeight(44);
        autofillButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #A94408, #FF6900);" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        autofillButton.setOnAction(e -> {
            stopCamera();
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(ShopkeeperInventoryAddProduct.addProductScene(productForPrefill));
            }
        });

        productDetails.getChildren().addAll(
                productNameLabel,
                price,
                autofillButton,
                createInfoRow("Brand", brand),
                createInfoRow("Category", category),
                createInfoRow("Barcode", barcode),
                createInfoRow("SKU", sku),
                createInfoRow(
                        "Original Price (MRP)",
                        "₹ " + String.format("%.2f", mrp)
                ),
                createInfoRow(
                        "Selling Price",
                        "₹ " + String.format("%.2f", sellingPrice)
                ),
                createInfoRow("Stock", String.valueOf(stock)),
                createInfoRow("Status", status)
        );

        if (description != null && !description.trim().isEmpty()) {
            Label descriptionTitle = new Label("Description");
            descriptionTitle.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #333;"
            );

            Label descriptionLabel = new Label(description);
            descriptionLabel.setWrapText(true);
            descriptionLabel.setStyle(
                    "-fx-font-size: 12px;" +
                    "-fx-text-fill: #666;"
            );

            productDetails.getChildren().addAll(
                    descriptionTitle,
                    descriptionLabel
            );
        }
    }

    private HBox createInfoRow(String title, String value) {

        HBox row = new HBox(10);
        row.setAlignment(Pos.CENTER_LEFT);

        Label label = new Label(title);
        label.setPrefWidth(130);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #777;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setWrapText(true);
        valueLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #333;"
        );

        HBox.setHgrow(valueLabel, Priority.ALWAYS);
        row.getChildren().addAll(label, valueLabel);

        return row;
    }

    private void showProductNotFound(String code) {

        if (productDetails == null) {
            return;
        }

        productDetails.getChildren().clear();
        productDetails.setAlignment(Pos.CENTER);

        Label icon = new Label("⚠");
        icon.setStyle(
                "-fx-font-size: 55px;" +
                "-fx-text-fill: #C7460B;"
        );

        Label title = new Label("Product Not Found");
        title.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #292525;"
        );

        Label codeLabel = new Label("Scanned Code: " + code);
        codeLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #777;"
        );

        Label message = new Label(
                "This product does not exist in your BuyNeX inventory.\nYou can use this barcode to create a new product!"
        );
        message.setWrapText(true);
        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #777;" +
                "-fx-text-alignment: center;"
        );

        Button useBarcodeButton = new Button("➕  Use this Barcode to Add Product");
        useBarcodeButton.setPrefWidth(280);
        useBarcodeButton.setPrefHeight(42);
        useBarcodeButton.setStyle(
                "-fx-background-color: #C7460B;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8;" +
                "-fx-cursor: hand;"
        );
        useBarcodeButton.setOnAction(e -> {

            
            stopCamera();
            ProductModel emptyWithBarcode = new ProductModel();
            emptyWithBarcode.setBarcode(code);
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(ShopkeeperInventoryAddProduct.addProductScene(emptyWithBarcode));
            }
        });

        productDetails.getChildren().addAll(
                icon,
                title,
                codeLabel,
                message,
                useBarcodeButton
        );
    }

    private void showError(String message) {

        if (productDetails == null) {
            return;
        }

        productDetails.getChildren().clear();
        productDetails.setAlignment(Pos.CENTER);

        Label error = new Label(
                "Something went wrong.\n\n" +
                (message != null ? message : "Unknown error")
        );

        error.setWrapText(true);
        error.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #C7460B;"
        );

        productDetails.getChildren().add(error);
    }

    /* ------------------------------------------------------------
       SCAN AGAIN
       ------------------------------------------------------------ */
    private void restartScanner() {

        closed = false;

        System.out.println("Restarting scanner...");

        stopScanningThread();
        closeWebcamOnly();
        cleanStaleLockFiles();

        if (productDetails != null) {

            productDetails.getChildren().clear();
            productDetails.setAlignment(Pos.CENTER);

            Label icon = new Label("▦");
            icon.setStyle(
                    "-fx-font-size: 55px;" +
                    "-fx-text-fill: #C7460B;"
            );

            Label text = new Label(
                    "Scan a product to view details"
            );
            text.setStyle(
                    "-fx-font-size: 15px;" +
                    "-fx-text-fill: #888;"
            );

            productDetails.getChildren().addAll(icon, text);
        }

        Platform.runLater(() -> {
            updateStatus("🟡 Starting camera...");
            startCamera();
        });
    }

    /* ------------------------------------------------------------
       STOP SCANNER THREAD ONLY
       ------------------------------------------------------------ */
    private synchronized void stopScanningThread() {

        scanning = false;

        if (executor != null) {
            try {
                executor.shutdownNow();
            } catch (Exception ignored) {
            }
            executor = null;
        }
    }

    /* ------------------------------------------------------------
       PUBLIC: STOP CAMERA
       ------------------------------------------------------------ */
    public void stopCamera() {

        synchronized (CAMERA_LOCK) {

            closed = true;

            if (activeScanner == this) {
                activeScanner = null;
            }

            stopScanningThread();
            closeWebcamOnly();

            System.out.println("Scanner closed.");
        }
    }

    /* ------------------------------------------------------------
       INTERNAL CAMERA CLOSE
       ------------------------------------------------------------ */
    private void shutdownCameraInternal() {

        closed = true;
        stopScanningThread();
        closeWebcamOnly();
    }

    private void closeWebcamOnly() {

        Webcam cameraToClose = webcam;
        webcam = null;

        if (cameraToClose != null) {

            try {
                if (cameraToClose.getLock() != null) {
                    try {
                        cameraToClose.getLock().unlock();
                    } catch (Exception ignored) {}
                }

                if (cameraToClose.isOpen()) {
                    cameraToClose.close();
                    System.out.println("BuyNeX camera closed.");
                }

            } catch (Exception e) {
                System.err.println(
                        "Error while closing camera: " + e.getMessage()
                );
            }
        }

        cleanStaleLockFiles();
        CAMERA_OPENING.set(false);
    }

    private static void cleanStaleLockFiles() {
        try {
            File tmpDir = new File(System.getProperty("java.io.tmpdir"));
            File[] files = tmpDir.listFiles((dir, name) -> name != null && name.contains("webcam-lock"));
            if (files != null) {
                for (File f : files) {
                    try {
                        f.delete();
                    } catch (Exception ignored) {}
                }
            }
        } catch (Exception ignored) {}
    }

    /* ------------------------------------------------------------
       STATUS
       ------------------------------------------------------------ */
    private void updateStatus(String message) {

        if (scanStatus == null) {
            return;
        }

        if (Platform.isFxApplicationThread()) {
            scanStatus.setText(message);
        } else {
            Platform.runLater(() -> {
                if (scanStatus != null) {
                    scanStatus.setText(message);
                }
            });
        }
    }

    /* ------------------------------------------------------------
       BACK
       ------------------------------------------------------------ */
    private void goBackToAddProduct() {
        Platform.runLater(() -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(ShopkeeperInventoryAddProduct.addProductScene());
            }
        });
    }

    /* ------------------------------------------------------------
       SAFE FIRESTORE VALUES
       ------------------------------------------------------------ */
    private String getString(
            DocumentSnapshot doc,
            String field,
            String defaultValue) {

        Object value = doc.get(field);

        if (value == null) {
            return defaultValue;
        }

        return String.valueOf(value);
    }

    private Double getDouble(
            DocumentSnapshot doc,
            String field) {
        return getDouble(doc, field, 0.0);
    }

    private Double getDouble(
            DocumentSnapshot doc,
            String field,
            Double defaultValue) {

        Object value = doc.get(field);

        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }

        try {
            if (value == null) {
                return defaultValue;
            }

            return Double.parseDouble(value.toString());

        } catch (Exception e) {
            return defaultValue;
        }
    }

    private Long getLong(
            DocumentSnapshot doc,
            String field) {
        return getLong(doc, field, 0L);
    }

    private Long getLong(
            DocumentSnapshot doc,
            String field,
            Long defaultValue) {

        Object value = doc.get(field);

        if (value instanceof Number) {
            return ((Number) value).longValue();
        }

        try {
            if (value == null) {
                return defaultValue;
            }

            return Long.parseLong(value.toString());

        } catch (Exception e) {
            return defaultValue;
        }
    }
}

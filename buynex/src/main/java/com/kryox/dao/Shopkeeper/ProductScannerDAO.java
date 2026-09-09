package com.kryox.dao.Shopkeeper;

import com.google.cloud.firestore.*;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductScannerDAO {

    private final Firestore db = Firebaseconfig.gFirestore();

    /**
     * Finds a product by scanned barcode, SKU, or product ID.
     * 1. Checks current shopkeeper's Products subcollection
     * 2. Checks global collectionGroup("Products") across all shopkeepers
     * 3. Checks root "Products" and "products" collections
     */
    public DocumentSnapshot findProduct(String scannedCode)
            throws ExecutionException, InterruptedException {

        if (scannedCode == null || scannedCode.trim().isEmpty()) {
            return null;
        }

        scannedCode = scannedCode.trim();

        // 1. Check logged-in shopkeeper's Products collection first
        String shopkeeperUid = ShopkeeperLogController.getShopkeeperUid();
        if (shopkeeperUid != null && !shopkeeperUid.trim().isEmpty()) {
            try {
                CollectionReference shopProducts = db.collection("Shopkeepers")
                        .document(shopkeeperUid)
                        .collection("Products");

                // Check by document ID
                DocumentSnapshot directDoc = shopProducts.document(scannedCode).get().get();
                if (directDoc != null && directDoc.exists()) {
                    return directDoc;
                }

                // Check by barcode
                List<QueryDocumentSnapshot> byBarcode = shopProducts
                        .whereEqualTo("barcode", scannedCode)
                        .limit(1)
                        .get().get().getDocuments();
                if (!byBarcode.isEmpty()) {
                    return byBarcode.get(0);
                }

                // Check by productId
                List<QueryDocumentSnapshot> byProductId = shopProducts
                        .whereEqualTo("productId", scannedCode)
                        .limit(1)
                        .get().get().getDocuments();
                if (!byProductId.isEmpty()) {
                    return byProductId.get(0);
                }

                // Check by SKU
                List<QueryDocumentSnapshot> bySku = shopProducts
                        .whereEqualTo("sku", scannedCode)
                        .limit(1)
                        .get().get().getDocuments();
                if (!bySku.isEmpty()) {
                    return bySku.get(0);
                }
            } catch (Exception e) {
                System.err.println("Error searching shopkeeper products: " + e.getMessage());
            }
        }

        // 2. Search across ALL shopkeepers with collectionGroup("Products")
        try {
            List<QueryDocumentSnapshot> cgBarcode = db.collectionGroup("Products")
                    .whereEqualTo("barcode", scannedCode)
                    .limit(1)
                    .get().get().getDocuments();
            if (!cgBarcode.isEmpty()) {
                return cgBarcode.get(0);
            }

            List<QueryDocumentSnapshot> cgProductId = db.collectionGroup("Products")
                    .whereEqualTo("productId", scannedCode)
                    .limit(1)
                    .get().get().getDocuments();
            if (!cgProductId.isEmpty()) {
                return cgProductId.get(0);
            }

            List<QueryDocumentSnapshot> cgSku = db.collectionGroup("Products")
                    .whereEqualTo("sku", scannedCode)
                    .limit(1)
                    .get().get().getDocuments();
            if (!cgSku.isEmpty()) {
                return cgSku.get(0);
            }
        } catch (Exception e) {
            System.err.println("Error searching collectionGroup Products: " + e.getMessage());
        }

        // 3. Fallback to root "Products" or "products" collections
        for (String collName : new String[]{"Products", "products"}) {
            try {
                DocumentReference rootDocRef = db.collection(collName).document(scannedCode);
                DocumentSnapshot rootDoc = rootDocRef.get().get();
                if (rootDoc != null && rootDoc.exists()) {
                    return rootDoc;
                }

                List<QueryDocumentSnapshot> rootBarcode = db.collection(collName)
                        .whereEqualTo("barcode", scannedCode)
                        .limit(1)
                        .get().get().getDocuments();
                if (!rootBarcode.isEmpty()) {
                    return rootBarcode.get(0);
                }

                List<QueryDocumentSnapshot> rootProductId = db.collection(collName)
                        .whereEqualTo("productId", scannedCode)
                        .limit(1)
                        .get().get().getDocuments();
                if (!rootProductId.isEmpty()) {
                    return rootProductId.get(0);
                }
            } catch (Exception e) {
                // Ignore collection-specific fallback errors
            }
        }

        return null;
    }
}
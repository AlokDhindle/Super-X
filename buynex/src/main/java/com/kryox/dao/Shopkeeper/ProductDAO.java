package com.kryox.dao.Shopkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.view.Shopkeeper.ShopkeeperInventory;
import com.kryox.view.Shopkeeper.ViewConstants;

public class ProductDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();



    public void addProduct(ProductModel productModel) {

        System.out.println("Adding Product...");

        try {

            if (productModel == null) {

                System.out.println(
                        "ERROR: ProductModel is null."
                );

                return;
            }

            String shopkeeperUid =
                    ShopkeeperLogController.getShopkeeperUid();

            if (shopkeeperUid == null ||
                    shopkeeperUid.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Shopkeeper UID is missing."
                );

                return;
            }

            if (productModel.getProductId() == null ||
                    productModel.getProductId().trim().isEmpty()) {

                System.out.println(
                        "ERROR: Product ID is missing."
                );

                return;
            }

            if (productModel.getShopkeeperUid() == null ||
                    productModel.getShopkeeperUid().trim().isEmpty()) {
                productModel.setShopkeeperUid(shopkeeperUid);
            }

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .collection("Products")
                    .document(productModel.getProductId())
                    .create(productModel)
                    .get();

            System.out.println(
                    "Product added successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR: Product could not be added."
            );

            e.printStackTrace();
        }
    }

    // Customer side par sabhi Shopkeepers ke Products
    // fetch honge.
    //
    // Structure:
    //
    // Shopkeepers
    //      ├── UID 1
    //      │     └── Products
    //      ├── UID 2
    //      │     └── Products
    //      └── UID 3
    //            └── Products
    //
    // collectionGroup("Products") sabhi Products fetch karega.

    public ArrayList<ProductModel> getProducts() {

        System.out.println(
                "========================================"
        );

        System.out.println(
                "GETTING ALL SHOPKEEPER PRODUCTS"
        );

        System.out.println(
                "========================================"
        );

        ArrayList<ProductModel> products =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> query =
                    db.collectionGroup("Products")
                            .get();

            QuerySnapshot querySnapshot =
                    query.get();

            for (DocumentSnapshot document :
                    querySnapshot.getDocuments()) {

                if (!document.exists()) {
                    continue;
                }

                try {

                    ProductModel product =
                            document.toObject(
                                    ProductModel.class
                            );

                    if (product != null) {

                        // Agar ProductModel mein productId
                        // save nahi hua hai, document ID use karo.
                        if (product.getProductId() == null ||
                                product.getProductId()
                                        .trim()
                                        .isEmpty()) {

                            product.setProductId(
                                    document.getId()
                            );
                        }

                        // Extract shopkeeperUid from document reference path if missing
                        if (product.getShopkeeperUid() == null ||
                                product.getShopkeeperUid().trim().isEmpty()) {
                            try {
                                if (document.getReference() != null &&
                                        document.getReference().getParent() != null &&
                                        document.getReference().getParent().getParent() != null) {
                                    product.setShopkeeperUid(
                                            document.getReference().getParent().getParent().getId()
                                    );
                                }
                            } catch (Exception ex) {
                                // ignore
                            }
                        }

                        products.add(product);

                        System.out.println(
                                "--------------------------------"
                        );

                        System.out.println(
                                "Product ID: "
                                        + product.getProductId()
                        );

                        System.out.println(
                                "Product Name: "
                                        + product.getProductName()
                        );

                        System.out.println(
                                "Category: "
                                        + product.getCategory()
                        );

                        System.out.println(
                                "Brand: "
                                        + product.getBrand()
                        );

                        System.out.println(
                                "Selling Price: "
                                        + product.getSellingPrice()
                        );

                        System.out.println(
                                "Stock: "
                                        + product.getStockQuantity()
                        );

                        System.out.println(
                                "Status: "
                                        + product.getStatus()
                        );

                    }

                } catch (Exception productError) {

                    System.out.println(
                            "ERROR reading product document: "
                                    + document.getId()
                    );

                    productError.printStackTrace();
                }
            }

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "TOTAL PRODUCTS FETCHED: "
                            + products.size()
            );

            System.out.println(
                    "========================================"
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR while fetching products."
            );

            e.printStackTrace();
        }

        return products;
    }



    public void updateProduct(
            ProductModel productModel) {

        System.out.println(
                "Updating Product..."
        );

        try {

            if (productModel == null) {

                System.out.println(
                        "ERROR: ProductModel is null."
                );

                return;
            }

            String shopkeeperUid =
                    ShopkeeperLogController.getShopkeeperUid();

            if (shopkeeperUid == null ||
                    shopkeeperUid.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Shopkeeper UID is missing."
                );

                return;
            }

            if (productModel.getProductId() == null ||
                    productModel.getProductId()
                            .trim()
                            .isEmpty()) {

                System.out.println(
                        "ERROR: Product ID is missing."
                );

                return;
            }

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .collection("Products")
                    .document(productModel.getProductId())
                    .set(productModel)
                    .get();

            System.out.println(
                    "Product updated successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR while updating product."
            );

            e.printStackTrace();
        }
    }



    public void deleteProduct(
            String productId) {

        System.out.println(
                "Deleting Product..."
        );

        try {

            if (productId == null ||
                    productId.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Product ID is missing."
                );

                return;
            }

            String shopkeeperUid =
                    ShopkeeperLogController.getShopkeeperUid();

            if (shopkeeperUid == null ||
                    shopkeeperUid.trim().isEmpty()) {

                System.out.println(
                        "ERROR: Shopkeeper UID is missing."
                );

                return;
            }

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .collection("Products")
                    .document(productId)
                    .delete()
                    .get();

            System.out.println(
                    "Product deleted successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ERROR while deleting product."
            );

            e.printStackTrace();
        }
    }

    // ==========================================
    // DEDUCT STOCK ON ORDER ACCEPTANCE
    // ==========================================

    public boolean deductStockForOrder(OrderModel order) {
        if (order == null || order.getOrderId() == null || order.getOrderId().isBlank()) {
            System.out.println("Cannot deduct stock: Order or Order ID is missing.");
            return false;
        }

        System.out.println("========================================");
        System.out.println("DEDUCTING STOCK FOR ORDER: " + order.getOrderId());
        System.out.println("========================================");

        List<OrderItemModel> items = order.getProducts();

        // If items list is empty in memory, attempt to fetch from Firestore order document
        if (items == null || items.isEmpty()) {
            try {
                DocumentSnapshot orderDoc = db.collection("Orders")
                        .document(order.getOrderId())
                        .get()
                        .get();
                if (orderDoc != null && orderDoc.exists()) {
                    Object rawProductsObj = orderDoc.get("products");
                    if (rawProductsObj instanceof List<?>) {
                        items = new ArrayList<>();
                        for (Object obj : (List<?>) rawProductsObj) {
                            if (obj instanceof Map<?, ?>) {
                                Map<?, ?> map = (Map<?, ?>) obj;
                                OrderItemModel oim = new OrderItemModel();
                                if (map.get("productId") != null) oim.setProductId(map.get("productId").toString());
                                if (map.get("productName") != null) oim.setProductName(map.get("productName").toString());
                                if (map.get("quantity") != null) {
                                    try {
                                        oim.setQuantity(Integer.parseInt(map.get("quantity").toString()));
                                    } catch (Exception ignored) {}
                                }
                                items.add(oim);
                            }
                        }
                        order.setProducts(items);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error fetching order products from Firestore: " + e.getMessage());
            }
        }

        if (items == null || items.isEmpty()) {
            System.out.println("No product items found for order: " + order.getOrderId());
            return false;
        }

        boolean allUpdated = true;
        for (OrderItemModel item : items) {
            if (item == null) continue;
            String productId = item.getProductId();
            String productName = item.getProductName();
            int quantity = item.getQuantity() > 0 ? item.getQuantity() : 1;

            boolean updated = deductProductStock(order.getShopkeeperUid(), productId, productName, quantity);
            if (!updated) {
                allUpdated = false;
                System.out.println("Warning: Could not update stock for product: " + productName + " (ID: " + productId + ")");
            }
        }

        // Notify in-memory inventory view to refresh
        try {
            ShopkeeperInventory.refreshData();
        } catch (Throwable t) {
            // Ignore if GUI not initialized
        }

        return allUpdated;
    }

    public boolean deductProductStock(String shopUid, String productId, String productName, int quantityToDeduct) {
        try {
            DocumentSnapshot productDoc = findProductDocument(shopUid, productId, productName);
            if (productDoc == null || !productDoc.exists()) {
                System.out.println("Product not found in Firestore for deduction: " + productName + " (ID: " + productId + ")");
                return false;
            }

            DocumentReference docRef = productDoc.getReference();

            int currentStock = 0;
            Object stockObj = productDoc.get("stockQuantity");
            if (stockObj instanceof Number) {
                currentStock = ((Number) stockObj).intValue();
            } else if (stockObj != null) {
                try {
                    currentStock = Integer.parseInt(stockObj.toString().trim());
                } catch (Exception ignored) {}
            }

            int newStock = Math.max(0, currentStock - quantityToDeduct);

            Map<String, Object> updates = new HashMap<>();
            updates.put("stockQuantity", newStock);

            if (newStock <= 0) {
                updates.put("status", "OUT_OF_STOCK");
            }

            docRef.update(updates).get();

            System.out.println("Successfully deducted stock for '" + safeString(productDoc.getString("productName"))
                    + "': " + currentStock + " - " + quantityToDeduct + " = " + newStock
                    + " (Doc ID: " + productDoc.getId() + ")");

            // Update in-memory inventory directly if present
            try {
                ShopkeeperInventory.updateProductStockInMemory(
                        productDoc.getId(),
                        productDoc.getString("productId"),
                        productDoc.getString("productName"),
                        newStock
                );
            } catch (Throwable ignored) {}

            return true;
        } catch (Exception e) {
            System.out.println("Error deducting stock: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private DocumentSnapshot findProductDocument(String shopUid, String productId, String productName) {
        // 1. Search in order's shopkeeper UID Products subcollection
        if (shopUid != null && !shopUid.isBlank() && !"default_shopkeeper".equalsIgnoreCase(shopUid) && !"shopkeeperUid".equalsIgnoreCase(shopUid)) {
            DocumentSnapshot doc = searchInShopkeeperProducts(shopUid.trim(), productId, productName);
            if (doc != null && doc.exists()) {
                return doc;
            }
        }

        // 2. Search in logged-in shopkeeper's Products subcollection
        String currentUid = ShopkeeperLogController.getShopkeeperUid();
        if (currentUid == null || currentUid.isBlank()) {
            if (ViewConstants.shopkeeperModel != null && ViewConstants.shopkeeperModel.getShopkeeperUid() != null) {
                currentUid = ViewConstants.shopkeeperModel.getShopkeeperUid();
            }
        }
        if (currentUid != null && !currentUid.isBlank() && !currentUid.equalsIgnoreCase(shopUid)) {
            DocumentSnapshot doc = searchInShopkeeperProducts(currentUid.trim(), productId, productName);
            if (doc != null && doc.exists()) {
                return doc;
            }
        }

        // 3. Fallback: Search across all products using collectionGroup("Products")
        return searchInCollectionGroupProducts(productId, productName);
    }

    private DocumentSnapshot searchInShopkeeperProducts(String shopUid, String productId, String productName) {
        try {
            CollectionReference productsRef = db.collection("Shopkeepers")
                    .document(shopUid)
                    .collection("Products");

            // Direct document lookup by ID
            if (productId != null && !productId.isBlank()) {
                try {
                    DocumentSnapshot directDoc = productsRef.document(productId.trim()).get().get();
                    if (directDoc != null && directDoc.exists()) {
                        return directDoc;
                    }
                } catch (Exception ignored) {}

                // Query by productId field
                try {
                    List<QueryDocumentSnapshot> list = productsRef.whereEqualTo("productId", productId.trim())
                            .limit(1).get().get().getDocuments();
                    if (!list.isEmpty()) {
                        return list.get(0);
                    }
                } catch (Exception ignored) {}
            }

            // Query by productName field
            if (productName != null && !productName.isBlank()) {
                try {
                    List<QueryDocumentSnapshot> list = productsRef.whereEqualTo("productName", productName.trim())
                            .limit(1).get().get().getDocuments();
                    if (!list.isEmpty()) {
                        return list.get(0);
                    }
                } catch (Exception ignored) {}
            }

            // Fallback: Check all docs in this shopkeeper's collection for case-insensitive matching
            List<QueryDocumentSnapshot> allDocs = productsRef.get().get().getDocuments();
            for (QueryDocumentSnapshot doc : allDocs) {
                String docId = doc.getId();
                String pId = doc.getString("productId");
                String pName = doc.getString("productName");

                if (productId != null && !productId.isBlank()) {
                    String cleanPId = productId.trim();
                    if (cleanPId.equalsIgnoreCase(docId) || cleanPId.equalsIgnoreCase(pId) || cleanPId.equalsIgnoreCase(pName)) {
                        return doc;
                    }
                }
                if (productName != null && !productName.isBlank()) {
                    String cleanPName = productName.trim();
                    if (cleanPName.equalsIgnoreCase(docId) || cleanPName.equalsIgnoreCase(pId) || cleanPName.equalsIgnoreCase(pName)) {
                        return doc;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error searching in shopkeeper products: " + e.getMessage());
        }
        return null;
    }

    private DocumentSnapshot searchInCollectionGroupProducts(String productId, String productName) {
        try {
            if (productId != null && !productId.isBlank()) {
                try {
                    List<QueryDocumentSnapshot> list = db.collectionGroup("Products")
                            .whereEqualTo("productId", productId.trim())
                            .limit(1).get().get().getDocuments();
                    if (!list.isEmpty()) {
                        return list.get(0);
                    }
                } catch (Exception ignored) {}
            }

            if (productName != null && !productName.isBlank()) {
                try {
                    List<QueryDocumentSnapshot> list = db.collectionGroup("Products")
                            .whereEqualTo("productName", productName.trim())
                            .limit(1).get().get().getDocuments();
                    if (!list.isEmpty()) {
                        return list.get(0);
                    }
                } catch (Exception ignored) {}
            }

            // Scan all products in collectionGroup for case-insensitive / doc ID match
            List<QueryDocumentSnapshot> allDocs = db.collectionGroup("Products").get().get().getDocuments();
            for (QueryDocumentSnapshot doc : allDocs) {
                String docId = doc.getId();
                String pId = doc.getString("productId");
                String pName = doc.getString("productName");

                if (productId != null && !productId.isBlank()) {
                    String cleanPId = productId.trim();
                    if (cleanPId.equalsIgnoreCase(docId) || cleanPId.equalsIgnoreCase(pId) || cleanPId.equalsIgnoreCase(pName)) {
                        return doc;
                    }
                }
                if (productName != null && !productName.isBlank()) {
                    String cleanPName = productName.trim();
                    if (cleanPName.equalsIgnoreCase(docId) || cleanPName.equalsIgnoreCase(pId) || cleanPName.equalsIgnoreCase(pName)) {
                        return doc;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error searching collectionGroup products: " + e.getMessage());
        }
        return null;
    }

    private String safeString(String str) {
        return str != null ? str : "";
    }
}
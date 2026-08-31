package com.kryox.dao.Shopkeeper;

import java.util.ArrayList;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.ProductModel;

public class ProductDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();

    // ============================================================
    // ADD PRODUCT
    // ============================================================

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

    // ============================================================
    // GET ALL PRODUCTS
    // ============================================================
    //
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
    // ============================================================

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

    // ============================================================
    // UPDATE PRODUCT
    // ============================================================

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

    // ============================================================
    // DELETE PRODUCT
    // ============================================================

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
}
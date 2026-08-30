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

        private Firestore db = Firebaseconfig.gFirestore();


    public void addProduct(ProductModel productModel) {
        System.out.println("Adding Product");
        try {

            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .collection("Products")
                    .document(productModel.getProductId())
                    .create(productModel);
            System.out.println("Product added");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public ArrayList<ProductModel> getProducts() {
        System.out.println("Getting Products");

        ArrayList<ProductModel> products = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .collection("Products")
                    .get();
            QuerySnapshot querySnapshot = query.get();

            for (DocumentSnapshot document : querySnapshot) {
                ProductModel productModel = document.toObject(ProductModel.class);
                products.add(productModel);
                System.out.println("Product retrieved" + productModel.getProductName());
            }
            System.out.println(products.size());
            return products;

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Products retrieved");
        return products;
    }

    // ================================================================
    // UPDATE PRODUCT
    // ================================================================

    public void updateProduct(
            ProductModel productModel) {

        try {

            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .collection("Products")
                    .document(productModel.getProductId())
                    .set(productModel);

            System.out.println("Product updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     // ================================================================
    // DELETE PRODUCT
    // ================================================================

    public void deleteProduct(
            String productId) {

        try {

            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .collection("Products")
                    .document(productId)
                    .delete();

            System.out.println("Product deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

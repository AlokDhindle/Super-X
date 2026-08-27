package com.kryox.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.kryox.config.FirebaseConfig;
import com.kryox.control.ShopkeeperLogController;
import com.kryox.model.ProductModel;
import com.kryox.model.ShopkeeperModel;


public class ShopkeeperDAO {

    private  Firestore db = FirebaseConfig.getFirestore();

    public void addShop(ShopkeeperModel shopkeeperModel) {
        System.out.println("Adding Shopkeeper");

        try {
            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .create(shopkeeperModel);
            System.out.println("Shopkeeper added");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public ShopkeeperModel getShopDetails(String shopkeeperUid) {
        System.out.println("Getting Shopkeeper Details");

        try {
            ApiFuture<DocumentSnapshot> doc = db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .get();
            DocumentSnapshot document = doc.get();
            if (document.exists()) {
                System.out.println("DocumentSnapshot data: " + document.getData());
                return document.toObject(ShopkeeperModel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }   

    public void updateShopkeeperDetails(String shopNameValue, String ownerNameValue, String mobileValue, String panValue, String gstValue,
            String categoryValue, String addressValue, String stateValue, String cityValue,
            String pinValue, String licenseValue) {
        System.out.println("Updating Shopkeeper Details");
        try {
            db.collection("Shopkeeprs")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .update("shopNameValue", shopNameValue)
                    .update("ownerNameValue", ownerNameValue)
                    .update("mobileValue", mobileValue)
                    .update("panValue", panValue)
                    .update("gstValue", gstValue)
                    .update("categoryValue", categoryValue)
                    .update("addressValue", addressValue)
                    .update("stateValue", stateValue)
                    .update("cityValue", cityValue)
                    .update("pinValue", pinValue)
                    .update("licenseValue", licenseValue);
            System.out.println("Shopkeeper details updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProduct(ProductModel productModel) {
        System.out.println("Adding Product");
        try {
    
            db.collection("Shopkeeprs")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .collection("Products")
                    .document(productModel.getProductId())
                    .create(productModel);
            System.out.println("Product added");
        }catch (Exception e) {
            e.printStackTrace();    
            return;     
        }

    }

    public void deleteAccount() {
        System.out.println("Deleting Account");

        try {
            db.collection("Shopkeeprs")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .delete();
            System.out.println("Account deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

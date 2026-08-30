package com.kryox.dao.Shopkeeper;

import java.util.HashMap;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.ShopkeeperModel;

public class ShopkeeperDAO {

    private Firestore db = Firebaseconfig.gFirestore();

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

    public void updateShopkeeperDetails(String shopNameValue, String ownerNameValue, String mobileValue,
            String panValue, String gstValue,
            String categoryValue, String addressValue, String stateValue, String cityValue,
            String pinValue, String licenseValue) {
        System.out.println("Updating Shopkeeper Details");
        try {

            Map<String, Object> updates = new HashMap<>();

            updates.put("shopNameValue", shopNameValue);
            updates.put("ownerNameValue", ownerNameValue);
            updates.put("mobileValue", mobileValue);
            updates.put("panValue", panValue);
            updates.put("gstValue", gstValue);
            updates.put("categoryValue", categoryValue);
            updates.put("addressValue", addressValue);
            updates.put("stateValue", stateValue);
            updates.put("cityValue", cityValue);
            updates.put("pinValue", pinValue);
            updates.put("licenseValue", licenseValue);

            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .update(updates);

            System.out.println("Shopkeeper details updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void deleteAccount() {
        System.out.println("Deleting Account");

        try {
            db.collection("Shopkeepers")
                    .document(ShopkeeperLogController.getShopkeeperUid())
                    .delete();
            System.out.println("Account deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.kryox.dao.Shopkeeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.ShopkeeperModel;

public class ShopkeeperDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();

    public void addShop(
            ShopkeeperModel shopkeeperModel
    ) {

        System.out.println("Adding Shopkeeper");

        try {

            String shopkeeperUid =
                    ShopkeeperLogController.getShopkeeperUid();

            shopkeeperModel.setApproved(false);
            shopkeeperModel.setShopkeeperUid(shopkeeperUid);

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .set(shopkeeperModel)
                    .get();

            System.out.println(
                    "Shopkeeper added with approved = false"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShopkeeperModel getShopDetails(
            String shopkeeperUid
    ) {

        try {

            ApiFuture<DocumentSnapshot> doc =
                    db.collection("Shopkeepers")
                            .document(shopkeeperUid)
                            .get();

            DocumentSnapshot document =
                    doc.get();

            if (document.exists()) {

                return document.toObject(
                        ShopkeeperModel.class
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<QueryDocumentSnapshot>
            getAllShopkeepers() {

        List<QueryDocumentSnapshot> all =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("Shopkeepers")
                            .get()
                            .get();

            all.addAll(
                    snapshot.getDocuments()
            );

            System.out.println(
                    "Total Shopkeepers = "
                            + all.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "All Shopkeepers fetch error: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return all;
    }

    public List<QueryDocumentSnapshot>
            getPendingShopkeepers() {

        List<QueryDocumentSnapshot> pending =
                new ArrayList<>();

        for (QueryDocumentSnapshot document :
                getAllShopkeepers()) {

            Boolean approved =
                    document.getBoolean("approved");

            if (!Boolean.TRUE.equals(approved)) {
                pending.add(document);
            }
        }

        return pending;
    }

    public List<QueryDocumentSnapshot>
            getVerifiedShopkeepers() {

        List<QueryDocumentSnapshot> verified =
                new ArrayList<>();

        for (QueryDocumentSnapshot document :
                getAllShopkeepers()) {

            Boolean approved =
                    document.getBoolean("approved");

            if (Boolean.TRUE.equals(approved)) {
                verified.add(document);
            }
        }

        return verified;
    }

    public int getPendingShopkeeperCount() {

        return getPendingShopkeepers().size();
    }

    public boolean approveShopkeeper(
            String shopkeeperUid
    ) {

        try {

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .update(
                            "approved",
                            true
                    )
                    .get();

            System.out.println(
                    "Shopkeeper approved: "
                            + shopkeeperUid
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Shopkeeper approval error: "
                            + e.getMessage()
            );

            e.printStackTrace();
            return false;
        }
    }

    public void updateShopkeeperDetails(
            String shopNameValue,
            String ownerNameValue,
            String mobileValue,
            String panValue,
            String gstValue,
            String categoryValue,
            String addressValue,
            String stateValue,
            String cityValue,
            String pinValue,
            String licenseValue
    ) {

        try {

            Map<String, Object> updates =
                    new HashMap<>();

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
                    .document(
                            ShopkeeperLogController
                                    .getShopkeeperUid()
                    )
                    .update(updates)
                    .get();

            System.out.println(
                    "Shopkeeper details updated"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount() {

        try {

            db.collection("Shopkeepers")
                    .document(
                            ShopkeeperLogController
                                    .getShopkeeperUid()
                    )
                    .delete()
                    .get();

            System.out.println(
                    "Account deleted"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

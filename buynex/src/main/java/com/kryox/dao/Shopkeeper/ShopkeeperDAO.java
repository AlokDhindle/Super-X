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

    private boolean isApprovedDocument(QueryDocumentSnapshot document) {
        if (document == null) {
            return false;
        }
        for (String field : new String[]{"approved", "isApproved", "verified", "isVerified"}) {
            Object val = document.get(field);
            if (val instanceof Boolean && (Boolean) val) {
                return true;
            }
            if (val != null && "true".equalsIgnoreCase(String.valueOf(val).trim())) {
                return true;
            }
        }
        String status = document.getString("status");
        if (status != null && (status.equalsIgnoreCase("APPROVED")
                || status.equalsIgnoreCase("Approved")
                || status.equalsIgnoreCase("ACTIVE")
                || status.equalsIgnoreCase("Active"))) {
            return true;
        }
        return false;
    }

    public List<QueryDocumentSnapshot>
            getPendingShopkeepers() {

        List<QueryDocumentSnapshot> pending =
                new ArrayList<>();

        for (QueryDocumentSnapshot document :
                getAllShopkeepers()) {

            if (!isApprovedDocument(document)) {
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

            if (isApprovedDocument(document)) {
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

            Map<String, Object> updates = new HashMap<>();
            updates.put("approved", true);
            updates.put("isApproved", true);
            updates.put("status", "APPROVED");

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .update(updates)
                    .get();

            System.out.println(
                    "Shopkeeper approved: "
                            + shopkeeperUid
            );

            try {
                DocumentSnapshot doc = db.collection("Shopkeepers")
                        .document(shopkeeperUid)
                        .get()
                        .get();
                if (doc.exists()) {
                    String email = doc.getString("email");
                    if (email == null || email.isBlank()) {
                        email = doc.getString("userEmail");
                    }
                    if (email != null && !email.isBlank()) {
                        Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put("approved", true);
                        userUpdates.put("isApproved", true);
                        userUpdates.put("status", "APPROVED");
                        db.collection("User").document(email).update(userUpdates);
                    }
                }
            } catch (Exception ignored) {
            }

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

    public boolean rejectShopkeeper(
            String shopkeeperUid,
            String reason
    ) {
        try {
            Map<String, Object> updates = new HashMap<>();
            updates.put("approved", false);
            updates.put("isApproved", false);
            updates.put("status", "REJECTED");
            if (reason != null && !reason.isBlank()) {
                updates.put("rejectionReason", reason);
            }

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .update(updates)
                    .get();

            System.out.println(
                    "Shopkeeper rejected: "
                            + shopkeeperUid
            );

            try {
                DocumentSnapshot doc = db.collection("Shopkeepers")
                        .document(shopkeeperUid)
                        .get()
                        .get();
                if (doc.exists()) {
                    String email = doc.getString("email");
                    if (email == null || email.isBlank()) {
                        email = doc.getString("userEmail");
                    }
                    if (email != null && !email.isBlank()) {
                        Map<String, Object> userUpdates = new HashMap<>();
                        userUpdates.put("approved", false);
                        userUpdates.put("isApproved", false);
                        userUpdates.put("status", "REJECTED");
                        db.collection("User").document(email).update(userUpdates);
                    }
                }
            } catch (Exception ignored) {
            }

            return true;
        } catch (Exception e) {
            System.out.println(
                    "Shopkeeper rejection error: "
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

    public void updateShopkeeperPlan(
            String shopkeeperUid,
            String planName,
            String planAmount,
            String planBillingCycle,
            String planRenewalDate,
            String planStatus
    ) {
        try {
            if (shopkeeperUid == null || shopkeeperUid.isBlank()) {
                shopkeeperUid = ShopkeeperLogController.getShopkeeperUid();
            }
            if (shopkeeperUid == null || shopkeeperUid.isBlank()) {
                return;
            }

            Map<String, Object> updates = new HashMap<>();
            updates.put("planName", planName);
            updates.put("planAmount", planAmount);
            updates.put("planBillingCycle", planBillingCycle);
            updates.put("planRenewalDate", planRenewalDate);
            updates.put("planStatus", planStatus);

            db.collection("Shopkeepers")
                    .document(shopkeeperUid)
                    .update(updates);

            System.out.println("Shopkeeper plan updated in Firestore for UID: " + shopkeeperUid);
        } catch (Exception e) {
            System.out.println("Firestore updateShopkeeperPlan error: " + e.getMessage());
        }
    }
}
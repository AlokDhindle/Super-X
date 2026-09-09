package com.kryox.dao.Shopkeeper;

import java.util.ArrayList;
import java.util.UUID;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.OfferModel;

public class OfferDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();

    public void addOffer(
            OfferModel offerModel
    ) {

        try {

            String offerId =
                    UUID.randomUUID()
                            .toString();

            offerModel.setOfferId(
                    offerId
            );

            if (offerModel.getCategory() == null ||
                    offerModel.getCategory()
                            .trim()
                            .isEmpty()) {

                offerModel.setCategory(
                        "General"
                );
            }

            db.collection("Shopkeepers")
                    .document(
                            ShopkeeperLogController
                                    .getShopkeeperUid()
                    )
                    .collection("Offers")
                    .document(offerId)
                    .set(offerModel)
                    .get();

            System.out.println(
                    "Offer added successfully"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public ArrayList<OfferModel>
            getOffers() {

        ArrayList<OfferModel> offers =
                new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> query =
                    db.collection("Shopkeepers")
                            .document(
                                    ShopkeeperLogController
                                            .getShopkeeperUid()
                            )
                            .collection("Offers")
                            .get();

            QuerySnapshot querySnapshot =
                    query.get();

            for (DocumentSnapshot document :
                    querySnapshot) {

                OfferModel offer =
                        document.toObject(
                                OfferModel.class
                        );

                if (offer != null) {

                    offers.add(offer);

                    System.out.println(
                            "Offer retrieved: "
                                    + offer.getOfferName()
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return offers;
    }

    public ArrayList<OfferModel>
            getAllOffersForAdmin() {

        ArrayList<OfferModel> offers =
                new ArrayList<>();

        try {

            QuerySnapshot querySnapshot =
                    db.collectionGroup("Offers")
                            .get()
                            .get();

            for (DocumentSnapshot document :
                    querySnapshot.getDocuments()) {

                OfferModel offer =
                        document.toObject(
                                OfferModel.class
                        );

                if (offer != null) {

                    offers.add(offer);
                }
            }

            System.out.println(
                    "Admin Analytics Offers = "
                            + offers.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Admin offer fetch error: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return offers;
    }

    public boolean deleteOffer(String offerId) {
        if (offerId == null || offerId.trim().isEmpty()) {
            return false;
        }
        try {
            boolean deleted = false;
            String shopUid = ShopkeeperLogController.getShopkeeperUid();
            if (shopUid != null && !shopUid.trim().isEmpty()) {
                db.collection("Shopkeepers")
                        .document(shopUid)
                        .collection("Offers")
                        .document(offerId)
                        .delete()
                        .get();
                deleted = true;
            }

            QuerySnapshot qs = db.collectionGroup("Offers")
                    .whereEqualTo("offerId", offerId)
                    .get()
                    .get();

            if (qs != null && !qs.isEmpty()) {
                for (DocumentSnapshot doc : qs.getDocuments()) {
                    doc.getReference().delete().get();
                    deleted = true;
                }
            }

            System.out.println("Offer deleted successfully: " + offerId);
            return deleted;
        } catch (Exception e) {
            System.err.println("Error deleting offer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}


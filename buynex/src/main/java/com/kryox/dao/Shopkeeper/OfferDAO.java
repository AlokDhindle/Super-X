package com.kryox.dao.Shopkeeper;

import java.util.ArrayList;
import java.util.UUID;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.FirebaseConfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.OfferModel;

public class OfferDAO {

    private  Firestore db = FirebaseConfig.getFirestore();

    // ================================================================
    // ADD OFFER
    // ================================================================

    public void addOffer(
            OfferModel offerModel) {

        try {

            String offerId = UUID.randomUUID().toString();

            offerModel.setOfferId(
                    offerId);

            db.collection("Shopkeepers")
                    .document(
                            ShopkeeperLogController
                                    .getShopkeeperUid())
                    .collection("Offers")
                    .document(offerId)
                    .set(offerModel)
                    .get();

            System.out.println(
                    "Offer added successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================================================================
    // GET OFFERS
    // ================================================================

    public ArrayList<OfferModel> getOffers() {

        ArrayList<OfferModel> offers = new ArrayList<>();

        try {

            ApiFuture<QuerySnapshot> query = db.collection("Shopkeepers")
                    .document(
                            ShopkeeperLogController
                                    .getShopkeeperUid())
                    .collection("Offers")
                    .get();

            QuerySnapshot querySnapshot = query.get();

            for (DocumentSnapshot document : querySnapshot) {

                OfferModel offer = document.toObject(
                        OfferModel.class);

                if (offer != null) {

                    offers.add(offer);

                    System.out.println(
                            "Offer retrieved: "
                                    + offer.getOfferName());
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return offers;
    }

}

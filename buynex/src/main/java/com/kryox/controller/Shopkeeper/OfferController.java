package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;

import com.kryox.dao.Shopkeeper.OfferDAO;
import com.kryox.model.Shopkeeper.OfferModel;

public class OfferController {

    public static void addNewOffer(
            OfferModel offerModel
    ) {

        new Thread(() -> {

            System.out.println(
                    "Adding new offer"
            );

            new OfferDAO()
                    .addOffer(
                            offerModel
                    );

        }).start();
    }

    public static ArrayList<OfferModel>
            getOffers() {

        System.out.println(
                "Getting offers"
        );

        return new OfferDAO()
                .getOffers();
    }

    public static ArrayList<OfferModel>
            getAllOffersForAdmin() {

        System.out.println(
                "Getting all offers for admin analytics"
        );

        return new OfferDAO()
                .getAllOffersForAdmin();
    }

    public static boolean deleteOffer(String offerId) {
        System.out.println("Deleting offer: " + offerId);
        return new OfferDAO().deleteOffer(offerId);
    }

    public static void deleteOfferAsync(String offerId, java.util.function.Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean success = new OfferDAO().deleteOffer(offerId);
            if (callback != null) {
                javafx.application.Platform.runLater(() -> callback.accept(success));
            }
        }).start();
    }
}


package com.kryox.dao.Customer;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Customer.Productcart;

public class Cartdao {

    private Firestore db = Firebaseconfig.gFirestore();

    // ADD PRODUCT
    public void addTocart(Productcart productcart) {

        try {

            String userId = productcart.getUserId();

            System.out.println("========== ADD TO CART ==========");
            System.out.println("USER ID = [" + userId + "]");
            System.out.println("PRODUCT = [" + productcart.getName() + "]");
            System.out.println("PRICE = [" + productcart.getPrice() + "]");

            if (userId == null || userId.isBlank()) {
                System.out.println("ERROR: USER ID IS NULL");
                return;
            }
<<<<<<< HEAD

=======
            System.out.println("SAVING TO: Orders/" + userId + "/Cart/" + productcart.getName());
>>>>>>> Sayali
            db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .document(productcart.getName())
                    .set(productcart)
                    .get();

            System.out.println("PRODUCT SAVED SUCCESSFULLY");
            System.out.println("================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // GET CART
    public List<Productcart> getCart(String userId) {

        List<Productcart> list = new ArrayList<>();

        try {

            System.out.println("========== GET CART ==========");
            System.out.println("FETCH USER ID = [" + userId + "]");

            if (userId == null || userId.isBlank()) {

                System.out.println("ERROR: FETCH USER ID IS NULL");

                return list;
            }

            QuerySnapshot snapshot =
                    db.collection("Orders")
                            .document(userId)
                            .collection("Cart")
                            .get()
                            .get();

            System.out.println(
                    "DOCUMENT COUNT = "
                            + snapshot.size()
            );

            for (DocumentSnapshot document :
                    snapshot.getDocuments()) {

                System.out.println(
                        "FOUND DOCUMENT = "
                                + document.getId()
                );

                Productcart product =
                        document.toObject(Productcart.class);

                if (product != null) {
                    list.add(product);
                }
            }

            System.out.println(
                    "TOTAL PRODUCTS = "
                            + list.size()
            );

            System.out.println("==============================");

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}
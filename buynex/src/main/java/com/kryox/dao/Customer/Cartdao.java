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

    public void addTocart(Productcart productcart) {
        try {
            String userId = productcart.getUserId();
            if (userId == null || userId.isBlank()) {
                System.out.println("ERROR: USER ID IS NULL");
                return;
            }

            db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .document(productcart.getName())
                    .set(productcart)
                    .get();

            System.out.println("PRODUCT SAVED SUCCESSFULLY");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Productcart> getCart(String userId) {
        List<Productcart> list = new ArrayList<>();
        try {
            if (userId == null || userId.isBlank()) {
                System.out.println("ERROR: FETCH USER ID IS NULL");
                return list;
            }

            QuerySnapshot snapshot = db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .get()
                    .get();

            for (DocumentSnapshot document : snapshot.getDocuments()) {
                Productcart product = document.toObject(Productcart.class);
                if (product != null) {
                    list.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateQuantity(String userId, String productName, int quantity) {
        try {
            if (userId == null || userId.isBlank() || productName == null || productName.isBlank()) {
                return;
            }
            db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .document(productName)
                    .update("quantity", quantity)
                    .get();
            System.out.println("Cart quantity updated for " + productName + " -> " + quantity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromCart(String userId, String productName) {
        try {
            if (userId == null || userId.isBlank() || productName == null || productName.isBlank()) {
                return;
            }
            db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .document(productName)
                    .delete()
                    .get();
            System.out.println("Product removed from cart: " + productName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearCart(String userId) {
        try {
            if (userId == null || userId.isBlank()) {
                return;
            }
            QuerySnapshot snapshot = db.collection("Orders")
                    .document(userId)
                    .collection("Cart")
                    .get()
                    .get();
            for (DocumentSnapshot doc : snapshot.getDocuments()) {
                doc.getReference().delete().get();
            }
            System.out.println("Cart cleared in Firestore for user: " + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
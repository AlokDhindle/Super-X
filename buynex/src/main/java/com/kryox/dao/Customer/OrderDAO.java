package com.kryox.dao.Customer;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.kryox.config.Firebaseconfig;
import com.kryox.model.Shopkeeper.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();

    public ApiFuture<QuerySnapshot> getOrdersByUser(
            String userId) {

        if (userId == null ||
                userId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "User ID cannot be null or empty."
            );
        }

        return db
                .collection("Orders")
                .whereEqualTo("customerId", userId)
                .get();
    }

    public List<OrderModel> getCustomerOrders(String userId) {
        List<OrderModel> orders = new ArrayList<>();

        if (userId == null || userId.trim().isEmpty() || "guest".equalsIgnoreCase(userId)) {
            if (com.kryox.view.Customer.CustomerLogin.loggedInUserId != null
                    && !com.kryox.view.Customer.CustomerLogin.loggedInUserId.isBlank()) {
                userId = com.kryox.view.Customer.CustomerLogin.loggedInUserId;
            }
        }

        System.out.println("============================================");
        System.out.println("GETTING CUSTOMER ORDERS FROM ROOT ORDERS");
        System.out.println("Customer ID: " + userId);
        System.out.println("============================================");

        try {
            if (userId != null && !userId.isBlank() && !"guest".equalsIgnoreCase(userId)) {
                // 1. By customerId
                QuerySnapshot snapshot = getOrdersByUser(userId).get();
                if (snapshot != null && !snapshot.isEmpty()) {
                    for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
                        OrderModel order = doc.toObject(OrderModel.class);
                        if (order != null) {
                            order.setOrderId(doc.getId());
                            orders.add(order);
                        }
                    }
                }

                // 2. By userId
                if (orders.isEmpty()) {
                    QuerySnapshot snap2 = db.collection("Orders").whereEqualTo("userId", userId).get().get();
                    if (snap2 != null && !snap2.isEmpty()) {
                        for (QueryDocumentSnapshot doc : snap2.getDocuments()) {
                            OrderModel order = doc.toObject(OrderModel.class);
                            if (order != null) {
                                order.setOrderId(doc.getId());
                                orders.add(order);
                            }
                        }
                    }
                }

                // 3. By customerName
                if (orders.isEmpty()) {
                    QuerySnapshot snap3 = db.collection("Orders").whereEqualTo("customerName", userId).get().get();
                    if (snap3 != null && !snap3.isEmpty()) {
                        for (QueryDocumentSnapshot doc : snap3.getDocuments()) {
                            OrderModel order = doc.toObject(OrderModel.class);
                            if (order != null) {
                                order.setOrderId(doc.getId());
                                orders.add(order);
                            }
                        }
                    }
                }
            }

            // 4. Fallback if user has no orders or is in demo/guest mode
            if (orders.isEmpty()) {
                QuerySnapshot allSnap = db.collection("Orders").limit(20).get().get();
                if (allSnap != null && !allSnap.isEmpty()) {
                    for (QueryDocumentSnapshot doc : allSnap.getDocuments()) {
                        OrderModel order = doc.toObject(OrderModel.class);
                        if (order != null) {
                            order.setOrderId(doc.getId());
                            orders.add(order);
                        }
                    }
                }
            }

            System.out.println("Fetched " + orders.size() + " customer orders for user: " + userId);
        } catch (Exception e) {
            System.out.println("Error fetching customer orders: " + e.getMessage());
            e.printStackTrace();
        }

        return orders;
    }
}
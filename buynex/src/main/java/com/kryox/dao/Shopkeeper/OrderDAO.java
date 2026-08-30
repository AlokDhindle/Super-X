
package com.kryox.dao.Shopkeeper;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import com.kryox.config.FirebaseConfig;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.OrderModel;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();


    // ============================================================
    // GET SHOPKEEPER ORDERS COLLECTION
    //
    // Shopkeepers/{shopkeeperUid}/Orders
    // ============================================================

    private CollectionReference getOrdersCollection() {

        return db.collection("Shopkeepers")
                .document(
                        ShopkeeperLogController.getShopkeeperUid()
                )
                .collection("Orders");
    }


    // ============================================================
    // GET ORDERS COLLECTION FOR A SPECIFIC DATE
    //
    // Shopkeepers/{uid}/Orders/{date}/OrderList
    // ============================================================

    private CollectionReference getOrdersByDateCollection(
            String date) {

        return getOrdersCollection()
                .document(date)
                .collection("OrderList");
    }


    // ============================================================
    // ADD NEW ORDER
    //
    // Order is stored inside today's date document.
    // ============================================================

    public boolean addOrder(
            OrderModel order) {

        try {

            // Get today's date
            String today =
                    LocalDate.now().toString();


            // Get the collection for today's orders
            CollectionReference orderCollection =
                    getOrdersByDateCollection(
                            today
                    );


            // Create a new Firestore document
            DocumentReference documentReference =
                    orderCollection.document();


            // Store generated document ID
            order.setOrderId(
                    documentReference.getId()
            );


            // Store the order date
            order.setOrderDate(
                    today
            );


            // Set initial status if not already set
            if (order.getOrderStatus() == null
                    || order.getOrderStatus().isEmpty()) {

                order.setOrderStatus(
                        "NEW"
                );
            }


            // Save order
            ApiFuture<com.google.cloud.firestore.WriteResult>
                    future =
                    documentReference.set(order);


            future.get();


            System.out.println(
                    "Order added successfully"
            );

            System.out.println(
                    "Order ID: "
                            + order.getOrderId()
            );

            System.out.println(
                    "Order Date: "
                            + today
            );


            return true;


        } catch (Exception e) {

            System.out.println(
                    "Error adding order"
            );

            e.printStackTrace();

            return false;
        }
    }


    // ============================================================
    // GET ALL NEW ORDERS
    //
    // Searches all OrderList subcollections across all dates.
    // ============================================================

    public ArrayList<OrderModel> getNewOrders() {

        ArrayList<OrderModel> orders =
                new ArrayList<>();

        try {

            Query query =
                    db.collectionGroup(
                            "OrderList"
                    )
                    .whereEqualTo(
                            "orderStatus",
                            "NEW"
                    );


            ApiFuture<QuerySnapshot>
                    future =
                    query.get();


            QuerySnapshot querySnapshot =
                    future.get();


            for (QueryDocumentSnapshot document
                    : querySnapshot.getDocuments()) {

                OrderModel order =
                        document.toObject(
                                OrderModel.class
                        );


                if (order != null) {

                    // Make sure order ID is set
                    order.setOrderId(
                            document.getId()
                    );


                    orders.add(order);


                    System.out.println(
                            "New order retrieved: "
                                    + order.getOrderId()
                    );
                }
            }


            System.out.println(
                    "Total new orders: "
                            + orders.size()
            );


        } catch (Exception e) {

            System.out.println(
                    "Error getting new orders"
            );

            e.printStackTrace();
        }


        return orders;
    }


    // ============================================================
    // GET ORDERS BY STATUS
    //
    // Example:
    // NEW
    // PREPARING
    // READY
    // COMPLETED
    // DECLINED
    // ============================================================

    public ArrayList<OrderModel> getOrdersByStatus(
            String status) {

        ArrayList<OrderModel> orders =
                new ArrayList<>();

        try {

            Query query =
                    db.collectionGroup(
                            "OrderList"
                    )
                    .whereEqualTo(
                            "orderStatus",
                            status
                    );


            QuerySnapshot querySnapshot =
                    query.get()
                            .get();


            for (QueryDocumentSnapshot document
                    : querySnapshot.getDocuments()) {

                OrderModel order =
                        document.toObject(
                                OrderModel.class
                        );


                if (order != null) {

                    order.setOrderId(
                            document.getId()
                    );


                    orders.add(order);
                }
            }


        } catch (Exception e) {

            System.out.println(
                    "Error getting "
                            + status
                            + " orders"
            );

            e.printStackTrace();
        }


        return orders;
    }


    // ============================================================
    // GET PREPARING ORDERS
    // ============================================================

    public ArrayList<OrderModel> getPreparingOrders() {

        return getOrdersByStatus(
                "PREPARING"
        );
    }


    // ============================================================
    // GET READY ORDERS
    // ============================================================

    public ArrayList<OrderModel> getReadyOrders() {

        return getOrdersByStatus(
                "READY"
        );
    }


    // ============================================================
    // ACCEPT ORDER
    //
    // NEW → PREPARING
    // ============================================================

    public boolean acceptOrder(
            OrderModel order) {

        return updateOrderStatus(
                order,
                "PREPARING"
        );
    }


    // ============================================================
    // DECLINE ORDER
    //
    // NEW → DECLINED
    // ============================================================

    public boolean declineOrder(
            OrderModel order) {

        return updateOrderStatus(
                order,
                "DECLINED"
        );
    }


    // ============================================================
    // MARK ORDER AS READY
    //
    // PREPARING → READY
    // ============================================================

    public boolean markOrderReady(
            OrderModel order) {

        return updateOrderStatus(
                order,
                "READY"
        );
    }


    // ============================================================
    // UPDATE ORDER STATUS
    //
    // Uses orderDate to find the correct date collection.
    // ============================================================

    public boolean updateOrderStatus(
            OrderModel order,
            String newStatus) {

        try {

            getOrdersByDateCollection(
                    order.getOrderDate()
            )
                    .document(
                            order.getOrderId()
                    )
                    .update(
                            "orderStatus",
                            newStatus
                    )
                    .get();


            // Update local object too
            order.setOrderStatus(
                    newStatus
            );


            System.out.println(
                    "Order "
                            + order.getOrderId()
                            + " updated to "
                            + newStatus
            );


            return true;


        } catch (Exception e) {

            System.out.println(
                    "Error updating order status"
            );

            e.printStackTrace();

            return false;
        }
    }


    // ============================================================
    // GET ORDERS FOR A PARTICULAR DATE
    //
    // Example:
    // getOrdersByDate("2026-08-29")
    // ============================================================

    public ArrayList<OrderModel> getOrdersByDate(
            String date) {

        ArrayList<OrderModel> orders =
                new ArrayList<>();

        try {

            QuerySnapshot querySnapshot =
                    getOrdersByDateCollection(
                            date
                    )
                    .get()
                    .get();


            for (QueryDocumentSnapshot document
                    : querySnapshot.getDocuments()) {

                OrderModel order =
                        document.toObject(
                                OrderModel.class
                        );


                if (order != null) {

                    order.setOrderId(
                            document.getId()
                    );

                    orders.add(order);
                }
            }


        } catch (Exception e) {

            System.out.println(
                    "Error getting orders for date: "
                            + date
            );

            e.printStackTrace();
        }


        return orders;
    }


    // ============================================================
    // DELETE ORDER
    // ============================================================

    public boolean deleteOrder(
            OrderModel order) {

        try {

            getOrdersByDateCollection(
                    order.getOrderDate()
            )
                    .document(
                            order.getOrderId()
                    )
                    .delete()
                    .get();


            System.out.println(
                    "Order deleted successfully"
            );


            return true;


        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public ArrayList<OrderModel> getAllOrders() {
        ArrayList<OrderModel> orders = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> query = db.collectionGroup("OrderList")
                    .get();
            QuerySnapshot querySnapshot = query.get();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                OrderModel order = document.toObject(OrderModel.class);
                if (order != null) {
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
}

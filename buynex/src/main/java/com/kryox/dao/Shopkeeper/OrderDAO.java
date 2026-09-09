package com.kryox.dao.Shopkeeper;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentChange;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Shopkeeper.ProductController;
import com.kryox.controller.Shopkeeper.ShopkeeperLogController;
import com.kryox.model.Shopkeeper.OrderModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OrderDAO {

    private final Firestore db =
            Firebaseconfig.gFirestore();

    private ListenerRegistration orderListener;

    private String getCurrentShopkeeperUid() {

        String uid = ShopkeeperLogController.getShopkeeperUid();
        if (uid == null || uid.isBlank()) {
            if (com.kryox.view.Shopkeeper.ViewConstants.shopkeeperModel != null
                    && com.kryox.view.Shopkeeper.ViewConstants.shopkeeperModel.getShopkeeperUid() != null) {
                uid = com.kryox.view.Shopkeeper.ViewConstants.shopkeeperModel.getShopkeeperUid();
            }
        }
        return uid;
    }

    private CollectionReference getOrdersCollection() {

        return db.collection("Orders");
    }

    public boolean addOrder(OrderModel order) {

        try {

            if (order == null) {
                System.out.println("Order cannot be null");
                return false;
            }

            String shopkeeperUid = order.getShopkeeperUid();

            if (shopkeeperUid == null || shopkeeperUid.isBlank()) {
                shopkeeperUid = getCurrentShopkeeperUid();
                if (shopkeeperUid == null || shopkeeperUid.isBlank()) {
                    shopkeeperUid = "default_shopkeeper";
                }
                order.setShopkeeperUid(shopkeeperUid);
            }

            String today = LocalDate.now().toString();

            DocumentReference documentReference = getOrdersCollection().document();
            String orderId = documentReference.getId();

            order.setOrderId(orderId);
            order.setOrderDate(today);

            if (order.getOrderStatus() == null || order.getOrderStatus().isBlank()) {
                order.setOrderStatus("NEW");
            }

            documentReference.set(order).get();

            System.out.println("================================");
            System.out.println("ORDER CREATED IN ROOT ORDERS");
            System.out.println("Order ID: " + orderId);
            System.out.println("Shopkeeper UID: " + shopkeeperUid);
            System.out.println("Status: " + order.getOrderStatus());
            System.out.println("================================");

            return true;

        } catch (Exception e) {

            System.out.println("Error adding order: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<OrderModel> getNewOrders() {

        return getOrdersByStatus("NEW");
    }

    public void listenForNewOrders(Consumer<OrderModel> onNewOrder) {

        try {

            stopOrderListener();

            String uid = getCurrentShopkeeperUid();

            if (uid == null || uid.isBlank()) {
                System.out.println("Shopkeeper UID missing. Listener not started.");
                return;
            }

            Query query = getOrdersCollection()
                    .whereEqualTo("orderStatus", "NEW");

            orderListener = query.addSnapshotListener((snapshot, error) -> {

                if (error != null) {
                    System.out.println("Order listener error: " + error.getMessage());
                    return;
                }

                if (snapshot == null) {
                    return;
                }

                for (DocumentChange change : snapshot.getDocumentChanges()) {

                    if (change.getType() == DocumentChange.Type.ADDED) {

                        try {
                            OrderModel order = change.getDocument().toObject(OrderModel.class);
                            if (order != null) {
                                order.setOrderId(change.getDocument().getId());
                                if (onNewOrder != null) {
                                    onNewOrder.accept(order);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            System.out.println("REAL-TIME ORDER LISTENER STARTED FOR SHOPKEEPER: " + uid);

        } catch (Exception e) {

            System.out.println("Unable to start order listener");
            e.printStackTrace();
        }
    }

    public void stopOrderListener() {

        if (orderListener != null) {
            orderListener.remove();
            orderListener = null;
            System.out.println("Order listener stopped");
        }
    }

    public boolean acceptOrder(OrderModel order) {
        if (order == null || order.getOrderId() == null || order.getOrderId().isBlank()) {
            System.out.println("Order or Order ID missing for acceptOrder");
            return false;
        }

        // Deduct inventory stock if not already deducted for this order
        if (!order.isStockDeducted()) {
            try {
                boolean deducted = ProductController.deductStockForOrder(order);
                if (deducted) {
                    order.setStockDeducted(true);
                }
            } catch (Exception e) {
                System.out.println("Error deducting stock on order acceptance: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return updateOrderStatus(order, "PREPARING");
    }

    public boolean declineOrder(OrderModel order) {

        return updateOrderStatus(order, "DECLINED");
    }

    public boolean markOrderReady(OrderModel order) {

        return updateOrderStatus(order, "READY");
    }

    public boolean updateOrderStatus(OrderModel order, String newStatus) {

        try {

            if (order == null || order.getOrderId() == null || order.getOrderId().isBlank()) {
                System.out.println("Order or Order ID missing");
                return false;
            }

            Map<String, Object> updateFields = new HashMap<>();
            updateFields.put("orderStatus", newStatus);
            if ("PREPARING".equalsIgnoreCase(newStatus)) {
                updateFields.put("stockDeducted", true);
                order.setStockDeducted(true);
            }

            getOrdersCollection()
                    .document(order.getOrderId())
                    .update(updateFields)
                    .get();

            order.setOrderStatus(newStatus);

            System.out.println("Order " + order.getOrderId() + " status updated to → " + newStatus);

            return true;

        } catch (Exception e) {

            System.out.println("Error updating order status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean assignDeliveryPartner(OrderModel order, String riderName, String riderPhone, String riderDistance) {

        try {

            if (order == null || order.getOrderId() == null || order.getOrderId().isBlank()) {
                System.out.println("Order or Order ID missing for rider assignment");
                return false;
            }

            getOrdersCollection()
                    .document(order.getOrderId())
                    .update(
                            "orderStatus", "OUT_FOR_DELIVERY",
                            "riderName", riderName,
                            "riderPhone", riderPhone,
                            "riderDistance", riderDistance
                    )
                    .get();

            order.setOrderStatus("OUT_FOR_DELIVERY");
            order.setRiderName(riderName);
            order.setRiderPhone(riderPhone);
            order.setRiderDistance(riderDistance);

            System.out.println("Assigned delivery partner " + riderName + " (" + riderDistance + ") to order " + order.getOrderId());

            return true;

        } catch (Exception e) {

            System.out.println("Error assigning delivery partner: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean requestDeliveryPartner(OrderModel order) {
        return updateOrderStatus(order, "REQUESTING_DELIVERY");
    }

    public ArrayList<OrderModel> getDeliveryRequests() {
        ArrayList<OrderModel> requests = new ArrayList<>();
        try {
            QuerySnapshot snapshot = getOrdersCollection().get().get();
            for (QueryDocumentSnapshot doc : snapshot.getDocuments()) {
                OrderModel order = doc.toObject(OrderModel.class);
                if (order != null) {
                    order.setOrderId(doc.getId());
                    String st = order.getOrderStatus();
                    if ("REQUESTING_DELIVERY".equalsIgnoreCase(st) || "OUT_FOR_DELIVERY".equalsIgnoreCase(st) || "ACCEPTED".equalsIgnoreCase(st)) {
                        requests.add(order);
                    }
                }
            }
            System.out.println("Fetched " + requests.size() + " active delivery requests (REQUESTING_DELIVERY / OUT_FOR_DELIVERY / ACCEPTED) from Firestore.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }

    public ArrayList<OrderModel> getOrdersByStatus(String status) {

        ArrayList<OrderModel> orders = new ArrayList<>();

        try {

            String uid = getCurrentShopkeeperUid();

            QuerySnapshot snapshot = getOrdersCollection().get().get();

            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                OrderModel order = document.toObject(OrderModel.class);

                if (order != null) {

                    order.setOrderId(document.getId());

                    String currentStatus = order.getOrderStatus();
                    String orderShopkeeperUid = order.getShopkeeperUid();

                    boolean isMatchingShopkeeper = (uid == null || uid.isBlank()
                            || orderShopkeeperUid == null || orderShopkeeperUid.isBlank()
                            || uid.equalsIgnoreCase(orderShopkeeperUid)
                            || "shopkeeperUid".equalsIgnoreCase(orderShopkeeperUid)
                            || "default_shopkeeper".equalsIgnoreCase(orderShopkeeperUid));

                    boolean isMatchingStatus = (status == null || status.isBlank()
                            || (currentStatus != null && (currentStatus.equalsIgnoreCase(status)
                            || ("READY".equalsIgnoreCase(status) && "REQUESTING_DELIVERY".equalsIgnoreCase(currentStatus))
                            || ("DELIVERY".equalsIgnoreCase(status) && "OUT_FOR_DELIVERY".equalsIgnoreCase(currentStatus))
                            || ("OUT_FOR_DELIVERY".equalsIgnoreCase(status) && "DELIVERY".equalsIgnoreCase(currentStatus)))));

                    if (isMatchingShopkeeper && isMatchingStatus) {
                        orders.add(order);
                    }
                }
            }

            System.out.println("Orders with status " + status + " for shopkeeper (" + uid + "): " + orders.size());

        } catch (Exception e) {

            System.out.println("Error getting " + status + " orders: " + e.getMessage());
            e.printStackTrace();
        }

        return orders;
    }

    public ArrayList<OrderModel> getPreparingOrders() {

        return getOrdersByStatus("PREPARING");
    }

    public ArrayList<OrderModel> getReadyOrders() {

        return getOrdersByStatus("READY");
    }

    public ArrayList<OrderModel> getAllOrders() {

        return getOrdersByStatus(null);
    }

    public ArrayList<OrderModel> getOrdersByDate(String date) {

        ArrayList<OrderModel> orders = new ArrayList<>();

        try {

            String uid = getCurrentShopkeeperUid();

            QuerySnapshot snapshot = getOrdersCollection().get().get();

            for (QueryDocumentSnapshot document : snapshot.getDocuments()) {

                OrderModel order = document.toObject(OrderModel.class);

                if (order != null) {

                    order.setOrderId(document.getId());

                    String orderDate = order.getOrderDate();
                    String orderShopkeeperUid = order.getShopkeeperUid();

                    boolean isMatchingShopkeeper = (uid == null || uid.isBlank()
                            || orderShopkeeperUid == null || orderShopkeeperUid.isBlank()
                            || uid.equalsIgnoreCase(orderShopkeeperUid));

                    boolean isMatchingDate = (date == null || date.isBlank()
                            || (orderDate != null && orderDate.equalsIgnoreCase(date)));

                    if (isMatchingShopkeeper && isMatchingDate) {
                        orders.add(order);
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orders;
    }

    public boolean deleteOrder(OrderModel order) {

        try {

            if (order == null || order.getOrderId() == null) {
                return false;
            }

            getOrdersCollection().document(order.getOrderId()).delete().get();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}
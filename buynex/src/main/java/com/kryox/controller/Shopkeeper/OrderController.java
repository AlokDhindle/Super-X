package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.kryox.dao.Shopkeeper.OrderDAO;
import com.kryox.model.Shopkeeper.OrderModel;

public class OrderController {

    private static final OrderDAO orderDAO =
            new OrderDAO();

    // GET NEW ORDERS

    public static ArrayList<OrderModel> getNewOrders() {

        return orderDAO.getNewOrders();
    }

    // ACCEPT

    public static boolean acceptOrder(
            OrderModel order) {

        return orderDAO.acceptOrder(order);
    }

    // DECLINE

    public static boolean declineOrder(
            OrderModel order) {

        return orderDAO.declineOrder(order);
    }

    // READY

    public static boolean markOrderReady(
            OrderModel order) {

        return orderDAO.markOrderReady(order);
    }

    // ALL ORDERS

    public static ArrayList<OrderModel> getAllOrders() {

        return orderDAO.getAllOrders();
    }

    // PREPARING

    public static ArrayList<OrderModel> getPreparingOrders() {

        return orderDAO.getPreparingOrders();
    }

    // READY ORDERS

    public static ArrayList<OrderModel> getReadyOrders() {

        return orderDAO.getReadyOrders();
    }

    // ORDERS BY STATUS

    public static ArrayList<OrderModel> getOrdersByStatus(
            String status) {

        return orderDAO.getOrdersByStatus(status);
    }

    // REAL-TIME LISTENER

    public static void listenForNewOrders(
            Consumer<OrderModel> onNewOrder) {

        orderDAO.listenForNewOrders(
                onNewOrder
        );
    }

    // UPDATE STATUS

    public static boolean updateOrderStatus(OrderModel order, String newStatus) {

        return orderDAO.updateOrderStatus(order, newStatus);
    }

    // ASSIGN DELIVERY PARTNER (1.5KM RADIUS)

    public static boolean assignDeliveryPartner(OrderModel order, String riderName, String riderPhone, String riderDistance) {

        return orderDAO.assignDeliveryPartner(order, riderName, riderPhone, riderDistance);
    }

    // BROADCAST DELIVERY REQUEST

    public static boolean requestDeliveryPartner(OrderModel order) {

        return orderDAO.requestDeliveryPartner(order);
    }

    // GET BROADCASTED DELIVERY REQUESTS FOR DELIVERY PORTAL

    public static ArrayList<OrderModel> getDeliveryRequests() {

        return orderDAO.getDeliveryRequests();
    }

    // STOP LISTENER

    public static void stopOrderListener() {

        orderDAO.stopOrderListener();
    }
}
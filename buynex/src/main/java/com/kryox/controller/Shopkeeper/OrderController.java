package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;

import com.kryox.dao.Shopkeeper.OrderDAO;
import com.kryox.model.Shopkeeper.OrderModel;

public class OrderController {

    public static ArrayList<OrderModel> getNewOrders() {
        return new OrderDAO().getNewOrders();
    }

    public static boolean acceptOrder(OrderModel order) {
        return new OrderDAO().acceptOrder(order);
    }

    public static boolean declineOrder(OrderModel order) {
        return new OrderDAO().declineOrder(order);
    }

    public static ArrayList<OrderModel> getAllOrders() {
        return new OrderDAO().getAllOrders();
    }



    
    
}

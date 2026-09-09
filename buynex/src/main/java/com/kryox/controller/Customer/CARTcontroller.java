package com.kryox.controller.Customer;

import java.util.List;

import com.kryox.dao.Customer.Cartdao;
import com.kryox.model.Customer.Productcart;

public class CARTcontroller {

    private Cartdao cartdao = new Cartdao();


    public void addTocart(String userId, String productId, String name, double price, String name1, int quantity, String shopkeeperUid) {

        System.out.println("CONTROLLER USER ID = [" + userId + "], PRODUCT ID = [" + productId + "]");

        String finalShopkeeperUid = (shopkeeperUid != null && !shopkeeperUid.isBlank() && !"default_shopkeeper".equalsIgnoreCase(shopUid(shopkeeperUid)))
                ? shopkeeperUid
                : userId;

        Productcart productcart =
                new Productcart(userId, productId, name, price, name1, quantity, finalShopkeeperUid);
        cartdao.addTocart(productcart);
    }

    public void addTocart(String userId, String name, double price, String name1, int quantity, String shopkeeperUid) {
        addTocart(userId, name, name, price, name1, quantity, shopkeeperUid);
    }

    private String shopUid(String uid) {
        return uid != null ? uid.trim() : "";
    }

    public void addTocart(String userId, String name, double price, String name1, int quantity) {
        addTocart(userId, name, price, name1, quantity, userId);
    }


    public List<Productcart> getCart(
            String userId) {

        System.out.println("CONTROLLER FETCH USER ID = [" + userId + "]");

        return cartdao.getCart(userId);
    }

    public void updateQuantity(String userId, String productName, int quantity) {
        cartdao.updateQuantity(userId, productName, quantity);
    }

    public void deleteFromCart(String userId, String productName) {
        cartdao.deleteFromCart(userId, productName);
    }

    public void clearCart(String userId) {
        cartdao.clearCart(userId);
    }
}
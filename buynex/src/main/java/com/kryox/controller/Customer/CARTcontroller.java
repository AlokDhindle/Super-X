package com.kryox.controller.Customer;

import java.util.List;

import com.kryox.dao.Customer.Cartdao;
import com.kryox.model.Customer.Productcart;

public class CARTcontroller {

    private Cartdao cartdao = new Cartdao();


    public void addTocart(String userId,String name,double price,String name1,int quantity) {

        System.out.println("CONTROLLER USER ID = [" + userId + "]");

        Productcart productcart =
                new Productcart(userId,name,price,name1,quantity);
                cartdao.addTocart(productcart);
    }


    public List<Productcart> getCart(
            String userId) {

        System.out.println("CONTROLLER FETCH USER ID = [" + userId + "]");

        return cartdao.getCart(userId);
    }
}
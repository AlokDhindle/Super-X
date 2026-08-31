package com.kryox.model.Customer;

public class Productcart {

    private String UserId;
    private String name;
    private double price;
    private String name1;
    private int quantity;

    // IMPORTANT:
    // Firestore fetch ke liye required
    public Productcart() {
    }

    // Normal constructor
    public Productcart(
            String UserId,
            String name,
            double price,
            String name1, int quantity) {

        this.UserId = UserId;
        this.name = name;
        this.price = price;
        this.name1 = name1;
        this.quantity = quantity;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
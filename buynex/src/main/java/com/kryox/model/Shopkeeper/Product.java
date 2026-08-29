package com.kryox.model.Shopkeeper;

public class Product {

    private int id;
    private String name;
    private String seller;
    private double price;
    private String imagePath;
    private int quantity;

    public Product(int id, String name, String seller,
                   double price, String imagePath, int quantity) {

        this.id = id;
        this.name = name;
        this.seller = seller;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSeller() {
        return seller;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
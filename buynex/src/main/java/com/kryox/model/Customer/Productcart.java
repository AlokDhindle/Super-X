package com.kryox.model.Customer;

public class Productcart {

    private String UserId;
    private String productId;
    private String name;
    private double price;
    private String name1;
    private int quantity;
    private String shopkeeperUid;

    // IMPORTANT:
    // Firestore fetch ke liye required
    public Productcart() {
    }

    // Normal constructor
    public Productcart(
            String UserId,
            String name,
            double price,
            String name1,
            int quantity,
            String shopkeeperUid) {

        this.UserId = UserId;
        this.productId = name;
        this.name = name;
        this.price = price;
        this.name1 = name1;
        this.shopkeeperUid = shopkeeperUid;
        this.quantity = quantity;
    }

    public Productcart(
            String UserId,
            String productId,
            String name,
            double price,
            String name1,
            int quantity,
            String shopkeeperUid) {

        this.UserId = UserId;
        this.productId = productId != null && !productId.isBlank() ? productId : name;
        this.name = name;
        this.price = price;
        this.name1 = name1;
        this.shopkeeperUid = shopkeeperUid;
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

    public String getShopkeeperUid() {
        return shopkeeperUid;
    }

    public void setShopkeeperUid(String shopkeeperUid) {
        this.shopkeeperUid = shopkeeperUid;
    }

    public String getProductId() {
        if (productId != null && !productId.isBlank()) {
            return productId;
        }
        return name;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
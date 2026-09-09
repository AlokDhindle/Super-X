package com.kryox.model.Shopkeeper;

public class OrderItemModel {

    private String productId;
    private String productName;
    private String imageUrl;

    private double price;
    private int quantity;
    private double totalPrice;
    private String shopName;

    public OrderItemModel() {
    }

    public OrderItemModel(
            String productId,
            String productName,
            String imageUrl,
            double price,
            int quantity,
            double totalPrice) {

        this.productId = productId;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public double getUnitPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        if (totalPrice > 0) {
            return totalPrice;
        }
        return price * (quantity > 0 ? quantity : 1);
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnitPrice(double unitPrice) {
        this.price = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
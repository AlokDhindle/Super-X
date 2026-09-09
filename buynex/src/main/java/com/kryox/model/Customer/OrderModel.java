package com.kryox.model.Customer;

public class OrderModel {

    private String orderId;
    private String userId;
    private String category;
    private String productName;
    private String orderDetails;
    private String price;
    private String status;
    private String icon;

    // Default constructor
    // Firestore ke liye required hai
    public OrderModel() {
    }

    // Parameterized constructor
    public OrderModel(String orderId,
                      String userId,
                      String category,
                      String productName,
                      String orderDetails,
                      String price,
                      String status,
                      String icon) {

        this.orderId = orderId;
        this.userId = userId;
        this.category = category;
        this.productName = productName;
        this.orderDetails = orderDetails;
        this.price = price;
        this.status = status;
        this.icon = icon;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getIcon() {
        return icon;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
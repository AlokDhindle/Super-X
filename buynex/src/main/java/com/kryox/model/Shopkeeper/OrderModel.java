package com.kryox.model.Shopkeeper;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    private String orderId;
    private String userId;
    private String customerName;
    private String customerPhone;

    private String shopkeeperUid;
    private String shopName;

    private String orderDate;
    private String orderStatus;

    private double totalAmount;

    private List<OrderItemModel> products =
            new ArrayList<>();

    private String riderName;
    private String riderPhone;
    private String riderDistance;
    private double shopLat = 18.5204;
    private double shopLng = 73.8567;
    private String paymentStatus;
    private double subtotal;
    private double platformFee;
    private double tax;
    private double deliveryFee;
    private double discount;
    private String promoCode;
    private boolean stockDeducted;

    public OrderModel() {
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCustomerId() {
        if (userId != null && !userId.isBlank()) {
            return userId;
        }
        return customerName;
    }

    public String getCustomerName() {
        if (customerName != null && !customerName.isBlank()) {
            return customerName;
        }
        if (userId != null && !userId.isBlank()) {
            return userId;
        }
        return "Customer";
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getShopkeeperUid() {
        return shopkeeperUid;
    }

    public String getShopName() {
        return shopName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItemModel> getProducts() {
        return products;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCustomerId(String customerId) {
        this.userId = customerId;
        if (this.customerName == null || this.customerName.isBlank()) {
            this.customerName = customerId;
        }
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setShopkeeperUid(String shopkeeperUid) {
        this.shopkeeperUid = shopkeeperUid;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setProducts(List<OrderItemModel> products) {

        if (products == null) {
            this.products = new ArrayList<>();
        } else {
            this.products = products;
        }
    }

    public void addProduct(OrderItemModel item) {

        if (item != null) {
            products.add(item);
        }
    }

    public String getRiderName() {
        if (riderName != null && !riderName.isBlank()) {
            return riderName;
        }
        return "Rahul Sharma";
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhone() {
        if (riderPhone != null && !riderPhone.isBlank()) {
            return riderPhone;
        }
        return "+91 98765 43210";
    }

    public void setRiderPhone(String riderPhone) {
        this.riderPhone = riderPhone;
    }

    public String getRiderDistance() {
        if (riderDistance != null && !riderDistance.isBlank()) {
            return riderDistance;
        }
        return "0.8 km away (within 1.5km radius)";
    }

    public void setRiderDistance(String riderDistance) {
        this.riderDistance = riderDistance;
    }

    public double getShopLat() {
        return shopLat;
    }

    public void setShopLat(double shopLat) {
        this.shopLat = shopLat;
    }

    public double getShopLng() {
        return shopLng;
    }

    public void setShopLng(double shopLng) {
        this.shopLng = shopLng;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(double platformFee) {
        this.platformFee = platformFee;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public boolean isStockDeducted() {
        return stockDeducted;
    }

    public void setStockDeducted(boolean stockDeducted) {
        this.stockDeducted = stockDeducted;
    }
}
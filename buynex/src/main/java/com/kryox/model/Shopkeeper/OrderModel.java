package com.kryox.model.Shopkeeper;

import java.util.ArrayList;

public class OrderModel {

    private String orderId;

    private String customerId;

    private String customerName;

    private String customerPhone;

    private ArrayList<OrderItemModel> products;

    private double subtotal;

    private double discount;

    private double tax;

    private double totalAmount;

    private String orderStatus;

    private String paymentMethod;

    private String paymentStatus;

    private String orderDate;

    private String deliveryAddress;


    // ============================================================
    // EMPTY CONSTRUCTOR
    // REQUIRED FOR FIRESTORE
    // ============================================================

    public OrderModel() {
    }


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public OrderModel(
            String orderId,
            String customerId,
            String customerName,
            String customerPhone,
            ArrayList<OrderItemModel> products,
            double subtotal,
            double discount,
            double tax,
            double totalAmount,
            String orderStatus,
            String paymentMethod,
            String paymentStatus,
            String orderDate,
            String deliveryAddress) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.products = products;
        this.subtotal = subtotal;
        this.discount = discount;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
    }


    // ============================================================
    // ORDER ID
    // ============================================================

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    // ============================================================
    // CUSTOMER ID
    // ============================================================

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    // ============================================================
    // CUSTOMER NAME
    // ============================================================

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    // ============================================================
    // CUSTOMER PHONE
    // ============================================================

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    // ============================================================
    // PRODUCTS
    // ============================================================

    public ArrayList<OrderItemModel> getProducts() {
        return products;
    }

    public void setProducts(
            ArrayList<OrderItemModel> products) {

        this.products = products;
    }


    // ============================================================
    // SUBTOTAL
    // ============================================================

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }


    // ============================================================
    // DISCOUNT
    // ============================================================

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


    // ============================================================
    // TAX
    // ============================================================

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }


    // ============================================================
    // TOTAL AMOUNT
    // ============================================================

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    // ============================================================
    // ORDER STATUS
    // ============================================================

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    // ============================================================
    // PAYMENT METHOD
    // ============================================================

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    // ============================================================
    // PAYMENT STATUS
    // ============================================================

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    // ============================================================
    // ORDER DATE
    // ============================================================

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    // ============================================================
    // DELIVERY ADDRESS
    // ============================================================

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(
            String deliveryAddress) {

        this.deliveryAddress = deliveryAddress;
    }
}

package com.kryox.model;
import java.time.LocalDate;

public class ProductModel {

    String productId;
    String productName;
    String category;
    String brand;
    String descriptionValue;
    String sku;
    String barcode;
    Double mrp;
    Double costPrice;
    Double sellingPrice;
    Double discount;
    Double tax;
    int stockQuantity;
    int lowStockLimit;
    String unit;
    String status;
    String batchNumber;
    String expiryTracking;
    LocalDate manufacturingDate;
    LocalDate expiryDate;
    String imageUrl;



    public ProductModel() {
    }

    public ProductModel(String productId, String productName, String category, String brand, String descriptionValue, String sku, String barcode, Double mrp, Double costPrice, Double sellingPrice, Double discount, Double tax, int stockQuantity, int lowStockLimit, String unit, String status, String batchNumber, String expiryTracking, LocalDate manufacturingDate, LocalDate expiryDate,String imageUrl) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.brand = brand;
        this.descriptionValue = descriptionValue;
        this.sku = sku;
        this.barcode = barcode;
        this.mrp = mrp;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;   
        this.discount = discount;
        this.tax = tax;
        this.stockQuantity = stockQuantity;
        this.lowStockLimit = lowStockLimit;
        this.unit = unit;
        this.status = status;
        this.batchNumber = batchNumber;
        this.expiryTracking = expiryTracking;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Double getMrp() {
        return mrp;
    }

    public void setMrp(Double mrp) {
        this.mrp = mrp;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getLowStockLimit() {
        return lowStockLimit;
    }

    public void setLowStockLimit(int lowStockLimit) {
        this.lowStockLimit = lowStockLimit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getExpiryTracking() {
        return expiryTracking;
    }

    public void setExpiryTracking(String expiryTracking) {
        this.expiryTracking = expiryTracking;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}

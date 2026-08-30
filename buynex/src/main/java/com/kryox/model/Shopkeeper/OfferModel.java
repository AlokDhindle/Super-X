package com.kryox.model.Shopkeeper;

public class OfferModel {

    private String offerId;

    private String offerName;

    private String promoCode;

    private String description;

    // PERCENTAGE or FLAT_AMOUNT
    private String discountType;

    private double discountValue;

    // Stored as String: yyyy-MM-dd
    private String startDate;

    private String endDate;

    private int maxUsesPerCustomer;

    private int totalUsageLimit;

    // ACTIVE or INACTIVE
    private String status;


    // ============================================================
    // EMPTY CONSTRUCTOR
    // REQUIRED FOR FIRESTORE
    // ============================================================

    public OfferModel() {
    }


    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public OfferModel(
            String offerId,
            String offerName,
            String promoCode,
            String description,
            String discountType,
            double discountValue,
            String startDate,
            String endDate,
            int maxUsesPerCustomer,
            int totalUsageLimit,
            String status) {

        this.offerId = offerId;
        this.offerName = offerName;
        this.promoCode = promoCode;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxUsesPerCustomer = maxUsesPerCustomer;
        this.totalUsageLimit = totalUsageLimit;
        this.status = status;
    }


    // ============================================================
    // OFFER ID
    // ============================================================

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }


    // ============================================================
    // OFFER NAME
    // ============================================================

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }


    // ============================================================
    // PROMO CODE
    // ============================================================

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }


    // ============================================================
    // DESCRIPTION
    // ============================================================

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // ============================================================
    // DISCOUNT TYPE
    // ============================================================

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }


    // ============================================================
    // DISCOUNT VALUE
    // ============================================================

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }


    // ============================================================
    // START DATE
    // ============================================================

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    // ============================================================
    // END DATE
    // ============================================================

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    // ============================================================
    // MAX USES PER CUSTOMER
    // ============================================================

    public int getMaxUsesPerCustomer() {
        return maxUsesPerCustomer;
    }

    public void setMaxUsesPerCustomer(int maxUsesPerCustomer) {
        this.maxUsesPerCustomer = maxUsesPerCustomer;
    }


    // ============================================================
    // TOTAL USAGE LIMIT
    // ============================================================

    public int getTotalUsageLimit() {
        return totalUsageLimit;
    }

    public void setTotalUsageLimit(int totalUsageLimit) {
        this.totalUsageLimit = totalUsageLimit;
    }


    // ============================================================
    // STATUS
    // ============================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
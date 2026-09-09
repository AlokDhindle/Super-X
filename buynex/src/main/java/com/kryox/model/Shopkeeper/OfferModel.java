package com.kryox.model.Shopkeeper;

public class OfferModel {

    private String offerId;
    private String offerName;
    private String promoCode;
    private String description;
    private String discountType;
    private double discountValue;
    private String startDate;
    private String endDate;
    private int maxUsesPerCustomer;
    private int totalUsageLimit;
    private String status;

    private String category = "General";
    private int redemptions = 0;
    private int totalViews = 0;
    private double netProfit = 0.0;

    public OfferModel() {
    }

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
            String status
    ) {

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

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getMaxUsesPerCustomer() {
        return maxUsesPerCustomer;
    }

    public void setMaxUsesPerCustomer(int maxUsesPerCustomer) {
        this.maxUsesPerCustomer = maxUsesPerCustomer;
    }

    public int getTotalUsageLimit() {
        return totalUsageLimit;
    }

    public void setTotalUsageLimit(int totalUsageLimit) {
        this.totalUsageLimit = totalUsageLimit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        if (category == null ||
                category.trim().isEmpty()) {

            this.category = "General";

        } else {

            this.category = category.trim();
        }
    }

    public int getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(int redemptions) {
        this.redemptions = Math.max(0, redemptions);
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = Math.max(0, totalViews);
    }

    public double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(double netProfit) {
        this.netProfit = netProfit;
    }
}

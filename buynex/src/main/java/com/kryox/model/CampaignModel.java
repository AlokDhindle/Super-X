package com.kryox.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CampaignModel implements Serializable {

    private boolean currentlyActive;
    private String campaignId;
    private String shopkeeperId;
    private String shopkeeperName;
    private String storeName;
    private String title;
    private String description;
    private String imageUrl;
    private String startDate; // YYYY-MM-DD
    private String endDate;   // YYYY-MM-DD
    private double discount;
    private String discountType; // "PERCENTAGE", "FLAT_AMOUNT", "FREE_DELIVERY"
    private List<String> applicableProducts;
    private String applicableCategories;
    private double minimumOrderAmount;
    private double maximumDiscount;
    private String termsAndConditions;
    private String status;      // "PENDING", "APPROVED", "REJECTED", "CANCELLED"
    private String requestType; // "CAMPAIGN_REQUEST"
    private String createdAt;
    private String updatedAt;
    private String approvedBy;
    private String approvedAt;
    private String rejectedBy;
    private String rejectedAt;
    private String rejectionReason;

    public CampaignModel() {
        this.applicableProducts = new ArrayList<>();
        this.status = "PENDING";
        this.requestType = "CAMPAIGN_REQUEST";
    }

    public CampaignModel(
            String campaignId,
            String shopkeeperId,
            String shopkeeperName,
            String storeName,
            String title,
            String description,
            String imageUrl,
            String startDate,
            String endDate,
            double discount,
            String discountType,
            List<String> applicableProducts,
            String applicableCategories,
            double minimumOrderAmount,
            double maximumDiscount,
            String termsAndConditions,
            String status,
            String requestType,
            String createdAt,
            String updatedAt
    ) {
        this.campaignId = campaignId;
        this.shopkeeperId = shopkeeperId;
        this.shopkeeperName = shopkeeperName;
        this.storeName = storeName;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
        this.discountType = discountType;
        this.applicableProducts = applicableProducts != null ? applicableProducts : new ArrayList<>();
        this.applicableCategories = applicableCategories;
        this.minimumOrderAmount = minimumOrderAmount;
        this.maximumDiscount = maximumDiscount;
        this.termsAndConditions = termsAndConditions;
        this.status = status != null ? status : "PENDING";
        this.requestType = requestType != null ? requestType : "CAMPAIGN_REQUEST";
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public boolean isCurrentlyActive() {
        if (!"APPROVED".equalsIgnoreCase(status)) {
            return false;
        }
        try {
            LocalDate today = LocalDate.now();
            if (startDate != null && !startDate.trim().isEmpty()) {
                LocalDate start = LocalDate.parse(startDate.trim());
                if (today.isBefore(start)) {
                    return false;
                }
            }
            if (endDate != null && !endDate.trim().isEmpty()) {
                LocalDate end = LocalDate.parse(endDate.trim());
                if (today.isAfter(end)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setCurrentlyActive(boolean currentlyActive) {
        this.currentlyActive = currentlyActive;
    }

    public double calculateDiscountedPrice(double originalPrice) {
        if (originalPrice <= 0) {
            return 0;
        }
        if (discount <= 0) {
            return originalPrice;
        }

        double discountedPrice = originalPrice;
        if (discountType != null && discountType.toUpperCase().contains("PERCENT")) {
            double discountAmount = originalPrice * (discount / 100.0);
            if (maximumDiscount > 0 && discountAmount > maximumDiscount) {
                discountAmount = maximumDiscount;
            }
            discountedPrice = Math.max(0, originalPrice - discountAmount);
        } else if (discountType != null && discountType.toUpperCase().contains("FLAT")) {
            discountedPrice = Math.max(0, originalPrice - discount);
        }

        return Math.round(discountedPrice * 100.0) / 100.0;
    }

    // Getters and Setters

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getShopkeeperId() {
        return shopkeeperId;
    }

    public void setShopkeeperId(String shopkeeperId) {
        this.shopkeeperId = shopkeeperId;
    }

    public String getShopkeeperName() {
        return shopkeeperName;
    }

    public void setShopkeeperName(String shopkeeperName) {
        this.shopkeeperName = shopkeeperName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCampaignTitle() {
        return title;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.title = campaignTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCampaignDescription() {
        return description;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.description = campaignDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBanner() {
        return imageUrl;
    }

    public void setBanner(String banner) {
        this.imageUrl = banner;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public List<String> getApplicableProducts() {
        return applicableProducts;
    }

    public void setApplicableProducts(List<String> applicableProducts) {
        this.applicableProducts = applicableProducts != null ? applicableProducts : new ArrayList<>();
    }

    public String getApplicableCategories() {
        return applicableCategories;
    }

    public void setApplicableCategories(String applicableCategories) {
        this.applicableCategories = applicableCategories;
    }

    public double getMinimumOrderAmount() {
        return minimumOrderAmount;
    }

    public void setMinimumOrderAmount(double minimumOrderAmount) {
        this.minimumOrderAmount = minimumOrderAmount;
    }

    public double getMaximumDiscount() {
        return maximumDiscount;
    }

    public void setMaximumDiscount(double maximumDiscount) {
        this.maximumDiscount = maximumDiscount;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(String approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public String getRejectedAt() {
        return rejectedAt;
    }

    public void setRejectedAt(String rejectedAt) {
        this.rejectedAt = rejectedAt;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}

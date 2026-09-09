package com.kryox.model.Shopkeeper;

public class ShopkeeperModel {

    private String shopNameValue;
    private String ownerNameValue;
    private String mobileValue;
    private String panValue;
    private String gstValue;
    private String categoryValue;
    private String addressValue;
    private String stateValue;
    private String cityValue;
    private String pinValue;
    private String licenseValue;

    private String uniqueIdValue;
    private String shopkeeperUid;

    private String descriptionValue;

    private String shopLogoURL;
    private String shopPhotoURL;
    private String licenseDocumentURL;
    private String gstCertificateURL;

    private String role = "Shopkeeper";

    private boolean approved = false;

    /*
     * Possible values:
     *
     * PENDING_APPROVAL
     * APPROVED
     * REJECTED
     */
    private String status = "PENDING_APPROVAL";

    private String rejectionReason = "";

    // Subscription Plan
    private String planName = "Pro";
    private String planAmount = "₹49/mo";
    private String planBillingCycle = "Monthly";
    private String planRenewalDate = "Nov 15, 2026";
    private String planStatus = "ACTIVE";

    public ShopkeeperModel() {
        // Required for Firestore
    }

    public ShopkeeperModel(
            String shopNameValue,
            String ownerNameValue,
            String mobileValue,
            String panValue,
            String gstValue,
            String categoryValue,
            String addressValue,
            String stateValue,
            String cityValue,
            String pinValue,
            String licenseValue,
            String uniqueIdValue,
            String shopkeeperUid,
            String descriptionValue,
            String shopLogoURL,
            String shopPhotoURL,
            String licenseDocumentURL,
            String gstCertificateURL
    ) {

        this.shopNameValue = shopNameValue;
        this.ownerNameValue = ownerNameValue;
        this.mobileValue = mobileValue;
        this.panValue = panValue;
        this.gstValue = gstValue;
        this.categoryValue = categoryValue;
        this.addressValue = addressValue;
        this.stateValue = stateValue;
        this.cityValue = cityValue;
        this.pinValue = pinValue;
        this.licenseValue = licenseValue;
        this.uniqueIdValue = uniqueIdValue;
        this.shopkeeperUid = shopkeeperUid;
        this.descriptionValue = descriptionValue;
        this.shopLogoURL = shopLogoURL;
        this.shopPhotoURL = shopPhotoURL;
        this.licenseDocumentURL = licenseDocumentURL;
        this.gstCertificateURL = gstCertificateURL;

        this.role = "Shopkeeper";
        this.approved = false;
        this.status = "PENDING_APPROVAL";
        this.rejectionReason = "";
    }

    public String getShopNameValue() {
        return shopNameValue;
    }

    public void setShopNameValue(String shopNameValue) {
        this.shopNameValue = shopNameValue;
    }

    public String getOwnerNameValue() {
        return ownerNameValue;
    }

    public void setOwnerNameValue(String ownerNameValue) {
        this.ownerNameValue = ownerNameValue;
    }

    public String getMobileValue() {
        return mobileValue;
    }

    public void setMobileValue(String mobileValue) {
        this.mobileValue = mobileValue;
    }

    public String getPanValue() {
        return panValue;
    }

    public void setPanValue(String panValue) {
        this.panValue = panValue;
    }

    public String getGstValue() {
        return gstValue;
    }

    public void setGstValue(String gstValue) {
        this.gstValue = gstValue;
    }

    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public String getAddressValue() {
        return addressValue;
    }

    public void setAddressValue(String addressValue) {
        this.addressValue = addressValue;
    }

    public String getStateValue() {
        return stateValue;
    }

    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getCityValue() {
        return cityValue;
    }

    public void setCityValue(String cityValue) {
        this.cityValue = cityValue;
    }

    public String getPinValue() {
        return pinValue;
    }

    public void setPinValue(String pinValue) {
        this.pinValue = pinValue;
    }

    public String getLicenseValue() {
        return licenseValue;
    }

    public void setLicenseValue(String licenseValue) {
        this.licenseValue = licenseValue;
    }

    public String getUniqueIdValue() {
        return uniqueIdValue;
    }

    public void setUniqueIdValue(String uniqueIdValue) {
        this.uniqueIdValue = uniqueIdValue;
    }

    public String getShopkeeperUid() {
        return shopkeeperUid;
    }

    public void setShopkeeperUid(String shopkeeperUid) {
        this.shopkeeperUid = shopkeeperUid;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getShopLogoURL() {
        return shopLogoURL;
    }

    public void setShopLogoURL(String shopLogoURL) {
        this.shopLogoURL = shopLogoURL;
    }

    public String getShopPhotoURL() {
        return shopPhotoURL;
    }

    public void setShopPhotoURL(String shopPhotoURL) {
        this.shopPhotoURL = shopPhotoURL;
    }

    public String getLicenseDocumentURL() {
        return licenseDocumentURL;
    }

    public void setLicenseDocumentURL(String licenseDocumentURL) {
        this.licenseDocumentURL = licenseDocumentURL;
    }

    public String getGstCertificateURL() {
        return gstCertificateURL;
    }

    public void setGstCertificateURL(String gstCertificateURL) {
        this.gstCertificateURL = gstCertificateURL;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public boolean isPendingApproval() {
        return !approved
                && "PENDING_APPROVAL".equalsIgnoreCase(status);
    }

    public boolean isRejected() {
        return "REJECTED".equalsIgnoreCase(status);
    }

    public boolean isVerified() {
        return approved
                && "APPROVED".equalsIgnoreCase(status);
    }

    public String getPlanName() {
        return planName != null ? planName : "Pro";
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanAmount() {
        return planAmount != null ? planAmount : "₹49/mo";
    }

    public void setPlanAmount(String planAmount) {
        this.planAmount = planAmount;
    }

    public String getPlanBillingCycle() {
        return planBillingCycle != null ? planBillingCycle : "Monthly";
    }

    public void setPlanBillingCycle(String planBillingCycle) {
        this.planBillingCycle = planBillingCycle;
    }

    public String getPlanRenewalDate() {
        return planRenewalDate != null ? planRenewalDate : "Nov 15, 2026";
    }

    public void setPlanRenewalDate(String planRenewalDate) {
        this.planRenewalDate = planRenewalDate;
    }

    public String getPlanStatus() {
        return planStatus != null ? planStatus : "ACTIVE";
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    @Override
    public String toString() {
        return "ShopkeeperModel{" +
                "shopNameValue='" + shopNameValue + '\'' +
                ", ownerNameValue='" + ownerNameValue + '\'' +
                ", mobileValue='" + mobileValue + '\'' +
                ", shopkeeperUid='" + shopkeeperUid + '\'' +
                ", role='" + role + '\'' +
                ", approved=" + approved +
                ", status='" + status + '\'' +
                '}';
    }
}
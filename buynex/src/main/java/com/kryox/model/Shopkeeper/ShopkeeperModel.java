package com.kryox.model.Shopkeeper;

public class ShopkeeperModel {
    
    String shopNameValue;
    String ownerNameValue;
    String mobileValue;
    String panValue;
    String gstValue;
    String categoryValue;
    String addressValue;
    String stateValue;
    String cityValue;
    String pinValue;
    String licenseValue;
    String uniqueIdValue;
    String shopkeeperUid;
    String descriptionValue;
    String shopLogoURL;
    String shopPhotoURL;
    String licenseDocumentURL;
    String gstCertificateURL;
    String role = "Shopkeeper";
    boolean approved = false;

    public ShopkeeperModel() {
    }

    public ShopkeeperModel( String shopNameValue, String ownerNameValue, String mobileValue, String panValue, String gstValue,
            String categoryValue, String addressValue, String stateValue, String cityValue,
            String pinValue, String licenseValue, String uniqueIdValue,String shopkeeperUid,String descriptionValue,
            String shopLogoURL, String shopPhotoURL, String licenseDocumentURL, String gstCertificateURL
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
    }


    public String getOwnerNameValue() {
        return ownerNameValue;
    }

    public void setOwnerNameValue(String ownerNameValue) {
        this.ownerNameValue = ownerNameValue;
    }

    public String getShopNameValue() {
        return shopNameValue;
    }

    public void setShopNameValue(String shopNameValue) {
        this.shopNameValue = shopNameValue;
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

    public String getRole() {
        return role;
    }

    public boolean isApproved() {
        return approved;
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
}

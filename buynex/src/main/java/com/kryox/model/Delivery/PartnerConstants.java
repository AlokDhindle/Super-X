package com.kryox.model.Delivery;

import java.util.Map;

public class PartnerConstants {

    // Active Partner Details
    public static String UID = "";
    public static String FULL_NAME = "Partner";
    public static String EMAIL = "";
    public static String PHONE = "";
    public static String ADDRESS = "";
    public static String VEHICLE_TYPE = "Bike / Motorcycle";
    public static String VEHICLE_NUMBER = "";
    public static String PARTNER_TIER = "Standard Partner";
    public static String CITY = "Pune";
    public static String STATUS = "Active";
    public static String PROFILE_PHOTO_URL = ""; // Cloudinary Photo URL

    // Payout & Banking Details
    public static String BANK_NAME = "HDFC Bank";
    public static String ACCOUNT_NUMBER = "000000000000";
    public static String MASKED_ACCOUNT = "•••• •••• 0000";
    public static String IFSC_CODE = "HDFC0000123";
    public static String UPI_ID = "";

    public static void setLoggedInPartner(Map<String, Object> data) {
        if (data == null) return;

        FULL_NAME      = getStringOrDefault(data, "fullName", "Partner");
        EMAIL          = getStringOrDefault(data, "email", "");
        
        // Checks 'mobile' first (from registration), fallback to 'phone'
        PHONE          = getFirstAvailable(data, "mobile", "phone", "");
        
        ADDRESS        = getStringOrDefault(data, "address", "");
        VEHICLE_TYPE   = getStringOrDefault(data, "vehicleType", "Bike / Motorcycle");
        
        // Checks 'vehicleNumber' first (from registration), fallback to 'vehicleIdNumber'
        VEHICLE_NUMBER = getFirstAvailable(data, "vehicleNumber", "vehicleIdNumber", "");
        
        PARTNER_TIER   = getStringOrDefault(data, "partnerTier", "Standard Partner");
        CITY           = getStringOrDefault(data, "city", "Pune");
        STATUS         = getStringOrDefault(data, "status", "Active");
        PROFILE_PHOTO_URL = getStringOrDefault(data, "profilePhotoUrl", "");

        // Banking details
        BANK_NAME      = getStringOrDefault(data, "bankName", "HDFC Bank");
        ACCOUNT_NUMBER = getStringOrDefault(data, "accountNumber", "000000000000");
        IFSC_CODE      = getStringOrDefault(data, "ifscCode", "HDFC0000123");
        UPI_ID         = getStringOrDefault(data, "upiId", "");

        if (ACCOUNT_NUMBER.length() >= 4) {
            String last4 = ACCOUNT_NUMBER.substring(ACCOUNT_NUMBER.length() - 4);
            MASKED_ACCOUNT = "•••• •••• " + last4;
        } else {
            MASKED_ACCOUNT = "•••• •••• " + ACCOUNT_NUMBER;
        }
    }

    public static void clear() {
        UID            = "";
        FULL_NAME      = "Partner";
        EMAIL          = "";
        PHONE          = "";
        ADDRESS        = "";
        VEHICLE_TYPE   = "Bike / Motorcycle";
        VEHICLE_NUMBER = "";
        PARTNER_TIER   = "Standard Partner";
        CITY           = "Pune";
        STATUS         = "Active";
        PROFILE_PHOTO_URL = "";

        BANK_NAME      = "HDFC Bank";
        ACCOUNT_NUMBER = "000000000000";
        MASKED_ACCOUNT = "•••• •••• 0000";
        IFSC_CODE      = "HDFC0000123";
        UPI_ID         = "";
    }

    private static String getStringOrDefault(Map<String, Object> data, String key, String defaultValue) {
        Object val = data.get(key);
        return (val != null && !val.toString().trim().isEmpty()) ? val.toString().trim() : defaultValue;
    }

    private static String getFirstAvailable(Map<String, Object> data, String key1, String key2, String defaultValue) {
        Object val1 = data.get(key1);
        if (val1 != null && !val1.toString().trim().isEmpty()) return val1.toString().trim();
        Object val2 = data.get(key2);
        if (val2 != null && !val2.toString().trim().isEmpty()) return val2.toString().trim();
        return defaultValue;
    }
}
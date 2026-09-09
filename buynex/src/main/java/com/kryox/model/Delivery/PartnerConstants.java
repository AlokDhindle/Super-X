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
    public static String PROFILE_PHOTO_URL = "";

    // Document URLs (Cloudinary)
    public static String ID_CARD_URL = "";
    public static String LICENSE_DOC_URL = "";
    public static String RC_BOOK_URL = "";

    // Dynamic Document Compliance & Approval Statuses
    public static String LICENSE_STATUS = "Pending Approval";
    public static String GOVERNMENT_ID_STATUS = "Pending Approval";
    public static String RC_BOOK_STATUS = "Pending Approval";
    public static String INSURANCE_STATUS = "Pending Approval";
    public static boolean IS_ADMIN_APPROVED = false;

    // Performance & Ratings Metrics
    public static double RATING_SCORE = 5.0;
    public static String RATING_QUOTE = "\"Fast and always polite! Great service.\"";
    public static int TOTAL_DELIVERIES = 0;
    public static double COMPLETION_RATE = 100.0;

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
        PHONE          = getFirstAvailable(data, "mobile", "phone", "");
        ADDRESS        = getStringOrDefault(data, "address", "");
        VEHICLE_TYPE   = getStringOrDefault(data, "vehicleType", "Bike / Motorcycle");
        VEHICLE_NUMBER = getFirstAvailable(data, "vehicleNumber", "vehicleIdNumber", "");
        PARTNER_TIER   = getStringOrDefault(data, "partnerTier", "Standard Partner");
        CITY           = getStringOrDefault(data, "city", "Pune");
        STATUS         = getStringOrDefault(data, "status", "Active");
        
        PROFILE_PHOTO_URL = getFirstAvailable(data, "profilePhotoUrl", "profilePhotoPath", "");
        ID_CARD_URL       = getFirstAvailable(data, "idCardUrl", "idCardPath", "");
        LICENSE_DOC_URL   = getFirstAvailable(data, "licenseDocUrl", "licenseDocPath", "");
        RC_BOOK_URL       = getFirstAvailable(data, "rcBookUrl", "rcBookPath", "");

        // Dynamic Document Verification Statuses
        LICENSE_STATUS       = getStringOrDefault(data, "licenseStatus", "Pending Approval");
        GOVERNMENT_ID_STATUS = getFirstAvailable(data, "governmentIdStatus", "idCardStatus", "Pending Approval");
        RC_BOOK_STATUS       = getStringOrDefault(data, "rcBookStatus", "Pending Approval");
        INSURANCE_STATUS     = getStringOrDefault(data, "insuranceStatus", "Pending Approval");
        
        Object approvedObj = data.get("isAdminApproved");
        if (approvedObj instanceof Boolean) {
            IS_ADMIN_APPROVED = (Boolean) approvedObj;
        } else if (approvedObj != null) {
            IS_ADMIN_APPROVED = Boolean.parseBoolean(approvedObj.toString());
        } else {
            IS_ADMIN_APPROVED = false;
        }

        // Performance & Ratings Metrics
        RATING_SCORE       = getDoubleOrDefault(data, "ratingScore", 5.0);
        RATING_QUOTE       = getStringOrDefault(data, "ratingQuote", "\"Fast and always polite! Great service.\"");
        TOTAL_DELIVERIES   = getIntOrDefault(data, "totalDeliveries", 0);
        COMPLETION_RATE    = getDoubleOrDefault(data, "completionRate", 100.0);

        // Banking Details
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
        UID               = "";
        FULL_NAME         = "Partner";
        EMAIL             = "";
        PHONE             = "";
        ADDRESS           = "";
        VEHICLE_TYPE      = "Bike / Motorcycle";
        VEHICLE_NUMBER    = "";
        PARTNER_TIER      = "Standard Partner";
        CITY              = "Pune";
        STATUS            = "Active";
        PROFILE_PHOTO_URL = "";

        ID_CARD_URL       = "";
        LICENSE_DOC_URL   = "";
        RC_BOOK_URL       = "";

        LICENSE_STATUS       = "Pending Approval";
        GOVERNMENT_ID_STATUS = "Pending Approval";
        RC_BOOK_STATUS       = "Pending Approval";
        INSURANCE_STATUS     = "Pending Approval";
        IS_ADMIN_APPROVED    = false;

        RATING_SCORE       = 5.0;
        RATING_QUOTE       = "\"Fast and always polite! Great service.\"";
        TOTAL_DELIVERIES   = 0;
        COMPLETION_RATE    = 100.0;

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

    private static String getFirstAvailable(Map<String, Object> data, String key1, String key2, String key3, String defaultValue) {
        Object val1 = data.get(key1);
        if (val1 != null && !val1.toString().trim().isEmpty()) return val1.toString().trim();
        Object val2 = data.get(key2);
        if (val2 != null && !val2.toString().trim().isEmpty()) return val2.toString().trim();
        Object val3 = data.get(key3);
        if (val3 != null && !val3.toString().trim().isEmpty()) return val3.toString().trim();
        return defaultValue;
    }

    private static double getDoubleOrDefault(Map<String, Object> data, String key, double defaultValue) {
        Object val = data.get(key);
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        } else if (val != null) {
            try {
                return Double.parseDouble(val.toString());
            } catch (NumberFormatException ignored) {}
        }
        return defaultValue;
    }

    private static int getIntOrDefault(Map<String, Object> data, String key, int defaultValue) {
        Object val = data.get(key);
        if (val instanceof Number) {
            return ((Number) val).intValue();
        } else if (val != null) {
            try {
                return Integer.parseInt(val.toString());
            } catch (NumberFormatException ignored) {}
        }
        return defaultValue;
    }
}
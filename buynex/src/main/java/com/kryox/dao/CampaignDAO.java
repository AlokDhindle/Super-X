package com.kryox.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.CampaignModel;
import com.kryox.model.NotificationModel;

public class CampaignDAO {

    private static final String CAMPAIGNS_COLLECTION = "Campaigns";
    private static final String NOTIFICATIONS_COLLECTION = "Notifications";

    // In-memory cache to guarantee real-time reactivity and local fallback
    private static final List<CampaignModel> memoryCache = new CopyOnWriteArrayList<>();
    private static final List<NotificationModel> notificationCache = new CopyOnWriteArrayList<>();
    private static boolean seededDefaults = false;

    private Firestore getDb() {
        try {
            return Firebaseconfig.gFirestore();
        } catch (Exception e) {
            System.err.println("Firebase Firestore connection error in CampaignDAO: " + e.getMessage());
            return null;
        }
    }

    public CampaignDAO() {
        ensureSeededDefaults();
    }

    private synchronized void ensureSeededDefaults() {
        if (!seededDefaults && memoryCache.isEmpty()) {
            seededDefaults = true;
            // Seed a sample approved campaign so customer portal has immediate active campaign preview if DB is empty
            LocalDate today = LocalDate.now();
            String start = today.minusDays(2).toString();
            String end = today.plusDays(15).toString();

            CampaignModel sample = new CampaignModel(
                    "camp_diwali_mega_sale",
                    "shop_demo_1",
                    "ABC Grocery Store",
                    "ABC Grocery Store",
                    "Diwali Mega Sale",
                    "Celebrate festival of lights with 20% off across all store groceries and festive snack packs!",
                    "/assets/images/image.png",
                    start,
                    end,
                    20.0,
                    "PERCENTAGE",
                    new ArrayList<>(),
                    "Groceries",
                    200.0,
                    500.0,
                    "1. Valid on selected products.\n2. Maximum discount ₹500.\n3. Applicable while stocks last.",
                    "APPROVED",
                    "CAMPAIGN_REQUEST",
                    today.minusDays(3).toString(),
                    today.minusDays(2).toString()
            );
            sample.setApprovedBy("Admin");
            sample.setApprovedAt(today.minusDays(2).toString());
            memoryCache.add(sample);
        }
    }

    public boolean createCampaignRequest(CampaignModel campaign) {
        if (campaign == null) {
            return false;
        }

        if (campaign.getCampaignId() == null || campaign.getCampaignId().trim().isEmpty()) {
            campaign.setCampaignId("CAMP_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        campaign.setCreatedAt(now);
        campaign.setUpdatedAt(now);
        campaign.setStatus("PENDING");
        campaign.setRequestType("CAMPAIGN_REQUEST");

        // Add to local cache immediately
        memoryCache.removeIf(c -> c.getCampaignId().equals(campaign.getCampaignId()));
        memoryCache.add(0, campaign);

        // Persist to Firestore
        try {
            Firestore db = getDb();
            if (db != null) {
                db.collection(CAMPAIGNS_COLLECTION)
                        .document(campaign.getCampaignId())
                        .set(campaign)
                        .get();
                System.out.println("✓ Campaign request saved to Firestore: " + campaign.getCampaignId());
            }
            return true;
        } catch (Exception e) {
            System.err.println("Firestore write error in createCampaignRequest: " + e.getMessage());
            return true; // Still true because memoryCache has it
        }
    }

    public List<CampaignModel> getAllCampaignRequests() {
        List<CampaignModel> list = new ArrayList<>();
        try {
            Firestore db = getDb();
            if (db != null) {
                QuerySnapshot snapshot = db.collection(CAMPAIGNS_COLLECTION).get().get();
                for (DocumentSnapshot doc : snapshot.getDocuments()) {
                    CampaignModel cm = doc.toObject(CampaignModel.class);
                    if (cm != null) {
                        list.add(cm);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Firestore read error in getAllCampaignRequests: " + e.getMessage());
        }

        if (list.isEmpty()) {
            return new ArrayList<>(memoryCache);
        }

        // Merge Firestore results with local memory
        for (CampaignModel cm : list) {
            if (cm != null && cm.getCampaignId() != null) {
                memoryCache.removeIf(existing -> existing.getCampaignId().equals(cm.getCampaignId()));
                memoryCache.add(cm);
            }
        }

        return new ArrayList<>(memoryCache);
    }

    public List<CampaignModel> getPendingCampaigns() {
        List<CampaignModel> all = getAllCampaignRequests();
        List<CampaignModel> pending = new ArrayList<>();
        for (CampaignModel c : all) {
            if ("PENDING".equalsIgnoreCase(c.getStatus())) {
                pending.add(c);
            }
        }
        return pending;
    }

    public List<CampaignModel> getApprovedActiveCampaigns() {
        List<CampaignModel> all = getAllCampaignRequests();
        List<CampaignModel> active = new ArrayList<>();
        for (CampaignModel c : all) {
            if (c != null && c.isCurrentlyActive()) {
                active.add(c);
            }
        }
        return active;
    }

    public List<CampaignModel> getCampaignsForShopkeeper(String shopkeeperId) {
        List<CampaignModel> all = getAllCampaignRequests();
        List<CampaignModel> mine = new ArrayList<>();
        for (CampaignModel c : all) {
            if (c != null) {
                if (shopkeeperId == null || shopkeeperId.trim().isEmpty()) {
                    mine.add(c);
                } else if (shopkeeperId.equalsIgnoreCase(c.getShopkeeperId())) {
                    mine.add(c);
                }
            }
        }
        return mine;
    }

    public CampaignModel getCampaignById(String campaignId) {
        if (campaignId == null || campaignId.trim().isEmpty()) {
            return null;
        }
        for (CampaignModel c : memoryCache) {
            if (campaignId.equalsIgnoreCase(c.getCampaignId())) {
                return c;
            }
        }
        try {
            Firestore db = getDb();
            if (db != null) {
                DocumentSnapshot doc = db.collection(CAMPAIGNS_COLLECTION).document(campaignId).get().get();
                if (doc.exists()) {
                    return doc.toObject(CampaignModel.class);
                }
            }
        } catch (Exception e) {
            System.err.println("Firestore get error: " + e.getMessage());
        }
        return null;
    }

    public boolean approveCampaign(String campaignId, String adminId) {
        if (campaignId == null) {
            return false;
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        CampaignModel target = null;

        for (CampaignModel c : memoryCache) {
            if (campaignId.equalsIgnoreCase(c.getCampaignId())) {
                c.setStatus("APPROVED");
                c.setApprovedBy(adminId != null ? adminId : "Admin");
                c.setApprovedAt(now);
                c.setUpdatedAt(now);
                target = c;
                break;
            }
        }

        try {
            Firestore db = getDb();
            if (db != null) {
                Map<String, Object> updates = new HashMap<>();
                updates.put("status", "APPROVED");
                updates.put("approvedBy", adminId != null ? adminId : "Admin");
                updates.put("approvedAt", now);
                updates.put("updatedAt", now);

                db.collection(CAMPAIGNS_COLLECTION)
                        .document(campaignId)
                        .update(updates)
                        .get();
            }
        } catch (Exception e) {
            System.err.println("Firestore approve update error: " + e.getMessage());
        }

        // Send Shopkeeper Notification
        if (target != null) {
            String shopId = target.getShopkeeperId() != null ? target.getShopkeeperId() : "all_shopkeepers";
            createNotification(
                    shopId,
                    "SHOPKEEPER",
                    target.getCampaignId(),
                    "CAMPAIGN_APPROVED",
                    "Campaign Approved",
                    "Your campaign '" + target.getTitle() + "' has been approved by BuyNex Admin."
            );

            // Send Customer Live Notification
            createNotification(
                    "all_customers",
                    "CUSTOMER",
                    target.getCampaignId(),
                    "CAMPAIGN_LIVE",
                    "🔔 New Campaign Available",
                    "'" + target.getTitle() + "' is now live on BuyNex! Get "
                            + (int) target.getDiscount() + "% OFF on selected products."
            );
        }

        return true;
    }

    public boolean rejectCampaign(String campaignId, String adminId, String reason) {
        if (campaignId == null) {
            return false;
        }

        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        CampaignModel target = null;

        for (CampaignModel c : memoryCache) {
            if (campaignId.equalsIgnoreCase(c.getCampaignId())) {
                c.setStatus("REJECTED");
                c.setRejectedBy(adminId != null ? adminId : "Admin");
                c.setRejectedAt(now);
                c.setRejectionReason(reason);
                c.setUpdatedAt(now);
                target = c;
                break;
            }
        }

        try {
            Firestore db = getDb();
            if (db != null) {
                Map<String, Object> updates = new HashMap<>();
                updates.put("status", "REJECTED");
                updates.put("rejectedBy", adminId != null ? adminId : "Admin");
                updates.put("rejectedAt", now);
                updates.put("rejectionReason", reason != null ? reason : "Does not meet current campaign guidelines.");
                updates.put("updatedAt", now);

                db.collection(CAMPAIGNS_COLLECTION)
                        .document(campaignId)
                        .update(updates)
                        .get();
            }
        } catch (Exception e) {
            System.err.println("Firestore reject update error: " + e.getMessage());
        }

        // Send Shopkeeper Rejection Notification
        if (target != null) {
            String shopId = target.getShopkeeperId() != null ? target.getShopkeeperId() : "all_shopkeepers";
            String reasonText = (reason != null && !reason.trim().isEmpty()) ? "\nReason: " + reason : "";
            createNotification(
                    shopId,
                    "SHOPKEEPER",
                    target.getCampaignId(),
                    "CAMPAIGN_REJECTED",
                    "Campaign Rejected",
                    "Your campaign '" + target.getTitle() + "' was rejected by BuyNex Admin." + reasonText
            );
        }

        return true;
    }

    public void createNotification(
            String recipientId,
            String recipientRole,
            String campaignId,
            String type,
            String title,
            String message
    ) {
        String notifId = "NOTIF_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        NotificationModel notif = new NotificationModel(
                notifId,
                recipientId,
                recipientRole,
                campaignId,
                type,
                title,
                message,
                false,
                now
        );

        notificationCache.add(0, notif);

        try {
            Firestore db = getDb();
            if (db != null) {
                db.collection(NOTIFICATIONS_COLLECTION)
                        .document(notifId)
                        .set(notif);
            }
        } catch (Exception e) {
            System.err.println("Firestore notification error: " + e.getMessage());
        }
    }

    public List<NotificationModel> getNotificationsForRecipient(String recipientId, String role) {
        List<NotificationModel> result = new ArrayList<>();
        for (NotificationModel n : notificationCache) {
            if (n != null) {
                if (recipientId != null && recipientId.equalsIgnoreCase(n.getRecipientId())) {
                    result.add(n);
                } else if (role != null && role.equalsIgnoreCase(n.getRecipientRole())) {
                    result.add(n);
                } else if ("all_customers".equalsIgnoreCase(n.getRecipientId()) && "CUSTOMER".equalsIgnoreCase(role)) {
                    result.add(n);
                } else if ("all_shopkeepers".equalsIgnoreCase(n.getRecipientId()) && "SHOPKEEPER".equalsIgnoreCase(role)) {
                    result.add(n);
                }
            }
        }
        return result;
    }

    public boolean deleteCampaign(String campaignId) {
        if (campaignId == null || campaignId.trim().isEmpty()) {
            return false;
        }
        memoryCache.removeIf(c -> campaignId.equalsIgnoreCase(c.getCampaignId()));
        try {
            Firestore db = getDb();
            if (db != null) {
                db.collection(CAMPAIGNS_COLLECTION)
                        .document(campaignId)
                        .delete()
                        .get();
                System.out.println("✓ Campaign deleted from Firestore: " + campaignId);
            }
            return true;
        } catch (Exception e) {
            System.err.println("Firestore delete error in deleteCampaign: " + e.getMessage());
            return true;
        }
    }
}


package com.kryox.controller;

import java.util.List;
import java.util.function.Consumer;

import com.kryox.dao.CampaignDAO;
import com.kryox.model.CampaignModel;
import com.kryox.model.NotificationModel;

import javafx.application.Platform;

public class CampaignController {

    private static final CampaignDAO dao = new CampaignDAO();

    public static boolean submitCampaignRequest(CampaignModel campaign) {
        return dao.createCampaignRequest(campaign);
    }

    public static void submitCampaignRequestAsync(CampaignModel campaign, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean success = dao.createCampaignRequest(campaign);
            if (callback != null) {
                Platform.runLater(() -> callback.accept(success));
            }
        }).start();
    }

    public static List<CampaignModel> getAllCampaignRequests() {
        return dao.getAllCampaignRequests();
    }

    public static void getAllCampaignRequestsAsync(Consumer<List<CampaignModel>> callback) {
        new Thread(() -> {
            List<CampaignModel> list = dao.getAllCampaignRequests();
            if (callback != null) {
                Platform.runLater(() -> callback.accept(list));
            }
        }).start();
    }

    public static List<CampaignModel> getPendingCampaigns() {
        return dao.getPendingCampaigns();
    }

    public static int getPendingCount() {
        return dao.getPendingCampaigns().size();
    }

    public static List<CampaignModel> getApprovedActiveCampaigns() {
        return dao.getApprovedActiveCampaigns();
    }

    public static void getApprovedActiveCampaignsAsync(Consumer<List<CampaignModel>> callback) {
        new Thread(() -> {
            List<CampaignModel> list = dao.getApprovedActiveCampaigns();
            if (callback != null) {
                Platform.runLater(() -> callback.accept(list));
            }
        }).start();
    }

    public static List<CampaignModel> getCampaignsForShopkeeper(String shopkeeperId) {
        return dao.getCampaignsForShopkeeper(shopkeeperId);
    }

    public static CampaignModel getCampaignById(String campaignId) {
        return dao.getCampaignById(campaignId);
    }

    public static boolean approveCampaign(String campaignId, String adminId) {
        return dao.approveCampaign(campaignId, adminId);
    }

    public static void approveCampaignAsync(String campaignId, String adminId, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean res = dao.approveCampaign(campaignId, adminId);
            if (callback != null) {
                Platform.runLater(() -> callback.accept(res));
            }
        }).start();
    }

    public static boolean rejectCampaign(String campaignId, String adminId, String reason) {
        return dao.rejectCampaign(campaignId, adminId, reason);
    }

    public static void rejectCampaignAsync(String campaignId, String adminId, String reason, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean res = dao.rejectCampaign(campaignId, adminId, reason);
            if (callback != null) {
                Platform.runLater(() -> callback.accept(res));
            }
        }).start();
    }

    public static List<NotificationModel> getNotificationsForRecipient(String recipientId, String role) {
        return dao.getNotificationsForRecipient(recipientId, role);
    }

    public static boolean deleteCampaign(String campaignId) {
        return dao.deleteCampaign(campaignId);
    }

    public static void deleteCampaignAsync(String campaignId, Consumer<Boolean> callback) {
        new Thread(() -> {
            boolean res = dao.deleteCampaign(campaignId);
            if (callback != null) {
                Platform.runLater(() -> callback.accept(res));
            }
        }).start();
    }
}


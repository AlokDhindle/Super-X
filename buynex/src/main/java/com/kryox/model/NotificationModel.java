package com.kryox.model;

import java.io.Serializable;

public class NotificationModel implements Serializable {

    private String notificationId;
    private String recipientId;
    private String recipientRole; // "SHOPKEEPER", "CUSTOMER", "ADMIN", "ALL"
    private String campaignId;
    private String type;          // "CAMPAIGN_APPROVED", "CAMPAIGN_REJECTED", "CAMPAIGN_LIVE"
    private String title;
    private String message;
    private boolean isRead;
    private String createdAt;

    public NotificationModel() {
        this.isRead = false;
    }

    public NotificationModel(
            String notificationId,
            String recipientId,
            String recipientRole,
            String campaignId,
            String type,
            String title,
            String message,
            boolean isRead,
            String createdAt
    ) {
        this.notificationId = notificationId;
        this.recipientId = recipientId;
        this.recipientRole = recipientRole;
        this.campaignId = campaignId;
        this.type = type;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientRole() {
        return recipientRole;
    }

    public void setRecipientRole(String recipientRole) {
        this.recipientRole = recipientRole;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}

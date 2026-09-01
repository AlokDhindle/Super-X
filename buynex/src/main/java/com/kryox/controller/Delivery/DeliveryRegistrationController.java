package com.kryox.controller.Delivery;

import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.model.Delivery.DeliveryPartner;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Delivery.RegistrationSuccess;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class DeliveryRegistrationController {

    private final DeliveryPartnerDAO partnerDAO = new DeliveryPartnerDAO();

    public void handleRegistration(DeliveryPartner partner, String password, String confirmPassword, boolean termsAccepted) {
        // 1. Basic Field Validations
        if (partner.getEmail() == null || partner.getEmail().trim().isEmpty() || password == null || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please provide a valid email and password.");
            return;
        }

        if (password.length() < 8) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 8 characters long.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Passwords do not match.");
            return;
        }

        if (!termsAccepted) {
            showAlert(Alert.AlertType.WARNING, "Terms & Conditions", "Please accept the Terms & Conditions to proceed.");
            return;
        }

        // 2. Register with Firebase Auth & Save to Firestore
        partnerDAO.registerPartnerWithAuth(partner, password)
            .thenAccept(uid -> Platform.runLater(() -> {
                // Synchronize global in-memory session cache
                PartnerConstants.UID = uid;
                PartnerConstants.FULL_NAME = partner.getFullName();
                PartnerConstants.EMAIL = partner.getEmail();
                PartnerConstants.PHONE = partner.getMobile();
                PartnerConstants.VEHICLE_TYPE = partner.getVehicleType();
                PartnerConstants.VEHICLE_NUMBER = partner.getVehicleNumber();
                
                if (partner.getProfilePhotoPath() != null && !partner.getProfilePhotoPath().isEmpty()) {
                    PartnerConstants.PROFILE_PHOTO_URL = partner.getProfilePhotoPath();
                }

                // Navigate to Success Screen safely
                if (Homepage.HomepageStage != null) {
                    Homepage.HomepageStage.setScene(RegistrationSuccess.registrationSuccessScene());
                }
            }))
            .exceptionally(ex -> {
                Platform.runLater(() -> {
                    String errorMsg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                    showAlert(Alert.AlertType.ERROR, "Registration Error", errorMsg != null ? errorMsg : "Registration failed.");
                });
                return null;
            });
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
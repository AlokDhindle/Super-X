package com.kryox.controller.Delivery;

import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.model.Delivery.DeliveryPartner;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Delivery.RegistrationSuccess;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.Scene;

public class DeliveryRegistrationController {

    private final DeliveryPartnerDAO partnerDAO = new DeliveryPartnerDAO();

    public void handleRegistration(DeliveryPartner partner, String password, String confirmPassword, boolean termsAccepted, Scene scene) {
        if (!termsAccepted) {
            showAlert(Alert.AlertType.WARNING, "Terms & Conditions", "Please accept the terms & conditions to proceed.");
            return;
        }

        if (partner.getFullName() == null || partner.getFullName().trim().isEmpty() ||
            partner.getMobile() == null || partner.getMobile().trim().isEmpty() ||
            partner.getEmail() == null || partner.getEmail().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Details", "Please fill in all mandatory fields (Full Name, Mobile, Email).");
            return;
        }

        // Vehicle Details Validation
        if (partner.getVehicleNumber() == null || partner.getVehicleNumber().trim().isEmpty() ||
            partner.getDrivingLicense() == null || partner.getDrivingLicense().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Vehicle Details Missing", "Please provide your Vehicle Registration Number and Driving License Number.");
            return;
        }

        // Document Upload Verification
        if (partner.getIdCardPath() == null || partner.getIdCardPath().trim().isEmpty() ||
            partner.getLicenseDocPath() == null || partner.getLicenseDocPath().trim().isEmpty() ||
            partner.getRcBookPath() == null || partner.getRcBookPath().trim().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Documents Incomplete", "Please upload your Government ID, Driving License, and Vehicle RC Book for admin verification.");
            return;
        }

        // Password Validation
        if (password == null || password.length() < 8) {
            showAlert(Alert.AlertType.WARNING, "Weak Password", "Password must be at least 8 characters long.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.WARNING, "Password Mismatch", "Passwords do not match. Please re-enter.");
            return;
        }

        // Execute registration via DAO
        partnerDAO.registerPartnerWithAuth(partner, password)
                .thenAccept(uid -> Platform.runLater(() -> {
                    // Sync active registered session variables
                    PartnerConstants.UID = uid;
                    PartnerConstants.FULL_NAME = partner.getFullName();
                    PartnerConstants.EMAIL = partner.getEmail();
                    PartnerConstants.PHONE = partner.getMobile();
                    PartnerConstants.VEHICLE_TYPE = partner.getVehicleType();
                    PartnerConstants.VEHICLE_NUMBER = partner.getVehicleNumber();
                    PartnerConstants.PARTNER_TIER = "Standard Partner";
                    
                    if (partner.getProfilePhotoPath() != null && !partner.getProfilePhotoPath().isEmpty()) {
                        PartnerConstants.PROFILE_PHOTO_URL = partner.getProfilePhotoPath();
                    }

                    RegistrationSuccess.show(scene);
                }))
                .exceptionally(ex -> {
                    Platform.runLater(() -> {
                        String errorMsg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                        showAlert(Alert.AlertType.ERROR, "Registration Error", errorMsg);
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
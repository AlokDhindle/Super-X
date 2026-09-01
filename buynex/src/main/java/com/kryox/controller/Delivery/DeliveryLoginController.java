
package com.kryox.controller.Delivery;

import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Delivery.PartnerDashboard;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class DeliveryLoginController {

    private final DeliveryPartnerDAO partnerDAO = new DeliveryPartnerDAO();

    public void handleLogin(String identifier, String password) {

        if (identifier == null || identifier.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Login Error",
                    "Please enter your Phone Number/Email and Password."
            );
            return;
        }

        String input = identifier.trim();

        if (input.contains("@")) {

            partnerDAO.authenticatePartner(input, password)
                    .thenAccept(partner -> Platform.runLater(() -> {

                        if (partner != null) {

                            PartnerConstants.UID =
                                    partner.getId() != null
                                            ? partner.getId()
                                            : "";

                            PartnerConstants.FULL_NAME =
                                    partner.getFullName() != null &&
                                    !partner.getFullName().isEmpty()
                                            ? partner.getFullName()
                                            : "Partner";

                            PartnerConstants.EMAIL =
                                    partner.getEmail() != null
                                            ? partner.getEmail()
                                            : input;

                            PartnerConstants.PHONE =
                                    partner.getMobile() != null
                                            ? partner.getMobile()
                                            : "";

                            PartnerConstants.VEHICLE_TYPE =
                                    partner.getVehicleType() != null
                                            ? partner.getVehicleType()
                                            : "Bike / Motorcycle";

                            PartnerConstants.VEHICLE_NUMBER =
                                    partner.getVehicleNumber() != null
                                            ? partner.getVehicleNumber()
                                            : "";

                            PartnerConstants.BANK_NAME =
                                    partner.getBankName() != null
                                            ? partner.getBankName()
                                            : "HDFC Bank";

                            PartnerConstants.ACCOUNT_NUMBER =
                                    partner.getAccountNumber() != null
                                            ? partner.getAccountNumber()
                                            : "000000000000";

                            PartnerConstants.IFSC_CODE =
                                    partner.getIfscCode() != null
                                            ? partner.getIfscCode()
                                            : "HDFC0000123";

                            if (partner.getProfilePhotoPath() != null &&
                                    !partner.getProfilePhotoPath().isEmpty()) {

                                PartnerConstants.PROFILE_PHOTO_URL =
                                        partner.getProfilePhotoPath();
                            }

                            if (PartnerConstants.ACCOUNT_NUMBER.length() >= 4) {

                                PartnerConstants.MASKED_ACCOUNT =
                                        "•••• •••• " +
                                        PartnerConstants.ACCOUNT_NUMBER.substring(
                                                PartnerConstants.ACCOUNT_NUMBER.length() - 4
                                        );

                            } else {

                                PartnerConstants.MASKED_ACCOUNT =
                                        "•••• •••• " +
                                        PartnerConstants.ACCOUNT_NUMBER;
                            }

                            if (Homepage.HomepageStage != null) {
                                Homepage.HomepageStage.setScene(
                                        PartnerDashboard.partnerDashboardScene()
                                );
                            }

                        } else {

                            showAlert(
                                    Alert.AlertType.ERROR,
                                    "Authentication Failed",
                                    "Incorrect email or password."
                            );
                        }
                    }))
                    .exceptionally(ex -> {

                        Platform.runLater(() ->
                                showAlert(
                                        Alert.AlertType.ERROR,
                                        "Authentication Failed",
                                        "Incorrect email or password."
                                )
                        );

                        return null;
                    });

        } else {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Login Error",
                    "Please enter a valid email address."
            );
        }
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}


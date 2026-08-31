package com.kryox.controller.Delivery;

import com.kryox.dao.Delivery.DeliveryPartnerDAO;
import com.kryox.model.Delivery.PartnerConstants;
import com.kryox.view.Customer.Homepage;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DeliveryLoginController {

    private final DeliveryPartnerDAO partnerDAO =
            new DeliveryPartnerDAO();

    public void handleLogin(
            String identifier,
            String password,
            Stage primaryStage
    ) {

        if (identifier == null ||
                identifier.trim().isEmpty() ||
                password == null ||
                password.trim().isEmpty()) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Login Error",
                    "Please enter your Email and Password."
            );

            return;
        }

        String input =
                identifier.trim();

        if (!input.contains("@")) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Email Required",
                    "Please login using your registered email address."
            );

            return;
        }

        partnerDAO.authenticatePartner(
                input,
                password
        )
                .thenAccept(partner ->
                        Platform.runLater(() -> {

                            if (partner == null) {

                                showAlert(
                                        Alert.AlertType.ERROR,
                                        "Authentication Failed",
                                        "Unable to load partner profile."
                                );

                                return;
                            }

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

                            PartnerConstants.ADDRESS =
                                    partner.getAddress() != null
                                            ? partner.getAddress()
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
                                            : "";

                            PartnerConstants.ACCOUNT_NUMBER =
                                    partner.getAccountNumber() != null
                                            ? partner.getAccountNumber()
                                            : "";

                            PartnerConstants.IFSC_CODE =
                                    partner.getIfscCode() != null
                                            ? partner.getIfscCode()
                                            : "";

                            PartnerConstants.STATUS =
                                    partner.getStatus() != null
                                            ? partner.getStatus()
                                            : "APPROVED";

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

                            Stage activeStage =
                                    primaryStage != null
                                            ? primaryStage
                                            : Homepage.HomepageStage;

                            openDashboard(activeStage);
                        })
                )
                .exceptionally(ex -> {

                    Platform.runLater(() -> {

                        Throwable cause =
                                ex.getCause() != null
                                        ? ex.getCause()
                                        : ex;

                        String message =
                                cause.getMessage();

                        if (message == null ||
                                message.isBlank()) {

                            message =
                                    "Login failed.";
                        }

                        showAlert(
                                Alert.AlertType.WARNING,
                                "Login Failed",
                                message
                        );
                    });

                    return null;
                });
    }

    private void openDashboard(
            Stage stage
    ) {

        if (stage == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Dashboard Error",
                    "Application stage not found."
            );

            return;
        }

        try {

            Class<?> dashboardClass =
                    Class.forName(
                            "com.kryox.view.Delivery.PartnerDashboard"
                    );

            dashboardClass
                    .getMethod(
                            "show",
                            Stage.class
                    )
                    .invoke(
                            null,
                            stage
                    );

        } catch (ClassNotFoundException e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Dashboard Error",
                    "PartnerDashboard.java file not found."
            );

        } catch (Exception e) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Dashboard Error",
                    "Unable to open Delivery Partner Dashboard."
            );

            e.printStackTrace();
        }
    }

    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
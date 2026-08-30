package com.kryox.dao.Admin;

import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.model.Admin.Admin;

public class AdminDao {

    private final ControllerFirebase firebase =
            new ControllerFirebase();

    public boolean registerAdmin(Admin admin) {

        String idToken =
                firebase.signUp(
                        admin.getEmail(),
                        admin.getPassword()
                );

        if (idToken == null) {

            System.out.println("Firebase Authentication failed");

            return false;
        }

        boolean stored =
                firebase.saveAdminData(
                        admin.getEmployeeId(),
                        admin.getFullName(),
                        admin.getUsername(),
                        admin.getEmail(),
                        admin.getMobile(),
                        admin.getRole(),
                        admin.getAccessCode(),
                        idToken
                );

        if (stored) {

            System.out.println(
                    "Admin registration completed successfully"
            );

            return true;
        }

        System.out.println(
                "Authentication successful but Firestore save failed"
        );

        return false;
    }
}
package com.kryox.dao.Admin;

import org.json.JSONObject;

import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.model.Admin.Admin;

public class AdminDao {

    private final ControllerFirebase firebase =
            new ControllerFirebase();

    public boolean registerAdmin(Admin admin) {

        JSONObject authResult =
                firebase.signUpAdmin(
                        admin.getEmail(),
                        admin.getPassword()
                );

        if (authResult == null) {

            System.out.println(
                    "Firebase Authentication failed"
            );

            return false;
        }

        String uid =
                authResult.getString(
                        "localId"
                );

        String idToken =
                authResult.getString(
                        "idToken"
                );

        boolean stored =
                firebase.saveAdminData(
                        uid,
                        admin.getEmployeeId(),
                        admin.getFullName(),
                        admin.getUsername(),
                        admin.getEmail(),
                        admin.getMobile(),
                        "Admin",
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
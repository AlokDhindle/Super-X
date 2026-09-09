package com.kryox.dao.Admin;

import org.json.JSONObject;

import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.model.Admin.Admin;

public class AdminDao {

    private final ControllerFirebase firebase =
            new ControllerFirebase();

    public boolean registerAdmin(Admin admin) {

        try {

            if (admin == null) {

                System.out.println(
                        "Admin data is null"
                );

                return false;
            }

            JSONObject authResult =
                    firebase.signUpAdmin(
                            admin.getEmail(),
                            admin.getPassword()
                    );

            // Authentication failed
            if (authResult == null) {

                System.out.println(
                        "Firebase Authentication failed"
                );

                return false;
            }

            String uid =
                    authResult.optString(
                            "localId",
                            ""
                    );

            String idToken =
                    authResult.optString(
                            "idToken",
                            ""
                    );

            if (uid.isBlank()) {

                System.out.println(
                        "Firebase UID not received"
                );

                return false;
            }

            if (idToken.isBlank()) {

                System.out.println(
                        "Firebase ID Token not received"
                );

                return false;
            }

            System.out.println(
                    "Admin Firebase UID: " + uid
            );

            boolean stored =
                    firebase.saveAdminData(
                            uid,
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
                    "Authentication successful but "
                            + "Firestore save failed"
            );

            return false;

        } catch (Exception e) {

            System.out.println(
                    "Admin registration error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }
}
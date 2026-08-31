
package com.kryox.controller.Customer;

import com.kryox.dao.Customer.UserDao;
import com.kryox.model.Customer.User;

public class Userstorecontroller {

    private final UserDao userdao = new UserDao();


    // =========================================================
    // ADD USER
    // =========================================================

    public boolean addUsers(
            String name,
            String email,
            String mobile,
            String role,
            String password) {

        User user = new User(
                name,
                email,
                mobile,
                role
        );

        return userdao.saveUser(user);
    }


    // =========================================================
    // GET ROLE BY EMAIL
    // =========================================================

    public String getrole(String email) {

        return userdao.getRoleByEmail(email);
    }


    // =========================================================
    // GET USER BY FIREBASE UID
    //
    // Settings page se Firebase UID aayegi.
    // DAO internally:
    // UID -> Firebase Auth -> Email
    // Email -> Firestore User/{email}
    // =========================================================

    public User getUser(String userId) {

        System.out.println(
                "Controller: Getting user with UID = "
                        + userId
        );

        if (userId == null ||
                userId.trim().isEmpty()) {

            System.out.println(
                    "Controller: User ID is empty"
            );

            return null;
        }

        return userdao.getUserById(
                userId.trim()
        );
    }


    // =========================================================
    // UPDATE USER BY FIREBASE UID
    //
    // oldUserId = Firebase Authentication UID
    //
    // DAO internally old UID se old email find karega
    // aur existing Firestore document update karega.
    // =========================================================

    public boolean updateUser(
            String oldUserId,
            String name,
            String email,
            String mobile) {

        System.out.println(
                "================================"
        );

        System.out.println(
                "Controller: updateUser() called"
        );

        System.out.println(
                "UID    : " + oldUserId
        );

        System.out.println(
                "Name   : " + name
        );

        System.out.println(
                "Email  : " + email
        );

        System.out.println(
                "Mobile : " + mobile
        );

        System.out.println(
                "================================"
        );


        // =====================================================
        // VALIDATION
        // =====================================================

        if (oldUserId == null ||
                oldUserId.trim().isEmpty()) {

            System.out.println(
                    "Controller: UID is empty"
            );

            return false;
        }


        if (name == null ||
                name.trim().isEmpty()) {

            System.out.println(
                    "Controller: Name is empty"
            );

            return false;
        }


        if (email == null ||
                email.trim().isEmpty()) {

            System.out.println(
                    "Controller: Email is empty"
            );

            return false;
        }


        if (mobile == null ||
                mobile.trim().isEmpty()) {

            System.out.println(
                    "Controller: Mobile is empty"
            );

            return false;
        }


        // =====================================================
        // CALL DAO
        // =====================================================

        boolean result =
                userdao.updateUser(
                        oldUserId.trim(),
                        name.trim(),
                        email.trim(),
                        mobile.trim()
                );


        // =====================================================
        // RESULT
        // =====================================================

        if (result) {

            System.out.println(
                    "Controller: User updated successfully"
            );

        } else {

            System.out.println(
                    "Controller: User update failed"
            );
        }


        return result;
    }
}


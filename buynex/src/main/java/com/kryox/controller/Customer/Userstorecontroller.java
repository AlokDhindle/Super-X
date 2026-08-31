package com.kryox.controller.Customer;

import java.util.List;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.kryox.dao.Customer.UserDao;
import com.kryox.model.Customer.User;

public class Userstorecontroller {

    private final UserDao userdao =
            new UserDao();

    public void addUsers(
            String name,
            String email,
            String mobile,
            String role,
            String password
    ) {

        String finalRole =
                role == null ||
                        role.isBlank()
                        ? "Customer"
                        : role.trim();

        User user =
                new User(
                        name,
                        email,
                        mobile,
                        finalRole
                );

        userdao.saveUser(user);
    }

    public String getrole(
            String email
    ) {

        return userdao
                .getRoleByEmail(
                        email
                );
    }

    public List<User> getAllUsers() {

        return userdao
                .getAllUsers();
    }

    // =====================================================
    // SETTINGS PAGE METHODS
    // =====================================================

    public User getUser(
            String email
    ) {

        return userdao
                .getUser(
                        email
                );
    }

    public boolean updateUser(
            String value1,
            String value2,
            String value3,
            String value4
    ) {

        return userdao
                .updateUser(
                        value1,
                        value2,
                        value3,
                        value4
                );
    }

    // =====================================================
    // SHOP APPROVAL METHODS
    // =====================================================

    public boolean saveShopVerificationData(
            String email,
            String shopName,
            String category,
            String businessLicenseUrl,
            String gstCertificateUrl
    ) {

        return userdao
                .saveShopVerificationData(
                        email,
                        shopName,
                        category,
                        businessLicenseUrl,
                        gstCertificateUrl
                );
    }

    public List<QueryDocumentSnapshot>
            getPendingShopkeepers() {

        return userdao
                .getPendingShopkeepers();
    }

    public boolean approveShopkeeper(
            String email
    ) {

        return userdao
                .approveShopkeeper(
                        email
                );
    }

    public boolean isShopApproved(
            String email
    ) {

        return userdao
                .isShopApproved(
                        email
                );
    }
}

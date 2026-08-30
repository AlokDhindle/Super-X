package com.kryox.controller.Customer;

import java.util.List;

import com.kryox.dao.Customer.UserDao;
import com.kryox.model.Customer.User;

public class Userstorecontroller {

    UserDao userdao =
            new UserDao();


    public void addUsers(
            String name,
            String email,
            String mobile,
            String role,
            String password
    ) {

        User user =
                new User(
                        name,
                        email,
                        mobile,
                        role
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
}
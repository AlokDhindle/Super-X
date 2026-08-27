package com.kryox.model.Customer;

import javafx.scene.control.TextField;

public class User {

    private String name;
    private String email;
    private String mobile;
    private String dateOfBirth;
    private String role;

    public User(){

    }

    public User(String name, String email, String mobile,String  role) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dateOfBirth;
    }

    public void setDob(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
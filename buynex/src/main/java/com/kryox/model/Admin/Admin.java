package com.kryox.model.Admin;

public class Admin {

    private String fullName;
    private String username;
    private String email;
    private String mobile;
    private String employeeId;
    private String role;
    private String password;
    private String accessCode;

    public Admin() {
    }

    public Admin(
            String fullName,
            String username,
            String email,
            String mobile,
            String employeeId,
            String role,
            String password,
            String accessCode
    ) {

        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.employeeId = employeeId;
        this.role = role;
        this.password = password;
        this.accessCode = accessCode;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getAccessCode() {
        return accessCode;
    }
}
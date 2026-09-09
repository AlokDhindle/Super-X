package com.kryox.model.Admin;

public class AdminSession {

    public static String uid;
    public static String fullName;
    public static String username;
    public static String email;
    public static String mobile;
    public static String employeeId;
    public static String role;

    public static void setAdmin(
            String uidValue,
            String fullNameValue,
            String usernameValue,
            String emailValue,
            String mobileValue,
            String employeeIdValue,
            String roleValue
    ) {
        uid = uidValue;
        fullName = fullNameValue;
        username = usernameValue;
        email = emailValue;
        mobile = mobileValue;
        employeeId = employeeIdValue;
        role = roleValue;
    }

    public static void clear() {
        uid = null;
        fullName = null;
        username = null;
        email = null;
        mobile = null;
        employeeId = null;
        role = null;
    }
}
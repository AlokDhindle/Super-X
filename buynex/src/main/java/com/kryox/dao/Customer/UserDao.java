package com.kryox.dao.Customer;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Customer.User;

public class UserDao {

    private final Firestore db = Firebaseconfig.gFirestore();

    public void saveUser(User user) {

        try {

            Map<String, Object> data = new HashMap<>();

            data.put("name", user.getName());
            data.put("email", user.getEmail());
            data.put("mobile", user.getMobile());
            data.put("role", user.getRole());

            if (user.getDob() != null && !user.getDob().isBlank()) {
                data.put("dateOfBirth", user.getDob());
            }

            if ("Shopkeeper".equalsIgnoreCase(user.getRole())) {
                data.put("approved", false);
            }

            db.collection("User")
                    .document(user.getEmail())
                    .set(data)
                    .get();

            System.out.println("User saved successfully");

        } catch (Exception e) {

            System.out.println(
                    "User save error : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    public String getRoleByEmail(String email) {

        try {

            DocumentSnapshot snapshot =
                    db.collection("User")
                            .document(email)
                            .get()
                            .get();

            if (snapshot.exists()) {

                String role =
                        snapshot.getString("role");

                System.out.println(
                        "Firebase Role : "
                                + role
                );

                return role;
            }

            System.out.println(
                    "User document not found"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAllUsers() {

        List<User> users =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("User")
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                String name =
                        document.getString("name");

                String email =
                        document.getString("email");

                String mobile =
                        document.getString("mobile");

                String role =
                        document.getString("role");

                User user =
                        new User(
                                name,
                                email,
                                mobile,
                                role
                        );

                String dateOfBirth =
                        document.getString(
                                "dateOfBirth"
                        );

                if (dateOfBirth != null) {
                    user.setDob(dateOfBirth);
                }

                users.add(user);
            }

            System.out.println(
                    "Total Users fetched : "
                            + users.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "User fetch error : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return users;
    }

    // =====================================================
    // SETTINGS PAGE - GET ONE USER
    // =====================================================

    public User getUser(String email) {

        try {

            DocumentSnapshot document =
                    db.collection("User")
                            .document(email)
                            .get()
                            .get();

            if (!document.exists()) {

                System.out.println(
                        "User not found : "
                                + email
                );

                return null;
            }

            String name =
                    document.getString("name");

            String userEmail =
                    document.getString("email");

            String mobile =
                    document.getString("mobile");

            String role =
                    document.getString("role");

            User user =
                    new User(
                            name,
                            userEmail,
                            mobile,
                            role
                    );

            String dob =
                    document.getString(
                            "dateOfBirth"
                    );

            if (dob == null ||
                    dob.isBlank()) {

                dob =
                        document.getString(
                                "dob"
                        );
            }

            if (dob != null) {
                user.setDob(dob);
            }

            return user;

        } catch (Exception e) {

            System.out.println(
                    "Get user error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return null;
        }
    }

    // =====================================================
    // SETTINGS PAGE - UPDATE USER
    // Expected data: email + name + mobile + dob
    // Email can be in any one of the 4 String arguments.
    // =====================================================

    public boolean updateUser(
            String value1,
            String value2,
            String value3,
            String value4
    ) {

        try {

            String[] values = {
                    value1,
                    value2,
                    value3,
                    value4
            };

            String email = null;

            for (String value : values) {

                if (value != null &&
                        value.contains("@")) {

                    email = value.trim();
                    break;
                }
            }

            if (email == null ||
                    email.isBlank()) {

                System.out.println(
                        "Update user failed: email not found"
                );

                return false;
            }

            DocumentSnapshot oldDocument =
                    db.collection("User")
                            .document(email)
                            .get()
                            .get();

            if (!oldDocument.exists()) {

                System.out.println(
                        "Update user failed: user not found"
                );

                return false;
            }

            String oldName =
                    oldDocument.getString("name");

            String oldMobile =
                    oldDocument.getString("mobile");

            String oldDob =
                    oldDocument.getString(
                            "dateOfBirth"
                    );

            String name = oldName;
            String mobile = oldMobile;
            String dob = oldDob;

            for (String value : values) {

                if (value == null ||
                        value.isBlank() ||
                        value.equals(email)) {

                    continue;
                }

                String clean =
                        value.trim();

                if (clean.matches(
                        "^[+0-9][0-9 ()-]{7,}$"
                )) {

                    mobile = clean;

                } else if (clean.matches(
                        ".*\\d{1,4}[-/]\\d{1,2}[-/]\\d{1,4}.*"
                )) {

                    dob = clean;

                } else {

                    name = clean;
                }
            }

            Map<String, Object> update =
                    new HashMap<>();

            if (name != null) {
                update.put("name", name);
            }

            update.put("email", email);

            if (mobile != null) {
                update.put(
                        "mobile",
                        mobile
                );
            }

            if (dob != null) {
                update.put(
                        "dateOfBirth",
                        dob
                );
            }

            db.collection("User")
                    .document(email)
                    .update(update)
                    .get();

            System.out.println(
                    "User updated successfully"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Update user error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // SHOP VERIFICATION DATA
    // =====================================================

    public boolean saveShopVerificationData(
            String email,
            String shopName,
            String category,
            String businessLicenseUrl,
            String gstCertificateUrl
    ) {

        try {

            Map<String, Object> data =
                    new HashMap<>();

            data.put(
                    "shopName",
                    shopName
            );

            data.put(
                    "category",
                    category
            );

            data.put(
                    "businessLicenseUrl",
                    businessLicenseUrl
            );

            data.put(
                    "gstCertificateUrl",
                    gstCertificateUrl
            );

            data.put(
                    "approved",
                    false
            );

            db.collection("User")
                    .document(email)
                    .update(data)
                    .get();

            System.out.println(
                    "Shop verification data saved"
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Shop verification data save error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    public List<QueryDocumentSnapshot>
            getPendingShopkeepers() {

        List<QueryDocumentSnapshot> pending =
                new ArrayList<>();

        try {

            QuerySnapshot snapshot =
                    db.collection("User")
                            .whereEqualTo(
                                    "role",
                                    "Shopkeeper"
                            )
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                Boolean approved =
                        document.getBoolean(
                                "approved"
                        );

                if (!Boolean.TRUE.equals(
                        approved
                )) {

                    pending.add(document);
                }
            }

            System.out.println(
                    "Pending Shopkeepers : "
                            + pending.size()
            );

        } catch (Exception e) {

            System.out.println(
                    "Pending shopkeeper fetch error : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return pending;
    }

    public boolean approveShopkeeper(
            String email
    ) {

        try {

            db.collection("User")
                    .document(email)
                    .update(
                            "approved",
                            true
                    )
                    .get();

            System.out.println(
                    "Shopkeeper approved : "
                            + email
            );

            return true;

        } catch (Exception e) {

            System.out.println(
                    "Shopkeeper approve error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    public boolean isShopApproved(
            String email
    ) {

        try {

            DocumentSnapshot snapshot =
                    db.collection("User")
                            .document(email)
                            .get()
                            .get();

            if (!snapshot.exists()) {
                return false;
            }

            Boolean approved =
                    snapshot.getBoolean(
                            "approved"
                    );

            return Boolean.TRUE.equals(
                    approved
            );

        } catch (Exception e) {

            System.out.println(
                    "Shop approval check error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }

    // =====================================================
    // DASHBOARD - COUNT USERS BY ROLE
    // =====================================================

    public int getUserCountByRole(
            String role
    ) {

        try {

            QuerySnapshot snapshot =
                    db.collection("User")
                            .whereEqualTo(
                                    "role",
                                    role
                            )
                            .get()
                            .get();

            return snapshot.size();

        } catch (Exception e) {

            System.out.println(
                    "Role count error : "
                            + e.getMessage()
            );

            e.printStackTrace();

            return 0;
        }
    }


    // =====================================================
    // DASHBOARD - SAVE DAILY USER ACTIVITY
    // One user is counted only once per day.
    // =====================================================

    public void saveUserActivity(
            String email
    ) {

        try {

            if (email == null ||
                    email.isBlank()) {

                return;
            }

            String cleanEmail =
                    email.trim()
                            .toLowerCase();

            String date =
                    LocalDate.now()
                            .format(
                                    DateTimeFormatter.ISO_LOCAL_DATE
                            );

            String encodedEmail =
                    Base64.getUrlEncoder()
                            .withoutPadding()
                            .encodeToString(
                                    cleanEmail.getBytes(
                                            StandardCharsets.UTF_8
                                    )
                            );

            String activityDocumentId =
                    date + "_" + encodedEmail;

            Map<String, Object> activity =
                    new HashMap<>();

            activity.put(
                    "email",
                    cleanEmail
            );

            activity.put(
                    "date",
                    date
            );

            activity.put(
                    "lastActivity",
                    FieldValue.serverTimestamp()
            );

            db.collection(
                    "user_activity"
            )
                    .document(
                            activityDocumentId
                    )
                    .set(activity)
                    .get();

            System.out.println(
                    "User activity saved : "
                            + cleanEmail
                            + " | "
                            + date
            );

        } catch (Exception e) {

            System.out.println(
                    "User activity save error : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    // =====================================================
    // DASHBOARD - LAST 7 DAYS ACTIVE USER COUNT
    // Returns date -> unique active users count.
    // =====================================================

    public Map<String, Integer>
            getLast7DaysActiveUsers() {

        Map<String, Integer> counts =
                new LinkedHashMap<>();

        DateTimeFormatter formatter =
                DateTimeFormatter.ISO_LOCAL_DATE;

        LocalDate today =
                LocalDate.now();

        for (int i = 6; i >= 0; i--) {

            String date =
                    today.minusDays(i)
                            .format(formatter);

            counts.put(
                    date,
                    0
            );
        }

        try {

            QuerySnapshot snapshot =
                    db.collection(
                            "user_activity"
                    )
                            .get()
                            .get();

            for (QueryDocumentSnapshot document :
                    snapshot.getDocuments()) {

                String date =
                        document.getString(
                                "date"
                        );

                if (date != null &&
                        counts.containsKey(date)) {

                    counts.put(
                            date,
                            counts.get(date) + 1
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Active users fetch error : "
                            + e.getMessage()
            );

            e.printStackTrace();
        }

        return counts;
    }

}

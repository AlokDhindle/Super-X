package com.kryox.dao.Customer;

import java.util.HashMap;
import java.util.Map;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Customer.User;

public class UserDao {

    private final Firestore db = Firebaseconfig.gFirestore();

    // =========================================================
    // SAVE USER
    // Existing Firestore structure: User/{email}
    // =========================================================

    public boolean saveUser(User user) {
        try {
            db.collection("User")
                    .document(user.getEmail())
                    .set(user)
                    .get();

            System.out.println("USER SAVED SUCCESSFULLY");
            return true;

        } catch (Exception e) {
            System.out.println("ERROR WHILE SAVING USER");
            e.printStackTrace();
            return false;
        }
    }

    // =========================================================
    // GET ROLE BY EMAIL
    // =========================================================

    public String getRoleByEmail(String email) {
        try {
            DocumentSnapshot snapshot = db.collection("User")
                    .document(email.trim())
                    .get()
                    .get();

            if (snapshot.exists()) {
                String role = snapshot.getString("role");
                System.out.println("Firebase Role : " + role);
                return role;
            }

            System.out.println("User document not found: " + email);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // GET USER
    //
    // Settings receives Firebase Authentication UID.
    // Existing Firestore users are stored using EMAIL as document ID.
    //
    // So:
    // UID -> Firebase Auth -> Email -> Firestore User/{email}
    // =========================================================

    public User getUserById(String userId) {
        try {
            if (userId == null || userId.trim().isEmpty()) {
                System.out.println("USER ID IS EMPTY");
                return null;
            }

            userId = userId.trim();

            // First try UID as document ID.
            DocumentSnapshot snapshot = db.collection("User")
                    .document(userId)
                    .get()
                    .get();

            if (snapshot.exists()) {
                System.out.println("USER FOUND BY UID DOCUMENT: " + userId);
                return snapshot.toObject(User.class);
            }

            // Existing project stores documents by email.
            // Resolve UID to email using Firebase Authentication.
            UserRecord authUser = FirebaseAuth.getInstance().getUser(userId);
            String email = authUser.getEmail();

            if (email == null || email.trim().isEmpty()) {
                System.out.println("EMAIL NOT FOUND FOR UID: " + userId);
                return null;
            }

            System.out.println("UID -> EMAIL: " + email);

            snapshot = db.collection("User")
                    .document(email.trim())
                    .get()
                    .get();

            if (snapshot.exists()) {
                System.out.println("USER FOUND BY EMAIL: " + email);
                return snapshot.toObject(User.class);
            }

            System.out.println("USER NOT FOUND IN FIRESTORE: " + email);

        } catch (Exception e) {
            System.out.println("ERROR GETTING USER BY UID");
            e.printStackTrace();
        }

        return null;
    }

    // =========================================================
    // UPDATE USER
    //
    // oldUserId is Firebase Authentication UID, NOT email.
    // We resolve UID -> old email and update the existing
    // email-keyed Firestore document.
    // =========================================================

    public boolean updateUser(
            String oldUserId,
            String newName,
            String newEmail,
            String newMobile) {

        try {
            if (oldUserId == null || oldUserId.trim().isEmpty()) {
                System.out.println("USER ID IS EMPTY");
                return false;
            }

            if (newName == null || newName.trim().isEmpty()) {
                System.out.println("NAME IS EMPTY");
                return false;
            }

            if (newEmail == null || newEmail.trim().isEmpty()) {
                System.out.println("EMAIL IS EMPTY");
                return false;
            }

            if (newMobile == null || newMobile.trim().isEmpty()) {
                System.out.println("MOBILE IS EMPTY");
                return false;
            }

            oldUserId = oldUserId.trim();
            newName = newName.trim();
            newEmail = newEmail.trim();
            newMobile = newMobile.trim();

            // -------------------------------------------------
            // Get Firebase Auth user using UID
            // -------------------------------------------------

            UserRecord authUser = FirebaseAuth.getInstance().getUser(oldUserId);

            String oldEmail = authUser.getEmail();

            if (oldEmail == null || oldEmail.trim().isEmpty()) {
                System.out.println("OLD EMAIL NOT FOUND FOR UID");
                return false;
            }

            oldEmail = oldEmail.trim();

            System.out.println("UPDATE USER STARTED");
            System.out.println("UID       : " + oldUserId);
            System.out.println("OLD EMAIL : " + oldEmail);
            System.out.println("NEW EMAIL : " + newEmail);
            System.out.println("NEW NAME  : " + newName);
            System.out.println("NEW MOBILE: " + newMobile);

            // -------------------------------------------------
            // Read old Firestore document
            // -------------------------------------------------

            DocumentSnapshot oldDocument = db.collection("User")
                    .document(oldEmail)
                    .get()
                    .get();

            if (!oldDocument.exists()) {
                System.out.println(
                        "OLD USER DOCUMENT DOES NOT EXIST: " + oldEmail);
                return false;
            }

            // Preserve existing fields such as role/password/etc.
            Map<String, Object> userData = new HashMap<>();

            if (oldDocument.getData() != null) {
                userData.putAll(oldDocument.getData());
            }

            userData.put("name", newName);
            userData.put("email", newEmail);
            userData.put("mobile", newMobile);

            // -------------------------------------------------
            // Email is unchanged
            // -------------------------------------------------

            if (oldEmail.equalsIgnoreCase(newEmail)) {

                db.collection("User")
                        .document(oldEmail)
                        .set(userData)
                        .get();

                System.out.println("USER UPDATED SUCCESSFULLY");
                return true;
            }

            // -------------------------------------------------
            // Email changed
            //
            // Firestore document ID is email in the old design,
            // so create new email document and delete old one.
            // -------------------------------------------------

            DocumentSnapshot newDocument = db.collection("User")
                    .document(newEmail)
                    .get()
                    .get();

            if (newDocument.exists()) {
                System.out.println("NEW EMAIL ALREADY EXISTS");
                return false;
            }

            db.collection("User")
                    .document(newEmail)
                    .set(userData)
                    .get();

            // Update Firebase Authentication email as well.
            FirebaseAuth.getInstance()
                    .updateUser(
                            new UserRecord.UpdateRequest(oldUserId)
                                    .setEmail(newEmail));

            db.collection("User")
                    .document(oldEmail)
                    .delete()
                    .get();

            System.out.println("USER EMAIL + PROFILE UPDATED SUCCESSFULLY");
            return true;

        } catch (Exception e) {
            System.out.println("USER UPDATE FAILED");
            e.printStackTrace();
            return false;
        }
    }
}

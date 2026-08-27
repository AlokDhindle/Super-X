package com.kryox.dao.Customer;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Customer.User;

public class UserDao {
    private Firestore db = Firebaseconfig.gFirestore();

    public void saveUser(User user) {
        try {
            db.collection("User")
                    .document(user.getEmail())
                    .create(user);

            System.out.println("User saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRoleByEmail(String email) {

        try {

            DocumentSnapshot snapshot = db
                    .collection("User")
                    .document(email)
                    .get()
                    .get();

            if (snapshot.exists()) {

                String role = snapshot.getString("role");

                System.out.println("Firebase Role : " + role);

                return role;
            }

            System.out.println("User document not found");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.kryox.dao.Customer;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.kryox.config.Firebaseconfig;
import com.kryox.model.Customer.User;

public class UserDao {

    private Firestore db =
            Firebaseconfig.gFirestore();


    public void saveUser(User user) {

        try {

            db.collection("User")
                    .document(user.getEmail())
                    .create(user);

            System.out.println(
                    "User saved successfully"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public String getRoleByEmail(
            String email
    ) {

        try {

            DocumentSnapshot snapshot =
                    db.collection("User")
                            .document(email)
                            .get()
                            .get();

            if (snapshot.exists()) {

                String role =
                        getValueAsString(
                                snapshot,
                                "role"
                        );

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


            for (QueryDocumentSnapshot document
                    : snapshot.getDocuments()) {


                String name =
                        getValueAsString(
                                document,
                                "name"
                        );


                String email =
                        getValueAsString(
                                document,
                                "email"
                        );


                String mobile =
                        getValueAsString(
                                document,
                                "mobile"
                        );


                String role =
                        getValueAsString(
                                document,
                                "role"
                        );


                String dob =
                        getValueAsString(
                                document,
                                "dob"
                        );


                User user =
                        new User(
                                name,
                                email,
                                mobile,
                                role
                        );


                if (dob != null
                        && !dob.isBlank()) {

                    user.setDob(
                            dob
                    );
                }


                users.add(
                        user
                );


                System.out.println(
                        "Fetched User : "
                                + name
                                + " | "
                                + email
                                + " | "
                                + mobile
                                + " | "
                                + role
                );
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


    private String getValueAsString(
            DocumentSnapshot document,
            String fieldName
    ) {

        Object value =
                document.get(
                        fieldName
                );

        if (value == null) {

            return "";
        }

        return String.valueOf(
                value
        );
    }
}
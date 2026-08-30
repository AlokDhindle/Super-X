package com.kryox.config;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Firebaseconfig {

    static {
        getFirebaseConfig();
    }

    private static void getFirebaseConfig() {

        try {

            if (!FirebaseApp.getApps().isEmpty()) {
                return;
            }

            FileInputStream serviceAccount =
                    new FileInputStream(
                            "C:\\Java_26\\testing\\Super-X\\buynex\\src\\main\\resources\\assets\\serviceAccount.json.json"
                    );

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(
                                            serviceAccount
                                    )
                            )
                            .build();

            FirebaseApp.initializeApp(
                    options
            );

            serviceAccount.close();

            System.out.println(
                    "Firebase Connected Successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Firebase Connection Failed!"
            );

            e.printStackTrace();
        }
    }

    public static Firestore gFirestore() {

        if (FirebaseApp.getApps().isEmpty()) {

            getFirebaseConfig();
        }

        if (FirebaseApp.getApps().isEmpty()) {

            throw new IllegalStateException(
                    "Firebase is not initialized."
            );
        }

        return FirestoreClient.getFirestore();
    }
}
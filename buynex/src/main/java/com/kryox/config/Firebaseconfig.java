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

            FileInputStream serviceAccount =
                    new FileInputStream(
                            "C:\\BuyNex\\Super-X\\buynex\\src\\main\\resources\\serviceAccount.json"
                    );

            FirebaseOptions options =
                    new FirebaseOptions.Builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(serviceAccount)
                            )
                            .build();

            // Firebase already initialized hai to dobara initialize nahi karega
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            serviceAccount.close();

            System.out.println("Firebase Connected Successfully!");

        } catch (Exception e) {

            System.out.println("Firebase Connection Failed!");
            e.printStackTrace();
        }
    }

    public static Firestore gFirestore() {
        return FirestoreClient.getFirestore();
    }
}
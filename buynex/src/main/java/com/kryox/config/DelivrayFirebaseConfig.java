package com.kryox.config;

import java.io.InputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class DelivrayFirebaseConfig {

    // PASTE YOUR ACTUAL FIREBASE WEB API KEY HERE
    public static final String WEB_API_KEY = "AIzaSyAmmfnbaq-fPAXz5YUH8guxWrykcEKRZ2g";

    private static Firestore firestore;

    static {
        initializeFirebase();
    }

    private static void initializeFirebase() {
        try {
            InputStream serviceAccount = DelivrayFirebaseConfig.class
                    .getClassLoader()
                    .getResourceAsStream("firebase_service_account.json");

            if (serviceAccount == null) {
                System.err.println("Error: 'firebase_service_account.json' not found inside src/main/resources/!");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            firestore = FirestoreClient.getFirestore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Firestore getFireStore() {
        if (firestore == null) {
            initializeFirebase();
        }
        return firestore;
    }
}
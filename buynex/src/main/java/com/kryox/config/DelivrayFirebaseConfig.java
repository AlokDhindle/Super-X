package com.kryox.config;

import com.google.cloud.firestore.Firestore;

public class DelivrayFirebaseConfig {

    public static final String WEB_API_KEY =
            Apikey.API_KEY;

    private DelivrayFirebaseConfig() {
    }

    public static Firestore getFireStore() {

        return Firebaseconfig.gFirestore();
    }
}

package com.kryox.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Firebaseconfig {

    private static FirebaseApp firebaseApp;

    public static final String WEB_API_KEY =
            Apikey.API_KEY;

    public static void getFirebaseConfig() {

        try {

            if (firebaseApp != null) {
                return;
            }

            if (!FirebaseApp.getApps().isEmpty()) {
                firebaseApp = FirebaseApp.getInstance();
                return;
            }

            InputStream serviceAccount =
                    Firebaseconfig.class.getResourceAsStream(
                            "/assets/serviceAccount.json"
                    );

            if (serviceAccount == null) {

                serviceAccount =
                        Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream(
                                        "assets/serviceAccount.json"
                                );
            }

            if (serviceAccount == null) {

                Path path =
                        Paths.get(
                                System.getProperty("user.dir"),
                                "buynex",
                                "src",
                                "main",
                                "resources",
                                "assets",
                                "serviceAccount.json"
                        );

                if (!Files.exists(path)) {

                    path =
                            Paths.get(
                                    System.getProperty("user.dir"),
                                    "src",
                                    "main",
                                    "resources",
                                    "assets",
                                    "serviceAccount.json"
                            );
                }

                System.out.println(
                        "Trying Firebase path: "
                                + path.toAbsolutePath()
                );

                if (Files.exists(path)) {

                    serviceAccount =
                            new FileInputStream(
                                    path.toFile()
                            );
                }
            }

            if (serviceAccount == null) {

                System.out.println(
                        "serviceAccount.json not found."
                );

                return;
            }

            FirebaseOptions options =
                    FirebaseOptions.builder()
                            .setCredentials(
                                    GoogleCredentials.fromStream(
                                            serviceAccount
                                    )
                            )
                            .build();

            firebaseApp =
                    FirebaseApp.initializeApp(
                            options
                    );

            System.out.println(
                    "Firebase initialized successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Firebase Connection Failed!"
            );

            e.printStackTrace();
        }
    }

    public static Firestore gFirestore() {

        if (firebaseApp == null) {
            getFirebaseConfig();
        }

        if (firebaseApp == null) {

            throw new IllegalStateException(
                    "Firebase is not initialized."
            );
        }

        return FirestoreClient.getFirestore(
                firebaseApp
        );
    }

    public static Firestore getFireStore() {

        return gFirestore();
    }
}
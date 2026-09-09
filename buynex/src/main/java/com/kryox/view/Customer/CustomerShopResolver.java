package com.kryox.view.Customer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.kryox.config.Firebaseconfig;
import com.kryox.view.Shopkeeper.ViewConstants;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class CustomerShopResolver {

    private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

    static {
        CACHE.put("default", "BuyNeX Super Mart");
        CACHE.put("guest", "BuyNeX Mart");
    }

    public static String getShopName(String shopkeeperUid) {
        if (shopkeeperUid == null || shopkeeperUid.trim().isEmpty()) {
            return "BuyNeX Partner Store";
        }
        String trimmed = shopkeeperUid.trim();
        if (CACHE.containsKey(trimmed)) {
            return CACHE.get(trimmed);
        }
        if (ViewConstants.shopkeeperModel != null && trimmed.equals(ViewConstants.shopkeeperModel.getShopkeeperUid())) {
            String name = ViewConstants.shopkeeperModel.getShopNameValue();
            if (name != null && !name.isBlank()) {
                CACHE.put(trimmed, name.trim());
                return name.trim();
            }
        }
        String shortId = trimmed.length() > 6 ? trimmed.substring(0, 6) : trimmed;
        return "Store #" + shortId;
    }

    public static void bindShopName(Label targetLabel, String shopkeeperUid) {
        bindShopName(targetLabel, shopkeeperUid, "🏪 ");
    }

    public static void bindShopName(Label targetLabel, String shopkeeperUid, String prefix) {
        if (targetLabel == null) return;

        String current = getShopName(shopkeeperUid);
        targetLabel.setText(prefix + current);

        if (shopkeeperUid == null || shopkeeperUid.trim().isEmpty()) {
            return;
        }

        String uid = shopkeeperUid.trim();
        if (CACHE.containsKey(uid) && !CACHE.get(uid).startsWith("Store #")) {
            targetLabel.setText(prefix + CACHE.get(uid));
            return;
        }

        new Thread(() -> {
            try {
                Firestore db = Firebaseconfig.gFirestore();
                if (db != null) {
                    DocumentSnapshot snap = db.collection("Shopkeepers").document(uid).get().get();
                    if (snap != null && snap.exists()) {
                        String sName = snap.getString("shopNameValue");
                        if (sName == null || sName.isBlank()) {
                            sName = snap.getString("shopName");
                        }
                        if (sName != null && !sName.isBlank()) {
                            final String finalName = sName.trim();
                            CACHE.put(uid, finalName);
                            Platform.runLater(() -> targetLabel.setText(prefix + finalName));
                        }
                    }
                }
            } catch (Exception e) {
                // Retain existing fallback label gracefully
            }
        }).start();
    }
}

package com.kryox.view.Customer;

import com.kryox.controller.Shopkeeper.OfferController;
import com.kryox.model.Customer.Productcart;
import com.kryox.model.Shopkeeper.OfferModel;
import java.util.ArrayList;
import java.util.List;

public class CartOfferManager {

    private static OfferModel appliedOffer = null;
    private static String preappliedCode = null;

    public static class OfferResult {
        public final boolean success;
        public final String message;
        public final double discountAmount;
        public final OfferModel offer;

        public OfferResult(boolean success, String message, double discountAmount, OfferModel offer) {
            this.success = success;
            this.message = message;
            this.discountAmount = discountAmount;
            this.offer = offer;
        }
    }

    public static void setPreappliedCode(String code) {
        preappliedCode = (code != null) ? code.trim() : null;
    }

    public static String getPreappliedCode() {
        return preappliedCode;
    }

    public static void clearPreappliedCode() {
        preappliedCode = null;
    }

    public static OfferModel getAppliedOffer() {
        return appliedOffer;
    }

    public static void clearAppliedOffer() {
        appliedOffer = null;
    }

    /**
     * Get all available offers: fetched from shopkeeper Firestore offers + verified default offers
     */
    public static List<OfferModel> getAvailableOffers() {
        List<OfferModel> allOffers = new ArrayList<>();

        // Add dynamic shopkeeper offers from database
        try {
            ArrayList<OfferModel> fetched = OfferController.getAllOffersForAdmin();
            if (fetched != null && !fetched.isEmpty()) {
                for (OfferModel om : fetched) {
                    if (om != null && om.getPromoCode() != null && !om.getPromoCode().isBlank()) {
                        allOffers.add(om);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("CartOfferManager: Error fetching dynamic offers: " + e.getMessage());
        }

        // Add standard default offers if not already present
        addDefaultOfferIfMissing(allOffers, "GROCERY20", "20% OFF on Grocery", "Percentage Discount", 20.0, "Grocery", "Save 20% on all fresh grocery items.");
        addDefaultOfferIfMissing(allOffers, "TECH50", "Flat ₹50 OFF on Electronics", "Fixed Amount", 50.0, "Electronics", "Flat ₹50 discount on electronics gear.");
        addDefaultOfferIfMissing(allOffers, "BUYNEX15", "15% OFF Super Deal", "Percentage Discount", 15.0, "General", "Get 15% off across all categories.");
        addDefaultOfferIfMissing(allOffers, "SAVE10", "Flat 10% OFF Welcome Offer", "Percentage Discount", 10.0, "General", "Welcome special 10% discount on cart.");

        return allOffers;
    }

    private static void addDefaultOfferIfMissing(List<OfferModel> list, String code, String name, String type, double val, String cat, String desc) {
        for (OfferModel om : list) {
            if (om.getPromoCode() != null && om.getPromoCode().equalsIgnoreCase(code)) {
                return;
            }
        }
        OfferModel defaultOffer = new OfferModel();
        defaultOffer.setOfferId("def_" + code.toLowerCase());
        defaultOffer.setOfferName(name);
        defaultOffer.setPromoCode(code);
        defaultOffer.setDiscountType(type);
        defaultOffer.setDiscountValue(val);
        defaultOffer.setCategory(cat);
        defaultOffer.setDescription(desc);
        defaultOffer.setStatus("Active");
        list.add(defaultOffer);
    }

    /**
     * Applies a promo code against current cart items and subtotal
     */
    public static OfferResult applyOffer(String code, double subtotal, List<Productcart> items) {
        if (code == null || code.trim().isEmpty()) {
            return new OfferResult(false, "Please enter a promo code.", 0.0, null);
        }

        String trimmed = code.trim();
        List<OfferModel> available = getAvailableOffers();
        OfferModel matched = null;

        for (OfferModel om : available) {
            if (om.getPromoCode() != null && om.getPromoCode().equalsIgnoreCase(trimmed)) {
                matched = om;
                break;
            }
        }

        if (matched == null) {
            return new OfferResult(false, "Invalid promo code '" + trimmed + "'. Try GROCERY20 or TECH50.", 0.0, null);
        }

        if (subtotal <= 0) {
            return new OfferResult(false, "Cart is empty. Add items before applying offers.", 0.0, null);
        }

        double discount = calculateDiscount(matched, subtotal, items);
        if (discount <= 0) {
            return new OfferResult(false, "Offer '" + matched.getPromoCode() + "' is not applicable to items in your cart.", 0.0, null);
        }

        appliedOffer = matched;
        return new OfferResult(true, "Promo code '" + matched.getPromoCode() + "' applied successfully!", discount, matched);
    }

    /**
     * Calculate discount amount for an offer given current subtotal and items
     */
    public static double calculateDiscount(OfferModel offer, double subtotal, List<Productcart> items) {
        if (offer == null || subtotal <= 0) return 0.0;

        double discountVal = offer.getDiscountValue();
        if (discountVal <= 0) return 0.0;

        String cat = offer.getCategory();
        boolean isCategorySpecific = cat != null && !cat.isBlank() && !"General".equalsIgnoreCase(cat) && !"All".equalsIgnoreCase(cat);

        double eligibleSubtotal = subtotal;
        if (isCategorySpecific && items != null && !items.isEmpty()) {
            double catSubtotal = 0.0;
            String targetCat = cat.toLowerCase();
            for (Productcart p : items) {
                if (p == null) continue;
                String itemShopOrCat = p.getName1() != null ? p.getName1().toLowerCase() : "";
                String itemName = p.getName() != null ? p.getName().toLowerCase() : "";
                if (itemShopOrCat.contains(targetCat) || itemName.contains(targetCat) || matchesCategoryKeywords(targetCat, itemName, itemShopOrCat)) {
                    int q = p.getQuantity() > 0 ? p.getQuantity() : 1;
                    catSubtotal += p.getPrice() * q;
                }
            }
            if (catSubtotal > 0) {
                eligibleSubtotal = catSubtotal;
            } else {
                eligibleSubtotal = subtotal;
            }
        }

        double discount = 0.0;
        String type = offer.getDiscountType() != null ? offer.getDiscountType().toLowerCase() : "percent";
        if (type.contains("percent") || type.contains("%")) {
            discount = eligibleSubtotal * (discountVal / 100.0);
        } else {
            discount = Math.min(eligibleSubtotal, discountVal);
        }

        discount = Math.round(discount * 100.0) / 100.0;
        return Math.min(discount, subtotal);
    }

    private static boolean matchesCategoryKeywords(String cat, String name, String shop) {
        if (cat.contains("groc")) {
            return name.contains("bread") || name.contains("milk") || name.contains("honey") || name.contains("avocado") || name.contains("fruit") || name.contains("vegetable");
        }
        if (cat.contains("elec")) {
            return name.contains("phone") || name.contains("headphone") || name.contains("watch") || name.contains("laptop") || name.contains("charger");
        }
        return false;
    }
}

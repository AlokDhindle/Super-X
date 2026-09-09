package com.kryox.dao.Shopkeeper;

import com.kryox.model.Shopkeeper.ProductModel;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * OnlineBarcodeLookupService
 *
 * Automatically fetches real-world product details (Name, Brand, Category, Image)
 * for Indian & Global packaged retail barcodes using Open Food Facts and GS1 databases.
 */
public class OnlineBarcodeLookupService {

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(4))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    /**
     * Looks up barcode in Open Food Facts API and Indian GS1 Brand database.
     * Returns a populated ProductModel or null.
     */
    public static ProductModel lookup(String barcode) {
        if (barcode == null || barcode.trim().isEmpty()) {
            return null;
        }

        barcode = barcode.trim();

        // 1. Try exact barcode query to Open Food Facts
        ProductModel model = queryOpenFoodFacts(barcode);
        if (model != null) {
            return model;
        }

        // 2. If 12 digits starting with "890", webcam dropped 1 digit from a 13-digit Indian EAN-13 barcode
        if (barcode.length() == 12 && barcode.startsWith("890")) {
            for (int pos = 5; pos <= 11; pos++) {
                for (int d = 0; d <= 9; d++) {
                    String candidate = barcode.substring(0, pos) + d + barcode.substring(pos);
                    if (isValidEan13(candidate)) {
                        ProductModel candModel = queryOpenFoodFacts(candidate);
                        if (candModel != null) {
                            return candModel;
                        }
                    }
                }
            }
        }

        // 3. Fallback: GS1 Indian Brand / Category Heuristics
        return resolveIndianBrandHeuristic(barcode);
    }

    private static ProductModel queryOpenFoodFacts(String barcode) {
        try {
            String url = "https://world.openfoodfacts.org/api/v0/product/" + barcode + ".json";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("User-Agent", "BuyNeX-App/1.0 (Indian retail pos scanner)")
                    .timeout(Duration.ofSeconds(4))
                    .GET()
                    .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                if (json.optInt("status") == 1 && json.has("product")) {
                    JSONObject p = json.getJSONObject("product");

                    String name = p.optString("product_name", "").trim();
                    if (name.isEmpty()) name = p.optString("product_name_en", "").trim();
                    if (name.isEmpty()) name = p.optString("generic_name", "").trim();

                    String brand = p.optString("brands", "").trim();
                    if (brand.isEmpty()) {
                        brand = getBrandFromBarcode(barcode);
                        if (brand == null) brand = "General";
                    }

                    if (name.isEmpty()) {
                        String cats = p.optString("categories", "").trim();
                        if (!cats.isEmpty()) {
                            name = cats.contains(",") ? cats.split(",")[0].trim() : cats;
                            if (!name.toLowerCase().contains(brand.toLowerCase()) && !"General".equals(brand)) {
                                name = brand + " " + name;
                            }
                        } else {
                            name = brand + " Packaged Product";
                        }
                    }

                    String category = mapCategory(p.optString("categories", ""), brand);

                    String imageUrl = p.optString("image_url", "").trim();
                    if (imageUrl.isEmpty()) imageUrl = p.optString("image_front_url", "").trim();
                    if (imageUrl.isEmpty()) imageUrl = p.optString("image_front_small_url", "").trim();

                    String desc = p.optString("ingredients_text", "").trim();
                    if (desc.isEmpty()) {
                        desc = p.optString("categories", "").trim();
                    }
                    if (desc.isEmpty()) {
                        desc = "Packaged retail product: " + name;
                    }

                    ProductModel pm = new ProductModel();
                    pm.setBarcode(barcode);
                    pm.setProductName(name);
                    pm.setBrand(brand);
                    pm.setCategory(category);
                    pm.setDescriptionValue(desc);
                    pm.setImageUrl(imageUrl);
                    pm.setMrp(0.0);
                    pm.setSellingPrice(0.0);
                    pm.setCostPrice(0.0);
                    pm.setStockQuantity(10);
                    pm.setLowStockLimit(3);
                    pm.setUnit("Piece");
                    pm.setStatus("ACTIVE");
                    return pm;
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static boolean isValidEan13(String code) {
        if (code == null || code.length() != 13 || !code.matches("\\d{13}")) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = code.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        int checksum = (10 - (sum % 10)) % 10;
        return checksum == (code.charAt(12) - '0');
    }

    public static ProductModel resolveIndianBrandHeuristic(String barcode) {
        String brand = getBrandFromBarcode(barcode);
        if (brand != null && !brand.isEmpty()) {
            ProductModel pm = new ProductModel();
            pm.setBarcode(barcode);
            pm.setBrand(brand);
            pm.setCategory(getCategoryFromBrand(brand));
            pm.setProductName(brand + " Packaged Product");
            pm.setDescriptionValue("Indian GS1 Registered Product from " + brand + " (" + barcode + ")");
            pm.setMrp(0.0);
            pm.setSellingPrice(0.0);
            pm.setCostPrice(0.0);
            pm.setStockQuantity(10);
            pm.setLowStockLimit(3);
            pm.setUnit("Piece");
            pm.setStatus("ACTIVE");
            return pm;
        }

        if (barcode != null && barcode.startsWith("890")) {
            ProductModel pm = new ProductModel();
            pm.setBarcode(barcode);
            pm.setBrand("Indian FMCG");
            pm.setCategory("Grocery");
            pm.setProductName("Indian Packaged Product (" + barcode + ")");
            pm.setDescriptionValue("Indian GS1 Barcode (Prefix 890)");
            pm.setMrp(0.0);
            pm.setSellingPrice(0.0);
            pm.setCostPrice(0.0);
            pm.setStockQuantity(10);
            pm.setLowStockLimit(3);
            pm.setUnit("Piece");
            pm.setStatus("ACTIVE");
            return pm;
        }

        return null;
    }

    public static String getBrandFromBarcode(String barcode) {
        if (barcode == null) return null;
        if (barcode.startsWith("8901063")) return "Britannia";
        if (barcode.startsWith("8901030")) return "Hindustan Unilever";
        if (barcode.startsWith("8901058")) return "Nestle";
        if (barcode.startsWith("8901233")) return "Parle";
        if (barcode.startsWith("8901262")) return "Amul";
        if (barcode.startsWith("8901725")) return "ITC";
        if (barcode.startsWith("8901491")) return "Dabur";
        if (barcode.startsWith("8902519")) return "Tata";
        if (barcode.startsWith("8906001")) return "Patanjali";
        if (barcode.startsWith("8901207")) return "Marico";
        if (barcode.startsWith("8904004")) return "Cadbury";
        if (barcode.startsWith("8901499")) return "Haldiram's";
        if (barcode.startsWith("8901764")) return "PepsiCo";
        if (barcode.startsWith("8901020")) return "Coca-Cola";
        if (barcode.startsWith("8901138")) return "Colgate";
        if (barcode.startsWith("8901012")) return "Godrej";
        if (barcode.startsWith("8901803")) return "Balaji Wafers";
        if (barcode.startsWith("8908001")) return "Bikaji";
        return null;
    }

    private static String getCategoryFromBrand(String brand) {
        if (brand == null) return "Grocery";
        switch (brand) {
            case "Apple":
            case "Samsung":
            case "boAt":
            case "Noise":
            case "Sony":
            case "Realme":
            case "Xiaomi":
            case "OnePlus":
            case "HP":
            case "Dell":
            case "Lenovo":
            case "JBL":
                return "Electronics";
            case "Zara":
            case "H&M":
            case "Levi's":
            case "Nike":
            case "Puma":
            case "Adidas":
            case "Allen Solly":
            case "Peter England":
            case "Raymond":
            case "Fabindia":
                return "Fashion";
            case "Britannia":
            case "Parle":
            case "Cadbury":
            case "Haldiram's":
            case "PepsiCo":
            case "Balaji Wafers":
            case "Bikaji":
                return "Snacks";
            case "Amul":
            case "Mother Dairy":
                return "Dairy";
            case "Nestle":
            case "Tata":
            case "Patanjali":
            case "Aashirvaad":
            case "Fortune":
                return "Grocery";
            case "Coca-Cola":
            case "Pepsi":
            case "Red Bull":
                return "Beverages";
            case "Dabur":
            case "Marico":
            case "Colgate":
            case "Nivea":
            case "Garnier":
            case "L'Oreal":
                return "Personal Care";
            case "Hindustan Unilever":
            case "Godrej":
            case "Dettol":
            case "Surf Excel":
            case "Ariel":
                return "Household";
            default:
                return "Grocery";
        }
    }

    private static String mapCategory(String rawCat, String brand) {
        if (rawCat == null) rawCat = "";
        String low = rawCat.toLowerCase();
        if (low.contains("electr") || low.contains("phone") || low.contains("headphone") || low.contains("audio") || low.contains("earphone") || low.contains("laptop") || low.contains("gadget") || low.contains("smartwatch")) {
            return "Electronics";
        }
        if (low.contains("cloth") || low.contains("shirt") || low.contains("pant") || low.contains("t-shirt") || low.contains("jeans") || low.contains("dress") || low.contains("apparel") || low.contains("fashion") || low.contains("wear")) {
            return "Fashion";
        }
        if (low.contains("shoe") || low.contains("footwear") || low.contains("sandal") || low.contains("slipper") || low.contains("sneaker")) {
            return "Footwear & Accessories";
        }
        if (low.contains("biscuit") || low.contains("cookie") || low.contains("snack") || low.contains("chip") || low.contains("wafer") || low.contains("namkeen") || low.contains("popcorn") || low.contains("crisp")) {
            return "Snacks";
        }
        if (low.contains("bread") || low.contains("cake") || low.contains("bakery") || low.contains("pastry") || low.contains("toast") || low.contains("croissant")) {
            return "Bakery";
        }
        if (low.contains("milk") || low.contains("cheese") || low.contains("butter") || low.contains("dairy") || low.contains("yogurt") || low.contains("dahi") || low.contains("paneer")) {
            return "Dairy";
        }
        if (low.contains("beverage") || low.contains("drink") || low.contains("juice") || low.contains("tea") || low.contains("coffee") || low.contains("soda") || low.contains("water") || low.contains("cola")) {
            return "Beverages";
        }
        if (low.contains("soap") || low.contains("shampoo") || low.contains("toothpaste") || low.contains("skin") || low.contains("care") || low.contains("lotion") || low.contains("cream") || low.contains("deodorant")) {
            return "Personal Care";
        }
        if (low.contains("beauty") || low.contains("cosmetic") || low.contains("makeup") || low.contains("lipstick") || low.contains("perfume")) {
            return "Beauty & Cosmetics";
        }
        if (low.contains("detergent") || low.contains("cleaner") || low.contains("household") || low.contains("dishwash") || low.contains("floor cleaner")) {
            return "Household";
        }
        if (low.contains("kitchen") || low.contains("cookware") || low.contains("utensil") || low.contains("pan") || low.contains("appliance")) {
            return "Home & Kitchen";
        }
        if (low.contains("pharmacy") || low.contains("medicine") || low.contains("health") || low.contains("supplement") || low.contains("tablet") || low.contains("capsule")) {
            return "Health & Pharmacy";
        }
        if (low.contains("book") || low.contains("stationery") || low.contains("notebook") || low.contains("pen") || low.contains("pencil")) {
            return "Books & Stationery";
        }
        if (low.contains("toy") || low.contains("baby") || low.contains("game") || low.contains("doll") || low.contains("puzzle")) {
            return "Toys & Baby";
        }
        if (low.contains("sport") || low.contains("fitness") || low.contains("gym") || low.contains("yoga")) {
            return "Sports & Fitness";
        }
        if (low.contains("furniture") || low.contains("chair") || low.contains("table") || low.contains("desk")) {
            return "Furniture";
        }
        if (low.contains("fruit") || low.contains("vegetable") || low.contains("produce") || low.contains("fresh")) {
            return "Produce";
        }
        return getCategoryFromBrand(brand);
    }
}

package com.kryox.controller.Shopkeeper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kryox.controller.Customer.Chatbot;
import com.kryox.model.Shopkeeper.OrderItemModel;
import com.kryox.model.Shopkeeper.OrderModel;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.model.Shopkeeper.ShopkeeperModel;
import com.kryox.view.Shopkeeper.ViewConstants;

public class ShopkeeperChatbotController {

    public static class StoreSnapshot {
        public String shopName = "BuyNeX Store";
        public String ownerName = "Shopkeeper";
        public String category = "Retail";
        public int totalProducts = 0;
        public int lowStockCount = 0;
        public int outOfStockCount = 0;
        public List<String> lowStockItems = new ArrayList<>();
        public int totalOrders = 0;
        public int newOrdersCount = 0;
        public int preparingOrdersCount = 0;
        public int readyOrdersCount = 0;
        public int deliveredOrdersCount = 0;
        public double totalRevenue = 0.0;
        public double avgOrderValue = 0.0;
        public List<String> topSellingProducts = new ArrayList<>();
    }

    // 1. COMPUTE REAL-TIME STORE SNAPSHOT
    public static StoreSnapshot getStoreSnapshot() {
        StoreSnapshot snapshot = new StoreSnapshot();

        // Shopkeeper details
        ShopkeeperModel shopkeeper = ViewConstants.shopkeeperModel;
        if (shopkeeper != null) {
            if (shopkeeper.getShopNameValue() != null && !shopkeeper.getShopNameValue().isBlank()) {
                snapshot.shopName = shopkeeper.getShopNameValue().trim();
            }
            if (shopkeeper.getOwnerNameValue() != null && !shopkeeper.getOwnerNameValue().isBlank()) {
                snapshot.ownerName = shopkeeper.getOwnerNameValue().trim();
            }
            if (shopkeeper.getCategoryValue() != null && !shopkeeper.getCategoryValue().isBlank()) {
                snapshot.category = shopkeeper.getCategoryValue().trim();
            }
        }

        // Inventory data
        try {
            ArrayList<ProductModel> products = ProductController.fetchProducts();
            if (products != null) {
                snapshot.totalProducts = products.size();
                for (ProductModel p : products) {
                    if (p == null) continue;
                    int stock = p.getStockQuantity();
                    int limit = p.getLowStockLimit() > 0 ? p.getLowStockLimit() : 5;
                    String pName = p.getProductName() != null ? p.getProductName() : "Product";

                    if (stock == 0) {
                        snapshot.outOfStockCount++;
                        snapshot.lowStockItems.add(pName + " (OUT OF STOCK)");
                    } else if (stock <= limit) {
                        snapshot.lowStockCount++;
                        snapshot.lowStockItems.add(pName + " (" + stock + " left)");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Chatbot could not fetch products: " + e.getMessage());
        }

        // Orders & Revenue data
        try {
            ArrayList<OrderModel> orders = OrderController.getAllOrders();
            if (orders != null) {
                snapshot.totalOrders = orders.size();
                Map<String, Integer> productFrequency = new LinkedHashMap<>();

                for (OrderModel order : orders) {
                    if (order == null) continue;
                    String st = order.getOrderStatus() != null ? order.getOrderStatus().trim().toUpperCase() : "";

                    if ("NEW".equals(st)) {
                        snapshot.newOrdersCount++;
                    } else if ("PREPARING".equals(st)) {
                        snapshot.preparingOrdersCount++;
                    } else if ("READY".equals(st) || "REQUESTING_DELIVERY".equals(st)) {
                        snapshot.readyOrdersCount++;
                    } else if ("DELIVERED".equals(st) || "OUT_FOR_DELIVERY".equals(st)) {
                        snapshot.deliveredOrdersCount++;
                    }

                    if (!"DECLINED".equals(st) && !"CANCELLED".equals(st)) {
                        snapshot.totalRevenue += order.getTotalAmount();
                    }

                    // Count item popularity
                    if (order.getProducts() != null) {
                        for (OrderItemModel item : order.getProducts()) {
                            if (item != null && item.getProductName() != null && !item.getProductName().isBlank()) {
                                String name = item.getProductName().trim();
                                int qty = item.getQuantity() > 0 ? item.getQuantity() : 1;
                                productFrequency.put(name, productFrequency.getOrDefault(name, 0) + qty);
                            }
                        }
                    }
                }

                if (snapshot.totalOrders > 0) {
                    snapshot.avgOrderValue = snapshot.totalRevenue / snapshot.totalOrders;
                }

                // Sort top products
                List<Map.Entry<String, Integer>> list = new ArrayList<>(productFrequency.entrySet());
                list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
                for (int i = 0; i < Math.min(3, list.size()); i++) {
                    snapshot.topSellingProducts.add(list.get(i).getKey() + " (" + list.get(i).getValue() + " units)");
                }
            }
        } catch (Exception e) {
            System.out.println("Chatbot could not fetch orders: " + e.getMessage());
        }

        return snapshot;
    }

    // 2. MAIN METHOD TO GET BOT REPLY
    public static String getBotReply(String userMessage) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return "How can I assist you with your store today?";
        }

        String trimmed = userMessage.trim();
        String lower = trimmed.toLowerCase();
        StoreSnapshot snap = getStoreSnapshot();

        // 1. Attempt Gemini AI Response with Rich Real-time Store Context
        try {
            String systemPrompt = buildSystemPrompt(snap, trimmed);
            String aiResponse = Chatbot.getGeminiResponse(systemPrompt);

            if (aiResponse != null && !aiResponse.isBlank()
                    && !aiResponse.startsWith("Gemini API error")
                    && !aiResponse.startsWith("Gemini server")
                    && !aiResponse.startsWith("Gemini response")
                    && !aiResponse.startsWith("Sorry, Gemini")) {
                return aiResponse.trim();
            }
        } catch (Exception e) {
            System.out.println("Shopkeeper Gemini fallback triggered: " + e.getMessage());
        }

        // 2. Intelligent Dynamic Rule-Based Response using Real Data
        return generateDynamicFallback(snap, lower, trimmed);
    }

    private static String buildSystemPrompt(StoreSnapshot s, String userMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("You are BuyNeX AI, an intelligent AI business assistant for ").append(s.ownerName)
          .append(", owner of '").append(s.shopName).append("' (Category: ").append(s.category).append(").\n")
          .append("Here is the merchant's real-time live business snapshot:\n")
          .append("- Total Products in Catalog: ").append(s.totalProducts).append("\n")
          .append("- Low Stock Alerts: ").append(s.lowStockCount).append(" items, Out of Stock: ").append(s.outOfStockCount).append("\n");

        if (!s.lowStockItems.isEmpty()) {
            sb.append("  Low Stock Items: ").append(String.join(", ", s.lowStockItems.subList(0, Math.min(4, s.lowStockItems.size())))).append("\n");
        }

        sb.append("- Total Orders: ").append(s.totalOrders)
          .append(" (New: ").append(s.newOrdersCount)
          .append(", Preparing: ").append(s.preparingOrdersCount)
          .append(", Ready: ").append(s.readyOrdersCount).append(")\n")
          .append("- Total Revenue: ₹").append(String.format("%.2f", s.totalRevenue))
          .append(", Avg Order Value: ₹").append(String.format("%.2f", s.avgOrderValue)).append("\n");

        if (!s.topSellingProducts.isEmpty()) {
            sb.append("- Top Selling Products: ").append(String.join(", ", s.topSellingProducts)).append("\n");
        }

        sb.append("\nInstructions:\n")
          .append("- Answer the shopkeeper's query concisely, practically, and professionally (maximum 3-4 sentences or clear bullet points).\n")
          .append("- Reference their real store numbers when relevant to give concrete, actionable business advice.\n")
          .append("- Merchant query: \"").append(userMessage).append("\"\n")
          .append("Reply:");

        return sb.toString();
    }

    // 3. FALLBACK ENGINE BASED ON REAL LIVE DATA
    public static String generateDynamicFallback(StoreSnapshot s, String lower, String original) {
        // Sales / Revenue analysis
        if (lower.contains("sale") || lower.contains("revenue") || lower.contains("earning") || lower.contains("income") || lower.contains("turnover")) {
            return formatSalesAnalysis(s);
        }

        // Stock / Inventory
        if (lower.contains("stock") || lower.contains("inventory") || lower.contains("item") || lower.contains("product") || lower.contains("catalog") || lower.contains("restock")) {
            return formatStockStatus(s);
        }

        // Orders
        if (lower.contains("order") || lower.contains("pending") || lower.contains("preparing") || lower.contains("dispatch") || lower.contains("queue")) {
            return formatOrdersStatus(s);
        }

        // Top selling / Best seller
        if (lower.contains("top") || lower.contains("best") || lower.contains("popular") || lower.contains("demand")) {
            return formatTopSelling(s);
        }

        // Tips / Improvement / Growth
        if (lower.contains("tip") || lower.contains("growth") || lower.contains("improve") || lower.contains("advice") || lower.contains("suggest") || lower.contains("help")) {
            return formatGrowthTips(s);
        }

        // Shop / Profile Info
        if (lower.contains("shop") || lower.contains("store") || lower.contains("profile") || lower.contains("who are you") || lower.contains("name")) {
            return "🏪 Store Overview for " + s.shopName + ":\n"
                    + "• Owner: " + s.ownerName + "\n"
                    + "• Category: " + s.category + "\n"
                    + "• Catalog: " + s.totalProducts + " active products\n"
                    + "• Total Orders: " + s.totalOrders + " received\n"
                    + "• Cumulative Revenue: ₹" + String.format("%.2f", s.totalRevenue);
        }

        // Greetings
        if (lower.contains("hi") || lower.contains("hello") || lower.contains("hey") || lower.contains("namaste")) {
            return "Hello " + s.ownerName + "! 👋 Welcome to BuyNeX AI Assistant for " + s.shopName + ".\n\n"
                    + "I'm monitoring your live store data. You have " + s.newOrdersCount + " new orders waiting and "
                    + (s.lowStockCount + s.outOfStockCount) + " inventory items needing attention. How can I help you right now?";
        }

        // Default response
        return "🤖 BuyNeX AI Assistant:\n"
                + "I can help you monitor and grow " + s.shopName + "!\n\n"
                + "You can ask me to:\n"
                + "• 'Analyze my sales' - Review live revenue & order metrics\n"
                + "• 'Check my stock' - Inspect low-stock & out-of-stock items\n"
                + "• 'Pending orders' - Check active order fulfillment queue\n"
                + "• 'Top selling items' - See highest customer demand\n"
                + "• 'Growth tips' - Actionable tips to boost store revenue";
    }

    // 4. ACTION-SPECIFIC FORMATTERS
    public static String formatSalesAnalysis(StoreSnapshot s) {
        StringBuilder sb = new StringBuilder();
        sb.append("📊 Live Sales & Revenue Analysis (").append(s.shopName).append("):\n\n");
        sb.append("• Total Revenue: ₹").append(String.format("%.2f", s.totalRevenue)).append("\n");
        sb.append("• Total Orders: ").append(s.totalOrders).append("\n");
        sb.append("• Average Order Value (AOV): ₹").append(String.format("%.2f", s.avgOrderValue)).append("\n");
        sb.append("• Fulfilled / In-Transit: ").append(s.deliveredOrdersCount).append(" orders\n");

        if (!s.topSellingProducts.isEmpty()) {
            sb.append("• Top Selling Item: ").append(s.topSellingProducts.get(0)).append("\n");
        }

        if (s.totalRevenue > 0) {
            sb.append("\n💡 Insight: Your store is generating steady traction. Creating combo bundles with top items can increase your average order value!");
        } else {
            sb.append("\n💡 Insight: No completed orders recorded yet. Consider setting up a special launch discount in Offers to get your first orders!");
        }

        return sb.toString();
    }

    public static String formatStockStatus(StoreSnapshot s) {
        StringBuilder sb = new StringBuilder();
        sb.append("📦 Inventory & Stock Status (").append(s.shopName).append("):\n\n");
        sb.append("• Total Catalog Products: ").append(s.totalProducts).append("\n");
        sb.append("• Out of Stock: ").append(s.outOfStockCount).append(" items\n");
        sb.append("• Low Stock Alert: ").append(s.lowStockCount).append(" items\n");

        if (!s.lowStockItems.isEmpty()) {
            sb.append("\n⚠️ Items Needing Attention:\n");
            int limit = Math.min(5, s.lowStockItems.size());
            for (int i = 0; i < limit; i++) {
                sb.append("  - ").append(s.lowStockItems.get(i)).append("\n");
            }
            if (s.lowStockItems.size() > 5) {
                sb.append("  - ...and ").append(s.lowStockItems.size() - 5).append(" more items.\n");
            }
            sb.append("\n🔔 Recommendation: Restock these items soon to prevent missed customer orders.");
        } else if (s.totalProducts > 0) {
            sb.append("\n✅ All inventory levels are healthy! No immediate restock needed.");
        } else {
            sb.append("\n⚠️ Your catalog is currently empty. Add products from the Inventory tab to start selling!");
        }

        return sb.toString();
    }

    public static String formatOrdersStatus(StoreSnapshot s) {
        StringBuilder sb = new StringBuilder();
        sb.append("🛒 Live Order Fulfillment Queue:\n\n");
        sb.append("• 🆕 New Orders (Pending Acceptance): ").append(s.newOrdersCount).append("\n");
        sb.append("• ⏳ Preparing Orders: ").append(s.preparingOrdersCount).append("\n");
        sb.append("• 📦 Ready for Dispatch: ").append(s.readyOrdersCount).append("\n");
        sb.append("• 🚴 Delivered / Out: ").append(s.deliveredOrdersCount).append("\n");

        if (s.newOrdersCount > 0) {
            sb.append("\n⚡ Action Needed: You have ").append(s.newOrdersCount)
              .append(" new order(s) waiting! Visit the Orders tab to accept and start preparing.");
        } else {
            sb.append("\n✨ Your order queue is up-to-date! Ready to receive incoming orders.");
        }

        return sb.toString();
    }

    public static String formatTopSelling(StoreSnapshot s) {
        StringBuilder sb = new StringBuilder();
        sb.append("🏆 Top Selling Products (").append(s.shopName).append("):\n\n");

        if (!s.topSellingProducts.isEmpty()) {
            for (int i = 0; i < s.topSellingProducts.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(s.topSellingProducts.get(i)).append("\n");
            }
            sb.append("\n🎯 Tip: Keep these bestsellers in high stock and position related accessories for upselling!");
        } else {
            sb.append("• Not enough order history yet to determine top sellers.\n")
              .append("• Once customer orders come in, best-performing items will rank here automatically.");
        }

        return sb.toString();
    }

    public static String formatGrowthTips(StoreSnapshot s) {
        StringBuilder sb = new StringBuilder();
        sb.append("💡 AI Business Growth Recommendations:\n\n");

        if (s.lowStockCount > 0 || s.outOfStockCount > 0) {
            sb.append("1. Restock Critical Inventory: ").append(s.lowStockCount + s.outOfStockCount)
              .append(" items are low or out of stock. Keeping products in stock maintains your search visibility.\n\n");
        } else {
            sb.append("1. Catalog Expansion: Your stock health is great! Consider adding 3-5 complementary seasonal items.\n\n");
        }

        if (s.totalRevenue > 0) {
            sb.append("2. Bundle Best Sellers: Pair high-volume items with higher-margin products into combo deals.\n\n");
        } else {
            sb.append("2. Launch Promotions: Use the Offers page to activate a 10% welcome discount to attract initial buyers.\n\n");
        }

        sb.append("3. Fast Order Handover: Keeping preparation time under 15 minutes boosts your BuyNeX store rating and algorithmic ranking.");

        return sb.toString();
    }
}

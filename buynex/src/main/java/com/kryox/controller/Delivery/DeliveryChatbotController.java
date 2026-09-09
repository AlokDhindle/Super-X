package com.kryox.controller.Delivery;

import com.kryox.controller.Customer.Chatbot;

public class DeliveryChatbotController {

    public static String getBotReply(String channelId, String userMessage, String partnerName) {
        if (userMessage == null || userMessage.trim().isEmpty()) {
            return "How can I help you on your active delivery run?";
        }

        String trimmed = userMessage.trim();
        String lower = trimmed.toLowerCase();

        // 1. Try Gemini AI if available
        try {
            String systemPrompt = buildSystemPrompt(channelId, partnerName, trimmed);
            String aiResponse = Chatbot.getGeminiResponse(systemPrompt);

            if (aiResponse != null && !aiResponse.isBlank()
                    && !aiResponse.startsWith("Gemini API error")
                    && !aiResponse.startsWith("Gemini server")
                    && !aiResponse.startsWith("Gemini response")) {
                return aiResponse.trim();
            }
        } catch (Exception e) {
            System.out.println("Gemini delivery bot fallback triggered: " + e.getMessage());
        }

        // 2. Intelligent Dynamic Rule-Based Response Fallback
        return generateDynamicFallback(channelId, lower, partnerName, trimmed);
    }

    private static String buildSystemPrompt(String channelId, String partnerName, String userMessage) {
        String role;
        if ("CH-CUSTOMER".equalsIgnoreCase(channelId)) {
            role = "You are Sarah J., the customer who ordered Order #BN-4920 on BuyNeX. You live at Door 4B, Elmwood Heights. Respond politely and concisely (1-2 sentences) to delivery rider " + partnerName + ".";
        } else if ("CH-OPS".equalsIgnoreCase(channelId)) {
            role = "You are BuyNeX 24x7 Safety & Roadside Breakdown Operations Hub. Respond urgently and clearly (1-2 sentences) to delivery rider " + partnerName + " regarding safety, vehicle issues, or delays.";
        } else if ("CH-DISPATCH".equalsIgnoreCase(channelId)) {
            role = "You are Agent Riya from BuyNeX Central Dispatch Support Desk. Help delivery partner " + partnerName + " with order routing, gate codes, customer issues, or ETA updates in 1-2 practical sentences.";
        } else {
            role = "You are BuyNeX Delivery AI Assistant for delivery partners. Answer questions concisely (2 sentences max) regarding routes, payouts, order status, app features, or delivery protocols for rider " + partnerName + ".";
        }

        return role + "\nDelivery Partner Message: \"" + userMessage + "\"\nReply:";
    }

    private static String generateDynamicFallback(String channelId, String lower, String partnerName, String original) {
        // Customer Channel Persona
        if ("CH-CUSTOMER".equalsIgnoreCase(channelId)) {
            if (lower.contains("reach") || lower.contains("here") || lower.contains("outside") || lower.contains("arrived")) {
                return "Thank you " + partnerName + "! Please leave it by door 4B. The doorbell is working, appreciate the quick delivery!";
            }
            if (lower.contains("code") || lower.contains("gate") || lower.contains("entry") || lower.contains("security")) {
                return "The building gate code is #0842. If it doesn't open, dial 402 on the intercom and I'll buzz you in!";
            }
            if (lower.contains("call") || lower.contains("phone") || lower.contains("number")) {
                return "I'm available on phone at 98765-43210. Just ringing the bell is also fine!";
            }
            if (lower.contains("delay") || lower.contains("traffic") || lower.contains("late") || lower.contains("eta")) {
                return "No worries at all! Drive safely, thanks for updating me.";
            }
            return "Thanks for the update, " + partnerName + "! Looking forward to the package.";
        }

        // Safety & Breakdown Ops Persona
        if ("CH-OPS".equalsIgnoreCase(channelId)) {
            if (lower.contains("breakdown") || lower.contains("puncture") || lower.contains("bike") || lower.contains("scooter") || lower.contains("accident")) {
                return "🚨 Safety alert logged for rider " + partnerName + ". We have dispatched roadside recovery. If you need immediate towing or ambulance, call our emergency hotline: 1800-419-7890.";
            }
            if (lower.contains("delay") || lower.contains("stuck") || lower.contains("rain") || lower.contains("weather")) {
                return "Weather/Transit delay registered. Your on-time delivery score is protected under bad weather exemption.";
            }
            if (lower.contains("order") || lower.contains("reassign")) {
                return "Order #BN-4920 has been temporarily paused. Another partner within 1 km is on standby to take over if you cannot proceed.";
            }
            return "24x7 Safety Hub standing by. Your live GPS coordinates are being tracked. Press Emergency Helpline if you need assistance.";
        }

        // Dispatch Desk Persona
        if ("CH-DISPATCH".equalsIgnoreCase(channelId)) {
            if (lower.contains("gate") || lower.contains("code") || lower.contains("entry")) {
                return "Gate code confirmed: #0842 (Building Elmwood Heights, Block B). Verified with customer records.";
            }
            if (lower.contains("unreachable") || lower.contains("not picking") || lower.contains("no answer")) {
                return "Dispatch is calling customer Sarah J. now. Please wait 3 minutes; if still unreachable, doorstep drop is approved.";
            }
            if (lower.contains("address") || lower.contains("location") || lower.contains("map") || lower.contains("wrong")) {
                return "Updated delivery landmark: Next to Green Valley Community Park, entrance opposite Elmwood Library. GPS pin refreshed.";
            }
            if (lower.contains("delay") || lower.contains("traffic")) {
                return "Traffic delay noted (+12 mins). Customer's live ETA has been updated automatically.";
            }
            if (lower.contains("complete") || lower.contains("delivered") || lower.contains("done")) {
                return "Great job, " + partnerName + "! Order #BN-4920 marked as completed. Searching for next high-payout batch nearby...";
            }
            return "Dispatch Desk Agent Riya here: All systems green on your route. Let us know if you need customer intervention.";
        }

        // AI Assistant Channel Persona (CH-AI-BOT or default)
        if (lower.contains("payout") || lower.contains("earning") || lower.contains("money") || lower.contains("payment") || lower.contains("salary")) {
            return "💰 Payouts are settled every Monday directly into your verified bank account. Today you have completed 3 deliveries with ₹420 estimated earnings + ₹50 peak surge bonus!";
        }
        if (lower.contains("gate") || lower.contains("code")) {
            return "🚪 Entry gate code for active order #BN-4920 is #0842. Tower B, 4th floor.";
        }
        if (lower.contains("unreachable") || lower.contains("customer")) {
            return "📞 Protocol: Ring twice, wait 3 minutes. If unanswered, you can leave the order at doorstep 4B and upload a delivery confirmation photo.";
        }
        if (lower.contains("breakdown") || lower.contains("emergency")) {
            return "⚡ Roadside Assistance is available 24x7. Switch to the Safety & Breakdown tab or dial 1800-419-7890.";
        }
        if (lower.contains("offline") || lower.contains("online") || lower.contains("break")) {
            return "⏱ You can toggle your status to OFFLINE from the dashboard toggle once active order #BN-4920 is delivered.";
        }
        if (lower.contains("hi") || lower.contains("hello") || lower.contains("hey")) {
            return "Hello " + partnerName + "! 🤖 I am your BuyNeX Delivery AI Assistant. Ask me about your active order, navigation shortcuts, payouts, or emergency protocols!";
        }

        return "🤖 AI Dispatch Assistant: I understand your request regarding \"" + original + "\". Your active delivery status is healthy. For order rerouting or customer escalation, I can notify dispatch immediately.";
    }
}

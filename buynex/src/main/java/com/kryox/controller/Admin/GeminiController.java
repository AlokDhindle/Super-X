package com.kryox.controller.Admin;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kryox.config.GeminiConfig;


public class GeminiController {

    private final HttpClient client = HttpClient.newHttpClient();

    public String sendMessage(String userText) {

        try {

            String apiKey = GeminiConfig.getApiKey();

            String systemPrompt =
                    "You are the AI Assistant for the BuyNeX application. " +
                    "Only answer questions related to BuyNeX, its admin dashboard, " +
                    "customers, shops, shop verification, products, inventory, orders, " +
                    "offers, campaigns, analytics, reports, notifications, documents, " +
                    "support tickets, settings, Firebase authentication, Cloudinary uploads, " +
                    "JavaFX UI, and BuyNeX project development. " +
                    "Do not answer unrelated questions. " +
                    "If the question is unrelated to BuyNeX, reply exactly: " +
                    "'Sorry, I can only help with BuyNeX related queries.'";

            String requestBody =
                    "{" +
                    "\"system_instruction\":{" +
                    "\"parts\":[{" +
                    "\"text\":\"" +
                    escapeJson(systemPrompt) +
                    "\"" +
                    "}]" +
                    "}," +

                    "\"contents\":[{" +
                    "\"parts\":[{" +
                    "\"text\":\"" +
                    escapeJson(userText) +
                    "\"" +
                    "}]" +
                    "}]" +
                    "}";

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.1-flash-lite:generateContent"
                                    )
                            )
                            .header("x-goog-api-key", apiKey)
                            .header("Content-Type", "application/json")
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            requestBody
                                    )
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            // Gemini response JSON
            JSONObject jsonResponse =
                    new JSONObject(response.body());

            // candidates array
            JSONArray candidates =jsonResponse.getJSONArray("candidates");

            // first candidate
            JSONObject candidate =candidates.getJSONObject(0);

            // content
            JSONObject content =candidate.getJSONObject("content");

            // parts array
            JSONArray parts =content.getJSONArray("parts");

            String answer =parts.getJSONObject(0).getString("text");

            return answer;

        } catch (Exception e) {

            e.printStackTrace();

            return "Error connecting to Gemini.";
        }
    }


    private String escapeJson(String text) {

        if (text == null) {
            return "";
        }

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
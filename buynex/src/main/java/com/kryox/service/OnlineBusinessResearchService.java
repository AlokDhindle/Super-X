package com.kryox.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kryox.config.APIKEYconfig;

/**
 * OnlineBusinessResearchService
 *
 * Purpose:
 * ------------
 * Searches online business knowledge relevant to the
 * shopkeeper's business.
 *
 * IMPORTANT:
 * This class does NOT generate the video.
 *
 * It only collects business-related knowledge which
 * will later be used by BusinessAnalysisService.
 */
public class OnlineBusinessResearchService {

    private static final HttpClient HTTP_CLIENT =
            HttpClient.newHttpClient();

    private OnlineBusinessResearchService() {
    }

    /**
     * Research relevant business knowledge.
     *
     * Gemini is asked to identify useful online business
     * resources/topics for the particular shop.
     *
     * @param shopName shop name
     * @param category shop/business category
     * @param description shop description
     * @return researched business knowledge
     */
    public static String researchBusiness(
            String shopName,
            String category,
            String description) {

        try {

            String safeShopName =
                    safe(shopName);

            String safeCategory =
                    safe(category);

            String prompt =
                    "You are a retail business research assistant.\n"
                    + "Shop Name: " + safeShopName + "\n"
                    + "Category: " + safeCategory + "\n\n"
                    + "Provide 3 concise, high-impact retail best practices in bullet points (under 60 words).";

            String result = callGemini(prompt);
            if (result != null && !result.isBlank()) {
                return result.trim();
            }

        } catch (Exception e) {
            System.out.println(
                    "ONLINE BUSINESS RESEARCH ERROR (Using fast smart fallback): " + e.getMessage()
            );
        }

        return "1. Strategic visual merchandising: Place top-selling items at eye level.\n"
             + "2. Transparent pricing & bundles: Clearly label prices and offer value combos.\n"
             + "3. Customer engagement: Provide express checkout and localized loyalty perks.";
    }


    /**
     * Gemini API request.
     */
    private static String callGemini(
            String prompt)
            throws Exception {

        String apiKey =
                APIKEYconfig.GEMINI_API_KEY;

        if (apiKey == null ||
                apiKey.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Gemini API key is missing."
            );
        }

        JSONObject textPart =
                new JSONObject()
                        .put(
                                "text",
                                prompt
                        );

        JSONArray parts =
                new JSONArray()
                        .put(textPart);

        JSONObject content =
                new JSONObject()
                        .put(
                                "parts",
                                parts
                        );

        JSONArray contents =
                new JSONArray()
                        .put(content);

        JSONObject genConfig =
                new JSONObject()
                        .put("maxOutputTokens", 200)
                        .put("temperature", 0.3);

        JSONObject requestBody =
                new JSONObject()
                        .put(
                                "contents",
                                contents
                        )
                        .put(
                                "generationConfig",
                                genConfig
                        );

        String url =
        "https://generativelanguage.googleapis.com/"
        + "v1beta/models/gemini-flash-lite-latest:generateContent"
        + "?key="
        + apiKey;

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(url)
                        )
                        .timeout(
                                java.time.Duration.ofSeconds(3)
                        )
                        .header(
                                "Content-Type",
                                "application/json"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(
                                                requestBody.toString()
                                        )
                        )
                        .build();

        HttpResponse<String> response =
                HTTP_CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );

        System.out.println(
                "Gemini Research HTTP Status: "
                        + response.statusCode()
        );

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            System.out.println(
                    "Gemini Research Response:"
            );

            System.out.println(
                    response.body()
            );

            throw new RuntimeException(
                    "Online business research failed. HTTP "
                            + response.statusCode()
            );
        }

        JSONObject result =
                new JSONObject(
                        response.body()
                );

        JSONArray candidates =
                result.optJSONArray(
                        "candidates"
                );

        if (candidates == null ||
                candidates.length() == 0) {

            return "";
        }

        JSONObject candidate =
                candidates.getJSONObject(0);

        JSONObject content1 =
                candidate.optJSONObject(
                        "content"
                );

        if (content1 == null) {
            return "";
        }

        JSONArray responseParts =
                content1.optJSONArray(
                        "parts"
                );

        if (responseParts == null ||
                responseParts.length() == 0) {

            return "";
        }

        return responseParts
                .getJSONObject(0)
                .optString(
                        "text",
                        ""
                );
    }


    private static String safe(
            String value) {

        if (value == null ||
                value.isBlank()) {

            return "Not provided";
        }

        return value.trim();
    }
    public static String generateAnalysisWithGemini(
        String prompt)
        throws Exception {

    return callGemini(prompt);
}
}
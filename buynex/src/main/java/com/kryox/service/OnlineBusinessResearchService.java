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

            String safeDescription =
                    safe(description);

            String prompt =
                    "You are a business research assistant.\n\n"

                    + "We are analysing a shopkeeper's business.\n\n"

                    + "Shop Name: "
                    + safeShopName
                    + "\n"

                    + "Business Category: "
                    + safeCategory
                    + "\n"

                    + "Shop Description: "
                    + safeDescription
                    + "\n\n"

                    + "Research current and reliable online "
                    + "business knowledge relevant to this "
                    + "type of shop.\n\n"

                    + "Focus only on:\n"
                    + "1. Retail business improvement\n"
                    + "2. Store/shop improvement\n"
                    + "3. Product presentation\n"
                    + "4. Product assortment\n"
                    + "5. Inventory practices\n"
                    + "6. Pricing practices\n"
                    + "7. Customer experience\n"
                    + "8. Product visibility\n"
                    + "9. Digital/online selling improvement\n"
                    + "10. Marketing practices\n\n"

                    + "Use publicly available online business "
                    + "books, guides, articles and recognised "
                    + "business resources as knowledge sources.\n\n"

                    + "Do NOT invent a source.\n"
                    + "Do NOT reproduce copyrighted books.\n"
                    + "Return concise business principles and "
                    + "source names/URLs where available.\n";

            return callGemini(prompt);

        } catch (Exception e) {

            System.out.println(
                    "ONLINE BUSINESS RESEARCH ERROR"
            );

            e.printStackTrace();

            return "";
        }
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

        JSONObject requestBody =
                new JSONObject()
                        .put(
                                "contents",
                                contents
                        );

        String url =
        "https://generativelanguage.googleapis.com/"
        + "v1beta/models/gemini-3.6-flash:generateContent"
        + "?key="
        + apiKey;

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(url)
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
package com.kryox.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.kryox.config.APIKEYconfig;
import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.model.Shopkeeper.ShopkeeperModel;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * ============================================================
 * BUSINESS ANALYSIS SERVICE
 * ============================================================
 *
 * This service compares:
 *
 * 1. Actual Shopkeeper Data
 * 2. Actual Product Data
 * 3. Online Business Knowledge
 *
 * and generates:
 *
 * - Business Summary
 * - Current Strengths
 * - Business Gaps
 * - Product Improvements
 * - Shop Improvements
 * - Customer Experience Improvements
 * - Priority Actions
 * - Video Story Points
 *
 * NOTE:
 * This class does NOT generate the video.
 *
 * ============================================================
 */
public class BusinessAnalysisService {

    private static final HttpClient HTTP_CLIENT =
            HttpClient.newHttpClient();


    // ============================================================
    // PRIVATE CONSTRUCTOR
    // ============================================================

    private BusinessAnalysisService() {
    }


    // ============================================================
    // MAIN BUSINESS ANALYSIS METHOD
    // ============================================================

    public static String analyseBusiness(
            ShopkeeperModel shopkeeper,
            ArrayList<ProductModel> products,
            String onlineResearch) {

        try {

            // ----------------------------------------------------
            // VALIDATE SHOPKEEPER
            // ----------------------------------------------------

            if (shopkeeper == null) {

                throw new IllegalArgumentException(
                        "Shopkeeper data is missing."
                );
            }


            // ----------------------------------------------------
            // VALIDATE PRODUCTS
            // ----------------------------------------------------

            if (products == null) {

                products =
                        new ArrayList<>();
            }


            // ----------------------------------------------------
            // BUILD SHOP DATA
            // ----------------------------------------------------

            String shopData =
                    buildShopData(
                            shopkeeper
                    );


            // ----------------------------------------------------
            // BUILD PRODUCT DATA
            // ----------------------------------------------------

            String productData =
                    buildProductData(
                            products
                    );


            // ----------------------------------------------------
            // ONLINE RESEARCH
            // ----------------------------------------------------

            String research =
                    onlineResearch == null
                            ? ""
                            : onlineResearch.trim();


            if (research.isEmpty()) {

                research =
                        "No online business research was available.";
            }


            // ----------------------------------------------------
            // CREATE AI PROMPT
            // ----------------------------------------------------

            String prompt =
                    buildAnalysisPrompt(
                            shopData,
                            productData,
                            research
                    );


            // ----------------------------------------------------
            // CALL GEMINI
            // ----------------------------------------------------

            String result =
                    callGemini(
                            prompt
                    );


            if (result == null ||
                    result.trim().isEmpty()) {

                throw new IllegalStateException(
                        "AI business analysis returned empty result."
                );
            }


            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "BUSINESS ANALYSIS COMPLETED"
            );

            System.out.println(
                    "========================================"
            );


            return result;


        } catch (Exception e) {

            System.out.println(
                    "========================================"
            );

            System.out.println(
                    "BUSINESS ANALYSIS ERROR"
            );

            System.out.println(
                    "========================================"
            );

            e.printStackTrace();

            return "";
        }
    }


    // ============================================================
    // BUILD SHOP DATA
    // ============================================================

    private static String buildShopData(
            ShopkeeperModel shopkeeper) {

        StringBuilder data =
                new StringBuilder();


        data.append(
                "Shop Name: "
        );

        data.append(
                safe(
                        shopkeeper.getShopNameValue()
                )
        );

        data.append("\n");


        data.append(
                "Owner Name: "
        );

        data.append(
                safe(
                        shopkeeper.getOwnerNameValue()
                )
        );

        data.append("\n");


        data.append(
                "Business Category: "
        );

        data.append(
                safe(
                        shopkeeper.getCategoryValue()
                )
        );

        data.append("\n");


        data.append(
                "Business Description: "
        );

        data.append(
                safe(
                        shopkeeper.getDescriptionValue()
                )
        );

        data.append("\n");


        data.append(
                "Shop Address: "
        );

        data.append(
                safe(
                        shopkeeper.getAddressValue()
                )
        );

        data.append("\n");


        data.append(
                "City: "
        );

        data.append(
                safe(
                        shopkeeper.getCityValue()
                )
        );

        data.append("\n");


        data.append(
                "State: "
        );

        data.append(
                safe(
                        shopkeeper.getStateValue()
                )
        );

        data.append("\n");


        data.append(
                "PIN Code: "
        );

        data.append(
                safe(
                        shopkeeper.getPinValue()
                )
        );

        data.append("\n");


        /*
         * Shop photo and logo URLs are included because
         * they may be useful later for personalized
         * video generation.
         */

        data.append(
                "Shop Photo URL: "
        );

        data.append(
                safe(
                        shopkeeper.getShopPhotoURL()
                )
        );

        data.append("\n");


        data.append(
                "Shop Logo URL: "
        );

        data.append(
                safe(
                        shopkeeper.getShopLogoURL()
                )
        );


        return data.toString();
    }


    // ============================================================
    // BUILD PRODUCT DATA
    // ============================================================

    private static String buildProductData(
            ArrayList<ProductModel> products) {

        StringBuilder data =
                new StringBuilder();


        // --------------------------------------------------------
        // NO PRODUCTS
        // --------------------------------------------------------

        if (products.isEmpty()) {

            return "No products available.";
        }


        int productNumber = 1;


        // --------------------------------------------------------
        // LOOP THROUGH PRODUCTS
        // --------------------------------------------------------

        for (ProductModel product :
                products) {


            if (product == null) {
                continue;
            }


            data.append(
                    "\n----------------------------------------\n"
            );


            data.append(
                    "PRODUCT "
                            + productNumber
                            + "\n"
            );


            // ----------------------------------------------------
            // PRODUCT NAME
            // ----------------------------------------------------

            data.append(
                    "Product Name: "
            );

            data.append(
                    safe(
                            product.getProductName()
                    )
            );

            data.append("\n");


            // ----------------------------------------------------
            // CATEGORY
            // ----------------------------------------------------

            data.append(
                    "Category: "
            );

            data.append(
                    safe(
                            product.getCategory()
                    )
            );

            data.append("\n");


            // ----------------------------------------------------
            // SKU
            // ----------------------------------------------------

            data.append(
                    "SKU: "
            );

            data.append(
                    safe(
                            product.getSku()
                    )
            );

            data.append("\n");


            // ----------------------------------------------------
            // SELLING PRICE
            // ----------------------------------------------------

            data.append(
                    "Selling Price: "
            );

            if (product.getSellingPrice() != null) {

                data.append(
                        product.getSellingPrice()
                );

            } else {

                data.append(
                        "Not provided"
                );
            }

            data.append("\n");


            // ----------------------------------------------------
            // STOCK
            // ----------------------------------------------------

            data.append(
                    "Stock Quantity: "
            );

            data.append(
                    product.getStockQuantity()
            );

            data.append("\n");


            // ----------------------------------------------------
            // UNIT
            // ----------------------------------------------------

            data.append(
                    "Unit: "
            );

            data.append(
                    safe(
                            product.getUnit()
                    )
            );

            data.append("\n");


            // ----------------------------------------------------
            // EXPIRY TRACKING
            // ----------------------------------------------------

            data.append(
                    "Expiry Tracking: "
            );

            data.append(
                    safe(
                            product.getExpiryTracking()
                    )
            );

            data.append("\n");


            productNumber++;
        }


        return data.toString();
    }


    // ============================================================
    // BUILD ANALYSIS PROMPT
    // ============================================================

    private static String buildAnalysisPrompt(
            String shopData,
            String productData,
            String onlineResearch) {


        StringBuilder prompt =
                new StringBuilder();


        prompt.append(
                "You are an expert retail business "
                + "improvement analyst.\n\n"
        );


        prompt.append(
                "Your task is to analyse a real shopkeeper's "
                + "business and identify practical ways to "
                + "improve the shop and its products.\n\n"
        );


        // ========================================================
        // ACTUAL SHOP DATA
        // ========================================================

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                "ACTUAL SHOPKEEPER AND SHOP DATA\n"
        );

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                shopData
        );

        prompt.append(
                "\n\n"
        );


        // ========================================================
        // ACTUAL PRODUCT DATA
        // ========================================================

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                "ACTUAL PRODUCT DATA FROM FIRESTORE\n"
        );

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                productData
        );

        prompt.append(
                "\n\n"
        );


        // ========================================================
        // ONLINE BUSINESS KNOWLEDGE
        // ========================================================

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                "ONLINE BUSINESS KNOWLEDGE\n"
        );

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                onlineResearch
        );

        prompt.append(
                "\n\n"
        );


        // ========================================================
        // COMPARISON TASK
        // ========================================================

        prompt.append(
                "========================================\n"
        );

        prompt.append(
                "COMPARISON TASK\n"
        );

        prompt.append(
                "========================================\n\n"
        );


        prompt.append(
                "Compare the actual shop and actual products "
                + "with the online business knowledge.\n\n"
        );


        prompt.append(
                "Identify the gaps between the current "
                + "business situation and recommended "
                + "business practices.\n\n"
        );


        prompt.append(
                "Only provide recommendations that are "
                + "relevant to this particular shop.\n\n"
        );


        prompt.append(
                "Do not invent information about the shop.\n\n"
        );


        prompt.append(
                "If a required piece of information is not "
                + "available, clearly say that it is not "
                + "available instead of assuming it.\n\n"
        );


        // ========================================================
        // REQUIRED OUTPUT
        // ========================================================

        prompt.append(
                "Return the analysis using exactly these "
                + "sections:\n\n"
        );


        prompt.append(
                "1. BUSINESS SUMMARY\n"
        );


        prompt.append(
                "Give a short summary of the current shop "
                + "and business.\n\n"
        );


        prompt.append(
                "2. CURRENT STRENGTHS\n"
        );


        prompt.append(
                "Identify strengths visible from the "
                + "provided shop and product data.\n\n"
        );


        prompt.append(
                "3. IDENTIFIED GAPS\n"
        );


        prompt.append(
                "Identify important gaps between the current "
                + "business and recommended practices.\n\n"
        );


        prompt.append(
                "4. PRODUCT IMPROVEMENTS\n"
        );


        prompt.append(
                "Explain which product-related areas can "
                + "be improved and why.\n\n"
        );


        prompt.append(
                "5. SHOP IMPROVEMENTS\n"
        );


        prompt.append(
                "Explain which shop/business areas can "
                + "be improved and why.\n\n"
        );


        prompt.append(
                "6. CUSTOMER EXPERIENCE IMPROVEMENTS\n"
        );


        prompt.append(
                "Suggest practical improvements for customer "
                + "experience where relevant.\n\n"
        );


        prompt.append(
                "7. PRIORITY ACTIONS\n"
        );


        prompt.append(
                "Give the most important actions in priority "
                + "order.\n\n"
        );


        prompt.append(
                "8. VIDEO STORY POINTS\n"
        );


        prompt.append(
                "Create practical visual story points that "
                + "can later be converted into an AI-generated "
                + "business improvement video.\n\n"
        );


        prompt.append(
                "The video story should show:\n"
        );

        prompt.append(
                "- Current situation\n"
        );

        prompt.append(
                "- Identified problem\n"
        );

        prompt.append(
                "- Recommended improvement\n"
        );

        prompt.append(
                "- How the improvement should be implemented\n"
        );

        prompt.append(
                "- Expected business benefit\n"
        );


        return prompt.toString();
    }


    // ============================================================
    // GEMINI API
    // ============================================================

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


        // --------------------------------------------------------
        // TEXT PART
        // --------------------------------------------------------

        JSONObject textPart =
                new JSONObject();

        textPart.put(
                "text",
                prompt
        );


        // --------------------------------------------------------
        // PARTS ARRAY
        // --------------------------------------------------------

        JSONArray parts =
                new JSONArray();

        parts.put(
                textPart
        );


        // --------------------------------------------------------
        // CONTENT
        // --------------------------------------------------------

        JSONObject content =
                new JSONObject();

        content.put(
                "parts",
                parts
        );


        // --------------------------------------------------------
        // CONTENTS
        // --------------------------------------------------------

        JSONArray contents =
                new JSONArray();

        contents.put(
                content
        );


        // --------------------------------------------------------
        // REQUEST BODY
        // --------------------------------------------------------

        JSONObject requestBody =
                new JSONObject();

        requestBody.put(
                "contents",
                contents
        );


        // --------------------------------------------------------
        // GEMINI URL
        // --------------------------------------------------------

        String url =
        "https://generativelanguage.googleapis.com/"
        + "v1beta/models/gemini-3.6-flash:generateContent"
        + "?key="
        + apiKey;


        // --------------------------------------------------------
        // HTTP REQUEST
        // --------------------------------------------------------

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


        // --------------------------------------------------------
        // SEND REQUEST
        // --------------------------------------------------------

        HttpResponse<String> response =
                HTTP_CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );


        System.out.println(
                "Gemini Analysis HTTP Status: "
                        + response.statusCode()
        );


        // --------------------------------------------------------
        // ERROR RESPONSE
        // --------------------------------------------------------

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            System.out.println(
                    "Gemini Analysis Error:"
            );

            System.out.println(
                    response.body()
            );

            throw new RuntimeException(
                    "Gemini analysis failed. HTTP "
                            + response.statusCode()
            );
        }


        // --------------------------------------------------------
        // PARSE RESPONSE
        // --------------------------------------------------------

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
                candidates.getJSONObject(
                        0
                );


        JSONObject responseContent =
                candidate.optJSONObject(
                        "content"
                );


        if (responseContent == null) {

            return "";
        }


        JSONArray responseParts =
                responseContent.optJSONArray(
                        "parts"
                );


        if (responseParts == null ||
                responseParts.length() == 0) {

            return "";
        }


        StringBuilder answer =
                new StringBuilder();


        for (int i = 0;
                i < responseParts.length();
                i++) {

            JSONObject part =
                    responseParts
                            .optJSONObject(i);

            if (part == null) {
                continue;
            }


            String text =
                    part.optString(
                            "text",
                            ""
                    );


            if (!text.isEmpty()) {

                if (answer.length() > 0) {

                    answer.append(
                            "\n"
                    );
                }

                answer.append(
                        text
                );
            }
        }


        return answer.toString();
    }


    // ============================================================
    // SAFE STRING
    // ============================================================

    private static String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }

        return value.trim();
    }
}
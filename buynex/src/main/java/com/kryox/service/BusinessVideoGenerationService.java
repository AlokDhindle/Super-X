package com.kryox.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

import com.kryox.config.APIKEYconfig;

public class BusinessVideoGenerationService {

    private static final HttpClient CLIENT =
            HttpClient.newHttpClient();

    private static final String BASE_URL =
            "https://apis.viggle.ai";

    private BusinessVideoGenerationService() {
    }


    // ============================================================
    // CREATE VIDEO PROMPT
    // ============================================================

    public static String createVideoPrompt(
            String shopName,
            String category,
            String analysis) {

        StringBuilder prompt =
                new StringBuilder();

        prompt.append(
                "Create a professional business improvement "
                + "video for a retail shop.\n\n"
        );

        prompt.append(
                "Shop Name: "
        );

        prompt.append(
                safe(shopName)
        );

        prompt.append("\n");

        prompt.append(
                "Business Category: "
        );

        prompt.append(
                safe(category)
        );

        prompt.append("\n\n");

        prompt.append(
                "Business Analysis:\n"
        );

        prompt.append(
                safe(analysis)
        );

        prompt.append("\n\n");


        // ========================================================
        // OBJECTIVE
        // ========================================================

        prompt.append(
                "OBJECTIVE:\n"
        );

        prompt.append(
                "Create a practical visual explanation showing "
                + "how this shopkeeper can improve the business "
                + "based on the provided analysis.\n\n"
        );


        // ========================================================
        // VIDEO STORY
        // ========================================================

        prompt.append(
                "VIDEO STORY:\n"
        );

        prompt.append(
                "Show the current shop situation.\n"
        );

        prompt.append(
                "Show the identified business problem.\n"
        );

        prompt.append(
                "Show the recommended improvement.\n"
        );

        prompt.append(
                "Demonstrate how the shopkeeper can implement "
                + "the improvement.\n"
        );

        prompt.append(
                "Show the expected positive business outcome.\n\n"
        );


        // ========================================================
        // VISUAL STYLE
        // ========================================================

        prompt.append(
                "VISUAL STYLE:\n"
        );

        prompt.append(
                "Modern retail store.\n"
        );

        prompt.append(
                "Professional business presentation.\n"
        );

        prompt.append(
                "Realistic retail environment.\n"
        );

        prompt.append(
                "Clear visual storytelling.\n"
        );

        prompt.append(
                "Clean product presentation.\n"
        );

        prompt.append(
                "Educational and practical tone.\n\n"
        );


        // ========================================================
        // IMPORTANT RULES
        // ========================================================

        prompt.append(
                "IMPORTANT:\n"
        );

        prompt.append(
                "Do not invent information about the shop.\n"
        );

        prompt.append(
                "Do not invent products.\n"
        );

        prompt.append(
                "Do not make unsupported financial claims.\n"
        );

        prompt.append(
                "Only demonstrate improvements supported "
                + "by the business analysis.\n"
        );

        prompt.append(
                "Do not show unrelated content."
        );


        return prompt.toString();
    }


    // ============================================================
    // CREATE FORM FIELD
    // ============================================================

    private static void addFormField(
            StringBuilder form,
            String boundary,
            String name,
            String value) {

        form.append("--")
                .append(boundary)
                .append("\r\n");

        form.append(
                "Content-Disposition: form-data; name=\""
                        + name
                        + "\"\r\n"
        );

        form.append(
                "Content-Type: text/plain; charset=UTF-8\r\n"
        );

        form.append("\r\n");

        form.append(
                value == null
                        ? ""
                        : value
        );

        form.append("\r\n");
    }


    // ============================================================
    // CREATE VIGGLE VIDEO TASK
    // ============================================================

    public static String createVideoTask(
            String prompt)
            throws Exception {

        String apiKey =
                APIKEYconfig.VIGGLE_API_KEY;


        if (apiKey == null ||
                apiKey.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Viggle API key is missing."
            );
        }


        if (prompt == null ||
                prompt.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Video prompt is empty."
            );
        }


        // ========================================================
        // MULTIPART BOUNDARY
        // ========================================================

        String boundary =
                "----BuyNexViggleBoundary"
                        + System.currentTimeMillis();


        StringBuilder form =
                new StringBuilder();


        // ========================================================
        // PROMPT
        // ========================================================

        addFormField(
                form,
                boundary,
                "prompt",
                prompt
        );


        // ========================================================
        // QUALITY
        // ========================================================

        addFormField(
                form,
                boundary,
                "quality",
                "low"
        );


        // ========================================================
        // DURATION
        // ========================================================

        addFormField(
                form,
                boundary,
                "duration_s",
                "10"
        );


        // ========================================================
        // RESOLUTION
        // ========================================================

        addFormField(
                form,
                boundary,
                "resolution",
                "768p"
        );


        // ========================================================
        // ASPECT RATIO
        // ========================================================

        addFormField(
                form,
                boundary,
                "aspect_ratio",
                "16:9"
        );


        // ========================================================
        // WATERMARK
        // ========================================================

        addFormField(
                form,
                boundary,
                "watermark",
                "false"
        );


        // ========================================================
        // END MULTIPART REQUEST
        // ========================================================

        form.append("--")
                .append(boundary)
                .append("--\r\n");


        byte[] requestBody =
                form.toString()
                        .getBytes(
                                StandardCharsets.UTF_8
                        );


        // ========================================================
        // HTTP REQUEST
        // ========================================================

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL
                                                + "/v1/videos"
                                )
                        )
                        .header(
                                "Authorization",
                                "Bearer "
                                        + apiKey
                        )
                        .header(
                                "Content-Type",
                                "multipart/form-data; boundary="
                                        + boundary
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofByteArray(
                                                requestBody
                                        )
                        )
                        .build();


        // ========================================================
        // SEND REQUEST
        // ========================================================

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );


        System.out.println(
                "========================================"
        );

        System.out.println(
                "VIGGLE CREATE VIDEO"
        );

        System.out.println(
                "HTTP STATUS: "
                        + response.statusCode()
        );

        System.out.println(
                "RESPONSE:"
        );

        System.out.println(
                response.body()
        );

        System.out.println(
                "========================================"
        );


        // ========================================================
        // CHECK RESPONSE
        // ========================================================

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new RuntimeException(
                    "Viggle video request failed.\n"
                            + response.body()
            );
        }


        // ========================================================
        // PARSE RESPONSE
        // ========================================================

        JSONObject result =
                new JSONObject(
                        response.body()
                );


        String videoId =
                result.optString(
                        "id",
                        ""
                );


        if (videoId.isEmpty()) {

            throw new RuntimeException(
                    "Viggle did not return video ID.\n"
                            + response.body()
            );
        }


        System.out.println(
                "Viggle Video ID: "
                        + videoId
        );


        return videoId;
    }


    // ============================================================
    // GET VIDEO STATUS
    // ============================================================

    public static JSONObject getVideoStatus(
            String videoId)
            throws Exception {

        if (videoId == null ||
                videoId.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Viggle video ID is empty."
            );
        }


        String apiKey =
                APIKEYconfig.VIGGLE_API_KEY;


        if (apiKey == null ||
                apiKey.trim().isEmpty()) {

            throw new IllegalStateException(
                    "Viggle API key is missing."
            );
        }


        String url =
                BASE_URL
                        + "/v1/videos/"
                        + videoId;


        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(url)
                        )
                        .header(
                                "Authorization",
                                "Bearer "
                                        + apiKey
                        )
                        .GET()
                        .build();


        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString()
                );


        System.out.println(
                "Viggle Status HTTP: "
                        + response.statusCode()
        );


        System.out.println(
                "Viggle Status Response:"
        );

        System.out.println(
                response.body()
        );


        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new RuntimeException(
                    "Unable to check Viggle video.\n"
                            + response.body()
            );
        }


        return new JSONObject(
                response.body()
        );
    }


    // ============================================================
    // EXTRACT VIDEO URL
    // ============================================================

    private static String extractVideoUrl(
            JSONObject result) {

        if (result == null) {

            return "";
        }


        return result.optString(
                "video_url",
                ""
        );
    }


    // ============================================================
    // WAIT FOR VIDEO
    // ============================================================

    public static String waitForVideo(
            String videoId)
            throws Exception {


        /*
         * Maximum:
         *
         * 60 attempts × 5 seconds
         * = approximately 5 minutes.
         */

        int maxAttempts =
                60;


        for (
                int attempt = 1;
                attempt <= maxAttempts;
                attempt++
        ) {


            JSONObject result =
                    getVideoStatus(
                            videoId
                    );


            String status =
                    result.optString(
                            "status",
                            ""
                    );


            System.out.println(
                    "Viggle Video Status: "
                            + status
                            + " | Attempt: "
                            + attempt
            );


            // ====================================================
            // READY
            // ====================================================

            if ("ready".equalsIgnoreCase(
                    status)) {


                String videoUrl =
                        extractVideoUrl(
                                result
                        );


                if (videoUrl.isEmpty()) {

                    throw new RuntimeException(
                            "Viggle returned READY status "
                                    + "but video URL is missing."
                    );
                }


                return videoUrl;
            }


            // ====================================================
            // FAILED
            // ====================================================

            if ("failed".equalsIgnoreCase(
                    status)) {

                throw new RuntimeException(
                        "Viggle video generation failed.\n"
                                + result.toString()
                );
            }


            // ====================================================
            // CANCELLED
            // ====================================================

            if ("cancelled".equalsIgnoreCase(
                    status)) {

                throw new RuntimeException(
                        "Viggle video generation was cancelled."
                );
            }


            // ====================================================
            // WAIT
            // ====================================================

            Thread.sleep(
                    5000
            );
        }


        throw new RuntimeException(
                "Viggle video generation timed out "
                        + "after approximately 5 minutes."
        );
    }


    // ============================================================
    // COMPLETE VIDEO GENERATION
    // ============================================================

    public static String generateVideo(
            String shopName,
            String category,
            String analysis)
            throws Exception {


        // ========================================================
        // 1. CREATE PROMPT
        // ========================================================

        String prompt =
                createVideoPrompt(
                        shopName,
                        category,
                        analysis
                );


        System.out.println(
                "========================================"
        );

        System.out.println(
                "VIGGLE VIDEO PROMPT"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                prompt
        );


        // ========================================================
        // 2. CREATE VIDEO TASK
        // ========================================================

        String videoId =
                createVideoTask(
                        prompt
                );


        System.out.println(
                "Viggle Video ID: "
                        + videoId
        );


        // ========================================================
        // 3. WAIT FOR VIDEO
        // ========================================================

        String videoUrl =
                waitForVideo(
                        videoId
                );


        // ========================================================
        // 4. FINAL RESULT
        // ========================================================

        System.out.println(
                "========================================"
        );

        System.out.println(
                "VIGGLE VIDEO READY"
        );

        System.out.println(
                "========================================"
        );

        System.out.println(
                videoUrl
        );


        return videoUrl;
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
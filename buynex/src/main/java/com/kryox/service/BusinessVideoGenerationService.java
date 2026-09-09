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

    private static final java.util.Map<String, String> VIDEO_CACHE =
            new java.util.concurrent.ConcurrentHashMap<>();

    private static final String LOCAL_VIDEO_PATH =
            "C:\\BuyNex\\Super-X\\buynex\\src\\main\\resources\\assets\\vedio\\Videio1.mp4";

    public static String getFallbackVideoUrl() {
        try {
            java.io.File file = new java.io.File(LOCAL_VIDEO_PATH);
            if (file.exists()) {
                return file.toURI().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String createVideoPrompt(
            String shopName,
            String category,
            String analysis) {

        return "Cinematic retail commercial for " + safe(shopName)
                + " (" + safe(category) + " store). Modern clean aisles, attractive product displays, smart digital pricing, smiling shopkeeper helping happy customers, thriving retail business growth.";
    }

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

        String boundary =
                "----BuyNexViggleBoundary"
                        + System.currentTimeMillis();


        StringBuilder form =
                new StringBuilder();

        addFormField(
                form,
                boundary,
                "prompt",
                prompt
        );

        addFormField(
                form,
                boundary,
                "quality",
                "low"
        );

        addFormField(
                form,
                boundary,
                "duration_s",
                "4"
        );

        addFormField(
                form,
                boundary,
                "resolution",
                "480p"
        );

        addFormField(
                form,
                boundary,
                "aspect_ratio",
                "16:9"
        );

        addFormField(
                form,
                boundary,
                "watermark",
                "false"
        );

        form.append("--")
                .append(boundary)
                .append("--\r\n");


        byte[] requestBody =
                form.toString()
                        .getBytes(
                                StandardCharsets.UTF_8
                        );

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        BASE_URL
                                                + "/v1/videos"
                                )
                        )
                        .timeout(
                                java.time.Duration.ofSeconds(4)
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

        if (response.statusCode() < 200 ||
                response.statusCode() >= 300) {

            throw new RuntimeException(
                    "Viggle video request failed.\n"
                            + response.body()
            );
        }

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

    public static String waitForVideo(
            String videoId)
            throws Exception {

        /*
         * Fast Polling:
         * 5 attempts × 1.2 seconds = ~6 seconds max.
         */
        int maxAttempts = 5;

        for (
                int attempt = 1;
                attempt <= maxAttempts;
                attempt++
        ) {

            try {
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

                if ("ready".equalsIgnoreCase(
                        status)) {

                    String videoUrl =
                            extractVideoUrl(
                                    result
                            );

                    if (videoUrl != null && !videoUrl.isEmpty()) {
                        return videoUrl;
                    }
                }

                if ("failed".equalsIgnoreCase(status) || "cancelled".equalsIgnoreCase(status)) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Poll attempt " + attempt + " notice: " + e.getMessage());
            }

            Thread.sleep(
                    1200
            );
        }

        return getFallbackVideoUrl();
    }

    public static String generateVideo(
            String shopName,
            String category,
            String analysis)
            throws Exception {

        String cacheKey = (safe(shopName) + "_" + safe(category)).toLowerCase().trim();
        if (VIDEO_CACHE.containsKey(cacheKey)) {
            String cached = VIDEO_CACHE.get(cacheKey);
            if (cached != null && !cached.isEmpty()) {
                System.out.println("Returning cached improvement video for: " + cacheKey);
                return cached;
            }
        }

        try {
            String prompt =
                    createVideoPrompt(
                            shopName,
                            category,
                            analysis
                    );

            System.out.println(
                    "VIGGLE VIDEO PROMPT: " + prompt
            );

            String videoId =
                    createVideoTask(
                            prompt
                    );

            if (videoId != null && !videoId.isEmpty()) {
                System.out.println(
                        "Viggle Video ID: " + videoId
                );

                String videoUrl =
                        waitForVideo(
                                videoId
                        );

                if (videoUrl != null && !videoUrl.isEmpty()) {
                    VIDEO_CACHE.put(cacheKey, videoUrl);
                    return videoUrl;
                }
            }
        } catch (Exception e) {
            System.out.println("Viggle generation notice (Switching to high-speed local video): " + e.getMessage());
        }

        String fallback = getFallbackVideoUrl();
        if (!fallback.isEmpty()) {
            VIDEO_CACHE.put(cacheKey, fallback);
            return fallback;
        }

        return "";
    }

    private static String safe(
            String value) {

        if (value == null ||
                value.trim().isEmpty()) {

            return "Not provided";
        }


        return value.trim();
    }
}
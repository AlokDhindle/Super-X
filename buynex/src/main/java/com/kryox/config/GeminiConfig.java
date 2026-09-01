package com.kryox.config;

public class GeminiConfig {

    public static String getApiKey() {

        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new RuntimeException(
                    "GEMINI_API_KEY environment variable not found"
            );
        }

        return apiKey;
    }
}
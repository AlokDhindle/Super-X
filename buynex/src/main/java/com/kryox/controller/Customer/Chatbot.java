package com.kryox.controller.Customer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.kryox.config.APIKEYconfig;

public class Chatbot {

    // API key separate config file se aa rahi hai
    private static final String GEMINI_API_KEY =
            APIKEYconfig.GEMINI_API_KEY;

    private static final String GEMINI_MODEL =
            "gemini-3.7-flash";

    // ================= ACTIVITY API =================

    public static String getActivity() {

        try {

            System.out.println("In activity method");

            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI.create(
                    "https://bored-api.appbrewery.com/random");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // ================= ORIGIN API =================

    public static String getOrigin(String name) {

        try {

            HttpClient client = HttpClient.newHttpClient();

            URI uri = URI.create(
                    "https://api.nationalize.io/?name="
                            + name);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // ================= GEMINI CHATBOT =================

    public static String getGeminiResponse(String userMessage) {

        int maxRetries = 3;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {

            try {

                HttpClient client = HttpClient.newBuilder()
                        .connectTimeout(Duration.ofSeconds(15))
                        .build();

                // ================= JSON BODY =================

                String jsonBody = """
                        {
                          "contents": [
                            {
                              "parts": [
                                {
                                  "text": "%s"
                                }
                              ]
                            }
                          ],
                          "generationConfig": {
                            "maxOutputTokens": 200
                          }
                        }
                        """.formatted(escapeJson(userMessage));

                // ================= GEMINI URL =================

                URI uri = URI.create(
                        "https://generativelanguage.googleapis.com/v1beta/models/"
                                + GEMINI_MODEL
                                + ":generateContent");

                // ================= REQUEST =================

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .timeout(Duration.ofSeconds(45))
                        .header(
                                "x-goog-api-key",
                                GEMINI_API_KEY)
                        .header(
                                "Content-Type",
                                "application/json")
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(jsonBody))
                        .build();

                System.out.println(
                        "Sending request to Gemini... Attempt: "
                                + attempt);

                HttpResponse<String> response = client.send(
                        request,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println(
                        "Gemini HTTP Status: "
                                + response.statusCode());

                // ================= SUCCESS =================

                if (response.statusCode() == 200) {

                    return extractGeminiText(
                            response.body());
                }

                // ================= 503 =================

                if (response.statusCode() == 503) {

                    System.out.println(
                            "Gemini server temporarily unavailable.");

                    if (attempt < maxRetries) {

                        long delay =
                                2000L * attempt;

                        System.out.println(
                                "Retrying after "
                                        + delay
                                        + " ms...");

                        Thread.sleep(delay);

                        continue;
                    }

                    return "Gemini server abhi busy hai. "
                            + "Please thodi der baad try karo.";
                }

                // ================= 429 =================

                if (response.statusCode() == 429) {

                    if (attempt < maxRetries) {

                        long delay =
                                3000L * attempt;

                        System.out.println(
                                "Rate limit reached. Retrying after "
                                        + delay
                                        + " ms...");

                        Thread.sleep(delay);

                        continue;
                    }

                    return "Gemini API rate limit reached. "
                            + "Please thodi der baad try karo.";
                }

                // ================= OTHER ERRORS =================

                System.out.println(
                        "Gemini API Error Response:");

                System.out.println(
                        response.body());

                return "Gemini API error: HTTP "
                        + response.statusCode();

            } catch (java.net.http.HttpTimeoutException e) {

                System.out.println(
                        "Gemini request timeout. Attempt: "
                                + attempt);

                if (attempt < maxRetries) {

                    try {

                        long delay =
                                2000L * attempt;

                        Thread.sleep(delay);

                    } catch (InterruptedException ignored) {

                        Thread.currentThread().interrupt();

                        return "Gemini request interrupted.";
                    }

                    continue;
                }

                return "Gemini response lene mein timeout ho gaya.";

            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();

                return "Gemini request interrupted.";

            } catch (Exception e) {

                e.printStackTrace();

                return "Sorry, Gemini se response nahi aa raha.";
            }
        }

        return "Gemini se response nahi mila.";
    }

    // ================= JSON ESCAPE =================

    private static String escapeJson(String text) {

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

    // ================= GEMINI RESPONSE =================

    private static String extractGeminiText(String json) {

        try {

            String search = "\"text\": \"";

            int start = json.indexOf(search);

            if (start == -1) {

                return "Gemini ne koi response nahi diya.";
            }

            start += search.length();

            StringBuilder result =
                    new StringBuilder();

            boolean escaped = false;

            for (int i = start; i < json.length(); i++) {

                char ch = json.charAt(i);

                if (escaped) {

                    switch (ch) {

                        case 'n':
                            result.append('\n');
                            break;

                        case 'r':
                            result.append('\r');
                            break;

                        case 't':
                            result.append('\t');
                            break;

                        case '"':
                            result.append('"');
                            break;

                        case '\\':
                            result.append('\\');
                            break;

                        default:
                            result.append(ch);
                            break;
                    }

                    escaped = false;

                } else {

                    if (ch == '\\') {

                        escaped = true;

                    } else if (ch == '"') {

                        break;

                    } else {

                        result.append(ch);
                    }
                }
            }

            return result.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "Response read nahi ho paya.";
        }
    }
}
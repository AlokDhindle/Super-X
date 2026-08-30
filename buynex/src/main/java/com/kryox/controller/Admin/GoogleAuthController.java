package com.kryox.controller.Admin;

import java.awt.Desktop;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.kryox.config.GoogleOAuthConfig;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class GoogleAuthController {

    private final HttpClient client =
            HttpClient.newHttpClient();


    public boolean loginWithGoogle() {

        HttpServer server = null;

        try {

            String clientId =
                    GoogleOAuthConfig.CLIENT_ID;

            String clientSecret =
                    GoogleOAuthConfig.CLIENT_SECRET;


            if (clientId == null
                    || clientId.isBlank()
                    || clientId.contains("PASTE_YOUR")) {

                System.out.println(
                        "Google CLIENT_ID is not configured."
                );

                return false;
            }


            server =
                    HttpServer.create(
                            new InetSocketAddress(
                                    "127.0.0.1",
                                    0
                            ),
                            0
                    );


            int port =
                    server
                            .getAddress()
                            .getPort();


            String redirectUri =
                    "http://127.0.0.1:"
                            + port
                            + "/oauth2callback";


            String state =
                    randomUrlSafeString(32);

            String codeVerifier =
                    randomUrlSafeString(64);

            String codeChallenge =
                    createCodeChallenge(
                            codeVerifier
                    );


            CompletableFuture<String> codeFuture =
                    new CompletableFuture<>();


            HttpServer finalServer = server;

            server.createContext(
                    "/oauth2callback",
                    exchange -> {

                        Map<String, String> query =
                                parseQuery(
                                        exchange
                                                .getRequestURI()
                                                .getRawQuery()
                                );


                        String error =
                                query.get("error");

                        if (error != null) {

                            sendBrowserMessage(
                                    exchange,
                                    "Google Sign-In cancelled",
                                    "You can close this tab and return to BuyNeX."
                            );

                            codeFuture.completeExceptionally(
                                    new RuntimeException(
                                            "Google OAuth error: "
                                                    + error
                                    )
                            );

                            finalServer.stop(0);

                            return;
                        }


                        String returnedState =
                                query.get("state");

                        String code =
                                query.get("code");


                        if (returnedState == null
                                || !returnedState.equals(state)) {

                            sendBrowserMessage(
                                    exchange,
                                    "Google Sign-In failed",
                                    "Invalid login state. Please try again."
                            );

                            codeFuture.completeExceptionally(
                                    new RuntimeException(
                                            "Google OAuth state mismatch"
                                    )
                            );

                            finalServer.stop(0);

                            return;
                        }


                        if (code == null
                                || code.isBlank()) {

                            sendBrowserMessage(
                                    exchange,
                                    "Google Sign-In failed",
                                    "Authorization code was not received."
                            );

                            codeFuture.completeExceptionally(
                                    new RuntimeException(
                                            "Authorization code missing"
                                    )
                            );

                            finalServer.stop(0);

                            return;
                        }


                        sendBrowserMessage(
                                exchange,
                                "Google Sign-In successful",
                                "You can close this tab and return to BuyNeX."
                        );


                        codeFuture.complete(code);

                        finalServer.stop(0);
                    }
            );


            server.start();


            String authorizationUrl =
                    "https://accounts.google.com/o/oauth2/v2/auth"
                            + "?client_id="
                            + encode(clientId)

                            + "&redirect_uri="
                            + encode(redirectUri)

                            + "&response_type=code"

                            + "&scope="
                            + encode(
                                    "openid email profile"
                            )

                            + "&state="
                            + encode(state)

                            + "&code_challenge="
                            + encode(codeChallenge)

                            + "&code_challenge_method=S256"

                            + "&prompt=select_account";


            if (!Desktop.isDesktopSupported()
                    || !Desktop
                            .getDesktop()
                            .isSupported(
                                    Desktop.Action.BROWSE
                            )) {

                server.stop(0);

                System.out.println(
                        "Browser opening is not supported on this computer."
                );

                return false;
            }


            Desktop
                    .getDesktop()
                    .browse(
                            URI.create(
                                    authorizationUrl
                            )
                    );


            String authorizationCode =
                    codeFuture.get(
                            3,
                            TimeUnit.MINUTES
                    );


            String googleIdToken =
                    exchangeAuthorizationCode(
                            authorizationCode,
                            redirectUri,
                            codeVerifier,
                            clientId,
                            clientSecret
                    );


            if (googleIdToken == null
                    || googleIdToken.isBlank()) {

                return false;
            }


            ControllerFirebase firebase =
                    new ControllerFirebase();


            return firebase.loginWithGoogleIdToken(
                    googleIdToken
            );


        } catch (Exception e) {

            if (server != null) {

                try {
                    server.stop(0);
                } catch (Exception ignored) {
                }
            }

            System.out.println(
                    "Google Sign-In Error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return false;
        }
    }


    private String exchangeAuthorizationCode(
            String authorizationCode,
            String redirectUri,
            String codeVerifier,
            String clientId,
            String clientSecret
    ) {

        try {

            StringBuilder body =
                    new StringBuilder();

            body.append(
                    "code="
                            + encode(
                                    authorizationCode
                            )
            );

            body.append(
                    "&client_id="
                            + encode(
                                    clientId
                            )
            );


            if (clientSecret != null
                    && !clientSecret.isBlank()
                    && !clientSecret.contains("PASTE_YOUR")) {

                body.append(
                        "&client_secret="
                                + encode(
                                        clientSecret
                                )
                );
            }


            body.append(
                    "&redirect_uri="
                            + encode(
                                    redirectUri
                            )
            );

            body.append(
                    "&grant_type=authorization_code"
            );

            body.append(
                    "&code_verifier="
                            + encode(
                                    codeVerifier
                            )
            );


            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            "https://oauth2.googleapis.com/token"
                                    )
                            )
                            .header(
                                    "Content-Type",
                                    "application/x-www-form-urlencoded"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            body.toString()
                                    )
                            )
                            .build();


            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            System.out.println(
                    "Google Token Status: "
                            + response.statusCode()
            );


            if (response.statusCode() != 200) {

                System.out.println(
                        "Google Token Error: "
                                + response.body()
                );

                return null;
            }


            JSONObject json =
                    new JSONObject(
                            response.body()
                    );


            return json.optString(
                    "id_token",
                    null
            );


        } catch (Exception e) {

            System.out.println(
                    "Google token exchange error: "
                            + e.getMessage()
            );

            e.printStackTrace();

            return null;
        }
    }


    private String randomUrlSafeString(
            int numberOfBytes
    ) {

        byte[] bytes =
                new byte[numberOfBytes];

        new SecureRandom()
                .nextBytes(bytes);


        return Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }


    private String createCodeChallenge(
            String codeVerifier
    ) throws Exception {

        MessageDigest digest =
                MessageDigest.getInstance(
                        "SHA-256"
                );


        byte[] hash =
                digest.digest(
                        codeVerifier.getBytes(
                                StandardCharsets.US_ASCII
                        )
                );


        return Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(hash);
    }


    private Map<String, String> parseQuery(
            String rawQuery
    ) {

        Map<String, String> values =
                new HashMap<>();


        if (rawQuery == null
                || rawQuery.isBlank()) {

            return values;
        }


        String[] pairs =
                rawQuery.split("&");


        for (String pair : pairs) {

            String[] keyValue =
                    pair.split(
                            "=",
                            2
                    );


            String key =
                    URLDecoder.decode(
                            keyValue[0],
                            StandardCharsets.UTF_8
                    );


            String value =
                    keyValue.length > 1
                            ? URLDecoder.decode(
                                    keyValue[1],
                                    StandardCharsets.UTF_8
                            )
                            : "";


            values.put(
                    key,
                    value
            );
        }


        return values;
    }


    private void sendBrowserMessage(
            HttpExchange exchange,
            String title,
            String message
    ) throws IOException {

        String html =
                "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset='UTF-8'>"
                        + "<title>"
                        + title
                        + "</title>"
                        + "</head>"
                        + "<body style='font-family:Arial;background:#eee5df;"
                        + "display:flex;align-items:center;justify-content:center;"
                        + "height:100vh;margin:0;'>"
                        + "<div style='background:white;padding:35px 45px;"
                        + "border-radius:18px;text-align:center;"
                        + "box-shadow:0 8px 30px rgba(0,0,0,.12);'>"
                        + "<h2 style='color:#B75B0A;'>"
                        + title
                        + "</h2>"
                        + "<p>"
                        + message
                        + "</p>"
                        + "</div>"
                        + "</body>"
                        + "</html>";


        byte[] response =
                html.getBytes(
                        StandardCharsets.UTF_8
                );


        exchange
                .getResponseHeaders()
                .set(
                        "Content-Type",
                        "text/html; charset=UTF-8"
                );


        exchange.sendResponseHeaders(
                200,
                response.length
        );


        exchange
                .getResponseBody()
                .write(response);

        exchange
                .getResponseBody()
                .close();
    }


    private String encode(
            String value
    ) {

        return URLEncoder.encode(
                value,
                StandardCharsets.UTF_8
        );
    }
}

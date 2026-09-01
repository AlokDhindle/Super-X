package com.kryox.controller.Customer;

import com.kryox.config.RazorPayAPI;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import org.json.JSONObject;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PaymentController {

    // =========================================================
    // RAZORPAY TEST KEYS
    // =========================================================

    


    // =========================================================
    // PAYMENT SERVER
    // =========================================================

    private static final String PAYMENT_SERVER =
            "http://localhost:3000";
    private Runnable paymentSuccessCallback;


    // =========================================================
    // HTTP CLIENT
    // =========================================================

    private final HttpClient httpClient =
            HttpClient.newBuilder()
                    .connectTimeout(
                            Duration.ofSeconds(5)
                    )
                    .build();


    // =========================================================
    // PAYMENT POLLING
    // =========================================================

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    private ScheduledFuture<?> paymentPollingTask;


    // =========================================================
    // START PAYMENT
    // =========================================================

    public void startPayment(double amount, Runnable onSuccess) {
        this.paymentSuccessCallback = onSuccess;

        System.out.println();
        System.out.println("================================");
        System.out.println("BuyNeX Payment Started");
        System.out.println("Amount : ₹" + amount);
        System.out.println("================================");

        try {

            // -------------------------------------------------
            // VALIDATE AMOUNT
            // -------------------------------------------------

            if (amount <= 0) {

                showError(
                        "Invalid payment amount."
                );

                return;
            }


            // -------------------------------------------------
            // RUPEES → PAISE
            // -------------------------------------------------

            int amountInPaise =
                    (int) Math.round(
                            amount * 100
                    );


            System.out.println(
                    "Amount in paise : "
                            + amountInPaise
            );


            // -------------------------------------------------
            // RAZORPAY CLIENT
            // -------------------------------------------------

            RazorpayClient razorpay =
                    new RazorpayClient(
                            RazorPayAPI.RAZORPAY_KEY_ID,
                            RazorPayAPI.RAZORPAY_KEY_SECRET
                    );


            // -------------------------------------------------
            // ORDER REQUEST
            // -------------------------------------------------

            JSONObject orderRequest =
                    new JSONObject();

            orderRequest.put(
                    "amount",
                    amountInPaise
            );

            orderRequest.put(
                    "currency",
                    "INR"
            );

            orderRequest.put(
                    "receipt",
                    "BUYNEX_"
                            + System.currentTimeMillis()
            );


            // -------------------------------------------------
            // CREATE ORDER
            // -------------------------------------------------

            System.out.println();
            System.out.println(
                    "Creating Razorpay Order..."
            );


            Order order =
                    razorpay.orders.create(
                            orderRequest
                    );


            String orderId =
                    order.get("id");


            System.out.println();
            System.out.println(
                    "================================"
            );

            System.out.println(
                    "Razorpay Order Created"
            );

            System.out.println(
                    "Order ID : " + orderId
            );

            System.out.println(
                    "================================"
            );


            // -------------------------------------------------
            // IMPORTANT
            // START POLLING BEFORE OPENING BROWSER
            // -------------------------------------------------

            startPaymentStatusPolling(
                    orderId
            );


            // -------------------------------------------------
            // OPEN RAZORPAY CHECKOUT
            // -------------------------------------------------

            openRazorpayInBrowser(
                    orderId,
                    amountInPaise
            );


        } catch (Exception e) {

            e.printStackTrace();

            showError(
                    "Unable to create Razorpay order.\n\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // OPEN RAZORPAY IN BROWSER
    // =========================================================

    private void openRazorpayInBrowser(
            String orderId,
            int amount
    ) {

        try {

            String html =
                    createCheckoutHTML(
                            orderId,
                            amount
                    );


            // -------------------------------------------------
            // CREATE TEMP HTML
            // -------------------------------------------------

            Path htmlFile =
                    Files.createTempFile(
                            "buynex_razorpay_",
                            ".html"
                    );


            Files.writeString(
                    htmlFile,
                    html,
                    StandardCharsets.UTF_8
            );


            System.out.println();
            System.out.println(
                    "Opening Razorpay in browser..."
            );

            System.out.println(
                    "HTML File : "
                            + htmlFile
            );


            // -------------------------------------------------
            // OPEN DEFAULT BROWSER
            // -------------------------------------------------

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().browse(
                        htmlFile.toUri()
                );

            } else {

                showError(
                        "Unable to open browser."
                );
            }


        } catch (IOException e) {

            e.printStackTrace();

            showError(
                    "Unable to open Razorpay checkout.\n\n"
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // RAZORPAY CHECKOUT HTML
    // =========================================================

    private String createCheckoutHTML(
            String orderId,
            int amount
    ) {

        return """
                <!DOCTYPE html>

                <html>

                <head>

                    <meta charset="UTF-8">

                    <meta name="viewport"
                          content="width=device-width,
                                   initial-scale=1.0">

                    <title>
                        BuyNeX Payment
                    </title>


                    <script
                        src="https://checkout.razorpay.com/v1/checkout.js">
                    </script>

                </head>


                <body>

                    <script>

                        var options = {

                            key: '%s',

                            amount: %d,

                            currency: 'INR',

                            name: 'BuyNeX',

                            description:
                                'BuyNeX Order Payment',

                            order_id: '%s',


                            callback_url:
                                'http://localhost:3000/payment-success',

                            redirect: true,


                            prefill: {

                                name: '',

                                email: '',

                                contact: ''

                            },


                            theme: {

                                color: '#FF6900'

                            }

                        };


                        var razorpay =
                            new Razorpay(options);


                        razorpay.on(
                            'payment.failed',
                            function(response) {

                                console.log(
                                    "================================"
                                );

                                console.log(
                                    "PAYMENT FAILED"
                                );

                                console.log(
                                    response.error.description
                                );

                                console.log(
                                    "================================"
                                );


                                alert(
                                    "Payment Failed\\n\\n"
                                    + response.error.description
                                );

                            }
                        );


                        razorpay.open();

                    </script>

                </body>

                </html>

                """.formatted(
                        RazorPayAPI.RAZORPAY_KEY_ID,
                        amount,
                        orderId
                );
    }


    // =========================================================
    // START PAYMENT STATUS POLLING
    // =========================================================

    private void startPaymentStatusPolling(
            String orderId
    ) {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "STARTING PAYMENT STATUS POLLING"
        );

        System.out.println(
                "Order ID : " + orderId
        );

        System.out.println(
                "Server : " + PAYMENT_SERVER
        );

        System.out.println(
                "================================"
        );


        // -----------------------------------------------------
        // STOP OLD POLLING
        // -----------------------------------------------------

        stopPaymentPolling();


        // -----------------------------------------------------
        // POLL EVERY 2 SECONDS
        // -----------------------------------------------------

        paymentPollingTask =
                scheduler.scheduleAtFixedRate(

                        () -> checkPaymentStatus(
                                orderId
                        ),

                        2,

                        2,

                        TimeUnit.SECONDS
                );
    }


    // =========================================================
    // CHECK PAYMENT STATUS
    // =========================================================

    private void checkPaymentStatus(
            String orderId
    ) {

        try {

            // -------------------------------------------------
            // URL
            // -------------------------------------------------

            String url =
                    PAYMENT_SERVER
                            + "/payment-status/"
                            + orderId;


            System.out.println();
            System.out.println(
                    "--------------------------------"
            );

            System.out.println(
                    "Checking Payment Status..."
            );

            System.out.println(
                    "Order ID : "
                            + orderId
            );

            System.out.println(
                    "URL : "
                            + url
            );


            // -------------------------------------------------
            // REQUEST
            // -------------------------------------------------

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(url)
                            )
                            .timeout(
                                    Duration.ofSeconds(5)
                            )
                            .GET()
                            .build();


            // -------------------------------------------------
            // SEND REQUEST
            // -------------------------------------------------

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );


            // -------------------------------------------------
            // PRINT HTTP STATUS
            // -------------------------------------------------

            System.out.println(
                    "HTTP Status : "
                            + response.statusCode()
            );


            // -------------------------------------------------
            // PRINT RAW RESPONSE
            // -------------------------------------------------

            System.out.println(
                    "Server Response : "
                            + response.body()
            );


            // -------------------------------------------------
            // HTTP ERROR
            // -------------------------------------------------

            if (
                    response.statusCode() != 200
            ) {

                System.out.println(
                        "Payment server returned HTTP "
                                + response.statusCode()
                );

                System.out.println(
                        "--------------------------------"
                );

                return;
            }


            // -------------------------------------------------
            // PARSE JSON
            // -------------------------------------------------

            JSONObject json =
                    new JSONObject(
                            response.body()
                    );


            // -------------------------------------------------
            // GET STATUS
            // -------------------------------------------------

            String status =
                    json.optString(
                            "status",
                            "PENDING"
                    );


            System.out.println(
                    "Payment Status : "
                            + status
            );


            // =================================================
            // SUCCESS
            // =================================================

            if (
                    "SUCCESS".equalsIgnoreCase(
                            status
                    )
            ) {

                System.out.println();
                System.out.println(
                        "================================"
                );

                System.out.println(
                        "PAYMENT SUCCESS RECEIVED"
                );

                System.out.println(
                        "================================"
                );


                // -------------------------------------------------
                // STOP POLLING
                // -------------------------------------------------

                stopPaymentPolling();


                // -------------------------------------------------
                // GET PAYMENT DATA
                // -------------------------------------------------

                String paymentId =
                        json.optString(
                                "paymentId",
                                ""
                        );


                String returnedOrderId =
                        json.optString(
                                "orderId",
                                orderId
                        );


                String signature =
                        json.optString(
                                "signature",
                                ""
                        );


                System.out.println(
                        "Payment ID : "
                                + paymentId
                );

                System.out.println(
                        "Order ID : "
                                + returnedOrderId
                );

                System.out.println(
                        "Signature : "
                                + signature
                );


                System.out.println(
                        "================================"
                );


                // -------------------------------------------------
                // JAVA FX SUCCESS
                // -------------------------------------------------

                paymentSuccessful(
                        paymentId,
                        returnedOrderId,
                        signature
                );

                return;
            }


            // =================================================
            // FAILED
            // =================================================

            if (
                    "FAILED".equalsIgnoreCase(
                            status
                    )
            ) {

                System.out.println();
                System.out.println(
                        "PAYMENT VERIFICATION FAILED"
                );


                stopPaymentPolling();


                showError(
                        "Payment verification failed."
                );

                return;
            }


            // =================================================
            // PENDING
            // =================================================

            System.out.println(
                    "Payment still pending..."
            );

            System.out.println(
                    "--------------------------------"
            );


        } catch (Exception e) {

            // -------------------------------------------------
            // IMPORTANT:
            // NOW WE SHOW THE REAL ERROR
            // -------------------------------------------------

            System.out.println();
            System.out.println(
                    "================================"
            );

            System.out.println(
                    "PAYMENT STATUS CHECK ERROR"
            );

            System.out.println(
                    "Order ID : "
                            + orderId
            );

            System.out.println(
                    "Error Type : "
                            + e.getClass().getName()
            );

            System.out.println(
                    "Error Message : "
                            + e.getMessage()
            );

            System.out.println(
                    "================================"
            );

            e.printStackTrace();
        }
    }


    // =========================================================
    // STOP PAYMENT POLLING
    // =========================================================

    private void stopPaymentPolling() {

        if (
                paymentPollingTask != null
        ) {

            paymentPollingTask.cancel(
                    false
            );

            paymentPollingTask = null;


            System.out.println();
            System.out.println(
                    "Payment polling stopped."
            );
        }
    }


    // =========================================================
    // PAYMENT SUCCESS
    // =========================================================

    public void paymentSuccessful(
            String paymentId,
            String orderId,
            String signature
    ) {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "PAYMENT SUCCESSFUL"
        );

        System.out.println(
                "Payment ID : "
                        + paymentId
        );

        System.out.println(
                "Order ID : "
                        + orderId
        );

        System.out.println(
                "Signature : "
                        + signature
        );

        System.out.println(
                "================================"
        );


        showSuccess(
                paymentId,
                orderId
        );


        // Execute callback only after verified payment success.
        if (paymentSuccessCallback != null) {
            Platform.runLater(paymentSuccessCallback);
        }

    }


    // =========================================================
    // SUCCESS ALERT
    // =========================================================

    private void showSuccess(
            String paymentId,
            String orderId
    ) {

        Platform.runLater(() -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );


            alert.setTitle(
                    "Payment Successful"
            );


            alert.setHeaderText(
                    "Payment Completed ✓"
            );


            alert.setContentText(
                    "Your BuyNeX payment was successful."
                            + "\n\n"
                            + "Payment ID: "
                            + paymentId
                            + "\n\n"
                            + "Order ID: "
                            + orderId
            );


            alert.showAndWait();
        });
    }


    // =========================================================
    // ERROR
    // =========================================================

    public void showError(
            String message
    ) {

        Platform.runLater(() -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );


            alert.setTitle(
                    "Payment Failed"
            );


            alert.setHeaderText(
                    null
            );


            alert.setContentText(
                    message
            );


            alert.showAndWait();
        });
    }


    // =========================================================
    // SHUTDOWN
    // =========================================================

    public void shutdown() {

        stopPaymentPolling();

        scheduler.shutdownNow();

        System.out.println(
                "PaymentController shutdown."
        );
    }
}
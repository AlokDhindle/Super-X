const express = require("express");
const crypto = require("crypto");

const app = express();

const PORT = 3000;

// =========================================================
// RAZORPAY SECRET
// =========================================================

// IMPORTANT:
// इथे तुझा Razorpay TEST KEY SECRET टाक.
// Key ID इथे आवश्यक नाही कारण या step मध्ये
// आपण फक्त signature verify करत आहोत.

const RAZORPAY_KEY_SECRET =
    "53nH6INb4ozT8BdA4crofajt";


// =========================================================
// MIDDLEWARE
// =========================================================

app.use(
    express.urlencoded({
        extended: true
    })
);

app.use(
    express.json()
);


// =========================================================
// PAYMENT STATUS STORAGE
// =========================================================

// Testing साठी memory मध्ये ठेवत आहोत.
//
// Production मध्ये Firestore / MySQL / MongoDB
// वापरायला पाहिजे.

const payments = new Map();


// =========================================================
// HEALTH CHECK
// =========================================================

app.get("/", (req, res) => {

    res.send(`
        <h1>BuyNeX Payment Server</h1>
        <p>Server is running.</p>
    `);
});


// =========================================================
// RAZORPAY CALLBACK
// =========================================================

app.post("/payment-success", (req, res) => {

    console.log(
        "================================"
    );

    console.log(
        "Razorpay Callback Received"
    );

    console.log(
        "================================"
    );


    const paymentId =
        req.body.razorpay_payment_id;

    const orderId =
        req.body.razorpay_order_id;

    const receivedSignature =
        req.body.razorpay_signature;


    console.log(
        "Payment ID :",
        paymentId
    );

    console.log(
        "Order ID :",
        orderId
    );

    console.log(
        "Signature :",
        receivedSignature
    );


    // -------------------------------------------------------
    // VALIDATE DATA
    // -------------------------------------------------------

    if (
        !paymentId ||
        !orderId ||
        !receivedSignature
    ) {

        return res.status(400).send(`
            <h1>Payment Verification Failed</h1>
            <p>Required payment data is missing.</p>
        `);
    }


    // -------------------------------------------------------
    // CREATE SIGNATURE
    // -------------------------------------------------------

    const generatedSignature =
        crypto
            .createHmac(
                "sha256",
                RAZORPAY_KEY_SECRET
            )
            .update(
                orderId + "|" + paymentId
            )
            .digest("hex");


    console.log(
        "Generated Signature :",
        generatedSignature
    );


    // -------------------------------------------------------
    // VERIFY SIGNATURE
    // -------------------------------------------------------

    const isValid =
        crypto.timingSafeEqual(
            Buffer.from(
                generatedSignature,
                "utf8"
            ),
            Buffer.from(
                receivedSignature,
                "utf8"
            )
        );


    // -------------------------------------------------------
    // INVALID PAYMENT
    // -------------------------------------------------------

    if (!isValid) {

        console.log(
            "❌ PAYMENT VERIFICATION FAILED"
        );


        payments.set(
            orderId,
            {
                status: "FAILED",
                paymentId: paymentId,
                orderId: orderId
            }
        );


        return res.status(400).send(`
            <!DOCTYPE html>

            <html>

            <head>

                <title>
                    BuyNeX Payment Failed
                </title>

            </head>

            <body>

                <h1>
                    Payment Verification Failed
                </h1>

                <p>
                    Signature verification failed.
                </p>

            </body>

            </html>
        `);
    }


    // -------------------------------------------------------
    // SUCCESS
    // -------------------------------------------------------

    console.log(
        "✅ PAYMENT VERIFIED SUCCESSFULLY"
    );


    payments.set(
        orderId,
        {
            status: "SUCCESS",
            paymentId: paymentId,
            orderId: orderId,
            signature: receivedSignature
        }
    );


    // -------------------------------------------------------
    // SUCCESS PAGE
    // -------------------------------------------------------

    res.send(`
        <!DOCTYPE html>

        <html>

        <head>

            <meta charset="UTF-8">

            <title>
                BuyNeX Payment Successful
            </title>

            <style>

                body {

                    font-family:
                        Arial,
                        sans-serif;

                    text-align:
                        center;

                    padding-top:
                        100px;

                    background:
                        #f5f5f5;

                }

                .box {

                    background:
                        white;

                    padding:
                        40px;

                    margin:
                        auto;

                    width:
                        400px;

                    border-radius:
                        12px;

                    box-shadow:
                        0 4px 20px
                        rgba(0,0,0,0.15);

                }

                h1 {

                    color:
                        #0a9f5b;

                }

            </style>

        </head>


        <body>

            <div class="box">

                <h1>
                    Payment Verified ✓
                </h1>

                <p>
                    BuyNeX payment was
                    successfully verified.
                </p>

                <p>
                    Payment ID:
                    ${paymentId}
                </p>

                <p>
                    You can close this window.
                </p>

            </div>

        </body>

        </html>
    `);
});


// =========================================================
// PAYMENT STATUS
// =========================================================

app.get(
    "/payment-status/:orderId",
    (req, res) => {

        const orderId =
            req.params.orderId;


        const payment =
            payments.get(orderId);


        if (!payment) {

            return res.json({
                status: "PENDING"
            });
        }


        return res.json(
            payment
        );
    }
);


// =========================================================
// START SERVER
// =========================================================

app.listen(
    PORT,
    () => {

        console.log(
            "================================"
        );

        console.log(
            "BuyNeX Payment Server Started"
        );

        console.log(
            "http://localhost:" + PORT
        );

        console.log(
            "================================"
        );
    }
);
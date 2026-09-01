const { onRequest } = require("firebase-functions/v2/https");
const { defineSecret } = require("firebase-functions/params");
const { initializeApp } = require("firebase-admin/app");
const Razorpay = require("razorpay");

// Firebase Admin initialize
initializeApp();

// =========================================================
// RAZORPAY SECRETS
// =========================================================

const razorpayKeyId = defineSecret("RAZORPAY_KEY_ID");
const razorpayKeySecret = defineSecret("RAZORPAY_KEY_SECRET");

// =========================================================
// CREATE RAZORPAY ORDER
// =========================================================

exports.createRazorpayOrder = onRequest(
    {
        secrets: [
            razorpayKeyId,
            razorpayKeySecret
        ],
        region: "asia-south1"
    },

    async (req, res) => {

        // -------------------------------------------------
        // CORS
        // -------------------------------------------------

        res.set("Access-Control-Allow-Origin", "*");
        res.set("Access-Control-Allow-Methods", "POST, OPTIONS");
        res.set("Access-Control-Allow-Headers", "Content-Type");

        // Browser/WebView preflight request
        if (req.method === "OPTIONS") {
            res.status(204).send("");
            return;
        }

        // -------------------------------------------------
        // ONLY POST REQUEST
        // -------------------------------------------------

        if (req.method !== "POST") {

            res.status(405).json({
                success: false,
                message: "Only POST request is allowed."
            });

            return;
        }

        try {

            // -------------------------------------------------
            // GET REQUEST DATA
            // -------------------------------------------------

            const amount = req.body.amount;

            console.log("Received amount:", amount);

            // -------------------------------------------------
            // VALIDATE AMOUNT
            // -------------------------------------------------

            if (
                amount === undefined ||
                amount === null ||
                !Number.isInteger(amount) ||
                amount <= 0
            ) {

                res.status(400).json({
                    success: false,
                    message:
                        "Amount must be a positive integer in paise."
                });

                return;
            }

            // -------------------------------------------------
            // RAZORPAY INSTANCE
            // -------------------------------------------------

            const razorpay = new Razorpay({

                key_id: razorpayKeyId.value(),

                key_secret: razorpayKeySecret.value()

            });

            // -------------------------------------------------
            // CREATE RAZORPAY ORDER
            // -------------------------------------------------

            const order = await razorpay.orders.create({

                amount: amount,

                currency: "INR",

                receipt:
                    "buynex_" +
                    Date.now(),

                notes: {

                    app: "BuyNeX",

                    source: "JavaFX"

                }

            });

            // -------------------------------------------------
            // SUCCESS RESPONSE
            // -------------------------------------------------

            console.log(
                "Razorpay Order Created:",
                order.id
            );

            res.status(200).json({

                success: true,

                orderId: order.id,

                amount: order.amount,

                currency: order.currency

            });

        } catch (error) {

            // -------------------------------------------------
            // ERROR
            // -------------------------------------------------

            console.error(
                "Razorpay Order Error:",
                error
            );

            res.status(500).json({

                success: false,

                message:
                    "Unable to create Razorpay order.",

                error:
                    error.message

            });
        }
    }
);
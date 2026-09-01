package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;

import com.kryox.model.Shopkeeper.ProductModel;
import com.kryox.model.Shopkeeper.ShopkeeperModel;
import com.kryox.service.BusinessAnalysisService;
import com.kryox.service.BusinessVideoGenerationService;
import com.kryox.service.OnlineBusinessResearchService;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Shopkeeper.BusinessImprovementVideoView;
import com.kryox.view.Shopkeeper.ViewConstants;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class BusinessImprovementController {

    private BusinessImprovementController() {
    }

    // ============================================================
    // START COMPLETE IMPROVEMENT PROCESS
    // ============================================================

    public static void startGeneration(
            ArrayList<ProductModel> products) {

        ArrayList<ProductModel> productList =
                products == null
                        ? new ArrayList<>()
                        : new ArrayList<>(products);


        // --------------------------------------------------------
        // SHOW GENERATION SCREEN
        // --------------------------------------------------------

        Platform.runLater(() -> {

            Stage stage =
                    getCurrentStage();

            if (stage == null) {
                showError(
                        "Unable to find the current application window."
                );
                return;
            }

            stage.setScene(
                    BusinessImprovementVideoView
                            .createGenerationScene()
            );

            stage.show();
        });


        // ========================================================
        // BACKGROUND TASK
        // ========================================================

        Task<String> task =
                new Task<String>() {

            @Override
            protected String call()
                    throws Exception {

                // =================================================
                // 1. SHOPKEEPER DATA
                // =================================================

                updateMessage(
                        "Reading your shop information..."
                );

                ShopkeeperModel shopkeeper =
                        ViewConstants.shopkeeperModel;


                if (shopkeeper == null) {

                    throw new IllegalStateException(
                            "Shopkeeper information is not available."
                    );
                }


                // =================================================
                // 2. PRODUCT DATA
                // =================================================

                updateMessage(
                        "Reading your product information..."
                );


                System.out.println(
                        "Product Count: "
                                + productList.size()
                );


                // =================================================
                // 3. ONLINE BUSINESS RESEARCH
                // =================================================

                updateMessage(
                        "Researching online business knowledge..."
                );


                String onlineResearch =
                        OnlineBusinessResearchService
                                .researchBusiness(

                                        shopkeeper
                                                .getShopNameValue(),

                                        shopkeeper
                                                .getCategoryValue(),

                                        shopkeeper
                                                .getDescriptionValue()
                                );


                if (onlineResearch == null) {

                    onlineResearch = "";
                }


                // =================================================
                // 4. BUSINESS ANALYSIS
                // =================================================

                updateMessage(
                        "Comparing your shop with "
                                + "business best practices..."
                );


                String analysis =
                        BusinessAnalysisService
                                .analyseBusiness(

                                        shopkeeper,

                                        productList,

                                        onlineResearch
                                );


                if (analysis == null ||
                        analysis.trim().isEmpty()) {

                    throw new IllegalStateException(
                            "Business analysis returned no result."
                    );
                }


                // =================================================
                // 5. CREATE VIDEO
                // =================================================

                updateMessage(
                        "Creating your personalized improvement video..."
                );


                String videoUrl =
                        BusinessVideoGenerationService
                                .generateVideo(

                                        shopkeeper
                                                .getShopNameValue(),

                                        shopkeeper
                                                .getCategoryValue(),

                                        analysis
                                );


                if (videoUrl == null ||
                        videoUrl.trim().isEmpty()) {

                    throw new IllegalStateException(
                            "Video URL was not generated."
                    );
                }


                // =================================================
                // 6. RETURN VIDEO URL
                // =================================================

                updateMessage(
                        "Your improvement video is ready."
                );


                return videoUrl;
            }
        };


        // ========================================================
        // STATUS LISTENER
        // ========================================================

        task.messageProperty()
                .addListener(
                        (
                                observable,
                                oldValue,
                                newValue
                        ) -> {

                            Platform.runLater(() -> {

                                BusinessImprovementVideoView
                                        .updateStatus(
                                                newValue
                                        );
                            });

                        }
                );


        // ========================================================
        // SUCCESS
        // ========================================================

        task.setOnSucceeded(
                event -> {

                    String videoUrl =
                            task.getValue();


                    Platform.runLater(() -> {

                        Stage stage =
                                getCurrentStage();


                        if (stage == null) {

                            showError(
                                    "Unable to open the video player."
                            );

                            return;
                        }


                        stage.setScene(
                                BusinessImprovementVideoView
                                        .createVideoScene(
                                                videoUrl
                                        )
                        );


                        stage.show();
                    });
                }
        );


        // ========================================================
        // FAILURE
        // ========================================================

        task.setOnFailed(
                event -> {

                    Throwable error =
                            task.getException();


                    if (error != null) {

                        error.printStackTrace();
                    }


                    String message =
                            error == null
                                    ? "Unknown error occurred."
                                    : error.getMessage();


                    if (message == null ||
                            message.trim().isEmpty()) {

                        message =
                                "Something went wrong while generating the video.";
                    }


                    showError(
                            message
                    );
                }
        );


        // ========================================================
        // START BACKGROUND THREAD
        // ========================================================

        Thread thread =
                new Thread(
                        task
                );


        thread.setDaemon(
                true
        );


        thread.start();
    }


    // ============================================================
    // GET CURRENT APPLICATION STAGE
    // ============================================================

    private static Stage getCurrentStage() {

        try {

            if (Homepage.HomepageStage != null) {

                return Homepage.HomepageStage;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }


    // ============================================================
    // ERROR
    // ============================================================

    private static void showError(
            String message) {

        Platform.runLater(() -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.ERROR
                    );


            alert.setTitle(
                    "Business Improvement"
            );


            alert.setHeaderText(
                    "Unable to generate improvement video"
            );


            alert.setContentText(
                    message
            );


            alert.showAndWait();
        });
    }
}
package com.kryox.view.Shopkeeper;

import java.net.URI;

import com.google.firebase.database.core.Platform;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BusinessImprovementVideoView {

    private static MediaPlayer mediaPlayer;

    private static MediaView mediaView;

    private static Label statusLabel;

    private static Button playPauseButton;

    private static Button stopButton;


    // ============================================================
    // PRIVATE CONSTRUCTOR
    // ============================================================

    private BusinessImprovementVideoView() {
    }


    // ============================================================
    // GENERATION SCREEN
    // ============================================================

    public static Scene createGenerationScene() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #F7F4F1;"
        );


        // ========================================================
        // TOP
        // ========================================================

        HBox topBar =
                new HBox();

        topBar.setPadding(
                new Insets(
                        20,
                        28,
                        20,
                        28
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );


        Text title =
                new Text(
                        "Business Improvement"
                );

        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;"
        );


        topBar.getChildren().add(
                title
        );


        root.setTop(
                topBar
        );


        // ========================================================
        // CENTER
        // ========================================================

        StackPane center =
                new StackPane();

        center.setPadding(
                new Insets(30)
        );


        VBox loadingBox =
                new VBox(
                        18
                );

        loadingBox.setAlignment(
                Pos.CENTER
        );


        Circle loadingCircle =
                new Circle(
                        28
                );

        loadingCircle.setFill(
                Color.web("#A94A18")
        );


        Text loadingIcon =
                new Text(
                        "AI"
                );

        loadingIcon.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;"
        );


        StackPane loadingIconPane =
                new StackPane(
                        loadingCircle,
                        loadingIcon
                );


        Label heading =
                new Label(
                        "Creating Your Business Improvement Video"
                );

        heading.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );


        statusLabel =
                new Label(
                        "Analysing your shop and products..."
                );

        statusLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #666666;"
        );


        loadingBox.getChildren().addAll(
                loadingIconPane,
                heading,
                statusLabel
        );


        center.getChildren().add(
                loadingBox
        );


        root.setCenter(
                center
        );


        // ========================================================
        // BOTTOM
        // ========================================================

        HBox bottom =
                new HBox();

        bottom.setPadding(
                new Insets(20)
        );

        bottom.setAlignment(
                Pos.CENTER
        );


        Button backButton =
                new Button(
                        "← Back to Dashboard"
                );

        backButton.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-text-fill: #444444;" +
                "-fx-font-size: 13px;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 10px 18px;" +
                "-fx-cursor: hand;"
        );


        backButton.setOnAction(
                e -> {

                    if (mediaPlayer != null) {

                        mediaPlayer.stop();

                        mediaPlayer.dispose();

                        mediaPlayer = null;
                    }

                    Stage stage =
                            (Stage) backButton
                                    .getScene()
                                    .getWindow();

                    stage.setScene(
                            ShopkeeperDashboard
                                    .dashboardScene()
                    );
                }
        );


        bottom.getChildren().add(
                backButton
        );


        root.setBottom(
                bottom
        );


        return new Scene(
                root,
                1100,
                700
        );
    }


    // ============================================================
    // VIDEO SCENE
    // ============================================================

    public static Scene createVideoScene(
            String videoUrl) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #111111;"
        );


        // ========================================================
        // TOP BAR
        // ========================================================

        HBox topBar =
                new HBox();

        topBar.setPadding(
                new Insets(
                        18,
                        25,
                        18,
                        25
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );


        Label title =
                new Label(
                        "Your Business Improvement Video"
                );

        title.setStyle(
                "-fx-font-size: 21px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );


        topBar.getChildren().add(
                title
        );


        root.setTop(
                topBar
        );


        // ========================================================
        // VIDEO
        // ========================================================

        StackPane videoContainer =
                new StackPane();

        videoContainer.setStyle(
                "-fx-background-color: #000000;"
        );


        try {

            if (videoUrl == null ||
                    videoUrl.trim().isEmpty()) {

                throw new IllegalArgumentException(
                        "Video URL is empty."
                );
            }


            Media media =
                    new Media(
                            videoUrl
                    );


            mediaPlayer =
                    new MediaPlayer(
                            media
                    );


            mediaView =
                    new MediaView(
                            mediaPlayer
                    );


            mediaView.setPreserveRatio(
                    true
            );


            mediaView.setFitWidth(
                    950
            );


            mediaView.setFitHeight(
                    540
            );


            videoContainer.getChildren().add(
                    mediaView
            );


            mediaPlayer.setOnReady(
        () -> {

            mediaPlayer.play();

            if (playPauseButton != null) {

                playPauseButton.setText(
                        "⏸ Pause"
                );
            }
        }
);


            mediaPlayer.setOnEndOfMedia(
                    () -> {

                        if (playPauseButton != null) {

                            playPauseButton.setText(
                                    "▶ Play"
                            );
                        }
                    }
            );


           mediaPlayer.setOnError(
        () -> {

            javafx.application.Platform.runLater(
                    () -> {

                        javafx.scene.control.Alert alert =
                                new javafx.scene.control.Alert(
                                        javafx.scene.control.Alert.AlertType.ERROR
                                );

                        alert.setTitle(
                                "Video Error"
                        );

                        alert.setHeaderText(
                                "Unable to play the generated video"
                        );

                        if (mediaPlayer != null &&
                                mediaPlayer.getError() != null) {

                            alert.setContentText(
                                    mediaPlayer
                                            .getError()
                                            .getMessage()
                            );

                        } else {

                            alert.setContentText(
                                    "The generated video could not be loaded."
                            );
                        }

                        alert.showAndWait();
                    }
            );
        }
);


        } catch (Exception e) {

            Label error =
                    new Label(
                            "Unable to load video.\n\n"
                                    + e.getMessage()
                    );


            error.setStyle(
                    "-fx-font-size: 15px;" +
                    "-fx-text-fill: white;"
            );


            videoContainer.getChildren().add(
                    error
            );
        }


        root.setCenter(
                videoContainer
        );
        


        // ========================================================
        // CONTROLS
        // ========================================================

        VBox controlsBox =
                new VBox(
                        15
                );

        controlsBox.setPadding(
                new Insets(
                        15,
                        25,
                        20,
                        25
                )
        );


        HBox controls =
                new HBox(
                        12
                );

        controls.setAlignment(
                Pos.CENTER
        );


        playPauseButton =
                new Button(
                        "⏸ Pause"
                );


        stopButton =
                new Button(
                        "⏹ Stop"
                );


        Button backButton =
                new Button(
                        "← Dashboard"
                );


        styleControlButton(
                playPauseButton
        );


        styleControlButton(
                stopButton
        );


        styleControlButton(
                backButton
        );


        playPauseButton.setOnAction(
                e -> togglePlayPause()
        );


        stopButton.setOnAction(
                e -> {

                    if (mediaPlayer != null) {

                        mediaPlayer.stop();

                        playPauseButton.setText(
                                "▶ Play"
                        );
                    }
                }
        );


        backButton.setOnAction(
                e -> {

                    if (mediaPlayer != null) {

                        mediaPlayer.stop();

                        mediaPlayer.dispose();

                        mediaPlayer = null;
                    }


                    Stage stage =
                            (Stage) backButton
                                    .getScene()
                                    .getWindow();


                    stage.setScene(
                            ShopkeeperDashboard
                                    .dashboardScene()
                    );
                }
        );


        controls.getChildren().addAll(
                playPauseButton,
                stopButton,
                backButton
        );


        Label footer =
                new Label(
                        "AI-generated recommendations based on your shop, products and business analysis."
                );


        footer.setStyle(
                "-fx-text-fill: #AAAAAA;" +
                "-fx-font-size: 12px;"
        );


        controlsBox.setAlignment(
                Pos.CENTER
        );


        controlsBox.getChildren().addAll(
                controls,
                footer
        );


        root.setBottom(
                controlsBox
        );


        return new Scene(
                root,
                1100,
                700
        );
    }


    // ============================================================
    // PLAY / PAUSE
    // ============================================================

    private static void togglePlayPause() {

        if (mediaPlayer == null) {
            return;
        }


        MediaPlayer.Status status =
                mediaPlayer.getStatus();


        if (status ==
                MediaPlayer.Status.PLAYING) {

            mediaPlayer.pause();

            playPauseButton.setText(
                    "▶ Play"
            );

        } else {

            mediaPlayer.play();

            playPauseButton.setText(
                    "⏸ Pause"
            );
        }
    }


    // ============================================================
    // BUTTON STYLE
    // ============================================================

    private static void styleControlButton(
            Button button) {

        button.setStyle(
                "-fx-background-color: #A94A18;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 9px;" +
                "-fx-padding: 10px 18px;" +
                "-fx-cursor: hand;"
        );
    }
    // ============================================================
// UPDATE GENERATION STATUS
// ============================================================

public static void updateStatus(
        String message) {

    if (statusLabel == null) {
        return;
    }

    javafx.application.Platform.runLater(() -> {

        statusLabel.setText(
                message
        );
    });
}

   
}
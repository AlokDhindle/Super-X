package com.kryox.view.Shopkeeper;



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


    // Private Constructor

    private BusinessImprovementVideoView() {
    }


    private static javafx.scene.control.ProgressBar progressBar;

    // Generation Screen

    public static Scene createGenerationScene() {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #F7F4F1;"
        );


        // Top

        HBox topBar =
                new HBox();

        topBar.setPadding(
                new Insets(
                        24,
                        36,
                        24,
                        36
                )
        );

        topBar.setAlignment(
                Pos.CENTER_LEFT
        );


        Text title =
                new Text(
                        "AI Business Improvement Video"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: #222222;"
        );


        topBar.getChildren().add(
                title
        );


        root.setTop(
                topBar
        );


        // Center

        StackPane center =
                new StackPane();

        center.setPadding(
                new Insets(30)
        );


        VBox loadingCard =
                new VBox(
                        20
                );

        loadingCard.setMaxWidth(
                650
        );

        loadingCard.setMaxHeight(
                360
        );

        loadingCard.setAlignment(
                Pos.CENTER
        );

        loadingCard.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 18px;" +
                "-fx-padding: 36px 45px;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 24, 0, 0, 8);"
        );


        Circle loadingCircle =
                new Circle(
                        32
                );

        loadingCircle.setFill(
                Color.web("#A94A18")
        );


        Text loadingIcon =
                new Text(
                        "AI"
                );

        loadingIcon.setStyle(
                "-fx-font-size: 16px;" +
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
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #222222;"
        );


        statusLabel =
                new Label(
                        "Analysing your shop and products..."
                );

        statusLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #555555;" +
                "-fx-font-weight: 500;"
        );

        progressBar =
                new javafx.scene.control.ProgressBar(
                        0.25
                );

        progressBar.setPrefWidth(
                480
        );

        progressBar.setPrefHeight(
                12
        );

        progressBar.setStyle(
                "-fx-accent: #A94A18;" +
                "-fx-background-radius: 6px;"
        );

        Label subNotice =
                new Label(
                        "⚡ Optimized with high-speed AI analysis and instant video synthesis"
                );

        subNotice.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #888888;"
        );


        loadingCard.getChildren().addAll(
                loadingIconPane,
                heading,
                statusLabel,
                progressBar,
                subNotice
        );


        center.getChildren().add(
                loadingCard
        );


        root.setCenter(
                center
        );


        // Bottom

        HBox bottom =
                new HBox();

        bottom.setPadding(
                new Insets(24)
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
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-padding: 12px 24px;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.06), 10, 0, 0, 3);"
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
                    stage.setWidth(1550);
                    stage.setHeight(850);
                    stage.centerOnScreen();
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
                1550,
                840
        );
    }


    // Video Scene

    public static Scene createVideoScene(
            String videoUrl) {

        BorderPane root =
                new BorderPane();

        root.setStyle(
                "-fx-background-color: #111111;"
        );


        // Top Bar

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


        // Video

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
                    1260
            );


            mediaView.setFitHeight(
                    620
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
        


        // Controls

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
                    stage.setWidth(1550);
                    stage.setHeight(850);
                    stage.centerOnScreen();
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
                1550,
                840
        );
    }


    // Play / Pause

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


    // Button Style

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
    // Update Generation Status

public static void updateStatus(
        String message) {

    javafx.application.Platform.runLater(() -> {

        if (statusLabel != null) {
            statusLabel.setText(
                    message
            );
        }

        if (progressBar != null && message != null) {
            String lower = message.toLowerCase();
            if (lower.contains("reading") || lower.contains("information")) {
                progressBar.setProgress(0.20);
            } else if (lower.contains("research") || lower.contains("online")) {
                progressBar.setProgress(0.45);
            } else if (lower.contains("comparing") || lower.contains("analys")) {
                progressBar.setProgress(0.70);
            } else if (lower.contains("creating") || lower.contains("video")) {
                progressBar.setProgress(0.90);
            } else if (lower.contains("ready")) {
                progressBar.setProgress(1.00);
            }
        }
    });
}

   
}
package com.kryox.view.Admin;

import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CongratulationsPage {

    public Scene getUserScene() {

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color: #eee5df;"
        );

        VBox main = new VBox();
        main.setAlignment(Pos.CENTER);
        main.setSpacing(28);

        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        card.setSpacing(18);
        card.setPadding(new Insets(42, 48, 42, 48));
        card.setMaxWidth(540);

        card.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:28;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.08),30,0,0,8);"
        );


        // =========================
        // SUCCESS ICON
        // =========================

        StackPane successIcon = new StackPane();

        Circle iconCircle = new Circle(46);
        iconCircle.setFill(Color.web("#EBE2DC"));

        Label check = new Label("✓");
        check.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        40
                )
        );

        check.setTextFill(
                Color.web("#994411")
        );

        successIcon.getChildren().addAll(
                iconCircle,
                check
        );


        // =========================
        // TITLE
        // =========================

        Text title = new Text(
                "Congratulations!"
        );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        title.setFill(
                Color.web("#1C1C1C")
        );


        // =========================
        // DESCRIPTION
        // =========================

        Text description = new Text(
                "Your Admin Account has been created successfully.\n" +
                "You now have full access to the BuyNeX Store\n" +
                "Manager ecosystem."
        );

        description.setTextAlignment(
                javafx.scene.text.TextAlignment.CENTER
        );

        description.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );

        description.setFill(
                Color.web("#6B6B6B")
        );


        // =========================
        // FEATURE CARDS
        // =========================

        HBox features = new HBox(16);

        features.setAlignment(
                Pos.CENTER
        );

        HBox verifiedCard =
                createFeatureCard(
                        "✓",
                        "Verified Access",
                        "Security protocols active"
                );

        HBox autoSetupCard =
                createFeatureCard(
                        "⚡",
                        "Auto-Setup",
                        "Dashboard configured"
                );

        HBox.setHgrow(
                verifiedCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                autoSetupCard,
                Priority.ALWAYS
        );

        features.getChildren().addAll(
                verifiedCard,
                autoSetupCard
        );


        // =========================
        // DASHBOARD BUTTON
        // =========================

        Button loginButton =
                new Button(
                        "login"
                );

        loginButton.setPrefWidth(
                370
        );

        loginButton.setPrefHeight(
                54
        );

        loginButton.setStyle(
                "-fx-background-color:linear-gradient(to right,#C64F00,#FFAE81);" +
                "-fx-text-fill:white;" +
                "-fx-font-size:16px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:25;" +
                "-fx-cursor:hand;"
        );


        // =========================
        // INVITE SECTION
        // =========================

        VBox inviteBox = new VBox(5);

        inviteBox.setAlignment(
                Pos.CENTER
        );

        Text inviteText =
                new Text(
                        "Need to set up your team first?"
                );

        inviteText.setFont(
                Font.font(
                        "Arial",
                        13
                )
        );

        inviteText.setFill(
                Color.web("#6B6B6B")
        );

        Label inviteLink =
                new Label(
                        "Invite Collaborators"
                );

        inviteLink.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        inviteLink.setTextFill(
                Color.web("#994411")
        );

        inviteLink.setStyle(
                "-fx-cursor:hand;"
        );

        inviteBox.getChildren().addAll(
                inviteText,
                inviteLink
        );


        // =========================
        // ADD CARD CONTENT
        // =========================

        card.getChildren().addAll(
                successIcon,
                title,
                description,
                features,
                loginButton,
                inviteBox
        );


        // =========================
        // FOOTER
        // =========================

        HBox footer = new HBox(8);

        footer.setAlignment(
                Pos.CENTER
        );

        Text logo = new Text(
                "BuyNeX"
        );

        logo.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        logo.setFill(
                Color.web("#1C1C1C")
        );

        Text line = new Text("|");

        line.setFill(
                Color.web("#AAAAAA")
        );

        Text enterprise =
                new Text(
                        "Enterprise Hub"
                );

        enterprise.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        enterprise.setFill(
                Color.web("#6B6B6B")
        );

        footer.getChildren().addAll(
                logo,
                line,
                enterprise
        );


        main.getChildren().addAll(
                card,
                footer
        );

        root.getChildren().add(main);


        // =========================
        // BUTTON HOVER
        // =========================

        loginButton.setOnMouseEntered(e -> {

            loginButton.setStyle(
                    "-fx-background-color:#C64F00;" +
                    "-fx-text-fill:white;" +
                    "-fx-font-size:16px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:25;" +
                    "-fx-cursor:hand;"
            );

        });

        loginButton.setOnMouseExited(e -> {

                loginButton.setStyle(
                    "-fx-background-color:linear-gradient(to right,#C64F00,#FFAE81);" +
                    "-fx-text-fill:white;" +
                    "-fx-font-size:16px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-background-radius:25;" +
                    "-fx-cursor:hand;"
            );

        });


        // =========================
        // GO TO DASHBOARD
        // =========================

        loginButton.setOnAction(e -> {

            AdminLoginPage dashboard =
                    new AdminLoginPage();

            Homepage.HomepageStage.setScene(
                    dashboard.getLoginScene()
            );

        });


        return new Scene(root,1550,850);
    }


    private HBox createFeatureCard(
            String iconText,
            String titleText,
            String subtitleText
    ) {

        HBox card = new HBox(14);

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setPadding(
                new Insets(
                        12
                )
        );

        card.setPrefWidth(
                215
        );

        card.setStyle(
                "-fx-background-color:#F5F5F7;" +
                "-fx-background-radius:12;" +
                "-fx-border-color:#EEEEEE;" +
                "-fx-border-radius:12;"
        );


        StackPane iconBox =
                new StackPane();

        iconBox.setPrefSize(
                40,
                40
        );

        iconBox.setMinSize(
                40,
                40
        );

        iconBox.setStyle(
                "-fx-background-color:#EBE2DC;" +
                "-fx-background-radius:8;"
        );

        Label icon =
                new Label(
                        iconText
                );

        icon.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        19
                )
        );

        icon.setTextFill(
                Color.web("#994411")
        );

        iconBox.getChildren().add(
                icon
        );


        VBox texts =
                new VBox(3);

        Text title =
                new Text(
                        titleText
                );

        title.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        14
                )
        );

        title.setFill(
                Color.web("#333333")
        );


        Text subtitle =
                new Text(
                        subtitleText
                );

        subtitle.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        subtitle.setFill(
                Color.web("#777777")
        );


        texts.getChildren().addAll(
                title,
                subtitle
        );

        card.getChildren().addAll(
                iconBox,
                texts
        );
        return card;
    }
}
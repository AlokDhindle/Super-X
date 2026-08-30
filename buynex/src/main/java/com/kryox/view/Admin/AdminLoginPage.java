package com.kryox.view.Admin;





import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.controller.Admin.GoogleAuthController;
import com.kryox.model.Admin.AdminSession;
import com.kryox.view.Customer.Homepage;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminLoginPage {

        private Scene LoginScene;

        public Scene getLoginScene() {

                Button backButton = new Button("←  Back");
                backButton.setPrefSize(95, 40);
                backButton.setStyle(
                                "-fx-background-color:#FFF0E7;" +
                                                "-fx-text-fill:#8A3600;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:20;" +
                                                "-fx-border-color:#E7BDA6;" +
                                                "-fx-border-radius:20;" +
                                                "-fx-cursor:hand;");

                backButton.setOnMouseEntered(e -> {
                        backButton.setStyle(
                                        "-fx-background-color:#FF6500;" +
                                                        "-fx-text-fill:white;" +
                                                        "-fx-font-size:13px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:20;" +
                                                        "-fx-border-color:#FF6500;" +
                                                        "-fx-border-radius:20;" +
                                                        "-fx-cursor:hand;");
                });

                backButton.setOnMouseExited(e -> {
                        backButton.setStyle(
                                        "-fx-background-color:#FFF0E7;" +
                                                        "-fx-text-fill:#8A3600;" +
                                                        "-fx-font-size:13px;" +
                                                        "-fx-font-weight:bold;" +
                                                        "-fx-background-radius:20;" +
                                                        "-fx-border-color:#E7BDA6;" +
                                                        "-fx-border-radius:20;" +
                                                        "-fx-cursor:hand;");
                });

                backButton.setOnAction(e -> {
                        try {
                                Homepage homePage = new Homepage();
                                homePage.start(Homepage.HomepageStage);
                        } catch (Exception ex) {
                                ex.printStackTrace();
                        }
                });

                Text logo = new Text("🚀 BuyNeX");
                logo.setStyle(
                                "-fx-fill:#B75B0A;" +
                                                "-fx-font-size:28px;" +
                                                "-fx-font-weight:bold;");

                Text adminPortal = new Text("ADMIN PORTAL");
                adminPortal.setStyle(
                                "-fx-fill:#666666;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");

                Text title = new Text("Welcome Back, Admin");
                title.setStyle(
                                "-fx-fill:#171717;" +
                                                "-fx-font-size:23px;" +
                                                "-fx-font-weight:bold;");

                Text subTitle = new Text(
                                "Securely access the BuyNeX Admin Dashboard.");
                subTitle.setStyle(
                                "-fx-fill:#777777;" +
                                                "-fx-font-size:14px;");

                Text idLabel = new Text("Admin ID or Username");
                idLabel.setStyle(
                                "-fx-fill:#333333;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                TextField idField = new TextField();
                idField.setPromptText("e.g. ADM-7720");
                idField.setPrefHeight(45);

                VBox idBox = new VBox(5);
                idBox.getChildren().addAll(
                                idLabel,
                                idField);

                Text emailLabel = new Text("Email Address");
                emailLabel.setStyle(
                                "-fx-fill:#333333;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                TextField emailField = new TextField();
                emailField.setPromptText("admin@buynex.com");
                emailField.setPrefHeight(45);

                VBox emailBox = new VBox(5);
                emailBox.getChildren().addAll(
                                emailLabel,
                                emailField);

                Text passwordBtn = new Text("Password");
                passwordBtn.setStyle(
                                "-fx-fill:#333333;" +
                                                "-fx-font-size:10px;" +
                                                "-fx-font-weight:bold;");

                Hyperlink forgot = new Hyperlink("Forgot password?");
                forgot.setStyle(
                                "-fx-text-fill:#B75B0A;" +
                                                "-fx-font-size:12px;" +
                                                "-fx-border-color:transparent;");

                HBox passwordTitle = new HBox();
                passwordTitle.setAlignment(Pos.CENTER_LEFT);

                HBox.setHgrow(forgot, Priority.ALWAYS);

                passwordTitle.getChildren().addAll(
                                passwordBtn,
                                forgot);

                PasswordField passwordField = new PasswordField();
                passwordField.setPromptText("••••••••");
                passwordField.setPrefHeight(45);

                VBox passwordBox = new VBox(5);
                passwordBox.getChildren().addAll(
                                passwordTitle,
                                passwordField);

                Text errorMessage = new Text("");
                errorMessage.setStyle(
                                "-fx-fill:#D32F2F;" +
                                                "-fx-font-size:13px;" +
                                                "-fx-font-weight:bold;");
                errorMessage.setVisible(false);
                errorMessage.setManaged(false);

                Button login = new Button("Login   →");
                login.setPrefHeight(50);
                login.setMaxWidth(Double.MAX_VALUE);

                login.setStyle(
                                "-fx-background-color:linear-gradient(to right,#B75B0A,#ff7300);" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-size:18px;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:8;");

                ControllerFirebase controler = new ControllerFirebase();

                emailField.textProperty().addListener((obs, oldValue, newValue) -> {
                        errorMessage.setVisible(false);
                        errorMessage.setManaged(false);
                });

                passwordField.textProperty().addListener((obs, oldValue, newValue) -> {
                        errorMessage.setVisible(false);
                        errorMessage.setManaged(false);
                });

                login.setOnAction(e -> {

                        String email = emailField.getText().trim();
                        String password = passwordField.getText();

                        if (email.isEmpty() || password.isEmpty()) {

                                errorMessage.setText(
                                                "Please enter email and password.");
                                errorMessage.setVisible(true);
                                errorMessage.setManaged(true);

                                return;
                        }

                        boolean flage = controler.login(
                                        email,
                                        password
                        );

                        if (flage) {

                                errorMessage.setVisible(false);
                                errorMessage.setManaged(false);

                                System.out.println("Admin Login successful");

                                System.out.println(
                                                "Profile loaded for: "
                                                                + AdminSession.fullName);

                                AdminDashboardPage dashboardPage =
                                                new AdminDashboardPage();

                                Homepage.HomepageStage.setScene(
                                                dashboardPage.getUserScene()
                                );

                        } else {

                                System.out.println("Admin Login failed");

                                errorMessage.setText(
                                                "Invalid login, email not verified, or Admin access not found.");
                                errorMessage.setVisible(true);
                                errorMessage.setManaged(true);
                        }
                });

                Separator separator1 = new Separator();
                Separator separator2 = new Separator();

                separator1.setPrefWidth(100);
                separator2.setPrefWidth(100);

                Button signUp = new Button("OR REGISTER");

                signUp.setStyle(
                                "-fx-fill:#777777;" +
                                                "-fx-font-size:11px;" +
                                                "-fx-font-weight:bold;");

                signUp.setOnAction(e -> {

                        AdminRegistrationPage register = new AdminRegistrationPage();
                        Runnable logiRunnable = new Runnable() {
                                public void run(){
                                        back();

                                }
                        };
                        Homepage.HomepageStage.setScene(
                                        register.getUserScene(logiRunnable));
                });

                HBox signInBox = new HBox(10);
                signInBox.setAlignment(Pos.CENTER);

                signInBox.getChildren().addAll(
                                separator1,
                                signUp,
                                separator2);

                Image googleImage = new Image("assets\\images\\admin\\google_logo.png");

                ImageView googleIcon = new ImageView(googleImage);

                googleIcon.setFitWidth(18);
                googleIcon.setFitHeight(18);
                googleIcon.setPreserveRatio(true);

                Button google = new Button("Google Workspace");

                google.setGraphic(googleIcon);
                google.setContentDisplay(ContentDisplay.LEFT);
                google.setGraphicTextGap(10);

                google.setPrefHeight(45);
                google.setMaxWidth(Double.MAX_VALUE);
                google.setAlignment(Pos.CENTER);

                google.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#eadede;" +
                                                "-fx-border-radius:8;" +
                                                "-fx-background-radius:8;" +
                                                "-fx-font-size:14px;");


                google.setOnAction(event -> {

                        google.setDisable(true);
                        google.setText(
                                        "Opening Google..."
                        );

                        errorMessage.setVisible(false);
                        errorMessage.setManaged(false);


                        Thread googleLoginThread =
                                        new Thread(() -> {

                                                GoogleAuthController googleAuth =
                                                                new GoogleAuthController();


                                                boolean googleLoginSuccess =
                                                                googleAuth.loginWithGoogle();


                                                Platform.runLater(() -> {

                                                        google.setDisable(false);
                                                        google.setText(
                                                                        "Google Workspace"
                                                        );


                                                        if (googleLoginSuccess) {

                                                                System.out.println(
                                                                                "Google Admin Login successful"
                                                                );


                                                                AdminDashboardPage dashboardPage =
                                                                                new AdminDashboardPage();


                                                                Homepage.HomepageStage.setScene(
                                                                                dashboardPage.getUserScene()
                                                                );

                                                        } else {

                                                                errorMessage.setText(
                                                                                "Google account is not registered as an Admin."
                                                                );

                                                                errorMessage.setVisible(true);
                                                                errorMessage.setManaged(true);
                                                        }
                                                });
                                        });


                        googleLoginThread.setDaemon(true);
                        googleLoginThread.start();
                });

                VBox card = new VBox(14);

                card.setAlignment(Pos.CENTER_LEFT);
                card.setPadding(new Insets(35));
                card.setPrefWidth(410);
                card.setMaxWidth(410);

                card.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-background-radius:25;" +
                                                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.12),20,0,0,5);");

                card.getChildren().addAll(
                                title,
                                subTitle,
                                idBox,
                                emailBox,
                                passwordBox,
                                errorMessage,
                                login,
                                signInBox,
                                google);

                Text security = new Text(
                                "🛡  Only authorized administrators can access this portal.");

                security.setStyle(
                                "-fx-fill:#666666;" +
                                                "-fx-font-size:11px;");

                HBox securityBox = new HBox();
                securityBox.setAlignment(Pos.CENTER);
                securityBox.setPadding(
                                new Insets(8, 18, 8, 18));

                securityBox.getChildren().add(security);

                Text privacy = new Text("Privacy Protocol");
                Text securityText = new Text("Security Guidelines");
                Text help = new Text("Help Desk");

                privacy.setStyle(
                                "-fx-fill:#777777;" +
                                                "-fx-font-size:11px;");

                securityText.setStyle(
                                "-fx-fill:#777777;" +
                                                "-fx-font-size:11px;");

                help.setStyle(
                                "-fx-fill:#777777;" +
                                                "-fx-font-size:11px;");

                HBox footer = new HBox(25);
                footer.setAlignment(Pos.CENTER);

                footer.getChildren().addAll(
                                privacy,
                                securityText,
                                help);

                Text textD = new Text(
                                "© 2024 BuyNeX Hyperlocal Ecosystems Inc.");

                textD.setStyle(
                                "-fx-fill:#888888;" +
                                                "-fx-font-size:11px;");

                RadialGradient orangeGlow = new RadialGradient(
                                0,
                                0,
                                0.82,
                                0.18,
                                0.45,
                                true,
                                CycleMethod.NO_CYCLE,
                                new Stop(
                                                0.0,
                                                Color.web("#D86B1F", 0.20)),
                                new Stop(
                                                0.35,
                                                Color.web("#EAD7D0", 0.15)),
                                new Stop(
                                                0.70,
                                                Color.web("#FAF8FE", 0.05)),
                                new Stop(
                                                1.0,
                                                Color.web("#FAF8FE", 0.0)));

                VBox vb = new VBox(12);

                vb.setAlignment(Pos.CENTER);
                vb.setPadding(new Insets(30));
                vb.setStyle("-fx-background-color: #eee5df;");

                vb.setBackground(
                                new Background(
                                                new BackgroundFill(
                                                                orangeGlow,
                                                                CornerRadii.EMPTY,
                                                                Insets.EMPTY)));

                vb.getChildren().addAll(
                                logo,
                                adminPortal,
                                card,
                                securityBox,
                                footer,
                                textD);

                StackPane root = new StackPane();

                root.getChildren().addAll(
                                vb,
                                backButton
                );

                StackPane.setAlignment(
                                backButton,
                                Pos.TOP_LEFT
                );

                StackPane.setMargin(
                                backButton,
                                new Insets(
                                                25,
                                                0,
                                                0,
                                                30
                                )
                );

                Scene scene = new Scene(
                                root,
                                1550,
                                850
                );

                LoginScene = scene;

                return LoginScene;

        }
        public void back(){
        Homepage.HomepageStage.setScene(LoginScene);
    }

}
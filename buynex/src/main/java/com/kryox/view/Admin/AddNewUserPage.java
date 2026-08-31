package com.kryox.view.Admin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.cloud.firestore.Firestore;
import com.kryox.config.Firebaseconfig;
import com.kryox.controller.Admin.Controller;
import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.view.Customer.Homepage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
import javafx.stage.FileChooser;

public class AddNewUserPage {

        public Scene getAddUserScene() {

                VBox left = new VBox();
                left.setPrefWidth(210);
                left.setSpacing(28);
                left.setPadding(new Insets(30, 15, 20, 15));
                left.setStyle("-fx-background-color:#F3E3D3;");

                Text logo = new Text("Admin Panel");
                logo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                logo.setFill(Color.web("#A83E00"));

                Text controller = new Text("Marketplace Controller");
                controller.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                controller.setFill(Color.web("#999999"));

                VBox logoBox = new VBox(4, logo, controller);

                Image dashboardImage = new Image(
                                getClass().getResource("/assets/images/admin/dashboard.png").toExternalForm());
                ImageView dashboardIcon = new ImageView(dashboardImage);
                dashboardIcon.setFitWidth(20);
                dashboardIcon.setFitHeight(20);
                dashboardIcon.setPreserveRatio(true);
                Text dashboardText = new Text("Dashboard");
                dashboardText.setFill(Color.web("#333333"));
                dashboardText.setFont(Font.font("Arial", 14));
                HBox dashboard = new HBox(10, dashboardIcon, dashboardText);
                dashboard.setAlignment(Pos.CENTER_LEFT);
                dashboard.setPadding(new Insets(10, 12, 10, 12));
                dashboard.setPrefWidth(180);

                Image usersImage = new Image("assets\\images\\admin\\admin_logo.png");
                ImageView usersIcon = new ImageView(usersImage);
                usersIcon.setFitWidth(20);
                usersIcon.setFitHeight(20);
                usersIcon.setPreserveRatio(true);
                Text usersText = new Text("Users");
                usersText.setFill(Color.WHITE);
                usersText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                HBox users = new HBox(10, usersIcon, usersText);
                users.setAlignment(Pos.CENTER_LEFT);
                users.setPadding(new Insets(10, 12, 10, 12));
                users.setPrefWidth(180);
                users.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-background-radius:10;");

                Image shopsImage = new Image(
                                getClass().getResource("/assets/images/admin/shop.png").toExternalForm());
                ImageView shopsIcon = new ImageView(shopsImage);
                shopsIcon.setFitWidth(20);
                shopsIcon.setFitHeight(20);
                shopsIcon.setPreserveRatio(true);
                Text shopsText = new Text("Shops");
                shopsText.setFill(Color.web("#333333"));
                shopsText.setFont(Font.font("Arial", 14));
                HBox shops = new HBox(10, shopsIcon, shopsText);
                shops.setAlignment(Pos.CENTER_LEFT);
                shops.setPadding(new Insets(10, 12, 10, 12));
                shops.setPrefWidth(180);

                Image offersImage = new Image(
                                getClass().getResource("/assets/images/admin/tag.png").toExternalForm());
                ImageView offersIcon = new ImageView(offersImage);
                offersIcon.setFitWidth(20);
                offersIcon.setFitHeight(20);
                offersIcon.setPreserveRatio(true);
                Text offersText = new Text("Offers");
                offersText.setFill(Color.web("#333333"));
                offersText.setFont(Font.font("Arial", 14));
                HBox offers = new HBox(10, offersIcon, offersText);
                offers.setAlignment(Pos.CENTER_LEFT);
                offers.setPadding(new Insets(10, 12, 10, 12));
                offers.setPrefWidth(180);

                Image analyticsImage = new Image(
                                getClass().getResource("/assets/images/admin/stats.png").toExternalForm());
                ImageView analyticsIcon = new ImageView(analyticsImage);
                analyticsIcon.setFitWidth(20);
                analyticsIcon.setFitHeight(20);
                analyticsIcon.setPreserveRatio(true);
                Text analyticsText = new Text("Analytics");
                analyticsText.setFill(Color.web("#333333"));
                analyticsText.setFont(Font.font("Arial", 14));
                HBox analytics = new HBox(10, analyticsIcon, analyticsText);
                analytics.setAlignment(Pos.CENTER_LEFT);
                analytics.setPadding(new Insets(10, 12, 10, 12));
                analytics.setPrefWidth(180);

                VBox menu = new VBox(4, dashboard, users, shops, offers, analytics);

                Image settingsImage = new Image(
                                getClass().getResource("/assets/images/admin/setting.png").toExternalForm());
                ImageView settingsIcon = new ImageView(settingsImage);
                settingsIcon.setFitWidth(20);
                settingsIcon.setFitHeight(20);
                settingsIcon.setPreserveRatio(true);
                Text settingsText = new Text("Settings");
                settingsText.setFill(Color.web("#333333"));
                settingsText.setFont(Font.font("Arial", 14));
                HBox settings = new HBox(10, settingsIcon, settingsText);
                settings.setAlignment(Pos.CENTER_LEFT);
                settings.setPadding(new Insets(10, 12, 10, 12));
                settings.setPrefWidth(180);

                Image supportImage = new Image(
                                getClass().getResource("/assets/images/admin/service-call.png").toExternalForm());
                ImageView supportIcon = new ImageView(supportImage);
                supportIcon.setFitWidth(20);
                supportIcon.setFitHeight(20);
                supportIcon.setPreserveRatio(true);
                Text supportText = new Text("Support");
                supportText.setFill(Color.web("#333333"));
                supportText.setFont(Font.font("Arial", 14));
                HBox support = new HBox(10, supportIcon, supportText);
                support.setAlignment(Pos.CENTER_LEFT);
                support.setPadding(new Insets(10, 12, 10, 12));
                support.setPrefWidth(180);

                VBox bottomMenu = new VBox(4, settings, support);

                Circle adminAvatar = new Circle(19, Color.web("#D9B79C"));

                Text admin = new Text("Alex Rivera");
                admin.setFont(Font.font("Arial", FontWeight.BOLD, 13));

                Text adminRole = new Text("Super Admin");
                adminRole.setFont(Font.font("Arial", 11));
                adminRole.setFill(Color.web("#777777"));

                VBox adminText = new VBox(2, admin, adminRole);
                HBox profile = new HBox(10, adminAvatar, adminText);
                profile.setAlignment(Pos.CENTER_LEFT);
                profile.setPadding(new Insets(10));
                profile.setStyle(
                                "-fx-background-color:#E4E2E7;" +
                                                "-fx-background-radius:12;");

                Region leftGrow = new Region();
                VBox.setVgrow(leftGrow, Priority.ALWAYS);

                left.getChildren().addAll(
                                logoBox,
                                menu,
                                new Separator(),
                                bottomMenu,
                                leftGrow,
                                profile);

                VBox rightBox = new VBox(20);
                rightBox.setPadding(new Insets(20, 25, 20, 25));
                rightBox.setStyle("-fx-background-color: #eee5df;");

                Text searchIcon = new Text("⌕");
                searchIcon.setFont(Font.font(24));

                TextField topSearch = new TextField();
                topSearch.setPromptText("Search anything...");
                topSearch.setPrefWidth(260);
                topSearch.setStyle(
                                "-fx-background-color:#F2F0F5;" +
                                                "-fx-background-radius:18;");

                HBox topSearchBox = new HBox(8, searchIcon, topSearch);
                topSearchBox.setStyle("-fx-background-color:#eee5df;");
                topSearchBox.setAlignment(Pos.CENTER_LEFT);

                Image chatbotImage = new Image(
                                getClass().getResource("/assets/images/admin/message.png").toExternalForm());
                ImageView chatbot = new ImageView(chatbotImage);
                chatbot.setFitWidth(22);
                chatbot.setFitHeight(22);
                chatbot.setPreserveRatio(true);

                Image bellImage = new Image(
                                getClass().getResource("/assets/images/admin/bell.png").toExternalForm());
                ImageView bell = new ImageView(bellImage);
                bell.setFitWidth(22);
                bell.setFitHeight(22);
                bell.setPreserveRatio(true);

                Text alex = new Text("Alex");
                alex.setFont(Font.font("Arial", FontWeight.BOLD, 17));

                HBox topRight = new HBox(18, chatbot, bell, alex);
                topRight.setAlignment(Pos.CENTER_RIGHT);

                Region topGrow = new Region();
                HBox.setHgrow(topGrow, Priority.ALWAYS);

                HBox top = new HBox(topSearchBox, topGrow, topRight);
                top.setAlignment(Pos.CENTER_LEFT);

                Text back = new Text("←  Back to Users");
                back.setFont(Font.font("Arial", 14));
                back.setFill(Color.web("#333333"));

                Text title = new Text("Add New User");
                title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

                Text subtitle = new Text("Create a new customer or shopkeeper account.");
                subtitle.setFont(Font.font("Arial", 14));
                subtitle.setFill(Color.web("#777777"));

                VBox heading = new VBox(8, back, title, subtitle);

                ImageView profileImage = new ImageView();
                profileImage.setFitWidth(110);
                profileImage.setFitHeight(110);
                profileImage.setPreserveRatio(false);
                profileImage.setClip(new Circle(55, 55, 55));

                Circle photoCircle = new Circle(55);
                photoCircle.setFill(Color.web("#F8F7F8"));
                photoCircle.setStroke(Color.web("#D3D0D5"));

                Image img = new Image("assets\\images\\admin\\admin_logo.png");

                ImageView personIcon = new ImageView(img);

                personIcon.setFitWidth(86);
                personIcon.setFitHeight(86);
                personIcon.setPreserveRatio(true);

                StackPane photo = new StackPane(photoCircle, personIcon, profileImage);

                Button uploadPhoto = new Button("Upload Photo");
                uploadPhoto.setPrefSize(155, 42);
                uploadPhoto.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-text-fill:#FF6500;" +
                                                "-fx-border-color:#FF6500;" +
                                                "-fx-border-radius:7;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:13px;");
                Controller controller1 = new Controller();
                final String[] uploadedProfileUrl = { "" };

                uploadPhoto.setOnAction(event -> {

                        FileChooser fileChooser = new FileChooser();

                        fileChooser.getExtensionFilters().add(
                                        new FileChooser.ExtensionFilter(
                                                        "Images",
                                                        "*.png",
                                                        "*.jpg",
                                                        "*.jpeg"));

                        File selectedFile = fileChooser.showOpenDialog(
                                        uploadPhoto.getScene().getWindow());

                        if (selectedFile != null) {

                                System.out.println(
                                                "Selected file: " + selectedFile.getAbsolutePath());

                                Image selectedImage = new Image(selectedFile.toURI().toString());
                                profileImage.setImage(selectedImage);
                                personIcon.setVisible(false);

                                String cloudinaryUrl = controller1.imageUpload(selectedFile);

                                if (cloudinaryUrl != null) {

                                        System.out.println(
                                                        "Cloudinary URL: " + cloudinaryUrl);

                                        uploadedProfileUrl[0] = cloudinaryUrl;

                                        Image image = new Image(cloudinaryUrl, true);
                                        profileImage.setImage(image);

                                } else {
                                        System.out.println("Image upload failed");
                                }
                        }
                });
                VBox photoBox = new VBox(15, photo, uploadPhoto);
                photoBox.setAlignment(Pos.TOP_CENTER);
                photoBox.setPrefWidth(210);
                photoBox.setPadding(new Insets(20));

                TextField fullName = new TextField();
                fullName.setPromptText("e.g. John Doe");

                TextField email = new TextField();
                email.setPromptText("e.g. john.doe@example.com");

                TextField phone = new TextField();
                phone.setPromptText("e.g. +91 98765 43210");

                ComboBox<String> role = new ComboBox<>();
                role.setPromptText("Select role");
                role.getItems().addAll("Customer", "Shopkeeper");

                ComboBox<String> status = new ComboBox<>();
                status.setPromptText("Select status");
                status.getItems().addAll("Active", "Suspended");

                PasswordField password = new PasswordField();
                password.setPromptText("Enter password");

                PasswordField confirmPassword = new PasswordField();
                confirmPassword.setPromptText("Confirm password");

                TextArea address = new TextArea();
                address.setPromptText("e.g. 123 Main Street, City, State, ZIP Code");
                address.setPrefRowCount(2);
                address.setWrapText(true);

                TextField[] fields = { fullName, email, phone, password, confirmPassword };

                for (TextField field : fields) {
                        field.setPrefHeight(40);
                        field.setStyle(
                                        "-fx-background-color:white;" +
                                                        "-fx-border-color:#DDD9E0;" +
                                                        "-fx-border-radius:6;" +
                                                        "-fx-background-radius:6;" +
                                                        "-fx-padding:0 12 0 12;" +
                                                        "-fx-font-size:13px;");
                }

                role.setPrefHeight(40);
                status.setPrefHeight(40);
                role.setMaxWidth(Double.MAX_VALUE);
                status.setMaxWidth(Double.MAX_VALUE);
                role.setStyle("-fx-background-color:white;-fx-border-color:#DDD9E0;-fx-border-radius:6;-fx-font-size:13px;");
                status.setStyle("-fx-background-color:white;-fx-border-color:#DDD9E0;-fx-border-radius:6;-fx-font-size:13px;");

                address.setStyle(
                                "-fx-control-inner-background:white;" +
                                                "-fx-border-color:#DDD9E0;" +
                                                "-fx-border-radius:6;" +
                                                "-fx-background-radius:6;" +
                                                "-fx-font-size:13px;");

                VBox nameBox = new VBox(6, new Label("Full Name"), fullName);
                VBox emailBox = new VBox(6, new Label("Email Address"), email);
                VBox phoneBox = new VBox(6, new Label("Phone Number"), phone);
                VBox roleBox = new VBox(6, new Label("Role"), role);
                VBox statusBox = new VBox(6, new Label("Status"), status);
                VBox passwordBox = new VBox(6, new Label("Password"), password);
                VBox confirmBox = new VBox(6, new Label("Confirm Password"), confirmPassword);

                VBox[] formBoxes = {
                                nameBox, emailBox, phoneBox, roleBox,
                                statusBox, passwordBox, confirmBox
                };

                for (VBox box : formBoxes) {
                        ((Label) box.getChildren().get(0)).setStyle("-fx-font-weight:bold;-fx-font-size:13px;");
                        box.setMaxWidth(Double.MAX_VALUE);
                        HBox.setHgrow(box, Priority.ALWAYS);
                }

                HBox formRow1 = new HBox(25, nameBox, emailBox);
                HBox formRow2 = new HBox(25, phoneBox, roleBox);
                HBox formRow3 = new HBox(25, statusBox, passwordBox);

                Region confirmGrow = new Region();
                HBox.setHgrow(confirmGrow, Priority.ALWAYS);
                HBox formRow4 = new HBox(25, confirmBox, confirmGrow);
                confirmBox.setPrefWidth(450);

                VBox addressBox = new VBox(6, new Label("Address"), address);
                ((Label) addressBox.getChildren().get(0)).setStyle("-fx-font-weight:bold;-fx-font-size:13px;");

                Button cancel = new Button("Cancel");
                cancel.setPrefSize(125, 43);
                cancel.setStyle(
                                "-fx-background-color:#E8E6EA;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:13px;");

                Button save = new Button("Save User");
                save.setPrefSize(145, 43);
                save.setStyle(
                                "-fx-background-color:#FF6500;" +
                                                "-fx-text-fill:white;" +
                                                "-fx-font-weight:bold;" +
                                                "-fx-background-radius:7;" +
                                                "-fx-font-size:13px;");

                HBox buttonBox = new HBox(20, cancel, save);
                buttonBox.setAlignment(Pos.CENTER_RIGHT);

                VBox form = new VBox(
                                20,
                                formRow1,
                                formRow2,
                                formRow3,
                                formRow4,
                                addressBox,
                                buttonBox);
                form.setPadding(new Insets(20));
                HBox.setHgrow(form, Priority.ALWAYS);

                HBox formCard = new HBox(photoBox, new Separator(), form);
                formCard.setPadding(new Insets(20));
                formCard.setAlignment(Pos.TOP_LEFT);
                formCard.setStyle(
                                "-fx-background-color:white;" +
                                                "-fx-border-color:#E5E1E8;" +
                                                "-fx-border-radius:12;" +
                                                "-fx-background-radius:12;");
                VBox.setVgrow(formCard, Priority.ALWAYS);

                back.setOnMouseClicked(e -> {
                        UserManagementPage page = new UserManagementPage();
                        Homepage.HomepageStage.setScene(page.getUserScene());
                });

                cancel.setOnAction(e -> {
                        UserManagementPage page = new UserManagementPage();
                        Homepage.HomepageStage.setScene(page.getUserScene());
                });

                save.setOnAction(e -> {
                        if (fullName.getText().isBlank() ||
                                        email.getText().isBlank() ||
                                        role.getValue() == null ||
                                        status.getValue() == null ||
                                        password.getText().isBlank()) {

                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setHeaderText(null);
                                alert.setContentText("Please fill all required fields.");
                                alert.showAndWait();
                                return;
                        }

                        if (!password.getText().equals(confirmPassword.getText())) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText(null);
                                alert.setContentText("Password and Confirm Password do not match.");
                                alert.showAndWait();
                                return;
                        }

                        String nameValue = fullName.getText().trim();
                        String emailValue = email.getText().trim().toLowerCase();
                        String phoneValue = phone.getText().trim();
                        String roleValue = role.getValue().trim();
                        String statusValue = status.getValue().trim();
                        String addressValue = address.getText().trim();
                        String passwordValue = password.getText();
                        String profileUrlValue = uploadedProfileUrl[0] == null
                                        ? ""
                                        : uploadedProfileUrl[0].trim();

                        save.setDisable(true);
                        save.setText("Saving...");

                        Thread saveThread = new Thread(() -> {
                                try {
                                        ControllerFirebase authController = new ControllerFirebase();

                                        String authToken = authController.signUp(
                                                        emailValue,
                                                        passwordValue);

                                        if (authToken == null) {
                                                Platform.runLater(() -> {
                                                        save.setDisable(false);
                                                        save.setText("Save User");

                                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                                        alert.setHeaderText(null);
                                                        alert.setContentText(
                                                                        "Authentication account could not be created. Email may already exist.");
                                                        alert.showAndWait();
                                                });
                                                return;
                                        }

                                        Map<String, Object> userData = new HashMap<>();
                                        userData.put("name", nameValue);
                                        userData.put("email", emailValue);
                                        userData.put("mobile", phoneValue);
                                        userData.put("role", roleValue);
                                        userData.put("status", statusValue);
                                        userData.put("address", addressValue);
                                        userData.put("profileImageUrl", profileUrlValue);

                                        Firestore db = Firebaseconfig.gFirestore();

                                        db.collection("User")
                                                        .document(emailValue)
                                                        .set(userData)
                                                        .get();

                                        Platform.runLater(() -> {
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setHeaderText(null);
                                                alert.setContentText(
                                                                "User saved successfully as " + roleValue + ".");
                                                alert.showAndWait();

                                                UserManagementPage page = new UserManagementPage();
                                                Homepage.HomepageStage.setScene(page.getUserScene());
                                        });

                                } catch (Exception ex) {
                                        ex.printStackTrace();

                                        Platform.runLater(() -> {
                                                save.setDisable(false);
                                                save.setText("Save User");

                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setHeaderText(null);
                                                alert.setContentText(
                                                                "User could not be saved: " + ex.getMessage());
                                                alert.showAndWait();
                                        });
                                }
                        });

                        saveThread.setDaemon(true);
                        saveThread.start();
                });

                rightBox.getChildren().addAll(top, heading, formCard);

                BorderPane root = new BorderPane();
                root.setLeft(left);
                root.setCenter(rightBox);
                root.setStyle("-fx-background-color:#FAF8FC;");

                Scene scene = new Scene(root, 1550, 850);
                return scene;
        }
}
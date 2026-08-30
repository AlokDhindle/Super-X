package com.kryox.view.Admin;

import com.kryox.dao.Admin.AdminDao;
import com.kryox.model.Admin.Admin;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminRegistrationPage {

    public static Stage registerPStage;

    private Scene registerScene;

    public Scene getUserScene(Runnable callBack) {

        Text logo = new Text("BuyNeX");
        logo.setStyle(
                "-fx-fill:white;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        Label info = new Label("Centralized Administration Portal");
        info.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );

        Image img1 = new Image(getClass().getResource("/assets/images/admin/admin logo.png").toExternalForm());

        ImageView iv1 = new ImageView(img1);
        iv1.setFitWidth(24);
        iv1.setFitHeight(24);
        iv1.setPreserveRatio(true);

        StackPane iconBox1 = new StackPane(iv1);
        iconBox1.setPrefSize(38, 38);
        iconBox1.setMinSize(38, 38);
        iconBox1.setMaxSize(38, 38);
        iconBox1.setStyle(
                "-fx-background-color:#ffffff33;" +
                "-fx-background-radius:7;"
        );

        Label f1 = new Label("Secure Admin Access");
        f1.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );

        HBox feature1 = new HBox(10);
        feature1.setAlignment(Pos.CENTER_LEFT);
        feature1.getChildren().addAll(iconBox1, f1);

        Image img2 = new Image(getClass().getResource("/assets/images/admin/sequrity logo.png").toExternalForm());

        ImageView iv2 = new ImageView(img2);
        iv2.setFitWidth(24);
        iv2.setFitHeight(24);
        iv2.setPreserveRatio(true);

        StackPane iconBox2 = new StackPane(iv2);
        iconBox2.setPrefSize(38, 38);
        iconBox2.setMinSize(38, 38);
        iconBox2.setMaxSize(38, 38);
        iconBox2.setStyle(
                "-fx-background-color:#ffffff33;" +
                "-fx-background-radius:7;"
        );

        Label f2 = new Label("Role-based Permissions");
        f2.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );

        HBox feature2 = new HBox(10);
        feature2.setAlignment(Pos.CENTER_LEFT);
        feature2.getChildren().addAll(iconBox2, f2);

        Image img3 = new Image(getClass().getResource("/assets/images/admin/analysis logo.png").toExternalForm());

        ImageView iv3 = new ImageView(img3);
        iv3.setFitWidth(24);
        iv3.setFitHeight(24);
        iv3.setPreserveRatio(true);

        StackPane iconBox3 = new StackPane(iv3);
        iconBox3.setPrefSize(38, 38);
        iconBox3.setMinSize(38, 38);
        iconBox3.setMaxSize(38, 38);
        iconBox3.setStyle(
                "-fx-background-color:#ffffff33;" +
                "-fx-background-radius:7;"
        );

        Label f3 = new Label("Real-time Operations Analytics");
        f3.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:15px;"
        );
        f3.setWrapText(true);

        HBox feature3 = new HBox(10);
        feature3.setAlignment(Pos.CENTER_LEFT);
        feature3.getChildren().addAll(iconBox3, f3);

        Image img = new Image(getClass().getResource("/assets/images/admin/img.png").toExternalForm());

        ImageView image = new ImageView(img);
        image.setFitWidth(200);
        image.setFitHeight(145);
        image.setPreserveRatio(true);
        image.setTranslateY(180);

        Label title = new Label("Create Admin Account");
        title.setStyle(
                "-fx-text-fill:#a84400;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        Label sub = new Label("Register a new administrator for BuyNeX.");
        sub.setStyle(
                "-fx-text-fill:#555555;" +
                "-fx-font-size:14px;"
        );

        Label nameLabel = new Label("Full Name");
        nameLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("John Doe");
        nameField.setPrefHeight(42);
        nameField.setStyle("-fx-font-size:13px;");

        VBox name = new VBox(4);
        name.getChildren().addAll(
                nameLabel,
                nameField
        );

        Label usernameLabel = new Label("Admin Username");
        usernameLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("j.doe_nx");
        usernameField.setPrefHeight(42);
        usernameField.setStyle("-fx-font-size:13px;");

        VBox username = new VBox(4);
        username.getChildren().addAll(
                usernameLabel,
                usernameField
        );

        HBox row1 = new HBox(16);
        row1.getChildren().addAll(
                name,
                username
        );

        HBox.setHgrow(name, Priority.ALWAYS);
        HBox.setHgrow(username, Priority.ALWAYS);

        Label emailLabel = new Label("Official Email");
        emailLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField emailField = new TextField();
        emailField.setPromptText("admin@buynex.com");
        emailField.setPrefHeight(42);
        emailField.setStyle("-fx-font-size:13px;");

        VBox email = new VBox(4);
        email.getChildren().addAll(
                emailLabel,
                emailField
        );

        Label mobileLabel = new Label("Mobile Number");
        mobileLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField mobileField = new TextField();
        mobileField.setPromptText("+1 (555) 000-0000");
        mobileField.setPrefHeight(42);
        mobileField.setStyle("-fx-font-size:13px;");

        VBox mobile = new VBox(4);
        mobile.getChildren().addAll(
                mobileLabel,
                mobileField
        );

        HBox row2 = new HBox(16);
        row2.getChildren().addAll(
                email,
                mobile
        );

        HBox.setHgrow(email, Priority.ALWAYS);
        HBox.setHgrow(mobile, Priority.ALWAYS);

        Label employeeLabel = new Label("Employee/Admin ID");
        employeeLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        TextField employeeField = new TextField();
        employeeField.setPromptText("NX-2024-88");
        employeeField.setPrefHeight(42);
        employeeField.setStyle("-fx-font-size:13px;");

        VBox employee = new VBox(4);
        employee.getChildren().addAll(
                employeeLabel,
                employeeField
        );

        Label roleLabel = new Label("Admin Role");
        roleLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        ComboBox<String> roleBox = new ComboBox<>();

        roleBox.getItems().addAll(
                "Admin",
                "Manager",
                "Super Admin"
        );

        roleBox.setPromptText("Select Role");
        roleBox.setMaxWidth(Double.MAX_VALUE);
        roleBox.setPrefHeight(42);
        roleBox.setStyle("-fx-font-size:13px;");

        VBox role = new VBox(4);
        role.getChildren().addAll(
                roleLabel,
                roleBox
        );

        HBox row3 = new HBox(16);
        row3.getChildren().addAll(
                employee,
                role
        );

        HBox.setHgrow(employee, Priority.ALWAYS);
        HBox.setHgrow(role, Priority.ALWAYS);

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("••••••••");
        passwordField.setPrefHeight(42);
        passwordField.setStyle("-fx-font-size:13px;");

        VBox password = new VBox(4);
        password.getChildren().addAll(
                passwordLabel,
                passwordField
        );

        Label confirmLabel = new Label("Confirm Password");
        confirmLabel.setStyle("-fx-font-size:13px;-fx-font-weight:bold;");

        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("••••••••");
        confirmField.setPrefHeight(42);
        confirmField.setStyle("-fx-font-size:13px;");

        VBox confirm = new VBox(4);
        confirm.getChildren().addAll(
                confirmLabel,
                confirmField
        );

        HBox row4 = new HBox(16);
        row4.getChildren().addAll(
                password,
                confirm
        );

        HBox.setHgrow(password, Priority.ALWAYS);
        HBox.setHgrow(confirm, Priority.ALWAYS);

        Label accessLabel =
                new Label("⚿ Secret Admin Access Code");

        accessLabel.setStyle(
                "-fx-text-fill:#a84400;" +
                "-fx-font-size:14px;"
        );

        TextField accessField = new TextField();
        accessField.setPromptText("Enter authorization hash");
        accessField.setPrefHeight(42);
        accessField.setStyle("-fx-font-size:13px;");

        Label accessInfo = new Label(
                "This code is provided by the System Architect during onboarding."
        );

        accessInfo.setStyle(
                "-fx-text-fill:#777777;" +
                "-fx-font-size:13px;"
        );

        accessInfo.setWrapText(true);

        VBox access = new VBox(7);
        access.setPadding(new Insets(11));

        access.setStyle(
                "-fx-background-color:#fff0e5;" +
                "-fx-border-color:#ffd0b5;" +
                "-fx-border-width:1;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;"
        );

        access.getChildren().addAll(
                accessLabel,
                accessField,
                accessInfo
        );

        Button create = new Button("Create Account   →");
        create.setMaxWidth(Double.MAX_VALUE);
        create.setPrefHeight(48);

        create.setStyle(
                "-fx-background-color:#e85b00;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:15px;" +
                "-fx-background-radius:5;"
        );

        create.setOnAction(event -> {

            String fullName = nameField.getText().trim();
            String usernameValue = usernameField.getText().trim();
            String emailValue = emailField.getText().trim();
            String mobileValue = mobileField.getText().trim();
            String employeeId = employeeField.getText().trim();
            String roleValue = roleBox.getValue();
            String passwordValue = passwordField.getText();
            String confirmPassword = confirmField.getText();
            String accessCode = accessField.getText().trim();

            if (fullName.isEmpty()
                    || usernameValue.isEmpty()
                    || emailValue.isEmpty()
                    || mobileValue.isEmpty()
                    || employeeId.isEmpty()
                    || roleValue == null
                    || passwordValue.isEmpty()
                    || confirmPassword.isEmpty()
                    || accessCode.isEmpty()) {

                Alert alert =
                        new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Please fill all fields."
                );

                alert.showAndWait();

                return;
            }

            if (!passwordValue.equals(confirmPassword)) {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Password and Confirm Password do not match."
                );

                alert.showAndWait();

                return;
            }

            Admin admin = new Admin(
                    employeeId,
                    fullName,
                    usernameValue,
                    emailValue,
                    passwordValue,
                    mobileValue,
                    roleValue,
                    accessCode
            );

            AdminDao adminDao =
                    new AdminDao();

            boolean stored =
                    adminDao.registerAdmin(admin);

            if (stored) {

                CongratulationsPage successPage =
                        new CongratulationsPage();

                Homepage.HomepageStage.setScene(
                        successPage.getUserScene()
                );

            } else {

                Alert alert =
                        new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Firebase Error");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Admin data could not be stored."
                );

                alert.showAndWait();
            }
        });

        Label signIn =
                new Label("Already have an account? Sign In");

        signIn.setStyle(
                "-fx-text-fill:#555555;" +
                "-fx-font-size:13px;"
        );

        signIn.setOnMouseClicked(event -> {

            if (callBack != null) {
                callBack.run();
            }
        });

        VBox left = new VBox(18);

        left.setPrefWidth(295);
        left.setMinWidth(295);
        left.setMaxWidth(295);
        left.setPrefHeight(710);
        left.setPadding(
                new Insets(48, 35, 35, 45)
        );

        left.setStyle(
                "-fx-background-color:#e85b00;" +
                "-fx-background-radius:12 0 0 12;"
        );

        left.getChildren().addAll(
                logo,
                info,
                feature1,
                feature2,
                feature3,
                image
        );

        VBox right = new VBox(11);

        right.setPrefWidth(445);
        right.setMinWidth(445);
        right.setMaxWidth(445);
        right.setPrefHeight(710);
        right.setPadding(
                new Insets(42, 45, 35, 45)
        );

        right.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:0 12 12 0;"
        );

        right.getChildren().addAll(
                title,
                sub,
                row1,
                row2,
                row3,
                row4,
                access,
                create,
                signIn
        );

        HBox mainBox = new HBox();

        mainBox.setPrefWidth(740);
        mainBox.setPrefHeight(710);
        mainBox.setMaxWidth(740);
        mainBox.setMaxHeight(710);

        mainBox.setAlignment(Pos.CENTER);

        mainBox.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:12;"
        );

        DropShadow shadow = new DropShadow();

        shadow.setRadius(18);
        shadow.setSpread(0.08);
        shadow.setOffsetX(0);
        shadow.setOffsetY(6);
        shadow.setColor(
                Color.rgb(0, 0, 0, 0.15)
        );

        mainBox.setEffect(shadow);

        mainBox.getChildren().addAll(
                left,
                right
        );

        VBox rootBox = new VBox();

        rootBox.setAlignment(Pos.CENTER);
        rootBox.setPadding(
                new Insets(30)
        );

        rootBox.setStyle(
                "-fx-background-color:#fafafa;"
        );

        rootBox.getChildren().add(mainBox);

        Scene sc =
                new Scene(rootBox, 1550, 850);

        registerScene = sc;

        return registerScene;
    }
}

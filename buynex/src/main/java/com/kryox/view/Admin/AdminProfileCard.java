package com.kryox.view.Admin;

import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.model.Admin.AdminSession;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
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
import javafx.stage.Popup;

public class AdminProfileCard {

    public HBox getProfileCard() {

        String name =
                AdminSession.fullName == null
                        || AdminSession.fullName.isBlank()
                        ? "Admin"
                        : AdminSession.fullName;

        String role =
                AdminSession.role == null
                        || AdminSession.role.isBlank()
                        ? "Admin"
                        : AdminSession.role;

        String email =
                AdminSession.email == null
                        || AdminSession.email.isBlank()
                        ? "-"
                        : AdminSession.email;

        String mobile =
                AdminSession.mobile == null
                        || AdminSession.mobile.isBlank()
                        ? "-"
                        : AdminSession.mobile;

        String employeeId =
                AdminSession.employeeId == null
                        || AdminSession.employeeId.isBlank()
                        ? "-"
                        : AdminSession.employeeId;

        String letter =
                name.isBlank()
                        ? "A"
                        : name.substring(0, 1).toUpperCase();


        Circle circle =
                new Circle(
                        20,
                        Color.WHITE
                );

        Text letterText =
                new Text(letter);

        letterText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        16
                )
        );

        letterText.setFill(
                Color.web("#A83E00")
        );

        StackPane avatar =
                new StackPane(
                        circle,
                        letterText
                );


        Text nameText =
                new Text(name);

        nameText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        13
                )
        );

        Text roleText =
                new Text(role);

        roleText.setFont(
                Font.font(
                        "Arial",
                        11
                )
        );

        roleText.setFill(
                Color.web("#777777")
        );

        VBox profileText =
                new VBox(
                        2,
                        nameText,
                        roleText
                );


        Region grow =
                new Region();

        HBox.setHgrow(
                grow,
                Priority.ALWAYS
        );

        Text arrow =
                new Text("›");

        arrow.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        22
                )
        );


        HBox profile =
                new HBox(
                        10,
                        avatar,
                        profileText,
                        grow,
                        arrow
                );

        profile.setAlignment(
                Pos.CENTER_LEFT
        );

        profile.setPadding(
                new Insets(10)
        );

        profile.setPrefWidth(180);

        profile.setStyle(
                "-fx-background-color:#E4E2E7;" +
                        "-fx-background-radius:12;" +
                        "-fx-cursor:hand;"
        );


        Popup profilePopup =
                new Popup();

        profilePopup.setAutoHide(true);


        Circle popupCircle =
                new Circle(
                        23,
                        Color.web("#FF6500")
                );

        Text popupLetter =
                new Text(letter);

        popupLetter.setFill(
                Color.WHITE
        );

        popupLetter.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        StackPane popupAvatar =
                new StackPane(
                        popupCircle,
                        popupLetter
                );


        Text popupName =
                new Text(name);

        popupName.setFill(
                Color.WHITE
        );

        popupName.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        15
                )
        );

        Text popupRole =
                new Text(role);

        popupRole.setFill(
                Color.web("#BBBBBB")
        );

        popupRole.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );

        VBox popupNameBox =
                new VBox(
                        3,
                        popupName,
                        popupRole
                );


        HBox popupProfile =
                new HBox(
                        12,
                        popupAvatar,
                        popupNameBox
                );

        popupProfile.setAlignment(
                Pos.CENTER_LEFT
        );


        Text emailText =
                new Text(
                        "Email\n" + email
                );

        emailText.setFill(
                Color.WHITE
        );

        emailText.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        Text mobileText =
                new Text(
                        "Mobile\n" + mobile
                );

        mobileText.setFill(
                Color.WHITE
        );

        mobileText.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        Text employeeText =
                new Text(
                        "Employee ID\n" + employeeId
                );

        employeeText.setFill(
                Color.WHITE
        );

        employeeText.setFont(
                Font.font(
                        "Arial",
                        12
                )
        );


        Button viewProfile =
                new Button(
                        "◉   View Profile"
                );

        viewProfile.setMaxWidth(
                Double.MAX_VALUE
        );

        viewProfile.setAlignment(
                Pos.CENTER_LEFT
        );

        viewProfile.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:13px;" +
                        "-fx-cursor:hand;"
        );


        Button logout =
                new Button(
                        "↪   Log out"
                );

        logout.setMaxWidth(
                Double.MAX_VALUE
        );

        logout.setAlignment(
                Pos.CENTER_LEFT
        );

        logout.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill:white;" +
                        "-fx-font-size:13px;" +
                        "-fx-cursor:hand;"
        );


        VBox popupBox =
                new VBox(
                        14,
                        popupProfile,
                        new Separator(),
                        emailText,
                        mobileText,
                        employeeText,
                        new Separator(),
                        viewProfile,
                        logout
                );

        popupBox.setPrefWidth(
                270
        );

        popupBox.setPadding(
                new Insets(18)
        );

        popupBox.setStyle(
                "-fx-background-color:#333333;" +
                        "-fx-background-radius:18;" +
                        "-fx-border-radius:18;" +
                        "-fx-effect:dropshadow(" +
                        "gaussian," +
                        "rgba(0,0,0,0.30)," +
                        "18,0,0,5);"
        );


        profilePopup
                .getContent()
                .add(popupBox);


        profile.setOnMouseEntered(event -> {

            profile.setStyle(
                    "-fx-background-color:#D7D4DA;" +
                            "-fx-background-radius:12;" +
                            "-fx-cursor:hand;"
            );
        });


        profile.setOnMouseExited(event -> {

            profile.setStyle(
                    "-fx-background-color:#E4E2E7;" +
                            "-fx-background-radius:12;" +
                            "-fx-cursor:hand;"
            );
        });


        profile.setOnMouseClicked(event -> {

            if (profilePopup.isShowing()) {

                profilePopup.hide();

            } else {

                Bounds bounds =
                        profile.localToScreen(
                                profile.getBoundsInLocal()
                        );

                if (bounds != null) {

                    profilePopup.show(
                            profile,
                            bounds.getMinX(),
                            bounds.getMinY() - 325
                    );
                }
            }
        });


        viewProfile.setOnAction(event -> {

            profilePopup.hide();

            AdminProfilePage profilePage =
                    new AdminProfilePage();

            Homepage.HomepageStage.setScene(
                    profilePage.getUserScene()
            );
        });


        logout.setOnAction(event -> {

            profilePopup.hide();

            ControllerFirebase.clearAdminSession();

            AdminLoginPage loginPage =
                    new AdminLoginPage();

            Homepage.HomepageStage.setScene(
                    loginPage.getLoginScene()
            );
        });


        return profile;
    }
}

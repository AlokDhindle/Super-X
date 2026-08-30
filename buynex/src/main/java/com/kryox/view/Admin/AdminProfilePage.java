package com.kryox.view.Admin;

import com.kryox.controller.Admin.ControllerFirebase;
import com.kryox.model.Admin.AdminSession;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

public class AdminProfilePage {

    public Scene getUserScene() {

        String fullName =
                AdminSession.fullName == null
                        || AdminSession.fullName.isBlank()
                        ? "Admin"
                        : AdminSession.fullName;

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

        String role =
                AdminSession.role == null
                        || AdminSession.role.isBlank()
                        ? "Admin"
                        : AdminSession.role;

        String letter =
                fullName.isBlank()
                        ? "A"
                        : fullName.substring(0, 1).toUpperCase();


        VBox root =
                new VBox();

        root.setStyle(
                "-fx-background-color:#EEE5DF;"
        );


        HBox top =
                new HBox();

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        top.setPadding(
                new Insets(
                        22,
                        35,
                        22,
                        35
                )
        );

        top.setStyle(
                "-fx-background-color:white;" +
                        "-fx-border-color:transparent transparent #E5E1E8 transparent;"
        );


        Button back =
                new Button(
                        "← Back"
                );

        back.setStyle(
                "-fx-background-color:#F1EEF2;" +
                        "-fx-background-radius:8;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-cursor:hand;"
        );


        Text topTitle =
                new Text(
                        "Admin Profile"
                );

        topTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        23
                )
        );


        Region topGrow =
                new Region();

        HBox.setHgrow(
                topGrow,
                Priority.ALWAYS
        );


        Button logout =
                new Button(
                        "Log out"
                );

        logout.setStyle(
                "-fx-background-color:#FF6500;" +
                        "-fx-text-fill:white;" +
                        "-fx-background-radius:8;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;" +
                        "-fx-cursor:hand;"
        );


        top.getChildren().addAll(
                back,
                new Region(),
                topTitle,
                topGrow,
                logout
        );

        ((Region) top.getChildren().get(1))
                .setPrefWidth(25);


        Circle avatarCircle =
                new Circle(
                        48,
                        Color.web("#FF6500")
                );

        Text avatarLetter =
                new Text(letter);

        avatarLetter.setFill(
                Color.WHITE
        );

        avatarLetter.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        30
                )
        );

        StackPane avatar =
                new StackPane(
                        avatarCircle,
                        avatarLetter
                );


        Text nameText =
                new Text(fullName);

        nameText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        25
                )
        );


        Text roleText =
                new Text(role);

        roleText.setFont(
                Font.font(
                        "Arial",
                        14
                )
        );

        roleText.setFill(
                Color.web("#777777")
        );


        VBox nameBox =
                new VBox(
                        5,
                        nameText,
                        roleText
                );

        nameBox.setAlignment(
                Pos.CENTER_LEFT
        );


        HBox profileHeader =
                new HBox(
                        20,
                        avatar,
                        nameBox
                );

        profileHeader.setAlignment(
                Pos.CENTER_LEFT
        );


        Text detailsTitle =
                new Text(
                        "Account Information"
                );

        detailsTitle.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        18
                )
        );


        VBox nameRow =
                createDetailRow(
                        "Full Name",
                        fullName
                );

        VBox emailRow =
                createDetailRow(
                        "Email Address",
                        email
                );

        VBox mobileRow =
                createDetailRow(
                        "Mobile Number",
                        mobile
                );

        VBox employeeRow =
                createDetailRow(
                        "Employee ID",
                        employeeId
                );

        VBox roleRow =
                createDetailRow(
                        "Role",
                        role
                );


        VBox card =
                new VBox(
                        22,
                        profileHeader,
                        new Separator(),
                        detailsTitle,
                        nameRow,
                        emailRow,
                        mobileRow,
                        employeeRow,
                        roleRow
                );

        card.setMaxWidth(
                650
        );

        card.setPadding(
                new Insets(30)
        );

        card.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:16;" +
                        "-fx-border-color:#E5E1E8;" +
                        "-fx-border-radius:16;" +
                        "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.08),15,0,0,4);"
        );


        VBox center =
                new VBox(card);

        center.setAlignment(
                Pos.TOP_CENTER
        );

        center.setPadding(
                new Insets(
                        50,
                        30,
                        30,
                        30
                )
        );

        VBox.setVgrow(
                center,
                Priority.ALWAYS
        );


        root.getChildren().addAll(
                top,
                center
        );


        back.setOnAction(event -> {

            AdminDashboardPage dashboard =
                    new AdminDashboardPage();

            Homepage.HomepageStage.setScene(
                    dashboard.getUserScene()
            );
        });


        logout.setOnAction(event -> {

            ControllerFirebase.clearAdminSession();

            AdminLoginPage loginPage =
                    new AdminLoginPage();

            Homepage.HomepageStage.setScene(
                    loginPage.getLoginScene()
            );
        });


        return new Scene(
                root,
                1500,
                850
        );
    }


    private VBox createDetailRow(
            String label,
            String value
    ) {

        Text labelText =
                new Text(label);

        labelText.setFont(
                Font.font(
                        "Arial",
                        FontWeight.BOLD,
                        12
                )
        );

        labelText.setFill(
                Color.web("#777777")
        );


        Text valueText =
                new Text(value);

        valueText.setFont(
                Font.font(
                        "Arial",
                        15
                )
        );


        VBox row =
                new VBox(
                        5,
                        labelText,
                        valueText
                );

        row.setPadding(
                new Insets(
                        10,
                        14,
                        10,
                        14
                )
        );

        row.setStyle(
                "-fx-background-color:#F8F6F9;" +
                        "-fx-background-radius:9;"
        );


        return row;
    }
}

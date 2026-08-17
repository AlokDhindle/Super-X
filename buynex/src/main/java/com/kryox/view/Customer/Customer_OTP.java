package com.kryox.view.Customer;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Customer_OTP {
    private Scene otpScene;
    Scene getotpScene(){
        Image mobileimage=new Image("assets\\logo\\mobileicon.png");
        ImageView imgView=new ImageView(mobileimage);
        imgView.setFitWidth(25);
        imgView.setFitHeight(25);
        HBox iconBox = new HBox(imgView);
        iconBox.setAlignment(Pos.CENTER);
        iconBox.setPrefSize(60, 60);
        iconBox.setMaxSize(60, 60);
        iconBox.setStyle("-fx-background-color:#FCE8DD;-fx-background-radius:30;");

        Text t1 = new Text("BuyNeX");
        t1.setStyle("-fx-font-size:28px;-fx-font-weight:bold;-fx-fill:#9C3700;");

        HBox hb1 = new HBox(t1);
        hb1.setAlignment(Pos.CENTER);

        Text t2 = new Text("Support");
        t2.setStyle("-fx-font-size:14px;-fx-fill:#5C4033;");

        Text t3 = new Text("Partner Login");
        t3.setStyle("-fx-font-size:14px;-fx-font-weight:bold;-fx-fill:#C65A1E;");

        HBox hb2 = new HBox(20, t2, t3);
        hb2.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox hb3 = new HBox(20, hb1, spacer, hb2);
        hb3.setPadding(new Insets(20, 30, 20, 30));
        hb3.setAlignment(Pos.CENTER_LEFT);
        hb3.setStyle("-fx-background-color:white;");

        Text heading = new Text("Verify Your Mobile\nNumber");
        heading.setStyle("-fx-font-size:30px;-fx-font-weight:bold;");

        Text sub = new Text("Enter the 6-digit OTP sent to your mobile\nnumber.");
        sub.setStyle("-fx-font-size:14px;-fx-fill:gray;");

        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();
        TextField tf4 = new TextField();
        TextField tf5 = new TextField();
        TextField tf6 = new TextField();

        tf1.setPrefSize(40,40);
        tf2.setPrefSize(40,40);
        tf3.setPrefSize(40,40);
        tf4.setPrefSize(40,40);
        tf5.setPrefSize(40,40);
        tf6.setPrefSize(40,40);

        HBox otpBox = new HBox(10, tf1, tf2, tf3, tf4, tf5, tf6);
        otpBox.setAlignment(Pos.CENTER);
        
        Button btn = new Button("Verify Code");
        btn.setPrefSize(220,45);
        btn.setStyle("-fx-background-color:#D66A16;-fx-text-fill:white;-fx-font-size:16px;-fx-font-weight:bold;");
        btn.setOnAction(event->{
            Dashbord db=new Dashbord();
            Homepage.HomepageStage.setScene(db.getDashbordScene());
        });
        
        Text timer = new Text("Resend in 00:28");
        timer.setStyle("-fx-font-size:13px;");

        DropShadow headerShadow = new DropShadow();
        headerShadow.setRadius(10);
        headerShadow.setSpread(0.04);
        headerShadow.setOffsetX(0);
        headerShadow.setOffsetY(2);
        headerShadow.setColor(Color.rgb(0, 0, 0, 0.12));
        hb3.setEffect(headerShadow);


        VBox vb = new VBox(18,
        iconBox,
        heading,
        sub,
        otpBox,
        btn,
        timer );

                vb.setAlignment(Pos.CENTER);
                vb.setPrefSize(380, 420);
                vb.setMaxSize(380, 420);

                vb.setStyle(
                    "-fx-background-color:white;" +
                    "-fx-background-radius:18;" +
                    "-fx-border-radius:18;" +
                    "-fx-padding:30;"
                );     
                        
    DropShadow shadow = new DropShadow();

    shadow.setRadius(35);
    shadow.setSpread(0.15);
    shadow.setOffsetX(0);
    shadow.setOffsetY(10);

    shadow.setColor(Color.rgb(0, 0, 0, 0.30));

vb.setEffect(shadow);
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #eee5df");
        root.setTop(hb3);
        root.setCenter(vb);
        Scene scene = new Scene(root,1550,830);
        otpScene=scene;
        

        return otpScene;
    }

    
    
}
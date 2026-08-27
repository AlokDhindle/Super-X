package com.kryox;

<<<<<<< HEAD
import com.kryox.view.ShopkeeperLogin;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Starting application");
        primaryStage = stage;
        primaryStage.setScene(ShopkeeperLogin.loginscene());
        primaryStage.setTitle("BuyNeX - Shop Partner");
        primaryStage.setMaximized(false);   
        primaryStage.show();
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);

=======
import javafx.application.Application;

import com.kryox.view.Customer.BookingSuccess;
import com.kryox.view.Customer.Checkout;
import com.kryox.view.Customer.Homepage;
import com.kryox.view.Customer.ShoppingCartUI;

public class Main {

    public static void main(String[] args) {
        Application.launch(Homepage.class, args);
>>>>>>> f5a4c14ed2bacf6ba6e6e4cb4e7c3d667086c715
    }
}
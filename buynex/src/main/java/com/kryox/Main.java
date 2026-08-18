package com.kryox;

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

    }
}
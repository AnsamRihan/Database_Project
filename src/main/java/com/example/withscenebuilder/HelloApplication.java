package com.example.withscenebuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    public static int custID = -1;
    public static int AdminId = -1;

    //----------------------------------------------------------------
    //book room in customer
    public static double roomReserveTotal = 0;
    public static int roomReserveHours = 0;
    public static double roomFoodTotal = 0;
    public static boolean flagForFood = false;
    //----------------------------------------------------------------
    //reserve game in customer
    public static boolean flagForFood2 = false;
    public static int reserseGameNum = 0;
    public static double reserveGameTotal = 0;
    public static double reserveGameTime = 0;
    public static double gameFoodTotal = 0;
    //-----------------------------------------------------------------
    //for guest game
    public static boolean flagForFood3 = false;
    public static int reserseGameNumGuest = 0;
    public static double reserveGameTotalGuest = 0;
    public static double reserveGameTimeGuest = 0;
    public static double gameFoodTotalGuest = 0;

    //-----------------------------------------------------------------
    public static int orderid;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("First.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Castle");
        stage.setScene(scene);
        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\CastleLogo.png");
        stage.getIcons().add(image);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
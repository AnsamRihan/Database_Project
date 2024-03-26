package com.example.withscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class First implements Initializable{

    private Scene sceneCustomer;
    private Scene sceneAdmin;
    private Stage stage;

    private Parent root;

    @FXML
    private ImageView background;
    @FXML
    private ImageView logo;

    @FXML
    private Button CustomerButton;

    @FXML
    private Button AdminButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\BACKGROUND3.jpg");
        background.setImage(image);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\CastleLogo.png");
        logo.setImage(image2);

    }

    public void SwitchToScene1(ActionEvent event) throws  IOException{
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustomer = new Scene(root);
        stage.setScene(sceneCustomer);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws  IOException{
        root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustomer = new Scene(root);
        stage.setScene(sceneCustomer);
        stage.show();
    }
}

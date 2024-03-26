package com.example.withscenebuilder;

import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class HelloController implements  Initializable{
    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;

    @FXML
    private ImageView background;
    @FXML
    private ImageView back;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView lock;

    @FXML
    private ImageView user;

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;
    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;

    @FXML
    private Button signupButton;

    @FXML
    private Button guestButton;

    private Scene sceneMainC;
    private Scene first;
    private Scene signup;
    private Scene guest;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\BACKGROUND3.jpg");
        background.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\back21.png");
        back.setImage(image1);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\CastleLogo.png");
        logo.setImage(image2);

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\locked-computer.png");
        lock.setImage(image3);

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\user.png");
        user.setImage(image4);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("First.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneMainC = new Scene(root);
        stage.setScene(sceneMainC);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if(username.getText().isEmpty() || pass.getText().isEmpty()){
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("USERNAME OR PASSWORD CANNOT BE EMPTY");
            alert.show();
        }else{
            int id = getID(username.getText(), pass.getText());
            if(id == -1){
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("WRONG PASSWORD OR USERNAME");
                alert.show();
            }else{
                HelloApplication.custID = id;
                root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                first = new Scene(root);
                stage.setScene(first);
                stage.show();
            }
        }
    }

    public void SwitchToScene3(ActionEvent event) throws  IOException{
        root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        signup = new Scene(root);
        stage.setScene(signup);
        stage.show();
    }

    public void SwitchToScene4(ActionEvent event) throws  IOException{
        root = FXMLLoader.load(getClass().getResource("guestLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        guest = new Scene(root);
        stage.setScene(guest);
        stage.show();
    }

    private void connectDB() throws ClassNotFoundException, SQLException {

        dbURL = "jdbc:mysql://" + URL + ":" + port + "/" + dbName + "?verifyServerCertificate=false";
        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        p.setProperty("useSSL", "false");
        p.setProperty("autoReconnect", "true");
        //Class.forName("com.mysql.jdbc.Driver");

        con = DriverManager.getConnection (dbURL, p);
    }

    private int getID(String username, String password) throws SQLException, ClassNotFoundException {

        connectDB();

        System.out.println("Connection established");

        String SQLtxt = "select C_id from Acc C where C.Username = '" + username + "' and C.pass= '" + password +"';";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);

        int cid =-1;

        while ( rs.next() ) {
            cid = rs.getInt(1);
        }

        System.out.println(cid);

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed");

        return cid;
    }
}
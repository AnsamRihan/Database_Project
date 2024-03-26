package com.example.withscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {

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
    private Button backButton ;

    @FXML
    private Button signUpButton  ;


    private Scene first ;
    private Scene SignUp ;

    private Stage stage ;

    private Parent root ;

    @FXML
    private TextField Ausername;

    @FXML
    private PasswordField Apass;


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

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\adminID.png");
        user.setImage(image4);
    }


    public void SwitchToScene4(ActionEvent event) throws Exception {
        root = FXMLLoader.load(getClass().getResource("First.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        first = new Scene (root);
        stage.setScene(first);
        stage.show();
    }



    public void SwitchToScene1(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if(Ausername.getText().isEmpty() || Apass.getText().isEmpty()){
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("USERNAME OR PASSWORD CANNOT BE EMPTY");
            alert.show();
        }else{
            if(onlyDigits(Ausername.getText(), Ausername.getText().length())){
                int id = getID(Integer.parseInt(Ausername.getText()), Apass.getText());
                if(id == -1){
                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type , "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setHeaderText("WRONG PASSWORD OR USERNAME");
                    alert.show();
                }else{
                    HelloApplication.AdminId = id;
                    root = FXMLLoader.load(getClass().getResource("MainAdmin.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    first = new Scene(root);
                    stage.setScene(first);
                    stage.show();
                }
            }else{
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("USERNAME CANNOT HAVE CHARACTERS");
                alert.show();
            }
        }

    }

    public static boolean onlyDigits(String str, int n) {
        for (int i = 0; i < n; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
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

    private int getID(int ID_ADMIN, String pass) throws SQLException, ClassNotFoundException {

        connectDB();

        String SQLtxt = "select admin_id from Administrator where admin_id = " + ID_ADMIN + " and pass = '" + pass + "';";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);

        int aid = -1;

        while ( rs.next() ) {
            aid = rs.getInt(1);
        }

        System.out.println(aid);

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed");
        System.out.println("Connection established-196 ");

        return aid;
    }

}

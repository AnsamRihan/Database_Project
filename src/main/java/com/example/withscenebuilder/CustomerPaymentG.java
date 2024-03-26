package com.example.withscenebuilder;

import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerPaymentG implements Initializable {
    @FXML
    private ImageView back;
    @FXML
    private ImageView background;

    @FXML
    private Button BackButton;

    @FXML
    private JFXTextArea textArea;

    @FXML
    private Label labelTotal;

    @FXML
    private Button purchase;

    private Scene CustomerVip;
    private Stage stage;
    private Parent root;


    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double total = 0;

        String formattedString = String.format("%.02f", HelloApplication.reserveGameTime);
        String reserveTotal  = String.format("%.02f", HelloApplication.reserveGameTotal);

        if(HelloApplication.flagForFood2){
            textArea.setText("1) You ordered " + HelloApplication.reserseGameNum + " Games for " + formattedString +  "h with total " + reserveTotal
                    + ".\n2) You ordered Food with total " + HelloApplication.gameFoodTotal + ".");
            total = HelloApplication.gameFoodTotal + HelloApplication.reserveGameTotal;
        }else{
            textArea.setText(" - You ordered " + HelloApplication.reserseGameNum + " Games for " + formattedString +  "h with total " + reserveTotal + ".");
            total = HelloApplication.reserveGameTotal;
        }

        String totalString = String.format("%.02f", total);

        labelTotal.setText(totalString);

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\BACKGROUND4.jpg");
        background.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\back21.png");
        back.setImage(image1);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerGames.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        CustomerVip = new Scene(root);
        stage.setScene(CustomerVip);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        connectDB();
        double total = HelloApplication.gameFoodTotal + HelloApplication.reserveGameTotal;
        ExecuteStatement("insert into Payment (C_id, Order_id, PDate, FoodTotal , VipTotal, GameTotal ,Total)\n" +
                "values(" + HelloApplication.custID + ", " + HelloApplication.orderid +", now(), " + HelloApplication.gameFoodTotal
                + ", 0, " + HelloApplication.reserveGameTotal + ", " + total +");");
        con.close();

        HelloApplication.orderid = -1;
        HelloApplication.flagForFood2 = false;
        HelloApplication.reserveGameTotal = 0;
        HelloApplication.gameFoodTotal = 0;
        HelloApplication.reserseGameNum = 0;
        HelloApplication.reserveGameTime = 0;

        root = FXMLLoader.load(getClass().getResource("CustomerGames.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        CustomerVip = new Scene(root);
        stage.setScene(CustomerVip);
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

    public void ExecuteStatement(String SQL) throws SQLException, ClassNotFoundException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        } catch (SQLException s) {
            System.out.println("SQL statement is not executed!");
        }
    }
}

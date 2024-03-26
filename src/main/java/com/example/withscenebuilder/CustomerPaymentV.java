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

public class CustomerPaymentV implements Initializable {
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

        if(HelloApplication.flagForFood){
            textArea.setText("1) You ordered " + HelloApplication.roomReserveHours + " hours of room vip with total " + HelloApplication.roomReserveTotal
                    + ".\n2) You ordered Food with total " + HelloApplication.roomFoodTotal+ ".");
            total = HelloApplication.roomFoodTotal + HelloApplication.roomReserveTotal;
        }else{
            textArea.setText(" - You ordered " + HelloApplication.roomReserveHours + " hours of room vip with total.");
            total = HelloApplication.roomReserveTotal;
        }

        labelTotal.setText(String.valueOf(total));

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\BACKGROUND4.jpg");
        background.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\back21.png");
        back.setImage(image1);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerVip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        CustomerVip = new Scene(root);
        stage.setScene(CustomerVip);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        connectDB();
        double total = HelloApplication.roomFoodTotal + HelloApplication.roomReserveTotal;
        ExecuteStatement("insert into Payment (C_id, Order_id, PDate, FoodTotal , VipTotal, GameTotal ,Total)\n" +
                "values(" + HelloApplication.custID + ", " + HelloApplication.orderid +", now(), " + HelloApplication.roomFoodTotal
                + ", " + HelloApplication.roomReserveTotal + ", 0, " + total +");");
        con.close();

        HelloApplication.orderid = -1;
        HelloApplication.flagForFood = false;
        HelloApplication.roomReserveTotal = 0;
        HelloApplication.roomFoodTotal = 0;
        HelloApplication.roomReserveHours = 0;

        root = FXMLLoader.load(getClass().getResource("CustomerVip.fxml"));
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

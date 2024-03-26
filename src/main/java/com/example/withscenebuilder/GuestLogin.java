package com.example.withscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.sql.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EventObject;
import java.util.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuestLogin implements Initializable {

    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField emailTF;





    @FXML
    private TextField phoneTF;

    @FXML
    private DatePicker birthdateTF;

    @FXML
    private RadioButton maleTF;

    @FXML
    private RadioButton femaleTF;
    @FXML
    private ImageView background;
    @FXML
    private ImageView back;
    @FXML
    private ImageView logo;


    @FXML
    private ImageView name;

    @FXML
    private ImageView email;

    @FXML
    private ImageView phone;

    @FXML
    private ImageView birthdate;

    @FXML
    private ImageView gender;

    @FXML
    private Button backButton;

    @FXML
    private Button ContinueButton;

    private Scene sceneCustomerLogin;
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
        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\BACKGROUND3.jpg");
        background.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\back21.png");
        back.setImage(image1);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\CastleLogo.png");
        logo.setImage(image2);

        Image image6 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\name2.png");
        name.setImage(image6);

        Image image7 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\email.png");
        email.setImage(image7);

        Image image8 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\phone.png");
        phone.setImage(image8);

        Image image9 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\birthdate.png");
        birthdate.setImage(image9);

        Image image10 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\gender.png");
        gender.setImage(image10);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustomerLogin = new Scene(root);
        stage.setScene(sceneCustomerLogin);
        stage.show();
    }

    public void add(ActionEvent event) throws Exception {
        String phoneNum;
        int phoneLength;

        if(fullNameTF.getText().isEmpty() || emailTF.getText().isEmpty()
                || phoneTF.getText().isEmpty() || birthdateTF.getValue() == null
                || (!maleTF.isSelected() && !femaleTF.isSelected())){
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("You can't leave empty values!");
            alert.show();
        }else{
            phoneNum = phoneTF.getText();
            phoneLength = phoneNum.length();
            if(usernameExists(fullNameTF.getText())){
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("PLEASE CHOOSE ANOTHER USERNAME!");
                alert.show();
            }else{
                if(onlyDigits(phoneNum, phoneLength)){
                    if (femaleTF.isSelected()){

                        Customer customer = new Customer(fullNameTF.getText(), "Female", emailTF.getText(), Integer.parseInt(phoneTF.getText()), birthdateTF.getValue());

                        try {
                            insertData(customer);
                        } catch (ClassNotFoundException | SQLException e2) {
                            //e2.printStackTrace();
                        }
                        root = FXMLLoader.load(getClass().getResource("MainGuest.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        sceneCustomerLogin = new Scene(root);
                        stage.setScene(sceneCustomerLogin);
                        stage.show();


                    }
                    else if(maleTF.isSelected()){

                        Customer customer = new Customer(fullNameTF.getText(), "Male", emailTF.getText(), Integer.parseInt(phoneTF.getText()), birthdateTF.getValue());

                        try {
                            insertData(customer);
                        } catch (ClassNotFoundException | SQLException e2) {
                            //e2.printStackTrace();
                        }

                        root = FXMLLoader.load(getClass().getResource("MainGuest.fxml"));
                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        sceneCustomerLogin = new Scene(root);
                        stage.setScene(sceneCustomerLogin);
                        stage.show();

                    }else {
                        Alert.AlertType type = Alert.AlertType.WARNING;
                        Alert alert = new Alert(type , "");
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.getDialogPane().setContentText("Gender Unknown");
                        alert.getDialogPane().setHeaderText("Please choose your gender before adding!");
                        alert.show();
                    }
                }else{
                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type , "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setHeaderText("Phone cannot contain characters!");
                    alert.show();
                }
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

    void insertData(Customer rc) throws ClassNotFoundException, SQLException {

        connectDB();

        String SQL2 = "SET @@SESSION.information_schema_stats_expiry = 0;"; // future autoincrement
        Statement stmt2 = con.createStatement();
        stmt2.executeUpdate(SQL2);

        String SQL = "SELECT `AUTO_INCREMENT` "
                + "FROM  INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = 'castle' "
                + "AND   TABLE_NAME   = 'Customer';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        int id = rs.getInt(1);
        HelloApplication.custID = id;
        ExecuteStatement("Insert into Customer ( Cname,  Gender , Email,   Phone_number,Birthdate) values('"
                + rc.getCname() + "','"
                + rc.getGender() + "', '"
                + rc.getEmail() + "', "
                + rc.getPhone_number() +", '"
                + rc.getBirthdate() + "');");


        con.close();

        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type , "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setContentText("Hello Guest, You are added!");
        alert.getDialogPane().setHeaderText("Insertion");
        alert.show();
    }

    public void ExecuteStatement(String SQL) throws SQLException, ClassNotFoundException {

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(SQL);
            stmt.close();

        } catch (SQLException s) {
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }

    private void connectDB() throws SQLException {

//        dbURL = "jdbc:mysql://localhost:3306/mysql/"+ dbName + "?verifyServerCertificate=false";
        dbURL = "jdbc:mysql://localhost:3306/castle";

        Properties p = new Properties();
        p.setProperty("user", dbUsername);
        p.setProperty("password", dbPassword);
        con = DriverManager.getConnection (dbURL, p);
    }

    public boolean usernameExists(String username) throws SQLException {
        boolean flag = false;
        connectDB();

        String SQL = "select Username from Acc where Username = '" + username +"';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);

        while ( rs.next() ) {
            flag = true;
        }

        con.close();
        return flag;
    }
}


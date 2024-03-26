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
import java.util.Properties;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signup implements Initializable {
    @FXML
    private TextField fullNameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private PasswordField password2TF;

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
    private ImageView lock;

    @FXML
    private ImageView lock2;

    @FXML
    private ImageView user;

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
    private Button signupButton;

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

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\locked-computer.png");
        lock.setImage(image3);

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\user.png");
        user.setImage(image4);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\locked-computer.png");
        lock2.setImage(image5);

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

    public void signup(ActionEvent event) throws Exception {
        String phoneNum;
        int phoneLength;
        if(fullNameTF.getText().isEmpty() || emailTF.getText().isEmpty() || usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty()
             || password2TF.getText().isEmpty() || phoneTF.getText().isEmpty() || birthdateTF.getValue() == null
             || (!maleTF.isSelected() && !femaleTF.isSelected())){
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("You can't leave empty values!");
                alert.show();
        }else{
            phoneNum = phoneTF.getText();
            phoneLength = phoneNum.length();
            if(usernameExists(usernameTF.getText())){
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("PLEASE CHOOSE ANOTHER USERNAME!");
                alert.show();
            }else{
                if(onlyDigits(phoneNum, phoneLength)){
                    if (femaleTF.isSelected()){
                        if (passwordTF.getText().equals(password2TF.getText())){
                            Customer customer = new Customer(fullNameTF.getText(), "Female", emailTF.getText(), Integer.parseInt(phoneTF.getText()), birthdateTF.getValue());
                            Acc account = new Acc(usernameTF.getText(), passwordTF.getText());
                            try {
                                insertData(customer, account);
                            } catch (ClassNotFoundException | SQLException e2) {
                                //e2.printStackTrace();
                            }
                            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            sceneCustomerLogin = new Scene(root);
                            stage.setScene(sceneCustomerLogin);
                            stage.show();
                        }
                        else {
                            Alert alert77 = new Alert(Alert.AlertType.WARNING);
                            alert77.setTitle("Warning");
                            alert77.setHeaderText("PASSWORD ERROR");
                            alert77.setContentText("CHECK YOUR PASSWORDS . "
                                    + "\nPlease try again.");
                            alert77.show();
                        }
                    }
                    else if(maleTF.isSelected()){
                        if (passwordTF.getText().equals(password2TF.getText())) {
                            Customer customer = new Customer(femaleTF.getText(), "Male", emailTF.getText(), Integer.parseInt(phoneTF.getText()), birthdateTF.getValue());
                            Acc account = new Acc(usernameTF.getText(), passwordTF.getText());
                            try {
                                insertData(customer, account);
                            } catch (ClassNotFoundException | SQLException e2) {
                                //e2.printStackTrace();
                            }
                            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            sceneCustomerLogin = new Scene(root);
                            stage.setScene(sceneCustomerLogin);
                            stage.show();
                        }
                        else {
                            Alert alert77 = new Alert(Alert.AlertType.WARNING);
                            alert77.setTitle("Warning");
                            alert77.setHeaderText("PASSWORD ERROR");
                            alert77.setContentText("CHECK YOUR PASSWORDS . "
                                    + "\nPlease try again.");
                            alert77.show();
                        }
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

    void insertData(Customer rc, Acc ac) throws ClassNotFoundException, SQLException {

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
        ac.setC_id(id);

        ExecuteStatement("Insert into Customer (Cname,  Gender , Email,   Phone_number,Birthdate) values('"
                + rc.getCname() + "','"
                + rc.getGender() + "', '"
                + rc.getEmail() + "', "
                + rc.getPhone_number() +", '"
                + rc.getBirthdate() + "');");

        ExecuteStatement("insert into Acc (Username, Pass, C_id, Start_date)\r\n"
                + "values ('" +ac.getUsername() + "', '"
                + ac.getPass() + "' ,"
                + id + ", curdate());");

        con.close();

        System.out.println("Customer, Account Added!");


        Alert.AlertType type = Alert.AlertType.WARNING;
        Alert alert = new Alert(type , "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setContentText("Customer, Account Added!");
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


    public boolean usernameExists(String username) throws SQLException, ClassNotFoundException {
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


package com.example.withscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainAdmin implements Initializable {

    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;

    private String fullNameInfo;
    private String numberInfo;
    private String genderInfo;

    @FXML
    private ImageView User;
    @FXML
    private ImageView add;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView orders;

    @FXML
    private ImageView orders1;

    @FXML
    private ImageView logOut;

    @FXML
    private Button ordersButton;
    @FXML
    private Button paymentsButton;

    @FXML
    private Button logoutButton;
    @FXML
    private Button UpdateButton;
    @FXML
    private Button UpdatePaswordButton;
    @FXML
    private TextField username;
    @FXML
    private TextField PhoneNumber;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    @FXML
    private TextField confirmPassword;
    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;

    private Scene sceneOrders;
    private Scene sceneVip;
    private Scene sceneCustLogin;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        try {
            readData();
        } catch (Exception e) {
        }

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\userWhite.jpg");
        User.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\profile.png");
        profile.setImage(image1);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\List.png");
        orders.setImage(image2);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\logout.png");
        logOut.setImage(image5);

        Image image6 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\List.png");
        orders1.setImage(image6);

        Image image7 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\add.png");
        add.setImage(image7);
    }

    private void readData() throws SQLException, ClassNotFoundException {
        connectDB();

        String SQL = "select AName, Phone_number, Gender from Administrator where admin_id =" + HelloApplication.AdminId +";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        fullNameInfo = rs.getString(1);
        numberInfo = rs.getString(2);
        genderInfo = rs.getString(3);

        username.setText(fullNameInfo);
        PhoneNumber.setText(numberInfo);
        if (genderInfo.equals("Female")) {
            rb2.setSelected(true);
            rb1.setSelected(false);
        }else{
            rb1.setSelected(true);
            rb2.setSelected(false);
        }
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminPayments.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOrders = new Scene(root);
        stage.setScene(sceneOrders);
        stage.show();

    }

    public void SwitchToSceneADD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminAdd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOrders = new Scene(root);
        stage.setScene(sceneOrders);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminOrders.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOrders = new Scene(root);
        stage.setScene(sceneOrders);
        stage.show();

    }

    public void SwitchToScene3(ActionEvent event) throws IOException {
        HelloApplication.AdminId=-1;
        root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustLogin = new Scene(root);
        stage.setScene(sceneCustLogin);
        stage.show();
    }
    public void updateDataCAdmin() throws ClassNotFoundException, SQLException {

        connectDB();

        if(username.getText().isEmpty() || PhoneNumber.getText().isEmpty()){
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setContentText("DIDN'T UPDATE NAME AND PHONE NUMBER CANNOT BE EMPTY!!");
            alert.getDialogPane().setHeaderText("DIDNN'T UPDATE!");
            alert.show();

        }else{
            if(onlyDigits(PhoneNumber.getText(), PhoneNumber.getText().length())){
                if (rb1.isSelected()){
                    String SQL = "UPDATE Administrator SET AName = '" + username.getText() + "', Phone_number = " + PhoneNumber.getText() + ", Gender  = 'Male'"
                            + " where admin_id = " + HelloApplication.AdminId + ";";
                    ExecuteStatement(SQL);
                    con.close();
                }
                else if (rb2.isSelected()){
                    String SQL = "UPDATE Administrator SET AName = '" + username.getText() + "', Phone_number = " + PhoneNumber.getText() + ", Gender  = 'Female'"
                            + " where admin_id = " + HelloApplication.AdminId + ";";
                    ExecuteStatement(SQL);
                    con.close();
                }

                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("Admin Info Updated!");
                alert.getDialogPane().setHeaderText("UPDATE!");
                alert.show();
            }else{
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("Phone number cannot have characters");
                alert.getDialogPane().setHeaderText("DIDN'T UPDATE!");
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
    public void updateDataAcc() throws ClassNotFoundException, SQLException {

        if(oldPassword.getText().equals(getpass())){
            if (newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("New password cannot be empty!");
                alert.getDialogPane().setHeaderText("UPDATE!");
                alert.show();
            }else{
                if (newPassword.getText().equals(confirmPassword.getText())) {
                    connectDB();
                    String SQL = "UPDATE Administrator SET Pass = '" + newPassword.getText() + "' WHERE admin_id = " + HelloApplication.AdminId + ";";
                    ExecuteStatement(SQL);
                    con.close();

                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type , "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setContentText("Password Updated!");
                    alert.getDialogPane().setHeaderText("UPDATE!");
                    alert.show();
                }else{
                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type , "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setContentText("The confirm password doesn't equal the new password!");
                    alert.getDialogPane().setHeaderText("UPDATE!");
                    alert.show();
                }
            }
        }else{
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setContentText("old password is wrong!");
            alert.getDialogPane().setHeaderText("DIDN'T UPDATE!");
            alert.show();
        }
    }

    private String getpass() throws SQLException, ClassNotFoundException {

        connectDB();

        String SQLtxt = "select pass from Administrator where admin_id = " + HelloApplication.AdminId +";";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);

        String pass = null;

        if (rs.next()) {
            pass = rs.getString(1);
        }

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed");

        return pass;
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
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }
}

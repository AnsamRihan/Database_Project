package com.example.withscenebuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class AdminAdd implements Initializable {

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
    private TextField newPassword;
    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;
    @FXML
    private DatePicker birthdateTF;

    private Scene sceneOrders;
    private Scene sceneVip;
    private Scene sceneCustLogin;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

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

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminPayments.fxml"));
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

    public void SwitchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainAdmin.fxml"));
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
    public void addAdmin() throws ClassNotFoundException, SQLException {

        connectDB();

        String SQL2 = "SET @@SESSION.information_schema_stats_expiry = 0;"; // future autoincrement
        Statement stmt2 = con.createStatement();
        stmt2.executeUpdate(SQL2);

        String SQL = "SELECT `AUTO_INCREMENT` "
                + "FROM  INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = 'castle' "
                + "AND   TABLE_NAME   = 'Administrator';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        int id = rs.getInt(1);

        con.close();

        if(username.getText().isEmpty() || newPassword.getText().isEmpty() || PhoneNumber.getText().isEmpty()
                || (!rb1.isSelected() && !rb2.isSelected()) || birthdateTF.getValue() == null){
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("You can't leave empty values!");
            alert.show();
        }else{
            if(onlyDigits(PhoneNumber.getText(), PhoneNumber.getText().length())){
                if(rb1.isSelected()){
                    connectDB();
                    ExecuteStatement("insert into Administrator (Gender, Phone_number, Aname, Pass, Birthdate)\n" +
                            "values ('Male', " + PhoneNumber.getText() + ", '" + username.getText() + "','" + newPassword.getText() + "','" + birthdateTF.getValue().toString() +"');");
                    con.close();
                }else{
                    connectDB();
                    ExecuteStatement("insert into Administrator (Gender, Phone_number, Aname, Pass, Birthdate)\n" +
                            "values ('Female', " + PhoneNumber.getText() + ", '" + username.getText() + "','" + newPassword.getText() + "','" + birthdateTF.getValue().toString() +"');");
                    con.close();
                }

                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("NEW ADMIN ADDED WITH ID = " + id + "!!");
                alert.getDialogPane().setHeaderText("Insertion");
                alert.show();
            }else{
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("Phone cannot contain characters!");
                alert.show();
            }
        }

        con.close();
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

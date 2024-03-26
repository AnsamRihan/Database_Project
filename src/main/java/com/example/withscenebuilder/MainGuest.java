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
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
public class MainGuest implements Initializable {
    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;

    private String fullNameInfo;
    private String emailInfo;
    private String numberInfo;
    private String genderInfo;
    @FXML

    private ImageView User;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView game;

    @FXML
    private ImageView logOut;

    @FXML
    private Button logoutButton;

    @FXML
    private Button gameButton;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    private Scene sceneGame;
    private Scene sceneCustLogin;
    private Stage stage;
    private Parent root;
    @FXML
    RadioButton rb1 = new RadioButton("FEMALE");
    @FXML
    RadioButton rb2 = new RadioButton("MALE");
    @FXML
    private Button updateButton;
    private ArrayList<Customer> Customers = new ArrayList<Customer>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            readData();
        } catch (Exception e) {
        }

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\userWhite.jpg");
        User.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\profile.png");
        profile.setImage(image1);

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\game.png");
        game.setImage(image3);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\logout.png");
        logOut.setImage(image5);
    }

    private void readData() throws SQLException, ClassNotFoundException {
        connectDB();

        String SQL = "select Cname, Email, Phone_number, Gender from Customer where C_id =" + HelloApplication.custID +";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        fullNameInfo = rs.getString(1);
        emailInfo = rs.getString(2);
        numberInfo = rs.getString(3);
        genderInfo = rs.getString(4);

        name.setText(fullNameInfo);
        email.setText(emailInfo);
        phone.setText(numberInfo);
        if (genderInfo.equals("Female")) {
            rb2.setSelected(true);
            rb1.setSelected(false);
        }else{
            rb1.setSelected(true);
            rb2.setSelected(false);
        }
    }

    public void SwitchToScene3(ActionEvent event) throws IOException {
        HelloApplication.custID = -1;
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustLogin = new Scene(root);
        stage.setScene(sceneCustLogin);
        stage.show();
    }

    public void SwitchToScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GuestGames.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneGame = new Scene(root);
        stage.setScene(sceneGame);
        stage.show();
    }
    public void updateDataCustomer() throws ClassNotFoundException, SQLException {

        connectDB();
        if (name.getText().isEmpty() || phone.getText().isEmpty()) {
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type, "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setContentText("DIDN'T UPDATE NAME AND PHONE NUMBER CANNOT BE EMPTY!!");
            alert.getDialogPane().setHeaderText("DIDN'T UPDATE!");
            alert.show();

        } else {
            if (onlyDigits(phone.getText(), phone.getText().length())) {
                if (email.getText().isEmpty()) {
                    if (rb1.isSelected()) {
                        String SQL = "UPDATE Customer SET Cname = '" + name.getText() + "', Phone_number = " + phone.getText() + ", Gender  = 'Male', Email = null"
                                + " WHERE C_id = " + HelloApplication.custID + ";";
                        ExecuteStatement(SQL);
                        con.close();
                    } else if (rb2.isSelected()) {
                        String SQL = "UPDATE Customer SET Cname = '" + name.getText() + "', Phone_number = " + phone.getText() + ", Gender  = 'Female', Email = null" +
                                " WHERE C_id = " + HelloApplication.custID + ";";
                        ExecuteStatement(SQL);
                        con.close();
                    }
                } else {
                    if (rb1.isSelected()) {
                        String SQL = "UPDATE Customer SET Cname = '" + name.getText() + "', Phone_number = " + phone.getText() + ", Gender  = 'Male', Email = '" + email.getText()
                                + "' WHERE C_id = " + HelloApplication.custID + ";";
                        ExecuteStatement(SQL);
                        con.close();
                    } else if (rb2.isSelected()) {
                        String SQL = "UPDATE Customer SET Cname = '" + name.getText() + "', Phone_number = " + phone.getText() + ", Gender  = 'Female', Email = '" + email.getText()
                                + "' WHERE C_id = " + HelloApplication.custID + ";";
                        ExecuteStatement(SQL);
                        con.close();
                    }
                }

                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type, "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("Customer Updated!");
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

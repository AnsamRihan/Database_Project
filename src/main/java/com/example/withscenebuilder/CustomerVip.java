package com.example.withscenebuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.*;

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
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerVip implements Initializable {

    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;

    public ArrayList<String> Time = new ArrayList<String>();
    public ArrayList<String> Checked = new ArrayList<String>();

    int numOfChecked = 0;

    int roomID = -1;

    @FXML
    private ImageView User;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView orders;
    @FXML
    private ImageView game;
    @FXML
    private ImageView vip;
    @FXML
    private ImageView logOut;
    @FXML
    private ImageView clock;
    @FXML
    private Button profileButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button gameButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button checkDate;

    @FXML
    private Button reserve;

    @FXML
    private JFXCheckBox AM10;
    @FXML
    private JFXCheckBox AM11;
    @FXML
    private JFXCheckBox PM12;
    @FXML
    private JFXCheckBox PM1;
    @FXML
    private JFXCheckBox PM2;
    @FXML
    private JFXCheckBox PM3;
    @FXML
    private JFXCheckBox PM4;
    @FXML
    private JFXCheckBox PM5;
    @FXML
    private JFXCheckBox PM6;
    @FXML
    private JFXCheckBox PM7;
    @FXML
    private JFXCheckBox PM8;
    @FXML
    private JFXCheckBox PM9;
    @FXML
    private JFXCheckBox PM10;
    @FXML
    private JFXCheckBox PM11;

    @FXML
    private Pane pane10am;
    @FXML
    private Pane pane11am;
    @FXML
    private Pane pane12pm;
    @FXML
    private Pane pane1pm;
    @FXML
    private Pane pane2pm;
    @FXML
    private Pane pane3pm;
    @FXML
    private Pane pane4pm;
    @FXML
    private Pane pane5pm;
    @FXML
    private Pane pane6pm;
    @FXML
    private Pane pane7pm;
    @FXML
    private Pane pane8pm;
    @FXML
    private Pane pane9pm;
    @FXML
    private Pane pane10pm;
    @FXML
    private Pane pane11pm;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField roomIdTF;

    private Scene sceneGame;
    private Scene sceneFood;
    private Scene sceneProfile;
    private Scene sceneOrders;
    private Scene sceneCustLogin;
    private Scene scene;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\userWhite.jpg");
        User.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\profile.png");
        profile.setImage(image1);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\List.png");
        orders.setImage(image2);

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\game.png");
        game.setImage(image3);

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\vip.png");
        vip.setImage(image4);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\logout.png");
        logOut.setImage(image5);

        Image image6 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\clock.png");
        clock.setImage(image6);

        pane10am.setStyle("-fx-background-color:#DEDEDE");
        pane11am.setStyle("-fx-background-color:#DEDEDE");
        pane12pm.setStyle("-fx-background-color:#DEDEDE");
        pane1pm.setStyle("-fx-background-color:#DEDEDE");
        pane2pm.setStyle("-fx-background-color:#DEDEDE");
        pane3pm.setStyle("-fx-background-color:#DEDEDE");
        pane4pm.setStyle("-fx-background-color:#DEDEDE");
        pane5pm.setStyle("-fx-background-color:#DEDEDE");
        pane6pm.setStyle("-fx-background-color:#DEDEDE");
        pane7pm.setStyle("-fx-background-color:#DEDEDE");
        pane8pm.setStyle("-fx-background-color:#DEDEDE");
        pane9pm.setStyle("-fx-background-color:#DEDEDE");
        pane10pm.setStyle("-fx-background-color:#DEDEDE");
        pane11pm.setStyle("-fx-background-color:#DEDEDE");

        AM10.setDisable(false);
        AM11.setDisable(false);
        PM12.setDisable(false);
        PM1.setDisable(false);
        PM3.setDisable(false);
        PM4.setDisable(false);
        PM5.setDisable(false);
        PM6.setDisable(false);
        PM7.setDisable(false);
        PM8.setDisable(false);
        PM9.setDisable(false);
        PM10.setDisable(false);
        PM11.setDisable(false);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneProfile = new Scene(root);
        stage.setScene(sceneProfile);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerOrders.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOrders = new Scene(root);
        stage.setScene(sceneOrders);
        stage.show();
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
        root = FXMLLoader.load(getClass().getResource("CustomerGames.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneGame = new Scene(root);
        stage.setScene(sceneGame);
        stage.show();
    }

    public void SwitchToScene5(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerFood.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneFood = new Scene(root);
        stage.setScene(sceneFood);
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
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }


    private void getTime(LocalDate date, int roomID) throws SQLException, ClassNotFoundException {

        Time.clear();

        connectDB();

        System.out.println("Connection established");

        String SQLtxt = "select t.Time_range from BookRoom r, Time_for_reservation t where r.Time_id = t.Time_id and r.BDate = '" + date.toString() +"' and r.Room_id = " + roomID +";";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);

        while ( rs.next() ) {
            Time.add(rs.getString(1));
        }

        rs.close();
        stmt.close();

        con.close();
        System.out.println("Connection closed");
    }

    public void clickOnCheck() throws SQLException, ClassNotFoundException {
        pane10am.setStyle("-fx-background-color:#DEDEDE");
        pane11am.setStyle("-fx-background-color:#DEDEDE");
        pane12pm.setStyle("-fx-background-color:#DEDEDE");
        pane1pm.setStyle("-fx-background-color:#DEDEDE");
        pane2pm.setStyle("-fx-background-color:#DEDEDE");
        pane3pm.setStyle("-fx-background-color:#DEDEDE");
        pane4pm.setStyle("-fx-background-color:#DEDEDE");
        pane5pm.setStyle("-fx-background-color:#DEDEDE");
        pane6pm.setStyle("-fx-background-color:#DEDEDE");
        pane7pm.setStyle("-fx-background-color:#DEDEDE");
        pane8pm.setStyle("-fx-background-color:#DEDEDE");
        pane9pm.setStyle("-fx-background-color:#DEDEDE");
        pane10pm.setStyle("-fx-background-color:#DEDEDE");
        pane11pm.setStyle("-fx-background-color:#DEDEDE");
        AM10.setDisable(false);
        AM11.setDisable(false);
        PM12.setDisable(false);
        PM1.setDisable(false);
        PM2.setDisable(false);
        PM3.setDisable(false);
        PM4.setDisable(false);
        PM5.setDisable(false);
        PM6.setDisable(false);
        PM7.setDisable(false);
        PM8.setDisable(false);
        PM9.setDisable(false);
        PM10.setDisable(false);
        PM11.setDisable(false);


        LocalDate date = datePicker.getValue();
        roomID = Integer.parseInt(roomIdTF.getText());
        getTime(date, roomID);

        //9b6ed2

        for(int i = 0; i < Time.size(); i++){
           if(Time.get(i).equals("10AM")){
               pane10am.setStyle("-fx-background-color:#000000");
               AM10.setDisable(true);
           } if (Time.get(i).equals("11AM")) {
               pane11am.setStyle("-fx-background-color:#000000");
               AM11.setDisable(true);
           } if (Time.get(i).equals("12PM")) {
               pane12pm.setStyle("-fx-background-color:#000000");
                PM12.setDisable(true);
           } if (Time.get(i).equals("1PM")) {
               pane1pm.setStyle("-fx-background-color:#000000");
               PM1.setDisable(true);
           } if (Time.get(i).equals("2PM")) {
               pane2pm.setStyle("-fx-background-color:#000000");
               PM2.setDisable(true);
           } if (Time.get(i).equals("3PM")) {
               pane3pm.setStyle("-fx-background-color:#000000");
               PM3.setDisable(true);
           } if (Time.get(i).equals("4PM")) {
               pane4pm.setStyle("-fx-background-color:#000000");
               PM4.setDisable(true);
           } if (Time.get(i).equals("5PM")) {
               pane5pm.setStyle("-fx-background-color:#000000");
               PM5.setDisable(true);
           } if (Time.get(i).equals("6PM")) {
               pane6pm.setStyle("-fx-background-color:#000000");
               PM6.setDisable(true);
           } if (Time.get(i).equals("7PM")) {
               pane7pm.setStyle("-fx-background-color:#000000");
               PM7.setDisable(true);
           } if (Time.get(i).equals("8PM")) {
               pane8pm.setStyle("-fx-background-color:#000000");
               PM8.setDisable(true);
           } if (Time.get(i).equals("9PM")) {
               pane9pm.setStyle("-fx-background-color:#000000");
               PM9.setDisable(true);
           } if (Time.get(i).equals("10PM")) {
               pane10pm.setStyle("-fx-background-color:#000000");
               PM10.setDisable(true);
           }if (Time.get(i).equals("11PM")) {
               pane11pm.setStyle("-fx-background-color:#000000");
               PM11.setDisable(true);
           }
        }
    }

    public void GetPrice() throws SQLException, ClassNotFoundException {
        if(AM10.isSelected()){
            numOfChecked++;
            Checked.add("10AM");
        } if (AM11.isSelected()) {
            numOfChecked++;
            Checked.add("11AM");
        } if (PM12.isSelected()) {
            numOfChecked++;
            Checked.add("12PM");
        } if (PM1.isSelected()) {
            numOfChecked++;
            Checked.add("1PM");
        } if (PM2.isSelected()) {
            numOfChecked++;
            Checked.add("2PM");
        } if (PM3.isSelected()) {
            numOfChecked++;
            Checked.add("3PM");
        } if (PM4.isSelected()) {
            numOfChecked++;
            Checked.add("4PM");
        }if (PM5.isSelected()) {
            numOfChecked++;
            Checked.add("5PM");
        } if (PM6.isSelected()) {
            numOfChecked++;
            Checked.add("6PM");
        } if (PM7.isSelected()) {
            numOfChecked++;
            Checked.add("7PM");
        } if (PM8.isSelected()) {
            numOfChecked++;
            Checked.add("8PM");
        } if (PM9.isSelected()) {
            numOfChecked++;
            Checked.add("9PM");
        } if (PM10.isSelected()) {
            numOfChecked++;
            Checked.add("10PM");
        } if (PM11.isSelected()) {
            numOfChecked++;
            Checked.add("11PM");
        }

        HelloApplication.roomReserveHours = numOfChecked;
        double price;
        double total;

        connectDB();


        String SQL = "select Price_per_hour from Room where Room_id = " + roomID +";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        price = rs.getInt(1);

        total = price * numOfChecked;
        HelloApplication.roomReserveTotal = total;
    }

    public void getOrderID() throws SQLException, ClassNotFoundException {
        int orderid;

        connectDB();

        String SQL2 = "SET @@SESSION.information_schema_stats_expiry = 0;"; // future autoincrement
        Statement stmt2 = con.createStatement();
        stmt2.executeUpdate(SQL2);

        String SQL = "SELECT `AUTO_INCREMENT` "
                + "FROM  INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = 'castle' "
                + "AND   TABLE_NAME   = 'OrderTable';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        orderid = rs.getInt(1);
        HelloApplication.orderid = orderid;

        con.close();
    }

    public void reserveRoom() throws SQLException, ClassNotFoundException {
        int timeID;

        connectDB();

        for(int i=0; i < Checked.size(); i++){
            String SQL3 = "select Time_id from Time_for_reservation where Time_range = '"+ Checked.get(i) +"';";
            Statement stmt3 = con.createStatement();
            ResultSet rs3 = stmt3.executeQuery(SQL3);
            rs3.next();
            timeID = rs3.getInt(1);

            String date = datePicker.getValue().toString();

            ExecuteStatement("insert into BookRoom (C_id, Room_id, Order_id, Time_id, BDate)\n" +
                    "values (" + HelloApplication.custID + ", " + roomID + "," + HelloApplication.orderid + ", " + timeID + ", '" + date +"');");
        }
        Checked.clear();
    }

    public void SwitchToPay(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if(!HelloApplication.flagForFood){
            getOrderID();
            connectDB();
            ExecuteStatement("insert into OrderTable (Order_id, Order_time) values (" + HelloApplication.orderid + ", now());");
            con.close();
        }
        GetPrice();
        reserveRoom();
        root = FXMLLoader.load(getClass().getResource("CustomerPaymentv.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

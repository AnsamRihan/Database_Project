package com.example.withscenebuilder;

import com.jfoenix.controls.JFXCheckBox;
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
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerGames implements Initializable {

    public ArrayList<Integer> games = new ArrayList<>();

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
    private ImageView pc1;

    @FXML
    private ImageView pc2;

    @FXML
    private ImageView pc3;

    @FXML
    private ImageView piliardo1;

    @FXML
    private ImageView piliardo2;

    @FXML
    private ImageView piliardo3;

    @FXML
    private ImageView ps1;

    @FXML
    private ImageView ps2;

    @FXML
    private ImageView ps3;

    @FXML
    private Button profileButton;

    @FXML
    private Button vipButton;

    @FXML
    private Button ordersButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button foodButton;

    @FXML
    private Button startSession;

    @FXML
    private Button endSession;

    @FXML
    private JFXCheckBox pc1CB;
    @FXML
    private JFXCheckBox pc2CB;
    @FXML
    private JFXCheckBox pc3CB;
    @FXML
    private JFXCheckBox ps4CB;
    @FXML
    private JFXCheckBox ps5CB;
    @FXML
    private JFXCheckBox ps6CB;
    @FXML
    private JFXCheckBox piliardo7CB;
    @FXML
    private JFXCheckBox piliardo8CB;
    @FXML
    private JFXCheckBox piliardo9CB;
    @FXML
    private Label pc1Price;
    @FXML
    private Label pc2Price;
    @FXML
    private Label pc3Price;
    @FXML
    private Label ps4Price;
    @FXML
    private Label ps5Price;
    @FXML
    private Label ps6Price;
    @FXML
    private Label p7Price;
    @FXML
    private Label p8Price;
    @FXML
    private Label p9Price;

    double p1;
    double p2;
    double p3;
    double p4;
    double p5;
    double p6;
    double p7;
    double p8;
    double p9;

    private Scene sceneProfile;
    private Scene sceneVip;
    private Scene sceneCustLogin;
    private Scene sceneOrders;
    private Scene sceneFood;
    private Scene scene;
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

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\game.png");
        game.setImage(image3);

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\vip.png");
        vip.setImage(image4);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\logout.png");
        logOut.setImage(image5);

        Image image6 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\PC.jpg");
        pc1.setImage(image6);

        Image image7 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\PC.jpg");
        pc2.setImage(image7);

        Image image8 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\PC.jpg");
        pc3.setImage(image8);

        Image image9 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\playstation.jpg");
        ps1.setImage(image9);

        Image image10 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\playstation.jpg");
        ps2.setImage(image10);

        Image image11 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\playstation.jpg");
        ps3.setImage(image11);

        Image image12 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\piliardo.jpg");
        piliardo1.setImage(image12);

        Image image13 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\piliardo.jpg");
        piliardo2.setImage(image13);

        Image image14 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\piliardo.jpg");
        piliardo3.setImage(image14);

        endSession.setDisable(true);
        endSession.setVisible(false);
    }

    public void SwitchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainCustomer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneProfile = new Scene(root);
        stage.setScene(sceneProfile);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerVip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneVip = new Scene(root);
        stage.setScene(sceneVip);
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
        root = FXMLLoader.load(getClass().getResource("CustomerOrders.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneOrders = new Scene(root);
        stage.setScene(sceneOrders);
        stage.show();
    }

    public void SwitchToScene5(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerFoodG.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneFood = new Scene(root);
        stage.setScene(sceneFood);
        stage.show();
    }

    public void startSessionFunction(ActionEvent event) throws SQLException, ClassNotFoundException {

        if(!HelloApplication.flagForFood2){
            getOrderID();
            connectDB();
            ExecuteStatement("insert into OrderTable (Order_id, Order_time) values (" + HelloApplication.orderid + ", now());");
            con.close();
        }

        insertGames();

        endSession.setDisable(false);
        endSession.setVisible(true);
        startSession.setDisable(true);
        startSession.setVisible(false);
    }

    public void insertGames() throws SQLException, ClassNotFoundException {
        connectDB();
        System.out.println(HelloApplication.custID);

        if(pc1CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 1, " + HelloApplication.orderid + ", now());");
        }if(pc2CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 2, " + HelloApplication.orderid + ", now());");
        }if(pc3CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 3, " + HelloApplication.orderid + ", now());");
        }if(ps4CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 4, " + HelloApplication.orderid + ", now());");
        }if(ps5CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 5, " + HelloApplication.orderid + ", now());");
        }if(ps6CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 6, " + HelloApplication.orderid + ", now());");
        }if(piliardo7CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 7, " + HelloApplication.orderid + ", now());");
        }if(piliardo8CB.isSelected()){
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 8, " + HelloApplication.orderid + ", now());");
        }if(piliardo9CB.isSelected()) {
            HelloApplication.reserseGameNum++;
            ExecuteStatement("insert into ReserveGame (C_id, Game_id, Order_id, Start_time)\n" +
                    "values (" + HelloApplication.custID + ", 9, " + HelloApplication.orderid + ", now());");
        }

        con.close();
    }

    public void endSessionFunction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {

        games.clear();

        connectDB();

        String SQLtxt = "select Game_id from ReserveGame where C_id = " + HelloApplication.custID + " and Order_id = " + HelloApplication.orderid +";";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQLtxt);

        while ( rs.next() ) {
            games.add(rs.getInt(1));
        }

        rs.close();
        stmt.close();

        String SQLtxt2 = "select distinct Start_time from ReserveGame where C_id = " + HelloApplication.custID + " and Order_id = " + HelloApplication.orderid +";";

        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery(SQLtxt2);
        rs2.next();
        String startDate = rs2.getString(1);

        rs2.close();
        stmt2.close();

        ExecuteStatement("Update ReserveGame Set End_time = now() where C_id = " + HelloApplication.custID + " and Order_id = " + HelloApplication.orderid + ";");

        String SQLtxt3 = "select distinct End_time from ReserveGame where C_id = " + HelloApplication.custID + " and Order_id = " + HelloApplication.orderid +";";

        Statement stmt3 = con.createStatement();
        ResultSet rs3 = stmt3.executeQuery(SQLtxt3);
        rs3.next();
        String endDate = rs3.getString(1);

        rs3.close();
        stmt3.close();

         //----------------------------------------------------------------------------------------------------------------

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime1= LocalDateTime.parse(startDate, formatter);
        LocalDateTime dateTime2= LocalDateTime.parse(endDate, formatter);

        long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();

        double toHours = (double)diffInMinutes/60;

        HelloApplication.reserveGameTime = toHours;

        //-----------------------------------------------------------------------------------------------------------------

        double total = 0;

        for(int i=0; i < games.size(); i++){
            if(games.get(i) == 1){
                total += (p1*toHours);
            }if(games.get(i) == 2){
                total += (p2*toHours);
            }if(games.get(i) == 3){
                total += (p3*toHours);
            }if(games.get(i) == 4){
                total += (p4*toHours);
            }if(games.get(i) == 5){
                total += (p5*toHours);
            }if(games.get(i) == 6){
                total += (p6*toHours);
            }if(games.get(i) == 7){
                total += (p7*toHours);
            }if(games.get(i) == 8){
                total += (p8*toHours);
            }if(games.get(i) == 9){
                total += (p9*toHours);
            }
        }

        HelloApplication.reserveGameTotal = total;

        con.close();
        endSession.setDisable(true);
        endSession.setVisible(false);
        startSession.setDisable(false);
        startSession.setVisible(true);

        root = FXMLLoader.load(getClass().getResource("CustomerPaymentG.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
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

    private void readData() throws SQLException, ClassNotFoundException {
        connectDB();

        String SQL = "select Price_per_hour from Game where Game_id = 1;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        p1 = rs.getDouble(1);
        pc1Price.setText(String.valueOf(p1));

        String SQL2 = "select Price_per_hour from Game where Game_id = 2;";
        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery(SQL2);
        rs2.next();
        p2 = rs2.getDouble(1);
        pc2Price.setText(String.valueOf(p2));

        String SQL3 = "select Price_per_hour from Game where Game_id = 3;";
        Statement stmt3 = con.createStatement();
        ResultSet rs3 = stmt3.executeQuery(SQL3);
        rs3.next();
        p3 = rs3.getDouble(1);
        pc3Price.setText(String.valueOf(p3));

        String SQL4 = "select Price_per_hour from Game where Game_id = 4;";
        Statement stmt4 = con.createStatement();
        ResultSet rs4 = stmt4.executeQuery(SQL4);
        rs4.next();
        p4 = rs4.getDouble(1);
        ps4Price.setText(String.valueOf(p4));

        String SQL5 = "select Price_per_hour from Game where Game_id = 5;";
        Statement stmt5 = con.createStatement();
        ResultSet rs5 = stmt5.executeQuery(SQL5);
        rs5.next();
        p5 = rs5.getDouble(1);
        ps5Price.setText(String.valueOf(p5));

        String SQL6 = "select Price_per_hour from Game where Game_id = 6;";
        Statement stmt6 = con.createStatement();
        ResultSet rs6 = stmt6.executeQuery(SQL6);
        rs6.next();
        p6 = rs6.getDouble(1);
        ps6Price.setText(String.valueOf(p6));

        String SQL7 = "select Price_per_hour from Game where Game_id = 7;";
        Statement stmt7 = con.createStatement();
        ResultSet rs7 = stmt7.executeQuery(SQL7);
        rs7.next();
        p7 = rs7.getDouble(1);
        p7Price.setText(String.valueOf(p7));

        String SQL8 = "select Price_per_hour from Game where Game_id = 8;";
        Statement stmt8 = con.createStatement();
        ResultSet rs8 = stmt8.executeQuery(SQL8);
        rs8.next();
        p8 = rs8.getDouble(1);
        p8Price.setText(String.valueOf(p8));

        String SQL9 = "select Price_per_hour from Game where Game_id = 9;";
        Statement stmt9 = con.createStatement();
        ResultSet rs9 = stmt9.executeQuery(SQL9);
        rs9.next();
        p9 = rs7.getDouble(1);
        p9Price.setText(String.valueOf(p9));
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
}

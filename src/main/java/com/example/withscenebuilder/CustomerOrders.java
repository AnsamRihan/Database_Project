package com.example.withscenebuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerOrders implements Initializable {

    //-------------------------------------------------------------------
    //Food table
    private ArrayList<FoodTable> foodInfo = new ArrayList<>();
    private ObservableList<FoodTable> foodInfoObs;
    @FXML
    private TableView<FoodTable> foodTable;
    @FXML
    private TableColumn<FoodTable, Integer> foodOrderIdClmn;
    @FXML
    private TableColumn<FoodTable, String> foodNameClmn;
    @FXML
    private TableColumn<FoodTable, String> foodDateClmn;
    @FXML
    private TableColumn<FoodTable, Integer> foodQuantityClmn;
    @FXML
    private TableColumn<FoodTable, Double> foodPriceClmn;
    //-------------------------------------------------------------------
    //Room table
    private ArrayList<RoomTable> roomInfo = new ArrayList<>();
    private ObservableList<RoomTable> roomInfoObs;
    @FXML
    private TableView<RoomTable> roomTable;
    @FXML
    private TableColumn<RoomTable, Integer> roomOrderIdClmn;
    @FXML
    private TableColumn<RoomTable, String> roomDateClmn;
    @FXML
    private TableColumn<RoomTable, Integer> roomIdClmn;
    @FXML
    private TableColumn<RoomTable, String> roomTimeRangeClmn;
    @FXML
    private TableColumn<RoomTable, Double> roomPriceClmn;

    //-------------------------------------------------------------------
    //Game table
    private ArrayList<GameTable> gameInfo = new ArrayList<>();
    private ObservableList<GameTable> gameInfoObs;
    @FXML
    private TableView<GameTable> gameTable;
    @FXML
    private TableColumn<GameTable, Integer> gameOrderIdClmn;
    @FXML
    private TableColumn<GameTable, String> gameDateClmn;
    @FXML
    private TableColumn<GameTable, Integer> gameIdClmn;
    @FXML
    private TableColumn<GameTable, String> gameTypeClmn;
    @FXML
    private TableColumn<GameTable, Double> gamePriceClmn;

    //-------------------------------------------------------------------
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
    private Button profileButton;

    @FXML
    private Button vipButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button gameButton;

    private Scene sceneGame;

    private Scene sceneProfile;
    private Scene sceneVip;
    private Scene sceneCustLogin;

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

        //-------------------------------------------------------------------------------------------
        //For Food

        foodOrderIdClmn.setCellValueFactory(new PropertyValueFactory<FoodTable, Integer>("orderid"));
        foodDateClmn.setCellValueFactory(new PropertyValueFactory<FoodTable, String>("date"));
        foodNameClmn.setCellValueFactory(new PropertyValueFactory<FoodTable, String>("FOOD_NAME"));
        foodQuantityClmn.setCellValueFactory(new PropertyValueFactory<FoodTable, Integer>("FOOD_QUANTITY"));
        foodPriceClmn.setCellValueFactory(new PropertyValueFactory<FoodTable, Double>("totalprice"));

        try {
            readFoodInfo();
        } catch (Exception e) {
        }

        foodInfoObs = FXCollections.observableArrayList(foodInfo);

        foodTable.setItems(foodInfoObs);

        //-------------------------------------------------------------------------------------------
        //For Room

        roomOrderIdClmn.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("Order_id"));
        roomDateClmn.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("Date"));
        roomIdClmn.setCellValueFactory(new PropertyValueFactory<RoomTable, Integer>("Room_id"));
        roomTimeRangeClmn.setCellValueFactory(new PropertyValueFactory<RoomTable, String>("TimeRange"));
        roomPriceClmn.setCellValueFactory(new PropertyValueFactory<RoomTable, Double>("price"));

        try {
            readRoomInfo();
        } catch (Exception e) {
        }

        roomInfoObs = FXCollections.observableArrayList(roomInfo);

        roomTable.setItems(roomInfoObs);

        //-------------------------------------------------------------------------------------------
        //For Game

        gameOrderIdClmn.setCellValueFactory(new PropertyValueFactory<GameTable, Integer>("order_id"));
        gameDateClmn.setCellValueFactory(new PropertyValueFactory<GameTable, String>("Order_time"));
        gameIdClmn.setCellValueFactory(new PropertyValueFactory<GameTable, Integer>("Game_id"));
        gameTypeClmn.setCellValueFactory(new PropertyValueFactory<GameTable, String>("Gamr_type"));
        gamePriceClmn.setCellValueFactory(new PropertyValueFactory<GameTable, Double>("Total"));

        try {
            readGameInfo();
        } catch (Exception e) {
        }

        gameInfoObs = FXCollections.observableArrayList(gameInfo);

        gameTable.setItems(gameInfoObs);

        //-------------------------------------------------------------------------------------------
    }

    public void readFoodInfo() throws SQLException, ClassNotFoundException {
        connectDB();

        foodInfo.clear();
        String SQL = "select t.Order_id, t.Order_time, f.FName, o.Quantity, o.Quantity * f.Price as Total\n" +
                "from OrderTable t, Food f, OrderedFood o\n" +
                "where o.Food_id = f.Food_id and o.Order_id = t.Order_id and o.C_id = " + HelloApplication.custID +";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()){
            FoodTable f = new FoodTable(rs.getInt(1), rs.getString(3), rs.getString(2), rs.getInt(4), rs.getDouble(5));
            foodInfo.add(f);
        }

        con.close();
    }

    public void readRoomInfo() throws SQLException, ClassNotFoundException {
        connectDB();

        roomInfo.clear();
        String SQL = "SELECT br.Order_id, tr.Time_range, r.Room_id, br.BDate, r.Price_per_hour \n" +
                "FROM BookRoom br\n" +
                "JOIN Time_for_reservation tr ON br.Time_id = tr.Time_id\n" +
                "JOIN Room r ON br.Room_id = r.Room_id \n" +
                "where br.C_id = " + HelloApplication.custID + ";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()){
            RoomTable r = new RoomTable(rs.getInt(1), rs.getString(4), rs.getInt(3), rs.getString(2), rs.getDouble(5));
            roomInfo.add(r);
        }

        con.close();
    }

    public void readGameInfo() throws SQLException, ClassNotFoundException {
        connectDB();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDate = null;
        String endDate = null;
        double price;

        gameInfo.clear();
        String SQL = "select r.Order_id, r.Start_time, r.End_time, g.Game_id, g.GType, g.Price_per_hour\n" +
                "from Game g, ReserveGame r\n" +
                "where g.Game_id = r.Game_id and r.C_id = " + HelloApplication.custID + ";";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()){
            if(rs.getString(3) != null){
                startDate = rs.getString(2);
                endDate = rs.getString(3);
                LocalDateTime dateTime1= LocalDateTime.parse(startDate, formatter);
                LocalDateTime dateTime2= LocalDateTime.parse(endDate, formatter);
                long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();
                double toHours = (double)diffInMinutes/60;

                price = toHours * rs.getDouble(6);

                GameTable g = new GameTable(rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getString(5), price);
                gameInfo.add(g);
            }
        }

        con.close();
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
        root = FXMLLoader.load(getClass().getResource("CustomerGames.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneGame = new Scene(root);
        stage.setScene(sceneGame);
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
}

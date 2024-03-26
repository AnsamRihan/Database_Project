package com.example.withscenebuilder;

import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerFood implements Initializable {

    double total = 0;
    @FXML
    private ImageView back;

    @FXML
    private ImageView cocacola;

    @FXML
    private ImageView sprite;

    @FXML
    private ImageView orange;

    @FXML
    private ImageView apple;

    @FXML
    private ImageView shawerma;

    @FXML
    private ImageView burger;

    @FXML
    private ImageView falafel;

    @FXML
    private ImageView fries;

    @FXML
    private Button backButton;

    @FXML
    private Button orderButton;

    @FXML
    private Label colaPrice;
    @FXML
    private Label spritePrice;
    @FXML
    private Label orangePrice;
    @FXML
    private Label applwPrice;
    @FXML
    private Label shawermaPrice;
    @FXML
    private Label falafelPrice;
    @FXML
    private Label friesPrice;
    @FXML
    private Label burgerPrice;

    @FXML
    private TextField colaQ;
    @FXML
    private TextField spriteQ;
    @FXML
    private TextField orangeQ;
    @FXML
    private TextField appleQ;
    @FXML
    private TextField shawermaQ;
    @FXML
    private TextField falafelQ;
    @FXML
    private TextField friesQ;
    @FXML
    private TextField burgerQ;

    @FXML
    private JFXCheckBox colaCB;
    @FXML
    private JFXCheckBox spriteCB;
    @FXML
    private JFXCheckBox orangeCB;
    @FXML
    private JFXCheckBox appleCB;
    @FXML
    private JFXCheckBox shawermaCB;
    @FXML
    private JFXCheckBox flafelCB;
    @FXML
    private JFXCheckBox friesCB;
    @FXML
    private JFXCheckBox burgerCB;

    double priceCola;
    double priceSprite;
    double priceOrange;
    double priceApple;
    double priceShawerma;
    double priceFalafel;
    double priceFries;
    double priceBurger;
    private Scene sceneVip;
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

        Image image = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\back21.png");
        back.setImage(image);

        Image image1 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\coca-cola.png");
        cocacola.setImage(image1);

        Image image2 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\sprite.png");
        sprite.setImage(image2);

        Image image3 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\kabi-orange.png");
        orange.setImage(image3);

        Image image4 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\kabi-apple.png");
        apple.setImage(image4);

        Image image5 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\shawerma.png");
        shawerma.setImage(image5);

        Image image6 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\falafel.png");
        falafel.setImage(image6);

        Image image7 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\fries.png");
        fries.setImage(image7);

        Image image8 = new Image("C:\\Users\\ansam\\IdeaProjects\\withSceneBuilder\\Images\\burger.png");
        burger.setImage(image8);

        try {
            getOrderID();
        } catch (Exception e) {
        }

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

    public void readData() throws SQLException, ClassNotFoundException {
        connectDB();

        String SQL = "select Price from Food where FName = 'Falafel';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        rs.next();
        priceFalafel = rs.getDouble(1);
        falafelPrice.setText(String.valueOf(rs.getDouble(1)));

        String SQL2 = "select Price from Food where FName = 'Shawerma';";
        Statement stmt2 = con.createStatement();
        ResultSet rs2 = stmt2.executeQuery(SQL2);
        rs2.next();
        priceShawerma = rs2.getDouble(1);
        shawermaPrice.setText(String.valueOf(rs2.getDouble(1)));

        String SQL3 = "select Price from Food where FName = 'Burger';";
        Statement stmt3 = con.createStatement();
        ResultSet rs3 = stmt3.executeQuery(SQL3);
        rs3.next();
        priceBurger = rs3.getDouble(1);
        burgerPrice.setText(String.valueOf(rs3.getDouble(1)));

        String SQL4 = "select Price from Food where FName = 'Fries';";
        Statement stmt4 = con.createStatement();
        ResultSet rs4 = stmt4.executeQuery(SQL4);
        rs4.next();
        priceFries = rs4.getDouble(1);
        friesPrice.setText(String.valueOf(rs4.getDouble(1)));

        String SQL5 = "select Price from Food where FName = 'CocaCola';";
        Statement stmt5 = con.createStatement();
        ResultSet rs5 = stmt5.executeQuery(SQL5);
        rs5.next();
        priceCola = rs5.getDouble(1);
        colaPrice.setText(String.valueOf(rs5.getDouble(1)));

        String SQL6 = "select Price from Food where FName = 'Sprite';";
        Statement stmt6 = con.createStatement();
        ResultSet rs6 = stmt6.executeQuery(SQL6);
        rs6.next();
        priceSprite = rs6.getDouble(1);
        spritePrice.setText(String.valueOf(rs6.getDouble(1)));

        String SQL7 = "select Price from Food where FName = 'Orange juice';";
        Statement stmt7 = con.createStatement();
        ResultSet rs7 = stmt7.executeQuery(SQL7);
        rs7.next();
        priceOrange = rs7.getDouble(1);
        orangePrice.setText(String.valueOf(rs7.getDouble(1)));

        String SQL8 = "select Price from Food where FName = 'Apple juice';";
        Statement stmt8 = con.createStatement();
        ResultSet rs8 = stmt8.executeQuery(SQL8);
        rs8.next();
        priceApple = rs8.getDouble(1);
        applwPrice.setText(String.valueOf(rs8.getDouble(1)));

        con.close();
    }

    public void SwitchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerVip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneVip = new Scene(root);
        stage.setScene(sceneVip);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        HelloApplication.flagForFood = true;

        connectDB();
        ExecuteStatement("insert into OrderTable (Order_id, Order_time) values (" + HelloApplication.orderid + ", now());");

        System.out.println(HelloApplication.orderid);

        int id;

        if(appleCB.isSelected()){
            total = total + (priceApple * Integer.parseInt(appleQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Apple juice';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + appleQ.getText() + ");");
        } if(orangeCB.isSelected()) {
            total = total + (priceOrange * Integer.parseInt(orangeQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Orange juice';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + orangeQ.getText() + ");");
        } if(colaCB.isSelected()) {
            total = total + (priceCola * Integer.parseInt(colaQ.getText()));
            String SQL = "select Food_id from Food where FName = 'CocaCola';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + colaQ.getText() + ");");
        } if(spriteCB.isSelected()) {
            total = total + (priceSprite * Integer.parseInt(spriteQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Sprite';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + spriteQ.getText() + ");");
        } if(shawermaCB.isSelected()) {
            total = total + (priceShawerma * Integer.parseInt(shawermaQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Shawerma';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + shawermaQ.getText() + ");");
        } if(flafelCB.isSelected()) {
            total = total + (priceFalafel * Integer.parseInt(falafelQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Falafel';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + falafelQ.getText() + ");");
        } if(friesCB.isSelected()) {
            total = total + (priceFries * Integer.parseInt(friesQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Fries';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + friesQ.getText() + ");");
        } if(burgerCB.isSelected()) {
            total = total + (priceBurger * Integer.parseInt(burgerQ.getText()));
            String SQL = "select Food_id from Food where FName = 'Burger';";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            id = rs.getInt(1);

            ExecuteStatement("insert into OrderedFood (Order_id, C_id, Food_id, Quantity) values("
                    + HelloApplication.orderid + ","
                    + HelloApplication.custID + ", "
                    + id + ", "
                    + burgerQ.getText() + ");");
        }

        con.close();

        HelloApplication.roomFoodTotal = total;

        root = FXMLLoader.load(getClass().getResource("CustomerVip.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneVip = new Scene(root);
        stage.setScene(sceneVip);
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

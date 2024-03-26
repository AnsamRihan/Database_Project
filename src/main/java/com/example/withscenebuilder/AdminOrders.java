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
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class AdminOrders implements Initializable {
    private String dbURL;
    private static String dbUsername = "root"; // database username
    private static String dbPassword = "root123"; // database password
    private static String URL = "127.0.0.1"; // server location
    private static String port = "3306"; // port that mysql uses
    private static String dbName = "castle"; //database on mysql to connect to
    private Connection con;

    @FXML
    private ImageView User;
    @FXML
    private ImageView add;
    @FXML
    private TextField enterMonth;
    @FXML
    private TextField enterYear;
    @FXML
    private Button getInfoButton;
    @FXML
    private Button profileButton;
    @FXML
    private ImageView logOut;
    @FXML
    private Button logoutButton;
    @FXML
    private Button paymentsButton;
    @FXML
    private ImageView orders;
    @FXML
    private ImageView orders1;
    @FXML
    private PieChart pieChart;
    @FXML
    private ImageView profile;
    @FXML
    private Label numOfOrders;

    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    private Scene scene;
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

        try {
            readData();
        } catch (Exception e) {
        }
    }

    private void readData() throws SQLException, ClassNotFoundException {
        connectDB();

        String SQL = "SELECT    MONTH(PDate), COUNT(*) \n" +
                "FROM      Payment \n" +
                "WHERE     YEAR(PDate) = '2023' \n" +
                "GROUP BY  MONTH(PDate);";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(SQL);
        while (rs.next()){
            String info = "Month " + rs.getString(1);
            pieChartData.add(new PieChart.Data(info, rs.getDouble(2)));
        }

        pieChart.setData(pieChartData);
    }

    public void getInfo() throws SQLException, ClassNotFoundException {
        connectDB();
        boolean flagInfo = false;
        boolean flagMoney = false;

        if(onlyDigits(enterYear.getText(), enterYear.getText().length()) && !enterYear.getText().isEmpty()){
            if(onlyDigits(enterMonth.getText(), enterMonth.getText().length()) && !enterMonth.getText().isEmpty()){
                pieChartData.clear();
                String SQL = "SELECT    MONTH(PDate), COUNT(*) \n" +
                        "FROM      Payment \n" +
                        "WHERE     YEAR(PDate) = '" + enterYear.getText() + "' \n" +
                        "GROUP BY  MONTH(PDate);\n";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
                while (rs.next()){
                    flagInfo = true;
                    String info = "Month " + rs.getString(1);
                    pieChartData.add(new PieChart.Data(info, rs.getInt(2)));
                }

                if(flagInfo == false){
                    numOfOrders.setText("000");
                    pieChart.setTitle("ORDER NUMBER PER MONTH " + enterYear.getText());
                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type , "");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setContentText("THERE IS NO ORDERS IN THIS YEAR!!!");
                    alert.getDialogPane().setHeaderText("INFO");
                    alert.show();
                }else{
                    pieChart.setData(pieChartData);
                    pieChart.setTitle("ORDER NUMBER PER MONTH " + enterYear.getText());
                    if(Integer.parseInt(enterMonth.getText()) < 13 && Integer.parseInt(enterMonth.getText()) >= 1){
                        String SQL2 = "SELECT  COUNT(*)\n" +
                                "FROM      Payment \n" +
                                "WHERE  YEAR(PDate) = " + enterYear.getText() + " and  MONTH(PDate) = " + enterMonth.getText() +";";
                        Statement stmt2 = con.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(SQL2);

                        while (rs2.next()) {
                            flagMoney = true;
                            numOfOrders.setText(String.valueOf(rs2.getInt(1)));
                        }

                        if(flagMoney == false){
                            numOfOrders.setText("000");
                            Alert.AlertType type = Alert.AlertType.WARNING;
                            Alert alert = new Alert(type , "");
                            alert.initModality(Modality.APPLICATION_MODAL);
                            alert.getDialogPane().setContentText("MONTH DOESN'T HAVE ANY ORDERS!!!");
                            alert.getDialogPane().setHeaderText("INFO");
                            alert.show();
                        }
                    }else{
                        Alert.AlertType type = Alert.AlertType.WARNING;
                        Alert alert = new Alert(type , "");
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.getDialogPane().setContentText("MONTH CANNOT BE LESS THAN 1 OR MORE THAN 12!!!");
                        alert.getDialogPane().setHeaderText("INFO");
                        alert.show();
                    }
                }
            }else{
                Alert.AlertType type = Alert.AlertType.WARNING;
                Alert alert = new Alert(type , "");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setContentText("MONTH CANNOT CONTAIN CHARACTERS OR BE LEFT EMPTY!!!");
                alert.getDialogPane().setHeaderText("INFO");
                alert.show();
            }
        }else{
            Alert.AlertType type = Alert.AlertType.WARNING;
            Alert alert = new Alert(type , "");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setContentText("YEAR CANNOT CONTAIN CHARACTERS!!!");
            alert.getDialogPane().setHeaderText("INFO");
            alert.show();
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

    public void SwitchToScene3(ActionEvent event) throws IOException {
        HelloApplication.AdminId=-1;
        root = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sceneCustLogin = new Scene(root);
        stage.setScene(sceneCustLogin);
        stage.show();
    }

    public void SwitchToScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainAdmin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToSceneADD(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminAdd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AdminPayments.fxml"));
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
            s.printStackTrace();
            System.out.println("SQL statement is not executed!");
        }
    }
}

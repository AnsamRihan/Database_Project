module com.example.withscenebuilder {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires com.jfoenix;


    opens com.example.withscenebuilder to javafx.fxml;
    exports com.example.withscenebuilder;
}
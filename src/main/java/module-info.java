module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports sample.pojo;
    opens sample.pojo to javafx.fxml;
    opens Model to javafx.fxml;
    exports Model;
}
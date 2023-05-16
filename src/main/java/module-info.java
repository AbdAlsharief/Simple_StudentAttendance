module com.example.studentattendance {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    exports com.example.studentattendance.controller to javafx.fxml;
    opens com.example.studentattendance.controller;
    exports com.example.studentattendance to javafx.fxml;
    opens com.example.studentattendance;
    exports com.example.studentattendance.models to javafx.fxml;
    opens com.example.studentattendance.models;
}

module com.example.studantattendence {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.studentattendance to javafx.fxml;
    opens com.example.studentattendance.controller to javafx.fxml;

    exports com.example.studentattendance;
    exports com.example.studentattendance.controller;
}

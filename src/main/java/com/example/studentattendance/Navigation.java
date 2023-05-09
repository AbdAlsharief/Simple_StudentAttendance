package com.example.studentattendance;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public final String LOGIN_FXML = "views/login.fxml";
    public final String ADMIN_FXML = "views/admin.fxml";
    public final String LECTURE_FXML = "views/lecture.fxml";
    public final String TEACHER_FXML = "views/teacher.fxml";
    public final String ACCOUNTS_FXML = "views/accounts.fxml";

    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

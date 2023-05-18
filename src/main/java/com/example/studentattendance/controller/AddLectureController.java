package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddLectureController {
@FXML
private GridPane addGrid;
    @FXML
    private ChoiceBox<String> showTeacher;

    @FXML
    private TextField lecture;

    @FXML
    private TextField classroom;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;
AccountDataModel accountDataModel = new AccountDataModel();
    private Navigation navigation= new Navigation();
    @FXML
    public void initialize() {
        // Populate the choice box with teacher usernames
        showTeacher.getItems().addAll(getTeacherUsernames());
    }

    @FXML
    private void save() {
        // Handle save button action here
    }

    @FXML
    private void back() {
   navigation.navigateTo(addGrid,navigation.LECTURE_FXML);
    }

    private ArrayList<String> getTeacherUsernames() {
        ArrayList<String> teacherUsernames = new ArrayList<>();
        for (Account account : accountDataModel.getAccounts()) {
            if (account.getCode() >= 100 && account.getCode() <= 200) {
                teacherUsernames.add(account.getUsername());
            }
        }
        return teacherUsernames;
    }

    // Add additional methods and event handlers as needed
}

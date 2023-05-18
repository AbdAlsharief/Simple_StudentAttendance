package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import com.example.studentattendance.models.Lecture;
import com.example.studentattendance.models.LectureDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
LectureDataModel lectureDataModel = new LectureDataModel();
    AccountDataModel accountDataModel = new AccountDataModel();
    private Navigation navigation= new Navigation();
    @FXML
    public void initialize() {
        // Populate the choice box with teacher usernames
        showTeacher.getItems().addAll(getTeacherUsernames());
    }

    @FXML
    private void save() {
        String selectedTeacherUsername = showTeacher.getValue();
        int selectedTeacherCode = accountDataModel.getCodeByUsername(selectedTeacherUsername);

        Lecture lecture1 = new Lecture(generateUniqueNumber(1000, 2000), selectedTeacherCode, selectedTeacherUsername, lecture.getText(), classroom.getText());
        lectureDataModel.addLecture(lecture1);
        lectureDataModel.saveLecture();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The student has been added successfully");
        alert.setTitle("Done");
        alert.setContentText("Username: " + lecture.getText() + ", Password: " + classroom.getText());

        alert.showAndWait();
    }

    @FXML
    private void back() {
   navigation.navigateTo(addGrid,navigation.LECTURE_FXML);
    }

    private ArrayList<String> getTeacherUsernames() {
        ArrayList<String> teacherUsernames = new ArrayList<>();
        for (Account account : accountDataModel.getAccounts()) {
            if (account.getCode() >= 100 && account.getCode() < 200) {
                teacherUsernames.add(account.getUsername());
            }
        }
        return teacherUsernames;
    }
    private int generateUniqueNumber(int min, int max) {
        int uniqueNumber;
        boolean isUnique;

        do {
            uniqueNumber = (int) (Math.random() * (max - min + 1)) + min;
            isUnique = true;

            // Check if the generated number is already used as a code for another account
            LectureDataModel lectureDataModel = new LectureDataModel();
            for (Lecture lecture : lectureDataModel.getLectures()) {
                if (lecture.getLCode() == uniqueNumber) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);

        return uniqueNumber;
    }


}

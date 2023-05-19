package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import com.example.studentattendance.models.Lecture;
import com.example.studentattendance.models.LectureDataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class EditLectureController {
    private Lecture selectedLecture;

    @FXML
    private GridPane editGrid;

    @FXML
    private ChoiceBox<String> showTeacher;

    @FXML
    private Button editButton;

    @FXML
    private Button backButton;

    private LectureDataModel lectureDataModel;
    private Navigation navigation;
    private AccountDataModel accountDataModel;

    public EditLectureController() {
        lectureDataModel = new LectureDataModel();
        navigation = new Navigation();
        accountDataModel = new AccountDataModel();
    }

    @FXML
    public void initialize() {
            showTeacher.getItems().addAll(getTeacherUsernames());
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



    @FXML
    public void onSave() {
        String selectedTeacherUsername = showTeacher.getValue();
        int selectedTeacherCode = accountDataModel.getCodeByUsername(selectedTeacherUsername);

        Lecture editedLecture = new Lecture(selectedLecture.getLCode(), selectedTeacherCode, selectedTeacherUsername, selectedLecture.getLName(), selectedLecture.getClassroom());
        lectureDataModel.addLecture(editedLecture);
        lectureDataModel.removeLecture(selectedLecture);
        lectureDataModel.saveLecture();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The lecture has been edited successfully");
        alert.setTitle("Done");
        alert.showAndWait();
    }

    @FXML
    public void onBack() {
        navigation.navigateTo(editGrid, navigation.LECTURE_FXML);
    }

    public void setSelectedLecture(Lecture selectedLecture) {
        this.selectedLecture = selectedLecture;
    }
}

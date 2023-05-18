package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import com.example.studentattendance.models.Lecture;
import com.example.studentattendance.models.LectureDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class EditLectureController {
    private Lecture selectedLecture;

    @FXML
    private GridPane editGrid;

    @FXML
    private ChoiceBox<String> showTeacher;

    @FXML
    private TextField lecture;

    @FXML
    private TextField classroom;

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
        // Set the initial values for the choice box and text fields
        if (selectedLecture != null) {
            lecture.setText(selectedLecture.getLName());
            classroom.setText(selectedLecture.getClassroom());
            showTeacher.getItems().addAll(getTeacherUsernames());
            showTeacher.setValue(String.valueOf(getTeacherUsernames()));
        }
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
        if (selectedLecture != null) {
            String newLecture = lecture.getText();
            String newClassroom = classroom.getText();
            String newTeacherUsername = showTeacher.getValue();

            selectedLecture.setLName(newLecture);
            selectedLecture.setClassroom(newClassroom);
            selectedLecture.setTeacher_name(newTeacherUsername);

            lectureDataModel.saveLecture();
        }

        navigation.navigateTo(editGrid, navigation.LECTURE_FXML);
    }

    @FXML
    public void onBack() {
        navigation.navigateTo(editGrid, navigation.LECTURE_FXML);
    }

    public void setSelectedLecture(Lecture selectedLecture) {
        this.selectedLecture = selectedLecture;
    }
}

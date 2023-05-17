package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.AccountDataModel;
import com.example.studentattendance.models.Lecture;
import com.example.studentattendance.models.LectureDataModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class LectureController {

    @FXML
    private AnchorPane lecturePane;

    @FXML
    private TableView<Lecture> table;

    @FXML
    private TableColumn<Lecture, Integer> codeColumn;
    @FXML
    private TableColumn<Lecture, Integer> teacherCode;

    @FXML
    private TableColumn<Lecture, String> TeacherName;

    @FXML
    private TableColumn<Lecture, String> Lecture;

    @FXML
    private TableColumn<Lecture, String> Classroom;

    private Navigation navigation;
    private LectureDataModel lectureDataModel;

    public LectureController() {
        navigation = new Navigation();
        lectureDataModel = new LectureDataModel();
    }

    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("lCode"));
       teacherCode.setCellValueFactory(new PropertyValueFactory<>("teacher_Code"));
        TeacherName.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
        Lecture.setCellValueFactory(new PropertyValueFactory<>("lName"));
        Classroom.setCellValueFactory(new PropertyValueFactory<>("classroom"));

        table.setItems(FXCollections.observableArrayList(lectureDataModel.getLectures()));
        table.refresh();
    }

    @FXML
    public void add() {
        // TODO: Implement add functionality
    }

    @FXML
    public void delete() {
        // TODO: Implement delete functionality
    }

    @FXML
    public void back() {
        navigation.navigateTo(lecturePane, navigation.ADMIN_FXML);
    }

    @FXML
    public void edit() {
        // TODO: Implement edit functionality
    }
}

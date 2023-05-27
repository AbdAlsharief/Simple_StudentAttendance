package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Lecture;
import com.example.studentattendance.models.LectureDataModel;
import com.example.studentattendance.models.Teacher_StudentDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class AttendanceController {
    public static String selectedLecture;
    @FXML
    private AnchorPane attendancePane;
    @FXML
    private ChoiceBox<String> lectureChoiceBox;
    LectureDataModel lectureDataModel=new LectureDataModel();
Navigation navigation=new Navigation();

@FXML
public void initialize(){
    lectureChoiceBox.getItems().addAll(getFilteredLectureNames(LoginController.loggedInUsername));

}
public void onAttendance() {
    selectedLecture = lectureChoiceBox.getSelectionModel().getSelectedItem();
    navigation.navigateTo(attendancePane,navigation.TAKE_ATTENDANCE_FXML);
}
public void onBack(){
    navigation.navigateTo(attendancePane,navigation.TEACHER_MAIN_FXML);

}
    public ArrayList<String> getFilteredLectureNames(String teacherName) {
        ArrayList<String> filteredLectureNames = new ArrayList<>();
        for (Lecture lecture : lectureDataModel.getLectures()) {
            if (lecture.getTeacher_name() != null && lecture.getTeacher_name().equals(teacherName)) {
                filteredLectureNames.add(lecture.getLName());
            }
        }
        return filteredLectureNames;
    }
}

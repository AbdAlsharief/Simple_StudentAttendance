package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Teacher_Student;
import com.example.studentattendance.models.Teacher_StudentDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class EditTeacherStudentController {
    @FXML
    private AnchorPane editPane;
    @FXML
    private TextField id;
    @FXML
    private TextField lecture;
    @FXML
    private TextField attendance;
    @FXML
    private Button saveButton;

    private Teacher_StudentDataModel teacherStudentDataModel = new Teacher_StudentDataModel();
    private int studentID;
    Navigation navigation = new Navigation();
    private String lectureName;

    public void check() {
        String studentIDText = id.getText();
        lectureName = lecture.getText();

        if (!studentIDText.isEmpty() && !lectureName.isEmpty()) {
            try {
                studentID = Integer.parseInt(studentIDText);
                int attendanceValue = teacherStudentDataModel.getAttendanceByIdAndLecture(studentID, lectureName);

                if (attendanceValue != -1) {
                    attendance.setText(String.valueOf(attendanceValue));
                    addAttendanceChangeListener();
                } else {
                    attendance.setText("Attendance not found");
                }
            } catch (NumberFormatException e) {
                attendance.setText("Invalid ID format");
            }
        } else {
            attendance.setText("Invalid input");
        }
    }

    private void addAttendanceChangeListener() {
        attendance.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                try {
                    int newAttendance = Integer.parseInt(newValue);
                    teacherStudentDataModel.updateAttendanceByIdAndLecture(studentID, lectureName, newAttendance);
                } catch (NumberFormatException e) {
                    attendance.setText(oldValue); // Revert to the previous valid value
                }
            }
        });
    }

    public void save() {
        if (studentID != 0 && !lectureName.isEmpty()) {
            try {
                int attendanceValue = teacherStudentDataModel.getAttendanceByIdAndLecture(studentID, lectureName);
                if (attendanceValue != -1) {
                    attendance.setText(String.valueOf(attendanceValue));
                    addAttendanceChangeListener();
                } else {
                    attendance.setText("Attendance not found");
                }
            } catch (NumberFormatException e) {
                // Handle invalid attendance format if needed
            }
        }
    }
    public void back(){
        navigation.navigateTo(editPane,navigation.TEACHER__STUDENT_FXML);
    }
}

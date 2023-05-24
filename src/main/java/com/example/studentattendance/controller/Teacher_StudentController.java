package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Teacher_StudentController {
    @FXML
    private AnchorPane teacherPane;

    @FXML
    private ChoiceBox<String> lectureChoiceBox;
    @FXML
    private ComboBox<Student> studentComboBox;
    @FXML
    private Button saveTeacher_Student;

    private LectureDataModel lectureDataModel;
    private Navigation navigation;

    private Teacher_StudentDataModel teacher_studentDataModel;
    private StudentDataModel studentDataModel;
    private ObservableList<Student> originalStudents;
    private AccountDataModel accountDataModel =new AccountDataModel();
    private  Student selectedStudent;


    public Teacher_StudentController(){
        navigation = new Navigation();
        teacher_studentDataModel=new Teacher_StudentDataModel();
        studentDataModel=new StudentDataModel();
        originalStudents = FXCollections.observableArrayList(studentDataModel.getStudents());
        lectureDataModel = new LectureDataModel();
    }

    public void initialize() {

        lectureChoiceBox.getItems().addAll(getFilteredLectureNames(LoginController.loggedInUsername));
        studentComboBox.setEditable(true);

        // Set up cell factory to display ID and student name
        studentComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getID() + " - " + student.getStudentName());
                }
            }
        });

        // Set up button cell to display ID and student name
        studentComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getID() + " - " + student.getStudentName());
                }
            }
        });

        // Add all students to the combo box
        studentComboBox.getItems().addAll(originalStudents);

        // Enable filtering for the combo box
        FilteredList<Student> filteredStudents = new FilteredList<>(originalStudents, p -> true);
        studentComboBox.setItems(filteredStudents);

        // Set up text field listener for searching
        studentComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            final TextField editor = studentComboBox.getEditor();
            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            String selectedLecture = null;
            if (studentComboBox.getSelectionModel().isEmpty() || selectedLecture == null) {
                // Display an error message or handle the validation failure
                return;
            }

// Get the selected student and lecture names
            selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            selectedLecture = lectureChoiceBox.getValue();

            // Use the filter predicate to match student by ID or name
            filteredStudents.setPredicate(student -> {
                if (student == null) {
                    return false;
                }

                // Check if the entered text matches the student's ID or name
                String lowerCaseFilter = newValue.toLowerCase();
                return String.valueOf(student.getID()).contains(lowerCaseFilter)
                        || student.getStudentName().toLowerCase().contains(lowerCaseFilter);
            });

            // Restore previous selection if the searched value is cleared
            if (newValue.isEmpty()) {
                studentComboBox.getSelectionModel().select(selectedStudent);
            }

            // Show/hide the drop-down list based on the filtering result
            if (!filteredStudents.isEmpty()) {
                studentComboBox.show();
            } else {
                studentComboBox.hide();
            }
        });
       // studentComboBox.setOnAction(event -> comboHandler(event));

        studentComboBox.setOnAction((event) -> {
             selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
            System.out.println(selectedStudent);

        });


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


    public void saveTeacher_Student() {

        handleStudentComboBoxAction();
        studentComboBox.getSelectionModel().clearSelection();
        lectureChoiceBox.getSelectionModel().clearSelection();
    }
    public void handleStudentComboBoxAction() {

//        Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
//        System.out.println(selectedStudent);
        String selectedLecture = lectureChoiceBox.getValue();
        System.out.println(selectedLecture);
        int lectureCode = lectureDataModel.getLCodeByLName(selectedLecture);
        String lectureName = lectureDataModel.getLNameByLCode(lectureCode);
        int attendance = 0;
        int teacherCode = accountDataModel.getCodeByUsername(LoginController.loggedInUsername);

        // Check if a student is selected
        if (selectedStudent != null) {
            Teacher_Student teacherStudent = new Teacher_Student(selectedStudent.getID(), selectedStudent.getStudentName(),
                    teacherCode, LoginController.loggedInUsername, lectureCode, lectureName, attendance);
            teacher_studentDataModel.addTeacher_Student(teacherStudent);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Teacher-Student relationship added successfully.");
            alert.showAndWait();
        }

    }


}

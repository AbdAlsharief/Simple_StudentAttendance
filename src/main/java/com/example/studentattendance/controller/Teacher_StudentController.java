package com.example.studentattendance.controller;

import com.example.studentattendance.DataModel;
import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Teacher_Student,Integer> ID;
    @FXML
    private TableColumn<Teacher_Student,String> student_Name;
    @FXML
    private TableColumn <Teacher_Student,String> teacher_Name;
    @FXML
    private TableColumn<Teacher_Student,String> lecture;
    @FXML
    private TableColumn<Teacher_Student,Integer> attendance;

    private DataModel.LectureDataModel lectureDataModel;
    private Navigation navigation;

    private DataModel.Teacher_StudentDataModel teacher_studentDataModel;
    private DataModel.StudentDataModel studentDataModel;
    private ObservableList<Student> originalStudents;
    private ObservableList<Teacher_Student> Teacher_Student;
    private DataModel.AccountDataModel accountDataModel =new DataModel.AccountDataModel();
//    private static Student selectedStudent;
private static String selectedStudentString ;
private static Student   selectedStudent ;


    public Teacher_StudentController(){
        navigation = new Navigation();
        teacher_studentDataModel=new DataModel.Teacher_StudentDataModel();
        studentDataModel=new DataModel.StudentDataModel();
        originalStudents = FXCollections.observableArrayList(studentDataModel.getStudents());
        lectureDataModel = new DataModel.LectureDataModel();
        Teacher_Student = FXCollections.observableArrayList(teacher_studentDataModel.getTeacher_StudentsByTeacherName(LoginController.loggedInUsername));
    }

    public void initialize() {
        try {
            ID.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
            student_Name.setCellValueFactory(new PropertyValueFactory<>("student_name"));
            teacher_Name.setCellValueFactory(new PropertyValueFactory<>("teacher_name"));
            lecture.setCellValueFactory(new PropertyValueFactory<>("l_Name"));
            attendance.setCellValueFactory(new PropertyValueFactory<>("attendance"));
            tableView.setItems(Teacher_Student);
            studentComboBox.getSelectionModel().getSelectedIndex();

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
                // Use the filter predicate to match students by ID or name
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
                    studentComboBox.getSelectionModel().clearSelection();
                }

                // Show/hide the drop-down list based on the filtering result
                if (!filteredStudents.isEmpty()) {
                    studentComboBox.show();
                } else {
                    studentComboBox.hide();
                }
            });



                studentComboBox.setOnAction(event -> {
                    selectedStudentString = String.valueOf(studentComboBox.getSelectionModel().getSelectedItem());
                    selectedStudent = null;
                    if (selectedStudent != null) {
                        selectedStudentString = selectedStudent.toString();
                    }
                    // Check if the selected item is a string
                    if (selectedStudentString instanceof String) {
                        // Iterate over the originalStudents list to find the matching student
                        for (Student student : originalStudents) {
                            if (student.toString().equals(selectedStudentString)) {
                                selectedStudent = student;
                                break;
                            }

                        }
                    }

                    tableView.refresh();
                    // Now you have the selected Student object
                    // Perform the desired action with the selected student
                    // ...
                });


//        studentComboBox.setOnAction(event -> {
//            selectedStudent = String.valueOf( studentComboBox.getSelectionModel().getSelectedItem());
//            System.out.println(selectedStudent);
////            String selectedLecture = lectureChoiceBox.getValue();
////            System.out.println(selectedStudent);
////            int lectureCode = lectureDataModel.getLCodeByLName(selectedLecture);
////            String lectureName = lectureDataModel.getLNameByLCode(lectureCode);
////            int attendance = 0;
////            int teacherCode = accountDataModel.getCodeByUsername(LoginController.loggedInUsername);
//
//            // Check if a student is selected
////            if (selectedStudent != null) {
////                Teacher_Student teacherStudent = new Teacher_Student(
////                        selectedStudent.getID(), selectedStudent.getStudentName(),
////                        teacherCode, LoginController.loggedInUsername,
////                        lectureCode, lectureName, attendance);
////                teacher_studentDataModel.addTeacher_Student(teacherStudent);
////
////                // Add the Teacher-Student relationship to the TableView
////                Teacher_Student.add(teacherStudent);
////
////                // Show success alert
////                Alert alert = new Alert(Alert.AlertType.INFORMATION);
////                alert.setTitle("Success");
////                alert.setHeaderText(null);
////                alert.setContentText("Teacher-Student relationship added successfully.");
////                alert.showAndWait();
////            }
//
//            // Clear the selection and refresh the view
//
//        });

            // ...
            tableView.refresh();
        } catch (IndexOutOfBoundsException e) {
            // Exception handling code
            e.printStackTrace(); // or any other action you want to take


        }
//        teacher_studentDataModel.checkAndUpdateAttendance();
    }
//        studentComboBox.setOnAction((event) -> {
//            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
//            System.out.println(selectedStudent);
//
//        });



//        studentComboBox.getSelectionModel().getSelectedIndex();
// Set up combo box listener for selecting a student
            // Perform the desired action when a student is selected
//            studentComboBox.getSelectionModel().getSelectedIndex();
//            Student selectedStudent = studentComboBox.getSelectionModel().getSelectedItem();
//            String selectedLecture = lectureChoiceBox.getValue();
//            System.out.println(selectedStudent);
//            int lectureCode = lectureDataModel.getLCodeByLName(selectedLecture);
//            String lectureName = lectureDataModel.getLNameByLCode(lectureCode);
//            int attendance = 0;
//            int teacherCode = accountDataModel.getCodeByUsername(LoginController.loggedInUsername);
//
//            // Check if a student is selected
//            if (selectedStudent != null) {
//                Teacher_Student teacherStudent = new Teacher_Student(selectedStudent.getID(), selectedStudent.getStudentName(),
//                        teacherCode, LoginController.loggedInUsername, lectureCode, lectureName, attendance);
//                teacher_studentDataModel.addTeacher_Student(teacherStudent);
//
//                // Add the Teacher-Student relationship to the TableView
//                Teacher_Student.add(teacherStudent);
//
//                // Show success alert
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Success");
//                alert.setHeaderText(null);
//                alert.setContentText("Teacher-Student relationship added successfully.");
//                alert.showAndWait();
//            }

            // Clear the selection and refresh the view





    public ArrayList<String> getFilteredLectureNames(String teacherName) {
        ArrayList<String> filteredLectureNames = new ArrayList<>();
        for (Lecture lecture : lectureDataModel.getLectures()) {
            if (lecture.getTeacher_name() != null && lecture.getTeacher_name().equals(teacherName)) {
                filteredLectureNames.add(lecture.getLName());
            }
        }
        return filteredLectureNames;
    }
    public void save(){
        try {


            handleStudentComboBoxAction();
            studentComboBox.getSelectionModel().clearSelection();
            lectureChoiceBox.getSelectionModel().clearSelection();
            tableView.refresh();
        }catch (Exception e){
            System.out.println("An error occurred during save");
        }
    }

    public void handleStudentComboBoxAction() {

        try {

            String selectedLecture = lectureChoiceBox.getValue();

            int lectureCode = lectureDataModel.getLCodeByLName(selectedLecture);
            String lectureName = lectureDataModel.getLNameByLCode(lectureCode);
            int attendance = 0;
            int teacherCode = accountDataModel.getCodeByUsername(LoginController.loggedInUsername);

            // Check if a student is selected
            // ...

            // Check if a student is selected

                Teacher_Student teacherStudent = new Teacher_Student(selectedStudent.getID(), String.valueOf(selectedStudent.getStudentName()),
                        teacherCode, LoginController.loggedInUsername, lectureCode, lectureName, attendance);
                teacher_studentDataModel.addTeacher_Student(teacherStudent);

                // Add the Teacher-Student relationship to the TableView
                Teacher_Student.add(teacherStudent);

                // Show success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Teacher-Student relationship added successfully.");
                alert.showAndWait();

                // Refresh the TableView
                tableView.refresh();
                // Handle IndexOutOfBoundsException


            } catch (IndexOutOfBoundsException e) {
                // Exception handling code
                e.printStackTrace(); // or any other action you want to take
            }

    }


    public void deleteTeacher_Student() {
        Teacher_Student selectedTeacherStudent = (com.example.studentattendance.models.Teacher_Student) tableView.getSelectionModel().getSelectedItem();
        if (selectedTeacherStudent != null) {
            // Remove the selected student from the data model and the TableView
            teacher_studentDataModel.removeTeacher_Student(selectedTeacherStudent);
            Teacher_Student.remove(selectedTeacherStudent);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Student removed successfully.");
            alert.showAndWait();
        }

    }
    public void edit(){
        navigation.navigateTo(teacherPane,navigation.EDIT_TEACHER_STUDENT_FXML);
    }
    public void back(){
        try {
            teacher_studentDataModel.checkAndUpdateAttendance();
            navigation.navigateTo(teacherPane, navigation.TEACHER_MAIN_FXML);
        }catch (Exception e){
            System.out.println("All is good");
        }
    }

}

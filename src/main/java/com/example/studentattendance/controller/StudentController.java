package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Student;
import com.example.studentattendance.models.StudentDataModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.util.Optional;

public class StudentController {

    @FXML
    private BorderPane studentPane;

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String> mobileNumberColumn;

    @FXML
    private TableColumn<Student, String> residenceAreaColumn;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private TextField mobileNumberTextField;

    @FXML
    private TextField residenceAreaTextField;

    private Navigation navigation;
    private StudentDataModel studentDataModel;

    public StudentController() {
        navigation = new Navigation();
        studentDataModel = new StudentDataModel();
    }

    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        residenceAreaColumn.setCellValueFactory(new PropertyValueFactory<>("residenceArea"));

        tableView.setEditable(true);
        tableView.setItems(FXCollections.observableArrayList(studentDataModel.getStudents()));

        // Enable multiple row selection
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Set cell factory for editable columns
        studentNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mobileNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        residenceAreaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Set cell value factories for editable columns
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        residenceAreaColumn.setCellValueFactory(new PropertyValueFactory<>("residenceArea"));

        // Set cell value factories to commit changes
        studentNameColumn.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setStudentName(event.getNewValue());
            // Update the data model with the new value
            student.setStudentName(event.getNewValue());
        });


        mobileNumberColumn.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setMobileNumber(event.getNewValue());
            // Update the data model with the new value
            student.setMobileNumber(event.getNewValue());
        });

        residenceAreaColumn.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setResidenceArea(event.getNewValue());
            // Update the data model with the new value
            student.setResidenceArea(event.getNewValue());
        });
        FilteredList<Student> filteredStudents = new FilteredList<>(FXCollections.observableArrayList(studentDataModel.getStudents()));

        // Set the filtered list as the items in the TableView
        tableView.setItems(filteredStudents);

        // Add a listener to the idTextField to filter the TableView based on the entered ID
        FilteredList<Student> finalFilteredStudents1 = filteredStudents;
        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredStudents1.setPredicate(student -> {
                // If the filter text is empty, show all students
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare the entered ID with the student's ID
                int enteredId = Integer.parseInt(newValue);
                return student.getID() == enteredId;
            });
        });
        filteredStudents = new FilteredList<>(FXCollections.observableArrayList(studentDataModel.getStudents()));

        // Set the filtered list as the items in the TableView
        tableView.setItems(filteredStudents);

        // Add a listener to the studentNameTextField to filter the TableView based on the entered student name
        FilteredList<Student> finalFilteredStudents = filteredStudents;
        studentNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredStudents.setPredicate(student -> {
                // If the filter text is empty, show all students
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convert both the filter text and student name to lowercase for case-insensitive comparison
                String filterText = newValue.toLowerCase();
                String studentName = student.getStudentName().toLowerCase();

                // Check if the student name contains the filter text
                return studentName.contains(filterText);
            });
        });
        filteredStudents = new FilteredList<>(FXCollections.observableArrayList(studentDataModel.getStudents()));

        // Set the filtered list as the items in the TableView
        tableView.setItems(filteredStudents);

        // Add a listener to the mobileNumberTextField to filter the TableView based on the entered mobile number
        FilteredList<Student> finalFilteredStudents2 = filteredStudents;
        mobileNumberTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredStudents2.setPredicate(student -> {
                // If the filter text is empty, show all students
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convert both the filter text and mobile number to lowercase for case-insensitive comparison
                String filterText = newValue.toLowerCase();
                String mobileNumber = student.getMobileNumber().toLowerCase();

                // Check if the mobile number contains the filter text
                return mobileNumber.contains(filterText);
            });
        });
        // Create a filtered list based on the original list of students
        filteredStudents = new FilteredList<>(FXCollections.observableArrayList(studentDataModel.getStudents()));

        // Set the filtered list as the items in the TableView
        tableView.setItems(filteredStudents);

        // Add a listener to the residenceAreaTextField to filter the TableView based on the entered residence area
        FilteredList<Student> finalFilteredStudents3 = filteredStudents;
        residenceAreaTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            finalFilteredStudents3.setPredicate(student -> {
                // If the filter text is empty, show all students
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Convert both the filter text and residence area to lowercase for case-insensitive comparison
                String filterText = newValue.toLowerCase();
                String residenceArea = student.getResidenceArea().toLowerCase();

                // Check if the residence area contains the filter text
                return residenceArea.contains(filterText);
            });
        });
    }



    @FXML
    public void add() {
//        String id = idTextField.getText();
//        String studentName = studentNameTextField.getText();
//        String mobileNumber = mobileNumberTextField.getText();
//        String residenceArea = residenceAreaTextField.getText();
//
//        // Validate the input fields (e.g., check if they are not empty)
//
//        // Create a new Student object
//        Student newStudent = new Student(id, studentName, mobileNumber, residenceArea);
//
//        // Add the new student to the data model
//        studentDataModel.addStudent(newStudent);
//
//        // Add the new student to the table view
//        tableView.getItems().add(newStudent);
//
//        // Clear the input fields
//        clearInputFields();
   }

    @FXML
    public void delete() {
        // Get the selected students from the table view
        ObservableList<Student> selectedStudents = tableView.getSelectionModel().getSelectedItems();
        if (selectedStudents != null && !selectedStudents.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Students");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected students?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                studentDataModel.removeStudent(selectedStudents);
                tableView.getItems().removeAll(selectedStudents);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Students Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select one or more students to delete.");
            alert.showAndWait();
        }
    }

//    @FXML
//    public void edit() {
//        // Get the selected student from the table view
//       Student selectedStudent = tableView.getSelectionModel().getSelectedItem();
//        if (selectedStudent != null) {
//            // Get the updated values from the input fields
//            int id = Integer.parseInt(idTextField.getText());
//            String studentName = studentNameTextField.getText();
//            String mobileNumber = mobileNumberTextField.getText();
//            String residenceArea = residenceAreaTextField.getText();
//
//            // Validate the input fields (e.g., check if they are not empty)
//
//            // Update the selected student with the new values
//            selectedStudent.setID(id);
//            selectedStudent.setStudentName(studentName);
//            selectedStudent.setMobileNumber(mobileNumber);
//            selectedStudent.setResidenceArea(residenceArea);
//
//            // Refresh the table view to reflect the changes
//            tableView.refresh();
//
//            // Clear the input fields
//            clearInputFields();
//       }
//   }

    @FXML
    public void back() {
        navigation.navigateTo(studentPane, navigation.ADMIN_FXML);
    }

    // Method to clear the input fields
    private void clearInputFields() {
//        idTextField.clear();
//        studentNameTextField.clear();
//        mobileNumberTextField.clear();
//        residenceAreaTextField.clear();
    }

}

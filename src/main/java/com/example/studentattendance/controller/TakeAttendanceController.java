package com.example.studentattendance.controller;

import com.example.studentattendance.DataModel;
import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Teacher_Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TakeAttendanceController {
    @FXML
    private AnchorPane takePane;

    @FXML
    private TableView<Teacher_Student> tableView;
    @FXML
    private TableColumn<Teacher_Student, Integer> studentId;
    @FXML
    private TableColumn<Teacher_Student, String> studentName;
    @FXML
    private TableColumn<Teacher_Student, Boolean> select;
    @FXML
    private TextField id;
    private FilteredList<Teacher_Student> filteredData;

    private DataModel.Teacher_StudentDataModel teacher_studentDataModel = new DataModel.Teacher_StudentDataModel();
    private Navigation navigation = new Navigation();

    public void initialize() {
        ObservableList<Teacher_Student> teacherStudentList = FXCollections.observableArrayList(teacher_studentDataModel.getTeacher_StudentsByLectureName(AttendanceController.selectedLecture));


        studentId.setCellValueFactory(new PropertyValueFactory<>("student_ID"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        select.setCellValueFactory(new PropertyValueFactory<Teacher_Student, Boolean>("selected"));
//        System.out.println(tableView.getSelectionModel().getSelectedItem().getSelected());
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Boolean selected = newSelection.getSelected().isSelected();
                System.out.println("Selected: " + selected);
            }
        });

        tableView.setItems(teacherStudentList);
        filteredData = new FilteredList<>(tableView.getItems());

        // Add a listener to the ID TextField
        id.textProperty().addListener((observable, oldValue, newValue) -> {
            // Apply the filter based on the entered value
            if (newValue.matches("\\d+")) {
                // Filter by ID column
                filteredData.setPredicate(student -> String.valueOf(student.getStudent_ID()).contains(newValue));
            } else {
                // Filter by name column
                filteredData.setPredicate(student -> student.getStudent_name().toLowerCase().contains(newValue.toLowerCase()));
            }
        });

        // Set the filtered data as the items of the table view
        tableView.setItems(filteredData);


    }

    public void onBack() {
        ObservableList<Teacher_Student> selectedStudents = FXCollections.observableArrayList();
        for (Teacher_Student student : tableView.getItems()) {
            if (student.getSelected().isSelected()) {
                selectedStudents.add(student);
            }
        }

        // Print the selected students' values
        for (Teacher_Student selectedStudent : selectedStudents) {
            teacher_studentDataModel.takeAttendance(selectedStudent.getStudent_ID());
        }
        for (Teacher_Student teacherStudent : tableView.getItems()) {
            teacherStudent.getSelected().setSelected(false);
        }

//        System.out.println(select.getColumns().getClass());
        navigation.navigateTo(takePane, navigation.ATTENDANCE_FXML);
    }

    public void selectAll() {
        for (Teacher_Student teacherStudent : tableView.getItems()) {
            teacherStudent.getSelected().setSelected(true);
        }
    }
    public void excelPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Excel File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls"));

        Stage stage = (Stage) takePane.getScene().getWindow();  // Get the current stage

        // Show file chooser dialog and get the selected file
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try (FileInputStream fis = new FileInputStream(selectedFile);
                 Workbook workbook = WorkbookFactory.create(fis)) {
                Sheet sheet = workbook.getSheetAt(0); // Assuming the student IDs are in the first sheet

                // Iterate through the rows and write student IDs to the Excel file
                for (Row row : sheet) {
                    int studentID = getStudentIDFromRow(row); // Replace this with your own logic to retrieve the student ID from the row
                    for (Teacher_Student teacherStudent : tableView.getItems()) {
                        if (teacherStudent.getStudent_ID() == studentID) {
                            teacherStudent.getSelected().setSelected(true);
                            break; // Exit the loop once the matching student is found
                        }
                    }
                    System.out.println("Student ID written: " + studentID); // Print the value of the student ID
                }

                // Save the workbook
                try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                    workbook.write(fos);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Replace this method with your own logic to retrieve the student ID from the row
    private int getStudentIDFromRow(Row row) {
        // Implement your own logic here to extract the student ID from the row
        // For example, if the student ID is stored in the first column (Column A):
        Cell cell = row.getCell(0);
        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        }
        return -1; // Return a default value if the student ID cannot be retrieved
    }

    }


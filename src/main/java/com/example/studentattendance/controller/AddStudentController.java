package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Student;
import com.example.studentattendance.models.StudentDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class AddStudentController {
    @FXML
    private AnchorPane addStudent;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField residenceAreaTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    private Navigation navigation;
    private StudentDataModel studentDataModel;
    private ToggleGroup genderToggleGroup;

    public AddStudentController() {
        navigation = new Navigation();
        studentDataModel = new StudentDataModel();
        genderToggleGroup = new ToggleGroup();
    }

    public void initialize() {
        // Set the toggle group for gender radio buttons
        maleRadioButton.setToggleGroup(genderToggleGroup);
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        // Set the default selected radio button
        maleRadioButton.setSelected(true);

        saveButton.setOnAction(event -> {
            // Retrieve input values
            String studentName = nameTextField.getText();
            String mobileNumber = phoneNumberTextField.getText();
            String residence = residenceAreaTextField.getText();
            String gender = getSelectedGender();

            // Validate input values (e.g., check if required fields are not empty)
            if (studentName.isEmpty() || mobileNumber.isEmpty() || residence.isEmpty()) {
                // Show error message or validation prompt
                return;
            }

            // Generate unique ID based on the selected gender
            int id = generateUniqueID(gender);

            // Create a new Student object
            Student student = new Student(id, studentName, mobileNumber, residence);

            // Add student to the data model
            studentDataModel.addStudent(student);

            // Clear input fields
            clearInputFields();
        });

        backButton.setOnAction(event -> navigation.navigateTo(addStudent, navigation.Student_FXML));
    }

    private void clearInputFields() {
        nameTextField.clear();
        phoneNumberTextField.clear();
        residenceAreaTextField.clear();
    }

    private int generateUniqueID(String gender) {
        int id;
        int minIdRange;
        int maxIdRange;

        if (gender.equalsIgnoreCase("male")) {
            minIdRange = 10000;
            maxIdRange = 20000;
        } else if (gender.equalsIgnoreCase("female")) {
            minIdRange = 20000;
            maxIdRange = 30000;
        } else {
            // Invalid gender, fallback to a default range
            minIdRange = 10000;
            maxIdRange = 30000;
        }

        id = studentDataModel.getNextUniqueID(minIdRange, maxIdRange);

        return id;
    }

    private String getSelectedGender() {
        RadioButton selectedRadioButton = (RadioButton) genderToggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        }
        return null;
    }
}

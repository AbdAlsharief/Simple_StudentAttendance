package com.example.studentattendance;

import com.example.studentattendance.controller.EditAccountController;
import com.example.studentattendance.controller.EditLectureController;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.Lecture;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    private Account selectedAccount;
    public final String LOGIN_FXML = "views/login.fxml";
    public final String ADMIN_FXML = "views/admin.fxml";
    public final String LECTURE_FXML = "views/lecture.fxml";
    public final String ADD_LECTURE_FXML = "views/addLecture.fxml";
    public final String EDIT_LECTURE_FXML = "views/editLecture.fxml";
    public final String TEACHER_FXML = "views/teacher_Student.fxml";
    public final String ACCOUNTS_FXML = "views/accounts.fxml";
    public final String ADD_ACCOUNTS_FXML = "views/addAccount.fxml";

    public final String EDIT_ACCOUNTS_FXML = "views/editAccount.fxml";
    public final String TEACHER_MAIN_FXML = "views/teacherMain.fxml";
    public final String Student_FXML = "views/student.fxml";
    public final String TEACHER__STUDENT_FXML = "views/teacher_Student.fxml";
    public final String ADD_STUDENT_FXML = "views/addStudent.fxml";
    public final String EDIT_TEACHER_STUDENT_FXML = "views/editTeacherStudent.fxml";
    public final String ATTENDANCE_FXML = "views/attendance.fxml";
    public final String TAKE_ATTENDANCE_FXML = "views/takeAttendance.fxml";
    public final String REPORT_FXML = "views/report.fxml";
    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void selectAccountNavigateTo(AnchorPane pane, String fxmlFile, Account selectedAccount) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Access the controller instance
            EditAccountController editAccountController = loader.getController();

            // Pass the selected account to the controller
            editAccountController.setSelectedAccount(selectedAccount);

            // Set the controller as a property of the root node
            root.setUserData(editAccountController);

            pane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void selectLectureNavigateTo(AnchorPane pane, String fxmlFile, Lecture selectedLecture) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Access the controller instance
            EditLectureController editLectureController = loader.getController();

            // Pass the selected account to the controller
            editLectureController.setSelectedLecture(selectedLecture);

            // Set the controller as a property of the root node
            root.setUserData(editLectureController);

            pane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


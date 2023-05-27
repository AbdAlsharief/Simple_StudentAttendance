
    package com.example.studentattendance;

import com.example.studentattendance.models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

    public class Main extends Application {
        String username = "admin";
        String password = "admin";

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("views/login.fxml"));
            Parent root = loader.load();

            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Student Attendance");
            primaryStage.resizableProperty();
            primaryStage.show();

            primaryStage.setOnCloseRequest(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("The program will be closed");
                alert.setHeaderText("Do you want to save the entered data?");
                alert.setContentText(null);

                ButtonType save = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.FINISH);
                ButtonType do_not_save = new ButtonType("Don't Save", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(save, cancel, do_not_save);

                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == save) {
                    AccountDataModel model = new AccountDataModel();
                    LectureDataModel lectureDataModel = new LectureDataModel();
                    StudentDataModel studentDataModel = new StudentDataModel();
                    Teacher_StudentDataModel teacherStudentDataModel =new Teacher_StudentDataModel();
                    studentDataModel.saveStudent();
                    lectureDataModel.saveLecture();
                    teacherStudentDataModel.saveTeacher_Student();
                    model.saveAccounts(); // Assuming you have a saveStudents() method in the DataModel class
                } else if (result.get() == cancel) {
                    event.consume();
                }
            });
        }
    }



package com.example.studentattendance.controller;

import com.example.studentattendance.DataModel;
import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML
    private AnchorPane root;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;
    static String loggedInUsername;

    private Navigation navigation = new Navigation();
    private DataModel.AccountDataModel model = new DataModel.AccountDataModel();

    @FXML
    public void initialize() {
        // Initialize the login screen
    }

    @FXML
    public void handleSubmitButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        loggedInUsername = username;

        Account account = model.getAccountByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            // Successful login
            if (model.isAdmin(account)) {
                navigation.navigateTo(root, navigation.ADMIN_FXML);
            } else {
                navigation.navigateTo(root, navigation.TEACHER_MAIN_FXML);
            }
        } else {
            // Invalid login
            showAlert(AlertType.ERROR, "Invalid Login", "Invalid username or password");
        }
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
   }
}

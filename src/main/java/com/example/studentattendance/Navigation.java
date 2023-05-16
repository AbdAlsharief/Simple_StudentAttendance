package com.example.studentattendance;

import com.example.studentattendance.controller.EditAccountController;
import com.example.studentattendance.models.Account;
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
    public final String TEACHER_FXML = "views/teacher.fxml";
    public final String ACCOUNTS_FXML = "views/accounts.fxml";
    public final String ADD_ACCOUNTS_FXML = "views/addAccount.fxml";
    public final String DELETE_ACCOUNTS_FXML = "views/deleteAccount.fxml";
    public final String EDIT_ACCOUNTS_FXML = "views/editAccount.fxml";

    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void selectNavigateTo(AnchorPane pane, String fxmlFile, Account selectedAccount) {
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

}


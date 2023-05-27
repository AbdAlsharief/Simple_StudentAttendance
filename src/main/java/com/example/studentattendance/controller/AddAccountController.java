package com.example.studentattendance.controller;

import com.example.studentattendance.DataModel;
import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddAccountController implements Initializable {
    @FXML
    private VBox addPane;

    private DataModel.AccountDataModel model;
    private Navigation navigation;

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private CheckBox check ;

    public AddAccountController() {
        model = new DataModel.AccountDataModel();
        navigation = new Navigation();
    }

    public void onAdd() {
        int uniqueNumber;
        if (isCheckboxSelected()) {
            uniqueNumber = generateUniqueNumber(0, 100);
        } else {
            uniqueNumber = generateUniqueNumber(100, 200);
        }

        Account account = new Account(uniqueNumber, username.getText(), password.getText());
        model.addAccount(account);
        model.saveAccounts();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The student has been added successfully");
        alert.setTitle("Done");
        alert.setContentText("Username: " + username.getText() + ", Password: " + password.getText());

        alert.showAndWait();

        clearFields();
    }

    public void onBack() {
        navigation.navigateTo(addPane, navigation.ACCOUNTS_FXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private boolean isCheckboxSelected() {
        if (check.isSelected()) {
            return true;
        }
        return false;
    }

    private void clearFields() {
        username.clear();
        password.clear();
    }
    private int generateUniqueNumber(int min, int max) {
        int uniqueNumber;
        boolean isUnique;

        do {
            uniqueNumber = (int) (Math.random() * (max - min + 1)) + min;
            isUnique = true;

            // Check if the generated number is already used as a code for another account
            DataModel.AccountDataModel accountDataModel = new DataModel.AccountDataModel();
            for (Account account : accountDataModel.getAccounts()) {
                if (account.getCode() == uniqueNumber) {
                    isUnique = false;
                    break;
                }
            }
        } while (!isUnique);

        return uniqueNumber;
    }
}

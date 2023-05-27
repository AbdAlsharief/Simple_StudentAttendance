package com.example.studentattendance.controller;

import com.example.studentattendance.DataModel;
import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EditAccountController {
    @FXML
    private VBox editPane;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    private DataModel.AccountDataModel model;
    private Navigation navigation;
    private Account selectedAccount;

    public EditAccountController() {
        model = new DataModel.AccountDataModel();
        navigation = new Navigation();
    }




    @FXML
    public void initialize() {
        // Set the initial values for the text fields
        if (selectedAccount != null) {
            username.setText(selectedAccount.getUsername());
            password.setText(selectedAccount.getPassword());
        }
    }

    @FXML
    public void onSave() {
        String newUsername = username.getText();
        String newPassword = password.getText();

        if (selectedAccount != null) {
            selectedAccount.setUsername(newUsername);
            selectedAccount.setPassword(newPassword);
            model.saveAccounts();
        }

        navigation.navigateTo(editPane, navigation.ACCOUNTS_FXML);
    }

    @FXML
    public void onBack() {
        navigation.navigateTo(editPane, navigation.ACCOUNTS_FXML);
    }
    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;

        // Use the selectedAccount as needed in the controller
        // For example, update the UI elements with the account details
    }

}

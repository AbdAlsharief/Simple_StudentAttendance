package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;
import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class AccountsController {
    @FXML
    AnchorPane accountPane;
    @FXML
    private TableView<Account> tableView;

    @FXML
    private TableColumn<Account, Integer> codeColumn;

    @FXML
    private TableColumn<Account, String> usernameColumn;

    @FXML
    private TableColumn<Account, String> passwordColumn;
    Navigation navigation = new Navigation();

    AccountDataModel model = new AccountDataModel();

    public AccountsController() {

    }

    @FXML
    public void initialize() {
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableView.setItems(FXCollections.observableArrayList(model.getAccounts()));
        tableView.refresh();
    }

    public void add() {
        navigation.navigateTo(accountPane, navigation.ADD_ACCOUNTS_FXML);
    }

//    public void delete() {
//        navigation.navigateTo(accountPane, navigation.DELETE_ACCOUNTS_FXML);
//    }

    public void back() {
        navigation.navigateTo(accountPane, navigation.ADMIN_FXML);
    }

    public void edit() {
        Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            navigation.selectAccountNavigateTo(accountPane, navigation.EDIT_ACCOUNTS_FXML, selectedAccount);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Account Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an account to edit.");
            alert.showAndWait();
        }
    }

    public void delete() {
        Account selectedAccount = tableView.getSelectionModel().getSelectedItem();
        if (selectedAccount != null) {
            model.removeAccount(selectedAccount);
            tableView.getItems().remove(selectedAccount);
        }
    }
 }


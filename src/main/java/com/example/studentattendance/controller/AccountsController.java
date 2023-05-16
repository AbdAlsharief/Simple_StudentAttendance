package com.example.studentattendance.controller;

import com.example.studentattendance.models.Account;
import com.example.studentattendance.models.AccountDataModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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
}

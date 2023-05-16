//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.studentattendance.controller;

import com.example.studentattendance.Navigation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private  TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label message;
    @FXML
    AnchorPane root;
    Navigation navigation = new Navigation();

    public LoginController() {

    }


    @FXML
    private void initialize() {
        this.submitButton.setOnAction(this::submit);

    }

    @FXML
    public void submit(ActionEvent event) {
        String enteredUsername = this.usernameField.getText();
        String enteredPassword = this.passwordField.getText();

//        if (Account.(enteredUsername, enteredPassword)) {
//            if (Account.isAdmin(enteredUsername)) {
             navigation.navigateTo(root, navigation.ADMIN_FXML);
                }
//            }

//        }
//        public String getUsername(){
//        return String.valueOf(usernameField);
//        }

    }




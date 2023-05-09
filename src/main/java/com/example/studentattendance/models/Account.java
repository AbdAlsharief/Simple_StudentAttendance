package com.example.studentattendance.models;

import java.util.ArrayList;

public class Account {
    private static ArrayList<String> username =new ArrayList<>();
    private static ArrayList<String> password= new ArrayList<>();
    private static ArrayList<String> admin= new ArrayList<>();

    public Account() {
    }
    public Account(String username, String password, Boolean isAdmin) {
        this.username.add(username);
        this.password.add(password);
        if (isAdmin)
            this.admin.add(username);

    }
    public static void addAccount(String username, String password, Boolean isAdmin) {
        Account.username.add(username);
        Account.password.add(password);
        if (isAdmin)
            admin.add(username);
    }
    public static boolean authenticate(String username, String password) {
        int index = Account.username.indexOf(username);
        if (index != -1) {
            String storedPassword = Account.password.get(index);
            return storedPassword.equals(password);
        }
        return false;
    }

    public ArrayList<String> getUsernames() {
        return username;
    }

    public ArrayList<String> getPasswords() {
        return password;
    }

    public ArrayList<String> getAdmins() {
        return admin;
    }

    public static boolean isAdmin(String admin) {
        if (Account.admin.contains(admin)){
            return true;
        }else
            return false;
    }

    public void setUsername(String username) {
        this.username.add(username);

    }

    public void setPassword(String password) {
        this.password .add(password);
    }

    public void setAdmin(String admin) {
        this.admin.add(admin);
    }

}

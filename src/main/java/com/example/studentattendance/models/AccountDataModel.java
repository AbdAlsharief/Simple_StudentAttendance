package com.example.studentattendance.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AccountDataModel {
    private static ArrayList<Account> accounts = null;

    public AccountDataModel() {
        initialize();
    }

    public static void initialize() {
        if (accounts == null) {
            File file = new File("account.csv");
            if (file.exists()) {
                try (Scanner scanner = new Scanner(file)) {
                    // read header line
                    String s = scanner.nextLine();
                    accounts = new ArrayList<>();
                    while (scanner.hasNext()) {
                        s = scanner.nextLine();
                        String[] strings = s.split(",");
                        accounts.add(new Account(
                                Integer.parseInt(strings[0]),
                                strings[1],
                                strings[2]
                        ));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                accounts = new ArrayList<>();
                // add Test data when you run the program for the first time
                accounts.add(new Account(1, "Student1", "aa"));
                accounts.add(new Account(2, "Student2", "bb"));
                accounts.add(new Account(3, "Student3", "cc"));
                accounts.add(new Account(4, "Student4", "dd"));
            }
        }
    }

    public void saveAccounts() {
        try (PrintWriter pw = new PrintWriter("account.csv")) {
            pw.println("code,username,password");
            for (Account account : accounts) {
                pw.println(account.getCode() + "," + account.getUsername() + "," + account.getPassword());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountByUsername(String username) {
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account deleteAccountByUsername(String username) {
        for (Account account : accounts) {
            if (Objects.equals(account.getUsername(), username)) {
                accounts.remove(account);
                return account;
            }
        }
        return null;
    }

    public boolean isAdmin(Account account) {
        return account.getCode() >= 0 && account.getCode() < 100;
    }

    public void updateAccountCode(Account account) {
        if (isAdmin(account)) {
            account.setCode((int) (Math.random() * 100));
        } else {
            account.setCode((int) (Math.random() * 100) + 100);
        }
    }

    public  String getUsernameByCode(int code) {
        for (Account account : accounts) {
            if (account.getCode() >= 100 && account.getCode() < 200 && account.getCode() == code) {
                return account.getUsername();
            }
        }
        return null;
    }

    public int getCodeByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account.getCode();
            }
        }
        return -1;
    }

    public void setUsernameByCode(int code, String newUsername) {
        for (Account account : accounts) {
            if (account.getCode() == code) {
                account.setUsername(newUsername);
                break;
            }
        }
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }
}

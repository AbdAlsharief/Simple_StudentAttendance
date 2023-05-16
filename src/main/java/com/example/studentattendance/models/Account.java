package com.example.studentattendance.models;

import java.io.Serializable;
import java.util.Objects;

public class Account implements Serializable {
    private int code;
    private String username;
    private String password;
    public Account(){

    }

    public Account(int code, String username, String password) {
        this.code = code;
        this.username = username;
        this.password = password;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return code == account.code &&
                Objects.equals(username, account.username) &&
                Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, username, password);
    }

    @Override
    public String toString() {
        return code +","+ username +","+ password ;
    }
}

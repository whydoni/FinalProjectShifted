package com.example.loginactivity.model;

import java.io.Serializable;

public class RegisterModel implements Serializable {

    private String fullname;
    private String accountnumber;
    private String address;
    private String phonenumber;
    private String username;
    private String password;

    public RegisterModel(String fullname, String accountnumber, String address, String phonenumber, String username, String password) {
        this.fullname = fullname;
        this.accountnumber = accountnumber;
        this.address = address;
        this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
}

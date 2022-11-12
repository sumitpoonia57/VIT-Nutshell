package com.example.classroom;

public class storingdata {
    String name,username,password,phonenumber;

    public storingdata() {
    }

    public storingdata(String name, String username, String password, String phonenumber) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}

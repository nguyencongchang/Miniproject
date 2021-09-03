package com.example.appbookroom.entity;

public class User {

    private String username;
    private String password;
    private String jwt;

    public User() {
    }

    public User(String username, String password, String jwt) {
        this.username = username;
        this.password = password;
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isValidEmail() {
        if (username.isEmpty()) return true;
        return false;
    }

    public boolean isValidPassword() {
        if (password.isEmpty()) return true;
        return false;
    }
}
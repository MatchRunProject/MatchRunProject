package com.ygh547.matchrun.model;

import java.util.List;

public class UserRes {

    private String result;
    private String access_token;

    private List<User2> items;

    private String email;

    private String password;

    public List<User2> getItems() {
        return items;
    }

    public void setItems(List<User2> items) {
        this.items = items;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
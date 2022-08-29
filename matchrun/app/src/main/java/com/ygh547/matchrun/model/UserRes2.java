package com.ygh547.matchrun.model;

import java.util.List;

public class UserRes2 {

    private String result;
    private List<User2> items;

    private String email;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
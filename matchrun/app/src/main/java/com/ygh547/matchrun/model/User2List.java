package com.ygh547.matchrun.model;

import java.io.Serializable;
import java.util.List;

public class User2List implements Serializable {

    private String result;
    private int count;
    private List<User2> items;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<User2> getItems() {
        return items;
    }

    public void setItems(List<User2> items) {
        this.items = items;
    }
}

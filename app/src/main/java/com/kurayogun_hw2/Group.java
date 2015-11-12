package com.kurayogun_hw2;

import java.util.ArrayList;

/**
 * Created by kurayogun on 10/11/15.
 */
public class Group {

    private String name;
    private ArrayList<Child> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Child> getItems() {
        return items;
    }

    public void setItems(ArrayList<Child> items) {
        this.items = items;
    }
}

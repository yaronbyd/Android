package com.example.people;

import java.util.ArrayList;

public class User {
    private String phone;
    private ArrayList<Person> friends = new ArrayList<>();

    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Person> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Person> friends) {
        this.friends = friends;
    }
}

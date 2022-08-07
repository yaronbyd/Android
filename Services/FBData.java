package by.yaron;

import java.util.ArrayList;

public class FBData {
    private ArrayList<User> users;

    public FBData() {
    }

    public FBData(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}

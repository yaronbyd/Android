package com.example.people;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataModel {

    static public ArrayList<User> users = new ArrayList<>();
    static public User user = null;

    public static void save()
    {
        FirebaseDatabase.getInstance().getReference("users").setValue(DataModel.users);
    }

    public static void addStartingData()
    {
        User yaron = new User();
        yaron.setPhone("0526589539");
        ArrayList<Person> friendsOfYaron = new ArrayList<>();
        friendsOfYaron.add( new Person("Lital", 36) );
        friendsOfYaron.add( new Person("Ofek", 6) );
        friendsOfYaron.add( new Person("Nave", 2) );
        yaron.setFriends(friendsOfYaron);

        User shimon = new User();
        shimon.setPhone("0526120388");
        ArrayList<Person> friendsOfShimon = new ArrayList<>();
        friendsOfShimon.add( new Person("Yosef", 36) );
        friendsOfShimon.add( new Person("Tali", 10) );
        friendsOfShimon.add( new Person("Dan", 7) );
        shimon.setFriends(friendsOfShimon);

        users.add(yaron);
        users.add(shimon);

    }

}

package com.example.people;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataModel {

    static public ArrayList<Person> people = new ArrayList<>();

    public static void save()
    {
        FirebaseDatabase.getInstance().getReference("people").setValue(DataModel.people);
    }

}

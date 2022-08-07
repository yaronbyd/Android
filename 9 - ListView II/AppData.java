package com.example.appdata;

import java.util.ArrayList;

public class AppData {

    public static ArrayList<Person> people;

    public static void fillData()
    {
        people = new ArrayList<>();
        people.add(new Person("Yaron", 42));
        people.add(new Person("Shai", 52));
        people.add(new Person("Yaniv", 44));
        people.add(new Person("Sagit", 48));
        people.add(new Person("Ronen", 54));
    }

}

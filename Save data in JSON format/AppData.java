package com.example.appdata;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class AppData {

    public static ArrayList<Person> people;

    final static String FILE_NAME = "people.json";

    public static void saveData(Context context)
    {
        Gson gson = new Gson();
        Utils.writeToFile(context,FILE_NAME, gson.toJson(people));
    }

    public static void loadData(Context context)
    {
        String data = Utils.getStringFromFile(context, FILE_NAME);
        Gson gson = new Gson();
        people = gson.fromJson(data, new TypeToken<ArrayList<Person>>(){}.getType());
    }

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

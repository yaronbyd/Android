package by.yaron;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AppData {
    //public static HashMap<String, User> users = new HashMap<>();
    public static User currentUser;
    public static String currentUserPhone;

    public static void createDummyData() {
        Word word1 = new Word("1", "wahad");
        Word word2 = new Word("2", "tnen");
        Word word3 = new Word("3", "talate");
        ArrayList<Word> numbers = new ArrayList<>();
        numbers.add(word1);
        numbers.add(word2);
        numbers.add(word3);
        Chapter chapter1 = new Chapter(numbers, "numbers");

        Word word11 = new Word("tomato", "bandora");
        Word word21 = new Word("cucumber", "khiar");
        Word word31 = new Word("apple", "tufakh");
        ArrayList<Word> food = new ArrayList<>();
        food.add(word11);
        food.add(word21);
        food.add(word31);
        Chapter chapter2 = new Chapter(food, "numbers");

        ArrayList<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter1);
        chapters.add(chapter2);
        User user1 = new User("0526589539", chapters);
        ArrayList<User> users = new ArrayList();
        users.add(user1);

        User user2 = new User("0528386122", new ArrayList<Chapter>());
        users.add(user2);
//        AppData.users.add(users);
        //fbData = new FBData(users);
        saveData();
    }

    public static void saveData()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("users").child(currentUserPhone).setValue(currentUser);
//        DatabaseReference myRef = database.getReference("FBData");
//        myRef.setValue(fbData);

    }

}

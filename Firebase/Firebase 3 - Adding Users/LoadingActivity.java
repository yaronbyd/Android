package com.example.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("users");

        DataModel.addStartingData();
        DataModel.save();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<ArrayList<User>> t = new GenericTypeIndicator<ArrayList<User>>() {};
                ArrayList<User> fbUsers = dataSnapshot.getValue(t);
                DataModel.users.clear();
                DataModel.users.addAll(fbUsers);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("PeopleApp", "Failed to read value.", error.toException());
            }
        });

    }
}

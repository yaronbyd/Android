package com.example.people;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Person> people;
    ListView lvPeople;
    ArrayAdapter<Person> adapter;

    FirebaseDatabase database;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people = new ArrayList<>();

        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, people);
        lvPeople = findViewById(R.id.lvPeople);
        lvPeople.setAdapter(adapter);
        lvPeople.setOnItemClickListener(this);

        database = FirebaseDatabase.getInstance();
        dbRef = database.getReference("people");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<ArrayList<Person>> t = new GenericTypeIndicator<ArrayList<Person>>() {};
                ArrayList<Person> fbPeople = dataSnapshot.getValue(t);
                people.clear();
                people.addAll(fbPeople);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("PeopleApp", "Failed to read value.", error.toException());
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Person person = people.get(position);
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra("NAME", person.getName());
        intent.putExtra("AGE", person.getAge());
        intent.putExtra("ID", position);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            Person person = people.get(data.getIntExtra("ID",-1));
            person.setName(data.getStringExtra("NAME"));
            person.setAge(data.getIntExtra("AGE", 0));

            save();

            adapter.notifyDataSetChanged();
        }
    }

    private void save()
    {
        dbRef.setValue(people);
    }
}

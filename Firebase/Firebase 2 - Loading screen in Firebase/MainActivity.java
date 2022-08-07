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

    ListView lvPeople;
    ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1, DataModel.people);
        lvPeople = findViewById(R.id.lvPeople);
        lvPeople.setAdapter(adapter);
        lvPeople.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Person person = DataModel.people.get(position);
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
            Person person = DataModel.people.get(data.getIntExtra("ID",-1));
            person.setName(data.getStringExtra("NAME"));
            person.setAge(data.getIntExtra("AGE", 0));

            DataModel.save();

            adapter.notifyDataSetChanged();
        }
    }

}

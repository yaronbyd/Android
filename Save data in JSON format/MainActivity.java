package com.example.appdata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayAdapter<Person> adapter;
    ListView lvPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvPeople = findViewById(R.id.lvPeople);

        AppData.loadData(getApplicationContext());
//        AppData.fillData();
        AppData.saveData(getApplicationContext());

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, AppData.people);
        lvPeople.setAdapter(adapter);
        lvPeople.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditPersonActivity.class);
        intent.putExtra("INDEX", position);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
}
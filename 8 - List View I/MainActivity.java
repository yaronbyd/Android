package com.example.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ArrayList<Student> students;    // data
    ListView lvStudents;            // view
    ArrayAdapter<Student> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();

        lvStudents = findViewById(R.id.lvStudents);
        adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(adapter);
        lvStudents.setOnItemClickListener(this);
    }

    public void fillData()
    {
        students = new ArrayList<>();
        students.add(new Student("Yaron", "Ben-Yehuda", 172));
        students.add(new Student("Shimon", "Buskila", 180));
        students.add(new Student("Yosef", "Ben-Davi", 174));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = students.get(position);
        Toast.makeText(this, student.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}

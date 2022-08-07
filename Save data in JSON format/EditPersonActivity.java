package com.example.appdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditPersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etAge;
    Button btnSave;

    int position;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        position = getIntent().getIntExtra("INDEX", -1);
        person = AppData.people.get(position);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        etName.setText(person.getName());
        etAge.setText(Integer.toString(person.getAge()));

        btnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (etName.getText().toString().isEmpty())
            etName.setError("Please enter name");
        else if (etAge.getText().toString().isEmpty())
            etAge.setError("Please enter name");
        else {
            person.setName(etName.getText().toString());
            person.setAge(Integer.parseInt(etAge.getText().toString()));
            AppData.saveData(getApplicationContext());
            Toast.makeText(this, "Person " + person.getName() + " updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
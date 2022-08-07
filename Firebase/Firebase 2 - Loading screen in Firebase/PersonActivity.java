package com.example.people;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etAge;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        etName.setText(getIntent().getStringExtra("NAME"));
        etAge.setText(Integer.toString( getIntent().getIntExtra("AGE",0)));
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String  name = etName.getText().toString();
        String ageText = etAge.getText().toString();

        if (name.isEmpty())
            etName.setError("Enter Name");
        if (ageText.isEmpty())
            etName.setError("Enter Name");

        if (!name.isEmpty() && !ageText.isEmpty())
        {
            Intent intent = new Intent();
            intent.putExtra("ID", getIntent().getIntExtra("ID", -1));
            intent.putExtra("NAME", name);
            intent.putExtra("AGE", Integer.parseInt(ageText));
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}

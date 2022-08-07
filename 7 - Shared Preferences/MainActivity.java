package com.example.sharedprefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etAge;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        String name = sharedpreferences.getString("LAST_NAME", "");
        int age = sharedpreferences.getInt("LAST_AGE", -1);

        if (!name.isEmpty())
            etName.setText(name);

        if (age != -1)
            etAge.setText( Integer.toString(age) );

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int age;
        String name = etName.getText().toString();
        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        if (!etAge.getText().toString().isEmpty())
        {
            age = Integer.parseInt(etAge.getText().toString());
            editor.putInt("LAST_AGE", age);
        }
        editor.putString("LAST_NAME", name);
        editor.commit();

        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
    }
}

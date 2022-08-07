package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSend)
        {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("NAME", etName.getText().toString());
            startActivityForResult(intent, 0);
        }
    }
}

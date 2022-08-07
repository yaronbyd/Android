package com.example.people;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etPhone;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPhone = findViewById(R.id.etPhone);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String phone = etPhone.getText().toString();
        boolean found = false;

        for (int i=0; i<DataModel.users.size(); i++)
        {
            if (DataModel.users.get(i).getPhone().equals(phone))
            {
                DataModel.user = DataModel.users.get(i);
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 0);
                found = true;
            }
        }

        if (!found)
        {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }
    }
}

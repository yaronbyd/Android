package com.example.backtomainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBlue;
    Button btnRed;
    Button btnYellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btnBlue = findViewById(R.id.btnBlue);
        btnRed = findViewById(R.id.btnRed);
        btnYellow = findViewById(R.id.btnYellow);

        btnBlue.setOnClickListener(this);
        btnYellow.setOnClickListener(this);
        btnRed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnBlue)
        {
            Intent intent = new Intent();
            intent.putExtra("COLOR", "Blue");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        if (v == btnYellow)
        {
            Intent intent = new Intent();
            intent.putExtra("COLOR", "Yellow");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        if (v == btnRed)
        {
            Intent intent = new Intent();
            intent.putExtra("COLOR", "Red");
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}

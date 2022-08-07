package com.example.randomnumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etMin;
    EditText etMax;
    Button btnRandom;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMin = findViewById(R.id.etMin);
        etMax = findViewById(R.id.etMax);
        btnRandom = findViewById(R.id.btnRandom);
        tvResult = findViewById(R.id.tvResult);

        btnRandom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnRandom)
        {
            String minStr = etMin.getText().toString();
            String maxStr = etMax.getText().toString();
            if (minStr.equals(""))
                etMin.setError("Enter Number");
            if (maxStr.equals(""))
                etMax.setError("Enter Number");
            if (!minStr.equals("") && !maxStr.equals(""))
            {
                Random rand = new Random();
                // Generate random integers in range 0 to 999
                int min = Integer.parseInt(minStr);
                int max = Integer.parseInt(maxStr);
                int result = rand.nextInt(max-min) + min+1;
                tvResult.setText("Result is: " + result);
            }
        }
    }
}

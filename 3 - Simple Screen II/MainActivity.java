package com.example.multi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber1;
    EditText etNumber2;
    Button btnMulti;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnMulti = findViewById(R.id.btnMulti);
        tvResult = findViewById(R.id.tvResult);

        btnMulti.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String number1Str = etNumber1.getText().toString();
        String number2Str = etNumber2.getText().toString();
        if (number1Str.isEmpty())
            etNumber1.setError("Please enter a number");
        if (number2Str.isEmpty())
            etNumber2.setError("Please enter a number");

        if (!number1Str.isEmpty() && !number2Str.isEmpty())
        {
            int number1 = Integer.parseInt(number1Str);
            int number2 = Integer.parseInt(number2Str);
            int result = number1 * number2;
            String resultStr = Integer.toString(result);
            tvResult.setText(resultStr);
        }
    }
}

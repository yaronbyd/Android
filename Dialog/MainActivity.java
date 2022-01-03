package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvName;
    String name = "John";

    Dialog dialog;
    EditText etName;
    Button btnOK;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvName.setText(name);
        tvName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvName) {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.name_dialog_layout);
            dialog.setTitle("Enter name");
            dialog.setCancelable(true);
            etName = dialog.findViewById(R.id.etName);
            btnOK = dialog.findViewById(R.id.btnOK);
            btnCancel = dialog.findViewById(R.id.btnCancel);
            btnOK.setOnClickListener(this);
            btnCancel.setOnClickListener(this);
            etName.setText(name);
            dialog.show();
        }
        if (v == btnOK)
        {
            name = etName.getText().toString();
            tvName.setText(name);
            dialog.dismiss();
        }
        if (v == btnCancel)
        {
            dialog.dismiss();
        }
    }
}
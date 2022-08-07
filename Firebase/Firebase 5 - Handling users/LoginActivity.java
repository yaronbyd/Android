package by.yaron;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements ValueEventListener, DatabaseReference.CompletionListener {
    EditText etPhone;
    SharedPreferences sp;
    Button btnNewUser;
    Button btnLogin;

    User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPhone = (EditText)findViewById(R.id.etPhone);
        sp = getSharedPreferences("App", MODE_PRIVATE);
        etPhone.setText(sp.getString("UserPhone", ""));

        btnNewUser = findViewById(R.id.btnNewUser);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void newUser(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");
        String phone = etPhone.getText().toString();
        if (phone.equals(""))
        {
            etPhone.setError("Enter Phone");
        }
        else {
            newUser = new User();
            btnNewUser.setText("Creating....");
            btnNewUser.setEnabled(false);
            btnLogin.setEnabled(false);
            etPhone.setEnabled(false);
            newUser.setPhone(phone);
            usersRef.child(phone).setValue(newUser, this);
        }
    }
    @Override
    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
        // code for adding a new user
        AppData.currentUser = newUser;
        AppData.currentUserPhone = etPhone.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "User created successfuly for " + AppData.currentUserPhone, Toast.LENGTH_SHORT).show();
    }
    public void login(View view) {
        String phone = etPhone.getText().toString();
        if (phone.equals(""))
        {
            etPhone.setError("Enter Phone");
        }
        else {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference usersRef = database.getReference("users");
            DatabaseReference child = usersRef.child(phone);
            btnNewUser.setEnabled(false);
            btnLogin.setEnabled(false);
            etPhone.setEnabled(false);
            child.addValueEventListener(this);
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        // user data is coming
        User user = dataSnapshot.getValue(User.class);
        if (user == null)
        {
            Toast.makeText(this, "No user with phone " + dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();

            btnNewUser.setEnabled(true);
            btnLogin.setEnabled(true);
            etPhone.setEnabled(true);
        }
        else
        {
            AppData.currentUser = user;
            AppData.currentUserPhone = etPhone.getText().toString();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

}

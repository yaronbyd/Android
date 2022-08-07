package by.yaron;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {

    boolean loaded = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

//        if (item.getItemId() == R.id.action_create_dummy_data)
//        {
//            AppData.createDummyData();
//
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("FBData");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                    FBData data = dataSnapshot.getValue(FBData.class);
                    AppData.fbData = data;

                    if (!loaded) {
                        loaded = true;
                        // Try login with the last user
                        SharedPreferences sp = getSharedPreferences("App", MODE_PRIVATE);
                        String phone = sp.getString("UserPhone", "");
                        boolean userFound = false;
                        if (!phone.isEmpty()) {
                            ArrayList<User> users = AppData.fbData.getUsers();
                            for (int i = 0; i < users.size() && !userFound; i++) {
                                if (users.get(i).getPhone().equals(phone)) {
                                    userFound = true;
                                    AppData.currentUser = users.get(i);
                                }
                            }
                        }
                        Intent intent;
                        Class activity;
                        if (userFound)
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                        else
                            intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Log.d("LearnWords", "Loaded data");
                    }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("LearnWords", "Failed to read value.", error.toException());
            }
        });
    }

    public void buildDummyData(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

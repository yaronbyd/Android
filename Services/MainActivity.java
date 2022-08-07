package by.yaron;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView lvChapters;

    Dialog dialog;
    EditText dlgChapterName;
    Button dlgCreate;
    ArrayAdapter<Chapter> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvChapters = findViewById(R.id.lvChapters);

        ArrayList<Chapter> chapters = AppData.currentUser.getChapters();
        adapter = new ArrayAdapter<Chapter>(this, android.R.layout.simple_list_item_1, chapters);
        lvChapters.setAdapter(adapter);	// הצגת הנתונים
        lvChapters.setOnItemClickListener(this);

        setTitle("Chapters");

        Intent intent = new Intent(this, CheckUpdatesService.class);
        startService(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, ChapterActivity.class);
        intent.putExtra("ChapterIndex", position);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_logout) {   // Logout
            AppData.currentUser = null;

            SharedPreferences sp = getSharedPreferences("App", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("UserPhone");
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_add_chapter) {   // Add Chapter
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_new_chapter);
            dialog.setTitle("Create Chapter");
            dlgChapterName = dialog.findViewById(R.id.etChapterName);
            dlgCreate = dialog.findViewById(R.id.btnCreate);
            dlgCreate.setOnClickListener(this);
            dialog.show();

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == dlgCreate)
        {
            String chapterName = dlgChapterName.getText().toString();
            if (chapterName.isEmpty())
                dlgChapterName.setError("Enter name");
            else {
                Chapter chapter = new Chapter(new ArrayList(), chapterName);
                AppData.currentUser.getChapters().add(chapter);
                adapter.notifyDataSetChanged();

                dialog.dismiss();
                Intent intent = new Intent(this, ChapterActivity.class);
                intent.putExtra("ChapterIndex", AppData.currentUser.getChapters().size()-1);
                startActivity(intent);
            }
        }
    }
}

package com.example.customlistview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    ListView lvMovies;
    ArrayAdapter<Movie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        fillData();

        lvMovies = findViewById(R.id.lvMovies);
        adapter = new MovieAdapter(this);
        lvMovies.setAdapter(adapter);
    }

    void fillData()
    {
        movies = new ArrayList<>();
        movies.add(new Movie("The Matrix Resurrections", "Lana Wachowski", R.drawable.matrix));
        movies.add(new Movie("SPIDER-MAN: No Way Home", "Jon Watts", R.drawable.spiderman));
        movies.add(new Movie("Don't look up", "Adam McKay", R.drawable.dont));
        movies.add(new Movie("The King's Man", "Mathew Vaughn", R.drawable.kingsman));
    }

    class MovieAdapter extends ArrayAdapter
    {
        public MovieAdapter(@NonNull Context context) {
            super(context, 0, movies);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.moview, parent, false);
            TextView tvName = view.findViewById(R.id.tvName);
            TextView tvDirector = view.findViewById(R.id.tvDirector);
            ImageView ivImage = view.findViewById(R.id.ivImage);

            Movie movie = movies.get(position);
            tvName.setText(movie.getName());
            tvDirector.setText("Director:" + movie.getDirector());
            ivImage.setImageResource(movie.getMovieResource());
            return view;
        }
    }
}
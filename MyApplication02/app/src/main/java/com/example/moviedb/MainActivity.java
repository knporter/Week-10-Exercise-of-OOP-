package com.example.moviedb;

// MainActivity.java
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView movieRecyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieRecyclerView = findViewById(R.id.movie_recycler_view);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this)); // 必须设置 LayoutManager！

        loadMovieData();
    }

    private void loadMovieData() {
        List<Movie> movies = JsonUtils.loadMoviesFromJson(this);
        Log.d("MovieDebug", "Loaded movies count: " + (movies != null ? movies.size() : 0));

        if (movies == null || movies.isEmpty()) {
            showError("No movies found or error loading data");
            return;
        }

        if (movies == null || movies.isEmpty()) {
            showError("No movies found or error loading data");
            return;
        }

        if (adapter == null) {
            adapter = new MovieAdapter(movies);
            movieRecyclerView.setAdapter(adapter);
        } else {
            adapter.updateMovies(movies);
        }
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
package com.example.moviedb;
// MovieAdapter.java
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
        Log.d("MovieDebug", "Adapter movies count: " + movies.size());
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public void updateMovies(List<Movie> newMovies) {
        this.movies = newMovies;
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView yearTextView;
        private TextView genreTextView;
        private ImageView posterImageView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.movie_title);
            yearTextView = itemView.findViewById(R.id.movie_year);
            genreTextView = itemView.findViewById(R.id.movie_genre);
            posterImageView = itemView.findViewById(R.id.movie_poster);
        }

        public void bind(Movie movie) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(String.valueOf(movie.getYear()));
            genreTextView.setText(movie.getGenre());

            // In a real app, you'd load the actual image here
            // For this example, we'll just set a placeholder
            posterImageView.setImageResource(R.drawable.default_poster);
        }
    }
}
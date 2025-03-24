package com.example.moviedb;

// JsonUtils.java
import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();

        try {
            // 从 res/raw 加载 movies.json
            InputStream is = context.getResources().openRawResource(R.raw.movies);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            String jsonString = builder.toString();
            JSONArray moviesArray = new JSONArray(jsonString);

            for (int i = 0; i < moviesArray.length(); i++) {
                try {
                    JSONObject movieObj = moviesArray.getJSONObject(i);
                    String title = movieObj.getString("title");
                    Integer year = movieObj.getInt("year");
                    String genre = movieObj.optString("genre", "Unknown");
                    String poster = movieObj.optString("poster", "default_poster");

                    movies.add(new Movie(title, year, genre, poster));
                } catch (JSONException | IllegalArgumentException e) {
                    Log.e(TAG, "Error parsing movie data (position " + i + "): " + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to load JSON file", e);
        }

        return movies;
    }
}
package com.example.moviedb;

import java.util.Calendar;

// Movie.java
public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    public Movie(String title, Integer year, String genre, String posterResource) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;

        if (year == null || year < 1888 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Invalid year");
        }
        this.year = year;

        this.genre = (genre == null || genre.trim().isEmpty()) ? "Unknown Genre" : genre;
        this.posterResource = (posterResource == null || posterResource.trim().isEmpty()) ? "default_poster" : posterResource;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterResource() {
        return posterResource;
    }
}
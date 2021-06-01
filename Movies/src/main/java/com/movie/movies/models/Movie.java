package com.movie.movies.models;

public class Movie {

    private String name;
    private String movieId;
    private String description;

    public Movie(String movieId, String name, String description) {
        this.movieId = movieId;
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

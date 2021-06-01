package com.movie.movies.models.info;

import java.util.ArrayList;
import java.util.List;

public class GenreList {

    private ArrayList<Genre> genres;


    public GenreList(){
        this.genres = new ArrayList<>();
    }
    public GenreList(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<Genre> getGenres() { return genres; }
    public void setGenres(ArrayList<Genre> genres) { this.genres = genres; }
}

package com.movie.movies.models.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoviesList {

    private List<Movies> results;
    private List<Movies> cast;

    public MoviesList (){

    }

    public List<Movies> getResults() { return results; }

    public List<Movies> getCast() { return cast; }
}

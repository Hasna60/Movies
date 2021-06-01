package com.movie.movies.models.info;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {

    private String overview;
    private String id;
    private String title;
    private String poster_path;
    private String release_date;
    private String fav_path;
    private int [] genre_ids;

    private String genre;

    public Movies() { }

    public String getDesc() {
        return "http://localhost:8080/desc/"+ title + "/" + getPoster_path1() + "/"
                + getOverview() + "/" + getGenre() + "/" + getId();
    }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500"+ poster_path;
    }
    public String getPoster_path1() { return poster_path; }
    public void setPoster_path(String poster_path) { this.poster_path = poster_path; }

    public String getRelease_date() { return release_date; }
    public void setRelease_date(String release_date) { this.release_date = release_date; }

    public void setFav(String fav_path) { this.fav_path = fav_path; }
    public void setFav_path(String fav_path) { this.fav_path = fav_path; }
    public String getFav() {
        return "http://localhost:8080/fav/"+ title + '/' + poster_path;
    }
    public String getFav_path() { return "http://localhost:8080/fav/"+ title + '/' + poster_path; }

    public int[] getGenre_ids() { return genre_ids; }
    public void setGenre_ids(int[] genre_ids) { this.genre_ids = genre_ids; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}

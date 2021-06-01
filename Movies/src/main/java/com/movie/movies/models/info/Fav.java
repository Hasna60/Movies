package com.movie.movies.models.info;


import javax.persistence.*;

@Entity
@Table(name = "fav")
public class Fav {

    @Id
    public int id;
    public String name;

    public String poster;

    public Fav(String name, String poster) {
        this.name = name;
        this.poster = poster;
    }

    public Fav() { }

    public String deleteFav() {return "http://localhost:8080/delete/"+ name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getPoster() {
        return "https://image.tmdb.org/t/p/w500/"+ poster;
    }
    public void setPoster(String poster) { this.poster = poster; }
}

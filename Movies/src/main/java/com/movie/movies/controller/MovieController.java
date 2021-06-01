package com.movie.movies.controller;

import com.movie.movies.models.info.GenreList;
import com.movie.movies.models.info.Movies;
import com.movie.movies.models.info.MoviesList;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MovieController {

    private String apiKey;

    public MovieController(@Value("${api.key}") String apiKey) {
        this.apiKey = apiKey;
    }

    @GetMapping("/home")
    public String run(RestTemplate restTemplate, Model model) throws Exception {

        try {

            MoviesList Movie = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/movie/upcoming?api_key=" + apiKey, MoviesList.class);

            GenreList Genre = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey + "&language=en-US", GenreList.class);


            for (int i = 0; i < Movie.getResults().size(); i++) {
                if (Movie.getResults().get(i).getPoster_path() == null) {
                    Movie.getResults().get(i).setPoster_path("https://previews.123rf.com/images/pazhyna/pazhyna1709/pazhyna170900030/85934183-cinema-movie-time-banner-.jpg");
                }

                String total = "";
                for (int j = 0; j < Movie.getResults().get(i).getGenre_ids().length; j++) {
                    for (int c = 0; c < Genre.getGenres().size(); c++) {
                        if (Movie.getResults().get(i).getGenre_ids()[j] == Genre.getGenres().get(c).getId()) {
                            total += Genre.getGenres().get(c).getName() + ", ";
                        }
                    }
                }

                Movie.getResults().get(i).setGenre(total);
            }

            model.addAttribute("movies", Movie.getResults());

        } catch (NotReadablePropertyException e) { }
        return "movies";
    }

    @PostMapping("/home")
    public String run(@ModelAttribute("movies") Movies movies, RestTemplate restTemplate, Model model) throws Exception {

        try {
            MoviesList Movie = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=the", MoviesList.class);

            for (int i = 0; i < Movie.getResults().size(); i++) {
                if (Movie.getResults().get(i).getPoster_path() == null) {
                    Movie.getResults().get(i).setPoster_path("https://previews.123rf.com/images/pazhyna/pazhyna1709/pazhyna170900030/85934183-cinema-movie-time-banner-.jpg");
                }
            }

            model.addAttribute("movies", Movie.getResults());

        } catch (NotReadablePropertyException e) {

            System.out.println(e.getMessage() + "");
        }


        return "movies";
    }

    @GetMapping("/desc/{movieName}/{poster}/{overview}/{genre}/{id}")
    public String name(Model model, @PathVariable("movieName") String movieName, @PathVariable("poster") String poster, @PathVariable("overview") String overview, @PathVariable("genre") String genres, @PathVariable("id") String id, RestTemplate restTemplate) {

        Movies movie = new Movies();
        movie.setTitle(movieName);
        movie.setPoster_path("/" + poster);
        movie.setOverview(overview);
        movie.setGenre(genres);
        movie.setId(id);
        model.addAttribute("movies", movie);

        MoviesList Movie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/" + id + "/similar?api_key=" + apiKey + "", MoviesList.class);

        GenreList Genre = restTemplate.getForObject(
                "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey + "&language=en-US", GenreList.class);


        for (int i = 0; i < Movie.getResults().size(); i++) {
            if (Movie.getResults().get(i).getPoster_path() == null) {
                Movie.getResults().get(i).setPoster_path("https://previews.123rf.com/images/pazhyna/pazhyna1709/pazhyna170900030/85934183-cinema-movie-time-banner-.jpg");
            }

            String total = "";
            for (int j = 0; j < Movie.getResults().get(i).getGenre_ids().length; j++) {
                for (int c = 0; c < Genre.getGenres().size(); c++) {
                    if (Movie.getResults().get(i).getGenre_ids()[j] == Genre.getGenres().get(c).getId()) {
                        total += Genre.getGenres().get(c).getName() + ", ";
                    }
                }
            }

            Movie.getResults().get(i).setGenre(total);

        }

        model.addAttribute("MovieList", Movie.getResults());
        return "description";
    }

    @GetMapping("/search/{movieName}")
    public String name(Model model, @PathVariable("movieName") String movieName, RestTemplate restTemplate) {

        try {

            MoviesList Movie = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + movieName, MoviesList.class);

            GenreList Genre = restTemplate.getForObject(
                    "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey + "&language=en-US", GenreList.class);


            for (int i = 0; i < Movie.getResults().size(); i++) {
                if (Movie.getResults().get(i).getPoster_path() == null) {
                    Movie.getResults().get(i).setPoster_path("https://previews.123rf.com/images/pazhyna/pazhyna1709/pazhyna170900030/85934183-cinema-movie-time-banner-.jpg");
                }

                String total = "";
                for (int j = 0; j < Movie.getResults().get(i).getGenre_ids().length; j++) {
                    for (int c = 0; c < Genre.getGenres().size(); c++) {
                        if (Movie.getResults().get(i).getGenre_ids()[j] == Genre.getGenres().get(c).getId()) {
                            total += Genre.getGenres().get(c).getName() + ", ";
                        }
                    }
                }

                Movie.getResults().get(i).setGenre(total);

            }
            model.addAttribute("movies", Movie.getResults());

        } catch (NotReadablePropertyException e) {

            System.out.println(e.getMessage() + "");
        }


        return "movies";
    }
}
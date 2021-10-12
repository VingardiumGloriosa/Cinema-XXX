package com.example.cinemaproject.dtos;

import com.example.cinemaproject.entities.Cinema;
import com.example.cinemaproject.entities.Movie;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    int id;
    String title;
    String ratings;
    String actors;
    Integer year;
    String genre;
    String description;
    Integer length;

    public MovieDTO(String title, String ratings, String actors, Integer year, String genre, String description, Integer length){
        this.title = title;
        this.ratings = ratings;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }

    public MovieDTO(Movie movie){
        this.title = movie.getTitle();
        this.ratings = movie.getRatings();
        this.actors = movie.getActors();
        this.year = movie.getYear();
        this.genre = movie.getGenre();
        this.description = movie.getDescription();
        this.length = movie.getLength();
        this.id = movie.getId();
    }

    public static List<MovieDTO> MovieDTOSfromMovie(Iterable<Movie> movies){
        List<MovieDTO> dtos = StreamSupport.stream(movies.spliterator(), false)
                .map(movie -> new MovieDTO(movie))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Movie movieFromMovieDTO(MovieDTO movie){
        return new Movie(movie.getTitle(),movie.getRatings(), movie.getActors(), movie.year, movie.getGenre(), movie.getDescription(), movie.getLength());
    }

}

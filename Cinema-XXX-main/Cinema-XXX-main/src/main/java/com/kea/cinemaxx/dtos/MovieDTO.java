package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    int movieId;
    String title;
    int rating;
    String actors;
    Integer year;
    String genre;
    String description;
    Integer length;

    public MovieDTO(String title, int rating, String actors, int year, String genre, String description, int length){
        this.title = title;
        this.rating = rating;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }

    public MovieDTO(Movie movie){
        this.title = movie.getTitle();
        this.rating = movie.getRating();
        this.actors = movie.getActors();
        this.year = movie.getYear();
        this.genre = movie.getGenre();
        this.description = movie.getDescription();
        this.length = movie.getLength();
        this.movieId = movie.getMovieId();
    }

    public static List<MovieDTO> MovieDTOSfromMovie(Iterable<Movie> movies){
        List<MovieDTO> dtos = StreamSupport.stream(movies.spliterator(), false)
                .map(movie -> new MovieDTO(movie))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Movie movieFromMovieDTO(MovieDTO movie){
        return new Movie(movie.getTitle(),movie.getRating(), movie.getActors(), movie.getYear(), movie.getGenre(), movie.getDescription(), movie.getLength());
    }

}

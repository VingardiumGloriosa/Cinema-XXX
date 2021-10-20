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

    String movieId;
    String title;
    String rating;
    String actors;
    String year;
    String genre;
    String description;
    String length;
    String trailer;
    String poster;
    String images;

    public MovieDTO(Movie movie){
        this.title = movie.getTitle();
        this.rating = movie.getRating();
        this.actors = movie.getActors();
        this.year = movie.getYear();
        this.genre = movie.getGenre();
        this.description = movie.getDescription();
        this.length = movie.getLength();
        this.movieId = movie.getMovieId();
        this.poster=movie.getPoster();
        this.trailer=movie.getTrailer();
        this.images=movie.getImages();
    }


    public static List<MovieDTO> MovieDTOSfromMovie(Iterable<Movie> movies){
        List<MovieDTO> dtos = StreamSupport.stream(movies.spliterator(), false)
                .map(movie -> new MovieDTO(movie))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Movie movieFromMovieDTO(MovieDTO movie){
        return new Movie(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getRating(),
                movie.getActors(),
                movie.getYear(),
                movie.getGenre(),
                movie.getDescription(),
                movie.getLength(),
                movie.getTrailer(),
                movie.getPoster(),
                movie.getImages());
    }

    public static Movie movieFromMovieDTO(String movieId, String title){
        return new Movie(movieId,title);
    }

}

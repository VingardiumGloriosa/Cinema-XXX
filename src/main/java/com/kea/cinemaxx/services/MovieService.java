package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.dtos.MovieDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.repositiories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getMovies() {
        return MovieDTO.MovieDTOSfromMovie(movieRepository.findAll());
    }

    public MovieDTO getMovie(int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        return new MovieDTO(movie);
    }

    // We don't need the following for now but we'll need it next week :D
    //    List<Movie> findMovieByRating(int rating);
    //    List<Movie> findMovieByYear(int year);
    //    List<Movie> findMovieByActors(String actors);
    //    List<Movie> findMovieByTitle(String title);
    //    List<Movie> findMovieByGenre(String genre);

}

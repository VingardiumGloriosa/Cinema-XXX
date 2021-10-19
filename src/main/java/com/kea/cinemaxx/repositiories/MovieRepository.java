package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findMovieByRating(int rating);
    List<Movie> findMovieByYear(int year);
    List<Movie> findMovieByActors(String actors);
    List<Movie> findMoviesByTitle(String title);
    Movie findMovieByTitle(String title);
    List<Movie> findMovieByGenre(String genre);
    // findMovieByScreenings??
    List<Movie> findAll();

}

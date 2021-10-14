package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.MovieDTO;
import com.kea.cinemaxx.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping("/{movieId}")
    MovieDTO getMovie(@PathVariable int movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping
    List<MovieDTO> getMoviesByTitle(@RequestParam String title) {
        return movieService.getMoviesByTitle(title);
    }

}

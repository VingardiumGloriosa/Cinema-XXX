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

    @GetMapping("/id/{movieId}")
    MovieDTO getMovie(@PathVariable int movieId) {
        return movieService.getMovie(movieId);
    }

    @GetMapping("/title/{title}")
    List<MovieDTO> getMoviesByTitle(@PathVariable String title) {
        return movieService.getMoviesByTitle(title);
    }

    @GetMapping
    List<MovieDTO> getAllMovies(){ return movieService.getMovies();}

    @PostMapping
    MovieDTO addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }

    @PutMapping("/{id}")
    MovieDTO editMovie(@RequestBody MovieDTO movieToEdit,@PathVariable int id ) throws Exception {
        return movieService.editMovie(movieToEdit,id);
    }
}

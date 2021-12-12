package com.kea.cinemaxx.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kea.cinemaxx.dtos.MovieDTO;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.repositiories.MovieRepository;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class MovieService {

    MovieRepository movieRepository;
    ScreeningService screeningService;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getMovies() {
        return MovieDTO.MovieDTOSfromMovie(movieRepository.findAll());
    }

    public MovieDTO getMovie(String movieId){
        List<MovieDTO> temp = getMovies();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getMovieId().equals(movieId)){
               return temp.get(i);}
        }
         return null;
    }

    public boolean getMoviesByTitle(String title){
        List<MovieDTO> temp = getMovies();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getTitle().equals(title)){
                return true;}
        }
        return false;
    }

    public MovieDTO getMovieByTitle(String title){
        List<MovieDTO> temp = getMovies();
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getTitle().equals(title)){
                return temp.get(i);}
        }
        return null;
    }

    public MovieDTO addMovie(MovieDTO newMovie){
        Movie movieToMake = MovieDTO.movieFromMovieDTO(newMovie);
        return new MovieDTO(movieRepository.save(movieToMake));
    }

    public MovieDTO addMovie(String movieId, String title){
        Movie movieToMake = MovieDTO.movieFromMovieDTO(movieId,title);
        return new MovieDTO(movieRepository.save(movieToMake));
    }

    public MovieDTO editMovie(MovieDTO movieDTO, String id){
        Movie movieOriginal = movieRepository.findById(id).orElseThrow();
        movieOriginal.setActors(movieDTO.getActors());
        movieOriginal.setDescription(movieDTO.getDescription());
        movieOriginal.setGenre(movieDTO.getGenre());
        movieOriginal.setLength(movieDTO.getLength());
        movieOriginal.setRating(movieDTO.getRating());
        movieOriginal.setYear(movieDTO.getYear());
        movieOriginal.setTitle(movieDTO.getTitle());
        return new MovieDTO(movieRepository.save(movieOriginal));
    }

    public MovieDTO editMovieDescription(String id, String description){
        Movie movieOriginal = movieRepository.findById(id).orElseThrow();
        movieOriginal.setDescription(description);
        return new MovieDTO(movieRepository.save(movieOriginal));
    }

    public MovieDTO editMovieRating(String id, String rating){
        Movie movieOriginal = movieRepository.findById(id).orElseThrow();
        movieOriginal.setRating(rating);
        return new MovieDTO(movieRepository.save(movieOriginal));
    }

    // We don't need the following for now but we'll need it next week :D
    //    List<Movie> findMovieByRating(int rating);
    //    List<Movie> findMovieByYear(int year);
    //    List<Movie> findMovieByActors(String actors);
    //    List<Movie> findMovieByTitle(String title);
    //    List<Movie> findMovieByGenre(String genre);
  
/*
    public void deleteMovie(String id){
        screeningService.deleteScreeningByMovieTitle(getMovie(id).getTitle());
        movieRepository.deleteById(id);
    }*/

}

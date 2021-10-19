package com.kea.cinemaxx.rest;
import com.google.gson.*;
import com.kea.cinemaxx.dtos.MovieDTO;
import com.kea.cinemaxx.services.MovieService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    MovieService movieService;

    String host = "https://movie-database-imdb-alternative.p.rapidapi.com/";
    String charset = "UTF-8";
    // Headers for a request
    String x_rapidapi_host = "movie-database-imdb-alternative.p.rapidapi.com";
    String x_rapidapi_key = "c492d9313emsh7aba9bd79001238p116d38jsna1b61b7d9747";

    public MovieController(MovieService movieService){this.movieService = movieService;}

    @GetMapping("/id/{movieId}")
    String getMovie(@PathVariable String movieId) throws UnsupportedEncodingException, UnirestException {
        if(movieService.getMovie(movieId)){
            // Params
            // Host, charset and headers vars should be the same
            // Format query for preventing encoding problems
            String query = String.format("i=%s",
                    URLEncoder.encode(movieId, charset));
            // Json response
            HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                    .header("x-rapidapi-host", x_rapidapi_host)
                    .header("x-rapidapi-key", x_rapidapi_key)
                    .asJson();
            //Prettifying
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(response.getBody().toString());
            je.getAsJsonObject().addProperty("Trailer and images","https://www.imdb.com/title/"+movieId);
            return  gson.toJson(je);
        }
        return null;
    }

    @GetMapping("/title/{title}")
    String getMoviesByTitle(@PathVariable String title) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://movie-database-imdb-alternative.p.rapidapi.com/?s="+title+"&r=json&page=1")
                .header("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .header("x-rapidapi-key", "c492d9313emsh7aba9bd79001238p116d38jsna1b61b7d9747")
                .asString();
        //Prettifying
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response.getBody());
        return gson.toJson(je);
    }

    @GetMapping
    List<MovieDTO> getAllMovies(){ return movieService.getMovies();}
/*
    @DeleteMapping("/delete/{id}")
    void deleteMovie(@PathVariable int id){movieService.deleteMovie(id);}

    @DeleteMapping("/delete/{title}")
    void deleteMovieByTitle(@PathVariable String title){movieService.deleteMovieByTitle(title);}

    @PostMapping
    MovieDTO addMovie(@RequestBody MovieDTO movieDTO){
        return movieService.addMovie(movieDTO);
    }
*/
    @PostMapping("addById/{movieId}")
    MovieDTO addMovieById(@PathVariable String movieId) throws UnsupportedEncodingException, UnirestException {
        String query = String.format("i=%s",
                URLEncoder.encode(movieId, charset));
        // Json response
        HttpResponse<JsonNode> response = Unirest.get(host + "?" + query)
                .header("x-rapidapi-host", x_rapidapi_host)
                .header("x-rapidapi-key", x_rapidapi_key)
                .asJson();
        HttpResponse<JsonNode> response2 = Unirest.get("https://imdb-api.com/API/Images/k_b5xul4h1/"+movieId)
                .asJson();
        HttpResponse<JsonNode> response3 = Unirest.get("https://imdb-api.com/API/Trailer/k_b5xul4h1/"+movieId)
                .asJson();
        System.out.println(response2.getBody().getObject().get("items").toString());
        MovieDTO temporary = new MovieDTO(
                response.getBody().getObject().get("imdbID").toString(),
                response.getBody().getObject().get("Title").toString(),
                response.getBody().getObject().get("Rated").toString(),
                response.getBody().getObject().get("Actors").toString(),
                response.getBody().getObject().get("Year").toString(),
                response.getBody().getObject().get("Genre").toString(),
                response.getBody().getObject().get("Plot").toString(),
                response.getBody().getObject().get("Runtime").toString(),
                response3.getBody().getObject().get("link").toString(),
                response.getBody().getObject().get("Poster").toString(),
                response2.getBody().getObject().get("items").toString()
        );
        movieService.addMovie(temporary);
        return temporary;
    }

    @PutMapping("/{id}")
    MovieDTO editMovie(@RequestBody MovieDTO movieToEdit,@PathVariable int id ) throws Exception {
        return movieService.editMovie(movieToEdit,id);
    }
}

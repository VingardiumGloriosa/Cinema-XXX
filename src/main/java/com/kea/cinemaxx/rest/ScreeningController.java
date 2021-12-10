package com.kea.cinemaxx.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.services.ScreeningService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/screenings")
public class ScreeningController {

    ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/get")
    @ResponseBody
    List<ScreeningDTO> getScreenings(@RequestParam (required = false) String date1,
                                     @RequestParam (required = false) String date2,
                                     @RequestParam (required = false) String cinemaName,
                                     @RequestParam (required = false) String movieName) {

        // the url should be like
        // ...api/screenings/options?date1=2021-12-08&date2=2021-12-09&cinemaName=Empire%&movieName=Dune

        return screeningService.getScreenings(date1,date2,cinemaName,movieName);

    }

    @GetMapping("/get-by-id/{id}")
    ScreeningDTO getScreening(@PathVariable int id) {
        return screeningService.getScreening(id);
    }

    @PostMapping
    ScreeningDTO addScreening(/* @RequestBody ScreeningDTO newScreening */
                                @RequestParam String date,
                                @RequestParam String time,
                                @RequestParam String movieId,
                                @RequestParam int hallId
                                ) {
        return screeningService.addScreening(date,time,movieId,hallId);
    }

    @PutMapping("/edit/{screeningId}")
    ScreeningDTO editScreening(@RequestParam int screeningId,
                               @RequestParam String date,
                               @RequestParam String time,
                               @RequestParam String movieId,
                               @RequestParam int hallId
    ) throws Exception {
        return screeningService.editScreening(screeningId, date, time, movieId, hallId);
    }

    @DeleteMapping("/delete-by-id/{screeningId}")
    void deleteScreeningById(@PathVariable int screeningId){
        screeningService.deleteScreening(screeningId);
    }

    // not working because "unsafe" :(
    @DeleteMapping("/delete-by-movie")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteScreeningByMovie(@RequestParam String movieName) {
        screeningService.deleteScreeningByMovieTitle(movieName);
    }

}



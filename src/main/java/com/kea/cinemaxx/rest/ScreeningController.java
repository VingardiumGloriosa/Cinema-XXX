package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.services.ScreeningService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/screenings")
public class ScreeningController {

    ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/options")
    @ResponseBody
    List<ScreeningDTO> getScreenings(@RequestParam (required = false) String date1,
                                     @RequestParam (required = false) String date2,
                                     @RequestParam (required = false) String cinemaName,
                                     @RequestParam (required = false) String movieName) {

        // the url should be .../options?date1=10-12-2000&date2=10-02-2000&cinemaName=Barbara&movieName=Dune

        return screeningService.getScreenings(date1,date2,cinemaName,movieName);

    }

}



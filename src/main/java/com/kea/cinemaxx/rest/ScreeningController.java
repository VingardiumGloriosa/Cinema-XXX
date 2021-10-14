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

//    @GetMapping("/options")
//    @ResponseBody
//    List<ScreeningDTO> getScreenings(@RequestParam (required = false) String date,
//                                     @RequestParam (required = false) String weekNumber,
//                                     @RequestParam (required = false) String cinemaName,
//                                     @RequestParam (required = false) String movieName) {
//
//        // the url should be .../options?date=10-12-2000&cinemaName=Barbara&movieName=Dune
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
//
//        if (date!=null) {
//            LocalDate d = LocalDate.parse(date, formatter);
//            return screeningService.getDayScreenings(d,cinemaName,movieName);
//        }
//        else if (weekNumber!=null) {
//            int week = Integer.parseInt(weekNumber);
//            return screeningService.getWeekScreenings(week,cinemaName,movieName);
//        }
//        else {
//            return screeningService.getScreenings(cinemaName, movieName);
//        }
//
//    }



}



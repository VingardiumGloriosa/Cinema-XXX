package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.dtos.HallDTO;
import com.kea.cinemaxx.services.HallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/halls")
public class HallController {

    HallService hallService;

    public HallController(HallService hallService){this.hallService = hallService;}

    @GetMapping
    List<HallDTO> getHalls(@RequestParam(required = false) int hallId){

        return hallService.getHalls(hallId);
    }

    @GetMapping("/{hallId}") //not sure if it can be the same as in cinemas
    HallDTO getHall(@PathVariable int hallId){
        return hallService.getHall(hallId);
    }

}

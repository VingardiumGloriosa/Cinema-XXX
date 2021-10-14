package com.kea.cinemaxx.rest;

public class CinemaController {

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.services.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cinemas")
public class CinemaController {

    CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService){this.cinemaService = cinemaService;}

    @GetMapping
    List<CinemaDTO> getCinemas(@RequestParam(required = false) int zipCode,
                               @RequestParam(required = false) String name){
//            if(zipCode == 0 && name != null){
//                //We will eventually handle this better
//                throw new IllegalArgumentException("make is required when name is supplied");
//            }
            return cinemaService.getCinemas(zipCode,name);
        }

    @GetMapping("/{cinemaId}")
    CinemaDTO getCinema(@PathVariable int cinemaId){
        return cinemaService.getCinema(cinemaId);
    }

}

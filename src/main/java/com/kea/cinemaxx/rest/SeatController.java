package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.SeatDTO;
import com.kea.cinemaxx.services.SeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/seats")
public class SeatController {

    SeatService seatService;

    public SeatController(SeatService seatService){this.seatService = seatService;}

    @GetMapping("/{id}")
    SeatDTO getSeat(@PathVariable int seatId) {

        return seatService.getSeat(seatId);
    }

    // get list of free seats (Patrik)
}

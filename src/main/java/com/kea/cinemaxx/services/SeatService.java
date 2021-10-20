package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.SeatDTO;
import com.kea.cinemaxx.entities.Seat;
import com.kea.cinemaxx.repositiories.SeatRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public SeatDTO getSeat(int seatId){
        Seat seat = seatRepository.findById(seatId).orElseThrow();
        return  new SeatDTO(seat);
    }

    // get list of free seats (Patrik)

    public void updateSeat() {

    }

}

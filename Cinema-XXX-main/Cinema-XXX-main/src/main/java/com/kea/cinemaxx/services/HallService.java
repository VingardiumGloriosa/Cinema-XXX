package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.dtos.HallDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.repositiories.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<HallDTO> getHalls(int hallId) {
        try { return HallDTO.HallDTOSfromHall(hallRepository.findHallByHallId(hallId)); }
        catch(Exception e) { return HallDTO.HallDTOSfromHall(hallRepository.findAll()); }
    }

    public HallDTO getHall(int hallId) {
        Hall hall = hallRepository.findById(hallId).orElseThrow();
        return new HallDTO(hall);
    }

}

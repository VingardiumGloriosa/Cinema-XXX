package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.repositiories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;

    public ScreeningService (ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

//    public List<ScreeningDTO> getScreenings(LocalDate date, boolean fetchWeek, String cinemaName, String movieName) {
//
//        if (date.equals(LocalDate.now()) && fetchWeek && cinemaName!=null && movieName!=null ) {
//            ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll());
//        }
//
//      this is not completed, we need to change the entities a little bit.
//
//    }


}

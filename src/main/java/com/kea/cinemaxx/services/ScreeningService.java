package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.repositiories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;

    public ScreeningService (ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;

    }

//    public List<ScreeningDTO> getScreenings(String cinemaName, String movieName) {
//
//        if(cinemaName!=null && movieName!=null) {
//
//            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll());
//        }
//        else { return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll()); }
//    }
//
//    public List<ScreeningDTO> getDayScreenings(LocalDate date, String cinemaName, String movieName) {
//
//        if() {}
//        else { return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll()); }
//
//    }
//
//    public List<ScreeningDTO> getWeekScreenings(int weekNumber, String cinemaName, String movieName) {
//
//        // week number pattern: w
//
//        if() {}
//        else { return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll()); }
//
//    }


}

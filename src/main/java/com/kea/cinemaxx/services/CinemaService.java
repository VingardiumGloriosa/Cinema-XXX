package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.repositiories.CinemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public List<CinemaDTO> getCinemas() {
        return CinemaDTO.CinemaDTOSfromCinema(cinemaRepository.findAll());
    }

    public List<CinemaDTO> getCinemas(int zipCode, String name) {
        if(name!=null && zipCode!=0) {
            return CinemaDTO.CinemaDTOSfromCinema(cinemaRepository.findCinemaByZipCodeAndName(zipCode,name));
        }
        else if(name==null && zipCode!=0) {
            return CinemaDTO.CinemaDTOSfromCinema(cinemaRepository.findCinemaByZipCode(zipCode));
        }
        else if(name!=null && zipCode==0) {
            return CinemaDTO.CinemaDTOSfromCinema(cinemaRepository.findCinemasByName(name));
        }
        else return CinemaDTO.CinemaDTOSfromCinema(cinemaRepository.findAll());
    }

    public CinemaDTO getCinema(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow();
        return new CinemaDTO(cinema);
    }

    // future additions:
        // add cinema()
        // edit cinema()
        // delete cinema()

}

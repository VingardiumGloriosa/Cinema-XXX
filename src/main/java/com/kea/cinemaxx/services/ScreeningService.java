package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.repositiories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;

    public ScreeningService (ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<ScreeningDTO> getScreenings(String date1, String date2, String cinemaName, String movieName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy"); // week number pattern: w // for the future
        Cinema cinema = new Cinema();
        Movie movie = new Movie();

        // if all the parameters are given
        if (date1!=null && date2!=null && cinemaName!=null && movieName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            cinema.setName(cinemaName);
            movie.setTitle(movieName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndMovieAndCinema(d1,d2,movie,cinema));

        }

        // only one date and cinema and movie
        else if (date1!=null && date2==null && cinemaName!=null && movieName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            cinema.setName(cinemaName);
            movie.setTitle(movieName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndMovieAndCinema(d,movie,cinema));

        }

        // only cinema and movie
        else if (date1==null && cinemaName!=null && movieName!=null) {
            cinema.setName(cinemaName);
            movie.setTitle(movieName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinemaAndMovie(cinema,movie));
        }

        // only cinema
        else if (movieName==null && cinemaName!=null) {
            cinema.setName(cinemaName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinema(cinema));
        }

        // only movie
        else if (movieName!=null) {
            movie.setTitle(movieName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByMovie(movie));
        }

        // no params specified
        else { return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll()); }

    }

}

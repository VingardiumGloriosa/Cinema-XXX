package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.repositiories.CinemaRepository;
import com.kea.cinemaxx.repositiories.MovieRepository;
import com.kea.cinemaxx.repositiories.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;
    CinemaRepository cinemaRepository;

    public ScreeningService (ScreeningRepository screeningRepository, MovieRepository movieRepository, CinemaRepository cinemaRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public List<ScreeningDTO> getScreenings(String date1, String date2, String cinemaName, String movieName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy"); // week number pattern: w // for the future

        Cinema cinema = new Cinema();
        Movie movie = new Movie();

        if (cinemaName!=null) {
            for (Cinema c : cinemaRepository.findCinemaByName(cinemaName)) {
                if (c.getName().equals(cinemaName)) { cinema = c; break; }
            }
        }

        if (movieName!=null) {
            for (Movie m : movieRepository.findMovieByTitle(movieName)) {
                if (m.getTitle().equals(movieName)) { movie = m; break; }
            }
        }

        // if all the parameters are given
        if (date1!=null && date2!=null && cinemaName!=null && movieName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndMovieAndCinema(d1,d2,movie,cinema));
        }

        // start date + end date + movie
        else if (date1!=null && date2!=null && cinemaName==null && movieName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndMovie(d1,d2,movie));
        }

        // start date + end date + cinema
        else if (date1!=null && date2!=null && cinemaName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndCinema(d1,d2,cinema));
        }

        // only one date and cinema and movie
        else if (date1!=null && date2==null && cinemaName!=null && movieName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndMovieAndCinema(d,movie,cinema));
        }

        // only one date and cinema
        else if (date1!=null && date2==null && cinemaName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndCinema(d,cinema));
        }

        // only one date and movie
        else if (date1!=null && date2==null && movieName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndMovie(d,movie));
        }

        // only cinema and movie
        else if (date1==null && cinemaName!=null && movieName!=null) {
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinemaAndMovie(cinema,movie));
        }

        // only cinema
        else if (movieName==null && cinemaName!=null) {
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinema(cinema));
        }

        // only movie
        else if (movieName!=null) {
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByMovie(movie));
        }

        // no params specified
        else { return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll()); }

    }

    public ScreeningDTO getScreening(int screeningId) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow();
        return new ScreeningDTO(screening);
    }

    public ScreeningDTO addScreening(ScreeningDTO newScreening) {
        Screening screeningToMake = ScreeningDTO.screeningFromScreeningDTO(newScreening);
        return new ScreeningDTO(screeningRepository.save(screeningToMake));
    }

    public ScreeningDTO editScreening(ScreeningDTO screeningToEdit, int screeningId) {
        Screening screeningOrg = screeningRepository.findById(screeningId).orElseThrow();
        screeningOrg.setCinema(screeningToEdit.getCinema());
        screeningOrg.setHall(screeningToEdit.getHall());
        screeningOrg.setTime(screeningToEdit.getTime());
        screeningOrg.setDate(screeningToEdit.getDate());
        screeningOrg.setMovie(screeningToEdit.getMovie());
        return new ScreeningDTO(screeningRepository.save(screeningOrg));
    }

    public void deleteScreening(int screeningId) {
        //Make 404 when screening is not found
        screeningRepository.deleteById(screeningId);
    }

    public void deleteScreeningByMovie(String movie){
        for (ScreeningDTO screening : getScreenings(null,null,null,movie)){
            screeningRepository.deleteById(screening.getScreeningId());
        }
    }

}

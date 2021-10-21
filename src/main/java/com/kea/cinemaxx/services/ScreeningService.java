package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.CinemaDTO;
import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.dtos.SeatDTO;
import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.entities.*;
import com.kea.cinemaxx.repositiories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScreeningService {

    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;
    CinemaRepository cinemaRepository;
    TicketRepository ticketRepository;
    UserRepository userRepository;
    HallRepository hallRepository;
    SeatRepository seatRepository;

    public ScreeningService (ScreeningRepository screeningRepository, MovieRepository movieRepository, CinemaRepository cinemaRepository, TicketRepository ticketRepository, UserRepository userRepository, HallRepository hallRepository, SeatRepository seatRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.hallRepository = hallRepository;
        this.seatRepository = seatRepository;
    }

    public List<ScreeningDTO> getScreenings(String date1, String date2, String cinemaName, String movieName) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // week number pattern: w // for the future

        Cinema cinema;
        Movie movie;

        // if all the parameters are given
        if (date1!=null && date2!=null && cinemaName!=null && movieName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            movie = movieRepository.findMovieByTitle(movieName);
            System.out.println("all params");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndMovieAndCinema(d1,d2,movie,cinema));
        }

        // start date + end date + movie
        else if (date1!=null && date2!=null && movieName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            movie = movieRepository.findMovieByTitle(movieName);
            System.out.println("movie between dates");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndMovie(d1,d2,movie));
        }

        // start date + end date + cinema
        else if (date1!=null && date2!=null && cinemaName!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            System.out.println("cinema between dates");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetweenAndCinema(d1,d2,cinema));
        }

        // only one date and cinema and movie
        else if (date1!=null && cinemaName!=null && movieName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            movie = movieRepository.findMovieByTitle(movieName);
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndMovieAndCinema(d,movie,cinema));
        }

        // only one date and cinema
        else if (date1!=null && cinemaName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            System.out.println("cinema and date");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndCinema(d,cinema));
        }

        // only one date and movie
        else if (date1!=null && movieName!=null) {
            LocalDate d = LocalDate.parse(date1, formatter);
            movie = movieRepository.findMovieByTitle(movieName);
            System.out.println("movie and date");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateAndMovie(d,movie));
        }

        // date range
        else if (date1!=null && date2!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            LocalDate d2 = LocalDate.parse(date2, formatter);
            System.out.println("dates between");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDateBetween(d1,d2));
        }

        // one date
        else if (date1!=null) {
            LocalDate d1 = LocalDate.parse(date1, formatter);
            System.out.println("one date");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByDate(d1));
        }

        // only cinema and movie
        else if (cinemaName!=null && movieName!=null) {
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            movie = movieRepository.findMovieByTitle(movieName);
            System.out.println("cinema and movie");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinemaAndMovie(cinema,movie));
        }

        // only cinema
        else if (cinemaName!=null) {
            cinema = cinemaRepository.findCinemaByName(cinemaName);
            System.out.println("cinema");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByCinema(cinema));
        }

        // only movie
        else if (movieName!=null) {
            movie = movieRepository.findMovieByTitle(movieName);
            System.out.println("movie");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findScreeningByMovie(movie));
        }

        // no params specified
        else {
            System.out.println("get all");
            return ScreeningDTO.ScreeningDTOSfromScreening(screeningRepository.findAll());
        }

    }

    public ScreeningDTO getScreening(int screeningId) {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow();
        return new ScreeningDTO(screening);
    }

    public ScreeningDTO addScreening(String date, String time, String movieId, int hallId) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME; // "12:30:30"

        // parsing the input data
        LocalDate screeningDate = LocalDate.parse(date,dateFormatter);
        LocalTime screeningTime = LocalTime.parse(time, timeFormatter);
        Movie screeningMovie = movieRepository.findById(movieId).orElseThrow();
        Hall screeningHall = hallRepository.findById(hallId).orElseThrow();
        Cinema screeningCinema = screeningHall.getCinema();

        Screening newScreening = new Screening(screeningTime, screeningDate,screeningMovie,screeningHall,screeningCinema);

        List<Seat> screeningSeats = newScreening.getHall().getSeats();
        List<Ticket> screeningTickets = new ArrayList<>();
        User admin = userRepository.findByAdminIsTrue().get(0);

        ScreeningDTO screening = new ScreeningDTO(screeningRepository.save(newScreening));

        // generating new tickets for the new screening
        for (Seat seat: screeningSeats) {
            SeatDTO seatDTO = new SeatDTO(seatRepository.save(seat));
            Ticket ticket = new Ticket(false, admin, seat, newScreening);
            screeningTickets.add(ticket);
            TicketDTO ticketDTO = new TicketDTO(ticketRepository.save(ticket));
        }

        newScreening.setTickets(screeningTickets);
//        return new ScreeningDTO(screeningRepository.save(newScreening));

        return screening;
    }

    public ScreeningDTO editScreening(int screeningId, String date, String time, String movieId, int hallId) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME; // "12:30:30"

        Screening screeningOrg = screeningRepository.findById(screeningId).orElseThrow();
        screeningOrg.setCinema(hallRepository.findById(hallId).orElseThrow().getCinema());
        screeningOrg.setHall(hallRepository.findById(hallId).orElseThrow());
        screeningOrg.setTime(LocalTime.parse(time, timeFormatter));
        screeningOrg.setDate(LocalDate.parse(date,dateFormatter));
        screeningOrg.setMovie(movieRepository.findById(movieId).orElseThrow());

        return new ScreeningDTO(screeningRepository.save(screeningOrg));
    }

    public void deleteScreening(int screeningId) {
        screeningRepository.deleteById(screeningId);
    }

    public void deleteScreeningByMovieTitle(String movieName){
        Movie movie = movieRepository.findMovieByTitle(movieName);
//        for (ScreeningDTO screening : getScreenings(null,null,null,movieName)){  //I think getScreenings() can't be used here
            screeningRepository.deleteByMovie(movie);
//        }
    }

}

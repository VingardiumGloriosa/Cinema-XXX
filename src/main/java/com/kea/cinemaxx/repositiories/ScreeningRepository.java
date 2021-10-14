package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    List<Screening> findScreeningByDate(LocalDate date);

    List<Screening> findScreeningByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Screening> findScreeningByHall(Hall hall);
    List<Screening> findScreeningByMovie(Movie movie);
    List<Screening> findScreeningByCinema(Cinema cinema);
    List<Screening> findScreeningByCinemaAndMovie(Cinema cinema, Movie movie);
    List<Screening> findScreeningByDateAndCinema(LocalDate date, Cinema Cinema);
    List<Screening> findScreeningByDateAndMovieAndCinema(LocalDate date, Movie movie, Cinema cinema);
    List<Screening> findScreeningByDateBetweenAndMovieAndCinema(LocalDate startDate, LocalDate endDate, Movie movie, Cinema cinema);
    List<Screening> findAll();

    //    List<Screening> findScreeningByWeek(int weekNumber);
    //    List<Screening> findScreeningByWeekAndCinema(int weekNumber, String cinemaName);
    //    List<Screening> findScreeningByWeekAndMovieAndCinema(int weekNumber, int movieId, int cinemaId);

}

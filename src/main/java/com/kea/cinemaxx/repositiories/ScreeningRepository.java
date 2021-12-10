package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    List<Screening> findScreeningByDate(LocalDate date);
    List<Screening> findScreeningByDateAndMovie(LocalDate date, Movie movie);
    List<Screening> findScreeningByDateAndCinema(LocalDate date, Cinema cinema);
    List<Screening> findScreeningByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Screening> findScreeningByDateBetweenAndMovie(LocalDate startDate, LocalDate endDate, Movie movie);
    List<Screening> findScreeningByDateBetweenAndCinema(LocalDate startDate, LocalDate endDate, Cinema cinema);
    List<Screening> findScreeningByHall(Hall hall);
    List<Screening> findScreeningByMovie(Movie movie);
    List<Screening> findScreeningByCinema(Cinema cinema);
    List<Screening> findScreeningByCinemaAndMovie(Cinema cinema, Movie movie);
    List<Screening> findScreeningByDateAndMovieAndCinema(LocalDate date, Movie movie, Cinema cinema);
    List<Screening> findScreeningByDateBetweenAndMovieAndCinema(LocalDate startDate, LocalDate endDate, Movie movie, Cinema cinema);
//    List<Screening> findAll();

    void deleteByMovie(Movie movie);

    //    List<Screening> findScreeningByWeek(int weekNumber);
    //    List<Screening> findScreeningByWeekAndCinema(int weekNumber, String cinemaName);
    //    List<Screening> findScreeningByWeekAndMovieAndCinema(int weekNumber, int movieId, int cinemaId);

}

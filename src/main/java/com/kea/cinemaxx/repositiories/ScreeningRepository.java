package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Screening;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    List<Screening> findScreeningByDate(LocalDate date);
    List<Screening> findScreeningByWeek(int weekNumber); // I think we should use this for efficiency this week
    List<Screening> findScreeningByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Screening> findScreeningByHallId(int hallId); // (screenings.hallId -> halls.cinemaId -> cinemas.name)
    List<Screening> findScreeningByMovieId(int movieId);

    List<Screening> findScreeningByDateAndHallId(LocalDate date, int cinemaId);
    List<Screening> findScreeningByWeekAndCinema(int weekNumber, String cinemaName);
    List<Screening> findScreeningByWeekAndMovieAndCinema(int weekNumber, String movieName, String cinemaName);
    List<Screening> findScreeningByDateAndMovieAndCinema(LocalDate date, String movieName, String cinemaName);
    List<Screening> findAll();


}

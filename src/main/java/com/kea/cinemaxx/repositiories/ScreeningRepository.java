package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Screening;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {

    List<Screening> findScreeningByDate(LocalDate date);
    List<Screening> findScreeningByDateBetween(LocalDate startDate, LocalDate endDate); //And  movie theater in question
    List<Screening> findScreeningByCinema(String cinemaName); // (screenings.hallId -> halls.cinemaId -> cinemas.name)
    List<Screening> findScreeningByMovie(String movieName); //these have to be added as entities

}

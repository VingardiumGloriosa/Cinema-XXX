package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Screening;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    Screening findScreeningByDate(LocalDate date);
    Screening findScreeningByDateBetween(LocalDate date); //And  movie theater in question
}

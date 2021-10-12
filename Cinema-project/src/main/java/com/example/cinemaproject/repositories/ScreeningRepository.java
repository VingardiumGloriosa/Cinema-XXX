package com.example.cinemaproject.repositories;

import com.example.cinemaproject.entities.Screening;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
    Screening findScreeningByDate(LocalDate date);
    Screening findScreeningByDateBetween(LocalDate date); //And  movie theater in question
}

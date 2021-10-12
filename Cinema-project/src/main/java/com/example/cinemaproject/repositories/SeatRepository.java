package com.example.cinemaproject.repositories;

import com.example.cinemaproject.entities.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
}

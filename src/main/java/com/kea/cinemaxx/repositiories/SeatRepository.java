package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Seat;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
}
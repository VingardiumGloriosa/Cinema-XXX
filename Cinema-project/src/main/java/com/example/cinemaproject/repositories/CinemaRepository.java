package com.example.cinemaproject.repositories;

import com.example.cinemaproject.entities.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema, Integer> {
}

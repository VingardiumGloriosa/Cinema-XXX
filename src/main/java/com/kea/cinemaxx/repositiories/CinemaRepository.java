package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import org.springframework.data.repository.CrudRepository;

public interface CinemaRepository extends CrudRepository<Cinema,Integer> {
}

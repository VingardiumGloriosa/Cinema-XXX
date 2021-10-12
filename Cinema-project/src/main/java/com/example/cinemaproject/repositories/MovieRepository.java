package com.example.cinemaproject.repositories;

import com.example.cinemaproject.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}

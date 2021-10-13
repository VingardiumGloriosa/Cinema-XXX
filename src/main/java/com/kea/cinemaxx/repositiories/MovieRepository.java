package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {
}

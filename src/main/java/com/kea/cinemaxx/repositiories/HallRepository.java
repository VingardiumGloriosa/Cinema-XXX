package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Hall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HallRepository extends CrudRepository<Hall, Integer> {

    List<Hall> findHallByCinemaID (int cinemaID);

}

package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema,Integer> {

    List<Cinema> findCinemaByZipCode(int zipCode);
    List<Cinema> findCinemaByName(String name);
    List<Cinema> findCinemaByZipCodeAndName(int zipCode, String name);
    List<Cinema> findAll();

}

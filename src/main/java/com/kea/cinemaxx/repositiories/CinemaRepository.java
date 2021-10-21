package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema,Integer> {

    List<Cinema> findCinemaByZipCode(int zipCode);
    List<Cinema> findCinemasByName(String name);
    Cinema findCinemaByName(String name);
    List<Cinema> findCinemaByZipCodeAndName(int zipCode, String name);
    List<Cinema> findAll();


}

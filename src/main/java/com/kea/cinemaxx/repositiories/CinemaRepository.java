package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Cinema;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CinemaRepository extends CrudRepository<Cinema,Integer> {

    List<Cinema> findCinemaByZipCode(int zipCode);
    List<Cinema> findCinemaByName(String name);

}


// example: List<Car> findCarByBrand(String brand);
//    List<Car> findCarByBrandAndModel(String brand, String model);
//    List<Car> findCarByPricePrDayLessThan(double pricePrDay);
//    List<Car> findCarByPricePrDayLessThanEqual(double pricePrDay);
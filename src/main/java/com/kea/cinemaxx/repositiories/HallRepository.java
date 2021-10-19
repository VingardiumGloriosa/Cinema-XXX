package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends CrudRepository<Hall, Integer> {

    List<Hall> findHallByHallId (int hallId);
    List<Hall> findAll();

}

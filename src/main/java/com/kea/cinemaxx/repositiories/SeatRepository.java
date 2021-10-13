package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Seat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, Integer> {

    //Again, they probably need to be entities but I'm not sure what the best practice is
    List<Seat> findSeatByHall(int hallId);
    List<Seat> findSeatByHallandReserved(int hallId, boolean reserved);
    List<Seat> findSeatByRowandHall(int hallId, int row);
    List<Seat> findSeatByColumnandHall(int hallId, char column);

}
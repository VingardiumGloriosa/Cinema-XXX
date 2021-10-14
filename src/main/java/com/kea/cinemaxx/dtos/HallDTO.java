package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HallDTO {

    int hallId;
    int numberOfSeats;
    Cinema cinema;

    public HallDTO(int numberOfSeats){
        this.numberOfSeats = numberOfSeats;
    }

    public HallDTO(Hall hall){
        this.numberOfSeats = getNumberOfSeats();
        this.hallId = hall.getHallId();
    }

    public static List<HallDTO> HallDTOSfromHall(Iterable<Hall> halls){
        List<HallDTO> dtos = StreamSupport.stream(halls.spliterator(), false)
                .map(hall -> new HallDTO(hall))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Hall hallFromHallDTO(HallDTO hall){
        return new Hall(hall.getNumberOfSeats(), hall.getCinema(), hall.getHallId());
    }


}

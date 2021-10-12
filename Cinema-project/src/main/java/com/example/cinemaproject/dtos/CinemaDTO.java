package com.example.cinemaproject.dtos;

import com.example.cinemaproject.entities.Cinema;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaDTO {

    int id;
    String name;
    String address;

    public CinemaDTO(String name, String address){
        this.name = name;
        this.address = address;
    }

    public CinemaDTO(Cinema cinema){
        this.name = cinema.getName();
        this.address = cinema.getAddress();
        this.id = cinema.getId();
    }

    public static List<CinemaDTO> CinemaDTOSfromCinema(Iterable<Cinema> cinemas){
        List<CinemaDTO> dtos = StreamSupport.stream(cinemas.spliterator(), false)
                .map(cinema -> new CinemaDTO(cinema))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Cinema cinemaFromCinemaDTO(CinemaDTO cinema){
        return new Cinema(cinema.getName(),cinema.getAddress());
    }

    //in Car demo there is a different version for member and car DTOs, where memeber oen also has a version without ID
}



package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Cinema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CinemaDTO {

    int cinemaId;
    int numOfHalls;
    String address;
    int zipCode;
    String name;

    public CinemaDTO(int numOfHalls, String address, int zipCode, String name) {
        this.numOfHalls = numOfHalls;
        this.address = address;
        this.zipCode = zipCode;
        this.name = name;
    }

    public CinemaDTO(Cinema cinema){
        this.numOfHalls = cinema.getNumOfHalls();
        this.address = cinema.getAddress();
        this.zipCode = cinema.getZipCode();
        this.name = cinema.getName();
        this.cinemaId = cinema.getCinemaId();
    }

    public static List<CinemaDTO> CinemaDTOSfromCinema(Iterable<Cinema> cinemas){
        List<CinemaDTO> dtos = StreamSupport.stream(cinemas.spliterator(), false)
                .map(cinema -> new CinemaDTO(cinema))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Cinema cinemaFromCinemaDTO(CinemaDTO cinema){
        return new Cinema(cinema.getNumOfHalls(), cinema.getAddress(), cinema.getZipCode(), cinema.getName());
    }

    //in Car demo there is a different version for member and car DTOs, where memeber oen also has a version without ID

}

package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class ScreeningDTO {

    int screeningId;
    LocalTime time;
    LocalDate date; //should set date format? @JsonFormat(pattern = "dd-MM-YYYY")
    Movie movie;
    Hall hall;
    Cinema cinema;

    public ScreeningDTO(LocalTime time,  LocalDate date, int movie, int hall){
        this.time = time;
        this.date = date;

        // shouldn't this have the movie, hall and cinema too? i'm not sure
    }

    public ScreeningDTO(Screening screening){
        this.time = screening.getTime();
        this.date = screening.getDate();
        this.screeningId = screening.getScreeningId();
    }

    public static List<ScreeningDTO> ScreeningDTOSfromScreening(Iterable<Screening> screenings){
        List<ScreeningDTO> dtos = StreamSupport.stream(screenings.spliterator(), false)
                .map(screening -> new ScreeningDTO(screening))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Screening screeningFromScreeningDTO(ScreeningDTO screening){
        return new Screening(screening.getTime(),screening.getDate(), screening.getMovie(), screening.getHall(), screening.getCinema());
    }

}

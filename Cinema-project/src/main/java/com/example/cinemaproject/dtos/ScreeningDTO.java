package com.example.cinemaproject.dtos;

import com.example.cinemaproject.entities.Cinema;
import com.example.cinemaproject.entities.Screening;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreeningDTO {

    int id;
    LocalTime time;
    LocalDate date; //should set date format? @JsonFormat(pattern = "dd-MM-YYYY")
    String movie;

    public ScreeningDTO(LocalTime time,  LocalDate date, String movie){
        this.time = time;
        this.date = date;
        this.movie = movie;
    }

    public ScreeningDTO(Screening screening){
        this.time = screening.getTime();
        this.date = screening.getDate();
        this.movie = screening.getMovie();
        this.id = screening.getId();
    }

    public static List<ScreeningDTO> ScreeningDTOSfromScreening(Iterable<Screening> screenings){
        List<ScreeningDTO> dtos = StreamSupport.stream(screenings.spliterator(), false)
                .map(screening -> new ScreeningDTO(screening))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Screening screeningFromScreeningDTO(ScreeningDTO screening){
        return new Screening(screening.getTime(),screening.getDate(),screening.getMovie());
    }

}

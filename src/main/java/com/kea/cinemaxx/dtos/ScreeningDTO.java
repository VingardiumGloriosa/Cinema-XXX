package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    int movieId;
    int hallId;

    public ScreeningDTO(LocalTime time,  LocalDate date, int movieId, int hallId){
        this.time = time;
        this.date = date;
        this.movieId = movieId;
        this.hallId = hallId;
    }

    public ScreeningDTO(Screening screening){
        this.time = screening.getTime();
        this.date = screening.getDate();
        this.movieId = screening.getMovieId();
        this.hallId = screening.getHallId();
        this.screeningId = screening.getScreeningId();
    }

    public static List<ScreeningDTO> ScreeningDTOSfromScreening(Iterable<Screening> screenings){
        List<ScreeningDTO> dtos = StreamSupport.stream(screenings.spliterator(), false)
                .map(screening -> new ScreeningDTO(screening))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Screening screeningFromScreeningDTO(ScreeningDTO screening){
        return new Screening(screening.getTime(),screening.getDate(),screening.getMovieId(), screening.getHallId());
    }

}

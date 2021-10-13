package com.kea.cinemaxx.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int screeningId;

    @Column(length = 60,nullable = false)
    LocalTime time;

    @Column(length = 60,nullable = false)
    LocalDate date;

    @Column(length = 60,nullable = false) //is this needed or is this supposed to be from entity movie?
    int movieId;

    @Column(length = 60, nullable = false)
    int hallId;

    public Screening(){}

    public  Screening(LocalTime time, LocalDate date, int movieId, int hallId){
        this.time = time;
        this.date = date;
        this.movieId = movieId;
        this.hallId = hallId;
    }

}

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

//    @Column(length = 60,nullable = false) //is this needed or is this supposed to be from entity movie?
//    int movieId;
//
//    @Column(length = 60, nullable = false)
//    int hallId;

    @ManyToOne
    @JoinColumn(name="hallId", nullable=false)
    Hall hall;

    @ManyToOne
    @JoinColumn(name="movieId", nullable=false)
    Movie movie;

    @ManyToOne
    @JoinColumn(name="cinemaId", nullable = false)
    Cinema cinema;

    public Screening(){}

    public  Screening(LocalTime time, LocalDate date, Movie movie, Hall hall, Cinema cinema){
        this.time = time;
        this.date = date;
        this.hall = hall;
        this.movie = movie;
        this.cinema = cinema;
    }


}

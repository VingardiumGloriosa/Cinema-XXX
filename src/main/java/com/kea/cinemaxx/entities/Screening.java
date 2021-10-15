package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
@Table(name = "screening")
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
//    @Column(length = 60, nullable = false)
//    int hallId;

    @ManyToOne
    @JoinColumn(name="hallId", nullable=false)
    @JsonBackReference
    Hall hall;

    // JoinColumn might be causing us the problems we have with the screening.
    // All the problems we're having are because the halls and cinemas keep being fetched on a loop.

    @ManyToOne
    @JoinColumn(name="movieId", nullable=false)
    @JsonBackReference
    Movie movie;

    @ManyToOne
    @JoinColumn(name="cinemaId", nullable = false)
    @JsonBackReference
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

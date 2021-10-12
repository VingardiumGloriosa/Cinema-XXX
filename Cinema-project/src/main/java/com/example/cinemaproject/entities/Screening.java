package com.example.cinemaproject.entities;

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
    int id;

    @Column(length = 60,nullable = false)
    LocalTime time;

    @Column(length = 60,nullable = false) //added
    LocalDate date;

    @Column(length = 60,nullable = false) //is this needed or is this supposed to be from entity movie?
    String movie;

    public  Screening(LocalTime time, LocalDate date, String movie){
        this.time = time;
        this.date = date;
        this.movie = movie;
    }

    public Screening(){}
}

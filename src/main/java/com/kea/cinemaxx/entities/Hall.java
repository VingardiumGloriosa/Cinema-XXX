package com.kea.cinemaxx.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int hallId;

    @Column(length = 4,nullable = false)
    int numberOfSeats;

    @Column(length = 60, nullable = false)
    int cinemaId;

    public Hall(){}

    public Hall(int numberOfSeats, int cinemaId) {
        this.numberOfSeats = numberOfSeats;
        this.cinemaId = cinemaId;
    }
}

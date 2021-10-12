package com.example.cinemaproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 60,nullable = false)
    int numberOfSeats;


    public  Hall(int numberOfSeats){
        this.numberOfSeats = numberOfSeats;
    }

    public Hall(){}
}

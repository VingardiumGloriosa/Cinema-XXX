package com.example.cinemaproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 60,nullable = false)
    String reservationName;

    @Column(length = 60,nullable = false)
    String reservationEmail;

    public  Ticket(String reservationName, String reservationEmail){
        this.reservationName = reservationName;
        this.reservationEmail = reservationEmail;
    }

    public Ticket(){}

}

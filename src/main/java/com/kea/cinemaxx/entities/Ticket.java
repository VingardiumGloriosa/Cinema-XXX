package com.kea.cinemaxx.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ticketId;

    @Column(length = 60,nullable = false)
    String reservationName;

    @Column(length = 60,nullable = false)
    String reservationEmail;

    //they need to be entities

    @Column
    int seatId;

    @Column
    int screeningId;

    public Ticket(){}

    public Ticket(String reservationName, String reservationEmail, int seatId, int screeningId) {
        this.reservationName = reservationName;
        this.reservationEmail = reservationEmail;
        this.seatId = seatId;
        this.screeningId = screeningId;
    }

}

package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import com.kea.cinemaxx.entities.Seat;
import com.kea.cinemaxx.entities.Screening;


import javax.persistence.*;

@Entity
@Getter @Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ticketId;

    boolean purchased;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    Seat seat;

    @ManyToOne
    @JoinColumn(name = "screening_id", nullable = false)
    Screening screening;

    public Ticket(){}

    public Ticket(boolean purchased, User user, Seat seat, Screening screening) {
        this.purchased = purchased;
        this.user = user;
        this.seat = seat;
        this.screening = screening;
    }

}

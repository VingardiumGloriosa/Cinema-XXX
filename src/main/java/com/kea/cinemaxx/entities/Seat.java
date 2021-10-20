package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int seatId;

    @Column
    int seatRow;

    @Column
    char seatColumn;

    @Column
    boolean reserved = false; //if ticket.purchased=true set this to true

    //orphanRemoval will delete any tickets for this seat when the seat is deleted
    @OneToOne (orphanRemoval = true, mappedBy = "seat", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference ("ticketForSeat")
    Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    @JsonBackReference
    Hall hall;

    public Seat(){}

    public  Seat(int seatRow, char seatColumn, boolean reserved, Hall hall){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.reserved = reserved;
        this.hall = hall;
    }

}

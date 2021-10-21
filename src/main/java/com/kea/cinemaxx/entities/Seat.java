package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    //orphanRemoval will delete any tickets for this seat when the seat is deleted
    @OneToMany (orphanRemoval = true, mappedBy = "seat", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonIgnore
    List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    Hall hall;

    public Seat(){}

    public  Seat(int seatRow, char seatColumn, Hall hall){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.hall = hall;
    }

}

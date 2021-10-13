package com.kea.cinemaxx.entities;

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
    int row;

    @Column
    char column;

    @Column
    boolean reserved = false;

    public Seat(){}

    public  Seat(int row, char column, boolean reserved){
        this.row = row;
        this.column = column;
        this.reserved = reserved;
    }

}

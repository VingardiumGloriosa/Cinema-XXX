package com.kea.cinemaxx.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Seat {

    @Id
    @Column//do we have to do this differently with two ids? is it generated?
    int row;

    @Id
    @Column
    int column;

    @Column
    boolean reserved = false;

    public  Seat(int row, int column, boolean reserved){
        this.row = row;
        this.column = column;
        this.reserved = reserved;
    }

    public Seat(){}

}
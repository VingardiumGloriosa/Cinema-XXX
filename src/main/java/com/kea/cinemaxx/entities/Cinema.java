package com.kea.cinemaxx.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 60,nullable = false)
    String name;

    @Column(length = 60,nullable = false)
    String address;

    public  Cinema(String name, String address){
        this.name = name;
        this.address = address;
    }

    public Cinema(){}

}

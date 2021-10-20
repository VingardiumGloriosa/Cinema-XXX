package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int cinemaId;

    @Column(length = 3, nullable = false)
    int numOfHalls;

    @Column(length = 30,nullable = false)
    String address;

    @Column(length = 4, nullable = false)
    int zipCode;

    @Column(length = 20,nullable = false)
    String name;

    @OneToMany (mappedBy = "cinema",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference("cinemaToHall")
    List<Hall> halls = new ArrayList<>();

    @OneToMany (mappedBy = "cinema",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference("ticketToScreening")
    List<Screening> screenings = new ArrayList<>();


    public Cinema() {}

    public  Cinema(int numOfHalls, String address, int zipCode, String name){
        this.numOfHalls = numOfHalls;
        this.address = address;
        this.zipCode = zipCode;
        this.name = name;
    }

}

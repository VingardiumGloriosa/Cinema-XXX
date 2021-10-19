package com.kea.cinemaxx.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int hallId;

    @Column(length = 4,nullable = false)
    int numberOfSeats;

//    @Column(length = 60, nullable = false)
//    int cinemaId;

    @ManyToOne
    @JoinColumn(name="cinema_id", nullable=false)
    @JsonBackReference // add values with the same names to make it reference the right things
    Cinema cinema;

    @OneToMany (mappedBy = "hall",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    List<Screening> screenings = new ArrayList<>();

    public Hall(){}

    public Hall(int numberOfSeats, Cinema cinema) {
        this.numberOfSeats = numberOfSeats;
        this.cinema = cinema;
    }

}

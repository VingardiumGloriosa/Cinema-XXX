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

    // we don't really need this, it's better to have a List<> of the actual seats
    @Column(length = 4,nullable = false)
    int numberOfSeats;

    @ManyToOne
    @JoinColumn(name="cinema_id", nullable=false)
    @JsonBackReference("cinemaToHall") // add values with the same names to make it reference the right things
    Cinema cinema;

    //orphanRemoval will delete any screenings for this hall when the hall is deleted
    @OneToMany (orphanRemoval = true, mappedBy = "hall", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference("screeningToHall")
    List<Screening> screenings = new ArrayList<>();

    //orphanRemoval will delete any seats for this hall when the hall is deleted
    @OneToMany(orphanRemoval = true, mappedBy = "hall", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference("seatToHall")
    List<Seat> seats = new ArrayList<>();

    public Hall(){}

    public Hall(int numberOfSeats, Cinema cinema) {
        this.numberOfSeats = numberOfSeats;
        this.cinema = cinema;
    }

}

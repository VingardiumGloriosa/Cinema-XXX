package com.kea.cinemaxx.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.kea.cinemaxx.dtos.MovieDTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int movieId;

    @Column(length = 60,nullable = false)
    String title;

    @Column(length = 60,nullable = false)
    int rating; //this is AGE RESTRICTION for now = min age required

    @Column(length = 60,nullable = false)
    String actors;

    @Column(length = 60,nullable = false)
    int year;

    @Column(length = 60,nullable = false)
    String genre;

    @Column(length = 1000,nullable = false)
    String description;

    @Column(length = 60,nullable = false)
    int length; //in minutes?


    //I considered adding the CascadeType.REMOVE to this variable BUT I found out that orphanRemoval=true does exactly what we needed:
    //it will remove all the referenced screenings for 'this' movie ;);)
    @OneToMany (orphanRemoval = true, mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    List<Screening> screenings = new ArrayList<>();

    public Movie(){}

    public  Movie(String title, int rating, String actors, Integer year, String genre, String description, Integer length){
        this.title = title;
        this.rating = rating;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }

}

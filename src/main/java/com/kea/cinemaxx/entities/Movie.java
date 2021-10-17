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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    String movieId;

    @Column(length = 60)
    String title;

    @Column(length = 60)
    int rating; //this is AGE RESTRICTION for now = min age required

    @Column(length = 60)
    String actors;

    @Column(length = 60)
    int year;

    @Column(length = 60)
    String genre;

    @Column(length = 1000)
    String description;

    @Column(length = 60)
    int length; //in minutes?

    @OneToMany (mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference
    List<Screening> screenings = new ArrayList<>();

    public Movie(){}

    public Movie(String movieId,String title){
        this.movieId=movieId;
        this.title=title;
    }

    public Movie(String title){
        this.title=title;
    }

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

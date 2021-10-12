package com.example.cinemaproject.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 60,nullable = false)
    String title;

    @Column(length = 60,nullable = false)
    String ratings;

    @Column(length = 60,nullable = false)
    String actors;

    @Column(length = 60,nullable = false)
    Integer year;

    @Column(length = 60,nullable = false)
    String genre;

    @Column(length = 60,nullable = false)
    String description;

    @Column(length = 60,nullable = false)
    Integer length;


    public  Movie(String title, String ratings, String actors, Integer year, String genre, String description, Integer length){
        this.title = title;
        this.ratings = ratings;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }

    public Movie(){}
}

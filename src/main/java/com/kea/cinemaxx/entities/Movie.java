package com.kea.cinemaxx.entities;

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
    int rating;

    @Column(length = 60,nullable = false)
    String actors;

    @Column(length = 60,nullable = false)
    int year;

    @Column(length = 60,nullable = false)
    String genre;

    @Column(length = 60,nullable = false)
    String description;

    @Column(length = 60,nullable = false)
    int length;

    @OneToMany (mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
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

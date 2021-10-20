package com.kea.cinemaxx.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.kea.cinemaxx.dtos.MovieDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
public class Movie {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    String movieId;

    @Column(length = 60)
    String title;

    @Column(length = 60)
    String rating; //this is AGE RESTRICTION for now = min age required

    @Column(length = 60)
    String actors;

    @Column(length = 60)
    String year;

    @Column(length = 60)
    String genre;

    @Column(length = 1000)
    String description;

    @Column(length = 60)
    String length; //in minutes?

    @Column(length = 1000)
    String trailer;

    @Column(length = 1000)
    String poster;

    @Column(length = 1000)
    String images;


    //I considered adding the CascadeType.REMOVE to this variable BUT I found out that orphanRemoval=true does exactly what we needed:
    //it will remove all the referenced screenings for 'this' movie ;);)
    @OneToMany (orphanRemoval = true, mappedBy = "movie",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JsonManagedReference ("screeningToMovie")
    List<Screening> screenings = new ArrayList<>();

    public Movie(){}

    public Movie(String movieId,String title){
        this.movieId=movieId;
        this.title=title;
    }

    public Movie(String title){
        this.title=title;
    }

    public  Movie(String title, String rating, String actors, String year, String genre, String description, String length){
        this.title = title;
        this.rating = rating;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }
    public  Movie(String movieId,String title, String rating, String actors, String year, String genre, String description, String length){
        this.movieId=movieId;
        this.title = title;
        this.rating = rating;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
    }


    public Movie(String movieId, String title, String rating, String actors, String year, String genre, String description, String length, String trailer, String poster, String images) {
        this.movieId=movieId;
        this.title = title;
        this.rating = rating;
        this.actors = actors;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.length = length;
        this.trailer=trailer;
        this.poster=poster;
        this.images=images;
    }
}

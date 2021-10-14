package com.kea.cinemaxx.configuration;

import com.kea.cinemaxx.entities.Cinema;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.entities.Movie;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.repositiories.CinemaRepository;
import com.kea.cinemaxx.repositiories.HallRepository;
import com.kea.cinemaxx.repositiories.MovieRepository;
import com.kea.cinemaxx.repositiories.ScreeningRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile("!test")
public class DataSetup implements CommandLineRunner{

    CinemaRepository cinemaRepository;
    HallRepository hallRepository;
    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;

    public DataSetup(CinemaRepository cinemaRepository, HallRepository hallRepository, ScreeningRepository screeningRepository,MovieRepository movieRepository){
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------->   ");
//        Cinema cinema1 = cinemaRepository.save(new Cinema(5, "Guldbergsgade 29F, Copenhagen", 2200, "Empire Sun"));
//        Cinema cinema2 = cinemaRepository.save(new Cinema(8,"Schlangade 30A, Copenhagen",2400,"Empire Moon"));
//        Cinema cinema3 = cinemaRepository.save(new Cinema(8,"Olgade 21F, Copenhagen",2400,"Empire Mars"));

        Cinema cinema1 = new Cinema(5, "Guldbergsgade 29F, Copenhagen", 2200, "Empire Sun");
        Cinema cinema2 = new Cinema(8,"Schlangade 30A, Copenhagen",2400,"Empire Moon");
        Cinema cinema3 = new Cinema(8,"Schlangade 30A, Copenhagen",2400,"Empire Moon");
        cinemaRepository.save(cinema1);
        cinemaRepository.save(cinema2);
        cinemaRepository.save(cinema3);



        Hall hall1 = new Hall(100, cinema1);
        Hall hall2 = new Hall(80, cinema1);
        Hall hall3 = new Hall(50, cinema1);
        Hall hall4 = new Hall(100, cinema1);
        Hall hall5 = new Hall(100, cinema1);
        hallRepository.save(hall1);
        hallRepository.save(hall2);
        hallRepository.save(hall3);
        hallRepository.save(hall4);
        hallRepository.save(hall5);

        Movie movie1 = new Movie("Dune", 13, "Timothee Chalamet, Rebecca Fergyson, Zendja", 2021, "Sci-fi", "A guy is born into a royal family and tries to survive the first part", 155);
        Movie movie2 = new Movie("No Time To Die", 13, "Daniel Craig, Ana de Armas", 2021, "Action", "James Bond has left the active service and enjoys his retirement", 163);
        Movie movie3 = new Movie("Shang-Chi", 13, "Simu Liu, Awkwafina, Tony Chiu-Wai Leung", 2021, "Fantasy", "Shang-Chi, the master of weaponry-based Kung Fu, is forced to confront his past", 132);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        Screening screening01 = new Screening(LocalTime.of(8,0,0),LocalDate.of(2021,12,8), movie1, hall1, cinema1);
        Screening screening02 = new Screening(LocalTime.of(12,0,0),LocalDate.of(2021,12,9), movie1, hall1, cinema1);
        Screening screening03 = new Screening(LocalTime.of(8,0,0),LocalDate.of(2021,12,10), movie2, hall2, cinema1);
        Screening screening04 = new Screening(LocalTime.of(20,0,0),LocalDate.of(2021,12,8), movie3, hall3, cinema1);
        screeningRepository.save(screening01);
        screeningRepository.save(screening02);
        screeningRepository.save(screening03);
        screeningRepository.save(screening04);


    }
}

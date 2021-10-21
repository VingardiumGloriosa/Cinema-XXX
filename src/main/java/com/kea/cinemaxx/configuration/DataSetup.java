package com.kea.cinemaxx.configuration;

import com.kea.cinemaxx.dtos.MovieDTO;
import com.kea.cinemaxx.entities.*;
import com.kea.cinemaxx.repositiories.*;
import com.kea.cinemaxx.services.MovieService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@Profile("!test")
public class DataSetup implements CommandLineRunner {

    CinemaRepository cinemaRepository;
    HallRepository hallRepository;
    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;
    TicketRepository ticketRepository;
    SeatRepository seatRepository;
    UserRepository userRepository;
    DBSetup dbSetup = new DBSetup();


    public DataSetup(CinemaRepository cinemaRepository, HallRepository hallRepository, ScreeningRepository screeningRepository, MovieRepository movieRepository, TicketRepository ticketRepository, SeatRepository seatRepository, UserRepository userRepository) {
        this.cinemaRepository = cinemaRepository;
        this.hallRepository = hallRepository;
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------->   ");

        Cinema cinema1 = new Cinema(5, "Guldbergsgade 29F, Copenhagen", 2200, "Empire Sun");
        Cinema cinema2 = new Cinema(8, "Schlangade 30A, Copenhagen", 2400, "Empire Moon");
//        Cinema cinema3 = new Cinema(8,"Olgade 21F, Copenhagen",2400,"Empire Mars");
        cinemaRepository.save(cinema1);
        cinemaRepository.save(cinema2);
//        cinemaRepository.save(cinema3);

        Hall hall1 = new Hall(40, cinema1);
        Hall hall2 = new Hall(60, cinema2);
//        Hall hall3 = new Hall(60, cinema3);
//        Hall hall4 = new Hall(100, cinema2);
//        Hall hall5 = new Hall(100, cinema3);
        hallRepository.save(hall1);
        hallRepository.save(hall2);
//        hallRepository.save(hall3);
//        hallRepository.save(hall4);
//        hallRepository.save(hall5);
/*
        Movie movie1 = new Movie("Dune", 13, "Timothee Chalamet, Rebecca Fergyson, Zendja", 2021, "Sci-fi", "A guy is born into a royal family and tries to survive the first part", 155);
        Movie movie2 = new Movie("No Time To Die", 13, "Daniel Craig, Ana de Armas", 2021, "Action", "James Bond has left the active service and enjoys his retirement", 163);
        Movie movie3 = new Movie("Shang-Chi", 13, "Simu Liu, Awkwafina, Tony Chiu-Wai Leung", 2021, "Fantasy", "Shang-Chi, the master of weaponry-based Kung Fu, is forced to confront his past", 132);
        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);
*/
        Movie m1 = new Movie("tt0120338","Titanic","PG-13","Leonardo DiCaprio, Kate Winslet, Billy Zane","1997","Drama, Romance","A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the luxurious, ill-fated R.M.S. Titanic.","194 min","https://www.imdb.com/video/vi907189785","https://m.media-amazon.com/images/M/MV5BMDdmZGU3NDQtY2E5My00ZTliLWIzOTUtMTY4ZGI1YjdiNjk3XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg","[{\\\"image\\\":\\\"https://imdb-api.com/images/original/MV5BNzY5MDQzOTAwNV5BMl5BanBnXkFtZTcwNTY0NTk1Nw@@._V1_Ratio1.3600_AL_.jpg\\\",\\\"title\\\":\\\"Kate Winslet in Titanic (1997)\\\"");
        movieRepository.save(m1);

        Screening screening01 = new Screening(LocalTime.of(8, 0, 0), LocalDate.of(2021, 12, 8), m1, hall1, cinema1);
        Screening screening02 = new Screening(LocalTime.of(12, 0, 0), LocalDate.of(2021, 12, 9), m1, hall2, cinema2);
//        Screening screening03 = new Screening(LocalTime.of(8,0,0),LocalDate.of(2021,12,10), m1, hall3, cinema3);
        Screening screening04 = new Screening(LocalTime.of(20, 0, 0), LocalDate.of(2021, 12, 8), m1, hall1, cinema1);
        screeningRepository.save(screening01);
        screeningRepository.save(screening02);
//        screeningRepository.save(screening03);
        screeningRepository.save(screening04);

        User user1 = new User(true);
        User user2 = new User(false);
        User user3 = new User(false);
        User user4 = new User(false);
        User user5 = new User(false);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);


        //hall 1
        for (int i = 1; i < 5; i++) {
            for (char j = 'A'; j < 'M'; j++) {
                Seat seat = new Seat(i, j, hall1);
                seatRepository.save(seat);
                Ticket ticket1 = new Ticket(true, user2, seat, screening01);
                Ticket ticket4 = new Ticket(false, user1, seat, screening04);
                ticketRepository.save(ticket1);
                ticketRepository.save(ticket4);
            }
        }

        //hall2
        for (int i = 1; i < 7; i++) {
            for (char j = 'A'; j < 'M'; j++) {
                Seat seat = new Seat(i, j, hall2);
                seatRepository.save(seat);
                Ticket ticket2 = new Ticket(false, user1, seat, screening02);
                ticketRepository.save(ticket2);
            }
        }

//        Ticket ticket1 = new Ticket(true, user1, seat1, screening01);
//        ticketRepository.save(ticket1);

    }
}

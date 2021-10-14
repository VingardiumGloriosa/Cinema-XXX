create table cinemas
(
    cinemaId int auto_increment
        primary key,
    numOfHalls int not null,
    address varchar(30) not null,
    zipCode int default 0 not null,
    name varchar(20) not null
);

create table halls
(
    hallId int auto_increment
        primary key,
    numOfSeats int not null,
    cinemaId int not null,
    constraint cinemaId
        foreign key (cinemaId) references cinemas (cinemaId)
);

create table movies
(
    movieId int auto_increment
        primary key,
    rating int not null,
    actors varchar(50) not null,
    title varchar(50) default '' not null,
    year int not null,
    genre varchar(50) default '' not null,
    description text not null,
    length int not null
);

create table screenings
(
    screeningId int auto_increment
        primary key,
    time time default '00:00:00' not null,
    movieId int default 0 not null,
    date date default '2000-01-01' not null,
    hallId int not null,
    cinemaId int not null,
    constraint screenings_cinemas_cinemaId_fk
        foreign key (cinemaId) references cinemas (cinemaId),
    constraint screenings_halls_hallId_fk
        foreign key (hallId) references halls (hallId),
    constraint screenings_movies_movieId_fk
        foreign key (movieId) references movies (movieId)
);

create table seats
(
    seatId int auto_increment
        primary key,
    row int not null,
    `column` char not null,
    hallId int not null,
    reserved binary(1) null,
    constraint hallId
        foreign key (hallId) references halls (hallId)
);

create index `column`
    on seats (`column`);

create index row
    on seats (row);

create table tickets
(
    ticketId int auto_increment
        primary key,
    reservationName varchar(50) default '0' not null,
    reservationMail varchar(50) default '0' not null,
    seatId int not null,
    screeningId int not null,
    constraint tickets_screenings_screeningId_fk
        foreign key (screeningId) references screenings (screeningId),
    constraint tickets_seats_seatId_fk
        foreign key (seatId) references seats (seatId)
);




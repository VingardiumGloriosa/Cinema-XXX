package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Hall;
import com.kea.cinemaxx.entities.Seat;
import com.kea.cinemaxx.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@AllArgsConstructor
@Getter @Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class SeatDTO {

    int seatId;
    int seatRow;
    char seatColumn;
    boolean reserved;
    Ticket ticket;
    Hall hall;

    public SeatDTO(int seatRow, char seatColumn, boolean reserved, Ticket ticket, Hall hall){
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.reserved = reserved;
        this.ticket = ticket;
        this.hall = hall;
    }

    public SeatDTO(Seat seat){
        this.seatRow = seat.getSeatRow();
        this.seatColumn = seat.getSeatColumn();
        this.reserved = seat.isReserved();
        this.ticket = seat.getTicket();
        this.hall = seat.getHall();
        this.seatId = seat.getSeatId();
    }

    public static List<SeatDTO> SeatDTOSfromSeat(Iterable<Seat> seats){
        List<SeatDTO> dtos = StreamSupport.stream(seats.spliterator(), false)
                .map(seat -> new SeatDTO(seat))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Seat seatFromSeatDTO(SeatDTO seat){
        return new Seat(seat.getSeatRow(),seat.getSeatColumn(),seat.isReserved(),seat.getTicket(), seat.getHall());
    }

}

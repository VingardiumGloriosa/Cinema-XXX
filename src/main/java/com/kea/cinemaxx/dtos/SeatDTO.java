package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Seat;
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

    int row;
    int column;
    boolean reserved;

    public SeatDTO(int row, int column, boolean reserved){
        this.row = row;
        this.column = column;
        this.reserved = reserved;
    }

    public SeatDTO(Seat seat){
        this.row = seat.getRow();
        this.column = seat.getColumn();
        this.reserved = seat.isReserved();
    }

    public static List<SeatDTO> SeatDTOSfromSeat(Iterable<Seat> seats){
        List<SeatDTO> dtos = StreamSupport.stream(seats.spliterator(), false)
                .map(seat -> new SeatDTO(seat))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Seat seatFromSeatDTO(SeatDTO seat){
        return new Seat(seat.getRow(),seat.getColumn(),seat.isReserved());
    }

}

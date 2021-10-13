package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class TicketDTO {

    int ticketId;
    String reservationName;
    String reservationEmail;
    int seatId;
    int screeningId;

    public TicketDTO(String reservationName, String reservationEmail){
        this.reservationName = reservationName;
        this.reservationEmail = reservationEmail;
    }

    public TicketDTO(Ticket ticket){
        this.reservationName = ticket.getReservationName();
        this.reservationEmail = ticket.getReservationEmail();
        this.ticketId = ticket.getTicketId();
    }

    public static List<TicketDTO> TicketDTOSfromTicket(Iterable<Ticket> tickets){
        List<TicketDTO> dtos = StreamSupport.stream(tickets.spliterator(), false)
                .map(ticket -> new TicketDTO(ticket))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Ticket ticketFromTicketDTO(TicketDTO ticket){
        return new Ticket(ticket.getReservationName(),ticket.getReservationEmail(), ticket.getSeatId(), ticket.getScreeningId());
    }

}

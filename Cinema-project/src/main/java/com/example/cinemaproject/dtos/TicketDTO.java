package com.example.cinemaproject.dtos;

import com.example.cinemaproject.entities.Ticket;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO {

    int id;
    String reservationName;
    String reservationEmail;

    public TicketDTO(String reservationName, String reservationEmail){
        this.reservationName = reservationName;
        this.reservationEmail = reservationEmail;
    }

    public TicketDTO(Ticket ticket){
        this.reservationName = ticket.getReservationName();
        this.reservationEmail = ticket.getReservationEmail();
        this.id = ticket.getId();
    }

    public static List<TicketDTO> TicketDTOSfromTicket(Iterable<Ticket> tickets){
        List<TicketDTO> dtos = StreamSupport.stream(tickets.spliterator(), false)
                .map(ticket -> new TicketDTO(ticket))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Ticket ticketFromTicketDTO(TicketDTO ticket){
        return new Ticket(ticket.getReservationName(),ticket.getReservationEmail());
    }

}

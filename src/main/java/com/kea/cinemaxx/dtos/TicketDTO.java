package com.kea.cinemaxx.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.entities.Seat;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude (JsonInclude.Include.NON_NULL)
public class TicketDTO {

    int ticketId;
    boolean purchased;
    User user; //default user is user#1 (admin)
    Seat seat;
    Screening screening;

    public TicketDTO(boolean purchased, User user, Seat seat, Screening screening){
        this.purchased = purchased;
        this.user = user;
        this.seat = seat;
        this.screening = screening;
    }

    public TicketDTO(Ticket ticket){
        this.purchased = ticket.isPurchased();
        this.user = ticket.getUser();
        this.seat = ticket.getSeat();
        this.screening = ticket.getScreening();
        this.ticketId = ticket.getTicketId();
    }

    public static List<TicketDTO> TicketDTOSfromTicket(Iterable<Ticket> tickets){
        List<TicketDTO> dtos = StreamSupport.stream(tickets.spliterator(), false)
                .map(ticket -> new TicketDTO(ticket))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Ticket ticketFromTicketDTO(TicketDTO ticket){
        return new Ticket(ticket.isPurchased(),ticket.getUser(), ticket.getSeat(), ticket.getScreening());
    }

}

package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.repositiories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    TicketRepository ticketRepository;

    public List<TicketDTO> getTickets() {
        //and other conditions maybe? the admin should be able to see these?
        return TicketDTO.TicketDTOSfromTicket(ticketRepository.findAll());
    }

    public TicketDTO getTicket(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        return new TicketDTO(ticket);
    }

    // create booking (Sam)
    public TicketDTO addTicket(TicketDTO newTicket){
        Ticket ticketToMake = TicketDTO.ticketFromTicketDTO(newTicket);
        return new TicketDTO(ticketRepository.save(ticketToMake));
    }

    public boolean isTicketFree(int screeningId, int seatId){
        Ticket t = ticketRepository.findTicketByScreening_ScreeningIdAndSeat_SeatId(screeningId, seatId);
        return t==null ? true : false;
    }

    // edit booking (Chia)
    // if ticket exists and this.user_id == booking.user_id
    // show free seats for the same screening (wouldn't make sense to change anything else)
    // edit ticket.seat_id
    public TicketDTO editTicket(TicketDTO ticketToEdit, int ticketId) {
        Ticket ticketOrg = ticketRepository.findById(ticketId).orElseThrow();
        ticketOrg.setPurchased(ticketToEdit.isPurchased());
        ticketOrg.setUser(ticketToEdit.getUser());
        ticketOrg.setSeat(ticketToEdit.getSeat());
        ticketOrg.setScreening(ticketToEdit.getScreening());
        return new TicketDTO(ticketRepository.save(ticketOrg));
    }

    // cancel booking (Chia)
    // if ticket exists and this.user_id == booking.user_id
    // delete ticket
    // set seat to reserved=false again
    public void deleteTicket(int ticketId) {
        ticketRepository.deleteById(ticketId);
    }




}

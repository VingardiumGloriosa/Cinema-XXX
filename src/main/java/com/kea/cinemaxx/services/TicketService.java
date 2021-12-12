package com.kea.cinemaxx.services;

import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.entities.Screening;
import com.kea.cinemaxx.entities.Seat;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.entities.User;
import com.kea.cinemaxx.repositiories.SeatRepository;
import com.kea.cinemaxx.repositiories.TicketRepository;
import com.kea.cinemaxx.repositiories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TicketService {

    TicketRepository ticketRepository;
    UserRepository userRepository;
    SeatRepository seatRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
    }

    final ResponseStatusException UNAUTHORIZED_USER = new ResponseStatusException(
            HttpStatus.UNAUTHORIZED, "User does not have permissions to perform this action."
    );

    public List<TicketDTO> getSeatsByBooking(boolean purchased, int screeningId, int userId) {

        User user = userRepository.findById(userId).orElseThrow();

        if (user.isAdmin()) {
            return TicketDTO.TicketDTOSfromTicket(ticketRepository.findTicketByPurchasedAndScreening_ScreeningId(purchased, screeningId));
        }
        
        else {
            throw UNAUTHORIZED_USER;
        }

    }

    public List<TicketDTO> getTickets() {
        Iterable<Ticket> tickets = ticketRepository.findAll();
        return TicketDTO.TicketDTOSfromTicket(tickets);
    }

    public List<TicketDTO> getTicketsByScreening(int id) {
        Iterable<Ticket> tickets = ticketRepository.findByScreening_ScreeningId(id);
        return TicketDTO.TicketDTOSfromTicket(tickets);
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


    public TicketDTO reserveTicket(int userId, int ticketId) {

        User user = userRepository.findById(userId).orElseThrow();
        Ticket ticketToReserve = ticketRepository.findById(ticketId).orElseThrow();

        // existing user wants to purchase a free ticket
        if (!(ticketToReserve.isPurchased())) {
            ticketToReserve.setPurchased(true);
            ticketToReserve.setUser(user);
        }

        // the ticket is not free
        else if (ticketToReserve.isPurchased()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "The ticket cannot be purchased as it currently belongs to someone else."
            );
        }

        else {
            throw UNAUTHORIZED_USER;
        }
        return new TicketDTO(ticketRepository.save(ticketToReserve));
    }

    // edit booking (Chia)
    public TicketDTO editTicket(int userId, int newSeatId, int ticketId) {

        Ticket oldTicket = ticketRepository.findById(ticketId).orElseThrow();
        User ticketOwnerOrAdmin = userRepository.findById(userId).orElseThrow();

        if (ticketOwnerOrAdmin.isAdmin() || ticketOwnerOrAdmin.getUserId() == oldTicket.getUser().getUserId()) {

            Seat newSeat = seatRepository.findById(newSeatId).orElseThrow();

            Ticket newTicket = ticketRepository.findBySeat(newSeat);

            newTicket.setPurchased(oldTicket.isPurchased());
            newTicket.setUser(oldTicket.getUser());
            newTicket.setScreening(oldTicket.getScreening());

            oldTicket = resetTicket(oldTicket);

            TicketDTO oldTicketDTO = new TicketDTO(ticketRepository.save(oldTicket));
            TicketDTO newTicketDTO = new TicketDTO(ticketRepository.save(newTicket));

            //ticket 1 paid | seat 1
            //ticket 2 not paid | seat 2
            //ticket 1 paid | seat 1 -> 2 => ticket 1.reset(), ticket 2 paid=true, user=this, seat=2(stays the same)

            return newTicketDTO;
        }
        else throw UNAUTHORIZED_USER; //exception declared above
    }

    // cancel booking (Chia)
    public void deleteTicket(int userId, int ticketId) {

        Ticket ticketOrg = ticketRepository.findById(ticketId).orElseThrow();
        User ticketOwnerOrAdmin = userRepository.findById(userId).orElseThrow();

        if (ticketOwnerOrAdmin.isAdmin() || ticketOwnerOrAdmin.getUserId() == ticketOrg.getUser().getUserId()) {
            ticketOrg.setPurchased(false);
            ticketOrg.setUser(new User(1)); //free tickets default to admin user
            TicketDTO newTicket = new TicketDTO(ticketRepository.save(ticketOrg));
//            ticketRepository.deleteById(ticketId); // can't actually be deleted like that!
        }

        else throw UNAUTHORIZED_USER; //exception declared above

    }

    public Ticket resetTicket(Ticket ticket) {

        ticket.setPurchased(false);
        ticket.setUser(new User(1)); //free tickets default to admin user

        return ticket;
    }

}

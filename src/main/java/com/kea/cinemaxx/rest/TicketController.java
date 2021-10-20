package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.ScreeningDTO;
import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.dtos.UserDTO;
import com.kea.cinemaxx.entities.Ticket;
import com.kea.cinemaxx.services.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {

    TicketService ticketService;

    public TicketController (TicketService ticketService) { this.ticketService = ticketService; }

    // get tickets
    @GetMapping("/get")
    @ResponseBody
    List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    // get ticket by id
    @GetMapping("/get-by-id/{id}")
    TicketDTO getTicket(@PathVariable int id) {
        return ticketService.getTicket(id);
    }

    // create booking (Sam)
    @PostMapping()
    //I think this should be addBooking since the tickets are already in the db technically
    TicketDTO addTicket(@RequestBody TicketDTO newTicket){
        return ticketService.addTicket(newTicket);
    }

    @GetMapping("/{screeningId}/{seatId}")
    boolean isTicketFree(@PathVariable int screeningId, @PathVariable int seatId){
        return ticketService.isTicketFree(screeningId, seatId);
    }

    // edit booking (Chia)
    @PutMapping("/edit-booking/{ticketId}")
    TicketDTO editBooking(@RequestBody TicketDTO ticketToEdit, @RequestBody UserDTO ticketOwnerOrAdmin, @PathVariable int ticketId) {
        return ticketService.editTicket(ticketToEdit, ticketOwnerOrAdmin, ticketId);
    }

    // cancel booking (Chia)
    @PutMapping("/delete-booking/{ticketId}")
    void deleteBooking(@RequestBody UserDTO ticketOwnerOrAdmin, @PathVariable int ticketId) {
        ticketService.deleteTicket(ticketOwnerOrAdmin, ticketId);
    }

}

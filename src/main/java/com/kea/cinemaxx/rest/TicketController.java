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

    @GetMapping("/get")
    @ResponseBody
    List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/get-by-id/{id}")
    TicketDTO getTicket(@PathVariable int id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/getSeats/{purchased}/{screeningId}")
    List<TicketDTO> getSeatsByBooking(@PathVariable boolean purchased, @PathVariable int screeningId){
        return ticketService.getSeatsByBooking(purchased, screeningId);
    }

    @PostMapping()
    TicketDTO addTicket(@RequestBody TicketDTO newTicket){
        return ticketService.addTicket(newTicket);
    }

    @PutMapping("/reserve-ticket")
    TicketDTO reserveTicket(@RequestParam int userId, @RequestParam int ticketId) {
        // ...api/tickets/reserve-ticket?userId=2&ticketId=3
        return ticketService.reserveTicket(userId, ticketId);
    }

    @PutMapping("/edit-booking")
    TicketDTO editBooking(@RequestParam int userId, @RequestParam int newSeatId, @RequestParam int oldTicketId) {
        return ticketService.editTicket(userId, newSeatId, oldTicketId);
    }

    @PutMapping("/delete-booking")
    void deleteBooking(@RequestBody int userId, @RequestParam int ticketId) {
        ticketService.deleteTicket(userId, ticketId);
    }

}

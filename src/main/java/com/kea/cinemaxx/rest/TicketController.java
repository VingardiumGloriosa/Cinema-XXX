package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.TicketDTO;
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
    public List<TicketDTO> getTickets() {
        return ticketService.getTickets();
    }

    // get ticket by id
    @GetMapping("/get-by-id/{id}")
    public TicketDTO getTicket(@PathVariable int id) {
        return ticketService.getTicket(id);
    }

    // create booking (Sam)

    @PostMapping()
    TicketDTO addTicket(@RequestBody TicketDTO newTicket){

        return ticketService.addTicket(newTicket);}

    @GetMapping("/{screeningId}/{seatId}")
    boolean isTicketFree(@PathVariable int screeningId, @PathVariable int seatId){
        return ticketService.isTicketFree(screeningId, seatId);
    }

    // cancel booking (Chia)

    // edit booking (Chia)

}

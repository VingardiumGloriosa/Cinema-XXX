package com.kea.cinemaxx.rest;

import com.kea.cinemaxx.dtos.TicketDTO;
import com.kea.cinemaxx.services.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // cancel booking (Chia)

    // edit booking (Chia)

}

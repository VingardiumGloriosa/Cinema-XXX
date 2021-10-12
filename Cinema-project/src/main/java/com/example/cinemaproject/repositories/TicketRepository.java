package com.example.cinemaproject.repositories;

import com.example.cinemaproject.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}

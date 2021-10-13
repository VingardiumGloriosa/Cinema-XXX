package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
}
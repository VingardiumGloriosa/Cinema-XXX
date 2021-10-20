package com.kea.cinemaxx.repositiories;

import com.kea.cinemaxx.entities.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
//    List<Ticket> findTicketByReservationEmail(String reservationEmail);
//    List<Ticket> findTicketByReservationName(String reservationName);
//    List<Ticket> findTicketByScreening(int screeningId); //does this make sense? maybe we don't need it now
}
package cl.prototype.repository;

import org.springframework.data.repository.CrudRepository;

import cl.prototype.entities.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {

}

package com.greatlearning.TicketTracker.service;

import java.util.List;

import com.greatlearning.TicketTracker.model.Ticket;

public interface TicketService {

	Ticket addTicket(Ticket ticket);

	Ticket editTicket(long ticketId, Ticket ticket);

	Ticket getTicket(long ticketId);

	List<Ticket> getAllTickets();

	List<Ticket> searchByKeyword(String keyword);

	void deleteTicket(long ticketId);

}

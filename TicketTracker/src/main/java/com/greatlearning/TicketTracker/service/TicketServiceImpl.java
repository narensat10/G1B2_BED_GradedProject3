package com.greatlearning.TicketTracker.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.TicketTracker.model.Ticket;
import com.greatlearning.TicketTracker.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	@Autowired
	TicketRepository ticketRepository;

	// adding and a new ticket to repository
	@Override
	public Ticket addTicket(Ticket ticket) {
		Date newDate = new Date();
		String formatedDate = dateFormat.format(newDate);
		ticket.setCreated_date(formatedDate);
		return ticketRepository.save(ticket);
	}

	// Editing the details of ticket by its id and saving the changes
	@Override
	public Ticket editTicket(long ticketId, Ticket ticket) {

		// creating Edited date
		Date editDate = new Date();
		String updateDate = dateFormat.format(editDate);

		Ticket ticket_db = getTicket(ticketId);
		ticket_db.setTitle(ticket.getTitle());
		ticket_db.setDescription(ticket.getDescription());
		ticket_db.setContent(ticket.getContent());
		ticket_db.setEdited_date(updateDate);

		return ticketRepository.save(ticket_db);
	}

	// fetching ticket with id from repository
	@Override
	public Ticket getTicket(long ticketId) {

		return ticketRepository.findById(ticketId).get();
	}

	// fetching all tickets from repository
	@Override
	public List<Ticket> getAllTickets() {

		return ticketRepository.findAll();
	}

	// fetching list of tickets having keyword in title
	@Override
	public List<Ticket> searchByKeyword(String keyword) {

		return ticketRepository.findByTitleContainingIgnoreCase(keyword);
	}

	// deleting tickets with id in repository
	@Override
	public void deleteTicket(long ticketId) {
		ticketRepository.deleteById(ticketId);

	}
}

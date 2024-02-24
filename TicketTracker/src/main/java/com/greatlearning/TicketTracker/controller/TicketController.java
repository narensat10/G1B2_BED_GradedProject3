package com.greatlearning.TicketTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.TicketTracker.model.Ticket;
import com.greatlearning.TicketTracker.service.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {

	@Autowired
	TicketService ticketService;

	// will map to list-tickets page and show list of tickets present
	@RequestMapping("/list")
	public String listTickets(Model theModel) {
		List<Ticket> list_tickets = ticketService.getAllTickets();
		theModel.addAttribute("tickets", list_tickets);
		return "tickets/list-tickets";
	}

	// will map to ticket-form page and will create the new ticket
	@RequestMapping("/new")
	public String createTicket(Model theModel) {
		Ticket newTicket = new Ticket();
		theModel.addAttribute("ticket", newTicket);
		return "tickets/ticket-form";
	}

	// will map to update-ticket page and will be able to edit the details in the
	// ticket
	@RequestMapping("/edit")
	public String updateTicket(@RequestParam("ticketId") long tid, Model theModel) {
		// getting the ticket from the service

		Ticket ticket_db = ticketService.getTicket(tid);
		// set ticket as model attribute to pre populate
		theModel.addAttribute("ticket", ticket_db);

		// sending to ticket form

		return "tickets/update-ticket";
	}

	// will save the ticket and redirect to list-tickets page
	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("ticket") Ticket save_ticket) {
		ticketService.addTicket(save_ticket);
		return "redirect:/ticket/list";
	}

	// will update the changes made in ticket and will navigate to list-tickets
	@PostMapping("/update")
	public String updateTicket(@ModelAttribute("ticket") Ticket update_ticket) {
		ticketService.editTicket(update_ticket.getId(), update_ticket);
		return "redirect:/ticket/list";
	}

	// will delete selected ticket
	@PostMapping("/delete")
	public String deleteTicket(@RequestParam("ticketId") long tid) {
		ticketService.deleteTicket(tid);
		return "redirect:/ticket/list";
	}

	// will show the selected ticket
	@RequestMapping("/view")
	public String viewTicket(@RequestParam("ticketId") long tid, Model theModel) {
		Ticket viewTicket = ticketService.getTicket(tid);
		theModel.addAttribute("ticket", viewTicket);
		return "tickets/view-ticket";
	}

	// will show the tickets which have the keyword in title
	@RequestMapping("/search")
	public String searchTicket(@RequestParam("keyword") String searchWord, Model theModel) {
		List<Ticket> list = ticketService.searchByKeyword(searchWord);
		theModel.addAttribute("tickets", list);
		return "tickets/list-tickets";
	}

}

package com.ticket.stms.service;

import com.ticket.stms.dto.TicketDTO;
import com.ticket.stms.dto.AssignTicketDTO;

import com.ticket.stms.entity.Ticket;

import java.util.List;

public interface TicketService {

    Ticket createTicket(
            TicketDTO dto
    );

    List<Ticket> getAllTickets();

    Ticket assignTicket(
            AssignTicketDTO dto
    );

    Ticket updateStatus(
            Long id,

            String status
    );

    Ticket getTicketById(
            Long id
    );
    List<Ticket>
    getTicketsByStatus(
            String status
    );

    List<Ticket>
    getTicketsByPriority(
            String priority
    );

    List<Ticket>
    getAssignedTickets(
            Long agentId
    );
}
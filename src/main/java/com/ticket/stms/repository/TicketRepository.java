package com.ticket.stms.repository;

import com.ticket.stms.entity.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository
        extends JpaRepository<
        Ticket,
        Long
        > {

    List<Ticket>
    findByStatus(
            String status
    );

    List<Ticket>
    findByPriority(
            String priority
    );

    List<Ticket>
    findByAssignedToId(
            Long agentId
    );

}
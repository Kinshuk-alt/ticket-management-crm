package com.ticket.stms.controller;

import com.ticket.stms.dto.TicketDTO;
import com.ticket.stms.entity.Ticket;
import com.ticket.stms.dto.AssignTicketDTO;
import com.ticket.stms.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(
        "/api/tickets"
)

public class TicketController {

    @Autowired

    TicketService ticketService;

    @PostMapping

    public Ticket createTicket(

            @RequestBody

            TicketDTO dto

    ){

        return ticketService.createTicket(
                dto
        );

    }
    @PostMapping(
            "/assign"
    )

    public Ticket assignTicket(

            @RequestBody

            AssignTicketDTO dto

    ){

        return ticketService
                .assignTicket(
                        dto
                );

    }
    @PutMapping(
            "/status/{id}"
    )

    public Ticket updateStatus(

            @PathVariable
            Long id,

            @RequestParam
            String status

    ){

        return ticketService
                .updateStatus(
                        id,
                        status
                );

    }


    @GetMapping
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(
            @PathVariable
            Long id
    ){
        return ticketService.getTicketById(id);
    }
    @GetMapping(
            "/search/status"
    )

    public List<Ticket>
    searchStatus(

            @RequestParam

            String status

    ){

        return ticketService
                .getTicketsByStatus(
                        status
                );

    }

    @GetMapping(
            "/search/priority"
    )

    public List<Ticket>
    searchPriority(

            @RequestParam

            String priority

    ){

        return ticketService
                .getTicketsByPriority(
                        priority
                );

    }

    @GetMapping(
            "/agent/{agentId}"
    )

    public List<Ticket>
    getAgentTickets(

            @PathVariable

            Long agentId

    ){

        return ticketService
                .getAssignedTickets(
                        agentId
                );

    }

}
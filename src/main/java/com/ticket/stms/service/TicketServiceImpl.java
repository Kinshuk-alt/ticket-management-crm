package com.ticket.stms.service;

import com.ticket.stms.dto.TicketDTO;
import com.ticket.stms.dto.AssignTicketDTO;
import com.ticket.stms.entity.Ticket;
import com.ticket.stms.entity.User;
import java.time.LocalDateTime;


import com.ticket.stms.repository.TicketRepository;
import com.ticket.stms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TicketServiceImpl
        implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Override

    public Ticket createTicket(
            TicketDTO dto
    ){

        User user=
                userRepository
                        .findById(
                                dto.getUserId()
                        )

                        .orElseThrow(
                                ()->new RuntimeException(
                                        "User not found"
                                )
                        );

        Ticket ticket=
                new Ticket();

        ticket.setTitle(
                dto.getTitle()
        );

        ticket.setDescription(
                dto.getDescription()
        );

        ticket.setPriority(
                dto.getPriority()
        );

        ticket.setStatus(
                "OPEN"
        );

        ticket.setCreatedAt(
                LocalDateTime.now()
        );

        ticket.setCreatedBy(
                user
        );

        return ticketRepository.save(
                ticket
        );

    }

    @Override

    public List<Ticket>
    getAllTickets(){

        return ticketRepository.findAll();

    }
    @Override

    public Ticket assignTicket(
            AssignTicketDTO dto
    ){

        Ticket ticket=
                ticketRepository
                        .findById(
                                dto.getTicketId()
                        )

                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Ticket not found"
                                )
                        );

        User agent=
                userRepository
                        .findById(
                                dto.getAgentId()
                        )

                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Agent not found"
                                )
                        );

        ticket.setAssignedTo(
                agent
        );

        if(
                !agent.getRole()
                        .equals(
                                "AGENT"
                        )
        ){

            throw new RuntimeException(
                    "Ticket can only be assigned to AGENT"
            );

        }

        ticket.setStatus(
                "IN_PROGRESS"
        );

        return ticketRepository.save(
                ticket
        );

    }
    @Override

    public Ticket updateStatus(
            Long id,

            String status
    ){

        Ticket ticket=
                ticketRepository
                        .findById(id)

                        .orElseThrow(
                                ()->new RuntimeException(
                                        "Ticket not found"
                                )
                        );

        ticket.setStatus(
                status
        );

        return ticketRepository.save(
                ticket
        );

    }
    @Override
    public Ticket getTicketById(
            Long id
    ){
        return ticketRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Ticket not found"
                        )
                );
    }
    @Override

    public List<Ticket>
    getTicketsByStatus(
            String status
    ){

        return ticketRepository
                .findByStatus(
                        status
                );

    }

    @Override

    public List<Ticket>
    getTicketsByPriority(
            String priority
    ){

        return ticketRepository
                .findByPriority(
                        priority
                );

    }

    @Override

    public List<Ticket>
    getAssignedTickets(
            Long agentId
    ){

        return ticketRepository
                .findByAssignedToId(
                        agentId
                );

    }

}
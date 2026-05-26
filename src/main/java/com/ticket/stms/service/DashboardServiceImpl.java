package com.ticket.stms.service;

import com.ticket.stms.dto.DashboardDTO;

import com.ticket.stms.entity.Ticket;

import com.ticket.stms.repository.TicketRepository;
import com.ticket.stms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DashboardServiceImpl
        implements DashboardService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Override

    public DashboardDTO getDashboard(){

        DashboardDTO dto=
                new DashboardDTO();

        List<Ticket> tickets=
                ticketRepository.findAll();

        dto.setTotalUsers(
                userRepository.count()
        );

        dto.setTotalTickets(
                tickets.size()
        );

        dto.setOpenTickets(

                tickets.stream()

                        .filter(
                                t->"OPEN"
                                        .equals(
                                                t.getStatus()
                                        )
                        )

                        .count()
        );

        dto.setResolvedTickets(

                tickets.stream()

                        .filter(
                                t->"RESOLVED"
                                        .equals(
                                                t.getStatus()
                                        )
                        )

                        .count()
        );

        dto.setInProgressTickets(

                tickets.stream()

                        .filter(
                                t->"IN_PROGRESS"
                                        .equals(
                                                t.getStatus()
                                        )
                        )

                        .count()
        );

        return dto;

    }

}
package com.ticket.stms.service;

import com.ticket.stms.dto.ReportDTO;

import com.ticket.stms.entity.Ticket;

import com.ticket.stms.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReportServiceImpl
        implements ReportService {

    @Autowired
    TicketRepository ticketRepository;

    @Override

    public ReportDTO generateReport(){

        List<Ticket> tickets =
                ticketRepository.findAll();

        ReportDTO report =
                new ReportDTO();

        long total =
                tickets.size();

        long open =
                tickets.stream()

                        .filter(
                                t -> "OPEN"
                                        .equals(
                                                t.getStatus()
                                        )
                        )

                        .count();

        long resolved =
                tickets.stream()

                        .filter(
                                t -> "RESOLVED"
                                        .equals(
                                                t.getStatus()
                                        )
                        )

                        .count();

        long high =
                tickets.stream()

                        .filter(
                                t -> "HIGH"
                                        .equals(
                                                t.getPriority()
                                        )
                        )

                        .count();

        report.setTotalTickets(
                total
        );

        report.setOpenTickets(
                open
        );

        report.setResolvedTickets(
                resolved
        );

        report.setHighPriorityTickets(
                high
        );

        return report;

    }

}
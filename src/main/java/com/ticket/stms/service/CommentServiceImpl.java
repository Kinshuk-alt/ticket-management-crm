package com.ticket.stms.service;

import com.ticket.stms.dto.CommentDTO;

import com.ticket.stms.entity.Comment;
import com.ticket.stms.entity.Ticket;
import com.ticket.stms.entity.User;

import com.ticket.stms.repository.CommentRepository;
import com.ticket.stms.repository.TicketRepository;
import com.ticket.stms.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class CommentServiceImpl
        implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Override

    public Comment addComment(
            CommentDTO dto
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

        Comment comment=
                new Comment();

        comment.setMessage(
                dto.getMessage()
        );

        comment.setCreatedAt(
                LocalDateTime.now()
        );

        comment.setTicket(
                ticket
        );

        comment.setUser(
                user
        );

        return commentRepository.save(
                comment
        );

    }

    @Override

    public List<Comment>
    getCommentsByTicket(
            Long ticketId
    ){

        return commentRepository.findAll()
                .stream()

                .filter(
                        c->c.getTicket()
                                .getId()
                                .equals(
                                        ticketId
                                )
                )

                .toList();

    }

}
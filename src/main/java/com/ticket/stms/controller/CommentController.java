package com.ticket.stms.controller;

import com.ticket.stms.dto.CommentDTO;
import com.ticket.stms.entity.Comment;

import com.ticket.stms.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping(
        "/api/comments"
)

public class CommentController {

    @Autowired

    CommentService commentService;

    @PostMapping

    public Comment addComment(

            @RequestBody

            CommentDTO dto

    ){

        return commentService
                .addComment(
                        dto
                );

    }

    @GetMapping(
            "/ticket/{ticketId}"
    )

    public List<Comment>
    getComments(

            @PathVariable

            Long ticketId

    ){

        return commentService
                .getCommentsByTicket(
                        ticketId
                );

    }

}
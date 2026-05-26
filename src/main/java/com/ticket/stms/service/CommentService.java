package com.ticket.stms.service;

import com.ticket.stms.dto.CommentDTO;
import com.ticket.stms.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(
            CommentDTO dto
    );

    List<Comment> getCommentsByTicket(
            Long ticketId
    );

}
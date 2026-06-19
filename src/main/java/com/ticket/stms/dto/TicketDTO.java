package com.ticket.stms.dto;

import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {

    private String title;

    private String description;

    private String priority;

    private String category;

    private Long userId;

}
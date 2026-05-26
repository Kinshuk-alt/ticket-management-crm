package com.ticket.stms.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Entity

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Table(name="tickets")

public class Ticket {

    @Id

    @GeneratedValue(
            strategy=
                    GenerationType.IDENTITY
    )

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private LocalDateTime createdAt;

    @ManyToOne

    @JoinColumn(
            name="created_by"
    )

    private User createdBy;
    @ManyToOne

    @JoinColumn(
            name="assigned_to"
    )

    private User assignedTo;

}
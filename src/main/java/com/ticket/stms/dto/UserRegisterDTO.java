package com.ticket.stms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

public class UserRegisterDTO {

    @NotBlank(
            message = "Name required"
    )

    private String name;

    @Email(
            message = "Invalid email"
    )

    private String email;

    @NotBlank(
            message = "Password required"
    )

    private String password;

    @NotBlank(
            message = "Role required"
    )

    private String role;

}
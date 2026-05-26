package com.ticket.stms.controller;

import com.ticket.stms.dto.UserRegisterDTO;
import com.ticket.stms.dto.LoginDTO;
import com.ticket.stms.entity.User;
import com.ticket.stms.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/users")

public class UserController {

    @Autowired

    private UserService userService;

    @PostMapping("/register")

    public User registerUser(

            @Valid

            @RequestBody

            UserRegisterDTO dto

    ){

        return userService.registerUser(
                dto
        );

    }
    @PostMapping(
            "/login"
    )

    public String loginUser(

            @RequestBody

            LoginDTO dto

    ){

        return
                userService
                        .loginUser(
                                dto
                        );

    }

    @GetMapping

    public List<User> getUsers(){

        return userService.getAllUsers();

    }

}
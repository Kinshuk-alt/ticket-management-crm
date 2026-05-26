package com.ticket.stms.controller;

import com.ticket.stms.dto.DashboardDTO;

import com.ticket.stms.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(
        "/api/dashboard"
)

public class DashboardController {

    @Autowired

    DashboardService dashboardService;

    @GetMapping

    public DashboardDTO getDashboard(){

        return dashboardService
                .getDashboard();

    }

}
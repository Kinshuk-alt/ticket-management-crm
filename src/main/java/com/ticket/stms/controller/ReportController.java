package com.ticket.stms.controller;

import com.ticket.stms.dto.ReportDTO;

import com.ticket.stms.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(
        "/api/reports"
)

public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping

    public ReportDTO getReport(){

        return reportService
                .generateReport();

    }

}
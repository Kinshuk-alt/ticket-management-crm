package com.ticket.stms.dto;

public class ReportDTO {

    private Long totalTickets;

    private Long openTickets;

    private Long resolvedTickets;

    private Long highPriorityTickets;

    public Long getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(
            Long totalTickets
    ) {
        this.totalTickets = totalTickets;
    }

    public Long getOpenTickets() {
        return openTickets;
    }

    public void setOpenTickets(
            Long openTickets
    ) {
        this.openTickets = openTickets;
    }

    public Long getResolvedTickets() {
        return resolvedTickets;
    }

    public void setResolvedTickets(
            Long resolvedTickets
    ) {
        this.resolvedTickets = resolvedTickets;
    }

    public Long getHighPriorityTickets() {
        return highPriorityTickets;
    }

    public void setHighPriorityTickets(
            Long highPriorityTickets
    ) {
        this.highPriorityTickets = highPriorityTickets;
    }
}
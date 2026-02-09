package com.example.ticket_classifier.controller;

import com.example.ticket_classifier.model.ClassificationResponse;
import com.example.ticket_classifier.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classify")
public class ClassificationController {

    private final TicketService ticketService;

    public ClassificationController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ClassificationResponse classifyTicket(@RequestBody TicketRequest request) {
        return ticketService.classify(request.text());
    }

    public record TicketRequest(String text) {
    }
}
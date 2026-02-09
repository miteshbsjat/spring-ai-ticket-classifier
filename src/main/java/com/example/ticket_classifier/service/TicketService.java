package com.example.ticket_classifier.service;

import com.example.ticket_classifier.model.ClassificationResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final ChatClient chatClient;

    // Spring Boot will automatically inject a ChatClient.Builder bean
    public TicketService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a support ticket classifier. " +
                        "Classify the ticket into: BILLING, TECHNICAL, or GENERAL.")
                .build();
    }

    public ClassificationResponse classify(String text) {
        return chatClient.prompt()
                .user(text)
                .call()
                .entity(ClassificationResponse.class);
    }
}
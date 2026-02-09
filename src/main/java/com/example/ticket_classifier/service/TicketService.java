package com.example.ticket_classifier.service;

import com.example.ticket_classifier.model.ClassificationResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final ChatClient chatClient;

    public TicketService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                        You are a support ticket classifier.
                        1. Classify the ticket into: BILLING, TECHNICAL, or GENERAL.
                        2. Assign a Priority:
                           - HIGH: If the user is angry, mentions money loss, or a broken core feature (like OTP).
                           - MEDIUM: For general technical issues or billing questions.
                           - LOW: For general feedback or subjective statements.
                        """)
                .build();
    }

    public ClassificationResponse classify(String text) {
        return chatClient.prompt()
                .user(text)
                .call()
                .entity(ClassificationResponse.class);
    }
}
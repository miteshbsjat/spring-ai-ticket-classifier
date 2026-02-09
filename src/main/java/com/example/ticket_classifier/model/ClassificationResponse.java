package com.example.ticket_classifier.model;

public record ClassificationResponse(
        Category category,
        Priority priority, // Added Priority field
        String reasoning) {
    public enum Category {
        BILLING, TECHNICAL, GENERAL
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }
}
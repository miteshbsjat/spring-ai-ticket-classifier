package com.example.ticket_classifier.model;

public record ClassificationResponse(Category category, String reasoning) {
    public enum Category {
        BILLING, TECHNICAL, GENERAL
    }
}
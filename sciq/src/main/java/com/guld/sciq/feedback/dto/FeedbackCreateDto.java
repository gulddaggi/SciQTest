package com.guld.sciq.feedback.dto;

public record FeedbackCreateDto(
    Long questionId,
    String content
) {
} 
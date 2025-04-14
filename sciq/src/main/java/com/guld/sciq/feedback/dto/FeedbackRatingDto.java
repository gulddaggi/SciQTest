package com.guld.sciq.feedback.dto;

import java.time.LocalDateTime;

public record FeedbackRatingDto(
    Long ratingId,
    Long userId,
    String userName,
    int rating,
    String comment,
    LocalDateTime createdAt
) {} 
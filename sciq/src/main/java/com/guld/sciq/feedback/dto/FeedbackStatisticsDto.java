package com.guld.sciq.feedback.dto;

import java.util.Map;

public record FeedbackStatisticsDto(
    double averageRating,
    int totalRatings,
    int helpfulCount,
    int notHelpfulCount,
    Map<Integer, Integer> ratingDistribution
) {} 
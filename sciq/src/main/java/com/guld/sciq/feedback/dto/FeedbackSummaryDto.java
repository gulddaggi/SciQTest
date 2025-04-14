package com.guld.sciq.feedback.dto;

import java.util.List;
import java.util.Map;

public record FeedbackSummaryDto(
    int totalFeedbacks,
    double averageRating,
    List<String> topTags,
    List<String> keyInsights,
    Map<String, Integer> sentimentDistribution
) {} 
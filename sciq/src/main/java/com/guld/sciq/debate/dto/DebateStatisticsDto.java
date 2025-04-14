package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record DebateStatisticsDto(
    int totalComments,
    int proComments,
    int conComments,
    int totalLikes,
    int proLikes,
    int conLikes,
    Map<LocalDateTime, Integer> commentTrend,
    Map<LocalDateTime, Integer> likeTrend
) {} 
package com.guld.sciq.debate.dto;

public record DebateTrendDto(
    String category,
    int totalDebates,
    int openDebates,
    int closedDebates,
    double averageDuration,
    int averageParticipants
) {} 
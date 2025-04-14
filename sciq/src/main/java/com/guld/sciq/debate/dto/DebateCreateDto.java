package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

public record DebateCreateDto(
    String title,
    String description,
    String category,
    LocalDateTime scheduledStartTime,
    Integer durationInMinutes
) {} 
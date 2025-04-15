package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;
import com.guld.sciq.common.enums.ScienceDisciplineType;

public record DebateCreateDto(
    String title,
    String description,
    ScienceDisciplineType scienceDiscipline,
    LocalDateTime scheduledStartTime,
    Integer durationInMinutes
) {} 
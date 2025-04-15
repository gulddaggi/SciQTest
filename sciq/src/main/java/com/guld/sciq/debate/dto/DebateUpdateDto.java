package com.guld.sciq.debate.dto;

import com.guld.sciq.common.enums.ScienceDisciplineType;

import java.time.LocalDateTime;

public record DebateUpdateDto(
    String title,
    String description,
    ScienceDisciplineType scienceDiscipline
) {}
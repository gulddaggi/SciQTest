package com.guld.sciq.question.dto;

import com.guld.sciq.common.enums.ScienceDisciplineType;

public record QuestionCreateDto(
    String title,
    String content,
    ScienceDisciplineType scienceDiscipline
) {} 
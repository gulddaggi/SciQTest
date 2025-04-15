package com.guld.sciq.feedback.dto;

import com.guld.sciq.feedback.entity.FeedbackTargetType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "피드백 생성 요청")
public record FeedbackCreateDto(
    @NotBlank @Size(min = 2, max = 100) String title,
    @NotBlank @Size(min = 10, max = 1000) String content,
    @NotNull FeedbackTargetType targetType,
    @NotNull  Long targetId
) {}

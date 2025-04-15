package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

import com.guld.sciq.common.enums.ScienceDisciplineType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "토론 수정 요청")
public record DebateUpdateDto(
    @Schema(description = "토론 제목", example = "인공지능의 윤리적 문제")
    @Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하여야 합니다.")
    String title,

    @Schema(description = "토론 내용", example = "인공지능의 발전으로 인한 윤리적 문제에 대해 논의합니다.")
    @Size(min = 10, max = 1000, message = "내용은 10자 이상 1000자 이하여야 합니다.")
    String description,

    @Schema(description = "과학 분야", example = "COMPUTER_SCIENCE")
    ScienceDisciplineType scienceDiscipline,

    @Schema(description = "예정 시작 시간", example = "2024-03-20T14:00:00")
    LocalDateTime scheduledStartTime,

    @Schema(description = "진행 시간(분)", example = "60")
    Integer durationInMinutes
) {}
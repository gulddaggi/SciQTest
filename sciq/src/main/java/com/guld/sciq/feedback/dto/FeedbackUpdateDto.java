package com.guld.sciq.feedback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(description = "피드백 수정 요청")
public record FeedbackUpdateDto(
    @Schema(description = "피드백 제목", example = "토론 내용이 명확하지 않습니다")
    @Size(min = 2, max = 100, message = "제목은 2자 이상 100자 이하여야 합니다.")
    String title,

    @Schema(description = "피드백 내용", example = "토론의 주제가 너무 광범위하여 집중하기 어렵습니다.")
    @Size(min = 10, max = 1000, message = "내용은 10자 이상 1000자 이하여야 합니다.")
    String content
) {} 
package com.guld.sciq.question.dto;

import com.guld.sciq.common.enums.ScienceDisciplineType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {
    @Schema(description = "질문 제목", example = "양자역학의 기본 원리는 무엇인가요?")
    private String title;

    @Schema(description = "질문 내용", example = "양자역학의 기본 원리에 대해 자세히 설명해주세요.")
    private String content;

    @Schema(description = "과학 분야", example = "PHYSICS")
    private ScienceDisciplineType scienceDiscipline;
} 
package com.guld.sciq.question.dto;

import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    @Schema(description = "질문 ID", example = "1")
    private Long id;

    @Schema(description = "질문 작성자 정보")
    private User user;

    @Schema(description = "질문 제목", example = "양자역학의 기본 원리는 무엇인가요?")
    private String title;

    @Schema(description = "질문 내용", example = "양자역학의 기본 원리에 대해 자세히 설명해주세요.")
    private String content;

    @Schema(description = "과학 분야", example = "PHYSICS")
    private ScienceDisciplineType scienceDiscipline;

    @Schema(description = "추천 수", example = "0")
    private Integer recommendCnt;

    @Schema(description = "생성일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;

    public static QuestionResponse from(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .user(question.getUser())
                .createdAt(question.getCreatedAt())
                .updatedAt(question.getUpdatedAt())
                .build();
    }
} 
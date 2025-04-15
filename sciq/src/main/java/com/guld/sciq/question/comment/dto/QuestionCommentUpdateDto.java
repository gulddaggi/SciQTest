package com.guld.sciq.question.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCommentUpdateDto {
    @Schema(description = "댓글 내용", example = "좋은 질문이네요!")
    private String content;
} 
package com.guld.sciq.question.comment.dto;

import com.guld.sciq.question.comment.entity.QuestionComment;
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
public class QuestionCommentDto {
    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "댓글 작성자 닉네임", example = "홍길동")
    private String userNickName;

    @Schema(description = "댓글 내용", example = "좋은 질문이네요!")
    private String content;

    @Schema(description = "좋아요 수", example = "0")
    private Integer likeCnt;

    @Schema(description = "생성일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;

    public static QuestionCommentDto from(QuestionComment comment) {
        return QuestionCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .userNickName(comment.getUserNickName())
                .likeCnt(comment.getLikeCnt())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
} 
package com.guld.sciq.question.dto;

import java.time.LocalDateTime;

import com.guld.sciq.question.entity.QuestionComment;
import com.guld.sciq.user.dto.UserDto;

public record QuestionCommentDto(
    Integer commentId,
    Long questionId,
    UserDto user,
    String content,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static QuestionCommentDto from(QuestionComment comment) {
        return new QuestionCommentDto(
            comment.getCommentId(),
            comment.getQuestion().getId(),
            null, // userId만 있으므로 null로 설정
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getUpdatedAt()
        );
    }
} 
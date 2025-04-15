package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

import com.guld.sciq.debate.entity.DebateComment;
import com.guld.sciq.debate.entity.DebateStance;

public record DebateCommentDto(
    Long id,
    Long debateId,
    Long userId,
    String userNickName,
    String content,
    DebateStance stance,
    LocalDateTime createdAt,
    Integer likes
) {
    public static DebateCommentDto from(DebateComment comment) {
        return new DebateCommentDto(
            comment.getId(),
            comment.getDebate().getId(),
            comment.getUserId(),
            comment.getUserNickName(),
            comment.getContent(),
            comment.getStance(),
            comment.getCreatedAt(),
            comment.getLikes()
        );
    }
} 
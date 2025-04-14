package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

public record DebateCommentDto(
    Long commentId,
    Long debateId,
    String nickname,
    String stance,
    String content,
    LocalDateTime createdAt,
    Integer likes
) {
} 
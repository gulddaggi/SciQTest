package com.guld.sciq.debate.comment.dto;

import com.guld.sciq.debate.comment.entity.DebateComment;
import com.guld.sciq.debate.entity.DebateStance;
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
public class DebateCommentDto {
    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 작성자 ID", example = "1")
    private Long userId;

    @Schema(description = "댓글 작성자 닉네임", example = "홍길동")
    private String userNickName;

    @Schema(description = "댓글 내용", example = "좋은 토론이네요!")
    private String content;

    @Schema(description = "토론 입장", example = "PRO")
    private DebateStance stance;

    @Schema(description = "좋아요 수", example = "0")
    private Integer likeCnt;

    @Schema(description = "생성일시")
    private LocalDateTime createdAt;

    @Schema(description = "수정일시")
    private LocalDateTime updatedAt;

    public static DebateCommentDto from(DebateComment comment) {
        return DebateCommentDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .userId(comment.getUserId())
                .userNickName(comment.getUserNickName())
                .stance(comment.getStance())
                .likeCnt(comment.getLikeCnt())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }
} 
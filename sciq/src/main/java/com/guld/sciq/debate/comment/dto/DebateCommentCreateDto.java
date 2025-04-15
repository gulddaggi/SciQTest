package com.guld.sciq.debate.comment.dto;

import com.guld.sciq.debate.entity.DebateStance;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebateCommentCreateDto {
    @Schema(description = "댓글 내용", example = "좋은 토론이네요!")
    private String content;

    @Schema(description = "토론 입장", example = "PRO")
    private DebateStance stance;
} 
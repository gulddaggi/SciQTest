package com.guld.sciq.feedback.dto;

import com.guld.sciq.feedback.entity.Feedback;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "피드백 응답")
public record FeedbackDto(
    @Schema(description = "피드백 ID", example = "1")
    Long id,
    @Schema(description = "피드백 내용", example = "토론의 주제가 너무 광범위하여 집중하기 어렵습니다.")
    String content,

    @Schema(description = "피드백 작성자 ID", example = "1")
    Long userId,

    @Schema(description = "피드백 작성자 닉네임", example = "user123")
    String userNickName,

    @Schema(description = "피드백 대상 토론 ID", example = "1")
    Long debateId,

    @Schema(description = "도움이 됨 수", example = "5")
    int helpfulCount,

    @Schema(description = "생성일시")
    LocalDateTime createdAt,

    @Schema(description = "수정일시")
    LocalDateTime updatedAt
) {
    public static FeedbackDto from(Feedback feedback) {
        return new FeedbackDto(
            feedback.getId(),
            feedback.getContent(),
            feedback.getUser().getId(),
            feedback.getUser().getNickName(),
            feedback.getDebate().getId(),
            feedback.getHelpfulCount(),
            feedback.getCreatedAt(),
            feedback.getUpdatedAt()
        );
    }
}
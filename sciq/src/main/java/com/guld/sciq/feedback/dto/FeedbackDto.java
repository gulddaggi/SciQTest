package com.guld.sciq.feedback.dto;

import java.time.LocalDateTime;

import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.question.entity.Question;

public record FeedbackDto(
    Long feedbackId,
    Long questionId,
    Long userId,
    String content,
    Integer helpfulCount,
    Integer notHelpfulCount,
    LocalDateTime createdAt
) {
	public static FeedbackDto from(Feedback feedback) {
		return new FeedbackDto(
			feedback.getFeedbackId(),
			feedback.getQuestion().getId(),
			feedback.getUserId(),
			feedback.getContent(),
			feedback.getHelpfulCount(),
			feedback.getNotHelpfulCount(),
			feedback.getCreatedAt()
		);
	}
}
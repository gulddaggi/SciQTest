package com.guld.sciq.feedback.dto;

import java.time.LocalDateTime;

import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.debate.dto.DebateDto;

public record FeedbackDto(
    Long feedbackId,
    Long userId,
    QuestionDto question,
    DebateDto debate,
    String content,
    Integer helpfulCount,
    Integer notHelpfulCount,
    LocalDateTime createdAt
) {
	public static FeedbackDto from(Feedback feedback) {
		return new FeedbackDto(
			feedback.getFeedbackId(),
			feedback.getUserId(),
			feedback.getQuestion() != null ? QuestionDto.from(feedback.getQuestion()) : null,
			feedback.getDebate() != null ? DebateDto.from(feedback.getDebate()) : null,
			feedback.getContent(),
			feedback.getHelpfulCount(),
			feedback.getNotHelpfulCount(),
			feedback.getCreatedAt()
		);
	}
}
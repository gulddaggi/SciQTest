package com.guld.sciq.feedback.dto;

import java.time.LocalDateTime;

import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.user.dto.UserDto;

public record FeedbackDto(
    Long feedbackId,
    UserDto user,
    QuestionDto question,
    DebateDto debate,
    String content,
    Integer helpfulCount,
    LocalDateTime createdAt
) {
	public static FeedbackDto from(Feedback feedback) {
		return new FeedbackDto(
			feedback.getFeedbackId(),
			UserDto.from(feedback.getUser()),
			feedback.getQuestion() != null ? QuestionDto.from(feedback.getQuestion()) : null,
			feedback.getDebate() != null ? DebateDto.from(feedback.getDebate()) : null,
			feedback.getContent(),
			feedback.getHelpfulCount(),
			feedback.getCreatedAt()
		);
	}
}
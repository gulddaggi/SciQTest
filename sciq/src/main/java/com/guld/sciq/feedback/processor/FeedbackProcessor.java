package com.guld.sciq.feedback.processor;

import org.springframework.stereotype.Component;

import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;
import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.feedback.repository.FeedbackRepository;
import com.guld.sciq.global.exception.FeedbackNotFoundException;
import com.guld.sciq.global.exception.QuestionNotFoundException;
import com.guld.sciq.global.exception.UnauthorizedException;
import com.guld.sciq.global.exception.UserNotFoundException;
import com.guld.sciq.global.exception.DebateNotFoundException;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FeedbackProcessor {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final DebateRepository debateRepository;

    public FeedbackDto createFeedback(FeedbackCreateDto createDto, Long userId, Long questionId, Long debateId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        Question question = null;
        if (questionId != null) {
            question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        }

        Debate debate = null;
        if (debateId != null) {
            debate = debateRepository.findById(debateId)
                    .orElseThrow(() -> new DebateNotFoundException("Debate not found"));
        }

        if (question == null && debate == null) {
            throw new IllegalArgumentException("Either questionId or debateId must be provided");
        }

        Feedback feedback = Feedback.builder()
                .content(createDto.content())
                .question(question)
                .debate(debate)
                .userId(userId)
                .helpfulCount(0)
                .notHelpfulCount(0)
                .build();

        Feedback savedFeedback = feedbackRepository.save(feedback);
        return FeedbackDto.from(savedFeedback);
    }

    public FeedbackDto getFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));
        return FeedbackDto.from(feedback);
    }

    public FeedbackDto updateFeedback(Long feedbackId, FeedbackUpdateDto updateDto, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));

        if (!feedback.getUserId().equals(userId)) {
            throw new UnauthorizedException("Only the feedback creator can update the feedback");
        }

        feedback.updateContent(updateDto.content());
        Feedback updatedFeedback = feedbackRepository.save(feedback);
        return FeedbackDto.from(updatedFeedback);
    }

    public void deleteFeedback(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));

        if (!feedback.getUserId().equals(userId)) {
            throw new UnauthorizedException("Only the feedback creator can delete the feedback");
        }

        feedbackRepository.delete(feedback);
    }

    
    public void markAsHelpful(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));

        feedback.increaseHelpfulCount();
        feedbackRepository.save(feedback);
    }

    public void markAsNotHelpful(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found"));

        feedback.increaseNotHelpfulCount();
        feedbackRepository.save(feedback);
    }
}
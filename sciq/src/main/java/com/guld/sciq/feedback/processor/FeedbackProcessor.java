package com.guld.sciq.feedback.processor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;
import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.feedback.entity.FeedbackLike;
import com.guld.sciq.feedback.entity.FeedbackTargetType;
import com.guld.sciq.feedback.repository.FeedbackLikeRepository;
import com.guld.sciq.feedback.repository.FeedbackRepository;
import com.guld.sciq.global.exception.*;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FeedbackProcessor {
    
    private final FeedbackRepository feedbackRepository;
    private final FeedbackLikeRepository feedbackLikeRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final DebateRepository debateRepository;
    
    
    @Transactional
    public FeedbackDto createFeedback(FeedbackCreateDto dto,
        Long userId) {
        
        switch (dto.targetType()) {
            case QUESTION -> fetchQuestion(dto.targetId());
            case DEBATE   -> fetchDebate(dto.targetId());
            default       -> throw new IllegalStateException(ErrorMessage.FEEDBACK_TYPE_REQUIRED);
        }
        
        User user = fetchUser(userId);
        
        Feedback feedback = Feedback.builder()
            .user(user)
            .content(dto.content())
            .question(dto.targetType() == FeedbackTargetType.QUESTION
                ? fetchQuestion(dto.targetId())
                : null)
            .debate(dto.targetType() == FeedbackTargetType.DEBATE
                ? fetchDebate(dto.targetId())
                : null)
            .helpfulCount(0)
            .build();
        
        return FeedbackDto.from(feedbackRepository
            .save(feedback));
    }
    
    
    public FeedbackDto getFeedback(Long id) {
        return FeedbackDto.from(fetchFeedback(id));
    }
    
    
    @Transactional
    public FeedbackDto updateFeedback(Long id, FeedbackUpdateDto dto, Long userId) {
        Feedback feedback = fetchFeedback(id);
        verifyOwner(feedback, userId);
        
        feedback.updateContent(dto.content());
        return FeedbackDto.from(feedbackRepository
            .save(feedback));
    }
    
    
    @Transactional
    public void deleteFeedback(Long id, Long userId) {
        Feedback feedback = fetchFeedback(id);
        verifyOwner(feedback, userId);
        feedbackRepository
            .delete(feedback);
    }
    
    
    @Transactional
    public void markHelpful(Long feedbackId, Long userId) {
        Feedback feedback = fetchFeedback(feedbackId);
        User     user     = fetchUser(userId);
        
        FeedbackLike like = feedbackLikeRepository
            .findByFeedbackAndUser(feedback, user)
            .orElseGet(() -> new FeedbackLike(feedback, user));
        
        feedbackLikeRepository.save(like);
    }
    
    private Feedback fetchFeedback(Long id) {
        return feedbackRepository
            .findById(id)
            .orElseThrow(() -> new FeedbackNotFoundException(ErrorMessage.FEEDBACK_NOT_FOUND));
    }
    
    private User fetchUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }
    
    private Question fetchQuestion(Long id) {
        return questionRepository.findById(id)
            .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
    }
    
    private Debate fetchDebate(Long id) {
        return debateRepository.findById(id)
            .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));
    }
    
    private void verifyOwner(Feedback feedback, Long userId) {
        if (!feedback.getUser().getId().equals(userId))
            throw new UnauthorizedException(ErrorMessage.FEEDBACK_NOT_OWNER);
    }
}

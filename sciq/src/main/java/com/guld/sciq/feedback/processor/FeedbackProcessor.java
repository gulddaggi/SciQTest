package com.guld.sciq.feedback.processor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;
import com.guld.sciq.feedback.entity.Feedback;
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
    
    private final FeedbackRepository feedbackRepo;
    private final UserRepository userRepo;
    private final QuestionRepository questionRepo;
    private final DebateRepository debateRepo;
    
    
    @Transactional
    public FeedbackDto createFeedback(FeedbackCreateDto dto,
        Long userId,
        Long questionId,
        Long debateId) {
        
        if (questionId == null && debateId == null)
            throw new IllegalArgumentException(ErrorMessage.FEEDBACK_TARGET_REQUIRED);
        
        User     user     = fetchUser(userId);
        Question question = questionId != null ? fetchQuestion(questionId) : null;
        Debate   debate   = debateId   != null ? fetchDebate(debateId)     : null;
        
        Feedback feedback = Feedback.builder()
            .content(dto.content())
            .question(question)
            .debate(debate)
            .user(user)
            .helpfulCount(0)
            .build();
        
        return FeedbackDto.from(feedbackRepo.save(feedback));
    }
    
    
    public FeedbackDto getFeedback(Long id) {
        return FeedbackDto.from(fetchFeedback(id));
    }
    
    
    @Transactional
    public FeedbackDto updateFeedback(Long id, FeedbackUpdateDto dto, Long userId) {
        Feedback feedback = fetchFeedback(id);
        verifyOwner(feedback, userId);
        
        feedback.updateContent(dto.content());
        return FeedbackDto.from(feedbackRepo.save(feedback));
    }
    
    
    @Transactional
    public void deleteFeedback(Long id, Long userId) {
        Feedback feedback = fetchFeedback(id);
        verifyOwner(feedback, userId);
        feedbackRepo.delete(feedback);
    }
    
    
    @Transactional
    public void markHelpful(Long id) {
        fetchFeedback(id).increaseHelpfulCount();   // Dirty checking으로 자동 flush
    }
    
    private Feedback fetchFeedback(Long id) {
        return feedbackRepo.findById(id)
            .orElseThrow(() -> new FeedbackNotFoundException(ErrorMessage.FEEDBACK_NOT_FOUND));
    }
    
    private User fetchUser(Long id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }
    
    private Question fetchQuestion(Long id) {
        return questionRepo.findById(id)
            .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
    }
    
    private Debate fetchDebate(Long id) {
        return debateRepo.findById(id)
            .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));
    }
    
    private void verifyOwner(Feedback feedback, Long userId) {
        if (!feedback.getUser().getId().equals(userId))
            throw new UnauthorizedException(ErrorMessage.FEEDBACK_NOT_OWNER);
    }
}

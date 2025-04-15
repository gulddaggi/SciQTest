package com.guld.sciq.feedback.service;

import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;

import java.util.List;

public interface FeedbackService {
    // 기본 CRUD
    FeedbackDto createFeedback(FeedbackCreateDto createDto, Long userId);
    FeedbackDto getFeedback(Long feedbackId);
    FeedbackDto updateFeedback(Long feedbackId, FeedbackUpdateDto updateDto, Long userId);
    void deleteFeedback(Long feedbackId, Long userId);
    
    // 평가 관련
    void markAsHelpful(Long feedbackId, Long userId);
    
    // 조회
    List<FeedbackDto> getFeedbacksByQuestion(Long questionId);
    List<FeedbackDto> getFeedbacksByDebate(Long debateId);
    List<FeedbackDto> getFeedbacksByUser(Long userId);
    List<FeedbackDto> getTopRatedFeedbacks(Long questionId, int limit);
} 
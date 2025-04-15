package com.guld.sciq.feedback.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;
import com.guld.sciq.feedback.processor.FeedbackProcessor;
import com.guld.sciq.feedback.repository.FeedbackRepository;
import com.guld.sciq.feedback.service.FeedbackService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackProcessor feedbackProcessor;

    @Override
    public FeedbackDto createFeedback(FeedbackCreateDto createDto, Long userId) {
        return feedbackProcessor.createFeedback(createDto, userId);
    }

    @Override
    public FeedbackDto getFeedback(Long feedbackId) {
        return feedbackProcessor.getFeedback(feedbackId);
    }

    @Override
    public FeedbackDto updateFeedback(Long feedbackId, FeedbackUpdateDto updateDto, Long userId) {
        return feedbackProcessor.updateFeedback(feedbackId, updateDto, userId);
    }

    @Override
    public void deleteFeedback(Long feedbackId, Long userId) {
        feedbackProcessor.deleteFeedback(feedbackId, userId);
    }

    @Override
    public void markAsHelpful(Long feedbackId, Long userId) {
        //TODO: 유저도 트래킹 할건지? 한번만 가능하게 할건지?
        feedbackProcessor.markHelpful(feedbackId, userId);
    }
    
    @Override
    public List<FeedbackDto> getFeedbacksByQuestion(Long questionId) {
        return feedbackRepository.findByQuestionId(questionId)
                .stream()
                .map(FeedbackDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDto> getFeedbacksByDebate(Long debateId) {
        return feedbackRepository.findByDebateId(debateId)
                .stream()
                .map(FeedbackDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<FeedbackDto> getFeedbacksByUser(Long userId) {
        return feedbackRepository.findByUserId(userId)
                .stream()
                .map(FeedbackDto::from)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<FeedbackDto> getTopRatedFeedbacks(Long questionId, int limit) {
        return null;
    }
}
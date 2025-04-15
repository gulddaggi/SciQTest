package com.guld.sciq.feedback.service.impl;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.feedback.entity.FeedbackLike;
import com.guld.sciq.feedback.repository.FeedbackLikeRepository;
import com.guld.sciq.feedback.repository.FeedbackRepository;
import com.guld.sciq.feedback.service.FeedbackLikeService;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedbackLikeServiceImpl implements FeedbackLikeService {

    private final FeedbackLikeRepository feedbackLikeRepository;
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void likeFeedback(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FEEDBACK_NOT_FOUND));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USER_NOT_FOUND));

        if (!hasLikedFeedback(feedbackId, userId)) {
            FeedbackLike feedbackLike = FeedbackLike.create(feedback, user);
            feedbackLikeRepository.save(feedbackLike);
        }
    }

    @Override
    @Transactional
    public void unlikeFeedback(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FEEDBACK_NOT_FOUND));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USER_NOT_FOUND));

        feedbackLikeRepository.deleteByFeedbackAndUser(feedback, user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasLikedFeedback(Long feedbackId, Long userId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FEEDBACK_NOT_FOUND));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.USER_NOT_FOUND));

        return feedbackLikeRepository.existsByFeedbackAndUser(feedback, user);
    }

    @Override
    @Transactional(readOnly = true)
    public long getLikeCount(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FEEDBACK_NOT_FOUND));
        
        return feedbackLikeRepository.countByFeedback(feedback);
    }
} 
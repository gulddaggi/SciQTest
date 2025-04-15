package com.guld.sciq.feedback.service;

public interface FeedbackLikeService {
    void likeFeedback(Long feedbackId, Long userId);
    void unlikeFeedback(Long feedbackId, Long userId);
    boolean hasLikedFeedback(Long feedbackId, Long userId);
    long getLikeCount(Long feedbackId);
} 
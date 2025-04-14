package com.guld.sciq.feedback.repository;

import com.guld.sciq.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByQuestionId(Long questionId);
    List<Feedback> findByDebateId(Long debateId);
    List<Feedback> findByUserId(Long userId);
} 
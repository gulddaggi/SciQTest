package com.guld.sciq.feedback.repository;

import com.guld.sciq.feedback.entity.Feedback;
import com.guld.sciq.feedback.entity.FeedbackLike;
import com.guld.sciq.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackLikeRepository extends JpaRepository<FeedbackLike, Long> {
    Optional<FeedbackLike> findByFeedbackAndUser(Feedback feedback, User user);
    boolean existsByFeedbackAndUser(Feedback feedback, User user);
    void deleteByFeedbackAndUser(Feedback feedback, User user);
    long countByFeedback(Feedback feedback);
} 
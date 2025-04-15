package com.guld.sciq.feedback.entity;

import com.guld.sciq.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback_likes",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"feedback_id", "user_id"})
    }
)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedbackLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public FeedbackLike(Feedback feedback, User user) {
        this.feedback = feedback;
        this.user = user;
    }

    public static FeedbackLike create(Feedback feedback, User user) {
        return FeedbackLike.builder()
            .feedback(feedback)
            .user(user)
            .build();
    }
}
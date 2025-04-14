package com.guld.sciq.feedback.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.debate.entity.Debate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_tb")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "debate_id")
    private Debate debate;

    @Column(name = "user_id")
    private Long userId;

    @Column
    private String content;

    @Column
    private int helpfulCount = 0;

    @Column
    private int notHelpfulCount = 0;

    @Column
    private LocalDateTime createdAt;

    public void increaseHelpfulCount() {
        this.helpfulCount++;
    }

    public void increaseNotHelpfulCount() {
        this.notHelpfulCount++;
    }

    public void updateContent(String content) {
        this.content = content;
    }
} 
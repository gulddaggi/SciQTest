package com.guld.sciq.question.entity;

import com.guld.sciq.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question_comment_tb")
@Getter
@Setter
public class QuestionComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column
    private String content;
}
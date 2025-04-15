package com.guld.sciq.question.entity;

import com.guld.sciq.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "question_comment_tb")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public void updateContent(String content) {
        this.content = content;
    }
}
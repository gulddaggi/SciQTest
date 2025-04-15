package com.guld.sciq.question.comment.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.question.entity.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "question_comment_tb")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_nickname", nullable = false)
    private String userNickName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer likeCnt;

    public void updateContent(String content) {
        this.content = content;
    }

    public void increaseLikeCnt() {
        this.likeCnt++;
    }

    public void decreaseLikeCnt() {
        if (this.likeCnt > 0) {
            this.likeCnt--;
        }
    }
}
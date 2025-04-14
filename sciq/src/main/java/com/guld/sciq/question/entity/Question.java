package com.guld.sciq.question.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "question_tb")
@Getter
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private Integer recommendCnt = 0;
}
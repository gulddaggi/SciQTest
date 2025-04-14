package com.guld.sciq.debate.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "debate_comment_tb")
@Getter
public class DebateComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "debate_id", nullable = false)
    private Debate debate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column
    private DebateStance stance;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer likes = 0;
}
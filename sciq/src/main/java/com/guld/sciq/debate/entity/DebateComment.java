package com.guld.sciq.debate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "debate_comment_tb")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebateComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "debate_id", nullable = false)
    private Debate debate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column
    private String userNickName;
    
    @Enumerated(EnumType.STRING)
    @Column
    private DebateStance stance;

    @Column
    private String content;

    @Column
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer likes = 0;

    public void update(String content, DebateStance stance) {
        this.content = content;
        this.stance = stance;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreseLikes(){
        if (this.likes > 0) {
            this.likes--;
        }
    }
}
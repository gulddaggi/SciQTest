package com.guld.sciq.question.entity;

import com.guld.sciq.config.BaseEntity;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "question_tb")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScienceDisciplineType scienceDiscipline;

    @Column
    private Integer recommendCnt = 0;
}
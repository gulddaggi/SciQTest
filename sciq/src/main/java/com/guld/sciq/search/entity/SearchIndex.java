package com.guld.sciq.search.entity;

import com.guld.sciq.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "search_index_tb")
@Getter
@Setter
public class SearchIndex extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indexId;

    @Column(nullable = false)
    private String contentType;  // DEBATE, QUESTION, FEEDBACK

    @Column(nullable = false)
    private Long contentId;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    private String category;

    @Column
    private String tags;

    @Column
    private Integer relevanceScore = 0;
} 
package com.guld.sciq.search.repository;

import com.guld.sciq.search.entity.SearchIndex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SearchIndexRepository extends JpaRepository<SearchIndex, Long> {
    List<SearchIndex> findByContentTypeAndTitleContaining(String contentType, String keyword);
    List<SearchIndex> findByContentTypeAndCategory(String contentType, String category);
    List<SearchIndex> findByContentTypeAndTagsContaining(String contentType, String tag);
    List<SearchIndex> findByContentTypeAndRelevanceScoreGreaterThanEqual(String contentType, Integer minScore);
    List<SearchIndex> findByContentTypeAndCreatedAtBetween(String contentType, LocalDateTime start, LocalDateTime end);
} 
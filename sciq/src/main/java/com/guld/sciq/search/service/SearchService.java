package com.guld.sciq.search.service;

import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.search.dto.SearchFilter;
import com.guld.sciq.search.dto.SearchResultDto;

import java.util.List;

public interface SearchService {
    // 통합 검색
    SearchResultDto search(String keyword, SearchFilter filter);
    SearchResultDto searchByCategory(String category, SearchFilter filter);
    SearchResultDto searchByTags(List<String> tags, SearchFilter filter);
    
    // 토론 전용 검색
    List<DebateDto> searchDebates(String keyword, SearchFilter filter);
    List<DebateDto> searchOpenDebates(SearchFilter filter);
    List<DebateDto> searchUpcomingDebates(SearchFilter filter);
    
    // 인덱스 관리
    void indexContent(String contentType, Long contentId);
    void updateIndex(String contentType, Long contentId);
    void deleteIndex(String contentType, Long contentId);
} 
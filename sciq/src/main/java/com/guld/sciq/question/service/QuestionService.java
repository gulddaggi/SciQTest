package com.guld.sciq.question.service;

import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.common.enums.ScienceDisciplineType;

import java.util.List;

public interface QuestionService {
    // 기본 CRUD
    QuestionDto createQuestion(QuestionCreateDto createDto, Long userId);
    QuestionDto getQuestion(Long questionId);
    QuestionDto updateQuestion(Long questionId, QuestionUpdateDto updateDto, Long userId);
    void deleteQuestion(Long questionId, Long userId);
    
    // 추천 관련
    void recommendQuestion(Long questionId, Long userId);
    
    // 조회
    List<QuestionDto> getQuestionsByUser(Long userId);
    List<QuestionDto> getQuestionsByScienceDiscipline(ScienceDisciplineType scienceDiscipline);
    List<QuestionDto> getTopRatedQuestions(int limit);
    List<QuestionDto> getRecentQuestions(int limit);
} 
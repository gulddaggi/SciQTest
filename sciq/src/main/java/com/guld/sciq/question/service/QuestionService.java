package com.guld.sciq.question.service;

import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public interface QuestionService {
    QuestionDto createQuestion(QuestionCreateDto createDto, Long userId);
    QuestionDto getQuestion(Long questionId);
    List<QuestionDto> getAllQuestions();
    QuestionDto updateQuestion(Long questionId, QuestionUpdateDto updateDto, Long userId);
    void deleteQuestion(Long questionId, Long userId);
    void recommendQuestion(Long questionId, Long userId);
    List<QuestionDto> getQuestionsByUser(Long userId);
    List<QuestionDto> getQuestionsByScienceDiscipline(ScienceDisciplineType scienceDiscipline);
    List<QuestionDto> getTopRatedQuestions(int limit);
    List<QuestionDto> getRecentQuestions(int limit);
}
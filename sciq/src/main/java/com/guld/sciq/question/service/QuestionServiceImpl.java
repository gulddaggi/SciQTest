package com.guld.sciq.question.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.question.processor.QuestionProcessor;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.common.enums.ScienceDisciplineType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionProcessor questionProcessor;

    @Override
    public QuestionDto createQuestion(QuestionCreateDto createDto, Long userId) {
        return questionProcessor.createQuestion(createDto, userId);
    }

    @Override
    public QuestionDto getQuestion(Long questionId) {
        return questionProcessor.getQuestion(questionId);
    }

    @Override
    public QuestionDto updateQuestion(Long questionId, QuestionUpdateDto updateDto, Long userId) {
        return questionProcessor.updateQuestion(questionId, updateDto, userId);
    }

    @Override
    public void deleteQuestion(Long questionId, Long userId) {
        questionProcessor.deleteQuestion(questionId, userId);
    }

    @Override
    public void recommendQuestion(Long questionId, Long userId) {
        questionProcessor.recommendQuestion(questionId, userId);
    }

    @Override
    public List<QuestionDto> getQuestionsByUser(Long userId) {
        return questionRepository.findByUserId(userId)
                .stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getQuestionsByScienceDiscipline(ScienceDisciplineType scienceDiscipline) {
        return questionRepository.findByScienceDiscipline(scienceDiscipline)
                .stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getTopRatedQuestions(int limit) {
        return questionRepository.findTopRatedQuestions(limit)
                .stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionDto> getRecentQuestions(int limit) {
        return questionRepository.findRecentQuestions(limit)
                .stream()
                .map(QuestionDto::from)
                .collect(Collectors.toList());
    }
} 
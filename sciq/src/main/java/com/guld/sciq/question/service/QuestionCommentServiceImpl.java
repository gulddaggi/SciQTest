package com.guld.sciq.question.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guld.sciq.question.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.dto.QuestionCommentDto;
import com.guld.sciq.question.processor.QuestionCommentProcessor;
import com.guld.sciq.question.repository.QuestionCommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService {
    private final QuestionCommentProcessor questionCommentProcessor;
    private final QuestionCommentRepository questionCommentRepository;

    @Override
    public QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId) {
        return questionCommentProcessor.createComment(createDto, questionId, userId);
    }

    @Override
    public QuestionCommentDto getComment(Integer commentId) {
        return questionCommentProcessor.getComment(commentId);
    }

    @Override
    public QuestionCommentDto updateComment(Integer commentId, QuestionCommentCreateDto updateDto, Long userId) {
        return questionCommentProcessor.updateComment(commentId, updateDto, userId);
    }

    @Override
    public void deleteComment(Integer commentId, Long userId) {
        questionCommentProcessor.deleteComment(commentId, userId);
    }

    @Override
    public void likeComment(Integer commentId, Long userId) {
        questionCommentProcessor.likeComment(commentId, userId);
    }

    @Override
    public List<QuestionCommentDto> getCommentsByQuestion(Long questionId) {
        return questionCommentRepository.findByQuestionId(questionId)
                .stream()
                .map(QuestionCommentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuestionCommentDto> getCommentsByUser(Long userId) {
        return questionCommentRepository.findByUserId(userId)
                .stream()
                .map(QuestionCommentDto::from)
                .collect(Collectors.toList());
    }
} 
package com.guld.sciq.question.comment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guld.sciq.question.comment.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.comment.dto.QuestionCommentDto;
import com.guld.sciq.question.comment.dto.QuestionCommentUpdateDto;
import com.guld.sciq.question.comment.processor.QuestionCommentProcessor;
import com.guld.sciq.question.comment.repository.QuestionCommentRepository;
import com.guld.sciq.question.comment.service.QuestionCommentService;
import com.guld.sciq.question.dto.QuestionUpdateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionCommentServiceImpl implements QuestionCommentService {
    private final QuestionCommentProcessor questionCommentProcessor;
    private final QuestionCommentRepository questionCommentRepository;

    @Override
    @Transactional
    public QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId) {
        return questionCommentProcessor.createComment(createDto, questionId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionCommentDto getComment(Long commentId) {
        return questionCommentProcessor.getComment(commentId);
    }

    @Override
    @Transactional
    public QuestionCommentDto updateComment(Long commentId, QuestionCommentUpdateDto updateDto, Long userId) {
        return questionCommentProcessor.updateComment(commentId, updateDto, userId);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        questionCommentProcessor.deleteComment(commentId, userId);
    }

    @Override
    @Transactional
    public void likeComment(Long commentId, Long userId) {
        questionCommentProcessor.likeComment(commentId, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionCommentDto> getCommentsByQuestion(Long questionId) {
        return questionCommentRepository.findByQuestionId(questionId)
                .stream()
                .map(QuestionCommentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionCommentDto> getCommentsByUser(Long userId) {
        return questionCommentRepository.findByUserId(userId)
                .stream()
                .map(QuestionCommentDto::from)
                .collect(Collectors.toList());
    }
} 
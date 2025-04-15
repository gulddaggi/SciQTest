package com.guld.sciq.question.service;

import com.guld.sciq.question.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.dto.QuestionCommentDto;

import java.util.List;

public interface QuestionCommentService {
    QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId);
    QuestionCommentDto getComment(Integer commentId);
    QuestionCommentDto updateComment(Integer commentId, QuestionCommentCreateDto updateDto, Long userId);
    void deleteComment(Integer commentId, Long userId);
    void likeComment(Integer commentId, Long userId);
    List<QuestionCommentDto> getCommentsByQuestion(Long questionId);
    List<QuestionCommentDto> getCommentsByUser(Long userId);
} 
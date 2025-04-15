package com.guld.sciq.question.comment.service;

import com.guld.sciq.question.comment.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.comment.dto.QuestionCommentDto;
import com.guld.sciq.question.comment.dto.QuestionCommentUpdateDto;

import java.util.List;

public interface QuestionCommentService {
    QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId);
    QuestionCommentDto getComment(Long commentId);
    QuestionCommentDto updateComment(Long commentId, QuestionCommentUpdateDto updateDto, Long userId);
    void deleteComment(Long commentId, Long userId);
    void likeComment(Long commentId, Long userId);
    List<QuestionCommentDto> getCommentsByQuestion(Long questionId);
    List<QuestionCommentDto> getCommentsByUser(Long userId);
} 
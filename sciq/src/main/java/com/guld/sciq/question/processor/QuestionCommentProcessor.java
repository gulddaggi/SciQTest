package com.guld.sciq.question.processor;

import org.springframework.stereotype.Component;

import com.guld.sciq.question.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.dto.QuestionCommentDto;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.entity.QuestionComment;
import com.guld.sciq.question.repository.QuestionCommentRepository;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.global.exception.QuestionCommentNotFoundException;
import com.guld.sciq.global.exception.QuestionNotFoundException;
import com.guld.sciq.global.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionCommentProcessor {
    private final QuestionCommentRepository questionCommentRepository;
    private final QuestionRepository questionRepository;

    public QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        QuestionComment comment = QuestionComment.builder()
                .question(question)
                .userId(userId)
                .content(createDto.content())
                .build();

        QuestionComment savedComment = questionCommentRepository.save(comment);
        return QuestionCommentDto.from(savedComment);
    }

    public QuestionCommentDto getComment(Integer commentId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException("Comment not found"));
        return QuestionCommentDto.from(comment);
    }

    public QuestionCommentDto updateComment(Integer commentId, QuestionCommentCreateDto updateDto, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException("Comment not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new UnauthorizedException("Only the comment creator can update the comment");
        }

        comment.updateContent(updateDto.content());
        QuestionComment updatedComment = questionCommentRepository.save(comment);
        return QuestionCommentDto.from(updatedComment);
    }

    public void deleteComment(Integer commentId, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException("Comment not found"));

        if (!comment.getUserId().equals(userId)) {
            throw new UnauthorizedException("Only the comment creator can delete the comment");
        }

        questionCommentRepository.delete(comment);
    }

    public void likeComment(Integer commentId, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException("Comment not found"));
        // TODO: 좋아요 기능 구현
    }
} 
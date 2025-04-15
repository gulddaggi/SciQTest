package com.guld.sciq.question.comment.processor;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.global.exception.QuestionCommentNotFoundException;
import com.guld.sciq.question.comment.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.comment.dto.QuestionCommentDto;
import com.guld.sciq.question.comment.dto.QuestionCommentUpdateDto;
import com.guld.sciq.question.comment.entity.QuestionComment;
import com.guld.sciq.question.comment.repository.QuestionCommentRepository;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionCommentProcessor {
    private final QuestionCommentRepository questionCommentRepository;
    private final UserService userService;

    public QuestionCommentDto createComment(QuestionCommentCreateDto createDto, Long questionId, Long userId) {
        QuestionComment comment = QuestionComment.builder()
                .content(createDto.content())
                .question(Question.builder().id(questionId).build())
                .userId(userId)
                .build();
        
        return QuestionCommentDto.from(questionCommentRepository.save(comment));
    }

    public QuestionCommentDto getComment(Long commentId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException(ErrorMessage.QUESTION_COMMENT_NOT_FOUND));
        return QuestionCommentDto.from(comment);
    }

    public QuestionCommentDto updateComment(Long commentId, QuestionCommentUpdateDto updateDto, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException(ErrorMessage.QUESTION_COMMENT_NOT_FOUND));
        
        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException(ErrorMessage.QUESTION_COMMENT_UPDATE_UNAUTHORIZED);
        }

        comment.updateContent(updateDto.getContent());
        return QuestionCommentDto.from(questionCommentRepository.save(comment));
    }

    public void deleteComment(Long commentId, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException(ErrorMessage.QUESTION_COMMENT_NOT_FOUND));
        
        if (!comment.getUserId().equals(userId)) {
            throw new IllegalArgumentException(ErrorMessage.QUESTION_COMMENT_DELETE_UNAUTHORIZED);
        }

        questionCommentRepository.delete(comment);
    }

    public void likeComment(Long commentId, Long userId) {
        QuestionComment comment = questionCommentRepository.findById(commentId)
                .orElseThrow(() -> new QuestionCommentNotFoundException(ErrorMessage.QUESTION_COMMENT_NOT_FOUND));
        
        comment.increaseLikeCnt();
        questionCommentRepository.save(comment);
    }
} 
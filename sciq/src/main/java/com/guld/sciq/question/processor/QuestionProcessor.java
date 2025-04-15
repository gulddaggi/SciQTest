package com.guld.sciq.question.processor;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.global.exception.QuestionNotFoundException;
import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionProcessor {
    private final QuestionRepository questionRepository;
    private final UserService userService;

    public QuestionDto createQuestion(QuestionCreateDto createDto, Long userId) {
        User user = userService.getUserById(userId);
        Question question = Question.builder()
                .user(user)
                .title(createDto.getTitle())
                .content(createDto.getContent())
                .scienceDiscipline(createDto.getScienceDiscipline())
                .recommendCnt(0)
                .build();
        
        return QuestionDto.from(questionRepository.save(question));
    }

    public QuestionDto getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        return QuestionDto.from(question);
    }

    public QuestionDto updateQuestion(Long questionId, QuestionUpdateDto updateDto, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        
        if (!question.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException(ErrorMessage.QUESTION_UPDATE_UNAUTHORIZED);
        }

        question.setTitle(updateDto.getTitle());
        question.setContent(updateDto.getContent());
        question.setScienceDiscipline(updateDto.getScienceDiscipline());

        return QuestionDto.from(questionRepository.save(question));
    }

    public void deleteQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        
        if (!question.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException(ErrorMessage.QUESTION_DELETE_UNAUTHORIZED);
        }

        questionRepository.delete(question);
    }

    public void recommendQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(ErrorMessage.QUESTION_NOT_FOUND));
        
        question.setRecommendCnt(question.getRecommendCnt() + 1);
        questionRepository.save(question);
    }
} 
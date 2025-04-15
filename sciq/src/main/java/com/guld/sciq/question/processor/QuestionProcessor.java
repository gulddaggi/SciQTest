package com.guld.sciq.question.processor;

import org.springframework.stereotype.Component;

import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.question.entity.Question;
import com.guld.sciq.question.repository.QuestionRepository;
import com.guld.sciq.global.exception.QuestionNotFoundException;
import com.guld.sciq.global.exception.UnauthorizedException;
import com.guld.sciq.global.exception.UserNotFoundException;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuestionProcessor {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionDto createQuestion(QuestionCreateDto createDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Question question = Question.builder()
                .user(user)
                .title(createDto.title())
                .content(createDto.content())
                .scienceDiscipline(createDto.scienceDiscipline())
                .recommendCnt(0)
                .build();

        Question savedQuestion = questionRepository.save(question);
        return QuestionDto.from(savedQuestion);
    }

    public QuestionDto getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));
        return QuestionDto.from(question);
    }

    public QuestionDto updateQuestion(Long questionId, QuestionUpdateDto updateDto, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        if (!question.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only the question creator can update the question");
        }

        question.setTitle(updateDto.title());
        question.setContent(updateDto.content());
        question.setScienceDiscipline(updateDto.scienceDiscipline());

        Question updatedQuestion = questionRepository.save(question);
        return QuestionDto.from(updatedQuestion);
    }

    public void deleteQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        if (!question.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Only the question creator can delete the question");
        }

        questionRepository.delete(question);
    }

    public void recommendQuestion(Long questionId, Long userId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found"));

        question.setRecommendCnt(question.getRecommendCnt() + 1);
        questionRepository.save(question);
    }
} 
package com.guld.sciq.question.dto;

import java.time.LocalDateTime;

import com.guld.sciq.question.entity.Question;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.user.dto.UserDto;

public record QuestionDto(
    Long id,
    UserDto user,
    String title,
    String content,
    ScienceDisciplineType scienceDiscipline,
    Integer recommendCnt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static QuestionDto from(Question question) {
        return new QuestionDto(
            question.getId(),
            UserDto.from(question.getUser()),
            question.getTitle(),
            question.getContent(),
            question.getScienceDiscipline(),
            question.getRecommendCnt(),
            question.getCreatedAt(),
            question.getUpdatedAt()
        );
    }
} 
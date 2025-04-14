package com.guld.sciq.search.dto;

import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;

import java.util.List;

public record SearchResultDto(
    List<DebateDto> debates,
    List<QuestionDto> questions,
    List<FeedbackDto> feedbacks,
    int totalResults
) {} 
package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.debate.entity.DebateStatus;

public record DebateDto(
    Long debateId,
    UserDto user,
    String title,
    String description,
    ScienceDisciplineType scienceDiscipline,
    DebateStatus status,
    LocalDateTime scheduledStartTime,
    LocalDateTime scheduledEndTime,
    Integer durationInMinutes,
    boolean closed,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public static DebateDto from(Debate debate) {
        return new DebateDto(
            debate.getId(),
            UserDto.from(debate.getUser()),
            debate.getTitle(),
            debate.getDescription(),
            debate.getScienceDiscipline(),
            debate.getStatus(),
            debate.getScheduledStartTime(),
            debate.getScheduledEndTime(),
            debate.getDurationInMinutes(),
            debate.isClosed(),
            debate.getCreatedAt(),
            debate.getUpdatedAt()
        );
    }
}
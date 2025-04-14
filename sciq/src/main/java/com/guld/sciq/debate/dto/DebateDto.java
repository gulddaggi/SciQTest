package com.guld.sciq.debate.dto;

import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;

import java.time.LocalDateTime;

public record DebateDto(
    Long debateId,
    String nickname,
    String title,
    String description,
    DebateStatus status,
    LocalDateTime scheduledStartTime,
    LocalDateTime scheduledEndTime,
    Integer durationInMinutes,
    boolean closed
) {
    public static DebateDto from(Debate debate) {
        return new DebateDto(
            debate.getId(),
            debate.getUser().getNickName(),
            debate.getTitle(),
            debate.getDescription(),
            debate.getStatus(),
            debate.getScheduledStartTime(),
            debate.getScheduledEndTime(),
            debate.getDurationInMinutes(),
            debate.isClosed()
        );
    }
}
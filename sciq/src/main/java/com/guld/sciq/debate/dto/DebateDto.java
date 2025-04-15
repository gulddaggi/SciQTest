package com.guld.sciq.debate.dto;

import java.time.LocalDateTime;

import com.guld.sciq.common.enums.ScienceDisciplineType;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.user.dto.UserDto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토론 응답")
public record DebateDto(
    @Schema(description = "토론 ID", example = "1")
    Long id,

    @Schema(description = "작성자 정보")
    UserDto user,

    @Schema(description = "토론 제목", example = "인공지능의 윤리적 문제")
    String title,

    @Schema(description = "토론 내용", example = "인공지능의 발전으로 인한 윤리적 문제에 대해 논의합니다.")
    String description,

    @Schema(description = "과학 분야", example = "COMPUTER_SCIENCE")
    ScienceDisciplineType scienceDiscipline,

    @Schema(description = "토론 상태", example = "PENDING")
    DebateStatus status,

    @Schema(description = "예정 시작 시간", example = "2024-03-20T14:00:00")
    LocalDateTime scheduledStartTime,

    @Schema(description = "예정 종료 시간", example = "2024-03-20T15:00:00")
    LocalDateTime scheduledEndTime,

    @Schema(description = "진행 시간(분)", example = "60")
    Integer durationInMinutes,

    @Schema(description = "종료 여부", example = "false")
    boolean closed
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
            debate.isClosed()
        );
    }
}
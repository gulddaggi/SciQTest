package com.guld.sciq.debate.service;

import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.DebateStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface DebateService {
    // 기본 CRUD
    DebateDto createDebate(DebateCreateDto createDto, Long userId);
    DebateDto getDebate(Long debateId);
    DebateDto updateDebate(Long debateId, DebateUpdateDto updateDto, Long userId);
    void deleteDebate(Long debateId, Long userId);
    
    // 상태 관리 (사용자 중심)
    void openDebate(Long debateId, Long userId);
    void closeDebate(Long debateId, Long userId);
    
    // 시간 관리
    void scheduleDebate(Long debateId, LocalDateTime startTime, Integer duration);
    void extendDebate(Long debateId, Integer additionalMinutes, Long userId);
    
    // 조회
    List<DebateDto> getOpenDebates();
    List<DebateDto> getClosedDebates();
    List<DebateDto> getScheduledDebates();
    List<DebateDto> getOngoingDebates();
    List<DebateDto> getDebatesByUser(Long userId);
    
    // 추가된 메서드
    List<DebateDto> getAllDebates();
    DebateDto updateDebateStatus(Long debateId, DebateStatus status, Long userId);
}
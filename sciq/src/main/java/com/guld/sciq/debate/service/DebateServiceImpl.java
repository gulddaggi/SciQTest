package com.guld.sciq.debate.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.debate.repository.DebateRepository;

@Service
@RequiredArgsConstructor
public class DebateServiceImpl implements DebateService {
    private final DebateRepository debateRepository;
    private final DebateProcessor debateProcessor;

    @Override
    public DebateDto createDebate(DebateCreateDto createDto, Long userId) {
        return debateProcessor.createDebate(createDto, userId);
    }

    @Override
    public DebateDto getDebate(Long debateId) {
        return debateProcessor.getDebate(debateId);
    }

    @Override
    public DebateDto updateDebate(Long debateId, DebateUpdateDto updateDto, Long userId) {
        return debateProcessor.updateDebate(debateId, updateDto, userId);
    }

    @Override
    public void deleteDebate(Long debateId, Long userId) {
        debateProcessor.deleteDebate(debateId, userId);
    }

    @Override
    public void openDebate(Long debateId, Long userId) {
        debateProcessor.openDebate(debateId, userId);
    }

    @Override
    public void closeDebate(Long debateId, Long userId) {
        debateProcessor.closeDebate(debateId, userId);
    }

    @Override
    public void scheduleDebate(Long debateId, LocalDateTime startTime, Integer duration) {
        debateProcessor.scheduleDebate(debateId, startTime, duration);
    }

    @Override
    public void extendDebate(Long debateId, Integer additionalMinutes) {
        debateProcessor.extendDebate(debateId, additionalMinutes);
    }

    @Override
    public void updateDebateSchedule(Long debateId, LocalDateTime newStartTime, Integer newDuration) {
        debateProcessor.updateDebateSchedule(debateId, newStartTime, newDuration);
    }

    @Override
    public List<DebateDto> getOpenDebates() {
        return debateRepository.findByStatus(DebateStatus.OPEN)
                .stream()
                .map(DebateDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebateDto> getClosedDebates() {
        return debateRepository.findByStatus(DebateStatus.CLOSED)
                .stream()
                .map(DebateDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebateDto> getScheduledDebates() {
        LocalDateTime now = LocalDateTime.now();
        return debateRepository.findByScheduledStartTimeAfter(now)
                .stream()
                .map(DebateDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebateDto> getOngoingDebates() {
        LocalDateTime now = LocalDateTime.now();
        return debateRepository.findByStatusAndScheduledStartTimeBefore(DebateStatus.OPEN, now)
                .stream()
                .map(DebateDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebateDto> getDebatesByUser(Long userId) {
        return debateRepository.findByUserId(userId)
                .stream()
                .map(DebateDto::from)
                .collect(Collectors.toList());
    }
}
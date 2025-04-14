package com.guld.sciq.debate.service;

import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.global.exception.DebateNotFoundException;
import com.guld.sciq.global.exception.UnauthorizedException;
import com.guld.sciq.global.exception.UserNotFoundException;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DebateProcessor {
    private final DebateRepository debateRepository;
    private final UserRepository userRepository;

    public DebateDto createDebate(DebateCreateDto createDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        Debate debate = Debate.builder()
                .user(user)
                .title(createDto.title())
                .description(createDto.description())
                .status(DebateStatus.PENDING)
                .scheduledStartTime(createDto.scheduledStartTime())
                .durationInMinutes(createDto.durationInMinutes())
                .closed(false)
                .build();

        Debate savedDebate = debateRepository.save(debate);
        return DebateDto.from(savedDebate);
    }

    public DebateDto getDebate(Long debateId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));
        return DebateDto.from(debate);
    }

    public DebateDto updateDebate(Long debateId, DebateUpdateDto updateDto, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("토론 작성자만 수정할 수 있습니다.");
        }

        debate.update(
            updateDto.title(),
            updateDto.description(),
            updateDto.scheduledStartTime(),
            updateDto.durationInMinutes()
        );

        Debate updatedDebate = debateRepository.save(debate);
        return DebateDto.from(updatedDebate);
    }

    public void deleteDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("토론 작성자만 삭제할 수 있습니다.");
        }

        debateRepository.delete(debate);
    }

    public void openDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("토론 작성자만 열 수 있습니다.");
        }

        debate.setStatus(DebateStatus.OPEN);
        debateRepository.save(debate);
    }

    public void closeDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("토론 작성자만 닫을 수 있습니다.");
        }

        debate.setStatus(DebateStatus.CLOSED);
        debate.setClosed(true);
        debateRepository.save(debate);
    }

    public void scheduleDebate(Long debateId, LocalDateTime startTime, Integer duration) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        debate.setScheduledStartTime(startTime);
        debate.setDurationInMinutes(duration);
        debateRepository.save(debate);
    }

    public void extendDebate(Long debateId, Integer additionalMinutes) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        debate.setDurationInMinutes(debate.getDurationInMinutes() + additionalMinutes);
        debateRepository.save(debate);
    }

    public void updateDebateSchedule(Long debateId, LocalDateTime newStartTime, Integer newDuration) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException("토론을 찾을 수 없습니다."));

        debate.setScheduledStartTime(newStartTime);
        debate.setDurationInMinutes(newDuration);
        debateRepository.save(debate);
    }
}
package com.guld.sciq.debate.processor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.global.exception.DebateNotFoundException;
import com.guld.sciq.global.exception.ErrorMessage;
import com.guld.sciq.global.exception.UnauthorizedException;
import com.guld.sciq.global.exception.UserNotFoundException;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DebateProcessor {
    private final DebateRepository debateRepository;
    private final UserRepository userRepository;

    public DebateDto createDebate(DebateCreateDto createDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        Debate debate = Debate.builder()
                .user(user)
                .title(createDto.title())
                .description(createDto.description())
                .scienceDiscipline(createDto.scienceDiscipline())
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
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));
        return DebateDto.from(debate);
    }

    public DebateDto updateDebate(Long debateId, DebateUpdateDto updateDto, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_NOT_OWNER);
        }

        debate.update(
            updateDto.title(),
            updateDto.description(),
            updateDto.scienceDiscipline(),
            updateDto.scheduledStartTime(),
            updateDto.durationInMinutes()
        );

        Debate updatedDebate = debateRepository.save(debate);
        return DebateDto.from(updatedDebate);
    }

    public void deleteDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_NOT_OWNER);
        }

        debateRepository.delete(debate);
    }

    public void openDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_NOT_OWNER);
        }

        debate.setStatus(DebateStatus.OPEN);
        debateRepository.save(debate);
    }

    public void closeDebate(Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        if (!debate.getUser().getId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_NOT_OWNER);
        }

        debate.setStatus(DebateStatus.CLOSED);
        debateRepository.save(debate);
    }

    public void scheduleDebate(Long debateId, LocalDateTime startTime, Integer duration) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        debate.update(
            debate.getTitle(),
            debate.getDescription(),
            debate.getScienceDiscipline(),
            startTime,
            duration
        );

        debateRepository.save(debate);
    }

    public void extendDebate(Long debateId, Integer additionalMinutes) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        int newDuration = debate.getDurationInMinutes() + additionalMinutes;
        debate.update(
            debate.getTitle(),
            debate.getDescription(),
            debate.getScienceDiscipline(),
            debate.getScheduledStartTime(),
            newDuration
        );

        debateRepository.save(debate);
    }

    public void updateDebateSchedule(Long debateId, LocalDateTime newStartTime, Integer newDuration) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        debate.update(
            debate.getTitle(),
            debate.getDescription(),
            debate.getScienceDiscipline(),
            newStartTime,
            newDuration
        );

        debateRepository.save(debate);
    }
} 
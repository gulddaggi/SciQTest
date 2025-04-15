package com.guld.sciq.debate.processor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.global.exception.*;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DebateProcessor {
    
    private final DebateRepository debateRepository;
    private final UserRepository userRepository;
    
    
    @Transactional
    public DebateDto createDebate(DebateCreateDto dto, Long userId) {
        validateCreate(dto);
        
        User user = fetchUser(userId);
        
        Debate debate = Debate.builder()
            .user(user)
            .title(dto.title())
            .description(dto.description())
            .scienceDiscipline(dto.scienceDiscipline())
            .status(DebateStatus.PENDING)
            .scheduledStartTime(dto.scheduledStartTime())
            .scheduledEndTime(calcEnd(dto))
            .durationInMinutes(dto.durationInMinutes())
            .closed(false)
            .build();
        
        return DebateDto.from(debateRepository.save(debate));
    }
    
    
    public DebateDto getDebate(Long id) {
        return DebateDto.from(fetchDebate(id));
    }
    
    
    @Transactional
    public DebateDto updateDebate(Long id, DebateUpdateDto dto, Long userId) {
        validateUpdate(dto);
        
        Debate debate = fetchDebate(id);
        verifyOwner(debate, userId);
        
        debate.update(
            dto.title(),
            dto.description(),
            dto.scienceDiscipline());
        
        return DebateDto.from(debateRepository.save(debate));
    }
    
    
    @Transactional
    public void deleteDebate(Long id, Long userId) {
        Debate debate = fetchDebate(id);
        verifyOwner(debate, userId);
        debateRepository.delete(debate);
    }
    
    
    @Transactional
    public void openDebate(Long id, Long userId) {
        changeStatus(id, userId, DebateStatus.OPEN, ErrorMessage.DEBATE_ALREADY_OPEN);
    }
    
    @Transactional
    public void closeDebate(Long id, Long userId) {
        changeStatus(id, userId, DebateStatus.CLOSED, ErrorMessage.DEBATE_ALREADY_CLOSED);
    }
    
    
    @Transactional
    public void scheduleDebate(Long id, LocalDateTime start, Integer duration) {
        validateSchedule(start, duration);
        
        Debate debate = fetchDebate(id);
        ensureNotFinalState(debate);
        
        debate.scheduledDebate(start, duration);
    }
    
    @Transactional
    public void extendDebate(Long id, Integer addMinutes, Long userId) {
        Debate debate = fetchDebate(id);
        verifyOwner(debate, userId);
        
        if (addMinutes == null || addMinutes <= 0)
            throw new IllegalArgumentException(ErrorMessage.DEBATE_DURATION_INVALID);
        
        fetchDebate(id).extendDebate(addMinutes);
    }
    
    private Debate fetchDebate(Long id) {
        return debateRepository.findById(id)
            .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));
    }
    
    private User fetchUser(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));
    }
    
    private void verifyOwner(Debate debate, Long userId) {
        if (!debate.getUser().getId().equals(userId))
            throw new UnauthorizedException(ErrorMessage.DEBATE_NOT_OWNER);
    }
    
    private void changeStatus(Long id, Long userId, DebateStatus target, String alreadyMsg) {
        Debate debate = fetchDebate(id);
        verifyOwner(debate, userId);
        if (debate.getStatus() == target)
            throw new IllegalStateException(alreadyMsg);
        debate.changeStatus(target);
    }
    
    private void ensureNotFinalState(Debate debate) {
        if (debate.getStatus() == DebateStatus.OPEN)
            throw new IllegalStateException(ErrorMessage.DEBATE_ALREADY_OPEN);
        if (debate.getStatus() == DebateStatus.CLOSED || debate.isClosed())
            throw new IllegalStateException(ErrorMessage.DEBATE_ALREADY_CLOSED);
    }
    
    private LocalDateTime calcEnd(DebateCreateDto dto) {
        return dto.durationInMinutes() == null
            ? null
            : dto.scheduledStartTime().plusMinutes(dto.durationInMinutes());
    }
    
    
    private void validateCreate(DebateCreateDto d) {
        if (isBlank(d.title()))
            throw new IllegalArgumentException(ErrorMessage.DEBATE_TITLE_REQUIRED);
        if (isBlank(d.description()))
            throw new IllegalArgumentException(ErrorMessage.DEBATE_CONTENT_REQUIRED);
        if (d.scienceDiscipline() == null)
            throw new IllegalArgumentException(ErrorMessage.DEBATE_SCIENCE__REQUIRED);
    }
    
    private void validateUpdate(DebateUpdateDto d) {
        if (d.title() != null && isBlank(d.title()))
            throw new IllegalArgumentException(ErrorMessage.DEBATE_TITLE_REQUIRED);
    }
    
    private void validateSchedule(LocalDateTime start, Integer duration) {
        if (start == null)
            throw new IllegalArgumentException(ErrorMessage.DEBATE_DEADLINE_REQUIRED);
        if (start.isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException(ErrorMessage.DEBATE_DEADLINE_INVALID);
        if (duration == null || duration <= 0)
            throw new IllegalArgumentException(ErrorMessage.DEBATE_DURATION_INVALID);
    }
    
    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}

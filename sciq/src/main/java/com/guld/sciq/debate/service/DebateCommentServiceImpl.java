package com.guld.sciq.debate.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guld.sciq.debate.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.dto.DebateCommentDto;
import com.guld.sciq.debate.processor.DebateCommentProcessor;
import com.guld.sciq.debate.repository.DebateCommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebateCommentServiceImpl implements DebateCommentService {
    private final DebateCommentProcessor debateCommentProcessor;
    private final DebateCommentRepository debateCommentRepository;

    @Override
    public DebateCommentDto createComment(DebateCommentCreateDto createDto, Long debateId, Long userId) {
        return debateCommentProcessor.createComment(createDto, debateId, userId);
    }

    @Override
    public DebateCommentDto getComment(Long commentId) {
        return debateCommentProcessor.getComment(commentId);
    }

    @Override
    public DebateCommentDto updateComment(Long commentId, DebateCommentCreateDto updateDto, Long userId) {
        return debateCommentProcessor.updateComment(commentId, updateDto, userId);
    }

    @Override
    public void deleteComment(Long commentId, Long userId) {
        debateCommentProcessor.deleteComment(commentId, userId);
    }

    @Override
    public void likeComment(Long commentId, Long userId) {
        debateCommentProcessor.likeComment(commentId, userId);
    }

    @Override
    public List<DebateCommentDto> getCommentsByDebate(Long debateId) {
        return debateCommentRepository.findByDebateId(debateId)
                .stream()
                .map(DebateCommentDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<DebateCommentDto> getCommentsByUser(Long userId) {
        return debateCommentRepository.findByUserId(userId)
                .stream()
                .map(DebateCommentDto::from)
                .collect(Collectors.toList());
    }
} 
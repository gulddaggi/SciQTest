package com.guld.sciq.debate.comment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.guld.sciq.debate.comment.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.comment.dto.DebateCommentDto;
import com.guld.sciq.debate.comment.processor.DebateCommentProcessor;
import com.guld.sciq.debate.comment.repository.DebateCommentRepository;
import com.guld.sciq.debate.comment.service.DebateCommentService;
import com.guld.sciq.debate.dto.DebateCommentUpdateDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebateCommentServiceImpl implements DebateCommentService {
    private final DebateCommentProcessor debateCommentProcessor;
    private final DebateCommentRepository debateCommentRepository;

    @Override
    public DebateCommentDto createComment(DebateCommentCreateDto createDto, Long debateId, Long userId, String userNickname) {
        return debateCommentProcessor.createComment(createDto, debateId, userId,userNickname);
    }

    @Override
    public DebateCommentDto getComment(Long commentId) {
        return debateCommentProcessor.getComment(commentId);
    }

    @Override
    public DebateCommentDto updateComment(Long commentId, DebateCommentUpdateDto updateDto, Long userId) {
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
package com.guld.sciq.debate.service;

import com.guld.sciq.debate.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.dto.DebateCommentDto;

import java.util.List;

public interface DebateCommentService {
    DebateCommentDto createComment(DebateCommentCreateDto createDto, Long debateId, Long userId);
    DebateCommentDto getComment(Long commentId);
    DebateCommentDto updateComment(Long commentId, DebateCommentCreateDto updateDto, Long userId);
    void deleteComment(Long commentId, Long userId);
    void likeComment(Long commentId, Long userId);
    List<DebateCommentDto> getCommentsByDebate(Long debateId);
    List<DebateCommentDto> getCommentsByUser(Long userId);
} 
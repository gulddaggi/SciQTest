package com.guld.sciq.debate.comment.service;

import com.guld.sciq.debate.comment.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.comment.dto.DebateCommentDto;
import com.guld.sciq.debate.dto.DebateCommentUpdateDto;

import java.util.List;

public interface DebateCommentService {
    DebateCommentDto createComment(DebateCommentCreateDto createDto, Long debateId, Long userId, String userNickName);
    DebateCommentDto getComment(Long commentId);
    DebateCommentDto updateComment(Long commentId, DebateCommentUpdateDto updateDto, Long userId);
    void deleteComment(Long commentId, Long userId);
    void likeComment(Long commentId, Long userId);
    List<DebateCommentDto> getCommentsByDebate(Long debateId);
    List<DebateCommentDto> getCommentsByUser(Long userId);
} 
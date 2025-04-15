package com.guld.sciq.debate.processor;

import org.springframework.stereotype.Component;

import com.guld.sciq.debate.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.dto.DebateCommentDto;
import com.guld.sciq.debate.entity.Debate;
import com.guld.sciq.debate.entity.DebateComment;
import com.guld.sciq.debate.repository.DebateCommentRepository;
import com.guld.sciq.debate.repository.DebateRepository;
import com.guld.sciq.global.exception.DebateCommentNotFoundException;
import com.guld.sciq.global.exception.DebateNotFoundException;
import com.guld.sciq.global.exception.ErrorMessage;
import com.guld.sciq.global.exception.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DebateCommentProcessor {
    private final DebateCommentRepository debateCommentRepository;
    private final DebateRepository debateRepository;

    public DebateCommentDto createComment(DebateCommentCreateDto createDto, Long debateId, Long userId) {
        Debate debate = debateRepository.findById(debateId)
                .orElseThrow(() -> new DebateNotFoundException(ErrorMessage.DEBATE_NOT_FOUND));

        DebateComment comment = DebateComment.builder()
                .debate(debate)
                .userId(userId)
                .userNickName(createDto.userNickName())
                .content(createDto.content())
                .stance(createDto.stance())
                .likes(0)
                .build();

        DebateComment savedComment = debateCommentRepository.save(comment);
        return DebateCommentDto.from(savedComment);
    }

    public DebateCommentDto getComment(Long commentId) {
        DebateComment comment = debateCommentRepository.findById(commentId)
                .orElseThrow(() -> new DebateCommentNotFoundException(ErrorMessage.DEBATE_COMMENT_NOT_FOUND));
        return DebateCommentDto.from(comment);
    }

    public DebateCommentDto updateComment(Long commentId, DebateCommentCreateDto updateDto, Long userId) {
        DebateComment comment = debateCommentRepository.findById(commentId)
                .orElseThrow(() -> new DebateCommentNotFoundException(ErrorMessage.DEBATE_COMMENT_NOT_FOUND));

        if (!comment.getUserId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_COMMENT_NOT_OWNER);
        }

        comment.update(updateDto.content(), updateDto.stance());
        DebateComment updatedComment = debateCommentRepository.save(comment);
        return DebateCommentDto.from(updatedComment);
    }

    public void deleteComment(Long commentId, Long userId) {
        DebateComment comment = debateCommentRepository.findById(commentId)
                .orElseThrow(() -> new DebateCommentNotFoundException(ErrorMessage.DEBATE_COMMENT_NOT_FOUND));

        if (!comment.getUserId().equals(userId)) {
            throw new UnauthorizedException(ErrorMessage.DEBATE_COMMENT_NOT_OWNER);
        }

        debateCommentRepository.delete(comment);
    }

    public void likeComment(Long commentId, Long userId) {
        DebateComment comment = debateCommentRepository.findById(commentId)
                .orElseThrow(() -> new DebateCommentNotFoundException(ErrorMessage.DEBATE_COMMENT_NOT_FOUND));
        comment.increaseLikes();
        debateCommentRepository.save(comment);
    }

    public void unlikeComment(Long commentId, Long userId) {
        DebateComment comment = debateCommentRepository.findById(commentId)
                .orElseThrow(() -> new DebateCommentNotFoundException(ErrorMessage.DEBATE_COMMENT_NOT_FOUND));
        comment.decreseLikes();
        debateCommentRepository.save(comment);
    }
} 
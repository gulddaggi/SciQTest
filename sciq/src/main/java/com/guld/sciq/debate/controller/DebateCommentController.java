package com.guld.sciq.debate.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.guld.sciq.security.UserPrincipal;
import com.guld.sciq.debate.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.service.DebateCommentService;
import com.guld.sciq.utils.ApiUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Debate Comment", description = "Debate Comment 관련 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debates/{debateId}/comments")
public class DebateCommentController {
    private final DebateCommentService debateCommentService;

    @Operation(summary = "댓글 생성", description = "토론에 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createComment(
            @PathVariable Long debateId,
            @RequestBody DebateCommentCreateDto createDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            debateCommentService.createComment(createDto, debateId, userPrincipal.getId())));
    }

    @Operation(summary = "댓글 조회", description = "특정 댓글을 조회합니다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(ApiUtils.success(debateCommentService.getComment(commentId)));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody DebateCommentCreateDto updateDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            debateCommentService.updateComment(commentId, updateDto, userPrincipal.getId())));
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        debateCommentService.deleteComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 표시합니다.")
    @PostMapping("/{commentId}/like")
    public ResponseEntity<?> likeComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        debateCommentService.likeComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론의 댓글 목록 조회", description = "특정 토론의 모든 댓글을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getCommentsByDebate(@PathVariable Long debateId) {
        return ResponseEntity.ok(ApiUtils.success(debateCommentService.getCommentsByDebate(debateId)));
    }
} 
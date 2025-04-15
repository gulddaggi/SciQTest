package com.guld.sciq.question.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.guld.sciq.question.comment.dto.QuestionCommentCreateDto;
import com.guld.sciq.question.comment.dto.QuestionCommentUpdateDto;
import com.guld.sciq.question.comment.service.QuestionCommentService;
import com.guld.sciq.security.UserPrincipal;
import com.guld.sciq.utils.ApiUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "질문 댓글", description = "Question Comment 관련 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions/{questionId}/comments")
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;

    @Operation(summary = "댓글 생성", description = "질문에 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createComment(
            @PathVariable Long questionId,
            @RequestBody QuestionCommentCreateDto createDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            questionCommentService.createComment(createDto, questionId, userPrincipal.getId())));
    }

    @Operation(summary = "댓글 조회", description = "특정 댓글을 조회합니다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable Long commentId) {
        return ResponseEntity.ok(ApiUtils.success(questionCommentService.getComment(commentId)));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @PathVariable Long commentId,
            @RequestBody QuestionCommentUpdateDto updateDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            questionCommentService.updateComment(commentId, updateDto, userPrincipal.getId())));
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        questionCommentService.deleteComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 누릅니다.")
    @PostMapping("/{commentId}/like")
    public ResponseEntity<?> likeComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        questionCommentService.likeComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "질문의 댓글 목록 조회", description = "특정 질문의 모든 댓글을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getCommentsByQuestion(@PathVariable Long questionId) {
        return ResponseEntity.ok(ApiUtils.success(questionCommentService.getCommentsByQuestion(questionId)));
    }
} 
package com.guld.sciq.debate.controller;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.debate.comment.dto.DebateCommentCreateDto;
import com.guld.sciq.debate.comment.dto.DebateCommentDto;
import com.guld.sciq.debate.comment.service.DebateCommentService;
import com.guld.sciq.debate.dto.DebateCommentUpdateDto;
import com.guld.sciq.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "토론 댓글", description = "토론 댓글 관련 API")
@RestController
@RequestMapping("/api/v1/debates/{debateId}/comments")
@RequiredArgsConstructor
public class DebateCommentController {

    private final DebateCommentService debateCommentService;

    @Operation(summary = "댓글 생성", description = "새로운 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createComment(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "댓글 생성 정보", required = true)
        @RequestBody DebateCommentCreateDto createDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            debateCommentService.createComment(createDto, debateId, userPrincipal.getId(), userPrincipal.getNickName())));
    }

    @Operation(summary = "댓글 조회", description = "특정 댓글의 상세 정보를 조회합니다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "댓글 ID", required = true)
        @PathVariable Long commentId
    ) {
        return ResponseEntity.ok(ApiUtils.success(debateCommentService.getComment(commentId)));
    }

    @Operation(summary = "댓글 수정", description = "기존 댓글을 수정합니다.")
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "댓글 ID", required = true)
        @PathVariable Long commentId,
        @Parameter(description = "댓글 수정 정보", required = true)
        @RequestBody DebateCommentUpdateDto updateDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(ApiUtils.success(
            debateCommentService.updateComment(commentId, updateDto, userPrincipal.getId())));
    }

    @Operation(summary = "댓글 삭제", description = "기존 댓글을 삭제합니다.")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "댓글 ID", required = true)
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        debateCommentService.deleteComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 추가합니다.")
    @PostMapping("/{commentId}/like")
    public ResponseEntity<?> likeComment(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "댓글 ID", required = true)
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        debateCommentService.likeComment(commentId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론의 댓글 목록 조회", description = "특정 토론의 모든 댓글을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getCommentsByDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId
    ) {
        return ResponseEntity.ok(ApiUtils.success(debateCommentService.getCommentsByDebate(debateId)));
    }

    @Operation(summary = "사용자의 댓글 목록 조회", description = "특정 사용자의 모든 댓글을 조회합니다.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DebateCommentDto>> getCommentsByUser(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long debateId,
        @Parameter(description = "사용자 ID", required = true)
        @PathVariable Long userId
    ) {
        List<DebateCommentDto> comments = debateCommentService.getCommentsByUser(userId);
        return ResponseEntity.ok(comments);
    }
} 
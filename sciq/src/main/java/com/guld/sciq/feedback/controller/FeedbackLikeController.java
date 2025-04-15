package com.guld.sciq.feedback.controller;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.feedback.service.FeedbackLikeService;
import com.guld.sciq.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "피드백 좋아요", description = "피드백 좋아요 관련 API")
@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackLikeController {

    private final FeedbackLikeService feedbackLikeService;

    @Operation(summary = "피드백 좋아요", description = "피드백에 좋아요를 추가합니다.")
    @PostMapping("/{feedbackId}/like")
    public ResponseEntity<?> likeFeedback(
        @Parameter(description = "피드백 ID", required = true) @PathVariable Long feedbackId,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        feedbackLikeService.likeFeedback(feedbackId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "피드백 좋아요 취소", description = "피드백의 좋아요를 취소합니다.")
    @DeleteMapping("/{feedbackId}/like")
    public ResponseEntity<?> unlikeFeedback(
        @Parameter(description = "피드백 ID", required = true) @PathVariable Long feedbackId,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        feedbackLikeService.unlikeFeedback(feedbackId, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "피드백 좋아요 상태 확인", description = "현재 사용자가 해당 피드백에 좋아요를 했는지 확인합니다.")
    @GetMapping("/{feedbackId}/like")
    public ResponseEntity<Boolean> hasLiked(
        @Parameter(description = "피드백 ID", required = true) @PathVariable Long feedbackId,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        boolean hasLiked = feedbackLikeService.hasLikedFeedback(feedbackId, userPrincipal.getId());
        return ResponseEntity.ok(hasLiked);
    }

    @Operation(summary = "피드백 좋아요 수 조회", description = "피드백의 좋아요 수를 조회합니다.")
    @GetMapping("/{feedbackId}/like/count")
    public ResponseEntity<Long> getLikeCount(
        @Parameter(description = "피드백 ID", required = true) @PathVariable Long feedbackId
    ) {
        long likeCount = feedbackLikeService.getLikeCount(feedbackId);
        return ResponseEntity.ok(likeCount);
    }
} 
package com.guld.sciq.feedback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.feedback.dto.FeedbackCreateDto;
import com.guld.sciq.feedback.dto.FeedbackDto;
import com.guld.sciq.feedback.dto.FeedbackUpdateDto;
import com.guld.sciq.feedback.processor.FeedbackProcessor;
import com.guld.sciq.security.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Tag(name = "피드백 관리", description = "피드백 생성, 조회, 수정, 삭제 API")
@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackProcessor feedbackProcessor;

    @Operation(summary = "피드백 생성", description = "새로운 피드백을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "피드백 생성 성공",
            content = @Content(schema = @Schema(implementation = FeedbackDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "404", description = "사용자 정보 없음")
    })
    @PostMapping
    public ResponseEntity<?> createFeedback(
        @Parameter(description = "피드백 생성 정보", required = true)
        @Valid @RequestBody FeedbackCreateDto createDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            feedbackProcessor.createFeedback(createDto, userPrincipal.getId())));
    }

    @Operation(summary = "피드백 조회", description = "특정 피드백의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "피드백 조회 성공",
            content = @Content(schema = @Schema(implementation = FeedbackDto.class))),
        @ApiResponse(responseCode = "404", description = "피드백 정보 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedback(
        @Parameter(description = "피드백 ID", required = true)
        @PathVariable Long id) {
        return ResponseEntity.ok(ApiUtils.success(feedbackProcessor.getFeedback(id)));
    }

    @Operation(summary = "피드백 수정", description = "기존 피드백을 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "피드백 수정 성공",
            content = @Content(schema = @Schema(implementation = FeedbackDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "피드백 정보 없음")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedback(
        @Parameter(description = "피드백 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "피드백 수정 정보", required = true)
        @Valid @RequestBody FeedbackUpdateDto updateDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            feedbackProcessor.updateFeedback(id, updateDto, userPrincipal.getId())));
    }

    @Operation(summary = "피드백 삭제", description = "기존 피드백을 삭제합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "피드백 삭제 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "피드백 정보 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(
        @Parameter(description = "피드백 ID", required = true)
        @PathVariable Long id,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        feedbackProcessor.deleteFeedback(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "피드백 도움됨 표시", description = "피드백이 도움이 되었다고 표시합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "도움됨 표시 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "404", description = "피드백 정보 없음")
    })
    @PostMapping("/{id}/helpful")
    public ResponseEntity<Void> markHelpful(
        @Parameter(description = "피드백 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "사용자 ID", required = true)
        @RequestParam Long userId) {
        feedbackProcessor.markHelpful(id, userId);
        return ResponseEntity.ok().build();
    }
} 
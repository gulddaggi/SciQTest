package com.guld.sciq.question.controller;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.question.dto.QuestionCreateDto;
import com.guld.sciq.question.dto.QuestionDto;
import com.guld.sciq.question.dto.QuestionUpdateDto;
import com.guld.sciq.question.service.QuestionService;
import com.guld.sciq.security.UserPrincipal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "질문 API", description = "질문 관련 API")
@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @Operation(summary = "질문 생성", description = "새로운 질문을 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createQuestion(
            @Valid @RequestBody QuestionCreateDto request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            questionService.createQuestion(request, userPrincipal.getId())));
    }

    @Operation(summary = "질문 조회", description = "특정 질문의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id) {
        return ResponseEntity.ok(ApiUtils.success(questionService.getQuestion(id)));
    }

    @Operation(summary = "질문 목록 조회", description = "모든 질문 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(ApiUtils.success(questionService.getAllQuestions()));
    }

    @Operation(summary = "질문 수정", description = "기존 질문을 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionUpdateDto request,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(
            questionService.updateQuestion(id, request, userPrincipal.getId())));
    }

    @Operation(summary = "질문 삭제", description = "기존 질문을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable Long id,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        questionService.deleteQuestion(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "질문 추천", description = "질문에 추천을 추가합니다.")
    @PostMapping("/{id}/recommend")
    public ResponseEntity recommendQuestion(
        @PathVariable Long questionId,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        questionService.recommendQuestion(questionId, userPrincipal.getId());
        return ResponseEntity.ok("추천 완료");
    }
} 
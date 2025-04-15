package com.guld.sciq.debate.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.debate.dto.DebateCreateDto;
import com.guld.sciq.debate.dto.DebateDto;
import com.guld.sciq.debate.dto.DebateUpdateDto;
import com.guld.sciq.debate.entity.DebateStatus;
import com.guld.sciq.debate.service.DebateService;
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
import org.springframework.security.core.context.SecurityContextHolder;

@Tag(name = "토론 관리", description = "토론 생성, 조회, 수정, 삭제 API")
@RestController
@RequestMapping("/api/v1/debates")
@RequiredArgsConstructor
public class DebateController {
    private final DebateService debateService;

    @Operation(summary = "토론 생성", description = "새로운 토론을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 생성 성공", 
            content = @Content(schema = @Schema(implementation = DebateDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "404", description = "사용자 정보 없음")
    })
    @PostMapping
    public ResponseEntity<?> createDebate(
        @Parameter(description = "토론 생성 정보", required = true)
        @RequestBody DebateCreateDto createDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(ApiUtils.success(
            debateService.createDebate(createDto, userPrincipal.getId())));
    }

    @Operation(summary = "토론 조회", description = "특정 토론의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 조회 성공",
            content = @Content(schema = @Schema(implementation = DebateDto.class))),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(ApiUtils.success(debateService.getDebate(id)));
    }

    @Operation(summary = "토론 수정", description = "기존 토론을 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 수정 성공",
            content = @Content(schema = @Schema(implementation = DebateDto.class))),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "토론 수정 정보", required = true)
        @RequestBody DebateUpdateDto updateDto,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(ApiUtils.success(
            debateService.updateDebate(id, updateDto, userPrincipal.getId())));
    }

    @Operation(summary = "토론 삭제", description = "기존 토론을 삭제합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 삭제 성공"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        debateService.deleteDebate(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론 목록 조회", description = "모든 토론의 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getAllDebates() {
        return ResponseEntity.ok(ApiUtils.success(debateService.getAllDebates()));
    }

    @Operation(summary = "사용자별 토론 조회", description = "특정 사용자의 토론 목록을 조회합니다.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDebatesByUser(
        @Parameter(description = "사용자 ID", required = true)
        @PathVariable Long userId
    ) {
        return ResponseEntity.ok(ApiUtils.success(debateService.getDebatesByUser(userId)));
    }

    @Operation(summary = "토론 상태 변경", description = "토론의 상태를 변경합니다.")
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateDebateStatus(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "새로운 상태", required = true)
        @RequestParam DebateStatus status,
        @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(ApiUtils.success(
            debateService.updateDebateStatus(id, status, userPrincipal.getId())));
    }

    @Operation(summary = "토론 열기", description = "토론을 열어 참여를 시작합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 열기 성공"),
        @ApiResponse(responseCode = "400", description = "이미 열린 토론"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @PostMapping("/{id}/open")
    public ResponseEntity<?> openDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        debateService.openDebate(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론 닫기", description = "토론을 닫아 참여를 종료합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 닫기 성공"),
        @ApiResponse(responseCode = "400", description = "이미 닫힌 토론"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @PostMapping("/{id}/close")
    public ResponseEntity<?> closeDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        debateService.closeDebate(id, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론 스케줄링", description = "토론의 시작 시간과 기간을 설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 스케줄링 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 스케줄 정보"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @PostMapping("/{id}/schedule")
    public ResponseEntity<?> scheduleDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "시작 시간", required = true)
        @RequestParam LocalDateTime startTime,
        @Parameter(description = "진행 시간(분)", required = true)
        @RequestParam Integer duration){
        debateService.scheduleDebate(id, startTime, duration);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "토론 시간 연장", description = "토론의 진행 시간을 연장합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "토론 시간 연장 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 연장 시간"),
        @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자"),
        @ApiResponse(responseCode = "403", description = "권한 없음"),
        @ApiResponse(responseCode = "404", description = "토론 정보 없음")
    })
    @PostMapping("/{id}/extend")
    public ResponseEntity<?> extendDebate(
        @Parameter(description = "토론 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "추가 시간(분)", required = true)
        @RequestParam Integer additionalMinutes,
        @AuthenticationPrincipal UserPrincipal userPrincipal) {
        debateService.extendDebate(id, additionalMinutes, userPrincipal.getId());
        return ResponseEntity.ok(ApiUtils.success(null));
    }
} 
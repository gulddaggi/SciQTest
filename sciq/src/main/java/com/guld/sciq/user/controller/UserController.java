package com.guld.sciq.user.controller;

import com.guld.sciq.common.response.ApiUtils;
import com.guld.sciq.user.dto.UserCreateDto;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.user.dto.UserUpdateDto;
import com.guld.sciq.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "사용자 관리", description = "사용자 생성, 조회, 수정, 삭제 API")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @PostMapping
    public ResponseEntity<?> createUser(
        @Parameter(description = "사용자 생성 정보", required = true)
        @RequestBody UserCreateDto createDto
    ) {
        return ResponseEntity.ok(ApiUtils.success(userService.createUser(createDto)));
    }

    @Operation(summary = "사용자 조회", description = "특정 사용자의 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(
        @Parameter(description = "사용자 ID", required = true)
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(ApiUtils.success(userService.getUser(id)));
    }

    @Operation(summary = "사용자 수정", description = "기존 사용자의 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
        @Parameter(description = "사용자 ID", required = true)
        @PathVariable Long id,
        @Parameter(description = "사용자 수정 정보", required = true)
        @RequestBody UserUpdateDto updateDto
    ) {
        return ResponseEntity.ok(ApiUtils.success(userService.updateUser(id, updateDto)));
    }

    @Operation(summary = "사용자 삭제", description = "기존 사용자를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
        @Parameter(description = "사용자 ID", required = true)
        @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiUtils.success(null));
    }

    @Operation(summary = "사용자 목록 조회", description = "모든 사용자의 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(ApiUtils.success(userService.getAllUsers()));
    }

    @Operation(summary = "이메일로 사용자 조회", description = "이메일로 사용자를 조회합니다.")
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(
        @Parameter(description = "이메일", required = true)
        @PathVariable String email
    ) {
        return ResponseEntity.ok(ApiUtils.success(userService.getUserByEmail(email)));
    }
}

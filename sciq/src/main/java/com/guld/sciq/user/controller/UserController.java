package com.guld.sciq.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.guld.sciq.security.UserPrincipal;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.user.repository.UserRepository;
import com.guld.sciq.user.service.UserService;
import com.guld.sciq.utils.ApiUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User", description = "User 관련 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Operation(
            summary = "유저 정보 조회",
            description = "로그인된 유저의 정보를 조회합니다. 이 엔드포인트는 인증된 유저만 접근할 수 있으며, 이메일, 이름 및 추가 프로필 정보를 포함한 유저 정보를 반환합니다."
    )
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(ApiUtils.success(UserDto.Response.toDto(userService.findByEmail(userPrincipal.getEmail()))));
    }

    @Operation(
            summary = "유저 정보 수정",
            description = "로그인된 유저의 추가 정보를 수정합니다. 이 엔드포인트는 유저가 자신의 프로필 세부 정보를 수정할 수 있도록 하며, 이름, 주소 또는 기타 선택적 필드를 변경할 수 있습니다. 인증된 유저만 접근할 수 있습니다."
    )
    @PostMapping("/info")
    public ResponseEntity<?> editUserInfo(
            @RequestBody UserDto.Request userDto,
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        userService.updateUserAdditionalInfo(userPrincipal.getEmail(),userDto);
        return ResponseEntity.ok(ApiUtils.success("유저프로필 업데이트 완료"));
    }
}

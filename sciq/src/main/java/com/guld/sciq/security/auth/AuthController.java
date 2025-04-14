package com.guld.sciq.security.auth;

import com.guld.sciq.security.dto.AuthDto;
import com.guld.sciq.security.dto.TokenDto;
import com.guld.sciq.utils.ApiUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 관련 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입을 진행합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthDto.SignUpRequest signUpRequest) {
        authService.signup(signUpRequest);
        return ResponseEntity.ok(ApiUtils.success(authService.login(signUpRequest)));
    }

    @Operation(summary = "로그인", description = "사용자가 로그인하여 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.SignUpRequest signUpRequest) {
        return ResponseEntity.ok(ApiUtils.success(authService.login(signUpRequest)));
    }


    @Operation(summary = "로그아웃", description = "사용자가 로그아웃하여 토큰을 무효화합니다.")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenDto.Request requestDto, HttpServletRequest request) {
        authService.logout(requestDto, request);
        return ResponseEntity.ok(ApiUtils.success("로그아웃 완료"));
    }

    @Operation(summary = "토큰 재발급", description = "만료된 토큰을 재발급받습니다.")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDto.Request requestDto, HttpServletRequest request) {
        return ResponseEntity.ok(ApiUtils.success(authService.reissue(requestDto, request)));
    }
}

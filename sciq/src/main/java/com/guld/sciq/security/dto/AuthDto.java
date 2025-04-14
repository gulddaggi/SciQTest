package com.guld.sciq.security.dto;

import com.guld.sciq.user.entity.Prefer;
import com.guld.sciq.user.entity.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guld.sciq.user.entity.User;

import java.util.Optional;

public class AuthDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class DefaultRequest {

        @NotNull
        @Size(min = 4, max = 30)
        private String email;

        @NotNull
        @Size(min = 8, max = 50)
        private String password;

        @NotNull
        private String name;

        @NotNull
        private String nickName;

        private String schoolName;

        @NotNull
        private Role role;

        private Prefer prefer;

        // 회원가입 시 User 엔티티로 변환
        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .nickName(nickName)
                    .schoolName(schoolName)
                    .role(role)
                    .prefer(prefer)
                    .build();
        }

        // [선택] 내부 변환용 (응답용으로 사용 X)
        public static DefaultRequest toDto(User user) {
            return DefaultRequest.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .schoolName(user.getSchoolName())
                    .role(user.getRole())
                    .prefer(user.getPrefer())
                    .build();
        }

        // 로그인용 인증 객체 생성
        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }
}

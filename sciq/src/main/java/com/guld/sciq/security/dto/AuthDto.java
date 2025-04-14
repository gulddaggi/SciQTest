package com.guld.sciq.security.dto;

import com.guld.sciq.user.entity.UserPrefer;
import com.guld.sciq.user.entity.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guld.sciq.user.entity.User;

public class AuthDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class SignUpRequest {
        @NotNull(message = "이메일은 필수입니다.")
        @Size(min = 1, max = 50, message = "이메일은 1자 이상 50자 이하로 입력해주세요.")
        private String email;
        
        @NotNull(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        private String password;
        
        @NotNull(message = "이름은 필수입니다.")
        @Size(min = 1, max = 20, message = "이름은 1자 이상 20자 이하로 입력해주세요.")
        private String userName;
        
        @NotNull(message = "닉네임은 필수입니다.")
        @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
        private String nickName;
        
        @NotNull(message = "학교명은 필수입니다.")
        @Size(min = 1, max = 50, message = "학교명은 1자 이상 50자 이하로 입력해주세요.")
        private String schoolName;
        
        @NotNull(message = "선호도는 필수입니다.")
        private UserPrefer prefer;
        
        @NotNull(message = "권한은 필수입니다.")
        private UserRole userRole;
        
        // 회원가입 시 User 엔티티로 변환
        public User toEntity(PasswordEncoder passwordEncoder) {
            return User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .userName(userName)
                    .nickName(nickName)
                    .schoolName(schoolName)
                    .prefer(prefer)
                    .role(userRole)
                    .build();
        }

        // 로그인용 인증 객체 생성
        public UsernamePasswordAuthenticationToken toAuthentication() {
            return new UsernamePasswordAuthenticationToken(email, password);
        }
    }
}

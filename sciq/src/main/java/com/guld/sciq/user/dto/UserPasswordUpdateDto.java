package com.guld.sciq.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record UserPasswordUpdateDto(
    @NotBlank(message = "현재 비밀번호는 필수 입력값입니다.")
    String currentPassword,

    @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", 
             message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
    String newPassword
) {} 
package com.guld.sciq.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", 
             message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
    String password,

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
    String userName,

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문, 숫자만 사용 가능합니다.")
    String nickName,

    String schoolName
) {} 
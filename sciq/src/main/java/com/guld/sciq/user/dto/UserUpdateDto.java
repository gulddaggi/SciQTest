package com.guld.sciq.user.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

public record UserUpdateDto(
    @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
    String userName,

    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하여야 합니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+$", message = "닉네임은 한글, 영문, 숫자만 사용 가능합니다.")
    String nickName,

    String schoolName
) {} 
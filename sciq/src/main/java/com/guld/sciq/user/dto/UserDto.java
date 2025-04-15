package com.guld.sciq.user.dto;

import com.guld.sciq.user.entity.User;

public record UserDto(
    Long id,
    String email,
    String userName,
    String nickName,
    String schoolName
) {
    public static UserDto from(User user) {
        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getUserName(),
            user.getNickName(),
            user.getSchoolName()
        );
    }
}




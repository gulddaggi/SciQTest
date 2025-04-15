package com.guld.sciq.user.dto;

import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.entity.UserPrefer;

public record UserDto(
    Long id,
    String email,
    String userName,
    String nickName,
    String schoolName,
    UserPrefer prefer,
    int points,
    int level
) {
    public static UserDto from(User user) {
        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getUserName(),
            user.getNickName(),
            user.getSchoolName(),
            user.getPrefer(),
            user.getPoints(),
            user.getLevel()
        );
    }

    public record Request(
        String userName,
        String nickName,
        String schoolName,
        UserPrefer prefer
    ) {
        public static Request from(User user) {
            return new Request(
                user.getUserName(),
                user.getNickName(),
                user.getSchoolName(),
                user.getPrefer()
            );
        }
    }

    public record Response(
        Long id,
        String email,
        String userName,
        String nickName,
        String schoolName,
        UserPrefer prefer,
        int points,
        int level
    ) {
        public static Response from(User user) {
            return new Response(
                user.getId(),
                user.getEmail(),
                user.getUserName(),
                user.getNickName(),
                user.getSchoolName(),
                user.getPrefer(),
                user.getPoints(),
                user.getLevel()
            );
        }
    }
}




package com.guld.sciq.user.dto;

import com.guld.sciq.user.entity.Prefer;
import com.guld.sciq.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

import com.guld.sciq.user.entity.User;

public class UserDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String name;
        private String email;
        private String nickname;
        private String password;
        private String schoolName;
        private Prefer prefer;
        private Role role;

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String name;
        private String email;
        private String nickname;
        private String schoolName;
        private String prefer;
        private String role;

        public static Response toDto(User user) {
            return Response.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .nickname(user.getNickName())
                    .schoolName(user.getSchoolName())
                    .prefer(user.getPrefer() != null ? user.getPrefer().name() : null)
                    .role(user.getRole() != null ? user.getRole().name() : null)
                    .build();
        }
    }

}




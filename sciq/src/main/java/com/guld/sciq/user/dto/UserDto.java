package com.guld.sciq.user.dto;

import com.guld.sciq.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.entity.UserPrefer;

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
        private UserPrefer prefer;
        private UserRole userRole;

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

        public static Response from(User user) {
            return Response.builder()
                    .name(user.getUserName())
                    .email(user.getEmail())
                    .nickname(user.getNickName())
                    .schoolName(user.getSchoolName())
                    .prefer(user.getPrefer().name())
                    .role(user.getRole().name())
                    .build();
        }
    }

}




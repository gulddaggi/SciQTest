package com.guld.sciq.user.service;

import com.guld.sciq.user.dto.UserCreateDto;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.user.dto.UserUpdateDto;
import com.guld.sciq.user.entity.User;

public interface UserService {
    // 기본 CRUD 작업
    UserDto createUser(UserCreateDto createDto);
    UserDto getUser(Long userId);
    UserDto updateUser(Long userId, UserUpdateDto updateDto);
    void deleteUser(Long userId);
    
    // 비밀번호 관련
    void changePassword(Long userId, String oldPassword, String newPassword);
    
    // 이메일 관련
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User getReferenceByEmail(String email);
    
    // 추가 정보 업데이트
    void updateUserAdditionalInfo(String email, UserDto.Request userDto);
}

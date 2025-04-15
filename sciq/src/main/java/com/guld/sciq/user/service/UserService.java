package com.guld.sciq.user.service;

import com.guld.sciq.user.dto.UserCreateDto;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.user.dto.UserUpdateDto;
import com.guld.sciq.user.entity.User;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
    
    // 전체 사용자 조회
    List<UserDto> getAllUsers();
    
    // 이메일로 사용자 조회
    UserDto getUserByEmail(String email);
    
    @Transactional(readOnly = true)
    User getUserById(Long id);
}

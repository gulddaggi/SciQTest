package com.guld.sciq.user.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import com.guld.sciq.exception.ErrorMessage;
import com.guld.sciq.user.dto.UserDto;
import com.guld.sciq.user.entity.Prefer;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    // private final S3Service s3Service;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND));
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND));
    }

    public User getReferenceByEmail(String email) {
        // 실제 엔터티를 로드하지 않고, 프록시 객체를 반환
        return entityManager.getReference(User.class, findByEmail(email).getUserId());
    }

    public void updateUserAdditionalInfo(String email, UserDto.Request userDto) {
        User user = findByEmail(email);
        user.setPrefer(userDto.getPrefer());
        userRepository.save(user);
    }

    // @Transactional
    // public String generatePresignedUrl(String email) {
    //     URL presignedUrl = s3Service.getPresignedUrl(String.format("users/%s", email));
    //     return presignedUrl.toString();
    // }
    //
    // @Transactional
    // public void completeImageUpload(UserDto.ImageRequest imageRequest, String userEmail) {
    //     User user = findByEmail(userEmail);
    //     String userImageUrl = s3Service.getUserImageUrl(imageRequest.getPresignedUrl());
    //     user.setProfileImageUrl(userImageUrl);
    //
    //     userRepository.save(user);
    // }

}

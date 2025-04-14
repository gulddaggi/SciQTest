package com.guld.sciq.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guld.sciq.user.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);
    void deleteByKey(String key);
}

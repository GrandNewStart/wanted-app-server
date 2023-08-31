package com.rcplus.wanted.configs.jwt;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    public Optional<RefreshToken> findByUserId(Long userId);
    public Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

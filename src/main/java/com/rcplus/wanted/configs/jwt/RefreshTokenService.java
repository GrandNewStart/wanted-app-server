package com.rcplus.wanted.configs.jwt;

import java.time.Duration;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.models.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtProperties jwtProperties;

    public RefreshToken createRefreshToken(User user) throws BaseException {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + Duration.ofDays(14).toMillis());
        String refreshToken = Jwts.builder()
            .setSubject(user.getEmail())
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(jwtProperties.getIssuer())
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .addClaims(Map.of("id", user.getId()))
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
            .compact();
        try {
            Optional<RefreshToken> existingToken = this.refreshTokenRepository.findByUserId(user.getId());
            if (existingToken.isPresent()) {
                this.refreshTokenRepository.delete(existingToken.get());
            }   
            return this.refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));
        } catch (Exception e) {
            throw new BaseException(REQUEST_ERROR);
        }
    }

    public RefreshToken findByRefreshToken(String refreshToken) throws BaseException {
        Optional<RefreshToken> token = this.refreshTokenRepository.findByRefreshToken(refreshToken);
        if (token.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        return token.get();
    }
    
}

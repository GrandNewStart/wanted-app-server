package com.rcplus.wanted.configs.jwt;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenProvider {
    
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * User 객체와 토큰 유효 기간을 받아 새로운 토큰을 생성한다.
     * 
     * @param user User 엔티티 객체
     * @param expiredAt 토큰의 유효 기간
     * @return JWT
     */
    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        // return this.makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
        Date expiry = new Date(now.getTime() + expiredAt.toMillis());
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuer(this.jwtProperties.getIssuer())
            .setIssuedAt(now)
            .setExpiration(expiry)
            .setSubject(user.getEmail())
            .claim("id", user.getId())
            .signWith(SignatureAlgorithm.HS256, this.jwtProperties.getSecretKey())
            .compact();
    }

    /*
     * 전달받은 토큰의 유효성을 판단한다.
     */
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(this.jwtProperties.getSecretKey())
                .parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    /*
     * 전달받은 토큰을 파싱하여 인증 정보를 반환한다.
     */
    public Authentication getAuthentication(String token) {
        Claims claims = this.getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(
            new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities),
            token,
            authorities
        );
    }
    /*
    * 전달받은 토큰으로부터 토큰 주인에 해당하는 User의 아이디 값을 반환한다.
    */
    public Long getUserId(String token) {
        Claims claims = this.getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .setSigningKey(this.jwtProperties.getSecretKey())
            .parseClaimsJws(token)
            .getBody();
    }

}

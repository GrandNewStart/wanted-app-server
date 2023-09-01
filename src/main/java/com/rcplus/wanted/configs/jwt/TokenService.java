package com.rcplus.wanted.configs.jwt;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class TokenService {
    
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserRepository userRepository;

    public LogInDto.Response createNewTokens(LogInDto.Request request) throws BaseException {
        Optional<User> userOpt = this.userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(request.getPassword())) {
            throw new BaseException(INVALID_JWT);
        }
        String accessToken = this.tokenProvider.generateToken(user, Duration.ofHours(1));
        RefreshToken refrehToken = this.refreshTokenService.createRefreshToken(user);
        return LogInDto.Response.builder()
            .accessToken(accessToken)
            .refreshToken(refrehToken.getRefreshToken())
            .build();
    }

    public CreateAccessTokenDto.Response refreshTokens(CreateAccessTokenDto.Request request) throws BaseException {
        if (!this.tokenProvider.validToken(request.getRefreshToken())) {
            throw new BaseException(INVALID_JWT);
        }
        Long userId = this.refreshTokenService.findByRefreshToken(request.getRefreshToken()).getUserId();
        Optional<User> userOpt = this.userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        User user = userOpt.get();
        String accessToken = this.tokenProvider.generateToken(user, Duration.ofHours(2));
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(user);
        return new CreateAccessTokenDto.Response(accessToken, refreshToken.getRefreshToken());
    }

    public User getUserFromHttpHeaders(HttpHeaders headers) throws BaseException {
        List<String> authHeaders = headers.get("Authorization");
        if (authHeaders == null) {
            throw new BaseException(INVALID_JWT);
        }
        String auth = authHeaders.get(0);
        if (auth == null) {
            throw new BaseException(INVALID_JWT);
        }
        String[] auths = auth.split(" ");
        if (auths.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        if (!auths[0].equals("Bearer")) {
            throw new BaseException(INVALID_JWT);
        }
        String token = auths[1];
        if (!this.tokenProvider.validToken(token)) {
            throw new BaseException(INVALID_JWT);
        }
        Long userId = this.tokenProvider.getUserId(token);
        if (userId == null) {
            throw new BaseException(INVALID_JWT);
        }
        Optional<User> userOpt = this.userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        return userOpt.get();
    }

}

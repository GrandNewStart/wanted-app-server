package com.rcplus.wanted.services.impls;

import static com.rcplus.wanted.configs.BaseResponseStatus.*;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rcplus.wanted.configs.BaseException;
import com.rcplus.wanted.dtos.LogInDto;
import com.rcplus.wanted.dtos.RefreshTokenDto;
import com.rcplus.wanted.dtos.SignUpDto;
import com.rcplus.wanted.models.User;
import com.rcplus.wanted.repositories.UserRepository;
import com.rcplus.wanted.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SignUpDto.Response createUser(SignUpDto.Request request) throws BaseException {
        Optional<User> existingUser = this.userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new BaseException(DUPLICATED_EMAIL);
        }
        User user = this.userRepository.save(request.toUser());
        return SignUpDto.Response.builder()
            .id(user.getId())
            .name(user.getName())
            .build();
    }

    @Override
    public void deleteUser(com.rcplus.wanted.dtos.SignOutDto.Request request) throws BaseException {
        Optional<User> user = this.userRepository.findById(request.getUserId());
        if (user.isEmpty()) {
            throw new BaseException(REQUEST_ERROR);
        }
        if (!user.get().getEmail().equals(request.getEmail())) {
            throw new BaseException(REQUEST_ERROR);
        }
        if (!user.get().getPassword().equals(request.getPassword())) {
            throw new BaseException(REQUEST_ERROR);
        }
        this.userRepository.delete(user.get());
    }

    @Override
    public User findById(Long userId) {
        return this.userRepository.findById(userId)
            .orElseThrow(()->new IllegalArgumentException("Unexpected user"));
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(()->new IllegalArgumentException(email));
    }

    @Override
    public LogInDto.Response logIn(LogInDto.Request request) throws BaseException {
        String email = request.getEmail();
        if (email.isEmpty()) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        Optional<User> user = this.userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        if (!user.get().getPassword().equals(request.getPassword())) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        String userCredential = user.get().getEmail() + ":" + user.get().getPassword();
        String token = Base64.getEncoder().encodeToString(userCredential.getBytes());
        return LogInDto.Response.builder()
            .accessToken(token)
            .refreshToken(token)
            .expireTime(3600)
            .build();
    }

    @Override
    public RefreshTokenDto.Response refreshToken(RefreshTokenDto.Request request, String auth) throws BaseException {
        String[] auths = auth.split(" ");
        if (auths.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        if (!auths[0].equals("Basic")) {
            throw new BaseException(INVALID_JWT);
        }
        String token = auths[1];
        String credential = new String(Base64.getDecoder().decode(token));
        String[] credentials = credential.split(":");
        if (credentials.length != 2) {
            throw new BaseException(INVALID_JWT);
        }
        String email = credentials[0];
        String password = credentials[1];
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BaseException(INVALID_JWT);
        }
        if (!user.get().getPassword().equals(password)) {
            throw new BaseException(INVALID_JWT);
        }
        String userCredential = user.get().getEmail() + ":" + user.get().getPassword();
        String newToken = Base64.getEncoder().encodeToString(userCredential.getBytes());
        return RefreshTokenDto.Response.builder()
            .accessToken(newToken)
            .refreshToken(newToken)
            .expireTime(3600)
            .build();
    }
    
}
